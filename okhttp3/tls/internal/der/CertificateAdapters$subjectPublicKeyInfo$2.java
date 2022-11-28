package okhttp3.tls.internal.der;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$subjectPublicKeyInfo$2 extends Lambda implements Function1<List<?>, SubjectPublicKeyInfo> {
    public static final CertificateAdapters$subjectPublicKeyInfo$2 INSTANCE = new CertificateAdapters$subjectPublicKeyInfo$2();

    CertificateAdapters$subjectPublicKeyInfo$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final SubjectPublicKeyInfo invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type okhttp3.tls.internal.der.AlgorithmIdentifier");
        Object obj2 = it.get(1);
        Objects.requireNonNull(obj2, "null cannot be cast to non-null type okhttp3.tls.internal.der.BitString");
        return new SubjectPublicKeyInfo((AlgorithmIdentifier) obj, (BitString) obj2);
    }
}
