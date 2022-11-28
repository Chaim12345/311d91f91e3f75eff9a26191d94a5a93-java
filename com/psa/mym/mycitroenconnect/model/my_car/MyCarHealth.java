package com.psa.mym.mycitroenconnect.model.my_car;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyCarHealth {
    @NotNull
    private final String healthStatus;
    @NotNull
    private final String label;

    public MyCarHealth(@NotNull String healthStatus, @NotNull String label) {
        Intrinsics.checkNotNullParameter(healthStatus, "healthStatus");
        Intrinsics.checkNotNullParameter(label, "label");
        this.healthStatus = healthStatus;
        this.label = label;
    }

    public static /* synthetic */ MyCarHealth copy$default(MyCarHealth myCarHealth, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = myCarHealth.healthStatus;
        }
        if ((i2 & 2) != 0) {
            str2 = myCarHealth.label;
        }
        return myCarHealth.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.healthStatus;
    }

    @NotNull
    public final String component2() {
        return this.label;
    }

    @NotNull
    public final MyCarHealth copy(@NotNull String healthStatus, @NotNull String label) {
        Intrinsics.checkNotNullParameter(healthStatus, "healthStatus");
        Intrinsics.checkNotNullParameter(label, "label");
        return new MyCarHealth(healthStatus, label);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MyCarHealth) {
            MyCarHealth myCarHealth = (MyCarHealth) obj;
            return Intrinsics.areEqual(this.healthStatus, myCarHealth.healthStatus) && Intrinsics.areEqual(this.label, myCarHealth.label);
        }
        return false;
    }

    @NotNull
    public final String getHealthStatus() {
        return this.healthStatus;
    }

    @NotNull
    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return (this.healthStatus.hashCode() * 31) + this.label.hashCode();
    }

    @NotNull
    public String toString() {
        return "MyCarHealth(healthStatus=" + this.healthStatus + ", label=" + this.label + ')';
    }
}
