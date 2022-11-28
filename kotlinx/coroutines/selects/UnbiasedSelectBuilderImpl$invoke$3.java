package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class UnbiasedSelectBuilderImpl$invoke$3 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SelectClause2 f12377a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ UnbiasedSelectBuilderImpl f12378b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Object f12379c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Function2 f12380d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnbiasedSelectBuilderImpl$invoke$3(SelectClause2 selectClause2, UnbiasedSelectBuilderImpl unbiasedSelectBuilderImpl, Object obj, Function2 function2) {
        super(0);
        this.f12377a = selectClause2;
        this.f12378b = unbiasedSelectBuilderImpl;
        this.f12379c = obj;
        this.f12380d = function2;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.f12377a.registerSelectClause2(this.f12378b.getInstance(), this.f12379c, this.f12380d);
    }
}
