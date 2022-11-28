package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.engines.ChaCha7539Engine;
import org.bouncycastle.crypto.macs.Poly1305;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class BcChaCha20Poly1305 implements TlsAEADCipherImpl {
    private static final byte[] ZEROES = new byte[15];

    /* renamed from: a  reason: collision with root package name */
    protected final ChaCha7539Engine f14953a = new ChaCha7539Engine();

    /* renamed from: b  reason: collision with root package name */
    protected final Poly1305 f14954b = new Poly1305();

    /* renamed from: c  reason: collision with root package name */
    protected final boolean f14955c;

    /* renamed from: d  reason: collision with root package name */
    protected int f14956d;

    public BcChaCha20Poly1305(boolean z) {
        this.f14955c = z;
    }

    protected void a() {
        byte[] bArr = new byte[64];
        this.f14953a.processBytes(bArr, 0, 64, bArr, 0);
        this.f14954b.init(new KeyParameter(bArr, 0, 32));
        Arrays.fill(bArr, (byte) 0);
    }

    protected void b(byte[] bArr, int i2, int i3) {
        this.f14954b.update(bArr, i2, i3);
        int i4 = i3 % 16;
        if (i4 != 0) {
            this.f14954b.update(ZEROES, 0, 16 - i4);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int doFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (this.f14955c) {
            if (i3 == this.f14953a.processBytes(bArr, i2, i3, bArr2, i4)) {
                b(bArr2, i4, i3);
                byte[] bArr3 = new byte[16];
                Pack.longToLittleEndian(this.f14956d & BodyPartID.bodyIdMax, bArr3, 0);
                Pack.longToLittleEndian(i3 & BodyPartID.bodyIdMax, bArr3, 8);
                this.f14954b.update(bArr3, 0, 16);
                this.f14954b.doFinal(bArr2, i4 + i3);
                return i3 + 16;
            }
            throw new IllegalStateException();
        }
        int i5 = i3 - 16;
        b(bArr, i2, i5);
        byte[] bArr4 = new byte[16];
        Pack.longToLittleEndian(this.f14956d & BodyPartID.bodyIdMax, bArr4, 0);
        Pack.longToLittleEndian(i5 & BodyPartID.bodyIdMax, bArr4, 8);
        this.f14954b.update(bArr4, 0, 16);
        this.f14954b.doFinal(bArr4, 0);
        if (!TlsUtils.constantTimeAreEqual(16, bArr4, 0, bArr, i2 + i5)) {
            throw new TlsFatalAlert((short) 20);
        }
        if (i5 == this.f14953a.processBytes(bArr, i2, i5, bArr2, i4)) {
            return i5;
        }
        throw new IllegalStateException();
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int getOutputSize(int i2) {
        return this.f14955c ? i2 + 16 : i2 - 16;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void init(byte[] bArr, int i2, byte[] bArr2) {
        if (bArr == null || bArr.length != 12 || i2 != 16) {
            throw new TlsFatalAlert((short) 80);
        }
        this.f14953a.init(this.f14955c, new ParametersWithIV(null, bArr));
        a();
        if (bArr2 == null) {
            this.f14956d = 0;
            return;
        }
        this.f14956d = bArr2.length;
        b(bArr2, 0, bArr2.length);
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void setKey(byte[] bArr, int i2, int i3) {
        this.f14953a.init(this.f14955c, new ParametersWithIV(new KeyParameter(bArr, i2, i3), ZEROES, 0, 12));
    }
}
