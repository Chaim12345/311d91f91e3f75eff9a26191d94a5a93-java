package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ThreadState {
    @JvmField
    @NotNull
    public final CoroutineContext context;
    @NotNull
    private final ThreadContextElement<Object>[] elements;

    /* renamed from: i  reason: collision with root package name */
    private int f12363i;
    @NotNull
    private final Object[] values;

    public ThreadState(@NotNull CoroutineContext coroutineContext, int i2) {
        this.context = coroutineContext;
        this.values = new Object[i2];
        this.elements = new ThreadContextElement[i2];
    }

    public final void append(@NotNull ThreadContextElement<?> threadContextElement, @Nullable Object obj) {
        Object[] objArr = this.values;
        int i2 = this.f12363i;
        objArr[i2] = obj;
        ThreadContextElement<Object>[] threadContextElementArr = this.elements;
        this.f12363i = i2 + 1;
        threadContextElementArr[i2] = threadContextElement;
    }

    public final void restore(@NotNull CoroutineContext coroutineContext) {
        int length = this.elements.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i2 = length - 1;
            ThreadContextElement<Object> threadContextElement = this.elements[length];
            Intrinsics.checkNotNull(threadContextElement);
            threadContextElement.restoreThreadContext(coroutineContext, this.values[length]);
            if (i2 < 0) {
                return;
            }
            length = i2;
        }
    }
}
