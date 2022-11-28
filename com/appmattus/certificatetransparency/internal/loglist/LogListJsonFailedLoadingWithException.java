package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogListJsonFailedLoadingWithException extends LogListResult.Invalid {
    @NotNull
    private final Exception exception;

    public LogListJsonFailedLoadingWithException(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ LogListJsonFailedLoadingWithException copy$default(LogListJsonFailedLoadingWithException logListJsonFailedLoadingWithException, Exception exc, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            exc = logListJsonFailedLoadingWithException.exception;
        }
        return logListJsonFailedLoadingWithException.copy(exc);
    }

    @NotNull
    public final Exception component1() {
        return this.exception;
    }

    @NotNull
    public final LogListJsonFailedLoadingWithException copy(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new LogListJsonFailedLoadingWithException(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LogListJsonFailedLoadingWithException) && Intrinsics.areEqual(this.exception, ((LogListJsonFailedLoadingWithException) obj).exception);
    }

    @NotNull
    public final Exception getException() {
        return this.exception;
    }

    public int hashCode() {
        return this.exception.hashCode();
    }

    @NotNull
    public String toString() {
        return "log-list.json failed to load with " + ExceptionExtKt.stringStackTrace(this.exception);
    }
}
