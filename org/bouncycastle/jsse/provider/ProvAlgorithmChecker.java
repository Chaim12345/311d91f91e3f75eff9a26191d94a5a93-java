package org.bouncycastle.jsse.provider;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCertificate;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
class ProvAlgorithmChecker extends PKIXCertPathChecker {
    private final BCAlgorithmConstraints algorithmConstraints;
    private final JcaJceHelper helper;
    private final boolean isInFipsMode;
    private X509Certificate issuerCert;
    private static final Map<String, String> sigAlgNames = createSigAlgNames();
    private static final Set<String> sigAlgNoParams = createSigAlgNoParams();
    private static final byte[] DER_NULL_ENCODING = {5, 0};
    private static final String SIG_ALG_NAME_rsa_pss_pss_sha256 = JsseUtils.v("SHA256withRSAandMGF1", "RSASSA-PSS");
    private static final String SIG_ALG_NAME_rsa_pss_pss_sha384 = JsseUtils.v("SHA384withRSAandMGF1", "RSASSA-PSS");
    private static final String SIG_ALG_NAME_rsa_pss_pss_sha512 = JsseUtils.v("SHA512withRSAandMGF1", "RSASSA-PSS");
    private static final String SIG_ALG_NAME_rsa_pss_rsae_sha256 = JsseUtils.v("SHA256withRSAandMGF1", "RSA");
    private static final String SIG_ALG_NAME_rsa_pss_rsae_sha384 = JsseUtils.v("SHA384withRSAandMGF1", "RSA");
    private static final String SIG_ALG_NAME_rsa_pss_rsae_sha512 = JsseUtils.v("SHA512withRSAandMGF1", "RSA");

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvAlgorithmChecker(boolean z, JcaJceHelper jcaJceHelper, BCAlgorithmConstraints bCAlgorithmConstraints) {
        Objects.requireNonNull(jcaJceHelper, "'helper' cannot be null");
        Objects.requireNonNull(bCAlgorithmConstraints, "'algorithmConstraints' cannot be null");
        this.isInFipsMode = z;
        this.helper = jcaJceHelper;
        this.algorithmConstraints = bCAlgorithmConstraints;
        this.issuerCert = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(JcaJceHelper jcaJceHelper, BCAlgorithmConstraints bCAlgorithmConstraints, X509Certificate[] x509CertificateArr, KeyPurposeId keyPurposeId, int i2) {
        X509Certificate x509Certificate = x509CertificateArr[x509CertificateArr.length - 1];
        if (x509CertificateArr.length > 1) {
            checkIssuedBy(jcaJceHelper, bCAlgorithmConstraints, x509CertificateArr[x509CertificateArr.length - 2], x509Certificate);
        }
        checkEndEntity(jcaJceHelper, bCAlgorithmConstraints, x509CertificateArr[0], keyPurposeId, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(boolean z, JcaJceHelper jcaJceHelper, BCAlgorithmConstraints bCAlgorithmConstraints, Set set, X509Certificate[] x509CertificateArr, KeyPurposeId keyPurposeId, int i2) {
        int length = x509CertificateArr.length;
        while (length > 0 && set.contains(x509CertificateArr[length - 1])) {
            length--;
        }
        if (length < x509CertificateArr.length) {
            X509Certificate x509Certificate = x509CertificateArr[length];
            if (length > 0) {
                checkIssuedBy(jcaJceHelper, bCAlgorithmConstraints, x509CertificateArr[length - 1], x509Certificate);
            }
        } else {
            checkIssued(jcaJceHelper, bCAlgorithmConstraints, x509CertificateArr[length - 1]);
        }
        ProvAlgorithmChecker provAlgorithmChecker = new ProvAlgorithmChecker(z, jcaJceHelper, bCAlgorithmConstraints);
        provAlgorithmChecker.init(false);
        for (int i3 = length - 1; i3 >= 0; i3--) {
            provAlgorithmChecker.check(x509CertificateArr[i3], Collections.emptySet());
        }
        checkEndEntity(jcaJceHelper, bCAlgorithmConstraints, x509CertificateArr[0], keyPurposeId, i2);
    }

    static String c(KeyPurposeId keyPurposeId) {
        if (KeyPurposeId.id_kp_clientAuth.equals(keyPurposeId)) {
            return "clientAuth";
        }
        if (KeyPurposeId.id_kp_serverAuth.equals(keyPurposeId)) {
            return "serverAuth";
        }
        return "(" + keyPurposeId + ")";
    }

    private static void checkEndEntity(JcaJceHelper jcaJceHelper, BCAlgorithmConstraints bCAlgorithmConstraints, X509Certificate x509Certificate, KeyPurposeId keyPurposeId, int i2) {
        if (keyPurposeId != null && !j(x509Certificate, keyPurposeId)) {
            throw new CertPathValidatorException("Certificate doesn't support '" + c(keyPurposeId) + "' ExtendedKeyUsage");
        } else if (i2 >= 0) {
            if (!l(x509Certificate, i2)) {
                throw new CertPathValidatorException("Certificate doesn't support '" + d(i2) + "' KeyUsage");
            } else if (bCAlgorithmConstraints.permits(e(i2), x509Certificate.getPublicKey())) {
            } else {
                throw new CertPathValidatorException("Public key not permitted for '" + d(i2) + "' KeyUsage");
            }
        }
    }

    private static void checkIssued(JcaJceHelper jcaJceHelper, BCAlgorithmConstraints bCAlgorithmConstraints, X509Certificate x509Certificate) {
        String f2 = f(x509Certificate, null);
        if (!JsseUtils.Q(f2)) {
            throw new CertPathValidatorException("Signature algorithm could not be determined");
        }
        if (bCAlgorithmConstraints.permits(JsseUtils.f13898c, f2, g(jcaJceHelper, x509Certificate))) {
            return;
        }
        throw new CertPathValidatorException("Signature algorithm '" + f2 + "' not permitted with given parameters");
    }

    private static void checkIssuedBy(JcaJceHelper jcaJceHelper, BCAlgorithmConstraints bCAlgorithmConstraints, X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        String f2 = f(x509Certificate, x509Certificate2);
        if (!JsseUtils.Q(f2)) {
            throw new CertPathValidatorException("Signature algorithm could not be determined");
        }
        if (bCAlgorithmConstraints.permits(JsseUtils.f13898c, f2, x509Certificate2.getPublicKey(), g(jcaJceHelper, x509Certificate))) {
            return;
        }
        throw new CertPathValidatorException("Signature algorithm '" + f2 + "' not permitted with given parameters and issuer public key");
    }

    private static Map<String, String> createSigAlgNames() {
        HashMap hashMap = new HashMap(4);
        hashMap.put(EdECObjectIdentifiers.id_Ed25519.getId(), EdDSAParameterSpec.Ed25519);
        hashMap.put(EdECObjectIdentifiers.id_Ed448.getId(), EdDSAParameterSpec.Ed448);
        hashMap.put(OIWObjectIdentifiers.dsaWithSHA1.getId(), "SHA1withDSA");
        hashMap.put(X9ObjectIdentifiers.id_dsa_with_sha1.getId(), "SHA1withDSA");
        return Collections.unmodifiableMap(hashMap);
    }

    private static Set<String> createSigAlgNoParams() {
        HashSet hashSet = new HashSet();
        hashSet.add(OIWObjectIdentifiers.dsaWithSHA1.getId());
        hashSet.add(X9ObjectIdentifiers.id_dsa_with_sha1.getId());
        hashSet.add(PKCSObjectIdentifiers.id_RSASSA_PSS.getId());
        return Collections.unmodifiableSet(hashSet);
    }

    static String d(int i2) {
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 4) {
                    return "(" + i2 + ")";
                }
                return "keyAgreement";
            }
            return "keyEncipherment";
        }
        return "digitalSignature";
    }

