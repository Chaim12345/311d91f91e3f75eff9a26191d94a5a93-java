package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
/* loaded from: classes2.dex */
final class zzfe extends zzfc {
    public static final Parcelable.Creator<zzfe> CREATOR = new zzfd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfe(AutocompleteActivityMode autocompleteActivityMode, zzhs zzhsVar, zzfj zzfjVar, @Nullable String str, @Nullable String str2, @Nullable LocationBias locationBias, @Nullable LocationRestriction locationRestriction, zzhs zzhsVar2, @Nullable TypeFilter typeFilter, int i2, int i3) {
        super(autocompleteActivityMode, zzhsVar, zzfjVar, str, str2, locationBias, locationRestriction, zzhsVar2, typeFilter, i2, i3);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(zzh(), i2);
        parcel.writeList(zzj());
        parcel.writeParcelable(zzf(), i2);
        if (zzl() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zzl());
        }
        if (zzk() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zzk());
        }
        parcel.writeParcelable(zzc(), i2);
        parcel.writeParcelable(zzd(), i2);
        parcel.writeList(zzi());
        parcel.writeParcelable(zze(), i2);
        parcel.writeInt(zza());
        parcel.writeInt(zzb());
    }
}
