package com.psa.mym.mycitroenconnect.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class JwsResponse {
    private boolean isValidSignature;

    public JwsResponse(boolean z) {
        this.isValidSignature = z;
    }

    public static /* synthetic */ JwsResponse copy$default(JwsResponse jwsResponse, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = jwsResponse.isValidSignature;
        }
        return jwsResponse.copy(z);
    }

    public final boolean component1() {
        return this.isValidSignature;
    }

    @NotNull
    public final JwsResponse copy(boolean z) {
        return new JwsResponse(z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof JwsResponse) && this.isValidSignature == ((JwsResponse) obj).isValidSignature;
    }

    public int hashCode() {
        boolean z = this.isValidSignature;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public final boolean isValidSignature() {
        return this.isValidSignature;
    }

    public final void setValidSignature(boolean z) {
        this.isValidSignature = z;
    }

    @NotNull
    public String toString() {
        return "JwsResponse(isValidSignature=" + this.isValidSignature + ')';
    }
}
