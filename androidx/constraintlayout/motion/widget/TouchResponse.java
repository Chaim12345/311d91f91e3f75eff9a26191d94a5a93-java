package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TouchResponse {
    public static final int COMPLETE_MODE_CONTINUOUS_VELOCITY = 0;
    public static final int COMPLETE_MODE_SPRING = 1;
    private static final boolean DEBUG = false;
    private static final float EPSILON = 1.0E-7f;
    private static final int SEC_TO_MILLISECONDS = 1000;
    private static final int SIDE_BOTTOM = 3;
    private static final int SIDE_END = 6;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_MIDDLE = 4;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_START = 5;
    private static final int SIDE_TOP = 0;
    private static final String TAG = "TouchResponse";
    private static final int TOUCH_DOWN = 1;
    private static final int TOUCH_END = 5;
    private static final int TOUCH_LEFT = 2;
    private static final int TOUCH_RIGHT = 3;
    private static final int TOUCH_START = 4;
    private static final int TOUCH_UP = 0;

    /* renamed from: a  reason: collision with root package name */
    float f2157a;

    /* renamed from: b  reason: collision with root package name */
    float f2158b;

    /* renamed from: c  reason: collision with root package name */
    boolean f2159c;
    private float[] mAnchorDpDt;
    private int mAutoCompleteMode;
    private float mDragScale;
    private boolean mDragStarted;
    private float mDragThreshold;
    private int mFlags;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mLimitBoundsTo;
    private float mMaxAcceleration;
    private float mMaxVelocity;
    private final MotionLayout mMotionLayout;
    private boolean mMoveWhenScrollAtTop;
    private int mOnTouchUp;
    private int mRotationCenterId;
    private int mSpringBoundary;
    private float mSpringDamping;
    private float mSpringMass;
    private float mSpringStiffness;
    private float mSpringStopThreshold;
    private int[] mTempLoc;
    private int mTouchAnchorId;
    private int mTouchAnchorSide;
    private float mTouchAnchorX;
    private float mTouchAnchorY;
    private float mTouchDirectionX;
    private float mTouchDirectionY;
    private int mTouchRegionId;
    private int mTouchSide;
    private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public TouchResponse(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.mTouchAnchorSide = 0;
        this.mTouchSide = 0;
        this.mOnTouchUp = 0;
        this.mTouchAnchorId = -1;
        this.mTouchRegionId = -1;
        this.mLimitBoundsTo = -1;
        this.mTouchAnchorY = 0.5f;
        this.mTouchAnchorX = 0.5f;
        this.f2157a = 0.5f;
        this.f2158b = 0.5f;
        this.mRotationCenterId = -1;
        this.f2159c = false;
        this.mTouchDirectionX = 0.0f;
        this.mTouchDirectionY = 1.0f;
        this.mDragStarted = false;
        this.mAnchorDpDt = new float[2];
        this.mTempLoc = new int[2];
        this.mMaxVelocity = 4.0f;
        this.mMaxAcceleration = 1.2f;
        this.mMoveWhenScrollAtTop = true;
        this.mDragScale = 1.0f;
        this.mFlags = 0;
        this.mDragThreshold = 10.0f;
        this.mSpringDamping = 10.0f;
        this.mSpringMass = 1.0f;
        this.mSpringStiffness = Float.NaN;
        this.mSpringStopThreshold = Float.NaN;
        this.mSpringBoundary = 0;
        this.mAutoCompleteMode = 0;
        this.mMotionLayout = motionLayout;
        fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser));
    }

    public TouchResponse(MotionLayout motionLayout, OnSwipe onSwipe) {
        this.mTouchAnchorSide = 0;
        this.mTouchSide = 0;
        this.mOnTouchUp = 0;
        this.mTouchAnchorId = -1;
        this.mTouchRegionId = -1;
        this.mLimitBoundsTo = -1;
        this.mTouchAnchorY = 0.5f;
        this.mTouchAnchorX = 0.5f;
        this.f2157a = 0.5f;
        this.f2158b = 0.5f;
        this.mRotationCenterId = -1;
        this.f2159c = false;
        this.mTouchDirectionX = 0.0f;
        this.mTouchDirectionY = 1.0f;
        this.mDragStarted = false;
        this.mAnchorDpDt = new float[2];
        this.mTempLoc = new int[2];
        this.mMaxVelocity = 4.0f;
        this.mMaxAcceleration = 1.2f;
        this.mMoveWhenScrollAtTop = true;
        this.mDragScale = 1.0f;
        this.mFlags = 0;
        this.mDragThreshold = 10.0f;
        this.mSpringDamping = 10.0f;
        this.mSpringMass = 1.0f;
        this.mSpringStiffness = Float.NaN;
        this.mSpringStopThreshold = Float.NaN;
        this.mSpringBoundary = 0;
        this.mAutoCompleteMode = 0;
        this.mMotionLayout = motionLayout;
        this.mTouchAnchorId = onSwipe.getTouchAnchorId();
        int touchAnchorSide = onSwipe.getTouchAnchorSide();
        this.mTouchAnchorSide = touchAnchorSide;
        if (touchAnchorSide != -1) {
            float[][] fArr = TOUCH_SIDES;
            this.mTouchAnchorX = fArr[touchAnchorSide][0];
            this.mTouchAnchorY = fArr[touchAnchorSide][1];
        }
        int dragDirection = onSwipe.getDragDirection();
        this.mTouchSide = dragDirection;
        float[][] fArr2 = TOUCH_DIRECTION;
        if (dragDirection < fArr2.length) {
            this.mTouchDirectionX = fArr2[dragDirection][0];
            this.mTouchDirectionY = fArr2[dragDirection][1];
        } else {
            this.mTouchDirectionY = Float.NaN;
            this.mTouchDirectionX = Float.NaN;
            this.f2159c = true;
        }
        this.mMaxVelocity = onSwipe.getMaxVelocity();
        this.mMaxAcceleration = onSwipe.getMaxAcceleration();
        this.mMoveWhenScrollAtTop = onSwipe.getMoveWhenScrollAtTop();
        this.mDragScale = onSwipe.getDragScale();
        this.mDragThreshold = onSwipe.getDragThreshold();
        this.mTouchRegionId = onSwipe.getTouchRegionId();
        this.mOnTouchUp = onSwipe.getOnTouchUp();
        this.mFlags = onSwipe.getNestedScrollFlags();
        this.mLimitBoundsTo = onSwipe.getLimitBoundsTo();
        this.mRotationCenterId = onSwipe.getRotationCenterId();
        this.mSpringBoundary = onSwipe.getSpringBoundary();
        this.mSpringDamping = onSwipe.getSpringDamping();
        this.mSpringMass = onSwipe.getSpringMass();
        this.mSpringStiffness = onSwipe.getSpringStiffness();
        this.mSpringStopThreshold = onSwipe.getSpringStopThreshold();
        this.mAutoCompleteMode = onSwipe.getAutoCompleteMode();
    }

    private void fill(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index == R.styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = typedArray.getResourceId(index, this.mTouchAnchorId);
            } else if (index == R.styleable.OnSwipe_touchAnchorSide) {
                int i3 = typedArray.getInt(index, this.mTouchAnchorSide);
                this.mTouchAnchorSide = i3;
                float[][] fArr = TOUCH_SIDES;
                this.mTouchAnchorX = fArr[i3][0];
                this.mTouchAnchorY = fArr[i3][1];
            } else if (index == R.styleable.OnSwipe_dragDirection) {
                int i4 = typedArray.getInt(index, this.mTouchSide);
                this.mTouchSide = i4;
                float[][] fArr2 = TOUCH_DIRECTION;
                if (i4 < fArr2.length) {
                    this.mTouchDirectionX = fArr2[i4][0];
                    this.mTouchDirectionY = fArr2[i4][1];
                } else {
                    this.mTouchDirectionY = Float.NaN;
                    this.mTouchDirectionX = Float.NaN;
                    this.f2159c = true;
                }
            } else if (index == R.styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = typedArray.getFloat(index, this.mMaxVelocity);
            } else if (index == R.styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = typedArray.getFloat(index, this.mMaxAcceleration);
            } else if (index == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = typedArray.getBoolean(index, this.mMoveWhenScrollAtTop);
            } else if (index == R.styleable.OnSwipe_dragScale) {
                this.mDragScale = typedArray.getFloat(index, this.mDragScale);
            } else if (index == R.styleable.OnSwipe_dragThreshold) {
                this.mDragThreshold = typedArray.getFloat(index, this.mDragThreshold);
            } else if (index == R.styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = typedArray.getResourceId(index, this.mTouchRegionId);
            } else if (index == R.styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = typedArray.getInt(index, this.mOnTouchUp);
            } else if (index == R.styleable.OnSwipe_nestedScrollFlags) {
                this.mFlags = typedArray.getInteger(index, 0);
            } else if (index == R.styleable.OnSwipe_limitBoundsTo) {
                this.mLimitBoundsTo = typedArray.getResourceId(index, 0);
            } else if (index == R.styleable.OnSwipe_rotationCenterId) {
                this.mRotationCenterId = typedArray.getResourceId(index, this.mRotationCenterId);
            } else if (index == R.styleable.OnSwipe_springDamping) {
                this.mSpringDamping = typedArray.getFloat(index, this.mSpringDamping);
            } else if (index == R.styleable.OnSwipe_springMass) {
                this.mSpringMass = typedArray.getFloat(index, this.mSpringMass);
            } else if (index == R.styleable.OnSwipe_springStiffness) {
                this.mSpringStiffness = typedArray.getFloat(index, this.mSpringStiffness);
            } else if (index == R.styleable.OnSwipe_springStopThreshold) {
                this.mSpringStopThreshold = typedArray.getFloat(index, this.mSpringStopThreshold);
            } else if (index == R.styleable.OnSwipe_springBoundary) {
                this.mSpringBoundary = typedArray.getInt(index, this.mSpringBoundary);
            } else if (index == R.styleable.OnSwipe_autoCompleteMode) {
                this.mAutoCompleteMode = typedArray.getInt(index, this.mAutoCompleteMode);
            }
        }
    }

    private void fillFromAttributeList(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnSwipe);
        fill(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float a(float f2, float f3) {
        return (f2 * this.mTouchDirectionX) + (f3 * this.mTouchDirectionY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF b(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i2 = this.mLimitBoundsTo;
        if (i2 == -1 || (findViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.mMaxAcceleration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.mMoveWhenScrollAtTop;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float e(float f2, float f3) {
        this.mMotionLayout.M(this.mTouchAnchorId, this.mMotionLayout.getProgress(), this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f4 = this.mTouchDirectionX;
        if (f4 != 0.0f) {
            float[] fArr = this.mAnchorDpDt;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f2 * f4) / fArr[0];
        }
        float[] fArr2 = this.mAnchorDpDt;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f3 * this.mTouchDirectionY) / fArr2[1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF f(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i2 = this.mTouchRegionId;
        if (i2 == -1 || (findViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.mTouchRegionId;
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public int getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    public int getSpringBoundary() {
        return this.mSpringBoundary;
    }

    public float getSpringDamping() {
        return this.mSpringDamping;
    }

    public float getSpringMass() {
        return this.mSpringMass;
    }

    public float getSpringStiffness() {
        return this.mSpringStiffness;
    }

    public float getSpringStopThreshold() {
        return this.mSpringStopThreshold;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(MotionEvent motionEvent, MotionLayout.MotionTracker motionTracker, int i2, MotionScene motionScene) {
        int i3;
        if (this.f2159c) {
            i(motionEvent, motionTracker, i2, motionScene);
            return;
        }
        motionTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastTouchX = motionEvent.getRawX();
            this.mLastTouchY = motionEvent.getRawY();
            this.mDragStarted = false;
        } else if (action == 1) {
            this.mDragStarted = false;
            motionTracker.computeCurrentVelocity(1000);
            float xVelocity = motionTracker.getXVelocity();
            float yVelocity = motionTracker.getYVelocity();
            float progress = this.mMotionLayout.getProgress();
            int i4 = this.mTouchAnchorId;
            if (i4 != -1) {
                this.mMotionLayout.M(i4, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
            } else {
                float min = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                float[] fArr = this.mAnchorDpDt;
                fArr[1] = this.mTouchDirectionY * min;
                fArr[0] = min * this.mTouchDirectionX;
            }
            float f2 = this.mTouchDirectionX;
            float[] fArr2 = this.mAnchorDpDt;
            float f3 = fArr2[0];
            float f4 = fArr2[1];
            float f5 = f2 != 0.0f ? xVelocity / fArr2[0] : yVelocity / fArr2[1];
            float f6 = !Float.isNaN(f5) ? (f5 / 3.0f) + progress : progress;
            if (f6 != 0.0f && f6 != 1.0f && (i3 = this.mOnTouchUp) != 3) {
                float f7 = ((double) f6) < 0.5d ? 0.0f : 1.0f;
                if (i3 == 6) {
                    if (progress + f5 < 0.0f) {
                        f5 = Math.abs(f5);
                    }
                    f7 = 1.0f;
                }
                if (this.mOnTouchUp == 7) {
                    if (progress + f5 > 1.0f) {
                        f5 = -Math.abs(f5);
                    }
                    f7 = 0.0f;
                }
                this.mMotionLayout.touchAnimateTo(this.mOnTouchUp, f7, f5);
                if (0.0f < progress && 1.0f > progress) {
                    return;
                }
            } else if (0.0f < f6 && 1.0f > f6) {
                return;
            }
            this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
        } else if (action != 2) {
        } else {
            float rawY = motionEvent.getRawY() - this.mLastTouchY;
            float rawX = motionEvent.getRawX() - this.mLastTouchX;
            if (Math.abs((this.mTouchDirectionX * rawX) + (this.mTouchDirectionY * rawY)) > this.mDragThreshold || this.mDragStarted) {
                float progress2 = this.mMotionLayout.getProgress();
                if (!this.mDragStarted) {
                    this.mDragStarted = true;
                    this.mMotionLayout.setProgress(progress2);
                }
                int i5 = this.mTouchAnchorId;
                if (i5 != -1) {
                    this.mMotionLayout.M(i5, progress2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                } else {
                    float min2 = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                    float[] fArr3 = this.mAnchorDpDt;
                    fArr3[1] = this.mTouchDirectionY * min2;
                    fArr3[0] = min2 * this.mTouchDirectionX;
                }
                float f8 = this.mTouchDirectionX;
                float[] fArr4 = this.mAnchorDpDt;
                if (Math.abs(((f8 * fArr4[0]) + (this.mTouchDirectionY * fArr4[1])) * this.mDragScale) < 0.01d) {
                    float[] fArr5 = this.mAnchorDpDt;
                    fArr5[0] = 0.01f;
                    fArr5[1] = 0.01f;
                }
                float max = Math.max(Math.min(progress2 + (this.mTouchDirectionX != 0.0f ? rawX / this.mAnchorDpDt[0] : rawY / this.mAnchorDpDt[1]), 1.0f), 0.0f);
                if (this.mOnTouchUp == 6) {
                    max = Math.max(max, 0.01f);
                }
                if (this.mOnTouchUp == 7) {
                    max = Math.min(max, 0.99f);
                }
                float progress3 = this.mMotionLayout.getProgress();
                if (max != progress3) {
                    int i6 = (progress3 > 0.0f ? 1 : (progress3 == 0.0f ? 0 : -1));
                    if (i6 == 0 || progress3 == 1.0f) {
                        this.mMotionLayout.J(i6 == 0);
                    }
                    this.mMotionLayout.setProgress(max);
                    motionTracker.computeCurrentVelocity(1000);
                    this.mMotionLayout.f2081i = this.mTouchDirectionX != 0.0f ? motionTracker.getXVelocity() / this.mAnchorDpDt[0] : motionTracker.getYVelocity() / this.mAnchorDpDt[1];
                } else {
                    this.mMotionLayout.f2081i = 0.0f;
                }
                this.mLastTouchX = motionEvent.getRawX();
                this.mLastTouchY = motionEvent.getRawY();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void i(MotionEvent motionEvent, MotionLayout.MotionTracker motionTracker, int i2, MotionScene motionScene) {
        View findViewById;
        float left;
        int i3;
        float rawY;
        int i4;
        float f2;
        int i5;
        float[] fArr;
        View findViewById2;
        double atan2;
        float[] fArr2;
        motionTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastTouchX = motionEvent.getRawX();
            this.mLastTouchY = motionEvent.getRawY();
            this.mDragStarted = false;
        } else if (action != 1) {
            if (action != 2) {
                return;
            }
            motionEvent.getRawY();
            motionEvent.getRawX();
            float width = this.mMotionLayout.getWidth() / 2.0f;
            float height = this.mMotionLayout.getHeight() / 2.0f;
            int i6 = this.mRotationCenterId;
            if (i6 != -1) {
                View findViewById3 = this.mMotionLayout.findViewById(i6);
                this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
                height = ((findViewById3.getTop() + findViewById3.getBottom()) / 2.0f) + this.mTempLoc[1];
                width = this.mTempLoc[0] + ((findViewById3.getLeft() + findViewById3.getRight()) / 2.0f);
            } else {
                int i7 = this.mTouchAnchorId;
                if (i7 != -1) {
                    if (this.mMotionLayout.findViewById(this.mMotionLayout.O(i7).getAnimateRelativeTo()) == null) {
                        Log.e(TAG, "could not find view to animate to");
                    } else {
                        this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
                        width = this.mTempLoc[0] + ((findViewById2.getLeft() + findViewById2.getRight()) / 2.0f);
                        height = this.mTempLoc[1] + ((findViewById2.getTop() + findViewById2.getBottom()) / 2.0f);
                    }
                }
            }
            float rawX = motionEvent.getRawX() - width;
            float rawY2 = motionEvent.getRawY() - height;
            float atan22 = (float) (((Math.atan2(motionEvent.getRawY() - height, motionEvent.getRawX() - width) - Math.atan2(this.mLastTouchY - height, this.mLastTouchX - width)) * 180.0d) / 3.141592653589793d);
            if (atan22 > 330.0f) {
                atan22 -= 360.0f;
            } else if (atan22 < -330.0f) {
                atan22 += 360.0f;
            }
            if (Math.abs(atan22) > 0.01d || this.mDragStarted) {
                float progress = this.mMotionLayout.getProgress();
                if (!this.mDragStarted) {
                    this.mDragStarted = true;
                    this.mMotionLayout.setProgress(progress);
                }
                int i8 = this.mTouchAnchorId;
                if (i8 != -1) {
                    this.mMotionLayout.M(i8, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                    this.mAnchorDpDt[1] = (float) Math.toDegrees(fArr2[1]);
                } else {
                    this.mAnchorDpDt[1] = 360.0f;
                }
                float max = Math.max(Math.min(progress + ((atan22 * this.mDragScale) / this.mAnchorDpDt[1]), 1.0f), 0.0f);
                float progress2 = this.mMotionLayout.getProgress();
                if (max != progress2) {
                    int i9 = (progress2 > 0.0f ? 1 : (progress2 == 0.0f ? 0 : -1));
                    if (i9 == 0 || progress2 == 1.0f) {
                        this.mMotionLayout.J(i9 == 0);
                    }
                    this.mMotionLayout.setProgress(max);
                    motionTracker.computeCurrentVelocity(1000);
                    float xVelocity = motionTracker.getXVelocity();
                    double yVelocity = motionTracker.getYVelocity();
                    double d2 = xVelocity;
                    this.mMotionLayout.f2081i = (float) Math.toDegrees((float) ((Math.hypot(yVelocity, d2) * Math.sin(Math.atan2(yVelocity, d2) - atan2)) / Math.hypot(rawX, rawY2)));
                } else {
                    this.mMotionLayout.f2081i = 0.0f;
                }
                this.mLastTouchX = motionEvent.getRawX();
                this.mLastTouchY = motionEvent.getRawY();
            }
        } else {
            this.mDragStarted = false;
            motionTracker.computeCurrentVelocity(16);
            float xVelocity2 = motionTracker.getXVelocity();
            float yVelocity2 = motionTracker.getYVelocity();
            float progress3 = this.mMotionLayout.getProgress();
            float width2 = this.mMotionLayout.getWidth() / 2.0f;
            float height2 = this.mMotionLayout.getHeight() / 2.0f;
            int i10 = this.mRotationCenterId;
            if (i10 == -1) {
                int i11 = this.mTouchAnchorId;
                if (i11 != -1) {
                    findViewById = this.mMotionLayout.findViewById(this.mMotionLayout.O(i11).getAnimateRelativeTo());
                    this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
                    left = this.mTempLoc[0] + ((findViewById.getLeft() + findViewById.getRight()) / 2.0f);
                    i3 = this.mTempLoc[1];
                }
                float rawX2 = motionEvent.getRawX() - width2;
                double degrees = Math.toDegrees(Math.atan2(motionEvent.getRawY() - height2, rawX2));
                i4 = this.mTouchAnchorId;
                if (i4 == -1) {
                    this.mMotionLayout.M(i4, progress3, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                    this.mAnchorDpDt[1] = (float) Math.toDegrees(fArr[1]);
                } else {
                    this.mAnchorDpDt[1] = 360.0f;
                }
                float degrees2 = ((float) (Math.toDegrees(Math.atan2(yVelocity2 + rawY, xVelocity2 + rawX2)) - degrees)) * 62.5f;
                f2 = Float.isNaN(degrees2) ? (((degrees2 * 3.0f) * this.mDragScale) / this.mAnchorDpDt[1]) + progress3 : progress3;
                if (f2 == 0.0f && f2 != 1.0f && (i5 = this.mOnTouchUp) != 3) {
                    float f3 = (degrees2 * this.mDragScale) / this.mAnchorDpDt[1];
                    float f4 = ((double) f2) < 0.5d ? 0.0f : 1.0f;
                    if (i5 == 6) {
                        if (progress3 + f3 < 0.0f) {
                            f3 = Math.abs(f3);
                        }
                        f4 = 1.0f;
                    }
                    if (this.mOnTouchUp == 7) {
                        if (progress3 + f3 > 1.0f) {
                            f3 = -Math.abs(f3);
                        }
                        f4 = 0.0f;
                    }
                    this.mMotionLayout.touchAnimateTo(this.mOnTouchUp, f4, f3 * 3.0f);
                    if (0.0f < progress3 && 1.0f > progress3) {
                        return;
                    }
                } else if (0.0f < f2 && 1.0f > f2) {
                    return;
                }
                this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
            }
            findViewById = this.mMotionLayout.findViewById(i10);
            this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
            left = this.mTempLoc[0] + ((findViewById.getLeft() + findViewById.getRight()) / 2.0f);
            i3 = this.mTempLoc[1];
            height2 = i3 + ((findViewById.getTop() + findViewById.getBottom()) / 2.0f);
            width2 = left;
            float rawX22 = motionEvent.getRawX() - width2;
            double degrees3 = Math.toDegrees(Math.atan2(motionEvent.getRawY() - height2, rawX22));
            i4 = this.mTouchAnchorId;
            if (i4 == -1) {
            }
            float degrees22 = ((float) (Math.toDegrees(Math.atan2(yVelocity2 + rawY, xVelocity2 + rawX22)) - degrees3)) * 62.5f;
            if (Float.isNaN(degrees22)) {
            }
            if (f2 == 0.0f) {
            }
            if (0.0f < f2) {
                return;
            }
            this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(float f2, float f3) {
        float progress = this.mMotionLayout.getProgress();
        if (!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(progress);
        }
        this.mMotionLayout.M(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f4 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        if (Math.abs((f4 * fArr[0]) + (this.mTouchDirectionY * fArr[1])) < 0.01d) {
            float[] fArr2 = this.mAnchorDpDt;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f5 = this.mTouchDirectionX;
        float max = Math.max(Math.min(progress + (f5 != 0.0f ? (f2 * f5) / this.mAnchorDpDt[0] : (f3 * this.mTouchDirectionY) / this.mAnchorDpDt[1]), 1.0f), 0.0f);
        if (max != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(max);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(float f2, float f3) {
        this.mDragStarted = false;
        float progress = this.mMotionLayout.getProgress();
        this.mMotionLayout.M(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f4 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        float f5 = fArr[0];
        float f6 = this.mTouchDirectionY;
        float f7 = fArr[1];
        float f8 = f4 != 0.0f ? (f2 * f4) / fArr[0] : (f3 * f6) / fArr[1];
        if (!Float.isNaN(f8)) {
            progress += f8 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i2 = this.mOnTouchUp;
            if ((i2 != 3) && z) {
                this.mMotionLayout.touchAnimateTo(i2, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(float f2, float f3) {
        this.mLastTouchX = f2;
        this.mLastTouchY = f3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(float f2, float f3) {
        this.mLastTouchX = f2;
        this.mLastTouchY = f3;
        this.mDragStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        View view;
        int i2 = this.mTouchAnchorId;
        if (i2 != -1) {
            view = this.mMotionLayout.findViewById(i2);
            if (view == null) {
                Log.e(TAG, "cannot find TouchAnchorId @id/" + Debug.getName(this.mMotionLayout.getContext(), this.mTouchAnchorId));
            }
        } else {
            view = null;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            nestedScrollView.setOnTouchListener(new View.OnTouchListener(this) { // from class: androidx.constraintlayout.motion.widget.TouchResponse.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return false;
                }
            });
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener(this) { // from class: androidx.constraintlayout.motion.widget.TouchResponse.2
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public void onScrollChange(NestedScrollView nestedScrollView2, int i3, int i4, int i5, int i6) {
                }
            });
        }
    }

    public void setAnchorId(int i2) {
        this.mTouchAnchorId = i2;
    }

    public void setMaxAcceleration(float f2) {
        this.mMaxAcceleration = f2;
    }

    public void setMaxVelocity(float f2) {
        this.mMaxVelocity = f2;
    }

    public void setRTL(boolean z) {
        if (z) {
            float[][] fArr = TOUCH_DIRECTION;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = TOUCH_SIDES;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = TOUCH_DIRECTION;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = TOUCH_SIDES;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = TOUCH_SIDES;
        int i2 = this.mTouchAnchorSide;
        this.mTouchAnchorX = fArr5[i2][0];
        this.mTouchAnchorY = fArr5[i2][1];
        int i3 = this.mTouchSide;
        float[][] fArr6 = TOUCH_DIRECTION;
        if (i3 >= fArr6.length) {
            return;
        }
        this.mTouchDirectionX = fArr6[i3][0];
        this.mTouchDirectionY = fArr6[i3][1];
    }

    public void setTouchAnchorLocation(float f2, float f3) {
        this.mTouchAnchorX = f2;
        this.mTouchAnchorY = f3;
    }

    public void setTouchUpMode(int i2) {
        this.mOnTouchUp = i2;
    }

    public String toString() {
        if (Float.isNaN(this.mTouchDirectionX)) {
            return Key.ROTATION;
        }
        return this.mTouchDirectionX + " , " + this.mTouchDirectionY;
    }
}
