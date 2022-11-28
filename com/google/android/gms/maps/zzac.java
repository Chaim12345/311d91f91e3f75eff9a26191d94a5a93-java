package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.IGoogleMapDelegate;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzac extends com.google.android.gms.maps.internal.zzaq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ OnMapReadyCallback f6636a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(zzad zzadVar, OnMapReadyCallback onMapReadyCallback) {
        this.f6636a = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzar
    public final void zzb(IGoogleMapDelegate iGoogleMapDelegate) {
        this.f6636a.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
