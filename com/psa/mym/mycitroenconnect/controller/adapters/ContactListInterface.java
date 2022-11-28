package com.psa.mym.mycitroenconnect.controller.adapters;

import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface ContactListInterface {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onItemClick$default(ContactListInterface contactListInterface, EmergencyDetailsItem emergencyDetailsItem, boolean z, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onItemClick");
            }
            if ((i2 & 2) != 0) {
                z = false;
            }
            contactListInterface.onItemClick(emergencyDetailsItem, z);
        }
    }

    void onCheckedChange(@NotNull ArrayList<EmergencyDetailsItem> arrayList);

    void onItemClick(@NotNull EmergencyDetailsItem emergencyDetailsItem, boolean z);
}