    static Set e(int i2) {
        return i2 != 2 ? i2 != 4 ? JsseUtils.f13898c : JsseUtils.f13896a : JsseUtils.f13897b;
    }

    static String f(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        ASN1ObjectIdentifier algorithm;
        String sigAlgOID = x509Certificate.getSigAlgOID();
        String str = sigAlgNames.get(sigAlgOID);
        if (str != null) {
            return str;
        }
        if (PKCSObjectIdentifiers.id_RSASSA_PSS.getId().equals(sigAlgOID)) {
            RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(x509Certificate.getSigAlgParams());
            if (rSASSAPSSparams != null && (algorithm = rSASSAPSSparams.getHashAlgorithm().getAlgorithm()) != null) {
                if (x509Certificate2 != null) {
                    x509Certificate = x509Certificate2;
                }
                try {
                    JcaTlsCertificate jcaTlsCertificate = new JcaTlsCertificate((JcaTlsCrypto) null, x509Certificate);
                    if (NISTObjectIdentifiers.id_sha256.equals((ASN1Primitive) algorithm)) {
                        if (jcaTlsCertificate.supportsSignatureAlgorithmCA((short) 9)) {
                            return SIG_ALG_NAME_rsa_pss_pss_sha256;
                        }
                        if (jcaTlsCertificate.supportsSignatureAlgorithmCA((short) 4)) {
                            return SIG_ALG_NAME_rsa_pss_rsae_sha256;
                        }
                    } else if (NISTObjectIdentifiers.id_sha384.equals((ASN1Primitive) algorithm)) {
                        if (jcaTlsCertificate.supportsSignatureAlgorithmCA((short) 10)) {
                            return SIG_ALG_NAME_rsa_pss_pss_sha384;
                        }
                        if (jcaTlsCertificate.supportsSignatureAlgorithmCA((short) 5)) {
                            return SIG_ALG_NAME_rsa_pss_rsae_sha384;
                        }
                    } else if (NISTObjectIdentifiers.id_sha512.equals((ASN1Primitive) algorithm)) {
                        if (jcaTlsCertificate.supportsSignatureAlgorithmCA((short) 11)) {
                            return SIG_ALG_NAME_rsa_pss_pss_sha512;
                        }
                        if (jcaTlsCertificate.supportsSignatureAlgorithmCA((short) 6)) {
                            return SIG_ALG_NAME_rsa_pss_rsae_sha512;
                        }
                    }
                } catch (IOException unused) {
                }
            }
            return null;
        }
        return x509Certificate.getSigAlgName();
    }

