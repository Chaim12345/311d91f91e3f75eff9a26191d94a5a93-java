package okhttp3.tls.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class InsecureAndroidTrustManager implements X509TrustManager {
    @Nullable
    private final Method checkServerTrustedMethod;
    @NotNull
    private final X509TrustManager delegate;
    @NotNull
    private final List<String> insecureHosts;

    public InsecureAndroidTrustManager(@NotNull X509TrustManager delegate, @NotNull List<String> insecureHosts) {
        Method method;
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(insecureHosts, "insecureHosts");
        this.delegate = delegate;
        this.insecureHosts = insecureHosts;
        try {
            method = delegate.getClass().getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class);
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        this.checkServerTrustedMethod = method;
    }

    @Override // javax.net.ssl.X509TrustManager
    @NotNull
    public Void checkClientTrusted(@NotNull X509Certificate[] chain, @Nullable String str) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        throw new CertificateException("Unsupported operation");
    }

    @Override // javax.net.ssl.X509TrustManager
    @NotNull
    public Void checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        throw new CertificateException("Unsupported operation");
    }

    @NotNull
    public final List<Certificate> checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType, @NotNull String host) {
        List<Certificate> emptyList;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        Intrinsics.checkNotNullParameter(host, "host");
        if (this.insecureHosts.contains(host)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        try {
            Method method = this.checkServerTrustedMethod;
            if (method != null) {
                Object invoke = method.invoke(this.delegate, chain, authType, host);
                if (invoke != null) {
                    return (List) invoke;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<java.security.cert.Certificate>");
            }
            throw new CertificateException("Failed to call checkServerTrusted");
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            Intrinsics.checkNotNullExpressionValue(targetException, "e.targetException");
            throw targetException;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    @NotNull
    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] acceptedIssuers = this.delegate.getAcceptedIssuers();
        Intrinsics.checkNotNullExpressionValue(acceptedIssuers, "delegate.acceptedIssuers");
        return acceptedIssuers;
    }
}
