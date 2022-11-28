package com.appmattus.certificatetransparency;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class VerificationResult {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<String> forDisplay(Map<String, ? extends SctVerificationResult> map) {
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry<String, ? extends SctVerificationResult> entry : map.entrySet()) {
                arrayList.add(entry.getKey() + AbstractJsonLexerKt.COLON + entry.getValue());
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Failure extends VerificationResult {

        /* loaded from: classes.dex */
        public static final class LogServersFailed extends Failure {
            @NotNull
            private final LogListResult.Invalid logListResult;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LogServersFailed(@NotNull LogListResult.Invalid logListResult) {
                super(null);
                Intrinsics.checkNotNullParameter(logListResult, "logListResult");
                this.logListResult = logListResult;
            }

            public static /* synthetic */ LogServersFailed copy$default(LogServersFailed logServersFailed, LogListResult.Invalid invalid, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    invalid = logServersFailed.logListResult;
                }
                return logServersFailed.copy(invalid);
            }

            @NotNull
            public final LogListResult.Invalid component1() {
                return this.logListResult;
            }

            @NotNull
            public final LogServersFailed copy(@NotNull LogListResult.Invalid logListResult) {
                Intrinsics.checkNotNullParameter(logListResult, "logListResult");
                return new LogServersFailed(logListResult);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof LogServersFailed) && Intrinsics.areEqual(this.logListResult, ((LogServersFailed) obj).logListResult);
            }

            @NotNull
            public final LogListResult.Invalid getLogListResult() {
                return this.logListResult;
            }

            public int hashCode() {
                return this.logListResult.hashCode();
            }

            @NotNull
            public String toString() {
                return "Failure: Unable to load log servers with " + this.logListResult;
            }
        }

        /* loaded from: classes.dex */
        public static final class NoCertificates extends Failure {
            @NotNull
            public static final NoCertificates INSTANCE = new NoCertificates();

            private NoCertificates() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "Failure: No certificates";
            }
        }

        /* loaded from: classes.dex */
        public static final class NoScts extends Failure {
            @NotNull
            public static final NoScts INSTANCE = new NoScts();

            private NoScts() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "Failure: This certificate does not have any Signed Certificate Timestamps in it.";
            }
        }

        /* loaded from: classes.dex */
        public static final class TooFewSctsTrusted extends Failure {
            private final int minSctCount;
            @NotNull
            private final Map<String, SctVerificationResult> scts;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public TooFewSctsTrusted(@NotNull Map<String, ? extends SctVerificationResult> scts, int i2) {
                super(null);
                Intrinsics.checkNotNullParameter(scts, "scts");
                this.scts = scts;
                this.minSctCount = i2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ TooFewSctsTrusted copy$default(TooFewSctsTrusted tooFewSctsTrusted, Map map, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    map = tooFewSctsTrusted.scts;
                }
                if ((i3 & 2) != 0) {
                    i2 = tooFewSctsTrusted.minSctCount;
                }
                return tooFewSctsTrusted.copy(map, i2);
            }

            @NotNull
            public final Map<String, SctVerificationResult> component1() {
                return this.scts;
            }

            public final int component2() {
                return this.minSctCount;
            }

            @NotNull
            public final TooFewSctsTrusted copy(@NotNull Map<String, ? extends SctVerificationResult> scts, int i2) {
                Intrinsics.checkNotNullParameter(scts, "scts");
                return new TooFewSctsTrusted(scts, i2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof TooFewSctsTrusted) {
                    TooFewSctsTrusted tooFewSctsTrusted = (TooFewSctsTrusted) obj;
                    return Intrinsics.areEqual(this.scts, tooFewSctsTrusted.scts) && this.minSctCount == tooFewSctsTrusted.minSctCount;
                }
                return false;
            }

            public final int getMinSctCount() {
                return this.minSctCount;
            }

            @NotNull
            public final Map<String, SctVerificationResult> getScts() {
                return this.scts;
            }

            public int hashCode() {
                return (this.scts.hashCode() * 31) + Integer.hashCode(this.minSctCount);
            }

            @NotNull
            public String toString() {
                Map<String, SctVerificationResult> map = this.scts;
                int i2 = 0;
                if (!map.isEmpty()) {
                    for (Map.Entry<String, SctVerificationResult> entry : map.entrySet()) {
                        if (entry.getValue() instanceof SctVerificationResult.Valid) {
                            i2++;
                        }
                    }
                }
                return "Failure: Too few trusted SCTs, required " + this.minSctCount + ", found " + i2 + " in " + VerificationResult.Companion.forDisplay(this.scts);
            }
        }

        /* loaded from: classes.dex */
        public static final class UnknownIoException extends Failure {
            @NotNull
            private final IOException ioException;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UnknownIoException(@NotNull IOException ioException) {
                super(null);
                Intrinsics.checkNotNullParameter(ioException, "ioException");
                this.ioException = ioException;
            }

            public static /* synthetic */ UnknownIoException copy$default(UnknownIoException unknownIoException, IOException iOException, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    iOException = unknownIoException.ioException;
                }
                return unknownIoException.copy(iOException);
            }

            @NotNull
            public final IOException component1() {
                return this.ioException;
            }

            @NotNull
            public final UnknownIoException copy(@NotNull IOException ioException) {
                Intrinsics.checkNotNullParameter(ioException, "ioException");
                return new UnknownIoException(ioException);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UnknownIoException) && Intrinsics.areEqual(this.ioException, ((UnknownIoException) obj).ioException);
            }

            @NotNull
            public final IOException getIoException() {
                return this.ioException;
            }

            public int hashCode() {
                return this.ioException.hashCode();
            }

            @NotNull
            public String toString() {
                return "Failure: IOException " + this.ioException;
            }
        }

        private Failure() {
            super(null);
        }

        public /* synthetic */ Failure(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Success extends VerificationResult {

        /* loaded from: classes.dex */
        public static final class DisabledForHost extends Success {
            @NotNull
            private final String host;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public DisabledForHost(@NotNull String host) {
                super(null);
                Intrinsics.checkNotNullParameter(host, "host");
                this.host = host;
            }

            public static /* synthetic */ DisabledForHost copy$default(DisabledForHost disabledForHost, String str, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    str = disabledForHost.host;
                }
                return disabledForHost.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.host;
            }

            @NotNull
            public final DisabledForHost copy(@NotNull String host) {
                Intrinsics.checkNotNullParameter(host, "host");
                return new DisabledForHost(host);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof DisabledForHost) && Intrinsics.areEqual(this.host, ((DisabledForHost) obj).host);
            }

            @NotNull
            public final String getHost() {
                return this.host;
            }

            public int hashCode() {
                return this.host.hashCode();
            }

            @NotNull
            public String toString() {
                return "Success: SCT not enabled for " + this.host;
            }
        }

        /* loaded from: classes.dex */
        public static final class InsecureConnection extends Success {
            @NotNull
            private final String host;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public InsecureConnection(@NotNull String host) {
                super(null);
                Intrinsics.checkNotNullParameter(host, "host");
                this.host = host;
            }

            public static /* synthetic */ InsecureConnection copy$default(InsecureConnection insecureConnection, String str, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    str = insecureConnection.host;
                }
                return insecureConnection.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.host;
            }

            @NotNull
            public final InsecureConnection copy(@NotNull String host) {
                Intrinsics.checkNotNullParameter(host, "host");
                return new InsecureConnection(host);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof InsecureConnection) && Intrinsics.areEqual(this.host, ((InsecureConnection) obj).host);
            }

            @NotNull
            public final String getHost() {
                return this.host;
            }

            public int hashCode() {
                return this.host.hashCode();
            }

            @NotNull
            public String toString() {
                return "Success: SCT not enabled for insecure connection to " + this.host;
            }
        }

        /* loaded from: classes.dex */
        public static final class Trusted extends Success {
            @NotNull
            private final Map<String, SctVerificationResult> scts;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public Trusted(@NotNull Map<String, ? extends SctVerificationResult> scts) {
                super(null);
                Intrinsics.checkNotNullParameter(scts, "scts");
                this.scts = scts;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Trusted copy$default(Trusted trusted, Map map, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    map = trusted.scts;
                }
                return trusted.copy(map);
            }

            @NotNull
            public final Map<String, SctVerificationResult> component1() {
                return this.scts;
            }

            @NotNull
            public final Trusted copy(@NotNull Map<String, ? extends SctVerificationResult> scts) {
                Intrinsics.checkNotNullParameter(scts, "scts");
                return new Trusted(scts);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Trusted) && Intrinsics.areEqual(this.scts, ((Trusted) obj).scts);
            }

            @NotNull
            public final Map<String, SctVerificationResult> getScts() {
                return this.scts;
            }

            public int hashCode() {
                return this.scts.hashCode();
            }

            @NotNull
            public String toString() {
                return "Success: SCT trusted logs " + VerificationResult.Companion.forDisplay(this.scts);
            }
        }

        private Success() {
            super(null);
        }

        public /* synthetic */ Success(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private VerificationResult() {
    }

    public /* synthetic */ VerificationResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
