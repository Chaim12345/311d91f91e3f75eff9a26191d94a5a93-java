package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ASN1ParsingFailed extends SctVerificationResult.Invalid.FailedWithException {
    @NotNull
    private final IOException exception;

    public ASN1ParsingFailed(@NotNull IOException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.exception = exception;
    }

    public static /* synthetic */ ASN1ParsingFailed copy$default(ASN1ParsingFailed aSN1ParsingFailed, IOException iOException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            iOException = aSN1ParsingFailed.getException();
        }
        return aSN1ParsingFailed.copy(iOException);
    }

    @NotNull
    public final IOException component1() {
        return getException();
    }

    @NotNull
    public final ASN1ParsingFailed copy(@NotNull IOException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new ASN1ParsingFailed(exception);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ASN1ParsingFailed) && Intrinsics.areEqual(getException(), ((ASN1ParsingFailed) obj).getException());
    }

    @Override // com.appmattus.certificatetransparency.SctVerificationResult.Invalid.FailedWithException
    @NotNull
    public IOException getException() {
        return this.exception;
    }

    public int hashCode() {
        return getException().hashCode();
    }

    @NotNull
    public String toString() {
        return "Error during ASN.1 parsing of certificate with: " + ExceptionExtKt.stringStackTrace(getException());
    }
}
