package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbk;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
/* loaded from: classes2.dex */
final class zzai extends zzbk {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaChangeListener f6640a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzai(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        this.f6640a = onStreetViewPanoramaChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbl
    public final void zzb(StreetViewPanoramaLocation streetViewPanoramaLocation) {
        this.f6640a.onStreetViewPanoramaChange(streetViewPanoramaLocation);
    }
}
