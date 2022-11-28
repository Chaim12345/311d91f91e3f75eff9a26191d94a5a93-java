package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import org.apache.http.message.TokenParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    public static final boolean DEBUG = false;
    public static final String TAG = "MotionPaths";

    /* renamed from: b  reason: collision with root package name */
    int f2066b;
    private float height;
    private Easing mKeyFrameEasing;
    private float position;
    private float width;
    private float x;
    private float y;
    private float alpha = 1.0f;

    /* renamed from: a  reason: collision with root package name */
    int f2065a = 0;
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
    LinkedHashMap f2067c = new LinkedHashMap();

    private boolean diff(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(MotionConstrainedPoint motionConstrainedPoint, HashSet hashSet) {
        if (diff(this.alpha, motionConstrainedPoint.alpha)) {
            hashSet.add("alpha");
        }
        if (diff(this.elevation, motionConstrainedPoint.elevation)) {
            hashSet.add("elevation");
        }
        int i2 = this.f2066b;
        int i3 = motionConstrainedPoint.f2066b;
        if (i2 != i3 && this.f2065a == 0 && (i2 == 0 || i3 == 0)) {
            hashSet.add("alpha");
        }
        if (diff(this.rotation, motionConstrainedPoint.rotation)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add("transitionPathRotate");
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
            hashSet.add(Key.PIVOT_X);
        }
        if (diff(this.mPivotY, motionConstrainedPoint.mPivotY)) {
            hashSet.add(Key.PIVOT_Y);
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
    }

    public void addValues(HashMap<String, ViewSpline> hashMap, int i2) {
        String str;
        for (String str2 : hashMap.keySet()) {
            ViewSpline viewSpline = hashMap.get(str2);
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
                case -1225497657:
                    if (str2.equals("translationX")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str2.equals("translationY")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str2.equals("translationZ")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str2.equals("progress")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -908189618:
                    if (str2.equals("scaleX")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case -908189617:
                    if (str2.equals("scaleY")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case -760884510:
                    if (str2.equals(Key.PIVOT_X)) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case -760884509:
                    if (str2.equals(Key.PIVOT_Y)) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case -40300674:
                    if (str2.equals(Key.ROTATION)) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case -4379043:
                    if (str2.equals("elevation")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 37232917:
                    if (str2.equals("transitionPathRotate")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 92909918:
                    if (str2.equals("alpha")) {
                        c2 = TokenParser.CR;
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
                    viewSpline.setPoint(i2, f3);
                    break;
                case 1:
                    if (!Float.isNaN(this.rotationY)) {
                        f3 = this.rotationY;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case 2:
                    if (!Float.isNaN(this.translationX)) {
                        f3 = this.translationX;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case 3:
                    if (!Float.isNaN(this.translationY)) {
                        f3 = this.translationY;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case 4:
                    if (!Float.isNaN(this.translationZ)) {
                        f3 = this.translationZ;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case 5:
                    if (!Float.isNaN(this.mProgress)) {
                        f3 = this.mProgress;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case 6:
                    if (!Float.isNaN(this.scaleX)) {
                        f2 = this.scaleX;
                    }
                    viewSpline.setPoint(i2, f2);
                    break;
                case 7:
                    if (!Float.isNaN(this.scaleY)) {
                        f2 = this.scaleY;
                    }
                    viewSpline.setPoint(i2, f2);
                    break;
                case '\b':
                    if (!Float.isNaN(this.mPivotX)) {
                        f3 = this.mPivotX;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case '\t':
                    if (!Float.isNaN(this.mPivotY)) {
                        f3 = this.mPivotY;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case '\n':
                    if (!Float.isNaN(this.rotation)) {
                        f3 = this.rotation;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case 11:
                    if (!Float.isNaN(this.elevation)) {
                        f3 = this.elevation;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case '\f':
                    if (!Float.isNaN(this.mPathRotate)) {
                        f3 = this.mPathRotate;
                    }
                    viewSpline.setPoint(i2, f3);
                    break;
                case '\r':
                    if (!Float.isNaN(this.alpha)) {
                        f2 = this.alpha;
                    }
                    viewSpline.setPoint(i2, f2);
                    break;
                default:
                    if (str2.startsWith("CUSTOM")) {
                        String str3 = str2.split(",")[1];
                        if (!this.f2067c.containsKey(str3)) {
                            break;
                        } else {
                            ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2067c.get(str3);
                            if (viewSpline instanceof ViewSpline.CustomSet) {
                                ((ViewSpline.CustomSet) viewSpline).setPoint(i2, constraintAttribute);
                                break;
                            } else {
                                str = str2 + " ViewSpline not a CustomSet frame = " + i2 + ", value" + constraintAttribute.getValueToInterpolate() + viewSpline;
                            }
                        }
                    } else {
                        str = "UNKNOWN spline " + str2;
                    }
                    Log.e("MotionPaths", str);
                    break;
            }
        }
    }

    public void applyParameters(View view) {
        this.f2066b = view.getVisibility();
        this.alpha = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.applyElevation = false;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            this.elevation = view.getElevation();
        }
        this.rotation = view.getRotation();
        this.rotationX = view.getRotationX();
        this.rotationY = view.getRotationY();
        this.scaleX = view.getScaleX();
        this.scaleY = view.getScaleY();
        this.mPivotX = view.getPivotX();
        this.mPivotY = view.getPivotY();
        this.translationX = view.getTranslationX();
        this.translationY = view.getTranslationY();
        if (i2 >= 21) {
            this.translationZ = view.getTranslationZ();
        }
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        ConstraintSet.PropertySet propertySet = constraint.propertySet;
        int i2 = propertySet.mVisibilityMode;
        this.f2065a = i2;
        int i3 = propertySet.visibility;
        this.f2066b = i3;
        this.alpha = (i3 == 0 || i2 != 0) ? propertySet.alpha : 0.0f;
        ConstraintSet.Transform transform = constraint.transform;
        this.applyElevation = transform.applyElevation;
        this.elevation = transform.elevation;
        this.rotation = transform.rotation;
        this.rotationX = transform.rotationX;
        this.rotationY = transform.rotationY;
        this.scaleX = transform.scaleX;
        this.scaleY = transform.scaleY;
        this.mPivotX = transform.transformPivotX;
        this.mPivotY = transform.transformPivotY;
        this.translationX = transform.translationX;
        this.translationY = transform.translationY;
        this.translationZ = transform.translationZ;
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.mPathRotate = motion.mPathRotate;
        this.mDrawPath = motion.mDrawPath;
        this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
        this.mProgress = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.isContinuous()) {
                this.f2067c.put(str, constraintAttribute);
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

    public void setState(Rect rect, View view, int i2, float f2) {
        float f3;
        b(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(view);
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

    public void setState(Rect rect, ConstraintSet constraintSet, int i2, int i3) {
        float f2;
        b(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(constraintSet.getParameters(i3));
        float f3 = 90.0f;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return;
                    }
                }
            }
            f2 = this.rotation + 90.0f;
            this.rotation = f2;
            if (f2 > 180.0f) {
                f3 = 360.0f;
                this.rotation = f2 - f3;
            }
            return;
        }
        f2 = this.rotation;
        this.rotation = f2 - f3;
    }

    public void setState(View view) {
        b(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        applyParameters(view);
    }
}
