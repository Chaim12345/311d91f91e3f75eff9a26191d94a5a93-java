package com.psa.mym.mycitroenconnect.controller.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import androidx.core.app.NotificationManagerCompat;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.TransparentActivity;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.AppLockFingerprintActivity;
import com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertFragment;
import com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class TransparentActivity extends BaseActivity implements FullScreenAlertInterface {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int notificationId = -1;
    @Nullable
    private MyNotificationModel notificationModel;

    private final void getIntentData() {
        if (getIntent() == null) {
            finish();
            return;
        }
        this.notificationModel = (MyNotificationModel) getIntent().getParcelableExtra(AppConstants.ARG_PUSH_NOTIF_MODEL);
        this.notificationId = getIntent().getIntExtra(AppConstants.ARG_PUSH_NOTIF_ID, -1);
        showAlertNotification();
    }

    private final void goToAppLockFingerPrintActivity() {
        NotificationManagerCompat.from(this).cancelAll();
        Intent intent = new Intent(this, AppLockFingerprintActivity.class);
        intent.putExtra(AppConstants.ARG_PUSH_NOTIF_MODEL, this.notificationModel);
        intent.putExtra(AppConstants.ARG_IS_FROM_PUSH_NOTIF, true);
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }

    private final void showAlertNotification() {
        runOnUiThread(new Runnable() { // from class: i.q
            @Override // java.lang.Runnable
            public final void run() {
                TransparentActivity.m85showAlertNotification$lambda1(TransparentActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_IntrusionAlert) != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_LowFuelAlert) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_CrashAlert) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
        r1 = kotlin.jvm.internal.Intrinsics.areEqual(r0.getPriority(), "1");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_TowAwayAlert) == false) goto L21;
     */
    /* renamed from: showAlertNotification$lambda-1  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m85showAlertNotification$lambda1(TransparentActivity this$0) {
        boolean z;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MyNotificationModel myNotificationModel = this$0.notificationModel;
        if (myNotificationModel == null || myNotificationModel == null) {
            return;
        }
        String alertState = myNotificationModel.getAlertState();
        switch (alertState.hashCode()) {
            case -894483947:
                break;
            case 93415538:
                break;
            case 970664341:
                break;
            case 1881199922:
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            this$0.goToAppLockFingerPrintActivity();
            return;
        }
        FullScreenAlertFragment.Companion companion = FullScreenAlertFragment.Companion;
        FullScreenAlertFragment newInstance = companion.newInstance(myNotificationModel.getAlertState());
        newInstance.show(this$0.getSupportFragmentManager(), companion.getTAG());
        newInstance.setOnFullScreenAlertListener(this$0);
        newInstance.setCancelable(false);
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface
    public void onAlertBtnClick(@Nullable String str) {
        Logger logger = Logger.INSTANCE;
        logger.e("Alert Type: " + str);
        goToAppLockFingerPrintActivity();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface
    public void onAlertDismissClick() {
        if (this.notificationId != -1) {
            NotificationManagerCompat.from(this).cancel(this.notificationId);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_transparent);
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
            }
        } else {
            getWindow().setFlags(1024, 1024);
        }
        Object systemService = getSystemService("power");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        PowerManager powerManager = (PowerManager) systemService;
        if (!powerManager.isInteractive()) {
            powerManager.newWakeLock(268435462, "myCitroenApp:notificationLock").acquire(3000L);
        }
        setFinishOnTouchOutside(false);
        Logger.INSTANCE.e("Transparent Activity Start");
        getIntentData();
    }
}
