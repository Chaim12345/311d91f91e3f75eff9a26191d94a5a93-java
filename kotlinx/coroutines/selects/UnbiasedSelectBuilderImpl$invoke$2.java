package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class UnbiasedSelectBuilderImpl$invoke$2 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SelectClause1 f12374a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ UnbiasedSelectBuilderImpl f12375b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function2 f12376c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnbiasedSelectBuilderImpl$invoke$2(SelectClause1 selectClause1, UnbiasedSelectBuilderImpl unbiasedSelectBuilderImpl, Function2 function2) {
        super(0);
        this.f12374a = selectClause1;
        this.f12375b = unbiasedSelectBuilderImpl;
        this.f12376c = function2;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.f12374a.registerSelectClause1(this.f12375b.getInstance(), this.f12376c);
    }
}
