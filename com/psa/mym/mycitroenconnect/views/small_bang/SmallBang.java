package com.psa.mym.mycitroenconnect.views.small_bang;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import androidx.core.view.ViewCompat;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes3.dex */
public class SmallBang extends View {
    private final long ANIMATE_DURATION;
    private float DOT_BIG_RADIUS;
    private int DOT_NUMBER;
    private float DOT_SMALL_RADIUS;
    private float MAX_CIRCLE_RADIUS;
    private float MAX_RADIUS;
    private final float P1;
    private final float P2;
    private final float P3;
    private final float RING_WIDTH;

    /* renamed from: a  reason: collision with root package name */
    int[] f10793a;

    /* renamed from: b  reason: collision with root package name */
    List f10794b;
    private int centerX;
    private int centerY;
    private Paint circlePaint;
    private final int[] mExpandInset;
    private SmallBangListener mListener;
    private float progress;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Dot {

        /* renamed from: a  reason: collision with root package name */
        int f10796a;

        /* renamed from: b  reason: collision with root package name */
        int f10797b;

        Dot() {
        }
    }

    public SmallBang(Context context) {
        super(context);
        this.f10793a = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.f10794b = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(null, 0);
    }

    public SmallBang(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10793a = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.f10794b = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(attributeSet, 0);
    }

    public SmallBang(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10793a = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.f10794b = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(attributeSet, i2);
    }

    public SmallBang(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f10793a = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.f10794b = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(attributeSet, i2);
    }

    public static SmallBang attach2Window(Activity activity) {
        SmallBang smallBang = new SmallBang(activity);
        ((ViewGroup) activity.findViewById(16908290)).addView(smallBang, new ViewGroup.LayoutParams(-1, -1));
        return smallBang;
    }

