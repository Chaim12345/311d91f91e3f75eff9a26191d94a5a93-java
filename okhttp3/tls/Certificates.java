package okhttp3.tls;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "Certificates")
/* loaded from: classes3.dex */
public final class Certificates {
    @NotNull
    public static final String certificatePem(@NotNull X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(x509Certificate, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append("-----BEGIN CERTIFICATE-----\n");
        ByteString.Companion companion = ByteString.Companion;
        byte[] encoded = x509Certificate.getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "encoded");
        encodeBase64Lines(sb, ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null));
        sb.append("-----END CERTIFICATE-----\n");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static final X509Certificate decodeCertificatePem(@NotNull String str) {
        Object single;
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            Collection<? extends Certificate> certificates = CertificateFactory.getInstance("X.509").generateCertificates(new Buffer().writeUtf8(str).inputStream());
            Intrinsics.checkNotNullExpressionValue(certificates, "certificates");
            single = CollectionsKt___CollectionsKt.single(certificates);
            if (single != null) {
                return (X509Certificate) single;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("failed to decode certificate", e2);
        } catch (GeneralSecurityException e3) {
            throw new IllegalArgumentException("failed to decode certificate", e3);
        } catch (NoSuchElementException e4) {
            throw new IllegalArgumentException("failed to decode certificate", e4);
        }
    }

    public static final void encodeBase64Lines(@NotNull StringBuilder sb, @NotNull ByteString data) {
        IntRange until;
        IntProgression step;
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(data, "data");
        String base64 = data.base64();
        until = RangesKt___RangesKt.until(0, base64.length());
        step = RangesKt___RangesKt.step(until, 64);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 <= 0 || first > last) && (step2 >= 0 || last > first)) {
            return;
        }
        while (true) {
            int i2 = first + step2;
            sb.append((CharSequence) base64, first, Math.min(first + 64, base64.length()));
            sb.append('\n');
            if (first == last) {
                return;
            }
            first = i2;
        }
    }
}
