package com.rd.animation;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import com.rd.animation.ValueAnimation;
/* loaded from: classes3.dex */
public class SlideAnimation extends AbsAnimation<ValueAnimator> {
    private static final int ANIMATION_DURATION = 350;
    private static final String ANIMATION_X_COORDINATE = "ANIMATION_X_COORDINATE";
    private int xEndCoordinate;
    private int xStartCoordinate;

    public SlideAnimation(@NonNull ValueAnimation.UpdateListener updateListener) {
        super(updateListener);
    }

    private PropertyValuesHolder createColorPropertyHolder() {
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(ANIMATION_X_COORDINATE, this.xStartCoordinate, this.xEndCoordinate);
        ofInt.setEvaluator(new IntEvaluator());
        return ofInt;
    }

    private boolean hasChanges(int i2, int i3) {
        return (this.xStartCoordinate == i2 && this.xEndCoordinate == i3) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimateUpdated(@NonNull ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue(ANIMATION_X_COORDINATE)).intValue();
        ValueAnimation.UpdateListener updateListener = this.f10807b;
        if (updateListener != null) {
            updateListener.onSlideAnimationUpdated(intValue);
        }
    }

    @Override // com.rd.animation.AbsAnimation
    @NonNull
    public ValueAnimator createAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(350L);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.rd.animation.SlideAnimation.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                SlideAnimation.this.onAnimateUpdated(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    @Override // com.rd.animation.AbsAnimation
    public SlideAnimation progress(float f2) {
        Animator animator = this.f10808c;
        if (animator != null) {
            ((ValueAnimator) animator).setCurrentPlayTime(f2 * ((float) this.f10806a));
        }
        return this;
    }

    @NonNull
    public SlideAnimation with(int i2, int i3) {
        if (this.f10808c != null && hasChanges(i2, i3)) {
            this.xStartCoordinate = i2;
            this.xEndCoordinate = i3;
            ((ValueAnimator) this.f10808c).setValues(createColorPropertyHolder());
        }
        return this;
    }
}
