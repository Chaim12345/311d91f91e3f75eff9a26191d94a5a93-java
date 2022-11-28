package org.bouncycastle.jsse.util;

import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes3.dex */
public class URLConnectionUtil {

    /* renamed from: a  reason: collision with root package name */
    protected final SSLSocketFactory f14063a;

    public URLConnectionUtil() {
        this(null);
    }

    public URLConnectionUtil(SSLSocketFactory sSLSocketFactory) {
        this.f14063a = sSLSocketFactory;
    }

    protected URLConnection a(URL url, URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
            SSLSocketFactory sSLSocketFactory = this.f14063a;
            if (sSLSocketFactory == null) {
                sSLSocketFactory = httpsURLConnection.getSSLSocketFactory();
            }
            httpsURLConnection.setSSLSocketFactory(b(sSLSocketFactory, url));
            return httpsURLConnection;
        }
        return uRLConnection;
    }

    protected SSLSocketFactory b(SSLSocketFactory sSLSocketFactory, URL url) {
        return new SNISocketFactory(sSLSocketFactory, url);
    }

    public URLConnection openConnection(URL url) {
        return a(url, url.openConnection());
    }

    public URLConnection openConnection(URL url, Proxy proxy) {
        return a(url, url.openConnection(proxy));
    }

    public InputStream openInputStream(URL url) {
        return openConnection(url).getInputStream();
    }
}
