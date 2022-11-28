package com.psa.mym.mycitroenconnect.model.secondary_user;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyCarResponse {
    @NotNull
    private final MyCars myCars;
    @NotNull
    private String screenName;

    public MyCarResponse(@NotNull MyCars myCars, @NotNull String screenName) {
        Intrinsics.checkNotNullParameter(myCars, "myCars");
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        this.myCars = myCars;
        this.screenName = screenName;
    }

    public /* synthetic */ MyCarResponse(MyCars myCars, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(myCars, (i2 & 2) != 0 ? "" : str);
    }

    public static /* synthetic */ MyCarResponse copy$default(MyCarResponse myCarResponse, MyCars myCars, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            myCars = myCarResponse.myCars;
        }
        if ((i2 & 2) != 0) {
            str = myCarResponse.screenName;
        }
        return myCarResponse.copy(myCars, str);
    }

    @NotNull
    public final MyCars component1() {
        return this.myCars;
    }

    @NotNull
    public final String component2() {
        return this.screenName;
    }

    @NotNull
    public final MyCarResponse copy(@NotNull MyCars myCars, @NotNull String screenName) {
        Intrinsics.checkNotNullParameter(myCars, "myCars");
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        return new MyCarResponse(myCars, screenName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MyCarResponse) {
            MyCarResponse myCarResponse = (MyCarResponse) obj;
            return Intrinsics.areEqual(this.myCars, myCarResponse.myCars) && Intrinsics.areEqual(this.screenName, myCarResponse.screenName);
        }
        return false;
    }

    @NotNull
    public final MyCars getMyCars() {
        return this.myCars;
    }

    @NotNull
    public final String getScreenName() {
        return this.screenName;
    }

    public int hashCode() {
        return (this.myCars.hashCode() * 31) + this.screenName.hashCode();
    }

    public final void setScreenName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.screenName = str;
    }

    @NotNull
    public String toString() {
        return "MyCarResponse(myCars=" + this.myCars + ", screenName=" + this.screenName + ')';
    }
}
