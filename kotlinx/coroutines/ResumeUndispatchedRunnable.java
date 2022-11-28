package kotlinx.coroutines;

import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class ResumeUndispatchedRunnable implements Runnable {
    @NotNull
    private final CancellableContinuation<Unit> continuation;
    @NotNull
    private final CoroutineDispatcher dispatcher;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeUndispatchedRunnable(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        this.dispatcher = coroutineDispatcher;
        this.continuation = cancellableContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.continuation.resumeUndispatched(this.dispatcher, Unit.INSTANCE);
    }
}
