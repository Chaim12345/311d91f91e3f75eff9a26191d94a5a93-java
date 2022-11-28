package com.rd.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.annotation.NonNull;
import com.rd.animation.ValueAnimation;
import com.rd.animation.WormAnimation;
/* loaded from: classes3.dex */
public class ThinWormAnimation extends WormAnimation {
    private static final float PERCENTAGE_HEIGHT_DURATION = 0.25f;
    private static final float PERCENTAGE_REVERSE_HEIGHT_DELAY = 0.65f;
    private static final float PERCENTAGE_SIZE_DURATION_DELAY = 0.7f;
    private int height;

    public ThinWormAnimation(@NonNull ValueAnimation.UpdateListener updateListener) {
        super(updateListener);
    }

    private ValueAnimator createHeightAnimator(int i2, int i3, long j2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(((float) this.f10806a) * PERCENTAGE_HEIGHT_DURATION);
        ofInt.setStartDelay(j2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.rd.animation.ThinWormAnimation.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ThinWormAnimation.this.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ThinWormAnimation thinWormAnimation = ThinWormAnimation.this;
                thinWormAnimation.f10807b.onThinWormAnimationUpdated(thinWormAnimation.f10820h, thinWormAnimation.f10821i, thinWormAnimation.height);
            }
        });
        return ofInt;
    }

    @Override // com.rd.animation.AbsAnimation
    public ThinWormAnimation duration(long j2) {
        super.duration(j2);
        return this;
    }

    @Override // com.rd.animation.WormAnimation, com.rd.animation.AbsAnimation
    public ThinWormAnimation progress(float f2) {
        Animator animator = this.f10808c;
        if (animator != null) {
            long j2 = f2 * ((float) this.f10806a);
            int size = ((AnimatorSet) animator).getChildAnimations().size();
            long j3 = ((float) this.f10806a) * PERCENTAGE_REVERSE_HEIGHT_DELAY;
            for (int i2 = 0; i2 < size; i2++) {
                ValueAnimator valueAnimator = (ValueAnimator) ((AnimatorSet) this.f10808c).getChildAnimations().get(i2);
                if (i2 == 3) {
                    if (j2 < j3) {
                        break;
                    }
                    j2 -= j3;
                }
                valueAnimator.setCurrentPlayTime(j2 >= valueAnimator.getDuration() ? valueAnimator.getDuration() : j2);
            }
        }
        return this;
    }

    @Override // com.rd.animation.WormAnimation
    public WormAnimation with(int i2, int i3, int i4, boolean z) {
        if (c(i2, i3, i4, z)) {
            this.f10808c = createAnimator();
            this.f10816d = i2;
            this.f10817e = i3;
            this.f10818f = i4;
            this.height = i4 * 2;
            this.f10819g = z;
            this.f10820h = i2 - i4;
            this.f10821i = i2 + i4;
            long j2 = this.f10806a;
            long j3 = ((float) j2) * PERCENTAGE_REVERSE_HEIGHT_DELAY;
            WormAnimation.AnimationValues a2 = a(z);
            ValueAnimator b2 = b(a2.f10824a, a2.f10825b, ((float) j2) * 0.7f, false);
            int i5 = this.height;
            ValueAnimator createHeightAnimator = createHeightAnimator(i5, i5 / 2, 0L);
            ValueAnimator b3 = b(a2.f10826c, a2.f10827d, j2, true);
            int i6 = this.height;
            ((AnimatorSet) this.f10808c).playTogether(b2, b3, createHeightAnimator, createHeightAnimator(i6 / 2, i6, j3));
        }
        return this;
    }
}
