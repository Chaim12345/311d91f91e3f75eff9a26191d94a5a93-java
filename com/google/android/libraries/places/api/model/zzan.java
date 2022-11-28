package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
final class zzan extends zzn {
    public static final Parcelable.Creator<zzan> CREATOR = new zzam();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzan(@Nullable TimeOfWeek timeOfWeek, @Nullable TimeOfWeek timeOfWeek2) {
        super(timeOfWeek, timeOfWeek2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(getOpen(), i2);
        parcel.writeParcelable(getClose(), i2);
    }
}
