package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzc extends com.google.android.gms.maps.internal.zzac {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnInfoWindowClickListener f6653a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(GoogleMap googleMap, GoogleMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.f6653a = onInfoWindowClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzad
    public final void zzb(com.google.android.gms.internal.maps.zzx zzxVar) {
        this.f6653a.onInfoWindowClick(new Marker(zzxVar));
    }
}
