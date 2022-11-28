package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes.dex */
public final class zzad extends com.google.android.gms.internal.common.zza implements zzaf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzad(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.zzaf
    public final com.google.android.gms.common.zzq zze(com.google.android.gms.common.zzn zznVar) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zzc(d2, zznVar);
        Parcel a2 = a(6, d2);
        com.google.android.gms.common.zzq zzqVar = (com.google.android.gms.common.zzq) com.google.android.gms.internal.common.zzc.zza(a2, com.google.android.gms.common.zzq.CREATOR);
        a2.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.common.internal.zzaf
    public final boolean zzf(com.google.android.gms.common.zzs zzsVar, IObjectWrapper iObjectWrapper) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zzc(d2, zzsVar);
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        Parcel a2 = a(5, d2);
        boolean zzf = com.google.android.gms.internal.common.zzc.zzf(a2);
        a2.recycle();
        return zzf;
    }

    @Override // com.google.android.gms.common.internal.zzaf
    public final boolean zzg() {
        Parcel a2 = a(7, d());
        boolean zzf = com.google.android.gms.internal.common.zzc.zzf(a2);
        a2.recycle();
        return zzf;
    }
}
