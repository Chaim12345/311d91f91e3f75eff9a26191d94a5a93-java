package okhttp3.tls.internal.der;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$attributeTypeAndValue$1 extends Lambda implements Function1<AttributeTypeAndValue, List<?>> {
    public static final CertificateAdapters$attributeTypeAndValue$1 INSTANCE = new CertificateAdapters$attributeTypeAndValue$1();

    CertificateAdapters$attributeTypeAndValue$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final List<?> invoke(@NotNull AttributeTypeAndValue it) {
        List<?> listOf;
        Intrinsics.checkNotNullParameter(it, "it");
        listOf = CollectionsKt__CollectionsKt.listOf(it.getType(), it.getValue());
        return listOf;
    }
}
