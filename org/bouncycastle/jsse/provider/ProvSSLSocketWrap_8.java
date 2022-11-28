package org.bouncycastle.jsse.provider;

import java.io.InputStream;
import java.net.Socket;
import java.util.List;
import java.util.function.BiFunction;
import javax.net.ssl.SSLSocket;
/* loaded from: classes3.dex */
class ProvSSLSocketWrap_8 extends ProvSSLSocketWrap {
    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketWrap_8(ContextData contextData, Socket socket, InputStream inputStream, boolean z) {
        super(contextData, socket, inputStream, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketWrap_8(ContextData contextData, Socket socket, String str, int i2, boolean z) {
        super(contextData, socket, str, i2, z);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized BiFunction<SSLSocket, List<String>, String> getHandshakeApplicationProtocolSelector() {
        return JsseUtils_8.k0(this.f14001k.getSocketAPSelector());
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setHandshakeApplicationProtocolSelector(BiFunction<SSLSocket, List<String>, String> biFunction) {
        this.f14001k.setSocketAPSelector(JsseUtils_8.s0(biFunction));
    }
}
