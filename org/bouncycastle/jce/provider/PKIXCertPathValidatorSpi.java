package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;
/* loaded from: classes3.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi() {
        this(false);
    }

    public PKIXCertPathValidatorSpi(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    static void a(X509Certificate x509Certificate) {
        if (x509Certificate instanceof BCX509Certificate) {
            RuntimeException runtimeException = null;
            try {
                if (((BCX509Certificate) x509Certificate).getTBSCertificateNative() != null) {
                    return;
                }
            } catch (RuntimeException e2) {
                runtimeException = e2;
            }
            throw new AnnotatedException("unable to process TBSCertificate", runtimeException);
        }
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e3) {
            throw new AnnotatedException(e3.getMessage());
        } catch (CertificateEncodingException e4) {
            throw new AnnotatedException("unable to process TBSCertificate", e4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.security.PublicKey, int] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) {
        PKIXExtendedParameters pKIXExtendedParameters;
        int i2;
        List<? extends Certificate> list;
        X500Name a2;
        PublicKey cAPublicKey;
        HashSet hashSet;
        int i3;
        ArrayList[] arrayListArr;
        List list2;
        int i4;
        HashSet hashSet2;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else if (!(certPathParameters instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        } else {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (pKIXExtendedParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Date q2 = CertPathValidatorUtilities.q(pKIXExtendedParameters, new Date());
        Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
        try {
            TrustAnchor e2 = CertPathValidatorUtilities.e((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
            if (e2 == null) {
                i2 = 1;
                list = certificates;
                try {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                } catch (AnnotatedException e3) {
                    e = e3;
                    throw new CertPathValidatorException(e.getMessage(), e.a(), certPath, list.size() - i2);
                }
            }
            a(e2.getTrustedCert());
            PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(e2).build();
            int i5 = size + 1;
            ArrayList[] arrayListArr2 = new ArrayList[i5];
            for (int i6 = 0; i6 < i5; i6++) {
                arrayListArr2[i6] = new ArrayList();
            }
            HashSet hashSet3 = new HashSet();
            hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
            arrayListArr2[0].add(new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false));
            PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
            HashSet hashSet4 = new HashSet();
            int i7 = build.isExplicitPolicyRequired() ? 0 : i5;
            int i8 = build.isAnyPolicyInhibited() ? 0 : i5;
            if (build.isPolicyMappingInhibited()) {
                i5 = 0;
            }
            X509Certificate trustedCert = e2.getTrustedCert();
            try {
                if (trustedCert != null) {
                    a2 = PrincipalUtils.e(trustedCert);
                    cAPublicKey = trustedCert.getPublicKey();
                } else {
                    a2 = PrincipalUtils.a(e2);
                    cAPublicKey = e2.getCAPublicKey();
                }
                try {
                    AlgorithmIdentifier h2 = CertPathValidatorUtilities.h(cAPublicKey);
                    h2.getAlgorithm();
                    h2.getParameters();
                    if (build.getTargetConstraints() == null || build.getTargetConstraints().match((Certificate) ((X509Certificate) certificates.get(0)))) {
                        List<PKIXCertPathChecker> certPathCheckers = build.getCertPathCheckers();
                        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
                            pKIXCertPathChecker.init(false);
                        }
                        ProvCrlRevocationChecker provCrlRevocationChecker = build.isRevocationEnabled() ? new ProvCrlRevocationChecker(this.helper) : null;
                        boolean z = true;
                        TrustAnchor trustAnchor = e2;
                        int i9 = size;
                        X509Certificate x509Certificate = null;
                        ?? r5 = i5;
                        int i10 = i8;
                        PKIXPolicyNode pKIXPolicyNode = r5;
                        int i11 = i7;
                        int size2 = certificates.size() - 1;
                        int i12 = i11;
                        int i13 = r5;
                        while (size2 >= 0) {
                            int i14 = size - size2;
                            int i15 = size;
                            X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                            boolean z2 = size2 == certificates.size() + (-1) ? z : false;
                            try {
                                a(x509Certificate2);
                                List<? extends Certificate> list3 = certificates;
                                int i16 = i12;
                                int i17 = size2;
                                Date date = q2;
                                Date date2 = q2;
                                int i18 = i13;
                                ProvCrlRevocationChecker provCrlRevocationChecker2 = provCrlRevocationChecker;
                                ProvCrlRevocationChecker provCrlRevocationChecker3 = provCrlRevocationChecker;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator2 = pKIXNameConstraintValidator;
                                ?? r7 = cAPublicKey;
                                ArrayList[] arrayListArr3 = arrayListArr2;
                                boolean z3 = z2;
                                TrustAnchor trustAnchor2 = trustAnchor;
                                PKIXExtendedParameters pKIXExtendedParameters2 = build;
                                X500Name x500Name = a2;
                                List list4 = certPathCheckers;
                                boolean z4 = z;
                                RFC3280CertPathUtilities.y(certPath, build, date, provCrlRevocationChecker2, i17, r7, z3, x500Name, trustedCert);
                                RFC3280CertPathUtilities.z(certPath, i17, pKIXNameConstraintValidator2, this.isForCRLCheck);
                                PKIXPolicyNode B = RFC3280CertPathUtilities.B(certPath, i17, RFC3280CertPathUtilities.A(certPath, i17, hashSet4, pKIXPolicyNode, arrayListArr3, r7, this.isForCRLCheck));
                                RFC3280CertPathUtilities.C(certPath, i17, B, i16);
                                if (x500Name != i15) {
                                    if (x509Certificate2 == null || x509Certificate2.getVersion() != z4) {
                                        RFC3280CertPathUtilities.c(certPath, i17);
                                        arrayListArr = arrayListArr3;
                                        PKIXPolicyNode b2 = RFC3280CertPathUtilities.b(certPath, i17, arrayListArr, B, i18);
                                        RFC3280CertPathUtilities.d(certPath, i17, pKIXNameConstraintValidator2);
                                        int e4 = RFC3280CertPathUtilities.e(certPath, i17, i16);
                                        int f2 = RFC3280CertPathUtilities.f(certPath, i17, i18);
                                        int g2 = RFC3280CertPathUtilities.g(certPath, i17, r7);
                                        i4 = RFC3280CertPathUtilities.h(certPath, i17, e4);
                                        i3 = RFC3280CertPathUtilities.i(certPath, i17, f2);
                                        int j2 = RFC3280CertPathUtilities.j(certPath, i17, g2);
                                        RFC3280CertPathUtilities.k(certPath, i17);
                                        i9 = RFC3280CertPathUtilities.m(certPath, i17, RFC3280CertPathUtilities.l(certPath, i17, i9));
                                        RFC3280CertPathUtilities.n(certPath, i17);
                                        Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                        if (criticalExtensionOIDs != null) {
                                            hashSet2 = new HashSet(criticalExtensionOIDs);
                                            hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                            hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                            hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                            hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                            hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                            hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                            hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                            hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                            hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                            hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                        } else {
                                            hashSet2 = new HashSet();
                                        }
                                        list2 = list4;
                                        RFC3280CertPathUtilities.o(certPath, i17, hashSet2, list2);
                                        X500Name e5 = PrincipalUtils.e(x509Certificate2);
                                        try {
                                            PublicKey n2 = CertPathValidatorUtilities.n(certPath.getCertificates(), i17, this.helper);
                                            AlgorithmIdentifier h3 = CertPathValidatorUtilities.h(n2);
                                            h3.getAlgorithm();
                                            h3.getParameters();
                                            pKIXPolicyNode = b2;
                                            i10 = j2;
                                            a2 = e5;
                                            cAPublicKey = n2;
                                            trustedCert = x509Certificate2;
                                            arrayListArr2 = arrayListArr;
                                            certPathCheckers = list2;
                                            x509Certificate = x509Certificate2;
                                            z = z4;
                                            certificates = list3;
                                            q2 = date2;
                                            build = pKIXExtendedParameters2;
                                            size = i15;
                                            i12 = i4;
                                            trustAnchor = trustAnchor2;
                                            i13 = i3;
                                            size2 = i17 - 1;
                                            pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                            provCrlRevocationChecker = provCrlRevocationChecker3;
                                        } catch (CertPathValidatorException e6) {
                                            throw new CertPathValidatorException("Next working key could not be retrieved.", e6, certPath, i17);
                                        }
                                    } else if (x500Name != z4 || !x509Certificate2.equals(trustAnchor2.getTrustedCert())) {
                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i17);
                                    }
                                }
                                i3 = i18;
                                int i19 = i9;
                                arrayListArr = arrayListArr3;
                                list2 = list4;
                                pKIXPolicyNode = B;
                                i10 = i19;
                                i9 = i19;
                                i4 = i16;
                                arrayListArr2 = arrayListArr;
                                certPathCheckers = list2;
                                x509Certificate = x509Certificate2;
                                z = z4;
                                certificates = list3;
                                q2 = date2;
                                build = pKIXExtendedParameters2;
                                size = i15;
                                i12 = i4;
                                trustAnchor = trustAnchor2;
                                i13 = i3;
                                size2 = i17 - 1;
                                pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                provCrlRevocationChecker = provCrlRevocationChecker3;
                            } catch (AnnotatedException e7) {
                                throw new CertPathValidatorException(e7.getMessage(), e7.a(), certPath, size2);
                            }
                        }
                        TrustAnchor trustAnchor3 = trustAnchor;
                        PKIXExtendedParameters pKIXExtendedParameters3 = build;
                        ArrayList[] arrayListArr4 = arrayListArr2;
                        List list5 = certPathCheckers;
                        int i20 = size2;
                        int i21 = i20 + 1;
                        int E = RFC3280CertPathUtilities.E(certPath, i21, RFC3280CertPathUtilities.D(i12, x509Certificate));
                        Set<String> criticalExtensionOIDs2 = x509Certificate.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs2 != null) {
                            hashSet = new HashSet(criticalExtensionOIDs2);
                            hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                            hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                            hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                            hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                            hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                            hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                            hashSet.remove(Extension.extendedKeyUsage.getId());
                        } else {
                            hashSet = new HashSet();
                        }
                        RFC3280CertPathUtilities.F(certPath, i21, list5, hashSet);
                        PKIXPolicyNode G = RFC3280CertPathUtilities.G(certPath, pKIXExtendedParameters3, initialPolicies, i21, arrayListArr4, pKIXPolicyNode, hashSet4);
                        if (E > 0 || G != null) {
                            return new PKIXCertPathValidatorResult(trustAnchor3, G, x509Certificate.getPublicKey());
                        }
                        throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i20);
                    }
                    throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                } catch (CertPathValidatorException e8) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e8, certPath, -1);
                }
            } catch (RuntimeException e9) {
                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e9, certPath, -1);
            }
        } catch (AnnotatedException e10) {
            e = e10;
            i2 = 1;
            list = certificates;
        }
    }
}
