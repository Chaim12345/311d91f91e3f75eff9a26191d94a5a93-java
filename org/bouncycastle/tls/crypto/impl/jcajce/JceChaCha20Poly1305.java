package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class JceChaCha20Poly1305 implements TlsAEADCipherImpl {
    private static final byte[] ZEROES = new byte[15];

    /* renamed from: a  reason: collision with root package name */
    protected final Cipher f15019a;

    /* renamed from: b  reason: collision with root package name */
    protected final Mac f15020b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f15021c;

    /* renamed from: d  reason: collision with root package name */
    protected SecretKey f15022d;

    /* renamed from: e  reason: collision with root package name */
    protected byte[] f15023e;

    public JceChaCha20Poly1305(JcaJceHelper jcaJceHelper, boolean z) {
        this.f15019a = jcaJceHelper.createCipher("ChaCha7539");
        this.f15020b = jcaJceHelper.createMac("Poly1305");
        this.f15021c = z ? 1 : 2;
    }

    protected void a(byte[] bArr) {
        this.f15020b.init(new SecretKeySpec(bArr, 0, 32, "Poly1305"));
        for (int i2 = 0; i2 < 64; i2++) {
            bArr[i2] = 0;
        }
    }

    protected void b(byte[] bArr) {
        if (bArr.length != this.f15019a.doFinal(bArr, 0, bArr.length, bArr, 0)) {
            throw new IllegalStateException();
        }
    }

    protected void c(byte[] bArr, int i2, int i3) {
        this.f15020b.update(bArr, i2, i3);
        int i4 = i3 % 16;
        if (i4 != 0) {
            this.f15020b.update(ZEROES, 0, 16 - i4);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int doFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        try {
            if (this.f15021c == 1) {
                byte[] bArr3 = new byte[i3 + 64];
                System.arraycopy(bArr, i2, bArr3, 64, i3);
                b(bArr3);
                System.arraycopy(bArr3, 64, bArr2, i4, i3);
                a(bArr3);
                byte[] bArr4 = this.f15023e;
                c(bArr4, 0, bArr4.length);
                c(bArr3, 64, i3);
                byte[] bArr5 = new byte[16];
                Pack.longToLittleEndian(this.f15023e.length & BodyPartID.bodyIdMax, bArr5, 0);
                Pack.longToLittleEndian(i3 & BodyPartID.bodyIdMax, bArr5, 8);
                this.f15020b.update(bArr5, 0, 16);
                this.f15020b.doFinal(bArr2, i4 + i3);
                return i3 + 16;
            }
            int i5 = i3 - 16;
            byte[] bArr6 = new byte[i5 + 64];
            System.arraycopy(bArr, i2, bArr6, 64, i5);
            b(bArr6);
            a(bArr6);
            byte[] bArr7 = this.f15023e;
            c(bArr7, 0, bArr7.length);
            c(bArr, i2, i5);
            byte[] bArr8 = new byte[16];
            Pack.longToLittleEndian(this.f15023e.length & BodyPartID.bodyIdMax, bArr8, 0);
            Pack.longToLittleEndian(BodyPartID.bodyIdMax & i5, bArr8, 8);
            this.f15020b.update(bArr8, 0, 16);
            this.f15020b.doFinal(bArr8, 0);
            if (!TlsUtils.constantTimeAreEqual(16, bArr8, 0, bArr, i2 + i5)) {
                throw new TlsFatalAlert((short) 20);
            }
            System.arraycopy(bArr6, 64, bArr2, i4, i5);
            return i5;
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int getOutputSize(int i2) {
        return this.f15021c == 1 ? i2 + 16 : i2 - 16;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void init(byte[] bArr, int i2, byte[] bArr2) {
        if (bArr == null || bArr.length != 12 || i2 != 16) {
            throw new TlsFatalAlert((short) 80);
        }
        try {
            this.f15019a.init(this.f15021c, this.f15022d, new IvParameterSpec(bArr), (SecureRandom) null);
            this.f15023e = bArr2;
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void setKey(byte[] bArr, int i2, int i3) {
        this.f15022d = new SecretKeySpec(bArr, i2, i3, "ChaCha7539");
    }
}
