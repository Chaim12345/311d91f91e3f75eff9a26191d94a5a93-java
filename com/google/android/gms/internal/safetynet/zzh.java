package com.google.android.gms.internal.safetynet;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes2.dex */
public final class zzh extends zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.safetynet.internal.ISafetyNetService");
    }

    public final void zzc(zzg zzgVar, byte[] bArr, String str) {
        Parcel a2 = a();
        zzc.zzb(a2, zzgVar);
        a2.writeByteArray(bArr);
        a2.writeString(str);
        b(7, a2);
    }

    public final void zzd(zzg zzgVar) {
        Parcel a2 = a();
        zzc.zzb(a2, zzgVar);
        b(4, a2);
    }

    public final void zze(zzg zzgVar) {
        Parcel a2 = a();
        zzc.zzb(a2, zzgVar);
        b(12, a2);
    }

    public final void zzf(zzg zzgVar) {
        Parcel a2 = a();
        zzc.zzb(a2, zzgVar);
        b(14, a2);
    }

    public final void zzg(zzg zzgVar) {
        Parcel a2 = a();
        zzc.zzb(a2, zzgVar);
        b(5, a2);
    }

    public final void zzh(zzg zzgVar, String str, int[] iArr, int i2, String str2) {
        Parcel a2 = a();
        zzc.zzb(a2, zzgVar);
        a2.writeString(str);
        a2.writeIntArray(iArr);
        a2.writeInt(i2);
        a2.writeString(str2);
        b(3, a2);
    }

    public final void zzi() {
        b(13, a());
    }

    public final void zzj(zzg zzgVar, String str) {
        Parcel a2 = a();
        zzc.zzb(a2, zzgVar);
        a2.writeString(str);
        b(6, a2);
    }
}
