package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes2.dex */
final class zzad extends zze {
    public static final Parcelable.Creator<zzad> CREATOR = new zzac();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzad(String str, @Nullable Integer num, List list, String str2, String str3, String str4, @Nullable List list2, @Nullable List list3, @Nullable List list4) {
        super(str, num, list, str2, str3, str4, list2, list3, list4);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int intValue;
        parcel.writeString(getPlaceId());
        if (getDistanceMeters() == null) {
            intValue = 1;
        } else {
            parcel.writeInt(0);
            intValue = getDistanceMeters().intValue();
        }
        parcel.writeInt(intValue);
        parcel.writeList(getPlaceTypes());
        parcel.writeString(zza());
        parcel.writeString(zzb());
        parcel.writeString(zzc());
        parcel.writeList(zzd());
        parcel.writeList(zze());
        parcel.writeList(zzf());
    }
}
