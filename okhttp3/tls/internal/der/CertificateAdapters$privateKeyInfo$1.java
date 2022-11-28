package okhttp3.tls.internal.der;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$privateKeyInfo$1 extends Lambda implements Function1<PrivateKeyInfo, List<?>> {
    public static final CertificateAdapters$privateKeyInfo$1 INSTANCE = new CertificateAdapters$privateKeyInfo$1();

    CertificateAdapters$privateKeyInfo$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final List<?> invoke(@NotNull PrivateKeyInfo it) {
        List<?> listOf;
        Intrinsics.checkNotNullParameter(it, "it");
        listOf = CollectionsKt__CollectionsKt.listOf(Long.valueOf(it.getVersion()), it.getAlgorithmIdentifier(), it.getPrivateKey());
        return listOf;
    }
}
