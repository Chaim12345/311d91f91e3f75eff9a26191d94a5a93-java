package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
/* loaded from: classes.dex */
public class KeyPosition extends KeyPositionBase {
    public static final String DRAWPATH = "drawPath";
    public static final String PERCENT_HEIGHT = "percentHeight";
    public static final String PERCENT_WIDTH = "percentWidth";
    public static final String PERCENT_X = "percentX";
    public static final String PERCENT_Y = "percentY";
    public static final String SIZE_PERCENT = "sizePercent";
    private static final String TAG = "KeyPosition";
    public static final String TRANSITION_EASING = "transitionEasing";
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;

    /* renamed from: g  reason: collision with root package name */
    String f2047g = null;

    /* renamed from: h  reason: collision with root package name */
    int f2048h = Key.UNSET;

    /* renamed from: i  reason: collision with root package name */
    int f2049i = 0;

    /* renamed from: j  reason: collision with root package name */
    float f2050j = Float.NaN;

    /* renamed from: k  reason: collision with root package name */
    float f2051k = Float.NaN;

    /* renamed from: l  reason: collision with root package name */
    float f2052l = Float.NaN;

    /* renamed from: m  reason: collision with root package name */
    float f2053m = Float.NaN;

    /* renamed from: n  reason: collision with root package name */
    float f2054n = Float.NaN;

    /* renamed from: o  reason: collision with root package name */
    float f2055o = Float.NaN;

    /* renamed from: p  reason: collision with root package name */
    int f2056p = 0;
    private float mCalculatedPositionX = Float.NaN;
    private float mCalculatedPositionY = Float.NaN;

