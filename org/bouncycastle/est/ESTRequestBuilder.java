package org.bouncycastle.est;

import java.net.URL;
import org.bouncycastle.est.HttpUtil;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class ESTRequestBuilder {

    /* renamed from: a  reason: collision with root package name */
    ESTHijacker f13547a;

    /* renamed from: b  reason: collision with root package name */
    ESTSourceConnectionListener f13548b;

    /* renamed from: c  reason: collision with root package name */
    ESTClient f13549c;
    private byte[] data;
    private HttpUtil.Headers headers;
    private final String method;
    private URL url;

    public ESTRequestBuilder(String str, URL url) {
        this.method = str;
        this.url = url;
        this.headers = new HttpUtil.Headers();
    }

    public ESTRequestBuilder(ESTRequest eSTRequest) {
        this.method = eSTRequest.f13540a;
        this.url = eSTRequest.f13541b;
        this.f13548b = eSTRequest.f13546g;
        this.data = eSTRequest.f13543d;
        this.f13547a = eSTRequest.f13544e;
        this.headers = (HttpUtil.Headers) eSTRequest.f13542c.clone();
        this.f13549c = eSTRequest.getClient();
    }

    public ESTRequestBuilder addHeader(String str, String str2) {
        this.headers.add(str, str2);
        return this;
    }

    public ESTRequest build() {
        return new ESTRequest(this.method, this.url, this.data, this.f13547a, this.f13548b, this.headers, this.f13549c);
    }

    public ESTRequestBuilder setHeader(String str, String str2) {
        this.headers.set(str, str2);
        return this;
    }

    public ESTRequestBuilder withClient(ESTClient eSTClient) {
        this.f13549c = eSTClient;
        return this;
    }

    public ESTRequestBuilder withConnectionListener(ESTSourceConnectionListener eSTSourceConnectionListener) {
        this.f13548b = eSTSourceConnectionListener;
        return this;
    }

    public ESTRequestBuilder withData(byte[] bArr) {
        this.data = Arrays.clone(bArr);
        return this;
    }

    public ESTRequestBuilder withHijacker(ESTHijacker eSTHijacker) {
        this.f13547a = eSTHijacker;
        return this;
    }

    public ESTRequestBuilder withURL(URL url) {
        this.url = url;
        return this;
    }
}
