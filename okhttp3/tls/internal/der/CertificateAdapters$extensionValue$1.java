package okhttp3.tls.internal.der;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class CertificateAdapters$extensionValue$1 extends Lambda implements Function1<Object, DerAdapter<?>> {
    public static final CertificateAdapters$extensionValue$1 INSTANCE = new CertificateAdapters$extensionValue$1();

    CertificateAdapters$extensionValue$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final DerAdapter<?> invoke(@Nullable Object obj) {
        BasicDerAdapter basicDerAdapter;
        BasicDerAdapter basicDerAdapter2;
        if (Intrinsics.areEqual(obj, ObjectIdentifiers.subjectAlternativeName)) {
            basicDerAdapter2 = CertificateAdapters.subjectAlternativeName;
            return basicDerAdapter2;
        } else if (Intrinsics.areEqual(obj, ObjectIdentifiers.basicConstraints)) {
            basicDerAdapter = CertificateAdapters.basicConstraints;
            return basicDerAdapter;
        } else {
            return null;
        }
    }
}
