package com.appmattus.certificatetransparency;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class SctVerificationResult {

    /* loaded from: classes.dex */
    public static abstract class Invalid extends SctVerificationResult {

        /* loaded from: classes.dex */
        public static class Failed extends Invalid {
            public Failed() {
                super(null);
            }
        }

        /* loaded from: classes.dex */
        public static final class FailedVerification extends Invalid {
            @NotNull
            public static final FailedVerification INSTANCE = new FailedVerification();

            private FailedVerification() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "SCT signature failed verification";
            }
        }

        /* loaded from: classes.dex */
        public static abstract class FailedWithException extends Invalid {
            public FailedWithException() {
                super(null);
            }

            @Nullable
            public abstract Exception getException();
        }

        /* loaded from: classes.dex */
        public static final class FutureTimestamp extends Invalid {
            private final long now;
            private final long timestamp;

            public FutureTimestamp(long j2, long j3) {
                super(null);
                this.timestamp = j2;
                this.now = j3;
            }

            public static /* synthetic */ FutureTimestamp copy$default(FutureTimestamp futureTimestamp, long j2, long j3, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    j2 = futureTimestamp.timestamp;
                }
                if ((i2 & 2) != 0) {
                    j3 = futureTimestamp.now;
                }
                return futureTimestamp.copy(j2, j3);
            }

            public final long component1() {
                return this.timestamp;
            }

            public final long component2() {
                return this.now;
            }

            @NotNull
            public final FutureTimestamp copy(long j2, long j3) {
                return new FutureTimestamp(j2, j3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof FutureTimestamp) {
                    FutureTimestamp futureTimestamp = (FutureTimestamp) obj;
                    return this.timestamp == futureTimestamp.timestamp && this.now == futureTimestamp.now;
                }
                return false;
            }

            public final long getNow() {
                return this.now;
            }

            public final long getTimestamp() {
                return this.timestamp;
            }

            public int hashCode() {
                return (Long.hashCode(this.timestamp) * 31) + Long.hashCode(this.now);
            }

            @NotNull
            public String toString() {
                return "SCT timestamp, " + this.timestamp + ", is in the future, current timestamp is " + this.now + '.';
            }
        }

        /* loaded from: classes.dex */
        public static final class LogServerUntrusted extends Invalid {
            private final long logServerValidUntil;
            private final long timestamp;

            public LogServerUntrusted(long j2, long j3) {
                super(null);
                this.timestamp = j2;
                this.logServerValidUntil = j3;
            }

            public static /* synthetic */ LogServerUntrusted copy$default(LogServerUntrusted logServerUntrusted, long j2, long j3, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    j2 = logServerUntrusted.timestamp;
                }
                if ((i2 & 2) != 0) {
                    j3 = logServerUntrusted.logServerValidUntil;
                }
                return logServerUntrusted.copy(j2, j3);
            }

            public final long component1() {
                return this.timestamp;
            }

            public final long component2() {
                return this.logServerValidUntil;
            }

            @NotNull
            public final LogServerUntrusted copy(long j2, long j3) {
                return new LogServerUntrusted(j2, j3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof LogServerUntrusted) {
                    LogServerUntrusted logServerUntrusted = (LogServerUntrusted) obj;
                    return this.timestamp == logServerUntrusted.timestamp && this.logServerValidUntil == logServerUntrusted.logServerValidUntil;
                }
                return false;
            }

            public final long getLogServerValidUntil() {
                return this.logServerValidUntil;
            }

            public final long getTimestamp() {
                return this.timestamp;
            }

            public int hashCode() {
                return (Long.hashCode(this.timestamp) * 31) + Long.hashCode(this.logServerValidUntil);
            }

            @NotNull
            public String toString() {
                return "SCT timestamp, " + this.timestamp + ", is greater than the log server validity, " + this.logServerValidUntil + '.';
            }
        }

        /* loaded from: classes.dex */
        public static final class NoTrustedLogServerFound extends Invalid {
            @NotNull
            public static final NoTrustedLogServerFound INSTANCE = new NoTrustedLogServerFound();

            private NoTrustedLogServerFound() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "No trusted log server found for SCT";
            }
        }

        private Invalid() {
            super(null);
        }

        public /* synthetic */ Invalid(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class Valid extends SctVerificationResult {
        @NotNull
        public static final Valid INSTANCE = new Valid();

        private Valid() {
            super(null);
        }

        @NotNull
        public String toString() {
            return "Valid SCT";
        }
    }

    private SctVerificationResult() {
    }

    public /* synthetic */ SctVerificationResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
