package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class UnbiasedSelectBuilderImpl$invoke$1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SelectClause0 f12371a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ UnbiasedSelectBuilderImpl f12372b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function1 f12373c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnbiasedSelectBuilderImpl$invoke$1(SelectClause0 selectClause0, UnbiasedSelectBuilderImpl unbiasedSelectBuilderImpl, Function1 function1) {
        super(0);
        this.f12371a = selectClause0;
        this.f12372b = unbiasedSelectBuilderImpl;
        this.f12373c = function1;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.f12371a.registerSelectClause0(this.f12372b.getInstance(), this.f12373c);
    }
}
