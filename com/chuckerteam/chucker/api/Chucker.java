package com.chuckerteam.chucker.api;

import android.content.Context;
import android.content.Intent;
import androidx.car.app.CarContext;
import com.chuckerteam.chucker.internal.support.ChuckerCrashHandler;
import com.chuckerteam.chucker.internal.support.NotificationHelper;
import com.chuckerteam.chucker.internal.ui.MainActivity;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002¢\u0006\u0004\b\u001a\u0010\u0012J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0007R\u001c\u0010\u000f\u001a\u00020\u00048\u0006@\u0007X\u0087T¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u0012\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u00020\u00048\u0006@\u0007X\u0087T¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u0012\u0004\b\u0014\u0010\u0012R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086D¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0016\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/chuckerteam/chucker/api/Chucker;", "", "Landroid/content/Context;", "context", "", CarContext.SCREEN_SERVICE, "Landroid/content/Intent;", "getLaunchIntent", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "collector", "", "registerDefaultCrashHandler", "dismissTransactionsNotification", "dismissErrorsNotification", "dismissNotifications", "SCREEN_HTTP", "I", "getSCREEN_HTTP$annotations", "()V", "SCREEN_ERROR", "getSCREEN_ERROR$annotations", "", "isOp", "Z", "()Z", "isOp$annotations", "<init>", "Screen", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class Chucker {
    public static final int SCREEN_ERROR = 2;
    public static final int SCREEN_HTTP = 1;
    public static final Chucker INSTANCE = new Chucker();
    private static final boolean isOp = true;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/chuckerteam/chucker/api/Chucker$Screen;", "", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    @Deprecated(message = "This param will be removed in 4.x release")
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface Screen {
    }

    private Chucker() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This fun will be removed in 4.x release", replaceWith = @ReplaceWith(expression = "Chucker.dismissNotifications(context)", imports = {}))
    @JvmStatic
    public static final void dismissErrorsNotification(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        new NotificationHelper(context).dismissErrorsNotification();
    }

    @JvmStatic
    public static final void dismissNotifications(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        new NotificationHelper(context).dismissNotifications();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This fun will be removed in 4.x release", replaceWith = @ReplaceWith(expression = "Chucker.dismissNotifications(context)", imports = {}))
    @JvmStatic
    public static final void dismissTransactionsNotification(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        new NotificationHelper(context).dismissTransactionsNotification();
    }

    @JvmStatic
    @NotNull
    public static final Intent getLaunchIntent(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent flags = new Intent(context, MainActivity.class).setFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(flags, "Intent(context, MainActi…t.FLAG_ACTIVITY_NEW_TASK)");
        return flags;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This fun will be removed in 4.x release", replaceWith = @ReplaceWith(expression = "Chucker.getLaunchIntent(context)", imports = {}))
    @JvmStatic
    @NotNull
    public static final Intent getLaunchIntent(@NotNull Context context, @Screen int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent putExtra = getLaunchIntent(context).putExtra(MainActivity.EXTRA_SCREEN, i2);
        Intrinsics.checkNotNullExpressionValue(putExtra, "getLaunchIntent(context)…ity.EXTRA_SCREEN, screen)");
        return putExtra;
    }

    @Deprecated(message = "This variable will be removed in 4.x release")
    public static /* synthetic */ void getSCREEN_ERROR$annotations() {
    }

    @Deprecated(message = "This variable will be removed in 4.x release")
    public static /* synthetic */ void getSCREEN_HTTP$annotations() {
    }

    public static /* synthetic */ void isOp$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This fun will be removed in 4.x release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    @JvmStatic
    public static final void registerDefaultCrashHandler(@NotNull ChuckerCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        Thread.setDefaultUncaughtExceptionHandler(new ChuckerCrashHandler(collector));
    }

    public final boolean isOp() {
        return isOp;
    }
}
