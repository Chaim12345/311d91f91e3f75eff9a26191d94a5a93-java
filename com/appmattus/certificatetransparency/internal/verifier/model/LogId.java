package com.appmattus.certificatetransparency.internal.verifier.model;

import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogId {
    @NotNull
    private final byte[] keyId;

    public LogId(@NotNull byte[] keyId) {
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        this.keyId = keyId;
    }

    public static /* synthetic */ LogId copy$default(LogId logId, byte[] bArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bArr = logId.keyId;
        }
        return logId.copy(bArr);
    }

    @NotNull
    public final byte[] component1() {
        return this.keyId;
    }

    @NotNull
    public final LogId copy(@NotNull byte[] keyId) {
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        return new LogId(keyId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(LogId.class, obj != null ? obj.getClass() : null)) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.verifier.model.LogId");
            return Arrays.equals(this.keyId, ((LogId) obj).keyId);
        }
        return false;
    }

    @NotNull
    public final byte[] getKeyId() {
        return this.keyId;
    }

    public int hashCode() {
        return Arrays.hashCode(this.keyId);
    }

    @NotNull
    public String toString() {
        return "LogId(keyId=" + Arrays.toString(this.keyId) + ')';
    }
}
