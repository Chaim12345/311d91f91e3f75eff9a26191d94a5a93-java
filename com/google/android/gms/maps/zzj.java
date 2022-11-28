package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* loaded from: classes2.dex */
final class zzj extends com.google.android.gms.maps.internal.zzam {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMapLoadedCallback f6660a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.f6660a = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzan
    public final void zzb() {
        this.f6660a.onMapLoaded();
    }
}
