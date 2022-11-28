package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.jcajce.PKIXCertStore;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.ExtendedPKIXParameters;
/* loaded from: classes3.dex */
public class PKIXCertPathBuilderSpi_8 extends CertPathBuilderSpi {
    private Exception certPathException;
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathBuilderSpi_8() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PKIXCertPathBuilderSpi_8(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected CertPathBuilderResult a(X509Certificate x509Certificate, PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters, List list) {
        CertificateFactory certificateFactory;
        PKIXCertPathValidatorSpi_8 pKIXCertPathValidatorSpi_8;
        CertPathBuilderResult certPathBuilderResult = null;
        if (list.contains(x509Certificate) || pKIXExtendedBuilderParameters.getExcludedCerts().contains(x509Certificate)) {
            return null;
        }
        if (pKIXExtendedBuilderParameters.getMaxPathLength() == -1 || list.size() - 1 <= pKIXExtendedBuilderParameters.getMaxPathLength()) {
            list.add(x509Certificate);
            try {
                try {
                    certificateFactory = new CertificateFactory();
                    pKIXCertPathValidatorSpi_8 = new PKIXCertPathValidatorSpi_8(this.isForCRLCheck);
                } catch (Exception unused) {
                    throw new RuntimeException("Exception creating support classes.");
                }
            } catch (AnnotatedException e2) {
                this.certPathException = e2;
            }
            if (CertPathValidatorUtilities.s(x509Certificate, pKIXExtendedBuilderParameters.getBaseParameters().getTrustAnchors(), pKIXExtendedBuilderParameters.getBaseParameters().getSigProvider())) {
                try {
                    CertPath engineGenerateCertPath = certificateFactory.engineGenerateCertPath(list);
                    try {
                        PKIXCertPathValidatorResult pKIXCertPathValidatorResult = (PKIXCertPathValidatorResult) pKIXCertPathValidatorSpi_8.engineValidate(engineGenerateCertPath, pKIXExtendedBuilderParameters);
                        return new PKIXCertPathBuilderResult(engineGenerateCertPath, pKIXCertPathValidatorResult.getTrustAnchor(), pKIXCertPathValidatorResult.getPolicyTree(), pKIXCertPathValidatorResult.getPublicKey());
                    } catch (Exception e3) {
                        throw new AnnotatedException("Certification path could not be validated.", e3);
                    }
                } catch (Exception e4) {
                    throw new AnnotatedException("Certification path could not be constructed from certificate list.", e4);
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(pKIXExtendedBuilderParameters.getBaseParameters().getCertificateStores());
            try {
                arrayList.addAll(CertPathValidatorUtilities.f(x509Certificate.getExtensionValue(Extension.issuerAlternativeName.getId()), pKIXExtendedBuilderParameters.getBaseParameters().getNamedCertificateStoreMap()));
                HashSet hashSet = new HashSet();
                try {
                    hashSet.addAll(CertPathValidatorUtilities.c(x509Certificate, pKIXExtendedBuilderParameters.getBaseParameters().getCertStores(), arrayList));
                    if (hashSet.isEmpty()) {
                        throw new AnnotatedException("No issuer certificate for certificate in certification path found.");
                    }
                    Iterator it = hashSet.iterator();
                    while (it.hasNext() && certPathBuilderResult == null) {
                        certPathBuilderResult = a((X509Certificate) it.next(), pKIXExtendedBuilderParameters, list);
                    }
                    if (certPathBuilderResult == null) {
                        list.remove(x509Certificate);
                    }
                    return certPathBuilderResult;
                } catch (AnnotatedException e5) {
                    throw new AnnotatedException("Cannot find issuer certificate for certificate in certification path.", e5);
                }
            } catch (CertificateParsingException e6) {
                throw new AnnotatedException("No additional X.509 stores can be added from certificate locations.", e6);
            }
            this.certPathException = e2;
            if (certPathBuilderResult == null) {
            }
            return certPathBuilderResult;
        }
        return null;
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) {
        PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters;
        Exception exc;
        PKIXExtendedBuilderParameters.Builder builder;
        if (certPathParameters instanceof PKIXBuilderParameters) {
            PKIXBuilderParameters pKIXBuilderParameters = (PKIXBuilderParameters) certPathParameters;
            PKIXExtendedParameters.Builder builder2 = new PKIXExtendedParameters.Builder(pKIXBuilderParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
                for (PKIXCertStore pKIXCertStore : extendedPKIXBuilderParameters.getAdditionalStores()) {
                    builder2.addCertificateStore(pKIXCertStore);
                }
                builder = new PKIXExtendedBuilderParameters.Builder(builder2.build());
                builder.addExcludedCerts(extendedPKIXBuilderParameters.getExcludedCerts());
                builder.setMaxPathLength(extendedPKIXBuilderParameters.getMaxPathLength());
            } else {
                builder = new PKIXExtendedBuilderParameters.Builder(pKIXBuilderParameters);
            }
            pKIXExtendedBuilderParameters = builder.build();
        } else if (!(certPathParameters instanceof PKIXExtendedBuilderParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + ".");
        } else {
            pKIXExtendedBuilderParameters = (PKIXExtendedBuilderParameters) certPathParameters;
        }
        ArrayList arrayList = new ArrayList();
        CertPathBuilderResult certPathBuilderResult = null;
        Iterator it = CertPathValidatorUtilities.d(pKIXExtendedBuilderParameters).iterator();
        while (it.hasNext() && certPathBuilderResult == null) {
            certPathBuilderResult = a((X509Certificate) it.next(), pKIXExtendedBuilderParameters, arrayList);
        }
        if (certPathBuilderResult == null && (exc = this.certPathException) != null) {
            if (exc instanceof AnnotatedException) {
                throw new CertPathBuilderException(this.certPathException.getMessage(), this.certPathException.getCause());
            }
            throw new CertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
        } else if (certPathBuilderResult == null && this.certPathException == null) {
            throw new CertPathBuilderException("Unable to find certificate chain.");
        } else {
            return certPathBuilderResult;
        }
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public PKIXCertPathChecker engineGetRevocationChecker() {
        return new ProvRevocationChecker(this.helper);
    }
}
