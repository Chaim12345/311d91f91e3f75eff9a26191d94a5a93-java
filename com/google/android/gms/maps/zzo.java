package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzo extends zzbe {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnPolygonClickListener f6665a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(GoogleMap googleMap, GoogleMap.OnPolygonClickListener onPolygonClickListener) {
        this.f6665a = onPolygonClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbf
    public final void zzb(com.google.android.gms.internal.maps.zzaa zzaaVar) {
        this.f6665a.onPolygonClick(new Polygon(zzaaVar));
    }
}
