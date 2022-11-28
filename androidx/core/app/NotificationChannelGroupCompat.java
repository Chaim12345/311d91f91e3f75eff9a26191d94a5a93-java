package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class NotificationChannelGroupCompat {

    /* renamed from: a  reason: collision with root package name */
    final String f2422a;

    /* renamed from: b  reason: collision with root package name */
    CharSequence f2423b;

    /* renamed from: c  reason: collision with root package name */
    String f2424c;
    private boolean mBlocked;
    private List<NotificationChannelCompat> mChannels;

    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        final NotificationChannelGroupCompat f2425a;

        public Builder(@NonNull String str) {
            this.f2425a = new NotificationChannelGroupCompat(str);
        }

        @NonNull
        public NotificationChannelGroupCompat build() {
            return this.f2425a;
        }

        @NonNull
        public Builder setDescription(@Nullable String str) {
            this.f2425a.f2424c = str;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.f2425a.f2423b = charSequence;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(28)
    public NotificationChannelGroupCompat(@NonNull NotificationChannelGroup notificationChannelGroup) {
        this(notificationChannelGroup, Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(26)
    public NotificationChannelGroupCompat(@NonNull NotificationChannelGroup notificationChannelGroup, @NonNull List list) {
        this(notificationChannelGroup.getId());
        List<NotificationChannelCompat> channelsCompat;
        this.f2423b = notificationChannelGroup.getName();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f2424c = notificationChannelGroup.getDescription();
        }
        if (i2 >= 28) {
            this.mBlocked = notificationChannelGroup.isBlocked();
            channelsCompat = getChannelsCompat(notificationChannelGroup.getChannels());
        } else {
            channelsCompat = getChannelsCompat(list);
        }
        this.mChannels = channelsCompat;
    }

    NotificationChannelGroupCompat(@NonNull String str) {
        this.mChannels = Collections.emptyList();
        this.f2422a = (String) Preconditions.checkNotNull(str);
    }

    @RequiresApi(26)
    private List<NotificationChannelCompat> getChannelsCompat(List<NotificationChannel> list) {
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel notificationChannel : list) {
            if (this.f2422a.equals(notificationChannel.getGroup())) {
                arrayList.add(new NotificationChannelCompat(notificationChannel));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationChannelGroup a() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 26) {
            return null;
        }
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(this.f2422a, this.f2423b);
        if (i2 >= 28) {
            notificationChannelGroup.setDescription(this.f2424c);
        }
        return notificationChannelGroup;
    }

    @NonNull
    public List<NotificationChannelCompat> getChannels() {
        return this.mChannels;
    }

    @Nullable
    public String getDescription() {
        return this.f2424c;
    }

    @NonNull
    public String getId() {
        return this.f2422a;
    }

    @Nullable
    public CharSequence getName() {
        return this.f2423b;
    }

    public boolean isBlocked() {
        return this.mBlocked;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this.f2422a).setName(this.f2423b).setDescription(this.f2424c);
    }
}
