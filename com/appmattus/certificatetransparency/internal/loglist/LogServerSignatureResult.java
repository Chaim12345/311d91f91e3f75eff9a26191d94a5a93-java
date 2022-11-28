package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.utils.ExceptionExtKt;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class LogServerSignatureResult {

    /* loaded from: classes.dex */
    public static abstract class Invalid extends LogServerSignatureResult {

        /* loaded from: classes.dex */
        public static final class NoSuchAlgorithm extends Invalid {
            @NotNull
            private final NoSuchAlgorithmException exception;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NoSuchAlgorithm(@NotNull NoSuchAlgorithmException exception) {
                super(null);
                Intrinsics.checkNotNullParameter(exception, "exception");
                this.exception = exception;
            }

            public static /* synthetic */ NoSuchAlgorithm copy$default(NoSuchAlgorithm noSuchAlgorithm, NoSuchAlgorithmException noSuchAlgorithmException, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    noSuchAlgorithmException = noSuchAlgorithm.exception;
                }
                return noSuchAlgorithm.copy(noSuchAlgorithmException);
            }

            @NotNull
            public final NoSuchAlgorithmException component1() {
                return this.exception;
            }

            @NotNull
            public final NoSuchAlgorithm copy(@NotNull NoSuchAlgorithmException exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                return new NoSuchAlgorithm(exception);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof NoSuchAlgorithm) && Intrinsics.areEqual(this.exception, ((NoSuchAlgorithm) obj).exception);
            }

            @NotNull
            public final NoSuchAlgorithmException getException() {
                return this.exception;
            }

            public int hashCode() {
                return this.exception.hashCode();
            }

            @NotNull
            public String toString() {
                return "Invalid signature (public key) with " + ExceptionExtKt.stringStackTrace(this.exception);
            }
        }

        /* loaded from: classes.dex */
        public static final class PublicKeyNotValid extends Invalid {
            @NotNull
            private final InvalidKeyException exception;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PublicKeyNotValid(@NotNull InvalidKeyException exception) {
                super(null);
                Intrinsics.checkNotNullParameter(exception, "exception");
                this.exception = exception;
            }

            public static /* synthetic */ PublicKeyNotValid copy$default(PublicKeyNotValid publicKeyNotValid, InvalidKeyException invalidKeyException, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    invalidKeyException = publicKeyNotValid.exception;
                }
                return publicKeyNotValid.copy(invalidKeyException);
            }

            @NotNull
            public final InvalidKeyException component1() {
                return this.exception;
            }

            @NotNull
            public final PublicKeyNotValid copy(@NotNull InvalidKeyException exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                return new PublicKeyNotValid(exception);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof PublicKeyNotValid) && Intrinsics.areEqual(this.exception, ((PublicKeyNotValid) obj).exception);
            }

            @NotNull
            public final InvalidKeyException getException() {
                return this.exception;
            }

            public int hashCode() {
                return this.exception.hashCode();
            }

            @NotNull
            public String toString() {
                return "Invalid signature (public key) with " + ExceptionExtKt.stringStackTrace(this.exception);
            }
        }

        /* loaded from: classes.dex */
        public static final class SignatureFailed extends Invalid {
            @NotNull
            public static final SignatureFailed INSTANCE = new SignatureFailed();

            private SignatureFailed() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "Invalid signature";
            }
        }

        /* loaded from: classes.dex */
        public static final class SignatureNotValid extends Invalid {
            @NotNull
            private final SignatureException exception;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SignatureNotValid(@NotNull SignatureException exception) {
                super(null);
                Intrinsics.checkNotNullParameter(exception, "exception");
                this.exception = exception;
            }

            public static /* synthetic */ SignatureNotValid copy$default(SignatureNotValid signatureNotValid, SignatureException signatureException, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    signatureException = signatureNotValid.exception;
                }
                return signatureNotValid.copy(signatureException);
            }

            @NotNull
            public final SignatureException component1() {
                return this.exception;
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
                return (obj instanceof SignatureNotValid) && Intrinsics.areEqual(this.exception, ((SignatureNotValid) obj).exception);
            }

            @NotNull
            public final SignatureException getException() {
                return this.exception;
            }

            public int hashCode() {
                return this.exception.hashCode();
            }

            @NotNull
            public String toString() {
                return "Invalid signature (public key) with " + ExceptionExtKt.stringStackTrace(this.exception);
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
    public static final class Valid extends LogServerSignatureResult {
        @NotNull
        public static final Valid INSTANCE = new Valid();

        private Valid() {
            super(null);
        }

        @NotNull
        public String toString() {
            return "Valid signature";
        }
    }

    private LogServerSignatureResult() {
    }

    public /* synthetic */ LogServerSignatureResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
