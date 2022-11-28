package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class zzp extends zza implements zzr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzr
    public final int zzd() {
        Parcel a2 = a(1, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzr
    public final int zze() {
        Parcel a2 = a(2, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzr
    public final int zzf() {
        Parcel a2 = a(6, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzr
    public final List<IBinder> zzg() {
        Parcel a2 = a(3, b());
        ArrayList<IBinder> createBinderArrayList = a2.createBinderArrayList();
        a2.recycle();
        return createBinderArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzr
    public final boolean zzh(zzr zzrVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzrVar);
        Parcel a2 = a(5, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzr
    public final boolean zzi() {
        Parcel a2 = a(4, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }
}
