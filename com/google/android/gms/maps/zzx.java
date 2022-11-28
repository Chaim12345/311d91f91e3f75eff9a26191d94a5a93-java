package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* loaded from: classes2.dex */
final class zzx extends com.google.android.gms.maps.internal.zzo {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnCameraIdleListener f6674a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.f6674a = onCameraIdleListener;
    }

    @Override // com.google.android.gms.maps.internal.zzp
    public final void zzb() {
        this.f6674a.onCameraIdle();
    }
}
