package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlinx.coroutines.EventLoopImplBase;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    @NotNull
    protected abstract Thread b();

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(long j2, @NotNull EventLoopImplBase.DelayedTask delayedTask) {
        DefaultExecutor.INSTANCE.schedule(j2, delayedTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void d() {
        Unit unit;
        Thread b2 = b();
        if (Thread.currentThread() != b2) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            if (timeSource != null) {
                timeSource.unpark(b2);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                LockSupport.unpark(b2);
            }
        }
    }
}
