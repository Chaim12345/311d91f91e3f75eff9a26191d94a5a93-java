package com.psa.mym.mycitroenconnect.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.AppLockFingerprintActivity;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.event_bus.GlobalBusUtil;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyFirebaseMessagingService extends FirebaseMessagingService {
    private final PendingIntent getContentIntent(int i2, MyNotificationModel myNotificationModel) {
        Logger.INSTANCE.e("Content Intent Start");
        Intent intent = new Intent(this, AppLockFingerprintActivity.class);
        intent.putExtra(AppConstants.ARG_PUSH_NOTIF_ID, i2);
        intent.putExtra(AppConstants.ARG_PUSH_NOTIF_MODEL, myNotificationModel);
        intent.putExtra(AppConstants.ARG_IS_FROM_PUSH_NOTIF, true);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 134217728);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(this, 0, int…tent.FLAG_UPDATE_CURRENT)");
        return activity;
    }

    private final PendingIntent getFullScreenIntent(int i2, MyNotificationModel myNotificationModel) {
        Logger.INSTANCE.e("Full Screen Intent Start");
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra(AppConstants.ARG_PUSH_NOTIF_ID, i2);
        intent.putExtra(AppConstants.ARG_PUSH_NOTIF_MODEL, myNotificationModel);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, intent, 134217728);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(this, 0, in…tent.FLAG_UPDATE_CURRENT)");
        return broadcast;
    }

    private final void handleNow() {
        Logger.INSTANCE.d("Short lived task is done.");
    }

    private final void sendNotification(String str, String str2, MyNotificationModel myNotificationModel) {
        int parseInt = !Intrinsics.areEqual(myNotificationModel.getNotificationId(), "") ? Integer.parseInt(myNotificationModel.getNotificationId()) : (int) ((System.currentTimeMillis() / 1000) % Integer.MAX_VALUE);
        String string = getString(R.string.default_notification_channel_id);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.defau…_notification_channel_id)");
        Uri defaultUri = RingtoneManager.getDefaultUri(2);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, string);
        builder.setPriority(ExtensionsKt.isScreenOn(this) ? -2 : 3);
        builder.setContentTitle(str);
        builder.setContentText(str2);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.mycitroen_app_round);
        if (ExtensionsKt.isScreenOn(this)) {
            builder.setVisibility(1);
            builder.setContentIntent(getContentIntent(parseInt, myNotificationModel));
        } else {
            builder.setFullScreenIntent(getFullScreenIntent(parseInt, myNotificationModel), true);
        }
        builder.setCategory(NotificationCompat.CATEGORY_ALARM);
        builder.setSound(defaultUri);
        Object systemService = getSystemService("notification");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(string, getString(R.string.app_name), 4));
        }
        notificationManager.notify(parseInt, builder.build());
    }

    private final void sendRegistrationToServer(String str) {
        if (str != null) {
            Logger.INSTANCE.d(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0071  */
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
        boolean z;
        boolean isBlank;
        String str;
        String str2;
        boolean isBlank2;
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        Logger logger = Logger.INSTANCE;
        logger.d("From: " + remoteMessage.getData());
        if (!SharedPref.Companion.isLogin(this)) {
            logger.e("User is not logged into application");
            return;
        }
        Map<String, String> data = remoteMessage.getData();
        Intrinsics.checkNotNullExpressionValue(data, "remoteMessage.data");
        data.isEmpty();
        logger.e("Message data payload: " + remoteMessage.getData());
        handleNow();
        String optString = new JSONObject(remoteMessage.getData()).optString("contentData");
        if (optString != null) {
            isBlank2 = StringsKt__StringsJVMKt.isBlank(optString);
            if (!isBlank2) {
                z = false;
                if (z) {
                    JSONObject jSONObject = new JSONObject(optString);
                    String title = jSONObject.optString("title");
                    String body = jSONObject.optString("body");
                    String optString2 = jSONObject.optString("alertState");
                    Intrinsics.checkNotNullExpressionValue(optString2, "contentJson.optString(\"alertState\")");
                    isBlank = StringsKt__StringsJVMKt.isBlank(optString2);
                    if (!(true ^ isBlank)) {
                        Intrinsics.checkNotNullExpressionValue(title, "title");
                        Locale locale = Locale.ROOT;
                        String lowerCase = title.toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        String lowerCase2 = "Child Account Invite".toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                            UpdatedEvents.INSTANCE.getChildInviteEvent().postValue(AppConstants.CHILD_INVITE);
                            str = AppConstants.CHILD_INVITE_NOTI_ID;
                        } else {
                            str = "";
                        }
                        String lowerCase3 = title.toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        String lowerCase4 = "Child Account Access Revoked".toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        if (Intrinsics.areEqual(lowerCase3, lowerCase4)) {
                            UpdatedEvents.INSTANCE.getAccessRevokeEvent().postValue(AppConstants.CHILD_ACCESS_REVOKE);
                            str2 = AppConstants.CHILD_REVOKE_NOTI_ID;
                        } else {
                            str2 = str;
                        }
                        MyNotificationModel myNotificationModel = new MyNotificationModel("", title, body == null ? "" : body, "", "", "", "", str2);
                        Intrinsics.checkNotNullExpressionValue(body, "body");
                        sendNotification(title, body, myNotificationModel);
                        return;
                    }
                    String alertState = jSONObject.optString("alertState");
                    String optString3 = jSONObject.optString("alertName");
                    String optString4 = jSONObject.optString("alertTime");
                    String optString5 = jSONObject.optString("vehicleId");
                    String optString6 = jSONObject.optString(LogFactory.PRIORITY_KEY);
                    String optString7 = jSONObject.optString("notificationId");
                    logger.e("alertState: " + alertState);
                    StringBuilder sb = new StringBuilder();
                    sb.append("title: ");
                    sb.append(title == null ? "" : title);
                    logger.e(sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("body: ");
                    sb2.append(body == null ? "" : body);
                    logger.e(sb2.toString());
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("alertName: ");
                    sb3.append(optString3 == null ? "" : optString3);
                    logger.e(sb3.toString());
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("alertTime: ");
                    sb4.append(optString4 == null ? "" : optString4);
                    logger.e(sb4.toString());
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("vehicleId: ");
                    sb5.append(optString5 == null ? "" : optString5);
                    logger.e(sb5.toString());
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("priority: ");
                    sb6.append(optString6 == null ? "" : optString6);
                    logger.e(sb6.toString());
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("notificationId: ");
                    sb7.append(optString7 == null ? "" : optString7);
                    logger.e(sb7.toString());
                    Intrinsics.checkNotNullExpressionValue(alertState, "alertState");
                    MyNotificationModel myNotificationModel2 = new MyNotificationModel(alertState, title == null ? "" : title, body == null ? "" : body, optString3 == null ? "" : optString3, optString4 == null ? "" : optString4, optString5 == null ? "" : optString5, optString6 == null ? "" : optString6, optString7 == null ? "" : optString7);
                    AppUtil.Companion companion = AppUtil.Companion;
                    Context applicationContext = getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                    if (companion.isAppIsInBackground(applicationContext)) {
                        return;
                    }
                    logger.e("activityVisible: true");
                    GlobalBusUtil.Companion.optBus().post(myNotificationModel2);
                    return;
                }
                return;
            }
        }
        z = true;
        if (z) {
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        Logger logger = Logger.INSTANCE;
        logger.d("Refreshed token: " + token);
        sendRegistrationToServer(token);
    }
}
