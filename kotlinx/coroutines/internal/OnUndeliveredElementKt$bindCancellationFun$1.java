package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class OnUndeliveredElementKt$bindCancellationFun$1 extends Lambda implements Function1<Throwable, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function1 f12359a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Object f12360b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ CoroutineContext f12361c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnUndeliveredElementKt$bindCancellationFun$1(Function1 function1, Object obj, CoroutineContext coroutineContext) {
        super(1);
        this.f12359a = function1;
        this.f12360b = obj;
        this.f12361c = coroutineContext;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Throwable th) {
        OnUndeliveredElementKt.callUndeliveredElement(this.f12359a, this.f12360b, this.f12361c);
    }
}
