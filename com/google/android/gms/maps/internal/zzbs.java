package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;
/* loaded from: classes2.dex */
public final class zzbs extends com.google.android.gms.internal.maps.zza implements IProjectionDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final LatLng fromScreenLocation(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, iObjectWrapper);
        Parcel a2 = a(1, b2);
        LatLng latLng = (LatLng) com.google.android.gms.internal.maps.zzc.zza(a2, LatLng.CREATOR);
        a2.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final VisibleRegion getVisibleRegion() {
        Parcel a2 = a(3, b());
        VisibleRegion visibleRegion = (VisibleRegion) com.google.android.gms.internal.maps.zzc.zza(a2, VisibleRegion.CREATOR);
        a2.recycle();
        return visibleRegion;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final IObjectWrapper toScreenLocation(LatLng latLng) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, latLng);
        Parcel a2 = a(2, b2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }
}
