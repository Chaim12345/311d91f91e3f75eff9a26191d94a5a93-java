package com.psa.mym.mycitroenconnect.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.TransparentActivity;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import com.psa.mym.mycitroenconnect.utils.Logger;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NotificationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        Context applicationContext;
        if (intent == null) {
            Logger.INSTANCE.e("Null intent received.");
            return;
        }
        Logger logger = Logger.INSTANCE;
        logger.e("Notification Received");
        int intExtra = intent.getIntExtra(AppConstants.ARG_PUSH_NOTIF_ID, -1);
        Intent intent2 = new Intent(context, TransparentActivity.class);
        intent2.addFlags(268435456);
        intent2.putExtra(AppConstants.ARG_PUSH_NOTIF_MODEL, (MyNotificationModel) intent.getParcelableExtra(AppConstants.ARG_PUSH_NOTIF_MODEL));
        intent2.putExtra(AppConstants.ARG_PUSH_NOTIF_ID, intExtra);
        StringBuilder sb = new StringBuilder();
        sb.append("Context is null? ");
        sb.append(context == null);
        logger.e(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Application Context is null? ");
        sb2.append((context != null ? context.getApplicationContext() : null) == null);
        logger.e(sb2.toString());
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            return;
        }
        applicationContext.startActivity(intent2);
    }
}
