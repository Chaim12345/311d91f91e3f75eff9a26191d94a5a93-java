package com.psa.mym.mycitroenconnect.controller.adapters;

import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface CarAccessListener {
    void onCarAccessButtonTap(int i2, @NotNull RegisteredVehicleItem registeredVehicleItem);

    void onCarAccessChanged(int i2, @NotNull RegisteredVehicleItem registeredVehicleItem);
}
