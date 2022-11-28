package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RawLogListSigFailedLoadingWithException extends RawLogListResult.Failure {
    @NotNull
    private final Exception exception;

    public RawLogListSigFailedLoadingWithException(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ RawLogListSigFailedLoadingWithException copy$default(RawLogListSigFailedLoadingWithException rawLogListSigFailedLoadingWithException, Exception exc, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            exc = rawLogListSigFailedLoadingWithException.exception;
        }
        return rawLogListSigFailedLoadingWithException.copy(exc);
    }

    @NotNull
    public final Exception component1() {
        return this.exception;
    }

    @NotNull
    public final RawLogListSigFailedLoadingWithException copy(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new RawLogListSigFailedLoadingWithException(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RawLogListSigFailedLoadingWithException) && Intrinsics.areEqual(this.exception, ((RawLogListSigFailedLoadingWithException) obj).exception);
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
