package okhttp3.tls.internal.der;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class CertificateAdapters$algorithmParameters$1 extends Lambda implements Function1<Object, DerAdapter<?>> {
    public static final CertificateAdapters$algorithmParameters$1 INSTANCE = new CertificateAdapters$algorithmParameters$1();

    CertificateAdapters$algorithmParameters$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final DerAdapter<?> invoke(@Nullable Object obj) {
        if (Intrinsics.areEqual(obj, ObjectIdentifiers.sha256WithRSAEncryption) || Intrinsics.areEqual(obj, ObjectIdentifiers.rsaEncryption)) {
            return Adapters.INSTANCE.getNULL();
        }
        if (Intrinsics.areEqual(obj, ObjectIdentifiers.ecPublicKey)) {
            return Adapters.INSTANCE.getOBJECT_IDENTIFIER();
        }
        return null;
    }
}
