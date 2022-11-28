package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzeo extends com.google.android.gms.internal.measurement.zzbm implements zzeq {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final String zzd(zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        Parcel b2 = b(11, a2);
        String readString = b2.readString();
        b2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final List zze(zzq zzqVar, boolean z) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        com.google.android.gms.internal.measurement.zzbo.zzd(a2, z);
        Parcel b2 = b(7, a2);
        ArrayList createTypedArrayList = b2.createTypedArrayList(zzlo.CREATOR);
        b2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final List zzf(String str, String str2, zzq zzqVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        Parcel b2 = b(16, a2);
        ArrayList createTypedArrayList = b2.createTypedArrayList(zzac.CREATOR);
        b2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final List zzg(String str, String str2, String str3) {
        Parcel a2 = a();
        a2.writeString(null);
        a2.writeString(str2);
        a2.writeString(str3);
        Parcel b2 = b(17, a2);
        ArrayList createTypedArrayList = b2.createTypedArrayList(zzac.CREATOR);
        b2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final List zzh(String str, String str2, boolean z, zzq zzqVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        com.google.android.gms.internal.measurement.zzbo.zzd(a2, z);
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        Parcel b2 = b(14, a2);
        ArrayList createTypedArrayList = b2.createTypedArrayList(zzlo.CREATOR);
        b2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final List zzi(String str, String str2, String str3, boolean z) {
        Parcel a2 = a();
        a2.writeString(null);
        a2.writeString(str2);
        a2.writeString(str3);
        com.google.android.gms.internal.measurement.zzbo.zzd(a2, z);
        Parcel b2 = b(15, a2);
        ArrayList createTypedArrayList = b2.createTypedArrayList(zzlo.CREATOR);
        b2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzj(zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(4, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzk(zzaw zzawVar, zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzawVar);
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(1, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzl(zzaw zzawVar, String str, String str2) {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzm(zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(18, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzn(zzac zzacVar, zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzacVar);
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(12, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzo(zzac zzacVar) {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzp(zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(20, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzq(long j2, String str, String str2, String str3) {
        Parcel a2 = a();
        a2.writeLong(j2);
        a2.writeString(str);
        a2.writeString(str2);
        a2.writeString(str3);
        c(10, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzr(Bundle bundle, zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, bundle);
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(19, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzs(zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(6, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zzt(zzlo zzloVar, zzq zzqVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzloVar);
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzqVar);
        c(2, a2);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final byte[] zzu(zzaw zzawVar, String str) {
        Parcel a2 = a();
        com.google.android.gms.internal.measurement.zzbo.zze(a2, zzawVar);
        a2.writeString(str);
        Parcel b2 = b(9, a2);
        byte[] createByteArray = b2.createByteArray();
        b2.recycle();
        return createByteArray;
    }
}
