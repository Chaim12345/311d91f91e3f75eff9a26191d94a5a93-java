package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzai;
/* loaded from: classes2.dex */
final class zzu extends zzai {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TileProvider f6634a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.f6634a = tileProvider;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    @Nullable
    public final Tile zzb(int i2, int i3, int i4) {
        return this.f6634a.getTile(i2, i3, i4);
    }
}
