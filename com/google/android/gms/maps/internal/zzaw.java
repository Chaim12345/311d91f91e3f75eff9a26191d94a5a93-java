package com.google.android.gms.maps.internal;

import android.os.Parcel;
/* loaded from: classes2.dex */
public abstract class zzaw extends com.google.android.gms.internal.maps.zzb implements zzax {
    public zzaw() {
        super("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            boolean zzb = zzb();
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zzc(parcel2, zzb);
            return true;
        }
        return false;
    }
}
