package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BarcodeParcelCreator")
/* loaded from: classes2.dex */
public final class zzon extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzon> CREATOR = new zzoo();
    @SafeParcelable.Field(getter = "getFormat", id = 1)
    private final int zza;
    @Nullable
    @SafeParcelable.Field(getter = "getDisplayValue", id = 2)
    private final String zzb;
    @Nullable
    @SafeParcelable.Field(getter = "getRawValue", id = 3)
    private final String zzc;
    @Nullable
    @SafeParcelable.Field(getter = "getRawBytes", id = 4)
    private final byte[] zzd;
    @Nullable
    @SafeParcelable.Field(getter = "getCornerPoints", id = 5)
    private final Point[] zze;
    @SafeParcelable.Field(getter = "getValueType", id = 6)
    private final int zzf;
    @Nullable
    @SafeParcelable.Field(getter = "getEmailParcel", id = 7)
    private final zzog zzg;
    @Nullable
    @SafeParcelable.Field(getter = "getPhoneParcel", id = 8)
    private final zzoj zzh;
    @Nullable
    @SafeParcelable.Field(getter = "getSmsParcel", id = 9)
    private final zzok zzi;
    @Nullable
    @SafeParcelable.Field(getter = "getWiFiParcel", id = 10)
    private final zzom zzj;
    @Nullable
    @SafeParcelable.Field(getter = "getUrlBookmarkParcel", id = 11)
    private final zzol zzk;
    @Nullable
    @SafeParcelable.Field(getter = "getGeoPointParcel", id = 12)
    private final zzoh zzl;
    @Nullable
    @SafeParcelable.Field(getter = "getCalendarEventParcel", id = 13)
    private final zzod zzm;
    @Nullable
    @SafeParcelable.Field(getter = "getContactInfoParcel", id = 14)
    private final zzoe zzn;
    @Nullable
    @SafeParcelable.Field(getter = "getDriverLicenseParcel", id = 15)
    private final zzof zzo;

    @SafeParcelable.Constructor
    public zzon(@SafeParcelable.Param(id = 1) int i2, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) byte[] bArr, @Nullable @SafeParcelable.Param(id = 5) Point[] pointArr, @SafeParcelable.Param(id = 6) int i3, @Nullable @SafeParcelable.Param(id = 7) zzog zzogVar, @Nullable @SafeParcelable.Param(id = 8) zzoj zzojVar, @Nullable @SafeParcelable.Param(id = 9) zzok zzokVar, @Nullable @SafeParcelable.Param(id = 10) zzom zzomVar, @Nullable @SafeParcelable.Param(id = 11) zzol zzolVar, @Nullable @SafeParcelable.Param(id = 12) zzoh zzohVar, @Nullable @SafeParcelable.Param(id = 13) zzod zzodVar, @Nullable @SafeParcelable.Param(id = 14) zzoe zzoeVar, @Nullable @SafeParcelable.Param(id = 15) zzof zzofVar) {
        this.zza = i2;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bArr;
        this.zze = pointArr;
        this.zzf = i3;
        this.zzg = zzogVar;
        this.zzh = zzojVar;
        this.zzi = zzokVar;
        this.zzj = zzomVar;
        this.zzk = zzolVar;
        this.zzl = zzohVar;
        this.zzm = zzodVar;
        this.zzn = zzoeVar;
        this.zzo = zzofVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i2, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzh, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzi, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzj, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzm, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzn, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzo, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzf;
    }

    @Nullable
    public final zzod zzc() {
        return this.zzm;
    }

    @Nullable
    public final zzoe zzd() {
        return this.zzn;
    }

    @Nullable
    public final zzof zze() {
        return this.zzo;
    }

    @Nullable
    public final zzog zzf() {
        return this.zzg;
    }

    @Nullable
    public final zzoh zzg() {
        return this.zzl;
    }

    @Nullable
    public final zzoj zzh() {
        return this.zzh;
    }

    @Nullable
    public final zzok zzi() {
        return this.zzi;
    }

    @Nullable
    public final zzol zzj() {
        return this.zzk;
    }

    @Nullable
    public final zzom zzk() {
        return this.zzj;
    }

    @Nullable
    public final String zzl() {
        return this.zzb;
    }

    @Nullable
    public final String zzm() {
        return this.zzc;
    }

    @Nullable
    public final byte[] zzn() {
        return this.zzd;
    }

    @Nullable
    public final Point[] zzo() {
        return this.zze;
    }
}
