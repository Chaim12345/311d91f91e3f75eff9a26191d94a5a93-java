package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class ThreadState implements Function1<Throwable, Unit> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _state$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadState.class, "_state");
    @Nullable
    private DisposableHandle cancelHandle;
    @NotNull
    private final Job job;
    @NotNull
    private volatile /* synthetic */ int _state = 0;
    private final Thread targetThread = Thread.currentThread();

    public ThreadState(@NotNull Job job) {
        this.job = job;
    }

    private final Void invalidState(int i2) {
        throw new IllegalStateException(("Illegal state " + i2).toString());
    }

    public final void clearInterrupt() {
        while (true) {
            int i2 = this._state;
            if (i2 != 0) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        Thread.interrupted();
                        return;
                    } else {
                        invalidState(i2);
                        throw new KotlinNothingValueException();
                    }
                }
            } else if (_state$FU.compareAndSet(this, i2, 1)) {
                DisposableHandle disposableHandle = this.cancelHandle;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                    return;
                }
                return;
            }
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        int i2;
        do {
            i2 = this._state;
            if (i2 != 0) {
                if (i2 == 1 || i2 == 2 || i2 == 3) {
                    return;
                }
                invalidState(i2);
                throw new KotlinNothingValueException();
            }
        } while (!_state$FU.compareAndSet(this, i2, 2));
        this.targetThread.interrupt();
        this._state = 3;
    }

    public final void setup() {
        int i2;
        this.cancelHandle = this.job.invokeOnCompletion(true, true, this);
        do {
            i2 = this._state;
            if (i2 != 0) {
                if (i2 == 2 || i2 == 3) {
                    return;
                }
                invalidState(i2);
                throw new KotlinNothingValueException();
            }
        } while (!_state$FU.compareAndSet(this, i2, 0));
    }
}
