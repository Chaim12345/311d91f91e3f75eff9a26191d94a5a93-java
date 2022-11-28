package com.rd.animation;

import android.animation.IntEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import com.rd.animation.ValueAnimation;
/* loaded from: classes3.dex */
public class FillAnimation extends ColorAnimation {
    private static final String ANIMATION_COLOR = "ANIMATION_COLOR";
    private static final String ANIMATION_COLOR_REVERSE = "ANIMATION_COLOR_REVERSE";
    private static final int ANIMATION_DURATION = 350;
    private static final String ANIMATION_RADIUS = "ANIMATION_RADIUS";
    private static final String ANIMATION_RADIUS_REVERSE = "ANIMATION_RADIUS_REVERSE";
    private static final String ANIMATION_STROKE = "ANIMATION_STROKE";
    private static final String ANIMATION_STROKE_REVERSE = "ANIMATION_STROKE_REVERSE";
    public static final int DEFAULT_STROKE_DP = 1;
    private int radiusPx;
    private int strokePx;

    public FillAnimation(@NonNull ValueAnimation.UpdateListener updateListener) {
        super(updateListener);
    }

    @NonNull
    private PropertyValuesHolder createRadiusPropertyHolder(boolean z) {
        int i2;
        int i3;
        String str;
        if (z) {
            i3 = this.radiusPx;
            i2 = i3 / 2;
            str = ANIMATION_RADIUS_REVERSE;
        } else {
            i2 = this.radiusPx;
            i3 = i2 / 2;
            str = ANIMATION_RADIUS;
        }
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(str, i2, i3);
        ofInt.setEvaluator(new IntEvaluator());
        return ofInt;
    }

    @NonNull
    private PropertyValuesHolder createStrokePropertyHolder(boolean z) {
        String str;
        int i2;
        int i3;
        if (z) {
            i3 = this.radiusPx;
            str = ANIMATION_STROKE_REVERSE;
            i2 = 0;
        } else {
            int i4 = this.radiusPx;
            str = ANIMATION_STROKE;
            i2 = i4;
            i3 = 0;
        }
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(str, i3, i2);
        ofInt.setEvaluator(new IntEvaluator());
        return ofInt;
    }

    private boolean hasChanges(int i2, int i3, int i4, int i5) {
        return (this.f10809d == i2 && this.f10810e == i3 && this.radiusPx == i4 && this.strokePx == i5) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimateUpdated(@NonNull ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_COLOR)).intValue();
        int intValue2 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_COLOR_REVERSE)).intValue();
        int intValue3 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_RADIUS)).intValue();
        int intValue4 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_RADIUS_REVERSE)).intValue();
        int intValue5 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_STROKE)).intValue();
        int intValue6 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_STROKE_REVERSE)).intValue();
        ValueAnimation.UpdateListener updateListener = this.f10807b;
        if (updateListener != null) {
            updateListener.onFillAnimationUpdated(intValue, intValue2, intValue3, intValue4, intValue5, intValue6);
        }
    }

    @Override // com.rd.animation.ColorAnimation, com.rd.animation.AbsAnimation
    @NonNull
    public ValueAnimator createAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(350L);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.rd.animation.FillAnimation.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                FillAnimation.this.onAnimateUpdated(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    @NonNull
    public FillAnimation with(int i2, int i3, int i4, int i5) {
        if (this.f10808c != null && hasChanges(i2, i3, i4, i5)) {
            this.f10809d = i2;
            this.f10810e = i3;
            this.radiusPx = i4;
            this.strokePx = i5;
            ((ValueAnimator) this.f10808c).setValues(b(false), b(true), createRadiusPropertyHolder(false), createRadiusPropertyHolder(true), createStrokePropertyHolder(false), createStrokePropertyHolder(true));
        }
        return this;
    }
}
