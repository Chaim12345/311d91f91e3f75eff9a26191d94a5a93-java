package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzd extends com.google.android.gms.maps.internal.zzag {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnInfoWindowLongClickListener f6654a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzd(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.f6654a = onInfoWindowLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzah
    public final void zzb(com.google.android.gms.internal.maps.zzx zzxVar) {
        this.f6654a.onInfoWindowLongClick(new Marker(zzxVar));
    }
}
