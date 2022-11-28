package com.psa.mym.mycitroenconnect.model.geo_fence;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class LocationData implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<LocationData> CREATOR = new Creator();
    @SerializedName("gpsLat")
    @Nullable
    private Double gpsLat;
    @SerializedName("gpsLong")
    @Nullable
    private Double gpsLong;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<LocationData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final LocationData createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LocationData(parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readInt() != 0 ? Double.valueOf(parcel.readDouble()) : null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final LocationData[] newArray(int i2) {
            return new LocationData[i2];
        }
    }

    public LocationData() {
        this(null, null, 3, null);
    }

    public LocationData(@Nullable Double d2, @Nullable Double d3) {
        this.gpsLat = d2;
        this.gpsLong = d3;
    }

    public /* synthetic */ LocationData(Double d2, Double d3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : d2, (i2 & 2) != 0 ? null : d3);
    }

    public static /* synthetic */ LocationData copy$default(LocationData locationData, Double d2, Double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d2 = locationData.gpsLat;
        }
        if ((i2 & 2) != 0) {
            d3 = locationData.gpsLong;
        }
        return locationData.copy(d2, d3);
    }

    @Nullable
    public final Double component1() {
        return this.gpsLat;
    }

    @Nullable
    public final Double component2() {
        return this.gpsLong;
    }

    @NotNull
    public final LocationData copy(@Nullable Double d2, @Nullable Double d3) {
        return new LocationData(d2, d3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocationData) {
            LocationData locationData = (LocationData) obj;
            return Intrinsics.areEqual((Object) this.gpsLat, (Object) locationData.gpsLat) && Intrinsics.areEqual((Object) this.gpsLong, (Object) locationData.gpsLong);
        }
        return false;
    }

    @Nullable
    public final Double getGpsLat() {
        return this.gpsLat;
    }

    @Nullable
    public final Double getGpsLong() {
        return this.gpsLong;
    }

    public int hashCode() {
        Double d2 = this.gpsLat;
        int hashCode = (d2 == null ? 0 : d2.hashCode()) * 31;
        Double d3 = this.gpsLong;
        return hashCode + (d3 != null ? d3.hashCode() : 0);
    }

    public final void setGpsLat(@Nullable Double d2) {
        this.gpsLat = d2;
    }

    public final void setGpsLong(@Nullable Double d2) {
        this.gpsLong = d2;
    }

    @NotNull
    public String toString() {
        return "LocationData(gpsLat=" + this.gpsLat + ", gpsLong=" + this.gpsLong + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Double d2 = this.gpsLat;
        if (d2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeDouble(d2.doubleValue());
        }
        Double d3 = this.gpsLong;
        if (d3 == null) {
            out.writeInt(0);
            return;
        }
        out.writeInt(1);
        out.writeDouble(d3.doubleValue());
    }
}
