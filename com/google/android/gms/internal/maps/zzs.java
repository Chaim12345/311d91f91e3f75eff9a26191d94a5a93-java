package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class zzs extends zza implements zzu {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final int zzd() {
        Parcel a2 = a(5, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final String zze() {
        Parcel a2 = a(1, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final String zzf() {
        Parcel a2 = a(2, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final void zzg() {
        c(3, b());
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final boolean zzh(zzu zzuVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzuVar);
        Parcel a2 = a(4, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }
}
