package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
/* loaded from: classes2.dex */
final class zzaj extends zzbi {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener f6641a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        this.f6641a = onStreetViewPanoramaCameraChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbj
    public final void zzb(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.f6641a.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
    }
}
