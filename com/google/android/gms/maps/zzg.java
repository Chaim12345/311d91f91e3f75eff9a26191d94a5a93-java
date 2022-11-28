package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
/* loaded from: classes2.dex */
final class zzg extends com.google.android.gms.maps.internal.zzay {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.OnMyLocationChangeListener f6657a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(GoogleMap googleMap, GoogleMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        this.f6657a = onMyLocationChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaz
    public final void zzb(IObjectWrapper iObjectWrapper) {
        this.f6657a.onMyLocationChange((Location) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
