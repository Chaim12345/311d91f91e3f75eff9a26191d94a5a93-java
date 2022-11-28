package okhttp3.tls.internal.der;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$extension$2 extends Lambda implements Function1<List<?>, Extension> {
    public static final CertificateAdapters$extension$2 INSTANCE = new CertificateAdapters$extension$2();

    CertificateAdapters$extension$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Extension invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
        Object obj2 = it.get(1);
        Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
        return new Extension((String) obj, ((Boolean) obj2).booleanValue(), it.get(2));
    }
}
