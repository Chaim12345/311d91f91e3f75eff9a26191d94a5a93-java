package com.psa.mym.mycitroenconnect.model.geo_fence;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceRepeat {
    @NotNull
    private String customTime;
    private boolean isCustom;
    private boolean isSelected;
    @NotNull
    private String repeatType;

    public GeoFenceRepeat(@NotNull String repeatType, boolean z, boolean z2, @NotNull String customTime) {
        Intrinsics.checkNotNullParameter(repeatType, "repeatType");
        Intrinsics.checkNotNullParameter(customTime, "customTime");
        this.repeatType = repeatType;
        this.isSelected = z;
        this.isCustom = z2;
        this.customTime = customTime;
    }

    public /* synthetic */ GeoFenceRepeat(String str, boolean z, boolean z2, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, z2, (i2 & 8) != 0 ? "" : str2);
    }

    public static /* synthetic */ GeoFenceRepeat copy$default(GeoFenceRepeat geoFenceRepeat, String str, boolean z, boolean z2, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = geoFenceRepeat.repeatType;
        }
        if ((i2 & 2) != 0) {
            z = geoFenceRepeat.isSelected;
        }
        if ((i2 & 4) != 0) {
            z2 = geoFenceRepeat.isCustom;
        }
        if ((i2 & 8) != 0) {
            str2 = geoFenceRepeat.customTime;
        }
        return geoFenceRepeat.copy(str, z, z2, str2);
    }

    @NotNull
    public final String component1() {
        return this.repeatType;
    }

    public final boolean component2() {
        return this.isSelected;
    }

    public final boolean component3() {
        return this.isCustom;
    }

    @NotNull
    public final String component4() {
        return this.customTime;
    }

    @NotNull
    public final GeoFenceRepeat copy(@NotNull String repeatType, boolean z, boolean z2, @NotNull String customTime) {
        Intrinsics.checkNotNullParameter(repeatType, "repeatType");
        Intrinsics.checkNotNullParameter(customTime, "customTime");
        return new GeoFenceRepeat(repeatType, z, z2, customTime);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GeoFenceRepeat) {
            GeoFenceRepeat geoFenceRepeat = (GeoFenceRepeat) obj;
            return Intrinsics.areEqual(this.repeatType, geoFenceRepeat.repeatType) && this.isSelected == geoFenceRepeat.isSelected && this.isCustom == geoFenceRepeat.isCustom && Intrinsics.areEqual(this.customTime, geoFenceRepeat.customTime);
        }
        return false;
    }

    @NotNull
    public final String getCustomTime() {
        return this.customTime;
    }

    @NotNull
    public final String getRepeatType() {
        return this.repeatType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.repeatType.hashCode() * 31;
        boolean z = this.isSelected;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        boolean z2 = this.isCustom;
        return ((i3 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.customTime.hashCode();
    }

    public final boolean isCustom() {
        return this.isCustom;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setCustom(boolean z) {
        this.isCustom = z;
    }

    public final void setCustomTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customTime = str;
    }

    public final void setRepeatType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.repeatType = str;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    @NotNull
    public String toString() {
        return "GeoFenceRepeat(repeatType=" + this.repeatType + ", isSelected=" + this.isSelected + ", isCustom=" + this.isCustom + ", customTime=" + this.customTime + ')';
    }
}