    static AlgorithmParameters g(JcaJceHelper jcaJceHelper, X509Certificate x509Certificate) {
        byte[] sigAlgParams = x509Certificate.getSigAlgParams();
        if (sigAlgParams == null) {
            return null;
        }
        String sigAlgOID = x509Certificate.getSigAlgOID();
        if (sigAlgNoParams.contains(sigAlgOID) && Arrays.areEqual(DER_NULL_ENCODING, sigAlgParams)) {
            return null;
        }
        try {
            AlgorithmParameters createAlgorithmParameters = jcaJceHelper.createAlgorithmParameters(sigAlgOID);
            try {
                createAlgorithmParameters.init(sigAlgParams);
                return createAlgorithmParameters;
            } catch (Exception e2) {
                throw new CertPathValidatorException(e2);
            }
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    static boolean h(PublicKey publicKey) {
        try {
            AlgorithmIdentifier algorithm = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm();
            if (X9ObjectIdentifiers.id_ecPublicKey.equals((ASN1Primitive) algorithm.getAlgorithm())) {
                ASN1Encodable parameters = algorithm.getParameters();
                if (parameters != null) {
                    return parameters.toASN1Primitive() instanceof ASN1ObjectIdentifier;
                }
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean i(PublicKey publicKey, boolean[] zArr, int i2, BCAlgorithmConstraints bCAlgorithmConstraints) {
        return m(zArr, i2) && bCAlgorithmConstraints.permits(e(i2), publicKey);
    }

    static boolean j(X509Certificate x509Certificate, KeyPurposeId keyPurposeId) {
        try {
            return k(x509Certificate.getExtendedKeyUsage(), keyPurposeId);
        } catch (CertificateParsingException unused) {
            return false;
        }
    }

    static boolean k(List list, KeyPurposeId keyPurposeId) {
        return list == null || list.contains(keyPurposeId.getId()) || list.contains(KeyPurposeId.anyExtendedKeyUsage.getId());
    }

    static boolean l(X509Certificate x509Certificate, int i2) {
        return m(x509Certificate.getKeyUsage(), i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean m(boolean[] zArr, int i2) {
        return zArr == null || (zArr.length > i2 && zArr[i2]);
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public void check(Certificate certificate, Collection<String> collection) {
        if (!(certificate instanceof X509Certificate)) {
            throw new CertPathValidatorException("checker can only be used for X.509 certificates");
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (this.isInFipsMode && !h(x509Certificate.getPublicKey())) {
            throw new CertPathValidatorException("non-FIPS public key found");
        }
        X509Certificate x509Certificate2 = this.issuerCert;
        if (x509Certificate2 != null) {
            checkIssuedBy(this.helper, this.algorithmConstraints, x509Certificate, x509Certificate2);
        }
        this.issuerCert = x509Certificate;
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public Set<String> getSupportedExtensions() {
        return null;
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public void init(boolean z) {
        if (z) {
            throw new CertPathValidatorException("forward checking not supported");
        }
        this.issuerCert = null;
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public boolean isForwardCheckingSupported() {
        return false;
    }
}
