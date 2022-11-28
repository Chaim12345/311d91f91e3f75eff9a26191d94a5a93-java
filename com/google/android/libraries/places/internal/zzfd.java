package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
/* loaded from: classes2.dex */
final class zzfd implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzfe((AutocompleteActivityMode) parcel.readParcelable(zzfl.class.getClassLoader()), zzhs.zzk(parcel.readArrayList(Place.Field.class.getClassLoader())), (zzfj) parcel.readParcelable(zzfl.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, (LocationBias) parcel.readParcelable(zzfl.class.getClassLoader()), (LocationRestriction) parcel.readParcelable(zzfl.class.getClassLoader()), zzhs.zzk(parcel.readArrayList(String.class.getClassLoader())), (TypeFilter) parcel.readParcelable(zzfl.class.getClassLoader()), parcel.readInt(), parcel.readInt());
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzfe[i2];
    }
}
