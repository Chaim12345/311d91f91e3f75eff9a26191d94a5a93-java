package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.IGoogleMapDelegate;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzat extends com.google.android.gms.maps.internal.zzaq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ OnMapReadyCallback f6648a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzat(zzau zzauVar, OnMapReadyCallback onMapReadyCallback) {
        this.f6648a = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzar
    public final void zzb(IGoogleMapDelegate iGoogleMapDelegate) {
        this.f6648a.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
