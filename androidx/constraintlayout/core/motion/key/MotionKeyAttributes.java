package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.http.message.TokenParser;
/* loaded from: classes.dex */
public class MotionKeyAttributes extends MotionKey {
    private static final boolean DEBUG = false;
    public static final int KEY_TYPE = 1;
    private static final String TAG = "KeyAttributes";
    private String mTransitionEasing;
    private int mCurveFit = -1;
    private int mVisibility = 0;
    private float mAlpha = Float.NaN;
    private float mElevation = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private float mProgress = Float.NaN;

    public MotionKeyAttributes() {
        this.mType = 1;
        this.mCustom = new HashMap<>();
    }

    private float getFloatValue(int i2) {
        if (i2 != 100) {
            switch (i2) {
                case 303:
                    return this.mAlpha;
                case 304:
                    return this.mTranslationX;
                case 305:
                    return this.mTranslationY;
                case 306:
                    return this.mTranslationZ;
                case 307:
                    return this.mElevation;
                case 308:
                    return this.mRotationX;
                case 309:
                    return this.mRotationY;
                case 310:
                    return this.mRotation;
                case 311:
                    return this.mScaleX;
                case 312:
                    return this.mScaleY;
                case 313:
                    return this.mPivotX;
                case 314:
                    return this.mPivotY;
                case 315:
                    return this.mProgress;
                case TypedValues.Attributes.TYPE_PATH_ROTATE /* 316 */:
                    return this.mTransitionPathRotate;
                default:
                    return Float.NaN;
            }
        }
        return this.mFramePosition;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
        if (r1.equals("pivotX") == false) goto L12;
     */
    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addValues(HashMap<String, SplineSet> hashMap) {
        int i2;
        float f2;
        Iterator<String> it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            SplineSet splineSet = hashMap.get(next);
            if (splineSet != null) {
                char c2 = 7;
                if (!next.startsWith("CUSTOM")) {
                    switch (next.hashCode()) {
                        case -1249320806:
                            if (next.equals("rotationX")) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1249320805:
                            if (next.equals("rotationY")) {
                                c2 = 1;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1249320804:
                            if (next.equals("rotationZ")) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1225497657:
                            if (next.equals("translationX")) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1225497656:
                            if (next.equals("translationY")) {
                                c2 = 4;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1225497655:
                            if (next.equals("translationZ")) {
                                c2 = 5;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1001078227:
                            if (next.equals("progress")) {
                                c2 = 6;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -987906986:
                            break;
                        case -987906985:
                            if (next.equals("pivotY")) {
                                c2 = '\b';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -908189618:
                            if (next.equals("scaleX")) {
                                c2 = '\t';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -908189617:
                            if (next.equals("scaleY")) {
                                c2 = '\n';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -4379043:
                            if (next.equals("elevation")) {
                                c2 = 11;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 92909918:
                            if (next.equals("alpha")) {
                                c2 = '\f';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 803192288:
                            if (next.equals("pathRotate")) {
                                c2 = TokenParser.CR;
                                break;
                            }
                            c2 = 65535;
                            break;
                        default:
                            c2 = 65535;
                            break;
                    }
                    switch (c2) {
                        case 0:
                            if (!Float.isNaN(this.mRotationX)) {
                                i2 = this.mFramePosition;
                                f2 = this.mRotationX;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (!Float.isNaN(this.mRotationY)) {
                                i2 = this.mFramePosition;
                                f2 = this.mRotationY;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (!Float.isNaN(this.mRotation)) {
                                i2 = this.mFramePosition;
                                f2 = this.mRotation;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (!Float.isNaN(this.mTranslationX)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTranslationX;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (!Float.isNaN(this.mTranslationY)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTranslationY;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (!Float.isNaN(this.mTranslationZ)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTranslationZ;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (!Float.isNaN(this.mProgress)) {
                                i2 = this.mFramePosition;
                                f2 = this.mProgress;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (!Float.isNaN(this.mRotationX)) {
                                i2 = this.mFramePosition;
                                f2 = this.mPivotX;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case '\b':
                            if (!Float.isNaN(this.mRotationY)) {
                                i2 = this.mFramePosition;
                                f2 = this.mPivotY;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case '\t':
                            if (!Float.isNaN(this.mScaleX)) {
                                i2 = this.mFramePosition;
                                f2 = this.mScaleX;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case '\n':
                            if (!Float.isNaN(this.mScaleY)) {
                                i2 = this.mFramePosition;
                                f2 = this.mScaleY;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (!Float.isNaN(this.mElevation)) {
                                i2 = this.mFramePosition;
                                f2 = this.mElevation;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case '\f':
                            if (!Float.isNaN(this.mAlpha)) {
                                i2 = this.mFramePosition;
                                f2 = this.mAlpha;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        case '\r':
                            if (!Float.isNaN(this.mTransitionPathRotate)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTransitionPathRotate;
                                splineSet.setPoint(i2, f2);
                                break;
                            } else {
                                break;
                            }
                        default:
                            System.err.println("not supported by KeyAttributes " + next);
                            break;
                    }
                } else {
                    CustomVariable customVariable = this.mCustom.get(next.substring(7));
                    if (customVariable != null) {
                        ((SplineSet.CustomSpline) splineSet).setPoint(this.mFramePosition, customVariable);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey clone() {
        return null;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashSet.add("pivotX");
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashSet.add("pivotY");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            hashSet.add("progress");
        }
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    public int getCurveFit() {
        return this.mCurveFit;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return TypedValues.Attributes.getId(str);
    }

    public void printAttributes() {
        HashSet<String> hashSet = new HashSet<>();
        getAttributeNames(hashSet);
        PrintStream printStream = System.out;
        printStream.println(" ------------- " + this.mFramePosition + " -------------");
        String[] strArr = (String[]) hashSet.toArray(new String[0]);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            int id = TypedValues.Attributes.getId(strArr[i2]);
            PrintStream printStream2 = System.out;
            printStream2.println(strArr[i2] + ":" + getFloatValue(id));
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (!Float.isNaN(this.mAlpha)) {
            hashMap.put("alpha", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mElevation)) {
            hashMap.put("elevation", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotation)) {
            hashMap.put("rotationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashMap.put("rotationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashMap.put("rotationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashMap.put("pivotX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashMap.put("pivotY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashMap.put("translationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashMap.put("translationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashMap.put("translationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashMap.put("pathRotate", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashMap.put("scaleX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashMap.put("scaleY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mProgress)) {
            hashMap.put("progress", Integer.valueOf(this.mCurveFit));
        }
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + it.next(), Integer.valueOf(this.mCurveFit));
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, float f2) {
        if (i2 != 100) {
            switch (i2) {
                case 303:
                    this.mAlpha = f2;
                    return true;
                case 304:
                    this.mTranslationX = f2;
                    return true;
                case 305:
                    this.mTranslationY = f2;
                    return true;
                case 306:
                    this.mTranslationZ = f2;
                    return true;
                case 307:
                    this.mElevation = f2;
                    return true;
                case 308:
                    this.mRotationX = f2;
                    return true;
                case 309:
                    this.mRotationY = f2;
                    return true;
                case 310:
                    this.mRotation = f2;
                    return true;
                case 311:
                    this.mScaleX = f2;
                    return true;
                case 312:
                    this.mScaleY = f2;
                    return true;
                case 313:
                    this.mPivotX = f2;
                    return true;
                case 314:
                    this.mPivotY = f2;
                    return true;
                case 315:
                    this.mProgress = f2;
                    return true;
                case TypedValues.Attributes.TYPE_PATH_ROTATE /* 316 */:
                    break;
                default:
                    return super.setValue(i2, f2);
            }
        }
        this.mTransitionPathRotate = f2;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, int i3) {
        if (i2 == 100) {
            this.mFramePosition = i3;
            return true;
        } else if (i2 == 301) {
            this.mCurveFit = i3;
            return true;
        } else if (i2 == 302) {
            this.mVisibility = i3;
            return true;
        } else if (setValue(i2, i3)) {
            return true;
        } else {
            return super.setValue(i2, i3);
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, String str) {
        if (i2 == 101) {
            this.f1723b = str;
            return true;
        } else if (i2 != 317) {
            return super.setValue(i2, str);
        } else {
            this.mTransitionEasing = str;
            return true;
        }
    }
}
