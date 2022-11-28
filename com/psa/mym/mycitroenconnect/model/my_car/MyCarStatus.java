package com.psa.mym.mycitroenconnect.model.my_car;

import androidx.annotation.DrawableRes;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyCarStatus {
    private final int icon;
    @NotNull
    private final String title;
    @NotNull
    private final String unit;
    @NotNull
    private String value;

    public MyCarStatus(@NotNull String title, @NotNull String value, @NotNull String unit, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.title = title;
        this.value = value;
        this.unit = unit;
        this.icon = i2;
    }

    public static /* synthetic */ MyCarStatus copy$default(MyCarStatus myCarStatus, String str, String str2, String str3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = myCarStatus.title;
        }
        if ((i3 & 2) != 0) {
            str2 = myCarStatus.value;
        }
        if ((i3 & 4) != 0) {
            str3 = myCarStatus.unit;
        }
        if ((i3 & 8) != 0) {
            i2 = myCarStatus.icon;
        }
        return myCarStatus.copy(str, str2, str3, i2);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @NotNull
    public final String component2() {
        return this.value;
    }

    @NotNull
    public final String component3() {
        return this.unit;
    }

    public final int component4() {
        return this.icon;
    }

    @NotNull
    public final MyCarStatus copy(@NotNull String title, @NotNull String value, @NotNull String unit, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new MyCarStatus(title, value, unit, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MyCarStatus) {
            MyCarStatus myCarStatus = (MyCarStatus) obj;
            return Intrinsics.areEqual(this.title, myCarStatus.title) && Intrinsics.areEqual(this.value, myCarStatus.value) && Intrinsics.areEqual(this.unit, myCarStatus.unit) && this.icon == myCarStatus.icon;
        }
        return false;
    }

    public final int getIcon() {
        return this.icon;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getUnit() {
        return this.unit;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return (((((this.title.hashCode() * 31) + this.value.hashCode()) * 31) + this.unit.hashCode()) * 31) + Integer.hashCode(this.icon);
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    @NotNull
    public String toString() {
        return "MyCarStatus(title=" + this.title + ", value=" + this.value + ", unit=" + this.unit + ", icon=" + this.icon + ')';
    }
}
