package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* loaded from: classes2.dex */
final class zzw extends com.google.android.gms.maps.internal.zzq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnCameraMoveCanceledListener f6673a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(GoogleMap googleMap, GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.f6673a = onCameraMoveCanceledListener;
    }

    @Override // com.google.android.gms.maps.internal.zzr
    public final void zzb() {
        this.f6673a.onCameraMoveCanceled();
    }
}
