package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes2.dex */
final class zzal extends zzl {
    public static final Parcelable.Creator<zzal> CREATOR = new zzak();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(List list, List list2) {
        super(list, list2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(getPeriods());
        parcel.writeList(getWeekdayText());
    }
}
