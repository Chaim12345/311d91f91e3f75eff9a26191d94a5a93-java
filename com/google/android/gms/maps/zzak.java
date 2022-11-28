package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
/* loaded from: classes2.dex */
final class zzak extends zzbm {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaClickListener f6642a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzak(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        this.f6642a = onStreetViewPanoramaClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbn
    public final void zzb(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.f6642a.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
    }
}
