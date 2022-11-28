package okhttp3.tls.internal.der;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$basicConstraints$2 extends Lambda implements Function1<List<?>, BasicConstraints> {
    public static final CertificateAdapters$basicConstraints$2 INSTANCE = new CertificateAdapters$basicConstraints$2();

    CertificateAdapters$basicConstraints$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final BasicConstraints invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        return new BasicConstraints(((Boolean) obj).booleanValue(), (Long) it.get(1));
    }
}
