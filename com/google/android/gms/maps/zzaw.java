package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbq;
/* loaded from: classes2.dex */
final class zzaw extends zzbq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ OnStreetViewPanoramaReadyCallback f6650a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaw(zzax zzaxVar, OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        this.f6650a = onStreetViewPanoramaReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzbr
    public final void zzb(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) {
        this.f6650a.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
    }
}
