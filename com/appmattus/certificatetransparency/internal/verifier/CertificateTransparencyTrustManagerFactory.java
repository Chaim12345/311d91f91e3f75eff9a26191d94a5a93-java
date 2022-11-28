package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.CTTrustManagerBuilderExtKt;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Objects;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509TrustManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CertificateTransparencyTrustManagerFactory extends TrustManagerFactorySpi {
    @Nullable
    private TrustManager[] cachedTrustManager;

    @Override // javax.net.ssl.TrustManagerFactorySpi
    @NotNull
    protected TrustManager[] engineGetTrustManagers() {
        TrustManager[] trustManagerArr;
        TrustManager[] trustManagers;
        if (this.cachedTrustManager == null) {
            CertificateTransparencyTrustManagerFactoryState certificateTransparencyTrustManagerFactoryState = CertificateTransparencyTrustManagerFactoryState.INSTANCE;
            TrustManagerFactory delegate = certificateTransparencyTrustManagerFactoryState.getDelegate();
            if (delegate == null || (trustManagers = delegate.getTrustManagers()) == null) {
                trustManagerArr = null;
            } else {
                ArrayList arrayList = new ArrayList(trustManagers.length);
                for (TrustManager trustManager : trustManagers) {
                    if (trustManager instanceof X509TrustManager) {
                        trustManager = CTTrustManagerBuilderExtKt.certificateTransparencyTrustManager((X509TrustManager) trustManager, certificateTransparencyTrustManagerFactoryState.getInit());
                    }
                    arrayList.add(trustManager);
                }
                Object[] array = arrayList.toArray(new TrustManager[0]);
                Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                trustManagerArr = (TrustManager[]) array;
            }
            this.cachedTrustManager = trustManagerArr;
        }
        TrustManager[] trustManagerArr2 = this.cachedTrustManager;
        return trustManagerArr2 == null ? new TrustManager[0] : trustManagerArr2;
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected void engineInit(@Nullable KeyStore keyStore) {
        TrustManagerFactory delegate = CertificateTransparencyTrustManagerFactoryState.INSTANCE.getDelegate();
        if (delegate != null) {
            delegate.init(keyStore);
        }
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected void engineInit(@Nullable ManagerFactoryParameters managerFactoryParameters) {
        TrustManagerFactory delegate = CertificateTransparencyTrustManagerFactoryState.INSTANCE.getDelegate();
        if (delegate != null) {
            delegate.init(managerFactoryParameters);
        }
    }
}
