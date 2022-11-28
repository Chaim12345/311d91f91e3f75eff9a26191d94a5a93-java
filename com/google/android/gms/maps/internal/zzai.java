package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes2.dex */
public final class zzai extends com.google.android.gms.internal.maps.zza implements zzaj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
    }

    @Override // com.google.android.gms.maps.internal.zzaj
    public final void zzd(Location location) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, location);
        c(2, b2);
    }
}
