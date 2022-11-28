package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import java.security.cert.CertificateParsingException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CertificateParsingFailed extends SctVerificationResult.Invalid.FailedWithException {
    @NotNull
    private final CertificateParsingException exception;

    public CertificateParsingFailed(@NotNull CertificateParsingException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ CertificateParsingFailed copy$default(CertificateParsingFailed certificateParsingFailed, CertificateParsingException certificateParsingException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            certificateParsingException = certificateParsingFailed.getException();
        }
        return certificateParsingFailed.copy(certificateParsingException);
    }

    @NotNull
    public final CertificateParsingException component1() {
        return getException();
    }

    @NotNull
    public final CertificateParsingFailed copy(@NotNull CertificateParsingException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new CertificateParsingFailed(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CertificateParsingFailed) && Intrinsics.areEqual(getException(), ((CertificateParsingFailed) obj).getException());
    }

    @Override // com.appmattus.certificatetransparency.SctVerificationResult.Invalid.FailedWithException
    @NotNull
    public CertificateParsingException getException() {
        return this.exception;
    }

    public int hashCode() {
        return getException().hashCode();
    }

    @NotNull
    public String toString() {
        return "Error parsing cert with: " + ExceptionExtKt.stringStackTrace(getException());
    }
}
