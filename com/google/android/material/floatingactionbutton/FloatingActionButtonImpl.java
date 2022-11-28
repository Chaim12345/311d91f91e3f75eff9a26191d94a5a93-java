package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class FloatingActionButtonImpl {
    private static final float HIDE_ICON_SCALE = 0.0f;
    private static final float HIDE_OPACITY = 0.0f;
    private static final float HIDE_SCALE = 0.0f;
    private static final float SHOW_ICON_SCALE = 1.0f;
    private static final float SHOW_OPACITY = 1.0f;
    private static final float SHOW_SCALE = 1.0f;

    /* renamed from: n  reason: collision with root package name */
    static final TimeInterpolator f7319n = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;

    /* renamed from: o  reason: collision with root package name */
    static final int[] f7320o = {16842919, 16842910};

    /* renamed from: p  reason: collision with root package name */
    static final int[] f7321p = {16843623, 16842908, 16842910};

    /* renamed from: q  reason: collision with root package name */
    static final int[] f7322q = {16842908, 16842910};

    /* renamed from: r  reason: collision with root package name */
    static final int[] f7323r = {16843623, 16842910};

    /* renamed from: s  reason: collision with root package name */
    static final int[] f7324s = {16842910};

    /* renamed from: t  reason: collision with root package name */
    static final int[] f7325t = new int[0];
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    ShapeAppearanceModel f7326a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    MaterialShapeDrawable f7327b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    Drawable f7328c;
    @Nullable
    private Animator currentAnimator;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    BorderDrawable f7329d;
    @Nullable
    private MotionSpec defaultHideMotionSpec;
    @Nullable
    private MotionSpec defaultShowMotionSpec;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    Drawable f7330e;

    /* renamed from: f  reason: collision with root package name */
    boolean f7331f;

    /* renamed from: h  reason: collision with root package name */
    float f7333h;
    private ArrayList<Animator.AnimatorListener> hideListeners;
    @Nullable
    private MotionSpec hideMotionSpec;

    /* renamed from: i  reason: collision with root package name */
    float f7334i;

    /* renamed from: j  reason: collision with root package name */
    float f7335j;

    /* renamed from: k  reason: collision with root package name */
    int f7336k;

    /* renamed from: l  reason: collision with root package name */
    final FloatingActionButton f7337l;

    /* renamed from: m  reason: collision with root package name */
    final ShadowViewDelegate f7338m;
    private int maxImageSize;
    @Nullable
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    private float rotation;
    private ArrayList<Animator.AnimatorListener> showListeners;
    @Nullable
    private MotionSpec showMotionSpec;
    @NonNull
    private final StateListAnimator stateListAnimator;
    private ArrayList<InternalTransformationCallback> transformationCallbacks;

    /* renamed from: g  reason: collision with root package name */
    boolean f7332g = true;
    private float imageMatrixScale = 1.0f;
    private int animState = 0;
    private final Rect tmpRect = new Rect();
    private final RectF tmpRectF1 = new RectF();
    private final RectF tmpRectF2 = new RectF();
    private final Matrix tmpMatrix = new Matrix();

    /* loaded from: classes2.dex */
    private class DisabledElevationAnimation extends ShadowAnimatorImpl {
        DisabledElevationAnimation(FloatingActionButtonImpl floatingActionButtonImpl) {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            return 0.0f;
        }
    }

    /* loaded from: classes2.dex */
    private class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl {
        ElevateToHoveredFocusedTranslationZAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.f7333h + floatingActionButtonImpl.f7334i;
        }
    }

    /* loaded from: classes2.dex */
    private class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl {
        ElevateToPressedTranslationZAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.f7333h + floatingActionButtonImpl.f7335j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface InternalTransformationCallback {
        void onScaleChanged();

        void onTranslationChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface InternalVisibilityChangedListener {
        void onHidden();

        void onShown();
    }

    /* loaded from: classes2.dex */
    private class ResetElevationAnimation extends ShadowAnimatorImpl {
        ResetElevationAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            return FloatingActionButtonImpl.this.f7333h;
        }
    }

    /* loaded from: classes2.dex */
    private abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private float shadowSizeEnd;
        private float shadowSizeStart;
        private boolean validValues;

        private ShadowAnimatorImpl() {
        }

        protected abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            FloatingActionButtonImpl.this.Z((int) this.shadowSizeEnd);
            this.validValues = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            if (!this.validValues) {
                MaterialShapeDrawable materialShapeDrawable = FloatingActionButtonImpl.this.f7327b;
                this.shadowSizeStart = materialShapeDrawable == null ? 0.0f : materialShapeDrawable.getElevation();
                this.shadowSizeEnd = a();
                this.validValues = true;
            }
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            float f2 = this.shadowSizeStart;
            floatingActionButtonImpl.Z((int) (f2 + ((this.shadowSizeEnd - f2) * valueAnimator.getAnimatedFraction())));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatingActionButtonImpl(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        this.f7337l = floatingActionButton;
        this.f7338m = shadowViewDelegate;
        StateListAnimator stateListAnimator = new StateListAnimator();
        this.stateListAnimator = stateListAnimator;
        stateListAnimator.addState(f7320o, createElevationAnimator(new ElevateToPressedTranslationZAnimation()));
        stateListAnimator.addState(f7321p, createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.addState(f7322q, createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.addState(f7323r, createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.addState(f7324s, createElevationAnimator(new ResetElevationAnimation()));
        stateListAnimator.addState(f7325t, createElevationAnimator(new DisabledElevationAnimation(this)));
        this.rotation = floatingActionButton.getRotation();
    }

    private void calculateImageMatrixFromScale(float f2, @NonNull Matrix matrix) {
        matrix.reset();
        Drawable drawable = this.f7337l.getDrawable();
        if (drawable == null || this.maxImageSize == 0) {
            return;
        }
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        rectF.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        int i2 = this.maxImageSize;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.maxImageSize;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    @NonNull
    private AnimatorSet createAnimator(@NonNull MotionSpec motionSpec, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f7337l, View.ALPHA, f2);
        motionSpec.getTiming("opacity").apply(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f7337l, View.SCALE_X, f3);
        motionSpec.getTiming("scale").apply(ofFloat2);
        workAroundOreoBug(ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f7337l, View.SCALE_Y, f3);
        motionSpec.getTiming("scale").apply(ofFloat3);
        workAroundOreoBug(ofFloat3);
        arrayList.add(ofFloat3);
        calculateImageMatrixFromScale(f4, this.tmpMatrix);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.f7337l, new ImageMatrixProperty(), new MatrixEvaluator() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.android.material.animation.MatrixEvaluator, android.animation.TypeEvaluator
            public Matrix evaluate(float f5, @NonNull Matrix matrix, @NonNull Matrix matrix2) {
                FloatingActionButtonImpl.this.imageMatrixScale = f5;
                return super.evaluate(f5, matrix, matrix2);
            }
        }, new Matrix(this.tmpMatrix));
        motionSpec.getTiming("iconScale").apply(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    @NonNull
    private ValueAnimator createElevationAnimator(@NonNull ShadowAnimatorImpl shadowAnimatorImpl) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(f7319n);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(shadowAnimatorImpl);
        valueAnimator.addUpdateListener(shadowAnimatorImpl);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    private MotionSpec getDefaultHideMotionSpec() {
        if (this.defaultHideMotionSpec == null) {
            this.defaultHideMotionSpec = MotionSpec.createFromResource(this.f7337l.getContext(), R.animator.design_fab_hide_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultHideMotionSpec);
    }

    private MotionSpec getDefaultShowMotionSpec() {
        if (this.defaultShowMotionSpec == null) {
            this.defaultShowMotionSpec = MotionSpec.createFromResource(this.f7337l.getContext(), R.animator.design_fab_show_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultShowMotionSpec);
    }

    @NonNull
    private ViewTreeObserver.OnPreDrawListener getOrCreatePreDrawListener() {
        if (this.preDrawListener == null) {
            this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.5
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    FloatingActionButtonImpl.this.z();
                    return true;
                }
            };
        }
        return this.preDrawListener;
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this.f7337l) && !this.f7337l.isInEditMode();
    }

    private void workAroundOreoBug(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new TypeEvaluator<Float>(this) { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.4

            /* renamed from: a  reason: collision with root package name */
            FloatEvaluator f7346a = new FloatEvaluator();

            @Override // android.animation.TypeEvaluator
            public Float evaluate(float f2, Float f3, Float f4) {
                float floatValue = this.f7346a.evaluate(f2, (Number) f3, (Number) f4).floatValue();
                if (floatValue < 0.1f) {
                    floatValue = 0.0f;
                }
                return Float.valueOf(floatValue);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A() {
        ArrayList<InternalTransformationCallback> arrayList = this.transformationCallbacks;
        if (arrayList != null) {
            Iterator<InternalTransformationCallback> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onScaleChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B() {
        ArrayList<InternalTransformationCallback> arrayList = this.transformationCallbacks;
        if (arrayList != null) {
            Iterator<InternalTransformationCallback> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onTranslationChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.showListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D(@NonNull InternalTransformationCallback internalTransformationCallback) {
        ArrayList<InternalTransformationCallback> arrayList = this.transformationCallbacks;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(internalTransformationCallback);
    }

    boolean E() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(@Nullable ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.f7327b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintList(colorStateList);
        }
        BorderDrawable borderDrawable = this.f7329d;
        if (borderDrawable != null) {
            borderDrawable.b(colorStateList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G(@Nullable PorterDuff.Mode mode) {
        MaterialShapeDrawable materialShapeDrawable = this.f7327b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintMode(mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void H(float f2) {
        if (this.f7333h != f2) {
            this.f7333h = f2;
            x(f2, this.f7334i, this.f7335j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(boolean z) {
        this.f7331f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void J(@Nullable MotionSpec motionSpec) {
        this.hideMotionSpec = motionSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void K(float f2) {
        if (this.f7334i != f2) {
            this.f7334i = f2;
            x(this.f7333h, f2, this.f7335j);
        }
    }

    final void L(float f2) {
        this.imageMatrixScale = f2;
        Matrix matrix = this.tmpMatrix;
        calculateImageMatrixFromScale(f2, matrix);
        this.f7337l.setImageMatrix(matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void M(int i2) {
        if (this.maxImageSize != i2) {
            this.maxImageSize = i2;
            X();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void N(int i2) {
        this.f7336k = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void O(float f2) {
        if (this.f7335j != f2) {
            this.f7335j = f2;
            x(this.f7333h, this.f7334i, f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void P(@Nullable ColorStateList colorStateList) {
        Drawable drawable = this.f7328c;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, RippleUtils.sanitizeRippleDrawableColor(colorStateList));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Q(boolean z) {
        this.f7332g = z;
        Y();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void R(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.f7326a = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.f7327b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        Drawable drawable = this.f7328c;
        if (drawable instanceof Shapeable) {
            ((Shapeable) drawable).setShapeAppearanceModel(shapeAppearanceModel);
        }
        BorderDrawable borderDrawable = this.f7329d;
        if (borderDrawable != null) {
            borderDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void S(@Nullable MotionSpec motionSpec) {
        this.showMotionSpec = motionSpec;
    }

    boolean T() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean U() {
        return !this.f7331f || this.f7337l.getSizeDimension() >= this.f7336k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void V(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (r()) {
            return;
        }
        Animator animator = this.currentAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (!shouldAnimateVisibilityChange()) {
            this.f7337l.internalSetVisibility(0, z);
            this.f7337l.setAlpha(1.0f);
            this.f7337l.setScaleY(1.0f);
            this.f7337l.setScaleX(1.0f);
            L(1.0f);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onShown();
                return;
            }
            return;
        }
        if (this.f7337l.getVisibility() != 0) {
            this.f7337l.setAlpha(0.0f);
            this.f7337l.setScaleY(0.0f);
            this.f7337l.setScaleX(0.0f);
            L(0.0f);
        }
        MotionSpec motionSpec = this.showMotionSpec;
        if (motionSpec == null) {
            motionSpec = getDefaultShowMotionSpec();
        }
        AnimatorSet createAnimator = createAnimator(motionSpec, 1.0f, 1.0f, 1.0f);
        createAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                FloatingActionButtonImpl.this.animState = 0;
                FloatingActionButtonImpl.this.currentAnimator = null;
                InternalVisibilityChangedListener internalVisibilityChangedListener2 = internalVisibilityChangedListener;
                if (internalVisibilityChangedListener2 != null) {
                    internalVisibilityChangedListener2.onShown();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                FloatingActionButtonImpl.this.f7337l.internalSetVisibility(0, z);
                FloatingActionButtonImpl.this.animState = 2;
                FloatingActionButtonImpl.this.currentAnimator = animator2;
            }
        });
        ArrayList<Animator.AnimatorListener> arrayList = this.showListeners;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                createAnimator.addListener(it.next());
            }
        }
        createAnimator.start();
    }

    void W() {
        FloatingActionButton floatingActionButton;
        int i2;
        if (Build.VERSION.SDK_INT == 19) {
            if (this.rotation % 90.0f != 0.0f) {
                i2 = 1;
                if (this.f7337l.getLayerType() != 1) {
                    floatingActionButton = this.f7337l;
                    floatingActionButton.setLayerType(i2, null);
                }
            } else if (this.f7337l.getLayerType() != 0) {
                floatingActionButton = this.f7337l;
                i2 = 0;
                floatingActionButton.setLayerType(i2, null);
            }
        }
        MaterialShapeDrawable materialShapeDrawable = this.f7327b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShadowCompatRotation((int) this.rotation);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void X() {
        L(this.imageMatrixScale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void Y() {
        Rect rect = this.tmpRect;
        k(rect);
        y(rect);
        this.f7338m.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Z(float f2) {
        MaterialShapeDrawable materialShapeDrawable = this.f7327b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setElevation(f2);
        }
    }

    public void addOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        if (this.hideListeners == null) {
            this.hideListeners = new ArrayList<>();
        }
        this.hideListeners.add(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(@NonNull Animator.AnimatorListener animatorListener) {
        if (this.showListeners == null) {
            this.showListeners = new ArrayList<>();
        }
        this.showListeners.add(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@NonNull InternalTransformationCallback internalTransformationCallback) {
        if (this.transformationCallbacks == null) {
            this.transformationCallbacks = new ArrayList<>();
        }
        this.transformationCallbacks.add(internalTransformationCallback);
    }

    MaterialShapeDrawable f() {
        return new MaterialShapeDrawable((ShapeAppearanceModel) Preconditions.checkNotNull(this.f7326a));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final Drawable g() {
        return this.f7330e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getElevation() {
        return this.f7333h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        return this.f7331f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final MotionSpec i() {
        return this.hideMotionSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float j() {
        return this.f7334i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(@NonNull Rect rect) {
        int sizeDimension = this.f7331f ? (this.f7336k - this.f7337l.getSizeDimension()) / 2 : 0;
        float elevation = this.f7332g ? getElevation() + this.f7335j : 0.0f;
        int max = Math.max(sizeDimension, (int) Math.ceil(elevation));
        int max2 = Math.max(sizeDimension, (int) Math.ceil(elevation * 1.5f));
        rect.set(max, max2, max, max2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float l() {
        return this.f7335j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final ShapeAppearanceModel m() {
        return this.f7326a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final MotionSpec n() {
        return this.showMotionSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (q()) {
            return;
        }
        Animator animator = this.currentAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (!shouldAnimateVisibilityChange()) {
            this.f7337l.internalSetVisibility(z ? 8 : 4, z);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
                return;
            }
            return;
        }
        MotionSpec motionSpec = this.hideMotionSpec;
        if (motionSpec == null) {
            motionSpec = getDefaultHideMotionSpec();
        }
        AnimatorSet createAnimator = createAnimator(motionSpec, 0.0f, 0.0f, 0.0f);
        createAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.1
            private boolean cancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                this.cancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                FloatingActionButtonImpl.this.animState = 0;
                FloatingActionButtonImpl.this.currentAnimator = null;
                if (this.cancelled) {
                    return;
                }
                FloatingActionButton floatingActionButton = FloatingActionButtonImpl.this.f7337l;
                boolean z2 = z;
                floatingActionButton.internalSetVisibility(z2 ? 8 : 4, z2);
                InternalVisibilityChangedListener internalVisibilityChangedListener2 = internalVisibilityChangedListener;
                if (internalVisibilityChangedListener2 != null) {
                    internalVisibilityChangedListener2.onHidden();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                FloatingActionButtonImpl.this.f7337l.internalSetVisibility(0, z);
                FloatingActionButtonImpl.this.animState = 1;
                FloatingActionButtonImpl.this.currentAnimator = animator2;
                this.cancelled = false;
            }
        });
        ArrayList<Animator.AnimatorListener> arrayList = this.hideListeners;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                createAnimator.addListener(it.next());
            }
        }
        createAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        MaterialShapeDrawable f2 = f();
        this.f7327b = f2;
        f2.setTintList(colorStateList);
        if (mode != null) {
            this.f7327b.setTintMode(mode);
        }
        this.f7327b.setShadowColor(-12303292);
        this.f7327b.initializeElevationOverlay(this.f7337l.getContext());
        RippleDrawableCompat rippleDrawableCompat = new RippleDrawableCompat(this.f7327b.getShapeAppearanceModel());
        rippleDrawableCompat.setTintList(RippleUtils.sanitizeRippleDrawableColor(colorStateList2));
        this.f7328c = rippleDrawableCompat;
        this.f7330e = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.checkNotNull(this.f7327b), rippleDrawableCompat});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean q() {
        return this.f7337l.getVisibility() == 0 ? this.animState == 1 : this.animState != 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r() {
        return this.f7337l.getVisibility() != 0 ? this.animState == 2 : this.animState != 1;
    }

    public void removeOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.hideListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s() {
        this.stateListAnimator.jumpToCurrentState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t() {
        MaterialShapeDrawable materialShapeDrawable = this.f7327b;
        if (materialShapeDrawable != null) {
            MaterialShapeUtils.setParentAbsoluteElevation(this.f7337l, materialShapeDrawable);
        }
        if (E()) {
            this.f7337l.getViewTreeObserver().addOnPreDrawListener(getOrCreatePreDrawListener());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v() {
        ViewTreeObserver viewTreeObserver = this.f7337l.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.preDrawListener;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.preDrawListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(int[] iArr) {
        this.stateListAnimator.setState(iArr);
    }

    void x(float f2, float f3, float f4) {
        Y();
        Z(f2);
    }

    void y(@NonNull Rect rect) {
        ShadowViewDelegate shadowViewDelegate;
        Drawable drawable;
        Preconditions.checkNotNull(this.f7330e, "Didn't initialize content background");
        if (T()) {
            drawable = new InsetDrawable(this.f7330e, rect.left, rect.top, rect.right, rect.bottom);
            shadowViewDelegate = this.f7338m;
        } else {
            shadowViewDelegate = this.f7338m;
            drawable = this.f7330e;
        }
        shadowViewDelegate.setBackgroundDrawable(drawable);
    }

    void z() {
        float rotation = this.f7337l.getRotation();
        if (this.rotation != rotation) {
            this.rotation = rotation;
            W();
        }
    }
}
