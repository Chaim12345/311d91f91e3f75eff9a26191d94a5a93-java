package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class IdleTripsData {
    @NotNull
    private final String idlingLoss;
    @NotNull
    private final String tripDateTime;
    @NotNull
    private final String tripId;

    public IdleTripsData(@NotNull String tripDateTime, @NotNull String tripId, @NotNull String idlingLoss) {
        Intrinsics.checkNotNullParameter(tripDateTime, "tripDateTime");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(idlingLoss, "idlingLoss");
        this.tripDateTime = tripDateTime;
        this.tripId = tripId;
        this.idlingLoss = idlingLoss;
    }

    public static /* synthetic */ IdleTripsData copy$default(IdleTripsData idleTripsData, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = idleTripsData.tripDateTime;
        }
        if ((i2 & 2) != 0) {
            str2 = idleTripsData.tripId;
        }
        if ((i2 & 4) != 0) {
            str3 = idleTripsData.idlingLoss;
        }
        return idleTripsData.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.tripDateTime;
    }

    @NotNull
    public final String component2() {
        return this.tripId;
    }

    @NotNull
    public final String component3() {
        return this.idlingLoss;
    }

    @NotNull
    public final IdleTripsData copy(@NotNull String tripDateTime, @NotNull String tripId, @NotNull String idlingLoss) {
        Intrinsics.checkNotNullParameter(tripDateTime, "tripDateTime");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(idlingLoss, "idlingLoss");
        return new IdleTripsData(tripDateTime, tripId, idlingLoss);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IdleTripsData) {
            IdleTripsData idleTripsData = (IdleTripsData) obj;
            return Intrinsics.areEqual(this.tripDateTime, idleTripsData.tripDateTime) && Intrinsics.areEqual(this.tripId, idleTripsData.tripId) && Intrinsics.areEqual(this.idlingLoss, idleTripsData.idlingLoss);
        }
        return false;
    }

    @NotNull
    public final String getIdlingLoss() {
        return this.idlingLoss;
    }

    @NotNull
    public final String getTripDateTime() {
        return this.tripDateTime;
    }

    @NotNull
    public final String getTripId() {
        return this.tripId;
    }

    public int hashCode() {
        return (((this.tripDateTime.hashCode() * 31) + this.tripId.hashCode()) * 31) + this.idlingLoss.hashCode();
    }

    @NotNull
    public String toString() {
        return "IdleTripsData(tripDateTime=" + this.tripDateTime + ", tripId=" + this.tripId + ", idlingLoss=" + this.idlingLoss + ')';
    }
}
