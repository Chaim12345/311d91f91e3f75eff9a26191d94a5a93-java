package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Set;
/* loaded from: classes.dex */
public class MotionWidget implements TypedValues {
    public static final int FILL_PARENT = -1;
    public static final int GONE_UNSET = Integer.MIN_VALUE;
    private static final int INTERNAL_MATCH_CONSTRAINT = -3;
    private static final int INTERNAL_MATCH_PARENT = -1;
    private static final int INTERNAL_WRAP_CONTENT = -2;
    private static final int INTERNAL_WRAP_CONTENT_CONSTRAINED = -4;
    public static final int INVISIBLE = 0;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int MATCH_PARENT = -1;
    public static final int PARENT_ID = 0;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    public static final int UNSET = -1;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 4;
    public static final int WRAP_CONTENT = -2;

    /* renamed from: a  reason: collision with root package name */
    WidgetFrame f1718a;

    /* renamed from: b  reason: collision with root package name */
    Motion f1719b;

    /* renamed from: c  reason: collision with root package name */
    PropertySet f1720c;

    /* renamed from: d  reason: collision with root package name */
    float f1721d;
    private float mProgress;

    /* loaded from: classes.dex */
    public static class Motion {
        private static final int INTERPOLATOR_REFERENCE_ID = -2;
        private static final int INTERPOLATOR_UNDEFINED = -3;
        private static final int SPLINE_STRING = -1;
        public int mAnimateRelativeTo = -1;
        public int mAnimateCircleAngleTo = 0;
        public String mTransitionEasing = null;
        public int mPathMotionArc = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPolarRelativeTo = -1;
        public float mPathRotate = Float.NaN;
        public float mQuantizeMotionPhase = Float.NaN;
        public int mQuantizeMotionSteps = -1;
        public String mQuantizeInterpolatorString = null;
        public int mQuantizeInterpolatorType = -3;
        public int mQuantizeInterpolatorID = -1;
    }

    /* loaded from: classes.dex */
    public static class PropertySet {
        public int visibility = 4;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;
    }

    public MotionWidget() {
        this.f1718a = new WidgetFrame();
        this.f1719b = new Motion();
        this.f1720c = new PropertySet();
    }

    public MotionWidget(WidgetFrame widgetFrame) {
        this.f1718a = new WidgetFrame();
        this.f1719b = new Motion();
        this.f1720c = new PropertySet();
        this.f1718a = widgetFrame;
    }

    public MotionWidget findViewById(int i2) {
        return null;
    }

    public float getAlpha() {
        return this.f1720c.alpha;
    }

    public int getBottom() {
        return this.f1718a.bottom;
    }

    public CustomVariable getCustomAttribute(String str) {
        return this.f1718a.getCustomAttribute(str);
    }

    public Set<String> getCustomAttributeNames() {
        return this.f1718a.getCustomAttributeNames();
    }

    public int getHeight() {
        WidgetFrame widgetFrame = this.f1718a;
        return widgetFrame.bottom - widgetFrame.top;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        int id = TypedValues.Attributes.getId(str);
        return id != -1 ? id : TypedValues.Motion.getId(str);
    }

