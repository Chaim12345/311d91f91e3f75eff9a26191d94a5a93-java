package com.psa.mym.mycitroenconnect.model.notification;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NotificationResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("notifications")
    @NotNull
    private ArrayList<Notification> notifications;

    public NotificationResponse() {
        this(0, null, 3, null);
    }

    public NotificationResponse(int i2, @NotNull ArrayList<Notification> notifications) {
        Intrinsics.checkNotNullParameter(notifications, "notifications");
        this.count = i2;
        this.notifications = notifications;
    }

    public /* synthetic */ NotificationResponse(int i2, ArrayList arrayList, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? new ArrayList() : arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NotificationResponse copy$default(NotificationResponse notificationResponse, int i2, ArrayList arrayList, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = notificationResponse.count;
        }
        if ((i3 & 2) != 0) {
            arrayList = notificationResponse.notifications;
        }
        return notificationResponse.copy(i2, arrayList);
    }

    public final int component1() {
        return this.count;
    }

    @NotNull
    public final ArrayList<Notification> component2() {
        return this.notifications;
    }

    @NotNull
    public final NotificationResponse copy(int i2, @NotNull ArrayList<Notification> notifications) {
        Intrinsics.checkNotNullParameter(notifications, "notifications");
        return new NotificationResponse(i2, notifications);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NotificationResponse) {
            NotificationResponse notificationResponse = (NotificationResponse) obj;
            return this.count == notificationResponse.count && Intrinsics.areEqual(this.notifications, notificationResponse.notifications);
        }
        return false;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final ArrayList<Notification> getNotifications() {
        return this.notifications;
    }

    public int hashCode() {
        return (Integer.hashCode(this.count) * 31) + this.notifications.hashCode();
    }

    public final void setCount(int i2) {
        this.count = i2;
    }

    public final void setNotifications(@NotNull ArrayList<Notification> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.notifications = arrayList;
    }

    @NotNull
    public String toString() {
        return "NotificationResponse(count=" + this.count + ", notifications=" + this.notifications + ')';
    }
}
