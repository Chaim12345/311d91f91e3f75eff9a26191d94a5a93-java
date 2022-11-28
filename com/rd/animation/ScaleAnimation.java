package com.rd.animation;

import android.animation.IntEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import com.rd.animation.ValueAnimation;
/* loaded from: classes3.dex */
public class ScaleAnimation extends ColorAnimation {
    private static final String ANIMATION_COLOR = "ANIMATION_COLOR";
    private static final String ANIMATION_COLOR_REVERSE = "ANIMATION_COLOR_REVERSE";
    private static final int ANIMATION_DURATION = 350;
    private static final String ANIMATION_SCALE = "ANIMATION_SCALE";
    private static final String ANIMATION_SCALE_REVERSE = "ANIMATION_SCALE_REVERSE";
    public static final float DEFAULT_SCALE_FACTOR = 0.7f;
    public static final float MAX_SCALE_FACTOR = 1.0f;
    public static final float MIN_SCALE_FACTOR = 0.3f;
    private int radiusPx;
    private float scaleFactor;

    public ScaleAnimation(@NonNull ValueAnimation.UpdateListener updateListener) {
        super(updateListener);
    }

    @NonNull
    private PropertyValuesHolder createScalePropertyHolder(boolean z) {
        int i2;
        int i3;
        String str;
        if (z) {
            i3 = this.radiusPx;
            i2 = (int) (i3 * this.scaleFactor);
            str = ANIMATION_SCALE_REVERSE;
        } else {
            i2 = this.radiusPx;
            i3 = (int) (i2 * this.scaleFactor);
            str = ANIMATION_SCALE;
        }
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(str, i3, i2);
        ofInt.setEvaluator(new IntEvaluator());
        return ofInt;
    }

    private boolean hasChanges(int i2, int i3, int i4, float f2) {
        return (this.f10809d == i2 && this.f10810e == i3 && this.radiusPx == i4 && this.scaleFactor == f2) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimateUpdated(@NonNull ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_COLOR)).intValue();
        int intValue2 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_COLOR_REVERSE)).intValue();
        int intValue3 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_SCALE)).intValue();
        int intValue4 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_SCALE_REVERSE)).intValue();
        ValueAnimation.UpdateListener updateListener = this.f10807b;
        if (updateListener != null) {
            updateListener.onScaleAnimationUpdated(intValue, intValue2, intValue3, intValue4);
        }
    }

    @Override // com.rd.animation.ColorAnimation, com.rd.animation.AbsAnimation
    @NonNull
    public ValueAnimator createAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(350L);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.rd.animation.ScaleAnimation.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ScaleAnimation.this.onAnimateUpdated(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    @NonNull
    public ScaleAnimation with(int i2, int i3, int i4, float f2) {
        if (this.f10808c != null && hasChanges(i2, i3, i4, f2)) {
            this.f10809d = i2;
            this.f10810e = i3;
            this.radiusPx = i4;
            this.scaleFactor = f2;
            ((ValueAnimator) this.f10808c).setValues(b(false), b(true), createScalePropertyHolder(false), createScalePropertyHolder(true));
        }
        return this;
    }
}