    public int getLeft() {
        return this.f1718a.left;
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public MotionWidget getParent() {
        return null;
    }

    public float getPivotX() {
        return this.f1718a.pivotX;
    }

    public float getPivotY() {
        return this.f1718a.pivotY;
    }

    public int getRight() {
        return this.f1718a.right;
    }

    public float getRotationX() {
        return this.f1718a.rotationX;
    }

    public float getRotationY() {
        return this.f1718a.rotationY;
    }

    public float getRotationZ() {
        return this.f1718a.rotationZ;
    }

    public float getScaleX() {
        return this.f1718a.scaleX;
    }

    public float getScaleY() {
        return this.f1718a.scaleY;
    }

    public int getTop() {
        return this.f1718a.top;
    }

    public float getTranslationX() {
        return this.f1718a.translationX;
    }

    public float getTranslationY() {
        return this.f1718a.translationY;
    }

    public float getTranslationZ() {
        return this.f1718a.translationZ;
    }

    public float getValueAttributes(int i2) {
        switch (i2) {
            case 303:
                return this.f1718a.alpha;
            case 304:
                return this.f1718a.translationX;
            case 305:
                return this.f1718a.translationY;
            case 306:
                return this.f1718a.translationZ;
            case 307:
            default:
                return Float.NaN;
            case 308:
                return this.f1718a.rotationX;
            case 309:
                return this.f1718a.rotationY;
            case 310:
                return this.f1718a.rotationZ;
            case 311:
                return this.f1718a.scaleX;
            case 312:
                return this.f1718a.scaleY;
            case 313:
                return this.f1718a.pivotX;
            case 314:
                return this.f1718a.pivotY;
            case 315:
                return this.mProgress;
            case TypedValues.Attributes.TYPE_PATH_ROTATE /* 316 */:
                return this.f1721d;
        }
    }

    public int getVisibility() {
        return this.f1720c.visibility;
    }

    public WidgetFrame getWidgetFrame() {
        return this.f1718a;
    }

    public int getWidth() {
        WidgetFrame widgetFrame = this.f1718a;
        return widgetFrame.right - widgetFrame.left;
    }

    public int getX() {
        return this.f1718a.left;
    }

    public int getY() {
        return this.f1718a.top;
    }

    public void layout(int i2, int i3, int i4, int i5) {
        setBounds(i2, i3, i4, i5);
    }

    public void setBounds(int i2, int i3, int i4, int i5) {
        if (this.f1718a == null) {
            this.f1718a = new WidgetFrame((ConstraintWidget) null);
        }
        WidgetFrame widgetFrame = this.f1718a;
        widgetFrame.top = i3;
        widgetFrame.left = i2;
        widgetFrame.right = i4;
        widgetFrame.bottom = i5;
    }

    public void setCustomAttribute(String str, int i2, float f2) {
        this.f1718a.setCustomAttribute(str, i2, f2);
    }

    public void setCustomAttribute(String str, int i2, int i3) {
        this.f1718a.setCustomAttribute(str, i2, i3);
    }

    public void setCustomAttribute(String str, int i2, String str2) {
        this.f1718a.setCustomAttribute(str, i2, str2);
    }

    public void setCustomAttribute(String str, int i2, boolean z) {
        this.f1718a.setCustomAttribute(str, i2, z);
    }

    public void setPivotX(float f2) {
        this.f1718a.pivotX = f2;
    }

    public void setPivotY(float f2) {
        this.f1718a.pivotY = f2;
    }

    public void setRotationX(float f2) {
        this.f1718a.rotationX = f2;
    }

    public void setRotationY(float f2) {
        this.f1718a.rotationY = f2;
    }

    public void setRotationZ(float f2) {
        this.f1718a.rotationZ = f2;
    }

    public void setScaleX(float f2) {
        this.f1718a.scaleX = f2;
    }

    public void setScaleY(float f2) {
        this.f1718a.scaleY = f2;
    }

    public void setTranslationX(float f2) {
        this.f1718a.translationX = f2;
    }

    public void setTranslationY(float f2) {
        this.f1718a.translationY = f2;
    }

    public void setTranslationZ(float f2) {
        this.f1718a.translationZ = f2;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, float f2) {
        if (setValueAttributes(i2, f2)) {
            return true;
        }
        return setValueMotion(i2, f2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, int i3) {
        return setValueAttributes(i2, i3);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, String str) {
        return setValueMotion(i2, str);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, boolean z) {
        return false;
    }

    public boolean setValueAttributes(int i2, float f2) {
        switch (i2) {
            case 303:
                this.f1718a.alpha = f2;
                return true;
            case 304:
                this.f1718a.translationX = f2;
                return true;
            case 305:
                this.f1718a.translationY = f2;
                return true;
            case 306:
                this.f1718a.translationZ = f2;
                return true;
            case 307:
            default:
                return false;
            case 308:
                this.f1718a.rotationX = f2;
                return true;
            case 309:
                this.f1718a.rotationY = f2;
                return true;
            case 310:
                this.f1718a.rotationZ = f2;
                return true;
            case 311:
                this.f1718a.scaleX = f2;
                return true;
            case 312:
                this.f1718a.scaleY = f2;
                return true;
            case 313:
                this.f1718a.pivotX = f2;
                return true;
            case 314:
                this.f1718a.pivotY = f2;
                return true;
            case 315:
                this.mProgress = f2;
                return true;
            case TypedValues.Attributes.TYPE_PATH_ROTATE /* 316 */:
                this.f1721d = f2;
                return true;
        }
    }

    public boolean setValueMotion(int i2, float f2) {
        switch (i2) {
            case 600:
                this.f1719b.mMotionStagger = f2;
                return true;
            case 601:
                this.f1719b.mPathRotate = f2;
                return true;
            case TypedValues.Motion.TYPE_QUANTIZE_MOTION_PHASE /* 602 */:
                this.f1719b.mQuantizeMotionPhase = f2;
                return true;
            default:
                return false;
        }
    }

    public boolean setValueMotion(int i2, int i3) {
        switch (i2) {
            case TypedValues.Motion.TYPE_ANIMATE_RELATIVE_TO /* 605 */:
                this.f1719b.mAnimateRelativeTo = i3;
                return true;
            case TypedValues.Motion.TYPE_ANIMATE_CIRCLEANGLE_TO /* 606 */:
                this.f1719b.mAnimateCircleAngleTo = i3;
                return true;
            case TypedValues.Motion.TYPE_PATHMOTION_ARC /* 607 */:
                this.f1719b.mPathMotionArc = i3;
                return true;
            case TypedValues.Motion.TYPE_DRAW_PATH /* 608 */:
                this.f1719b.mDrawPath = i3;
                return true;
            case TypedValues.Motion.TYPE_POLAR_RELATIVETO /* 609 */:
                this.f1719b.mPolarRelativeTo = i3;
                return true;
            case TypedValues.Motion.TYPE_QUANTIZE_MOTIONSTEPS /* 610 */:
                this.f1719b.mQuantizeMotionSteps = i3;
                return true;
            case TypedValues.Motion.TYPE_QUANTIZE_INTERPOLATOR_TYPE /* 611 */:
                this.f1719b.mQuantizeInterpolatorType = i3;
                return true;
            case TypedValues.Motion.TYPE_QUANTIZE_INTERPOLATOR_ID /* 612 */:
                this.f1719b.mQuantizeInterpolatorID = i3;
                return true;
            default:
                return false;
        }
    }

    public boolean setValueMotion(int i2, String str) {
        if (i2 == 603) {
            this.f1719b.mTransitionEasing = str;
            return true;
        } else if (i2 != 604) {
            return false;
        } else {
            this.f1719b.mQuantizeInterpolatorString = str;
            return true;
        }
    }

    public void setVisibility(int i2) {
        this.f1720c.visibility = i2;
    }

    public String toString() {
        return this.f1718a.left + ", " + this.f1718a.top + ", " + this.f1718a.right + ", " + this.f1718a.bottom;
    }
}
