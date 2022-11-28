package org.bouncycastle.jsse.provider;

import java.net.InetAddress;
import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
/* loaded from: classes3.dex */
class ProvSSLServerSocketFactory extends SSLServerSocketFactory {

    /* renamed from: a  reason: collision with root package name */
    protected final ContextData f13945a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLServerSocketFactory(ContextData contextData) {
        this.f13945a = contextData;
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket() {
        return new ProvSSLServerSocket(this.f13945a);
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int i2) {
        return new ProvSSLServerSocket(this.f13945a, i2);
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int i2, int i3) {
        return new ProvSSLServerSocket(this.f13945a, i2, i3);
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int i2, int i3, InetAddress inetAddress) {
        return new ProvSSLServerSocket(this.f13945a, i2, i3, inetAddress);
    }

    @Override // javax.net.ssl.SSLServerSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f13945a.c().f(false);
    }

    @Override // javax.net.ssl.SSLServerSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f13945a.c().l();
    }
}
