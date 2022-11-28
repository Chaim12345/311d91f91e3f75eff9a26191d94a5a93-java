package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.IndoorBuilding;
/* loaded from: classes2.dex */
final class zzk extends com.google.android.gms.maps.internal.zzaa {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnIndoorStateChangeListener f6661a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(GoogleMap googleMap, GoogleMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.f6661a = onIndoorStateChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzab
    public final void zzb() {
        this.f6661a.onIndoorBuildingFocused();
    }

    @Override // com.google.android.gms.maps.internal.zzab
    public final void zzc(com.google.android.gms.internal.maps.zzr zzrVar) {
        this.f6661a.onIndoorLevelActivated(new IndoorBuilding(zzrVar));
    }
}
