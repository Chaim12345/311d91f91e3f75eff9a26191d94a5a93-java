package kotlin.coroutines.jvm.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class RunSuspend implements Continuation<Unit> {
    @Nullable
    private Result<Unit> result;

    public final void await() {
        synchronized (this) {
            while (true) {
                Result<Unit> m1382getResultxLWZpok = m1382getResultxLWZpok();
                if (m1382getResultxLWZpok == null) {
                    wait();
                } else {
                    ResultKt.throwOnFailure(m1382getResultxLWZpok.m196unboximpl());
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Nullable
    /* renamed from: getResult-xLWZpok  reason: not valid java name */
    public final Result<Unit> m1382getResultxLWZpok() {
        return this.result;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        synchronized (this) {
            this.result = Result.m186boximpl(obj);
            notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void setResult(@Nullable Result<Unit> result) {
        this.result = result;
    }
}
