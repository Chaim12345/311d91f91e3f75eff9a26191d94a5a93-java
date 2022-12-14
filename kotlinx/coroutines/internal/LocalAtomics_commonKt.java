package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class LocalAtomics_commonKt {
    public static final int getValue(@NotNull AtomicInteger atomicInteger) {
        return atomicInteger.get();
    }

    public static final void setValue(@NotNull AtomicInteger atomicInteger, int i2) {
        atomicInteger.set(i2);
    }
}
