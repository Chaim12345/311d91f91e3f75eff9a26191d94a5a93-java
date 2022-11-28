package com.psa.mym.mycitroenconnect.api.body.dashboard;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RefreshCommandBody {
    @SerializedName("accountId")
    private int accountId;
    @SerializedName("actionType")
    @NotNull
    private String actionType;
    @SerializedName("deviceVinno")
    @NotNull
    private String deviceVinno;

    public RefreshCommandBody() {
        this(0, null, null, 7, null);
    }

    public RefreshCommandBody(int i2, @NotNull String actionType, @NotNull String deviceVinno) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(deviceVinno, "deviceVinno");
        this.accountId = i2;
        this.actionType = actionType;
        this.deviceVinno = deviceVinno;
    }

    public /* synthetic */ RefreshCommandBody(int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? "" : str2);
    }

    public static /* synthetic */ RefreshCommandBody copy$default(RefreshCommandBody refreshCommandBody, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = refreshCommandBody.accountId;
        }
        if ((i3 & 2) != 0) {
            str = refreshCommandBody.actionType;
        }
        if ((i3 & 4) != 0) {
            str2 = refreshCommandBody.deviceVinno;
        }
        return refreshCommandBody.copy(i2, str, str2);
    }

    public final int component1() {
        return this.accountId;
    }

    @NotNull
    public final String component2() {
        return this.actionType;
    }

    @NotNull
    public final String component3() {
        return this.deviceVinno;
    }

    @NotNull
    public final RefreshCommandBody copy(int i2, @NotNull String actionType, @NotNull String deviceVinno) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(deviceVinno, "deviceVinno");
        return new RefreshCommandBody(i2, actionType, deviceVinno);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RefreshCommandBody) {
            RefreshCommandBody refreshCommandBody = (RefreshCommandBody) obj;
            return this.accountId == refreshCommandBody.accountId && Intrinsics.areEqual(this.actionType, refreshCommandBody.actionType) && Intrinsics.areEqual(this.deviceVinno, refreshCommandBody.deviceVinno);
        }
        return false;
    }

    public final int getAccountId() {
        return this.accountId;
    }

    @NotNull
    public final String getActionType() {
        return this.actionType;
    }

    @NotNull
    public final String getDeviceVinno() {
        return this.deviceVinno;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.accountId) * 31) + this.actionType.hashCode()) * 31) + this.deviceVinno.hashCode();
    }

    public final void setAccountId(int i2) {
        this.accountId = i2;
    }

    public final void setActionType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.actionType = str;
    }

    public final void setDeviceVinno(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceVinno = str;
    }

    @NotNull
    public String toString() {
        return "RefreshCommandBody(accountId=" + this.accountId + ", actionType=" + this.actionType + ", deviceVinno=" + this.deviceVinno + ')';
    }
}
