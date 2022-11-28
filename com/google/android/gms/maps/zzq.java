package com.google.android.gms.maps;

import android.graphics.Bitmap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzq extends zzbt {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleMap.SnapshotReadyCallback f6667a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(GoogleMap googleMap, GoogleMap.SnapshotReadyCallback snapshotReadyCallback) {
        this.f6667a = snapshotReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzbu
    public final void zzb(Bitmap bitmap) {
        this.f6667a.onSnapshotReady(bitmap);
    }

    @Override // com.google.android.gms.maps.internal.zzbu
    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.f6667a.onSnapshotReady((Bitmap) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
