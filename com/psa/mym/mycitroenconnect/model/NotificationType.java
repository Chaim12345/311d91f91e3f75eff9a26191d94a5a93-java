package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NotificationType {
    private boolean isSelected;
    @NotNull
    private final String notificationType;

    public NotificationType(@NotNull String notificationType, boolean z) {
        Intrinsics.checkNotNullParameter(notificationType, "notificationType");
        this.notificationType = notificationType;
        this.isSelected = z;
    }

    public static /* synthetic */ NotificationType copy$default(NotificationType notificationType, String str, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = notificationType.notificationType;
        }
        if ((i2 & 2) != 0) {
            z = notificationType.isSelected;
        }
        return notificationType.copy(str, z);
    }

    @NotNull
    public final String component1() {
        return this.notificationType;
    }

    public final boolean component2() {
        return this.isSelected;
    }

    @NotNull
    public final NotificationType copy(@NotNull String notificationType, boolean z) {
        Intrinsics.checkNotNullParameter(notificationType, "notificationType");
        return new NotificationType(notificationType, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NotificationType) {
            NotificationType notificationType = (NotificationType) obj;
            return Intrinsics.areEqual(this.notificationType, notificationType.notificationType) && this.isSelected == notificationType.isSelected;
        }
        return false;
    }

    @NotNull
    public final String getNotificationType() {
        return this.notificationType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.notificationType.hashCode() * 31;
        boolean z = this.isSelected;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode + i2;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    @NotNull
    public String toString() {
        return "NotificationType(notificationType=" + this.notificationType + ", isSelected=" + this.isSelected + ')';
    }
}
