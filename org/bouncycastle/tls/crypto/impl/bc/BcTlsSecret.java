package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto;
import org.bouncycastle.tls.crypto.impl.AbstractTlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class BcTlsSecret extends AbstractTlsSecret {
    private static final byte[] SSL3_CONST = generateSSL3Constants();

    /* renamed from: b  reason: collision with root package name */
    protected final BcTlsCrypto f14983b;

    public BcTlsSecret(BcTlsCrypto bcTlsCrypto, byte[] bArr) {
        super(bArr);
        this.f14983b = bcTlsCrypto;
    }

    public static BcTlsSecret convert(BcTlsCrypto bcTlsCrypto, TlsSecret tlsSecret) {
        if (tlsSecret instanceof BcTlsSecret) {
            return (BcTlsSecret) tlsSecret;
        }
        if (tlsSecret instanceof AbstractTlsSecret) {
            return bcTlsCrypto.a(AbstractTlsSecret.c((AbstractTlsSecret) tlsSecret));
        }
        throw new IllegalArgumentException("unrecognized TlsSecret - cannot copy data: " + tlsSecret.getClass().getName());
    }

    private static byte[] generateSSL3Constants() {
        byte[] bArr = new byte[120];
        int i2 = 0;
        for (int i3 = 0; i3 < 15; i3++) {
            byte b2 = (byte) (i3 + 65);
            int i4 = 0;
            while (i4 <= i3) {
                bArr[i2] = b2;
                i4++;
                i2++;
            }
        }
        return bArr;
    }

    @Override // org.bouncycastle.tls.crypto.impl.AbstractTlsSecret
    protected AbstractTlsCrypto d() {
        return this.f14983b;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret deriveUsingPRF(int i2, String str, byte[] bArr, int i3) {
        a();
        try {
            if (i2 == 4) {
                return TlsCryptoUtils.hkdfExpandLabel(this, 4, str, bArr, i3);
            } else if (i2 == 5) {
                return TlsCryptoUtils.hkdfExpandLabel(this, 5, str, bArr, i3);
            } else if (i2 != 7) {
                return this.f14983b.a(f(i2, str, bArr, i3));
            } else {
                return TlsCryptoUtils.hkdfExpandLabel(this, 7, str, bArr, i3);
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    protected void e(Digest digest, byte[] bArr, int i2, int i3, byte[] bArr2, byte[] bArr3) {
        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(bArr, i2, i3));
        int macSize = hMac.getMacSize();
        byte[] bArr4 = new byte[macSize];
        byte[] bArr5 = new byte[macSize];
        int i4 = 0;
        byte[] bArr6 = bArr2;
        while (i4 < bArr3.length) {
            hMac.update(bArr6, 0, bArr6.length);
            hMac.doFinal(bArr4, 0);
            hMac.update(bArr4, 0, macSize);
            hMac.update(bArr2, 0, bArr2.length);
            hMac.doFinal(bArr5, 0);
            System.arraycopy(bArr5, 0, bArr3, i4, Math.min(macSize, bArr3.length - i4));
            i4 += macSize;
            bArr6 = bArr4;
        }
    }

    protected byte[] f(int i2, String str, byte[] bArr, int i3) {
        if (i2 == 0) {
            return i(bArr, i3);
        }
        byte[] concatenate = Arrays.concatenate(Strings.toByteArray(str), bArr);
        return 1 == i2 ? g(concatenate, i3) : h(i2, concatenate, i3);
    }

    protected byte[] g(byte[] bArr, int i2) {
        int length = (this.f14924a.length + 1) / 2;
        byte[] bArr2 = new byte[i2];
        e(this.f14983b.createDigest(1), this.f14924a, 0, length, bArr, bArr2);
        byte[] bArr3 = new byte[i2];
        Digest createDigest = this.f14983b.createDigest(2);
        byte[] bArr4 = this.f14924a;
        e(createDigest, bArr4, bArr4.length - length, length, bArr, bArr3);
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr3[i3]);
        }
        return bArr2;
    }

    protected byte[] h(int i2, byte[] bArr, int i3) {
        Digest createDigest = this.f14983b.createDigest(TlsCryptoUtils.getHashForPRF(i2));
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = this.f14924a;
        e(createDigest, bArr3, 0, bArr3.length, bArr, bArr2);
        return bArr2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret hkdfExpand(int i2, byte[] bArr, int i3) {
        if (i3 < 1) {
            return this.f14983b.a(TlsUtils.EMPTY_BYTES);
        }
        int hashOutputSize = TlsCryptoUtils.getHashOutputSize(i2);
        if (i3 > hashOutputSize * 255) {
            throw new IllegalArgumentException("'length' must be <= 255 * (output size of 'hashAlgorithm')");
        }
        a();
        byte[] bArr2 = this.f14924a;
        HMac hMac = new HMac(this.f14983b.createDigest(i2));
        hMac.init(new KeyParameter(bArr2));
        byte[] bArr3 = new byte[i3];
        byte[] bArr4 = new byte[hashOutputSize];
        byte b2 = 0;
        int i4 = 0;
        while (true) {
            hMac.update(bArr, 0, bArr.length);
            b2 = (byte) (b2 + 1);
            hMac.update(b2);
            hMac.doFinal(bArr4, 0);
            int i5 = i3 - i4;
            if (i5 <= hashOutputSize) {
                System.arraycopy(bArr4, 0, bArr3, i4, i5);
                return this.f14983b.a(bArr3);
            }
            System.arraycopy(bArr4, 0, bArr3, i4, hashOutputSize);
            i4 += hashOutputSize;
            hMac.update(bArr4, 0, hashOutputSize);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret hkdfExtract(int i2, TlsSecret tlsSecret) {
        byte[] bArr;
        a();
        byte[] bArr2 = this.f14924a;
        this.f14924a = null;
        HMac hMac = new HMac(this.f14983b.createDigest(i2));
        hMac.init(new KeyParameter(bArr2));
        convert(this.f14983b, tlsSecret).j(hMac);
        bArr = new byte[hMac.getMacSize()];
        hMac.doFinal(bArr, 0);
        return this.f14983b.a(bArr);
    }

    protected byte[] i(byte[] bArr, int i2) {
        int i3 = 1;
        Digest createDigest = this.f14983b.createDigest(1);
        Digest createDigest2 = this.f14983b.createDigest(2);
        int digestSize = createDigest.getDigestSize();
        int digestSize2 = createDigest2.getDigestSize();
        byte[] bArr2 = new byte[Math.max(digestSize, digestSize2)];
        byte[] bArr3 = new byte[i2];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            createDigest2.update(SSL3_CONST, i5, i3);
            int i6 = i3 + 1;
            i5 += i3;
            byte[] bArr4 = this.f14924a;
            createDigest2.update(bArr4, 0, bArr4.length);
            createDigest2.update(bArr, 0, bArr.length);
            createDigest2.doFinal(bArr2, 0);
            byte[] bArr5 = this.f14924a;
            createDigest.update(bArr5, 0, bArr5.length);
            createDigest.update(bArr2, 0, digestSize2);
            int i7 = i2 - i4;
            if (i7 < digestSize) {
                createDigest.doFinal(bArr2, 0);
                System.arraycopy(bArr2, 0, bArr3, i4, i7);
                i4 += i7;
            } else {
                createDigest.doFinal(bArr3, i4);
                i4 += digestSize;
            }
            i3 = i6;
        }
        return bArr3;
    }

    protected synchronized void j(Mac mac) {
        a();
        byte[] bArr = this.f14924a;
        mac.update(bArr, 0, bArr.length);
    }
}
