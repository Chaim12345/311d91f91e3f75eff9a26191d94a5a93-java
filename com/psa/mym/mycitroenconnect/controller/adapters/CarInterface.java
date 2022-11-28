package com.psa.mym.mycitroenconnect.controller.adapters;

import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface CarInterface {
    void onCarChange(@NotNull MyCar myCar, int i2);

    void onCarViewDetails(@NotNull MyCar myCar, int i2);
}
