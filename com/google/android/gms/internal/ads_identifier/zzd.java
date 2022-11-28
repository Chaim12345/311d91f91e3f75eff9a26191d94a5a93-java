package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class zzd extends zza implements zzf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    @Override // com.google.android.gms.internal.ads_identifier.zzf
    public final String zzc() {
        Parcel b2 = b(1, a());
        String readString = b2.readString();
        b2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads_identifier.zzf
    public final boolean zzd() {
        Parcel b2 = b(6, a());
        boolean zzb = zzc.zzb(b2);
        b2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads_identifier.zzf
    public final boolean zze(boolean z) {
        Parcel a2 = a();
        zzc.zza(a2, true);
        Parcel b2 = b(2, a2);
        boolean zzb = zzc.zzb(b2);
        b2.recycle();
        return zzb;
    }
}
