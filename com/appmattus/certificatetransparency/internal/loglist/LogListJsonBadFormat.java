package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogListJsonBadFormat extends LogListResult.Invalid {
    @NotNull
    private final SerializationException exception;

    public LogListJsonBadFormat(@NotNull SerializationException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ LogListJsonBadFormat copy$default(LogListJsonBadFormat logListJsonBadFormat, SerializationException serializationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            serializationException = logListJsonBadFormat.exception;
        }
        return logListJsonBadFormat.copy(serializationException);
    }

    @NotNull
    public final SerializationException component1() {
        return this.exception;
    }

    @NotNull
    public final LogListJsonBadFormat copy(@NotNull SerializationException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new LogListJsonBadFormat(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LogListJsonBadFormat) && Intrinsics.areEqual(this.exception, ((LogListJsonBadFormat) obj).exception);
    }

    @NotNull
    public final SerializationException getException() {
        return this.exception;
    }

    public int hashCode() {
        return this.exception.hashCode();
    }

    @NotNull
    public String toString() {
        return "log-list.json badly formatted with " + ExceptionExtKt.stringStackTrace(this.exception);
    }
}
