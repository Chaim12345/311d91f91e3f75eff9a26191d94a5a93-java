package com.karumi.dexter;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
/* loaded from: classes2.dex */
class AndroidPermissionService {
    /* JADX INFO: Access modifiers changed from: package-private */
    public int checkSelfPermission(@NonNull Context context, @NonNull String str) {
        return PermissionChecker.checkSelfPermission(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestPermissions(@Nullable Activity activity, @NonNull String[] strArr, int i2) {
        if (activity == null) {
            return;
        }
        ActivityCompat.requestPermissions(activity, strArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldShowRequestPermissionRationale(@Nullable Activity activity, @NonNull String str) {
        if (activity == null) {
            return false;
        }
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
    }
}
