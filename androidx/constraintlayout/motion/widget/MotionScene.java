package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
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
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class MotionScene {
    private static final String CONSTRAINTSET_TAG = "ConstraintSet";
    private static final boolean DEBUG = false;
    private static final String INCLUDE_TAG = "include";
    private static final String INCLUDE_TAG_UC = "Include";
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final String KEYFRAMESET_TAG = "KeyFrameSet";
    public static final int LAYOUT_HONOR_REQUEST = 1;
    public static final int LAYOUT_IGNORE_REQUEST = 0;
    private static final int MIN_DURATION = 8;
    private static final String MOTIONSCENE_TAG = "MotionScene";
    private static final String ONCLICK_TAG = "OnClick";
    private static final String ONSWIPE_TAG = "OnSwipe";
    private static final int SPLINE_STRING = -1;
    private static final String STATESET_TAG = "StateSet";
    private static final String TAG = "MotionScene";
    private static final String TRANSITION_TAG = "Transition";
    public static final int UNSET = -1;
    private static final String VIEW_TRANSITION = "ViewTransition";

    /* renamed from: c  reason: collision with root package name */
    final ViewTransitionController f2151c;

    /* renamed from: d  reason: collision with root package name */
    float f2152d;

    /* renamed from: e  reason: collision with root package name */
    float f2153e;
    private MotionEvent mLastTouchDown;
    private final MotionLayout mMotionLayout;
    private boolean mRtl;
    private MotionLayout.MotionTracker mVelocityTracker;

    /* renamed from: a  reason: collision with root package name */
    StateSet f2149a = null;

    /* renamed from: b  reason: collision with root package name */
    Transition f2150b = null;
    private boolean mDisableAutoTransition = false;
    private ArrayList<Transition> mTransitionList = new ArrayList<>();
    private Transition mDefaultTransition = null;
    private ArrayList<Transition> mAbstractTransitionList = new ArrayList<>();
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private HashMap<String, Integer> mConstraintSetIdMap = new HashMap<>();
    private SparseIntArray mDeriveMap = new SparseIntArray();
    private boolean DEBUG_DESKTOP = false;
    private int mDefaultDuration = 400;
    private int mLayoutDuringTransition = 0;
    private boolean mIgnoreTouch = false;
    private boolean mMotionOutsideRegion = false;

    /* loaded from: classes.dex */
    public static class Transition {
        public static final int AUTO_ANIMATE_TO_END = 4;
        public static final int AUTO_ANIMATE_TO_START = 3;
        public static final int AUTO_JUMP_TO_END = 2;
        public static final int AUTO_JUMP_TO_START = 1;
        public static final int AUTO_NONE = 0;
        private int mAutoTransition;
        private int mConstraintSetEnd;
        private int mConstraintSetStart;
        private int mDefaultInterpolator;
        private int mDefaultInterpolatorID;
        private String mDefaultInterpolatorString;
        private boolean mDisable;
        private int mDuration;
        private int mId;
        private boolean mIsAbstract;
        private ArrayList<KeyFrames> mKeyFramesList;
        private int mLayoutDuringTransition;
        private final MotionScene mMotionScene;
        private ArrayList<TransitionOnClick> mOnClicks;
        private int mPathMotionArc;
        private float mStagger;
        private TouchResponse mTouchResponse;
        private int mTransitionFlags;

        /* loaded from: classes.dex */
        public static class TransitionOnClick implements View.OnClickListener {
            public static final int ANIM_TOGGLE = 17;
            public static final int ANIM_TO_END = 1;
            public static final int ANIM_TO_START = 16;
            public static final int JUMP_TO_END = 256;
            public static final int JUMP_TO_START = 4096;

            /* renamed from: a  reason: collision with root package name */
            int f2155a;

            /* renamed from: b  reason: collision with root package name */
            int f2156b;
            private final Transition mTransition;

            public TransitionOnClick(Context context, Transition transition, XmlPullParser xmlPullParser) {
                this.f2155a = -1;
                this.f2156b = 17;
                this.mTransition = transition;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.OnClick);
                int indexCount = obtainStyledAttributes.getIndexCount();
                for (int i2 = 0; i2 < indexCount; i2++) {
                    int index = obtainStyledAttributes.getIndex(i2);
                    if (index == R.styleable.OnClick_targetId) {
                        this.f2155a = obtainStyledAttributes.getResourceId(index, this.f2155a);
                    } else if (index == R.styleable.OnClick_clickAction) {
                        this.f2156b = obtainStyledAttributes.getInt(index, this.f2156b);
                    }
                }
                obtainStyledAttributes.recycle();
            }

            public TransitionOnClick(Transition transition, int i2, int i3) {
                this.f2155a = -1;
                this.f2156b = 17;
                this.mTransition = transition;
                this.f2155a = i2;
                this.f2156b = i3;
            }

            boolean a(Transition transition, MotionLayout motionLayout) {
                Transition transition2 = this.mTransition;
                if (transition2 == transition) {
                    return true;
                }
                int i2 = transition2.mConstraintSetEnd;
                int i3 = this.mTransition.mConstraintSetStart;
                int i4 = motionLayout.f2082j;
                return i3 == -1 ? i4 != i2 : i4 == i3 || i4 == i2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r7v4, types: [android.view.View] */
            public void addOnClickListeners(MotionLayout motionLayout, int i2, Transition transition) {
                int i3 = this.f2155a;
                MotionLayout motionLayout2 = motionLayout;
                if (i3 != -1) {
                    motionLayout2 = motionLayout.findViewById(i3);
                }
                if (motionLayout2 == null) {
                    Log.e(TypedValues.MotionScene.NAME, "OnClick could not find id " + this.f2155a);
                    return;
                }
                int i4 = transition.mConstraintSetStart;
                int i5 = transition.mConstraintSetEnd;
                if (i4 == -1) {
                    motionLayout2.setOnClickListener(this);
                    return;
                }
                int i6 = this.f2156b;
                boolean z = false;
                boolean z2 = ((i6 & 1) != 0 && i2 == i4) | ((i6 & 1) != 0 && i2 == i4) | ((i6 & 256) != 0 && i2 == i4) | ((i6 & 16) != 0 && i2 == i5);
                if ((i6 & 4096) != 0 && i2 == i5) {
                    z = true;
                }
                if (z2 || z) {
                    motionLayout2.setOnClickListener(this);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:44:0x00a3  */
            /* JADX WARN: Removed duplicated region for block: B:61:0x00e6 A[ORIG_RETURN, RETURN] */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onClick(View view) {
                float f2;
                MotionLayout motionLayout = this.mTransition.mMotionScene.mMotionLayout;
                if (!motionLayout.isInteractionEnabled()) {
                    return;
                }
                if (this.mTransition.mConstraintSetStart == -1) {
                    int currentState = motionLayout.getCurrentState();
                    if (currentState == -1) {
                        motionLayout.transitionToState(this.mTransition.mConstraintSetEnd);
                        return;
                    }
                    Transition transition = new Transition(this.mTransition.mMotionScene, this.mTransition);
                    transition.mConstraintSetStart = currentState;
                    transition.mConstraintSetEnd = this.mTransition.mConstraintSetEnd;
                    motionLayout.setTransition(transition);
                    motionLayout.transitionToEnd();
                    return;
                }
                Transition transition2 = this.mTransition.mMotionScene.f2150b;
                int i2 = this.f2156b;
                boolean z = false;
                boolean z2 = ((i2 & 1) == 0 && (i2 & 256) == 0) ? false : true;
                boolean z3 = ((i2 & 16) == 0 && (i2 & 4096) == 0) ? false : true;
                if (z2 && z3) {
                    Transition transition3 = this.mTransition.mMotionScene.f2150b;
                    Transition transition4 = this.mTransition;
                    if (transition3 != transition4) {
                        motionLayout.setTransition(transition4);
                    }
                    if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                        z3 = false;
                    }
                    if (a(transition2, motionLayout)) {
                        return;
                    }
                    if (z && (this.f2156b & 1) != 0) {
                        motionLayout.setTransition(this.mTransition);
                        motionLayout.transitionToEnd();
                        return;
                    } else if (z3 && (this.f2156b & 16) != 0) {
                        motionLayout.setTransition(this.mTransition);
                        motionLayout.transitionToStart();
                        return;
                    } else {
                        if (z && (this.f2156b & 256) != 0) {
                            motionLayout.setTransition(this.mTransition);
                            f2 = 1.0f;
                        } else if (!z3 || (this.f2156b & 4096) == 0) {
                            return;
                        } else {
                            motionLayout.setTransition(this.mTransition);
                            f2 = 0.0f;
                        }
                        motionLayout.setProgress(f2);
                        return;
                    }
                }
                z = z2;
                if (a(transition2, motionLayout)) {
                }
            }

            public void removeOnClickListeners(MotionLayout motionLayout) {
                int i2 = this.f2155a;
                if (i2 == -1) {
                    return;
                }
                View findViewById = motionLayout.findViewById(i2);
                if (findViewById != null) {
                    findViewById.setOnClickListener(null);
                    return;
                }
                Log.e(TypedValues.MotionScene.NAME, " (*)  could not find id " + this.f2155a);
            }
        }

        public Transition(int i2, MotionScene motionScene, int i3, int i4) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList<>();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList<>();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mLayoutDuringTransition = 0;
            this.mTransitionFlags = 0;
            this.mId = i2;
            this.mMotionScene = motionScene;
            this.mConstraintSetStart = i3;
            this.mConstraintSetEnd = i4;
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
        }

        Transition(MotionScene motionScene, Context context, XmlPullParser xmlPullParser) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList<>();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList<>();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mLayoutDuringTransition = 0;
            this.mTransitionFlags = 0;
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
            this.mMotionScene = motionScene;
            fillFromAttributeList(motionScene, context, Xml.asAttributeSet(xmlPullParser));
        }

        Transition(MotionScene motionScene, Transition transition) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList<>();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList<>();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mLayoutDuringTransition = 0;
            this.mTransitionFlags = 0;
            this.mMotionScene = motionScene;
            this.mDuration = motionScene.mDefaultDuration;
            if (transition != null) {
                this.mPathMotionArc = transition.mPathMotionArc;
                this.mDefaultInterpolator = transition.mDefaultInterpolator;
                this.mDefaultInterpolatorString = transition.mDefaultInterpolatorString;
                this.mDefaultInterpolatorID = transition.mDefaultInterpolatorID;
                this.mDuration = transition.mDuration;
                this.mKeyFramesList = transition.mKeyFramesList;
                this.mStagger = transition.mStagger;
                this.mLayoutDuringTransition = transition.mLayoutDuringTransition;
            }
        }

        private void fill(MotionScene motionScene, Context context, TypedArray typedArray) {
            ConstraintSet constraintSet;
            SparseArray sparseArray;
            int i2;
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                if (index == R.styleable.Transition_constraintSetEnd) {
                    this.mConstraintSetEnd = typedArray.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintSetEnd);
                    if ("layout".equals(resourceTypeName)) {
                        constraintSet = new ConstraintSet();
                        constraintSet.load(context, this.mConstraintSetEnd);
                        sparseArray = motionScene.mConstraintSetMap;
                        i2 = this.mConstraintSetEnd;
                        sparseArray.append(i2, constraintSet);
                    } else {
                        if ("xml".equals(resourceTypeName)) {
                            this.mConstraintSetEnd = motionScene.parseInclude(context, this.mConstraintSetEnd);
                        }
                    }
                } else {
                    if (index == R.styleable.Transition_constraintSetStart) {
                        this.mConstraintSetStart = typedArray.getResourceId(index, this.mConstraintSetStart);
                        String resourceTypeName2 = context.getResources().getResourceTypeName(this.mConstraintSetStart);
                        if ("layout".equals(resourceTypeName2)) {
                            constraintSet = new ConstraintSet();
                            constraintSet.load(context, this.mConstraintSetStart);
                            sparseArray = motionScene.mConstraintSetMap;
                            i2 = this.mConstraintSetStart;
                            sparseArray.append(i2, constraintSet);
                        } else if ("xml".equals(resourceTypeName2)) {
                            this.mConstraintSetStart = motionScene.parseInclude(context, this.mConstraintSetStart);
                        }
                    } else if (index == R.styleable.Transition_motionInterpolator) {
                        int i4 = typedArray.peekValue(index).type;
                        if (i4 == 1) {
                            int resourceId = typedArray.getResourceId(index, -1);
                            this.mDefaultInterpolatorID = resourceId;
                            if (resourceId == -1) {
                            }
                            this.mDefaultInterpolator = -2;
                        } else if (i4 == 3) {
                            String string = typedArray.getString(index);
                            this.mDefaultInterpolatorString = string;
                            if (string != null) {
                                if (string.indexOf("/") > 0) {
                                    this.mDefaultInterpolatorID = typedArray.getResourceId(index, -1);
                                    this.mDefaultInterpolator = -2;
                                } else {
                                    this.mDefaultInterpolator = -1;
                                }
                            }
                        } else {
                            this.mDefaultInterpolator = typedArray.getInteger(index, this.mDefaultInterpolator);
                        }
                    } else if (index == R.styleable.Transition_duration) {
                        int i5 = typedArray.getInt(index, this.mDuration);
                        this.mDuration = i5;
                        if (i5 < 8) {
                            this.mDuration = 8;
                        }
                    } else if (index == R.styleable.Transition_staggered) {
                        this.mStagger = typedArray.getFloat(index, this.mStagger);
                    } else if (index == R.styleable.Transition_autoTransition) {
                        this.mAutoTransition = typedArray.getInteger(index, this.mAutoTransition);
                    } else if (index == R.styleable.Transition_android_id) {
                        this.mId = typedArray.getResourceId(index, this.mId);
                    } else if (index == R.styleable.Transition_transitionDisable) {
                        this.mDisable = typedArray.getBoolean(index, this.mDisable);
                    } else if (index == R.styleable.Transition_pathMotionArc) {
                        this.mPathMotionArc = typedArray.getInteger(index, -1);
                    } else if (index == R.styleable.Transition_layoutDuringTransition) {
                        this.mLayoutDuringTransition = typedArray.getInteger(index, 0);
                    } else if (index == R.styleable.Transition_transitionFlags) {
                        this.mTransitionFlags = typedArray.getInteger(index, 0);
                    }
                }
            }
            if (this.mConstraintSetStart == -1) {
                this.mIsAbstract = true;
            }
        }

        private void fillFromAttributeList(MotionScene motionScene, Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transition);
            fill(motionScene, context, obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }

        public void addKeyFrame(KeyFrames keyFrames) {
            this.mKeyFramesList.add(keyFrames);
        }

        public void addOnClick(int i2, int i3) {
            Iterator<TransitionOnClick> it = this.mOnClicks.iterator();
            while (it.hasNext()) {
                TransitionOnClick next = it.next();
                if (next.f2155a == i2) {
                    next.f2156b = i3;
                    return;
                }
            }
            this.mOnClicks.add(new TransitionOnClick(this, i2, i3));
        }

        public void addOnClick(Context context, XmlPullParser xmlPullParser) {
            this.mOnClicks.add(new TransitionOnClick(context, this, xmlPullParser));
        }

        public String debugString(Context context) {
            String resourceEntryName = this.mConstraintSetStart == -1 ? "null" : context.getResources().getResourceEntryName(this.mConstraintSetStart);
            if (this.mConstraintSetEnd == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.mConstraintSetEnd);
        }

        public int getAutoTransition() {
            return this.mAutoTransition;
        }

        public int getDuration() {
            return this.mDuration;
        }

        public int getEndConstraintSetId() {
            return this.mConstraintSetEnd;
        }

        public int getId() {
            return this.mId;
        }

        public List<KeyFrames> getKeyFrameList() {
            return this.mKeyFramesList;
        }

        public int getLayoutDuringTransition() {
            return this.mLayoutDuringTransition;
        }

        public List<TransitionOnClick> getOnClickList() {
            return this.mOnClicks;
        }

        public int getPathMotionArc() {
            return this.mPathMotionArc;
        }

        public float getStagger() {
            return this.mStagger;
        }

        public int getStartConstraintSetId() {
            return this.mConstraintSetStart;
        }

        public TouchResponse getTouchResponse() {
            return this.mTouchResponse;
        }

        public boolean isEnabled() {
            return !this.mDisable;
        }

        public boolean isTransitionFlag(int i2) {
            return (i2 & this.mTransitionFlags) != 0;
        }

        public void removeOnClick(int i2) {
            TransitionOnClick transitionOnClick;
            Iterator<TransitionOnClick> it = this.mOnClicks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    transitionOnClick = null;
                    break;
                }
                transitionOnClick = it.next();
                if (transitionOnClick.f2155a == i2) {
                    break;
                }
            }
            if (transitionOnClick != null) {
                this.mOnClicks.remove(transitionOnClick);
            }
        }

        public void setAutoTransition(int i2) {
            this.mAutoTransition = i2;
        }

        public void setDuration(int i2) {
            this.mDuration = Math.max(i2, 8);
        }

        public void setEnable(boolean z) {
            setEnabled(z);
        }

        public void setEnabled(boolean z) {
            this.mDisable = !z;
        }

        public void setInterpolatorInfo(int i2, String str, int i3) {
            this.mDefaultInterpolator = i2;
            this.mDefaultInterpolatorString = str;
            this.mDefaultInterpolatorID = i3;
        }

        public void setLayoutDuringTransition(int i2) {
            this.mLayoutDuringTransition = i2;
        }

        public void setOnSwipe(OnSwipe onSwipe) {
            this.mTouchResponse = onSwipe == null ? null : new TouchResponse(this.mMotionScene.mMotionLayout, onSwipe);
        }

        public void setOnTouchUp(int i2) {
            TouchResponse touchResponse = getTouchResponse();
            if (touchResponse != null) {
                touchResponse.setTouchUpMode(i2);
            }
        }

        public void setPathMotionArc(int i2) {
            this.mPathMotionArc = i2;
        }

        public void setStagger(float f2) {
            this.mStagger = f2;
        }

        public void setTransitionFlag(int i2) {
            this.mTransitionFlags = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionScene(Context context, MotionLayout motionLayout, int i2) {
        this.mMotionLayout = motionLayout;
        this.f2151c = new ViewTransitionController(motionLayout);
        load(context, i2);
        SparseArray<ConstraintSet> sparseArray = this.mConstraintSetMap;
        int i3 = R.id.motion_base;
        sparseArray.put(i3, new ConstraintSet());
        this.mConstraintSetIdMap.put("motion_base", Integer.valueOf(i3));
    }

    public MotionScene(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
        this.f2151c = new ViewTransitionController(motionLayout);
    }

    private int getId(Context context, String str) {
        int i2;
        if (str.contains("/")) {
            i2 = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.DEBUG_DESKTOP) {
                System.out.println("id getMap res = " + i2);
            }
        } else {
            i2 = -1;
        }
        if (i2 == -1) {
            if (str.length() > 1) {
                return Integer.parseInt(str.substring(1));
            }
            Log.e(TypedValues.MotionScene.NAME, "error in parsing id");
            return i2;
        }
        return i2;
    }

    private int getIndex(Transition transition) {
        int i2 = transition.mId;
        if (i2 != -1) {
            for (int i3 = 0; i3 < this.mTransitionList.size(); i3++) {
                if (this.mTransitionList.get(i3).mId == i2) {
                    return i3;
                }
            }
            return -1;
        }
        throw new IllegalArgumentException("The transition must have an id");
    }

    private int getRealID(int i2) {
        int stateGetConstraintID;
        StateSet stateSet = this.f2149a;
        return (stateSet == null || (stateGetConstraintID = stateSet.stateGetConstraintID(i2, -1, -1)) == -1) ? i2 : stateGetConstraintID;
    }

    private boolean hasCycleDependency(int i2) {
        int i3 = this.mDeriveMap.get(i2);
        int size = this.mDeriveMap.size();
        while (i3 > 0) {
            if (i3 == i2) {
                return true;
            }
            int i4 = size - 1;
            if (size < 0) {
                return true;
            }
            i3 = this.mDeriveMap.get(i3);
            size = i4;
        }
        return false;
    }

    private boolean isProcessingTouch() {
        return this.mVelocityTracker != null;
    }

    private void load(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        Transition transition = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                char c2 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                    continue;
                } else if (eventType != 2) {
                    continue;
                } else {
                    String name = xml.getName();
                    if (this.DEBUG_DESKTOP) {
                        System.out.println("parsing = " + name);
                    }
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals(CONSTRAINTSET_TAG)) {
                                c2 = 5;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1239391468:
                            if (name.equals("KeyFrameSet")) {
                                c2 = '\b';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -687739768:
                            if (name.equals(INCLUDE_TAG_UC)) {
                                c2 = 7;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 61998586:
                            if (name.equals("ViewTransition")) {
                                c2 = '\t';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 269306229:
                            if (name.equals(TRANSITION_TAG)) {
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 312750793:
                            if (name.equals(ONCLICK_TAG)) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 327855227:
                            if (name.equals(ONSWIPE_TAG)) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 793277014:
                            if (name.equals(TypedValues.MotionScene.NAME)) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1382829617:
                            if (name.equals(STATESET_TAG)) {
                                c2 = 4;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1942574248:
                            if (name.equals(INCLUDE_TAG)) {
                                c2 = 6;
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
                            parseMotionSceneTags(context, xml);
                            continue;
                        case 1:
                            ArrayList<Transition> arrayList = this.mTransitionList;
                            Transition transition2 = new Transition(this, context, xml);
                            arrayList.add(transition2);
                            if (this.f2150b == null && !transition2.mIsAbstract) {
                                this.f2150b = transition2;
                                if (transition2.mTouchResponse != null) {
                                    this.f2150b.mTouchResponse.setRTL(this.mRtl);
                                }
                            }
                            if (transition2.mIsAbstract) {
                                if (transition2.mConstraintSetEnd == -1) {
                                    this.mDefaultTransition = transition2;
                                } else {
                                    this.mAbstractTransitionList.add(transition2);
                                }
                                this.mTransitionList.remove(transition2);
                            }
                            transition = transition2;
                            continue;
                        case 2:
                            if (transition == null) {
                                String resourceEntryName = context.getResources().getResourceEntryName(i2);
                                int lineNumber = xml.getLineNumber();
                                StringBuilder sb = new StringBuilder();
                                sb.append(" OnSwipe (");
                                sb.append(resourceEntryName);
                                sb.append(".xml:");
                                sb.append(lineNumber);
                                sb.append(")");
                            }
                            if (transition != null) {
                                transition.mTouchResponse = new TouchResponse(context, this.mMotionLayout, xml);
                                continue;
                            } else {
                                continue;
                            }
                        case 3:
                            if (transition != null) {
                                transition.addOnClick(context, xml);
                                continue;
                            } else {
                                continue;
                            }
                        case 4:
                            this.f2149a = new StateSet(context, xml);
                            continue;
                        case 5:
                            parseConstraintSet(context, xml);
                            continue;
                        case 6:
                        case 7:
                            parseInclude(context, xml);
                            continue;
                        case '\b':
                            KeyFrames keyFrames = new KeyFrames(context, xml);
                            if (transition != null) {
                                transition.mKeyFramesList.add(keyFrames);
                                continue;
                            } else {
                                continue;
                            }
                        case '\t':
                            this.f2151c.add(new ViewTransition(context, xml));
                            continue;
                        default:
                            continue;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
        if (r8.equals("constraintRotate") == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int parseConstraintSet(Context context, XmlPullParser xmlPullParser) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.setForceId(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        while (true) {
            char c2 = 1;
            if (i2 >= attributeCount) {
                if (i3 != -1) {
                    if (this.mMotionLayout.f2088p != 0) {
                        constraintSet.setValidateOnParse(true);
                    }
                    constraintSet.load(context, xmlPullParser);
                    if (i4 != -1) {
                        this.mDeriveMap.put(i3, i4);
                    }
                    this.mConstraintSetMap.put(i3, constraintSet);
                }
                return i3;
            }
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (this.DEBUG_DESKTOP) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            switch (attributeName.hashCode()) {
                case -1496482599:
                    if (attributeName.equals("deriveConstraintsFrom")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1153153640:
                    break;
                case 3355:
                    if (attributeName.equals("id")) {
                        c2 = 2;
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
                    i4 = getId(context, attributeValue);
                    break;
                case 1:
                    constraintSet.mRotate = Integer.parseInt(attributeValue);
                    break;
                case 2:
                    i3 = getId(context, attributeValue);
                    this.mConstraintSetIdMap.put(stripID(attributeValue), Integer.valueOf(i3));
                    constraintSet.mIdString = Debug.getName(context, i3);
                    break;
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int parseInclude(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && CONSTRAINTSET_TAG.equals(name)) {
                    return parseConstraintSet(context, xml);
                }
            }
            return -1;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    private void parseInclude(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.include);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.include_constraintSet) {
                parseInclude(context, obtainStyledAttributes.getResourceId(index, -1));
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void parseMotionSceneTags(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.MotionScene_defaultDuration) {
                int i3 = obtainStyledAttributes.getInt(index, this.mDefaultDuration);
                this.mDefaultDuration = i3;
                if (i3 < 8) {
                    this.mDefaultDuration = 8;
                }
            } else if (index == R.styleable.MotionScene_layoutDuringTransition) {
                this.mLayoutDuringTransition = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void readConstraintChain(int i2, MotionLayout motionLayout) {
        ConstraintSet constraintSet = this.mConstraintSetMap.get(i2);
        constraintSet.derivedState = constraintSet.mIdString;
        int i3 = this.mDeriveMap.get(i2);
        if (i3 > 0) {
            readConstraintChain(i3, motionLayout);
            ConstraintSet constraintSet2 = this.mConstraintSetMap.get(i3);
            if (constraintSet2 == null) {
                Log.e(TypedValues.MotionScene.NAME, "ERROR! invalid deriveConstraintsFrom: @id/" + Debug.getName(this.mMotionLayout.getContext(), i3));
                return;
            }
            constraintSet.derivedState += "/" + constraintSet2.derivedState;
            constraintSet.readFallback(constraintSet2);
        } else {
            constraintSet.derivedState += "  layout";
            constraintSet.readFallback(motionLayout);
        }
        constraintSet.applyDeltaFrom(constraintSet);
    }

    public static String stripID(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf(47);
        return indexOf < 0 ? str : str.substring(indexOf + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 != (-1)) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void A(int i2, int i3) {
        int i4;
        int i5;
        StateSet stateSet = this.f2149a;
        if (stateSet != null) {
            i4 = stateSet.stateGetConstraintID(i2, -1, -1);
            if (i4 == -1) {
                i4 = i2;
            }
            i5 = this.f2149a.stateGetConstraintID(i3, -1, -1);
        } else {
            i4 = i2;
        }
        i5 = i3;
        Transition transition = this.f2150b;
        if (transition != null && transition.mConstraintSetEnd == i3 && this.f2150b.mConstraintSetStart == i2) {
            return;
        }
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if ((next.mConstraintSetEnd == i5 && next.mConstraintSetStart == i4) || (next.mConstraintSetEnd == i3 && next.mConstraintSetStart == i2)) {
                this.f2150b = next;
                if (next == null || next.mTouchResponse == null) {
                    return;
                }
                this.f2150b.mTouchResponse.setRTL(this.mRtl);
                return;
            }
        }
        Transition transition2 = this.mDefaultTransition;
        Iterator<Transition> it2 = this.mAbstractTransitionList.iterator();
        while (it2.hasNext()) {
            Transition next2 = it2.next();
            if (next2.mConstraintSetEnd == i3) {
                transition2 = next2;
            }
        }
        Transition transition3 = new Transition(this, transition2);
        transition3.mConstraintSetStart = i4;
        transition3.mConstraintSetEnd = i5;
        if (i4 != -1) {
            this.mTransitionList.add(transition3);
        }
        this.f2150b = transition3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return;
        }
        this.f2150b.mTouchResponse.n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean C() {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            if (it.next().mTouchResponse != null) {
                return true;
            }
        }
        Transition transition = this.f2150b;
        return (transition == null || transition.mTouchResponse == null) ? false : true;
    }

    public void addOnClickListeners(MotionLayout motionLayout, int i2) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mOnClicks.size() > 0) {
                Iterator it2 = next.mOnClicks.iterator();
                while (it2.hasNext()) {
                    ((Transition.TransitionOnClick) it2.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it3 = this.mAbstractTransitionList.iterator();
        while (it3.hasNext()) {
            Transition next2 = it3.next();
            if (next2.mOnClicks.size() > 0) {
                Iterator it4 = next2.mOnClicks.iterator();
                while (it4.hasNext()) {
                    ((Transition.TransitionOnClick) it4.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it5 = this.mTransitionList.iterator();
        while (it5.hasNext()) {
            Transition next3 = it5.next();
            if (next3.mOnClicks.size() > 0) {
                Iterator it6 = next3.mOnClicks.iterator();
                while (it6.hasNext()) {
                    ((Transition.TransitionOnClick) it6.next()).addOnClickListeners(motionLayout, i2, next3);
                }
            }
        }
        Iterator<Transition> it7 = this.mAbstractTransitionList.iterator();
        while (it7.hasNext()) {
            Transition next4 = it7.next();
            if (next4.mOnClicks.size() > 0) {
                Iterator it8 = next4.mOnClicks.iterator();
                while (it8.hasNext()) {
                    ((Transition.TransitionOnClick) it8.next()).addOnClickListeners(motionLayout, i2, next4);
                }
            }
        }
    }

    public void addTransition(Transition transition) {
        int index = getIndex(transition);
        if (index == -1) {
            this.mTransitionList.add(transition);
        } else {
            this.mTransitionList.set(index, transition);
        }
    }

    public boolean applyViewTransition(int i2, MotionController motionController) {
        return this.f2151c.d(i2, motionController);
    }

    public Transition bestTransitionFor(int i2, float f2, float f3, MotionEvent motionEvent) {
        if (i2 != -1) {
            List<Transition> transitionsWithState = getTransitionsWithState(i2);
            float f4 = 0.0f;
            Transition transition = null;
            RectF rectF = new RectF();
            for (Transition transition2 : transitionsWithState) {
                if (!transition2.mDisable && transition2.mTouchResponse != null) {
                    transition2.mTouchResponse.setRTL(this.mRtl);
                    RectF f5 = transition2.mTouchResponse.f(this.mMotionLayout, rectF);
                    if (f5 == null || motionEvent == null || f5.contains(motionEvent.getX(), motionEvent.getY())) {
                        RectF b2 = transition2.mTouchResponse.b(this.mMotionLayout, rectF);
                        if (b2 == null || motionEvent == null || b2.contains(motionEvent.getX(), motionEvent.getY())) {
                            float a2 = transition2.mTouchResponse.a(f2, f3);
                            if (transition2.mTouchResponse.f2159c && motionEvent != null) {
                                float x = motionEvent.getX() - transition2.mTouchResponse.f2157a;
                                float y = motionEvent.getY() - transition2.mTouchResponse.f2158b;
                                a2 = ((float) (Math.atan2(f3 + y, f2 + x) - Math.atan2(x, y))) * 10.0f;
                            }
                            float f6 = a2 * (transition2.mConstraintSetEnd == i2 ? -1.0f : 1.1f);
                            if (f6 > f4) {
                                transition = transition2;
                                f4 = f6;
                            }
                        }
                    }
                }
            }
            return transition;
        }
        return this.f2150b;
    }

    public void disableAutoTransition(boolean z) {
        this.mDisableAutoTransition = z;
    }

    public void enableViewTransition(int i2, boolean z) {
        this.f2151c.e(i2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f(MotionLayout motionLayout, int i2) {
        Transition transition;
        if (isProcessingTouch() || this.mDisableAutoTransition) {
            return false;
        }
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mAutoTransition != 0 && ((transition = this.f2150b) != next || !transition.isTransitionFlag(2))) {
                if (i2 == next.mConstraintSetStart && (next.mAutoTransition == 4 || next.mAutoTransition == 2)) {
                    MotionLayout.TransitionState transitionState = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(next);
                    if (next.mAutoTransition == 4) {
                        motionLayout.transitionToEnd();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.K(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState);
                        motionLayout.R();
                    }
                    return true;
                } else if (i2 == next.mConstraintSetEnd && (next.mAutoTransition == 3 || next.mAutoTransition == 1)) {
                    MotionLayout.TransitionState transitionState2 = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState2);
                    motionLayout.setTransition(next);
                    if (next.mAutoTransition == 3) {
                        motionLayout.transitionToStart();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.K(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState2);
                        motionLayout.R();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0;
        }
        return this.f2150b.mTouchResponse.getAutoCompleteMode();
    }

    public int gatPathMotionArc() {
        Transition transition = this.f2150b;
        if (transition != null) {
            return transition.mPathMotionArc;
        }
        return -1;
    }

    public ConstraintSet getConstraintSet(Context context, String str) {
        if (this.DEBUG_DESKTOP) {
            PrintStream printStream = System.out;
            printStream.println("id " + str);
            PrintStream printStream2 = System.out;
            printStream2.println("size " + this.mConstraintSetMap.size());
        }
        for (int i2 = 0; i2 < this.mConstraintSetMap.size(); i2++) {
            int keyAt = this.mConstraintSetMap.keyAt(i2);
            String resourceName = context.getResources().getResourceName(keyAt);
            if (this.DEBUG_DESKTOP) {
                PrintStream printStream3 = System.out;
                printStream3.println("Id for <" + i2 + "> is <" + resourceName + "> looking for <" + str + ">");
            }
            if (str.equals(resourceName)) {
                return this.mConstraintSetMap.get(keyAt);
            }
        }
        return null;
    }

    public int[] getConstraintSetIds() {
        int size = this.mConstraintSetMap.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.mConstraintSetMap.keyAt(i2);
        }
        return iArr;
    }

    public ArrayList<Transition> getDefinedTransitions() {
        return this.mTransitionList;
    }

    public int getDuration() {
        Transition transition = this.f2150b;
        return transition != null ? transition.mDuration : this.mDefaultDuration;
    }

    public Interpolator getInterpolator() {
        int i2 = this.f2150b.mDefaultInterpolator;
        if (i2 != -2) {
            if (i2 == -1) {
                final Easing interpolator = Easing.getInterpolator(this.f2150b.mDefaultInterpolatorString);
                return new Interpolator(this) { // from class: androidx.constraintlayout.motion.widget.MotionScene.1
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
        return AnimationUtils.loadInterpolator(this.mMotionLayout.getContext(), this.f2150b.mDefaultInterpolatorID);
    }

    public void getKeyFrames(MotionController motionController) {
        Transition transition = this.f2150b;
        if (transition != null) {
            Iterator it = transition.mKeyFramesList.iterator();
            while (it.hasNext()) {
                ((KeyFrames) it.next()).addFrames(motionController);
            }
            return;
        }
        Transition transition2 = this.mDefaultTransition;
        if (transition2 != null) {
            Iterator it2 = transition2.mKeyFramesList.iterator();
            while (it2.hasNext()) {
                ((KeyFrames) it2.next()).addFrames(motionController);
            }
        }
    }

    public float getPathPercent(View view, int i2) {
        return 0.0f;
    }

    public float getStaggered() {
        Transition transition = this.f2150b;
        if (transition != null) {
            return transition.mStagger;
        }
        return 0.0f;
    }

    public Transition getTransitionById(int i2) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mId == i2) {
                return next;
            }
        }
        return null;
    }

    public List<Transition> getTransitionsWithState(int i2) {
        int realID = getRealID(i2);
        ArrayList arrayList = new ArrayList();
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.mConstraintSetStart == realID || next.mConstraintSetEnd == realID) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstraintSet h(int i2) {
        return i(i2, -1, -1);
    }

    ConstraintSet i(int i2, int i3, int i4) {
        ConstraintSet constraintSet;
        int stateGetConstraintID;
        if (this.DEBUG_DESKTOP) {
            PrintStream printStream = System.out;
            printStream.println("id " + i2);
            PrintStream printStream2 = System.out;
            printStream2.println("size " + this.mConstraintSetMap.size());
        }
        StateSet stateSet = this.f2149a;
        if (stateSet != null && (stateGetConstraintID = stateSet.stateGetConstraintID(i2, i3, i4)) != -1) {
            i2 = stateGetConstraintID;
        }
        if (this.mConstraintSetMap.get(i2) == null) {
            Log.e(TypedValues.MotionScene.NAME, "Warning could not find ConstraintSet id/" + Debug.getName(this.mMotionLayout.getContext(), i2) + " In MotionScene");
            SparseArray<ConstraintSet> sparseArray = this.mConstraintSetMap;
            constraintSet = sparseArray.get(sparseArray.keyAt(0));
        } else {
            constraintSet = this.mConstraintSetMap.get(i2);
        }
        return constraintSet;
    }

    public boolean isViewTransitionEnabled(int i2) {
        return this.f2151c.g(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        Transition transition = this.f2150b;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetEnd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Key k(Context context, int i2, int i3, int i4) {
        Transition transition = this.f2150b;
        if (transition == null) {
            return null;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            KeyFrames keyFrames = (KeyFrames) it.next();
            for (Integer num : keyFrames.getKeys()) {
                if (i3 == num.intValue()) {
                    Iterator<Key> it2 = keyFrames.getKeyFramesForView(num.intValue()).iterator();
                    while (it2.hasNext()) {
                        Key next = it2.next();
                        if (next.f2041a == i4 && next.f2044d == i2) {
                            return next;
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float l() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.f2150b.mTouchResponse.c();
    }

    public int lookUpConstraintId(String str) {
        Integer num = this.mConstraintSetIdMap.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public String lookUpConstraintName(int i2) {
        for (Map.Entry<String, Integer> entry : this.mConstraintSetIdMap.entrySet()) {
            Integer value = entry.getValue();
            if (value != null && value.intValue() == i2) {
                return entry.getKey();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float m() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.f2150b.mTouchResponse.getMaxVelocity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return false;
        }
        return this.f2150b.mTouchResponse.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float o(float f2, float f3) {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.f2150b.mTouchResponse.e(f2, f3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int p() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0;
        }
        return this.f2150b.mTouchResponse.getSpringBoundary();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float q() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.f2150b.mTouchResponse.getSpringDamping();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float r() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.f2150b.mTouchResponse.getSpringMass();
    }

    public void removeTransition(Transition transition) {
        int index = getIndex(transition);
        if (index != -1) {
            this.mTransitionList.remove(index);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float s() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.f2150b.mTouchResponse.getSpringStiffness();
    }

    public void setConstraintSet(int i2, ConstraintSet constraintSet) {
        this.mConstraintSetMap.put(i2, constraintSet);
    }

    public void setDuration(int i2) {
        Transition transition = this.f2150b;
        if (transition != null) {
            transition.setDuration(i2);
        } else {
            this.mDefaultDuration = i2;
        }
    }

    public void setKeyframe(View view, int i2, String str, Object obj) {
        Transition transition = this.f2150b;
        if (transition == null) {
            return;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (it2.hasNext()) {
                if (it2.next().f2041a == i2) {
                    if (obj != null) {
                        ((Float) obj).floatValue();
                    }
                    str.equalsIgnoreCase("app:PerpendicularPath_percent");
                }
            }
        }
    }

    public void setRtl(boolean z) {
        this.mRtl = z;
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return;
        }
        this.f2150b.mTouchResponse.setRTL(this.mRtl);
    }

    public void setTransition(Transition transition) {
        this.f2150b = transition;
        if (transition == null || transition.mTouchResponse == null) {
            return;
        }
        this.f2150b.mTouchResponse.setRTL(this.mRtl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float t() {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.f2150b.mTouchResponse.getSpringStopThreshold();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int u() {
        Transition transition = this.f2150b;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetStart;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean v(View view, int i2) {
        Transition transition = this.f2150b;
        if (transition == null) {
            return false;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (it2.hasNext()) {
                if (it2.next().f2041a == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateLayout(MotionLayout motionLayout) {
        return motionLayout == this.mMotionLayout && motionLayout.f2078f == this;
    }

    public void viewTransition(int i2, View... viewArr) {
        this.f2151c.j(i2, viewArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(float f2, float f3) {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return;
        }
        this.f2150b.mTouchResponse.j(f2, f3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(float f2, float f3) {
        Transition transition = this.f2150b;
        if (transition == null || transition.mTouchResponse == null) {
            return;
        }
        this.f2150b.mTouchResponse.k(f2, f3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(MotionEvent motionEvent, int i2, MotionLayout motionLayout) {
        MotionLayout.MotionTracker motionTracker;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = this.mMotionLayout.Q();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (i2 != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.f2152d = motionEvent.getRawX();
                this.f2153e = motionEvent.getRawY();
                this.mLastTouchDown = motionEvent;
                this.mIgnoreTouch = false;
                if (this.f2150b.mTouchResponse != null) {
                    RectF b2 = this.f2150b.mTouchResponse.b(this.mMotionLayout, rectF);
                    if (b2 != null && !b2.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                        this.mLastTouchDown = null;
                        this.mIgnoreTouch = true;
                        return;
                    }
                    RectF f2 = this.f2150b.mTouchResponse.f(this.mMotionLayout, rectF);
                    if (f2 == null || f2.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                        this.mMotionOutsideRegion = false;
                    } else {
                        this.mMotionOutsideRegion = true;
                    }
                    this.f2150b.mTouchResponse.l(this.f2152d, this.f2153e);
                    return;
                }
                return;
            } else if (action == 2 && !this.mIgnoreTouch) {
                float rawY = motionEvent.getRawY() - this.f2153e;
                float rawX = motionEvent.getRawX() - this.f2152d;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.mLastTouchDown) == null) {
                    return;
                }
                Transition bestTransitionFor = bestTransitionFor(i2, rawX, rawY, motionEvent2);
                if (bestTransitionFor != null) {
                    motionLayout.setTransition(bestTransitionFor);
                    RectF f3 = this.f2150b.mTouchResponse.f(this.mMotionLayout, rectF);
                    if (f3 != null && !f3.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                        z = true;
                    }
                    this.mMotionOutsideRegion = z;
                    this.f2150b.mTouchResponse.m(this.f2152d, this.f2153e);
                }
            }
        }
        if (this.mIgnoreTouch) {
            return;
        }
        Transition transition = this.f2150b;
        if (transition != null && transition.mTouchResponse != null && !this.mMotionOutsideRegion) {
            this.f2150b.mTouchResponse.h(motionEvent, this.mVelocityTracker, i2, this);
        }
        this.f2152d = motionEvent.getRawX();
        this.f2153e = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (motionTracker = this.mVelocityTracker) == null) {
            return;
        }
        motionTracker.recycle();
        this.mVelocityTracker = null;
        int i3 = motionLayout.f2082j;
        if (i3 != -1) {
            f(motionLayout, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(MotionLayout motionLayout) {
        for (int i2 = 0; i2 < this.mConstraintSetMap.size(); i2++) {
            int keyAt = this.mConstraintSetMap.keyAt(i2);
            if (hasCycleDependency(keyAt)) {
                Log.e(TypedValues.MotionScene.NAME, "Cannot be derived from yourself");
                return;
            }
            readConstraintChain(keyAt, motionLayout);
        }
    }
}
