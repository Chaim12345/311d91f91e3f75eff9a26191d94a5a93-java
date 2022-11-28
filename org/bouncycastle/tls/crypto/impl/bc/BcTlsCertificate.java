package org.bouncycastle.tls.crypto.impl.bc;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsEncryptor;
import org.bouncycastle.tls.crypto.TlsVerifier;
import org.bouncycastle.tls.crypto.impl.RSAUtil;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BcTlsCertificate implements TlsCertificate {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsCrypto f14967a;

    /* renamed from: b  reason: collision with root package name */
    protected final Certificate f14968b;

    /* renamed from: c  reason: collision with root package name */
    protected RSAKeyParameters f14969c;

    public BcTlsCertificate(BcTlsCrypto bcTlsCrypto, Certificate certificate) {
        this.f14969c = null;
        this.f14967a = bcTlsCrypto;
        this.f14968b = certificate;
    }

    public BcTlsCertificate(BcTlsCrypto bcTlsCrypto, byte[] bArr) {
        this(bcTlsCrypto, parseCertificate(bArr));
    }

    public static BcTlsCertificate convert(BcTlsCrypto bcTlsCrypto, TlsCertificate tlsCertificate) {
        return tlsCertificate instanceof BcTlsCertificate ? (BcTlsCertificate) tlsCertificate : new BcTlsCertificate(bcTlsCrypto, tlsCertificate.getEncoded());
    }

    public static Certificate parseCertificate(byte[] bArr) {
        try {
            return Certificate.getInstance(TlsUtils.readASN1Object(bArr));
        } catch (IllegalArgumentException e2) {
            throw new TlsFatalAlert((short) 42, (Throwable) e2);
        }
    }

    protected AsymmetricKeyParameter a() {
        try {
            return PublicKeyFactory.createKey(this.f14968b.getSubjectPublicKeyInfo());
        } catch (RuntimeException e2) {
            throw new TlsFatalAlert((short) 43, (Throwable) e2);
        }
    }

    protected boolean b(int i2) {
        KeyUsage fromExtensions;
        Extensions extensions = this.f14968b.getTBSCertificate().getExtensions();
        return extensions == null || (fromExtensions = KeyUsage.fromExtensions(extensions)) == null || ((fromExtensions.getBytes()[0] & 255) & i2) == i2;
    }

    protected boolean c() {
        return RSAUtil.supportsPKCS1(this.f14968b.getSubjectPublicKeyInfo().getAlgorithm());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsCertificate checkUsageInRole(int i2) {
        if (i2 == 1) {
            validateKeyUsage(8);
            getPubKeyDH();
            return this;
        } else if (i2 == 2) {
            validateKeyUsage(8);
            getPubKeyEC();
            return this;
        } else {
            throw new TlsFatalAlert((short) 46);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsEncryptor createEncryptor(int i2) {
        validateKeyUsage(32);
        if (i2 == 3) {
            RSAKeyParameters pubKeyRSA = getPubKeyRSA();
            this.f14969c = pubKeyRSA;
            return new BcTlsRSAEncryptor(this.f14967a, pubKeyRSA);
        }
        throw new TlsFatalAlert((short) 46);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsVerifier createVerifier(int i2) {
        validateKeyUsage(128);
        if (i2 != 513) {
            if (i2 != 515) {
                if (i2 != 1025) {
                    if (i2 != 1027) {
                        if (i2 != 1281) {
                            if (i2 != 1283) {
                                if (i2 != 1537) {
                                    if (i2 != 1539) {
                                        switch (i2) {
                                            case SignatureScheme.rsa_pss_rsae_sha256 /* 2052 */:
                                            case SignatureScheme.rsa_pss_rsae_sha384 /* 2053 */:
                                            case SignatureScheme.rsa_pss_rsae_sha512 /* 2054 */:
                                                i();
                                                return new BcTlsRSAPSSVerifier(this.f14967a, getPubKeyRSA(), i2);
                                            case SignatureScheme.ed25519 /* 2055 */:
                                                return new BcTlsEd25519Verifier(this.f14967a, getPubKeyEd25519());
                                            case SignatureScheme.ed448 /* 2056 */:
                                                return new BcTlsEd448Verifier(this.f14967a, getPubKeyEd448());
                                            case SignatureScheme.rsa_pss_pss_sha256 /* 2057 */:
                                            case SignatureScheme.rsa_pss_pss_sha384 /* 2058 */:
                                            case SignatureScheme.rsa_pss_pss_sha512 /* 2059 */:
                                                h(SignatureScheme.getSignatureAlgorithm(i2));
                                                return new BcTlsRSAPSSVerifier(this.f14967a, getPubKeyRSA(), i2);
                                            default:
                                                switch (i2) {
                                                    case SignatureScheme.ecdsa_brainpoolP256r1tls13_sha256 /* 2074 */:
                                                    case SignatureScheme.ecdsa_brainpoolP384r1tls13_sha384 /* 2075 */:
                                                    case SignatureScheme.ecdsa_brainpoolP512r1tls13_sha512 /* 2076 */:
                                                        break;
                                                    default:
                                                        throw new TlsFatalAlert((short) 46);
                                                }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return new BcTlsECDSA13Verifier(this.f14967a, getPubKeyEC(), i2);
        }
        g();
        return new BcTlsRSAVerifier(this.f14967a, getPubKeyRSA());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsVerifier createVerifier(short s2) {
        switch (s2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return createVerifier(SignatureScheme.from((short) 8, s2));
            default:
                validateKeyUsage(128);
                if (s2 == 1) {
                    g();
                    return new BcTlsRSAVerifier(this.f14967a, getPubKeyRSA());
                } else if (s2 != 2) {
                    if (s2 == 3) {
                        return new BcTlsECDSAVerifier(this.f14967a, getPubKeyEC());
                    }
                    throw new TlsFatalAlert((short) 46);
                } else {
                    return new BcTlsDSAVerifier(this.f14967a, getPubKeyDSS());
                }
        }
    }

    protected boolean d(short s2) {
        return RSAUtil.supportsPSS_PSS(s2, this.f14968b.getSubjectPublicKeyInfo().getAlgorithm());
    }

    protected boolean e() {
        return RSAUtil.supportsPSS_RSAE(this.f14968b.getSubjectPublicKeyInfo().getAlgorithm());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected boolean f(short s2, int i2) {
        if (b(i2)) {
            AsymmetricKeyParameter a2 = a();
            switch (s2) {
                case 1:
                    return c() && (a2 instanceof RSAKeyParameters);
                case 2:
                    return a2 instanceof DSAPublicKeyParameters;
                case 3:
                    break;
                case 4:
                case 5:
                case 6:
                    return e() && (a2 instanceof RSAKeyParameters);
                case 7:
                    return a2 instanceof Ed25519PublicKeyParameters;
                case 8:
                    return a2 instanceof Ed448PublicKeyParameters;
                case 9:
                case 10:
                case 11:
                    return d(s2) && (a2 instanceof RSAKeyParameters);
                default:
                    switch (s2) {
                        case 26:
                        case 27:
                        case 28:
                            break;
                        default:
                            return false;
                    }
            }
            return a2 instanceof ECPublicKeyParameters;
        }
        return false;
    }

    protected void g() {
        if (!c()) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getEncoded() {
        return this.f14968b.getEncoded(ASN1Encoding.DER);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extension extension;
        Extensions extensions = this.f14968b.getTBSCertificate().getExtensions();
        if (extensions == null || (extension = extensions.getExtension(aSN1ObjectIdentifier)) == null) {
            return null;
        }
        return Arrays.clone(extension.getExtnValue().getOctets());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public short getLegacySignatureAlgorithm() {
        AsymmetricKeyParameter a2 = a();
        if (a2.isPrivate()) {
            throw new TlsFatalAlert((short) 80);
        }
        if (b(128)) {
            if (a2 instanceof RSAKeyParameters) {
                return (short) 1;
            }
            if (a2 instanceof DSAPublicKeyParameters) {
                return (short) 2;
            }
            return a2 instanceof ECPublicKeyParameters ? (short) 3 : (short) -1;
        }
        return (short) -1;
    }

    public DHPublicKeyParameters getPubKeyDH() {
        try {
            return (DHPublicKeyParameters) a();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    public DSAPublicKeyParameters getPubKeyDSS() {
        try {
            return (DSAPublicKeyParameters) a();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    public ECPublicKeyParameters getPubKeyEC() {
        try {
            return (ECPublicKeyParameters) a();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    public Ed25519PublicKeyParameters getPubKeyEd25519() {
        try {
            return (Ed25519PublicKeyParameters) a();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    public Ed448PublicKeyParameters getPubKeyEd448() {
        try {
            return (Ed448PublicKeyParameters) a();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    public RSAKeyParameters getPubKeyRSA() {
        try {
            return (RSAKeyParameters) a();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public BigInteger getSerialNumber() {
        return this.f14968b.getSerialNumber().getValue();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public String getSigAlgOID() {
        return this.f14968b.getSignatureAlgorithm().getAlgorithm().getId();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public ASN1Encodable getSigAlgParams() {
        return this.f14968b.getSignatureAlgorithm().getParameters();
    }

    protected void h(short s2) {
        if (!d(s2)) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    protected void i() {
        if (!e()) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithm(short s2) {
        return f(s2, 128);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithmCA(short s2) {
        return f(s2, 4);
    }

    public void validateKeyUsage(int i2) {
        if (!b(i2)) {
            throw new TlsFatalAlert((short) 46);
        }
    }
}
