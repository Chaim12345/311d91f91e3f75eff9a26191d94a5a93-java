package org.bouncycastle.jsse.provider;

import java.net.InetAddress;
import java.util.List;
import java.util.function.BiFunction;
import javax.net.ssl.SSLSocket;
/* loaded from: classes3.dex */
class ProvSSLSocketDirect_8 extends ProvSSLSocketDirect {
    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect_8(ContextData contextData) {
        super(contextData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect_8(ContextData contextData, String str, int i2) {
        super(contextData, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect_8(ContextData contextData, String str, int i2, InetAddress inetAddress, int i3) {
        super(contextData, str, i2, inetAddress, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect_8(ContextData contextData, InetAddress inetAddress, int i2) {
        super(contextData, inetAddress, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect_8(ContextData contextData, InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        super(contextData, inetAddress, i2, inetAddress2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSocketDirect_8(ContextData contextData, boolean z, boolean z2, ProvSSLParameters provSSLParameters) {
        super(contextData, z, z2, provSSLParameters);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized BiFunction<SSLSocket, List<String>, String> getHandshakeApplicationProtocolSelector() {
        return JsseUtils_8.k0(this.f13984h.getSocketAPSelector());
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setHandshakeApplicationProtocolSelector(BiFunction<SSLSocket, List<String>, String> biFunction) {
        this.f13984h.setSocketAPSelector(JsseUtils_8.s0(biFunction));
    }
}
