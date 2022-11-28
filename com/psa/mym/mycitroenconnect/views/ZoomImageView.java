package com.psa.mym.mycitroenconnect.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.ViewCompat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ZoomImageView extends AppCompatImageView {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final float MAX_SCALE = 3.0f;
    public static final float MID_SCALE = 1.75f;
    public static final float MIN_SCALE = 1.0f;
    private static final long VALUE_ANIMATOR_DURATION = 300;
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    private final Matrix baseMatrix;
    private boolean debugInfoVisible;
    private boolean disallowPagingWhenZoomed;
    @NotNull
    private Function1<? super Float, Unit> dismissProgressListener;
    @Nullable
    private final RectF displayRect;
    @NotNull
    private final Matrix drawMatrix;
    @NotNull
    private final ZoomImageView$flingRunnable$1 flingRunnable;
    private boolean handlingDismiss;
    @NotNull
    private String logText;
    @NotNull
    private final float[] matrixValues;
    private float oldScale;
    @Nullable
    private View.OnClickListener onClickListener;
    @NotNull
    private Function0<Unit> onDismiss;
    @NotNull
    private Function0<Unit> onDrawableLoaded;
    @Nullable
    private View.OnLongClickListener onLongClickListener;
    @Nullable
    private ValueAnimator panAnimator;
    @NotNull
    private final RectF preEventImgRect;
    private ScaleGestureDetector scaleDetector;
    @NotNull
    private final ZoomImageView$scaleListener$1 scaleListener;
    private OverScroller scroller;
    private boolean swipeToDismissEnabled;
    private GestureDetector tapDetector;
    @NotNull
    private final Paint textPaint;
    private float touchSlop;
    private int viewHeight;
    private int viewWidth;
    @Nullable
    private ValueAnimator zoomAnimator;
    @NotNull
    private final AccelerateDecelerateInterpolator zoomInterpolator;
    @NotNull
    private final Matrix zoomMatrix;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v24, types: [com.psa.mym.mycitroenconnect.views.ZoomImageView$scaleListener$1] */
    public ZoomImageView(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.textPaint = new Paint();
        this.zoomMatrix = new Matrix();
        this.baseMatrix = new Matrix();
        this.preEventImgRect = new RectF();
        this.matrixValues = new float[9];
        this.logText = "";
        this.oldScale = 1.0f;
        this.viewWidth = ((getRight() - getLeft()) - getPaddingLeft()) - getPaddingRight();
        this.viewHeight = ((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom();
        this.zoomInterpolator = new AccelerateDecelerateInterpolator();
        this.onDismiss = ZoomImageView$onDismiss$1.INSTANCE;
        this.onDrawableLoaded = ZoomImageView$onDrawableLoaded$1.INSTANCE;
        this.dismissProgressListener = ZoomImageView$dismissProgressListener$1.INSTANCE;
        this.displayRect = new RectF();
        this.drawMatrix = new Matrix();
        this.flingRunnable = new ZoomImageView$flingRunnable$1(this);
        this.scaleListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.psa.mym.mycitroenconnect.views.ZoomImageView$scaleListener$1
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkNotNullParameter(detector, "detector");
                if (Float.isNaN(detector.getScaleFactor()) || Float.isInfinite(detector.getScaleFactor())) {
                    return false;
                }
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                if (zoomImageView.matrixValues[0] <= 3.0f || detector.getScaleFactor() <= 1.0f) {
                    ZoomImageView zoomImageView2 = ZoomImageView.this;
                    zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                    zoomImageView2.oldScale = zoomImageView2.matrixValues[0];
                    ZoomImageView.this.setScale(detector.getScaleFactor(), detector.getFocusX(), detector.getFocusY());
                    return true;
                }
                return false;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkNotNullParameter(detector, "detector");
                super.onScaleEnd(detector);
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                zoomImageView.oldScale = zoomImageView.matrixValues[0];
                ZoomImageView zoomImageView2 = ZoomImageView.this;
                zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                if (zoomImageView2.matrixValues[0] < 1.0f) {
                    ZoomImageView.this.setScaleAbsolute(1.0f, detector.getFocusX(), detector.getFocusY());
                }
            }
        };
        initView();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v24, types: [com.psa.mym.mycitroenconnect.views.ZoomImageView$scaleListener$1] */
    public ZoomImageView(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        this.textPaint = new Paint();
        this.zoomMatrix = new Matrix();
        this.baseMatrix = new Matrix();
        this.preEventImgRect = new RectF();
        this.matrixValues = new float[9];
        this.logText = "";
        this.oldScale = 1.0f;
        this.viewWidth = ((getRight() - getLeft()) - getPaddingLeft()) - getPaddingRight();
        this.viewHeight = ((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom();
        this.zoomInterpolator = new AccelerateDecelerateInterpolator();
        this.onDismiss = ZoomImageView$onDismiss$1.INSTANCE;
        this.onDrawableLoaded = ZoomImageView$onDrawableLoaded$1.INSTANCE;
        this.dismissProgressListener = ZoomImageView$dismissProgressListener$1.INSTANCE;
        this.displayRect = new RectF();
        this.drawMatrix = new Matrix();
        this.flingRunnable = new ZoomImageView$flingRunnable$1(this);
        this.scaleListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.psa.mym.mycitroenconnect.views.ZoomImageView$scaleListener$1
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkNotNullParameter(detector, "detector");
                if (Float.isNaN(detector.getScaleFactor()) || Float.isInfinite(detector.getScaleFactor())) {
                    return false;
                }
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                if (zoomImageView.matrixValues[0] <= 3.0f || detector.getScaleFactor() <= 1.0f) {
                    ZoomImageView zoomImageView2 = ZoomImageView.this;
                    zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                    zoomImageView2.oldScale = zoomImageView2.matrixValues[0];
                    ZoomImageView.this.setScale(detector.getScaleFactor(), detector.getFocusX(), detector.getFocusY());
                    return true;
                }
                return false;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkNotNullParameter(detector, "detector");
                super.onScaleEnd(detector);
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                zoomImageView.oldScale = zoomImageView.matrixValues[0];
                ZoomImageView zoomImageView2 = ZoomImageView.this;
                zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                if (zoomImageView2.matrixValues[0] < 1.0f) {
                    ZoomImageView.this.setScaleAbsolute(1.0f, detector.getFocusX(), detector.getFocusY());
                }
            }
        };
        initView();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v24, types: [com.psa.mym.mycitroenconnect.views.ZoomImageView$scaleListener$1] */
    public ZoomImageView(@NotNull Context context, @NotNull AttributeSet attrs, int i2) {
        super(context, attrs, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        this.textPaint = new Paint();
        this.zoomMatrix = new Matrix();
        this.baseMatrix = new Matrix();
        this.preEventImgRect = new RectF();
        this.matrixValues = new float[9];
        this.logText = "";
        this.oldScale = 1.0f;
        this.viewWidth = ((getRight() - getLeft()) - getPaddingLeft()) - getPaddingRight();
        this.viewHeight = ((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom();
        this.zoomInterpolator = new AccelerateDecelerateInterpolator();
        this.onDismiss = ZoomImageView$onDismiss$1.INSTANCE;
        this.onDrawableLoaded = ZoomImageView$onDrawableLoaded$1.INSTANCE;
        this.dismissProgressListener = ZoomImageView$dismissProgressListener$1.INSTANCE;
        this.displayRect = new RectF();
        this.drawMatrix = new Matrix();
        this.flingRunnable = new ZoomImageView$flingRunnable$1(this);
        this.scaleListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.psa.mym.mycitroenconnect.views.ZoomImageView$scaleListener$1
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkNotNullParameter(detector, "detector");
                if (Float.isNaN(detector.getScaleFactor()) || Float.isInfinite(detector.getScaleFactor())) {
                    return false;
                }
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                if (zoomImageView.matrixValues[0] <= 3.0f || detector.getScaleFactor() <= 1.0f) {
                    ZoomImageView zoomImageView2 = ZoomImageView.this;
                    zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                    zoomImageView2.oldScale = zoomImageView2.matrixValues[0];
                    ZoomImageView.this.setScale(detector.getScaleFactor(), detector.getFocusX(), detector.getFocusY());
                    return true;
                }
                return false;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkNotNullParameter(detector, "detector");
                super.onScaleEnd(detector);
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                zoomImageView.oldScale = zoomImageView.matrixValues[0];
                ZoomImageView zoomImageView2 = ZoomImageView.this;
                zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                if (zoomImageView2.matrixValues[0] < 1.0f) {
                    ZoomImageView.this.setScaleAbsolute(1.0f, detector.getFocusX(), detector.getFocusY());
                }
            }
        };
        initView();
    }

    private final void animatePan(final float f2, final float f3, final float f4, final float f5, final Float f6) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3, f4, f5);
        ofFloat.setDuration(300L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.psa.mym.mycitroenconnect.views.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ZoomImageView.m169animatePan$lambda8$lambda5(f2, f4, f3, f5, this, f6, valueAnimator);
            }
        });
        ofFloat.setInterpolator(this.zoomInterpolator);
        ofFloat.start();
        Intrinsics.checkNotNullExpressionValue(ofFloat, "");
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.psa.mym.mycitroenconnect.views.ZoomImageView$animatePan$lambda-8$$inlined$doOnCancel$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                ZoomImageView.this.panImage(0.0f, 0.0f, true);
                ZoomImageView.this.handlingDismiss = false;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }
        });
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.psa.mym.mycitroenconnect.views.ZoomImageView$animatePan$lambda-8$$inlined$doOnEnd$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                ZoomImageView.this.handlingDismiss = false;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }
        });
        this.panAnimator = ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: animatePan$lambda-8$lambda-5  reason: not valid java name */
    public static final void m169animatePan$lambda8$lambda5(float f2, float f3, float f4, float f5, ZoomImageView this$0, Float f6, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.panImage(f2 - ((f2 - f3) * valueAnimator.getAnimatedFraction()), f4 - ((f4 - f5) * valueAnimator.getAnimatedFraction()), true);
        if (f6 != null) {
            if (1.0f - valueAnimator.getAnimatedFraction() < f6.floatValue()) {
                this$0.dismissProgressListener.invoke(Float.valueOf(1.0f - valueAnimator.getAnimatedFraction()));
            }
        }
    }

    private final void animateZoom(float f2, float f3, final float f4, final float f5) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        ofFloat.setDuration(300L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.psa.mym.mycitroenconnect.views.d
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ZoomImageView.m170animateZoom$lambda3$lambda2(ZoomImageView.this, f4, f5, valueAnimator);
            }
        });
        ofFloat.setInterpolator(this.zoomInterpolator);
        ofFloat.start();
        this.zoomAnimator = ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: animateZoom$lambda-3$lambda-2  reason: not valid java name */
    public static final void m170animateZoom$lambda3$lambda2(ZoomImageView this$0, float f2, float f3, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        this$0.zoomMatrix.getValues(this$0.matrixValues);
        this$0.setZoom(floatValue / this$0.matrixValues[0], f2, f3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(ZoomImageView zoomImageView, float f2, float f3, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        zoomImageView.panImage(f2, f3, z);
    }

    private final void cancelAnimation() {
        ValueAnimator valueAnimator = this.zoomAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator2 = this.zoomAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
    }

    private final float getCurrentScale() {
        this.zoomMatrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }

    private final float getCurrentTransX() {
        this.zoomMatrix.getValues(this.matrixValues);
        return this.matrixValues[2];
    }

    private final float getCurrentTransY() {
        this.zoomMatrix.getValues(this.matrixValues);
        return this.matrixValues[5];
    }

    private final float getDismissProgress() {
        this.zoomMatrix.getValues(this.matrixValues);
        return Math.abs(this.matrixValues[5]) / (this.viewHeight / 3.0f);
    }

    private final float getDismissThreshold() {
        return this.viewHeight / 3.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RectF getDisplayRect() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            RectF rectF = this.displayRect;
            if (rectF != null) {
                rectF.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            getDrawMatrix().mapRect(this.displayRect);
            return this.displayRect;
        }
        return null;
    }

    private final Matrix getDrawMatrix() {
        this.drawMatrix.set(this.baseMatrix);
        this.drawMatrix.postConcat(this.zoomMatrix);
        return this.drawMatrix;
    }

    private final int getDrawableHeight() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return 0;
    }

    private final int getDrawableWidth() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return 0;
    }

    private final void initTextPaint() {
        this.textPaint.setColor(-1);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setTextSize(40.0f);
    }

    private final void initView() {
        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        initTextPaint();
        setScaleType(ImageView.ScaleType.MATRIX);
        this.scaleDetector = new ScaleGestureDetector(getContext(), this.scaleListener);
        this.scroller = new OverScroller(getContext(), new DecelerateInterpolator());
        this.tapDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.psa.mym.mycitroenconnect.views.ZoomImageView$initView$1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(@NotNull MotionEvent e2) {
                Intrinsics.checkNotNullParameter(e2, "e");
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                zoomImageView.oldScale = zoomImageView.matrixValues[0];
                ZoomImageView zoomImageView2 = ZoomImageView.this;
                zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                ZoomImageView.this.setScaleAbsolute(zoomImageView2.matrixValues[0] == 1.0f ? 1.75f : 1.0f, e2.getX(), e2.getY());
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(@Nullable MotionEvent motionEvent) {
                ZoomImageView$flingRunnable$1 zoomImageView$flingRunnable$1;
                OverScroller overScroller;
                RectF displayRect;
                ValueAnimator valueAnimator;
                ValueAnimator valueAnimator2;
                RectF rectF;
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView$flingRunnable$1 = zoomImageView.flingRunnable;
                zoomImageView.removeCallbacks(zoomImageView$flingRunnable$1);
                overScroller = ZoomImageView.this.scroller;
                if (overScroller == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scroller");
                    overScroller = null;
                }
                overScroller.forceFinished(true);
                displayRect = ZoomImageView.this.getDisplayRect();
                if (displayRect != null) {
                    rectF = ZoomImageView.this.preEventImgRect;
                    rectF.set(displayRect);
                }
                valueAnimator = ZoomImageView.this.panAnimator;
                if (valueAnimator != null) {
                    valueAnimator.removeAllUpdateListeners();
                }
                valueAnimator2 = ZoomImageView.this.panAnimator;
                if (valueAnimator2 != null) {
                    valueAnimator2.cancel();
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(@NotNull MotionEvent e1, @NotNull MotionEvent e2, float f2, float f3) {
                RectF rectF;
                int i2;
                RectF rectF2;
                ZoomImageView$flingRunnable$1 zoomImageView$flingRunnable$1;
                RectF rectF3;
                ZoomImageView$flingRunnable$1 zoomImageView$flingRunnable$12;
                RectF rectF4;
                OverScroller overScroller;
                ZoomImageView$flingRunnable$1 zoomImageView$flingRunnable$13;
                ZoomImageView$flingRunnable$1 zoomImageView$flingRunnable$14;
                ZoomImageView$flingRunnable$1 zoomImageView$flingRunnable$15;
                Intrinsics.checkNotNullParameter(e1, "e1");
                Intrinsics.checkNotNullParameter(e2, "e2");
                if (ZoomImageView.this.getCurrentZoom() <= 1.0f) {
                    return false;
                }
                rectF = ZoomImageView.this.preEventImgRect;
                float width = rectF.width();
                i2 = ZoomImageView.this.viewWidth;
                int i3 = (int) (width - i2);
                rectF2 = ZoomImageView.this.preEventImgRect;
                int height = (int) (rectF2.height() - ZoomImageView.this.viewHeight);
                zoomImageView$flingRunnable$1 = ZoomImageView.this.flingRunnable;
                rectF3 = ZoomImageView.this.preEventImgRect;
                zoomImageView$flingRunnable$1.setLastX(-rectF3.left);
                zoomImageView$flingRunnable$12 = ZoomImageView.this.flingRunnable;
                rectF4 = ZoomImageView.this.preEventImgRect;
                zoomImageView$flingRunnable$12.setLastY(-rectF4.top);
                overScroller = ZoomImageView.this.scroller;
                if (overScroller == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scroller");
                    overScroller = null;
                }
                OverScroller overScroller2 = overScroller;
                zoomImageView$flingRunnable$13 = ZoomImageView.this.flingRunnable;
                int lastX = (int) zoomImageView$flingRunnable$13.getLastX();
                zoomImageView$flingRunnable$14 = ZoomImageView.this.flingRunnable;
                overScroller2.fling(lastX, (int) zoomImageView$flingRunnable$14.getLastY(), -((int) f2), -((int) f3), 0, i3, 0, height);
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView$flingRunnable$15 = zoomImageView.flingRunnable;
                ViewCompat.postOnAnimation(zoomImageView, zoomImageView$flingRunnable$15);
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(@Nullable MotionEvent motionEvent) {
                View.OnLongClickListener onLongClickListener;
                onLongClickListener = ZoomImageView.this.onLongClickListener;
                if (onLongClickListener != null) {
                    onLongClickListener.onLongClick(ZoomImageView.this);
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:29:0x00c7, code lost:
                if ((r14 == ((float) r1)) != false) goto L27;
             */
            /* JADX WARN: Code restructure failed: missing block: B:37:0x00dd, code lost:
                if ((r13.left == 0.0f) != false) goto L27;
             */
            /* JADX WARN: Code restructure failed: missing block: B:45:0x00fa, code lost:
                if ((r13.bottom == ((float) r10.f10764a.viewHeight)) != false) goto L27;
             */
            /* JADX WARN: Code restructure failed: missing block: B:46:0x00fc, code lost:
                r13 = false;
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x0111, code lost:
                if ((r13.top == 0.0f) != false) goto L27;
             */
            /* JADX WARN: Removed duplicated region for block: B:58:0x011d  */
            /* JADX WARN: Removed duplicated region for block: B:61:0x012a  */
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public boolean onScroll(@NotNull MotionEvent e1, @NotNull MotionEvent e2, float f2, float f3) {
                ScaleGestureDetector scaleGestureDetector;
                boolean z;
                ViewParent parent;
                float f4;
                float f5;
                boolean z2;
                RectF rectF;
                RectF rectF2;
                RectF rectF3;
                RectF rectF4;
                int i2;
                Intrinsics.checkNotNullParameter(e1, "e1");
                Intrinsics.checkNotNullParameter(e2, "e2");
                scaleGestureDetector = ZoomImageView.this.scaleDetector;
                if (scaleGestureDetector == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scaleDetector");
                    scaleGestureDetector = null;
                }
                if (scaleGestureDetector.isInProgress()) {
                    return false;
                }
                float abs = Math.abs(f2);
                float abs2 = Math.abs(f3);
                ZoomImageView zoomImageView = ZoomImageView.this;
                zoomImageView.zoomMatrix.getValues(zoomImageView.matrixValues);
                if (zoomImageView.matrixValues[0] > 1.0f) {
                    ZoomImageView.c(ZoomImageView.this, f2, f3, false, 4, null);
                } else if (ZoomImageView.this.getSwipeToDismissEnabled() && abs2 > abs) {
                    ZoomImageView.this.handlingDismiss = true;
                    ZoomImageView.c(ZoomImageView.this, 0.0f, f3, false, 4, null);
                    Function1<Float, Unit> dismissProgressListener = ZoomImageView.this.getDismissProgressListener();
                    ZoomImageView zoomImageView2 = ZoomImageView.this;
                    zoomImageView2.zoomMatrix.getValues(zoomImageView2.matrixValues);
                    dismissProgressListener.invoke(Float.valueOf(Math.abs(zoomImageView2.matrixValues[5]) / (zoomImageView2.viewHeight / 3.0f)));
                }
                if (!ZoomImageView.this.getDisallowPagingWhenZoomed()) {
                    z2 = ZoomImageView.this.handlingDismiss;
                    if (!z2) {
                        if (abs > abs2) {
                            if (f2 > 0.0f) {
                                rectF4 = ZoomImageView.this.preEventImgRect;
                                float f6 = rectF4.right;
                                i2 = ZoomImageView.this.viewWidth;
                            }
                            if (f2 < 0.0f) {
                                rectF3 = ZoomImageView.this.preEventImgRect;
                            }
                        } else {
                            if (f3 > 0.0f) {
                                rectF2 = ZoomImageView.this.preEventImgRect;
                            }
                            if (f3 < 0.0f) {
                                rectF = ZoomImageView.this.preEventImgRect;
                            }
                        }
                        parent = ZoomImageView.this.getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(z);
                        }
                        f4 = ZoomImageView.this.touchSlop;
                        if (abs <= f4) {
                            f5 = ZoomImageView.this.touchSlop;
                            if (abs2 <= f5) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
                z = true;
                parent = ZoomImageView.this.getParent();
                if (parent != null) {
                }
                f4 = ZoomImageView.this.touchSlop;
                if (abs <= f4) {
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(@Nullable MotionEvent motionEvent) {
                View.OnClickListener onClickListener;
                onClickListener = ZoomImageView.this.onClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(ZoomImageView.this);
                    return true;
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void panImage(float f2, float f3, boolean z) {
        if (z) {
            this.zoomMatrix.setTranslate(f2, f3);
        } else {
            this.zoomMatrix.postTranslate(-f2, -f3);
        }
        setBounds();
        updateMatrix(getDrawMatrix());
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
        if (r1 < r3) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
        if (r0 < r1) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setBounds() {
        float f2;
        float f3;
        float f4;
        RectF displayRect = getDisplayRect();
        if (displayRect == null) {
            return;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        float f5 = this.viewHeight;
        float f6 = 0.0f;
        if (height <= f5) {
            if (!this.handlingDismiss) {
                f5 = (f5 - height) / 2;
                f2 = displayRect.top;
                f3 = f5 - f2;
            }
            f3 = 0.0f;
        } else {
            float f7 = displayRect.top;
            if (f7 > 0.0f) {
                f3 = -f7;
            } else {
                f2 = displayRect.bottom;
            }
        }
        float f8 = this.viewWidth;
        if (width > f8) {
            float f9 = displayRect.left;
            if (f9 > 0.0f) {
                f6 = -f9;
            } else {
                f4 = displayRect.right;
            }
            this.zoomMatrix.postTranslate(f6, f3);
        }
        f8 = (f8 - width) / 2;
        f4 = displayRect.left;
        f6 = f8 - f4;
        this.zoomMatrix.postTranslate(f6, f3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setScale(float f2, float f3, float f4) {
        setZoom(f2, f3, f4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setScaleAbsolute(float f2, float f3, float f4) {
        if (f2 > 3.0f) {
            f2 = 3.0f;
        } else if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        cancelAnimation();
        animateZoom(this.oldScale, f2, f3, f4);
    }

    private final void setZoom(float f2, float f3, float f4) {
        this.zoomMatrix.postScale(f2, f2, f3, f4);
        setBounds();
        updateMatrix(getDrawMatrix());
    }

    private final void updateMatrix(Matrix matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append("tX: ");
        this.zoomMatrix.getValues(this.matrixValues);
        sb.append(this.matrixValues[2]);
        sb.append(" tY: ");
        this.zoomMatrix.getValues(this.matrixValues);
        sb.append(this.matrixValues[5]);
        this.logText = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.logText);
        sb2.append(" Scale: ");
        this.zoomMatrix.getValues(this.matrixValues);
        sb2.append(this.matrixValues[0]);
        this.logText = sb2.toString();
        setImageMatrix(matrix);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final float getCurrentZoom() {
        this.zoomMatrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }

    public final boolean getDebugInfoVisible() {
        return this.debugInfoVisible;
    }

    public final boolean getDisallowPagingWhenZoomed() {
        return this.disallowPagingWhenZoomed;
    }

    @NotNull
    public final Function1<Float, Unit> getDismissProgressListener() {
        return this.dismissProgressListener;
    }

    @NotNull
    public final Function0<Unit> getOnDismiss() {
        return this.onDismiss;
    }

    @NotNull
    public final Function0<Unit> getOnDrawableLoaded() {
        return this.onDrawableLoaded;
    }

    public final boolean getSwipeToDismissEnabled() {
        return this.swipeToDismissEnabled;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0032, code lost:
        if (r0 == null) goto L10;
     */
    @Override // android.widget.ImageView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onDraw(@NotNull Canvas canvas) {
        String str;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (this.debugInfoVisible) {
            canvas.drawText(this.logText, 10.0f, getHeight() - 10.0f, this.textPaint);
            RectF displayRect = getDisplayRect();
            if (displayRect != null) {
                str = "Drawable: " + displayRect;
            }
            str = "";
            canvas.drawText(str, 10.0f, 40.0f, this.textPaint);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.viewWidth = ((i4 - i2) - getPaddingLeft()) - getPaddingRight();
        this.viewHeight = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        if (z) {
            resetZoom();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bb  */
    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(@Nullable MotionEvent motionEvent) {
        boolean z;
        ViewParent parent;
        GestureDetector gestureDetector;
        this.zoomMatrix.getValues(this.matrixValues);
        boolean z2 = false;
        ScaleGestureDetector scaleGestureDetector = null;
        if (this.matrixValues[0] <= 1.0f) {
            ScaleGestureDetector scaleGestureDetector2 = this.scaleDetector;
            if (scaleGestureDetector2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scaleDetector");
                scaleGestureDetector2 = null;
            }
            if (!scaleGestureDetector2.isInProgress() && !this.handlingDismiss) {
                z = false;
                if (motionEvent != null && motionEvent.getAction() == 1) {
                    z2 = true;
                }
                if (z2 && this.handlingDismiss) {
                    this.zoomMatrix.getValues(this.matrixValues);
                    if (Math.abs(this.matrixValues[5]) <= this.viewHeight / 3.0f) {
                        this.onDismiss.invoke();
                    } else {
                        this.zoomMatrix.getValues(this.matrixValues);
                        float f2 = this.matrixValues[5];
                        this.zoomMatrix.getValues(this.matrixValues);
                        animatePan(0.0f, f2, 0.0f, 0.0f, Float.valueOf(Math.abs(this.matrixValues[5]) / (this.viewHeight / 3.0f)));
                    }
                }
                parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(z);
                }
                gestureDetector = this.tapDetector;
                if (gestureDetector == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tapDetector");
                    gestureDetector = null;
                }
                if (!gestureDetector.onTouchEvent(motionEvent)) {
                    ScaleGestureDetector scaleGestureDetector3 = this.scaleDetector;
                    if (scaleGestureDetector3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("scaleDetector");
                    } else {
                        scaleGestureDetector = scaleGestureDetector3;
                    }
                    scaleGestureDetector.onTouchEvent(motionEvent);
                }
                return true;
            }
        }
        z = true;
        if (motionEvent != null) {
            z2 = true;
        }
        if (z2) {
            this.zoomMatrix.getValues(this.matrixValues);
            if (Math.abs(this.matrixValues[5]) <= this.viewHeight / 3.0f) {
            }
        }
        parent = getParent();
        if (parent != null) {
        }
        gestureDetector = this.tapDetector;
        if (gestureDetector == null) {
        }
        if (!gestureDetector.onTouchEvent(motionEvent)) {
        }
        return true;
    }

    public final void resetZoom() {
        Drawable drawable;
        Drawable drawable2 = getDrawable();
        this.baseMatrix.setRectToRect(new RectF(0.0f, 0.0f, drawable2 != null ? drawable2.getIntrinsicWidth() : 0, getDrawable() != null ? drawable.getIntrinsicHeight() : 0), new RectF(0.0f, 0.0f, this.viewWidth, this.viewHeight), Matrix.ScaleToFit.CENTER);
        setScaleAbsolute(1.0f, this.viewWidth / 2.0f, this.viewHeight / 2.0f);
        setImageMatrix(this.baseMatrix);
    }

    public final void setCurrentZoom(float f2) {
        this.zoomMatrix.getValues(this.matrixValues);
        this.oldScale = this.matrixValues[0];
        setScaleAbsolute(f2, this.viewWidth / 2.0f, this.viewHeight / 2.0f);
    }

    public final void setDebugInfoVisible(boolean z) {
        this.debugInfoVisible = z;
    }

    public final void setDisallowPagingWhenZoomed(boolean z) {
        this.disallowPagingWhenZoomed = z;
    }

    public final void setDismissProgressListener(@NotNull Function1<? super Float, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.dismissProgressListener = function1;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable != null) {
            this.onDrawableLoaded.invoke();
            resetZoom();
            this.zoomMatrix.set(getImageMatrix());
        }
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public final void setOnDismiss(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onDismiss = function0;
    }

    public final void setOnDrawableLoaded(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onDrawableLoaded = function0;
    }

    @Override // android.view.View
    public void setOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public final void setSwipeToDismissEnabled(boolean z) {
        this.swipeToDismissEnabled = z;
    }
}
