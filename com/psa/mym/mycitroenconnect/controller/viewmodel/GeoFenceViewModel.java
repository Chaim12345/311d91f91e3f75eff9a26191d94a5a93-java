package com.psa.mym.mycitroenconnect.controller.viewmodel;

import androidx.lifecycle.ViewModel;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceViewModel extends ViewModel {
    @Nullable
    private GeoFenceCommonModel geoFenceCommonModel;
    private int selectedFenceId = -1;

    @Nullable
    public final GeoFenceCommonModel getGeoFenceCommonModel() {
        return this.geoFenceCommonModel;
    }

    public final int getSelectedFenceId() {
        return this.selectedFenceId;
    }

    public final void setGeoFenceCommonModel(@Nullable GeoFenceCommonModel geoFenceCommonModel) {
        this.geoFenceCommonModel = geoFenceCommonModel;
    }

    public final void setSelectedFenceId(int i2) {
        this.selectedFenceId = i2;
    }
}
