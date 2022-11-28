package com.appmattus.certificatetransparency.internal.loglist.parser;

import com.appmattus.certificatetransparency.internal.loglist.LogListJsonFailedLoading;
import com.appmattus.certificatetransparency.internal.loglist.LogListJsonFailedLoadingWithException;
import com.appmattus.certificatetransparency.internal.loglist.LogListSigFailedLoadingWithException;
import com.appmattus.certificatetransparency.internal.loglist.LogServerSignatureResult;
import com.appmattus.certificatetransparency.internal.loglist.RawLogListJsonFailedLoadingWithException;
import com.appmattus.certificatetransparency.internal.loglist.RawLogListSigFailedLoadingWithException;
import com.appmattus.certificatetransparency.internal.loglist.SignatureVerificationFailed;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListToLogListResultTransformer {
    @NotNull
    private final LogListJsonParser logListJsonParser;
    @NotNull
    private final LogListVerifier logListVerifier;

    public RawLogListToLogListResultTransformer() {
        this(null, null, 3, null);
    }

    public RawLogListToLogListResultTransformer(@NotNull LogListVerifier logListVerifier, @NotNull LogListJsonParser logListJsonParser) {
        Intrinsics.checkNotNullParameter(logListVerifier, "logListVerifier");
        Intrinsics.checkNotNullParameter(logListJsonParser, "logListJsonParser");
        this.logListVerifier = logListVerifier;
        this.logListJsonParser = logListJsonParser;
    }

    public /* synthetic */ RawLogListToLogListResultTransformer(LogListVerifier logListVerifier, LogListJsonParser logListJsonParser, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new LogListVerifier(null, 1, null) : logListVerifier, (i2 & 2) != 0 ? new LogListJsonParserV2() : logListJsonParser);
    }

    private final LogListResult.Invalid transformFailure(RawLogListResult.Failure failure) {
        return failure instanceof RawLogListJsonFailedLoadingWithException ? new LogListJsonFailedLoadingWithException(((RawLogListJsonFailedLoadingWithException) failure).getException()) : failure instanceof RawLogListSigFailedLoadingWithException ? new LogListSigFailedLoadingWithException(((RawLogListSigFailedLoadingWithException) failure).getException()) : LogListJsonFailedLoading.INSTANCE;
    }

    private final LogListResult transformSuccess(RawLogListResult.Success success) {
        byte[] component1 = success.component1();
        LogServerSignatureResult verify = this.logListVerifier.verify(component1, success.component2());
        if (verify instanceof LogServerSignatureResult.Valid) {
            return this.logListJsonParser.parseJson(new String(component1, Charsets.UTF_8));
        }
        if (verify instanceof LogServerSignatureResult.Invalid) {
            return new SignatureVerificationFailed((LogServerSignatureResult.Invalid) verify);
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public final LogListResult transform(@NotNull RawLogListResult rawLogListResult) {
        Intrinsics.checkNotNullParameter(rawLogListResult, "rawLogListResult");
        if (rawLogListResult instanceof RawLogListResult.Success) {
            return transformSuccess((RawLogListResult.Success) rawLogListResult);
        }
        if (rawLogListResult instanceof RawLogListResult.Failure) {
            return transformFailure((RawLogListResult.Failure) rawLogListResult);
        }
        throw new NoWhenBranchMatchedException();
    }
}
