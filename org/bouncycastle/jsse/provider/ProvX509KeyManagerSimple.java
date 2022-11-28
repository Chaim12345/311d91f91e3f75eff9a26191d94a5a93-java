package org.bouncycastle.jsse.provider;

import java.net.Socket;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
/* loaded from: classes3.dex */
class ProvX509KeyManagerSimple extends BCX509ExtendedKeyManager {
    private final Map<String, Credential> credentials;
    private final JcaJceHelper helper;
    private final boolean isInFipsMode;
    private static final Logger LOG = Logger.getLogger(ProvX509KeyManagerSimple.class.getName());
    private static final Map<String, PublicKeyFilter> FILTERS_CLIENT = createFiltersClient();
    private static final Map<String, PublicKeyFilter> FILTERS_SERVER = createFiltersServer();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class Credential {
        private final String alias;
        private final X509Certificate[] certificateChain;
        private final PrivateKey privateKey;

        Credential(String str, PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.alias = str;
            this.privateKey = privateKey;
            this.certificateChain = x509CertificateArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class DefaultPublicKeyFilter implements PublicKeyFilter {

        /* renamed from: a  reason: collision with root package name */
        final String f14037a;

        /* renamed from: b  reason: collision with root package name */
        final Class f14038b;

        /* renamed from: c  reason: collision with root package name */
        final int f14039c;

        DefaultPublicKeyFilter(String str, Class cls, int i2) {
            this.f14037a = str;
            this.f14038b = cls;
            this.f14039c = i2;
        }

        private boolean appliesTo(PublicKey publicKey) {
            Class cls;
            String str = this.f14037a;
            return (str != null && str.equalsIgnoreCase(JsseUtils.G(publicKey))) || ((cls = this.f14038b) != null && cls.isInstance(publicKey));
        }

        @Override // org.bouncycastle.jsse.provider.ProvX509KeyManagerSimple.PublicKeyFilter
        public boolean accepts(PublicKey publicKey, boolean[] zArr, BCAlgorithmConstraints bCAlgorithmConstraints) {
            return appliesTo(publicKey) && ProvAlgorithmChecker.i(publicKey, zArr, this.f14039c, bCAlgorithmConstraints);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ECPublicKeyFilter13 implements PublicKeyFilter {

        /* renamed from: a  reason: collision with root package name */
        final ASN1ObjectIdentifier f14040a;

        ECPublicKeyFilter13(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
            this.f14040a = aSN1ObjectIdentifier;
        }

        private boolean appliesTo(PublicKey publicKey) {
            if ("EC".equalsIgnoreCase(JsseUtils.G(publicKey)) || ECPublicKey.class.isInstance(publicKey)) {
                return this.f14040a.equals((ASN1Primitive) JsseUtils.C(publicKey));
            }
            return false;
        }

        @Override // org.bouncycastle.jsse.provider.ProvX509KeyManagerSimple.PublicKeyFilter
        public boolean accepts(PublicKey publicKey, boolean[] zArr, BCAlgorithmConstraints bCAlgorithmConstraints) {
            return appliesTo(publicKey) && ProvAlgorithmChecker.i(publicKey, zArr, 0, bCAlgorithmConstraints);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Match implements Comparable<Match> {

        /* renamed from: d  reason: collision with root package name */
        static final Quality f14041d = Quality.MISMATCH_SNI;

        /* renamed from: e  reason: collision with root package name */
        static final Match f14042e = new Match(Quality.NONE, Integer.MAX_VALUE, null);

        /* renamed from: a  reason: collision with root package name */
        final Quality f14043a;

        /* renamed from: b  reason: collision with root package name */
        final int f14044b;

        /* renamed from: c  reason: collision with root package name */
        final Credential f14045c;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public enum Quality {
            OK,
            RSA_MULTI_USE,
            MISMATCH_SNI,
            EXPIRED,
            NONE
        }

        Match(Quality quality, int i2, Credential credential) {
            this.f14043a = quality;
            this.f14044b = i2;
            this.f14045c = credential;
        }

        boolean a() {
            return Quality.OK == this.f14043a && this.f14044b == 0;
        }

        boolean b() {
            return this.f14043a.compareTo(f14041d) < 0;
        }

        @Override // java.lang.Comparable
        public int compareTo(Match match) {
            int compare = Boolean.compare(match.b(), b());
            if (compare == 0) {
                int compare2 = Integer.compare(this.f14044b, match.f14044b);
                return compare2 == 0 ? this.f14043a.compareTo(match.f14043a) : compare2;
            }
            return compare;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public interface PublicKeyFilter {
        boolean accepts(PublicKey publicKey, boolean[] zArr, BCAlgorithmConstraints bCAlgorithmConstraints);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509KeyManagerSimple(boolean z, JcaJceHelper jcaJceHelper, KeyStore keyStore, char[] cArr) {
        this.isInFipsMode = z;
        this.helper = jcaJceHelper;
        this.credentials = loadCredentials(keyStore, cArr);
    }

    private static void addECFilter13(Map<String, PublicKeyFilter> map, int i2) {
        ASN1ObjectIdentifier oid;
        if (!NamedGroup.canBeNegotiated(i2, ProtocolVersion.TLSv13)) {
            throw new IllegalStateException("Invalid named group for TLS 1.3 EC filter");
        }
        String curveName = NamedGroup.getCurveName(i2);
        if (curveName != null && (oid = ECNamedCurveTable.getOID(curveName)) != null) {
            addFilterToMap(map, JsseUtils.x("EC", i2), new ECPublicKeyFilter13(oid));
            return;
        }
        Logger logger = LOG;
        logger.warning("Failed to register public key filter for EC with " + NamedGroup.getText(i2));
    }

    private static void addFilter(Map<String, PublicKeyFilter> map, int i2, String str, Class<? extends PublicKey> cls, String... strArr) {
        DefaultPublicKeyFilter defaultPublicKeyFilter = new DefaultPublicKeyFilter(str, cls, i2);
        for (String str2 : strArr) {
            addFilterToMap(map, str2, defaultPublicKeyFilter);
        }
    }

    private static void addFilter(Map<String, PublicKeyFilter> map, Class<? extends PublicKey> cls, String... strArr) {
        addFilter(map, 0, null, cls, strArr);
    }

    private static void addFilter(Map<String, PublicKeyFilter> map, String str) {
        addFilter(map, 0, str, null, str);
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, int i2, String str, Class<? extends PublicKey> cls, int... iArr) {
        addFilter(map, i2, str, cls, getKeyTypesLegacyServer(iArr));
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, int i2, String str, int... iArr) {
        addFilterLegacyServer(map, i2, str, null, iArr);
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, Class<? extends PublicKey> cls, int... iArr) {
        addFilterLegacyServer(map, 0, null, cls, iArr);
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, String str, int... iArr) {
        addFilterLegacyServer(map, 0, str, iArr);
    }

    private static void addFilterToMap(Map<String, PublicKeyFilter> map, String str, PublicKeyFilter publicKeyFilter) {
        if (map.put(str, publicKeyFilter) != null) {
            throw new IllegalStateException("Duplicate keys in filters");
        }
    }

    private static List<Match> addToMatches(List<Match> list, Match match) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(match);
        return list;
    }

    private String chooseAlias(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        Match bestMatch = getBestMatch(list, principalArr, transportData, z);
        if (bestMatch.compareTo(Match.f14042e) >= 0) {
            LOG.fine("No matching key found");
            return null;
        }
        String str = list.get(bestMatch.f14044b);
        String alias = getAlias(bestMatch);
        Logger logger = LOG;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Found matching key of type: " + str + ", returning alias: " + alias);
        }
        return alias;
    }

    private BCX509Key chooseKeyBC(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        String str;
        BCX509Key createKeyBC;
        Match bestMatch = getBestMatch(list, principalArr, transportData, z);
        if (bestMatch.compareTo(Match.f14042e) >= 0 || (createKeyBC = createKeyBC((str = list.get(bestMatch.f14044b)), bestMatch.f14045c)) == null) {
            LOG.fine("No matching key found");
            return null;
        }
        Logger logger = LOG;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Found matching key of type: " + str + ", from alias: " + getAlias(bestMatch));
        }
        return createKeyBC;
    }

    private static Map<String, PublicKeyFilter> createFiltersClient() {
        HashMap hashMap = new HashMap();
        addFilter(hashMap, EdDSAParameterSpec.Ed25519);
        addFilter(hashMap, EdDSAParameterSpec.Ed448);
        addECFilter13(hashMap, 31);
        addECFilter13(hashMap, 32);
        addECFilter13(hashMap, 33);
        addECFilter13(hashMap, 23);
        addECFilter13(hashMap, 24);
        addECFilter13(hashMap, 25);
        addFilter(hashMap, "RSA");
        addFilter(hashMap, "RSASSA-PSS");
        addFilter(hashMap, DSAPublicKey.class, "DSA");
        addFilter(hashMap, ECPublicKey.class, "EC");
        return Collections.unmodifiableMap(hashMap);
    }

    private static Map<String, PublicKeyFilter> createFiltersServer() {
        HashMap hashMap = new HashMap();
        addFilter(hashMap, EdDSAParameterSpec.Ed25519);
        addFilter(hashMap, EdDSAParameterSpec.Ed448);
        addECFilter13(hashMap, 31);
        addECFilter13(hashMap, 32);
        addECFilter13(hashMap, 33);
        addECFilter13(hashMap, 23);
        addECFilter13(hashMap, 24);
        addECFilter13(hashMap, 25);
        addFilter(hashMap, "RSA");
        addFilter(hashMap, "RSASSA-PSS");
        addFilterLegacyServer(hashMap, DSAPublicKey.class, 3, 22);
        addFilterLegacyServer(hashMap, ECPublicKey.class, 17);
        addFilterLegacyServer(hashMap, "RSA", 5, 19, 23);
        addFilterLegacyServer(hashMap, 2, "RSA", 1);
        return Collections.unmodifiableMap(hashMap);
    }

    private BCX509Key createKeyBC(String str, Credential credential) {
        if (credential == null) {
            return null;
        }
        return new ProvX509Key(str, credential.privateKey, credential.certificateChain);
    }

    private static String getAlias(Match match) {
        return match.f14045c.alias;
    }

    private static String[] getAliases(List<Match> list) {
        String[] strArr = new String[list.size()];
        int i2 = 0;
        for (Match match : list) {
            strArr[i2] = getAlias(match);
            i2++;
        }
        return strArr;
    }

    private String[] getAliases(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        if (this.credentials.isEmpty() || list.isEmpty()) {
            return null;
        }
        int size = list.size();
        Set<Principal> uniquePrincipals = getUniquePrincipals(principalArr);
        BCAlgorithmConstraints c2 = TransportData.c(transportData, true);
        Date date = new Date();
        String requestedHostName = getRequestedHostName(transportData, z);
        List<Match> list2 = null;
        for (Credential credential : this.credentials.values()) {
            List<Match> list3 = list2;
            Match potentialMatch = getPotentialMatch(credential, list, size, uniquePrincipals, c2, z, date, requestedHostName);
            list2 = potentialMatch.compareTo(Match.f14042e) < 0 ? addToMatches(list3, potentialMatch) : list3;
        }
        List<Match> list4 = list2;
        if (list4 == null || list4.isEmpty()) {
            return null;
        }
        Collections.sort(list4);
        return getAliases(list4);
    }

    private Match getBestMatch(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        boolean z2;
        Match match = Match.f14042e;
        if (this.credentials.isEmpty() || list.isEmpty()) {
            return match;
        }
        int size = list.size();
        Set<Principal> uniquePrincipals = getUniquePrincipals(principalArr);
        BCAlgorithmConstraints c2 = TransportData.c(transportData, true);
        Date date = new Date();
        String requestedHostName = getRequestedHostName(transportData, z);
        Match match2 = match;
        int i2 = size;
        for (Credential credential : this.credentials.values()) {
            int i3 = i2;
            Match match3 = match2;
            match2 = getPotentialMatch(credential, list, i2, uniquePrincipals, c2, z, date, requestedHostName);
            if (match2.compareTo(match3) >= 0) {
                z2 = true;
                i2 = i3;
                match2 = match3;
            } else if (match2.a()) {
                return match2;
            } else {
                if (match2.b()) {
                    z2 = true;
                    i2 = Math.min(i3, match2.f14044b + 1);
                } else {
                    z2 = true;
                    i2 = i3;
                }
            }
        }
        return match2;
    }

    private static Match.Quality getCertificateQuality(X509Certificate x509Certificate, Date date, String str) {
        try {
            x509Certificate.checkValidity(date);
            if (str != null) {
                try {
                    ProvX509TrustManager.a(str, x509Certificate, "HTTPS");
                } catch (CertificateException unused) {
                    return Match.Quality.MISMATCH_SNI;
                }
            }
            if ("RSA".equalsIgnoreCase(JsseUtils.G(x509Certificate.getPublicKey()))) {
                boolean[] keyUsage = x509Certificate.getKeyUsage();
                if (ProvAlgorithmChecker.m(keyUsage, 0) && ProvAlgorithmChecker.m(keyUsage, 2)) {
                    return Match.Quality.RSA_MULTI_USE;
                }
            }
            return Match.Quality.OK;
        } catch (CertificateException unused2) {
            return Match.Quality.EXPIRED;
        }
    }

    private Credential getCredential(String str) {
        if (str == null) {
            return null;
        }
        return this.credentials.get(str);
    }

    private static List<String> getKeyTypes(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (str == null) {
                throw new IllegalArgumentException("Key types cannot be null");
            }
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static String[] getKeyTypesLegacyServer(int... iArr) {
        int length = iArr.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = JsseUtils.z(iArr[i2]);
        }
        return strArr;
    }

    private Match getPotentialMatch(Credential credential, List<String> list, int i2, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str) {
        int suitableKeyTypeForEECert;
        X509Certificate[] x509CertificateArr = credential.certificateChain;
        if (!TlsUtils.isNullOrEmpty(x509CertificateArr) && isSuitableChainForIssuers(x509CertificateArr, set) && (suitableKeyTypeForEECert = getSuitableKeyTypeForEECert(x509CertificateArr[0], list, i2, bCAlgorithmConstraints, z)) >= 0) {
            String str2 = list.get(suitableKeyTypeForEECert);
            Logger logger = LOG;
            logger.finer("EE cert potentially usable for key type: " + str2);
            if (isSuitableChain(x509CertificateArr, bCAlgorithmConstraints, z)) {
                return new Match(getCertificateQuality(x509CertificateArr[0], date, str), suitableKeyTypeForEECert, credential);
            }
            logger.finer("Unsuitable chain for key type: " + str2);
        }
        return Match.f14042e;
    }

    private static String getRequestedHostName(TransportData transportData, boolean z) {
        BCExtendedSSLSession e2;
        BCSNIHostName H;
        if (transportData == null || !z || (e2 = transportData.e()) == null || (H = JsseUtils.H(e2.getRequestedServerNames())) == null) {
            return null;
        }
        return H.getAsciiName();
    }

    private static int getSuitableKeyTypeForEECert(X509Certificate x509Certificate, List<String> list, int i2, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z) {
        Map<String, PublicKeyFilter> map = z ? FILTERS_SERVER : FILTERS_CLIENT;
        PublicKey publicKey = x509Certificate.getPublicKey();
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        for (int i3 = 0; i3 < i2; i3++) {
            PublicKeyFilter publicKeyFilter = map.get(list.get(i3));
            if (publicKeyFilter != null && publicKeyFilter.accepts(publicKey, keyUsage, bCAlgorithmConstraints)) {
                return i3;
            }
        }
        return -1;
    }

    private static Set<Principal> getUniquePrincipals(Principal[] principalArr) {
        if (principalArr == null) {
            return null;
        }
        if (principalArr.length > 0) {
            HashSet hashSet = new HashSet();
            for (Principal principal : principalArr) {
                if (principal != null) {
                    hashSet.add(principal);
                }
            }
            if (!hashSet.isEmpty()) {
                return Collections.unmodifiableSet(hashSet);
            }
        }
        return Collections.emptySet();
    }

    private boolean isSuitableChain(X509Certificate[] x509CertificateArr, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z) {
        try {
            ProvAlgorithmChecker.b(this.isInFipsMode, this.helper, bCAlgorithmConstraints, Collections.emptySet(), x509CertificateArr, ProvX509KeyManager.b(z), -1);
            return true;
        } catch (CertPathValidatorException e2) {
            LOG.log(Level.FINEST, "Certificate chain check failed", (Throwable) e2);
            return false;
        }
    }

    private static boolean isSuitableChainForIssuers(X509Certificate[] x509CertificateArr, Set<Principal> set) {
        if (set == null || set.isEmpty()) {
            return true;
        }
        int length = x509CertificateArr.length;
        do {
            length--;
            if (length < 0) {
                X509Certificate x509Certificate = x509CertificateArr[0];
                return x509Certificate.getBasicConstraints() >= 0 && set.contains(x509Certificate.getSubjectX500Principal());
            }
        } while (!set.contains(x509CertificateArr[length].getIssuerX500Principal()));
        return true;
    }

    private static Map<String, Credential> loadCredentials(KeyStore keyStore, char[] cArr) {
        PrivateKey privateKey;
        HashMap hashMap = new HashMap(4);
        if (keyStore != null) {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String nextElement = aliases.nextElement();
                if (keyStore.entryInstanceOf(nextElement, KeyStore.PrivateKeyEntry.class) && (privateKey = (PrivateKey) keyStore.getKey(nextElement, cArr)) != null) {
                    X509Certificate[] P = JsseUtils.P(keyStore.getCertificateChain(nextElement));
                    if (!TlsUtils.isNullOrEmpty(P)) {
                        hashMap.put(nextElement, new Credential(nextElement, privateKey, P));
                    }
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    protected BCX509Key a(String str, String str2) {
        return createKeyBC(str, getCredential(str2));
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return chooseAlias(getKeyTypes(strArr), principalArr, TransportData.a(socket), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseClientKeyBC(String[] strArr, Principal[] principalArr, Socket socket) {
        return chooseKeyBC(getKeyTypes(strArr), principalArr, TransportData.a(socket), false);
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseAlias(getKeyTypes(strArr), principalArr, TransportData.b(sSLEngine), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseEngineClientKeyBC(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseKeyBC(getKeyTypes(strArr), principalArr, TransportData.b(sSLEngine), false);
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseAlias(getKeyTypes(str), principalArr, TransportData.b(sSLEngine), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseEngineServerKeyBC(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseKeyBC(getKeyTypes(strArr), principalArr, TransportData.b(sSLEngine), true);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return chooseAlias(getKeyTypes(str), principalArr, TransportData.a(socket), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseServerKeyBC(String[] strArr, Principal[] principalArr, Socket socket) {
        return chooseKeyBC(getKeyTypes(strArr), principalArr, TransportData.a(socket), true);
    }

    @Override // javax.net.ssl.X509KeyManager
    public X509Certificate[] getCertificateChain(String str) {
        Credential credential = getCredential(str);
        if (credential == null) {
            return null;
        }
        return (X509Certificate[]) credential.certificateChain.clone();
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, false);
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        Credential credential = getCredential(str);
        if (credential == null) {
            return null;
        }
        return credential.privateKey;
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, true);
    }
}
