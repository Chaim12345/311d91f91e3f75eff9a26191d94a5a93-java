package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import java.security.InvalidKeyException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogPublicKeyNotValid extends SctVerificationResult.Invalid.FailedWithException {
    @NotNull
    private final InvalidKeyException exception;

    public LogPublicKeyNotValid(@NotNull InvalidKeyException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ LogPublicKeyNotValid copy$default(LogPublicKeyNotValid logPublicKeyNotValid, InvalidKeyException invalidKeyException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            invalidKeyException = logPublicKeyNotValid.getException();
        }
        return logPublicKeyNotValid.copy(invalidKeyException);
    }

    @NotNull
    public final InvalidKeyException component1() {
        return getException();
    }

    @NotNull
    public final LogPublicKeyNotValid copy(@NotNull InvalidKeyException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new LogPublicKeyNotValid(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LogPublicKeyNotValid) && Intrinsics.areEqual(getException(), ((LogPublicKeyNotValid) obj).getException());
    }

    @Override // com.appmattus.certificatetransparency.SctVerificationResult.Invalid.FailedWithException
    @NotNull
    public InvalidKeyException getException() {
        return this.exception;
    }

    public int hashCode() {
        return getException().hashCode();
    }

    @NotNull
    public String toString() {
        return "Log's public key cannot be used with " + ExceptionExtKt.stringStackTrace(getException());
    }
}
