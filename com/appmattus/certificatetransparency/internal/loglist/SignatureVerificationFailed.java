package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.loglist.LogServerSignatureResult;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SignatureVerificationFailed extends LogListResult.Invalid {
    @NotNull
    private final LogServerSignatureResult.Invalid signatureResult;

    public SignatureVerificationFailed(@NotNull LogServerSignatureResult.Invalid signatureResult) {
        Intrinsics.checkNotNullParameter(signatureResult, "signatureResult");
        this.signatureResult = signatureResult;
    }

    public static /* synthetic */ SignatureVerificationFailed copy$default(SignatureVerificationFailed signatureVerificationFailed, LogServerSignatureResult.Invalid invalid, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            invalid = signatureVerificationFailed.signatureResult;
        }
        return signatureVerificationFailed.copy(invalid);
    }

    @NotNull
    public final LogServerSignatureResult.Invalid component1() {
        return this.signatureResult;
    }

    @NotNull
    public final SignatureVerificationFailed copy(@NotNull LogServerSignatureResult.Invalid signatureResult) {
        Intrinsics.checkNotNullParameter(signatureResult, "signatureResult");
        return new SignatureVerificationFailed(signatureResult);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SignatureVerificationFailed) && Intrinsics.areEqual(this.signatureResult, ((SignatureVerificationFailed) obj).signatureResult);
    }

    @NotNull
    public final LogServerSignatureResult.Invalid getSignatureResult() {
        return this.signatureResult;
    }

    public int hashCode() {
        return this.signatureResult.hashCode();
    }

    @NotNull
    public String toString() {
        return "SignatureVerificationFailed(signatureResult=" + this.signatureResult + ')';
    }
}
