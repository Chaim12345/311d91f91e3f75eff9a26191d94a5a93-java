package com.google.maps.android;
/* loaded from: classes2.dex */
class MathUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static double a(double d2) {
        return Math.asin(Math.sqrt(d2)) * 2.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double b(double d2, double d3, double d4) {
        return d2 < d3 ? d3 : d2 > d4 ? d4 : d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double c(double d2) {
        double sin = Math.sin(d2 * 0.5d);
        return sin * sin;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double d(double d2, double d3, double d4) {
        return c(d2 - d3) + (c(d4) * Math.cos(d2) * Math.cos(d3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double e(double d2) {
        double d3 = d2 * d2;
        return (d3 / (Math.sqrt(1.0d - d3) + 1.0d)) * 0.5d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double f(double d2) {
        return (Math.atan(Math.exp(d2)) * 2.0d) - 1.5707963267948966d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double g(double d2) {
        return Math.log(Math.tan((d2 * 0.5d) + 0.7853981633974483d));
    }

    static double h(double d2, double d3) {
        return ((d2 % d3) + d3) % d3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double i(double d2) {
        return Math.sqrt(d2 * (1.0d - d2)) * 2.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double j(double d2, double d3) {
        double sqrt = Math.sqrt((1.0d - d2) * d2);
        double sqrt2 = Math.sqrt((1.0d - d3) * d3);
        return ((sqrt + sqrt2) - (((sqrt * d3) + (sqrt2 * d2)) * 2.0d)) * 2.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double k(double d2, double d3, double d4) {
        return (d2 < d3 || d2 >= d4) ? h(d2 - d3, d4 - d3) + d3 : d2;
    }
}
