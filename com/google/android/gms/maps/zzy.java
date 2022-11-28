package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes2.dex */
final class zzy extends com.google.android.gms.maps.internal.zzak {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMapClickListener f6675a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.f6675a = onMapClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void zzb(LatLng latLng) {
        this.f6675a.onMapClick(latLng);
    }
}
