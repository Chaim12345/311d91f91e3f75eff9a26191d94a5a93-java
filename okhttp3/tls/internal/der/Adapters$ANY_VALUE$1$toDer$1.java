package okhttp3.tls.internal.der;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class Adapters$ANY_VALUE$1$toDer$1 extends Lambda implements Function1<BufferedSink, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DerWriter f12605a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ AnyValue f12606b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Adapters$ANY_VALUE$1$toDer$1(DerWriter derWriter, AnyValue anyValue) {
        super(1);
        this.f12605a = derWriter;
        this.f12606b = anyValue;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(BufferedSink bufferedSink) {
        invoke2(bufferedSink);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull BufferedSink it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.f12605a.writeOctetString(this.f12606b.getBytes());
        this.f12605a.setConstructed(this.f12606b.getConstructed());
    }
}
