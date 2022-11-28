package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ContactInfoCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzu();
    @SafeParcelable.Field(id = 2)
    public zzl zza;
    @SafeParcelable.Field(id = 3)
    public String zzb;
    @SafeParcelable.Field(id = 4)
    public String zzc;
    @SafeParcelable.Field(id = 5)
    public zzm[] zzd;
    @SafeParcelable.Field(id = 6)
    public zzj[] zze;
    @SafeParcelable.Field(id = 7)
    public String[] zzf;
    @SafeParcelable.Field(id = 8)
    public zze[] zzg;

    public zzh() {
    }

    @SafeParcelable.Constructor
    public zzh(@SafeParcelable.Param(id = 2) zzl zzlVar, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) zzm[] zzmVarArr, @SafeParcelable.Param(id = 6) zzj[] zzjVarArr, @SafeParcelable.Param(id = 7) String[] strArr, @SafeParcelable.Param(id = 8) zze[] zzeVarArr) {
        this.zza = zzlVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzmVarArr;
        this.zze = zzjVarArr;
        this.zzf = strArr;
        this.zzg = zzeVarArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i2, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzd, i2, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zze, i2, false);
        SafeParcelWriter.writeStringArray(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzg, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
