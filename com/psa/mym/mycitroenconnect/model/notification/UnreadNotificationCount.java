package com.psa.mym.mycitroenconnect.model.notification;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class UnreadNotificationCount {
    @SerializedName("Total Count")
    private final int totalCount;
    @SerializedName("UnRead Count")
    private final int unReadCount;

    public UnreadNotificationCount(int i2, int i3) {
        this.totalCount = i2;
        this.unReadCount = i3;
    }

    public static /* synthetic */ UnreadNotificationCount copy$default(UnreadNotificationCount unreadNotificationCount, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = unreadNotificationCount.totalCount;
        }
        if ((i4 & 2) != 0) {
            i3 = unreadNotificationCount.unReadCount;
        }
        return unreadNotificationCount.copy(i2, i3);
    }

    public final int component1() {
        return this.totalCount;
    }

    public final int component2() {
        return this.unReadCount;
    }

    @NotNull
    public final UnreadNotificationCount copy(int i2, int i3) {
        return new UnreadNotificationCount(i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UnreadNotificationCount) {
            UnreadNotificationCount unreadNotificationCount = (UnreadNotificationCount) obj;
            return this.totalCount == unreadNotificationCount.totalCount && this.unReadCount == unreadNotificationCount.unReadCount;
        }
        return false;
    }

    public final int getTotalCount() {
        return this.totalCount;
    }

    public final int getUnReadCount() {
        return this.unReadCount;
    }

    public int hashCode() {
        return (Integer.hashCode(this.totalCount) * 31) + Integer.hashCode(this.unReadCount);
    }

    @NotNull
    public String toString() {
        return "UnreadNotificationCount(totalCount=" + this.totalCount + ", unReadCount=" + this.unReadCount + ')';
    }
}
