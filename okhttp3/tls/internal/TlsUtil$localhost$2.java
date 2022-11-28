package okhttp3.tls.internal;

import java.net.InetAddress;
import java.security.cert.X509Certificate;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.tls.HandshakeCertificates;
import okhttp3.tls.HeldCertificate;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class TlsUtil$localhost$2 extends Lambda implements Function0<HandshakeCertificates> {
    public static final TlsUtil$localhost$2 INSTANCE = new TlsUtil$localhost$2();

    TlsUtil$localhost$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final HandshakeCertificates invoke() {
        HeldCertificate.Builder commonName = new HeldCertificate.Builder().commonName("localhost");
        String canonicalHostName = InetAddress.getByName("localhost").getCanonicalHostName();
        Intrinsics.checkNotNullExpressionValue(canonicalHostName, "getByName(\"localhost\").canonicalHostName");
        HeldCertificate build = commonName.addSubjectAlternativeName(canonicalHostName).build();
        return new HandshakeCertificates.Builder().heldCertificate(build, new X509Certificate[0]).addTrustedCertificate(build.certificate()).build();
    }
}
