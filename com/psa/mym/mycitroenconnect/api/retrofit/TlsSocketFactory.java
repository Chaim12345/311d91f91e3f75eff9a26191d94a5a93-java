package com.psa.mym.mycitroenconnect.api.retrofit;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.TlsVersion;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class TlsSocketFactory extends SSLSocketFactory {
    @NotNull
    private final SSLSocketFactory delegate;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String[] ALLOWED_TLS_VERSIONS = {TlsVersion.TLS_1_2.javaName()};

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String[] getALLOWED_TLS_VERSIONS() {
            return TlsSocketFactory.ALLOWED_TLS_VERSIONS;
        }
    }

    public TlsSocketFactory(@NotNull SSLSocketFactory delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    private final Socket patch(Socket socket) {
        if (socket instanceof SSLSocket) {
            ((SSLSocket) socket).setEnabledProtocols(ALLOWED_TLS_VERSIONS);
        }
        return socket;
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull String host, int i2) {
        Intrinsics.checkNotNullParameter(host, "host");
        Socket createSocket = this.delegate.createSocket(host, i2);
        Intrinsics.checkNotNullExpressionValue(createSocket, "delegate.createSocket(host, port)");
        return patch(createSocket);
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull String host, int i2, @NotNull InetAddress localHost, int i3) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(localHost, "localHost");
        Socket createSocket = this.delegate.createSocket(host, i2, localHost, i3);
        Intrinsics.checkNotNullExpressionValue(createSocket, "delegate.createSocket(ho…rt, localHost, localPort)");
        return patch(createSocket);
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull InetAddress host, int i2) {
        Intrinsics.checkNotNullParameter(host, "host");
        Socket createSocket = this.delegate.createSocket(host, i2);
        Intrinsics.checkNotNullExpressionValue(createSocket, "delegate.createSocket(host, port)");
        return patch(createSocket);
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull InetAddress address, int i2, @NotNull InetAddress localAddress, int i3) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(localAddress, "localAddress");
        Socket createSocket = this.delegate.createSocket(address, i2, localAddress, i3);
        Intrinsics.checkNotNullExpressionValue(createSocket, "delegate.createSocket(ad… localAddress, localPort)");
        return patch(createSocket);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    @NotNull
    public Socket createSocket(@NotNull Socket s2, @NotNull String host, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(s2, "s");
        Intrinsics.checkNotNullParameter(host, "host");
        Socket createSocket = this.delegate.createSocket(s2, host, i2, z);
        Intrinsics.checkNotNullExpressionValue(createSocket, "delegate.createSocket(s, host, port, autoClose)");
        return patch(createSocket);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    @NotNull
    public String[] getDefaultCipherSuites() {
        String[] defaultCipherSuites = this.delegate.getDefaultCipherSuites();
        Intrinsics.checkNotNullExpressionValue(defaultCipherSuites, "delegate.defaultCipherSuites");
        return defaultCipherSuites;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    @NotNull
    public String[] getSupportedCipherSuites() {
        String[] supportedCipherSuites = this.delegate.getSupportedCipherSuites();
        Intrinsics.checkNotNullExpressionValue(supportedCipherSuites, "delegate.supportedCipherSuites");
        return supportedCipherSuites;
    }
}
