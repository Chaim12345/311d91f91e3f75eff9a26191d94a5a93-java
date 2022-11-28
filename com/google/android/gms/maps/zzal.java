package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbo;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
/* loaded from: classes2.dex */
final class zzal extends zzbo {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaLongClickListener f6643a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
        this.f6643a = onStreetViewPanoramaLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbp
    public final void zzb(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.f6643a.onStreetViewPanoramaLongClick(streetViewPanoramaOrientation);
    }
}
