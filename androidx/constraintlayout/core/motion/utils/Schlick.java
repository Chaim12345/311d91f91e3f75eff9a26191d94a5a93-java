package androidx.constraintlayout.core.motion.utils;
/* loaded from: classes.dex */
public class Schlick extends Easing {
    private static final boolean DEBUG = false;

    /* renamed from: c  reason: collision with root package name */
    double f1805c;

    /* renamed from: d  reason: collision with root package name */
    double f1806d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Schlick(String str) {
        this.f1752a = str;
        int indexOf = str.indexOf(40);
        int indexOf2 = str.indexOf(44, indexOf);
        this.f1805c = Double.parseDouble(str.substring(indexOf + 1, indexOf2).trim());
        int i2 = indexOf2 + 1;
        this.f1806d = Double.parseDouble(str.substring(i2, str.indexOf(44, i2)).trim());
    }

    private double dfunc(double d2) {
        double d3 = this.f1806d;
        if (d2 < d3) {
            double d4 = this.f1805c;
            return ((d4 * d3) * d3) / ((((d3 - d2) * d4) + d2) * ((d4 * (d3 - d2)) + d2));
        }
        double d5 = this.f1805c;
        return (((d3 - 1.0d) * d5) * (d3 - 1.0d)) / (((((-d5) * (d3 - d2)) - d2) + 1.0d) * ((((-d5) * (d3 - d2)) - d2) + 1.0d));
    }

    private double func(double d2) {
        double d3 = this.f1806d;
        return d2 < d3 ? (d3 * d2) / (d2 + (this.f1805c * (d3 - d2))) : ((1.0d - d3) * (d2 - 1.0d)) / ((1.0d - d2) - (this.f1805c * (d3 - d2)));
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double get(double d2) {
        return func(d2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double getDiff(double d2) {
        return dfunc(d2);
    }
}
