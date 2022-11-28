package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
/* loaded from: classes.dex */
public class NotificationChannelCompat {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private static final int DEFAULT_LIGHT_COLOR = 0;
    private static final boolean DEFAULT_SHOW_BADGE = true;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final String f2408a;

    /* renamed from: b  reason: collision with root package name */
    CharSequence f2409b;

    /* renamed from: c  reason: collision with root package name */
    int f2410c;

    /* renamed from: d  reason: collision with root package name */
    String f2411d;

    /* renamed from: e  reason: collision with root package name */
    String f2412e;

    /* renamed from: f  reason: collision with root package name */
    boolean f2413f;

    /* renamed from: g  reason: collision with root package name */
    Uri f2414g;

    /* renamed from: h  reason: collision with root package name */
    AudioAttributes f2415h;

    /* renamed from: i  reason: collision with root package name */
    boolean f2416i;

    /* renamed from: j  reason: collision with root package name */
    int f2417j;

    /* renamed from: k  reason: collision with root package name */
    boolean f2418k;

    /* renamed from: l  reason: collision with root package name */
    long[] f2419l;

    /* renamed from: m  reason: collision with root package name */
    String f2420m;
    private boolean mBypassDnd;
    private boolean mCanBubble;
    private boolean mImportantConversation;
    private int mLockscreenVisibility;

    /* renamed from: n  reason: collision with root package name */
    String f2421n;

    /* loaded from: classes.dex */
    public static class Builder {
        private final NotificationChannelCompat mChannel;

        public Builder(@NonNull String str, int i2) {
            this.mChannel = new NotificationChannelCompat(str, i2);
        }

        @NonNull
        public NotificationChannelCompat build() {
            return this.mChannel;
        }

        @NonNull
        public Builder setConversationId(@NonNull String str, @NonNull String str2) {
            if (Build.VERSION.SDK_INT >= 30) {
                NotificationChannelCompat notificationChannelCompat = this.mChannel;
                notificationChannelCompat.f2420m = str;
                notificationChannelCompat.f2421n = str2;
            }
            return this;
        }

        @NonNull
        public Builder setDescription(@Nullable String str) {
            this.mChannel.f2411d = str;
            return this;
        }

        @NonNull
        public Builder setGroup(@Nullable String str) {
            this.mChannel.f2412e = str;
            return this;
        }

        @NonNull
        public Builder setImportance(int i2) {
            this.mChannel.f2410c = i2;
            return this;
        }

        @NonNull
        public Builder setLightColor(int i2) {
            this.mChannel.f2417j = i2;
            return this;
        }

        @NonNull
        public Builder setLightsEnabled(boolean z) {
            this.mChannel.f2416i = z;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.mChannel.f2409b = charSequence;
            return this;
        }

        @NonNull
        public Builder setShowBadge(boolean z) {
            this.mChannel.f2413f = z;
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri uri, @Nullable AudioAttributes audioAttributes) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.f2414g = uri;
            notificationChannelCompat.f2415h = audioAttributes;
            return this;
        }

        @NonNull
        public Builder setVibrationEnabled(boolean z) {
            this.mChannel.f2418k = z;
            return this;
        }

        @NonNull
        public Builder setVibrationPattern(@Nullable long[] jArr) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.f2418k = jArr != null && jArr.length > 0;
            notificationChannelCompat.f2419l = jArr;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(26)
    public NotificationChannelCompat(@NonNull NotificationChannel notificationChannel) {
        this(notificationChannel.getId(), notificationChannel.getImportance());
        this.f2409b = notificationChannel.getName();
        this.f2411d = notificationChannel.getDescription();
        this.f2412e = notificationChannel.getGroup();
        this.f2413f = notificationChannel.canShowBadge();
        this.f2414g = notificationChannel.getSound();
        this.f2415h = notificationChannel.getAudioAttributes();
        this.f2416i = notificationChannel.shouldShowLights();
        this.f2417j = notificationChannel.getLightColor();
        this.f2418k = notificationChannel.shouldVibrate();
        this.f2419l = notificationChannel.getVibrationPattern();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.f2420m = notificationChannel.getParentChannelId();
            this.f2421n = notificationChannel.getConversationId();
        }
        this.mBypassDnd = notificationChannel.canBypassDnd();
        this.mLockscreenVisibility = notificationChannel.getLockscreenVisibility();
        if (i2 >= 29) {
            this.mCanBubble = notificationChannel.canBubble();
        }
        if (i2 >= 30) {
            this.mImportantConversation = notificationChannel.isImportantConversation();
        }
    }

    NotificationChannelCompat(@NonNull String str, int i2) {
        this.f2413f = true;
        this.f2414g = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.f2417j = 0;
        this.f2408a = (String) Preconditions.checkNotNull(str);
        this.f2410c = i2;
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2415h = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationChannel a() {
        String str;
        String str2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 26) {
            return null;
        }
        NotificationChannel notificationChannel = new NotificationChannel(this.f2408a, this.f2409b, this.f2410c);
        notificationChannel.setDescription(this.f2411d);
        notificationChannel.setGroup(this.f2412e);
        notificationChannel.setShowBadge(this.f2413f);
        notificationChannel.setSound(this.f2414g, this.f2415h);
        notificationChannel.enableLights(this.f2416i);
        notificationChannel.setLightColor(this.f2417j);
        notificationChannel.setVibrationPattern(this.f2419l);
        notificationChannel.enableVibration(this.f2418k);
        if (i2 >= 30 && (str = this.f2420m) != null && (str2 = this.f2421n) != null) {
            notificationChannel.setConversationId(str, str2);
        }
        return notificationChannel;
    }

    public boolean canBubble() {
        return this.mCanBubble;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public boolean canShowBadge() {
        return this.f2413f;
    }

    @Nullable
    public AudioAttributes getAudioAttributes() {
        return this.f2415h;
    }

    @Nullable
    public String getConversationId() {
        return this.f2421n;
    }

    @Nullable
    public String getDescription() {
        return this.f2411d;
    }

    @Nullable
    public String getGroup() {
        return this.f2412e;
    }

    @NonNull
    public String getId() {
        return this.f2408a;
    }

    public int getImportance() {
        return this.f2410c;
    }

    public int getLightColor() {
        return this.f2417j;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    @Nullable
    public CharSequence getName() {
        return this.f2409b;
    }

    @Nullable
    public String getParentChannelId() {
        return this.f2420m;
    }

    @Nullable
    public Uri getSound() {
        return this.f2414g;
    }

    @Nullable
    public long[] getVibrationPattern() {
        return this.f2419l;
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }

    public boolean shouldShowLights() {
        return this.f2416i;
    }

    public boolean shouldVibrate() {
        return this.f2418k;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this.f2408a, this.f2410c).setName(this.f2409b).setDescription(this.f2411d).setGroup(this.f2412e).setShowBadge(this.f2413f).setSound(this.f2414g, this.f2415h).setLightsEnabled(this.f2416i).setLightColor(this.f2417j).setVibrationEnabled(this.f2418k).setVibrationPattern(this.f2419l).setConversationId(this.f2420m, this.f2421n);
    }
}
