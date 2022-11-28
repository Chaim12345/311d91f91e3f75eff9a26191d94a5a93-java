package com.psa.mym.mycitroenconnect.model.secondary_user;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SecondaryUserVerify {
    @SerializedName("fullHash")
    @NotNull
    private final String fullHash;
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private final String message;
    @SerializedName("statusCode")
    private final int statusCode;
    @SerializedName(FirebaseAnalytics.Param.SUCCESS)
    @NotNull
    private final String success;

    public SecondaryUserVerify(@NotNull String message, int i2, @NotNull String success, @NotNull String fullHash) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fullHash, "fullHash");
        this.message = message;
        this.statusCode = i2;
        this.success = success;
        this.fullHash = fullHash;
    }

    public static /* synthetic */ SecondaryUserVerify copy$default(SecondaryUserVerify secondaryUserVerify, String str, int i2, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = secondaryUserVerify.message;
        }
        if ((i3 & 2) != 0) {
            i2 = secondaryUserVerify.statusCode;
        }
        if ((i3 & 4) != 0) {
            str2 = secondaryUserVerify.success;
        }
        if ((i3 & 8) != 0) {
            str3 = secondaryUserVerify.fullHash;
        }
        return secondaryUserVerify.copy(str, i2, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    public final int component2() {
        return this.statusCode;
    }

    @NotNull
    public final String component3() {
        return this.success;
    }

    @NotNull
    public final String component4() {
        return this.fullHash;
    }

    @NotNull
    public final SecondaryUserVerify copy(@NotNull String message, int i2, @NotNull String success, @NotNull String fullHash) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fullHash, "fullHash");
        return new SecondaryUserVerify(message, i2, success, fullHash);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SecondaryUserVerify) {
            SecondaryUserVerify secondaryUserVerify = (SecondaryUserVerify) obj;
            return Intrinsics.areEqual(this.message, secondaryUserVerify.message) && this.statusCode == secondaryUserVerify.statusCode && Intrinsics.areEqual(this.success, secondaryUserVerify.success) && Intrinsics.areEqual(this.fullHash, secondaryUserVerify.fullHash);
        }
        return false;
    }

    @NotNull
    public final String getFullHash() {
        return this.fullHash;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final String getSuccess() {
        return this.success;
    }

    public int hashCode() {
        return (((((this.message.hashCode() * 31) + Integer.hashCode(this.statusCode)) * 31) + this.success.hashCode()) * 31) + this.fullHash.hashCode();
    }

    @NotNull
    public String toString() {
        return "SecondaryUserVerify(message=" + this.message + ", statusCode=" + this.statusCode + ", success=" + this.success + ", fullHash=" + this.fullHash + ')';
    }
}
