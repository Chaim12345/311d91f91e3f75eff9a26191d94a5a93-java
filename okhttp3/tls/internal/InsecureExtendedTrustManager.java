package okhttp3.tls.internal;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@IgnoreJRERequirement
/* loaded from: classes3.dex */
public final class InsecureExtendedTrustManager extends X509ExtendedTrustManager {
    @NotNull
    private final X509ExtendedTrustManager delegate;
    @NotNull
    private final List<String> insecureHosts;

    public InsecureExtendedTrustManager(@NotNull X509ExtendedTrustManager delegate, @NotNull List<String> insecureHosts) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(insecureHosts, "insecureHosts");
        this.delegate = delegate;
        this.insecureHosts = insecureHosts;
    }

    @Override // javax.net.ssl.X509TrustManager
    @NotNull
    public Void checkClientTrusted(@NotNull X509Certificate[] chain, @Nullable String str) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        throw new CertificateException("Unsupported operation");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    @NotNull
    public Void checkClientTrusted(@NotNull X509Certificate[] chain, @NotNull String authType, @Nullable Socket socket) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        throw new CertificateException("Unsupported operation");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    @NotNull
    public Void checkClientTrusted(@NotNull X509Certificate[] chain, @NotNull String authType, @Nullable SSLEngine sSLEngine) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        throw new CertificateException("Unsupported operation");
    }

    @Override // javax.net.ssl.X509TrustManager
    @NotNull
    public Void checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        throw new CertificateException("Unsupported operation");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType, @NotNull Socket socket) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        Intrinsics.checkNotNullParameter(socket, "socket");
        if (this.insecureHosts.contains(Util.peerName(socket))) {
            return;
        }
        this.delegate.checkServerTrusted(chain, authType, socket);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType, @NotNull SSLEngine engine) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        Intrinsics.checkNotNullParameter(engine, "engine");
        if (this.insecureHosts.contains(engine.getPeerHost())) {
            return;
        }
        this.delegate.checkServerTrusted(chain, authType, engine);
    }

    @Override // javax.net.ssl.X509TrustManager
    @NotNull
    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] acceptedIssuers = this.delegate.getAcceptedIssuers();
        Intrinsics.checkNotNullExpressionValue(acceptedIssuers, "delegate.acceptedIssuers");
        return acceptedIssuers;
    }
}
