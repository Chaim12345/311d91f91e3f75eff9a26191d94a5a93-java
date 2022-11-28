package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class BlockingEventLoop extends EventLoopImplBase {
    @NotNull
    private final Thread thread;

    public BlockingEventLoop(@NotNull Thread thread) {
        this.thread = thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    @NotNull
    protected Thread b() {
        return this.thread;
    }
}
