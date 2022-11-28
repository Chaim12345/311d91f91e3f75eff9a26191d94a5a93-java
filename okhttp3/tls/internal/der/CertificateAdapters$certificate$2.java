package okhttp3.tls.internal.der;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$certificate$2 extends Lambda implements Function1<List<?>, Certificate> {
    public static final CertificateAdapters$certificate$2 INSTANCE = new CertificateAdapters$certificate$2();

    CertificateAdapters$certificate$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Certificate invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type okhttp3.tls.internal.der.TbsCertificate");
        Object obj2 = it.get(1);
        Objects.requireNonNull(obj2, "null cannot be cast to non-null type okhttp3.tls.internal.der.AlgorithmIdentifier");
        Object obj3 = it.get(2);
        Objects.requireNonNull(obj3, "null cannot be cast to non-null type okhttp3.tls.internal.der.BitString");
        return new Certificate((TbsCertificate) obj, (AlgorithmIdentifier) obj2, (BitString) obj3);
    }
}
