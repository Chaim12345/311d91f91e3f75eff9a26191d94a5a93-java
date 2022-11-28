package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TimeZone {
    @SerializedName("Code")
    @NotNull
    private final String code;
    @SerializedName("GmtOffset")
    private final double gmtOffset;
    @SerializedName("IsDaylightSaving")
    private final boolean isDaylightSaving;
    @SerializedName("Name")
    @NotNull
    private final String name;
    @SerializedName("NextOffsetChange")
    @NotNull
    private final Object nextOffsetChange;

    public TimeZone(@NotNull String code, double d2, boolean z, @NotNull String name, @NotNull Object nextOffsetChange) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(nextOffsetChange, "nextOffsetChange");
        this.code = code;
        this.gmtOffset = d2;
        this.isDaylightSaving = z;
        this.name = name;
        this.nextOffsetChange = nextOffsetChange;
    }

    public static /* synthetic */ TimeZone copy$default(TimeZone timeZone, String str, double d2, boolean z, String str2, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            str = timeZone.code;
        }
        if ((i2 & 2) != 0) {
            d2 = timeZone.gmtOffset;
        }
        double d3 = d2;
        if ((i2 & 4) != 0) {
            z = timeZone.isDaylightSaving;
        }
        boolean z2 = z;
        if ((i2 & 8) != 0) {
            str2 = timeZone.name;
        }
        String str3 = str2;
        if ((i2 & 16) != 0) {
            obj = timeZone.nextOffsetChange;
        }
        return timeZone.copy(str, d3, z2, str3, obj);
    }

    @NotNull
    public final String component1() {
        return this.code;
    }

    public final double component2() {
        return this.gmtOffset;
    }

    public final boolean component3() {
        return this.isDaylightSaving;
    }

    @NotNull
    public final String component4() {
        return this.name;
    }

    @NotNull
    public final Object component5() {
        return this.nextOffsetChange;
    }

    @NotNull
    public final TimeZone copy(@NotNull String code, double d2, boolean z, @NotNull String name, @NotNull Object nextOffsetChange) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(nextOffsetChange, "nextOffsetChange");
        return new TimeZone(code, d2, z, name, nextOffsetChange);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TimeZone) {
            TimeZone timeZone = (TimeZone) obj;
            return Intrinsics.areEqual(this.code, timeZone.code) && Intrinsics.areEqual((Object) Double.valueOf(this.gmtOffset), (Object) Double.valueOf(timeZone.gmtOffset)) && this.isDaylightSaving == timeZone.isDaylightSaving && Intrinsics.areEqual(this.name, timeZone.name) && Intrinsics.areEqual(this.nextOffsetChange, timeZone.nextOffsetChange);
        }
        return false;
    }

    @NotNull
    public final String getCode() {
        return this.code;
    }

    public final double getGmtOffset() {
        return this.gmtOffset;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Object getNextOffsetChange() {
        return this.nextOffsetChange;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((this.code.hashCode() * 31) + Double.hashCode(this.gmtOffset)) * 31;
        boolean z = this.isDaylightSaving;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return ((((hashCode + i2) * 31) + this.name.hashCode()) * 31) + this.nextOffsetChange.hashCode();
    }

    public final boolean isDaylightSaving() {
        return this.isDaylightSaving;
    }

    @NotNull
    public String toString() {
        return "TimeZone(code=" + this.code + ", gmtOffset=" + this.gmtOffset + ", isDaylightSaving=" + this.isDaylightSaving + ", name=" + this.name + ", nextOffsetChange=" + this.nextOffsetChange + ')';
    }
}
