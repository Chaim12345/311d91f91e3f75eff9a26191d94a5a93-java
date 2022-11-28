package org.bouncycastle.jsse.util;

import java.net.Socket;
import java.net.URL;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
/* loaded from: classes3.dex */
public class SNISocketFactory extends CustomSSLSocketFactory {
    private static final Logger LOG = Logger.getLogger(SNISocketFactory.class.getName());

    /* renamed from: c  reason: collision with root package name */
    protected static final ThreadLocal f14061c = new ThreadLocal();

    /* renamed from: b  reason: collision with root package name */
    protected final URL f14062b;

    public SNISocketFactory(SSLSocketFactory sSLSocketFactory, URL url) {
        super(sSLSocketFactory);
        this.f14062b = url;
    }

    public static SocketFactory getDefault() {
        SSLSocketFactory sSLSocketFactory = (SSLSocketFactory) f14061c.get();
        return sSLSocketFactory != null ? sSLSocketFactory : SSLSocketFactory.getDefault();
    }

    @Override // org.bouncycastle.jsse.util.CustomSSLSocketFactory
    protected Socket a(Socket socket) {
        if (socket instanceof BCSSLSocket) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) socket;
            BCSNIHostName b2 = b();
            if (b2 != null) {
                Logger logger = LOG;
                logger.fine("Setting SNI on socket: " + b2);
                BCSSLParameters bCSSLParameters = new BCSSLParameters();
                bCSSLParameters.setServerNames(Collections.singletonList(b2));
                bCSSLSocket.setParameters(bCSSLParameters);
            }
        }
        return socket;
    }

    protected BCSNIHostName b() {
        return SNIUtil.getBCSNIHostName(this.f14062b);
    }

    public <V> V call(Callable<V> callable) {
        try {
            ThreadLocal threadLocal = f14061c;
            threadLocal.set(this);
            V call = callable.call();
            threadLocal.remove();
            return call;
        } catch (Throwable th) {
            f14061c.remove();
            throw th;
        }
    }
}
