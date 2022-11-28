package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MotionKeyTimeCycle extends MotionKey {
    public static final int KEY_TYPE = 3;
    private static final String TAG = "KeyTimeCycle";
    private String mTransitionEasing;
    private int mCurveFit = -1;
    private float mAlpha = Float.NaN;
    private float mElevation = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private float mProgress = Float.NaN;
    private int mWaveShape = 0;
    private String mCustomWaveShape = null;
    private float mWavePeriod = Float.NaN;
    private float mWaveOffset = 0.0f;

    public MotionKeyTimeCycle() {
        this.mType = 3;
        this.mCustom = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0089, code lost:
        if (r1.equals("scaleX") == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addTimeValues(HashMap<String, TimeCycleSplineSet> hashMap) {
        int i2;
        float f2;
        Iterator<String> it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            TimeCycleSplineSet timeCycleSplineSet = hashMap.get(next);
            if (timeCycleSplineSet != null) {
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
                        case -908189618:
                            break;
                        case -908189617:
                            if (next.equals("scaleY")) {
                                c2 = '\b';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -4379043:
                            if (next.equals("elevation")) {
                                c2 = '\t';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 92909918:
                            if (next.equals("alpha")) {
                                c2 = '\n';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 803192288:
                            if (next.equals("pathRotate")) {
                                c2 = 11;
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
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (!Float.isNaN(this.mRotationY)) {
                                i2 = this.mFramePosition;
                                f2 = this.mRotationY;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (!Float.isNaN(this.mRotation)) {
                                i2 = this.mFramePosition;
                                f2 = this.mRotation;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (!Float.isNaN(this.mTranslationX)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTranslationX;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (!Float.isNaN(this.mTranslationY)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTranslationY;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (!Float.isNaN(this.mTranslationZ)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTranslationZ;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (!Float.isNaN(this.mProgress)) {
                                i2 = this.mFramePosition;
                                f2 = this.mProgress;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (!Float.isNaN(this.mScaleX)) {
                                i2 = this.mFramePosition;
                                f2 = this.mScaleX;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case '\b':
                            if (!Float.isNaN(this.mScaleY)) {
                                i2 = this.mFramePosition;
                                f2 = this.mScaleY;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case '\t':
                            if (!Float.isNaN(this.mTranslationZ)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTranslationZ;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case '\n':
                            if (!Float.isNaN(this.mAlpha)) {
                                i2 = this.mFramePosition;
                                f2 = this.mAlpha;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (!Float.isNaN(this.mTransitionPathRotate)) {
                                i2 = this.mFramePosition;
                                f2 = this.mTransitionPathRotate;
                                timeCycleSplineSet.setPoint(i2, f2, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                                break;
                            } else {
                                break;
                            }
                        default:
                            Utils.loge("KeyTimeCycles", "UNKNOWN addValues \"" + next + "\"");
                            break;
                    }
                } else {
                    CustomVariable customVariable = this.mCustom.get(next.substring(7));
                    if (customVariable != null) {
                        ((TimeCycleSplineSet.CustomVarSet) timeCycleSplineSet).setPoint(this.mFramePosition, customVariable, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey clone() {
        return new MotionKeyTimeCycle().copy((MotionKey) this);
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKeyTimeCycle copy(MotionKey motionKey) {
        super.copy(motionKey);
        MotionKeyTimeCycle motionKeyTimeCycle = (MotionKeyTimeCycle) motionKey;
        this.mTransitionEasing = motionKeyTimeCycle.mTransitionEasing;
        this.mCurveFit = motionKeyTimeCycle.mCurveFit;
        this.mWaveShape = motionKeyTimeCycle.mWaveShape;
        this.mWavePeriod = motionKeyTimeCycle.mWavePeriod;
        this.mWaveOffset = motionKeyTimeCycle.mWaveOffset;
        this.mProgress = motionKeyTimeCycle.mProgress;
        this.mAlpha = motionKeyTimeCycle.mAlpha;
        this.mElevation = motionKeyTimeCycle.mElevation;
        this.mRotation = motionKeyTimeCycle.mRotation;
        this.mTransitionPathRotate = motionKeyTimeCycle.mTransitionPathRotate;
        this.mRotationX = motionKeyTimeCycle.mRotationX;
        this.mRotationY = motionKeyTimeCycle.mRotationY;
        this.mScaleX = motionKeyTimeCycle.mScaleX;
        this.mScaleY = motionKeyTimeCycle.mScaleY;
        this.mTranslationX = motionKeyTimeCycle.mTranslationX;
        this.mTranslationY = motionKeyTimeCycle.mTranslationY;
        this.mTranslationZ = motionKeyTimeCycle.mTranslationZ;
        return this;
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
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("pathRotate");
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
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return TypedValues.Cycle.getId(str);
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, float f2) {
        if (i2 == 315) {
            this.mProgress = a(Float.valueOf(f2));
            return true;
        } else if (i2 == 401) {
            this.mCurveFit = b(Float.valueOf(f2));
            return true;
        } else if (i2 == 403) {
            this.mAlpha = f2;
            return true;
        } else if (i2 == 416) {
            this.mTransitionPathRotate = a(Float.valueOf(f2));
            return true;
        } else if (i2 == 423) {
            this.mWavePeriod = a(Float.valueOf(f2));
            return true;
        } else if (i2 == 424) {
            this.mWaveOffset = a(Float.valueOf(f2));
            return true;
        } else {
            switch (i2) {
                case 304:
                    this.mTranslationX = a(Float.valueOf(f2));
                    return true;
                case 305:
                    this.mTranslationY = a(Float.valueOf(f2));
                    return true;
                case 306:
                    this.mTranslationZ = a(Float.valueOf(f2));
                    return true;
                case 307:
                    this.mElevation = a(Float.valueOf(f2));
                    return true;
                case 308:
                    this.mRotationX = a(Float.valueOf(f2));
                    return true;
                case 309:
                    this.mRotationY = a(Float.valueOf(f2));
                    return true;
                case 310:
                    this.mRotation = a(Float.valueOf(f2));
                    return true;
                case 311:
                    this.mScaleX = a(Float.valueOf(f2));
                    return true;
                case 312:
                    this.mScaleY = a(Float.valueOf(f2));
                    return true;
                default:
                    return super.setValue(i2, f2);
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, int i3) {
        if (i2 == 100) {
            this.mFramePosition = i3;
            return true;
        } else if (i2 != 421) {
            return super.setValue(i2, i3);
        } else {
            this.mWaveShape = i3;
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, String str) {
        if (i2 == 420) {
            this.mTransitionEasing = str;
            return true;
        } else if (i2 != 421) {
            return super.setValue(i2, str);
        } else {
            this.mWaveShape = 7;
            this.mCustomWaveShape = str;
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, boolean z) {
        return super.setValue(i2, z);
    }
}
