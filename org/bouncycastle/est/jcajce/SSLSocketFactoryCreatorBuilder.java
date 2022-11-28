package org.bouncycastle.est.jcajce;

import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Objects;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes3.dex */
class SSLSocketFactoryCreatorBuilder {

    /* renamed from: a  reason: collision with root package name */
    protected String f13575a = "TLS";

    /* renamed from: b  reason: collision with root package name */
    protected Provider f13576b;

    /* renamed from: c  reason: collision with root package name */
    protected KeyManager[] f13577c;

    /* renamed from: d  reason: collision with root package name */
    protected X509TrustManager[] f13578d;

    /* renamed from: e  reason: collision with root package name */
    protected SecureRandom f13579e;

    public SSLSocketFactoryCreatorBuilder(X509TrustManager x509TrustManager) {
        Objects.requireNonNull(x509TrustManager, "Trust managers can not be null");
        this.f13578d = new X509TrustManager[]{x509TrustManager};
    }

    public SSLSocketFactoryCreatorBuilder(X509TrustManager[] x509TrustManagerArr) {
        Objects.requireNonNull(x509TrustManagerArr, "Trust managers can not be null");
        this.f13578d = x509TrustManagerArr;
    }

    public SSLSocketFactoryCreator build() {
        return new SSLSocketFactoryCreator() { // from class: org.bouncycastle.est.jcajce.SSLSocketFactoryCreatorBuilder.1
            @Override // org.bouncycastle.est.jcajce.SSLSocketFactoryCreator
            public SSLSocketFactory createFactory() {
                SSLSocketFactoryCreatorBuilder sSLSocketFactoryCreatorBuilder = SSLSocketFactoryCreatorBuilder.this;
                Provider provider = sSLSocketFactoryCreatorBuilder.f13576b;
                String str = sSLSocketFactoryCreatorBuilder.f13575a;
                SSLContext sSLContext = provider != null ? SSLContext.getInstance(str, provider) : SSLContext.getInstance(str);
                SSLSocketFactoryCreatorBuilder sSLSocketFactoryCreatorBuilder2 = SSLSocketFactoryCreatorBuilder.this;
                sSLContext.init(sSLSocketFactoryCreatorBuilder2.f13577c, sSLSocketFactoryCreatorBuilder2.f13578d, sSLSocketFactoryCreatorBuilder2.f13579e);
                return sSLContext.getSocketFactory();
            }

            @Override // org.bouncycastle.est.jcajce.SSLSocketFactoryCreator
            public boolean isTrusted() {
                int i2 = 0;
                while (true) {
                    X509TrustManager[] x509TrustManagerArr = SSLSocketFactoryCreatorBuilder.this.f13578d;
                    if (i2 == x509TrustManagerArr.length) {
                        return false;
                    }
                    if (x509TrustManagerArr[i2].getAcceptedIssuers().length > 0) {
                        return true;
                    }
                    i2++;
                }
            }
        };
    }

    public SSLSocketFactoryCreatorBuilder withKeyManager(KeyManager keyManager) {
        if (keyManager == null) {
            this.f13577c = null;
        } else {
            this.f13577c = new KeyManager[]{keyManager};
        }
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withKeyManagers(KeyManager[] keyManagerArr) {
        this.f13577c = keyManagerArr;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withProvider(String str) {
        Provider provider = Security.getProvider(str);
        this.f13576b = provider;
        if (provider != null) {
            return this;
        }
        throw new NoSuchProviderException("JSSE provider not found: " + str);
    }

    public SSLSocketFactoryCreatorBuilder withProvider(Provider provider) {
        this.f13576b = provider;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withSecureRandom(SecureRandom secureRandom) {
        this.f13579e = secureRandom;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withTLSVersion(String str) {
        this.f13575a = str;
        return this;
    }
}
