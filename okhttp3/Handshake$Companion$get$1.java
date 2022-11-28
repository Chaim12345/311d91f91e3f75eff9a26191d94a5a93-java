package okhttp3;

import java.security.cert.Certificate;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class Handshake$Companion$get$1 extends Lambda implements Function0<List<? extends Certificate>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f12502a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Handshake$Companion$get$1(List list) {
        super(0);
        this.f12502a = list;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final List<? extends Certificate> invoke() {
        return this.f12502a;
    }
}
