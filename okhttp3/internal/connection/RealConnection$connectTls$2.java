package okhttp3.internal.connection;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.Handshake;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class RealConnection$connectTls$2 extends Lambda implements Function0<List<? extends X509Certificate>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ RealConnection f12546a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealConnection$connectTls$2(RealConnection realConnection) {
        super(0);
        this.f12546a = realConnection;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final List<? extends X509Certificate> invoke() {
        Handshake handshake;
        int collectionSizeOrDefault;
        handshake = this.f12546a.handshake;
        Intrinsics.checkNotNull(handshake);
        List<Certificate> peerCertificates = handshake.peerCertificates();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(peerCertificates, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Certificate certificate : peerCertificates) {
            arrayList.add((X509Certificate) certificate);
        }
        return arrayList;
    }
}
