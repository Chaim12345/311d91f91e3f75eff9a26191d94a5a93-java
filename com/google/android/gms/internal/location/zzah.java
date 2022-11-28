package com.google.android.gms.internal.location;

import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class zzah extends zzb implements zzai {
    public zzah() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            zzb((zzaa) zzc.zza(parcel, zzaa.CREATOR));
        } else if (i2 != 2) {
            return false;
        } else {
            zzc();
        }
        return true;
    }
}
