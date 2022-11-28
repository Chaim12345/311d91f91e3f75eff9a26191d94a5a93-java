package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto;
import org.bouncycastle.tls.crypto.impl.AbstractTlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class JceTlsSecret extends AbstractTlsSecret {
    private static final byte[] SSL3_CONST = generateSSL3Constants();

    /* renamed from: b  reason: collision with root package name */
    protected final JcaTlsCrypto f15039b;

    public JceTlsSecret(JcaTlsCrypto jcaTlsCrypto, byte[] bArr) {
        super(bArr);
        this.f15039b = jcaTlsCrypto;
    }

    public static JceTlsSecret convert(JcaTlsCrypto jcaTlsCrypto, TlsSecret tlsSecret) {
        if (tlsSecret instanceof JceTlsSecret) {
            return (JceTlsSecret) tlsSecret;
        }
        if (tlsSecret instanceof AbstractTlsSecret) {
            return jcaTlsCrypto.a(AbstractTlsSecret.c((AbstractTlsSecret) tlsSecret));
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
        return this.f15039b;
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
                return this.f15039b.a(f(i2, str, bArr, i3));
            } else {
                return TlsCryptoUtils.hkdfExpandLabel(this, 7, str, bArr, i3);
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    protected void e(String str, byte[] bArr, int i2, int i3, byte[] bArr2, byte[] bArr3) {
        String str2 = "Hmac" + str;
        Mac createMac = this.f15039b.getHelper().createMac(str2);
        createMac.init(new SecretKeySpec(bArr, i2, i3, str2));
        int macLength = createMac.getMacLength();
        byte[] bArr4 = new byte[macLength];
        byte[] bArr5 = new byte[macLength];
        int i4 = 0;
        byte[] bArr6 = bArr2;
        while (i4 < bArr3.length) {
            createMac.update(bArr6, 0, bArr6.length);
            createMac.doFinal(bArr4, 0);
            createMac.update(bArr4, 0, macLength);
            createMac.update(bArr2, 0, bArr2.length);
            createMac.doFinal(bArr5, 0);
            System.arraycopy(bArr5, 0, bArr3, i4, Math.min(macLength, bArr3.length - i4));
            i4 += macLength;
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
        byte[] bArr2 = this.f14924a;
        int length = (bArr2.length + 1) / 2;
        byte[] bArr3 = new byte[i2];
        e(MessageDigestAlgorithms.MD5, bArr2, 0, length, bArr, bArr3);
        byte[] bArr4 = new byte[i2];
        byte[] bArr5 = this.f14924a;
        e("SHA1", bArr5, bArr5.length - length, length, bArr, bArr4);
        for (int i3 = 0; i3 < i2; i3++) {
            bArr3[i3] = (byte) (bArr3[i3] ^ bArr4[i3]);
        }
        return bArr3;
    }

    protected byte[] h(int i2, byte[] bArr, int i3) {
        String replaceAll = this.f15039b.s(TlsCryptoUtils.getHashForPRF(i2)).replaceAll(HelpFormatter.DEFAULT_OPT_PREFIX, "");
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = this.f14924a;
        e(replaceAll, bArr3, 0, bArr3.length, bArr, bArr2);
        return bArr2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret hkdfExpand(int i2, byte[] bArr, int i3) {
        if (i3 < 1) {
            return this.f15039b.a(TlsUtils.EMPTY_BYTES);
        }
        int hashOutputSize = TlsCryptoUtils.getHashOutputSize(i2);
        if (i3 > hashOutputSize * 255) {
            throw new IllegalArgumentException("'length' must be <= 255 * (output size of 'hashAlgorithm')");
        }
        a();
        byte[] bArr2 = this.f14924a;
        try {
            String t2 = this.f15039b.t(i2);
            Mac createMac = this.f15039b.getHelper().createMac(t2);
            createMac.init(new SecretKeySpec(bArr2, 0, bArr2.length, t2));
            byte[] bArr3 = new byte[i3];
            byte[] bArr4 = new byte[hashOutputSize];
            byte b2 = 0;
            int i4 = 0;
            while (true) {
                createMac.update(bArr, 0, bArr.length);
                b2 = (byte) (b2 + 1);
                createMac.update(b2);
                createMac.doFinal(bArr4, 0);
                int i5 = i3 - i4;
                if (i5 <= hashOutputSize) {
                    System.arraycopy(bArr4, 0, bArr3, i4, i5);
                    return this.f15039b.a(bArr3);
                }
                System.arraycopy(bArr4, 0, bArr3, i4, hashOutputSize);
                i4 += hashOutputSize;
                createMac.update(bArr4, 0, hashOutputSize);
            }
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized TlsSecret hkdfExtract(int i2, TlsSecret tlsSecret) {
        Mac createMac;
        a();
        byte[] bArr = this.f14924a;
        this.f14924a = null;
        try {
            String t2 = this.f15039b.t(i2);
            createMac = this.f15039b.getHelper().createMac(t2);
            createMac.init(new SecretKeySpec(bArr, 0, bArr.length, t2));
            convert(this.f15039b, tlsSecret).j(createMac);
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException(e2);
        }
        return this.f15039b.a(createMac.doFinal());
    }

    protected byte[] i(byte[] bArr, int i2) {
        MessageDigest createDigest = this.f15039b.getHelper().createDigest(MessageDigestAlgorithms.MD5);
        MessageDigest createDigest2 = this.f15039b.getHelper().createDigest("SHA-1");
        int digestLength = createDigest.getDigestLength();
        int digestLength2 = createDigest2.getDigestLength();
        byte[] bArr2 = new byte[Math.max(digestLength, digestLength2)];
        byte[] bArr3 = new byte[i2];
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            createDigest2.update(SSL3_CONST, i5, i3);
            int i6 = i3 + 1;
            i5 += i3;
            byte[] bArr4 = this.f14924a;
            createDigest2.update(bArr4, 0, bArr4.length);
            createDigest2.update(bArr, 0, bArr.length);
            createDigest2.digest(bArr2, 0, digestLength2);
            byte[] bArr5 = this.f14924a;
            createDigest.update(bArr5, 0, bArr5.length);
            createDigest.update(bArr2, 0, digestLength2);
            int i7 = i2 - i4;
            if (i7 < digestLength) {
                createDigest.digest(bArr2, 0, digestLength);
                System.arraycopy(bArr2, 0, bArr3, i4, i7);
                i4 += i7;
            } else {
                createDigest.digest(bArr3, i4, digestLength);
                i4 += digestLength;
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
