package org.bouncycastle.est;

import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import org.bouncycastle.est.HttpUtil;
/* loaded from: classes3.dex */
public class ESTRequest {

    /* renamed from: a  reason: collision with root package name */
    final String f13540a;

    /* renamed from: b  reason: collision with root package name */
    final URL f13541b;

    /* renamed from: c  reason: collision with root package name */
    HttpUtil.Headers f13542c;

    /* renamed from: d  reason: collision with root package name */
    final byte[] f13543d;

    /* renamed from: e  reason: collision with root package name */
    final ESTHijacker f13544e;

    /* renamed from: f  reason: collision with root package name */
    final ESTClient f13545f;

    /* renamed from: g  reason: collision with root package name */
    final ESTSourceConnectionListener f13546g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ESTRequest(String str, URL url, byte[] bArr, ESTHijacker eSTHijacker, ESTSourceConnectionListener eSTSourceConnectionListener, HttpUtil.Headers headers, ESTClient eSTClient) {
        this.f13542c = new HttpUtil.Headers();
        this.f13540a = str;
        this.f13541b = url;
        this.f13543d = bArr;
        this.f13544e = eSTHijacker;
        this.f13546g = eSTSourceConnectionListener;
        this.f13542c = headers;
        this.f13545f = eSTClient;
    }

    public ESTClient getClient() {
        return this.f13545f;
    }

    public Map<String, String[]> getHeaders() {
        return (Map) this.f13542c.clone();
    }

    public ESTHijacker getHijacker() {
        return this.f13544e;
    }

    public ESTSourceConnectionListener getListener() {
        return this.f13546g;
    }

    public String getMethod() {
        return this.f13540a;
    }

    public URL getURL() {
        return this.f13541b;
    }

    public void writeData(OutputStream outputStream) {
        byte[] bArr = this.f13543d;
        if (bArr != null) {
            outputStream.write(bArr);
        }
    }
}
