package com.rd.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.annotation.NonNull;
import com.rd.animation.ValueAnimation;
import java.util.Iterator;
/* loaded from: classes3.dex */
public class WormAnimation extends AbsAnimation<AnimatorSet> {

    /* renamed from: d  reason: collision with root package name */
    int f10816d;

    /* renamed from: e  reason: collision with root package name */
    int f10817e;

    /* renamed from: f  reason: collision with root package name */
    int f10818f;

    /* renamed from: g  reason: collision with root package name */
    boolean f10819g;

    /* renamed from: h  reason: collision with root package name */
    int f10820h;

    /* renamed from: i  reason: collision with root package name */
    int f10821i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class AnimationValues {

        /* renamed from: a  reason: collision with root package name */
        final int f10824a;

        /* renamed from: b  reason: collision with root package name */
        final int f10825b;

        /* renamed from: c  reason: collision with root package name */
        final int f10826c;

        /* renamed from: d  reason: collision with root package name */
        final int f10827d;

        AnimationValues(WormAnimation wormAnimation, int i2, int i3, int i4, int i5) {
            this.f10824a = i2;
            this.f10825b = i3;
            this.f10826c = i4;
            this.f10827d = i5;
        }
    }

    public WormAnimation(@NonNull ValueAnimation.UpdateListener updateListener) {
        super(updateListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public AnimationValues a(boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (z) {
            int i6 = this.f10816d;
            int i7 = this.f10818f;
            i2 = i6 + i7;
            int i8 = this.f10817e;
            i3 = i8 + i7;
            i4 = i6 - i7;
            i5 = i8 - i7;
        } else {
            int i9 = this.f10816d;
            int i10 = this.f10818f;
            i2 = i9 - i10;
            int i11 = this.f10817e;
            i3 = i11 - i10;
            i4 = i9 + i10;
            i5 = i11 + i10;
        }
        return new AnimationValues(this, i2, i3, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValueAnimator b(int i2, int i3, long j2, final boolean z) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.rd.animation.WormAnimation.1
            /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
                r0.f10821i = r3;
             */
            /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
                if (r0.f10819g != false) goto L9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
                if (r0.f10819g != false) goto L5;
             */
            /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
                r0.f10820h = r3;
             */
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WormAnimation wormAnimation;
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (z) {
                    wormAnimation = WormAnimation.this;
                } else {
                    wormAnimation = WormAnimation.this;
                }
                WormAnimation wormAnimation2 = WormAnimation.this;
                wormAnimation2.f10807b.onWormAnimationUpdated(wormAnimation2.f10820h, wormAnimation2.f10821i);
            }
        });
        return ofInt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(int i2, int i3, int i4, boolean z) {
        return (this.f10816d == i2 && this.f10817e == i3 && this.f10818f == i4 && this.f10819g == z) ? false : true;
    }

    @Override // com.rd.animation.AbsAnimation
    @NonNull
    public AnimatorSet createAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        return animatorSet;
    }

    @Override // com.rd.animation.AbsAnimation
    public WormAnimation progress(float f2) {
        Animator animator = this.f10808c;
        if (animator != null) {
            long j2 = f2 * ((float) this.f10806a);
            Iterator<Animator> it = ((AnimatorSet) animator).getChildAnimations().iterator();
            while (it.hasNext()) {
                ValueAnimator valueAnimator = (ValueAnimator) it.next();
                long duration = valueAnimator.getDuration();
                if (j2 < 0) {
                    j2 = 0;
                }
                if (j2 < duration) {
                    duration = j2;
                }
                valueAnimator.setCurrentPlayTime(duration);
                j2 -= duration;
            }
        }
        return this;
    }

    public WormAnimation with(int i2, int i3, int i4, boolean z) {
        if (c(i2, i3, i4, z)) {
            this.f10808c = createAnimator();
            this.f10816d = i2;
            this.f10817e = i3;
            this.f10818f = i4;
            this.f10819g = z;
            this.f10820h = i2 - i4;
            this.f10821i = i2 + i4;
            AnimationValues a2 = a(z);
            long j2 = this.f10806a / 2;
            ((AnimatorSet) this.f10808c).playSequentially(b(a2.f10824a, a2.f10825b, j2, false), b(a2.f10826c, a2.f10827d, j2, true));
        }
        return this;
    }
}
