package org.bouncycastle.jsse.provider;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLServerSocket extends SSLServerSocket {

    /* renamed from: a  reason: collision with root package name */
    protected final ContextData f13941a;

    /* renamed from: b  reason: collision with root package name */
    protected final ProvSSLParameters f13942b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f13943c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f13944d;

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLServerSocket(ContextData contextData) {
        this.f13943c = true;
        this.f13944d = false;
        this.f13941a = contextData;
        this.f13942b = contextData.c().h(this.f13944d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLServerSocket(ContextData contextData, int i2) {
        super(i2);
        this.f13943c = true;
        this.f13944d = false;
        this.f13941a = contextData;
        this.f13942b = contextData.c().h(this.f13944d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLServerSocket(ContextData contextData, int i2, int i3) {
        super(i2, i3);
        this.f13943c = true;
        this.f13944d = false;
        this.f13941a = contextData;
        this.f13942b = contextData.c().h(this.f13944d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLServerSocket(ContextData contextData, int i2, int i3, InetAddress inetAddress) {
        super(i2, i3, inetAddress);
        this.f13943c = true;
        this.f13944d = false;
        this.f13941a = contextData;
        this.f13942b = contextData.c().h(this.f13944d);
    }

    @Override // java.net.ServerSocket
    public synchronized Socket accept() {
        ProvSSLSocketDirect f2;
        f2 = SSLSocketUtil.f(this.f13941a, this.f13943c, this.f13944d, this.f13942b.a());
        implAccept(f2);
        f2.j();
        return f2;
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized boolean getEnableSessionCreation() {
        return this.f13943c;
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized String[] getEnabledCipherSuites() {
        return this.f13942b.getCipherSuites();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized String[] getEnabledProtocols() {
        return this.f13942b.getProtocols();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized boolean getNeedClientAuth() {
        return this.f13942b.getNeedClientAuth();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized SSLParameters getSSLParameters() {
        return SSLParametersUtil.b(this.f13942b);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized String[] getSupportedCipherSuites() {
        return this.f13941a.c().l();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized String[] getSupportedProtocols() {
        return this.f13941a.c().n();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized boolean getUseClientMode() {
        return this.f13944d;
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized boolean getWantClientAuth() {
        return this.f13942b.getWantClientAuth();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized void setEnableSessionCreation(boolean z) {
        this.f13943c = z;
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized void setEnabledCipherSuites(String[] strArr) {
        this.f13942b.setCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized void setEnabledProtocols(String[] strArr) {
        this.f13942b.setProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized void setNeedClientAuth(boolean z) {
        this.f13942b.setNeedClientAuth(z);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized void setSSLParameters(SSLParameters sSLParameters) {
        SSLParametersUtil.e(this.f13942b, sSLParameters);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized void setUseClientMode(boolean z) {
        if (this.f13944d != z) {
            this.f13941a.c().t(this.f13942b, z);
            this.f13944d = z;
        }
    }

    @Override // javax.net.ssl.SSLServerSocket
    public synchronized void setWantClientAuth(boolean z) {
        this.f13942b.setWantClientAuth(z);
    }
}
