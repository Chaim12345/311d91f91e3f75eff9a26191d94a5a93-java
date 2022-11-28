package org.bouncycastle.jsse.provider;

import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreParameters;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.tls.TlsUtils;
/* loaded from: classes3.dex */
class ProvX509TrustManager extends BCX509ExtendedTrustManager {
    private final X509TrustManager exportX509TrustManager;
    private final JcaJceHelper helper;
    private final boolean isInFipsMode;
    private final PKIXBuilderParameters pkixParametersTemplate;
    private final Set<X509Certificate> trustedCerts;
    private static final Logger LOG = Logger.getLogger(ProvX509TrustManager.class.getName());
    private static final boolean provCheckRevocation = PropertyUtils.b("com.sun.net.ssl.checkRevocation", false);
    private static final boolean provTrustManagerCheckEKU = PropertyUtils.b("org.bouncycastle.jsse.trustManager.checkEKU", true);
    private static final Map<String, Integer> keyUsagesServer = createKeyUsagesServer();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509TrustManager(boolean z, JcaJceHelper jcaJceHelper, PKIXParameters pKIXParameters) {
        this.isInFipsMode = z;
        this.helper = jcaJceHelper;
        Set<X509Certificate> trustedCerts = getTrustedCerts(pKIXParameters.getTrustAnchors());
        this.trustedCerts = trustedCerts;
        if (trustedCerts.isEmpty()) {
            this.pkixParametersTemplate = null;
        } else if (pKIXParameters instanceof PKIXBuilderParameters) {
            this.pkixParametersTemplate = (PKIXBuilderParameters) pKIXParameters;
        } else {
            PKIXBuilderParameters pKIXBuilderParameters = new PKIXBuilderParameters(pKIXParameters.getTrustAnchors(), pKIXParameters.getTargetCertConstraints());
            this.pkixParametersTemplate = pKIXBuilderParameters;
            pKIXBuilderParameters.setAnyPolicyInhibited(pKIXParameters.isAnyPolicyInhibited());
            pKIXBuilderParameters.setCertPathCheckers(pKIXParameters.getCertPathCheckers());
            pKIXBuilderParameters.setCertStores(pKIXParameters.getCertStores());
            pKIXBuilderParameters.setDate(pKIXParameters.getDate());
            pKIXBuilderParameters.setExplicitPolicyRequired(pKIXParameters.isExplicitPolicyRequired());
            pKIXBuilderParameters.setInitialPolicies(pKIXParameters.getInitialPolicies());
            pKIXBuilderParameters.setPolicyMappingInhibited(pKIXParameters.isPolicyMappingInhibited());
            pKIXBuilderParameters.setPolicyQualifiersRejected(pKIXParameters.getPolicyQualifiersRejected());
            pKIXBuilderParameters.setRevocationEnabled(pKIXParameters.isRevocationEnabled());
            pKIXBuilderParameters.setSigProvider(pKIXParameters.getSigProvider());
        }
        this.exportX509TrustManager = X509TrustManagerUtil.a(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509TrustManager(boolean z, JcaJceHelper jcaJceHelper, Set set) {
        this.isInFipsMode = z;
        this.helper = jcaJceHelper;
        Set<X509Certificate> trustedCerts = getTrustedCerts(set);
        this.trustedCerts = trustedCerts;
        if (trustedCerts.isEmpty()) {
            this.pkixParametersTemplate = null;
        } else {
            PKIXBuilderParameters pKIXBuilderParameters = new PKIXBuilderParameters(set, (CertSelector) null);
            this.pkixParametersTemplate = pKIXBuilderParameters;
            pKIXBuilderParameters.setRevocationEnabled(provCheckRevocation);
        }
        this.exportX509TrustManager = X509TrustManagerUtil.a(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str, X509Certificate x509Certificate, String str2) {
        boolean z;
        String X = JsseUtils.X(str);
        if (str2.equalsIgnoreCase("HTTPS")) {
            z = true;
        } else if (!str2.equalsIgnoreCase("LDAP") && !str2.equalsIgnoreCase("LDAPS")) {
            throw new CertificateException("Unknown endpoint ID algorithm: " + str2);
        } else {
            z = false;
        }
        HostnameUtil.a(X, x509Certificate, z);
    }

    private static void addKeyUsageServer(Map<String, Integer> map, int i2, int... iArr) {
        for (int i3 : iArr) {
            if (map.put(JsseUtils.r(i3), Integer.valueOf(i2)) != null) {
                throw new IllegalStateException("Duplicate keys in server key usages");
            }
        }
    }

    private static void addStatusResponses(CertPathBuilder certPathBuilder, PKIXBuilderParameters pKIXBuilderParameters, X509Certificate[] x509CertificateArr, List<byte[]> list) {
        HashMap hashMap = new HashMap();
        int min = Math.min(x509CertificateArr.length, list.size());
        for (int i2 = 0; i2 < min; i2++) {
            byte[] bArr = list.get(i2);
            if (bArr != null && bArr.length > 0) {
                X509Certificate x509Certificate = x509CertificateArr[i2];
                if (!hashMap.containsKey(x509Certificate)) {
                    hashMap.put(x509Certificate, bArr);
                }
            }
        }
        if (hashMap.isEmpty()) {
            return;
        }
        try {
            PKIXUtil.a(certPathBuilder, pKIXBuilderParameters, hashMap);
        } catch (RuntimeException e2) {
            LOG.log(Level.FINE, "Failed to add status responses for revocation checking", (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(X509Certificate[] x509CertificateArr, TransportData transportData, boolean z) {
        if (transportData != null) {
            String endpointIdentificationAlgorithm = transportData.f().getEndpointIdentificationAlgorithm();
            if (JsseUtils.Q(endpointIdentificationAlgorithm)) {
                BCExtendedSSLSession e2 = transportData.e();
                if (e2 == null) {
                    throw new CertificateException("No handshake session");
                }
                checkEndpointID(x509CertificateArr[0], endpointIdentificationAlgorithm, z, e2);
            }
        }
    }

    private X509Certificate[] buildCertPath(X509Certificate[] x509CertificateArr, BCAlgorithmConstraints bCAlgorithmConstraints, List<byte[]> list) {
        CertStore certStore;
        CertPathBuilder certPathBuilder;
        X509Certificate x509Certificate = x509CertificateArr[0];
        if (this.trustedCerts.contains(x509Certificate)) {
            return new X509Certificate[]{x509Certificate};
        }
        Provider provider = this.helper.createCertificateFactory("X.509").getProvider();
        CertStoreParameters certStoreParameters = getCertStoreParameters(x509Certificate, x509CertificateArr);
        try {
            certStore = CertStore.getInstance("Collection", certStoreParameters, provider);
        } catch (GeneralSecurityException unused) {
            certStore = CertStore.getInstance("Collection", certStoreParameters);
        }
        try {
            certPathBuilder = CertPathBuilder.getInstance("PKIX", provider);
        } catch (NoSuchAlgorithmException unused2) {
            certPathBuilder = CertPathBuilder.getInstance("PKIX");
        }
        PKIXBuilderParameters pKIXBuilderParameters = (PKIXBuilderParameters) this.pkixParametersTemplate.clone();
        pKIXBuilderParameters.addCertPathChecker(new ProvAlgorithmChecker(this.isInFipsMode, this.helper, bCAlgorithmConstraints));
        pKIXBuilderParameters.addCertStore(certStore);
        pKIXBuilderParameters.setTargetCertConstraints(createTargetCertConstraints(x509Certificate, pKIXBuilderParameters.getTargetCertConstraints()));
        if (!list.isEmpty()) {
            addStatusResponses(certPathBuilder, pKIXBuilderParameters, x509CertificateArr, list);
        }
        PKIXCertPathBuilderResult pKIXCertPathBuilderResult = (PKIXCertPathBuilderResult) certPathBuilder.build(pKIXBuilderParameters);
        return getTrustedChain(pKIXCertPathBuilderResult.getCertPath(), pKIXCertPathBuilderResult.getTrustAnchor());
    }

    private static void checkEndpointID(X509Certificate x509Certificate, String str, boolean z, BCExtendedSSLSession bCExtendedSSLSession) {
        BCSNIHostName H;
        String peerHost = bCExtendedSSLSession.getPeerHost();
        if (z && (H = JsseUtils.H(bCExtendedSSLSession.getRequestedServerNames())) != null) {
            String asciiName = H.getAsciiName();
            if (!asciiName.equalsIgnoreCase(peerHost)) {
                try {
                    a(asciiName, x509Certificate, str);
                    return;
                } catch (CertificateException e2) {
                    Logger logger = LOG;
                    Level level = Level.FINE;
                    logger.log(level, "Server's endpoint ID did not match the SNI host_name: " + asciiName, (Throwable) e2);
                }
            }
        }
        a(peerHost, x509Certificate, str);
    }

    private void checkTrusted(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) {
        if (TlsUtils.isNullOrEmpty(x509CertificateArr)) {
            throw new IllegalArgumentException("'chain' must be a chain of at least one certificate");
        }
        if (TlsUtils.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("'authType' must be a non-null, non-empty string");
        }
        if (this.pkixParametersTemplate == null) {
            throw new CertificateException("Unable to build a CertPath: no PKIXBuilderParameters available");
        }
        b(validateChain(x509CertificateArr, str, transportData, z), transportData, z);
    }

    private static Map<String, Integer> createKeyUsagesServer() {
        HashMap hashMap = new HashMap();
        addKeyUsageServer(hashMap, 0, 3, 5, 17, 19, 0);
        addKeyUsageServer(hashMap, 2, 1);
        addKeyUsageServer(hashMap, 4, 7, 9, 16, 18);
        return Collections.unmodifiableMap(hashMap);
    }

    private static X509CertSelector createTargetCertConstraints(X509Certificate x509Certificate, CertSelector certSelector) {
        return new X509CertSelector(x509Certificate, certSelector) { // from class: org.bouncycastle.jsse.provider.ProvX509TrustManager.1

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ X509Certificate f14046a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ CertSelector f14047b;

            {
                this.f14046a = x509Certificate;
                this.f14047b = certSelector;
                setCertificate(x509Certificate);
            }

            @Override // java.security.cert.X509CertSelector, java.security.cert.CertSelector
            public boolean match(Certificate certificate) {
                CertSelector certSelector2;
                return super.match(certificate) && ((certSelector2 = this.f14047b) == null || certSelector2.match(certificate));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyPurposeId d(boolean z) {
        if (provTrustManagerCheckEKU) {
            return z ? KeyPurposeId.id_kp_serverAuth : KeyPurposeId.id_kp_clientAuth;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(boolean z, String str) {
        if (z) {
            Integer num = keyUsagesServer.get(str);
            if (num != null) {
                return num.intValue();
            }
            throw new CertificateException("Unsupported server authType: " + str);
        }
        return 0;
    }

    private CertStoreParameters getCertStoreParameters(X509Certificate x509Certificate, X509Certificate[] x509CertificateArr) {
        ArrayList arrayList = new ArrayList(x509CertificateArr.length);
        arrayList.add(x509Certificate);
        for (int i2 = 1; i2 < x509CertificateArr.length; i2++) {
            if (!this.trustedCerts.contains(x509CertificateArr[i2])) {
                arrayList.add(x509CertificateArr[i2]);
            }
        }
        return new CollectionCertStoreParameters(Collections.unmodifiableCollection(arrayList));
    }

    private static X509Certificate getTrustedCert(TrustAnchor trustAnchor) {
        X509Certificate trustedCert = trustAnchor.getTrustedCert();
        if (trustedCert != null) {
            return trustedCert;
        }
        throw new CertificateException("No certificate for TrustAnchor");
    }

    private static Set<X509Certificate> getTrustedCerts(Set<TrustAnchor> set) {
        X509Certificate trustedCert;
        HashSet hashSet = new HashSet(set.size());
        for (TrustAnchor trustAnchor : set) {
            if (trustAnchor != null && (trustedCert = trustAnchor.getTrustedCert()) != null) {
                hashSet.add(trustedCert);
            }
        }
        return hashSet;
    }

    private static X509Certificate[] getTrustedChain(CertPath certPath, TrustAnchor trustAnchor) {
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size() + 1;
        X509Certificate[] x509CertificateArr = new X509Certificate[size];
        certificates.toArray(x509CertificateArr);
        x509CertificateArr[size - 1] = getTrustedCert(trustAnchor);
        return x509CertificateArr;
    }

    private X509Certificate[] validateChain(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) {
        try {
            BCAlgorithmConstraints c2 = TransportData.c(transportData, false);
            X509Certificate[] buildCertPath = buildCertPath(x509CertificateArr, c2, TransportData.h(transportData));
            ProvAlgorithmChecker.a(this.helper, c2, buildCertPath, d(z), e(z, str));
            return buildCertPath;
        } catch (GeneralSecurityException e2) {
            throw new CertificateException("Unable to construct a valid chain", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public X509TrustManager c() {
        return this.exportX509TrustManager;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        checkTrusted(x509CertificateArr, str, null, false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
        checkTrusted(x509CertificateArr, str, TransportData.a(socket), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
        checkTrusted(x509CertificateArr, str, TransportData.b(sSLEngine), false);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        checkTrusted(x509CertificateArr, str, null, true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
        checkTrusted(x509CertificateArr, str, TransportData.a(socket), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
        checkTrusted(x509CertificateArr, str, TransportData.b(sSLEngine), true);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        Set<X509Certificate> set = this.trustedCerts;
        return (X509Certificate[]) set.toArray(new X509Certificate[set.size()]);
    }
}
