package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class JobKt {
    @NotNull
    public static final CompletableJob Job(@Nullable Job job) {
        return JobKt__JobKt.Job(job);
    }

    public static final void cancel(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        JobKt__JobKt.cancel(coroutineContext, cancellationException);
    }

    public static final void cancel(@NotNull Job job, @NotNull String str, @Nullable Throwable th) {
        JobKt__JobKt.cancel(job, str, th);
    }

    @Nullable
    public static final Object cancelAndJoin(@NotNull Job job, @NotNull Continuation<? super Unit> continuation) {
        return JobKt__JobKt.cancelAndJoin(job, continuation);
    }

    public static final void cancelChildren(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        JobKt__JobKt.cancelChildren(coroutineContext, cancellationException);
    }

    public static final void cancelChildren(@NotNull Job job, @Nullable CancellationException cancellationException) {
        JobKt__JobKt.cancelChildren(job, cancellationException);
    }

    public static final void cancelFutureOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation, future);
    }

    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job job, @NotNull Future<?> future) {
        return JobKt__FutureKt.cancelFutureOnCompletion(job, future);
    }

    @NotNull
    public static final DisposableHandle disposeOnCompletion(@NotNull Job job, @NotNull DisposableHandle disposableHandle) {
        return JobKt__JobKt.disposeOnCompletion(job, disposableHandle);
    }

    public static final void ensureActive(@NotNull CoroutineContext coroutineContext) {
        JobKt__JobKt.ensureActive(coroutineContext);
    }

    public static final void ensureActive(@NotNull Job job) {
        JobKt__JobKt.ensureActive(job);
    }

    @NotNull
    public static final Job getJob(@NotNull CoroutineContext coroutineContext) {
        return JobKt__JobKt.getJob(coroutineContext);
    }

    public static final boolean isActive(@NotNull CoroutineContext coroutineContext) {
        return JobKt__JobKt.isActive(coroutineContext);
    }
}
