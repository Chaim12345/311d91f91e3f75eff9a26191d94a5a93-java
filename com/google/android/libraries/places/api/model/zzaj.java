package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
final class zzaj extends zzj {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzai();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(int i2, int i3) {
        super(i2, i3);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(getHours());
        parcel.writeInt(getMinutes());
    }
}
