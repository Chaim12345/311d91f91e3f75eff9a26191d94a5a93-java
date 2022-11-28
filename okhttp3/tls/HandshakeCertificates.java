package okhttp3.tls;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okhttp3.tls.internal.TlsUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HandshakeCertificates {
    @NotNull
    private final X509KeyManager keyManager;
    @NotNull
    private final X509TrustManager trustManager;

    /* loaded from: classes3.dex */
    public static final class Builder {
        @Nullable
        private HeldCertificate heldCertificate;
        @Nullable
        private X509Certificate[] intermediates;
        @NotNull
        private final List<X509Certificate> trustedCertificates = new ArrayList();
        @NotNull
        private final List<String> insecureHosts = new ArrayList();

        @NotNull
        public final Builder addInsecureHost(@NotNull String hostname) {
            Intrinsics.checkNotNullParameter(hostname, "hostname");
            this.insecureHosts.add(hostname);
            return this;
        }

        @NotNull
        public final Builder addPlatformTrustedCertificates() {
            X509TrustManager platformTrustManager = Platform.Companion.get().platformTrustManager();
            List<X509Certificate> list = this.trustedCertificates;
            X509Certificate[] acceptedIssuers = platformTrustManager.getAcceptedIssuers();
            Collections.addAll(list, Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
            return this;
        }

        @NotNull
        public final Builder addTrustedCertificate(@NotNull X509Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            this.trustedCertificates.add(certificate);
            return this;
        }

        @NotNull
        public final HandshakeCertificates build() {
            List immutableList = Util.toImmutableList(this.insecureHosts);
            HeldCertificate heldCertificate = this.heldCertificate;
            X509Certificate[] x509CertificateArr = this.intermediates;
            if (x509CertificateArr == null) {
                x509CertificateArr = new X509Certificate[0];
            }
            return new HandshakeCertificates(TlsUtil.newKeyManager(null, heldCertificate, (X509Certificate[]) Arrays.copyOf(x509CertificateArr, x509CertificateArr.length)), TlsUtil.newTrustManager(null, this.trustedCertificates, immutableList), null);
        }

        @NotNull
        public final Builder heldCertificate(@NotNull HeldCertificate heldCertificate, @NotNull X509Certificate... intermediates) {
            Intrinsics.checkNotNullParameter(heldCertificate, "heldCertificate");
            Intrinsics.checkNotNullParameter(intermediates, "intermediates");
            this.heldCertificate = heldCertificate;
            this.intermediates = (X509Certificate[]) Arrays.copyOf(intermediates, intermediates.length);
            return this;
        }
    }

    private HandshakeCertificates(X509KeyManager x509KeyManager, X509TrustManager x509TrustManager) {
        this.keyManager = x509KeyManager;
        this.trustManager = x509TrustManager;
    }

    public /* synthetic */ HandshakeCertificates(X509KeyManager x509KeyManager, X509TrustManager x509TrustManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(x509KeyManager, x509TrustManager);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "keyManager", imports = {}))
    @JvmName(name = "-deprecated_keyManager")
    @NotNull
    /* renamed from: -deprecated_keyManager  reason: not valid java name */
    public final X509KeyManager m1830deprecated_keyManager() {
        return this.keyManager;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "trustManager", imports = {}))
    @JvmName(name = "-deprecated_trustManager")
    @NotNull
    /* renamed from: -deprecated_trustManager  reason: not valid java name */
    public final X509TrustManager m1831deprecated_trustManager() {
        return this.trustManager;
    }

    @JvmName(name = "keyManager")
    @NotNull
    public final X509KeyManager keyManager() {
        return this.keyManager;
    }

    @NotNull
    public final SSLContext sslContext() {
        SSLContext newSSLContext = Platform.Companion.get().newSSLContext();
        newSSLContext.init(new KeyManager[]{keyManager()}, new TrustManager[]{trustManager()}, new SecureRandom());
        return newSSLContext;
    }

    @NotNull
    public final SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory socketFactory = sslContext().getSocketFactory();
        Intrinsics.checkNotNullExpressionValue(socketFactory, "sslContext().socketFactory");
        return socketFactory;
    }

    @JvmName(name = "trustManager")
    @NotNull
    public final X509TrustManager trustManager() {
        return this.trustManager;
    }
}
