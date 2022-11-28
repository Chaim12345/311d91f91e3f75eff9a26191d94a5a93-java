package org.bouncycastle.jsse.provider;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCryptoProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLContextSpi extends SSLContextSpi {
    private static final List<String> DEFAULT_CIPHERSUITE_LIST;
    private static final List<String> DEFAULT_CIPHERSUITE_LIST_FIPS;
    private static final List<String> DEFAULT_PROTOCOL_LIST;
    private static final List<String> DEFAULT_PROTOCOL_LIST_FIPS;
    private static final String PROPERTY_CLIENT_CIPHERSUITES = "jdk.tls.client.cipherSuites";
    private static final String PROPERTY_CLIENT_PROTOCOLS = "jdk.tls.client.protocols";
    private static final String PROPERTY_SERVER_CIPHERSUITES = "jdk.tls.server.cipherSuites";
    private static final String PROPERTY_SERVER_PROTOCOLS = "jdk.tls.server.protocols";
    private static final Map<String, CipherSuiteInfo> SUPPORTED_CIPHERSUITE_MAP;
    private static final Map<String, CipherSuiteInfo> SUPPORTED_CIPHERSUITE_MAP_FIPS;
    private static final Map<String, ProtocolVersion> SUPPORTED_PROTOCOL_MAP;
    private static final Map<String, ProtocolVersion> SUPPORTED_PROTOCOL_MAP_FIPS;

    /* renamed from: a  reason: collision with root package name */
    protected final boolean f13920a;

    /* renamed from: b  reason: collision with root package name */
    protected final JcaTlsCryptoProvider f13921b;

    /* renamed from: c  reason: collision with root package name */
    protected final Map f13922c;
    private ContextData contextData = null;

    /* renamed from: d  reason: collision with root package name */
    protected final Map f13923d;

    /* renamed from: e  reason: collision with root package name */
    protected final String[] f13924e;

    /* renamed from: f  reason: collision with root package name */
    protected final String[] f13925f;

    /* renamed from: g  reason: collision with root package name */
    protected final String[] f13926g;

    /* renamed from: h  reason: collision with root package name */
    protected final String[] f13927h;
    private static final Logger LOG = Logger.getLogger(ProvSSLContextSpi.class.getName());
    private static final Set<BCCryptoPrimitive> TLS_CRYPTO_PRIMITIVES_BC = JsseUtils.f13896a;

    static {
        Map<String, CipherSuiteInfo> createSupportedCipherSuiteMap = createSupportedCipherSuiteMap();
        SUPPORTED_CIPHERSUITE_MAP = createSupportedCipherSuiteMap;
        SUPPORTED_CIPHERSUITE_MAP_FIPS = createSupportedCipherSuiteMapFips(createSupportedCipherSuiteMap);
        Map<String, ProtocolVersion> createSupportedProtocolMap = createSupportedProtocolMap();
        SUPPORTED_PROTOCOL_MAP = createSupportedProtocolMap;
        SUPPORTED_PROTOCOL_MAP_FIPS = createSupportedProtocolMapFips(createSupportedProtocolMap);
        List<String> createDefaultCipherSuiteList = createDefaultCipherSuiteList(createSupportedCipherSuiteMap.keySet());
        DEFAULT_CIPHERSUITE_LIST = createDefaultCipherSuiteList;
        DEFAULT_CIPHERSUITE_LIST_FIPS = createDefaultCipherSuiteListFips(createDefaultCipherSuiteList);
        List<String> createDefaultProtocolList = createDefaultProtocolList(createSupportedProtocolMap.keySet());
        DEFAULT_PROTOCOL_LIST = createDefaultProtocolList;
        DEFAULT_PROTOCOL_LIST_FIPS = createDefaultProtocolListFips(createDefaultProtocolList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLContextSpi(boolean z, JcaTlsCryptoProvider jcaTlsCryptoProvider, List list) {
        this.f13920a = z;
        this.f13921b = jcaTlsCryptoProvider;
        Map<String, CipherSuiteInfo> map = z ? SUPPORTED_CIPHERSUITE_MAP_FIPS : SUPPORTED_CIPHERSUITE_MAP;
        this.f13922c = map;
        Map<String, ProtocolVersion> map2 = z ? SUPPORTED_PROTOCOL_MAP_FIPS : SUPPORTED_PROTOCOL_MAP;
        this.f13923d = map2;
        List<String> list2 = z ? DEFAULT_CIPHERSUITE_LIST_FIPS : DEFAULT_CIPHERSUITE_LIST;
        List<String> list3 = z ? DEFAULT_PROTOCOL_LIST_FIPS : DEFAULT_PROTOCOL_LIST;
        this.f13924e = getDefaultEnabledCipherSuitesClient(map, list2);
        this.f13925f = getDefaultEnabledCipherSuitesServer(map, list2);
        this.f13926g = getDefaultEnabledProtocolsClient(map2, list3, list);
        this.f13927h = getDefaultEnabledProtocolsServer(map2, list3);
    }

    private static void addCipherSuite(Map<String, CipherSuiteInfo> map, String str, int i2) {
        if (map.put(str, CipherSuiteInfo.a(i2, str)) != null) {
            throw new IllegalStateException("Duplicate names in supported-cipher-suites");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CipherSuiteInfo c(String str) {
        return SUPPORTED_CIPHERSUITE_MAP.get(str);
    }

    private static List<String> createDefaultCipherSuiteList(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TLS_CHACHA20_POLY1305_SHA256");
        arrayList.add("TLS_AES_256_GCM_SHA384");
        arrayList.add("TLS_AES_128_GCM_SHA256");
        arrayList.add("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
        arrayList.add("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
        arrayList.add("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
        arrayList.add("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
        arrayList.add("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
        arrayList.add("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        arrayList.add("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
        arrayList.add("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
        arrayList.add("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");
        arrayList.add("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
        arrayList.add("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");
        arrayList.add("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
        arrayList.add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
        arrayList.add("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
        arrayList.add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
        arrayList.add("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
        arrayList.add("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
        arrayList.add("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
        arrayList.add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
        arrayList.add("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
        arrayList.add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
        arrayList.add("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
        arrayList.add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        arrayList.add("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
        arrayList.add("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
        arrayList.add("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
        arrayList.add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
        arrayList.add("TLS_RSA_WITH_AES_256_GCM_SHA384");
        arrayList.add("TLS_RSA_WITH_AES_128_GCM_SHA256");
        arrayList.add("TLS_RSA_WITH_AES_256_CBC_SHA256");
        arrayList.add("TLS_RSA_WITH_AES_128_CBC_SHA256");
        arrayList.add("TLS_RSA_WITH_AES_256_CBC_SHA");
        arrayList.add("TLS_RSA_WITH_AES_128_CBC_SHA");
        arrayList.retainAll(set);
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    private static List<String> createDefaultCipherSuiteListFips(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        FipsUtils.e(arrayList);
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    private static List<String> createDefaultProtocolList(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TLSv1.2");
        arrayList.add("TLSv1.1");
        arrayList.add("TLSv1");
        arrayList.retainAll(set);
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    private static List<String> createDefaultProtocolListFips(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        FipsUtils.f(arrayList);
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    private static Map<String, CipherSuiteInfo> createSupportedCipherSuiteMap() {
        TreeMap treeMap = new TreeMap();
        addCipherSuite(treeMap, "TLS_AES_128_CCM_8_SHA256", CipherSuite.TLS_AES_128_CCM_8_SHA256);
        addCipherSuite(treeMap, "TLS_AES_128_CCM_SHA256", CipherSuite.TLS_AES_128_CCM_SHA256);
        addCipherSuite(treeMap, "TLS_AES_128_GCM_SHA256", CipherSuite.TLS_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_AES_256_GCM_SHA384", CipherSuite.TLS_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_CHACHA20_POLY1305_SHA256", CipherSuite.TLS_CHACHA20_POLY1305_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256", CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256", CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CCM", CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CCM_8", CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CCM", CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CCM_8", CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CCM", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CCM", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_NULL_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_NULL_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA);
        addCipherSuite(treeMap, "TLS_RSA_WITH_3DES_EDE_CBC_SHA", 10);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CBC_SHA", 47);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CCM", CipherSuite.TLS_RSA_WITH_AES_128_CCM);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CCM_8", CipherSuite.TLS_RSA_WITH_AES_128_CCM_8);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_GCM_SHA256", CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CBC_SHA", 53);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CCM", CipherSuite.TLS_RSA_WITH_AES_256_CCM);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CCM_8", CipherSuite.TLS_RSA_WITH_AES_256_CCM_8);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_GCM_SHA384", CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256", CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_256_CBC_SHA256", 192);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_RSA_WITH_NULL_SHA", 2);
        addCipherSuite(treeMap, "TLS_RSA_WITH_NULL_SHA256", 59);
        return Collections.unmodifiableMap(treeMap);
    }

    private static Map<String, CipherSuiteInfo> createSupportedCipherSuiteMapFips(Map<String, CipherSuiteInfo> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        FipsUtils.e(linkedHashMap.keySet());
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static Map<String, ProtocolVersion> createSupportedProtocolMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("TLSv1.3", ProtocolVersion.TLSv13);
        linkedHashMap.put("TLSv1.2", ProtocolVersion.TLSv12);
        linkedHashMap.put("TLSv1.1", ProtocolVersion.TLSv11);
        linkedHashMap.put("TLSv1", ProtocolVersion.TLSv10);
        linkedHashMap.put("SSLv3", ProtocolVersion.SSLv3);
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static Map<String, ProtocolVersion> createSupportedProtocolMapFips(Map<String, ProtocolVersion> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        FipsUtils.f(linkedHashMap.keySet());
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d(int i2) {
        if (i2 == 0) {
            return "SSL_NULL_WITH_NULL_NULL";
        }
        if (TlsUtils.isValidUint16(i2)) {
            for (CipherSuiteInfo cipherSuiteInfo : SUPPORTED_CIPHERSUITE_MAP.values()) {
                if (cipherSuiteInfo.getCipherSuite() == i2) {
                    return cipherSuiteInfo.getName();
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyManager[] g() {
        KeyStoreConfig a2 = ProvKeyManagerFactorySpi.a();
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(a2.f13907a, a2.f13908b);
        return keyManagerFactory.getKeyManagers();
    }

    private static String[] getArray(Collection<String> collection) {
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    private static String[] getDefaultEnabledCipherSuites(Map<String, CipherSuiteInfo> map, String str, List<String> list) {
        List<String> jdkTlsCipherSuites = getJdkTlsCipherSuites(str, list);
        String[] strArr = new String[jdkTlsCipherSuites.size()];
        int i2 = 0;
        for (String str2 : jdkTlsCipherSuites) {
            if (map.containsKey(str2) && ProvAlgorithmConstraints.f13911b.permits(TLS_CRYPTO_PRIMITIVES_BC, str2, null)) {
                strArr[i2] = str2;
                i2++;
            }
        }
        return JsseUtils.V(strArr, i2);
    }

    private static String[] getDefaultEnabledCipherSuitesClient(Map<String, CipherSuiteInfo> map, List<String> list) {
        return getDefaultEnabledCipherSuites(map, PROPERTY_CLIENT_CIPHERSUITES, list);
    }

    private static String[] getDefaultEnabledCipherSuitesServer(Map<String, CipherSuiteInfo> map, List<String> list) {
        return getDefaultEnabledCipherSuites(map, PROPERTY_SERVER_CIPHERSUITES, list);
    }

    private static String[] getDefaultEnabledProtocols(Map<String, ProtocolVersion> map, String str, List<String> list, List<String> list2) {
        if (list2 == null) {
            list2 = getJdkTlsProtocols(str, list);
        }
        String[] strArr = new String[list2.size()];
        int i2 = 0;
        for (String str2 : list2) {
            if (map.containsKey(str2) && ProvAlgorithmConstraints.f13912c.permits(TLS_CRYPTO_PRIMITIVES_BC, str2, null)) {
                strArr[i2] = str2;
                i2++;
            }
        }
        return JsseUtils.V(strArr, i2);
    }

    private static String[] getDefaultEnabledProtocolsClient(Map<String, ProtocolVersion> map, List<String> list, List<String> list2) {
        return getDefaultEnabledProtocols(map, PROPERTY_CLIENT_PROTOCOLS, list, list2);
    }

    private static String[] getDefaultEnabledProtocolsServer(Map<String, ProtocolVersion> map, List<String> list) {
        return getDefaultEnabledProtocols(map, PROPERTY_SERVER_PROTOCOLS, list, null);
    }

    private static List<String> getJdkTlsCipherSuites(String str, List<String> list) {
        String[] g2 = PropertyUtils.g(str);
        if (g2 == null) {
            return list;
        }
        ArrayList arrayList = new ArrayList(g2.length);
        for (String str2 : g2) {
            if (!arrayList.contains(str2)) {
                if (SUPPORTED_CIPHERSUITE_MAP.containsKey(str2)) {
                    arrayList.add(str2);
                } else {
                    LOG.warning("'" + str + "' contains unsupported cipher suite: " + str2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            LOG.severe("'" + str + "' contained no supported cipher suites (ignoring)");
            return list;
        }
        return arrayList;
    }

    private static List<String> getJdkTlsProtocols(String str, List<String> list) {
        String[] g2 = PropertyUtils.g(str);
        if (g2 == null) {
            return list;
        }
        ArrayList arrayList = new ArrayList(g2.length);
        for (String str2 : g2) {
            if (!arrayList.contains(str2)) {
                if (SUPPORTED_PROTOCOL_MAP.containsKey(str2)) {
                    arrayList.add(str2);
                } else {
                    LOG.warning("'" + str + "' contains unsupported protocol: " + str2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            LOG.severe("'" + str + "' contained no supported protocols (ignoring)");
            return list;
        }
        return arrayList;
    }

    private static String[] getKeysArray(Map<String, ?> map) {
        return getArray(map.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustManager[] i() {
        KeyStore a2 = ProvTrustManagerFactorySpi.a();
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(a2);
        return trustManagerFactory.getTrustManagers();
    }

    private String[] implGetDefaultCipherSuites(boolean z) {
        return z ? this.f13924e : this.f13925f;
    }

    private String[] implGetDefaultProtocols(boolean z) {
        return z ? this.f13926g : this.f13927h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProtocolVersion j(String str) {
        return SUPPORTED_PROTOCOL_MAP.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String k(ProtocolVersion protocolVersion) {
        if (protocolVersion != null) {
            for (Map.Entry<String, ProtocolVersion> entry : SUPPORTED_PROTOCOL_MAP.entrySet()) {
                if (entry.getValue().equals(protocolVersion)) {
                    return entry.getKey();
                }
            }
            return "NONE";
        }
        return "NONE";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int[] a(JcaTlsCrypto jcaTlsCrypto, ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr) {
        String[] c2 = provSSLParameters.c();
        BCAlgorithmConstraints algorithmConstraints = provSSLParameters.getAlgorithmConstraints();
        ProtocolVersion latestTLS = ProtocolVersion.getLatestTLS(protocolVersionArr);
        ProtocolVersion earliestTLS = ProtocolVersion.getEarliestTLS(protocolVersionArr);
        boolean isTLSv13 = TlsUtils.isTLSv13(latestTLS);
        boolean z = !TlsUtils.isTLSv13(earliestTLS);
        int[] iArr = new int[c2.length];
        int i2 = 0;
        for (String str : c2) {
            CipherSuiteInfo cipherSuiteInfo = (CipherSuiteInfo) this.f13922c.get(str);
            if (cipherSuiteInfo != null) {
                if (cipherSuiteInfo.b()) {
                    if (!isTLSv13) {
                    }
                    if (!algorithmConstraints.permits(TLS_CRYPTO_PRIMITIVES_BC, str, null)) {
                        iArr[i2] = cipherSuiteInfo.getCipherSuite();
                        i2++;
                    }
                } else {
                    if (!z) {
                    }
                    if (!algorithmConstraints.permits(TLS_CRYPTO_PRIMITIVES_BC, str, null)) {
                    }
                }
            }
        }
        int[] supportedCipherSuites = TlsUtils.getSupportedCipherSuites(jcaTlsCrypto, iArr, 0, i2);
        if (supportedCipherSuites.length >= 1) {
            return supportedCipherSuites;
        }
        throw new IllegalStateException("No usable cipher suites enabled");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolVersion[] b(ProvSSLParameters provSSLParameters) {
        String[] d2 = provSSLParameters.d();
        BCAlgorithmConstraints algorithmConstraints = provSSLParameters.getAlgorithmConstraints();
        TreeSet treeSet = new TreeSet(new Comparator<ProtocolVersion>(this) { // from class: org.bouncycastle.jsse.provider.ProvSSLContextSpi.1
            @Override // java.util.Comparator
            public int compare(ProtocolVersion protocolVersion, ProtocolVersion protocolVersion2) {
                if (protocolVersion.isLaterVersionOf(protocolVersion2)) {
                    return -1;
                }
                return protocolVersion2.isLaterVersionOf(protocolVersion) ? 1 : 0;
            }
        });
        for (String str : d2) {
            ProtocolVersion protocolVersion = (ProtocolVersion) this.f13923d.get(str);
            if (protocolVersion != null && algorithmConstraints.permits(TLS_CRYPTO_PRIMITIVES_BC, str, null)) {
                treeSet.add(protocolVersion);
            }
        }
        if (treeSet.isEmpty()) {
            throw new IllegalStateException("No usable protocols enabled");
        }
        return (ProtocolVersion[]) treeSet.toArray(new ProtocolVersion[treeSet.size()]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized ContextData e() {
        ContextData contextData;
        contextData = this.contextData;
        if (contextData == null) {
            throw new IllegalStateException("SSLContext has not been initialized.");
        }
        return contextData;
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLEngine engineCreateSSLEngine() {
        return SSLEngineUtil.a(e());
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLEngine engineCreateSSLEngine(String str, int i2) {
        return SSLEngineUtil.b(e(), str, i2);
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLSessionContext engineGetClientSessionContext() {
        return e().b();
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLParameters engineGetDefaultSSLParameters() {
        e();
        return SSLParametersUtil.b(h(true));
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLSessionContext engineGetServerSessionContext() {
        return e().f();
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLServerSocketFactory engineGetServerSocketFactory() {
        return new ProvSSLServerSocketFactory(e());
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLSocketFactory engineGetSocketFactory() {
        return new ProvSSLSocketFactory(e());
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLParameters engineGetSupportedSSLParameters() {
        e();
        return SSLParametersUtil.b(o(true));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.net.ssl.SSLContextSpi
    public synchronized void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) {
        this.contextData = null;
        JcaTlsCrypto create = this.f13921b.create(secureRandom);
        JcaJceHelper helper = create.getHelper();
        BCX509ExtendedKeyManager r2 = r(helper, keyManagerArr);
        BCX509ExtendedTrustManager s2 = s(helper, trustManagerArr);
        create.getSecureRandom().nextInt();
        this.contextData = new ContextData(this, create, r2, s2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] f(boolean z) {
        return (String[]) implGetDefaultCipherSuites(z).clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLParameters h(boolean z) {
        return new ProvSSLParameters(this, implGetDefaultCipherSuites(z), implGetDefaultProtocols(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] l() {
        return getKeysArray(this.f13922c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] m(String[] strArr) {
        Objects.requireNonNull(strArr, "'cipherSuites' cannot be null");
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (TlsUtils.isNullOrEmpty(str)) {
                throw new IllegalArgumentException("'cipherSuites' cannot contain null or empty string elements");
            }
            if (this.f13922c.containsKey(str)) {
                arrayList.add(str);
            }
        }
        return getArray(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] n() {
        return getKeysArray(this.f13923d);
    }

    ProvSSLParameters o(boolean z) {
        return new ProvSSLParameters(this, l(), n());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean p() {
        return this.f13920a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean q(String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String str : strArr) {
            if (str == null || !this.f13923d.containsKey(str)) {
                return false;
            }
        }
        return true;
    }

    protected BCX509ExtendedKeyManager r(JcaJceHelper jcaJceHelper, KeyManager[] keyManagerArr) {
        if (keyManagerArr != null) {
            for (KeyManager keyManager : keyManagerArr) {
                if (keyManager instanceof X509KeyManager) {
                    return X509KeyManagerUtil.a(jcaJceHelper, (X509KeyManager) keyManager);
                }
            }
        }
        return DummyX509KeyManager.f13874a;
    }

    protected BCX509ExtendedTrustManager s(JcaJceHelper jcaJceHelper, TrustManager[] trustManagerArr) {
        if (trustManagerArr == null) {
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                trustManagerArr = trustManagerFactory.getTrustManagers();
            } catch (Exception e2) {
                LOG.log(Level.WARNING, "Failed to load default trust managers", (Throwable) e2);
            }
        }
        if (trustManagerArr != null) {
            for (TrustManager trustManager : trustManagerArr) {
                if (trustManager instanceof X509TrustManager) {
                    return X509TrustManagerUtil.b(this.f13920a, jcaJceHelper, (X509TrustManager) trustManager);
                }
            }
        }
        return DummyX509TrustManager.f13875a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(ProvSSLParameters provSSLParameters, boolean z) {
        if (provSSLParameters.c() == implGetDefaultCipherSuites(!z)) {
            provSSLParameters.e(implGetDefaultCipherSuites(z));
        }
        if (provSSLParameters.d() == implGetDefaultProtocols(!z)) {
            provSSLParameters.f(implGetDefaultProtocols(z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String u(ProvSSLParameters provSSLParameters, int i2) {
        String d2 = d(i2);
        if (d2 != null && JsseUtils.e(provSSLParameters.c(), d2) && provSSLParameters.getAlgorithmConstraints().permits(TLS_CRYPTO_PRIMITIVES_BC, d2, null) && this.f13922c.containsKey(d2) && (!this.f13920a || FipsUtils.a(d2))) {
            return d2;
        }
        throw new IllegalStateException("SSL connection negotiated unsupported ciphersuite: " + i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String v(ProvSSLParameters provSSLParameters, ProtocolVersion protocolVersion) {
        String k2 = k(protocolVersion);
        if (k2 != null && JsseUtils.e(provSSLParameters.d(), k2) && provSSLParameters.getAlgorithmConstraints().permits(TLS_CRYPTO_PRIMITIVES_BC, k2, null) && this.f13923d.containsKey(k2) && (!this.f13920a || FipsUtils.c(k2))) {
            return k2;
        }
        throw new IllegalStateException("SSL connection negotiated unsupported protocol: " + protocolVersion);
    }
}
