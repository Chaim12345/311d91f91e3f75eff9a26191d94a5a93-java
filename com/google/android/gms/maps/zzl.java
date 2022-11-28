package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.RuntimeRemoteException;
/* loaded from: classes2.dex */
final class zzl implements LocationSource.OnLocationChangedListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.maps.internal.zzaj f6662a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(zzs zzsVar, com.google.android.gms.maps.internal.zzaj zzajVar) {
        this.f6662a = zzajVar;
    }

    @Override // com.google.android.gms.maps.LocationSource.OnLocationChangedListener
    public final void onLocationChanged(Location location) {
        try {
            this.f6662a.zzd(location);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}
