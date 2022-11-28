package com.psa.mym.mycitroenconnect.api.body.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SharedVehicleNew {
    @SerializedName("vinNum")
    @NotNull
    private String vinNum;

    public SharedVehicleNew() {
        this(null, 1, null);
    }

    public SharedVehicleNew(@NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        this.vinNum = vinNum;
    }

    public /* synthetic */ SharedVehicleNew(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str);
    }

    public static /* synthetic */ SharedVehicleNew copy$default(SharedVehicleNew sharedVehicleNew, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sharedVehicleNew.vinNum;
        }
        return sharedVehicleNew.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.vinNum;
    }

    @NotNull
    public final SharedVehicleNew copy(@NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        return new SharedVehicleNew(vinNum);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SharedVehicleNew) && Intrinsics.areEqual(this.vinNum, ((SharedVehicleNew) obj).vinNum);
    }

    @NotNull
    public final String getVinNum() {
        return this.vinNum;
    }

    public int hashCode() {
        return this.vinNum.hashCode();
    }

    public final void setVinNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vinNum = str;
    }

    @NotNull
    public String toString() {
        return "SharedVehicleNew(vinNum=" + this.vinNum + ')';
    }
}
