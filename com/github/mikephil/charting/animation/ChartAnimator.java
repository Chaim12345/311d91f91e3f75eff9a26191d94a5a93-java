package com.github.mikephil.charting.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.github.mikephil.charting.animation.Easing;
/* loaded from: classes.dex */
public class ChartAnimator {

    /* renamed from: a  reason: collision with root package name */
    protected float f5249a = 1.0f;

    /* renamed from: b  reason: collision with root package name */
    protected float f5250b = 1.0f;
    private ValueAnimator.AnimatorUpdateListener mListener;

    public ChartAnimator() {
    }

    @RequiresApi(11)
    public ChartAnimator(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.mListener = animatorUpdateListener;
    }

    @RequiresApi(11)
    private ObjectAnimator xAnimator(int i2, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration(i2);
        return ofFloat;
    }

    @RequiresApi(11)
    private ObjectAnimator yAnimator(int i2, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration(i2);
        return ofFloat;
    }

    @RequiresApi(11)
    public void animateX(int i2) {
        animateX(i2, Easing.Linear);
    }

    @RequiresApi(11)
    public void animateX(int i2, Easing.EasingFunction easingFunction) {
        ObjectAnimator xAnimator = xAnimator(i2, easingFunction);
        xAnimator.addUpdateListener(this.mListener);
        xAnimator.start();
    }

    @Deprecated
    public void animateX(int i2, Easing.EasingOption easingOption) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
        ofFloat.setDuration(i2);
        ofFloat.addUpdateListener(this.mListener);
        ofFloat.start();
    }

    @RequiresApi(11)
    public void animateXY(int i2, int i3) {
        Easing.EasingFunction easingFunction = Easing.Linear;
        animateXY(i2, i3, easingFunction, easingFunction);
    }

    @RequiresApi(11)
    public void animateXY(int i2, int i3, Easing.EasingFunction easingFunction) {
        ObjectAnimator xAnimator = xAnimator(i2, easingFunction);
        ObjectAnimator yAnimator = yAnimator(i3, easingFunction);
        if (i2 > i3) {
            xAnimator.addUpdateListener(this.mListener);
        } else {
            yAnimator.addUpdateListener(this.mListener);
        }
        xAnimator.start();
        yAnimator.start();
    }

    @RequiresApi(11)
    public void animateXY(int i2, int i3, Easing.EasingFunction easingFunction, Easing.EasingFunction easingFunction2) {
        ObjectAnimator xAnimator = xAnimator(i2, easingFunction);
        ObjectAnimator yAnimator = yAnimator(i3, easingFunction2);
        if (i2 > i3) {
            xAnimator.addUpdateListener(this.mListener);
        } else {
            yAnimator.addUpdateListener(this.mListener);
        }
        xAnimator.start();
        yAnimator.start();
    }

    @Deprecated
    public void animateXY(int i2, int i3, Easing.EasingOption easingOption, Easing.EasingOption easingOption2) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption2));
        ofFloat.setDuration(i3);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        ofFloat2.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
        ofFloat2.setDuration(i2);
        if (i2 > i3) {
            ofFloat2.addUpdateListener(this.mListener);
        } else {
            ofFloat.addUpdateListener(this.mListener);
        }
        ofFloat2.start();
        ofFloat.start();
    }

    @RequiresApi(11)
    public void animateY(int i2) {
        animateY(i2, Easing.Linear);
    }

    @RequiresApi(11)
    public void animateY(int i2, Easing.EasingFunction easingFunction) {
        ObjectAnimator yAnimator = yAnimator(i2, easingFunction);
        yAnimator.addUpdateListener(this.mListener);
        yAnimator.start();
    }

    @Deprecated
    public void animateY(int i2, Easing.EasingOption easingOption) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
        ofFloat.setDuration(i2);
        ofFloat.addUpdateListener(this.mListener);
        ofFloat.start();
    }

    public float getPhaseX() {
        return this.f5250b;
    }

    public float getPhaseY() {
        return this.f5249a;
    }

    public void setPhaseX(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.f5250b = f2;
    }

    public void setPhaseY(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.f5249a = f2;
    }
}
