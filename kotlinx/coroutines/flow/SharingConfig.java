package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.channels.BufferOverflow;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SharingConfig<T> {
    @JvmField
    @NotNull
    public final CoroutineContext context;
    @JvmField
    public final int extraBufferCapacity;
    @JvmField
    @NotNull
    public final BufferOverflow onBufferOverflow;
    @JvmField
    @NotNull
    public final Flow<T> upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public SharingConfig(@NotNull Flow<? extends T> flow, int i2, @NotNull BufferOverflow bufferOverflow, @NotNull CoroutineContext coroutineContext) {
        this.upstream = flow;
        this.extraBufferCapacity = i2;
        this.onBufferOverflow = bufferOverflow;
        this.context = coroutineContext;
    }
}
