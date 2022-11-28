package org.bouncycastle.jsse.provider;

import java.security.KeyManagementException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCryptoProvider;
/* loaded from: classes3.dex */
class DefaultSSLContextSpi extends ProvSSLContextSpi {
    private static final Logger LOG = Logger.getLogger(DefaultSSLContextSpi.class.getName());

    /* loaded from: classes3.dex */
    private static class LazyInstance {
        private static final Exception initException;
        private static final DefaultSSLContextSpi instance;

        static {
            Exception exc = LazyManagers.initException;
            DefaultSSLContextSpi defaultSSLContextSpi = null;
            if (exc == null) {
                try {
                    defaultSSLContextSpi = new DefaultSSLContextSpi(false, new JcaTlsCryptoProvider());
                } catch (Exception e2) {
                    DefaultSSLContextSpi.LOG.log(Level.WARNING, "Failed to load default SSLContext", (Throwable) e2);
                    exc = DefaultSSLContextSpi.avoidCapturingException(e2);
                }
            }
            initException = exc;
            instance = defaultSSLContextSpi;
        }

        private LazyInstance() {
        }
    }

    /* loaded from: classes3.dex */
    private static class LazyManagers {
        private static final Exception initException;
        private static final KeyManager[] keyManagers;
        private static final TrustManager[] trustManagers;

        /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
        static {
            TrustManager[] trustManagerArr;
            KeyManager[] g2;
            KeyManager[] keyManagerArr = null;
            try {
                trustManagerArr = ProvSSLContextSpi.i();
                e = null;
            } catch (Exception e2) {
                e = e2;
                DefaultSSLContextSpi.LOG.log(Level.WARNING, "Failed to load default trust managers", (Throwable) e);
                trustManagerArr = null;
            }
            if (e == null) {
                try {
                    g2 = ProvSSLContextSpi.g();
                } catch (Exception e3) {
                    e = e3;
                    DefaultSSLContextSpi.LOG.log(Level.WARNING, "Failed to load default key managers", (Throwable) e);
                }
                if (e == null) {
                    e = DefaultSSLContextSpi.avoidCapturingException(e);
                    trustManagerArr = null;
                } else {
                    keyManagerArr = g2;
                }
                initException = e;
                keyManagers = keyManagerArr;
                trustManagers = trustManagerArr;
            }
            g2 = null;
            if (e == null) {
            }
            initException = e;
            keyManagers = keyManagerArr;
            trustManagers = trustManagerArr;
        }

        private LazyManagers() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultSSLContextSpi(boolean z, JcaTlsCryptoProvider jcaTlsCryptoProvider) {
        super(z, jcaTlsCryptoProvider, null);
        if (LazyManagers.initException != null) {
            throw new KeyManagementException("Default key/trust managers unavailable", LazyManagers.initException);
        }
        super.engineInit(LazyManagers.keyManagers, LazyManagers.trustManagers, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Exception avoidCapturingException(Exception exc) {
        return new KeyManagementException(exc.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLContextSpi y() {
        if (LazyInstance.initException == null) {
            return LazyInstance.instance;
        }
        throw LazyInstance.initException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.jsse.provider.ProvSSLContextSpi, javax.net.ssl.SSLContextSpi
    public void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) {
        throw new KeyManagementException("Default SSLContext is initialized automatically");
    }
}
