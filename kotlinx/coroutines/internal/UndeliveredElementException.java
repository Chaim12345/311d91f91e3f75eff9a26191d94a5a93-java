package kotlinx.coroutines.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class UndeliveredElementException extends RuntimeException {
    public UndeliveredElementException(@NotNull String str, @NotNull Throwable th) {
        super(str, th);
    }
}
