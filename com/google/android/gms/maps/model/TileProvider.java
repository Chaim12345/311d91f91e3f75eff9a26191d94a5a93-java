package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface TileProvider {
    @NonNull
    public static final Tile NO_TILE = new Tile(-1, -1, null);

    @Nullable
    Tile getTile(int i2, int i3, int i4);
}
