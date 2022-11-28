package com.psa.mym.mycitroenconnect.model.geo_fence;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceType {
    private int fenceId;
    private int fenceImage;
    private double fenceRadius;
    @NotNull
    private String fenceTransitionType;
    @NotNull
    private String fenceType;
    private boolean isSelected;

    public GeoFenceType(int i2, @NotNull String fenceType, int i3, boolean z, @NotNull String fenceTransitionType, double d2) {
        Intrinsics.checkNotNullParameter(fenceType, "fenceType");
        Intrinsics.checkNotNullParameter(fenceTransitionType, "fenceTransitionType");
        this.fenceId = i2;
        this.fenceType = fenceType;
        this.fenceImage = i3;
        this.isSelected = z;
        this.fenceTransitionType = fenceTransitionType;
        this.fenceRadius = d2;
    }

    public /* synthetic */ GeoFenceType(int i2, String str, int i3, boolean z, String str2, double d2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, str, i3, (i4 & 8) != 0 ? false : z, (i4 & 16) != 0 ? "" : str2, (i4 & 32) != 0 ? 0.0d : d2);
    }

    public static /* synthetic */ GeoFenceType copy$default(GeoFenceType geoFenceType, int i2, String str, int i3, boolean z, String str2, double d2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = geoFenceType.fenceId;
        }
        if ((i4 & 2) != 0) {
            str = geoFenceType.fenceType;
        }
        String str3 = str;
        if ((i4 & 4) != 0) {
            i3 = geoFenceType.fenceImage;
        }
        int i5 = i3;
        if ((i4 & 8) != 0) {
            z = geoFenceType.isSelected;
        }
        boolean z2 = z;
        if ((i4 & 16) != 0) {
            str2 = geoFenceType.fenceTransitionType;
        }
        String str4 = str2;
        if ((i4 & 32) != 0) {
            d2 = geoFenceType.fenceRadius;
        }
        return geoFenceType.copy(i2, str3, i5, z2, str4, d2);
    }

    public final int component1() {
        return this.fenceId;
    }

    @NotNull
    public final String component2() {
        return this.fenceType;
    }

    public final int component3() {
        return this.fenceImage;
    }

    public final boolean component4() {
        return this.isSelected;
    }

    @NotNull
    public final String component5() {
        return this.fenceTransitionType;
    }

    public final double component6() {
        return this.fenceRadius;
    }

    @NotNull
    public final GeoFenceType copy(int i2, @NotNull String fenceType, int i3, boolean z, @NotNull String fenceTransitionType, double d2) {
        Intrinsics.checkNotNullParameter(fenceType, "fenceType");
        Intrinsics.checkNotNullParameter(fenceTransitionType, "fenceTransitionType");
        return new GeoFenceType(i2, fenceType, i3, z, fenceTransitionType, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GeoFenceType) {
            GeoFenceType geoFenceType = (GeoFenceType) obj;
            return this.fenceId == geoFenceType.fenceId && Intrinsics.areEqual(this.fenceType, geoFenceType.fenceType) && this.fenceImage == geoFenceType.fenceImage && this.isSelected == geoFenceType.isSelected && Intrinsics.areEqual(this.fenceTransitionType, geoFenceType.fenceTransitionType) && Intrinsics.areEqual((Object) Double.valueOf(this.fenceRadius), (Object) Double.valueOf(geoFenceType.fenceRadius));
        }
        return false;
    }

    public final int getFenceId() {
        return this.fenceId;
    }

    public final int getFenceImage() {
        return this.fenceImage;
    }

    public final double getFenceRadius() {
        return this.fenceRadius;
    }

    @NotNull
    public final String getFenceTransitionType() {
        return this.fenceTransitionType;
    }

    @NotNull
    public final String getFenceType() {
        return this.fenceType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.fenceId) * 31) + this.fenceType.hashCode()) * 31) + Integer.hashCode(this.fenceImage)) * 31;
        boolean z = this.isSelected;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return ((((hashCode + i2) * 31) + this.fenceTransitionType.hashCode()) * 31) + Double.hashCode(this.fenceRadius);
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setFenceId(int i2) {
        this.fenceId = i2;
    }

    public final void setFenceImage(int i2) {
        this.fenceImage = i2;
    }

    public final void setFenceRadius(double d2) {
        this.fenceRadius = d2;
    }

    public final void setFenceTransitionType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fenceTransitionType = str;
    }

    public final void setFenceType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fenceType = str;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    @NotNull
    public String toString() {
        return "GeoFenceType(fenceId=" + this.fenceId + ", fenceType=" + this.fenceType + ", fenceImage=" + this.fenceImage + ", isSelected=" + this.isSelected + ", fenceTransitionType=" + this.fenceTransitionType + ", fenceRadius=" + this.fenceRadius + ')';
    }
}
