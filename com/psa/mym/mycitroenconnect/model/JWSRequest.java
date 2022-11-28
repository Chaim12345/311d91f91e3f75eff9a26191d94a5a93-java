package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class JWSRequest {
    @NotNull
    private final String signedAttestation;

    public JWSRequest(@NotNull String signedAttestation) {
        Intrinsics.checkNotNullParameter(signedAttestation, "signedAttestation");
        this.signedAttestation = signedAttestation;
    }

    public static /* synthetic */ JWSRequest copy$default(JWSRequest jWSRequest, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = jWSRequest.signedAttestation;
        }
        return jWSRequest.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.signedAttestation;
    }

    @NotNull
    public final JWSRequest copy(@NotNull String signedAttestation) {
        Intrinsics.checkNotNullParameter(signedAttestation, "signedAttestation");
        return new JWSRequest(signedAttestation);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof JWSRequest) && Intrinsics.areEqual(this.signedAttestation, ((JWSRequest) obj).signedAttestation);
    }

    @NotNull
    public final String getSignedAttestation() {
        return this.signedAttestation;
    }

    public int hashCode() {
        return this.signedAttestation.hashCode();
    }

    @NotNull
    public String toString() {
        return "JWSRequest(signedAttestation=" + this.signedAttestation + ')';
    }
}
