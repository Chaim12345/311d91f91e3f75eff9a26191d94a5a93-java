package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.maps.model.Tile;
/* loaded from: classes.dex */
public final class zzah extends zza implements zzaj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final Tile zzb(int i2, int i3, int i4) {
        Parcel b2 = b();
        b2.writeInt(i2);
        b2.writeInt(i3);
        b2.writeInt(i4);
        Parcel a2 = a(1, b2);
        Tile tile = (Tile) zzc.zza(a2, Tile.CREATOR);
        a2.recycle();
        return tile;
    }
}
