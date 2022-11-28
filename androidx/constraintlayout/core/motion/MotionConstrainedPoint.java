package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.Utils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    public static final boolean DEBUG = false;
    public static final String TAG = "MotionPaths";

    /* renamed from: b  reason: collision with root package name */
    int f1697b;
    private float height;
    private Easing mKeyFrameEasing;
    private float position;
    private float width;
    private float x;
    private float y;
    private float alpha = 1.0f;

    /* renamed from: a  reason: collision with root package name */
    int f1696a = 0;
    private boolean applyElevation = false;
    private float elevation = 0.0f;
    private float rotation = 0.0f;
    private float rotationX = 0.0f;
    public float rotationY = 0.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float translationX = 0.0f;
    private float translationY = 0.0f;
    private float translationZ = 0.0f;
    private int mDrawPath = 0;
    private float mPathRotate = Float.NaN;
    private float mProgress = Float.NaN;
    private int mAnimateRelativeTo = -1;

    /* renamed from: c  reason: collision with root package name */
    LinkedHashMap f1698c = new LinkedHashMap();

    private boolean diff(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(MotionConstrainedPoint motionConstrainedPoint, HashSet hashSet) {
        if (diff(this.alpha, motionConstrainedPoint.alpha)) {
            hashSet.add("alpha");
        }
        if (diff(this.elevation, motionConstrainedPoint.elevation)) {
            hashSet.add("translationZ");
        }
        int i2 = this.f1697b;
        int i3 = motionConstrainedPoint.f1697b;
        if (i2 != i3 && this.f1696a == 0 && (i2 == 4 || i3 == 4)) {
            hashSet.add("alpha");
        }
        if (diff(this.rotation, motionConstrainedPoint.rotation)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.mProgress) || !Float.isNaN(motionConstrainedPoint.mProgress)) {
            hashSet.add("progress");
        }
        if (diff(this.rotationX, motionConstrainedPoint.rotationX)) {
            hashSet.add("rotationX");
        }
        if (diff(this.rotationY, motionConstrainedPoint.rotationY)) {
            hashSet.add("rotationY");
        }
        if (diff(this.mPivotX, motionConstrainedPoint.mPivotX)) {
            hashSet.add("pivotX");
        }
        if (diff(this.mPivotY, motionConstrainedPoint.mPivotY)) {
            hashSet.add("pivotY");
        }
        if (diff(this.scaleX, motionConstrainedPoint.scaleX)) {
            hashSet.add("scaleX");
        }
        if (diff(this.scaleY, motionConstrainedPoint.scaleY)) {
            hashSet.add("scaleY");
        }
        if (diff(this.translationX, motionConstrainedPoint.translationX)) {
            hashSet.add("translationX");
        }
        if (diff(this.translationY, motionConstrainedPoint.translationY)) {
            hashSet.add("translationY");
        }
        if (diff(this.translationZ, motionConstrainedPoint.translationZ)) {
            hashSet.add("translationZ");
        }
        if (diff(this.elevation, motionConstrainedPoint.elevation)) {
            hashSet.add("elevation");
        }
    }

    public void addValues(HashMap<String, SplineSet> hashMap, int i2) {
        String str;
        for (String str2 : hashMap.keySet()) {
            SplineSet splineSet = hashMap.get(str2);
            str2.hashCode();
            char c2 = 65535;
            switch (str2.hashCode()) {
                case -1249320806:
                    if (str2.equals("rotationX")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1249320805:
                    if (str2.equals("rotationY")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1249320804:
                    if (str2.equals("rotationZ")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str2.equals("translationX")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str2.equals("translationY")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str2.equals("translationZ")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str2.equals("progress")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case -987906986:
                    if (str2.equals("pivotX")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case -987906985:
                    if (str2.equals("pivotY")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case -908189618:
                    if (str2.equals("scaleX")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case -908189617:
                    if (str2.equals("scaleY")) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case 92909918:
                    if (str2.equals("alpha")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 803192288:
                    if (str2.equals("pathRotate")) {
                        c2 = '\f';
                        break;
                    }
                    break;
            }
            float f2 = 1.0f;
            float f3 = 0.0f;
            switch (c2) {
                case 0:
                    if (!Float.isNaN(this.rotationX)) {
                        f3 = this.rotationX;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case 1:
                    if (!Float.isNaN(this.rotationY)) {
                        f3 = this.rotationY;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case 2:
                    if (!Float.isNaN(this.rotation)) {
                        f3 = this.rotation;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case 3:
                    if (!Float.isNaN(this.translationX)) {
                        f3 = this.translationX;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case 4:
                    if (!Float.isNaN(this.translationY)) {
                        f3 = this.translationY;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case 5:
                    if (!Float.isNaN(this.translationZ)) {
                        f3 = this.translationZ;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case 6:
                    if (!Float.isNaN(this.mProgress)) {
                        f3 = this.mProgress;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case 7:
                    if (!Float.isNaN(this.mPivotX)) {
                        f3 = this.mPivotX;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case '\b':
                    if (!Float.isNaN(this.mPivotY)) {
                        f3 = this.mPivotY;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                case '\t':
                    if (!Float.isNaN(this.scaleX)) {
                        f2 = this.scaleX;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case '\n':
                    if (!Float.isNaN(this.scaleY)) {
                        f2 = this.scaleY;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 11:
                    if (!Float.isNaN(this.alpha)) {
                        f2 = this.alpha;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case '\f':
                    if (!Float.isNaN(this.mPathRotate)) {
                        f3 = this.mPathRotate;
                    }
                    splineSet.setPoint(i2, f3);
                    break;
                default:
                    if (str2.startsWith("CUSTOM")) {
                        String str3 = str2.split(",")[1];
                        if (!this.f1698c.containsKey(str3)) {
                            break;
                        } else {
                            CustomVariable customVariable = (CustomVariable) this.f1698c.get(str3);
                            if (splineSet instanceof SplineSet.CustomSpline) {
                                ((SplineSet.CustomSpline) splineSet).setPoint(i2, customVariable);
                                break;
                            } else {
                                str = str2 + " ViewSpline not a CustomSet frame = " + i2 + ", value" + customVariable.getValueToInterpolate() + splineSet;
                            }
                        }
                    } else {
                        str = "UNKNOWN spline " + str2;
                    }
                    Utils.loge("MotionPaths", str);
                    break;
            }
        }
    }

    public void applyParameters(MotionWidget motionWidget) {
        this.f1697b = motionWidget.getVisibility();
        this.alpha = motionWidget.getVisibility() != 4 ? 0.0f : motionWidget.getAlpha();
        this.applyElevation = false;
        this.rotation = motionWidget.getRotationZ();
        this.rotationX = motionWidget.getRotationX();
        this.rotationY = motionWidget.getRotationY();
        this.scaleX = motionWidget.getScaleX();
        this.scaleY = motionWidget.getScaleY();
        this.mPivotX = motionWidget.getPivotX();
        this.mPivotY = motionWidget.getPivotY();
        this.translationX = motionWidget.getTranslationX();
        this.translationY = motionWidget.getTranslationY();
        this.translationZ = motionWidget.getTranslationZ();
        for (String str : motionWidget.getCustomAttributeNames()) {
            CustomVariable customAttribute = motionWidget.getCustomAttribute(str);
            if (customAttribute != null && customAttribute.isContinuous()) {
                this.f1698c.put(str, customAttribute);
            }
        }
    }

    void b(float f2, float f3, float f4, float f5) {
        this.x = f2;
        this.y = f3;
        this.width = f4;
        this.height = f5;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.position, motionConstrainedPoint.position);
    }

    public void setState(MotionWidget motionWidget) {
        b(motionWidget.getX(), motionWidget.getY(), motionWidget.getWidth(), motionWidget.getHeight());
        applyParameters(motionWidget);
    }

    public void setState(Rect rect, MotionWidget motionWidget, int i2, float f2) {
        float f3;
        b(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(motionWidget);
        this.mPivotX = Float.NaN;
        this.mPivotY = Float.NaN;
        if (i2 == 1) {
            f3 = f2 - 90.0f;
        } else if (i2 != 2) {
            return;
        } else {
            f3 = f2 + 90.0f;
        }
        this.rotation = f3;
    }
}
