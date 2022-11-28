package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogIdMismatch extends SctVerificationResult.Invalid.Failed {
    @NotNull
    private final String logServerId;
    @NotNull
    private final String sctLogId;

    public LogIdMismatch(@NotNull String sctLogId, @NotNull String logServerId) {
        Intrinsics.checkNotNullParameter(sctLogId, "sctLogId");
        Intrinsics.checkNotNullParameter(logServerId, "logServerId");
        this.sctLogId = sctLogId;
        this.logServerId = logServerId;
    }

    public static /* synthetic */ LogIdMismatch copy$default(LogIdMismatch logIdMismatch, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = logIdMismatch.sctLogId;
        }
        if ((i2 & 2) != 0) {
            str2 = logIdMismatch.logServerId;
        }
        return logIdMismatch.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.sctLogId;
    }

    @NotNull
    public final String component2() {
        return this.logServerId;
    }

    @NotNull
    public final LogIdMismatch copy(@NotNull String sctLogId, @NotNull String logServerId) {
        Intrinsics.checkNotNullParameter(sctLogId, "sctLogId");
        Intrinsics.checkNotNullParameter(logServerId, "logServerId");
        return new LogIdMismatch(sctLogId, logServerId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogIdMismatch) {
            LogIdMismatch logIdMismatch = (LogIdMismatch) obj;
            return Intrinsics.areEqual(this.sctLogId, logIdMismatch.sctLogId) && Intrinsics.areEqual(this.logServerId, logIdMismatch.logServerId);
        }
        return false;
    }

    @NotNull
    public final String getLogServerId() {
        return this.logServerId;
    }

    @NotNull
    public final String getSctLogId() {
        return this.sctLogId;
    }

    public int hashCode() {
        return (this.sctLogId.hashCode() * 31) + this.logServerId.hashCode();
    }

    @NotNull
    public String toString() {
        return "Log ID of SCT, " + this.sctLogId + ", does not match this log's ID, " + this.logServerId;
    }
}
