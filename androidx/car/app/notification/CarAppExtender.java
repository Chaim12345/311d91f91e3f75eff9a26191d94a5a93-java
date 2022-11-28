package androidx.car.app.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.CarColor;
import androidx.car.app.serialization.Bundler;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.CollectionUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarAppExtender implements NotificationCompat.Extender {
    private static final String EXTRA_ACTIONS = "actions";
    private static final String EXTRA_CAR_EXTENDER = "androidx.car.app.EXTENSIONS";
    private static final String EXTRA_CHANNEL_ID = "channel_id";
    private static final String EXTRA_COLOR = "color";
    private static final String EXTRA_CONTENT_INTENT = "content_intent";
    private static final String EXTRA_CONTENT_TEXT = "content_text";
    private static final String EXTRA_CONTENT_TITLE = "content_title";
    private static final String EXTRA_DELETE_INTENT = "delete_intent";
    private static final String EXTRA_IMPORTANCE = "importance";
    private static final String EXTRA_LARGE_BITMAP = "large_bitmap";
    private static final String EXTRA_SMALL_RES_ID = "small_res_id";
    private static final String TAG = "CarAppExtender";
    @Nullable
    private ArrayList<Notification.Action> mActions;
    @Nullable
    private String mChannelId;
    @Nullable
    private CarColor mColor;
    @Nullable
    private PendingIntent mContentIntent;
    @Nullable
    private CharSequence mContentText;
    @Nullable
    private CharSequence mContentTitle;
    @Nullable
    private PendingIntent mDeleteIntent;
    private int mImportance;
    @Nullable
    private Bitmap mLargeIconBitmap;
    private int mSmallIconResId;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        String mChannelId;
        @Nullable
        CarColor mColor;
        @Nullable
        PendingIntent mContentIntent;
        @Nullable
        CharSequence mContentText;
        @Nullable
        CharSequence mContentTitle;
        @Nullable
        PendingIntent mDeleteIntent;
        @Nullable
        Bitmap mLargeIconBitmap;
        int mSmallIconResId;
        final ArrayList<Notification.Action> mActions = new ArrayList<>();
        int mImportance = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;

        @NonNull
        public Builder addAction(@DrawableRes int i2, @NonNull CharSequence charSequence, @NonNull PendingIntent pendingIntent) {
            ArrayList<Notification.Action> arrayList = this.mActions;
            Objects.requireNonNull(charSequence);
            Objects.requireNonNull(pendingIntent);
            arrayList.add(new Notification.Action(i2, charSequence, pendingIntent));
            return this;
        }

        @NonNull
        public CarAppExtender build() {
            return new CarAppExtender(this);
        }

        @NonNull
        public Builder setChannelId(@NonNull String str) {
            this.mChannelId = str;
            return this;
        }

        @NonNull
        public Builder setColor(@NonNull CarColor carColor) {
            Objects.requireNonNull(carColor);
            this.mColor = carColor;
            return this;
        }

        @NonNull
        public Builder setContentIntent(@NonNull PendingIntent pendingIntent) {
            Objects.requireNonNull(pendingIntent);
            this.mContentIntent = pendingIntent;
            return this;
        }

        @NonNull
        public Builder setContentText(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mContentText = charSequence;
            return this;
        }

        @NonNull
        public Builder setContentTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mContentTitle = charSequence;
            return this;
        }

        @NonNull
        public Builder setDeleteIntent(@NonNull PendingIntent pendingIntent) {
            Objects.requireNonNull(pendingIntent);
            this.mDeleteIntent = pendingIntent;
            return this;
        }

        @NonNull
        public Builder setImportance(int i2) {
            this.mImportance = i2;
            return this;
        }

        @NonNull
        public Builder setLargeIcon(@NonNull Bitmap bitmap) {
            Objects.requireNonNull(bitmap);
            this.mLargeIconBitmap = bitmap;
            return this;
        }

        @NonNull
        public Builder setSmallIcon(int i2) {
            this.mSmallIconResId = i2;
            return this;
        }
    }

    public CarAppExtender(@NonNull Notification notification) {
        Bundle bundle;
        Bundle extras = NotificationCompat.getExtras(notification);
        if (extras == null || (bundle = extras.getBundle(EXTRA_CAR_EXTENDER)) == null) {
            return;
        }
        this.mContentTitle = bundle.getCharSequence(EXTRA_CONTENT_TITLE);
        this.mContentText = bundle.getCharSequence(EXTRA_CONTENT_TEXT);
        this.mSmallIconResId = bundle.getInt(EXTRA_SMALL_RES_ID);
        this.mLargeIconBitmap = (Bitmap) bundle.getParcelable(EXTRA_LARGE_BITMAP);
        this.mContentIntent = (PendingIntent) bundle.getParcelable(EXTRA_CONTENT_INTENT);
        this.mDeleteIntent = (PendingIntent) bundle.getParcelable(EXTRA_DELETE_INTENT);
        ArrayList<Notification.Action> parcelableArrayList = bundle.getParcelableArrayList(EXTRA_ACTIONS);
        this.mActions = parcelableArrayList == null ? new ArrayList<>() : parcelableArrayList;
        this.mImportance = bundle.getInt(EXTRA_IMPORTANCE, NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        Bundle bundle2 = bundle.getBundle("color");
        if (bundle2 != null) {
            try {
                this.mColor = (CarColor) Bundler.fromBundle(bundle2);
            } catch (BundlerException e2) {
                Log.e(TAG, "Failed to deserialize the notification color", e2);
            }
        }
        this.mChannelId = bundle.getString(EXTRA_CHANNEL_ID);
    }

    CarAppExtender(Builder builder) {
        this.mContentTitle = builder.mContentTitle;
        this.mContentText = builder.mContentText;
        this.mSmallIconResId = builder.mSmallIconResId;
        this.mLargeIconBitmap = builder.mLargeIconBitmap;
        this.mContentIntent = builder.mContentIntent;
        this.mDeleteIntent = builder.mDeleteIntent;
        this.mActions = builder.mActions;
        this.mImportance = builder.mImportance;
        this.mColor = builder.mColor;
        this.mChannelId = builder.mChannelId;
    }

    public static boolean isExtended(@NonNull Notification notification) {
        Objects.requireNonNull(notification);
        Bundle extras = NotificationCompat.getExtras(notification);
        return (extras == null || extras.getBundle(EXTRA_CAR_EXTENDER) == null) ? false : true;
    }

    @Override // androidx.core.app.NotificationCompat.Extender
    @NonNull
    public NotificationCompat.Builder extend(@NonNull NotificationCompat.Builder builder) {
        Objects.requireNonNull(builder);
        Bundle bundle = new Bundle();
        CharSequence charSequence = this.mContentTitle;
        if (charSequence != null) {
            bundle.putCharSequence(EXTRA_CONTENT_TITLE, charSequence);
        }
        CharSequence charSequence2 = this.mContentText;
        if (charSequence2 != null) {
            bundle.putCharSequence(EXTRA_CONTENT_TEXT, charSequence2);
        }
        int i2 = this.mSmallIconResId;
        if (i2 != 0) {
            bundle.putInt(EXTRA_SMALL_RES_ID, i2);
        }
        Bitmap bitmap = this.mLargeIconBitmap;
        if (bitmap != null) {
            bundle.putParcelable(EXTRA_LARGE_BITMAP, bitmap);
        }
        PendingIntent pendingIntent = this.mContentIntent;
        if (pendingIntent != null) {
            bundle.putParcelable(EXTRA_CONTENT_INTENT, pendingIntent);
        }
        PendingIntent pendingIntent2 = this.mDeleteIntent;
        if (pendingIntent2 != null) {
            bundle.putParcelable(EXTRA_DELETE_INTENT, pendingIntent2);
        }
        ArrayList<Notification.Action> arrayList = this.mActions;
        if (arrayList != null && !arrayList.isEmpty()) {
            bundle.putParcelableArrayList(EXTRA_ACTIONS, this.mActions);
        }
        bundle.putInt(EXTRA_IMPORTANCE, this.mImportance);
        CarColor carColor = this.mColor;
        if (carColor != null) {
            try {
                bundle.putBundle("color", Bundler.toBundle(carColor));
            } catch (BundlerException e2) {
                Log.e(TAG, "Failed to serialize the notification color", e2);
            }
        }
        String str = this.mChannelId;
        if (str != null) {
            bundle.putString(EXTRA_CHANNEL_ID, str);
        }
        builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
        return builder;
    }

    @NonNull
    public List<Notification.Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActions);
    }

    @Nullable
    public String getChannelId() {
        return this.mChannelId;
    }

    @Nullable
    public CarColor getColor() {
        return this.mColor;
    }

    @Nullable
    public PendingIntent getContentIntent() {
        return this.mContentIntent;
    }

    @Nullable
    public CharSequence getContentText() {
        return this.mContentText;
    }

    @Nullable
    public CharSequence getContentTitle() {
        return this.mContentTitle;
    }

    @Nullable
    public PendingIntent getDeleteIntent() {
        return this.mDeleteIntent;
    }

    public int getImportance() {
        return this.mImportance;
    }

    @Nullable
    public Bitmap getLargeIcon() {
        return this.mLargeIconBitmap;
    }

    @DrawableRes
    public int getSmallIcon() {
        return this.mSmallIconResId;
    }
}
