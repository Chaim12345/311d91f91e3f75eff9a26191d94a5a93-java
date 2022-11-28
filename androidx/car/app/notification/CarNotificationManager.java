package androidx.car.app.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.R;
import androidx.car.app.model.CarColor;
import androidx.car.app.notification.CarAppExtender;
import androidx.car.app.utils.CommonUtils;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationChannelGroupCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class CarNotificationManager {
    @NonNull
    private final Context mContext;
    @NonNull
    private final NotificationManagerCompat mNotificationManagerCompat;
    @Nullable
    @ColorInt
    private final Integer mPrimaryColor;
    @Nullable
    @ColorInt
    private final Integer mPrimaryColorDark;
    @Nullable
    @ColorInt
    private final Integer mSecondaryColor;
    @Nullable
    @ColorInt
    private final Integer mSecondaryColorDark;

    private CarNotificationManager(@NonNull Context context) {
        Objects.requireNonNull(context);
        Context context2 = context;
        this.mContext = context2;
        this.mNotificationManagerCompat = NotificationManagerCompat.from(context);
        Context createConfigurationContext = context2.createConfigurationContext(context.getResources().getConfiguration());
        int loadThemeId = loadThemeId(context);
        if (loadThemeId != 0) {
            createConfigurationContext.setTheme(loadThemeId);
        }
        Resources.Theme theme = createConfigurationContext.getTheme();
        Resources resources = theme.getResources();
        this.mPrimaryColor = getColor(resources.getIdentifier("carColorPrimary", "attr", context.getPackageName()), theme);
        this.mPrimaryColorDark = getColor(resources.getIdentifier("carColorPrimaryDark", "attr", context.getPackageName()), theme);
        this.mSecondaryColor = getColor(resources.getIdentifier("carColorSecondary", "attr", context.getPackageName()), theme);
        this.mSecondaryColorDark = getColor(resources.getIdentifier("carColorSecondaryDark", "attr", context.getPackageName()), theme);
    }

    @NonNull
    public static CarNotificationManager from(@NonNull Context context) {
        Objects.requireNonNull(context);
        return new CarNotificationManager(context);
    }

    private NotificationCompat.Action fromAndroidAction(@NonNull Notification.Action action) {
        return new NotificationCompat.Action.Builder(action.getIcon() == null ? null : IconCompat.createFromIcon(this.mContext, action.getIcon()), action.title, action.actionIntent).build();
    }

    @Nullable
    @ColorInt
    private static Integer getColor(int i2, Resources.Theme theme) {
        if (i2 != 0) {
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(new int[]{i2});
            Integer valueOf = Integer.valueOf(obtainStyledAttributes.getColor(0, 0));
            obtainStyledAttributes.recycle();
            return valueOf;
        }
        return null;
    }

    @NonNull
    public static Set<String> getEnabledListenerPackages(@NonNull Context context) {
        Objects.requireNonNull(context);
        return NotificationManagerCompat.getEnabledListenerPackages(context);
    }

    @StyleRes
    private static int loadThemeId(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getInt("androidx.car.app.theme");
            }
            return 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    private Notification upddateForAutomotive(@NonNull NotificationCompat.Builder builder) {
        Integer colorInt;
        CarAppExtender carAppExtender = new CarAppExtender(builder.build());
        List<Notification.Action> actions = carAppExtender.getActions();
        if (!actions.isEmpty()) {
            builder.clearActions();
            for (Notification.Action action : actions) {
                builder.addAction(fromAndroidAction(action));
            }
        }
        CarColor color = carAppExtender.getColor();
        if (color != null && (colorInt = getColorInt(color)) != null) {
            builder.setColorized(true);
            builder.setColor(colorInt.intValue());
        }
        PendingIntent contentIntent = carAppExtender.getContentIntent();
        if (contentIntent != null) {
            builder.setContentIntent(contentIntent);
        }
        CharSequence contentTitle = carAppExtender.getContentTitle();
        if (contentTitle != null) {
            builder.setContentTitle(contentTitle);
        }
        CharSequence contentText = carAppExtender.getContentText();
        if (contentText != null) {
            builder.setContentText(contentText);
        }
        PendingIntent deleteIntent = carAppExtender.getDeleteIntent();
        if (deleteIntent != null) {
            builder.setDeleteIntent(deleteIntent);
        }
        String channelId = carAppExtender.getChannelId();
        if (channelId != null) {
            builder.setChannelId(channelId);
        }
        Bitmap largeIcon = carAppExtender.getLargeIcon();
        if (largeIcon != null) {
            builder.setLargeIcon(largeIcon);
        }
        int smallIcon = carAppExtender.getSmallIcon();
        if (smallIcon != 0) {
            builder.setSmallIcon(smallIcon);
        }
        return builder.build();
    }

    public boolean areNotificationsEnabled() {
        return this.mNotificationManagerCompat.areNotificationsEnabled();
    }

    public void cancel(int i2) {
        this.mNotificationManagerCompat.cancel(i2);
    }

    public void cancel(@Nullable String str, int i2) {
        this.mNotificationManagerCompat.cancel(str, i2);
    }

    public void cancelAll() {
        this.mNotificationManagerCompat.cancelAll();
    }

    public void createNotificationChannel(@NonNull NotificationChannelCompat notificationChannelCompat) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(notificationChannelCompat);
        notificationManagerCompat.createNotificationChannel(notificationChannelCompat);
    }

    public void createNotificationChannelGroup(@NonNull NotificationChannelGroupCompat notificationChannelGroupCompat) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(notificationChannelGroupCompat);
        notificationManagerCompat.createNotificationChannelGroup(notificationChannelGroupCompat);
    }

    public void createNotificationChannelGroups(@NonNull List<NotificationChannelGroupCompat> list) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(list);
        notificationManagerCompat.createNotificationChannelGroupsCompat(list);
    }

    public void createNotificationChannels(@NonNull List<NotificationChannelCompat> list) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(list);
        notificationManagerCompat.createNotificationChannelsCompat(list);
    }

    public void deleteNotificationChannel(@NonNull String str) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(str);
        notificationManagerCompat.deleteNotificationChannel(str);
    }

    public void deleteNotificationChannelGroup(@NonNull String str) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(str);
        notificationManagerCompat.deleteNotificationChannelGroup(str);
    }

    public void deleteUnlistedNotificationChannels(@NonNull Collection<String> collection) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(collection);
        notificationManagerCompat.deleteUnlistedNotificationChannels(collection);
    }

    @Nullable
    @ColorInt
    @VisibleForTesting
    Integer getColorInt(CarColor carColor) {
        Context context;
        int i2;
        boolean z = (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
        int type = carColor.getType();
        if (type == 0) {
            return Integer.valueOf(z ? carColor.getColorDark() : carColor.getColor());
        }
        switch (type) {
            case 2:
                return z ? this.mPrimaryColorDark : this.mPrimaryColor;
            case 3:
                return z ? this.mSecondaryColorDark : this.mSecondaryColor;
            case 4:
                context = this.mContext;
                i2 = R.color.carColorRed;
                break;
            case 5:
                context = this.mContext;
                i2 = R.color.carColorGreen;
                break;
            case 6:
                context = this.mContext;
                i2 = R.color.carColorBlue;
                break;
            case 7:
                context = this.mContext;
                i2 = R.color.carColorYellow;
                break;
            default:
                return null;
        }
        return Integer.valueOf(context.getColor(i2));
    }

    public int getImportance() {
        return this.mNotificationManagerCompat.getImportance();
    }

    @Nullable
    public NotificationChannelCompat getNotificationChannel(@NonNull String str) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(str);
        return notificationManagerCompat.getNotificationChannelCompat(str);
    }

    @Nullable
    public NotificationChannelCompat getNotificationChannel(@NonNull String str, @NonNull String str2) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(str);
        Objects.requireNonNull(str2);
        return notificationManagerCompat.getNotificationChannelCompat(str, str2);
    }

    @Nullable
    public NotificationChannelGroupCompat getNotificationChannelGroup(@NonNull String str) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(str);
        return notificationManagerCompat.getNotificationChannelGroupCompat(str);
    }

    @NonNull
    public List<NotificationChannelGroupCompat> getNotificationChannelGroups() {
        return this.mNotificationManagerCompat.getNotificationChannelGroupsCompat();
    }

    @NonNull
    public List<NotificationChannelCompat> getNotificationChannels() {
        return this.mNotificationManagerCompat.getNotificationChannelsCompat();
    }

    public void notify(int i2, @NonNull NotificationCompat.Builder builder) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(builder);
        notificationManagerCompat.notify(i2, updateForCar(builder));
    }

    public void notify(@Nullable String str, int i2, @NonNull NotificationCompat.Builder builder) {
        NotificationManagerCompat notificationManagerCompat = this.mNotificationManagerCompat;
        Objects.requireNonNull(builder);
        notificationManagerCompat.notify(str, i2, updateForCar(builder));
    }

    @NonNull
    @VisibleForTesting
    Notification updateForCar(@NonNull NotificationCompat.Builder builder) {
        if (CommonUtils.isAutomotiveOS(this.mContext)) {
            return upddateForAutomotive(builder);
        }
        if (!CarAppExtender.isExtended(builder.build())) {
            builder.extend(new CarAppExtender.Builder().build());
        }
        return builder.build();
    }
}
