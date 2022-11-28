package androidx.constraintlayout.motion.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.Placeholder;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayoutStates;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    private static final boolean DEBUG = false;
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    private static final float EPSILON = 1.0E-5f;
    public static boolean IS_IN_EDIT_MODE = false;
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_NEVER_TO_END = 7;
    public static final int TOUCH_UP_NEVER_TO_START = 6;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    int A;
    int B;
    int C;
    int D;
    int E;
    float F;
    int G;
    int H;
    HashMap I;
    Rect J;
    TransitionState K;
    Model L;
    ArrayList M;

    /* renamed from: f  reason: collision with root package name */
    MotionScene f2078f;

    /* renamed from: g  reason: collision with root package name */
    Interpolator f2079g;

    /* renamed from: h  reason: collision with root package name */
    Interpolator f2080h;

    /* renamed from: i  reason: collision with root package name */
    float f2081i;

    /* renamed from: j  reason: collision with root package name */
    int f2082j;

    /* renamed from: k  reason: collision with root package name */
    HashMap f2083k;

    /* renamed from: l  reason: collision with root package name */
    float f2084l;
    private float lastPos;
    private float lastY;

    /* renamed from: m  reason: collision with root package name */
    float f2085m;
    private long mAnimationStartTime;
    private int mBeginState;
    private RectF mBoundsCheck;
    private DecelerateInterpolator mDecelerateLogic;
    private ArrayList<MotionHelper> mDecoratorsHelpers;
    private boolean mDelayedApply;
    private DesignTool mDesignTool;
    private int mEndState;
    private int mFrames;
    private boolean mInLayout;
    private boolean mInRotation;
    private boolean mInteractionEnabled;
    private Matrix mInverseMatrix;
    private boolean mKeepAnimating;
    private KeyCache mKeyCache;
    private long mLastDrawTime;
    private float mLastFps;
    private int mLastHeightMeasureSpec;
    private int mLastWidthMeasureSpec;
    private float mListenerPosition;
    private int mListenerState;
    private boolean mNeedsFireTransitionCompleted;
    private Runnable mOnComplete;
    private ArrayList<MotionHelper> mOnHideHelpers;
    private ArrayList<MotionHelper> mOnShowHelpers;
    private int mPreRotateHeight;
    private int mPreRotateWidth;
    private int mPreviouseRotation;
    private View mRegionView;
    private int[] mScheduledTransitionTo;
    private StateCache mStateCache;
    private StopLogic mStopLogic;
    private boolean mTemporalInterpolator;
    private float mTransitionDuration;
    private boolean mTransitionInstantly;
    private long mTransitionLastTime;
    private TransitionListener mTransitionListener;
    private CopyOnWriteArrayList<TransitionListener> mTransitionListeners;

    /* renamed from: n  reason: collision with root package name */
    float f2086n;

    /* renamed from: o  reason: collision with root package name */
    boolean f2087o;

    /* renamed from: p  reason: collision with root package name */
    int f2088p;

    /* renamed from: q  reason: collision with root package name */
    DevModeDraw f2089q;

    /* renamed from: r  reason: collision with root package name */
    int f2090r;

    /* renamed from: s  reason: collision with root package name */
    int f2091s;

    /* renamed from: t  reason: collision with root package name */
    boolean f2092t;
    float u;
    float v;
    long w;
    float x;
    protected boolean y;
    int z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.motion.widget.MotionLayout$5  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f2097a;

        static {
            int[] iArr = new int[TransitionState.values().length];
            f2097a = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2097a[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2097a[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2097a[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    class DecelerateInterpolator extends MotionInterpolator {

        /* renamed from: a  reason: collision with root package name */
        float f2098a = 0.0f;

        /* renamed from: b  reason: collision with root package name */
        float f2099b = 0.0f;

        /* renamed from: c  reason: collision with root package name */
        float f2100c;

        DecelerateInterpolator() {
        }

        public void config(float f2, float f3, float f4) {
            this.f2098a = f2;
            this.f2099b = f3;
            this.f2100c = f4;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator, android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3;
            float f4 = this.f2098a;
            if (f4 > 0.0f) {
                float f5 = this.f2100c;
                if (f4 / f5 < f2) {
                    f2 = f4 / f5;
                }
                MotionLayout.this.f2081i = f4 - (f5 * f2);
                f3 = (f4 * f2) - (((f5 * f2) * f2) / 2.0f);
            } else {
                float f6 = this.f2100c;
                if ((-f4) / f6 < f2) {
                    f2 = (-f4) / f6;
                }
                MotionLayout.this.f2081i = (f6 * f2) + f4;
                f3 = (f4 * f2) + (((f6 * f2) * f2) / 2.0f);
            }
            return f3 + this.f2099b;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
        public float getVelocity() {
            return MotionLayout.this.f2081i;
        }
    }

    /* loaded from: classes.dex */
    private class DevModeDraw {
        private static final int DEBUG_PATH_TICKS_PER_MS = 16;

        /* renamed from: a  reason: collision with root package name */
        float[] f2102a;

        /* renamed from: b  reason: collision with root package name */
        int[] f2103b;

        /* renamed from: c  reason: collision with root package name */
        float[] f2104c;

        /* renamed from: d  reason: collision with root package name */
        Path f2105d;

        /* renamed from: e  reason: collision with root package name */
        Paint f2106e;

        /* renamed from: f  reason: collision with root package name */
        Paint f2107f;

        /* renamed from: g  reason: collision with root package name */
        Paint f2108g;

        /* renamed from: h  reason: collision with root package name */
        Paint f2109h;

        /* renamed from: i  reason: collision with root package name */
        Paint f2110i;

        /* renamed from: j  reason: collision with root package name */
        DashPathEffect f2111j;

        /* renamed from: k  reason: collision with root package name */
        int f2112k;

        /* renamed from: l  reason: collision with root package name */
        Rect f2113l = new Rect();

        /* renamed from: m  reason: collision with root package name */
        boolean f2114m = false;
        private float[] mRectangle;

        /* renamed from: n  reason: collision with root package name */
        int f2115n;

        public DevModeDraw() {
            this.f2115n = 1;
            Paint paint = new Paint();
            this.f2106e = paint;
            paint.setAntiAlias(true);
            this.f2106e.setColor(-21965);
            this.f2106e.setStrokeWidth(2.0f);
            this.f2106e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f2107f = paint2;
            paint2.setAntiAlias(true);
            this.f2107f.setColor(-2067046);
            this.f2107f.setStrokeWidth(2.0f);
            this.f2107f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.f2108g = paint3;
            paint3.setAntiAlias(true);
            this.f2108g.setColor(-13391360);
            this.f2108g.setStrokeWidth(2.0f);
            this.f2108g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.f2109h = paint4;
            paint4.setAntiAlias(true);
            this.f2109h.setColor(-13391360);
            this.f2109h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.mRectangle = new float[8];
            Paint paint5 = new Paint();
            this.f2110i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.f2111j = dashPathEffect;
            this.f2108g.setPathEffect(dashPathEffect);
            this.f2104c = new float[100];
            this.f2103b = new int[50];
            if (this.f2114m) {
                this.f2106e.setStrokeWidth(8.0f);
                this.f2110i.setStrokeWidth(8.0f);
                this.f2107f.setStrokeWidth(8.0f);
                this.f2115n = 4;
            }
        }

        private void drawBasicPath(Canvas canvas) {
            canvas.drawLines(this.f2102a, this.f2106e);
        }

        private void drawPathAsConfigured(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i2 = 0; i2 < this.f2112k; i2++) {
                int[] iArr = this.f2103b;
                if (iArr[i2] == 1) {
                    z = true;
                }
                if (iArr[i2] == 0) {
                    z2 = true;
                }
            }
            if (z) {
                drawPathRelative(canvas);
            }
            if (z2) {
                drawPathCartesian(canvas);
            }
        }

        private void drawPathCartesian(Canvas canvas) {
            float[] fArr = this.f2102a;
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[fArr.length - 2];
            float f5 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f2, f4), Math.max(f3, f5), Math.max(f2, f4), Math.max(f3, f5), this.f2108g);
            canvas.drawLine(Math.min(f2, f4), Math.min(f3, f5), Math.min(f2, f4), Math.max(f3, f5), this.f2108g);
        }

        private void drawPathCartesianTicks(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f2102a;
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[fArr.length - 2];
            float f7 = fArr[fArr.length - 1];
            float min = Math.min(f4, f6);
            float max = Math.max(f5, f7);
            float min2 = f2 - Math.min(f4, f6);
            float max2 = Math.max(f5, f7) - f3;
            String str = "" + (((int) (((min2 * 100.0f) / Math.abs(f6 - f4)) + 0.5d)) / 100.0f);
            a(str, this.f2109h);
            canvas.drawText(str, ((min2 / 2.0f) - (this.f2113l.width() / 2)) + min, f3 - 20.0f, this.f2109h);
            canvas.drawLine(f2, f3, Math.min(f4, f6), f3, this.f2108g);
            String str2 = "" + (((int) (((max2 * 100.0f) / Math.abs(f7 - f5)) + 0.5d)) / 100.0f);
            a(str2, this.f2109h);
            canvas.drawText(str2, f2 + 5.0f, max - ((max2 / 2.0f) - (this.f2113l.height() / 2)), this.f2109h);
            canvas.drawLine(f2, f3, f2, Math.max(f5, f7), this.f2108g);
        }

        private void drawPathRelative(Canvas canvas) {
            float[] fArr = this.f2102a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f2108g);
        }

        private void drawPathRelativeTicks(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f2102a;
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[fArr.length - 2];
            float f7 = fArr[fArr.length - 1];
            float hypot = (float) Math.hypot(f4 - f6, f5 - f7);
            float f8 = f6 - f4;
            float f9 = f7 - f5;
            float f10 = (((f2 - f4) * f8) + ((f3 - f5) * f9)) / (hypot * hypot);
            float f11 = f4 + (f8 * f10);
            float f12 = f5 + (f10 * f9);
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f11, f12);
            float hypot2 = (float) Math.hypot(f11 - f2, f12 - f3);
            String str = "" + (((int) ((hypot2 * 100.0f) / hypot)) / 100.0f);
            a(str, this.f2109h);
            canvas.drawTextOnPath(str, path, (hypot2 / 2.0f) - (this.f2113l.width() / 2), -20.0f, this.f2109h);
            canvas.drawLine(f2, f3, f11, f12, this.f2108g);
        }

        private void drawPathScreenTicks(Canvas canvas, float f2, float f3, int i2, int i3) {
            String str = "" + (((int) ((((f2 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i2)) + 0.5d)) / 100.0f);
            a(str, this.f2109h);
            canvas.drawText(str, ((f2 / 2.0f) - (this.f2113l.width() / 2)) + 0.0f, f3 - 20.0f, this.f2109h);
            canvas.drawLine(f2, f3, Math.min(0.0f, 1.0f), f3, this.f2108g);
            String str2 = "" + (((int) ((((f3 - (i3 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i3)) + 0.5d)) / 100.0f);
            a(str2, this.f2109h);
            canvas.drawText(str2, f2 + 5.0f, 0.0f - ((f3 / 2.0f) - (this.f2113l.height() / 2)), this.f2109h);
            canvas.drawLine(f2, f3, f2, Math.max(0.0f, 1.0f), this.f2108g);
        }

        private void drawRectangle(Canvas canvas, MotionController motionController) {
            this.f2105d.reset();
            for (int i2 = 0; i2 <= 50; i2++) {
                motionController.d(i2 / 50, this.mRectangle, 0);
                Path path = this.f2105d;
                float[] fArr = this.mRectangle;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f2105d;
                float[] fArr2 = this.mRectangle;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f2105d;
                float[] fArr3 = this.mRectangle;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f2105d;
                float[] fArr4 = this.mRectangle;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f2105d.close();
            }
            this.f2106e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f2105d, this.f2106e);
            canvas.translate(-2.0f, -2.0f);
            this.f2106e.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawPath(this.f2105d, this.f2106e);
        }

        private void drawTicks(Canvas canvas, int i2, int i3, MotionController motionController) {
            int i4;
            int i5;
            float f2;
            float f3;
            View view = motionController.f2069b;
            if (view != null) {
                i4 = view.getWidth();
                i5 = motionController.f2069b.getHeight();
            } else {
                i4 = 0;
                i5 = 0;
            }
            for (int i6 = 1; i6 < i3 - 1; i6++) {
                if (i2 != 4 || this.f2103b[i6 - 1] != 0) {
                    float[] fArr = this.f2104c;
                    int i7 = i6 * 2;
                    float f4 = fArr[i7];
                    float f5 = fArr[i7 + 1];
                    this.f2105d.reset();
                    this.f2105d.moveTo(f4, f5 + 10.0f);
                    this.f2105d.lineTo(f4 + 10.0f, f5);
                    this.f2105d.lineTo(f4, f5 - 10.0f);
                    this.f2105d.lineTo(f4 - 10.0f, f5);
                    this.f2105d.close();
                    int i8 = i6 - 1;
                    motionController.i(i8);
                    if (i2 == 4) {
                        int[] iArr = this.f2103b;
                        if (iArr[i8] == 1) {
                            drawPathRelativeTicks(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else if (iArr[i8] == 0) {
                            drawPathCartesianTicks(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else if (iArr[i8] == 2) {
                            f2 = f5;
                            f3 = f4;
                            drawPathScreenTicks(canvas, f4 - 0.0f, f5 - 0.0f, i4, i5);
                            canvas.drawPath(this.f2105d, this.f2110i);
                        }
                        f2 = f5;
                        f3 = f4;
                        canvas.drawPath(this.f2105d, this.f2110i);
                    } else {
                        f2 = f5;
                        f3 = f4;
                    }
                    if (i2 == 2) {
                        drawPathRelativeTicks(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i2 == 3) {
                        drawPathCartesianTicks(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i2 == 6) {
                        drawPathScreenTicks(canvas, f3 - 0.0f, f2 - 0.0f, i4, i5);
                    }
                    canvas.drawPath(this.f2105d, this.f2110i);
                }
            }
            float[] fArr2 = this.f2102a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f2107f);
                float[] fArr3 = this.f2102a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f2107f);
            }
        }

        private void drawTranslation(Canvas canvas, float f2, float f3, float f4, float f5) {
            canvas.drawRect(f2, f3, f4, f5, this.f2108g);
            canvas.drawLine(f2, f3, f4, f5, this.f2108g);
        }

        void a(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.f2113l);
        }

        public void draw(Canvas canvas, HashMap<View, MotionController> hashMap, int i2, int i3) {
            if (hashMap == null || hashMap.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i3 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.mEndState) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.f2109h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f2106e);
            }
            for (MotionController motionController : hashMap.values()) {
                int drawPath = motionController.getDrawPath();
                if (i3 > 0 && drawPath == 0) {
                    drawPath = 1;
                }
                if (drawPath != 0) {
                    this.f2112k = motionController.b(this.f2104c, this.f2103b);
                    if (drawPath >= 1) {
                        int i4 = i2 / 16;
                        float[] fArr = this.f2102a;
                        if (fArr == null || fArr.length != i4 * 2) {
                            this.f2102a = new float[i4 * 2];
                            this.f2105d = new Path();
                        }
                        int i5 = this.f2115n;
                        canvas.translate(i5, i5);
                        this.f2106e.setColor(1996488704);
                        this.f2110i.setColor(1996488704);
                        this.f2107f.setColor(1996488704);
                        this.f2108g.setColor(1996488704);
                        motionController.c(this.f2102a, i4);
                        drawAll(canvas, drawPath, this.f2112k, motionController);
                        this.f2106e.setColor(-21965);
                        this.f2107f.setColor(-2067046);
                        this.f2110i.setColor(-2067046);
                        this.f2108g.setColor(-13391360);
                        int i6 = this.f2115n;
                        canvas.translate(-i6, -i6);
                        drawAll(canvas, drawPath, this.f2112k, motionController);
                        if (drawPath == 5) {
                            drawRectangle(canvas, motionController);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void drawAll(Canvas canvas, int i2, int i3, MotionController motionController) {
            if (i2 == 4) {
                drawPathAsConfigured(canvas);
            }
            if (i2 == 2) {
                drawPathRelative(canvas);
            }
            if (i2 == 3) {
                drawPathCartesian(canvas);
            }
            drawBasicPath(canvas);
            drawTicks(canvas, i2, i3, motionController);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Model {

        /* renamed from: a  reason: collision with root package name */
        ConstraintWidgetContainer f2117a = new ConstraintWidgetContainer();

        /* renamed from: b  reason: collision with root package name */
        ConstraintWidgetContainer f2118b = new ConstraintWidgetContainer();

        /* renamed from: c  reason: collision with root package name */
        ConstraintSet f2119c = null;

        /* renamed from: d  reason: collision with root package name */
        ConstraintSet f2120d = null;

        /* renamed from: e  reason: collision with root package name */
        int f2121e;

        /* renamed from: f  reason: collision with root package name */
        int f2122f;

        Model() {
        }

        private void computeStartEndSize(int i2, int i3) {
            int optimizationLevel = MotionLayout.this.getOptimizationLevel();
            MotionLayout motionLayout = MotionLayout.this;
            if (motionLayout.f2082j == motionLayout.getStartState()) {
                MotionLayout motionLayout2 = MotionLayout.this;
                ConstraintWidgetContainer constraintWidgetContainer = this.f2118b;
                ConstraintSet constraintSet = this.f2120d;
                motionLayout2.h(constraintWidgetContainer, optimizationLevel, (constraintSet == null || constraintSet.mRotate == 0) ? i2 : i3, (constraintSet == null || constraintSet.mRotate == 0) ? i3 : i2);
                ConstraintSet constraintSet2 = this.f2119c;
                if (constraintSet2 != null) {
                    MotionLayout motionLayout3 = MotionLayout.this;
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f2117a;
                    int i4 = constraintSet2.mRotate;
                    int i5 = i4 == 0 ? i2 : i3;
                    if (i4 == 0) {
                        i2 = i3;
                    }
                    motionLayout3.h(constraintWidgetContainer2, optimizationLevel, i5, i2);
                    return;
                }
                return;
            }
            ConstraintSet constraintSet3 = this.f2119c;
            if (constraintSet3 != null) {
                MotionLayout motionLayout4 = MotionLayout.this;
                ConstraintWidgetContainer constraintWidgetContainer3 = this.f2117a;
                int i6 = constraintSet3.mRotate;
                motionLayout4.h(constraintWidgetContainer3, optimizationLevel, i6 == 0 ? i2 : i3, i6 == 0 ? i3 : i2);
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            ConstraintWidgetContainer constraintWidgetContainer4 = this.f2118b;
            ConstraintSet constraintSet4 = this.f2120d;
            int i7 = (constraintSet4 == null || constraintSet4.mRotate == 0) ? i2 : i3;
            if (constraintSet4 == null || constraintSet4.mRotate == 0) {
                i2 = i3;
            }
            motionLayout5.h(constraintWidgetContainer4, optimizationLevel, i7, i2);
        }

        @SuppressLint({"LogConditional"})
        private void debugLayout(String str, ConstraintWidgetContainer constraintWidgetContainer) {
            String str2 = str + " " + Debug.getName((View) constraintWidgetContainer.getCompanionWidget());
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append("  ========= ");
            sb.append(constraintWidgetContainer);
            int size = constraintWidgetContainer.getChildren().size();
            for (int i2 = 0; i2 < size; i2++) {
                String str3 = str2 + "[" + i2 + "] ";
                ConstraintWidget constraintWidget = constraintWidgetContainer.getChildren().get(i2);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(constraintWidget.mTop.mTarget != null ? ExifInterface.GPS_DIRECTION_TRUE : "_");
                String sb3 = sb2.toString();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(sb3);
                sb4.append(constraintWidget.mBottom.mTarget != null ? "B" : "_");
                String sb5 = sb4.toString();
                StringBuilder sb6 = new StringBuilder();
                sb6.append(sb5);
                sb6.append(constraintWidget.mLeft.mTarget != null ? "L" : "_");
                String sb7 = sb6.toString();
                StringBuilder sb8 = new StringBuilder();
                sb8.append(sb7);
                sb8.append(constraintWidget.mRight.mTarget != null ? "R" : "_");
                String sb9 = sb8.toString();
                View view = (View) constraintWidget.getCompanionWidget();
                String name = Debug.getName(view);
                if (view instanceof TextView) {
                    name = name + "(" + ((Object) ((TextView) view).getText()) + ")";
                }
                StringBuilder sb10 = new StringBuilder();
                sb10.append(str3);
                sb10.append("  ");
                sb10.append(name);
                sb10.append(" ");
                sb10.append(constraintWidget);
                sb10.append(" ");
                sb10.append(sb9);
            }
            StringBuilder sb11 = new StringBuilder();
            sb11.append(str2);
            sb11.append(" done. ");
        }

        @SuppressLint({"LogConditional"})
        private void debugLayoutParam(String str, ConstraintLayout.LayoutParams layoutParams) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(layoutParams.startToStart != -1 ? "SS" : "__");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(layoutParams.startToEnd != -1 ? "|SE" : "|__");
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(sb4);
            sb5.append(layoutParams.endToStart != -1 ? "|ES" : "|__");
            String sb6 = sb5.toString();
            StringBuilder sb7 = new StringBuilder();
            sb7.append(sb6);
            sb7.append(layoutParams.endToEnd != -1 ? "|EE" : "|__");
            String sb8 = sb7.toString();
            StringBuilder sb9 = new StringBuilder();
            sb9.append(sb8);
            sb9.append(layoutParams.leftToLeft != -1 ? "|LL" : "|__");
            String sb10 = sb9.toString();
            StringBuilder sb11 = new StringBuilder();
            sb11.append(sb10);
            sb11.append(layoutParams.leftToRight != -1 ? "|LR" : "|__");
            String sb12 = sb11.toString();
            StringBuilder sb13 = new StringBuilder();
            sb13.append(sb12);
            sb13.append(layoutParams.rightToLeft != -1 ? "|RL" : "|__");
            String sb14 = sb13.toString();
            StringBuilder sb15 = new StringBuilder();
            sb15.append(sb14);
            sb15.append(layoutParams.rightToRight != -1 ? "|RR" : "|__");
            String sb16 = sb15.toString();
            StringBuilder sb17 = new StringBuilder();
            sb17.append(sb16);
            sb17.append(layoutParams.topToTop != -1 ? "|TT" : "|__");
            String sb18 = sb17.toString();
            StringBuilder sb19 = new StringBuilder();
            sb19.append(sb18);
            sb19.append(layoutParams.topToBottom != -1 ? "|TB" : "|__");
            String sb20 = sb19.toString();
            StringBuilder sb21 = new StringBuilder();
            sb21.append(sb20);
            sb21.append(layoutParams.bottomToTop != -1 ? "|BT" : "|__");
            String sb22 = sb21.toString();
            StringBuilder sb23 = new StringBuilder();
            sb23.append(sb22);
            sb23.append(layoutParams.bottomToBottom != -1 ? "|BB" : "|__");
            String sb24 = sb23.toString();
            StringBuilder sb25 = new StringBuilder();
            sb25.append(str);
            sb25.append(sb24);
        }

        @SuppressLint({"LogConditional"})
        private void debugWidget(String str, ConstraintWidget constraintWidget) {
            String str2;
            String str3;
            String str4;
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            String str5 = "__";
            if (constraintWidget.mTop.mTarget != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(ExifInterface.GPS_DIRECTION_TRUE);
                sb2.append(constraintWidget.mTop.mTarget.mType == ConstraintAnchor.Type.TOP ? ExifInterface.GPS_DIRECTION_TRUE : "B");
                str2 = sb2.toString();
            } else {
                str2 = "__";
            }
            sb.append(str2);
            String sb3 = sb.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb3);
            if (constraintWidget.mBottom.mTarget != null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("B");
                sb5.append(constraintWidget.mBottom.mTarget.mType == ConstraintAnchor.Type.TOP ? ExifInterface.GPS_DIRECTION_TRUE : "B");
                str3 = sb5.toString();
            } else {
                str3 = "__";
            }
            sb4.append(str3);
            String sb6 = sb4.toString();
            StringBuilder sb7 = new StringBuilder();
            sb7.append(sb6);
            if (constraintWidget.mLeft.mTarget != null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append("L");
                sb8.append(constraintWidget.mLeft.mTarget.mType == ConstraintAnchor.Type.LEFT ? "L" : "R");
                str4 = sb8.toString();
            } else {
                str4 = "__";
            }
            sb7.append(str4);
            String sb9 = sb7.toString();
            StringBuilder sb10 = new StringBuilder();
            sb10.append(sb9);
            if (constraintWidget.mRight.mTarget != null) {
                StringBuilder sb11 = new StringBuilder();
                sb11.append("R");
                sb11.append(constraintWidget.mRight.mTarget.mType == ConstraintAnchor.Type.LEFT ? "L" : "R");
                str5 = sb11.toString();
            }
            sb10.append(str5);
            String sb12 = sb10.toString();
            StringBuilder sb13 = new StringBuilder();
            sb13.append(str);
            sb13.append(sb12);
            sb13.append(" ---  ");
            sb13.append(constraintWidget);
        }

        private void setupConstraintWidget(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, constraintWidgetContainer);
            sparseArray.put(MotionLayout.this.getId(), constraintWidgetContainer);
            if (constraintSet != null && constraintSet.mRotate != 0) {
                MotionLayout motionLayout = MotionLayout.this;
                motionLayout.h(this.f2118b, motionLayout.getOptimizationLevel(), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getHeight(), 1073741824), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getWidth(), 1073741824));
            }
            Iterator<ConstraintWidget> it = constraintWidgetContainer.getChildren().iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                sparseArray.put(((View) next.getCompanionWidget()).getId(), next);
            }
            Iterator<ConstraintWidget> it2 = constraintWidgetContainer.getChildren().iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                View view = (View) next2.getCompanionWidget();
                constraintSet.applyToLayoutParams(view.getId(), layoutParams);
                next2.setWidth(constraintSet.getWidth(view.getId()));
                next2.setHeight(constraintSet.getHeight(view.getId()));
                if (view instanceof ConstraintHelper) {
                    constraintSet.applyToHelper((ConstraintHelper) view, next2, layoutParams, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).validateParams();
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                } else {
                    layoutParams.resolveLayoutDirection(0);
                }
                MotionLayout.this.c(false, view, next2, layoutParams, sparseArray);
                next2.setVisibility(constraintSet.getVisibilityMode(view.getId()) == 1 ? view.getVisibility() : constraintSet.getVisibility(view.getId()));
            }
            Iterator<ConstraintWidget> it3 = constraintWidgetContainer.getChildren().iterator();
            while (it3.hasNext()) {
                ConstraintWidget next3 = it3.next();
                if (next3 instanceof VirtualLayout) {
                    Helper helper = (Helper) next3;
                    ((ConstraintHelper) next3.getCompanionWidget()).updatePreLayout(constraintWidgetContainer, helper, sparseArray);
                    ((VirtualLayout) helper).captureWidgets();
                }
            }
        }

        void a(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidgetContainer constraintWidgetContainer2) {
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            HashMap<ConstraintWidget, ConstraintWidget> hashMap = new HashMap<>();
            hashMap.put(constraintWidgetContainer, constraintWidgetContainer2);
            constraintWidgetContainer2.getChildren().clear();
            constraintWidgetContainer2.copy(constraintWidgetContainer, hashMap);
            Iterator<ConstraintWidget> it = children.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                ConstraintWidget barrier = next instanceof androidx.constraintlayout.core.widgets.Barrier ? new androidx.constraintlayout.core.widgets.Barrier() : next instanceof Guideline ? new Guideline() : next instanceof Flow ? new Flow() : next instanceof Placeholder ? new Placeholder() : next instanceof Helper ? new HelperWidget() : new ConstraintWidget();
                constraintWidgetContainer2.add(barrier);
                hashMap.put(next, barrier);
            }
            Iterator<ConstraintWidget> it2 = children.iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                hashMap.get(next2).copy(next2, hashMap);
            }
        }

        ConstraintWidget b(ConstraintWidgetContainer constraintWidgetContainer, View view) {
            if (constraintWidgetContainer.getCompanionWidget() == view) {
                return constraintWidgetContainer;
            }
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            int size = children.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget = children.get(i2);
                if (constraintWidget.getCompanionWidget() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00e9  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x013d A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void build() {
            String str;
            SparseArray sparseArray;
            String str2;
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.f2083k.clear();
            SparseArray sparseArray2 = new SparseArray();
            int[] iArr = new int[childCount];
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = MotionLayout.this.getChildAt(i2);
                MotionController motionController = new MotionController(childAt);
                int id = childAt.getId();
                iArr[i2] = id;
                sparseArray2.put(id, motionController);
                MotionLayout.this.f2083k.put(childAt, motionController);
            }
            int i3 = 0;
            while (i3 < childCount) {
                View childAt2 = MotionLayout.this.getChildAt(i3);
                MotionController motionController2 = (MotionController) MotionLayout.this.f2083k.get(childAt2);
                if (motionController2 == null) {
                    sparseArray = sparseArray2;
                } else {
                    if (this.f2119c != null) {
                        ConstraintWidget b2 = b(this.f2117a, childAt2);
                        if (b2 != null) {
                            motionController2.t(MotionLayout.this.toRect(b2), this.f2119c, MotionLayout.this.getWidth(), MotionLayout.this.getHeight());
                        } else if (MotionLayout.this.f2088p != 0) {
                            Log.e("MotionLayout", Debug.getLocation() + "no widget for  " + Debug.getName(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    } else if (MotionLayout.this.mInRotation) {
                        ViewState viewState = (ViewState) MotionLayout.this.I.get(childAt2);
                        MotionLayout motionLayout = MotionLayout.this;
                        str = "MotionLayout";
                        sparseArray = sparseArray2;
                        str2 = "no widget for  ";
                        motionController2.setStartState(viewState, childAt2, motionLayout.H, motionLayout.mPreRotateWidth, MotionLayout.this.mPreRotateHeight);
                        if (this.f2120d != null) {
                            ConstraintWidget b3 = b(this.f2118b, childAt2);
                            if (b3 != null) {
                                motionController2.r(MotionLayout.this.toRect(b3), this.f2120d, MotionLayout.this.getWidth(), MotionLayout.this.getHeight());
                            } else if (MotionLayout.this.f2088p != 0) {
                                Log.e(str, Debug.getLocation() + str2 + Debug.getName(childAt2) + " (" + childAt2.getClass().getName() + ")");
                            }
                        }
                    }
                    sparseArray = sparseArray2;
                    str = "MotionLayout";
                    str2 = "no widget for  ";
                    if (this.f2120d != null) {
                    }
                }
                i3++;
                sparseArray2 = sparseArray;
            }
            SparseArray sparseArray3 = sparseArray2;
            int i4 = 0;
            while (i4 < childCount) {
                SparseArray sparseArray4 = sparseArray3;
                MotionController motionController3 = (MotionController) sparseArray4.get(iArr[i4]);
                int animateRelativeTo = motionController3.getAnimateRelativeTo();
                if (animateRelativeTo != -1) {
                    motionController3.setupRelative((MotionController) sparseArray4.get(animateRelativeTo));
                }
                i4++;
                sparseArray3 = sparseArray4;
            }
        }

        void c(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
            this.f2119c = constraintSet;
            this.f2120d = constraintSet2;
            this.f2117a = new ConstraintWidgetContainer();
            this.f2118b = new ConstraintWidgetContainer();
            this.f2117a.setMeasurer(((ConstraintLayout) MotionLayout.this).f2248b.getMeasurer());
            this.f2118b.setMeasurer(((ConstraintLayout) MotionLayout.this).f2248b.getMeasurer());
            this.f2117a.removeAllChildren();
            this.f2118b.removeAllChildren();
            a(((ConstraintLayout) MotionLayout.this).f2248b, this.f2117a);
            a(((ConstraintLayout) MotionLayout.this).f2248b, this.f2118b);
            if (MotionLayout.this.f2085m > 0.5d) {
                if (constraintSet != null) {
                    setupConstraintWidget(this.f2117a, constraintSet);
                }
                setupConstraintWidget(this.f2118b, constraintSet2);
            } else {
                setupConstraintWidget(this.f2118b, constraintSet2);
                if (constraintSet != null) {
                    setupConstraintWidget(this.f2117a, constraintSet);
                }
            }
            this.f2117a.setRtl(MotionLayout.this.e());
            this.f2117a.updateHierarchy();
            this.f2118b.setRtl(MotionLayout.this.e());
            this.f2118b.updateHierarchy();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f2117a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer2.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    this.f2118b.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer3 = this.f2117a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer3.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    this.f2118b.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            }
        }

        public boolean isNotConfiguredWith(int i2, int i3) {
            return (i2 == this.f2121e && i3 == this.f2122f) ? false : true;
        }

        public void measure(int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.D = mode;
            motionLayout.E = mode2;
            motionLayout.getOptimizationLevel();
            computeStartEndSize(i2, i3);
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                computeStartEndSize(i2, i3);
                MotionLayout.this.z = this.f2117a.getWidth();
                MotionLayout.this.A = this.f2117a.getHeight();
                MotionLayout.this.B = this.f2118b.getWidth();
                MotionLayout.this.C = this.f2118b.getHeight();
                MotionLayout motionLayout2 = MotionLayout.this;
                motionLayout2.y = (motionLayout2.z == motionLayout2.B && motionLayout2.A == motionLayout2.C) ? false : true;
            }
            MotionLayout motionLayout3 = MotionLayout.this;
            int i4 = motionLayout3.z;
            int i5 = motionLayout3.A;
            int i6 = motionLayout3.D;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                i4 = (int) (i4 + (motionLayout3.F * (motionLayout3.B - i4)));
            }
            int i7 = i4;
            int i8 = motionLayout3.E;
            if (i8 == Integer.MIN_VALUE || i8 == 0) {
                i5 = (int) (i5 + (motionLayout3.F * (motionLayout3.C - i5)));
            }
            MotionLayout.this.g(i2, i3, i7, i5, this.f2117a.isWidthMeasuredTooSmall() || this.f2118b.isWidthMeasuredTooSmall(), this.f2117a.isHeightMeasuredTooSmall() || this.f2118b.isHeightMeasuredTooSmall());
        }

        public void reEvaluateState() {
            measure(MotionLayout.this.mLastWidthMeasureSpec, MotionLayout.this.mLastHeightMeasureSpec);
            MotionLayout.this.setupMotionViews();
        }

        public void setMeasuredId(int i2, int i3) {
            this.f2121e = i2;
            this.f2122f = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface MotionTracker {
        void addMovement(MotionEvent motionEvent);

        void clear();

        void computeCurrentVelocity(int i2);

        void computeCurrentVelocity(int i2, float f2);

        float getXVelocity();

        float getXVelocity(int i2);

        float getYVelocity();

        float getYVelocity(int i2);

        void recycle();
    }

    /* loaded from: classes.dex */
    private static class MyTracker implements MotionTracker {
        private static MyTracker me = new MyTracker();

        /* renamed from: a  reason: collision with root package name */
        VelocityTracker f2124a;

        private MyTracker() {
        }

        public static MyTracker obtain() {
            me.f2124a = VelocityTracker.obtain();
            return me;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void addMovement(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void clear() {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i2) {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i2);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i2, float f2) {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i2, f2);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity() {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity(int i2) {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity(i2);
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity() {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity(int i2) {
            if (this.f2124a != null) {
                return getYVelocity(i2);
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void recycle() {
            VelocityTracker velocityTracker = this.f2124a;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f2124a = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class StateCache {

        /* renamed from: a  reason: collision with root package name */
        float f2125a = Float.NaN;

        /* renamed from: b  reason: collision with root package name */
        float f2126b = Float.NaN;

        /* renamed from: c  reason: collision with root package name */
        int f2127c = -1;

        /* renamed from: d  reason: collision with root package name */
        int f2128d = -1;

        StateCache() {
        }

        void a() {
            int i2 = this.f2127c;
            if (i2 != -1 || this.f2128d != -1) {
                if (i2 == -1) {
                    MotionLayout.this.transitionToState(this.f2128d);
                } else {
                    int i3 = this.f2128d;
                    if (i3 == -1) {
                        MotionLayout.this.setState(i2, -1, -1);
                    } else {
                        MotionLayout.this.setTransition(i2, i3);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.f2126b)) {
                if (Float.isNaN(this.f2125a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f2125a);
                return;
            }
            MotionLayout.this.setProgress(this.f2125a, this.f2126b);
            this.f2125a = Float.NaN;
            this.f2126b = Float.NaN;
            this.f2127c = -1;
            this.f2128d = -1;
        }

        public Bundle getTransitionState() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f2125a);
            bundle.putFloat("motion.velocity", this.f2126b);
            bundle.putInt("motion.StartState", this.f2127c);
            bundle.putInt("motion.EndState", this.f2128d);
            return bundle;
        }

        public void recordState() {
            this.f2128d = MotionLayout.this.mEndState;
            this.f2127c = MotionLayout.this.mBeginState;
            this.f2126b = MotionLayout.this.getVelocity();
            this.f2125a = MotionLayout.this.getProgress();
        }

        public void setEndState(int i2) {
            this.f2128d = i2;
        }

        public void setProgress(float f2) {
            this.f2125a = f2;
        }

        public void setStartState(int i2) {
            this.f2127c = i2;
        }

        public void setTransitionState(Bundle bundle) {
            this.f2125a = bundle.getFloat("motion.progress");
            this.f2126b = bundle.getFloat("motion.velocity");
            this.f2127c = bundle.getInt("motion.StartState");
            this.f2128d = bundle.getInt("motion.EndState");
        }

        public void setVelocity(float f2) {
            this.f2126b = f2;
        }
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionChange(MotionLayout motionLayout, int i2, int i3, float f2);

        void onTransitionCompleted(MotionLayout motionLayout, int i2);

        void onTransitionStarted(MotionLayout motionLayout, int i2, int i3);

        void onTransitionTrigger(MotionLayout motionLayout, int i2, boolean z, float f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(@NonNull Context context) {
        super(context);
        this.f2080h = null;
        this.f2081i = 0.0f;
        this.mBeginState = -1;
        this.f2082j = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.f2083k = new HashMap();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.f2084l = 0.0f;
        this.f2085m = 0.0f;
        this.f2086n = 0.0f;
        this.f2087o = false;
        this.f2088p = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.f2092t = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.y = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.G = 0;
        this.mInRotation = false;
        this.H = 0;
        this.I = new HashMap();
        this.J = new Rect();
        this.mDelayedApply = false;
        this.K = TransitionState.UNDEFINED;
        this.L = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.M = new ArrayList();
        init(null);
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2080h = null;
        this.f2081i = 0.0f;
        this.mBeginState = -1;
        this.f2082j = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.f2083k = new HashMap();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.f2084l = 0.0f;
        this.f2085m = 0.0f;
        this.f2086n = 0.0f;
        this.f2087o = false;
        this.f2088p = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.f2092t = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.y = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.G = 0;
        this.mInRotation = false;
        this.H = 0;
        this.I = new HashMap();
        this.J = new Rect();
        this.mDelayedApply = false;
        this.K = TransitionState.UNDEFINED;
        this.L = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.M = new ArrayList();
        init(attributeSet);
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2080h = null;
        this.f2081i = 0.0f;
        this.mBeginState = -1;
        this.f2082j = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.f2083k = new HashMap();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.f2084l = 0.0f;
        this.f2085m = 0.0f;
        this.f2086n = 0.0f;
        this.f2087o = false;
        this.f2088p = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.f2092t = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.y = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.G = 0;
        this.mInRotation = false;
        this.H = 0;
        this.I = new HashMap();
        this.J = new Rect();
        this.mDelayedApply = false;
        this.K = TransitionState.UNDEFINED;
        this.L = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.M = new ArrayList();
        init(attributeSet);
    }

    private boolean callTransformedTouchEvent(View view, MotionEvent motionEvent, float f2, float f3) {
        Matrix matrix = view.getMatrix();
        if (matrix.isIdentity()) {
            motionEvent.offsetLocation(f2, f3);
            boolean onTouchEvent = view.onTouchEvent(motionEvent);
            motionEvent.offsetLocation(-f2, -f3);
            return onTouchEvent;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(f2, f3);
        if (this.mInverseMatrix == null) {
            this.mInverseMatrix = new Matrix();
        }
        matrix.invert(this.mInverseMatrix);
        obtain.transform(this.mInverseMatrix);
        boolean onTouchEvent2 = view.onTouchEvent(obtain);
        obtain.recycle();
        return onTouchEvent2;
    }

    private void checkStructure() {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int u = motionScene.u();
        MotionScene motionScene2 = this.f2078f;
        checkStructure(u, motionScene2.h(motionScene2.u()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        Iterator<MotionScene.Transition> it = this.f2078f.getDefinedTransitions().iterator();
        while (it.hasNext()) {
            MotionScene.Transition next = it.next();
            MotionScene.Transition transition = this.f2078f.f2150b;
            checkStructure(next);
            int startConstraintSetId = next.getStartConstraintSetId();
            int endConstraintSetId = next.getEndConstraintSetId();
            String name = Debug.getName(getContext(), startConstraintSetId);
            String name2 = Debug.getName(getContext(), endConstraintSetId);
            if (sparseIntArray.get(startConstraintSetId) == endConstraintSetId) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + name + "->" + name2);
            }
            if (sparseIntArray2.get(endConstraintSetId) == startConstraintSetId) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + name + "->" + name2);
            }
            sparseIntArray.put(startConstraintSetId, endConstraintSetId);
            sparseIntArray2.put(endConstraintSetId, startConstraintSetId);
            if (this.f2078f.h(startConstraintSetId) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + name);
            }
            if (this.f2078f.h(endConstraintSetId) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + name);
            }
        }
    }

    private void checkStructure(int i2, ConstraintSet constraintSet) {
        String name = Debug.getName(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append("CHECK: ");
                sb.append(name);
                sb.append(" ALL VIEWS SHOULD HAVE ID's ");
                sb.append(childAt.getClass().getName());
                sb.append(" does not!");
            }
            if (constraintSet.getConstraint(id) == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("CHECK: ");
                sb2.append(name);
                sb2.append(" NO CONSTRAINTS for ");
                sb2.append(Debug.getName(childAt));
            }
        }
        int[] knownIds = constraintSet.getKnownIds();
        for (int i4 = 0; i4 < knownIds.length; i4++) {
            int i5 = knownIds[i4];
            String name2 = Debug.getName(getContext(), i5);
            if (findViewById(knownIds[i4]) == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("CHECK: ");
                sb3.append(name);
                sb3.append(" NO View matches id ");
                sb3.append(name2);
            }
            if (constraintSet.getHeight(i5) == -1) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("CHECK: ");
                sb4.append(name);
                sb4.append("(");
                sb4.append(name2);
                sb4.append(") no LAYOUT_HEIGHT");
            }
            if (constraintSet.getWidth(i5) == -1) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("CHECK: ");
                sb5.append(name);
                sb5.append("(");
                sb5.append(name2);
                sb5.append(") no LAYOUT_HEIGHT");
            }
        }
    }

    private void checkStructure(MotionScene.Transition transition) {
        if (transition.getStartConstraintSetId() == transition.getEndConstraintSetId()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    private void computeCurrentPositions() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            MotionController motionController = (MotionController) this.f2083k.get(childAt);
            if (motionController != null) {
                motionController.s(childAt);
            }
        }
    }

    @SuppressLint({"LogConditional"})
    private void debugPos() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(Debug.getLocation());
            sb.append(" ");
            sb.append(Debug.getName(this));
            sb.append(" ");
            sb.append(Debug.getName(getContext(), this.f2082j));
            sb.append(" ");
            sb.append(Debug.getName(childAt));
            sb.append(childAt.getLeft());
            sb.append(" ");
            sb.append(childAt.getTop());
        }
    }

    private void evaluateLayout() {
        boolean z;
        float signum = Math.signum(this.f2086n - this.f2085m);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.f2079g;
        float f2 = this.f2085m + (!(interpolator instanceof StopLogic) ? ((((float) (nanoTime - this.mTransitionLastTime)) * signum) * 1.0E-9f) / this.mTransitionDuration : 0.0f);
        if (this.mTransitionInstantly) {
            f2 = this.f2086n;
        }
        int i2 = (signum > 0.0f ? 1 : (signum == 0.0f ? 0 : -1));
        if ((i2 <= 0 || f2 < this.f2086n) && (signum > 0.0f || f2 > this.f2086n)) {
            z = false;
        } else {
            f2 = this.f2086n;
            z = true;
        }
        if (interpolator != null && !z) {
            f2 = this.mTemporalInterpolator ? interpolator.getInterpolation(((float) (nanoTime - this.mAnimationStartTime)) * 1.0E-9f) : interpolator.getInterpolation(f2);
        }
        if ((i2 > 0 && f2 >= this.f2086n) || (signum <= 0.0f && f2 <= this.f2086n)) {
            f2 = this.f2086n;
        }
        this.F = f2;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        Interpolator interpolator2 = this.f2080h;
        if (interpolator2 != null) {
            f2 = interpolator2.getInterpolation(f2);
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            MotionController motionController = (MotionController) this.f2083k.get(childAt);
            if (motionController != null) {
                motionController.n(childAt, f2, nanoTime2, this.mKeyCache);
            }
        }
        if (this.y) {
            requestLayout();
        }
    }

    private void fireTransitionChange() {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if ((this.mTransitionListener == null && ((copyOnWriteArrayList = this.mTransitionListeners) == null || copyOnWriteArrayList.isEmpty())) || this.mListenerPosition == this.f2084l) {
            return;
        }
        if (this.mListenerState != -1) {
            TransitionListener transitionListener = this.mTransitionListener;
            if (transitionListener != null) {
                transitionListener.onTransitionStarted(this, this.mBeginState, this.mEndState);
            }
            CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList2 = this.mTransitionListeners;
            if (copyOnWriteArrayList2 != null) {
                Iterator<TransitionListener> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().onTransitionStarted(this, this.mBeginState, this.mEndState);
                }
            }
        }
        this.mListenerState = -1;
        float f2 = this.f2084l;
        this.mListenerPosition = f2;
        TransitionListener transitionListener2 = this.mTransitionListener;
        if (transitionListener2 != null) {
            transitionListener2.onTransitionChange(this, this.mBeginState, this.mEndState, f2);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList3 = this.mTransitionListeners;
        if (copyOnWriteArrayList3 != null) {
            Iterator<TransitionListener> it2 = copyOnWriteArrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().onTransitionChange(this, this.mBeginState, this.mEndState, this.f2084l);
            }
        }
    }

    private void fireTransitionStarted(MotionLayout motionLayout, int i2, int i3) {
        TransitionListener transitionListener = this.mTransitionListener;
        if (transitionListener != null) {
            transitionListener.onTransitionStarted(this, i2, i3);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList = this.mTransitionListeners;
        if (copyOnWriteArrayList != null) {
            Iterator<TransitionListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onTransitionStarted(motionLayout, i2, i3);
            }
        }
    }

    private boolean handlesTouchEvent(float f2, float f3, View view, MotionEvent motionEvent) {
        boolean z;
        View childAt;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (handlesTouchEvent((childAt.getLeft() + f2) - view.getScrollX(), (childAt.getTop() + f3) - view.getScrollY(), viewGroup.getChildAt(childCount), motionEvent)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            this.mBoundsCheck.set(f2, f3, (view.getRight() + f2) - view.getLeft(), (view.getBottom() + f3) - view.getTop());
            if ((motionEvent.getAction() != 0 || this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY())) && callTransformedTouchEvent(view, motionEvent, -f2, -f3)) {
                return true;
            }
        }
        return z;
    }

    private void init(AttributeSet attributeSet) {
        MotionScene motionScene;
        int i2;
        IS_IN_EDIT_MODE = isInEditMode();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionLayout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == R.styleable.MotionLayout_layoutDescription) {
                    this.f2078f = new MotionScene(getContext(), this, obtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R.styleable.MotionLayout_currentState) {
                    this.f2082j = obtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R.styleable.MotionLayout_motionProgress) {
                    this.f2086n = obtainStyledAttributes.getFloat(index, 0.0f);
                    this.f2087o = true;
                } else if (index == R.styleable.MotionLayout_applyMotionScene) {
                    z = obtainStyledAttributes.getBoolean(index, z);
                } else if (index == R.styleable.MotionLayout_showPaths) {
                    if (this.f2088p == 0) {
                        i2 = obtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                        this.f2088p = i2;
                    }
                } else if (index == R.styleable.MotionLayout_motionDebug) {
                    i2 = obtainStyledAttributes.getInt(index, 0);
                    this.f2088p = i2;
                }
            }
            obtainStyledAttributes.recycle();
            if (this.f2078f == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.f2078f = null;
            }
        }
        if (this.f2088p != 0) {
            checkStructure();
        }
        if (this.f2082j != -1 || (motionScene = this.f2078f) == null) {
            return;
        }
        this.f2082j = motionScene.u();
        this.mBeginState = this.f2078f.u();
        this.mEndState = this.f2078f.j();
    }

    private void processTransitionCompleted() {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if (this.mTransitionListener == null && ((copyOnWriteArrayList = this.mTransitionListeners) == null || copyOnWriteArrayList.isEmpty())) {
            return;
        }
        Iterator it = this.M.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            TransitionListener transitionListener = this.mTransitionListener;
            if (transitionListener != null) {
                transitionListener.onTransitionCompleted(this, num.intValue());
            }
            CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList2 = this.mTransitionListeners;
            if (copyOnWriteArrayList2 != null) {
                Iterator<TransitionListener> it2 = copyOnWriteArrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().onTransitionCompleted(this, num.intValue());
                }
            }
        }
        this.M.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupMotionViews() {
        int childCount = getChildCount();
        this.L.build();
        boolean z = true;
        this.f2087o = true;
        SparseArray sparseArray = new SparseArray();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            sparseArray.put(childAt.getId(), this.f2083k.get(childAt));
        }
        int width = getWidth();
        int height = getHeight();
        int gatPathMotionArc = this.f2078f.gatPathMotionArc();
        if (gatPathMotionArc != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                MotionController motionController = (MotionController) this.f2083k.get(getChildAt(i4));
                if (motionController != null) {
                    motionController.setPathMotionArc(gatPathMotionArc);
                }
            }
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        int[] iArr = new int[this.f2083k.size()];
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            MotionController motionController2 = (MotionController) this.f2083k.get(getChildAt(i6));
            if (motionController2.getAnimateRelativeTo() != -1) {
                sparseBooleanArray.put(motionController2.getAnimateRelativeTo(), true);
                iArr[i5] = motionController2.getAnimateRelativeTo();
                i5++;
            }
        }
        if (this.mDecoratorsHelpers != null) {
            for (int i7 = 0; i7 < i5; i7++) {
                MotionController motionController3 = (MotionController) this.f2083k.get(findViewById(iArr[i7]));
                if (motionController3 != null) {
                    this.f2078f.getKeyFrames(motionController3);
                }
            }
            Iterator<MotionHelper> it = this.mDecoratorsHelpers.iterator();
            while (it.hasNext()) {
                it.next().onPreSetup(this, this.f2083k);
            }
            for (int i8 = 0; i8 < i5; i8++) {
                MotionController motionController4 = (MotionController) this.f2083k.get(findViewById(iArr[i8]));
                if (motionController4 != null) {
                    motionController4.setup(width, height, this.mTransitionDuration, getNanoTime());
                }
            }
        } else {
            for (int i9 = 0; i9 < i5; i9++) {
                MotionController motionController5 = (MotionController) this.f2083k.get(findViewById(iArr[i9]));
                if (motionController5 != null) {
                    this.f2078f.getKeyFrames(motionController5);
                    motionController5.setup(width, height, this.mTransitionDuration, getNanoTime());
                }
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt2 = getChildAt(i10);
            MotionController motionController6 = (MotionController) this.f2083k.get(childAt2);
            if (!sparseBooleanArray.get(childAt2.getId()) && motionController6 != null) {
                this.f2078f.getKeyFrames(motionController6);
                motionController6.setup(width, height, this.mTransitionDuration, getNanoTime());
            }
        }
        float staggered = this.f2078f.getStaggered();
        if (staggered != 0.0f) {
            boolean z2 = ((double) staggered) < 0.0d;
            float abs = Math.abs(staggered);
            float f2 = -3.4028235E38f;
            float f3 = Float.MAX_VALUE;
            int i11 = 0;
            float f4 = -3.4028235E38f;
            float f5 = Float.MAX_VALUE;
            while (true) {
                if (i11 >= childCount) {
                    z = false;
                    break;
                }
                MotionController motionController7 = (MotionController) this.f2083k.get(getChildAt(i11));
                if (!Float.isNaN(motionController7.f2071d)) {
                    break;
                }
                float finalX = motionController7.getFinalX();
                float finalY = motionController7.getFinalY();
                float f6 = z2 ? finalY - finalX : finalY + finalX;
                f5 = Math.min(f5, f6);
                f4 = Math.max(f4, f6);
                i11++;
            }
            if (!z) {
                while (i2 < childCount) {
                    MotionController motionController8 = (MotionController) this.f2083k.get(getChildAt(i2));
                    float finalX2 = motionController8.getFinalX();
                    float finalY2 = motionController8.getFinalY();
                    float f7 = z2 ? finalY2 - finalX2 : finalY2 + finalX2;
                    motionController8.f2073f = 1.0f / (1.0f - abs);
                    motionController8.f2072e = abs - (((f7 - f5) * abs) / (f4 - f5));
                    i2++;
                }
                return;
            }
            for (int i12 = 0; i12 < childCount; i12++) {
                MotionController motionController9 = (MotionController) this.f2083k.get(getChildAt(i12));
                if (!Float.isNaN(motionController9.f2071d)) {
                    f3 = Math.min(f3, motionController9.f2071d);
                    f2 = Math.max(f2, motionController9.f2071d);
                }
            }
            while (i2 < childCount) {
                MotionController motionController10 = (MotionController) this.f2083k.get(getChildAt(i2));
                if (!Float.isNaN(motionController10.f2071d)) {
                    motionController10.f2073f = 1.0f / (1.0f - abs);
                    float f8 = motionController10.f2071d;
                    motionController10.f2072e = abs - (z2 ? ((f2 - f8) / (f2 - f3)) * abs : ((f8 - f3) * abs) / (f2 - f3));
                }
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect toRect(ConstraintWidget constraintWidget) {
        this.J.top = constraintWidget.getY();
        this.J.left = constraintWidget.getX();
        Rect rect = this.J;
        int width = constraintWidget.getWidth();
        Rect rect2 = this.J;
        rect.right = width + rect2.left;
        int height = constraintWidget.getHeight();
        Rect rect3 = this.J;
        rect2.bottom = height + rect3.top;
        return rect3;
    }

    private static boolean willJump(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    void H(float f2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return;
        }
        float f3 = this.f2085m;
        float f4 = this.f2084l;
        if (f3 != f4 && this.mTransitionInstantly) {
            this.f2085m = f4;
        }
        float f5 = this.f2085m;
        if (f5 == f2) {
            return;
        }
        this.mTemporalInterpolator = false;
        this.f2086n = f2;
        this.mTransitionDuration = motionScene.getDuration() / 1000.0f;
        setProgress(this.f2086n);
        this.f2079g = null;
        this.f2080h = this.f2078f.getInterpolator();
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        this.f2087o = true;
        this.f2084l = f5;
        this.f2085m = f5;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(boolean z) {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return;
        }
        motionScene.disableAutoTransition(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            MotionController motionController = (MotionController) this.f2083k.get(getChildAt(i2));
            if (motionController != null) {
                motionController.f(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0249, code lost:
        if (r1 != r2) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x024c, code lost:
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x024d, code lost:
        r23.f2082j = r2;
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0259, code lost:
        if (r1 != r2) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void K(boolean z) {
        boolean z2;
        boolean z3;
        int childCount;
        Interpolator interpolator;
        int i2;
        int i3;
        int i4;
        if (this.mTransitionLastTime == -1) {
            this.mTransitionLastTime = getNanoTime();
        }
        float f2 = this.f2085m;
        if (f2 > 0.0f && f2 < 1.0f) {
            this.f2082j = -1;
        }
        boolean z4 = true;
        boolean z5 = false;
        if (this.mKeepAnimating || (this.f2087o && (z || this.f2086n != f2))) {
            float signum = Math.signum(this.f2086n - f2);
            long nanoTime = getNanoTime();
            Interpolator interpolator2 = this.f2079g;
            float f3 = !(interpolator2 instanceof MotionInterpolator) ? ((((float) (nanoTime - this.mTransitionLastTime)) * signum) * 1.0E-9f) / this.mTransitionDuration : 0.0f;
            float f4 = this.f2085m + f3;
            if (this.mTransitionInstantly) {
                f4 = this.f2086n;
            }
            int i5 = (signum > 0.0f ? 1 : (signum == 0.0f ? 0 : -1));
            if ((i5 <= 0 || f4 < this.f2086n) && (signum > 0.0f || f4 > this.f2086n)) {
                z2 = false;
            } else {
                f4 = this.f2086n;
                this.f2087o = false;
                z2 = true;
            }
            this.f2085m = f4;
            this.f2084l = f4;
            this.mTransitionLastTime = nanoTime;
            if (interpolator2 == null || z2) {
                this.f2081i = f3;
            } else if (this.mTemporalInterpolator) {
                float interpolation = interpolator2.getInterpolation(((float) (nanoTime - this.mAnimationStartTime)) * 1.0E-9f);
                Interpolator interpolator3 = this.f2079g;
                StopLogic stopLogic = this.mStopLogic;
                z3 = interpolator3 == stopLogic ? stopLogic.isStopped() ? true : true : false;
                this.f2085m = interpolation;
                this.mTransitionLastTime = nanoTime;
                Interpolator interpolator4 = this.f2079g;
                if (interpolator4 instanceof MotionInterpolator) {
                    float velocity = ((MotionInterpolator) interpolator4).getVelocity();
                    this.f2081i = velocity;
                    if (Math.abs(velocity) * this.mTransitionDuration <= EPSILON && z3) {
                        this.f2087o = false;
                    }
                    if (velocity > 0.0f && interpolation >= 1.0f) {
                        this.f2085m = 1.0f;
                        this.f2087o = false;
                        interpolation = 1.0f;
                    }
                    if (velocity < 0.0f && interpolation <= 0.0f) {
                        this.f2085m = 0.0f;
                        this.f2087o = false;
                        f4 = 0.0f;
                        if (Math.abs(this.f2081i) > EPSILON) {
                            setState(TransitionState.MOVING);
                        }
                        if (!z3) {
                            if ((i5 > 0 && f4 >= this.f2086n) || (signum <= 0.0f && f4 <= this.f2086n)) {
                                f4 = this.f2086n;
                                this.f2087o = false;
                            }
                            if (f4 >= 1.0f || f4 <= 0.0f) {
                                this.f2087o = false;
                                setState(TransitionState.FINISHED);
                            }
                        }
                        childCount = getChildCount();
                        this.mKeepAnimating = false;
                        long nanoTime2 = getNanoTime();
                        this.F = f4;
                        Interpolator interpolator5 = this.f2080h;
                        float interpolation2 = interpolator5 == null ? f4 : interpolator5.getInterpolation(f4);
                        interpolator = this.f2080h;
                        if (interpolator != null) {
                            float interpolation3 = interpolator.getInterpolation((signum / this.mTransitionDuration) + f4);
                            this.f2081i = interpolation3;
                            this.f2081i = interpolation3 - this.f2080h.getInterpolation(f4);
                        }
                        for (i2 = 0; i2 < childCount; i2++) {
                            View childAt = getChildAt(i2);
                            MotionController motionController = (MotionController) this.f2083k.get(childAt);
                            if (motionController != null) {
                                this.mKeepAnimating = motionController.n(childAt, interpolation2, nanoTime2, this.mKeyCache) | this.mKeepAnimating;
                            }
                        }
                        boolean z6 = (i5 <= 0 && f4 >= this.f2086n) || (signum <= 0.0f && f4 <= this.f2086n);
                        if (!this.mKeepAnimating && !this.f2087o && z6) {
                            setState(TransitionState.FINISHED);
                        }
                        if (this.y) {
                            requestLayout();
                        }
                        this.mKeepAnimating = (!z6) | this.mKeepAnimating;
                        if (f4 <= 0.0f && (i3 = this.mBeginState) != -1 && this.f2082j != i3) {
                            this.f2082j = i3;
                            this.f2078f.h(i3).applyCustomAttributes(this);
                            setState(TransitionState.FINISHED);
                            z5 = true;
                        }
                        if (f4 >= 1.0d) {
                            int i6 = this.f2082j;
                            int i7 = this.mEndState;
                            if (i6 != i7) {
                                this.f2082j = i7;
                                this.f2078f.h(i7).applyCustomAttributes(this);
                                setState(TransitionState.FINISHED);
                                z5 = true;
                            }
                        }
                        if (!this.mKeepAnimating || this.f2087o) {
                            invalidate();
                        } else if ((i5 > 0 && f4 == 1.0f) || (signum < 0.0f && f4 == 0.0f)) {
                            setState(TransitionState.FINISHED);
                        }
                        if (!this.mKeepAnimating && !this.f2087o && ((i5 > 0 && f4 == 1.0f) || (signum < 0.0f && f4 == 0.0f))) {
                            R();
                        }
                    }
                }
                f4 = interpolation;
                if (Math.abs(this.f2081i) > EPSILON) {
                }
                if (!z3) {
                }
                childCount = getChildCount();
                this.mKeepAnimating = false;
                long nanoTime22 = getNanoTime();
                this.F = f4;
                Interpolator interpolator52 = this.f2080h;
                if (interpolator52 == null) {
                }
                interpolator = this.f2080h;
                if (interpolator != null) {
                }
                while (i2 < childCount) {
                }
                if (i5 <= 0) {
                }
                if (!this.mKeepAnimating) {
                    setState(TransitionState.FINISHED);
                }
                if (this.y) {
                }
                this.mKeepAnimating = (!z6) | this.mKeepAnimating;
                if (f4 <= 0.0f) {
                    this.f2082j = i3;
                    this.f2078f.h(i3).applyCustomAttributes(this);
                    setState(TransitionState.FINISHED);
                    z5 = true;
                }
                if (f4 >= 1.0d) {
                }
                if (this.mKeepAnimating) {
                }
                invalidate();
                if (!this.mKeepAnimating) {
                    R();
                }
            } else {
                float interpolation4 = interpolator2.getInterpolation(f4);
                Interpolator interpolator6 = this.f2079g;
                this.f2081i = interpolator6 instanceof MotionInterpolator ? ((MotionInterpolator) interpolator6).getVelocity() : ((interpolator6.getInterpolation(f4 + f3) - interpolation4) * signum) / f3;
                f4 = interpolation4;
            }
            z3 = false;
            if (Math.abs(this.f2081i) > EPSILON) {
            }
            if (!z3) {
            }
            childCount = getChildCount();
            this.mKeepAnimating = false;
            long nanoTime222 = getNanoTime();
            this.F = f4;
            Interpolator interpolator522 = this.f2080h;
            if (interpolator522 == null) {
            }
            interpolator = this.f2080h;
            if (interpolator != null) {
            }
            while (i2 < childCount) {
            }
            if (i5 <= 0) {
            }
            if (!this.mKeepAnimating) {
            }
            if (this.y) {
            }
            this.mKeepAnimating = (!z6) | this.mKeepAnimating;
            if (f4 <= 0.0f) {
            }
            if (f4 >= 1.0d) {
            }
            if (this.mKeepAnimating) {
            }
            invalidate();
            if (!this.mKeepAnimating) {
            }
        }
        float f5 = this.f2085m;
        if (f5 >= 1.0f) {
            int i8 = this.f2082j;
            i4 = this.mEndState;
        } else if (f5 <= 0.0f) {
            int i9 = this.f2082j;
            i4 = this.mBeginState;
        }
        this.mNeedsFireTransitionCompleted |= z5;
        if (z5 && !this.mInLayout) {
            requestLayout();
        }
        this.f2084l = this.f2085m;
    }

    protected void L() {
        int i2;
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if ((this.mTransitionListener != null || ((copyOnWriteArrayList = this.mTransitionListeners) != null && !copyOnWriteArrayList.isEmpty())) && this.mListenerState == -1) {
            this.mListenerState = this.f2082j;
            if (this.M.isEmpty()) {
                i2 = -1;
            } else {
                ArrayList arrayList = this.M;
                i2 = ((Integer) arrayList.get(arrayList.size() - 1)).intValue();
            }
            int i3 = this.f2082j;
            if (i2 != i3 && i3 != -1) {
                this.M.add(Integer.valueOf(i3));
            }
        }
        processTransitionCompleted();
        Runnable runnable = this.mOnComplete;
        if (runnable != null) {
            runnable.run();
        }
        int[] iArr = this.mScheduledTransitionTo;
        if (iArr == null || this.G <= 0) {
            return;
        }
        transitionToState(iArr[0]);
        int[] iArr2 = this.mScheduledTransitionTo;
        System.arraycopy(iArr2, 1, iArr2, 0, iArr2.length - 1);
        this.G--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void M(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap hashMap = this.f2083k;
        View viewById = getViewById(i2);
        MotionController motionController = (MotionController) hashMap.get(viewById);
        if (motionController != null) {
            motionController.h(f2, f3, f4, fArr);
            float y = viewById.getY();
            this.lastPos = f2;
            this.lastY = y;
            return;
        }
        if (viewById == null) {
            resourceName = "" + i2;
        } else {
            resourceName = viewById.getContext().getResources().getResourceName(i2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("WARNING could not find view id ");
        sb.append(resourceName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String N(int i2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return null;
        }
        return motionScene.lookUpConstraintName(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionController O(int i2) {
        return (MotionController) this.f2083k.get(findViewById(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int P(String str) {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return 0;
        }
        return motionScene.lookUpConstraintId(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MotionTracker Q() {
        return MyTracker.obtain();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void R() {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return;
        }
        if (motionScene.f(this, this.f2082j)) {
            requestLayout();
            return;
        }
        int i2 = this.f2082j;
        if (i2 != -1) {
            this.f2078f.addOnClickListeners(this, i2);
        }
        if (this.f2078f.C()) {
            this.f2078f.B();
        }
    }

    public void addTransitionListener(TransitionListener transitionListener) {
        if (this.mTransitionListeners == null) {
            this.mTransitionListeners = new CopyOnWriteArrayList<>();
        }
        this.mTransitionListeners.add(transitionListener);
    }

    public boolean applyViewTransition(int i2, MotionController motionController) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            return motionScene.applyViewTransition(i2, motionController);
        }
        return false;
    }

    public ConstraintSet cloneConstraintSet(int i2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return null;
        }
        ConstraintSet h2 = motionScene.h(i2);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(h2);
        return constraintSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c7  */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void dispatchDraw(Canvas canvas) {
        long j2;
        ViewTransitionController viewTransitionController;
        ArrayList<MotionHelper> arrayList = this.mDecoratorsHelpers;
        if (arrayList != null) {
            Iterator<MotionHelper> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onPreDraw(canvas);
            }
        }
        K(false);
        MotionScene motionScene = this.f2078f;
        if (motionScene != null && (viewTransitionController = motionScene.f2151c) != null) {
            viewTransitionController.c();
        }
        super.dispatchDraw(canvas);
        if (this.f2078f == null) {
            return;
        }
        if ((this.f2088p & 1) == 1 && !isInEditMode()) {
            this.mFrames++;
            long nanoTime = getNanoTime();
            long j3 = this.mLastDrawTime;
            if (j3 != -1) {
                if (nanoTime - j3 > 200000000) {
                    this.mLastFps = ((int) ((this.mFrames / (((float) j2) * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.mFrames = 0;
                }
                Paint paint = new Paint();
                paint.setTextSize(42.0f);
                StringBuilder sb = new StringBuilder();
                sb.append(this.mLastFps + " fps " + Debug.getState(this, this.mBeginState) + " -> ");
                sb.append(Debug.getState(this, this.mEndState));
                sb.append(" (progress: ");
                sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
                sb.append(" ) state=");
                int i2 = this.f2082j;
                sb.append(i2 != -1 ? "undefined" : Debug.getState(this, i2));
                String sb2 = sb.toString();
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                canvas.drawText(sb2, 11.0f, getHeight() - 29, paint);
                paint.setColor(-7864184);
                canvas.drawText(sb2, 10.0f, getHeight() - 30, paint);
            }
            this.mLastDrawTime = nanoTime;
            Paint paint2 = new Paint();
            paint2.setTextSize(42.0f);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.mLastFps + " fps " + Debug.getState(this, this.mBeginState) + " -> ");
            sb3.append(Debug.getState(this, this.mEndState));
            sb3.append(" (progress: ");
            sb3.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb3.append(" ) state=");
            int i22 = this.f2082j;
            sb3.append(i22 != -1 ? "undefined" : Debug.getState(this, i22));
            String sb22 = sb3.toString();
            paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawText(sb22, 11.0f, getHeight() - 29, paint2);
            paint2.setColor(-7864184);
            canvas.drawText(sb22, 10.0f, getHeight() - 30, paint2);
        }
        if (this.f2088p > 1) {
            if (this.f2089q == null) {
                this.f2089q = new DevModeDraw();
            }
            this.f2089q.draw(canvas, this.f2083k, this.f2078f.getDuration(), this.f2088p);
        }
        ArrayList<MotionHelper> arrayList2 = this.mDecoratorsHelpers;
        if (arrayList2 != null) {
            Iterator<MotionHelper> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().onPostDraw(canvas);
            }
        }
    }

    public void enableTransition(int i2, boolean z) {
        boolean z2;
        MotionScene.Transition transition = getTransition(i2);
        if (z) {
            z2 = true;
        } else {
            MotionScene motionScene = this.f2078f;
            if (transition == motionScene.f2150b) {
                Iterator<MotionScene.Transition> it = motionScene.getTransitionsWithState(this.f2082j).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MotionScene.Transition next = it.next();
                    if (next.isEnabled()) {
                        this.f2078f.f2150b = next;
                        break;
                    }
                }
            }
            z2 = false;
        }
        transition.setEnabled(z2);
    }

    public void enableViewTransition(int i2, boolean z) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            motionScene.enableViewTransition(i2, z);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    protected void f(int i2) {
        this.f2250d = null;
    }

    public void fireTrigger(int i2, boolean z, float f2) {
        TransitionListener transitionListener = this.mTransitionListener;
        if (transitionListener != null) {
            transitionListener.onTransitionTrigger(this, i2, z, f2);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList = this.mTransitionListeners;
        if (copyOnWriteArrayList != null) {
            Iterator<TransitionListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onTransitionTrigger(this, i2, z, f2);
            }
        }
    }

    public ConstraintSet getConstraintSet(int i2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return null;
        }
        return motionScene.h(i2);
    }

    public int[] getConstraintSetIds() {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSetIds();
    }

    public int getCurrentState() {
        return this.f2082j;
    }

    public void getDebugMode(boolean z) {
        this.f2088p = z ? 2 : 1;
        invalidate();
    }

    public ArrayList<MotionScene.Transition> getDefinedTransitions() {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getDefinedTransitions();
    }

    public DesignTool getDesignTool() {
        if (this.mDesignTool == null) {
            this.mDesignTool = new DesignTool(this);
        }
        return this.mDesignTool;
    }

    public int getEndState() {
        return this.mEndState;
    }

    protected long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.f2085m;
    }

    public MotionScene getScene() {
        return this.f2078f;
    }

    public int getStartState() {
        return this.mBeginState;
    }

    public float getTargetPosition() {
        return this.f2086n;
    }

    public MotionScene.Transition getTransition(int i2) {
        return this.f2078f.getTransitionById(i2);
    }

    public Bundle getTransitionState() {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.recordState();
        return this.mStateCache.getTransitionState();
    }

    public long getTransitionTimeMs() {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            this.mTransitionDuration = motionScene.getDuration() / 1000.0f;
        }
        return this.mTransitionDuration * 1000.0f;
    }

    public float getVelocity() {
        return this.f2081i;
    }

    public void getViewVelocity(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float f5 = this.f2081i;
        float f6 = this.f2085m;
        if (this.f2079g != null) {
            float signum = Math.signum(this.f2086n - f6);
            float interpolation = this.f2079g.getInterpolation(this.f2085m + EPSILON);
            float interpolation2 = this.f2079g.getInterpolation(this.f2085m);
            f5 = (signum * ((interpolation - interpolation2) / EPSILON)) / this.mTransitionDuration;
            f4 = interpolation2;
        } else {
            f4 = f6;
        }
        Interpolator interpolator = this.f2079g;
        if (interpolator instanceof MotionInterpolator) {
            f5 = ((MotionInterpolator) interpolator).getVelocity();
        }
        MotionController motionController = (MotionController) this.f2083k.get(view);
        if ((i2 & 1) == 0) {
            motionController.m(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            motionController.h(f4, f2, f3, fArr);
        }
        if (i2 < 2) {
            fArr[0] = fArr[0] * f5;
            fArr[1] = fArr[1] * f5;
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    public boolean isDelayedApplicationOfInitialState() {
        return this.mDelayedApply;
    }

    public boolean isInRotation() {
        return this.mInRotation;
    }

    public boolean isInteractionEnabled() {
        return this.mInteractionEnabled;
    }

    public boolean isViewTransitionEnabled(int i2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            return motionScene.isViewTransitionEnabled(i2);
        }
        return false;
    }

    public void jumpToState(int i2) {
        float f2;
        if (!isAttachedToWindow()) {
            this.f2082j = i2;
        }
        if (this.mBeginState == i2) {
            f2 = 0.0f;
        } else if (this.mEndState != i2) {
            setTransition(i2, i2);
            return;
        } else {
            f2 = 1.0f;
        }
        setProgress(f2);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void loadLayoutDescription(int i2) {
        MotionScene.Transition transition;
        if (i2 == 0) {
            this.f2078f = null;
            return;
        }
        try {
            MotionScene motionScene = new MotionScene(getContext(), this, i2);
            this.f2078f = motionScene;
            if (this.f2082j == -1) {
                this.f2082j = motionScene.u();
                this.mBeginState = this.f2078f.u();
                this.mEndState = this.f2078f.j();
            }
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 19 && !isAttachedToWindow()) {
                this.f2078f = null;
                return;
            }
            if (i3 >= 17) {
                try {
                    Display display = getDisplay();
                    this.mPreviouseRotation = display == null ? 0 : display.getRotation();
                } catch (Exception e2) {
                    throw new IllegalArgumentException("unable to parse MotionScene file", e2);
                }
            }
            MotionScene motionScene2 = this.f2078f;
            if (motionScene2 != null) {
                ConstraintSet h2 = motionScene2.h(this.f2082j);
                this.f2078f.z(this);
                ArrayList<MotionHelper> arrayList = this.mDecoratorsHelpers;
                if (arrayList != null) {
                    Iterator<MotionHelper> it = arrayList.iterator();
                    while (it.hasNext()) {
                        it.next().onFinishedMotionScene(this);
                    }
                }
                if (h2 != null) {
                    h2.applyTo(this);
                }
                this.mBeginState = this.f2082j;
            }
            R();
            StateCache stateCache = this.mStateCache;
            if (stateCache != null) {
                if (this.mDelayedApply) {
                    post(new Runnable() { // from class: androidx.constraintlayout.motion.widget.MotionLayout.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MotionLayout.this.mStateCache.a();
                        }
                    });
                    return;
                } else {
                    stateCache.a();
                    return;
                }
            }
            MotionScene motionScene3 = this.f2078f;
            if (motionScene3 == null || (transition = motionScene3.f2150b) == null || transition.getAutoTransition() != 4) {
                return;
            }
            transitionToEnd();
            setState(TransitionState.SETUP);
            setState(TransitionState.MOVING);
        } catch (Exception e3) {
            throw new IllegalArgumentException("unable to parse MotionScene file", e3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        MotionScene.Transition transition;
        int i2;
        Display display;
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT >= 17 && (display = getDisplay()) != null) {
            this.mPreviouseRotation = display.getRotation();
        }
        MotionScene motionScene = this.f2078f;
        if (motionScene != null && (i2 = this.f2082j) != -1) {
            ConstraintSet h2 = motionScene.h(i2);
            this.f2078f.z(this);
            ArrayList<MotionHelper> arrayList = this.mDecoratorsHelpers;
            if (arrayList != null) {
                Iterator<MotionHelper> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onFinishedMotionScene(this);
                }
            }
            if (h2 != null) {
                h2.applyTo(this);
            }
            this.mBeginState = this.f2082j;
        }
        R();
        StateCache stateCache = this.mStateCache;
        if (stateCache != null) {
            if (this.mDelayedApply) {
                post(new Runnable() { // from class: androidx.constraintlayout.motion.widget.MotionLayout.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MotionLayout.this.mStateCache.a();
                    }
                });
                return;
            } else {
                stateCache.a();
                return;
            }
        }
        MotionScene motionScene2 = this.f2078f;
        if (motionScene2 == null || (transition = motionScene2.f2150b) == null || transition.getAutoTransition() != 4) {
            return;
        }
        transitionToEnd();
        setState(TransitionState.SETUP);
        setState(TransitionState.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        TouchResponse touchResponse;
        int g2;
        RectF f2;
        MotionScene motionScene = this.f2078f;
        if (motionScene != null && this.mInteractionEnabled) {
            ViewTransitionController viewTransitionController = motionScene.f2151c;
            if (viewTransitionController != null) {
                viewTransitionController.i(motionEvent);
            }
            MotionScene.Transition transition = this.f2078f.f2150b;
            if (transition != null && transition.isEnabled() && (touchResponse = transition.getTouchResponse()) != null && ((motionEvent.getAction() != 0 || (f2 = touchResponse.f(this, new RectF())) == null || f2.contains(motionEvent.getX(), motionEvent.getY())) && (g2 = touchResponse.g()) != -1)) {
                View view = this.mRegionView;
                if (view == null || view.getId() != g2) {
                    this.mRegionView = findViewById(g2);
                }
                View view2 = this.mRegionView;
                if (view2 != null) {
                    this.mBoundsCheck.set(view2.getLeft(), this.mRegionView.getTop(), this.mRegionView.getRight(), this.mRegionView.getBottom());
                    if (this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY()) && !handlesTouchEvent(this.mRegionView.getLeft(), this.mRegionView.getTop(), this.mRegionView, motionEvent)) {
                        return onTouchEvent(motionEvent);
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.mInLayout = true;
        try {
            if (this.f2078f == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.f2090r != i6 || this.f2091s != i7) {
                rebuildScene();
                K(true);
            }
            this.f2090r = i6;
            this.f2091s = i7;
        } finally {
            this.mInLayout = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        if (this.f2078f == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z = false;
        boolean z2 = (this.mLastWidthMeasureSpec == i2 && this.mLastHeightMeasureSpec == i3) ? false : true;
        if (this.mNeedsFireTransitionCompleted) {
            this.mNeedsFireTransitionCompleted = false;
            R();
            processTransitionCompleted();
            z2 = true;
        }
        if (this.f2249c) {
            z2 = true;
        }
        this.mLastWidthMeasureSpec = i2;
        this.mLastHeightMeasureSpec = i3;
        int u = this.f2078f.u();
        int j2 = this.f2078f.j();
        if ((z2 || this.L.isNotConfiguredWith(u, j2)) && this.mBeginState != -1) {
            super.onMeasure(i2, i3);
            this.L.c(this.f2248b, this.f2078f.h(u), this.f2078f.h(j2));
            this.L.reEvaluateState();
            this.L.setMeasuredId(u, j2);
        } else {
            if (z2) {
                super.onMeasure(i2, i3);
            }
            z = true;
        }
        if (this.y || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int width = this.f2248b.getWidth() + getPaddingLeft() + getPaddingRight();
            int height = this.f2248b.getHeight() + paddingTop;
            int i6 = this.D;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                width = (int) (this.z + (this.F * (this.B - i4)));
                requestLayout();
            }
            int i7 = this.E;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                height = (int) (this.A + (this.F * (this.C - i5)));
                requestLayout();
            }
            setMeasuredDimension(width, height);
        }
        evaluateLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(@NonNull View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(@NonNull View view, float f2, float f3) {
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(@NonNull final View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        MotionScene.Transition transition;
        TouchResponse touchResponse;
        int g2;
        MotionScene motionScene = this.f2078f;
        if (motionScene == null || (transition = motionScene.f2150b) == null || !transition.isEnabled()) {
            return;
        }
        int i5 = -1;
        if (!transition.isEnabled() || (touchResponse = transition.getTouchResponse()) == null || (g2 = touchResponse.g()) == -1 || view.getId() == g2) {
            if (motionScene.n()) {
                TouchResponse touchResponse2 = transition.getTouchResponse();
                if (touchResponse2 != null && (touchResponse2.getFlags() & 4) != 0) {
                    i5 = i3;
                }
                float f2 = this.f2084l;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(i5)) {
                    return;
                }
            }
            if (transition.getTouchResponse() != null && (transition.getTouchResponse().getFlags() & 1) != 0) {
                float o2 = motionScene.o(i2, i3);
                float f3 = this.f2085m;
                if ((f3 <= 0.0f && o2 < 0.0f) || (f3 >= 1.0f && o2 > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new Runnable(this) { // from class: androidx.constraintlayout.motion.widget.MotionLayout.3
                            @Override // java.lang.Runnable
                            public void run() {
                                view.setNestedScrollingEnabled(true);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            float f4 = this.f2084l;
            long nanoTime = getNanoTime();
            float f5 = i2;
            this.u = f5;
            float f6 = i3;
            this.v = f6;
            this.x = (float) ((nanoTime - this.w) * 1.0E-9d);
            this.w = nanoTime;
            motionScene.w(f5, f6);
            if (f4 != this.f2084l) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            K(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.f2092t = true;
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.f2092t || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.f2092t = false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2, int i3) {
        this.w = getNanoTime();
        this.x = 0.0f;
        this.u = 0.0f;
        this.v = 0.0f;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            motionScene.setRtl(e());
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2, int i3) {
        MotionScene.Transition transition;
        MotionScene motionScene = this.f2078f;
        return (motionScene == null || (transition = motionScene.f2150b) == null || transition.getTouchResponse() == null || (this.f2078f.f2150b.getTouchResponse().getFlags() & 2) != 0) ? false : true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(@NonNull View view, int i2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            float f2 = this.x;
            if (f2 == 0.0f) {
                return;
            }
            motionScene.x(this.u / f2, this.v / f2);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null && this.mInteractionEnabled && motionScene.C()) {
            MotionScene.Transition transition = this.f2078f.f2150b;
            if (transition == null || transition.isEnabled()) {
                this.f2078f.y(motionEvent, getCurrentState(), this);
                return true;
            }
            return super.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.mTransitionListeners == null) {
                this.mTransitionListeners = new CopyOnWriteArrayList<>();
            }
            this.mTransitionListeners.add(motionHelper);
            if (motionHelper.isUsedOnShow()) {
                if (this.mOnShowHelpers == null) {
                    this.mOnShowHelpers = new ArrayList<>();
                }
                this.mOnShowHelpers.add(motionHelper);
            }
            if (motionHelper.isUseOnHide()) {
                if (this.mOnHideHelpers == null) {
                    this.mOnHideHelpers = new ArrayList<>();
                }
                this.mOnHideHelpers.add(motionHelper);
            }
            if (motionHelper.isDecorator()) {
                if (this.mDecoratorsHelpers == null) {
                    this.mDecoratorsHelpers = new ArrayList<>();
                }
                this.mDecoratorsHelpers.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.mOnHideHelpers;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    @Deprecated
    public void rebuildMotion() {
        Log.e("MotionLayout", "This method is deprecated. Please call rebuildScene() instead.");
        rebuildScene();
    }

    public void rebuildScene() {
        this.L.reEvaluateState();
        invalidate();
    }

    public boolean removeTransitionListener(TransitionListener transitionListener) {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList = this.mTransitionListeners;
        if (copyOnWriteArrayList == null) {
            return false;
        }
        return copyOnWriteArrayList.remove(transitionListener);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        MotionScene motionScene;
        MotionScene.Transition transition;
        if (this.y || this.f2082j != -1 || (motionScene = this.f2078f) == null || (transition = motionScene.f2150b) == null || transition.getLayoutDuringTransition() != 0) {
            super.requestLayout();
        }
    }

    @RequiresApi(api = 17)
    public void rotateTo(int i2, int i3) {
        this.mInRotation = true;
        this.mPreRotateWidth = getWidth();
        this.mPreRotateHeight = getHeight();
        int rotation = getDisplay().getRotation();
        this.H = (rotation + 1) % 4 <= (this.mPreviouseRotation + 1) % 4 ? 2 : 1;
        this.mPreviouseRotation = rotation;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            ViewState viewState = (ViewState) this.I.get(childAt);
            if (viewState == null) {
                viewState = new ViewState();
                this.I.put(childAt, viewState);
            }
            viewState.getState(childAt);
        }
        this.mBeginState = -1;
        this.mEndState = i2;
        this.f2078f.A(-1, i2);
        this.L.c(this.f2248b, null, this.f2078f.h(this.mEndState));
        this.f2084l = 0.0f;
        this.f2085m = 0.0f;
        invalidate();
        transitionToEnd(new Runnable() { // from class: androidx.constraintlayout.motion.widget.MotionLayout.2
            @Override // java.lang.Runnable
            public void run() {
                MotionLayout.this.mInRotation = false;
            }
        });
        if (i3 > 0) {
            this.mTransitionDuration = i3 / 1000.0f;
        }
    }

    public void scheduleTransitionTo(int i2) {
        if (getCurrentState() == -1) {
            transitionToState(i2);
            return;
        }
        int[] iArr = this.mScheduledTransitionTo;
        if (iArr == null) {
            this.mScheduledTransitionTo = new int[4];
        } else if (iArr.length <= this.G) {
            this.mScheduledTransitionTo = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.mScheduledTransitionTo;
        int i3 = this.G;
        this.G = i3 + 1;
        iArr2[i3] = i2;
    }

    public void setDebugMode(int i2) {
        this.f2088p = i2;
        invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z) {
        this.mDelayedApply = z;
    }

    public void setInteractionEnabled(boolean z) {
        this.mInteractionEnabled = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.f2078f != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolator = this.f2078f.getInterpolator();
            if (interpolator != null) {
                setProgress(interpolator.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.mOnHideHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mOnHideHelpers.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mOnShowHelpers.get(i2).setProgress(f2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
        if (r4.f2085m == 0.0f) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
        r0 = androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005d, code lost:
        if (r4.f2085m == 1.0f) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setProgress(float f2) {
        TransitionState transitionState;
        int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
        if (i2 >= 0) {
            int i3 = (f2 > 1.0f ? 1 : (f2 == 1.0f ? 0 : -1));
        }
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setProgress(f2);
            return;
        }
        if (i2 <= 0) {
            if (this.f2085m == 1.0f && this.f2082j == this.mEndState) {
                setState(TransitionState.MOVING);
            }
            this.f2082j = this.mBeginState;
        } else if (f2 >= 1.0f) {
            if (this.f2085m == 0.0f && this.f2082j == this.mBeginState) {
                setState(TransitionState.MOVING);
            }
            this.f2082j = this.mEndState;
        } else {
            this.f2082j = -1;
            transitionState = TransitionState.MOVING;
        }
        setState(transitionState);
        if (this.f2078f == null) {
            return;
        }
        this.mTransitionInstantly = true;
        this.f2086n = f2;
        this.f2084l = f2;
        this.mTransitionLastTime = -1L;
        this.mAnimationStartTime = -1L;
        this.f2079g = null;
        this.f2087o = true;
        invalidate();
    }

    public void setProgress(float f2, float f3) {
        if (isAttachedToWindow()) {
            setProgress(f2);
            setState(TransitionState.MOVING);
            this.f2081i = f3;
            H(1.0f);
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setProgress(f2);
        this.mStateCache.setVelocity(f3);
    }

    public void setScene(MotionScene motionScene) {
        this.f2078f = motionScene;
        motionScene.setRtl(e());
        rebuildScene();
    }

    void setStartState(int i2) {
        if (isAttachedToWindow()) {
            this.f2082j = i2;
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setStartState(i2);
        this.mStateCache.setEndState(i2);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void setState(int i2, int i3, int i4) {
        setState(TransitionState.SETUP);
        this.f2082j = i2;
        this.mBeginState = -1;
        this.mEndState = -1;
        ConstraintLayoutStates constraintLayoutStates = this.f2250d;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i2, i3, i4);
            return;
        }
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            motionScene.h(i2).applyTo(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setState(TransitionState transitionState) {
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.f2082j == -1) {
            return;
        }
        TransitionState transitionState3 = this.K;
        this.K = transitionState;
        TransitionState transitionState4 = TransitionState.MOVING;
        if (transitionState3 == transitionState4 && transitionState == transitionState4) {
            fireTransitionChange();
        }
        int i2 = AnonymousClass5.f2097a[transitionState3.ordinal()];
        if (i2 == 1 || i2 == 2) {
            if (transitionState == transitionState4) {
                fireTransitionChange();
            }
            if (transitionState != transitionState2) {
                return;
            }
        } else if (i2 != 3 || transitionState != transitionState2) {
            return;
        }
        L();
    }

    public void setTransition(int i2) {
        MotionScene motionScene;
        int i3;
        if (this.f2078f != null) {
            MotionScene.Transition transition = getTransition(i2);
            this.mBeginState = transition.getStartConstraintSetId();
            this.mEndState = transition.getEndConstraintSetId();
            if (!isAttachedToWindow()) {
                if (this.mStateCache == null) {
                    this.mStateCache = new StateCache();
                }
                this.mStateCache.setStartState(this.mBeginState);
                this.mStateCache.setEndState(this.mEndState);
                return;
            }
            float f2 = Float.NaN;
            int i4 = this.f2082j;
            if (i4 == this.mBeginState) {
                f2 = 0.0f;
            } else if (i4 == this.mEndState) {
                f2 = 1.0f;
            }
            this.f2078f.setTransition(transition);
            this.L.c(this.f2248b, this.f2078f.h(this.mBeginState), this.f2078f.h(this.mEndState));
            rebuildScene();
            if (this.f2085m != f2) {
                if (f2 == 0.0f) {
                    J(true);
                    motionScene = this.f2078f;
                    i3 = this.mBeginState;
                } else if (f2 == 1.0f) {
                    J(false);
                    motionScene = this.f2078f;
                    i3 = this.mEndState;
                }
                motionScene.h(i3).applyTo(this);
            }
            this.f2085m = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(Debug.getLocation());
            sb.append(" transitionToStart ");
            transitionToStart();
        }
    }

    public void setTransition(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setStartState(i2);
            this.mStateCache.setEndState(i3);
            return;
        }
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            this.mBeginState = i2;
            this.mEndState = i3;
            motionScene.A(i2, i3);
            this.L.c(this.f2248b, this.f2078f.h(i2), this.f2078f.h(i3));
            rebuildScene();
            this.f2085m = 0.0f;
            transitionToStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTransition(MotionScene.Transition transition) {
        this.f2078f.setTransition(transition);
        setState(TransitionState.SETUP);
        float f2 = this.f2082j == this.f2078f.j() ? 1.0f : 0.0f;
        this.f2085m = f2;
        this.f2084l = f2;
        this.f2086n = f2;
        this.mTransitionLastTime = transition.isTransitionFlag(1) ? -1L : getNanoTime();
        int u = this.f2078f.u();
        int j2 = this.f2078f.j();
        if (u == this.mBeginState && j2 == this.mEndState) {
            return;
        }
        this.mBeginState = u;
        this.mEndState = j2;
        this.f2078f.A(u, j2);
        this.L.c(this.f2248b, this.f2078f.h(this.mBeginState), this.f2078f.h(this.mEndState));
        this.L.setMeasuredId(this.mBeginState, this.mEndState);
        this.L.reEvaluateState();
        rebuildScene();
    }

    public void setTransitionDuration(int i2) {
        MotionScene motionScene = this.f2078f;
        if (motionScene == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            motionScene.setDuration(i2);
        }
    }

    public void setTransitionListener(TransitionListener transitionListener) {
        this.mTransitionListener = transitionListener;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setTransitionState(bundle);
        if (isAttachedToWindow()) {
            this.mStateCache.a();
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return Debug.getName(context, this.mBeginState) + "->" + Debug.getName(context, this.mEndState) + " (pos:" + this.f2085m + " Dpos/Dt:" + this.f2081i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
        if (r10 != 7) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void touchAnimateTo(int i2, float f2, float f3) {
        Interpolator interpolator;
        if (this.f2078f == null || this.f2085m == f2) {
            return;
        }
        this.mTemporalInterpolator = true;
        this.mAnimationStartTime = getNanoTime();
        this.mTransitionDuration = this.f2078f.getDuration() / 1000.0f;
        this.f2086n = f2;
        this.f2087o = true;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            if (i2 != 4) {
                if (i2 != 5) {
                    if (i2 != 6) {
                    }
                } else if (!willJump(f3, this.f2085m, this.f2078f.l())) {
                    this.mStopLogic.config(this.f2085m, f2, f3, this.mTransitionDuration, this.f2078f.l(), this.f2078f.m());
                    this.f2081i = 0.0f;
                    int i3 = this.f2082j;
                    this.f2086n = f2;
                    this.f2082j = i3;
                    interpolator = this.mStopLogic;
                    this.f2079g = interpolator;
                    this.mTransitionInstantly = false;
                    this.mAnimationStartTime = getNanoTime();
                    invalidate();
                }
            }
            this.mDecelerateLogic.config(f3, this.f2085m, this.f2078f.l());
            interpolator = this.mDecelerateLogic;
            this.f2079g = interpolator;
            this.mTransitionInstantly = false;
            this.mAnimationStartTime = getNanoTime();
            invalidate();
        }
        if (i2 == 1 || i2 == 7) {
            f2 = 0.0f;
        } else if (i2 == 2 || i2 == 6) {
            f2 = 1.0f;
        }
        int g2 = this.f2078f.g();
        StopLogic stopLogic = this.mStopLogic;
        float f4 = this.f2085m;
        if (g2 == 0) {
            stopLogic.config(f4, f2, f3, this.mTransitionDuration, this.f2078f.l(), this.f2078f.m());
        } else {
            stopLogic.springConfig(f4, f2, f3, this.f2078f.r(), this.f2078f.s(), this.f2078f.q(), this.f2078f.t(), this.f2078f.p());
        }
        int i32 = this.f2082j;
        this.f2086n = f2;
        this.f2082j = i32;
        interpolator = this.mStopLogic;
        this.f2079g = interpolator;
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        invalidate();
    }

    public void touchSpringTo(float f2, float f3) {
        if (this.f2078f == null || this.f2085m == f2) {
            return;
        }
        this.mTemporalInterpolator = true;
        this.mAnimationStartTime = getNanoTime();
        this.mTransitionDuration = this.f2078f.getDuration() / 1000.0f;
        this.f2086n = f2;
        this.f2087o = true;
        this.mStopLogic.springConfig(this.f2085m, f2, f3, this.f2078f.r(), this.f2078f.s(), this.f2078f.q(), this.f2078f.t(), this.f2078f.p());
        int i2 = this.f2082j;
        this.f2086n = f2;
        this.f2082j = i2;
        this.f2079g = this.mStopLogic;
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        invalidate();
    }

    public void transitionToEnd() {
        H(1.0f);
        this.mOnComplete = null;
    }

    public void transitionToEnd(Runnable runnable) {
        H(1.0f);
        this.mOnComplete = runnable;
    }

    public void transitionToStart() {
        H(0.0f);
    }

    public void transitionToState(int i2) {
        if (isAttachedToWindow()) {
            transitionToState(i2, -1, -1);
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setEndState(i2);
    }

    public void transitionToState(int i2, int i3) {
        if (isAttachedToWindow()) {
            transitionToState(i2, -1, -1, i3);
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setEndState(i2);
    }

    public void transitionToState(int i2, int i3, int i4) {
        transitionToState(i2, i3, i4, -1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
        if (r14 > 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void transitionToState(int i2, int i3, int i4, int i5) {
        StateSet stateSet;
        int convertToConstraintSet;
        MotionScene motionScene = this.f2078f;
        if (motionScene != null && (stateSet = motionScene.f2149a) != null && (convertToConstraintSet = stateSet.convertToConstraintSet(this.f2082j, i2, i3, i4)) != -1) {
            i2 = convertToConstraintSet;
        }
        int i6 = this.f2082j;
        if (i6 == i2) {
            return;
        }
        if (this.mBeginState == i2) {
            H(0.0f);
            if (i5 > 0) {
                this.mTransitionDuration = i5 / 1000.0f;
            }
        } else if (this.mEndState == i2) {
            H(1.0f);
            if (i5 > 0) {
                this.mTransitionDuration = i5 / 1000.0f;
            }
        } else {
            this.mEndState = i2;
            if (i6 != -1) {
                setTransition(i6, i2);
                H(1.0f);
                this.f2085m = 0.0f;
                transitionToEnd();
                if (i5 > 0) {
                    this.mTransitionDuration = i5 / 1000.0f;
                    return;
                }
                return;
            }
            this.mTemporalInterpolator = false;
            this.f2086n = 1.0f;
            this.f2084l = 0.0f;
            this.f2085m = 0.0f;
            this.mTransitionLastTime = getNanoTime();
            this.mAnimationStartTime = getNanoTime();
            this.mTransitionInstantly = false;
            this.f2079g = null;
            if (i5 == -1) {
                this.mTransitionDuration = this.f2078f.getDuration() / 1000.0f;
            }
            this.mBeginState = -1;
            this.f2078f.A(-1, this.mEndState);
            SparseArray sparseArray = new SparseArray();
            if (i5 == 0) {
                i5 = this.f2078f.getDuration();
            }
            this.mTransitionDuration = i5 / 1000.0f;
            int childCount = getChildCount();
            this.f2083k.clear();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                this.f2083k.put(childAt, new MotionController(childAt));
                sparseArray.put(childAt.getId(), this.f2083k.get(childAt));
            }
            this.f2087o = true;
            this.L.c(this.f2248b, null, this.f2078f.h(i2));
            rebuildScene();
            this.L.build();
            computeCurrentPositions();
            int width = getWidth();
            int height = getHeight();
            if (this.mDecoratorsHelpers != null) {
                for (int i8 = 0; i8 < childCount; i8++) {
                    MotionController motionController = (MotionController) this.f2083k.get(getChildAt(i8));
                    if (motionController != null) {
                        this.f2078f.getKeyFrames(motionController);
                    }
                }
                Iterator<MotionHelper> it = this.mDecoratorsHelpers.iterator();
                while (it.hasNext()) {
                    it.next().onPreSetup(this, this.f2083k);
                }
                for (int i9 = 0; i9 < childCount; i9++) {
                    MotionController motionController2 = (MotionController) this.f2083k.get(getChildAt(i9));
                    if (motionController2 != null) {
                        motionController2.setup(width, height, this.mTransitionDuration, getNanoTime());
                    }
                }
            } else {
                for (int i10 = 0; i10 < childCount; i10++) {
                    MotionController motionController3 = (MotionController) this.f2083k.get(getChildAt(i10));
                    if (motionController3 != null) {
                        this.f2078f.getKeyFrames(motionController3);
                        motionController3.setup(width, height, this.mTransitionDuration, getNanoTime());
                    }
                }
            }
            float staggered = this.f2078f.getStaggered();
            if (staggered != 0.0f) {
                float f2 = Float.MAX_VALUE;
                float f3 = -3.4028235E38f;
                for (int i11 = 0; i11 < childCount; i11++) {
                    MotionController motionController4 = (MotionController) this.f2083k.get(getChildAt(i11));
                    float finalY = motionController4.getFinalY() + motionController4.getFinalX();
                    f2 = Math.min(f2, finalY);
                    f3 = Math.max(f3, finalY);
                }
                for (int i12 = 0; i12 < childCount; i12++) {
                    MotionController motionController5 = (MotionController) this.f2083k.get(getChildAt(i12));
                    float finalX = motionController5.getFinalX();
                    float finalY2 = motionController5.getFinalY();
                    motionController5.f2073f = 1.0f / (1.0f - staggered);
                    motionController5.f2072e = staggered - ((((finalX + finalY2) - f2) * staggered) / (f3 - f2));
                }
            }
            this.f2084l = 0.0f;
            this.f2085m = 0.0f;
            this.f2087o = true;
            invalidate();
        }
    }

    public void updateState() {
        this.L.c(this.f2248b, this.f2078f.h(this.mBeginState), this.f2078f.h(this.mEndState));
        rebuildScene();
    }

    public void updateState(int i2, ConstraintSet constraintSet) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            motionScene.setConstraintSet(i2, constraintSet);
        }
        updateState();
        if (this.f2082j == i2) {
            constraintSet.applyTo(this);
        }
    }

    public void updateStateAnimate(int i2, ConstraintSet constraintSet, int i3) {
        if (this.f2078f != null && this.f2082j == i2) {
            int i4 = R.id.view_transition;
            updateState(i4, getConstraintSet(i2));
            setState(i4, -1, -1);
            updateState(i2, constraintSet);
            MotionScene.Transition transition = new MotionScene.Transition(-1, this.f2078f, i4, i2);
            transition.setDuration(i3);
            setTransition(transition);
            transitionToEnd();
        }
    }

    public void viewTransition(int i2, View... viewArr) {
        MotionScene motionScene = this.f2078f;
        if (motionScene != null) {
            motionScene.viewTransition(i2, viewArr);
        } else {
            Log.e("MotionLayout", " no motionScene");
        }
    }
}
