package androidx.car.app.notification;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.CarContext;
import androidx.car.app.utils.CommonUtils;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarPendingIntent {
    @VisibleForTesting
    static final String CAR_APP_ACTIVITY_CLASSNAME = "androidx.car.app.activity.CarAppActivity";
    static final String COMPONENT_EXTRA_KEY = "androidx.car.app.notification.COMPONENT_EXTRA_KEY";
    private static final int FLAG_MUTABLE = 33554432;
    private static final String NAVIGATION_URI_PREFIX = "geo:";
    private static final String PHONE_URI_PREFIX = "tel:";
    private static final String SEARCH_QUERY_PARAMETER = "q";
    private static final String SEARCH_QUERY_PARAMETER_SPLITTER = "q=";

    private CarPendingIntent() {
    }

    private static PendingIntent createForAutomotive(Context context, int i2, Intent intent, int i3) {
        String packageName = context.getPackageName();
        ComponentName component = intent.getComponent();
        if (component != null && Objects.equals(component.getPackageName(), packageName)) {
            intent.setClassName(packageName, CAR_APP_ACTIVITY_CLASSNAME);
        }
        return PendingIntent.getActivity(context, i2, intent, i3);
    }

    private static PendingIntent createForProjected(Context context, int i2, Intent intent, int i3) {
        intent.putExtra(COMPONENT_EXTRA_KEY, intent.getComponent());
        intent.setClass(context, CarAppNotificationBroadcastReceiver.class);
        return PendingIntent.getBroadcast(context, i2, intent, i3);
    }

    @NonNull
    public static PendingIntent getCarApp(@NonNull Context context, int i2, @NonNull Intent intent, int i3) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(intent);
        validateIntent(context, intent);
        int i4 = (i3 & (-67108865)) | 33554432;
        return CommonUtils.isAutomotiveOS(context) ? createForAutomotive(context, i2, intent, i4) : createForProjected(context, i2, intent, i4);
    }

    @Nullable
    private static String getQueryString(Uri uri) {
        if (uri.isHierarchical()) {
            List<String> queryParameters = uri.getQueryParameters(SEARCH_QUERY_PARAMETER);
            if (queryParameters.isEmpty()) {
                return null;
            }
            return queryParameters.get(0);
        }
        String[] split = uri.getEncodedSchemeSpecificPart().split(SEARCH_QUERY_PARAMETER_SPLITTER);
        if (split.length < 2) {
            return null;
        }
        return split[1].split("&")[0];
    }

    private static boolean isLatitudeLongitude(String str) {
        String[] split = str.split(",");
        if (split.length == 2) {
            try {
                Double.parseDouble(split[0]);
                Double.parseDouble(split[1]);
                return true;
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    @VisibleForTesting
    static void validateIntent(Context context, Intent intent) {
        String packageName = context.getPackageName();
        String action = intent.getAction();
        ComponentName component = intent.getComponent();
        if (component != null && Objects.equals(component.getPackageName(), packageName)) {
            try {
                context.getPackageManager().getServiceInfo(component, 128);
            } catch (PackageManager.NameNotFoundException unused) {
                throw new InvalidParameterException("Intent does not have the CarAppService's ComponentName as its target" + intent);
            }
        } else if (Objects.equals(action, CarContext.ACTION_NAVIGATE)) {
            validateNavigationIntentIsValid(intent);
        } else if ("android.intent.action.DIAL".equals(action) || "android.intent.action.CALL".equals(action)) {
            validatePhoneIntentIsValid(intent);
        } else if (component != null) {
            throw new SecurityException("Explicitly starting a separate app is not supported");
        } else {
            throw new InvalidParameterException("The intent is not for a supported action");
        }
    }

    private static void validateNavigationIntentIsValid(Intent intent) {
        if (!(intent.getDataString() == null ? "" : intent.getDataString()).startsWith(NAVIGATION_URI_PREFIX)) {
            throw new InvalidParameterException("Navigation intent has a malformed uri");
        }
        Uri data = intent.getData();
        if (getQueryString(data) == null && !isLatitudeLongitude(data.getEncodedSchemeSpecificPart())) {
            throw new InvalidParameterException("Navigation intent has neither a location nor a query string");
        }
    }

    private static void validatePhoneIntentIsValid(Intent intent) {
        if (!(intent.getDataString() == null ? "" : intent.getDataString()).startsWith(PHONE_URI_PREFIX)) {
            throw new InvalidParameterException("Phone intent data is not properly formatted");
        }
        if (intent.getComponent() != null) {
            throw new SecurityException("Phone intent cannot have a component");
        }
    }
}
