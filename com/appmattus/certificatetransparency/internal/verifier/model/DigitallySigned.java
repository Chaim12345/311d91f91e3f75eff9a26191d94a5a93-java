package com.appmattus.certificatetransparency.internal.verifier.model;

import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class DigitallySigned {
    @NotNull
    private final HashAlgorithm hashAlgorithm;
    @NotNull
    private final byte[] signature;
    @NotNull
    private final SignatureAlgorithm signatureAlgorithm;

    /* loaded from: classes.dex */
    public enum HashAlgorithm {
        NONE(0),
        MD5(1),
        SHA1(2),
        SHA224(3),
        SHA256(4),
        SHA384(5),
        SHA512(6);
        
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int number;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Nullable
            public final HashAlgorithm forNumber(int i2) {
                HashAlgorithm[] values;
                for (HashAlgorithm hashAlgorithm : HashAlgorithm.values()) {
                    if (hashAlgorithm.getNumber() == i2) {
                        return hashAlgorithm;
                    }
                }
                return null;
            }
        }

        HashAlgorithm(int i2) {
            this.number = i2;
        }

        public final int getNumber() {
            return this.number;
        }
    }

    /* loaded from: classes.dex */
    public enum SignatureAlgorithm {
        ANONYMOUS(0),
        RSA(1),
        DSA(2),
        ECDSA(3);
        
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int number;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Nullable
            public final SignatureAlgorithm forNumber(int i2) {
                SignatureAlgorithm[] values;
                for (SignatureAlgorithm signatureAlgorithm : SignatureAlgorithm.values()) {
                    if (signatureAlgorithm.getNumber() == i2) {
                        return signatureAlgorithm;
                    }
                }
                return null;
            }
        }

        SignatureAlgorithm(int i2) {
            this.number = i2;
        }

        public final int getNumber() {
            return this.number;
        }
    }

    public DigitallySigned(@NotNull HashAlgorithm hashAlgorithm, @NotNull SignatureAlgorithm signatureAlgorithm, @NotNull byte[] signature) {
        Intrinsics.checkNotNullParameter(hashAlgorithm, "hashAlgorithm");
        Intrinsics.checkNotNullParameter(signatureAlgorithm, "signatureAlgorithm");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.hashAlgorithm = hashAlgorithm;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signature = signature;
    }

    public /* synthetic */ DigitallySigned(HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm, byte[] bArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? HashAlgorithm.NONE : hashAlgorithm, (i2 & 2) != 0 ? SignatureAlgorithm.ANONYMOUS : signatureAlgorithm, bArr);
    }

    public static /* synthetic */ DigitallySigned copy$default(DigitallySigned digitallySigned, HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm, byte[] bArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            hashAlgorithm = digitallySigned.hashAlgorithm;
        }
        if ((i2 & 2) != 0) {
            signatureAlgorithm = digitallySigned.signatureAlgorithm;
        }
        if ((i2 & 4) != 0) {
            bArr = digitallySigned.signature;
        }
        return digitallySigned.copy(hashAlgorithm, signatureAlgorithm, bArr);
    }

    @NotNull
    public final HashAlgorithm component1() {
        return this.hashAlgorithm;
    }

    @NotNull
    public final SignatureAlgorithm component2() {
        return this.signatureAlgorithm;
    }

    @NotNull
    public final byte[] component3() {
        return this.signature;
    }

    @NotNull
    public final DigitallySigned copy(@NotNull HashAlgorithm hashAlgorithm, @NotNull SignatureAlgorithm signatureAlgorithm, @NotNull byte[] signature) {
        Intrinsics.checkNotNullParameter(hashAlgorithm, "hashAlgorithm");
        Intrinsics.checkNotNullParameter(signatureAlgorithm, "signatureAlgorithm");
        Intrinsics.checkNotNullParameter(signature, "signature");
        return new DigitallySigned(hashAlgorithm, signatureAlgorithm, signature);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(DigitallySigned.class, obj != null ? obj.getClass() : null)) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.verifier.model.DigitallySigned");
            DigitallySigned digitallySigned = (DigitallySigned) obj;
            return this.hashAlgorithm == digitallySigned.hashAlgorithm && this.signatureAlgorithm == digitallySigned.signatureAlgorithm && Arrays.equals(this.signature, digitallySigned.signature);
        }
        return false;
    }

    @NotNull
    public final HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    @NotNull
    public final byte[] getSignature() {
        return this.signature;
    }

    @NotNull
    public final SignatureAlgorithm getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int hashCode() {
        return (((this.hashAlgorithm.hashCode() * 31) + this.signatureAlgorithm.hashCode()) * 31) + Arrays.hashCode(this.signature);
    }

    @NotNull
    public String toString() {
        return "DigitallySigned(hashAlgorithm=" + this.hashAlgorithm + ", signatureAlgorithm=" + this.signatureAlgorithm + ", signature=" + Arrays.toString(this.signature) + ')';
    }
}
