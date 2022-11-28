package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzat extends zzs {
    public static final Parcelable.Creator<zzat> CREATOR = new zzas();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzat(Place place, double d2) {
        super(place, d2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(getPlace(), i2);
        parcel.writeDouble(getLikelihood());
    }
}
