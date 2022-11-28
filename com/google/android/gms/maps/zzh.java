package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* loaded from: classes2.dex */
final class zzh extends com.google.android.gms.maps.internal.zzaw {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMyLocationButtonClickListener f6658a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(GoogleMap googleMap, GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        this.f6658a = onMyLocationButtonClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzax
    public final boolean zzb() {
        return this.f6658a.onMyLocationButtonClick();
    }
}
