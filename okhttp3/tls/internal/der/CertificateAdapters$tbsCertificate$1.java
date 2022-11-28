package okhttp3.tls.internal.der;

import java.util.List;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CertificateAdapters$tbsCertificate$1 extends Lambda implements Function1<TbsCertificate, List<?>> {
    public static final CertificateAdapters$tbsCertificate$1 INSTANCE = new CertificateAdapters$tbsCertificate$1();

    CertificateAdapters$tbsCertificate$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final List<?> invoke(@NotNull TbsCertificate it) {
        List<?> listOf;
        Intrinsics.checkNotNullParameter(it, "it");
        CertificateAdapters certificateAdapters = CertificateAdapters.INSTANCE;
        listOf = CollectionsKt__CollectionsKt.listOf(Long.valueOf(it.getVersion()), it.getSerialNumber(), it.getSignature(), TuplesKt.to(certificateAdapters.getRdnSequence$okhttp_tls(), it.getIssuer()), it.getValidity(), TuplesKt.to(certificateAdapters.getRdnSequence$okhttp_tls(), it.getSubject()), it.getSubjectPublicKeyInfo(), it.getIssuerUniqueID(), it.getSubjectUniqueID(), it.getExtensions());
        return listOf;
    }
}
