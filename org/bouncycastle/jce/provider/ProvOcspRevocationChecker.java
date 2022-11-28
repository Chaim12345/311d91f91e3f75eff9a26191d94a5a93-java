package org.bouncycastle.jce.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertPathValidatorException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.isara.IsaraObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.CertID;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.ocsp.ResponseBytes;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.RevokedInfo;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
import org.bouncycastle.asn1.x500.style.BCStrictStyle;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.internal.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.internal.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.jcajce.PKIXCertRevocationChecker;
import org.bouncycastle.jcajce.PKIXCertRevocationCheckerParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.MessageDigestUtils;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvOcspRevocationChecker implements PKIXCertRevocationChecker {
    private static final int DEFAULT_OCSP_MAX_RESPONSE_SIZE = 32768;
    private static final int DEFAULT_OCSP_TIMEOUT = 15000;
    private static final Map oids;
    private final JcaJceHelper helper;
    private boolean isEnabledOCSP;
    private String ocspURL;
    private PKIXCertRevocationCheckerParameters parameters;
    private final ProvRevocationChecker parent;

    static {
        HashMap hashMap = new HashMap();
        oids = hashMap;
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3411WITHGOST3410");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "GOST3411WITHECGOST3410");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256, "GOST3411-2012-256WITHECGOST3410-2012-256");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512, "GOST3411-2012-512WITHECGOST3410-2012-512");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA1, "SHA1WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA224, "SHA224WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA256, "SHA256WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA384, "SHA384WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA512, "SHA512WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_RIPEMD160, "RIPEMD160WITHPLAIN-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512WITHCVC-ECDSA");
        hashMap.put(IsaraObjectIdentifiers.id_alg_xmss, "XMSS");
        hashMap.put(IsaraObjectIdentifiers.id_alg_xmssmt, "XMSSMT");
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        hashMap.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        hashMap.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        hashMap.put(NISTObjectIdentifiers.dsa_with_sha224, "SHA224WITHDSA");
        hashMap.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
    }

    public ProvOcspRevocationChecker(ProvRevocationChecker provRevocationChecker, JcaJceHelper jcaJceHelper) {
        this.parent = provRevocationChecker;
        this.helper = jcaJceHelper;
    }

    static URI a(X509Certificate x509Certificate) {
        byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityInfoAccess.getId());
        if (extensionValue == null) {
            return null;
        }
        AccessDescription[] accessDescriptions = AuthorityInformationAccess.getInstance(ASN1OctetString.getInstance(extensionValue).getOctets()).getAccessDescriptions();
        for (int i2 = 0; i2 != accessDescriptions.length; i2++) {
            AccessDescription accessDescription = accessDescriptions[i2];
            if (AccessDescription.id_ad_ocsp.equals((ASN1Primitive) accessDescription.getAccessMethod())) {
                GeneralName accessLocation = accessDescription.getAccessLocation();
                if (accessLocation.getTagNo() == 6) {
                    try {
                        return new URI(((ASN1String) accessLocation.getName()).getString());
                    } catch (URISyntaxException unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(BasicOCSPResponse basicOCSPResponse, PKIXCertRevocationCheckerParameters pKIXCertRevocationCheckerParameters, byte[] bArr, X509Certificate x509Certificate, JcaJceHelper jcaJceHelper) {
        try {
            ASN1Sequence certs = basicOCSPResponse.getCerts();
            Signature createSignature = jcaJceHelper.createSignature(getSignatureName(basicOCSPResponse.getSignatureAlgorithm()));
            X509Certificate signerCert = getSignerCert(basicOCSPResponse, pKIXCertRevocationCheckerParameters.getSigningCert(), x509Certificate, jcaJceHelper);
            if (signerCert == null && certs == null) {
                throw new CertPathValidatorException("OCSP responder certificate not found");
            }
            if (signerCert != null) {
                createSignature.initVerify(signerCert.getPublicKey());
            } else {
                X509Certificate x509Certificate2 = (X509Certificate) jcaJceHelper.createCertificateFactory("X.509").generateCertificate(new ByteArrayInputStream(certs.getObjectAt(0).toASN1Primitive().getEncoded()));
                x509Certificate2.verify(pKIXCertRevocationCheckerParameters.getSigningCert().getPublicKey());
                x509Certificate2.checkValidity(pKIXCertRevocationCheckerParameters.getValidDate());
                if (!responderMatches(basicOCSPResponse.getTbsResponseData().getResponderID(), x509Certificate2, jcaJceHelper)) {
                    throw new CertPathValidatorException("responder certificate does not match responderID", null, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
                }
                List<String> extendedKeyUsage = x509Certificate2.getExtendedKeyUsage();
                if (extendedKeyUsage == null || !extendedKeyUsage.contains(KeyPurposeId.id_kp_OCSPSigning.getId())) {
                    throw new CertPathValidatorException("responder certificate not valid for signing OCSP responses", null, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
                }
                createSignature.initVerify(x509Certificate2);
            }
            createSignature.update(basicOCSPResponse.getTbsResponseData().getEncoded(ASN1Encoding.DER));
            if (createSignature.verify(basicOCSPResponse.getSignature().getBytes())) {
                if (bArr != null && !Arrays.areEqual(bArr, basicOCSPResponse.getTbsResponseData().getResponseExtensions().getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce).getExtnValue().getOctets())) {
                    throw new CertPathValidatorException("nonce mismatch in OCSP response", null, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
                }
                return true;
            }
            return false;
        } catch (IOException e2) {
            throw new CertPathValidatorException("OCSP response failure: " + e2.getMessage(), e2, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
        } catch (CertPathValidatorException e3) {
            throw e3;
        } catch (GeneralSecurityException e4) {
            throw new CertPathValidatorException("OCSP response failure: " + e4.getMessage(), e4, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
        }
    }

    private static byte[] calcKeyHash(MessageDigest messageDigest, PublicKey publicKey) {
        return messageDigest.digest(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getPublicKeyData().getBytes());
    }

    private CertID createCertID(CertID certID, Certificate certificate, ASN1Integer aSN1Integer) {
        return createCertID(certID.getHashAlgorithm(), certificate, aSN1Integer);
    }

    private CertID createCertID(AlgorithmIdentifier algorithmIdentifier, Certificate certificate, ASN1Integer aSN1Integer) {
        try {
            MessageDigest createMessageDigest = this.helper.createMessageDigest(MessageDigestUtils.getDigestName(algorithmIdentifier.getAlgorithm()));
            return new CertID(algorithmIdentifier, new DEROctetString(createMessageDigest.digest(certificate.getSubject().getEncoded(ASN1Encoding.DER))), new DEROctetString(createMessageDigest.digest(certificate.getSubjectPublicKeyInfo().getPublicKeyData().getBytes())), aSN1Integer);
        } catch (Exception e2) {
            throw new CertPathValidatorException("problem creating ID: " + e2, e2);
        }
    }

    private Certificate extractCert() {
        try {
            return Certificate.getInstance(this.parameters.getSigningCert().getEncoded());
        } catch (Exception e2) {
            throw new CertPathValidatorException("cannot process signing cert: " + e2.getMessage(), e2, this.parameters.getCertPath(), this.parameters.getIndex());
        }
    }

    private static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String digestName = MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
        int indexOf = digestName.indexOf(45);
        if (indexOf <= 0 || digestName.startsWith("SHA3")) {
            return digestName;
        }
        return digestName.substring(0, indexOf) + digestName.substring(indexOf + 1);
    }

    private static String getSignatureName(AlgorithmIdentifier algorithmIdentifier) {
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        if (parameters == null || DERNull.INSTANCE.equals(parameters) || !algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            Map map = oids;
            boolean containsKey = map.containsKey(algorithmIdentifier.getAlgorithm());
            ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
            return containsKey ? (String) map.get(algorithm) : algorithm.getId();
        }
        RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(parameters);
        return getDigestName(rSASSAPSSparams.getHashAlgorithm().getAlgorithm()) + "WITHRSAANDMGF1";
    }

    private static X509Certificate getSignerCert(BasicOCSPResponse basicOCSPResponse, X509Certificate x509Certificate, X509Certificate x509Certificate2, JcaJceHelper jcaJceHelper) {
        ResponderID responderID = basicOCSPResponse.getTbsResponseData().getResponderID();
        byte[] keyHash = responderID.getKeyHash();
        if (keyHash != null) {
            MessageDigest createMessageDigest = jcaJceHelper.createMessageDigest("SHA1");
            if (x509Certificate2 == null || !Arrays.areEqual(keyHash, calcKeyHash(createMessageDigest, x509Certificate2.getPublicKey()))) {
                if (x509Certificate == null || !Arrays.areEqual(keyHash, calcKeyHash(createMessageDigest, x509Certificate.getPublicKey()))) {
                    return null;
                }
                return x509Certificate;
            }
            return x509Certificate2;
        }
        X500NameStyle x500NameStyle = BCStrictStyle.INSTANCE;
        X500Name x500Name = X500Name.getInstance(x500NameStyle, responderID.getName());
        if (x509Certificate2 == null || !x500Name.equals(X500Name.getInstance(x500NameStyle, x509Certificate2.getSubjectX500Principal().getEncoded()))) {
            if (x509Certificate == null || !x500Name.equals(X500Name.getInstance(x500NameStyle, x509Certificate.getSubjectX500Principal().getEncoded()))) {
                return null;
            }
            return x509Certificate;
        }
        return x509Certificate2;
    }

    private static boolean responderMatches(ResponderID responderID, X509Certificate x509Certificate, JcaJceHelper jcaJceHelper) {
        byte[] keyHash = responderID.getKeyHash();
        if (keyHash != null) {
            return Arrays.areEqual(keyHash, calcKeyHash(jcaJceHelper.createMessageDigest("SHA1"), x509Certificate.getPublicKey()));
        }
        X500NameStyle x500NameStyle = BCStrictStyle.INSTANCE;
        return X500Name.getInstance(x500NameStyle, responderID.getName()).equals(X500Name.getInstance(x500NameStyle, x509Certificate.getSubjectX500Principal().getEncoded()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x01a0, code lost:
        if (r0.getHashAlgorithm().equals(r1.getCertID().getHashAlgorithm()) != false) goto L59;
     */
    @Override // org.bouncycastle.jcajce.PKIXCertRevocationChecker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void check(java.security.cert.Certificate certificate) {
        byte[] bArr;
        boolean z;
        RevokedInfo revokedInfo;
        X509Certificate x509Certificate = (X509Certificate) certificate;
        Map<X509Certificate, byte[]> ocspResponses = this.parent.getOcspResponses();
        URI ocspResponder = this.parent.getOcspResponder();
        if (ocspResponder == null) {
            if (this.ocspURL != null) {
                try {
                    ocspResponder = new URI(this.ocspURL);
                } catch (URISyntaxException e2) {
                    throw new CertPathValidatorException("configuration error: " + e2.getMessage(), e2, this.parameters.getCertPath(), this.parameters.getIndex());
                }
            } else {
                ocspResponder = a(x509Certificate);
            }
        }
        URI uri = ocspResponder;
        if (ocspResponses.get(x509Certificate) != null || uri == null) {
            List<java.security.cert.Extension> ocspExtensions = this.parent.getOcspExtensions();
            bArr = null;
            for (int i2 = 0; i2 != ocspExtensions.size(); i2++) {
                java.security.cert.Extension extension = ocspExtensions.get(i2);
                byte[] value = extension.getValue();
                if (OCSPObjectIdentifiers.id_pkix_ocsp_nonce.getId().equals(extension.getId())) {
                    bArr = value;
                }
            }
            z = false;
        } else if (this.ocspURL == null && this.parent.getOcspResponder() == null && !this.isEnabledOCSP) {
            throw new RecoverableCertPathValidatorException("OCSP disabled by \"ocsp.enable\" setting", null, this.parameters.getCertPath(), this.parameters.getIndex());
        } else {
            try {
                ocspResponses.put(x509Certificate, OcspCache.a(createCertID(new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1), extractCert(), new ASN1Integer(x509Certificate.getSerialNumber())), this.parameters, uri, this.parent.getOcspResponderCert(), this.parent.getOcspExtensions(), this.helper).getEncoded());
                z = true;
                bArr = null;
            } catch (IOException e3) {
                throw new CertPathValidatorException("unable to encode OCSP response", e3, this.parameters.getCertPath(), this.parameters.getIndex());
            }
        }
        if (ocspResponses.isEmpty()) {
            throw new RecoverableCertPathValidatorException("no OCSP response found for any certificate", null, this.parameters.getCertPath(), this.parameters.getIndex());
        }
        OCSPResponse oCSPResponse = OCSPResponse.getInstance(ocspResponses.get(x509Certificate));
        ASN1Integer aSN1Integer = new ASN1Integer(x509Certificate.getSerialNumber());
        if (oCSPResponse == null) {
            throw new RecoverableCertPathValidatorException("no OCSP response found for certificate", null, this.parameters.getCertPath(), this.parameters.getIndex());
        }
        if (oCSPResponse.getResponseStatus().getIntValue() != 0) {
            throw new CertPathValidatorException("OCSP response failed: " + oCSPResponse.getResponseStatus().getValue(), null, this.parameters.getCertPath(), this.parameters.getIndex());
        }
        ResponseBytes responseBytes = ResponseBytes.getInstance(oCSPResponse.getResponseBytes());
        if (responseBytes.getResponseType().equals((ASN1Primitive) OCSPObjectIdentifiers.id_pkix_ocsp_basic)) {
            try {
                BasicOCSPResponse basicOCSPResponse = BasicOCSPResponse.getInstance(responseBytes.getResponse().getOctets());
                if (z || b(basicOCSPResponse, this.parameters, bArr, this.parent.getOcspResponderCert(), this.helper)) {
                    ASN1Sequence responses = ResponseData.getInstance(basicOCSPResponse.getTbsResponseData()).getResponses();
                    CertID certID = null;
                    for (int i3 = 0; i3 != responses.size(); i3++) {
                        SingleResponse singleResponse = SingleResponse.getInstance(responses.getObjectAt(i3));
                        if (aSN1Integer.equals((ASN1Primitive) singleResponse.getCertID().getSerialNumber())) {
                            ASN1GeneralizedTime nextUpdate = singleResponse.getNextUpdate();
                            if (nextUpdate != null && this.parameters.getValidDate().after(nextUpdate.getDate())) {
                                throw new ExtCertPathValidatorException("OCSP response expired");
                            }
                            certID = createCertID(singleResponse.getCertID(), extractCert(), aSN1Integer);
                            if (certID.equals(singleResponse.getCertID())) {
                                if (singleResponse.getCertStatus().getTagNo() == 0) {
                                    return;
                                }
                                if (singleResponse.getCertStatus().getTagNo() != 1) {
                                    throw new CertPathValidatorException("certificate revoked, details unknown", null, this.parameters.getCertPath(), this.parameters.getIndex());
                                }
                                throw new CertPathValidatorException("certificate revoked, reason=(" + RevokedInfo.getInstance(singleResponse.getCertStatus().getStatus()).getRevocationReason() + "), date=" + revokedInfo.getRevocationTime().getDate(), null, this.parameters.getCertPath(), this.parameters.getIndex());
                            }
                        }
                    }
                }
            } catch (CertPathValidatorException e4) {
                throw e4;
            } catch (Exception e5) {
                throw new CertPathValidatorException("unable to process OCSP response", e5, this.parameters.getCertPath(), this.parameters.getIndex());
            }
        }
    }

    public List<CertPathValidatorException> getSoftFailExceptions() {
        return null;
    }

    public Set<String> getSupportedExtensions() {
        return null;
    }

    public void init(boolean z) {
        if (z) {
            throw new CertPathValidatorException("forward checking not supported");
        }
        this.parameters = null;
        this.isEnabledOCSP = Properties.isOverrideSet("ocsp.enable");
        this.ocspURL = Properties.getPropertyValue("ocsp.responderURL");
    }

    @Override // org.bouncycastle.jcajce.PKIXCertRevocationChecker
    public void initialize(PKIXCertRevocationCheckerParameters pKIXCertRevocationCheckerParameters) {
        this.parameters = pKIXCertRevocationCheckerParameters;
        this.isEnabledOCSP = Properties.isOverrideSet("ocsp.enable");
        this.ocspURL = Properties.getPropertyValue("ocsp.responderURL");
    }

    public boolean isForwardCheckingSupported() {
        return false;
    }

    @Override // org.bouncycastle.jcajce.PKIXCertRevocationChecker
    public void setParameter(String str, Object obj) {
    }
}
