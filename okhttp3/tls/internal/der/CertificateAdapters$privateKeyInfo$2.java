package okhttp3.tls.internal.der;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$privateKeyInfo$2 extends Lambda implements Function1<List<?>, PrivateKeyInfo> {
    public static final CertificateAdapters$privateKeyInfo$2 INSTANCE = new CertificateAdapters$privateKeyInfo$2();

    CertificateAdapters$privateKeyInfo$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final PrivateKeyInfo invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Long");
        long longValue = ((Long) obj).longValue();
        Object obj2 = it.get(1);
        Objects.requireNonNull(obj2, "null cannot be cast to non-null type okhttp3.tls.internal.der.AlgorithmIdentifier");
        Object obj3 = it.get(2);
        Objects.requireNonNull(obj3, "null cannot be cast to non-null type okio.ByteString");
        return new PrivateKeyInfo(longValue, (AlgorithmIdentifier) obj2, (ByteString) obj3);
    }
}
