package org.bouncycastle.jsse.util;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes3.dex */
public class CustomSSLSocketFactory extends SSLSocketFactory {

    /* renamed from: a  reason: collision with root package name */
    protected final SSLSocketFactory f14060a;

    public CustomSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        Objects.requireNonNull(sSLSocketFactory, "'delegate' cannot be null");
        this.f14060a = sSLSocketFactory;
    }

    protected Socket a(Socket socket) {
        return socket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return a(this.f14060a.createSocket());
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) {
        return a(this.f14060a.createSocket(str, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) {
        return a(this.f14060a.createSocket(str, i2, inetAddress, i3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) {
        return a(this.f14060a.createSocket(inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        return a(this.f14060a.createSocket(inetAddress, i2, inetAddress2, i3));
    }

    public Socket createSocket(Socket socket, InputStream inputStream, boolean z) {
        return a(this.f14060a.createSocket(socket, inputStream, z));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) {
        return a(this.f14060a.createSocket(socket, str, i2, z));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f14060a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f14060a.getSupportedCipherSuites();
    }
}
