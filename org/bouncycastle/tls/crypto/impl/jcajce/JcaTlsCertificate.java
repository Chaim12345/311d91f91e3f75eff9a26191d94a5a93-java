package org.bouncycastle.tls.crypto.impl.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.interfaces.DHPublicKey;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsEncryptor;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes4.dex */
public class JcaTlsCertificate implements TlsCertificate {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f14996a;

    /* renamed from: b  reason: collision with root package name */
    protected final X509Certificate f14997b;

    /* renamed from: c  reason: collision with root package name */
    protected DHPublicKey f14998c;

    /* renamed from: d  reason: collision with root package name */
    protected PublicKey f14999d;

    public JcaTlsCertificate(JcaTlsCrypto jcaTlsCrypto, X509Certificate x509Certificate) {
        this.f14999d = null;
        this.f14996a = jcaTlsCrypto;
        this.f14997b = x509Certificate;
    }

    public JcaTlsCertificate(JcaTlsCrypto jcaTlsCrypto, byte[] bArr) {
        this(jcaTlsCrypto, parseCertificate(jcaTlsCrypto.getHelper(), bArr));
    }

    public static JcaTlsCertificate convert(JcaTlsCrypto jcaTlsCrypto, TlsCertificate tlsCertificate) {
        return tlsCertificate instanceof JcaTlsCertificate ? (JcaTlsCertificate) tlsCertificate : new JcaTlsCertificate(jcaTlsCrypto, tlsCertificate.getEncoded());
    }

