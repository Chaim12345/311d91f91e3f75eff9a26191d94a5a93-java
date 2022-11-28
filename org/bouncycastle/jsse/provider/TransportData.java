package org.bouncycastle.jsse.provider;

import java.net.Socket;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class TransportData {
    private final BCExtendedSSLSession handshakeSession;
    private final BCSSLParameters parameters;

    private TransportData(BCSSLParameters bCSSLParameters, BCExtendedSSLSession bCExtendedSSLSession) {
        this.parameters = bCSSLParameters;
        this.handshakeSession = bCExtendedSSLSession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TransportData a(Socket socket) {
        SSLSocket sSLSocket;
        BCSSLParameters k2;
        if ((socket instanceof SSLSocket) && socket.isConnected() && (k2 = SSLSocketUtil.k((sSLSocket = (SSLSocket) socket))) != null) {
            return new TransportData(k2, SSLSocketUtil.j(sSLSocket));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TransportData b(SSLEngine sSLEngine) {
        BCSSLParameters d2;
        if (sSLEngine == null || (d2 = SSLEngineUtil.d(sSLEngine)) == null) {
            return null;
        }
        return new TransportData(d2, SSLEngineUtil.c(sSLEngine));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCAlgorithmConstraints c(TransportData transportData, boolean z) {
        return transportData == null ? ProvAlgorithmConstraints.f13911b : transportData.d(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List h(TransportData transportData) {
        return transportData == null ? Collections.emptyList() : transportData.g();
    }

    BCAlgorithmConstraints d(boolean z) {
        BCAlgorithmConstraints algorithmConstraints = this.parameters.getAlgorithmConstraints();
        ProvAlgorithmConstraints provAlgorithmConstraints = ProvAlgorithmConstraints.f13911b;
        if (provAlgorithmConstraints == algorithmConstraints) {
            algorithmConstraints = null;
        }
        BCExtendedSSLSession bCExtendedSSLSession = this.handshakeSession;
        if (bCExtendedSSLSession != null && JsseUtils.S(bCExtendedSSLSession.getProtocol())) {
            String[] peerSupportedSignatureAlgorithmsBC = z ? this.handshakeSession.getPeerSupportedSignatureAlgorithmsBC() : this.handshakeSession.getLocalSupportedSignatureAlgorithmsBC();
            if (peerSupportedSignatureAlgorithmsBC != null) {
                return new ProvAlgorithmConstraints(algorithmConstraints, peerSupportedSignatureAlgorithmsBC, true);
            }
        }
        return algorithmConstraints == null ? provAlgorithmConstraints : new ProvAlgorithmConstraints(algorithmConstraints, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCExtendedSSLSession e() {
        return this.handshakeSession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCSSLParameters f() {
        return this.parameters;
    }

    List g() {
        BCExtendedSSLSession bCExtendedSSLSession = this.handshakeSession;
        return bCExtendedSSLSession == null ? Collections.emptyList() : bCExtendedSSLSession.getStatusResponses();
    }
}
