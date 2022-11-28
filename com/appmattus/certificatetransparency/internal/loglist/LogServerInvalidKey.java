package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogServerInvalidKey extends LogListResult.Invalid {
    @NotNull
    private final Exception exception;
    @NotNull
    private final String key;

    public LogServerInvalidKey(@NotNull Exception exception, @NotNull String key) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(key, "key");
        this.exception = exception;
        this.key = key;
    }

    public static /* synthetic */ LogServerInvalidKey copy$default(LogServerInvalidKey logServerInvalidKey, Exception exc, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            exc = logServerInvalidKey.exception;
        }
        if ((i2 & 2) != 0) {
            str = logServerInvalidKey.key;
        }
        return logServerInvalidKey.copy(exc, str);
    }

    @NotNull
    public final Exception component1() {
        return this.exception;
    }

    @NotNull
    public final String component2() {
        return this.key;
    }

    @NotNull
    public final LogServerInvalidKey copy(@NotNull Exception exception, @NotNull String key) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(key, "key");
        return new LogServerInvalidKey(exception, key);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogServerInvalidKey) {
            LogServerInvalidKey logServerInvalidKey = (LogServerInvalidKey) obj;
            return Intrinsics.areEqual(this.exception, logServerInvalidKey.exception) && Intrinsics.areEqual(this.key, logServerInvalidKey.key);
        }
        return false;
    }

    @NotNull
    public final Exception getException() {
        return this.exception;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    public int hashCode() {
        return (this.exception.hashCode() * 31) + this.key.hashCode();
    }

    @NotNull
    public String toString() {
        return "Public key for log server " + this.key + " cannot be used with " + ExceptionExtKt.stringStackTrace(this.exception);
    }
}
