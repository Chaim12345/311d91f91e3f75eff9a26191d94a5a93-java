package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "CalendarEventParcelCreator")
/* loaded from: classes2.dex */
public final class zzod extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzod> CREATOR = new zzos();
    @Nullable
    @SafeParcelable.Field(getter = "getSummary", id = 1)
    private final String zza;
    @Nullable
    @SafeParcelable.Field(getter = "getDescription", id = 2)
    private final String zzb;
    @Nullable
    @SafeParcelable.Field(getter = "getLocation", id = 3)
    private final String zzc;
    @Nullable
    @SafeParcelable.Field(getter = "getOrganizer", id = 4)
    private final String zzd;
    @Nullable
    @SafeParcelable.Field(getter = "getStatus", id = 5)
    private final String zze;
    @Nullable
    @SafeParcelable.Field(getter = "getStart", id = 6)
    private final zzoc zzf;
    @Nullable
    @SafeParcelable.Field(getter = "getEnd", id = 7)
    private final zzoc zzg;

    @SafeParcelable.Constructor
    public zzod(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) String str3, @Nullable @SafeParcelable.Param(id = 4) String str4, @Nullable @SafeParcelable.Param(id = 5) String str5, @Nullable @SafeParcelable.Param(id = 6) zzoc zzocVar, @Nullable @SafeParcelable.Param(id = 7) zzoc zzocVar2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = zzocVar;
        this.zzg = zzocVar2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzoc zza() {
        return this.zzg;
    }

    @Nullable
    public final zzoc zzb() {
        return this.zzf;
    }

    @Nullable
    public final String zzc() {
        return this.zzb;
    }

    @Nullable
    public final String zzd() {
        return this.zzc;
    }

    @Nullable
    public final String zze() {
        return this.zzd;
    }

    @Nullable
    public final String zzf() {
        return this.zze;
    }

    @Nullable
    public final String zzg() {
        return this.zza;
    }
}
