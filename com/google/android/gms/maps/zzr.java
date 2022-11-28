package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;
/* loaded from: classes2.dex */
final class zzr extends zzbc {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnPoiClickListener f6668a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(GoogleMap googleMap, GoogleMap.OnPoiClickListener onPoiClickListener) {
        this.f6668a = onPoiClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbd
    public final void zzb(PointOfInterest pointOfInterest) {
        this.f6668a.onPoiClick(pointOfInterest);
    }
}
