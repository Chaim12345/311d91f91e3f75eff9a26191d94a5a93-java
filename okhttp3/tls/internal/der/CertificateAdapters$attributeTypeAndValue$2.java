package okhttp3.tls.internal.der;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$attributeTypeAndValue$2 extends Lambda implements Function1<List<?>, AttributeTypeAndValue> {
    public static final CertificateAdapters$attributeTypeAndValue$2 INSTANCE = new CertificateAdapters$attributeTypeAndValue$2();

    CertificateAdapters$attributeTypeAndValue$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final AttributeTypeAndValue invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
        return new AttributeTypeAndValue((String) obj, it.get(1));
    }
}
