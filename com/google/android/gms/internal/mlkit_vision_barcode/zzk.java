package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "GeoPointCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR = new zzx();
    @SafeParcelable.Field(id = 2)
    public double zza;
    @SafeParcelable.Field(id = 3)
    public double zzb;

    public zzk() {
    }

    @SafeParcelable.Constructor
    public zzk(@SafeParcelable.Param(id = 2) double d2, @SafeParcelable.Param(id = 3) double d3) {
        this.zza = d2;
        this.zzb = d3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 2, this.zza);
        SafeParcelWriter.writeDouble(parcel, 3, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
