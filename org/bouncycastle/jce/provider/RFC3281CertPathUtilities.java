package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.TargetInformation;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.jcajce.PKIXCRLStore;
import org.bouncycastle.jcajce.PKIXCertRevocationCheckerParameters;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.PKIXAttrCertChecker;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CertStoreSelector;
/* loaded from: classes3.dex */
class RFC3281CertPathUtilities {
    private static final String TARGET_INFORMATION = Extension.targetInformation.getId();
    private static final String NO_REV_AVAIL = Extension.noRevAvail.getId();
    private static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTHORITY_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(X509AttributeCertificate x509AttributeCertificate, Set set, Set set2) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (x509AttributeCertificate.getAttributes(str) != null) {
                throw new CertPathValidatorException("Attribute certificate contains prohibited attribute: " + str + ".");
            }
        }
        Iterator it2 = set2.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            if (x509AttributeCertificate.getAttributes(str2) == null) {
                throw new CertPathValidatorException("Attribute certificate does not contain necessary attribute: " + str2 + ".");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void b(X509AttributeCertificate x509AttributeCertificate, PKIXExtendedParameters pKIXExtendedParameters, Date date, Date date2, X509Certificate x509Certificate, List list, JcaJceHelper jcaJceHelper) {
        boolean z;
        String str;
        AnnotatedException annotatedException;
        if (!pKIXExtendedParameters.isRevocationEnabled()) {
            return;
        }
        if (x509AttributeCertificate.getExtensionValue(NO_REV_AVAIL) != null) {
            if (x509AttributeCertificate.getExtensionValue(CRL_DISTRIBUTION_POINTS) != null || x509AttributeCertificate.getExtensionValue(AUTHORITY_INFO_ACCESS) != null) {
                throw new CertPathValidatorException("No rev avail extension is set, but also an AC revocation pointer.");
            }
            return;
        }
        try {
            CRLDistPoint cRLDistPoint = CRLDistPoint.getInstance(CertPathValidatorUtilities.m(x509AttributeCertificate, CRL_DISTRIBUTION_POINTS));
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.addAll(CertPathValidatorUtilities.g(cRLDistPoint, pKIXExtendedParameters.getNamedCRLStoreMap(), date2, jcaJceHelper));
                PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder(pKIXExtendedParameters);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    builder.addCRLStore((PKIXCRLStore) arrayList);
                }
                PKIXExtendedParameters build = builder.build();
                CertStatus certStatus = new CertStatus();
                ReasonsMask reasonsMask = new ReasonsMask();
                String str2 = "No valid CRL for distribution point found.";
                boolean z2 = true;
                if (cRLDistPoint != null) {
                    try {
                        DistributionPoint[] distributionPoints = cRLDistPoint.getDistributionPoints();
                        int i2 = 0;
                        z = false;
                        for (int i3 = 11; i2 < distributionPoints.length && certStatus.getCertStatus() == i3 && !reasonsMask.e(); i3 = 11) {
                            try {
                                int i4 = i2;
                                str = str2;
                                try {
                                    checkCRL(distributionPoints[i2], x509AttributeCertificate, (PKIXExtendedParameters) build.clone(), date, date2, x509Certificate, certStatus, reasonsMask, list, jcaJceHelper);
                                    i2 = i4 + 1;
                                    str2 = str;
                                    z = true;
                                } catch (AnnotatedException e2) {
                                    e = e2;
                                    annotatedException = new AnnotatedException(str, e);
                                    if (certStatus.getCertStatus() == 11) {
                                        try {
                                            try {
                                                checkCRL(new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, PrincipalUtils.b(x509AttributeCertificate)))), null, null), x509AttributeCertificate, (PKIXExtendedParameters) build.clone(), date, date2, x509Certificate, certStatus, reasonsMask, list, jcaJceHelper);
                                            } catch (Exception e3) {
                                                throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", e3);
                                            }
                                        } catch (AnnotatedException e4) {
                                            annotatedException = new AnnotatedException(str, e4);
                                        }
                                        if (z2) {
                                        }
                                    }
                                    z2 = z;
                                    if (z2) {
                                    }
                                }
                            } catch (AnnotatedException e5) {
                                e = e5;
                                str = str2;
                            }
                        }
                        str = str2;
                        annotatedException = null;
                    } catch (Exception e6) {
                        throw new ExtCertPathValidatorException("Distribution points could not be read.", e6);
                    }
                } else {
                    str = "No valid CRL for distribution point found.";
                    annotatedException = null;
                    z = false;
                }
                if (certStatus.getCertStatus() == 11 && !reasonsMask.e()) {
                    checkCRL(new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, PrincipalUtils.b(x509AttributeCertificate)))), null, null), x509AttributeCertificate, (PKIXExtendedParameters) build.clone(), date, date2, x509Certificate, certStatus, reasonsMask, list, jcaJceHelper);
                    if (z2) {
                        throw new ExtCertPathValidatorException("No valid CRL found.", annotatedException);
                    }
                    if (certStatus.getCertStatus() == 11) {
                        if (!reasonsMask.e() && certStatus.getCertStatus() == 11) {
                            certStatus.setCertStatus(12);
                        }
                        if (certStatus.getCertStatus() == 12) {
                            throw new CertPathValidatorException("Attribute certificate status could not be determined.");
                        }
                        return;
                    }
                    throw new CertPathValidatorException(("Attribute certificate revocation after " + certStatus.getRevocationDate()) + ", reason: " + RFC3280CertPathUtilities.f13832a[certStatus.getCertStatus()]);
                }
                z2 = z;
                if (z2) {
                }
            } catch (AnnotatedException e7) {
                throw new CertPathValidatorException("No additional CRL locations could be decoded from CRL distribution point extension.", e7);
            }
        } catch (AnnotatedException e8) {
            throw new CertPathValidatorException("CRL distribution point extension could not be read.", e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static CertPath c(X509AttributeCertificate x509AttributeCertificate, PKIXExtendedParameters pKIXExtendedParameters) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (x509AttributeCertificate.getHolder().getIssuer() != null) {
            X509CertSelector x509CertSelector = new X509CertSelector();
            x509CertSelector.setSerialNumber(x509AttributeCertificate.getHolder().getSerialNumber());
            Principal[] issuer = x509AttributeCertificate.getHolder().getIssuer();
            for (int i2 = 0; i2 < issuer.length; i2++) {
                try {
                    if (issuer[i2] instanceof X500Principal) {
                        x509CertSelector.setIssuer(((X500Principal) issuer[i2]).getEncoded());
                    }
                    CertPathValidatorUtilities.b(linkedHashSet, new PKIXCertStoreSelector.Builder(x509CertSelector).build(), pKIXExtendedParameters.getCertStores());
                } catch (IOException e2) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e2);
                } catch (AnnotatedException e3) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e3);
                }
            }
            if (linkedHashSet.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in base certificate ID for attribute certificate cannot be found.");
            }
        }
        if (x509AttributeCertificate.getHolder().getEntityNames() != null) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            Principal[] entityNames = x509AttributeCertificate.getHolder().getEntityNames();
            for (int i3 = 0; i3 < entityNames.length; i3++) {
                try {
                    if (entityNames[i3] instanceof X500Principal) {
                        x509CertStoreSelector.setIssuer(((X500Principal) entityNames[i3]).getEncoded());
                    }
                    CertPathValidatorUtilities.b(linkedHashSet, new PKIXCertStoreSelector.Builder(x509CertStoreSelector).build(), pKIXExtendedParameters.getCertStores());
                } catch (IOException e4) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e4);
                } catch (AnnotatedException e5) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e5);
                }
            }
            if (linkedHashSet.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in entity name for attribute certificate cannot be found.");
            }
        }
        PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder(pKIXExtendedParameters);
        Iterator it = linkedHashSet.iterator();
        ExtCertPathValidatorException extCertPathValidatorException = null;
        CertPathBuilderResult certPathBuilderResult = null;
        while (it.hasNext()) {
            X509CertStoreSelector x509CertStoreSelector2 = new X509CertStoreSelector();
            x509CertStoreSelector2.setCertificate((X509Certificate) it.next());
            builder.setTargetConstraints(new PKIXCertStoreSelector.Builder(x509CertStoreSelector2).build());
            try {
                try {
                    certPathBuilderResult = CertPathBuilder.getInstance("PKIX", BouncyCastleProvider.PROVIDER_NAME).build(new PKIXExtendedBuilderParameters.Builder(builder.build()).build());
                } catch (InvalidAlgorithmParameterException e6) {
                    throw new RuntimeException(e6.getMessage());
                } catch (CertPathBuilderException e7) {
                    extCertPathValidatorException = new ExtCertPathValidatorException("Certification path for public key certificate of attribute certificate could not be build.", e7);
                }
            } catch (NoSuchAlgorithmException e8) {
                throw new ExtCertPathValidatorException("Support class could not be created.", e8);
            } catch (NoSuchProviderException e9) {
                throw new ExtCertPathValidatorException("Support class could not be created.", e9);
            }
        }
        if (extCertPathValidatorException == null) {
            return certPathBuilderResult.getCertPath();
        }
        throw extCertPathValidatorException;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0101, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void checkCRL(DistributionPoint distributionPoint, X509AttributeCertificate x509AttributeCertificate, PKIXExtendedParameters pKIXExtendedParameters, Date date, Date date2, X509Certificate x509Certificate, CertStatus certStatus, ReasonsMask reasonsMask, List list, JcaJceHelper jcaJceHelper) {
        Iterator it;
        X509CRL x509crl;
        ReasonsMask s2;
        X509CRL x509crl2;
        if (x509AttributeCertificate.getExtensionValue(X509Extensions.NoRevAvail.getId()) != null) {
            return;
        }
        if (date2.getTime() > date.getTime()) {
            throw new AnnotatedException("Validation time is in future.");
        }
        Iterator it2 = CertPathValidatorUtilities.k(new PKIXCertRevocationCheckerParameters(pKIXExtendedParameters, date2, null, -1, x509Certificate, null), distributionPoint, x509AttributeCertificate, pKIXExtendedParameters, date2).iterator();
        boolean z = false;
        AnnotatedException e2 = null;
        while (it2.hasNext() && certStatus.getCertStatus() == 11 && !reasonsMask.e()) {
            try {
                x509crl = (X509CRL) it2.next();
                s2 = RFC3280CertPathUtilities.s(x509crl, distributionPoint);
            } catch (AnnotatedException e3) {
                e2 = e3;
                it = it2;
            }
            if (s2.c(reasonsMask)) {
                it = it2;
                try {
                    PublicKey u = RFC3280CertPathUtilities.u(x509crl, RFC3280CertPathUtilities.t(x509crl, x509AttributeCertificate, null, null, pKIXExtendedParameters, list, jcaJceHelper));
                    if (pKIXExtendedParameters.isUseDeltasEnabled()) {
                        try {
                            x509crl2 = RFC3280CertPathUtilities.v(CertPathValidatorUtilities.l(date, x509crl, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores(), jcaJceHelper), u);
                        } catch (AnnotatedException e4) {
                            e2 = e4;
                            it2 = it;
                        }
                    } else {
                        x509crl2 = null;
                    }
                    if (pKIXExtendedParameters.getValidityModel() != 1) {
                        try {
                            if (x509AttributeCertificate.getNotAfter().getTime() < x509crl.getThisUpdate().getTime()) {
                                throw new AnnotatedException("No valid CRL for current time found.");
                                break;
                            }
                        } catch (AnnotatedException e5) {
                            e2 = e5;
                        }
                    }
                    RFC3280CertPathUtilities.p(distributionPoint, x509AttributeCertificate, x509crl);
                    RFC3280CertPathUtilities.q(distributionPoint, x509AttributeCertificate, x509crl);
                    RFC3280CertPathUtilities.r(x509crl2, x509crl, pKIXExtendedParameters);
                    RFC3280CertPathUtilities.w(date2, x509crl2, x509AttributeCertificate, certStatus, pKIXExtendedParameters);
                    RFC3280CertPathUtilities.x(date2, x509crl, x509AttributeCertificate, certStatus);
                    if (certStatus.getCertStatus() == 8) {
                        certStatus.setCertStatus(11);
                    }
                    reasonsMask.a(s2);
                    z = true;
                } catch (AnnotatedException e6) {
                    e2 = e6;
                }
                it2 = it;
            } else {
                continue;
            }
        }
        throw e2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static CertPathValidatorResult d(CertPath certPath, PKIXExtendedParameters pKIXExtendedParameters) {
        try {
            try {
                return CertPathValidator.getInstance("PKIX", BouncyCastleProvider.PROVIDER_NAME).validate(certPath, pKIXExtendedParameters);
            } catch (InvalidAlgorithmParameterException e2) {
                throw new RuntimeException(e2.getMessage());
            } catch (CertPathValidatorException e3) {
                throw new ExtCertPathValidatorException("Certification path for issuer certificate of attribute certificate could not be validated.", e3);
            }
        } catch (NoSuchAlgorithmException e4) {
            throw new ExtCertPathValidatorException("Support class could not be created.", e4);
        } catch (NoSuchProviderException e5) {
            throw new ExtCertPathValidatorException("Support class could not be created.", e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void e(X509Certificate x509Certificate, PKIXExtendedParameters pKIXExtendedParameters) {
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        if (keyUsage != null && ((keyUsage.length <= 0 || !keyUsage[0]) && (keyUsage.length <= 1 || !keyUsage[1]))) {
            throw new CertPathValidatorException("Attribute certificate issuer public key cannot be used to validate digital signatures.");
        }
        if (x509Certificate.getBasicConstraints() != -1) {
            throw new CertPathValidatorException("Attribute certificate issuer is also a public key certificate issuer.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void f(X509Certificate x509Certificate, Set set) {
        Iterator it = set.iterator();
        boolean z = false;
        while (it.hasNext()) {
            TrustAnchor trustAnchor = (TrustAnchor) it.next();
            if (x509Certificate.getSubjectX500Principal().getName("RFC2253").equals(trustAnchor.getCAName()) || x509Certificate.equals(trustAnchor.getTrustedCert())) {
                z = true;
            }
        }
        if (!z) {
            throw new CertPathValidatorException("Attribute certificate issuer is not directly trusted.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void g(X509AttributeCertificate x509AttributeCertificate, Date date) {
        try {
            x509AttributeCertificate.checkValidity(date);
        } catch (CertificateExpiredException e2) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e2);
        } catch (CertificateNotYetValidException e3) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void h(X509AttributeCertificate x509AttributeCertificate, CertPath certPath, CertPath certPath2, PKIXExtendedParameters pKIXExtendedParameters, Set set) {
        Set<String> criticalExtensionOIDs = x509AttributeCertificate.getCriticalExtensionOIDs();
        String str = TARGET_INFORMATION;
        if (criticalExtensionOIDs.contains(str)) {
            try {
                TargetInformation.getInstance(CertPathValidatorUtilities.m(x509AttributeCertificate, str));
            } catch (IllegalArgumentException e2) {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", e2);
            } catch (AnnotatedException e3) {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", e3);
            }
        }
        criticalExtensionOIDs.remove(str);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((PKIXAttrCertChecker) it.next()).check(x509AttributeCertificate, certPath, certPath2, criticalExtensionOIDs);
        }
        if (criticalExtensionOIDs.isEmpty()) {
            return;
        }
        throw new CertPathValidatorException("Attribute certificate contains unsupported critical extensions: " + criticalExtensionOIDs);
    }
}
