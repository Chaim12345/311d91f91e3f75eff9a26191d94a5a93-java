package org.bouncycastle.jsse.provider;

import java.io.Closeable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLSocket;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class ProvSSLSocketBase extends SSLSocket implements BCSSLSocket {

    /* renamed from: c  reason: collision with root package name */
    protected static final boolean f13971c = PropertyUtils.b("org.bouncycastle.jsse.client.assumeOriginalHostName", false);

    /* renamed from: d  reason: collision with root package name */
    protected static final boolean f13972d = PropertyUtils.b("jdk.tls.trustNameService", false);

    /* renamed from: a  reason: collision with root package name */
    protected final Closeable f13973a = new Closeable() { // from class: org.bouncycastle.jsse.provider.ProvSSLSocketBase.1
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ProvSSLSocketBase.this.a();
        }
    };

    /* renamed from: b  reason: collision with root package name */
    protected final Map f13974b = Collections.synchronizedMap(new HashMap(4));

    private Collection<Map.Entry<HandshakeCompletedListener, AccessControlContext>> getHandshakeCompletedEntries() {
        ArrayList arrayList;
        synchronized (this.f13974b) {
            arrayList = this.f13974b.isEmpty() ? null : new ArrayList(this.f13974b.entrySet());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        super.close();
    }

    @Override // javax.net.ssl.SSLSocket
    public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        if (handshakeCompletedListener == null) {
            throw new IllegalArgumentException("'listener' cannot be null");
        }
        this.f13974b.put(handshakeCompletedListener, AccessController.getContext());
    }

    protected InetSocketAddress b(String str, int i2) {
        return str == null ? new InetSocketAddress(InetAddress.getByName(null), i2) : new InetSocketAddress(str, i2);
    }

    protected InetSocketAddress c(InetAddress inetAddress, int i2) {
        return new InetSocketAddress(inetAddress, i2);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public void connect(String str, int i2, int i3) {
        setHost(str);
        connect(b(str, i2), i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(InetAddress inetAddress, int i2) {
        bind(c(inetAddress, i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(String str, int i2) {
        connect(b(str, i2), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(InetAddress inetAddress, int i2) {
        connect(c(inetAddress, i2), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(SSLSession sSLSession) {
        final Collection<Map.Entry<HandshakeCompletedListener, AccessControlContext>> handshakeCompletedEntries = getHandshakeCompletedEntries();
        if (handshakeCompletedEntries == null) {
            return;
        }
        final HandshakeCompletedEvent handshakeCompletedEvent = new HandshakeCompletedEvent(this, sSLSession);
        SSLSocketUtil.i(new Runnable() { // from class: org.bouncycastle.jsse.provider.ProvSSLSocketBase.2
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry entry : handshakeCompletedEntries) {
                    final HandshakeCompletedListener handshakeCompletedListener = (HandshakeCompletedListener) entry.getKey();
                    AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: org.bouncycastle.jsse.provider.ProvSSLSocketBase.2.1
                        @Override // java.security.PrivilegedAction
                        public Void run() {
                            handshakeCompletedListener.handshakeCompleted(handshakeCompletedEvent);
                            return null;
                        }
                    }, (AccessControlContext) entry.getValue());
                }
            }
        });
    }

    @Override // java.net.Socket
    public final boolean getOOBInline() {
        throw new SocketException("This method is ineffective, since sending urgent data is not supported by SSLSockets");
    }

    @Override // javax.net.ssl.SSLSocket
    public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        if (handshakeCompletedListener == null) {
            throw new IllegalArgumentException("'listener' cannot be null");
        }
        if (this.f13974b.remove(handshakeCompletedListener) == null) {
            throw new IllegalArgumentException("'listener' is not registered");
        }
    }

    @Override // java.net.Socket
    public final void sendUrgentData(int i2) {
        throw new SocketException("This method is not supported by SSLSockets");
    }

    @Override // java.net.Socket
    public final void setOOBInline(boolean z) {
        throw new SocketException("This method is ineffective, since sending urgent data is not supported by SSLSockets");
    }
}
