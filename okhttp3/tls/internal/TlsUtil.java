package okhttp3.tls.internal;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.Platform;
import okhttp3.tls.HandshakeCertificates;
import okhttp3.tls.HeldCertificate;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TlsUtil {
    @NotNull
    public static final TlsUtil INSTANCE = new TlsUtil();
    @NotNull
    private static final Lazy localhost$delegate;
    @NotNull
    private static final char[] password;

    static {
        Lazy lazy;
        char[] charArray = "password".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        password = charArray;
        lazy = LazyKt__LazyJVMKt.lazy(TlsUtil$localhost$2.INSTANCE);
        localhost$delegate = lazy;
    }

    private TlsUtil() {
    }

    private final HandshakeCertificates getLocalhost() {
        return (HandshakeCertificates) localhost$delegate.getValue();
    }

    @JvmStatic
    @NotNull
    public static final HandshakeCertificates localhost() {
        return INSTANCE.getLocalhost();
    }

    private final KeyStore newEmptyKeyStore(String str) {
        if (str == null) {
            str = KeyStore.getDefaultType();
        }
        KeyStore keyStore = KeyStore.getInstance(str);
        keyStore.load(null, INSTANCE.getPassword());
        Intrinsics.checkNotNullExpressionValue(keyStore, "getInstance(keyStoreType…utStream, password)\n    }");
        return keyStore;
    }

    @JvmStatic
    @NotNull
    public static final X509KeyManager newKeyManager(@Nullable String str, @Nullable HeldCertificate heldCertificate, @NotNull X509Certificate... intermediates) {
        Intrinsics.checkNotNullParameter(intermediates, "intermediates");
        KeyStore newEmptyKeyStore = INSTANCE.newEmptyKeyStore(str);
        boolean z = true;
        if (heldCertificate != null) {
            Certificate[] certificateArr = new Certificate[intermediates.length + 1];
            certificateArr[0] = heldCertificate.certificate();
            ArraysKt___ArraysJvmKt.copyInto$default(intermediates, certificateArr, 1, 0, 0, 12, (Object) null);
            newEmptyKeyStore.setKeyEntry("private", heldCertificate.keyPair().getPrivate(), password, certificateArr);
        }
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(newEmptyKeyStore, password);
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
        Intrinsics.checkNotNull(keyManagers);
        if (keyManagers.length != 1 || !(keyManagers[0] instanceof X509KeyManager)) {
            z = false;
        }
        if (z) {
            KeyManager keyManager = keyManagers[0];
            Objects.requireNonNull(keyManager, "null cannot be cast to non-null type javax.net.ssl.X509KeyManager");
            return (X509KeyManager) keyManager;
        }
        String arrays = Arrays.toString(keyManagers);
        Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
        throw new IllegalStateException(Intrinsics.stringPlus("Unexpected key managers:", arrays).toString());
    }

    @JvmStatic
    @IgnoreJRERequirement
    @NotNull
    public static final X509TrustManager newTrustManager(@Nullable String str, @NotNull List<? extends X509Certificate> trustedCertificates, @NotNull List<String> insecureHosts) {
        Intrinsics.checkNotNullParameter(trustedCertificates, "trustedCertificates");
        Intrinsics.checkNotNullParameter(insecureHosts, "insecureHosts");
        KeyStore newEmptyKeyStore = INSTANCE.newEmptyKeyStore(str);
        int size = trustedCertificates.size();
        for (int i2 = 0; i2 < size; i2++) {
            newEmptyKeyStore.setCertificateEntry(Intrinsics.stringPlus("cert_", Integer.valueOf(i2)), trustedCertificates.get(i2));
        }
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(newEmptyKeyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        Intrinsics.checkNotNull(trustManagers);
        boolean z = true;
        if (!((trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) ? false : false)) {
            String arrays = Arrays.toString(trustManagers);
            Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
            throw new IllegalStateException(Intrinsics.stringPlus("Unexpected trust managers: ", arrays).toString());
        }
        TrustManager trustManager = trustManagers[0];
        Objects.requireNonNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
        if (insecureHosts.isEmpty()) {
            return x509TrustManager;
        }
        return Platform.Companion.isAndroid() ? new InsecureAndroidTrustManager(x509TrustManager, insecureHosts) : new InsecureExtendedTrustManager((X509ExtendedTrustManager) x509TrustManager, insecureHosts);
    }

    @NotNull
    public final char[] getPassword() {
        return password;
    }
}
