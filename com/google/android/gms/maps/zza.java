package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zza extends com.google.android.gms.maps.internal.zzas {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMarkerClickListener f6635a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(GoogleMap googleMap, GoogleMap.OnMarkerClickListener onMarkerClickListener) {
        this.f6635a = onMarkerClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final boolean zzb(com.google.android.gms.internal.maps.zzx zzxVar) {
        return this.f6635a.onMarkerClick(new Marker(zzxVar));
    }
}
