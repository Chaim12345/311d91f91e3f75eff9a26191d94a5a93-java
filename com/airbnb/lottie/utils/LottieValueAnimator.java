package com.airbnb.lottie.utils;

import android.view.Choreographer;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
/* loaded from: classes.dex */
public class LottieValueAnimator extends BaseLottieAnimator implements Choreographer.FrameCallback {
    @Nullable
    private LottieComposition composition;
    private float speed = 1.0f;
    private boolean speedReversedForRepeatMode = false;
    private long lastFrameTimeNs = 0;
    private float frame = 0.0f;
    private int repeatCount = 0;
    private float minFrame = -2.14748365E9f;
    private float maxFrame = 2.14748365E9f;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    protected boolean f4479a = false;

    private float getFrameDurationNs() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / lottieComposition.getFrameRate()) / Math.abs(this.speed);
    }

    private boolean isReversed() {
        return getSpeed() < 0.0f;
    }

    private void verifyFrame() {
        if (this.composition == null) {
            return;
        }
        float f2 = this.frame;
        if (f2 < this.minFrame || f2 > this.maxFrame) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.minFrame), Float.valueOf(this.maxFrame), Float.valueOf(this.frame)));
        }
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    @MainThread
    public void cancel() {
        a();
        g();
    }

    public void clearComposition() {
        this.composition = null;
        this.minFrame = -2.14748365E9f;
        this.maxFrame = 2.14748365E9f;
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j2) {
        f();
        if (this.composition == null || !isRunning()) {
            return;
        }
        L.beginSection("LottieValueAnimator#doFrame");
        long j3 = this.lastFrameTimeNs;
        float frameDurationNs = ((float) (j3 != 0 ? j2 - j3 : 0L)) / getFrameDurationNs();
        float f2 = this.frame;
        if (isReversed()) {
            frameDurationNs = -frameDurationNs;
        }
        float f3 = f2 + frameDurationNs;
        this.frame = f3;
        boolean z = !MiscUtils.contains(f3, getMinFrame(), getMaxFrame());
        this.frame = MiscUtils.clamp(this.frame, getMinFrame(), getMaxFrame());
        this.lastFrameTimeNs = j2;
        e();
        if (z) {
            if (getRepeatCount() == -1 || this.repeatCount < getRepeatCount()) {
                c();
                this.repeatCount++;
                if (getRepeatMode() == 2) {
                    this.speedReversedForRepeatMode = !this.speedReversedForRepeatMode;
                    reverseAnimationSpeed();
                } else {
                    this.frame = isReversed() ? getMaxFrame() : getMinFrame();
                }
                this.lastFrameTimeNs = j2;
            } else {
                this.frame = this.speed < 0.0f ? getMinFrame() : getMaxFrame();
                g();
                b(isReversed());
            }
        }
        verifyFrame();
        L.endSection("LottieValueAnimator#doFrame");
    }

    @MainThread
    public void endAnimation() {
        g();
        b(isReversed());
    }

    protected void f() {
        if (isRunning()) {
            h(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @MainThread
    protected void g() {
        h(true);
    }

    @Override // android.animation.ValueAnimator
    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAnimatedFraction() {
        float f2;
        float minFrame;
        if (this.composition == null) {
            return 0.0f;
        }
        if (isReversed()) {
            f2 = getMaxFrame();
            minFrame = this.frame;
        } else {
            f2 = this.frame;
            minFrame = getMinFrame();
        }
        return (f2 - minFrame) / (getMaxFrame() - getMinFrame());
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(getAnimatedValueAbsolute());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAnimatedValueAbsolute() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        return (this.frame - lottieComposition.getStartFrame()) / (this.composition.getEndFrame() - this.composition.getStartFrame());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getDuration() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0L;
        }
        return lottieComposition.getDuration();
    }

    public float getFrame() {
        return this.frame;
    }

    public float getMaxFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f2 = this.maxFrame;
        return f2 == 2.14748365E9f ? lottieComposition.getEndFrame() : f2;
    }

    public float getMinFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f2 = this.minFrame;
        return f2 == -2.14748365E9f ? lottieComposition.getStartFrame() : f2;
    }

    public float getSpeed() {
        return this.speed;
    }

    @MainThread
    protected void h(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z) {
            this.f4479a = false;
        }
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.f4479a;
    }

    @MainThread
    public void pauseAnimation() {
        g();
    }

    @MainThread
    public void playAnimation() {
        this.f4479a = true;
        d(isReversed());
        setFrame((int) (isReversed() ? getMaxFrame() : getMinFrame()));
        this.lastFrameTimeNs = 0L;
        this.repeatCount = 0;
        f();
    }

    @MainThread
    public void resumeAnimation() {
        float minFrame;
        this.f4479a = true;
        f();
        this.lastFrameTimeNs = 0L;
        if (isReversed() && getFrame() == getMinFrame()) {
            minFrame = getMaxFrame();
        } else if (isReversed() || getFrame() != getMaxFrame()) {
            return;
        } else {
            minFrame = getMinFrame();
        }
        this.frame = minFrame;
    }

    public void reverseAnimationSpeed() {
        setSpeed(-getSpeed());
    }

    public void setComposition(LottieComposition lottieComposition) {
        float startFrame;
        float endFrame;
        boolean z = this.composition == null;
        this.composition = lottieComposition;
        if (z) {
            startFrame = (int) Math.max(this.minFrame, lottieComposition.getStartFrame());
            endFrame = Math.min(this.maxFrame, lottieComposition.getEndFrame());
        } else {
            startFrame = (int) lottieComposition.getStartFrame();
            endFrame = lottieComposition.getEndFrame();
        }
        setMinAndMaxFrames(startFrame, (int) endFrame);
        float f2 = this.frame;
        this.frame = 0.0f;
        setFrame((int) f2);
        e();
    }

    public void setFrame(float f2) {
        if (this.frame == f2) {
            return;
        }
        this.frame = MiscUtils.clamp(f2, getMinFrame(), getMaxFrame());
        this.lastFrameTimeNs = 0L;
        e();
    }

    public void setMaxFrame(float f2) {
        setMinAndMaxFrames(this.minFrame, f2);
    }

    public void setMinAndMaxFrames(float f2, float f3) {
        if (f2 > f3) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(f2), Float.valueOf(f3)));
        }
        LottieComposition lottieComposition = this.composition;
        float startFrame = lottieComposition == null ? -3.4028235E38f : lottieComposition.getStartFrame();
        LottieComposition lottieComposition2 = this.composition;
        float endFrame = lottieComposition2 == null ? Float.MAX_VALUE : lottieComposition2.getEndFrame();
        this.minFrame = MiscUtils.clamp(f2, startFrame, endFrame);
        this.maxFrame = MiscUtils.clamp(f3, startFrame, endFrame);
        setFrame((int) MiscUtils.clamp(this.frame, f2, f3));
    }

    public void setMinFrame(int i2) {
        setMinAndMaxFrames(i2, (int) this.maxFrame);
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int i2) {
        super.setRepeatMode(i2);
        if (i2 == 2 || !this.speedReversedForRepeatMode) {
            return;
        }
        this.speedReversedForRepeatMode = false;
        reverseAnimationSpeed();
    }

    public void setSpeed(float f2) {
        this.speed = f2;
    }
}
