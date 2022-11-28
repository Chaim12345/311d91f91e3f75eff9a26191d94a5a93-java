package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzb extends com.google.android.gms.maps.internal.zzau {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMarkerDragListener f6652a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        this.f6652a = onMarkerDragListener;
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final void zzb(com.google.android.gms.internal.maps.zzx zzxVar) {
        this.f6652a.onMarkerDrag(new Marker(zzxVar));
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final void zzc(com.google.android.gms.internal.maps.zzx zzxVar) {
        this.f6652a.onMarkerDragEnd(new Marker(zzxVar));
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final void zzd(com.google.android.gms.internal.maps.zzx zzxVar) {
        this.f6652a.onMarkerDragStart(new Marker(zzxVar));
    }
}
