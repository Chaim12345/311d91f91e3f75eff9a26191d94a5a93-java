package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
/* loaded from: classes2.dex */
final class zzah extends zzh {
    public static final Parcelable.Creator<zzah> CREATOR = new zzag();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah(ParcelUuid parcelUuid) {
        super(parcelUuid);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(zza(), i2);
    }
}
