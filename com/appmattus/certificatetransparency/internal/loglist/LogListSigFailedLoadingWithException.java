package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogListSigFailedLoadingWithException extends LogListResult.Invalid {
    @NotNull
    private final Exception exception;

    public LogListSigFailedLoadingWithException(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ LogListSigFailedLoadingWithException copy$default(LogListSigFailedLoadingWithException logListSigFailedLoadingWithException, Exception exc, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            exc = logListSigFailedLoadingWithException.exception;
        }
        return logListSigFailedLoadingWithException.copy(exc);
    }

    @NotNull
    public final Exception component1() {
        return this.exception;
    }

    @NotNull
    public final LogListSigFailedLoadingWithException copy(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new LogListSigFailedLoadingWithException(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LogListSigFailedLoadingWithException) && Intrinsics.areEqual(this.exception, ((LogListSigFailedLoadingWithException) obj).exception);
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
        return "log-list.sig failed to load with " + ExceptionExtKt.stringStackTrace(this.exception);
    }
}
