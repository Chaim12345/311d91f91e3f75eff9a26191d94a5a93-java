package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public final class StateListAnimator {
    private final ArrayList<Tuple> tuples = new ArrayList<>();
    @Nullable
    private Tuple lastMatch = null;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    ValueAnimator f7384a = null;
    private final Animator.AnimatorListener animationListener = new AnimatorListenerAdapter() { // from class: com.google.android.material.internal.StateListAnimator.1
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            StateListAnimator stateListAnimator = StateListAnimator.this;
            if (stateListAnimator.f7384a == animator) {
                stateListAnimator.f7384a = null;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Tuple {

        /* renamed from: a  reason: collision with root package name */
        final int[] f7386a;

        /* renamed from: b  reason: collision with root package name */
        final ValueAnimator f7387b;

        Tuple(int[] iArr, ValueAnimator valueAnimator) {
            this.f7386a = iArr;
            this.f7387b = valueAnimator;
        }
    }

    private void cancel() {
        ValueAnimator valueAnimator = this.f7384a;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f7384a = null;
        }
    }

    private void start(@NonNull Tuple tuple) {
        ValueAnimator valueAnimator = tuple.f7387b;
        this.f7384a = valueAnimator;
        valueAnimator.start();
    }

    public void addState(int[] iArr, ValueAnimator valueAnimator) {
        Tuple tuple = new Tuple(iArr, valueAnimator);
        valueAnimator.addListener(this.animationListener);
        this.tuples.add(tuple);
    }

    public void jumpToCurrentState() {
        ValueAnimator valueAnimator = this.f7384a;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f7384a = null;
        }
    }

    public void setState(int[] iArr) {
        Tuple tuple;
        int size = this.tuples.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                tuple = null;
                break;
            }
            tuple = this.tuples.get(i2);
            if (StateSet.stateSetMatches(tuple.f7386a, iArr)) {
                break;
            }
            i2++;
        }
        Tuple tuple2 = this.lastMatch;
        if (tuple == tuple2) {
            return;
        }
        if (tuple2 != null) {
            cancel();
        }
        this.lastMatch = tuple;
        if (tuple != null) {
            start(tuple);
        }
    }
}
