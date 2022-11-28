package okhttp3.tls.internal.der;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$algorithmIdentifier$2 extends Lambda implements Function1<List<?>, AlgorithmIdentifier> {
    public static final CertificateAdapters$algorithmIdentifier$2 INSTANCE = new CertificateAdapters$algorithmIdentifier$2();

    CertificateAdapters$algorithmIdentifier$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final AlgorithmIdentifier invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
        return new AlgorithmIdentifier((String) obj, it.get(1));
    }
}
