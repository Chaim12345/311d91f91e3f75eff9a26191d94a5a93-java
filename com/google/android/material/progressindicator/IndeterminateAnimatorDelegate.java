package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class IndeterminateAnimatorDelegate<T extends Animator> {

    /* renamed from: a  reason: collision with root package name */
    protected IndeterminateDrawable f7430a;

    /* renamed from: b  reason: collision with root package name */
    protected final float[] f7431b;

    /* renamed from: c  reason: collision with root package name */
    protected final int[] f7432c;

    /* JADX INFO: Access modifiers changed from: protected */
    public IndeterminateAnimatorDelegate(int i2) {
        this.f7431b = new float[i2 * 2];
        this.f7432c = new int[i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float a(int i2, int i3, int i4) {
        return (i2 - i3) / i4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(@NonNull IndeterminateDrawable indeterminateDrawable) {
        this.f7430a = indeterminateDrawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void cancelAnimatorImmediately();

    public abstract void invalidateSpecValues();

    public abstract void registerAnimatorsCompleteCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void requestCancelAnimatorAfterCurrentCycle();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();
}
