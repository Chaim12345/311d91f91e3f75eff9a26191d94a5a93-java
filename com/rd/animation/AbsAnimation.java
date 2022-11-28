package com.rd.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import androidx.annotation.NonNull;
import com.rd.animation.ValueAnimation;
/* loaded from: classes3.dex */
public abstract class AbsAnimation<T extends Animator> {
    public static final int DEFAULT_ANIMATION_TIME = 350;

    /* renamed from: b  reason: collision with root package name */
    protected ValueAnimation.UpdateListener f10807b;

    /* renamed from: a  reason: collision with root package name */
    protected long f10806a = 350;

    /* renamed from: c  reason: collision with root package name */
    protected Animator f10808c = createAnimator();

    public AbsAnimation(@NonNull ValueAnimation.UpdateListener updateListener) {
        this.f10807b = updateListener;
    }

    @NonNull
    public abstract T createAnimator();

    public AbsAnimation duration(long j2) {
        this.f10806a = j2;
        Animator animator = this.f10808c;
        if (animator instanceof AnimatorSet) {
            j2 /= 2;
        }
        animator.setDuration(j2);
        return this;
    }

    public void end() {
        Animator animator = this.f10808c;
        if (animator == null || !animator.isStarted()) {
            return;
        }
        this.f10808c.end();
    }

    public abstract AbsAnimation progress(float f2);

    public void start() {
        Animator animator = this.f10808c;
        if (animator == null || animator.isRunning()) {
            return;
        }
        this.f10808c.start();
    }
}
