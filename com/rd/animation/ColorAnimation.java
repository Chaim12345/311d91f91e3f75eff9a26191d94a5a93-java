package com.rd.animation;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import com.rd.animation.ValueAnimation;
/* loaded from: classes3.dex */
public class ColorAnimation extends AbsAnimation<ValueAnimator> {
    private static final String ANIMATION_COLOR = "ANIMATION_COLOR";
    private static final String ANIMATION_COLOR_REVERSE = "ANIMATION_COLOR_REVERSE";
    private static final int ANIMATION_DURATION = 350;
    public static final String DEFAULT_SELECTED_COLOR = "#ffffff";
    public static final String DEFAULT_UNSELECTED_COLOR = "#33ffffff";

    /* renamed from: d  reason: collision with root package name */
    protected int f10809d;

    /* renamed from: e  reason: collision with root package name */
    protected int f10810e;

    public ColorAnimation(@NonNull ValueAnimation.UpdateListener updateListener) {
        super(updateListener);
    }

    private boolean hasChanges(int i2, int i3) {
        return (this.f10809d == i2 && this.f10810e == i3) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimateUpdated(@NonNull ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_COLOR)).intValue();
        int intValue2 = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_COLOR_REVERSE)).intValue();
        ValueAnimation.UpdateListener updateListener = this.f10807b;
        if (updateListener != null) {
            updateListener.onColorAnimationUpdated(intValue, intValue2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PropertyValuesHolder b(boolean z) {
        int i2;
        int i3;
        String str;
        if (z) {
            i2 = this.f10810e;
            i3 = this.f10809d;
            str = ANIMATION_COLOR_REVERSE;
        } else {
            i2 = this.f10809d;
            i3 = this.f10810e;
            str = ANIMATION_COLOR;
        }
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(str, i2, i3);
        ofInt.setEvaluator(new ArgbEvaluator());
        return ofInt;
    }

    @Override // com.rd.animation.AbsAnimation
    @NonNull
    public ValueAnimator createAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(350L);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.rd.animation.ColorAnimation.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ColorAnimation.this.onAnimateUpdated(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    @Override // com.rd.animation.AbsAnimation
    public ColorAnimation progress(float f2) {
        Animator animator = this.f10808c;
        if (animator != null) {
            ((ValueAnimator) animator).setCurrentPlayTime(f2 * ((float) this.f10806a));
        }
        return this;
    }

    @NonNull
    public ColorAnimation with(int i2, int i3) {
        if (this.f10808c != null && hasChanges(i2, i3)) {
            this.f10809d = i2;
            this.f10810e = i3;
            ((ValueAnimator) this.f10808c).setValues(b(false), b(true));
        }
        return this;
    }
}
