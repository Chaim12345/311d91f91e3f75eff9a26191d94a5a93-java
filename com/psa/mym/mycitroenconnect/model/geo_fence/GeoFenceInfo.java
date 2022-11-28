package com.psa.mym.mycitroenconnect.model.geo_fence;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceInfo {
    @NotNull
    private String detail;
    private int fenceImage;
    private boolean isSelected;
    @NotNull
    private String title;

    public GeoFenceInfo(@NotNull String title, int i2, @NotNull String detail, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(detail, "detail");
        this.title = title;
        this.fenceImage = i2;
        this.detail = detail;
        this.isSelected = z;
    }

    public /* synthetic */ GeoFenceInfo(String str, int i2, String str2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i2, str2, (i3 & 8) != 0 ? false : z);
    }

    public static /* synthetic */ GeoFenceInfo copy$default(GeoFenceInfo geoFenceInfo, String str, int i2, String str2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = geoFenceInfo.title;
        }
        if ((i3 & 2) != 0) {
            i2 = geoFenceInfo.fenceImage;
        }
        if ((i3 & 4) != 0) {
            str2 = geoFenceInfo.detail;
        }
        if ((i3 & 8) != 0) {
            z = geoFenceInfo.isSelected;
        }
        return geoFenceInfo.copy(str, i2, str2, z);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    public final int component2() {
        return this.fenceImage;
    }

    @NotNull
    public final String component3() {
        return this.detail;
    }

    public final boolean component4() {
        return this.isSelected;
    }

    @NotNull
    public final GeoFenceInfo copy(@NotNull String title, int i2, @NotNull String detail, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(detail, "detail");
        return new GeoFenceInfo(title, i2, detail, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GeoFenceInfo) {
            GeoFenceInfo geoFenceInfo = (GeoFenceInfo) obj;
            return Intrinsics.areEqual(this.title, geoFenceInfo.title) && this.fenceImage == geoFenceInfo.fenceImage && Intrinsics.areEqual(this.detail, geoFenceInfo.detail) && this.isSelected == geoFenceInfo.isSelected;
        }
        return false;
    }

    @NotNull
    public final String getDetail() {
        return this.detail;
    }

    public final int getFenceImage() {
        return this.fenceImage;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.title.hashCode() * 31) + Integer.hashCode(this.fenceImage)) * 31) + this.detail.hashCode()) * 31;
        boolean z = this.isSelected;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode + i2;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setDetail(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.detail = str;
    }

    public final void setFenceImage(int i2) {
        this.fenceImage = i2;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    @NotNull
    public String toString() {
        return "GeoFenceInfo(title=" + this.title + ", fenceImage=" + this.fenceImage + ", detail=" + this.detail + ", isSelected=" + this.isSelected + ')';
    }
}
