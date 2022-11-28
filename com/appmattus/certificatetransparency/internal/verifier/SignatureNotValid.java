package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import java.security.SignatureException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SignatureNotValid extends SctVerificationResult.Invalid.FailedWithException {
    @NotNull
    private final SignatureException exception;

    public SignatureNotValid(@NotNull SignatureException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ SignatureNotValid copy$default(SignatureNotValid signatureNotValid, SignatureException signatureException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            signatureException = signatureNotValid.getException();
        }
        return signatureNotValid.copy(signatureException);
    }

    @NotNull
    public final SignatureException component1() {
        return getException();
    }

    @NotNull
    public final SignatureNotValid copy(@NotNull SignatureException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new SignatureNotValid(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SignatureNotValid) && Intrinsics.areEqual(getException(), ((SignatureNotValid) obj).getException());
    }

    @Override // com.appmattus.certificatetransparency.SctVerificationResult.Invalid.FailedWithException
    @NotNull
    public SignatureException getException() {
        return this.exception;
    }

    public int hashCode() {
        return getException().hashCode();
    }

    @NotNull
    public String toString() {
        return "Signature object not properly initialized or signature from SCT is improperly encoded with: " + ExceptionExtKt.stringStackTrace(getException());
    }
}
