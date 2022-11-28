package org.bouncycastle.jsse.provider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertPathParameters;
import java.security.cert.Certificate;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;
import org.bouncycastle.jcajce.provider.keystore.util.AdaptingKeyStoreSpi;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.TlsUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvTrustManagerFactorySpi extends TrustManagerFactorySpi {
    private static final Logger LOG = Logger.getLogger(ProvTrustManagerFactorySpi.class.getName());
    private static final boolean provKeyStoreTypeCompat = PropertyUtils.a(AdaptingKeyStoreSpi.COMPAT_OVERRIDE, false);

    /* renamed from: a  reason: collision with root package name */
    protected final boolean f14022a;

    /* renamed from: b  reason: collision with root package name */
    protected final JcaJceHelper f14023b;

    /* renamed from: c  reason: collision with root package name */
    protected ProvX509TrustManager f14024c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTrustManagerFactorySpi(boolean z, JcaJceHelper jcaJceHelper) {
        this.f14022a = z;
        this.f14023b = jcaJceHelper;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(6:(9:(1:9)(2:34|(2:36|(2:(1:39)|40)(2:41|(1:(1:44)))))|11|(1:13)(1:33)|14|(1:16)(1:31)|17|18|(1:20)|21)|17|18|(0)|21|(2:(1:25)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (new java.io.File(r2).exists() != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00cd, code lost:
        r0 = java.security.KeyStore.getInstance("BCFKS");
        r0.load(null, null);
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009d A[Catch: all -> 0x00dc, TRY_ENTER, TryCatch #2 {all -> 0x00dc, blocks: (B:32:0x009d, B:33:0x00a6), top: B:49:0x009b }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a6 A[Catch: all -> 0x00dc, TRY_LEAVE, TryCatch #2 {all -> 0x00dc, blocks: (B:32:0x009d, B:33:0x00a6), top: B:49:0x009b }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static KeyStore a() {
        BufferedInputStream bufferedInputStream;
        String str;
        String defaultType = KeyStore.getDefaultType();
        boolean z = provKeyStoreTypeCompat && "pkcs12".equalsIgnoreCase(defaultType);
        String j2 = PropertyUtils.j("javax.net.ssl.trustStore");
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                if (!"NONE".equals(j2)) {
                    if (j2 == null) {
                        String j3 = PropertyUtils.j("java.home");
                        if (j3 != null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(j3);
                            sb.append("/lib/security/jssecacerts".replace("/", File.separator));
                            String sb2 = sb.toString();
                            if (new File(sb2).exists()) {
                                if (z) {
                                    defaultType = "jks";
                                }
                                j2 = sb2;
                            } else {
                                j2 = j3 + "/lib/security/cacerts".replace("/", str);
                                if (new File(j2).exists()) {
                                    if (z) {
                                        defaultType = "jks";
                                    }
                                }
                            }
                        }
                    }
                    KeyStore createTrustStore = createTrustStore(defaultType);
                    String e2 = PropertyUtils.e("javax.net.ssl.trustStorePassword");
                    char[] charArray = e2 == null ? e2.toCharArray() : null;
                    if (j2 != null) {
                        LOG.config("Initializing default trust store as empty");
                        bufferedInputStream = null;
                    } else {
                        LOG.config("Initializing default trust store from path: " + j2);
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(j2));
                    }
                    createTrustStore.load(bufferedInputStream, charArray);
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    return createTrustStore;
                }
                createTrustStore.load(bufferedInputStream, charArray);
                if (bufferedInputStream != null) {
                }
                return createTrustStore;
            } catch (Throwable th) {
                th = th;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                throw th;
            }
            if (j2 != null) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
        j2 = null;
        KeyStore createTrustStore2 = createTrustStore(defaultType);
        String e22 = PropertyUtils.e("javax.net.ssl.trustStorePassword");
        if (e22 == null) {
        }
    }

    private static void collectTrustAnchor(Set<TrustAnchor> set, Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            set.add(new TrustAnchor((X509Certificate) certificate, null));
        }
    }

    private static KeyStore createTrustStore(String str) {
        String trustStoreType = getTrustStoreType(str);
        String j2 = PropertyUtils.j("javax.net.ssl.trustStoreProvider");
        return TlsUtils.isNullOrEmpty(j2) ? KeyStore.getInstance(trustStoreType) : KeyStore.getInstance(trustStoreType, j2);
    }

    private static Set<TrustAnchor> getTrustAnchors(KeyStore keyStore) {
        Certificate certificate;
        Certificate[] certificateChain;
        if (keyStore == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String nextElement = aliases.nextElement();
            if (keyStore.isCertificateEntry(nextElement)) {
                certificate = keyStore.getCertificate(nextElement);
            } else if (keyStore.isKeyEntry(nextElement) && (certificateChain = keyStore.getCertificateChain(nextElement)) != null && certificateChain.length > 0) {
                certificate = certificateChain[0];
            }
            collectTrustAnchor(hashSet, certificate);
        }
        return hashSet;
    }

    private static String getTrustStoreType(String str) {
        String j2 = PropertyUtils.j("javax.net.ssl.trustStoreType");
        return j2 == null ? str : j2;
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected TrustManager[] engineGetTrustManagers() {
        ProvX509TrustManager provX509TrustManager = this.f14024c;
        if (provX509TrustManager != null) {
            return new TrustManager[]{provX509TrustManager.c()};
        }
        throw new IllegalStateException("TrustManagerFactory not initialized");
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected void engineInit(KeyStore keyStore) {
        if (keyStore == null) {
            try {
                keyStore = a();
            } catch (Error e2) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e2);
                throw e2;
            } catch (SecurityException e3) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e3);
            } catch (RuntimeException e4) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e4);
                throw e4;
            } catch (Exception e5) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e5);
                throw new KeyStoreException("Failed to load default trust store", e5);
            }
        }
        try {
            this.f14024c = new ProvX509TrustManager(this.f14022a, this.f14023b, getTrustAnchors(keyStore));
        } catch (InvalidAlgorithmParameterException e6) {
            throw new KeyStoreException("Failed to create trust manager", e6);
        }
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
        if (managerFactoryParameters instanceof CertPathTrustManagerParameters) {
            CertPathParameters parameters = ((CertPathTrustManagerParameters) managerFactoryParameters).getParameters();
            if (!(parameters instanceof PKIXParameters)) {
                throw new InvalidAlgorithmParameterException("parameters must inherit from PKIXParameters");
            }
            this.f14024c = new ProvX509TrustManager(this.f14022a, this.f14023b, (PKIXParameters) parameters);
        } else if (managerFactoryParameters == null) {
            throw new InvalidAlgorithmParameterException("spec cannot be null");
        } else {
            throw new InvalidAlgorithmParameterException("unknown spec: " + managerFactoryParameters.getClass().getName());
        }
    }
}
