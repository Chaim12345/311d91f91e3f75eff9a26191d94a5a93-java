package org.bouncycastle.est.jcajce;

import java.net.Socket;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.est.ESTClientProvider;
import org.bouncycastle.est.ESTService;
import org.bouncycastle.est.ESTServiceBuilder;
/* loaded from: classes3.dex */
public class JsseESTServiceBuilder extends ESTServiceBuilder {

    /* renamed from: d  reason: collision with root package name */
    protected SSLSocketFactoryCreator f13566d;

    /* renamed from: e  reason: collision with root package name */
    protected JsseHostnameAuthorizer f13567e;

    /* renamed from: f  reason: collision with root package name */
    protected int f13568f;

    /* renamed from: g  reason: collision with root package name */
    protected ChannelBindingProvider f13569g;

    /* renamed from: h  reason: collision with root package name */
    protected Set f13570h;

    /* renamed from: i  reason: collision with root package name */
    protected Long f13571i;

    /* renamed from: j  reason: collision with root package name */
    protected SSLSocketFactoryCreatorBuilder f13572j;

    /* renamed from: k  reason: collision with root package name */
    protected boolean f13573k;

    public JsseESTServiceBuilder(String str) {
        super(str);
        this.f13567e = new JsseDefaultHostnameAuthorizer(null);
        this.f13568f = 0;
        this.f13570h = new HashSet();
        this.f13573k = true;
        this.f13572j = new SSLSocketFactoryCreatorBuilder(JcaJceUtils.getTrustAllTrustManager());
    }

    public JsseESTServiceBuilder(String str, int i2, X509TrustManager x509TrustManager) {
        super(str + ":" + i2);
        this.f13567e = new JsseDefaultHostnameAuthorizer(null);
        this.f13568f = 0;
        this.f13570h = new HashSet();
        this.f13573k = true;
        this.f13572j = new SSLSocketFactoryCreatorBuilder(x509TrustManager);
    }

    public JsseESTServiceBuilder(String str, int i2, SSLSocketFactoryCreator sSLSocketFactoryCreator) {
        super(str + ":" + i2);
        this.f13567e = new JsseDefaultHostnameAuthorizer(null);
        this.f13568f = 0;
        this.f13570h = new HashSet();
        this.f13573k = true;
        Objects.requireNonNull(sSLSocketFactoryCreator, "No socket factory creator.");
        this.f13566d = sSLSocketFactoryCreator;
    }

    public JsseESTServiceBuilder(String str, int i2, X509TrustManager[] x509TrustManagerArr) {
        this(str + ":" + i2, x509TrustManagerArr);
    }

    public JsseESTServiceBuilder(String str, X509TrustManager x509TrustManager) {
        super(str);
        this.f13567e = new JsseDefaultHostnameAuthorizer(null);
        this.f13568f = 0;
        this.f13570h = new HashSet();
        this.f13573k = true;
        this.f13572j = new SSLSocketFactoryCreatorBuilder(x509TrustManager);
    }

    public JsseESTServiceBuilder(String str, SSLSocketFactoryCreator sSLSocketFactoryCreator) {
        super(str);
        this.f13567e = new JsseDefaultHostnameAuthorizer(null);
        this.f13568f = 0;
        this.f13570h = new HashSet();
        this.f13573k = true;
        Objects.requireNonNull(sSLSocketFactoryCreator, "No socket factory creator.");
        this.f13566d = sSLSocketFactoryCreator;
    }

    public JsseESTServiceBuilder(String str, X509TrustManager[] x509TrustManagerArr) {
        super(str);
        this.f13567e = new JsseDefaultHostnameAuthorizer(null);
        this.f13568f = 0;
        this.f13570h = new HashSet();
        this.f13573k = true;
        this.f13572j = new SSLSocketFactoryCreatorBuilder(x509TrustManagerArr);
    }

    public JsseESTServiceBuilder addCipherSuites(String str) {
        this.f13570h.add(str);
        return this;
    }

    public JsseESTServiceBuilder addCipherSuites(String[] strArr) {
        this.f13570h.addAll(Arrays.asList(strArr));
        return this;
    }

    @Override // org.bouncycastle.est.ESTServiceBuilder
    public ESTService build() {
        if (this.f13569g == null) {
            this.f13569g = new ChannelBindingProvider(this) { // from class: org.bouncycastle.est.jcajce.JsseESTServiceBuilder.1
                @Override // org.bouncycastle.est.jcajce.ChannelBindingProvider
                public boolean canAccessChannelBinding(Socket socket) {
                    return false;
                }

                @Override // org.bouncycastle.est.jcajce.ChannelBindingProvider
                public byte[] getChannelBinding(Socket socket, String str) {
                    return null;
                }
            };
        }
        if (this.f13566d == null) {
            this.f13566d = this.f13572j.build();
        }
        if (this.f13558b == null) {
            this.f13558b = new DefaultESTHttpClientProvider(this.f13567e, this.f13566d, this.f13568f, this.f13569g, this.f13570h, this.f13571i, this.f13573k);
        }
        return super.build();
    }

    public JsseESTServiceBuilder withChannelBindingProvider(ChannelBindingProvider channelBindingProvider) {
        this.f13569g = channelBindingProvider;
        return this;
    }

    @Override // org.bouncycastle.est.ESTServiceBuilder
    public JsseESTServiceBuilder withClientProvider(ESTClientProvider eSTClientProvider) {
        this.f13558b = eSTClientProvider;
        return this;
    }

    public JsseESTServiceBuilder withFilterCipherSuites(boolean z) {
        this.f13573k = z;
        return this;
    }

    public JsseESTServiceBuilder withHostNameAuthorizer(JsseHostnameAuthorizer jsseHostnameAuthorizer) {
        this.f13567e = jsseHostnameAuthorizer;
        return this;
    }

    public JsseESTServiceBuilder withKeyManager(KeyManager keyManager) {
        if (this.f13566d == null) {
            this.f13572j.withKeyManager(keyManager);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withKeyManagers(KeyManager[] keyManagerArr) {
        if (this.f13566d == null) {
            this.f13572j.withKeyManagers(keyManagerArr);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withProvider(String str) {
        if (this.f13566d == null) {
            this.f13572j.withProvider(str);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withProvider(Provider provider) {
        if (this.f13566d == null) {
            this.f13572j.withProvider(provider);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withReadLimit(long j2) {
        this.f13571i = Long.valueOf(j2);
        return this;
    }

    public JsseESTServiceBuilder withSecureRandom(SecureRandom secureRandom) {
        if (this.f13566d == null) {
            this.f13572j.withSecureRandom(secureRandom);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withTLSVersion(String str) {
        if (this.f13566d == null) {
            this.f13572j.withTLSVersion(str);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withTimeout(int i2) {
        this.f13568f = i2;
        return this;
    }
}
