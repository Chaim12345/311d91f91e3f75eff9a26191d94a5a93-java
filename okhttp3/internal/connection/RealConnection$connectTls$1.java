package okhttp3.internal.connection;

import java.security.cert.Certificate;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Handshake;
import okhttp3.internal.tls.CertificateChainCleaner;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class RealConnection$connectTls$1 extends Lambda implements Function0<List<? extends Certificate>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CertificatePinner f12543a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Handshake f12544b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Address f12545c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealConnection$connectTls$1(CertificatePinner certificatePinner, Handshake handshake, Address address) {
        super(0);
        this.f12543a = certificatePinner;
        this.f12544b = handshake;
        this.f12545c = address;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final List<? extends Certificate> invoke() {
        CertificateChainCleaner certificateChainCleaner$okhttp = this.f12543a.getCertificateChainCleaner$okhttp();
        Intrinsics.checkNotNull(certificateChainCleaner$okhttp);
        return certificateChainCleaner$okhttp.clean(this.f12544b.peerCertificates(), this.f12545c.url().host());
    }
}
