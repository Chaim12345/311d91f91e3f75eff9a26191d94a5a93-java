package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CertificateEncodingFailed extends SctVerificationResult.Invalid.FailedWithException {
    @NotNull
    private final Exception exception;

    public CertificateEncodingFailed(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ CertificateEncodingFailed copy$default(CertificateEncodingFailed certificateEncodingFailed, Exception exc, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            exc = certificateEncodingFailed.getException();
        }
        return certificateEncodingFailed.copy(exc);
    }

    @NotNull
    public final Exception component1() {
        return getException();
    }

    @NotNull
    public final CertificateEncodingFailed copy(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new CertificateEncodingFailed(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CertificateEncodingFailed) && Intrinsics.areEqual(getException(), ((CertificateEncodingFailed) obj).getException());
    }

    @Override // com.appmattus.certificatetransparency.SctVerificationResult.Invalid.FailedWithException
    @NotNull
    public Exception getException() {
        return this.exception;
    }

    public int hashCode() {
        return getException().hashCode();
    }

    @NotNull
    public String toString() {
        return "Certificate could not be encoded with: " + ExceptionExtKt.stringStackTrace(getException());
    }
}
