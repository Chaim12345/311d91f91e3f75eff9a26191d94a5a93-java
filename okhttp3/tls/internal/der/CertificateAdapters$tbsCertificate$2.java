package okhttp3.tls.internal.der;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$tbsCertificate$2 extends Lambda implements Function1<List<?>, TbsCertificate> {
    public static final CertificateAdapters$tbsCertificate$2 INSTANCE = new CertificateAdapters$tbsCertificate$2();

    CertificateAdapters$tbsCertificate$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final TbsCertificate invoke(@NotNull List<?> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object obj = it.get(0);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Long");
        long longValue = ((Long) obj).longValue();
        Object obj2 = it.get(1);
        Objects.requireNonNull(obj2, "null cannot be cast to non-null type java.math.BigInteger");
        BigInteger bigInteger = (BigInteger) obj2;
        Object obj3 = it.get(2);
        Objects.requireNonNull(obj3, "null cannot be cast to non-null type okhttp3.tls.internal.der.AlgorithmIdentifier");
        AlgorithmIdentifier algorithmIdentifier = (AlgorithmIdentifier) obj3;
        Object obj4 = it.get(3);
        Objects.requireNonNull(obj4, "null cannot be cast to non-null type kotlin.Pair<*, *>");
        Object second = ((Pair) obj4).getSecond();
        Objects.requireNonNull(second, "null cannot be cast to non-null type kotlin.collections.List<kotlin.collections.List<okhttp3.tls.internal.der.AttributeTypeAndValue>>");
        List list = (List) second;
        Object obj5 = it.get(4);
        Objects.requireNonNull(obj5, "null cannot be cast to non-null type okhttp3.tls.internal.der.Validity");
        Validity validity = (Validity) obj5;
        Object obj6 = it.get(5);
        Objects.requireNonNull(obj6, "null cannot be cast to non-null type kotlin.Pair<*, *>");
        Object second2 = ((Pair) obj6).getSecond();
        Objects.requireNonNull(second2, "null cannot be cast to non-null type kotlin.collections.List<kotlin.collections.List<okhttp3.tls.internal.der.AttributeTypeAndValue>>");
        List list2 = (List) second2;
        Object obj7 = it.get(6);
        Objects.requireNonNull(obj7, "null cannot be cast to non-null type okhttp3.tls.internal.der.SubjectPublicKeyInfo");
        Object obj8 = it.get(9);
        Objects.requireNonNull(obj8, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.tls.internal.der.Extension>");
        return new TbsCertificate(longValue, bigInteger, algorithmIdentifier, list, validity, list2, (SubjectPublicKeyInfo) obj7, (BitString) it.get(7), (BitString) it.get(8), (List) obj8);
    }
}
