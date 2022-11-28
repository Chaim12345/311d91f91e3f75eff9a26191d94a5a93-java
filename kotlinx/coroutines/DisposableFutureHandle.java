package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class DisposableFutureHandle implements DisposableHandle {
    @NotNull
    private final Future<?> future;

    public DisposableFutureHandle(@NotNull Future<?> future) {
        this.future = future;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        this.future.cancel(false);
    }

    @NotNull
    public String toString() {
        return "DisposableFutureHandle[" + this.future + AbstractJsonLexerKt.END_LIST;
    }
}
