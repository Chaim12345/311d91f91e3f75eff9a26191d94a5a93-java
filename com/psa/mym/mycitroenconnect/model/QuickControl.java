package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class QuickControl {
    private int controlImage;
    @NotNull
    private String controlName;

    public QuickControl(@NotNull String controlName, int i2) {
        Intrinsics.checkNotNullParameter(controlName, "controlName");
        this.controlName = controlName;
        this.controlImage = i2;
    }

    public /* synthetic */ QuickControl(String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, i2);
    }

    public static /* synthetic */ QuickControl copy$default(QuickControl quickControl, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = quickControl.controlName;
        }
        if ((i3 & 2) != 0) {
            i2 = quickControl.controlImage;
        }
        return quickControl.copy(str, i2);
    }

    @NotNull
    public final String component1() {
        return this.controlName;
    }

    public final int component2() {
        return this.controlImage;
    }

    @NotNull
    public final QuickControl copy(@NotNull String controlName, int i2) {
        Intrinsics.checkNotNullParameter(controlName, "controlName");
        return new QuickControl(controlName, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof QuickControl) {
            QuickControl quickControl = (QuickControl) obj;
            return Intrinsics.areEqual(this.controlName, quickControl.controlName) && this.controlImage == quickControl.controlImage;
        }
        return false;
    }

    public final int getControlImage() {
        return this.controlImage;
    }

    @NotNull
    public final String getControlName() {
        return this.controlName;
    }

    public int hashCode() {
        return (this.controlName.hashCode() * 31) + Integer.hashCode(this.controlImage);
    }

    public final void setControlImage(int i2) {
        this.controlImage = i2;
    }

    public final void setControlName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.controlName = str;
    }

    @NotNull
    public String toString() {
        return "QuickControl(controlName=" + this.controlName + ", controlImage=" + this.controlImage + ')';
    }
}
