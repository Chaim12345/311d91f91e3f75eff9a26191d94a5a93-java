package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface CancellableContinuation<T> extends Continuation<T> {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean cancel$default(CancellableContinuation cancellableContinuation, Throwable th, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    th = null;
                }
                return cancellableContinuation.cancel(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }

        public static /* synthetic */ Object tryResume$default(CancellableContinuation cancellableContinuation, Object obj, Object obj2, int i2, Object obj3) {
            if (obj3 == null) {
                if ((i2 & 2) != 0) {
                    obj2 = null;
                }
                return cancellableContinuation.tryResume(obj, obj2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryResume");
        }
    }

    boolean cancel(@Nullable Throwable th);

    @InternalCoroutinesApi
    void completeResume(@NotNull Object obj);

    @InternalCoroutinesApi
    void initCancellability();

    void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> function1);

    boolean isActive();

    boolean isCancelled();

    boolean isCompleted();

    @ExperimentalCoroutinesApi
    void resume(T t2, @Nullable Function1<? super Throwable, Unit> function1);

    @ExperimentalCoroutinesApi
    void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t2);

    @ExperimentalCoroutinesApi
    void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th);

    @InternalCoroutinesApi
    @Nullable
    Object tryResume(T t2, @Nullable Object obj);

    @InternalCoroutinesApi
    @Nullable
    Object tryResume(T t2, @Nullable Object obj, @Nullable Function1<? super Throwable, Unit> function1);

    @InternalCoroutinesApi
    @Nullable
    Object tryResumeWithException(@NotNull Throwable th);
}
