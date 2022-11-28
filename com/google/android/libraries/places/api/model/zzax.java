package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes2.dex */
final class zzax extends zzw {
    public static final Parcelable.Creator<zzax> CREATOR = new zzaw();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(LatLng latLng, LatLng latLng2) {
        super(latLng, latLng2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(getSouthwest(), i2);
        parcel.writeParcelable(getNortheast(), i2);
    }
}
