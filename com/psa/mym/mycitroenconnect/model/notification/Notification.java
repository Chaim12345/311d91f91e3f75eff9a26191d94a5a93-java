package com.psa.mym.mycitroenconnect.model.notification;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Notification {
    @SerializedName("alertName")
    @NotNull
    private String alertName;
    @SerializedName("alertState")
    @NotNull
    private String alertState;
    @SerializedName("body")
    @NotNull
    private String body;
    private transient boolean isExpanded;
    @SerializedName("notificationId")
    @NotNull
    private String notificationId;
    @SerializedName(LogFactory.PRIORITY_KEY)
    @NotNull
    private String priority;
    @SerializedName("read")
    private boolean read;
    @SerializedName("signalTimeStamp")
    private long signalTimeStamp;
    @SerializedName("title")
    @NotNull
    private String title;
    @SerializedName("vinNo")
    @NotNull
    private String vinNo;

    public Notification() {
        this(null, null, 0L, null, null, null, null, null, false, false, 1023, null);
    }

    public Notification(@NotNull String notificationId, @NotNull String vinNo, long j2, @NotNull String alertName, @NotNull String alertState, @NotNull String title, @NotNull String body, @NotNull String priority, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(notificationId, "notificationId");
        Intrinsics.checkNotNullParameter(vinNo, "vinNo");
        Intrinsics.checkNotNullParameter(alertName, "alertName");
        Intrinsics.checkNotNullParameter(alertState, "alertState");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(priority, "priority");
        this.notificationId = notificationId;
        this.vinNo = vinNo;
        this.signalTimeStamp = j2;
        this.alertName = alertName;
        this.alertState = alertState;
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.read = z;
        this.isExpanded = z2;
    }

    public /* synthetic */ Notification(String str, String str2, long j2, String str3, String str4, String str5, String str6, String str7, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? 0L : j2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) != 0 ? "" : str6, (i2 & 128) == 0 ? str7 : "", (i2 & 256) != 0 ? false : z, (i2 & 512) == 0 ? z2 : false);
    }

    @NotNull
    public final String component1() {
        return this.notificationId;
    }

    public final boolean component10() {
        return this.isExpanded;
    }

    @NotNull
    public final String component2() {
        return this.vinNo;
    }

    public final long component3() {
        return this.signalTimeStamp;
    }

    @NotNull
    public final String component4() {
        return this.alertName;
    }

    @NotNull
    public final String component5() {
        return this.alertState;
    }

    @NotNull
    public final String component6() {
        return this.title;
    }

    @NotNull
    public final String component7() {
        return this.body;
    }

    @NotNull
    public final String component8() {
        return this.priority;
    }

    public final boolean component9() {
        return this.read;
    }

    @NotNull
    public final Notification copy(@NotNull String notificationId, @NotNull String vinNo, long j2, @NotNull String alertName, @NotNull String alertState, @NotNull String title, @NotNull String body, @NotNull String priority, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(notificationId, "notificationId");
        Intrinsics.checkNotNullParameter(vinNo, "vinNo");
        Intrinsics.checkNotNullParameter(alertName, "alertName");
        Intrinsics.checkNotNullParameter(alertState, "alertState");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(priority, "priority");
        return new Notification(notificationId, vinNo, j2, alertName, alertState, title, body, priority, z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Notification) {
            Notification notification = (Notification) obj;
            return Intrinsics.areEqual(this.notificationId, notification.notificationId) && Intrinsics.areEqual(this.vinNo, notification.vinNo) && this.signalTimeStamp == notification.signalTimeStamp && Intrinsics.areEqual(this.alertName, notification.alertName) && Intrinsics.areEqual(this.alertState, notification.alertState) && Intrinsics.areEqual(this.title, notification.title) && Intrinsics.areEqual(this.body, notification.body) && Intrinsics.areEqual(this.priority, notification.priority) && this.read == notification.read && this.isExpanded == notification.isExpanded;
        }
        return false;
    }

    @NotNull
    public final String getAlertName() {
        return this.alertName;
    }

    @NotNull
    public final String getAlertState() {
        return this.alertState;
    }

    @NotNull
    public final String getBody() {
        return this.body;
    }

    @NotNull
    public final String getNotificationId() {
        return this.notificationId;
    }

    @NotNull
    public final String getPriority() {
        return this.priority;
    }

    public final boolean getRead() {
        return this.read;
    }

    public final long getSignalTimeStamp() {
        return this.signalTimeStamp;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getVinNo() {
        return this.vinNo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((((((((this.notificationId.hashCode() * 31) + this.vinNo.hashCode()) * 31) + Long.hashCode(this.signalTimeStamp)) * 31) + this.alertName.hashCode()) * 31) + this.alertState.hashCode()) * 31) + this.title.hashCode()) * 31) + this.body.hashCode()) * 31) + this.priority.hashCode()) * 31;
        boolean z = this.read;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        boolean z2 = this.isExpanded;
        return i3 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public final boolean isExpanded() {
        return this.isExpanded;
    }

    public final void setAlertName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertName = str;
    }

    public final void setAlertState(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertState = str;
    }

    public final void setBody(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.body = str;
    }

    public final void setExpanded(boolean z) {
        this.isExpanded = z;
    }

    public final void setNotificationId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.notificationId = str;
    }

    public final void setPriority(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.priority = str;
    }

    public final void setRead(boolean z) {
        this.read = z;
    }

    public final void setSignalTimeStamp(long j2) {
        this.signalTimeStamp = j2;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setVinNo(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vinNo = str;
    }

    @NotNull
    public String toString() {
        return "Notification(notificationId=" + this.notificationId + ", vinNo=" + this.vinNo + ", signalTimeStamp=" + this.signalTimeStamp + ", alertName=" + this.alertName + ", alertState=" + this.alertState + ", title=" + this.title + ", body=" + this.body + ", priority=" + this.priority + ", read=" + this.read + ", isExpanded=" + this.isExpanded + ')';
    }
}
