package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public enum zzfj implements Parcelable {
    FRAGMENT,
    INTENT;
    
    public static final Parcelable.Creator<zzfj> CREATOR = new Parcelable.Creator() { // from class: com.google.android.libraries.places.internal.zzfi
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return zzfj.valueOf(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object[] newArray(int i2) {
            return new zzfj[i2];
        }
    };

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(name());
    }
}
