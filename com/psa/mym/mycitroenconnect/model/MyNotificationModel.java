package com.psa.mym.mycitroenconnect.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
@Parcelize
/* loaded from: classes3.dex */
public final class MyNotificationModel implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<MyNotificationModel> CREATOR = new Creator();
    @NotNull
    private String alertName;
    @NotNull
    private String alertState;
    @NotNull
    private String alertTime;
    @NotNull
    private String body;
    @NotNull
    private String notificationId;
    @NotNull
    private String priority;
    @NotNull
    private String title;
    @NotNull
    private String vehicleId;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<MyNotificationModel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final MyNotificationModel createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new MyNotificationModel(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final MyNotificationModel[] newArray(int i2) {
            return new MyNotificationModel[i2];
        }
    }

    public MyNotificationModel(@NotNull String alertState, @NotNull String title, @NotNull String body, @NotNull String alertName, @NotNull String alertTime, @NotNull String vehicleId, @NotNull String priority, @NotNull String notificationId) {
        Intrinsics.checkNotNullParameter(alertState, "alertState");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(alertName, "alertName");
        Intrinsics.checkNotNullParameter(alertTime, "alertTime");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        Intrinsics.checkNotNullParameter(priority, "priority");
        Intrinsics.checkNotNullParameter(notificationId, "notificationId");
        this.alertState = alertState;
        this.title = title;
        this.body = body;
        this.alertName = alertName;
        this.alertTime = alertTime;
        this.vehicleId = vehicleId;
        this.priority = priority;
        this.notificationId = notificationId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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
    public final String getAlertTime() {
        return this.alertTime;
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

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public final void setAlertName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertName = str;
    }

    public final void setAlertState(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertState = str;
    }

    public final void setAlertTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertTime = str;
    }

    public final void setBody(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.body = str;
    }

    public final void setNotificationId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.notificationId = str;
    }

    public final void setPriority(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.priority = str;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setVehicleId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.alertState);
        out.writeString(this.title);
        out.writeString(this.body);
        out.writeString(this.alertName);
        out.writeString(this.alertTime);
        out.writeString(this.vehicleId);
        out.writeString(this.priority);
        out.writeString(this.notificationId);
    }
}
