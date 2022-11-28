package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes2.dex */
final class zzz extends com.google.android.gms.maps.internal.zzao {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMapLongClickListener f6676a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(GoogleMap googleMap, GoogleMap.OnMapLongClickListener onMapLongClickListener) {
        this.f6676a = onMapLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zzb(LatLng latLng) {
        this.f6676a.onMapLongClick(latLng);
    }
}
