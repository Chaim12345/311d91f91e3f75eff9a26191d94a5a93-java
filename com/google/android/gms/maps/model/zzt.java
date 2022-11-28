package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzaj;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzt implements TileProvider {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TileOverlayOptions f6633a;
    private final zzaj zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(TileOverlayOptions tileOverlayOptions) {
        zzaj zzajVar;
        this.f6633a = tileOverlayOptions;
        zzajVar = tileOverlayOptions.zza;
        this.zzb = zzajVar;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    @Nullable
    public final Tile getTile(int i2, int i3, int i4) {
        try {
            return this.zzb.zzb(i2, i3, i4);
        } catch (RemoteException unused) {
            return null;
        }
    }
}
