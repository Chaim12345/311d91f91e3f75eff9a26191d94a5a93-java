package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class UnbiasedSelectBuilderImpl$onTimeout$1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UnbiasedSelectBuilderImpl f12381a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f12382b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function1 f12383c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnbiasedSelectBuilderImpl$onTimeout$1(UnbiasedSelectBuilderImpl unbiasedSelectBuilderImpl, long j2, Function1 function1) {
        super(0);
        this.f12381a = unbiasedSelectBuilderImpl;
        this.f12382b = j2;
        this.f12383c = function1;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.f12381a.getInstance().onTimeout(this.f12382b, this.f12383c);
    }
}
