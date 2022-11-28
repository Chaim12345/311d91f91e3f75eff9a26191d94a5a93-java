package com.appmattus.certificatetransparency.internal.verifier.model;

import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SignedCertificateTimestamp {
    @NotNull
    private final byte[] extensions;
    @NotNull
    private final LogId id;
    @NotNull
    private final Version sctVersion;
    @NotNull
    private final DigitallySigned signature;
    private final long timestamp;

    public SignedCertificateTimestamp(@NotNull Version sctVersion, @NotNull LogId id, long j2, @NotNull DigitallySigned signature, @NotNull byte[] extensions) {
        Intrinsics.checkNotNullParameter(sctVersion, "sctVersion");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        this.sctVersion = sctVersion;
        this.id = id;
        this.timestamp = j2;
        this.signature = signature;
        this.extensions = extensions;
    }

    public /* synthetic */ SignedCertificateTimestamp(Version version, LogId logId, long j2, DigitallySigned digitallySigned, byte[] bArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? Version.UNKNOWN_VERSION : version, logId, j2, digitallySigned, bArr);
    }

    public static /* synthetic */ SignedCertificateTimestamp copy$default(SignedCertificateTimestamp signedCertificateTimestamp, Version version, LogId logId, long j2, DigitallySigned digitallySigned, byte[] bArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            version = signedCertificateTimestamp.sctVersion;
        }
        if ((i2 & 2) != 0) {
            logId = signedCertificateTimestamp.id;
        }
        LogId logId2 = logId;
        if ((i2 & 4) != 0) {
            j2 = signedCertificateTimestamp.timestamp;
        }
        long j3 = j2;
        if ((i2 & 8) != 0) {
            digitallySigned = signedCertificateTimestamp.signature;
        }
        DigitallySigned digitallySigned2 = digitallySigned;
        if ((i2 & 16) != 0) {
            bArr = signedCertificateTimestamp.extensions;
        }
        return signedCertificateTimestamp.copy(version, logId2, j3, digitallySigned2, bArr);
    }

    @NotNull
    public final Version component1() {
        return this.sctVersion;
    }

    @NotNull
    public final LogId component2() {
        return this.id;
    }

    public final long component3() {
        return this.timestamp;
    }

    @NotNull
    public final DigitallySigned component4() {
        return this.signature;
    }

    @NotNull
    public final byte[] component5() {
        return this.extensions;
    }

    @NotNull
    public final SignedCertificateTimestamp copy(@NotNull Version sctVersion, @NotNull LogId id, long j2, @NotNull DigitallySigned signature, @NotNull byte[] extensions) {
        Intrinsics.checkNotNullParameter(sctVersion, "sctVersion");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        return new SignedCertificateTimestamp(sctVersion, id, j2, signature, extensions);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(SignedCertificateTimestamp.class, obj != null ? obj.getClass() : null)) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.verifier.model.SignedCertificateTimestamp");
            SignedCertificateTimestamp signedCertificateTimestamp = (SignedCertificateTimestamp) obj;
            return this.sctVersion == signedCertificateTimestamp.sctVersion && Intrinsics.areEqual(this.id, signedCertificateTimestamp.id) && this.timestamp == signedCertificateTimestamp.timestamp && Intrinsics.areEqual(this.signature, signedCertificateTimestamp.signature) && Arrays.equals(this.extensions, signedCertificateTimestamp.extensions);
        }
        return false;
    }

    @NotNull
    public final byte[] getExtensions() {
        return this.extensions;
    }

    @NotNull
    public final LogId getId() {
        return this.id;
    }

    @NotNull
    public final Version getSctVersion() {
        return this.sctVersion;
    }

    @NotNull
    public final DigitallySigned getSignature() {
        return this.signature;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return (((((((this.sctVersion.hashCode() * 31) + this.id.hashCode()) * 31) + Long.hashCode(this.timestamp)) * 31) + this.signature.hashCode()) * 31) + Arrays.hashCode(this.extensions);
    }

    @NotNull
    public String toString() {
        return "SignedCertificateTimestamp(sctVersion=" + this.sctVersion + ", id=" + this.id + ", timestamp=" + this.timestamp + ", signature=" + this.signature + ", extensions=" + Arrays.toString(this.extensions) + ')';
    }
}
