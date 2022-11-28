package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public class SignatureAndHashAlgorithm {

    /* renamed from: a  reason: collision with root package name */
    protected final short f14823a;

    /* renamed from: b  reason: collision with root package name */
    protected final short f14824b;
    public static final SignatureAndHashAlgorithm ecdsa_brainpoolP256r1tls13_sha256 = create(SignatureScheme.ecdsa_brainpoolP256r1tls13_sha256);
    public static final SignatureAndHashAlgorithm ecdsa_brainpoolP384r1tls13_sha384 = create(SignatureScheme.ecdsa_brainpoolP384r1tls13_sha384);
    public static final SignatureAndHashAlgorithm ecdsa_brainpoolP512r1tls13_sha512 = create(SignatureScheme.ecdsa_brainpoolP512r1tls13_sha512);
    public static final SignatureAndHashAlgorithm ed25519 = create(SignatureScheme.ed25519);
    public static final SignatureAndHashAlgorithm ed448 = create(SignatureScheme.ed448);
    public static final SignatureAndHashAlgorithm gostr34102012_256 = create(8, 64);
    public static final SignatureAndHashAlgorithm gostr34102012_512 = create(8, 65);
    public static final SignatureAndHashAlgorithm rsa_pss_rsae_sha256 = create(SignatureScheme.rsa_pss_rsae_sha256);
    public static final SignatureAndHashAlgorithm rsa_pss_rsae_sha384 = create(SignatureScheme.rsa_pss_rsae_sha384);
    public static final SignatureAndHashAlgorithm rsa_pss_rsae_sha512 = create(SignatureScheme.rsa_pss_rsae_sha512);
    public static final SignatureAndHashAlgorithm rsa_pss_pss_sha256 = create(SignatureScheme.rsa_pss_pss_sha256);
    public static final SignatureAndHashAlgorithm rsa_pss_pss_sha384 = create(SignatureScheme.rsa_pss_pss_sha384);
    public static final SignatureAndHashAlgorithm rsa_pss_pss_sha512 = create(SignatureScheme.rsa_pss_pss_sha512);

    public SignatureAndHashAlgorithm(short s2, short s3) {
        if ((s2 & 255) != s2) {
            throw new IllegalArgumentException("'hash' should be a uint8");
        }
        if ((s3 & 255) != s3) {
            throw new IllegalArgumentException("'signature' should be a uint8");
        }
        this.f14823a = s2;
        this.f14824b = s3;
    }

    private static SignatureAndHashAlgorithm create(int i2) {
        return create(SignatureScheme.getHashAlgorithm(i2), SignatureScheme.getSignatureAlgorithm(i2));
    }

    private static SignatureAndHashAlgorithm create(short s2, short s3) {
        return new SignatureAndHashAlgorithm(s2, s3);
    }

    public static SignatureAndHashAlgorithm getInstance(short s2, short s3) {
        return s2 != 8 ? create(s2, s3) : getInstanceIntrinsic(s3);
    }

    private static SignatureAndHashAlgorithm getInstanceIntrinsic(short s2) {
        if (s2 != 64) {
            if (s2 != 65) {
                switch (s2) {
                    case 4:
                        return rsa_pss_rsae_sha256;
                    case 5:
                        return rsa_pss_rsae_sha384;
                    case 6:
                        return rsa_pss_rsae_sha512;
                    case 7:
                        return ed25519;
                    case 8:
                        return ed448;
                    case 9:
                        return rsa_pss_pss_sha256;
                    case 10:
                        return rsa_pss_pss_sha384;
                    case 11:
                        return rsa_pss_pss_sha512;
                    default:
                        switch (s2) {
                            case 26:
                                return ecdsa_brainpoolP256r1tls13_sha256;
                            case 27:
                                return ecdsa_brainpoolP384r1tls13_sha384;
                            case 28:
                                return ecdsa_brainpoolP512r1tls13_sha512;
                            default:
                                return create((short) 8, s2);
                        }
                }
            }
            return gostr34102012_512;
        }
        return gostr34102012_256;
    }

    public static SignatureAndHashAlgorithm parse(InputStream inputStream) {
        return getInstance(TlsUtils.readUint8(inputStream), TlsUtils.readUint8(inputStream));
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(getHash(), outputStream);
        TlsUtils.writeUint8(getSignature(), outputStream);
    }

    public boolean equals(Object obj) {
        if (obj instanceof SignatureAndHashAlgorithm) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) obj;
            return signatureAndHashAlgorithm.getHash() == getHash() && signatureAndHashAlgorithm.getSignature() == getSignature();
        }
        return false;
    }

    public short getHash() {
        return this.f14823a;
    }

    public short getSignature() {
        return this.f14824b;
    }

    public int hashCode() {
        return (getHash() << 16) | getSignature();
    }

    public String toString() {
        return "{" + HashAlgorithm.getText(this.f14823a) + "," + SignatureAlgorithm.getText(this.f14824b) + "}";
    }
}
