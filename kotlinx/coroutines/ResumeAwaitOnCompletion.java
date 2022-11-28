package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ResumeAwaitOnCompletion<T> extends JobNode {
    @NotNull
    private final CancellableContinuationImpl<T> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeAwaitOnCompletion(@NotNull CancellableContinuationImpl<? super T> cancellableContinuationImpl) {
        this.continuation = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        CancellableContinuationImpl<T> cancellableContinuationImpl;
        Object unboxState;
        Object state$kotlinx_coroutines_core = getJob().getState$kotlinx_coroutines_core();
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new AssertionError();
        }
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            cancellableContinuationImpl = this.continuation;
            Result.Companion companion = Result.Companion;
            unboxState = ResultKt.createFailure(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
        } else {
            cancellableContinuationImpl = this.continuation;
            Result.Companion companion2 = Result.Companion;
            unboxState = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
        }
        cancellableContinuationImpl.resumeWith(Result.m187constructorimpl(unboxState));
    }
}
