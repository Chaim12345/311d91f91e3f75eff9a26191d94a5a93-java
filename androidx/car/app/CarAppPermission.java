package androidx.car.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.car.app.utils.LogTags;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public final class CarAppPermission {
    public static final String ACCESS_SURFACE = "androidx.car.app.ACCESS_SURFACE";
    public static final String MAP_TEMPLATES = "androidx.car.app.MAP_TEMPLATES";
    public static final String NAVIGATION_TEMPLATES = "androidx.car.app.NAVIGATION_TEMPLATES";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface LibraryPermission {
    }

    private CarAppPermission() {
    }

    public static void checkHasLibraryPermission(@NonNull Context context, @NonNull String str) {
        if (Log.isLoggable(LogTags.TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Checking to see if the car app requested the required library permission: ");
            sb.append(str);
        }
        try {
            try {
                checkHasPermission(context, str);
            } catch (SecurityException unused) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                if (packageInfo.requestedPermissions != null) {
                    for (String str2 : packageInfo.requestedPermissions) {
                        if (str2.equals(str)) {
                            return;
                        }
                    }
                }
                throw new SecurityException("The car app does not have a required permission: " + str);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(LogTags.TAG, "Package name not found on the system: " + context.getPackageName(), e2);
            throw new SecurityException("The car app does not have a required permission: " + str);
        }
    }

    public static void checkHasPermission(@NonNull Context context, @NonNull String str) {
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return;
        }
        throw new SecurityException("The car app does not have the required permission: " + str);
    }
}
