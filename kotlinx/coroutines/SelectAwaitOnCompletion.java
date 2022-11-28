package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SelectAwaitOnCompletion<T, R> extends JobNode {
    @NotNull
    private final Function2<T, Continuation<? super R>, Object> block;
    @NotNull
    private final SelectInstance<R> select;

    /* JADX WARN: Multi-variable type inference failed */
    public SelectAwaitOnCompletion(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        this.select = selectInstance;
        this.block = function2;
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
            getJob().selectAwaitCompletion$kotlinx_coroutines_core(this.select, this.block);
        }
    }
}
