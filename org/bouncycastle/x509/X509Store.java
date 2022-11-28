package org.bouncycastle.x509;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Collection;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.x509.X509Util;
/* loaded from: classes4.dex */
public class X509Store implements Store {
    private Provider _provider;
    private X509StoreSpi _spi;

    private X509Store(Provider provider, X509StoreSpi x509StoreSpi) {
        this._provider = provider;
        this._spi = x509StoreSpi;
    }

    private static X509Store createStore(X509Util.Implementation implementation, X509StoreParameters x509StoreParameters) {
        X509StoreSpi x509StoreSpi = (X509StoreSpi) implementation.a();
        x509StoreSpi.engineInit(x509StoreParameters);
        return new X509Store(implementation.b(), x509StoreSpi);
    }

    public static X509Store getInstance(String str, X509StoreParameters x509StoreParameters) {
        try {
            return createStore(X509Util.f("X509Store", str), x509StoreParameters);
        } catch (NoSuchAlgorithmException e2) {
            throw new NoSuchStoreException(e2.getMessage());
        }
    }

    public static X509Store getInstance(String str, X509StoreParameters x509StoreParameters, String str2) {
        return getInstance(str, x509StoreParameters, X509Util.h(str2));
    }

    public static X509Store getInstance(String str, X509StoreParameters x509StoreParameters, Provider provider) {
        try {
            return createStore(X509Util.g("X509Store", str, provider), x509StoreParameters);
        } catch (NoSuchAlgorithmException e2) {
            throw new NoSuchStoreException(e2.getMessage());
        }
    }

    @Override // org.bouncycastle.util.Store
    public Collection getMatches(Selector selector) {
        return this._spi.engineGetMatches(selector);
    }

    public Provider getProvider() {
        return this._provider;
    }
}
