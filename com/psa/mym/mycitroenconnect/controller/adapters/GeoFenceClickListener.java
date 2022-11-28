package com.psa.mym.mycitroenconnect.controller.adapters;

import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface GeoFenceClickListener {
    void onDelete(int i2, @NotNull GetGeoFenceResponseItem getGeoFenceResponseItem);

    void onEditClick(@NotNull GetGeoFenceResponseItem getGeoFenceResponseItem);

    void onGeoFenceSwitchChange(boolean z, int i2);
}