    /* loaded from: classes.dex */
    private static class Loader {
        private static final int CURVE_FIT = 4;
        private static final int DRAW_PATH = 5;
        private static final int FRAME_POSITION = 2;
        private static final int PATH_MOTION_ARC = 10;
        private static final int PERCENT_HEIGHT = 12;
        private static final int PERCENT_WIDTH = 11;
        private static final int PERCENT_X = 6;
        private static final int PERCENT_Y = 7;
        private static final int SIZE_PERCENT = 8;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TYPE = 9;
        private static SparseIntArray mAttrMap;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyPosition_motionTarget, 1);
            mAttrMap.append(R.styleable.KeyPosition_framePosition, 2);
            mAttrMap.append(R.styleable.KeyPosition_transitionEasing, 3);
            mAttrMap.append(R.styleable.KeyPosition_curveFit, 4);
            mAttrMap.append(R.styleable.KeyPosition_drawPath, 5);
            mAttrMap.append(R.styleable.KeyPosition_percentX, 6);
            mAttrMap.append(R.styleable.KeyPosition_percentY, 7);
            mAttrMap.append(R.styleable.KeyPosition_keyPositionType, 9);
            mAttrMap.append(R.styleable.KeyPosition_sizePercent, 8);
            mAttrMap.append(R.styleable.KeyPosition_percentWidth, 11);
            mAttrMap.append(R.styleable.KeyPosition_percentHeight, 12);
            mAttrMap.append(R.styleable.KeyPosition_pathMotionArc, 10);
        }

        private Loader() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void read(KeyPosition keyPosition, TypedArray typedArray) {
            float f2;
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (mAttrMap.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyPosition.f2042b);
                            keyPosition.f2042b = resourceId;
                            if (resourceId != -1) {
                            }
                            keyPosition.f2043c = typedArray.getString(index);
                        } else {
                            if (typedArray.peekValue(index).type != 3) {
                                keyPosition.f2042b = typedArray.getResourceId(index, keyPosition.f2042b);
                                continue;
                            }
                            keyPosition.f2043c = typedArray.getString(index);
                        }
                    case 2:
                        keyPosition.f2041a = typedArray.getInt(index, keyPosition.f2041a);
                        continue;
                    case 3:
                        keyPosition.f2047g = typedArray.peekValue(index).type == 3 ? typedArray.getString(index) : Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                        continue;
                    case 4:
                        keyPosition.f2057f = typedArray.getInteger(index, keyPosition.f2057f);
                        continue;
                    case 5:
                        keyPosition.f2049i = typedArray.getInt(index, keyPosition.f2049i);
                        continue;
                    case 6:
                        keyPosition.f2052l = typedArray.getFloat(index, keyPosition.f2052l);
                        continue;
                    case 7:
                        keyPosition.f2053m = typedArray.getFloat(index, keyPosition.f2053m);
                        continue;
                    case 8:
                        f2 = typedArray.getFloat(index, keyPosition.f2051k);
                        keyPosition.f2050j = f2;
                        break;
                    case 9:
                        keyPosition.f2056p = typedArray.getInt(index, keyPosition.f2056p);
                        continue;
                    case 10:
                        keyPosition.f2048h = typedArray.getInt(index, keyPosition.f2048h);
                        continue;
                    case 11:
                        keyPosition.f2050j = typedArray.getFloat(index, keyPosition.f2050j);
                        continue;
                    case 12:
                        f2 = typedArray.getFloat(index, keyPosition.f2051k);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        continue;
                }
                keyPosition.f2051k = f2;
            }
            if (keyPosition.f2041a == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public KeyPosition() {
        this.f2044d = 2;
    }

    private void calcCartesianPosition(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        float f8 = Float.isNaN(this.f2052l) ? 0.0f : this.f2052l;
        float f9 = Float.isNaN(this.f2055o) ? 0.0f : this.f2055o;
        float f10 = Float.isNaN(this.f2053m) ? 0.0f : this.f2053m;
        this.mCalculatedPositionX = (int) (f2 + (f8 * f6) + ((Float.isNaN(this.f2054n) ? 0.0f : this.f2054n) * f7));
        this.mCalculatedPositionY = (int) (f3 + (f6 * f9) + (f7 * f10));
    }

    private void calcPathPosition(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        float f8 = this.f2052l;
        float f9 = this.f2053m;
        this.mCalculatedPositionX = f2 + (f6 * f8) + ((-f7) * f9);
        this.mCalculatedPositionY = f3 + (f7 * f8) + (f6 * f9);
    }

    private void calcScreenPosition(int i2, int i3) {
        float f2 = this.f2052l;
        float f3 = 0;
        this.mCalculatedPositionX = ((i2 - 0) * f2) + f3;
        this.mCalculatedPositionY = ((i3 - 0) * f2) + f3;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, ViewSpline> hashMap) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key clone() {
        return new KeyPosition().copy(this);
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key) {
        super.copy(key);
        KeyPosition keyPosition = (KeyPosition) key;
        this.f2047g = keyPosition.f2047g;
        this.f2048h = keyPosition.f2048h;
        this.f2049i = keyPosition.f2049i;
        this.f2050j = keyPosition.f2050j;
        this.f2051k = Float.NaN;
        this.f2052l = keyPosition.f2052l;
        this.f2053m = keyPosition.f2053m;
        this.f2054n = keyPosition.f2054n;
        this.f2055o = keyPosition.f2055o;
        this.mCalculatedPositionX = keyPosition.mCalculatedPositionX;
        this.mCalculatedPositionY = keyPosition.mCalculatedPositionY;
        return this;
    }

    void e(int i2, int i3, float f2, float f3, float f4, float f5) {
        int i4 = this.f2056p;
        if (i4 == 1) {
            calcPathPosition(f2, f3, f4, f5);
        } else if (i4 != 2) {
            calcCartesianPosition(f2, f3, f4, f5);
        } else {
            calcScreenPosition(i2, i3);
        }
    }

    void f(RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        if (strArr[0] == null) {
            strArr[0] = "percentX";
            fArr[0] = (f2 - centerX) / centerX2;
            strArr[1] = "percentY";
            fArr[1] = (f3 - centerY) / centerY2;
            return;
        }
        float f4 = (f2 - centerX) / centerX2;
        if ("percentX".equals(strArr[0])) {
            fArr[0] = f4;
            fArr[1] = (f3 - centerY) / centerY2;
            return;
        }
        fArr[1] = f4;
        fArr[0] = (f3 - centerY) / centerY2;
    }

    void g(RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        float hypot = (float) Math.hypot(centerX2, centerY2);
        if (hypot < 1.0E-4d) {
            System.out.println("distance ~ 0");
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            return;
        }
        float f4 = centerX2 / hypot;
        float f5 = centerY2 / hypot;
        float f6 = f3 - centerY;
        float f7 = f2 - centerX;
        float f8 = ((f4 * f6) - (f7 * f5)) / hypot;
        float f9 = ((f4 * f7) + (f5 * f6)) / hypot;
        if (strArr[0] != null) {
            if ("percentX".equals(strArr[0])) {
                fArr[0] = f9;
                fArr[1] = f8;
                return;
            }
            return;
        }
        strArr[0] = "percentX";
        strArr[1] = "percentY";
        fArr[0] = f9;
        fArr[1] = f8;
    }

    void h(View view, RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        rectF.centerX();
        rectF.centerY();
        rectF2.centerX();
        rectF2.centerY();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (strArr[0] == null) {
            strArr[0] = "percentX";
            fArr[0] = f2 / width;
            strArr[1] = "percentY";
            fArr[1] = f3 / height;
            return;
        }
        float f4 = f2 / width;
        if ("percentX".equals(strArr[0])) {
            fArr[0] = f4;
            fArr[1] = f3 / height;
            return;
        }
        fArr[1] = f4;
        fArr[0] = f3 / height;
    }

    @Override // androidx.constraintlayout.motion.widget.KeyPositionBase
    public boolean intersects(int i2, int i3, RectF rectF, RectF rectF2, float f2, float f3) {
        e(i2, i3, rectF.centerX(), rectF.centerY(), rectF2.centerX(), rectF2.centerY());
        return Math.abs(f2 - this.mCalculatedPositionX) < 20.0f && Math.abs(f3 - this.mCalculatedPositionY) < 20.0f;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyPosition));
    }

    @Override // androidx.constraintlayout.motion.widget.KeyPositionBase
    public void positionAttributes(View view, RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        int i2 = this.f2056p;
        if (i2 == 1) {
            g(rectF, rectF2, f2, f3, strArr, fArr);
        } else if (i2 != 2) {
            f(rectF, rectF2, f2, f3, strArr, fArr);
        } else {
            h(view, rectF, rectF2, f2, f3, strArr, fArr);
        }
    }

    public void setType(int i2) {
        this.f2056p = i2;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        float c2;
        str.hashCode();
        char c3 = 65535;
        switch (str.hashCode()) {
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c3 = 0;
                    break;
                }
                break;
            case -1127236479:
                if (str.equals("percentWidth")) {
                    c3 = 1;
                    break;
                }
                break;
            case -1017587252:
                if (str.equals("percentHeight")) {
                    c3 = 2;
                    break;
                }
                break;
            case -827014263:
                if (str.equals("drawPath")) {
                    c3 = 3;
                    break;
                }
                break;
            case -200259324:
                if (str.equals("sizePercent")) {
                    c3 = 4;
                    break;
                }
                break;
            case 428090547:
                if (str.equals("percentX")) {
                    c3 = 5;
                    break;
                }
                break;
            case 428090548:
                if (str.equals("percentY")) {
                    c3 = 6;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                this.f2047g = obj.toString();
                return;
            case 1:
                this.f2050j = c(obj);
                return;
            case 2:
                c2 = c(obj);
                break;
            case 3:
                this.f2049i = d(obj);
                return;
            case 4:
                c2 = c(obj);
                this.f2050j = c2;
                break;
            case 5:
                this.f2052l = c(obj);
                return;
            case 6:
                this.f2053m = c(obj);
                return;
            default:
                return;
        }
        this.f2051k = c2;
    }
}
