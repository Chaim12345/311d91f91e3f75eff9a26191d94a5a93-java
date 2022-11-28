package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class SelectJoinOnCompletion<R> extends JobNode {
    @NotNull
    private final Function1<Continuation<? super R>, Object> block;
    @NotNull
    private final SelectInstance<R> select;

    /* JADX WARN: Multi-variable type inference failed */
    public SelectJoinOnCompletion(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.select = selectInstance;
        this.block = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        if (this.select.trySelect()) {
            CancellableKt.startCoroutineCancellable(this.block, this.select.getCompletion());
        }
    }
}
