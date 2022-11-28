package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* loaded from: classes2.dex */
final class zzv extends com.google.android.gms.maps.internal.zzs {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnCameraMoveListener f6672a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(GoogleMap googleMap, GoogleMap.OnCameraMoveListener onCameraMoveListener) {
        this.f6672a = onCameraMoveListener;
    }

    @Override // com.google.android.gms.maps.internal.zzt
    public final void zzb() {
        this.f6672a.onCameraMove();
    }
}
