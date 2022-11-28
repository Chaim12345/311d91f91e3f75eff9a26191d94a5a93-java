package org.bouncycastle.jsse.provider;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes3.dex */
class ProvSSLSocketFactory extends SSLSocketFactory {

    /* renamed from: a  reason: collision with root package name */
    protected final ContextData f13994a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSocketFactory(ContextData contextData) {
        this.f13994a = contextData;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return SSLSocketUtil.a(this.f13994a);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) {
        return SSLSocketUtil.b(this.f13994a, str, i2);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) {
        return SSLSocketUtil.c(this.f13994a, str, i2, inetAddress, i3);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) {
        return SSLSocketUtil.d(this.f13994a, inetAddress, i2);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        return SSLSocketUtil.e(this.f13994a, inetAddress, i2, inetAddress2, i3);
    }

    public Socket createSocket(Socket socket, InputStream inputStream, boolean z) {
        return SSLSocketUtil.g(this.f13994a, socket, inputStream, z);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) {
        return SSLSocketUtil.h(this.f13994a, socket, str, i2, z);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f13994a.c().f(true);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f13994a.c().l();
    }
}
