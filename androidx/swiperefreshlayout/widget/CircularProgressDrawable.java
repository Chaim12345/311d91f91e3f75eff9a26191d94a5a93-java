package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class CircularProgressDrawable extends Drawable implements Animatable {
    private static final int ANIMATION_DURATION = 1332;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 7.5f;
    private static final float CENTER_RADIUS_LARGE = 11.0f;
    private static final float COLOR_CHANGE_OFFSET = 0.75f;
    public static final int DEFAULT = 1;
    private static final float GROUP_FULL_ROTATION = 216.0f;
    public static final int LARGE = 0;
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float MIN_PROGRESS_ARC = 0.01f;
    private static final float RING_ROTATION = 0.20999998f;
    private static final float SHRINK_OFFSET = 0.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3.0f;

    /* renamed from: a  reason: collision with root package name */
    float f4006a;

    /* renamed from: b  reason: collision with root package name */
    boolean f4007b;
    private Animator mAnimator;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final int[] COLORS = {ViewCompat.MEASURED_STATE_MASK};

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ProgressDrawableSize {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Ring {

        /* renamed from: a  reason: collision with root package name */
        final RectF f4012a = new RectF();

        /* renamed from: b  reason: collision with root package name */
        final Paint f4013b;

        /* renamed from: c  reason: collision with root package name */
        final Paint f4014c;

        /* renamed from: d  reason: collision with root package name */
        final Paint f4015d;

        /* renamed from: e  reason: collision with root package name */
        float f4016e;

        /* renamed from: f  reason: collision with root package name */
        float f4017f;

        /* renamed from: g  reason: collision with root package name */
        float f4018g;

        /* renamed from: h  reason: collision with root package name */
        float f4019h;

        /* renamed from: i  reason: collision with root package name */
        int[] f4020i;

        /* renamed from: j  reason: collision with root package name */
        int f4021j;

        /* renamed from: k  reason: collision with root package name */
        float f4022k;

        /* renamed from: l  reason: collision with root package name */
        float f4023l;

        /* renamed from: m  reason: collision with root package name */
        float f4024m;

        /* renamed from: n  reason: collision with root package name */
        boolean f4025n;

        /* renamed from: o  reason: collision with root package name */
        Path f4026o;

        /* renamed from: p  reason: collision with root package name */
        float f4027p;

        /* renamed from: q  reason: collision with root package name */
        float f4028q;

        /* renamed from: r  reason: collision with root package name */
        int f4029r;

        /* renamed from: s  reason: collision with root package name */
        int f4030s;

        /* renamed from: t  reason: collision with root package name */
        int f4031t;
        int u;

        Ring() {
            Paint paint = new Paint();
            this.f4013b = paint;
            Paint paint2 = new Paint();
            this.f4014c = paint2;
            Paint paint3 = new Paint();
            this.f4015d = paint3;
            this.f4016e = 0.0f;
            this.f4017f = 0.0f;
            this.f4018g = 0.0f;
            this.f4019h = 5.0f;
            this.f4027p = 1.0f;
            this.f4031t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        void A(int i2) {
            this.f4015d.setColor(i2);
        }

        void B(float f2) {
            this.f4028q = f2;
        }

        void C(int i2) {
            this.u = i2;
        }

        void D(ColorFilter colorFilter) {
            this.f4013b.setColorFilter(colorFilter);
        }

        void E(int i2) {
            this.f4021j = i2;
            this.u = this.f4020i[i2];
        }

        void F(@NonNull int[] iArr) {
            this.f4020i = iArr;
            E(0);
        }

        void G(float f2) {
            this.f4017f = f2;
        }

        void H(float f2) {
            this.f4018g = f2;
        }

        void I(boolean z) {
            if (this.f4025n != z) {
                this.f4025n = z;
            }
        }

        void J(float f2) {
            this.f4016e = f2;
        }

        void K(Paint.Cap cap) {
            this.f4013b.setStrokeCap(cap);
        }

        void L(float f2) {
            this.f4019h = f2;
            this.f4013b.setStrokeWidth(f2);
        }

        void M() {
            this.f4022k = this.f4016e;
            this.f4023l = this.f4017f;
            this.f4024m = this.f4018g;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f4012a;
            float f2 = this.f4028q;
            float f3 = (this.f4019h / 2.0f) + f2;
            if (f2 <= 0.0f) {
                f3 = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.f4029r * this.f4027p) / 2.0f, this.f4019h / 2.0f);
            }
            rectF.set(rect.centerX() - f3, rect.centerY() - f3, rect.centerX() + f3, rect.centerY() + f3);
            float f4 = this.f4016e;
            float f5 = this.f4018g;
            float f6 = (f4 + f5) * 360.0f;
            float f7 = ((this.f4017f + f5) * 360.0f) - f6;
            this.f4013b.setColor(this.u);
            this.f4013b.setAlpha(this.f4031t);
            float f8 = this.f4019h / 2.0f;
            rectF.inset(f8, f8);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f4015d);
            float f9 = -f8;
            rectF.inset(f9, f9);
            canvas.drawArc(rectF, f6, f7, false, this.f4013b);
            b(canvas, f6, f7, rectF);
        }

        void b(Canvas canvas, float f2, float f3, RectF rectF) {
            if (this.f4025n) {
                Path path = this.f4026o;
                if (path == null) {
                    Path path2 = new Path();
                    this.f4026o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                this.f4026o.moveTo(0.0f, 0.0f);
                this.f4026o.lineTo(this.f4029r * this.f4027p, 0.0f);
                Path path3 = this.f4026o;
                float f4 = this.f4027p;
                path3.lineTo((this.f4029r * f4) / 2.0f, this.f4030s * f4);
                this.f4026o.offset(((Math.min(rectF.width(), rectF.height()) / 2.0f) + rectF.centerX()) - ((this.f4029r * this.f4027p) / 2.0f), rectF.centerY() + (this.f4019h / 2.0f));
                this.f4026o.close();
                this.f4014c.setColor(this.u);
                this.f4014c.setAlpha(this.f4031t);
                canvas.save();
                canvas.rotate(f2 + f3, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.f4026o, this.f4014c);
                canvas.restore();
            }
        }

        int c() {
            return this.f4031t;
        }

        float d() {
            return this.f4030s;
        }

        float e() {
            return this.f4027p;
        }

        float f() {
            return this.f4029r;
        }

        int g() {
            return this.f4015d.getColor();
        }

        float h() {
            return this.f4028q;
        }

        int[] i() {
            return this.f4020i;
        }

        float j() {
            return this.f4017f;
        }

        int k() {
            return this.f4020i[l()];
        }

        int l() {
            return (this.f4021j + 1) % this.f4020i.length;
        }

        float m() {
            return this.f4018g;
        }

        boolean n() {
            return this.f4025n;
        }

        float o() {
            return this.f4016e;
        }

        int p() {
            return this.f4020i[this.f4021j];
        }

        float q() {
            return this.f4023l;
        }

        float r() {
            return this.f4024m;
        }

        float s() {
            return this.f4022k;
        }

        Paint.Cap t() {
            return this.f4013b.getStrokeCap();
        }

        float u() {
            return this.f4019h;
        }

        void v() {
            E(l());
        }

        void w() {
            this.f4022k = 0.0f;
            this.f4023l = 0.0f;
            this.f4024m = 0.0f;
            J(0.0f);
            G(0.0f);
            H(0.0f);
        }

        void x(int i2) {
            this.f4031t = i2;
        }

        void y(float f2, float f3) {
            this.f4029r = (int) f2;
            this.f4030s = (int) f3;
        }

        void z(float f2) {
            if (f2 != this.f4027p) {
                this.f4027p = f2;
            }
        }
    }

    public CircularProgressDrawable(@NonNull Context context) {
        this.mResources = ((Context) Preconditions.checkNotNull(context)).getResources();
        Ring ring = new Ring();
        this.mRing = ring;
        ring.F(COLORS);
        setStrokeWidth(STROKE_WIDTH);
        setupAnimators();
    }

    private void applyFinishTranslation(float f2, Ring ring) {
        b(f2, ring);
        ring.J(ring.s() + (((ring.q() - MIN_PROGRESS_ARC) - ring.s()) * f2));
        ring.G(ring.q());
        ring.H(ring.r() + ((((float) (Math.floor(ring.r() / MAX_PROGRESS_ARC) + 1.0d)) - ring.r()) * f2));
    }

    private int evaluateColorChange(float f2, int i2, int i3) {
        int i4 = (i2 >> 24) & 255;
        int i5 = (i2 >> 16) & 255;
        int i6 = (i2 >> 8) & 255;
        int i7 = i2 & 255;
        return ((i4 + ((int) ((((i3 >> 24) & 255) - i4) * f2))) << 24) | ((i5 + ((int) ((((i3 >> 16) & 255) - i5) * f2))) << 16) | ((i6 + ((int) ((((i3 >> 8) & 255) - i6) * f2))) << 8) | (i7 + ((int) (f2 * ((i3 & 255) - i7))));
    }

    private float getRotation() {
        return this.mRotation;
    }

    private void setRotation(float f2) {
        this.mRotation = f2;
    }

    private void setSizeParameters(float f2, float f3, float f4, float f5) {
        Ring ring = this.mRing;
        float f6 = this.mResources.getDisplayMetrics().density;
        ring.L(f3 * f6);
        ring.B(f2 * f6);
        ring.E(0);
        ring.y(f4 * f6, f5 * f6);
    }

    private void setupAnimators() {
        final Ring ring = this.mRing;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.swiperefreshlayout.widget.CircularProgressDrawable.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressDrawable.this.b(floatValue, ring);
                CircularProgressDrawable.this.a(floatValue, ring, false);
                CircularProgressDrawable.this.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(LINEAR_INTERPOLATOR);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: androidx.swiperefreshlayout.widget.CircularProgressDrawable.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.this.a(1.0f, ring, true);
                ring.M();
                ring.v();
                CircularProgressDrawable circularProgressDrawable = CircularProgressDrawable.this;
                if (!circularProgressDrawable.f4007b) {
                    circularProgressDrawable.f4006a += 1.0f;
                    return;
                }
                circularProgressDrawable.f4007b = false;
                animator.cancel();
                animator.setDuration(1332L);
                animator.start();
                ring.I(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                CircularProgressDrawable.this.f4006a = 0.0f;
            }
        });
        this.mAnimator = ofFloat;
    }

    void a(float f2, Ring ring, boolean z) {
        float interpolation;
        float f3;
        if (this.f4007b) {
            applyFinishTranslation(f2, ring);
        } else if (f2 != 1.0f || z) {
            float r2 = ring.r();
            if (f2 < 0.5f) {
                interpolation = ring.s();
                f3 = (MATERIAL_INTERPOLATOR.getInterpolation(f2 / 0.5f) * 0.79f) + MIN_PROGRESS_ARC + interpolation;
            } else {
                float s2 = ring.s() + 0.79f;
                interpolation = s2 - (((1.0f - MATERIAL_INTERPOLATOR.getInterpolation((f2 - 0.5f) / 0.5f)) * 0.79f) + MIN_PROGRESS_ARC);
                f3 = s2;
            }
            float f4 = (f2 + this.f4006a) * GROUP_FULL_ROTATION;
            ring.J(interpolation);
            ring.G(f3);
            ring.H(r2 + (RING_ROTATION * f2));
            setRotation(f4);
        }
    }

    void b(float f2, Ring ring) {
        ring.C(f2 > 0.75f ? evaluateColorChange((f2 - 0.75f) / 0.25f, ring.p(), ring.k()) : ring.p());
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.a(canvas, bounds);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mRing.c();
    }

    public boolean getArrowEnabled() {
        return this.mRing.n();
    }

    public float getArrowHeight() {
        return this.mRing.d();
    }

    public float getArrowScale() {
        return this.mRing.e();
    }

    public float getArrowWidth() {
        return this.mRing.f();
    }

    public int getBackgroundColor() {
        return this.mRing.g();
    }

    public float getCenterRadius() {
        return this.mRing.h();
    }

    @NonNull
    public int[] getColorSchemeColors() {
        return this.mRing.i();
    }

    public float getEndTrim() {
        return this.mRing.j();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getProgressRotation() {
        return this.mRing.m();
    }

    public float getStartTrim() {
        return this.mRing.o();
    }

    @NonNull
    public Paint.Cap getStrokeCap() {
        return this.mRing.t();
    }

    public float getStrokeWidth() {
        return this.mRing.u();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mRing.x(i2);
        invalidateSelf();
    }

    public void setArrowDimensions(float f2, float f3) {
        this.mRing.y(f2, f3);
        invalidateSelf();
    }

    public void setArrowEnabled(boolean z) {
        this.mRing.I(z);
        invalidateSelf();
    }

    public void setArrowScale(float f2) {
        this.mRing.z(f2);
        invalidateSelf();
    }

    public void setBackgroundColor(int i2) {
        this.mRing.A(i2);
        invalidateSelf();
    }

    public void setCenterRadius(float f2) {
        this.mRing.B(f2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.D(colorFilter);
        invalidateSelf();
    }

    public void setColorSchemeColors(@NonNull int... iArr) {
        this.mRing.F(iArr);
        this.mRing.E(0);
        invalidateSelf();
    }

    public void setProgressRotation(float f2) {
        this.mRing.H(f2);
        invalidateSelf();
    }

    public void setStartEndTrim(float f2, float f3) {
        this.mRing.J(f2);
        this.mRing.G(f3);
        invalidateSelf();
    }

    public void setStrokeCap(@NonNull Paint.Cap cap) {
        this.mRing.K(cap);
        invalidateSelf();
    }

    public void setStrokeWidth(float f2) {
        this.mRing.L(f2);
        invalidateSelf();
    }

    public void setStyle(int i2) {
        float f2;
        float f3;
        float f4;
        float f5;
        if (i2 == 0) {
            f2 = CENTER_RADIUS_LARGE;
            f3 = 3.0f;
            f4 = 12.0f;
            f5 = 6.0f;
        } else {
            f2 = CENTER_RADIUS;
            f3 = STROKE_WIDTH;
            f4 = 10.0f;
            f5 = 5.0f;
        }
        setSizeParameters(f2, f3, f4, f5);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Animator animator;
        long j2;
        this.mAnimator.cancel();
        this.mRing.M();
        if (this.mRing.j() != this.mRing.o()) {
            this.f4007b = true;
            animator = this.mAnimator;
            j2 = 666;
        } else {
            this.mRing.E(0);
            this.mRing.w();
            animator = this.mAnimator;
            j2 = 1332;
        }
        animator.setDuration(j2);
        this.mAnimator.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.mAnimator.cancel();
        setRotation(0.0f);
        this.mRing.I(false);
        this.mRing.E(0);
        this.mRing.w();
        invalidateSelf();
    }
}
