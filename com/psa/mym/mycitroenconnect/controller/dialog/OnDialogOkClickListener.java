package com.psa.mym.mycitroenconnect.controller.dialog;

import android.app.Dialog;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
/* loaded from: classes3.dex */
public interface OnDialogOkClickListener {
    void onDialogCancelClick(Dialog dialog, MyNotificationModel myNotificationModel);

    void onDialogOkClick(Dialog dialog, MyNotificationModel myNotificationModel);
}
