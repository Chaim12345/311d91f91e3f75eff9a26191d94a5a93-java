package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class CoroutinesInternalError extends Error {
    public CoroutinesInternalError(@NotNull String str, @NotNull Throwable th) {
        super(str, th);
    }
}
