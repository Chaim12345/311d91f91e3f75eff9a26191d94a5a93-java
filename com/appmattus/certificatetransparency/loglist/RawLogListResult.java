package com.appmattus.certificatetransparency.loglist;

import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class RawLogListResult {

    /* loaded from: classes.dex */
    public static class Failure extends RawLogListResult {
        public Failure() {
            super(null);
        }
    }

    /* loaded from: classes.dex */
    public static final class Success extends RawLogListResult {
        @NotNull
        private final byte[] logList;
        @NotNull
        private final byte[] signature;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(@NotNull byte[] logList, @NotNull byte[] signature) {
            super(null);
            Intrinsics.checkNotNullParameter(logList, "logList");
            Intrinsics.checkNotNullParameter(signature, "signature");
            this.logList = logList;
            this.signature = signature;
        }

        public static /* synthetic */ Success copy$default(Success success, byte[] bArr, byte[] bArr2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                bArr = success.logList;
            }
            if ((i2 & 2) != 0) {
                bArr2 = success.signature;
            }
            return success.copy(bArr, bArr2);
        }

        @NotNull
        public final byte[] component1() {
            return this.logList;
        }

        @NotNull
        public final byte[] component2() {
            return this.signature;
        }

        @NotNull
        public final Success copy(@NotNull byte[] logList, @NotNull byte[] signature) {
            Intrinsics.checkNotNullParameter(logList, "logList");
            Intrinsics.checkNotNullParameter(signature, "signature");
            return new Success(logList, signature);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (Intrinsics.areEqual(Success.class, obj != null ? obj.getClass() : null)) {
                Objects.requireNonNull(obj, "null cannot be cast to non-null type com.appmattus.certificatetransparency.loglist.RawLogListResult.Success");
                Success success = (Success) obj;
                return Arrays.equals(this.logList, success.logList) && Arrays.equals(this.signature, success.signature);
            }
            return false;
        }

        @NotNull
        public final byte[] getLogList() {
            return this.logList;
        }

        @NotNull
        public final byte[] getSignature() {
            return this.signature;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.logList) * 31) + Arrays.hashCode(this.signature);
        }

        @NotNull
        public String toString() {
            return "Success(logList=" + Arrays.toString(this.logList) + ", signature=" + Arrays.toString(this.signature) + ')';
        }
    }

    private RawLogListResult() {
    }

    public /* synthetic */ RawLogListResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
