package org.bouncycastle.jsse.provider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.KeyStoreBuilderParameters;
import javax.net.ssl.ManagerFactoryParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.tls.TlsUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvKeyManagerFactorySpi extends KeyManagerFactorySpi {
    private static final Logger LOG = Logger.getLogger(ProvKeyManagerFactorySpi.class.getName());

    /* renamed from: a  reason: collision with root package name */
    protected final boolean f13915a;

    /* renamed from: b  reason: collision with root package name */
    protected final JcaJceHelper f13916b;

    /* renamed from: c  reason: collision with root package name */
    protected BCX509ExtendedKeyManager f13917c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvKeyManagerFactorySpi(boolean z, JcaJceHelper jcaJceHelper) {
        this.f13915a = z;
        this.f13916b = jcaJceHelper;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyStoreConfig a() {
        BufferedInputStream bufferedInputStream;
        String defaultType = KeyStore.getDefaultType();
        String j2 = PropertyUtils.j("javax.net.ssl.keyStore");
        BufferedInputStream bufferedInputStream2 = null;
        if ("NONE".equals(j2) || j2 == null || !new File(j2).exists()) {
            j2 = null;
        }
        KeyStore createKeyStore = createKeyStore(defaultType);
        String e2 = PropertyUtils.e("javax.net.ssl.keyStorePassword");
        char[] charArray = e2 != null ? e2.toCharArray() : null;
        try {
            if (j2 == null) {
                LOG.config("Initializing default key store as empty");
                bufferedInputStream = null;
            } else {
                LOG.config("Initializing default key store from path: " + j2);
                bufferedInputStream = new BufferedInputStream(new FileInputStream(j2));
            }
            try {
                try {
                    createKeyStore.load(bufferedInputStream, charArray);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream2 = bufferedInputStream;
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    throw th;
                }
            } catch (NullPointerException unused) {
                createKeyStore = KeyStore.getInstance("BCFKS");
                createKeyStore.load(null, null);
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            return new KeyStoreConfig(createKeyStore, charArray);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static KeyStore createKeyStore(String str) {
        String keyStoreType = getKeyStoreType(str);
        String j2 = PropertyUtils.j("javax.net.ssl.keyStoreProvider");
        return TlsUtils.isNullOrEmpty(j2) ? KeyStore.getInstance(keyStoreType) : KeyStore.getInstance(keyStoreType, j2);
    }

    private static String getKeyStoreType(String str) {
        String j2 = PropertyUtils.j("javax.net.ssl.keyStoreType");
        return j2 == null ? str : j2;
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected KeyManager[] engineGetKeyManagers() {
        BCX509ExtendedKeyManager bCX509ExtendedKeyManager = this.f13917c;
        if (bCX509ExtendedKeyManager != null) {
            return new KeyManager[]{bCX509ExtendedKeyManager};
        }
        throw new IllegalStateException("KeyManagerFactory not initialized");
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected void engineInit(KeyStore keyStore, char[] cArr) {
        this.f13917c = new ProvX509KeyManagerSimple(this.f13915a, this.f13916b, keyStore, cArr);
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
        if (!(managerFactoryParameters instanceof KeyStoreBuilderParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be instance of KeyStoreBuilderParameters");
        }
        this.f13917c = new ProvX509KeyManager(this.f13915a, this.f13916b, ((KeyStoreBuilderParameters) managerFactoryParameters).getParameters());
    }
}
