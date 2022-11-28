package okhttp3.tls.internal.der;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$validity$1 extends Lambda implements Function1<Validity, List<?>> {
    public static final CertificateAdapters$validity$1 INSTANCE = new CertificateAdapters$validity$1();

    CertificateAdapters$validity$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final List<?> invoke(@NotNull Validity it) {
        List<?> listOf;
        Intrinsics.checkNotNullParameter(it, "it");
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Long[]{Long.valueOf(it.getNotBefore()), Long.valueOf(it.getNotAfter())});
        return listOf;
    }
}
