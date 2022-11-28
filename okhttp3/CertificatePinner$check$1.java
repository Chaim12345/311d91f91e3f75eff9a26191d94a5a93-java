package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.tls.CertificateChainCleaner;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class CertificatePinner$check$1 extends Lambda implements Function0<List<? extends X509Certificate>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CertificatePinner f12497a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ List f12498b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f12499c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificatePinner$check$1(CertificatePinner certificatePinner, List list, String str) {
        super(0);
        this.f12497a = certificatePinner;
        this.f12498b = list;
        this.f12499c = str;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final List<? extends X509Certificate> invoke() {
        int collectionSizeOrDefault;
        CertificateChainCleaner certificateChainCleaner$okhttp = this.f12497a.getCertificateChainCleaner$okhttp();
        List<Certificate> clean = certificateChainCleaner$okhttp == null ? null : certificateChainCleaner$okhttp.clean(this.f12498b, this.f12499c);
        if (clean == null) {
            clean = this.f12498b;
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(clean, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Certificate certificate : clean) {
            arrayList.add((X509Certificate) certificate);
        }
        return arrayList;
    }
}
