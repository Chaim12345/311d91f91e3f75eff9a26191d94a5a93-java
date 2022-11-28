package com.psa.mym.mycitroenconnect.api.body;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class UpdateTripNameBody {
    @SerializedName("tripId")
    @NotNull
    private String tripId;
    @SerializedName("tripName")
    @NotNull
    private String tripName;

    public UpdateTripNameBody() {
        this(null, null, 3, null);
    }

    public UpdateTripNameBody(@NotNull String tripId, @NotNull String tripName) {
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        this.tripId = tripId;
        this.tripName = tripName;
    }

    public /* synthetic */ UpdateTripNameBody(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2);
    }

    public static /* synthetic */ UpdateTripNameBody copy$default(UpdateTripNameBody updateTripNameBody, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = updateTripNameBody.tripId;
        }
        if ((i2 & 2) != 0) {
            str2 = updateTripNameBody.tripName;
        }
        return updateTripNameBody.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.tripId;
    }

    @NotNull
    public final String component2() {
        return this.tripName;
    }

    @NotNull
    public final UpdateTripNameBody copy(@NotNull String tripId, @NotNull String tripName) {
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        return new UpdateTripNameBody(tripId, tripName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UpdateTripNameBody) {
            UpdateTripNameBody updateTripNameBody = (UpdateTripNameBody) obj;
            return Intrinsics.areEqual(this.tripId, updateTripNameBody.tripId) && Intrinsics.areEqual(this.tripName, updateTripNameBody.tripName);
        }
        return false;
    }

    @NotNull
    public final String getTripId() {
        return this.tripId;
    }

    @NotNull
    public final String getTripName() {
        return this.tripName;
    }

    public int hashCode() {
        return (this.tripId.hashCode() * 31) + this.tripName.hashCode();
    }

    public final void setTripId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripId = str;
    }

    public final void setTripName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripName = str;
    }

    @NotNull
    public String toString() {
        return "UpdateTripNameBody(tripId=" + this.tripId + ", tripName=" + this.tripName + ')';
    }
}