    private void bang() {
        new ValueAnimator();
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(1000L);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.psa.mym.mycitroenconnect.views.small_bang.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SmallBang.this.lambda$bang$1(valueAnimator);
            }
        });
        duration.start();
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.psa.mym.mycitroenconnect.views.small_bang.SmallBang.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (SmallBang.this.mListener != null) {
                    SmallBang.this.mListener.onAnimationEnd();
                }
            }
        });
        initDots();
    }

    private int evaluateColor(int i2, int i3, float f2) {
        if (f2 <= 0.0f) {
            return i2;
        }
        if (f2 >= 1.0f) {
            return i3;
        }
        int i4 = (i2 >> 24) & 255;
        int i5 = (i2 >> 16) & 255;
        int i6 = (i2 >> 8) & 255;
        int i7 = i2 & 255;
        return (i7 + ((int) (f2 * ((i3 & 255) - i7)))) | ((i4 + ((int) ((((i3 >> 24) & 255) - i4) * f2))) << 24) | ((i5 + ((int) ((((i3 >> 16) & 255) - i5) * f2))) << 16) | ((i6 + ((int) ((((i3 >> 8) & 255) - i6) * f2))) << 8);
    }

    private void init(AttributeSet attributeSet, int i2) {
        Paint paint = new Paint(1);
        this.circlePaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.circlePaint.setColor(ViewCompat.MEASURED_STATE_MASK);
    }

    private void initDots() {
        SecureRandom secureRandom = new SecureRandom();
        for (int i2 = 0; i2 < this.DOT_NUMBER * 2; i2++) {
            Dot dot = new Dot();
            int[] iArr = this.f10793a;
            int nextInt = secureRandom.nextInt(99999);
            int[] iArr2 = this.f10793a;
            dot.f10796a = iArr[nextInt % iArr2.length];
            dot.f10797b = iArr2[secureRandom.nextInt(99999) % this.f10793a.length];
            this.f10794b.add(dot);
        }
    }

    private void initRadius(float f2) {
        this.MAX_CIRCLE_RADIUS = f2;
        this.MAX_RADIUS = 1.1f * f2;
        float f3 = f2 * 0.07f;
        this.DOT_BIG_RADIUS = f3;
        this.DOT_SMALL_RADIUS = f3 * 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$bang$0(View view, ValueAnimator valueAnimator) {
        float animatedFraction = (valueAnimator.getAnimatedFraction() * 0.9f) + 0.1f;
        view.setScaleX(animatedFraction);
        view.setScaleY(animatedFraction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bang$1(ValueAnimator valueAnimator) {
        this.progress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void bang(View view) {
        bang(view, null);
    }

    public void bang(final View view, float f2, SmallBangListener smallBangListener) {
        if (smallBangListener != null) {
            setmListener(smallBangListener);
            this.mListener.onAnimationStart();
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        rect.offset(-iArr[0], -iArr[1]);
        int[] iArr2 = this.mExpandInset;
        rect.inset(-iArr2[0], -iArr2[1]);
        this.centerX = rect.left + (rect.width() / 2);
        this.centerY = rect.top + (rect.height() / 2);
        if (f2 == -1.0f) {
            f2 = Math.max(rect.width(), rect.height());
        }
        initRadius(f2);
        view.setScaleX(0.1f);
        view.setScaleY(0.1f);
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(500L);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.psa.mym.mycitroenconnect.views.small_bang.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SmallBang.lambda$bang$0(view, valueAnimator);
            }
        });
        duration.setInterpolator(new OvershootInterpolator(2.0f));
        duration.setStartDelay(300L);
        duration.start();
        bang();
    }

    public void bang(View view, SmallBangListener smallBangListener) {
        bang(view, -1.0f, smallBangListener);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f2 = this.progress;
        if (f2 >= 0.0f && f2 <= 0.15f) {
            float f3 = f2 * 6.6666665f;
            float f4 = f3 <= 1.0f ? f3 : 1.0f;
            int[] iArr = this.f10793a;
            int i2 = iArr[0];
            int i3 = iArr[1];
            this.circlePaint.setStyle(Paint.Style.FILL);
            this.circlePaint.setColor(evaluateColor(i2, i3, f4));
            canvas.drawCircle(this.centerX, this.centerY, this.MAX_CIRCLE_RADIUS * f4, this.circlePaint);
        } else if (f2 > 0.15f) {
            if (f2 > 0.15f && f2 <= 0.3f) {
                float f5 = (f2 - 0.15f) / 0.15f;
                float f6 = f5 >= 0.0f ? f5 : 0.0f;
                if (f6 > 1.0f) {
                    f6 = 1.0f;
                }
                this.circlePaint.setStyle(Paint.Style.STROKE);
                float f7 = this.MAX_CIRCLE_RADIUS * (1.0f - f6);
                this.circlePaint.setStrokeWidth(f7);
                canvas.drawCircle(this.centerX, this.centerY, (this.MAX_CIRCLE_RADIUS * f6) + (f7 / 2.0f), this.circlePaint);
            }
            if (this.progress >= 0.28f) {
                this.circlePaint.setStyle(Paint.Style.FILL);
                float f8 = (this.progress - 0.28f) / 0.72f;
                float f9 = this.MAX_CIRCLE_RADIUS;
                float f10 = f9 + ((this.MAX_RADIUS - f9) * f8);
                for (int i4 = 0; i4 < this.f10794b.size(); i4 += 2) {
                    Dot dot = (Dot) this.f10794b.get(i4);
                    this.circlePaint.setColor(evaluateColor(dot.f10796a, dot.f10797b, f8));
                    double d2 = f10;
                    double d3 = i4 * 2 * 3.141592653589793d;
                    float f11 = 1.0f - f8;
                    canvas.drawCircle(((float) (Math.cos(d3 / this.DOT_NUMBER) * d2)) + this.centerX, ((float) (Math.sin(d3 / this.DOT_NUMBER) * d2)) + this.centerY, this.DOT_BIG_RADIUS * f11, this.circlePaint);
                    Dot dot2 = (Dot) this.f10794b.get(i4 + 1);
                    this.circlePaint.setColor(evaluateColor(dot2.f10796a, dot2.f10797b, f8));
                    canvas.drawCircle(((float) (Math.cos((d3 / this.DOT_NUMBER) + 0.2d) * d2)) + this.centerX, ((float) (d2 * Math.sin((d3 / this.DOT_NUMBER) + 0.2d))) + this.centerY, this.DOT_SMALL_RADIUS * f11, this.circlePaint);
                }
            }
        }
    }

    public void setColors(int[] iArr) {
        this.f10793a = Arrays.copyOf(iArr, iArr.length);
    }

    public void setDotNumber(int i2) {
        this.DOT_NUMBER = i2;
    }

    public void setmListener(SmallBangListener smallBangListener) {
        this.mListener = smallBangListener;
    }
}
