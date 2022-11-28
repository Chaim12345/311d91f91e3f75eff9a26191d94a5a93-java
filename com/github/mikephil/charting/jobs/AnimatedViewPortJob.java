package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public abstract class AnimatedViewPortJob extends ViewPortJob implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

    /* renamed from: h  reason: collision with root package name */
    protected ObjectAnimator f5355h;

    /* renamed from: i  reason: collision with root package name */
    protected float f5356i;

    /* renamed from: j  reason: collision with root package name */
    protected float f5357j;

    /* renamed from: k  reason: collision with root package name */
    protected float f5358k;

    public AnimatedViewPortJob(ViewPortHandler viewPortHandler, float f2, float f3, Transformer transformer, View view, float f4, float f5, long j2) {
        super(viewPortHandler, f2, f3, transformer, view);
        this.f5357j = f4;
        this.f5358k = f5;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, TypedValues.Cycle.S_WAVE_PHASE, 0.0f, 1.0f);
        this.f5355h = ofFloat;
        ofFloat.setDuration(j2);
        this.f5355h.addUpdateListener(this);
        this.f5355h.addListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        this.f5355h.removeAllListeners();
        this.f5355h.removeAllUpdateListeners();
        this.f5355h.reverse();
        this.f5355h.addUpdateListener(this);
        this.f5355h.addListener(this);
    }

    public float getPhase() {
        return this.f5356i;
    }

    public float getXOrigin() {
        return this.f5357j;
    }

    public float getYOrigin() {
        return this.f5358k;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
    }

    public abstract void recycleSelf();

    @Override // java.lang.Runnable
    @SuppressLint({"NewApi"})
    public void run() {
        this.f5355h.start();
    }

    public void setPhase(float f2) {
        this.f5356i = f2;
    }
}
