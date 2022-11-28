package com.google.maps.android.geometry;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes2.dex */
public class Point {
    public final double x;
    public final double y;

    public Point(double d2, double d3) {
        this.x = d2;
        this.y = d3;
    }

    public String toString() {
        return "Point{x=" + this.x + ", y=" + this.y + AbstractJsonLexerKt.END_OBJ;
    }
}
