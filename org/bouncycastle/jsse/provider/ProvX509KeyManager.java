package org.bouncycastle.jsse.provider;

import java.lang.ref.SoftReference;
import java.net.Socket;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.KeyPurposeId;
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
class ProvX509KeyManager extends BCX509ExtendedKeyManager {
    private final List<KeyStore.Builder> builders;
    private final JcaJceHelper helper;
    private final boolean isInFipsMode;
    private static final Logger LOG = Logger.getLogger(ProvX509KeyManager.class.getName());
    private static final boolean provKeyManagerCheckEKU = PropertyUtils.b("org.bouncycastle.jsse.keyManager.checkEKU", true);
    private static final Map<String, PublicKeyFilter> FILTERS_CLIENT = createFiltersClient();
    private static final Map<String, PublicKeyFilter> FILTERS_SERVER = createFiltersServer();
    private final AtomicLong versions = new AtomicLong();
    private final Map<String, SoftReference<KeyStore.PrivateKeyEntry>> cachedEntries = Collections.synchronizedMap(new LinkedHashMap<String, SoftReference<KeyStore.PrivateKeyEntry>>(this, 16, 0.75f, true) { // from class: org.bouncycastle.jsse.provider.ProvX509KeyManager.1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, SoftReference<KeyStore.PrivateKeyEntry>> entry) {
            return size() > 16;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class DefaultPublicKeyFilter implements PublicKeyFilter {

        /* renamed from: a  reason: collision with root package name */
        final String f14025a;

        /* renamed from: b  reason: collision with root package name */
        final Class f14026b;

        /* renamed from: c  reason: collision with root package name */
        final int f14027c;

        DefaultPublicKeyFilter(String str, Class cls, int i2) {
            this.f14025a = str;
            this.f14026b = cls;
            this.f14027c = i2;
        }

        private boolean appliesTo(PublicKey publicKey) {
            Class cls;
            String str = this.f14025a;
            return (str != null && str.equalsIgnoreCase(JsseUtils.G(publicKey))) || ((cls = this.f14026b) != null && cls.isInstance(publicKey));
        }

        @Override // org.bouncycastle.jsse.provider.ProvX509KeyManager.PublicKeyFilter
        public boolean accepts(PublicKey publicKey, boolean[] zArr, BCAlgorithmConstraints bCAlgorithmConstraints) {
            return appliesTo(publicKey) && ProvAlgorithmChecker.i(publicKey, zArr, this.f14027c, bCAlgorithmConstraints);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ECPublicKeyFilter13 implements PublicKeyFilter {

        /* renamed from: a  reason: collision with root package name */
        final ASN1ObjectIdentifier f14028a;

        ECPublicKeyFilter13(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
            this.f14028a = aSN1ObjectIdentifier;
        }

        private boolean appliesTo(PublicKey publicKey) {
            if ("EC".equalsIgnoreCase(JsseUtils.G(publicKey)) || ECPublicKey.class.isInstance(publicKey)) {
                return this.f14028a.equals((ASN1Primitive) JsseUtils.C(publicKey));
            }
            return false;
        }

        @Override // org.bouncycastle.jsse.provider.ProvX509KeyManager.PublicKeyFilter
        public boolean accepts(PublicKey publicKey, boolean[] zArr, BCAlgorithmConstraints bCAlgorithmConstraints) {
            return appliesTo(publicKey) && ProvAlgorithmChecker.i(publicKey, zArr, 0, bCAlgorithmConstraints);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Match implements Comparable<Match> {

        /* renamed from: g  reason: collision with root package name */
        static final Quality f14029g = Quality.MISMATCH_SNI;

        /* renamed from: h  reason: collision with root package name */
        static final Match f14030h = new Match(Quality.NONE, Integer.MAX_VALUE, -1, null, null, null);

        /* renamed from: a  reason: collision with root package name */
        final Quality f14031a;

        /* renamed from: b  reason: collision with root package name */
        final int f14032b;

        /* renamed from: c  reason: collision with root package name */
        final int f14033c;

        /* renamed from: d  reason: collision with root package name */
        final String f14034d;

        /* renamed from: e  reason: collision with root package name */
        final KeyStore f14035e;

        /* renamed from: f  reason: collision with root package name */
        final X509Certificate[] f14036f;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public enum Quality {
            OK,
            RSA_MULTI_USE,
            MISMATCH_SNI,
            EXPIRED,
            NONE
        }

        Match(Quality quality, int i2, int i3, String str, KeyStore keyStore, X509Certificate[] x509CertificateArr) {
            this.f14031a = quality;
            this.f14032b = i2;
            this.f14033c = i3;
            this.f14034d = str;
            this.f14035e = keyStore;
            this.f14036f = x509CertificateArr;
        }

        boolean a() {
            return Quality.OK == this.f14031a && this.f14032b == 0;
        }

        boolean b() {
            return this.f14031a.compareTo(f14029g) < 0;
        }

        @Override // java.lang.Comparable
        public int compareTo(Match match) {
            int compare = Boolean.compare(match.b(), b());
            if (compare == 0) {
                int compare2 = Integer.compare(this.f14032b, match.f14032b);
                return compare2 == 0 ? this.f14031a.compareTo(match.f14031a) : compare2;
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
    public ProvX509KeyManager(boolean z, JcaJceHelper jcaJceHelper, List list) {
        this.isInFipsMode = z;
        this.helper = jcaJceHelper;
        this.builders = list;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyPurposeId b(boolean z) {
        if (provKeyManagerCheckEKU) {
            return z ? KeyPurposeId.id_kp_serverAuth : KeyPurposeId.id_kp_clientAuth;
        }
        return null;
    }

    private String chooseAlias(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        Match bestMatch = getBestMatch(list, principalArr, transportData, z);
        if (bestMatch.compareTo(Match.f14030h) >= 0) {
            LOG.fine("No matching key found");
            return null;
        }
        String str = list.get(bestMatch.f14032b);
        String alias = getAlias(bestMatch, getNextVersionSuffix());
        Logger logger = LOG;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Found matching key of type: " + str + ", returning alias: " + alias);
        }
        return alias;
    }

    private BCX509Key chooseKeyBC(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        Match bestMatch = getBestMatch(list, principalArr, transportData, z);
        if (bestMatch.compareTo(Match.f14030h) < 0) {
            try {
                String str = list.get(bestMatch.f14032b);
                BCX509Key createKeyBC = createKeyBC(str, bestMatch.f14033c, bestMatch.f14034d, bestMatch.f14035e, bestMatch.f14036f);
                if (createKeyBC != null) {
                    Logger logger = LOG;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine("Found matching key of type: " + str + ", from alias: " + bestMatch.f14033c + "." + bestMatch.f14034d);
                    }
                    return createKeyBC;
                }
            } catch (Exception e2) {
                LOG.log(Level.FINER, "Failed to load private key", (Throwable) e2);
            }
        }
        LOG.fine("No matching key found");
        return null;
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

    private BCX509Key createKeyBC(String str, int i2, String str2, KeyStore keyStore, X509Certificate[] x509CertificateArr) {
        Key a2 = KeyStoreUtil.a(keyStore, str2, this.builders.get(i2).getProtectionParameter(str2));
        if (a2 instanceof PrivateKey) {
            return new ProvX509Key(str, (PrivateKey) a2, x509CertificateArr);
        }
        return null;
    }

    private static String getAlias(Match match, String str) {
        return match.f14033c + "." + match.f14034d + str;
    }

    private static String[] getAliases(List<Match> list, String str) {
        String[] strArr = new String[list.size()];
        int i2 = 0;
        for (Match match : list) {
            strArr[i2] = getAlias(match, str);
            i2++;
        }
        return strArr;
    }

    private String[] getAliases(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        int i2;
        int i3;
        List<Match> list2;
        ProvX509KeyManager provX509KeyManager = this;
        if (provX509KeyManager.builders.isEmpty() || list.isEmpty()) {
            return null;
        }
        int size = list.size();
        Set<Principal> uniquePrincipals = getUniquePrincipals(principalArr);
        BCAlgorithmConstraints c2 = TransportData.c(transportData, true);
        Date date = new Date();
        String requestedHostName = getRequestedHostName(transportData, z);
        int size2 = provX509KeyManager.builders.size();
        int i4 = 0;
        List<Match> list3 = null;
        while (i4 < size2) {
            try {
                KeyStore.Builder builder = provX509KeyManager.builders.get(i4);
                KeyStore keyStore = builder.getKeyStore();
                Enumeration<String> aliases = keyStore.aliases();
                List<Match> list4 = list3;
                while (aliases.hasMoreElements()) {
                    try {
                        list2 = list4;
                        i2 = i4;
                        i3 = size2;
                        try {
                            Match potentialMatch = getPotentialMatch(i4, builder, keyStore, aliases.nextElement(), list, size, uniquePrincipals, c2, z, date, requestedHostName);
                            list4 = potentialMatch.compareTo(Match.f14030h) < 0 ? addToMatches(list2, potentialMatch) : list2;
                            i4 = i2;
                            size2 = i3;
                        } catch (KeyStoreException e2) {
                            e = e2;
                            list3 = list2;
                            LOG.log(Level.WARNING, "Failed to fully process KeyStore.Builder at index " + i2, (Throwable) e);
                            i4 = i2 + 1;
                            provX509KeyManager = this;
                            size2 = i3;
                        }
                    } catch (KeyStoreException e3) {
                        e = e3;
                        list2 = list4;
                        i2 = i4;
                        i3 = size2;
                    }
                }
                i2 = i4;
                i3 = size2;
                list3 = list4;
            } catch (KeyStoreException e4) {
                e = e4;
                i2 = i4;
                i3 = size2;
            }
            i4 = i2 + 1;
            provX509KeyManager = this;
            size2 = i3;
        }
        if (list3 == null || list3.isEmpty()) {
            return null;
        }
        Collections.sort(list3);
        return getAliases(list3, getNextVersionSuffix());
    }

    private Match getBestMatch(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        int i2;
        int i3;
        boolean z2;
        int i4;
        Match match;
        int i5;
        ProvX509KeyManager provX509KeyManager = this;
        Match match2 = Match.f14030h;
        if (provX509KeyManager.builders.isEmpty() || list.isEmpty()) {
            return match2;
        }
        int size = list.size();
        Set<Principal> uniquePrincipals = getUniquePrincipals(principalArr);
        boolean z3 = true;
        BCAlgorithmConstraints c2 = TransportData.c(transportData, true);
        Date date = new Date();
        String requestedHostName = getRequestedHostName(transportData, z);
        int size2 = provX509KeyManager.builders.size();
        int i6 = 0;
        int i7 = size;
        Match match3 = match2;
        while (i6 < size2) {
            try {
                KeyStore.Builder builder = provX509KeyManager.builders.get(i6);
                KeyStore keyStore = builder.getKeyStore();
                Enumeration<String> aliases = keyStore.aliases();
                Match match4 = match3;
                int i8 = i7;
                while (aliases.hasMoreElements()) {
                    try {
                        int i9 = i8;
                        match = match4;
                        i2 = i6;
                        i3 = size2;
                        try {
                            match3 = getPotentialMatch(i6, builder, keyStore, aliases.nextElement(), list, i8, uniquePrincipals, c2, z, date, requestedHostName);
                            if (match3.compareTo(match) < 0) {
                                try {
                                    if (match3.a()) {
                                        return match3;
                                    }
                                    if (match3.b()) {
                                        z2 = true;
                                        i5 = i9;
                                        try {
                                            match4 = match3;
                                            i8 = Math.min(i5, match3.f14032b + 1);
                                        } catch (KeyStoreException e2) {
                                            e = e2;
                                            i7 = i5;
                                            LOG.log(Level.WARNING, "Failed to fully process KeyStore.Builder at index " + i2, (Throwable) e);
                                            i6 = i2 + 1;
                                            provX509KeyManager = this;
                                            z3 = z2;
                                            size2 = i3;
                                        }
                                    } else {
                                        z2 = true;
                                        match4 = match3;
                                        i8 = i9;
                                    }
                                } catch (KeyStoreException e3) {
                                    e = e3;
                                    i5 = i9;
                                    z2 = true;
                                }
                            } else {
                                z2 = true;
                                i8 = i9;
                                match4 = match;
                            }
                            z3 = z2;
                            i6 = i2;
                            size2 = i3;
                        } catch (KeyStoreException e4) {
                            e = e4;
                            i4 = i9;
                            z2 = true;
                            i7 = i4;
                            match3 = match;
                            LOG.log(Level.WARNING, "Failed to fully process KeyStore.Builder at index " + i2, (Throwable) e);
                            i6 = i2 + 1;
                            provX509KeyManager = this;
                            z3 = z2;
                            size2 = i3;
                        }
                    } catch (KeyStoreException e5) {
                        e = e5;
                        i4 = i8;
                        i2 = i6;
                        i3 = size2;
                        z2 = z3;
                        match = match4;
                    }
                }
                i2 = i6;
                i3 = size2;
                z2 = z3;
                i7 = i8;
                match3 = match4;
            } catch (KeyStoreException e6) {
                e = e6;
                i2 = i6;
                i3 = size2;
                z2 = z3;
            }
            i6 = i2 + 1;
            provX509KeyManager = this;
            z3 = z2;
            size2 = i3;
        }
        return match3;
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

    private String getNextVersionSuffix() {
        return "." + this.versions.incrementAndGet();
    }

    private Match getPotentialMatch(int i2, KeyStore.Builder builder, KeyStore keyStore, String str, List<String> list, int i3, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str2) {
        int suitableKeyTypeForEECert;
        if (keyStore.isKeyEntry(str)) {
            X509Certificate[] P = JsseUtils.P(keyStore.getCertificateChain(str));
            if (!TlsUtils.isNullOrEmpty(P) && isSuitableChainForIssuers(P, set) && (suitableKeyTypeForEECert = getSuitableKeyTypeForEECert(P[0], list, i3, bCAlgorithmConstraints, z)) >= 0) {
                String str3 = list.get(suitableKeyTypeForEECert);
                Logger logger = LOG;
                logger.finer("EE cert potentially usable for key type: " + str3);
                if (isSuitableChain(P, bCAlgorithmConstraints, z)) {
                    return new Match(getCertificateQuality(P[0], date, str2), suitableKeyTypeForEECert, i2, str, keyStore, P);
                }
                logger.finer("Unsuitable chain for key type: " + str3);
            }
        }
        return Match.f14030h;
    }

    private KeyStore.PrivateKeyEntry getPrivateKeyEntry(String str) {
        KeyStore.PrivateKeyEntry privateKeyEntry;
        if (str == null) {
            return null;
        }
        SoftReference<KeyStore.PrivateKeyEntry> softReference = this.cachedEntries.get(str);
        if (softReference == null || (privateKeyEntry = softReference.get()) == null) {
            KeyStore.PrivateKeyEntry loadPrivateKeyEntry = loadPrivateKeyEntry(str);
            if (loadPrivateKeyEntry != null) {
                this.cachedEntries.put(str, new SoftReference<>(loadPrivateKeyEntry));
            }
            return loadPrivateKeyEntry;
        }
        return privateKeyEntry;
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
            ProvAlgorithmChecker.b(this.isInFipsMode, this.helper, bCAlgorithmConstraints, Collections.emptySet(), x509CertificateArr, b(z), -1);
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

    private KeyStore.PrivateKeyEntry loadPrivateKeyEntry(String str) {
        int i2;
        int lastIndexOf;
        int parseInt;
        try {
            int indexOf = str.indexOf(46, 0);
            if (indexOf <= 0 || (lastIndexOf = str.lastIndexOf(46)) <= (i2 = indexOf + 1) || (parseInt = Integer.parseInt(str.substring(0, indexOf))) < 0 || parseInt >= this.builders.size()) {
                return null;
            }
            KeyStore.Builder builder = this.builders.get(parseInt);
            String substring = str.substring(i2, lastIndexOf);
            KeyStore.Entry entry = builder.getKeyStore().getEntry(substring, builder.getProtectionParameter(substring));
            if (entry instanceof KeyStore.PrivateKeyEntry) {
                return (KeyStore.PrivateKeyEntry) entry;
            }
            return null;
        } catch (Exception e2) {
            Logger logger = LOG;
            Level level = Level.FINER;
            logger.log(level, "Failed to load PrivateKeyEntry: " + str, (Throwable) e2);
            return null;
        }
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    protected BCX509Key a(String str, String str2) {
        PrivateKey privateKey;
        KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKeyEntry(str2);
        if (privateKeyEntry == null || (privateKey = privateKeyEntry.getPrivateKey()) == null) {
            return null;
        }
        X509Certificate[] P = JsseUtils.P(privateKeyEntry.getCertificateChain());
        if (TlsUtils.isNullOrEmpty(P)) {
            return null;
        }
        return new ProvX509Key(str, privateKey, P);
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
        KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKeyEntry(str);
        if (privateKeyEntry == null) {
            return null;
        }
        return (X509Certificate[]) privateKeyEntry.getCertificateChain();
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, false);
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKeyEntry(str);
        if (privateKeyEntry == null) {
            return null;
        }
        return privateKeyEntry.getPrivateKey();
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, true);
    }
}
