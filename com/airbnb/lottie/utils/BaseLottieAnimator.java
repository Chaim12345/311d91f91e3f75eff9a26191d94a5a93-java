package com.airbnb.lottie.utils;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Build;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public abstract class BaseLottieAnimator extends ValueAnimator {
    private final Set<ValueAnimator.AnimatorUpdateListener> updateListeners = new CopyOnWriteArraySet();
    private final Set<Animator.AnimatorListener> listeners = new CopyOnWriteArraySet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        for (Animator.AnimatorListener animatorListener : this.listeners) {
            animatorListener.onAnimationCancel(this);
        }
    }

    @Override // android.animation.Animator
    public void addListener(Animator.AnimatorListener animatorListener) {
        this.listeners.add(animatorListener);
    }

    @Override // android.animation.ValueAnimator
    public void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.updateListeners.add(animatorUpdateListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        for (Animator.AnimatorListener animatorListener : this.listeners) {
            if (Build.VERSION.SDK_INT >= 26) {
                animatorListener.onAnimationEnd(this, z);
            } else {
                animatorListener.onAnimationEnd(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        for (Animator.AnimatorListener animatorListener : this.listeners) {
            animatorListener.onAnimationRepeat(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(boolean z) {
        for (Animator.AnimatorListener animatorListener : this.listeners) {
            if (Build.VERSION.SDK_INT >= 26) {
                animatorListener.onAnimationStart(this, z);
            } else {
                animatorListener.onAnimationStart(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        for (ValueAnimator.AnimatorUpdateListener animatorUpdateListener : this.updateListeners) {
            animatorUpdateListener.onAnimationUpdate(this);
        }
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    @Override // android.animation.Animator
    public void removeAllListeners() {
        this.listeners.clear();
    }

    @Override // android.animation.ValueAnimator
    public void removeAllUpdateListeners() {
        this.updateListeners.clear();
    }

    @Override // android.animation.Animator
    public void removeListener(Animator.AnimatorListener animatorListener) {
        this.listeners.remove(animatorListener);
    }

    @Override // android.animation.ValueAnimator
    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.updateListeners.remove(animatorUpdateListener);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public ValueAnimator setDuration(long j2) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void setInterpolator(TimeInterpolator timeInterpolator) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void setStartDelay(long j2) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }
}
