package com.psa.mym.mycitroenconnect.model.dashboard;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: classes3.dex */
public final class Coordinate {
    @SerializedName("x")
    private double x;
    @SerializedName("y")
    private double y;

    public Coordinate() {
        this(0.0d, 0.0d, 3, null);
    }

    public Coordinate(double d2, double d3) {
        this.x = d2;
        this.y = d3;
    }

    public /* synthetic */ Coordinate(double d2, double d3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0d : d2, (i2 & 2) != 0 ? 0.0d : d3);
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final void setX(double d2) {
        this.x = d2;
    }

    public final void setY(double d2) {
        this.y = d2;
    }
}
