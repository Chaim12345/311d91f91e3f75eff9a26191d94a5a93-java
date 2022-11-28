package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzp extends zzbg {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnPolylineClickListener f6666a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.f6666a = onPolylineClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbh
    public final void zzb(com.google.android.gms.internal.maps.zzad zzadVar) {
        this.f6666a.onPolylineClick(new Polyline(zzadVar));
    }
}
