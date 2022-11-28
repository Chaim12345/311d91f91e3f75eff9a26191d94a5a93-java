package com.google.api.client.http.apache;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.ssl.SSLSocketFactory;
/* loaded from: classes2.dex */
final class SSLSocketFactoryExtension extends SSLSocketFactory {
    private final javax.net.ssl.SSLSocketFactory socketFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLSocketFactoryExtension(SSLContext sSLContext) {
        super((KeyStore) null);
        this.socketFactory = sSLContext.getSocketFactory();
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
    public Socket createSocket() {
        return this.socketFactory.createSocket();
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) {
        SSLSocket sSLSocket = (SSLSocket) this.socketFactory.createSocket(socket, str, i2, z);
        getHostnameVerifier().verify(str, sSLSocket);
        return sSLSocket;
    }
}
