package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.core.os.EnvironmentCompat;
import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ConstraintSet {
    private static final int ALPHA = 43;
    private static final int ANIMATE_CIRCLE_ANGLE_TO = 82;
    private static final int ANIMATE_RELATIVE_TO = 64;
    private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
    private static final int BARRIER_DIRECTION = 72;
    private static final int BARRIER_MARGIN = 73;
    private static final int BARRIER_TYPE = 1;
    public static final int BASELINE = 5;
    private static final int BASELINE_MARGIN = 93;
    private static final int BASELINE_TO_BASELINE = 1;
    private static final int BASELINE_TO_BOTTOM = 92;
    private static final int BASELINE_TO_TOP = 91;
    public static final int BOTTOM = 4;
    private static final int BOTTOM_MARGIN = 2;
    private static final int BOTTOM_TO_BOTTOM = 3;
    private static final int BOTTOM_TO_TOP = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    private static final int CHAIN_USE_RTL = 71;
    private static final int CIRCLE = 61;
    private static final int CIRCLE_ANGLE = 63;
    private static final int CIRCLE_RADIUS = 62;
    public static final int CIRCLE_REFERENCE = 8;
    private static final int CONSTRAINED_HEIGHT = 81;
    private static final int CONSTRAINED_WIDTH = 80;
    private static final int CONSTRAINT_REFERENCED_IDS = 74;
    private static final int CONSTRAINT_TAG = 77;
    private static final boolean DEBUG = false;
    private static final int DIMENSION_RATIO = 5;
    private static final int DRAW_PATH = 66;
    private static final int EDITOR_ABSOLUTE_X = 6;
    private static final int EDITOR_ABSOLUTE_Y = 7;
    private static final int ELEVATION = 44;
    public static final int END = 7;
    private static final int END_MARGIN = 8;
    private static final int END_TO_END = 9;
    private static final int END_TO_START = 10;
    private static final String ERROR_MESSAGE = "XML parser error must be within a Constraint ";
    public static final int GONE = 8;
    private static final int GONE_BASELINE_MARGIN = 94;
    private static final int GONE_BOTTOM_MARGIN = 11;
    private static final int GONE_END_MARGIN = 12;
    private static final int GONE_LEFT_MARGIN = 13;
    private static final int GONE_RIGHT_MARGIN = 14;
    private static final int GONE_START_MARGIN = 15;
    private static final int GONE_TOP_MARGIN = 16;
    private static final int GUIDE_BEGIN = 17;
    private static final int GUIDE_END = 18;
    private static final int GUIDE_PERCENT = 19;
    private static final int HEIGHT_DEFAULT = 55;
    private static final int HEIGHT_MAX = 57;
    private static final int HEIGHT_MIN = 59;
    private static final int HEIGHT_PERCENT = 70;
    public static final int HORIZONTAL = 0;
    private static final int HORIZONTAL_BIAS = 20;
    public static final int HORIZONTAL_GUIDELINE = 0;
    private static final int HORIZONTAL_STYLE = 41;
    private static final int HORIZONTAL_WEIGHT = 39;
    private static final int INTERNAL_MATCH_CONSTRAINT = -3;
    private static final int INTERNAL_MATCH_PARENT = -1;
    private static final int INTERNAL_WRAP_CONTENT = -2;
    private static final int INTERNAL_WRAP_CONTENT_CONSTRAINED = -4;
    public static final int INVISIBLE = 4;
    private static final String KEY_PERCENT_PARENT = "parent";
    private static final String KEY_RATIO = "ratio";
    private static final String KEY_WEIGHT = "weight";
    private static final int LAYOUT_CONSTRAINT_HEIGHT = 96;
    private static final int LAYOUT_CONSTRAINT_WIDTH = 95;
    private static final int LAYOUT_HEIGHT = 21;
    private static final int LAYOUT_VISIBILITY = 22;
    private static final int LAYOUT_WIDTH = 23;
    private static final int LAYOUT_WRAP_BEHAVIOR = 97;
    public static final int LEFT = 1;
    private static final int LEFT_MARGIN = 24;
    private static final int LEFT_TO_LEFT = 25;
    private static final int LEFT_TO_RIGHT = 26;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    private static final int MOTION_STAGGER = 79;
    private static final int MOTION_TARGET = 98;
    private static final int ORIENTATION = 27;
    public static final int PARENT_ID = 0;
    private static final int PATH_MOTION_ARC = 76;
    private static final int PROGRESS = 68;
    private static final int QUANTIZE_MOTION_INTERPOLATOR = 86;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_ID = 89;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_STR = 90;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_TYPE = 88;
    private static final int QUANTIZE_MOTION_PHASE = 85;
    private static final int QUANTIZE_MOTION_STEPS = 84;
    public static final int RIGHT = 2;
    private static final int RIGHT_MARGIN = 28;
    private static final int RIGHT_TO_LEFT = 29;
    private static final int RIGHT_TO_RIGHT = 30;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    private static final int ROTATION = 60;
    private static final int ROTATION_X = 45;
    private static final int ROTATION_Y = 46;
    private static final int SCALE_X = 47;
    private static final int SCALE_Y = 48;
    public static final int START = 6;
    private static final int START_MARGIN = 31;
    private static final int START_TO_END = 32;
    private static final int START_TO_START = 33;
    private static final String TAG = "ConstraintSet";
    public static final int TOP = 3;
    private static final int TOP_MARGIN = 34;
    private static final int TOP_TO_BOTTOM = 35;
    private static final int TOP_TO_TOP = 36;
    private static final int TRANSFORM_PIVOT_TARGET = 83;
    private static final int TRANSFORM_PIVOT_X = 49;
    private static final int TRANSFORM_PIVOT_Y = 50;
    private static final int TRANSITION_EASING = 65;
    private static final int TRANSITION_PATH_ROTATE = 67;
    private static final int TRANSLATION_X = 51;
    private static final int TRANSLATION_Y = 52;
    private static final int TRANSLATION_Z = 53;
    public static final int UNSET = -1;
    private static final int UNUSED = 87;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_BIAS = 37;
    public static final int VERTICAL_GUIDELINE = 1;
    private static final int VERTICAL_STYLE = 42;
    private static final int VERTICAL_WEIGHT = 40;
    private static final int VIEW_ID = 38;
    private static final int VISIBILITY_MODE = 78;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 0;
    private static final int WIDTH_DEFAULT = 54;
    private static final int WIDTH_MAX = 56;
    private static final int WIDTH_MIN = 58;
    private static final int WIDTH_PERCENT = 69;
    public static final int WRAP_CONTENT = -2;
    public String mIdString;
    private boolean mValidate;
    private static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private static SparseIntArray mapToConstant = new SparseIntArray();
    private static SparseIntArray overrideMapToConstant = new SparseIntArray();
    public String derivedState = "";
    public int mRotate = 0;
    private HashMap<String, ConstraintAttribute> mSavedAttributes = new HashMap<>();
    private boolean mForceId = true;
    private HashMap<Integer, Constraint> mConstraints = new HashMap<>();

    /* loaded from: classes.dex */
    public static class Constraint {

        /* renamed from: a  reason: collision with root package name */
        int f2296a;

        /* renamed from: b  reason: collision with root package name */
        String f2297b;

        /* renamed from: c  reason: collision with root package name */
        Delta f2298c;
        public final PropertySet propertySet = new PropertySet();
        public final Motion motion = new Motion();
        public final Layout layout = new Layout();
        public final Transform transform = new Transform();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static class Delta {
            private static final int INITIAL_BOOLEAN = 4;
            private static final int INITIAL_FLOAT = 10;
            private static final int INITIAL_INT = 10;
            private static final int INITIAL_STRING = 5;

            /* renamed from: a  reason: collision with root package name */
            int[] f2299a = new int[10];

            /* renamed from: b  reason: collision with root package name */
            int[] f2300b = new int[10];

            /* renamed from: c  reason: collision with root package name */
            int f2301c = 0;

            /* renamed from: d  reason: collision with root package name */
            int[] f2302d = new int[10];

            /* renamed from: e  reason: collision with root package name */
            float[] f2303e = new float[10];

            /* renamed from: f  reason: collision with root package name */
            int f2304f = 0;

            /* renamed from: g  reason: collision with root package name */
            int[] f2305g = new int[5];

            /* renamed from: h  reason: collision with root package name */
            String[] f2306h = new String[5];

            /* renamed from: i  reason: collision with root package name */
            int f2307i = 0;

            /* renamed from: j  reason: collision with root package name */
            int[] f2308j = new int[4];

            /* renamed from: k  reason: collision with root package name */
            boolean[] f2309k = new boolean[4];

            /* renamed from: l  reason: collision with root package name */
            int f2310l = 0;

            Delta() {
            }

            void a(int i2, float f2) {
                int i3 = this.f2304f;
                int[] iArr = this.f2302d;
                if (i3 >= iArr.length) {
                    this.f2302d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.f2303e;
                    this.f2303e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.f2302d;
                int i4 = this.f2304f;
                iArr2[i4] = i2;
                float[] fArr2 = this.f2303e;
                this.f2304f = i4 + 1;
                fArr2[i4] = f2;
            }

            void b(int i2, int i3) {
                int i4 = this.f2301c;
                int[] iArr = this.f2299a;
                if (i4 >= iArr.length) {
                    this.f2299a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.f2300b;
                    this.f2300b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.f2299a;
                int i5 = this.f2301c;
                iArr3[i5] = i2;
                int[] iArr4 = this.f2300b;
                this.f2301c = i5 + 1;
                iArr4[i5] = i3;
            }

            void c(int i2, String str) {
                int i3 = this.f2307i;
                int[] iArr = this.f2305g;
                if (i3 >= iArr.length) {
                    this.f2305g = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.f2306h;
                    this.f2306h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.f2305g;
                int i4 = this.f2307i;
                iArr2[i4] = i2;
                String[] strArr2 = this.f2306h;
                this.f2307i = i4 + 1;
                strArr2[i4] = str;
            }

            void d(int i2, boolean z) {
                int i3 = this.f2310l;
                int[] iArr = this.f2308j;
                if (i3 >= iArr.length) {
                    this.f2308j = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.f2309k;
                    this.f2309k = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.f2308j;
                int i4 = this.f2310l;
                iArr2[i4] = i2;
                boolean[] zArr2 = this.f2309k;
                this.f2310l = i4 + 1;
                zArr2[i4] = z;
            }

            void e(Constraint constraint) {
                for (int i2 = 0; i2 < this.f2301c; i2++) {
                    ConstraintSet.setDeltaValue(constraint, this.f2299a[i2], this.f2300b[i2]);
                }
                for (int i3 = 0; i3 < this.f2304f; i3++) {
                    ConstraintSet.setDeltaValue(constraint, this.f2302d[i3], this.f2303e[i3]);
                }
                for (int i4 = 0; i4 < this.f2307i; i4++) {
                    ConstraintSet.setDeltaValue(constraint, this.f2305g[i4], this.f2306h[i4]);
                }
                for (int i5 = 0; i5 < this.f2310l; i5++) {
                    ConstraintSet.setDeltaValue(constraint, this.f2308j[i5], this.f2309k[i5]);
                }
            }

            @SuppressLint({"LogConditional"})
            void f(String str) {
                for (int i2 = 0; i2 < this.f2301c; i2++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.f2299a[i2]);
                    sb.append(" = ");
                    sb.append(this.f2300b[i2]);
                }
                for (int i3 = 0; i3 < this.f2304f; i3++) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.f2302d[i3]);
                    sb2.append(" = ");
                    sb2.append(this.f2303e[i3]);
                }
                for (int i4 = 0; i4 < this.f2307i; i4++) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(this.f2305g[i4]);
                    sb3.append(" = ");
                    sb3.append(this.f2306h[i4]);
                }
                for (int i5 = 0; i5 < this.f2310l; i5++) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(this.f2308j[i5]);
                    sb4.append(" = ");
                    sb4.append(this.f2309k[i5]);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillFrom(int i2, ConstraintLayout.LayoutParams layoutParams) {
            this.f2296a = i2;
            Layout layout = this.layout;
            layout.leftToLeft = layoutParams.leftToLeft;
            layout.leftToRight = layoutParams.leftToRight;
            layout.rightToLeft = layoutParams.rightToLeft;
            layout.rightToRight = layoutParams.rightToRight;
            layout.topToTop = layoutParams.topToTop;
            layout.topToBottom = layoutParams.topToBottom;
            layout.bottomToTop = layoutParams.bottomToTop;
            layout.bottomToBottom = layoutParams.bottomToBottom;
            layout.baselineToBaseline = layoutParams.baselineToBaseline;
            layout.baselineToTop = layoutParams.baselineToTop;
            layout.baselineToBottom = layoutParams.baselineToBottom;
            layout.startToEnd = layoutParams.startToEnd;
            layout.startToStart = layoutParams.startToStart;
            layout.endToStart = layoutParams.endToStart;
            layout.endToEnd = layoutParams.endToEnd;
            layout.horizontalBias = layoutParams.horizontalBias;
            layout.verticalBias = layoutParams.verticalBias;
            layout.dimensionRatio = layoutParams.dimensionRatio;
            layout.circleConstraint = layoutParams.circleConstraint;
            layout.circleRadius = layoutParams.circleRadius;
            layout.circleAngle = layoutParams.circleAngle;
            layout.editorAbsoluteX = layoutParams.editorAbsoluteX;
            layout.editorAbsoluteY = layoutParams.editorAbsoluteY;
            layout.orientation = layoutParams.orientation;
            layout.guidePercent = layoutParams.guidePercent;
            layout.guideBegin = layoutParams.guideBegin;
            layout.guideEnd = layoutParams.guideEnd;
            layout.mWidth = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            layout.mHeight = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            layout.leftMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            layout.rightMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            layout.topMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            layout.bottomMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            layout.baselineMargin = layoutParams.baselineMargin;
            layout.verticalWeight = layoutParams.verticalWeight;
            layout.horizontalWeight = layoutParams.horizontalWeight;
            layout.verticalChainStyle = layoutParams.verticalChainStyle;
            layout.horizontalChainStyle = layoutParams.horizontalChainStyle;
            layout.constrainedWidth = layoutParams.constrainedWidth;
            layout.constrainedHeight = layoutParams.constrainedHeight;
            layout.widthDefault = layoutParams.matchConstraintDefaultWidth;
            layout.heightDefault = layoutParams.matchConstraintDefaultHeight;
            layout.widthMax = layoutParams.matchConstraintMaxWidth;
            layout.heightMax = layoutParams.matchConstraintMaxHeight;
            layout.widthMin = layoutParams.matchConstraintMinWidth;
            layout.heightMin = layoutParams.matchConstraintMinHeight;
            layout.widthPercent = layoutParams.matchConstraintPercentWidth;
            layout.heightPercent = layoutParams.matchConstraintPercentHeight;
            layout.mConstraintTag = layoutParams.constraintTag;
            layout.goneTopMargin = layoutParams.goneTopMargin;
            layout.goneBottomMargin = layoutParams.goneBottomMargin;
            layout.goneLeftMargin = layoutParams.goneLeftMargin;
            layout.goneRightMargin = layoutParams.goneRightMargin;
            layout.goneStartMargin = layoutParams.goneStartMargin;
            layout.goneEndMargin = layoutParams.goneEndMargin;
            layout.goneBaselineMargin = layoutParams.goneBaselineMargin;
            layout.mWrapBehavior = layoutParams.wrapBehaviorInParent;
            if (Build.VERSION.SDK_INT >= 17) {
                layout.endMargin = layoutParams.getMarginEnd();
                this.layout.startMargin = layoutParams.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillFromConstraints(int i2, Constraints.LayoutParams layoutParams) {
            fillFrom(i2, layoutParams);
            this.propertySet.alpha = layoutParams.alpha;
            Transform transform = this.transform;
            transform.rotation = layoutParams.rotation;
            transform.rotationX = layoutParams.rotationX;
            transform.rotationY = layoutParams.rotationY;
            transform.scaleX = layoutParams.scaleX;
            transform.scaleY = layoutParams.scaleY;
            transform.transformPivotX = layoutParams.transformPivotX;
            transform.transformPivotY = layoutParams.transformPivotY;
            transform.translationX = layoutParams.translationX;
            transform.translationY = layoutParams.translationY;
            transform.translationZ = layoutParams.translationZ;
            transform.elevation = layoutParams.elevation;
            transform.applyElevation = layoutParams.applyElevation;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillFromConstraints(ConstraintHelper constraintHelper, int i2, Constraints.LayoutParams layoutParams) {
            fillFromConstraints(i2, layoutParams);
            if (constraintHelper instanceof Barrier) {
                Layout layout = this.layout;
                layout.mHelperType = 1;
                Barrier barrier = (Barrier) constraintHelper;
                layout.mBarrierDirection = barrier.getType();
                this.layout.mReferenceIds = barrier.getReferencedIds();
                this.layout.mBarrierMargin = barrier.getMargin();
            }
        }

        private ConstraintAttribute get(String str, ConstraintAttribute.AttributeType attributeType) {
            if (!this.mCustomConstraints.containsKey(str)) {
                ConstraintAttribute constraintAttribute = new ConstraintAttribute(str, attributeType);
                this.mCustomConstraints.put(str, constraintAttribute);
                return constraintAttribute;
            }
            ConstraintAttribute constraintAttribute2 = this.mCustomConstraints.get(str);
            if (constraintAttribute2.getType() == attributeType) {
                return constraintAttribute2;
            }
            throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute2.getType().name());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setColorValue(String str, int i2) {
            get(str, ConstraintAttribute.AttributeType.COLOR_TYPE).setColorValue(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFloatValue(String str, float f2) {
            get(str, ConstraintAttribute.AttributeType.FLOAT_TYPE).setFloatValue(f2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIntValue(String str, int i2) {
            get(str, ConstraintAttribute.AttributeType.INT_TYPE).setIntValue(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStringValue(String str, String str2) {
            get(str, ConstraintAttribute.AttributeType.STRING_TYPE).setStringValue(str2);
        }

        public void applyDelta(Constraint constraint) {
            Delta delta = this.f2298c;
            if (delta != null) {
                delta.e(constraint);
            }
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout = this.layout;
            layoutParams.leftToLeft = layout.leftToLeft;
            layoutParams.leftToRight = layout.leftToRight;
            layoutParams.rightToLeft = layout.rightToLeft;
            layoutParams.rightToRight = layout.rightToRight;
            layoutParams.topToTop = layout.topToTop;
            layoutParams.topToBottom = layout.topToBottom;
            layoutParams.bottomToTop = layout.bottomToTop;
            layoutParams.bottomToBottom = layout.bottomToBottom;
            layoutParams.baselineToBaseline = layout.baselineToBaseline;
            layoutParams.baselineToTop = layout.baselineToTop;
            layoutParams.baselineToBottom = layout.baselineToBottom;
            layoutParams.startToEnd = layout.startToEnd;
            layoutParams.startToStart = layout.startToStart;
            layoutParams.endToStart = layout.endToStart;
            layoutParams.endToEnd = layout.endToEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = layout.leftMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = layout.rightMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = layout.topMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = layout.bottomMargin;
            layoutParams.goneStartMargin = layout.goneStartMargin;
            layoutParams.goneEndMargin = layout.goneEndMargin;
            layoutParams.goneTopMargin = layout.goneTopMargin;
            layoutParams.goneBottomMargin = layout.goneBottomMargin;
            layoutParams.horizontalBias = layout.horizontalBias;
            layoutParams.verticalBias = layout.verticalBias;
            layoutParams.circleConstraint = layout.circleConstraint;
            layoutParams.circleRadius = layout.circleRadius;
            layoutParams.circleAngle = layout.circleAngle;
            layoutParams.dimensionRatio = layout.dimensionRatio;
            layoutParams.editorAbsoluteX = layout.editorAbsoluteX;
            layoutParams.editorAbsoluteY = layout.editorAbsoluteY;
            layoutParams.verticalWeight = layout.verticalWeight;
            layoutParams.horizontalWeight = layout.horizontalWeight;
            layoutParams.verticalChainStyle = layout.verticalChainStyle;
            layoutParams.horizontalChainStyle = layout.horizontalChainStyle;
            layoutParams.constrainedWidth = layout.constrainedWidth;
            layoutParams.constrainedHeight = layout.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = layout.widthDefault;
            layoutParams.matchConstraintDefaultHeight = layout.heightDefault;
            layoutParams.matchConstraintMaxWidth = layout.widthMax;
            layoutParams.matchConstraintMaxHeight = layout.heightMax;
            layoutParams.matchConstraintMinWidth = layout.widthMin;
            layoutParams.matchConstraintMinHeight = layout.heightMin;
            layoutParams.matchConstraintPercentWidth = layout.widthPercent;
            layoutParams.matchConstraintPercentHeight = layout.heightPercent;
            layoutParams.orientation = layout.orientation;
            layoutParams.guidePercent = layout.guidePercent;
            layoutParams.guideBegin = layout.guideBegin;
            layoutParams.guideEnd = layout.guideEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = layout.mWidth;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = layout.mHeight;
            String str = layout.mConstraintTag;
            if (str != null) {
                layoutParams.constraintTag = str;
            }
            layoutParams.wrapBehaviorInParent = layout.mWrapBehavior;
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams.setMarginStart(layout.startMargin);
                layoutParams.setMarginEnd(this.layout.endMargin);
            }
            layoutParams.validate();
        }

        /* renamed from: clone */
        public Constraint m5clone() {
            Constraint constraint = new Constraint();
            constraint.layout.copyFrom(this.layout);
            constraint.motion.copyFrom(this.motion);
            constraint.propertySet.copyFrom(this.propertySet);
            constraint.transform.copyFrom(this.transform);
            constraint.f2296a = this.f2296a;
            constraint.f2298c = this.f2298c;
            return constraint;
        }

        public void printDelta(String str) {
            Delta delta = this.f2298c;
            if (delta != null) {
                delta.f(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Layout {
        private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
        private static final int BARRIER_DIRECTION = 72;
        private static final int BARRIER_MARGIN = 73;
        private static final int BASELINE_TO_BASELINE = 1;
        private static final int BOTTOM_MARGIN = 2;
        private static final int BOTTOM_TO_BOTTOM = 3;
        private static final int BOTTOM_TO_TOP = 4;
        private static final int CHAIN_USE_RTL = 71;
        private static final int CIRCLE = 61;
        private static final int CIRCLE_ANGLE = 63;
        private static final int CIRCLE_RADIUS = 62;
        private static final int CONSTRAINT_REFERENCED_IDS = 74;
        private static final int DIMENSION_RATIO = 5;
        private static final int EDITOR_ABSOLUTE_X = 6;
        private static final int EDITOR_ABSOLUTE_Y = 7;
        private static final int END_MARGIN = 8;
        private static final int END_TO_END = 9;
        private static final int END_TO_START = 10;
        private static final int GONE_BOTTOM_MARGIN = 11;
        private static final int GONE_END_MARGIN = 12;
        private static final int GONE_LEFT_MARGIN = 13;
        private static final int GONE_RIGHT_MARGIN = 14;
        private static final int GONE_START_MARGIN = 15;
        private static final int GONE_TOP_MARGIN = 16;
        private static final int GUIDE_BEGIN = 17;
        private static final int GUIDE_END = 18;
        private static final int GUIDE_PERCENT = 19;
        private static final int HEIGHT_PERCENT = 70;
        private static final int HORIZONTAL_BIAS = 20;
        private static final int HORIZONTAL_STYLE = 39;
        private static final int HORIZONTAL_WEIGHT = 37;
        private static final int LAYOUT_CONSTRAINT_HEIGHT = 42;
        private static final int LAYOUT_CONSTRAINT_WIDTH = 41;
        private static final int LAYOUT_HEIGHT = 21;
        private static final int LAYOUT_WIDTH = 22;
        private static final int LEFT_MARGIN = 23;
        private static final int LEFT_TO_LEFT = 24;
        private static final int LEFT_TO_RIGHT = 25;
        private static final int ORIENTATION = 26;
        private static final int RIGHT_MARGIN = 27;
        private static final int RIGHT_TO_LEFT = 28;
        private static final int RIGHT_TO_RIGHT = 29;
        private static final int START_MARGIN = 30;
        private static final int START_TO_END = 31;
        private static final int START_TO_START = 32;
        private static final int TOP_MARGIN = 33;
        private static final int TOP_TO_BOTTOM = 34;
        private static final int TOP_TO_TOP = 35;
        public static final int UNSET = -1;
        public static final int UNSET_GONE_MARGIN = Integer.MIN_VALUE;
        private static final int UNUSED = 76;
        private static final int VERTICAL_BIAS = 36;
        private static final int VERTICAL_STYLE = 40;
        private static final int VERTICAL_WEIGHT = 38;
        private static final int WIDTH_PERCENT = 69;
        private static SparseIntArray mapToConstant;
        public String mConstraintTag;
        public int mHeight;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public boolean mIsGuideline = false;
        public boolean mApply = false;
        public boolean mOverride = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int baselineToTop = -1;
        public int baselineToBottom = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public float circleAngle = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = 0;
        public int rightMargin = 0;
        public int topMargin = 0;
        public int bottomMargin = 0;
        public int endMargin = 0;
        public int startMargin = 0;
        public int baselineMargin = 0;
        public int goneLeftMargin = Integer.MIN_VALUE;
        public int goneTopMargin = Integer.MIN_VALUE;
        public int goneRightMargin = Integer.MIN_VALUE;
        public int goneBottomMargin = Integer.MIN_VALUE;
        public int goneEndMargin = Integer.MIN_VALUE;
        public int goneStartMargin = Integer.MIN_VALUE;
        public int goneBaselineMargin = Integer.MIN_VALUE;
        public float verticalWeight = -1.0f;
        public float horizontalWeight = -1.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public int widthDefault = 0;
        public int heightDefault = 0;
        public int widthMax = -1;
        public int heightMax = -1;
        public int widthMin = -1;
        public int heightMin = -1;
        public float widthPercent = 1.0f;
        public float heightPercent = 1.0f;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public int mHelperType = -1;
        public boolean constrainedWidth = false;
        public boolean constrainedHeight = false;
        public boolean mBarrierAllowsGoneWidgets = true;
        public int mWrapBehavior = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            mapToConstant.append(R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            mapToConstant.append(R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            mapToConstant.append(R.styleable.Layout_layout_editor_absoluteX, 6);
            mapToConstant.append(R.styleable.Layout_layout_editor_absoluteY, 7);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_begin, 17);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_end, 18);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_percent, 19);
            mapToConstant.append(R.styleable.Layout_android_orientation, 26);
            mapToConstant.append(R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            mapToConstant.append(R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            mapToConstant.append(R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            mapToConstant.append(R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginLeft, 13);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginTop, 16);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginRight, 14);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginBottom, 11);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginStart, 15);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginEnd, 12);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_weight, 38);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_bias, 36);
            mapToConstant.append(R.styleable.Layout_layout_constraintDimensionRatio, 5);
            mapToConstant.append(R.styleable.Layout_layout_constraintLeft_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintBaseline_creator, 76);
            mapToConstant.append(R.styleable.Layout_android_layout_marginLeft, 23);
            mapToConstant.append(R.styleable.Layout_android_layout_marginRight, 27);
            mapToConstant.append(R.styleable.Layout_android_layout_marginStart, 30);
            mapToConstant.append(R.styleable.Layout_android_layout_marginEnd, 8);
            mapToConstant.append(R.styleable.Layout_android_layout_marginTop, 33);
            mapToConstant.append(R.styleable.Layout_android_layout_marginBottom, 2);
            mapToConstant.append(R.styleable.Layout_android_layout_width, 22);
            mapToConstant.append(R.styleable.Layout_android_layout_height, 21);
            mapToConstant.append(R.styleable.Layout_layout_constraintWidth, 41);
            mapToConstant.append(R.styleable.Layout_layout_constraintHeight, 42);
            mapToConstant.append(R.styleable.Layout_layout_constrainedWidth, 41);
            mapToConstant.append(R.styleable.Layout_layout_constrainedHeight, 42);
            mapToConstant.append(R.styleable.Layout_layout_wrapBehaviorInParent, 97);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircle, 61);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircleRadius, 62);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircleAngle, 63);
            mapToConstant.append(R.styleable.Layout_layout_constraintWidth_percent, 69);
            mapToConstant.append(R.styleable.Layout_layout_constraintHeight_percent, 70);
            mapToConstant.append(R.styleable.Layout_chainUseRtl, 71);
            mapToConstant.append(R.styleable.Layout_barrierDirection, 72);
            mapToConstant.append(R.styleable.Layout_barrierMargin, 73);
            mapToConstant.append(R.styleable.Layout_constraint_referenced_ids, 74);
            mapToConstant.append(R.styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        void a(Context context, AttributeSet attributeSet) {
            StringBuilder sb;
            String str;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                int i3 = mapToConstant.get(index);
                if (i3 == 80) {
                    this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                } else if (i3 == 81) {
                    this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                } else if (i3 != 97) {
                    switch (i3) {
                        case 1:
                            this.baselineToBaseline = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToBaseline);
                            continue;
                        case 2:
                            this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                            continue;
                        case 3:
                            this.bottomToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToBottom);
                            continue;
                        case 4:
                            this.bottomToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToTop);
                            continue;
                        case 5:
                            this.dimensionRatio = obtainStyledAttributes.getString(index);
                            continue;
                        case 6:
                            this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                            continue;
                        case 7:
                            this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                            continue;
                        case 8:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                                break;
                            } else {
                                continue;
                            }
                        case 9:
                            this.endToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToEnd);
                            continue;
                        case 10:
                            this.endToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToStart);
                            continue;
                        case 11:
                            this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                            continue;
                        case 12:
                            this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                            continue;
                        case 13:
                            this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                            continue;
                        case 14:
                            this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                            continue;
                        case 15:
                            this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                            continue;
                        case 16:
                            this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                            continue;
                        case 17:
                            this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                            continue;
                        case 18:
                            this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                            continue;
                        case 19:
                            this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                            continue;
                        case 20:
                            this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                            continue;
                        case 21:
                            this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                            continue;
                        case 22:
                            this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                            continue;
                        case 23:
                            this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                            continue;
                        case 24:
                            this.leftToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToLeft);
                            continue;
                        case 25:
                            this.leftToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToRight);
                            continue;
                        case 26:
                            this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                            continue;
                        case 27:
                            this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                            continue;
                        case 28:
                            this.rightToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToLeft);
                            continue;
                        case 29:
                            this.rightToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToRight);
                            continue;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                                break;
                            } else {
                                continue;
                            }
                        case 31:
                            this.startToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToEnd);
                            continue;
                        case 32:
                            this.startToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToStart);
                            continue;
                        case 33:
                            this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                            continue;
                        case 34:
                            this.topToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToBottom);
                            continue;
                        case 35:
                            this.topToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToTop);
                            continue;
                        case 36:
                            this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                            continue;
                        case 37:
                            this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                            continue;
                        case 38:
                            this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                            continue;
                        case 39:
                            this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                            continue;
                        case 40:
                            this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                            continue;
                        case 41:
                            ConstraintSet.i(this, obtainStyledAttributes, index, 0);
                            continue;
                        case 42:
                            ConstraintSet.i(this, obtainStyledAttributes, index, 1);
                            continue;
                        default:
                            switch (i3) {
                                case 54:
                                    this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                    continue;
                                case 55:
                                    this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                    continue;
                                case 56:
                                    this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                    continue;
                                case 57:
                                    this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                    continue;
                                case 58:
                                    this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                    continue;
                                case 59:
                                    this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                    continue;
                                default:
                                    switch (i3) {
                                        case 61:
                                            this.circleConstraint = ConstraintSet.lookupID(obtainStyledAttributes, index, this.circleConstraint);
                                            continue;
                                        case 62:
                                            this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                            continue;
                                        case 63:
                                            this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                            continue;
                                        default:
                                            switch (i3) {
                                                case 69:
                                                    this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 70:
                                                    this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 71:
                                                    Log.e(ConstraintSet.TAG, "CURRENTLY UNSUPPORTED");
                                                    break;
                                                case 72:
                                                    this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                                    break;
                                                case 73:
                                                    this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                                    break;
                                                case 74:
                                                    this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                                    break;
                                                case 75:
                                                    this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                                    break;
                                                case 76:
                                                    sb = new StringBuilder();
                                                    str = "unused attribute 0x";
                                                    sb.append(str);
                                                    sb.append(Integer.toHexString(index));
                                                    sb.append("   ");
                                                    sb.append(mapToConstant.get(index));
                                                    continue;
                                                    continue;
                                                    continue;
                                                case 77:
                                                    this.mConstraintTag = obtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    switch (i3) {
                                                        case 91:
                                                            this.baselineToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToTop);
                                                            break;
                                                        case 92:
                                                            this.baselineToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToBottom);
                                                            break;
                                                        case 93:
                                                            this.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.baselineMargin);
                                                            break;
                                                        case 94:
                                                            this.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBaselineMargin);
                                                            break;
                                                        default:
                                                            sb = new StringBuilder();
                                                            str = "Unknown attribute 0x";
                                                            sb.append(str);
                                                            sb.append(Integer.toHexString(index));
                                                            sb.append("   ");
                                                            sb.append(mapToConstant.get(index));
                                                            continue;
                                                            continue;
                                                            continue;
                                                    }
                                            }
                                    }
                            }
                    }
                } else {
                    this.mWrapBehavior = obtainStyledAttributes.getInt(index, this.mWrapBehavior);
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Layout layout) {
            this.mIsGuideline = layout.mIsGuideline;
            this.mWidth = layout.mWidth;
            this.mApply = layout.mApply;
            this.mHeight = layout.mHeight;
            this.guideBegin = layout.guideBegin;
            this.guideEnd = layout.guideEnd;
            this.guidePercent = layout.guidePercent;
            this.leftToLeft = layout.leftToLeft;
            this.leftToRight = layout.leftToRight;
            this.rightToLeft = layout.rightToLeft;
            this.rightToRight = layout.rightToRight;
            this.topToTop = layout.topToTop;
            this.topToBottom = layout.topToBottom;
            this.bottomToTop = layout.bottomToTop;
            this.bottomToBottom = layout.bottomToBottom;
            this.baselineToBaseline = layout.baselineToBaseline;
            this.baselineToTop = layout.baselineToTop;
            this.baselineToBottom = layout.baselineToBottom;
            this.startToEnd = layout.startToEnd;
            this.startToStart = layout.startToStart;
            this.endToStart = layout.endToStart;
            this.endToEnd = layout.endToEnd;
            this.horizontalBias = layout.horizontalBias;
            this.verticalBias = layout.verticalBias;
            this.dimensionRatio = layout.dimensionRatio;
            this.circleConstraint = layout.circleConstraint;
            this.circleRadius = layout.circleRadius;
            this.circleAngle = layout.circleAngle;
            this.editorAbsoluteX = layout.editorAbsoluteX;
            this.editorAbsoluteY = layout.editorAbsoluteY;
            this.orientation = layout.orientation;
            this.leftMargin = layout.leftMargin;
            this.rightMargin = layout.rightMargin;
            this.topMargin = layout.topMargin;
            this.bottomMargin = layout.bottomMargin;
            this.endMargin = layout.endMargin;
            this.startMargin = layout.startMargin;
            this.baselineMargin = layout.baselineMargin;
            this.goneLeftMargin = layout.goneLeftMargin;
            this.goneTopMargin = layout.goneTopMargin;
            this.goneRightMargin = layout.goneRightMargin;
            this.goneBottomMargin = layout.goneBottomMargin;
            this.goneEndMargin = layout.goneEndMargin;
            this.goneStartMargin = layout.goneStartMargin;
            this.goneBaselineMargin = layout.goneBaselineMargin;
            this.verticalWeight = layout.verticalWeight;
            this.horizontalWeight = layout.horizontalWeight;
            this.horizontalChainStyle = layout.horizontalChainStyle;
            this.verticalChainStyle = layout.verticalChainStyle;
            this.widthDefault = layout.widthDefault;
            this.heightDefault = layout.heightDefault;
            this.widthMax = layout.widthMax;
            this.heightMax = layout.heightMax;
            this.widthMin = layout.widthMin;
            this.heightMin = layout.heightMin;
            this.widthPercent = layout.widthPercent;
            this.heightPercent = layout.heightPercent;
            this.mBarrierDirection = layout.mBarrierDirection;
            this.mBarrierMargin = layout.mBarrierMargin;
            this.mHelperType = layout.mHelperType;
            this.mConstraintTag = layout.mConstraintTag;
            int[] iArr = layout.mReferenceIds;
            if (iArr == null || layout.mReferenceIdString != null) {
                this.mReferenceIds = null;
            } else {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            }
            this.mReferenceIdString = layout.mReferenceIdString;
            this.constrainedWidth = layout.constrainedWidth;
            this.constrainedHeight = layout.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = layout.mBarrierAllowsGoneWidgets;
            this.mWrapBehavior = layout.mWrapBehavior;
        }

        public void dump(MotionScene motionScene, StringBuilder sb) {
            Field[] declaredFields = getClass().getDeclaredFields();
            sb.append("\n");
            for (Field field : declaredFields) {
                String name = field.getName();
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        Object obj = field.get(this);
                        Class<?> type = field.getType();
                        if (type == Integer.TYPE) {
                            Integer num = (Integer) obj;
                            if (num.intValue() != -1) {
                                Object lookUpConstraintName = motionScene.lookUpConstraintName(num.intValue());
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(lookUpConstraintName == null ? num : lookUpConstraintName);
                            }
                        } else if (type == Float.TYPE) {
                            Float f2 = (Float) obj;
                            if (f2.floatValue() != -1.0f) {
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(f2);
                            }
                        }
                        sb.append("\"\n");
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Motion {
        private static final int ANIMATE_CIRCLE_ANGLE_TO = 6;
        private static final int ANIMATE_RELATIVE_TO = 5;
        private static final int INTERPOLATOR_REFERENCE_ID = -2;
        private static final int INTERPOLATOR_UNDEFINED = -3;
        private static final int MOTION_DRAW_PATH = 4;
        private static final int MOTION_STAGGER = 7;
        private static final int PATH_MOTION_ARC = 2;
        private static final int QUANTIZE_MOTION_INTERPOLATOR = 10;
        private static final int QUANTIZE_MOTION_PHASE = 9;
        private static final int QUANTIZE_MOTION_STEPS = 8;
        private static final int SPLINE_STRING = -1;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 1;
        private static SparseIntArray mapToConstant;
        public boolean mApply = false;
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

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Motion_motionPathRotate, 1);
            mapToConstant.append(R.styleable.Motion_pathMotionArc, 2);
            mapToConstant.append(R.styleable.Motion_transitionEasing, 3);
            mapToConstant.append(R.styleable.Motion_drawPath, 4);
            mapToConstant.append(R.styleable.Motion_animateRelativeTo, 5);
            mapToConstant.append(R.styleable.Motion_animateCircleAngleTo, 6);
            mapToConstant.append(R.styleable.Motion_motionStagger, 7);
            mapToConstant.append(R.styleable.Motion_quantizeMotionSteps, 8);
            mapToConstant.append(R.styleable.Motion_quantizeMotionPhase, 9);
            mapToConstant.append(R.styleable.Motion_quantizeMotionInterpolator, 10);
        }

        void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case 3:
                        this.mTransitionEasing = obtainStyledAttributes.peekValue(index).type == 3 ? obtainStyledAttributes.getString(index) : Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                        break;
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.lookupID(obtainStyledAttributes, index, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mAnimateCircleAngleTo = obtainStyledAttributes.getInteger(index, this.mAnimateCircleAngleTo);
                        break;
                    case 7:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                    case 8:
                        this.mQuantizeMotionSteps = obtainStyledAttributes.getInteger(index, this.mQuantizeMotionSteps);
                        break;
                    case 9:
                        this.mQuantizeMotionPhase = obtainStyledAttributes.getFloat(index, this.mQuantizeMotionPhase);
                        break;
                    case 10:
                        int i3 = obtainStyledAttributes.peekValue(index).type;
                        if (i3 == 1) {
                            int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                            this.mQuantizeInterpolatorID = resourceId;
                            if (resourceId == -1) {
                                break;
                            }
                            this.mQuantizeInterpolatorType = -2;
                            break;
                        } else if (i3 != 3) {
                            this.mQuantizeInterpolatorType = obtainStyledAttributes.getInteger(index, this.mQuantizeInterpolatorID);
                            break;
                        } else {
                            String string = obtainStyledAttributes.getString(index);
                            this.mQuantizeInterpolatorString = string;
                            if (string.indexOf("/") <= 0) {
                                this.mQuantizeInterpolatorType = -1;
                                break;
                            } else {
                                this.mQuantizeInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                                this.mQuantizeInterpolatorType = -2;
                            }
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Motion motion) {
            this.mApply = motion.mApply;
            this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
            this.mTransitionEasing = motion.mTransitionEasing;
            this.mPathMotionArc = motion.mPathMotionArc;
            this.mDrawPath = motion.mDrawPath;
            this.mPathRotate = motion.mPathRotate;
            this.mMotionStagger = motion.mMotionStagger;
            this.mPolarRelativeTo = motion.mPolarRelativeTo;
        }
    }

    /* loaded from: classes.dex */
    public static class PropertySet {
        public boolean mApply = false;
        public int visibility = 0;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;

        void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.PropertySet_android_alpha) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == R.styleable.PropertySet_android_visibility) {
                    this.visibility = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = ConstraintSet.VISIBILITY_FLAGS[this.visibility];
                } else if (index == R.styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == R.styleable.PropertySet_motionProgress) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(PropertySet propertySet) {
            this.mApply = propertySet.mApply;
            this.visibility = propertySet.visibility;
            this.alpha = propertySet.alpha;
            this.mProgress = propertySet.mProgress;
            this.mVisibilityMode = propertySet.mVisibilityMode;
        }
    }

    /* loaded from: classes.dex */
    public static class Transform {
        private static final int ELEVATION = 11;
        private static final int ROTATION = 1;
        private static final int ROTATION_X = 2;
        private static final int ROTATION_Y = 3;
        private static final int SCALE_X = 4;
        private static final int SCALE_Y = 5;
        private static final int TRANSFORM_PIVOT_TARGET = 12;
        private static final int TRANSFORM_PIVOT_X = 6;
        private static final int TRANSFORM_PIVOT_Y = 7;
        private static final int TRANSLATION_X = 8;
        private static final int TRANSLATION_Y = 9;
        private static final int TRANSLATION_Z = 10;
        private static SparseIntArray mapToConstant;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public int transformPivotTarget = -1;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Transform_android_rotation, 1);
            mapToConstant.append(R.styleable.Transform_android_rotationX, 2);
            mapToConstant.append(R.styleable.Transform_android_rotationY, 3);
            mapToConstant.append(R.styleable.Transform_android_scaleX, 4);
            mapToConstant.append(R.styleable.Transform_android_scaleY, 5);
            mapToConstant.append(R.styleable.Transform_android_transformPivotX, 6);
            mapToConstant.append(R.styleable.Transform_android_transformPivotY, 7);
            mapToConstant.append(R.styleable.Transform_android_translationX, 8);
            mapToConstant.append(R.styleable.Transform_android_translationY, 9);
            mapToConstant.append(R.styleable.Transform_android_translationZ, 10);
            mapToConstant.append(R.styleable.Transform_android_elevation, 11);
            mapToConstant.append(R.styleable.Transform_transformPivotTarget, 12);
        }

        void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case 9:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.applyElevation = true;
                            this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        this.transformPivotTarget = ConstraintSet.lookupID(obtainStyledAttributes, index, this.transformPivotTarget);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Transform transform) {
            this.mApply = transform.mApply;
            this.rotation = transform.rotation;
            this.rotationX = transform.rotationX;
            this.rotationY = transform.rotationY;
            this.scaleX = transform.scaleX;
            this.scaleY = transform.scaleY;
            this.transformPivotX = transform.transformPivotX;
            this.transformPivotY = transform.transformPivotY;
            this.transformPivotTarget = transform.transformPivotTarget;
            this.translationX = transform.translationX;
            this.translationY = transform.translationY;
            this.translationZ = transform.translationZ;
            this.applyElevation = transform.applyElevation;
            this.elevation = transform.elevation;
        }
    }

    /* loaded from: classes.dex */
    class WriteJsonEngine {
        private static final String SPACE = "       ";

        /* renamed from: a  reason: collision with root package name */
        Writer f2311a;

        /* renamed from: b  reason: collision with root package name */
        Context f2312b;

        /* renamed from: c  reason: collision with root package name */
        int f2313c = 0;

        /* renamed from: d  reason: collision with root package name */
        HashMap f2314d = new HashMap();

        WriteJsonEngine(Writer writer, ConstraintLayout constraintLayout, int i2) {
            this.f2311a = writer;
            this.f2312b = constraintLayout.getContext();
        }

        private void writeDimension(String str, int i2, int i3, float f2, int i4, int i5, boolean z) {
            Writer writer;
            StringBuilder sb;
            String str2;
            Writer writer2;
            StringBuilder sb2;
            String str3;
            String str4;
            if (i2 != 0) {
                if (i2 == -2) {
                    writer = this.f2311a;
                    sb = new StringBuilder();
                    sb.append(SPACE);
                    sb.append(str);
                    str2 = ": 'wrap'\n";
                } else if (i2 != -1) {
                    Writer writer3 = this.f2311a;
                    writer3.write(SPACE + str + ": " + i2 + ",\n");
                    return;
                } else {
                    writer = this.f2311a;
                    sb = new StringBuilder();
                    sb.append(SPACE);
                    sb.append(str);
                    str2 = ": 'parent'\n";
                }
                sb.append(str2);
                writer.write(sb.toString());
                return;
            }
            if (i5 == -1 && i4 == -1) {
                if (i3 == 1) {
                    writer2 = this.f2311a;
                    sb2 = new StringBuilder();
                    sb2.append(SPACE);
                    sb2.append(str);
                    str4 = ": '???????????',\n";
                } else if (i3 != 2) {
                    return;
                } else {
                    writer2 = this.f2311a;
                    sb2 = new StringBuilder();
                    sb2.append(SPACE);
                    sb2.append(str);
                    sb2.append(": '");
                    sb2.append(f2);
                    str4 = "%',\n";
                }
                sb2.append(str4);
            } else if (i3 == 0) {
                Writer writer4 = this.f2311a;
                writer4.write(SPACE + str + ": {'spread' ," + i4 + ", " + i5 + "}\n");
                return;
            } else {
                if (i3 == 1) {
                    writer2 = this.f2311a;
                    sb2 = new StringBuilder();
                    sb2.append(SPACE);
                    sb2.append(str);
                    str3 = ": {'wrap' ,";
                } else if (i3 != 2) {
                    return;
                } else {
                    writer2 = this.f2311a;
                    sb2 = new StringBuilder();
                    sb2.append(SPACE);
                    sb2.append(str);
                    sb2.append(": {'");
                    sb2.append(f2);
                    str3 = "'% ,";
                }
                sb2.append(str3);
                sb2.append(i4);
                sb2.append(", ");
                sb2.append(i5);
                sb2.append("}\n");
            }
            writer2.write(sb2.toString());
        }

        private void writeGuideline(int i2, int i3, int i4, float f2) {
        }

        String a(int i2) {
            if (this.f2314d.containsKey(Integer.valueOf(i2))) {
                return "'" + ((String) this.f2314d.get(Integer.valueOf(i2))) + "'";
            } else if (i2 == 0) {
                return "'parent'";
            } else {
                String b2 = b(i2);
                this.f2314d.put(Integer.valueOf(i2), b2);
                return "'" + b2 + "'";
            }
        }

        String b(int i2) {
            try {
                if (i2 != -1) {
                    return this.f2312b.getResources().getResourceEntryName(i2);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                int i3 = this.f2313c + 1;
                this.f2313c = i3;
                sb.append(i3);
                return sb.toString();
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(EnvironmentCompat.MEDIA_UNKNOWN);
                int i4 = this.f2313c + 1;
                this.f2313c = i4;
                sb2.append(i4);
                return sb2.toString();
            }
        }

        void c(int i2, float f2, int i3) {
            if (i2 == -1) {
                return;
            }
            this.f2311a.write("       circle");
            this.f2311a.write(":[");
            this.f2311a.write(a(i2));
            Writer writer = this.f2311a;
            writer.write(", " + f2);
            Writer writer2 = this.f2311a;
            writer2.write(i3 + "]");
        }

        void d(String str, int i2, String str2, int i3, int i4) {
            if (i2 == -1) {
                return;
            }
            Writer writer = this.f2311a;
            writer.write(SPACE + str);
            this.f2311a.write(":[");
            this.f2311a.write(a(i2));
            this.f2311a.write(" , ");
            this.f2311a.write(str2);
            if (i3 != 0) {
                Writer writer2 = this.f2311a;
                writer2.write(" , " + i3);
            }
            this.f2311a.write("],\n");
        }

        void e() {
            this.f2311a.write("\n'ConstraintSet':{\n");
            for (Integer num : ConstraintSet.this.mConstraints.keySet()) {
                String a2 = a(num.intValue());
                Writer writer = this.f2311a;
                writer.write(a2 + ":{\n");
                Layout layout = ((Constraint) ConstraintSet.this.mConstraints.get(num)).layout;
                writeDimension("height", layout.mHeight, layout.heightDefault, layout.heightPercent, layout.heightMin, layout.heightMax, layout.constrainedHeight);
                writeDimension("width", layout.mWidth, layout.widthDefault, layout.widthPercent, layout.widthMin, layout.widthMax, layout.constrainedWidth);
                d("'left'", layout.leftToLeft, "'left'", layout.leftMargin, layout.goneLeftMargin);
                d("'left'", layout.leftToRight, "'right'", layout.leftMargin, layout.goneLeftMargin);
                d("'right'", layout.rightToLeft, "'left'", layout.rightMargin, layout.goneRightMargin);
                d("'right'", layout.rightToRight, "'right'", layout.rightMargin, layout.goneRightMargin);
                d("'baseline'", layout.baselineToBaseline, "'baseline'", -1, layout.goneBaselineMargin);
                d("'baseline'", layout.baselineToTop, "'top'", -1, layout.goneBaselineMargin);
                d("'baseline'", layout.baselineToBottom, "'bottom'", -1, layout.goneBaselineMargin);
                d("'top'", layout.topToBottom, "'bottom'", layout.topMargin, layout.goneTopMargin);
                d("'top'", layout.topToTop, "'top'", layout.topMargin, layout.goneTopMargin);
                d("'bottom'", layout.bottomToBottom, "'bottom'", layout.bottomMargin, layout.goneBottomMargin);
                d("'bottom'", layout.bottomToTop, "'top'", layout.bottomMargin, layout.goneBottomMargin);
                d("'start'", layout.startToStart, "'start'", layout.startMargin, layout.goneStartMargin);
                d("'start'", layout.startToEnd, "'end'", layout.startMargin, layout.goneStartMargin);
                d("'end'", layout.endToStart, "'start'", layout.endMargin, layout.goneEndMargin);
                d("'end'", layout.endToEnd, "'end'", layout.endMargin, layout.goneEndMargin);
                g("'horizontalBias'", layout.horizontalBias, 0.5f);
                g("'verticalBias'", layout.verticalBias, 0.5f);
                c(layout.circleConstraint, layout.circleAngle, layout.circleRadius);
                writeGuideline(layout.orientation, layout.guideBegin, layout.guideEnd, layout.guidePercent);
                i("'dimensionRatio'", layout.dimensionRatio);
                h("'barrierMargin'", layout.mBarrierMargin);
                h("'type'", layout.mHelperType);
                i("'ReferenceId'", layout.mReferenceIdString);
                j("'mBarrierAllowsGoneWidgets'", layout.mBarrierAllowsGoneWidgets, true);
                h("'WrapBehavior'", layout.mWrapBehavior);
                f("'verticalWeight'", layout.verticalWeight);
                f("'horizontalWeight'", layout.horizontalWeight);
                h("'horizontalChainStyle'", layout.horizontalChainStyle);
                h("'verticalChainStyle'", layout.verticalChainStyle);
                h("'barrierDirection'", layout.mBarrierDirection);
                int[] iArr = layout.mReferenceIds;
                if (iArr != null) {
                    k("'ReferenceIds'", iArr);
                }
                this.f2311a.write("}\n");
            }
            this.f2311a.write("}\n");
        }

        void f(String str, float f2) {
            if (f2 == -1.0f) {
                return;
            }
            Writer writer = this.f2311a;
            writer.write(SPACE + str);
            Writer writer2 = this.f2311a;
            writer2.write(": " + f2);
            this.f2311a.write(",\n");
        }

        void g(String str, float f2, float f3) {
            if (f2 == f3) {
                return;
            }
            Writer writer = this.f2311a;
            writer.write(SPACE + str);
            Writer writer2 = this.f2311a;
            writer2.write(": " + f2);
            this.f2311a.write(",\n");
        }

        void h(String str, int i2) {
            if (i2 == 0 || i2 == -1) {
                return;
            }
            Writer writer = this.f2311a;
            writer.write(SPACE + str);
            this.f2311a.write(":");
            Writer writer2 = this.f2311a;
            writer2.write(", " + i2);
            this.f2311a.write("\n");
        }

        void i(String str, String str2) {
            if (str2 == null) {
                return;
            }
            Writer writer = this.f2311a;
            writer.write(SPACE + str);
            this.f2311a.write(":");
            Writer writer2 = this.f2311a;
            writer2.write(", " + str2);
            this.f2311a.write("\n");
        }

        void j(String str, boolean z, boolean z2) {
            if (z == z2) {
                return;
            }
            Writer writer = this.f2311a;
            writer.write(SPACE + str);
            Writer writer2 = this.f2311a;
            writer2.write(": " + z);
            this.f2311a.write(",\n");
        }

        void k(String str, int[] iArr) {
            if (iArr == null) {
                return;
            }
            Writer writer = this.f2311a;
            writer.write(SPACE + str);
            this.f2311a.write(": ");
            int i2 = 0;
            while (i2 < iArr.length) {
                Writer writer2 = this.f2311a;
                StringBuilder sb = new StringBuilder();
                sb.append(i2 == 0 ? "[" : ", ");
                sb.append(a(iArr[i2]));
                writer2.write(sb.toString());
                i2++;
            }
            this.f2311a.write("],\n");
        }
    }

    /* loaded from: classes.dex */
    class WriteXmlEngine {
        private static final String SPACE = "\n       ";

        /* renamed from: a  reason: collision with root package name */
        Writer f2316a;

        /* renamed from: b  reason: collision with root package name */
        Context f2317b;

        /* renamed from: c  reason: collision with root package name */
        int f2318c = 0;

        /* renamed from: d  reason: collision with root package name */
        HashMap f2319d = new HashMap();

        WriteXmlEngine(Writer writer, ConstraintLayout constraintLayout, int i2) {
            this.f2316a = writer;
            this.f2317b = constraintLayout.getContext();
        }

        private void writeBaseDimension(String str, int i2, int i3) {
            Writer writer;
            StringBuilder sb;
            String str2;
            if (i2 != i3) {
                if (i2 == -2) {
                    writer = this.f2316a;
                    sb = new StringBuilder();
                    sb.append(SPACE);
                    sb.append(str);
                    str2 = "=\"wrap_content\"";
                } else if (i2 != -1) {
                    Writer writer2 = this.f2316a;
                    writer2.write(SPACE + str + "=\"" + i2 + "dp\"");
                    return;
                } else {
                    writer = this.f2316a;
                    sb = new StringBuilder();
                    sb.append(SPACE);
                    sb.append(str);
                    str2 = "=\"match_parent\"";
                }
                sb.append(str2);
                writer.write(sb.toString());
            }
        }

        private void writeBoolen(String str, boolean z, boolean z2) {
            if (z != z2) {
                Writer writer = this.f2316a;
                writer.write(SPACE + str + "=\"" + z + "dp\"");
            }
        }

        private void writeDimension(String str, int i2, int i3) {
            if (i2 != i3) {
                Writer writer = this.f2316a;
                writer.write(SPACE + str + "=\"" + i2 + "dp\"");
            }
        }

        private void writeEnum(String str, int i2, String[] strArr, int i3) {
            if (i2 != i3) {
                Writer writer = this.f2316a;
                writer.write(SPACE + str + "=\"" + strArr[i2] + "\"");
            }
        }

        String a(int i2) {
            if (this.f2319d.containsKey(Integer.valueOf(i2))) {
                return "@+id/" + ((String) this.f2319d.get(Integer.valueOf(i2))) + "";
            } else if (i2 == 0) {
                return ConstraintSet.KEY_PERCENT_PARENT;
            } else {
                String b2 = b(i2);
                this.f2319d.put(Integer.valueOf(i2), b2);
                return "@+id/" + b2 + "";
            }
        }

        String b(int i2) {
            try {
                if (i2 != -1) {
                    return this.f2317b.getResources().getResourceEntryName(i2);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                int i3 = this.f2318c + 1;
                this.f2318c = i3;
                sb.append(i3);
                return sb.toString();
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(EnvironmentCompat.MEDIA_UNKNOWN);
                int i4 = this.f2318c + 1;
                this.f2318c = i4;
                sb2.append(i4);
                return sb2.toString();
            }
        }

        void c() {
            this.f2316a.write("\n<ConstraintSet>\n");
            for (Integer num : ConstraintSet.this.mConstraints.keySet()) {
                String a2 = a(num.intValue());
                this.f2316a.write("  <Constraint");
                Writer writer = this.f2316a;
                writer.write("\n       android:id=\"" + a2 + "\"");
                Layout layout = ((Constraint) ConstraintSet.this.mConstraints.get(num)).layout;
                writeBaseDimension("android:layout_width", layout.mWidth, -5);
                writeBaseDimension("android:layout_height", layout.mHeight, -5);
                d("app:layout_constraintGuide_begin", (float) layout.guideBegin, -1.0f);
                d("app:layout_constraintGuide_end", layout.guideEnd, -1.0f);
                d("app:layout_constraintGuide_percent", layout.guidePercent, -1.0f);
                d("app:layout_constraintHorizontal_bias", layout.horizontalBias, 0.5f);
                d("app:layout_constraintVertical_bias", layout.verticalBias, 0.5f);
                f("app:layout_constraintDimensionRatio", layout.dimensionRatio, null);
                h("app:layout_constraintCircle", layout.circleConstraint);
                d("app:layout_constraintCircleRadius", layout.circleRadius, 0.0f);
                d("app:layout_constraintCircleAngle", layout.circleAngle, 0.0f);
                d("android:orientation", layout.orientation, -1.0f);
                d("app:layout_constraintVertical_weight", layout.verticalWeight, -1.0f);
                d("app:layout_constraintHorizontal_weight", layout.horizontalWeight, -1.0f);
                d("app:layout_constraintHorizontal_chainStyle", layout.horizontalChainStyle, 0.0f);
                d("app:layout_constraintVertical_chainStyle", layout.verticalChainStyle, 0.0f);
                d("app:barrierDirection", layout.mBarrierDirection, -1.0f);
                d("app:barrierMargin", layout.mBarrierMargin, 0.0f);
                writeDimension("app:layout_marginLeft", layout.leftMargin, 0);
                writeDimension("app:layout_goneMarginLeft", layout.goneLeftMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginRight", layout.rightMargin, 0);
                writeDimension("app:layout_goneMarginRight", layout.goneRightMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginStart", layout.startMargin, 0);
                writeDimension("app:layout_goneMarginStart", layout.goneStartMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginEnd", layout.endMargin, 0);
                writeDimension("app:layout_goneMarginEnd", layout.goneEndMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginTop", layout.topMargin, 0);
                writeDimension("app:layout_goneMarginTop", layout.goneTopMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginBottom", layout.bottomMargin, 0);
                writeDimension("app:layout_goneMarginBottom", layout.goneBottomMargin, Integer.MIN_VALUE);
                writeDimension("app:goneBaselineMargin", layout.goneBaselineMargin, Integer.MIN_VALUE);
                writeDimension("app:baselineMargin", layout.baselineMargin, 0);
                writeBoolen("app:layout_constrainedWidth", layout.constrainedWidth, false);
                writeBoolen("app:layout_constrainedHeight", layout.constrainedHeight, false);
                writeBoolen("app:barrierAllowsGoneWidgets", layout.mBarrierAllowsGoneWidgets, true);
                d("app:layout_wrapBehaviorInParent", layout.mWrapBehavior, 0.0f);
                h("app:baselineToBaseline", layout.baselineToBaseline);
                h("app:baselineToBottom", layout.baselineToBottom);
                h("app:baselineToTop", layout.baselineToTop);
                h("app:layout_constraintBottom_toBottomOf", layout.bottomToBottom);
                h("app:layout_constraintBottom_toTopOf", layout.bottomToTop);
                h("app:layout_constraintEnd_toEndOf", layout.endToEnd);
                h("app:layout_constraintEnd_toStartOf", layout.endToStart);
                h("app:layout_constraintLeft_toLeftOf", layout.leftToLeft);
                h("app:layout_constraintLeft_toRightOf", layout.leftToRight);
                h("app:layout_constraintRight_toLeftOf", layout.rightToLeft);
                h("app:layout_constraintRight_toRightOf", layout.rightToRight);
                h("app:layout_constraintStart_toEndOf", layout.startToEnd);
                h("app:layout_constraintStart_toStartOf", layout.startToStart);
                h("app:layout_constraintTop_toBottomOf", layout.topToBottom);
                h("app:layout_constraintTop_toTopOf", layout.topToTop);
                String[] strArr = {"spread", "wrap", "percent"};
                writeEnum("app:layout_constraintHeight_default", layout.heightDefault, strArr, 0);
                d("app:layout_constraintHeight_percent", layout.heightPercent, 1.0f);
                writeDimension("app:layout_constraintHeight_min", layout.heightMin, 0);
                writeDimension("app:layout_constraintHeight_max", layout.heightMax, 0);
                writeBoolen("android:layout_constrainedHeight", layout.constrainedHeight, false);
                writeEnum("app:layout_constraintWidth_default", layout.widthDefault, strArr, 0);
                d("app:layout_constraintWidth_percent", layout.widthPercent, 1.0f);
                writeDimension("app:layout_constraintWidth_min", layout.widthMin, 0);
                writeDimension("app:layout_constraintWidth_max", layout.widthMax, 0);
                writeBoolen("android:layout_constrainedWidth", layout.constrainedWidth, false);
                d("app:layout_constraintVertical_weight", layout.verticalWeight, -1.0f);
                d("app:layout_constraintHorizontal_weight", layout.horizontalWeight, -1.0f);
                e("app:layout_constraintHorizontal_chainStyle", layout.horizontalChainStyle);
                e("app:layout_constraintVertical_chainStyle", layout.verticalChainStyle);
                writeEnum("app:barrierDirection", layout.mBarrierDirection, new String[]{"left", "right", "top", "bottom", "start", "end"}, -1);
                f("app:layout_constraintTag", layout.mConstraintTag, null);
                int[] iArr = layout.mReferenceIds;
                if (iArr != null) {
                    g("'ReferenceIds'", iArr);
                }
                this.f2316a.write(" />\n");
            }
            this.f2316a.write("</ConstraintSet>\n");
        }

        void d(String str, float f2, float f3) {
            if (f2 == f3) {
                return;
            }
            Writer writer = this.f2316a;
            writer.write(SPACE + str);
            Writer writer2 = this.f2316a;
            writer2.write("=\"" + f2 + "\"");
        }

        void e(String str, int i2) {
            if (i2 == 0 || i2 == -1) {
                return;
            }
            Writer writer = this.f2316a;
            writer.write(SPACE + str + "=\"" + i2 + "\"\n");
        }

        void f(String str, String str2, String str3) {
            if (str2 == null || str2.equals(str3)) {
                return;
            }
            Writer writer = this.f2316a;
            writer.write(SPACE + str);
            Writer writer2 = this.f2316a;
            writer2.write("=\"" + str2 + "\"");
        }

        void g(String str, int[] iArr) {
            if (iArr == null) {
                return;
            }
            Writer writer = this.f2316a;
            writer.write(SPACE + str);
            this.f2316a.write(":");
            int i2 = 0;
            while (i2 < iArr.length) {
                Writer writer2 = this.f2316a;
                StringBuilder sb = new StringBuilder();
                sb.append(i2 == 0 ? "[" : ", ");
                sb.append(a(iArr[i2]));
                writer2.write(sb.toString());
                i2++;
            }
            this.f2316a.write("],\n");
        }

        void h(String str, int i2) {
            if (i2 == -1) {
                return;
            }
            Writer writer = this.f2316a;
            writer.write(SPACE + str);
            Writer writer2 = this.f2316a;
            writer2.write("=\"" + a(i2) + "\"");
        }
    }

    static {
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_toRightOf, 30);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_toTopOf, 36);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        mapToConstant.append(R.styleable.Constraint_layout_editor_absoluteX, 6);
        mapToConstant.append(R.styleable.Constraint_layout_editor_absoluteY, 7);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_begin, 17);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_end, 18);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_percent, 19);
        mapToConstant.append(R.styleable.Constraint_android_orientation, 27);
        mapToConstant.append(R.styleable.Constraint_layout_constraintStart_toEndOf, 32);
        mapToConstant.append(R.styleable.Constraint_layout_constraintStart_toStartOf, 33);
        mapToConstant.append(R.styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        mapToConstant.append(R.styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginLeft, 13);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginTop, 16);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginRight, 14);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginBottom, 11);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginStart, 15);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginEnd, 12);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_weight, 40);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_weight, 39);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_bias, 20);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_bias, 37);
        mapToConstant.append(R.styleable.Constraint_layout_constraintDimensionRatio, 5);
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_creator, 87);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginLeft, 24);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginRight, 28);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginStart, 31);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginEnd, 8);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginTop, 34);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginBottom, 2);
        mapToConstant.append(R.styleable.Constraint_android_layout_width, 23);
        mapToConstant.append(R.styleable.Constraint_android_layout_height, 21);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth, 95);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight, 96);
        mapToConstant.append(R.styleable.Constraint_android_visibility, 22);
        mapToConstant.append(R.styleable.Constraint_android_alpha, 43);
        mapToConstant.append(R.styleable.Constraint_android_elevation, 44);
        mapToConstant.append(R.styleable.Constraint_android_rotationX, 45);
        mapToConstant.append(R.styleable.Constraint_android_rotationY, 46);
        mapToConstant.append(R.styleable.Constraint_android_rotation, 60);
        mapToConstant.append(R.styleable.Constraint_android_scaleX, 47);
        mapToConstant.append(R.styleable.Constraint_android_scaleY, 48);
        mapToConstant.append(R.styleable.Constraint_android_transformPivotX, 49);
        mapToConstant.append(R.styleable.Constraint_android_transformPivotY, 50);
        mapToConstant.append(R.styleable.Constraint_android_translationX, 51);
        mapToConstant.append(R.styleable.Constraint_android_translationY, 52);
        mapToConstant.append(R.styleable.Constraint_android_translationZ, 53);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_default, 54);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_default, 55);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_max, 56);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_max, 57);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_min, 58);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_min, 59);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircle, 61);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircleRadius, 62);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircleAngle, 63);
        mapToConstant.append(R.styleable.Constraint_animateRelativeTo, 64);
        mapToConstant.append(R.styleable.Constraint_transitionEasing, 65);
        mapToConstant.append(R.styleable.Constraint_drawPath, 66);
        mapToConstant.append(R.styleable.Constraint_transitionPathRotate, 67);
        mapToConstant.append(R.styleable.Constraint_motionStagger, 79);
        mapToConstant.append(R.styleable.Constraint_android_id, 38);
        mapToConstant.append(R.styleable.Constraint_motionProgress, 68);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_percent, 69);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_percent, 70);
        mapToConstant.append(R.styleable.Constraint_layout_wrapBehaviorInParent, 97);
        mapToConstant.append(R.styleable.Constraint_chainUseRtl, 71);
        mapToConstant.append(R.styleable.Constraint_barrierDirection, 72);
        mapToConstant.append(R.styleable.Constraint_barrierMargin, 73);
        mapToConstant.append(R.styleable.Constraint_constraint_referenced_ids, 74);
        mapToConstant.append(R.styleable.Constraint_barrierAllowsGoneWidgets, 75);
        mapToConstant.append(R.styleable.Constraint_pathMotionArc, 76);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTag, 77);
        mapToConstant.append(R.styleable.Constraint_visibilityMode, 78);
        mapToConstant.append(R.styleable.Constraint_layout_constrainedWidth, 80);
        mapToConstant.append(R.styleable.Constraint_layout_constrainedHeight, 81);
        mapToConstant.append(R.styleable.Constraint_polarRelativeTo, 82);
        mapToConstant.append(R.styleable.Constraint_transformPivotTarget, 83);
        mapToConstant.append(R.styleable.Constraint_quantizeMotionSteps, 84);
        mapToConstant.append(R.styleable.Constraint_quantizeMotionPhase, 85);
        mapToConstant.append(R.styleable.Constraint_quantizeMotionInterpolator, 86);
        SparseIntArray sparseIntArray = overrideMapToConstant;
        int i2 = R.styleable.ConstraintOverride_layout_editor_absoluteY;
        sparseIntArray.append(i2, 6);
        overrideMapToConstant.append(i2, 7);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_orientation, 27);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginTop, 16);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginRight, 14);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginStart, 15);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginLeft, 24);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginRight, 28);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginStart, 31);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginEnd, 8);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginTop, 34);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginBottom, 2);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_width, 23);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_height, 21);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth, 95);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight, 96);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_visibility, 22);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_alpha, 43);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_elevation, 44);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_rotationX, 45);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_rotationY, 46);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_rotation, 60);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_scaleX, 47);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_scaleY, 48);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_transformPivotX, 49);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_transformPivotY, 50);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_translationX, 51);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_translationY, 52);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_translationZ, 53);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintCircleAngle, 63);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_animateRelativeTo, 64);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_transitionEasing, 65);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_drawPath, 66);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_transitionPathRotate, 67);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_motionStagger, 79);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_id, 38);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_motionTarget, 98);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_motionProgress, 68);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_chainUseRtl, 71);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_barrierDirection, 72);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_barrierMargin, 73);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_constraint_referenced_ids, 74);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_pathMotionArc, 76);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintTag, 77);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_visibilityMode, 78);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constrainedWidth, 80);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constrainedHeight, 81);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_polarRelativeTo, 82);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_transformPivotTarget, 83);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_quantizeMotionSteps, 84);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_quantizeMotionPhase, 85);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    private void addAttributes(ConstraintAttribute.AttributeType attributeType, String... strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (this.mSavedAttributes.containsKey(strArr[i2])) {
                ConstraintAttribute constraintAttribute = this.mSavedAttributes.get(strArr[i2]);
                if (constraintAttribute != null && constraintAttribute.getType() != attributeType) {
                    throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute.getType().name());
                }
            } else {
                this.mSavedAttributes.put(strArr[i2], new ConstraintAttribute(strArr[i2], attributeType));
            }
        }
    }

    public static Constraint buildDelta(Context context, XmlPullParser xmlPullParser) {
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, R.styleable.ConstraintOverride);
        populateOverride(context, constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private int[] convertReferenceString(View view, String str) {
        int i2;
        Object designInformation;
        String[] split = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i3 = 0;
        int i4 = 0;
        while (i3 < split.length) {
            String trim = split[i3].trim();
            try {
                i2 = R.id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i2 = 0;
            }
            if (i2 == 0) {
                i2 = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i2 == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i2 = ((Integer) designInformation).intValue();
            }
            iArr[i4] = i2;
            i3++;
            i4++;
        }
        return i4 != split.length ? Arrays.copyOf(iArr, i4) : iArr;
    }

    private void createHorizontalChain(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6, int i7, int i8) {
        if (iArr.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null && fArr.length != iArr.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null) {
            get(iArr[0]).layout.horizontalWeight = fArr[0];
        }
        get(iArr[0]).layout.horizontalChainStyle = i6;
        connect(iArr[0], i7, i2, i3, -1);
        for (int i9 = 1; i9 < iArr.length; i9++) {
            int i10 = iArr[i9];
            int i11 = i9 - 1;
            connect(iArr[i9], i7, iArr[i11], i8, -1);
            connect(iArr[i11], i8, iArr[i9], i7, -1);
            if (fArr != null) {
                get(iArr[i9]).layout.horizontalWeight = fArr[i9];
            }
        }
        connect(iArr[iArr.length - 1], i8, i4, i5, -1);
    }

    private Constraint fillFromAttributeList(Context context, AttributeSet attributeSet, boolean z) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R.styleable.ConstraintOverride : R.styleable.Constraint);
        populateConstraint(context, constraint, obtainStyledAttributes, z);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private Constraint get(int i2) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i2))) {
            this.mConstraints.put(Integer.valueOf(i2), new Constraint());
        }
        return this.mConstraints.get(Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(Object obj, TypedArray typedArray, int i2, int i3) {
        int i4;
        if (obj == null) {
            return;
        }
        int i5 = typedArray.peekValue(i2).type;
        if (i5 == 3) {
            j(obj, typedArray.getString(i2), i3);
            return;
        }
        int i6 = -2;
        boolean z = false;
        if (i5 != 5) {
            int i7 = typedArray.getInt(i2, 0);
            if (i7 != -4) {
                i6 = (i7 == -3 || !(i7 == -2 || i7 == -1)) ? 0 : i7;
            } else {
                z = true;
            }
        } else {
            i6 = typedArray.getDimensionPixelSize(i2, 0);
        }
        if (obj instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) obj;
            if (i3 == 0) {
                ((ViewGroup.MarginLayoutParams) layoutParams).width = i6;
                layoutParams.constrainedWidth = z;
                return;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).height = i6;
            layoutParams.constrainedHeight = z;
        } else if (obj instanceof Layout) {
            Layout layout = (Layout) obj;
            if (i3 == 0) {
                layout.mWidth = i6;
                layout.constrainedWidth = z;
                return;
            }
            layout.mHeight = i6;
            layout.constrainedHeight = z;
        } else if (obj instanceof Constraint.Delta) {
            Constraint.Delta delta = (Constraint.Delta) obj;
            if (i3 == 0) {
                delta.b(23, i6);
                i4 = 80;
            } else {
                delta.b(21, i6);
                i4 = 81;
            }
            delta.d(i4, z);
        }
    }

    static void j(Object obj, String str, int i2) {
        int i3;
        int i4;
        if (str == null) {
            return;
        }
        int indexOf = str.indexOf(61);
        int length = str.length();
        if (indexOf <= 0 || indexOf >= length - 1) {
            return;
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        if (substring2.length() > 0) {
            String trim = substring.trim();
            String trim2 = substring2.trim();
            if (KEY_RATIO.equalsIgnoreCase(trim)) {
                if (obj instanceof ConstraintLayout.LayoutParams) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) obj;
                    if (i2 == 0) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).width = 0;
                    } else {
                        ((ViewGroup.MarginLayoutParams) layoutParams).height = 0;
                    }
                    k(layoutParams, trim2);
                    return;
                } else if (obj instanceof Layout) {
                    ((Layout) obj).dimensionRatio = trim2;
                    return;
                } else if (obj instanceof Constraint.Delta) {
                    ((Constraint.Delta) obj).c(5, trim2);
                    return;
                } else {
                    return;
                }
            }
            try {
                if (KEY_WEIGHT.equalsIgnoreCase(trim)) {
                    float parseFloat = Float.parseFloat(trim2);
                    if (obj instanceof ConstraintLayout.LayoutParams) {
                        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) obj;
                        if (i2 == 0) {
                            ((ViewGroup.MarginLayoutParams) layoutParams2).width = 0;
                            layoutParams2.horizontalWeight = parseFloat;
                            return;
                        }
                        ((ViewGroup.MarginLayoutParams) layoutParams2).height = 0;
                        layoutParams2.verticalWeight = parseFloat;
                    } else if (obj instanceof Layout) {
                        Layout layout = (Layout) obj;
                        if (i2 == 0) {
                            layout.mWidth = 0;
                            layout.horizontalWeight = parseFloat;
                            return;
                        }
                        layout.mHeight = 0;
                        layout.verticalWeight = parseFloat;
                    } else if (obj instanceof Constraint.Delta) {
                        Constraint.Delta delta = (Constraint.Delta) obj;
                        if (i2 == 0) {
                            delta.b(23, 0);
                            i4 = 39;
                        } else {
                            delta.b(21, 0);
                            i4 = 40;
                        }
                        delta.a(i4, parseFloat);
                    }
                } else if (KEY_PERCENT_PARENT.equalsIgnoreCase(trim)) {
                    float max = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(trim2)));
                    if (obj instanceof ConstraintLayout.LayoutParams) {
                        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) obj;
                        if (i2 == 0) {
                            ((ViewGroup.MarginLayoutParams) layoutParams3).width = 0;
                            layoutParams3.matchConstraintPercentWidth = max;
                            layoutParams3.matchConstraintDefaultWidth = 2;
                            return;
                        }
                        ((ViewGroup.MarginLayoutParams) layoutParams3).height = 0;
                        layoutParams3.matchConstraintPercentHeight = max;
                        layoutParams3.matchConstraintDefaultHeight = 2;
                    } else if (obj instanceof Layout) {
                        Layout layout2 = (Layout) obj;
                        if (i2 == 0) {
                            layout2.mWidth = 0;
                            layout2.widthPercent = max;
                            layout2.widthDefault = 2;
                            return;
                        }
                        layout2.mHeight = 0;
                        layout2.heightPercent = max;
                        layout2.heightDefault = 2;
                    } else if (obj instanceof Constraint.Delta) {
                        Constraint.Delta delta2 = (Constraint.Delta) obj;
                        if (i2 == 0) {
                            delta2.b(23, 0);
                            i3 = 54;
                        } else {
                            delta2.b(21, 0);
                            i3 = 55;
                        }
                        delta2.b(i3, 2);
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k(ConstraintLayout.LayoutParams layoutParams, String str) {
        float f2 = Float.NaN;
        int i2 = -1;
        if (str != null) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i3 = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST)) {
                    i2 = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    i2 = 1;
                }
                i3 = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            try {
                if (indexOf2 < 0 || indexOf2 >= length - 1) {
                    String substring2 = str.substring(i3);
                    if (substring2.length() > 0) {
                        f2 = Float.parseFloat(substring2);
                    }
                } else {
                    String substring3 = str.substring(i3, indexOf2);
                    String substring4 = str.substring(indexOf2 + 1);
                    if (substring3.length() > 0 && substring4.length() > 0) {
                        float parseFloat = Float.parseFloat(substring3);
                        float parseFloat2 = Float.parseFloat(substring4);
                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                            f2 = i2 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        layoutParams.dimensionRatio = str;
        layoutParams.f2255c = f2;
        layoutParams.f2256d = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lookupID(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        return resourceId == -1 ? typedArray.getInt(i2, -1) : resourceId;
    }

    private void populateConstraint(Context context, Constraint constraint, TypedArray typedArray, boolean z) {
        Motion motion;
        String str;
        Motion motion2;
        StringBuilder sb;
        String str2;
        if (z) {
            populateOverride(context, constraint, typedArray);
            return;
        }
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index != R.styleable.Constraint_android_id && R.styleable.Constraint_android_layout_marginStart != index && R.styleable.Constraint_android_layout_marginEnd != index) {
                constraint.motion.mApply = true;
                constraint.layout.mApply = true;
                constraint.propertySet.mApply = true;
                constraint.transform.mApply = true;
            }
            switch (mapToConstant.get(index)) {
                case 1:
                    Layout layout = constraint.layout;
                    layout.baselineToBaseline = lookupID(typedArray, index, layout.baselineToBaseline);
                    continue;
                case 2:
                    Layout layout2 = constraint.layout;
                    layout2.bottomMargin = typedArray.getDimensionPixelSize(index, layout2.bottomMargin);
                    continue;
                case 3:
                    Layout layout3 = constraint.layout;
                    layout3.bottomToBottom = lookupID(typedArray, index, layout3.bottomToBottom);
                    continue;
                case 4:
                    Layout layout4 = constraint.layout;
                    layout4.bottomToTop = lookupID(typedArray, index, layout4.bottomToTop);
                    continue;
                case 5:
                    constraint.layout.dimensionRatio = typedArray.getString(index);
                    continue;
                case 6:
                    Layout layout5 = constraint.layout;
                    layout5.editorAbsoluteX = typedArray.getDimensionPixelOffset(index, layout5.editorAbsoluteX);
                    continue;
                case 7:
                    Layout layout6 = constraint.layout;
                    layout6.editorAbsoluteY = typedArray.getDimensionPixelOffset(index, layout6.editorAbsoluteY);
                    continue;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        Layout layout7 = constraint.layout;
                        layout7.endMargin = typedArray.getDimensionPixelSize(index, layout7.endMargin);
                    } else {
                        continue;
                    }
                case 9:
                    Layout layout8 = constraint.layout;
                    layout8.endToEnd = lookupID(typedArray, index, layout8.endToEnd);
                    continue;
                case 10:
                    Layout layout9 = constraint.layout;
                    layout9.endToStart = lookupID(typedArray, index, layout9.endToStart);
                    continue;
                case 11:
                    Layout layout10 = constraint.layout;
                    layout10.goneBottomMargin = typedArray.getDimensionPixelSize(index, layout10.goneBottomMargin);
                    continue;
                case 12:
                    Layout layout11 = constraint.layout;
                    layout11.goneEndMargin = typedArray.getDimensionPixelSize(index, layout11.goneEndMargin);
                    continue;
                case 13:
                    Layout layout12 = constraint.layout;
                    layout12.goneLeftMargin = typedArray.getDimensionPixelSize(index, layout12.goneLeftMargin);
                    continue;
                case 14:
                    Layout layout13 = constraint.layout;
                    layout13.goneRightMargin = typedArray.getDimensionPixelSize(index, layout13.goneRightMargin);
                    continue;
                case 15:
                    Layout layout14 = constraint.layout;
                    layout14.goneStartMargin = typedArray.getDimensionPixelSize(index, layout14.goneStartMargin);
                    continue;
                case 16:
                    Layout layout15 = constraint.layout;
                    layout15.goneTopMargin = typedArray.getDimensionPixelSize(index, layout15.goneTopMargin);
                    continue;
                case 17:
                    Layout layout16 = constraint.layout;
                    layout16.guideBegin = typedArray.getDimensionPixelOffset(index, layout16.guideBegin);
                    continue;
                case 18:
                    Layout layout17 = constraint.layout;
                    layout17.guideEnd = typedArray.getDimensionPixelOffset(index, layout17.guideEnd);
                    continue;
                case 19:
                    Layout layout18 = constraint.layout;
                    layout18.guidePercent = typedArray.getFloat(index, layout18.guidePercent);
                    continue;
                case 20:
                    Layout layout19 = constraint.layout;
                    layout19.horizontalBias = typedArray.getFloat(index, layout19.horizontalBias);
                    continue;
                case 21:
                    Layout layout20 = constraint.layout;
                    layout20.mHeight = typedArray.getLayoutDimension(index, layout20.mHeight);
                    continue;
                case 22:
                    PropertySet propertySet = constraint.propertySet;
                    propertySet.visibility = typedArray.getInt(index, propertySet.visibility);
                    PropertySet propertySet2 = constraint.propertySet;
                    propertySet2.visibility = VISIBILITY_FLAGS[propertySet2.visibility];
                    continue;
                case 23:
                    Layout layout21 = constraint.layout;
                    layout21.mWidth = typedArray.getLayoutDimension(index, layout21.mWidth);
                    continue;
                case 24:
                    Layout layout22 = constraint.layout;
                    layout22.leftMargin = typedArray.getDimensionPixelSize(index, layout22.leftMargin);
                    continue;
                case 25:
                    Layout layout23 = constraint.layout;
                    layout23.leftToLeft = lookupID(typedArray, index, layout23.leftToLeft);
                    continue;
                case 26:
                    Layout layout24 = constraint.layout;
                    layout24.leftToRight = lookupID(typedArray, index, layout24.leftToRight);
                    continue;
                case 27:
                    Layout layout25 = constraint.layout;
                    layout25.orientation = typedArray.getInt(index, layout25.orientation);
                    continue;
                case 28:
                    Layout layout26 = constraint.layout;
                    layout26.rightMargin = typedArray.getDimensionPixelSize(index, layout26.rightMargin);
                    continue;
                case 29:
                    Layout layout27 = constraint.layout;
                    layout27.rightToLeft = lookupID(typedArray, index, layout27.rightToLeft);
                    continue;
                case 30:
                    Layout layout28 = constraint.layout;
                    layout28.rightToRight = lookupID(typedArray, index, layout28.rightToRight);
                    continue;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        Layout layout29 = constraint.layout;
                        layout29.startMargin = typedArray.getDimensionPixelSize(index, layout29.startMargin);
                    } else {
                        continue;
                    }
                case 32:
                    Layout layout30 = constraint.layout;
                    layout30.startToEnd = lookupID(typedArray, index, layout30.startToEnd);
                    continue;
                case 33:
                    Layout layout31 = constraint.layout;
                    layout31.startToStart = lookupID(typedArray, index, layout31.startToStart);
                    continue;
                case 34:
                    Layout layout32 = constraint.layout;
                    layout32.topMargin = typedArray.getDimensionPixelSize(index, layout32.topMargin);
                    continue;
                case 35:
                    Layout layout33 = constraint.layout;
                    layout33.topToBottom = lookupID(typedArray, index, layout33.topToBottom);
                    continue;
                case 36:
                    Layout layout34 = constraint.layout;
                    layout34.topToTop = lookupID(typedArray, index, layout34.topToTop);
                    continue;
                case 37:
                    Layout layout35 = constraint.layout;
                    layout35.verticalBias = typedArray.getFloat(index, layout35.verticalBias);
                    continue;
                case 38:
                    constraint.f2296a = typedArray.getResourceId(index, constraint.f2296a);
                    continue;
                case 39:
                    Layout layout36 = constraint.layout;
                    layout36.horizontalWeight = typedArray.getFloat(index, layout36.horizontalWeight);
                    continue;
                case 40:
                    Layout layout37 = constraint.layout;
                    layout37.verticalWeight = typedArray.getFloat(index, layout37.verticalWeight);
                    continue;
                case 41:
                    Layout layout38 = constraint.layout;
                    layout38.horizontalChainStyle = typedArray.getInt(index, layout38.horizontalChainStyle);
                    continue;
                case 42:
                    Layout layout39 = constraint.layout;
                    layout39.verticalChainStyle = typedArray.getInt(index, layout39.verticalChainStyle);
                    continue;
                case 43:
                    PropertySet propertySet3 = constraint.propertySet;
                    propertySet3.alpha = typedArray.getFloat(index, propertySet3.alpha);
                    continue;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        Transform transform = constraint.transform;
                        transform.applyElevation = true;
                        transform.elevation = typedArray.getDimension(index, transform.elevation);
                    } else {
                        continue;
                    }
                case 45:
                    Transform transform2 = constraint.transform;
                    transform2.rotationX = typedArray.getFloat(index, transform2.rotationX);
                    continue;
                case 46:
                    Transform transform3 = constraint.transform;
                    transform3.rotationY = typedArray.getFloat(index, transform3.rotationY);
                    continue;
                case 47:
                    Transform transform4 = constraint.transform;
                    transform4.scaleX = typedArray.getFloat(index, transform4.scaleX);
                    continue;
                case 48:
                    Transform transform5 = constraint.transform;
                    transform5.scaleY = typedArray.getFloat(index, transform5.scaleY);
                    continue;
                case 49:
                    Transform transform6 = constraint.transform;
                    transform6.transformPivotX = typedArray.getDimension(index, transform6.transformPivotX);
                    continue;
                case 50:
                    Transform transform7 = constraint.transform;
                    transform7.transformPivotY = typedArray.getDimension(index, transform7.transformPivotY);
                    continue;
                case 51:
                    Transform transform8 = constraint.transform;
                    transform8.translationX = typedArray.getDimension(index, transform8.translationX);
                    continue;
                case 52:
                    Transform transform9 = constraint.transform;
                    transform9.translationY = typedArray.getDimension(index, transform9.translationY);
                    continue;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        Transform transform10 = constraint.transform;
                        transform10.translationZ = typedArray.getDimension(index, transform10.translationZ);
                    } else {
                        continue;
                    }
                case 54:
                    Layout layout40 = constraint.layout;
                    layout40.widthDefault = typedArray.getInt(index, layout40.widthDefault);
                    continue;
                case 55:
                    Layout layout41 = constraint.layout;
                    layout41.heightDefault = typedArray.getInt(index, layout41.heightDefault);
                    continue;
                case 56:
                    Layout layout42 = constraint.layout;
                    layout42.widthMax = typedArray.getDimensionPixelSize(index, layout42.widthMax);
                    continue;
                case 57:
                    Layout layout43 = constraint.layout;
                    layout43.heightMax = typedArray.getDimensionPixelSize(index, layout43.heightMax);
                    continue;
                case 58:
                    Layout layout44 = constraint.layout;
                    layout44.widthMin = typedArray.getDimensionPixelSize(index, layout44.widthMin);
                    continue;
                case 59:
                    Layout layout45 = constraint.layout;
                    layout45.heightMin = typedArray.getDimensionPixelSize(index, layout45.heightMin);
                    continue;
                case 60:
                    Transform transform11 = constraint.transform;
                    transform11.rotation = typedArray.getFloat(index, transform11.rotation);
                    continue;
                case 61:
                    Layout layout46 = constraint.layout;
                    layout46.circleConstraint = lookupID(typedArray, index, layout46.circleConstraint);
                    continue;
                case 62:
                    Layout layout47 = constraint.layout;
                    layout47.circleRadius = typedArray.getDimensionPixelSize(index, layout47.circleRadius);
                    continue;
                case 63:
                    Layout layout48 = constraint.layout;
                    layout48.circleAngle = typedArray.getFloat(index, layout48.circleAngle);
                    continue;
                case 64:
                    Motion motion3 = constraint.motion;
                    motion3.mAnimateRelativeTo = lookupID(typedArray, index, motion3.mAnimateRelativeTo);
                    continue;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        motion = constraint.motion;
                        str = typedArray.getString(index);
                    } else {
                        motion = constraint.motion;
                        str = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                    }
                    motion.mTransitionEasing = str;
                    continue;
                case 66:
                    constraint.motion.mDrawPath = typedArray.getInt(index, 0);
                    continue;
                case 67:
                    Motion motion4 = constraint.motion;
                    motion4.mPathRotate = typedArray.getFloat(index, motion4.mPathRotate);
                    continue;
                case 68:
                    PropertySet propertySet4 = constraint.propertySet;
                    propertySet4.mProgress = typedArray.getFloat(index, propertySet4.mProgress);
                    continue;
                case 69:
                    constraint.layout.widthPercent = typedArray.getFloat(index, 1.0f);
                    continue;
                case 70:
                    constraint.layout.heightPercent = typedArray.getFloat(index, 1.0f);
                    continue;
                case 71:
                    Log.e(TAG, "CURRENTLY UNSUPPORTED");
                    continue;
                case 72:
                    Layout layout49 = constraint.layout;
                    layout49.mBarrierDirection = typedArray.getInt(index, layout49.mBarrierDirection);
                    continue;
                case 73:
                    Layout layout50 = constraint.layout;
                    layout50.mBarrierMargin = typedArray.getDimensionPixelSize(index, layout50.mBarrierMargin);
                    continue;
                case 74:
                    constraint.layout.mReferenceIdString = typedArray.getString(index);
                    continue;
                case 75:
                    Layout layout51 = constraint.layout;
                    layout51.mBarrierAllowsGoneWidgets = typedArray.getBoolean(index, layout51.mBarrierAllowsGoneWidgets);
                    continue;
                case 76:
                    Motion motion5 = constraint.motion;
                    motion5.mPathMotionArc = typedArray.getInt(index, motion5.mPathMotionArc);
                    continue;
                case 77:
                    constraint.layout.mConstraintTag = typedArray.getString(index);
                    continue;
                case 78:
                    PropertySet propertySet5 = constraint.propertySet;
                    propertySet5.mVisibilityMode = typedArray.getInt(index, propertySet5.mVisibilityMode);
                    continue;
                case 79:
                    Motion motion6 = constraint.motion;
                    motion6.mMotionStagger = typedArray.getFloat(index, motion6.mMotionStagger);
                    continue;
                case 80:
                    Layout layout52 = constraint.layout;
                    layout52.constrainedWidth = typedArray.getBoolean(index, layout52.constrainedWidth);
                    continue;
                case 81:
                    Layout layout53 = constraint.layout;
                    layout53.constrainedHeight = typedArray.getBoolean(index, layout53.constrainedHeight);
                    continue;
                case 82:
                    Motion motion7 = constraint.motion;
                    motion7.mAnimateCircleAngleTo = typedArray.getInteger(index, motion7.mAnimateCircleAngleTo);
                    continue;
                case 83:
                    Transform transform12 = constraint.transform;
                    transform12.transformPivotTarget = lookupID(typedArray, index, transform12.transformPivotTarget);
                    continue;
                case 84:
                    Motion motion8 = constraint.motion;
                    motion8.mQuantizeMotionSteps = typedArray.getInteger(index, motion8.mQuantizeMotionSteps);
                    continue;
                case 85:
                    Motion motion9 = constraint.motion;
                    motion9.mQuantizeMotionPhase = typedArray.getFloat(index, motion9.mQuantizeMotionPhase);
                    continue;
                case 86:
                    int i3 = typedArray.peekValue(index).type;
                    if (i3 == 1) {
                        constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                        motion2 = constraint.motion;
                        if (motion2.mQuantizeInterpolatorID == -1) {
                            continue;
                        }
                        motion2.mQuantizeInterpolatorType = -2;
                    } else if (i3 == 3) {
                        constraint.motion.mQuantizeInterpolatorString = typedArray.getString(index);
                        if (constraint.motion.mQuantizeInterpolatorString.indexOf("/") > 0) {
                            constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                            motion2 = constraint.motion;
                            motion2.mQuantizeInterpolatorType = -2;
                        } else {
                            constraint.motion.mQuantizeInterpolatorType = -1;
                        }
                    } else {
                        Motion motion10 = constraint.motion;
                        motion10.mQuantizeInterpolatorType = typedArray.getInteger(index, motion10.mQuantizeInterpolatorID);
                    }
                case 87:
                    sb = new StringBuilder();
                    str2 = "unused attribute 0x";
                    break;
                case 88:
                case 89:
                case 90:
                default:
                    sb = new StringBuilder();
                    str2 = "Unknown attribute 0x";
                    break;
                case 91:
                    Layout layout54 = constraint.layout;
                    layout54.baselineToTop = lookupID(typedArray, index, layout54.baselineToTop);
                    continue;
                case 92:
                    Layout layout55 = constraint.layout;
                    layout55.baselineToBottom = lookupID(typedArray, index, layout55.baselineToBottom);
                    continue;
                case 93:
                    Layout layout56 = constraint.layout;
                    layout56.baselineMargin = typedArray.getDimensionPixelSize(index, layout56.baselineMargin);
                    continue;
                case 94:
                    Layout layout57 = constraint.layout;
                    layout57.goneBaselineMargin = typedArray.getDimensionPixelSize(index, layout57.goneBaselineMargin);
                    continue;
                case 95:
                    i(constraint.layout, typedArray, index, 0);
                    continue;
                case 96:
                    i(constraint.layout, typedArray, index, 1);
                    continue;
                case 97:
                    Layout layout58 = constraint.layout;
                    layout58.mWrapBehavior = typedArray.getInt(index, layout58.mWrapBehavior);
                    continue;
            }
            sb.append(str2);
            sb.append(Integer.toHexString(index));
            sb.append("   ");
            sb.append(mapToConstant.get(index));
        }
        Layout layout59 = constraint.layout;
        if (layout59.mReferenceIdString != null) {
            layout59.mReferenceIds = null;
        }
    }

    private static void populateOverride(Context context, Constraint constraint, TypedArray typedArray) {
        int i2;
        int i3;
        int i4;
        int i5;
        int dimensionPixelOffset;
        int i6;
        int layoutDimension;
        int i7;
        float f2;
        float dimension;
        int i8;
        int i9;
        boolean z;
        int i10;
        Motion motion;
        StringBuilder sb;
        String str;
        int indexCount = typedArray.getIndexCount();
        Constraint.Delta delta = new Constraint.Delta();
        constraint.f2298c = delta;
        constraint.motion.mApply = false;
        constraint.layout.mApply = false;
        constraint.propertySet.mApply = false;
        constraint.transform.mApply = false;
        for (int i11 = 0; i11 < indexCount; i11++) {
            int index = typedArray.getIndex(i11);
            float f3 = 1.0f;
            int i12 = 21;
            switch (overrideMapToConstant.get(index)) {
                case 2:
                    i2 = 2;
                    i3 = constraint.layout.bottomMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case 61:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                default:
                    sb = new StringBuilder();
                    str = "Unknown attribute 0x";
                    sb.append(str);
                    sb.append(Integer.toHexString(index));
                    sb.append("   ");
                    sb.append(mapToConstant.get(index));
                    break;
                case 5:
                    i4 = 5;
                    delta.c(i4, typedArray.getString(index));
                    break;
                case 6:
                    i2 = 6;
                    i5 = constraint.layout.editorAbsoluteX;
                    dimensionPixelOffset = typedArray.getDimensionPixelOffset(index, i5);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 7:
                    i2 = 7;
                    i5 = constraint.layout.editorAbsoluteY;
                    dimensionPixelOffset = typedArray.getDimensionPixelOffset(index, i5);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        i2 = 8;
                        i3 = constraint.layout.endMargin;
                        dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                        delta.b(i2, dimensionPixelOffset);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    i2 = 11;
                    i3 = constraint.layout.goneBottomMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 12:
                    i2 = 12;
                    i3 = constraint.layout.goneEndMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 13:
                    i2 = 13;
                    i3 = constraint.layout.goneLeftMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 14:
                    i2 = 14;
                    i3 = constraint.layout.goneRightMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 15:
                    i2 = 15;
                    i3 = constraint.layout.goneStartMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 16:
                    i2 = 16;
                    i3 = constraint.layout.goneTopMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 17:
                    delta.b(17, typedArray.getDimensionPixelOffset(index, constraint.layout.guideBegin));
                    break;
                case 18:
                    i2 = 18;
                    i5 = constraint.layout.guideEnd;
                    dimensionPixelOffset = typedArray.getDimensionPixelOffset(index, i5);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 19:
                    i6 = 19;
                    f3 = constraint.layout.guidePercent;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 20:
                    i6 = 20;
                    f3 = constraint.layout.horizontalBias;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 21:
                    layoutDimension = typedArray.getLayoutDimension(index, constraint.layout.mHeight);
                    delta.b(i12, layoutDimension);
                    break;
                case 22:
                    i2 = 22;
                    dimensionPixelOffset = VISIBILITY_FLAGS[typedArray.getInt(index, constraint.propertySet.visibility)];
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 23:
                    i2 = 23;
                    dimensionPixelOffset = typedArray.getLayoutDimension(index, constraint.layout.mWidth);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 24:
                    i2 = 24;
                    i3 = constraint.layout.leftMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 27:
                    i2 = 27;
                    i7 = constraint.layout.orientation;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 28:
                    i2 = 28;
                    i3 = constraint.layout.rightMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        i2 = 31;
                        i3 = constraint.layout.startMargin;
                        dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                        delta.b(i2, dimensionPixelOffset);
                        break;
                    } else {
                        break;
                    }
                case 34:
                    i2 = 34;
                    i3 = constraint.layout.topMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 37:
                    i6 = 37;
                    f3 = constraint.layout.verticalBias;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 38:
                    dimensionPixelOffset = typedArray.getResourceId(index, constraint.f2296a);
                    constraint.f2296a = dimensionPixelOffset;
                    i2 = 38;
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 39:
                    i6 = 39;
                    f3 = constraint.layout.horizontalWeight;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 40:
                    i6 = 40;
                    f3 = constraint.layout.verticalWeight;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 41:
                    i2 = 41;
                    i7 = constraint.layout.horizontalChainStyle;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 42:
                    i2 = 42;
                    i7 = constraint.layout.verticalChainStyle;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 43:
                    i6 = 43;
                    f3 = constraint.propertySet.alpha;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        i6 = 44;
                        delta.d(44, true);
                        f2 = constraint.transform.elevation;
                        dimension = typedArray.getDimension(index, f2);
                        delta.a(i6, dimension);
                        break;
                    } else {
                        break;
                    }
                case 45:
                    i6 = 45;
                    f3 = constraint.transform.rotationX;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 46:
                    i6 = 46;
                    f3 = constraint.transform.rotationY;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 47:
                    i6 = 47;
                    f3 = constraint.transform.scaleX;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 48:
                    i6 = 48;
                    f3 = constraint.transform.scaleY;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 49:
                    i6 = 49;
                    f2 = constraint.transform.transformPivotX;
                    dimension = typedArray.getDimension(index, f2);
                    delta.a(i6, dimension);
                    break;
                case 50:
                    i6 = 50;
                    f2 = constraint.transform.transformPivotY;
                    dimension = typedArray.getDimension(index, f2);
                    delta.a(i6, dimension);
                    break;
                case 51:
                    i6 = 51;
                    f2 = constraint.transform.translationX;
                    dimension = typedArray.getDimension(index, f2);
                    delta.a(i6, dimension);
                    break;
                case 52:
                    i6 = 52;
                    f2 = constraint.transform.translationY;
                    dimension = typedArray.getDimension(index, f2);
                    delta.a(i6, dimension);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        i6 = 53;
                        f2 = constraint.transform.translationZ;
                        dimension = typedArray.getDimension(index, f2);
                        delta.a(i6, dimension);
                        break;
                    } else {
                        break;
                    }
                case 54:
                    i2 = 54;
                    i7 = constraint.layout.widthDefault;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 55:
                    i2 = 55;
                    i7 = constraint.layout.heightDefault;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 56:
                    i2 = 56;
                    i3 = constraint.layout.widthMax;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 57:
                    i2 = 57;
                    i3 = constraint.layout.heightMax;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 58:
                    i2 = 58;
                    i3 = constraint.layout.widthMin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 59:
                    i2 = 59;
                    i3 = constraint.layout.heightMin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 60:
                    i6 = 60;
                    f3 = constraint.transform.rotation;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 62:
                    i2 = 62;
                    i3 = constraint.layout.circleRadius;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 63:
                    i6 = 63;
                    f3 = constraint.layout.circleAngle;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 64:
                    i2 = 64;
                    i8 = constraint.motion.mAnimateRelativeTo;
                    dimensionPixelOffset = lookupID(typedArray, index, i8);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 65:
                    delta.c(65, typedArray.peekValue(index).type == 3 ? typedArray.getString(index) : Easing.NAMED_EASING[typedArray.getInteger(index, 0)]);
                    break;
                case 66:
                    i2 = 66;
                    dimensionPixelOffset = typedArray.getInt(index, 0);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 67:
                    i6 = 67;
                    f3 = constraint.motion.mPathRotate;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 68:
                    i6 = 68;
                    f3 = constraint.propertySet.mProgress;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 69:
                    i6 = 69;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 70:
                    i6 = 70;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 71:
                    Log.e(TAG, "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    i2 = 72;
                    i7 = constraint.layout.mBarrierDirection;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 73:
                    i2 = 73;
                    i3 = constraint.layout.mBarrierMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 74:
                    i4 = 74;
                    delta.c(i4, typedArray.getString(index));
                    break;
                case 75:
                    i9 = 75;
                    z = constraint.layout.mBarrierAllowsGoneWidgets;
                    delta.d(i9, typedArray.getBoolean(index, z));
                    break;
                case 76:
                    i2 = 76;
                    i7 = constraint.motion.mPathMotionArc;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 77:
                    i4 = 77;
                    delta.c(i4, typedArray.getString(index));
                    break;
                case 78:
                    i2 = 78;
                    i7 = constraint.propertySet.mVisibilityMode;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 79:
                    i6 = 79;
                    f3 = constraint.motion.mMotionStagger;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 80:
                    i9 = 80;
                    z = constraint.layout.constrainedWidth;
                    delta.d(i9, typedArray.getBoolean(index, z));
                    break;
                case 81:
                    i9 = 81;
                    z = constraint.layout.constrainedHeight;
                    delta.d(i9, typedArray.getBoolean(index, z));
                    break;
                case 82:
                    i2 = 82;
                    i10 = constraint.motion.mAnimateCircleAngleTo;
                    dimensionPixelOffset = typedArray.getInteger(index, i10);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 83:
                    i2 = 83;
                    i8 = constraint.transform.transformPivotTarget;
                    dimensionPixelOffset = lookupID(typedArray, index, i8);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 84:
                    i2 = 84;
                    i10 = constraint.motion.mQuantizeMotionSteps;
                    dimensionPixelOffset = typedArray.getInteger(index, i10);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 85:
                    i6 = 85;
                    f3 = constraint.motion.mQuantizeMotionPhase;
                    dimension = typedArray.getFloat(index, f3);
                    delta.a(i6, dimension);
                    break;
                case 86:
                    int i13 = typedArray.peekValue(index).type;
                    i12 = 88;
                    if (i13 == 1) {
                        constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                        delta.b(89, constraint.motion.mQuantizeInterpolatorID);
                        motion = constraint.motion;
                        if (motion.mQuantizeInterpolatorID == -1) {
                            break;
                        }
                        motion.mQuantizeInterpolatorType = -2;
                        delta.b(88, -2);
                        break;
                    } else if (i13 != 3) {
                        Motion motion2 = constraint.motion;
                        motion2.mQuantizeInterpolatorType = typedArray.getInteger(index, motion2.mQuantizeInterpolatorID);
                        layoutDimension = constraint.motion.mQuantizeInterpolatorType;
                        delta.b(i12, layoutDimension);
                        break;
                    } else {
                        constraint.motion.mQuantizeInterpolatorString = typedArray.getString(index);
                        delta.c(90, constraint.motion.mQuantizeInterpolatorString);
                        if (constraint.motion.mQuantizeInterpolatorString.indexOf("/") <= 0) {
                            constraint.motion.mQuantizeInterpolatorType = -1;
                            delta.b(88, -1);
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                            delta.b(89, constraint.motion.mQuantizeInterpolatorID);
                            motion = constraint.motion;
                            motion.mQuantizeInterpolatorType = -2;
                            delta.b(88, -2);
                        }
                    }
                case 87:
                    sb = new StringBuilder();
                    str = "unused attribute 0x";
                    sb.append(str);
                    sb.append(Integer.toHexString(index));
                    sb.append("   ");
                    sb.append(mapToConstant.get(index));
                    break;
                case 93:
                    i2 = 93;
                    i3 = constraint.layout.baselineMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 94:
                    i2 = 94;
                    i3 = constraint.layout.goneBaselineMargin;
                    dimensionPixelOffset = typedArray.getDimensionPixelSize(index, i3);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 95:
                    i(delta, typedArray, index, 0);
                    break;
                case 96:
                    i(delta, typedArray, index, 1);
                    break;
                case 97:
                    i2 = 97;
                    i7 = constraint.layout.mWrapBehavior;
                    dimensionPixelOffset = typedArray.getInt(index, i7);
                    delta.b(i2, dimensionPixelOffset);
                    break;
                case 98:
                    if (MotionLayout.IS_IN_EDIT_MODE) {
                        int resourceId = typedArray.getResourceId(index, constraint.f2296a);
                        constraint.f2296a = resourceId;
                        if (resourceId != -1) {
                            break;
                        }
                        constraint.f2297b = typedArray.getString(index);
                        break;
                    } else {
                        if (typedArray.peekValue(index).type != 3) {
                            constraint.f2296a = typedArray.getResourceId(index, constraint.f2296a);
                            break;
                        }
                        constraint.f2297b = typedArray.getString(index);
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i2, float f2) {
        if (i2 == 19) {
            constraint.layout.guidePercent = f2;
        } else if (i2 == 20) {
            constraint.layout.horizontalBias = f2;
        } else if (i2 == 37) {
            constraint.layout.verticalBias = f2;
        } else if (i2 == 60) {
            constraint.transform.rotation = f2;
        } else if (i2 == 63) {
            constraint.layout.circleAngle = f2;
        } else if (i2 == 79) {
            constraint.motion.mMotionStagger = f2;
        } else if (i2 == 85) {
            constraint.motion.mQuantizeMotionPhase = f2;
        } else if (i2 == 39) {
            constraint.layout.horizontalWeight = f2;
        } else if (i2 == 40) {
            constraint.layout.verticalWeight = f2;
        } else {
            switch (i2) {
                case 43:
                    constraint.propertySet.alpha = f2;
                    return;
                case 44:
                    Transform transform = constraint.transform;
                    transform.elevation = f2;
                    transform.applyElevation = true;
                    return;
                case 45:
                    constraint.transform.rotationX = f2;
                    return;
                case 46:
                    constraint.transform.rotationY = f2;
                    return;
                case 47:
                    constraint.transform.scaleX = f2;
                    return;
                case 48:
                    constraint.transform.scaleY = f2;
                    return;
                case 49:
                    constraint.transform.transformPivotX = f2;
                    return;
                case 50:
                    constraint.transform.transformPivotY = f2;
                    return;
                case 51:
                    constraint.transform.translationX = f2;
                    return;
                case 52:
                    constraint.transform.translationY = f2;
                    return;
                case 53:
                    constraint.transform.translationZ = f2;
                    return;
                default:
                    switch (i2) {
                        case 67:
                            constraint.motion.mPathRotate = f2;
                            return;
                        case 68:
                            constraint.propertySet.mProgress = f2;
                            return;
                        case 69:
                            constraint.layout.widthPercent = f2;
                            return;
                        case 70:
                            constraint.layout.heightPercent = f2;
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i2, int i3) {
        if (i2 == 6) {
            constraint.layout.editorAbsoluteX = i3;
        } else if (i2 == 7) {
            constraint.layout.editorAbsoluteY = i3;
        } else if (i2 == 8) {
            constraint.layout.endMargin = i3;
        } else if (i2 == 27) {
            constraint.layout.orientation = i3;
        } else if (i2 == 28) {
            constraint.layout.rightMargin = i3;
        } else if (i2 == 41) {
            constraint.layout.horizontalChainStyle = i3;
        } else if (i2 == 42) {
            constraint.layout.verticalChainStyle = i3;
        } else if (i2 == 61) {
            constraint.layout.circleConstraint = i3;
        } else if (i2 == 62) {
            constraint.layout.circleRadius = i3;
        } else if (i2 == 72) {
            constraint.layout.mBarrierDirection = i3;
        } else if (i2 == 73) {
            constraint.layout.mBarrierMargin = i3;
        } else if (i2 == 88) {
            constraint.motion.mQuantizeInterpolatorType = i3;
        } else if (i2 == 89) {
            constraint.motion.mQuantizeInterpolatorID = i3;
        } else {
            switch (i2) {
                case 2:
                    constraint.layout.bottomMargin = i3;
                    return;
                case 11:
                    constraint.layout.goneBottomMargin = i3;
                    return;
                case 12:
                    constraint.layout.goneEndMargin = i3;
                    return;
                case 13:
                    constraint.layout.goneLeftMargin = i3;
                    return;
                case 14:
                    constraint.layout.goneRightMargin = i3;
                    return;
                case 15:
                    constraint.layout.goneStartMargin = i3;
                    return;
                case 16:
                    constraint.layout.goneTopMargin = i3;
                    return;
                case 17:
                    constraint.layout.guideBegin = i3;
                    return;
                case 18:
                    constraint.layout.guideEnd = i3;
                    return;
                case 31:
                    constraint.layout.startMargin = i3;
                    return;
                case 34:
                    constraint.layout.topMargin = i3;
                    return;
                case 38:
                    constraint.f2296a = i3;
                    return;
                case 64:
                    constraint.motion.mAnimateRelativeTo = i3;
                    return;
                case 66:
                    constraint.motion.mDrawPath = i3;
                    return;
                case 76:
                    constraint.motion.mPathMotionArc = i3;
                    return;
                case 78:
                    constraint.propertySet.mVisibilityMode = i3;
                    return;
                case 93:
                    constraint.layout.baselineMargin = i3;
                    return;
                case 94:
                    constraint.layout.goneBaselineMargin = i3;
                    return;
                case 97:
                    constraint.layout.mWrapBehavior = i3;
                    return;
                default:
                    switch (i2) {
                        case 21:
                            constraint.layout.mHeight = i3;
                            return;
                        case 22:
                            constraint.propertySet.visibility = i3;
                            return;
                        case 23:
                            constraint.layout.mWidth = i3;
                            return;
                        case 24:
                            constraint.layout.leftMargin = i3;
                            return;
                        default:
                            switch (i2) {
                                case 54:
                                    constraint.layout.widthDefault = i3;
                                    return;
                                case 55:
                                    constraint.layout.heightDefault = i3;
                                    return;
                                case 56:
                                    constraint.layout.widthMax = i3;
                                    return;
                                case 57:
                                    constraint.layout.heightMax = i3;
                                    return;
                                case 58:
                                    constraint.layout.widthMin = i3;
                                    return;
                                case 59:
                                    constraint.layout.heightMin = i3;
                                    return;
                                default:
                                    switch (i2) {
                                        case 82:
                                            constraint.motion.mAnimateCircleAngleTo = i3;
                                            return;
                                        case 83:
                                            constraint.transform.transformPivotTarget = i3;
                                            return;
                                        case 84:
                                            constraint.motion.mQuantizeMotionSteps = i3;
                                            return;
                                        default:
                                            return;
                                    }
                            }
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i2, String str) {
        if (i2 == 5) {
            constraint.layout.dimensionRatio = str;
        } else if (i2 == 65) {
            constraint.motion.mTransitionEasing = str;
        } else if (i2 == 74) {
            Layout layout = constraint.layout;
            layout.mReferenceIdString = str;
            layout.mReferenceIds = null;
        } else if (i2 == 77) {
            constraint.layout.mConstraintTag = str;
        } else if (i2 != 90) {
        } else {
            constraint.motion.mQuantizeInterpolatorString = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i2, boolean z) {
        if (i2 == 44) {
            constraint.transform.applyElevation = z;
        } else if (i2 == 75) {
            constraint.layout.mBarrierAllowsGoneWidgets = z;
        } else if (i2 == 80) {
            constraint.layout.constrainedWidth = z;
        } else if (i2 != 81) {
        } else {
            constraint.layout.constrainedHeight = z;
        }
    }

    private String sideToString(int i2) {
        switch (i2) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    private static String[] splitString(String str) {
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        boolean z = false;
        for (int i3 = 0; i3 < charArray.length; i3++) {
            if (charArray[i3] == ',' && !z) {
                arrayList.add(new String(charArray, i2, i3 - i2));
                i2 = i3 + 1;
            } else if (charArray[i3] == '\"') {
                z = !z;
            }
        }
        arrayList.add(new String(charArray, i2, charArray.length - i2));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public void addColorAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.COLOR_TYPE, strArr);
    }

    public void addFloatAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.FLOAT_TYPE, strArr);
    }

    public void addIntAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.INT_TYPE, strArr);
    }

    public void addStringAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.STRING_TYPE, strArr);
    }

    public void addToHorizontalChain(int i2, int i3, int i4) {
        connect(i2, 1, i3, i3 == 0 ? 1 : 2, 0);
        connect(i2, 2, i4, i4 == 0 ? 2 : 1, 0);
        if (i3 != 0) {
            connect(i3, 2, i2, 1, 0);
        }
        if (i4 != 0) {
            connect(i4, 1, i2, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int i2, int i3, int i4) {
        connect(i2, 6, i3, i3 == 0 ? 6 : 7, 0);
        connect(i2, 7, i4, i4 == 0 ? 7 : 6, 0);
        if (i3 != 0) {
            connect(i3, 7, i2, 6, 0);
        }
        if (i4 != 0) {
            connect(i4, 6, i2, 7, 0);
        }
    }

    public void addToVerticalChain(int i2, int i3, int i4) {
        connect(i2, 3, i3, i3 == 0 ? 3 : 4, 0);
        connect(i2, 4, i4, i4 == 0 ? 4 : 3, 0);
        if (i3 != 0) {
            connect(i3, 4, i2, 3, 0);
        }
        if (i4 != 0) {
            connect(i4, 3, i2, 4, 0);
        }
    }

    public void applyCustomAttributes(ConstraintLayout constraintLayout) {
        Constraint constraint;
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                StringBuilder sb = new StringBuilder();
                sb.append("id unknown ");
                sb.append(Debug.getName(childAt));
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else {
                if (this.mConstraints.containsKey(Integer.valueOf(id)) && (constraint = this.mConstraints.get(Integer.valueOf(id))) != null) {
                    ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                }
            }
        }
    }

    public void applyDeltaFrom(ConstraintSet constraintSet) {
        for (Constraint constraint : constraintSet.mConstraints.values()) {
            if (constraint.f2298c != null) {
                if (constraint.f2297b != null) {
                    for (Integer num : this.mConstraints.keySet()) {
                        Constraint constraint2 = getConstraint(num.intValue());
                        String str = constraint2.layout.mConstraintTag;
                        if (str != null && constraint.f2297b.matches(str)) {
                            constraint.f2298c.e(constraint2);
                            constraint2.mCustomConstraints.putAll((HashMap) constraint.mCustomConstraints.clone());
                        }
                    }
                } else {
                    constraint.f2298c.e(getConstraint(constraint.f2296a));
                }
            }
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        h(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void applyToHelper(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        Constraint constraint;
        int id = constraintHelper.getId();
        if (this.mConstraints.containsKey(Integer.valueOf(id)) && (constraint = this.mConstraints.get(Integer.valueOf(id))) != null && (constraintWidget instanceof HelperWidget)) {
            constraintHelper.loadParameters(constraint, (HelperWidget) constraintWidget, layoutParams, sparseArray);
        }
    }

    public void applyToLayoutParams(int i2, ConstraintLayout.LayoutParams layoutParams) {
        Constraint constraint;
        if (!this.mConstraints.containsKey(Integer.valueOf(i2)) || (constraint = this.mConstraints.get(Integer.valueOf(i2))) == null) {
            return;
        }
        constraint.applyTo(layoutParams);
    }

    public void applyToWithoutCustom(ConstraintLayout constraintLayout) {
        h(constraintLayout, false);
        constraintLayout.setConstraintSet(null);
    }

    public void center(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f2) {
        Constraint constraint;
        if (i5 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (i8 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (f2 <= 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if (i4 == 1 || i4 == 2) {
            connect(i2, 1, i3, i4, i5);
            connect(i2, 2, i6, i7, i8);
            constraint = this.mConstraints.get(Integer.valueOf(i2));
            if (constraint == null) {
                return;
            }
        } else if (i4 != 6 && i4 != 7) {
            connect(i2, 3, i3, i4, i5);
            connect(i2, 4, i6, i7, i8);
            Constraint constraint2 = this.mConstraints.get(Integer.valueOf(i2));
            if (constraint2 != null) {
                constraint2.layout.verticalBias = f2;
                return;
            }
            return;
        } else {
            connect(i2, 6, i3, i4, i5);
            connect(i2, 7, i6, i7, i8);
            constraint = this.mConstraints.get(Integer.valueOf(i2));
            if (constraint == null) {
                return;
            }
        }
        constraint.layout.horizontalBias = f2;
    }

    public void centerHorizontally(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        float f2;
        ConstraintSet constraintSet;
        int i8;
        int i9;
        int i10;
        if (i3 == 0) {
            i9 = 0;
            i4 = 1;
            i5 = 0;
            i10 = 0;
            i6 = 2;
            i7 = 0;
            f2 = 0.5f;
            constraintSet = this;
            i8 = i2;
        } else {
            i4 = 2;
            i5 = 0;
            i6 = 1;
            i7 = 0;
            f2 = 0.5f;
            constraintSet = this;
            i8 = i2;
            i9 = i3;
            i10 = i3;
        }
        constraintSet.center(i8, i9, i4, i5, i10, i6, i7, f2);
    }

    public void centerHorizontally(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f2) {
        connect(i2, 1, i3, i4, i5);
        connect(i2, 2, i6, i7, i8);
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i2));
        if (constraint != null) {
            constraint.layout.horizontalBias = f2;
        }
    }

    public void centerHorizontallyRtl(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        float f2;
        ConstraintSet constraintSet;
        int i8;
        int i9;
        int i10;
        if (i3 == 0) {
            i9 = 0;
            i4 = 6;
            i5 = 0;
            i10 = 0;
            i6 = 7;
            i7 = 0;
            f2 = 0.5f;
            constraintSet = this;
            i8 = i2;
        } else {
            i4 = 7;
            i5 = 0;
            i6 = 6;
            i7 = 0;
            f2 = 0.5f;
            constraintSet = this;
            i8 = i2;
            i9 = i3;
            i10 = i3;
        }
        constraintSet.center(i8, i9, i4, i5, i10, i6, i7, f2);
    }

    public void centerHorizontallyRtl(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f2) {
        connect(i2, 6, i3, i4, i5);
        connect(i2, 7, i6, i7, i8);
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i2));
        if (constraint != null) {
            constraint.layout.horizontalBias = f2;
        }
    }

    public void centerVertically(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        float f2;
        ConstraintSet constraintSet;
        int i8;
        int i9;
        int i10;
        if (i3 == 0) {
            i9 = 0;
            i4 = 3;
            i5 = 0;
            i10 = 0;
            i6 = 4;
            i7 = 0;
            f2 = 0.5f;
            constraintSet = this;
            i8 = i2;
        } else {
            i4 = 4;
            i5 = 0;
            i6 = 3;
            i7 = 0;
            f2 = 0.5f;
            constraintSet = this;
            i8 = i2;
            i9 = i3;
            i10 = i3;
        }
        constraintSet.center(i8, i9, i4, i5, i10, i6, i7, f2);
    }

    public void centerVertically(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f2) {
        connect(i2, 3, i3, i4, i5);
        connect(i2, 4, i6, i7, i8);
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i2));
        if (constraint != null) {
            constraint.layout.verticalBias = f2;
        }
    }

    public void clear(int i2) {
        this.mConstraints.remove(Integer.valueOf(i2));
    }

    public void clear(int i2, int i3) {
        Constraint constraint;
        if (!this.mConstraints.containsKey(Integer.valueOf(i2)) || (constraint = this.mConstraints.get(Integer.valueOf(i2))) == null) {
            return;
        }
        switch (i3) {
            case 1:
                Layout layout = constraint.layout;
                layout.leftToRight = -1;
                layout.leftToLeft = -1;
                layout.leftMargin = -1;
                layout.goneLeftMargin = Integer.MIN_VALUE;
                return;
            case 2:
                Layout layout2 = constraint.layout;
                layout2.rightToRight = -1;
                layout2.rightToLeft = -1;
                layout2.rightMargin = -1;
                layout2.goneRightMargin = Integer.MIN_VALUE;
                return;
            case 3:
                Layout layout3 = constraint.layout;
                layout3.topToBottom = -1;
                layout3.topToTop = -1;
                layout3.topMargin = 0;
                layout3.goneTopMargin = Integer.MIN_VALUE;
                return;
            case 4:
                Layout layout4 = constraint.layout;
                layout4.bottomToTop = -1;
                layout4.bottomToBottom = -1;
                layout4.bottomMargin = 0;
                layout4.goneBottomMargin = Integer.MIN_VALUE;
                return;
            case 5:
                Layout layout5 = constraint.layout;
                layout5.baselineToBaseline = -1;
                layout5.baselineToTop = -1;
                layout5.baselineToBottom = -1;
                layout5.baselineMargin = 0;
                layout5.goneBaselineMargin = Integer.MIN_VALUE;
                return;
            case 6:
                Layout layout6 = constraint.layout;
                layout6.startToEnd = -1;
                layout6.startToStart = -1;
                layout6.startMargin = 0;
                layout6.goneStartMargin = Integer.MIN_VALUE;
                return;
            case 7:
                Layout layout7 = constraint.layout;
                layout7.endToStart = -1;
                layout7.endToEnd = -1;
                layout7.endMargin = 0;
                layout7.goneEndMargin = Integer.MIN_VALUE;
                return;
            case 8:
                Layout layout8 = constraint.layout;
                layout8.circleAngle = -1.0f;
                layout8.circleRadius = -1;
                layout8.circleConstraint = -1;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void clone(Context context, int i2) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null));
    }

    public void clone(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.mConstraints.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                this.mConstraints.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            if (constraint != null) {
                constraint.mCustomConstraints = ConstraintAttribute.extractAttributes(this.mSavedAttributes, childAt);
                constraint.fillFrom(id, layoutParams);
                constraint.propertySet.visibility = childAt.getVisibility();
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 17) {
                    constraint.propertySet.alpha = childAt.getAlpha();
                    constraint.transform.rotation = childAt.getRotation();
                    constraint.transform.rotationX = childAt.getRotationX();
                    constraint.transform.rotationY = childAt.getRotationY();
                    constraint.transform.scaleX = childAt.getScaleX();
                    constraint.transform.scaleY = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        Transform transform = constraint.transform;
                        transform.transformPivotX = pivotX;
                        transform.transformPivotY = pivotY;
                    }
                    constraint.transform.translationX = childAt.getTranslationX();
                    constraint.transform.translationY = childAt.getTranslationY();
                    if (i3 >= 21) {
                        constraint.transform.translationZ = childAt.getTranslationZ();
                        Transform transform2 = constraint.transform;
                        if (transform2.applyElevation) {
                            transform2.elevation = childAt.getElevation();
                        }
                    }
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    constraint.layout.mBarrierAllowsGoneWidgets = barrier.getAllowsGoneWidget();
                    constraint.layout.mReferenceIds = barrier.getReferencedIds();
                    constraint.layout.mBarrierDirection = barrier.getType();
                    constraint.layout.mBarrierMargin = barrier.getMargin();
                }
            }
        }
    }

    public void clone(ConstraintSet constraintSet) {
        this.mConstraints.clear();
        for (Integer num : constraintSet.mConstraints.keySet()) {
            Constraint constraint = constraintSet.mConstraints.get(num);
            if (constraint != null) {
                this.mConstraints.put(num, constraint.m5clone());
            }
        }
    }

    public void clone(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.mConstraints.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraints.getChildAt(i2);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                this.mConstraints.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            if (constraint != null) {
                if (childAt instanceof ConstraintHelper) {
                    constraint.fillFromConstraints((ConstraintHelper) childAt, id, layoutParams);
                }
                constraint.fillFromConstraints(id, layoutParams);
            }
        }
    }

    public void connect(int i2, int i3, int i4, int i5) {
        Layout layout;
        Layout layout2;
        if (!this.mConstraints.containsKey(Integer.valueOf(i2))) {
            this.mConstraints.put(Integer.valueOf(i2), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i2));
        if (constraint == null) {
            return;
        }
        switch (i3) {
            case 1:
                if (i5 == 1) {
                    Layout layout3 = constraint.layout;
                    layout3.leftToLeft = i4;
                    layout3.leftToRight = -1;
                    return;
                } else if (i5 == 2) {
                    Layout layout4 = constraint.layout;
                    layout4.leftToRight = i4;
                    layout4.leftToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("left to " + sideToString(i5) + " undefined");
                }
            case 2:
                if (i5 == 1) {
                    Layout layout5 = constraint.layout;
                    layout5.rightToLeft = i4;
                    layout5.rightToRight = -1;
                    return;
                } else if (i5 == 2) {
                    Layout layout6 = constraint.layout;
                    layout6.rightToRight = i4;
                    layout6.rightToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                }
            case 3:
                if (i5 == 3) {
                    layout = constraint.layout;
                    layout.topToTop = i4;
                    layout.topToBottom = -1;
                    break;
                } else if (i5 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    layout = constraint.layout;
                    layout.topToBottom = i4;
                    layout.topToTop = -1;
                    break;
                }
            case 4:
                if (i5 == 4) {
                    layout = constraint.layout;
                    layout.bottomToBottom = i4;
                    layout.bottomToTop = -1;
                    break;
                } else if (i5 != 3) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    layout = constraint.layout;
                    layout.bottomToTop = i4;
                    layout.bottomToBottom = -1;
                    break;
                }
            case 5:
                if (i5 == 5) {
                    layout2 = constraint.layout;
                    layout2.baselineToBaseline = i4;
                } else if (i5 == 3) {
                    layout2 = constraint.layout;
                    layout2.baselineToTop = i4;
                } else if (i5 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    layout2 = constraint.layout;
                    layout2.baselineToBottom = i4;
                }
                layout2.bottomToBottom = -1;
                layout2.bottomToTop = -1;
                layout2.topToTop = -1;
                layout2.topToBottom = -1;
                return;
            case 6:
                if (i5 == 6) {
                    Layout layout7 = constraint.layout;
                    layout7.startToStart = i4;
                    layout7.startToEnd = -1;
                    return;
                } else if (i5 == 7) {
                    Layout layout8 = constraint.layout;
                    layout8.startToEnd = i4;
                    layout8.startToStart = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                }
            case 7:
                if (i5 == 7) {
                    Layout layout9 = constraint.layout;
                    layout9.endToEnd = i4;
                    layout9.endToStart = -1;
                    return;
                } else if (i5 == 6) {
                    Layout layout10 = constraint.layout;
                    layout10.endToStart = i4;
                    layout10.endToEnd = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                }
            default:
                throw new IllegalArgumentException(sideToString(i3) + " to " + sideToString(i5) + " unknown");
        }
        layout.baselineToBaseline = -1;
        layout.baselineToTop = -1;
        layout.baselineToBottom = -1;
    }

    public void connect(int i2, int i3, int i4, int i5, int i6) {
        Layout layout;
        Layout layout2;
        Layout layout3;
        if (!this.mConstraints.containsKey(Integer.valueOf(i2))) {
            this.mConstraints.put(Integer.valueOf(i2), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i2));
        if (constraint == null) {
            return;
        }
        switch (i3) {
            case 1:
                if (i5 == 1) {
                    Layout layout4 = constraint.layout;
                    layout4.leftToLeft = i4;
                    layout4.leftToRight = -1;
                } else if (i5 != 2) {
                    throw new IllegalArgumentException("Left to " + sideToString(i5) + " undefined");
                } else {
                    Layout layout5 = constraint.layout;
                    layout5.leftToRight = i4;
                    layout5.leftToLeft = -1;
                }
                constraint.layout.leftMargin = i6;
                return;
            case 2:
                if (i5 == 1) {
                    Layout layout6 = constraint.layout;
                    layout6.rightToLeft = i4;
                    layout6.rightToRight = -1;
                } else if (i5 != 2) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    Layout layout7 = constraint.layout;
                    layout7.rightToRight = i4;
                    layout7.rightToLeft = -1;
                }
                constraint.layout.rightMargin = i6;
                return;
            case 3:
                if (i5 == 3) {
                    layout = constraint.layout;
                    layout.topToTop = i4;
                    layout.topToBottom = -1;
                } else if (i5 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    layout = constraint.layout;
                    layout.topToBottom = i4;
                    layout.topToTop = -1;
                }
                layout.baselineToBaseline = -1;
                layout.baselineToTop = -1;
                layout.baselineToBottom = -1;
                constraint.layout.topMargin = i6;
                return;
            case 4:
                if (i5 == 4) {
                    layout2 = constraint.layout;
                    layout2.bottomToBottom = i4;
                    layout2.bottomToTop = -1;
                } else if (i5 != 3) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    layout2 = constraint.layout;
                    layout2.bottomToTop = i4;
                    layout2.bottomToBottom = -1;
                }
                layout2.baselineToBaseline = -1;
                layout2.baselineToTop = -1;
                layout2.baselineToBottom = -1;
                constraint.layout.bottomMargin = i6;
                return;
            case 5:
                if (i5 == 5) {
                    layout3 = constraint.layout;
                    layout3.baselineToBaseline = i4;
                } else if (i5 == 3) {
                    layout3 = constraint.layout;
                    layout3.baselineToTop = i4;
                } else if (i5 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    layout3 = constraint.layout;
                    layout3.baselineToBottom = i4;
                }
                layout3.bottomToBottom = -1;
                layout3.bottomToTop = -1;
                layout3.topToTop = -1;
                layout3.topToBottom = -1;
                return;
            case 6:
                if (i5 == 6) {
                    Layout layout8 = constraint.layout;
                    layout8.startToStart = i4;
                    layout8.startToEnd = -1;
                } else if (i5 != 7) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    Layout layout9 = constraint.layout;
                    layout9.startToEnd = i4;
                    layout9.startToStart = -1;
                }
                constraint.layout.startMargin = i6;
                return;
            case 7:
                if (i5 == 7) {
                    Layout layout10 = constraint.layout;
                    layout10.endToEnd = i4;
                    layout10.endToStart = -1;
                } else if (i5 != 6) {
                    throw new IllegalArgumentException("right to " + sideToString(i5) + " undefined");
                } else {
                    Layout layout11 = constraint.layout;
                    layout11.endToStart = i4;
                    layout11.endToEnd = -1;
                }
                constraint.layout.endMargin = i6;
                return;
            default:
                throw new IllegalArgumentException(sideToString(i3) + " to " + sideToString(i5) + " unknown");
        }
    }

    public void constrainCircle(int i2, int i3, int i4, float f2) {
        Layout layout = get(i2).layout;
        layout.circleConstraint = i3;
        layout.circleRadius = i4;
        layout.circleAngle = f2;
    }

    public void constrainDefaultHeight(int i2, int i3) {
        get(i2).layout.heightDefault = i3;
    }

    public void constrainDefaultWidth(int i2, int i3) {
        get(i2).layout.widthDefault = i3;
    }

    public void constrainHeight(int i2, int i3) {
        get(i2).layout.mHeight = i3;
    }

    public void constrainMaxHeight(int i2, int i3) {
        get(i2).layout.heightMax = i3;
    }

    public void constrainMaxWidth(int i2, int i3) {
        get(i2).layout.widthMax = i3;
    }

    public void constrainMinHeight(int i2, int i3) {
        get(i2).layout.heightMin = i3;
    }

    public void constrainMinWidth(int i2, int i3) {
        get(i2).layout.widthMin = i3;
    }

    public void constrainPercentHeight(int i2, float f2) {
        get(i2).layout.heightPercent = f2;
    }

    public void constrainPercentWidth(int i2, float f2) {
        get(i2).layout.widthPercent = f2;
    }

    public void constrainWidth(int i2, int i3) {
        get(i2).layout.mWidth = i3;
    }

    public void constrainedHeight(int i2, boolean z) {
        get(i2).layout.constrainedHeight = z;
    }

    public void constrainedWidth(int i2, boolean z) {
        get(i2).layout.constrainedWidth = z;
    }

    public void create(int i2, int i3) {
        Layout layout = get(i2).layout;
        layout.mIsGuideline = true;
        layout.orientation = i3;
    }

    public void createBarrier(int i2, int i3, int i4, int... iArr) {
        Layout layout = get(i2).layout;
        layout.mHelperType = 1;
        layout.mBarrierDirection = i3;
        layout.mBarrierMargin = i4;
        layout.mIsGuideline = false;
        layout.mReferenceIds = iArr;
    }

    public void createHorizontalChain(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6) {
        createHorizontalChain(i2, i3, i4, i5, iArr, fArr, i6, 1, 2);
    }

    public void createHorizontalChainRtl(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6) {
        createHorizontalChain(i2, i3, i4, i5, iArr, fArr, i6, 6, 7);
    }

    public void createVerticalChain(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6) {
        if (iArr.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null && fArr.length != iArr.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null) {
            get(iArr[0]).layout.verticalWeight = fArr[0];
        }
        get(iArr[0]).layout.verticalChainStyle = i6;
        connect(iArr[0], 3, i2, i3, 0);
        for (int i7 = 1; i7 < iArr.length; i7++) {
            int i8 = iArr[i7];
            int i9 = i7 - 1;
            connect(iArr[i7], 3, iArr[i9], 4, 0);
            connect(iArr[i9], 4, iArr[i7], 3, 0);
            if (fArr != null) {
                get(iArr[i7]).layout.verticalWeight = fArr[i7];
            }
        }
        connect(iArr[iArr.length - 1], 4, i4, i5, 0);
    }

    public void dump(MotionScene motionScene, int... iArr) {
        HashSet hashSet;
        Integer[] numArr;
        Set<Integer> keySet = this.mConstraints.keySet();
        if (iArr.length != 0) {
            hashSet = new HashSet();
            for (int i2 : iArr) {
                hashSet.add(Integer.valueOf(i2));
            }
        } else {
            hashSet = new HashSet(keySet);
        }
        System.out.println(hashSet.size() + " constraints");
        StringBuilder sb = new StringBuilder();
        for (Integer num : (Integer[]) hashSet.toArray(new Integer[0])) {
            Constraint constraint = this.mConstraints.get(num);
            if (constraint != null) {
                sb.append("<Constraint id=");
                sb.append(num);
                sb.append(" \n");
                constraint.layout.dump(motionScene, sb);
                sb.append("/>\n");
            }
        }
        System.out.println(sb.toString());
    }

    public boolean getApplyElevation(int i2) {
        return get(i2).transform.applyElevation;
    }

    public Constraint getConstraint(int i2) {
        if (this.mConstraints.containsKey(Integer.valueOf(i2))) {
            return this.mConstraints.get(Integer.valueOf(i2));
        }
        return null;
    }

    public HashMap<String, ConstraintAttribute> getCustomAttributeSet() {
        return this.mSavedAttributes;
    }

    public int getHeight(int i2) {
        return get(i2).layout.mHeight;
    }

    public int[] getKnownIds() {
        Integer[] numArr = (Integer[]) this.mConstraints.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }

    public Constraint getParameters(int i2) {
        return get(i2);
    }

    public int[] getReferencedIds(int i2) {
        int[] iArr = get(i2).layout.mReferenceIds;
        return iArr == null ? new int[0] : Arrays.copyOf(iArr, iArr.length);
    }

    public int getVisibility(int i2) {
        return get(i2).propertySet.visibility;
    }

    public int getVisibilityMode(int i2) {
        return get(i2).propertySet.mVisibilityMode;
    }

    public int getWidth(int i2) {
        return get(i2).layout.mWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(ConstraintLayout constraintLayout, boolean z) {
        View findViewById;
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.mConstraints.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                StringBuilder sb = new StringBuilder();
                sb.append("id unknown ");
                sb.append(Debug.getName(childAt));
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else {
                if (id != -1) {
                    if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                        if (constraint != null) {
                            if (childAt instanceof Barrier) {
                                constraint.layout.mHelperType = 1;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(constraint.layout.mBarrierDirection);
                                barrier.setMargin(constraint.layout.mBarrierMargin);
                                barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
                                Layout layout = constraint.layout;
                                int[] iArr = layout.mReferenceIds;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = layout.mReferenceIdString;
                                    if (str != null) {
                                        layout.mReferenceIds = convertReferenceString(barrier, str);
                                        barrier.setReferencedIds(constraint.layout.mReferenceIds);
                                    }
                                }
                            }
                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                            layoutParams.validate();
                            constraint.applyTo(layoutParams);
                            if (z) {
                                ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                            }
                            childAt.setLayoutParams(layoutParams);
                            PropertySet propertySet = constraint.propertySet;
                            if (propertySet.mVisibilityMode == 0) {
                                childAt.setVisibility(propertySet.visibility);
                            }
                            int i3 = Build.VERSION.SDK_INT;
                            if (i3 >= 17) {
                                childAt.setAlpha(constraint.propertySet.alpha);
                                childAt.setRotation(constraint.transform.rotation);
                                childAt.setRotationX(constraint.transform.rotationX);
                                childAt.setRotationY(constraint.transform.rotationY);
                                childAt.setScaleX(constraint.transform.scaleX);
                                childAt.setScaleY(constraint.transform.scaleY);
                                Transform transform = constraint.transform;
                                if (transform.transformPivotTarget != -1) {
                                    if (((View) childAt.getParent()).findViewById(constraint.transform.transformPivotTarget) != null) {
                                        float top = (findViewById.getTop() + findViewById.getBottom()) / 2.0f;
                                        float left = (findViewById.getLeft() + findViewById.getRight()) / 2.0f;
                                        if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                            childAt.setPivotX(left - childAt.getLeft());
                                            childAt.setPivotY(top - childAt.getTop());
                                        }
                                    }
                                } else {
                                    if (!Float.isNaN(transform.transformPivotX)) {
                                        childAt.setPivotX(constraint.transform.transformPivotX);
                                    }
                                    if (!Float.isNaN(constraint.transform.transformPivotY)) {
                                        childAt.setPivotY(constraint.transform.transformPivotY);
                                    }
                                }
                                childAt.setTranslationX(constraint.transform.translationX);
                                childAt.setTranslationY(constraint.transform.translationY);
                                if (i3 >= 21) {
                                    childAt.setTranslationZ(constraint.transform.translationZ);
                                    Transform transform2 = constraint.transform;
                                    if (transform2.applyElevation) {
                                        childAt.setElevation(transform2.elevation);
                                    }
                                }
                            }
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("WARNING NO CONSTRAINTS for view ");
                        sb2.append(id);
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = this.mConstraints.get(num);
            if (constraint2 != null) {
                if (constraint2.layout.mHelperType == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    Layout layout2 = constraint2.layout;
                    int[] iArr2 = layout2.mReferenceIds;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = layout2.mReferenceIdString;
                        if (str2 != null) {
                            layout2.mReferenceIds = convertReferenceString(barrier2, str2);
                            barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                        }
                    }
                    barrier2.setType(constraint2.layout.mBarrierDirection);
                    barrier2.setMargin(constraint2.layout.mBarrierMargin);
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                    barrier2.validateParams();
                    constraint2.applyTo(generateDefaultLayoutParams);
                    constraintLayout.addView(barrier2, generateDefaultLayoutParams);
                }
                if (constraint2.layout.mIsGuideline) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                    constraint2.applyTo(generateDefaultLayoutParams2);
                    constraintLayout.addView(guideline, generateDefaultLayoutParams2);
                }
            }
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt2 = constraintLayout.getChildAt(i4);
            if (childAt2 instanceof ConstraintHelper) {
                ((ConstraintHelper) childAt2).c(constraintLayout);
            }
        }
    }

    public boolean isForceId() {
        return this.mForceId;
    }

    public void load(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                    continue;
                } else if (eventType != 2) {
                    continue;
                } else {
                    String name = xml.getName();
                    Constraint fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        fillFromAttributeList.layout.mIsGuideline = true;
                    }
                    this.mConstraints.put(Integer.valueOf(fillFromAttributeList.f2296a), fillFromAttributeList);
                    continue;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x01cb, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void load(Context context, XmlPullParser xmlPullParser) {
        Constraint fillFromAttributeList;
        try {
            int eventType = xmlPullParser.getEventType();
            Constraint constraint = null;
            while (eventType != 1) {
                if (eventType != 0) {
                    char c2 = 65535;
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        switch (name.hashCode()) {
                            case -2025855158:
                                if (name.equals("Layout")) {
                                    c2 = 6;
                                    break;
                                }
                                break;
                            case -1984451626:
                                if (name.equals(TypedValues.Motion.NAME)) {
                                    c2 = 7;
                                    break;
                                }
                                break;
                            case -1962203927:
                                if (name.equals(ViewTransition.CONSTRAINT_OVERRIDE)) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case -1269513683:
                                if (name.equals("PropertySet")) {
                                    c2 = 4;
                                    break;
                                }
                                break;
                            case -1238332596:
                                if (name.equals("Transform")) {
                                    c2 = 5;
                                    break;
                                }
                                break;
                            case -71750448:
                                if (name.equals("Guideline")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case 366511058:
                                if (name.equals(ViewTransition.CUSTOM_METHOD)) {
                                    c2 = '\t';
                                    break;
                                }
                                break;
                            case 1331510167:
                                if (name.equals("Barrier")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case 1791837707:
                                if (name.equals(ViewTransition.CUSTOM_ATTRIBUTE)) {
                                    c2 = '\b';
                                    break;
                                }
                                break;
                            case 1803088381:
                                if (name.equals("Constraint")) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                        }
                        switch (c2) {
                            case 0:
                                fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser), false);
                                constraint = fillFromAttributeList;
                                break;
                            case 1:
                                fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser), true);
                                constraint = fillFromAttributeList;
                                break;
                            case 2:
                                fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser), false);
                                Layout layout = fillFromAttributeList.layout;
                                layout.mIsGuideline = true;
                                layout.mApply = true;
                                constraint = fillFromAttributeList;
                                break;
                            case 3:
                                fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser), false);
                                fillFromAttributeList.layout.mHelperType = 1;
                                constraint = fillFromAttributeList;
                                break;
                            case 4:
                                if (constraint == null) {
                                    throw new RuntimeException(ERROR_MESSAGE + xmlPullParser.getLineNumber());
                                }
                                constraint.propertySet.a(context, Xml.asAttributeSet(xmlPullParser));
                                break;
                            case 5:
                                if (constraint == null) {
                                    throw new RuntimeException(ERROR_MESSAGE + xmlPullParser.getLineNumber());
                                }
                                constraint.transform.a(context, Xml.asAttributeSet(xmlPullParser));
                                break;
                            case 6:
                                if (constraint == null) {
                                    throw new RuntimeException(ERROR_MESSAGE + xmlPullParser.getLineNumber());
                                }
                                constraint.layout.a(context, Xml.asAttributeSet(xmlPullParser));
                                break;
                            case 7:
                                if (constraint == null) {
                                    throw new RuntimeException(ERROR_MESSAGE + xmlPullParser.getLineNumber());
                                }
                                constraint.motion.a(context, Xml.asAttributeSet(xmlPullParser));
                                break;
                            case '\b':
                            case '\t':
                                if (constraint == null) {
                                    throw new RuntimeException(ERROR_MESSAGE + xmlPullParser.getLineNumber());
                                }
                                ConstraintAttribute.parse(context, xmlPullParser, constraint.mCustomConstraints);
                                break;
                        }
                    } else if (eventType == 3) {
                        String lowerCase = xmlPullParser.getName().toLowerCase(Locale.ROOT);
                        switch (lowerCase.hashCode()) {
                            case -2075718416:
                                if (lowerCase.equals("guideline")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case -190376483:
                                if (lowerCase.equals("constraint")) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case 426575017:
                                if (lowerCase.equals("constraintoverride")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case 2146106725:
                                if (lowerCase.equals("constraintset")) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                        }
                        if (c2 == 0) {
                            return;
                        }
                        if (c2 == 1 || c2 == 2 || c2 == 3) {
                            this.mConstraints.put(Integer.valueOf(constraint.f2296a), constraint);
                            constraint = null;
                        }
                    }
                } else {
                    xmlPullParser.getName();
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    public void parseColorAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split("=");
            if (split2.length != 2) {
                StringBuilder sb = new StringBuilder();
                sb.append(" Unable to parse ");
                sb.append(split[i2]);
            } else {
                constraint.setColorValue(split2[0], Color.parseColor(split2[1]));
            }
        }
    }

    public void parseFloatAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split("=");
            if (split2.length != 2) {
                StringBuilder sb = new StringBuilder();
                sb.append(" Unable to parse ");
                sb.append(split[i2]);
            } else {
                constraint.setFloatValue(split2[0], Float.parseFloat(split2[1]));
            }
        }
    }

    public void parseIntAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split("=");
            if (split2.length != 2) {
                StringBuilder sb = new StringBuilder();
                sb.append(" Unable to parse ");
                sb.append(split[i2]);
            } else {
                constraint.setFloatValue(split2[0], Integer.decode(split2[1]).intValue());
            }
        }
    }

    public void parseStringAttributes(Constraint constraint, String str) {
        String[] splitString = splitString(str);
        for (int i2 = 0; i2 < splitString.length; i2++) {
            String[] split = splitString[i2].split("=");
            StringBuilder sb = new StringBuilder();
            sb.append(" Unable to parse ");
            sb.append(splitString[i2]);
            constraint.setStringValue(split[0], split[1]);
        }
    }

    public void readFallback(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                this.mConstraints.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            if (constraint != null) {
                if (!constraint.layout.mApply) {
                    constraint.fillFrom(id, layoutParams);
                    if (childAt instanceof ConstraintHelper) {
                        constraint.layout.mReferenceIds = ((ConstraintHelper) childAt).getReferencedIds();
                        if (childAt instanceof Barrier) {
                            Barrier barrier = (Barrier) childAt;
                            constraint.layout.mBarrierAllowsGoneWidgets = barrier.getAllowsGoneWidget();
                            constraint.layout.mBarrierDirection = barrier.getType();
                            constraint.layout.mBarrierMargin = barrier.getMargin();
                        }
                    }
                    constraint.layout.mApply = true;
                }
                PropertySet propertySet = constraint.propertySet;
                if (!propertySet.mApply) {
                    propertySet.visibility = childAt.getVisibility();
                    constraint.propertySet.alpha = childAt.getAlpha();
                    constraint.propertySet.mApply = true;
                }
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 17) {
                    Transform transform = constraint.transform;
                    if (!transform.mApply) {
                        transform.mApply = true;
                        transform.rotation = childAt.getRotation();
                        constraint.transform.rotationX = childAt.getRotationX();
                        constraint.transform.rotationY = childAt.getRotationY();
                        constraint.transform.scaleX = childAt.getScaleX();
                        constraint.transform.scaleY = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (pivotX != 0.0d || pivotY != 0.0d) {
                            Transform transform2 = constraint.transform;
                            transform2.transformPivotX = pivotX;
                            transform2.transformPivotY = pivotY;
                        }
                        constraint.transform.translationX = childAt.getTranslationX();
                        constraint.transform.translationY = childAt.getTranslationY();
                        if (i3 >= 21) {
                            constraint.transform.translationZ = childAt.getTranslationZ();
                            Transform transform3 = constraint.transform;
                            if (transform3.applyElevation) {
                                transform3.elevation = childAt.getElevation();
                            }
                        }
                    }
                }
            }
        }
    }

    public void readFallback(ConstraintSet constraintSet) {
        for (Integer num : constraintSet.mConstraints.keySet()) {
            int intValue = num.intValue();
            Constraint constraint = constraintSet.mConstraints.get(num);
            if (!this.mConstraints.containsKey(Integer.valueOf(intValue))) {
                this.mConstraints.put(Integer.valueOf(intValue), new Constraint());
            }
            Constraint constraint2 = this.mConstraints.get(Integer.valueOf(intValue));
            if (constraint2 != null) {
                Layout layout = constraint2.layout;
                if (!layout.mApply) {
                    layout.copyFrom(constraint.layout);
                }
                PropertySet propertySet = constraint2.propertySet;
                if (!propertySet.mApply) {
                    propertySet.copyFrom(constraint.propertySet);
                }
                Transform transform = constraint2.transform;
                if (!transform.mApply) {
                    transform.copyFrom(constraint.transform);
                }
                Motion motion = constraint2.motion;
                if (!motion.mApply) {
                    motion.copyFrom(constraint.motion);
                }
                for (String str : constraint.mCustomConstraints.keySet()) {
                    if (!constraint2.mCustomConstraints.containsKey(str)) {
                        constraint2.mCustomConstraints.put(str, constraint.mCustomConstraints.get(str));
                    }
                }
            }
        }
    }

    public void removeAttribute(String str) {
        this.mSavedAttributes.remove(str);
    }

    public void removeFromHorizontalChain(int i2) {
        Constraint constraint;
        int i3;
        int i4;
        int i5;
        int i6;
        ConstraintSet constraintSet;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        ConstraintSet constraintSet2;
        int i13;
        if (!this.mConstraints.containsKey(Integer.valueOf(i2)) || (constraint = this.mConstraints.get(Integer.valueOf(i2))) == null) {
            return;
        }
        Layout layout = constraint.layout;
        int i14 = layout.leftToRight;
        int i15 = layout.rightToLeft;
        if (i14 == -1 && i15 == -1) {
            int i16 = layout.startToEnd;
            int i17 = layout.endToStart;
            if (i16 != -1 || i17 != -1) {
                if (i16 != -1 && i17 != -1) {
                    i12 = 0;
                    constraintSet2 = this;
                    constraintSet2.connect(i16, 7, i17, 6, 0);
                    i10 = 6;
                    i11 = 7;
                    i13 = i17;
                    i9 = i14;
                } else if (i17 != -1) {
                    i9 = layout.rightToRight;
                    if (i9 != -1) {
                        i10 = 7;
                        i11 = 7;
                        i12 = 0;
                        constraintSet2 = this;
                        i13 = i14;
                    } else {
                        i9 = layout.leftToLeft;
                        if (i9 != -1) {
                            i10 = 6;
                            i11 = 6;
                            i12 = 0;
                            constraintSet2 = this;
                            i13 = i17;
                        }
                    }
                }
                constraintSet2.connect(i13, i10, i9, i11, i12);
            }
            clear(i2, 6);
            i8 = 7;
        } else {
            if (i14 == -1 || i15 == -1) {
                i3 = layout.rightToRight;
                if (i3 != -1) {
                    i4 = 2;
                    i5 = 2;
                    i6 = 0;
                    constraintSet = this;
                    i7 = i14;
                } else {
                    i3 = layout.leftToLeft;
                    if (i3 != -1) {
                        i4 = 1;
                        i5 = 1;
                        i6 = 0;
                        constraintSet = this;
                        i7 = i15;
                    }
                    clear(i2, 1);
                    i8 = 2;
                }
            } else {
                i6 = 0;
                constraintSet = this;
                constraintSet.connect(i14, 2, i15, 1, 0);
                i4 = 1;
                i5 = 2;
                i7 = i15;
                i3 = i14;
            }
            constraintSet.connect(i7, i4, i3, i5, i6);
            clear(i2, 1);
            i8 = 2;
        }
        clear(i2, i8);
    }

    public void removeFromVerticalChain(int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        ConstraintSet constraintSet;
        int i7;
        if (this.mConstraints.containsKey(Integer.valueOf(i2))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(i2));
            if (constraint == null) {
                return;
            }
            Layout layout = constraint.layout;
            int i8 = layout.topToBottom;
            int i9 = layout.bottomToTop;
            if (i8 != -1 || i9 != -1) {
                if (i8 == -1 || i9 == -1) {
                    i3 = layout.bottomToBottom;
                    if (i3 != -1) {
                        i4 = 4;
                        i5 = 4;
                        i6 = 0;
                        constraintSet = this;
                        i7 = i8;
                    } else {
                        i3 = layout.topToTop;
                        if (i3 != -1) {
                            i4 = 3;
                            i5 = 3;
                            i6 = 0;
                            constraintSet = this;
                            i7 = i9;
                        }
                    }
                } else {
                    i6 = 0;
                    constraintSet = this;
                    constraintSet.connect(i8, 4, i9, 3, 0);
                    i4 = 3;
                    i5 = 4;
                    i7 = i9;
                    i3 = i8;
                }
                constraintSet.connect(i7, i4, i3, i5, i6);
            }
        }
        clear(i2, 3);
        clear(i2, 4);
    }

    public void setAlpha(int i2, float f2) {
        get(i2).propertySet.alpha = f2;
    }

    public void setApplyElevation(int i2, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(i2).transform.applyElevation = z;
        }
    }

    public void setBarrierType(int i2, int i3) {
        get(i2).layout.mHelperType = i3;
    }

    public void setColorValue(int i2, String str, int i3) {
        get(i2).setColorValue(str, i3);
    }

    public void setDimensionRatio(int i2, String str) {
        get(i2).layout.dimensionRatio = str;
    }

    public void setEditorAbsoluteX(int i2, int i3) {
        get(i2).layout.editorAbsoluteX = i3;
    }

    public void setEditorAbsoluteY(int i2, int i3) {
        get(i2).layout.editorAbsoluteY = i3;
    }

    public void setElevation(int i2, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(i2).transform.elevation = f2;
            get(i2).transform.applyElevation = true;
        }
    }

    public void setFloatValue(int i2, String str, float f2) {
        get(i2).setFloatValue(str, f2);
    }

    public void setForceId(boolean z) {
        this.mForceId = z;
    }

    public void setGoneMargin(int i2, int i3, int i4) {
        Constraint constraint = get(i2);
        switch (i3) {
            case 1:
                constraint.layout.goneLeftMargin = i4;
                return;
            case 2:
                constraint.layout.goneRightMargin = i4;
                return;
            case 3:
                constraint.layout.goneTopMargin = i4;
                return;
            case 4:
                constraint.layout.goneBottomMargin = i4;
                return;
            case 5:
                constraint.layout.goneBaselineMargin = i4;
                return;
            case 6:
                constraint.layout.goneStartMargin = i4;
                return;
            case 7:
                constraint.layout.goneEndMargin = i4;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setGuidelineBegin(int i2, int i3) {
        get(i2).layout.guideBegin = i3;
        get(i2).layout.guideEnd = -1;
        get(i2).layout.guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int i2, int i3) {
        get(i2).layout.guideEnd = i3;
        get(i2).layout.guideBegin = -1;
        get(i2).layout.guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int i2, float f2) {
        get(i2).layout.guidePercent = f2;
        get(i2).layout.guideEnd = -1;
        get(i2).layout.guideBegin = -1;
    }

    public void setHorizontalBias(int i2, float f2) {
        get(i2).layout.horizontalBias = f2;
    }

    public void setHorizontalChainStyle(int i2, int i3) {
        get(i2).layout.horizontalChainStyle = i3;
    }

    public void setHorizontalWeight(int i2, float f2) {
        get(i2).layout.horizontalWeight = f2;
    }

    public void setIntValue(int i2, String str, int i3) {
        get(i2).setIntValue(str, i3);
    }

    public void setLayoutWrapBehavior(int i2, int i3) {
        if (i3 < 0 || i3 > 3) {
            return;
        }
        get(i2).layout.mWrapBehavior = i3;
    }

    public void setMargin(int i2, int i3, int i4) {
        Constraint constraint = get(i2);
        switch (i3) {
            case 1:
                constraint.layout.leftMargin = i4;
                return;
            case 2:
                constraint.layout.rightMargin = i4;
                return;
            case 3:
                constraint.layout.topMargin = i4;
                return;
            case 4:
                constraint.layout.bottomMargin = i4;
                return;
            case 5:
                constraint.layout.baselineMargin = i4;
                return;
            case 6:
                constraint.layout.startMargin = i4;
                return;
            case 7:
                constraint.layout.endMargin = i4;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setReferencedIds(int i2, int... iArr) {
        get(i2).layout.mReferenceIds = iArr;
    }

    public void setRotation(int i2, float f2) {
        get(i2).transform.rotation = f2;
    }

    public void setRotationX(int i2, float f2) {
        get(i2).transform.rotationX = f2;
    }

    public void setRotationY(int i2, float f2) {
        get(i2).transform.rotationY = f2;
    }

    public void setScaleX(int i2, float f2) {
        get(i2).transform.scaleX = f2;
    }

    public void setScaleY(int i2, float f2) {
        get(i2).transform.scaleY = f2;
    }

    public void setStringValue(int i2, String str, String str2) {
        get(i2).setStringValue(str, str2);
    }

    public void setTransformPivot(int i2, float f2, float f3) {
        Transform transform = get(i2).transform;
        transform.transformPivotY = f3;
        transform.transformPivotX = f2;
    }

    public void setTransformPivotX(int i2, float f2) {
        get(i2).transform.transformPivotX = f2;
    }

    public void setTransformPivotY(int i2, float f2) {
        get(i2).transform.transformPivotY = f2;
    }

    public void setTranslation(int i2, float f2, float f3) {
        Transform transform = get(i2).transform;
        transform.translationX = f2;
        transform.translationY = f3;
    }

    public void setTranslationX(int i2, float f2) {
        get(i2).transform.translationX = f2;
    }

    public void setTranslationY(int i2, float f2) {
        get(i2).transform.translationY = f2;
    }

    public void setTranslationZ(int i2, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(i2).transform.translationZ = f2;
        }
    }

    public void setValidateOnParse(boolean z) {
        this.mValidate = z;
    }

    public void setVerticalBias(int i2, float f2) {
        get(i2).layout.verticalBias = f2;
    }

    public void setVerticalChainStyle(int i2, int i3) {
        get(i2).layout.verticalChainStyle = i3;
    }

    public void setVerticalWeight(int i2, float f2) {
        get(i2).layout.verticalWeight = f2;
    }

    public void setVisibility(int i2, int i3) {
        get(i2).propertySet.visibility = i3;
    }

    public void setVisibilityMode(int i2, int i3) {
        get(i2).propertySet.mVisibilityMode = i3;
    }

    public void writeState(Writer writer, ConstraintLayout constraintLayout, int i2) {
        writer.write("\n---------------------------------------------\n");
        if ((i2 & 1) == 1) {
            new WriteXmlEngine(writer, constraintLayout, i2).c();
        } else {
            new WriteJsonEngine(writer, constraintLayout, i2).e();
        }
        writer.write("\n---------------------------------------------\n");
    }
}
