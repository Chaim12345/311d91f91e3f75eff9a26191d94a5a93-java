package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import java.security.NoSuchAlgorithmException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class UnsupportedSignatureAlgorithm extends SctVerificationResult.Invalid.FailedWithException {
    @NotNull
    private final String algorithm;
    @Nullable
    private final NoSuchAlgorithmException exception;

    public UnsupportedSignatureAlgorithm(@NotNull String algorithm, @Nullable NoSuchAlgorithmException noSuchAlgorithmException) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.algorithm = algorithm;
        this.exception = noSuchAlgorithmException;
    }

    public /* synthetic */ UnsupportedSignatureAlgorithm(String str, NoSuchAlgorithmException noSuchAlgorithmException, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : noSuchAlgorithmException);
    }

    public static /* synthetic */ UnsupportedSignatureAlgorithm copy$default(UnsupportedSignatureAlgorithm unsupportedSignatureAlgorithm, String str, NoSuchAlgorithmException noSuchAlgorithmException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = unsupportedSignatureAlgorithm.algorithm;
        }
        if ((i2 & 2) != 0) {
            noSuchAlgorithmException = unsupportedSignatureAlgorithm.getException();
        }
        return unsupportedSignatureAlgorithm.copy(str, noSuchAlgorithmException);
    }

    @NotNull
    public final String component1() {
        return this.algorithm;
    }

    @Nullable
    public final NoSuchAlgorithmException component2() {
        return getException();
    }

    @NotNull
    public final UnsupportedSignatureAlgorithm copy(@NotNull String algorithm, @Nullable NoSuchAlgorithmException noSuchAlgorithmException) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        return new UnsupportedSignatureAlgorithm(algorithm, noSuchAlgorithmException);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UnsupportedSignatureAlgorithm) {
            UnsupportedSignatureAlgorithm unsupportedSignatureAlgorithm = (UnsupportedSignatureAlgorithm) obj;
            return Intrinsics.areEqual(this.algorithm, unsupportedSignatureAlgorithm.algorithm) && Intrinsics.areEqual(getException(), unsupportedSignatureAlgorithm.getException());
        }
        return false;
    }

    @NotNull
    public final String getAlgorithm() {
        return this.algorithm;
    }

    @Override // com.appmattus.certificatetransparency.SctVerificationResult.Invalid.FailedWithException
    @Nullable
    public NoSuchAlgorithmException getException() {
        return this.exception;
    }

    public int hashCode() {
        return (this.algorithm.hashCode() * 31) + (getException() == null ? 0 : getException().hashCode());
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        String str;
        if (getException() != null) {
            sb = new StringBuilder();
            sb.append("Unsupported signature algorithm ");
            sb.append(this.algorithm);
            sb.append(" with: ");
            str = ExceptionExtKt.stringStackTrace(getException());
        } else {
            sb = new StringBuilder();
            sb.append("Unsupported signature algorithm ");
            str = this.algorithm;
        }
        sb.append(str);
        return sb.toString();
    }
}
