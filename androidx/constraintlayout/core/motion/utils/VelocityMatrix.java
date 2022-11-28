package androidx.constraintlayout.core.motion.utils;
/* loaded from: classes.dex */
public class VelocityMatrix {
    private static String TAG = "VelocityMatrix";

    /* renamed from: a  reason: collision with root package name */
    float f1850a;

    /* renamed from: b  reason: collision with root package name */
    float f1851b;

    /* renamed from: c  reason: collision with root package name */
    float f1852c;

    /* renamed from: d  reason: collision with root package name */
    float f1853d;

    /* renamed from: e  reason: collision with root package name */
    float f1854e;

    /* renamed from: f  reason: collision with root package name */
    float f1855f;

    public void applyTransform(float f2, float f3, int i2, int i3, float[] fArr) {
        float f4;
        float f5 = fArr[0];
        float f6 = fArr[1];
        float f7 = (f3 - 0.5f) * 2.0f;
        float f8 = f5 + this.f1852c;
        float f9 = f6 + this.f1853d;
        float f10 = f8 + (this.f1850a * (f2 - 0.5f) * 2.0f);
        float f11 = f9 + (this.f1851b * f7);
        float radians = (float) Math.toRadians(this.f1854e);
        double radians2 = (float) Math.toRadians(this.f1855f);
        double d2 = i3 * f7;
        fArr[0] = f10 + (((float) ((((-i2) * f4) * Math.sin(radians2)) - (Math.cos(radians2) * d2))) * radians);
        fArr[1] = f11 + (radians * ((float) (((i2 * f4) * Math.cos(radians2)) - (d2 * Math.sin(radians2)))));
    }

    public void clear() {
        this.f1854e = 0.0f;
        this.f1853d = 0.0f;
        this.f1852c = 0.0f;
        this.f1851b = 0.0f;
        this.f1850a = 0.0f;
    }

    public void setRotationVelocity(KeyCycleOscillator keyCycleOscillator, float f2) {
        if (keyCycleOscillator != null) {
            this.f1854e = keyCycleOscillator.getSlope(f2);
        }
    }

    public void setRotationVelocity(SplineSet splineSet, float f2) {
        if (splineSet != null) {
            this.f1854e = splineSet.getSlope(f2);
            this.f1855f = splineSet.get(f2);
        }
    }

    public void setScaleVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f2) {
        if (keyCycleOscillator != null) {
            this.f1850a = keyCycleOscillator.getSlope(f2);
        }
        if (keyCycleOscillator2 != null) {
            this.f1851b = keyCycleOscillator2.getSlope(f2);
        }
    }

    public void setScaleVelocity(SplineSet splineSet, SplineSet splineSet2, float f2) {
        if (splineSet != null) {
            this.f1850a = splineSet.getSlope(f2);
        }
        if (splineSet2 != null) {
            this.f1851b = splineSet2.getSlope(f2);
        }
    }

    public void setTranslationVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f2) {
        if (keyCycleOscillator != null) {
            this.f1852c = keyCycleOscillator.getSlope(f2);
        }
        if (keyCycleOscillator2 != null) {
            this.f1853d = keyCycleOscillator2.getSlope(f2);
        }
    }

    public void setTranslationVelocity(SplineSet splineSet, SplineSet splineSet2, float f2) {
        if (splineSet != null) {
            this.f1852c = splineSet.getSlope(f2);
        }
        if (splineSet2 != null) {
            this.f1853d = splineSet2.getSlope(f2);
        }
    }
}
