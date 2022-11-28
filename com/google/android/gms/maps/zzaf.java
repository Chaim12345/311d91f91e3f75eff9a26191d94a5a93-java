package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.IGoogleMapDelegate;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaf extends com.google.android.gms.maps.internal.zzaq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ OnMapReadyCallback f6638a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(zzag zzagVar, OnMapReadyCallback onMapReadyCallback) {
        this.f6638a = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzar
    public final void zzb(IGoogleMapDelegate iGoogleMapDelegate) {
        this.f6638a.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
