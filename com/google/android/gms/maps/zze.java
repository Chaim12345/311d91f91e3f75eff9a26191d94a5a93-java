package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes2.dex */
final class zze extends com.google.android.gms.maps.internal.zzae {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnInfoWindowCloseListener f6655a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.f6655a = onInfoWindowCloseListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaf
    public final void zzb(com.google.android.gms.internal.maps.zzx zzxVar) {
        this.f6655a.onInfoWindowClose(new Marker(zzxVar));
    }
}
