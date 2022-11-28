package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DrivingBehaviourCardData {
    private final int count;
    private final int icon;
    @NotNull
    private final String label;

    public DrivingBehaviourCardData(int i2, int i3, @NotNull String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.icon = i2;
        this.count = i3;
        this.label = label;
    }

    public static /* synthetic */ DrivingBehaviourCardData copy$default(DrivingBehaviourCardData drivingBehaviourCardData, int i2, int i3, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = drivingBehaviourCardData.icon;
        }
        if ((i4 & 2) != 0) {
            i3 = drivingBehaviourCardData.count;
        }
        if ((i4 & 4) != 0) {
            str = drivingBehaviourCardData.label;
        }
        return drivingBehaviourCardData.copy(i2, i3, str);
    }

    public final int component1() {
        return this.icon;
    }

    public final int component2() {
        return this.count;
    }

    @NotNull
    public final String component3() {
        return this.label;
    }

    @NotNull
    public final DrivingBehaviourCardData copy(int i2, int i3, @NotNull String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        return new DrivingBehaviourCardData(i2, i3, label);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DrivingBehaviourCardData) {
            DrivingBehaviourCardData drivingBehaviourCardData = (DrivingBehaviourCardData) obj;
            return this.icon == drivingBehaviourCardData.icon && this.count == drivingBehaviourCardData.count && Intrinsics.areEqual(this.label, drivingBehaviourCardData.label);
        }
        return false;
    }

    public final int getCount() {
        return this.count;
    }

    public final int getIcon() {
        return this.icon;
    }

    @NotNull
    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.icon) * 31) + Integer.hashCode(this.count)) * 31) + this.label.hashCode();
    }

    @NotNull
    public String toString() {
        return "DrivingBehaviourCardData(icon=" + this.icon + ", count=" + this.count + ", label=" + this.label + ')';
    }
}
