package okhttp3.tls.internal.der;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.tls.internal.der.BasicDerAdapter;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class BasicDerAdapter$toDer$1 extends Lambda implements Function1<BufferedSink, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BasicDerAdapter f12621a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DerWriter f12622b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Object f12623c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicDerAdapter$toDer$1(BasicDerAdapter basicDerAdapter, DerWriter derWriter, Object obj) {
        super(1);
        this.f12621a = basicDerAdapter;
        this.f12622b = derWriter;
        this.f12623c = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(BufferedSink bufferedSink) {
        invoke2(bufferedSink);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull BufferedSink it) {
        BasicDerAdapter.Codec codec;
        Intrinsics.checkNotNullParameter(it, "it");
        codec = this.f12621a.codec;
        codec.encode(this.f12622b, this.f12623c);
    }
}
