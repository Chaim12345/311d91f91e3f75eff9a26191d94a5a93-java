package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
/* loaded from: classes2.dex */
final class zzt extends com.google.android.gms.maps.internal.zzm {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnCameraChangeListener f6670a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(GoogleMap googleMap, GoogleMap.OnCameraChangeListener onCameraChangeListener) {
        this.f6670a = onCameraChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzn
    public final void zzb(CameraPosition cameraPosition) {
        this.f6670a.onCameraChange(cameraPosition);
    }
}
