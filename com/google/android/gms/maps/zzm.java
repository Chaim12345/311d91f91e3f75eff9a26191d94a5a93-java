package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlay;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzm extends com.google.android.gms.maps.internal.zzy {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnGroundOverlayClickListener f6663a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(GoogleMap googleMap, GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
        this.f6663a = onGroundOverlayClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzz
    public final void zzb(com.google.android.gms.internal.maps.zzo zzoVar) {
        this.f6663a.onGroundOverlayClick(new GroundOverlay(zzoVar));
    }
}