    public static X509Certificate parseCertificate(JcaJceHelper jcaJceHelper, byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Certificate.getInstance(TlsUtils.readASN1Object(bArr)).getEncoded(ASN1Encoding.DER));
            X509Certificate x509Certificate = (X509Certificate) jcaJceHelper.createCertificateFactory("X.509").generateCertificate(byteArrayInputStream);
            if (byteArrayInputStream.available() == 0) {
                return x509Certificate;
            }
            throw new IOException("Extra data detected in stream");
        } catch (GeneralSecurityException e2) {
            throw new TlsCryptoException("unable to decode certificate", e2);
        }
    }

    DHPublicKey a() {
        try {
            return (DHPublicKey) g();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    DSAPublicKey b() {
        try {
            return (DSAPublicKey) g();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    ECPublicKey c() {
        try {
            return (ECPublicKey) g();
        } catch (ClassCastException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsCertificate checkUsageInRole(int i2) {
        if (i2 == 1) {
            n(4);
            this.f14998c = a();
            return this;
        } else if (i2 == 2) {
            n(4);
            c();
            return this;
        } else {
            throw new TlsFatalAlert((short) 46);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsEncryptor createEncryptor(int i2) {
        n(2);
        if (i2 == 3) {
            PublicKey f2 = f();
            this.f14999d = f2;
            return new JcaTlsRSAEncryptor(this.f14996a, f2);
        }
        throw new TlsFatalAlert((short) 46);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsVerifier createVerifier(int i2) {
        n(0);
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
                                                q();
                                                return new JcaTlsRSAPSSVerifier(this.f14996a, f(), i2);
                                            case SignatureScheme.ed25519 /* 2055 */:
                                                return new JcaTlsEd25519Verifier(this.f14996a, d());
                                            case SignatureScheme.ed448 /* 2056 */:
                                                return new JcaTlsEd448Verifier(this.f14996a, e());
                                            case SignatureScheme.rsa_pss_pss_sha256 /* 2057 */:
                                            case SignatureScheme.rsa_pss_pss_sha384 /* 2058 */:
                                            case SignatureScheme.rsa_pss_pss_sha512 /* 2059 */:
                                                p(SignatureScheme.getSignatureAlgorithm(i2));
                                                return new JcaTlsRSAPSSVerifier(this.f14996a, f(), i2);
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
            return new JcaTlsECDSA13Verifier(this.f14996a, c(), i2);
        }
        o();
        return new JcaTlsRSAVerifier(this.f14996a, f());
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
                n(0);
                if (s2 == 1) {
                    o();
                    return new JcaTlsRSAVerifier(this.f14996a, f());
                } else if (s2 != 2) {
                    if (s2 == 3) {
                        return new JcaTlsECDSAVerifier(this.f14996a, c());
                    }
                    throw new TlsFatalAlert((short) 46);
                } else {
                    return new JcaTlsDSAVerifier(this.f14996a, b());
                }
        }
    }

    PublicKey d() {
        PublicKey g2 = g();
        if (EdDSAParameterSpec.Ed25519.equals(g2.getAlgorithm())) {
            return g2;
        }
        throw new TlsFatalAlert((short) 46);
    }

    PublicKey e() {
        PublicKey g2 = g();
        if (EdDSAParameterSpec.Ed448.equals(g2.getAlgorithm())) {
            return g2;
        }
        throw new TlsFatalAlert((short) 46);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicKey f() {
        return g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PublicKey g() {
        try {
            return this.f14997b.getPublicKey();
        } catch (RuntimeException e2) {
            throw new TlsFatalAlert((short) 42, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getEncoded() {
        try {
            return this.f14997b.getEncoded();
        } catch (CertificateEncodingException e2) {
            throw new TlsCryptoException("unable to encode certificate: " + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        byte[] extensionValue = this.f14997b.getExtensionValue(aSN1ObjectIdentifier.getId());
        if (extensionValue == null) {
            return null;
        }
        return ((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public short getLegacySignatureAlgorithm() {
        PublicKey g2 = g();
        if (j(0)) {
            if (g2 instanceof RSAPublicKey) {
                return (short) 1;
            }
            if (g2 instanceof DSAPublicKey) {
                return (short) 2;
            }
            return g2 instanceof ECPublicKey ? (short) 3 : (short) -1;
        }
        return (short) -1;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public BigInteger getSerialNumber() {
        return this.f14997b.getSerialNumber();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public String getSigAlgOID() {
        return this.f14997b.getSigAlgOID();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public ASN1Encodable getSigAlgParams() {
        byte[] sigAlgParams = this.f14997b.getSigAlgParams();
        if (sigAlgParams == null) {
            return null;
        }
        ASN1Primitive readASN1Object = TlsUtils.readASN1Object(sigAlgParams);
        TlsUtils.requireDEREncoding(readASN1Object, sigAlgParams);
        return readASN1Object;
    }

    public X509Certificate getX509Certificate() {
        return this.f14997b;
    }

    protected SubjectPublicKeyInfo h() {
        return SubjectPublicKeyInfo.getInstance(g().getEncoded());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    protected boolean i(short r4) {
        /*
            r3 = this;
            java.security.PublicKey r0 = r3.g()
            r1 = 1
            r2 = 0
            switch(r4) {
                case 1: goto L3f;
                case 2: goto L3c;
                case 3: goto L39;
                case 4: goto L2c;
                case 5: goto L2c;
                case 6: goto L2c;
                case 7: goto L25;
                case 8: goto L1a;
                case 9: goto Ld;
                case 10: goto Ld;
                case 11: goto Ld;
                default: goto L9;
            }
        L9:
            switch(r4) {
                case 26: goto L39;
                case 27: goto L39;
                case 28: goto L39;
                default: goto Lc;
            }
        Lc:
            return r2
        Ld:
            boolean r4 = r3.l(r4)
            if (r4 == 0) goto L18
            boolean r4 = r0 instanceof java.security.interfaces.RSAPublicKey
            if (r4 == 0) goto L18
            goto L19
        L18:
            r1 = r2
        L19:
            return r1
        L1a:
            java.lang.String r4 = r0.getAlgorithm()
            java.lang.String r0 = "Ed448"
        L20:
            boolean r4 = r0.equals(r4)
            return r4
        L25:
            java.lang.String r4 = r0.getAlgorithm()
            java.lang.String r0 = "Ed25519"
            goto L20
        L2c:
            boolean r4 = r3.m()
            if (r4 == 0) goto L37
            boolean r4 = r0 instanceof java.security.interfaces.RSAPublicKey
            if (r4 == 0) goto L37
            goto L38
        L37:
            r1 = r2
        L38:
            return r1
        L39:
            boolean r4 = r0 instanceof java.security.interfaces.ECPublicKey
            return r4
        L3c:
            boolean r4 = r0 instanceof java.security.interfaces.DSAPublicKey
            return r4
        L3f:
            boolean r4 = r3.k()
            if (r4 == 0) goto L4a
            boolean r4 = r0 instanceof java.security.interfaces.RSAPublicKey
            if (r4 == 0) goto L4a
            goto L4b
        L4a:
            r1 = r2
        L4b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCertificate.i(short):boolean");
    }

    protected boolean j(int i2) {
        boolean[] keyUsage = this.f14997b.getKeyUsage();
        return keyUsage == null || (keyUsage.length > i2 && keyUsage[i2]);
    }

    protected boolean k() {
        return org.bouncycastle.tls.crypto.impl.RSAUtil.supportsPKCS1(h().getAlgorithm());
    }

    protected boolean l(short s2) {
        return org.bouncycastle.tls.crypto.impl.RSAUtil.supportsPSS_PSS(s2, h().getAlgorithm());
    }

    protected boolean m() {
        return org.bouncycastle.tls.crypto.impl.RSAUtil.supportsPSS_RSAE(h().getAlgorithm());
    }

    protected void n(int i2) {
        if (!j(i2)) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    protected void o() {
        if (!k()) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    protected void p(short s2) {
        if (!l(s2)) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    protected void q() {
        if (!m()) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithm(short s2) {
        if (j(0)) {
            return i(s2);
        }
        return false;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithmCA(short s2) {
        return i(s2);
    }
}
