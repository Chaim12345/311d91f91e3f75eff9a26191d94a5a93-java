package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Metadata {
    private static final String ACTION_C2DM_REGISTER = "com.google.android.c2dm.intent.REGISTER";
    private static final String ACTION_IID_TOKEN_REQUEST = "com.google.iid.TOKEN_REQUEST";
    private static final String GMSCORE_SEND_PERMISSION = "com.google.android.c2dm.permission.SEND";
    @GuardedBy("this")
    private String appVersionCode;
    @GuardedBy("this")
    private String appVersionName;
    private final Context context;
    @GuardedBy("this")
    private int gmsVersionCode;
    @GuardedBy("this")
    private int iidImplementation = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Metadata(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (applicationId.startsWith("1:")) {
            String[] split = applicationId.split(":");
            if (split.length < 2) {
                return null;
            }
            String str = split[1];
            if (str.isEmpty()) {
                return null;
            }
            return str;
        }
        return applicationId;
    }

    private PackageInfo getPackageInfo(String str) {
        try {
            return this.context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to find package ");
            sb.append(e2);
            return null;
        }
    }

    private synchronized void populateAppVersionInfo() {
        PackageInfo packageInfo = getPackageInfo(this.context.getPackageName());
        if (packageInfo != null) {
            this.appVersionCode = Integer.toString(packageInfo.versionCode);
            this.appVersionName = packageInfo.versionName;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String a() {
        if (this.appVersionCode == null) {
            populateAppVersionInfo();
        }
        return this.appVersionCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String b() {
        if (this.appVersionName == null) {
            populateAppVersionInfo();
        }
        return this.appVersionName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int d() {
        PackageInfo packageInfo;
        if (this.gmsVersionCode == 0 && (packageInfo = getPackageInfo("com.google.android.gms")) != null) {
            this.gmsVersionCode = packageInfo.versionCode;
        }
        return this.gmsVersionCode;
    }

    synchronized int e() {
        int i2 = this.iidImplementation;
        if (i2 != 0) {
            return i2;
        }
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager.checkPermission(GMSCORE_SEND_PERMISSION, "com.google.android.gms") == -1) {
            Log.e(Constants.TAG, "Google Play services missing or without correct permission.");
            return 0;
        }
        if (!PlatformVersion.isAtLeastO()) {
            Intent intent = new Intent(ACTION_C2DM_REGISTER);
            intent.setPackage("com.google.android.gms");
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                this.iidImplementation = 1;
                return 1;
            }
        }
        Intent intent2 = new Intent(ACTION_IID_TOKEN_REQUEST);
        intent2.setPackage("com.google.android.gms");
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
        if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
            this.iidImplementation = 2;
            return 2;
        }
        if (PlatformVersion.isAtLeastO()) {
            this.iidImplementation = 2;
        } else {
            this.iidImplementation = 1;
        }
        return this.iidImplementation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return e() != 0;
    }
}
