package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* loaded from: classes2.dex */
final class zzu extends com.google.android.gms.maps.internal.zzu {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnCameraMoveStartedListener f6671a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.f6671a = onCameraMoveStartedListener;
    }

    @Override // com.google.android.gms.maps.internal.zzv
    public final void zzb(int i2) {
        this.f6671a.onCameraMoveStarted(i2);
    }
}
