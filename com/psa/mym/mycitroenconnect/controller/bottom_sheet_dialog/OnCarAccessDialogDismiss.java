package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface OnCarAccessDialogDismiss {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onCarAccessDialogDismiss$default(OnCarAccessDialogDismiss onCarAccessDialogDismiss, boolean z, RegisteredVehicleItem registeredVehicleItem, String str, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onCarAccessDialogDismiss");
            }
            if ((i2 & 2) != 0) {
                registeredVehicleItem = null;
            }
            if ((i2 & 4) != 0) {
                str = null;
            }
            onCarAccessDialogDismiss.onCarAccessDialogDismiss(z, registeredVehicleItem, str);
        }
    }

    void onCarAccessDialogDismiss(boolean z, @Nullable RegisteredVehicleItem registeredVehicleItem, @Nullable String str);
}
