package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ViewTransition {
    public static final String CONSTRAINT_OVERRIDE = "ConstraintOverride";
    public static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
    public static final String CUSTOM_METHOD = "CustomMethod";
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    public static final String KEY_FRAME_SET_TAG = "KeyFrameSet";
    public static final int ONSTATE_ACTION_DOWN = 1;
    public static final int ONSTATE_ACTION_DOWN_UP = 3;
    public static final int ONSTATE_ACTION_UP = 2;
    public static final int ONSTATE_SHARED_VALUE_SET = 4;
    public static final int ONSTATE_SHARED_VALUE_UNSET = 5;
    private static final int SPLINE_STRING = -1;
    private static String TAG = "ViewTransition";
    private static final int UNSET = -1;
    public static final String VIEW_TRANSITION_TAG = "ViewTransition";

    /* renamed from: a  reason: collision with root package name */
    int f2160a;

    /* renamed from: b  reason: collision with root package name */
    KeyFrames f2161b;

    /* renamed from: c  reason: collision with root package name */
    ConstraintSet.Constraint f2162c;

    /* renamed from: d  reason: collision with root package name */
    Context f2163d;
    private int mId;
    private int mTargetId;
    private String mTargetString;
    private int mOnStateTransition = -1;
    private boolean mDisabled = false;
    private int mPathMotionArc = 0;
    private int mDuration = -1;
    private int mUpDuration = -1;
    private int mDefaultInterpolator = 0;
    private String mDefaultInterpolatorString = null;
    private int mDefaultInterpolatorID = -1;
    private int mSetsTag = -1;
    private int mClearsTag = -1;
    private int mIfTagSet = -1;
    private int mIfTagNotSet = -1;
    private int mSharedValueTarget = -1;
    private int mSharedValueID = -1;
    private int mSharedValueCurrent = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Animate {

        /* renamed from: a  reason: collision with root package name */
        long f2165a;

        /* renamed from: b  reason: collision with root package name */
        MotionController f2166b;

        /* renamed from: c  reason: collision with root package name */
        int f2167c;

        /* renamed from: e  reason: collision with root package name */
        ViewTransitionController f2169e;

        /* renamed from: f  reason: collision with root package name */
        Interpolator f2170f;

        /* renamed from: h  reason: collision with root package name */
        float f2172h;

        /* renamed from: i  reason: collision with root package name */
        float f2173i;

        /* renamed from: j  reason: collision with root package name */
        long f2174j;

        /* renamed from: l  reason: collision with root package name */
        boolean f2176l;
        private final int mClearsTag;
        private final int mSetsTag;

        /* renamed from: d  reason: collision with root package name */
        KeyCache f2168d = new KeyCache();

        /* renamed from: g  reason: collision with root package name */
        boolean f2171g = false;

        /* renamed from: k  reason: collision with root package name */
        Rect f2175k = new Rect();

        Animate(ViewTransitionController viewTransitionController, MotionController motionController, int i2, int i3, int i4, Interpolator interpolator, int i5, int i6) {
            this.f2176l = false;
            this.f2169e = viewTransitionController;
            this.f2166b = motionController;
            this.f2167c = i3;
            long nanoTime = System.nanoTime();
            this.f2165a = nanoTime;
            this.f2174j = nanoTime;
            this.f2169e.b(this);
            this.f2170f = interpolator;
            this.mSetsTag = i5;
            this.mClearsTag = i6;
            if (i4 == 3) {
                this.f2176l = true;
            }
            this.f2173i = i2 == 0 ? Float.MAX_VALUE : 1.0f / i2;
            a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
            if (this.f2171g) {
                c();
            } else {
                b();
            }
        }

        void b() {
            long nanoTime = System.nanoTime();
            this.f2174j = nanoTime;
            float f2 = this.f2172h + (((float) ((nanoTime - this.f2174j) * 1.0E-6d)) * this.f2173i);
            this.f2172h = f2;
            if (f2 >= 1.0f) {
                this.f2172h = 1.0f;
            }
            Interpolator interpolator = this.f2170f;
            float interpolation = interpolator == null ? this.f2172h : interpolator.getInterpolation(this.f2172h);
            MotionController motionController = this.f2166b;
            boolean n2 = motionController.n(motionController.f2069b, interpolation, nanoTime, this.f2168d);
            if (this.f2172h >= 1.0f) {
                if (this.mSetsTag != -1) {
                    this.f2166b.getView().setTag(this.mSetsTag, Long.valueOf(System.nanoTime()));
                }
                if (this.mClearsTag != -1) {
                    this.f2166b.getView().setTag(this.mClearsTag, null);
                }
                if (!this.f2176l) {
                    this.f2169e.h(this);
                }
            }
            if (this.f2172h < 1.0f || n2) {
                this.f2169e.f();
            }
        }

        void c() {
            long nanoTime = System.nanoTime();
            this.f2174j = nanoTime;
            float f2 = this.f2172h - (((float) ((nanoTime - this.f2174j) * 1.0E-6d)) * this.f2173i);
            this.f2172h = f2;
            if (f2 < 0.0f) {
                this.f2172h = 0.0f;
            }
            Interpolator interpolator = this.f2170f;
            float interpolation = interpolator == null ? this.f2172h : interpolator.getInterpolation(this.f2172h);
            MotionController motionController = this.f2166b;
            boolean n2 = motionController.n(motionController.f2069b, interpolation, nanoTime, this.f2168d);
            if (this.f2172h <= 0.0f) {
                if (this.mSetsTag != -1) {
                    this.f2166b.getView().setTag(this.mSetsTag, Long.valueOf(System.nanoTime()));
                }
                if (this.mClearsTag != -1) {
                    this.f2166b.getView().setTag(this.mClearsTag, null);
                }
                this.f2169e.h(this);
            }
            if (this.f2172h > 0.0f || n2) {
                this.f2169e.f();
            }
        }

        void d(boolean z) {
            int i2;
            this.f2171g = z;
            if (z && (i2 = this.f2167c) != -1) {
                this.f2173i = i2 == 0 ? Float.MAX_VALUE : 1.0f / i2;
            }
            this.f2169e.f();
            this.f2174j = System.nanoTime();
        }

        public void reactTo(int i2, float f2, float f3) {
            if (i2 == 1) {
                if (this.f2171g) {
                    return;
                }
                d(true);
            } else if (i2 != 2) {
            } else {
                this.f2166b.getView().getHitRect(this.f2175k);
                if (this.f2175k.contains((int) f2, (int) f3) || this.f2171g) {
                    return;
                }
                d(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTransition(Context context, XmlPullParser xmlPullParser) {
        boolean z;
        this.f2163d = context;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    switch (name.hashCode()) {
                        case -1962203927:
                            if (name.equals(CONSTRAINT_OVERRIDE)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case -1239391468:
                            if (name.equals(KEY_FRAME_SET_TAG)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 61998586:
                            if (name.equals(VIEW_TRANSITION_TAG)) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        case 366511058:
                            if (name.equals(CUSTOM_METHOD)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 1791837707:
                            if (name.equals(CUSTOM_ATTRIBUTE)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        default:
                            z = true;
                            break;
                    }
                    if (!z) {
                        parseViewTransitionTags(context, xmlPullParser);
                    } else if (z) {
                        this.f2161b = new KeyFrames(context, xmlPullParser);
                    } else if (z) {
                        this.f2162c = ConstraintSet.buildDelta(context, xmlPullParser);
                    } else if (z || z) {
                        ConstraintAttribute.parse(context, xmlPullParser, this.f2162c.mCustomConstraints);
                    } else {
                        Log.e(TAG, Debug.getLoc() + " unknown tag " + name);
                        Log.e(TAG, ".xml:" + xmlPullParser.getLineNumber());
                    }
                } else if (eventType != 3) {
                    continue;
                } else if (VIEW_TRANSITION_TAG.equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyTransition$0(View[] viewArr) {
        if (this.mSetsTag != -1) {
            for (View view : viewArr) {
                view.setTag(this.mSetsTag, Long.valueOf(System.nanoTime()));
            }
        }
        if (this.mClearsTag != -1) {
            for (View view2 : viewArr) {
                view2.setTag(this.mClearsTag, null);
            }
        }
    }

    private void parseViewTransitionTags(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.ViewTransition);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.ViewTransition_android_id) {
                this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
            } else if (index == R.styleable.ViewTransition_motionTarget) {
                if (MotionLayout.IS_IN_EDIT_MODE) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                    this.mTargetId = resourceId;
                    if (resourceId != -1) {
                    }
                    this.mTargetString = obtainStyledAttributes.getString(index);
                } else {
                    if (obtainStyledAttributes.peekValue(index).type != 3) {
                        this.mTargetId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                    }
                    this.mTargetString = obtainStyledAttributes.getString(index);
                }
            } else if (index == R.styleable.ViewTransition_onStateTransition) {
                this.mOnStateTransition = obtainStyledAttributes.getInt(index, this.mOnStateTransition);
            } else if (index == R.styleable.ViewTransition_transitionDisable) {
                this.mDisabled = obtainStyledAttributes.getBoolean(index, this.mDisabled);
            } else if (index == R.styleable.ViewTransition_pathMotionArc) {
                this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
            } else if (index == R.styleable.ViewTransition_duration) {
                this.mDuration = obtainStyledAttributes.getInt(index, this.mDuration);
            } else if (index == R.styleable.ViewTransition_upDuration) {
                this.mUpDuration = obtainStyledAttributes.getInt(index, this.mUpDuration);
            } else if (index == R.styleable.ViewTransition_viewTransitionMode) {
                this.f2160a = obtainStyledAttributes.getInt(index, this.f2160a);
            } else if (index == R.styleable.ViewTransition_motionInterpolator) {
                int i3 = obtainStyledAttributes.peekValue(index).type;
                if (i3 == 1) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, -1);
                    this.mDefaultInterpolatorID = resourceId2;
                    if (resourceId2 == -1) {
                    }
                    this.mDefaultInterpolator = -2;
                } else if (i3 == 3) {
                    String string = obtainStyledAttributes.getString(index);
                    this.mDefaultInterpolatorString = string;
                    if (string == null || string.indexOf("/") <= 0) {
                        this.mDefaultInterpolator = -1;
                    } else {
                        this.mDefaultInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                        this.mDefaultInterpolator = -2;
                    }
                } else {
                    this.mDefaultInterpolator = obtainStyledAttributes.getInteger(index, this.mDefaultInterpolator);
                }
            } else if (index == R.styleable.ViewTransition_setsTag) {
                this.mSetsTag = obtainStyledAttributes.getResourceId(index, this.mSetsTag);
            } else if (index == R.styleable.ViewTransition_clearsTag) {
                this.mClearsTag = obtainStyledAttributes.getResourceId(index, this.mClearsTag);
            } else if (index == R.styleable.ViewTransition_ifTagSet) {
                this.mIfTagSet = obtainStyledAttributes.getResourceId(index, this.mIfTagSet);
            } else if (index == R.styleable.ViewTransition_ifTagNotSet) {
                this.mIfTagNotSet = obtainStyledAttributes.getResourceId(index, this.mIfTagNotSet);
            } else if (index == R.styleable.ViewTransition_SharedValueId) {
                this.mSharedValueID = obtainStyledAttributes.getResourceId(index, this.mSharedValueID);
            } else if (index == R.styleable.ViewTransition_SharedValue) {
                this.mSharedValueTarget = obtainStyledAttributes.getInteger(index, this.mSharedValueTarget);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void updateTransition(MotionScene.Transition transition, View view) {
        int i2 = this.mDuration;
        if (i2 != -1) {
            transition.setDuration(i2);
        }
        transition.setPathMotionArc(this.mPathMotionArc);
        transition.setInterpolatorInfo(this.mDefaultInterpolator, this.mDefaultInterpolatorString, this.mDefaultInterpolatorID);
        int id = view.getId();
        KeyFrames keyFrames = this.f2161b;
        if (keyFrames != null) {
            ArrayList<Key> keyFramesForView = keyFrames.getKeyFramesForView(-1);
            KeyFrames keyFrames2 = new KeyFrames();
            Iterator<Key> it = keyFramesForView.iterator();
            while (it.hasNext()) {
                keyFrames2.addKey(it.next().m4clone().setViewId(id));
            }
            transition.addKeyFrame(keyFrames2);
        }
    }

    void b(ViewTransitionController viewTransitionController, MotionLayout motionLayout, View view) {
        MotionController motionController = new MotionController(view);
        motionController.q(view);
        this.f2161b.addAllFrames(motionController);
        motionController.setup(motionLayout.getWidth(), motionLayout.getHeight(), this.mDuration, System.nanoTime());
        new Animate(viewTransitionController, motionController, this.mDuration, this.mUpDuration, this.mOnStateTransition, f(motionLayout.getContext()), this.mSetsTag, this.mClearsTag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(ViewTransitionController viewTransitionController, MotionLayout motionLayout, int i2, ConstraintSet constraintSet, final View... viewArr) {
        int[] constraintSetIds;
        if (this.mDisabled) {
            return;
        }
        int i3 = this.f2160a;
        if (i3 == 2) {
            b(viewTransitionController, motionLayout, viewArr[0]);
            return;
        }
        if (i3 == 1) {
            for (int i4 : motionLayout.getConstraintSetIds()) {
                if (i4 != i2) {
                    ConstraintSet constraintSet2 = motionLayout.getConstraintSet(i4);
                    for (View view : viewArr) {
                        ConstraintSet.Constraint constraint = constraintSet2.getConstraint(view.getId());
                        ConstraintSet.Constraint constraint2 = this.f2162c;
                        if (constraint2 != null) {
                            constraint2.applyDelta(constraint);
                            constraint.mCustomConstraints.putAll(this.f2162c.mCustomConstraints);
                        }
                    }
                }
            }
        }
        ConstraintSet constraintSet3 = new ConstraintSet();
        constraintSet3.clone(constraintSet);
        for (View view2 : viewArr) {
            ConstraintSet.Constraint constraint3 = constraintSet3.getConstraint(view2.getId());
            ConstraintSet.Constraint constraint4 = this.f2162c;
            if (constraint4 != null) {
                constraint4.applyDelta(constraint3);
                constraint3.mCustomConstraints.putAll(this.f2162c.mCustomConstraints);
            }
        }
        motionLayout.updateState(i2, constraintSet3);
        int i5 = R.id.view_transition;
        motionLayout.updateState(i5, constraintSet);
        motionLayout.setState(i5, -1, -1);
        MotionScene.Transition transition = new MotionScene.Transition(-1, motionLayout.f2078f, i5, i2);
        for (View view3 : viewArr) {
            updateTransition(transition, view3);
        }
        motionLayout.setTransition(transition);
        motionLayout.transitionToEnd(new Runnable() { // from class: androidx.constraintlayout.motion.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                ViewTransition.this.lambda$applyTransition$0(viewArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(View view) {
        int i2 = this.mIfTagSet;
        boolean z = i2 == -1 || view.getTag(i2) != null;
        int i3 = this.mIfTagNotSet;
        return z && (i3 == -1 || view.getTag(i3) == null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.mId;
    }

    Interpolator f(Context context) {
        int i2 = this.mDefaultInterpolator;
        if (i2 != -2) {
            if (i2 == -1) {
                final Easing interpolator = Easing.getInterpolator(this.mDefaultInterpolatorString);
                return new Interpolator(this) { // from class: androidx.constraintlayout.motion.widget.ViewTransition.1
                    @Override // android.animation.TimeInterpolator
                    public float getInterpolation(float f2) {
                        return (float) interpolator.get(f2);
                    }
                };
            } else if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                if (i2 != 6) {
                                    return null;
                                }
                                return new AnticipateInterpolator();
                            }
                            return new OvershootInterpolator();
                        }
                        return new BounceInterpolator();
                    }
                    return new DecelerateInterpolator();
                }
                return new AccelerateInterpolator();
            } else {
                return new AccelerateDecelerateInterpolator();
            }
        }
        return AnimationUtils.loadInterpolator(context, this.mDefaultInterpolatorID);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return !this.mDisabled;
    }

    public int getSharedValue() {
        return this.mSharedValueTarget;
    }

    public int getSharedValueCurrent() {
        return this.mSharedValueCurrent;
    }

    public int getSharedValueID() {
        return this.mSharedValueID;
    }

    public int getStateTransition() {
        return this.mOnStateTransition;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if (!(this.mTargetId == -1 && this.mTargetString == null) && d(view)) {
            if (view.getId() == this.mTargetId) {
                return true;
            }
            return this.mTargetString != null && (view.getLayoutParams() instanceof ConstraintLayout.LayoutParams) && (str = ((ConstraintLayout.LayoutParams) view.getLayoutParams()).constraintTag) != null && str.matches(this.mTargetString);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(boolean z) {
        this.mDisabled = !z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j(int i2) {
        int i3 = this.mOnStateTransition;
        return i3 == 1 ? i2 == 0 : i3 == 2 ? i2 == 1 : i3 == 3 && i2 == 0;
    }

    public void setSharedValue(int i2) {
        this.mSharedValueTarget = i2;
    }

    public void setSharedValueCurrent(int i2) {
        this.mSharedValueCurrent = i2;
    }

    public void setSharedValueID(int i2) {
        this.mSharedValueID = i2;
    }

    public void setStateTransition(int i2) {
        this.mOnStateTransition = i2;
    }

    public String toString() {
        return "ViewTransition(" + Debug.getName(this.f2163d, this.mId) + ")";
    }
}
