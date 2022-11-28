package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzf extends com.google.android.gms.maps.internal.zzh {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.InfoWindowAdapter f6656a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        this.f6656a = infoWindowAdapter;
    }

    @Override // com.google.android.gms.maps.internal.zzi
    public final IObjectWrapper zzb(com.google.android.gms.internal.maps.zzx zzxVar) {
        return ObjectWrapper.wrap(this.f6656a.getInfoContents(new Marker(zzxVar)));
    }

    @Override // com.google.android.gms.maps.internal.zzi
    public final IObjectWrapper zzc(com.google.android.gms.internal.maps.zzx zzxVar) {
        return ObjectWrapper.wrap(this.f6656a.getInfoWindow(new Marker(zzxVar)));
    }
}
