package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NavItem {
    private boolean isTitle;
    private int menuDrawable;
    @NotNull
    private String menuName;

    public NavItem(@NotNull String menuName, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        this.menuName = menuName;
        this.menuDrawable = i2;
        this.isTitle = z;
    }

    public /* synthetic */ NavItem(String str, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, i2, (i3 & 4) != 0 ? false : z);
    }

    public static /* synthetic */ NavItem copy$default(NavItem navItem, String str, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = navItem.menuName;
        }
        if ((i3 & 2) != 0) {
            i2 = navItem.menuDrawable;
        }
        if ((i3 & 4) != 0) {
            z = navItem.isTitle;
        }
        return navItem.copy(str, i2, z);
    }

    @NotNull
    public final String component1() {
        return this.menuName;
    }

    public final int component2() {
        return this.menuDrawable;
    }

    public final boolean component3() {
        return this.isTitle;
    }

    @NotNull
    public final NavItem copy(@NotNull String menuName, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        return new NavItem(menuName, i2, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NavItem) {
            NavItem navItem = (NavItem) obj;
            return Intrinsics.areEqual(this.menuName, navItem.menuName) && this.menuDrawable == navItem.menuDrawable && this.isTitle == navItem.isTitle;
        }
        return false;
    }

    public final int getMenuDrawable() {
        return this.menuDrawable;
    }

    @NotNull
    public final String getMenuName() {
        return this.menuName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((this.menuName.hashCode() * 31) + Integer.hashCode(this.menuDrawable)) * 31;
        boolean z = this.isTitle;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode + i2;
    }

    public final boolean isTitle() {
        return this.isTitle;
    }

    public final void setMenuDrawable(int i2) {
        this.menuDrawable = i2;
    }

    public final void setMenuName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.menuName = str;
    }

    public final void setTitle(boolean z) {
        this.isTitle = z;
    }

    @NotNull
    public String toString() {
        return "NavItem(menuName=" + this.menuName + ", menuDrawable=" + this.menuDrawable + ", isTitle=" + this.isTitle + ')';
    }
}
