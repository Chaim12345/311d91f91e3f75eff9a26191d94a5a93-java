package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzba;
/* loaded from: classes2.dex */
final class zzi extends zzba {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMyLocationClickListener f6659a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(GoogleMap googleMap, GoogleMap.OnMyLocationClickListener onMyLocationClickListener) {
        this.f6659a = onMyLocationClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbb
    public final void zzb(Location location) {
        this.f6659a.onMyLocationClick(location);
    }
}
