package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import androidx.dynamicanimation.animation.DynamicAnimation;
/* loaded from: classes.dex */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    private static final float UNSET = Float.MAX_VALUE;
    private boolean mEndRequested;
    private float mPendingPosition;
    private SpringForce mSpring;

    public SpringAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        this.mSpring = null;
        this.mPendingPosition = Float.MAX_VALUE;
        this.mEndRequested = false;
    }

    public <K> SpringAnimation(K k2, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k2, floatPropertyCompat);
        this.mSpring = null;
        this.mPendingPosition = Float.MAX_VALUE;
        this.mEndRequested = false;
    }

    public <K> SpringAnimation(K k2, FloatPropertyCompat<K> floatPropertyCompat, float f2) {
        super(k2, floatPropertyCompat);
        this.mSpring = null;
        this.mPendingPosition = Float.MAX_VALUE;
        this.mEndRequested = false;
        this.mSpring = new SpringForce(f2);
    }

    private void sanityCheck() {
        SpringForce springForce = this.mSpring;
        if (springForce == null) {
            throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
        }
        double finalPosition = springForce.getFinalPosition();
        if (finalPosition > this.f2858g) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        if (finalPosition < this.f2859h) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
    }

    public void animateToFinalPosition(float f2) {
        if (isRunning()) {
            this.mPendingPosition = f2;
            return;
        }
        if (this.mSpring == null) {
            this.mSpring = new SpringForce(f2);
        }
        this.mSpring.setFinalPosition(f2);
        start();
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    void c(float f2) {
    }

    public boolean canSkipToEnd() {
        return this.mSpring.f2865b > 0.0d;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    boolean d(long j2) {
        SpringForce springForce;
        double d2;
        double d3;
        long j3;
        if (this.mEndRequested) {
            float f2 = this.mPendingPosition;
            if (f2 != Float.MAX_VALUE) {
                this.mSpring.setFinalPosition(f2);
                this.mPendingPosition = Float.MAX_VALUE;
            }
            this.f2853b = this.mSpring.getFinalPosition();
            this.f2852a = 0.0f;
            this.mEndRequested = false;
            return true;
        }
        if (this.mPendingPosition != Float.MAX_VALUE) {
            this.mSpring.getFinalPosition();
            j3 = j2 / 2;
            DynamicAnimation.MassState b2 = this.mSpring.b(this.f2853b, this.f2852a, j3);
            this.mSpring.setFinalPosition(this.mPendingPosition);
            this.mPendingPosition = Float.MAX_VALUE;
            springForce = this.mSpring;
            d2 = b2.f2861a;
            d3 = b2.f2862b;
        } else {
            springForce = this.mSpring;
            d2 = this.f2853b;
            d3 = this.f2852a;
            j3 = j2;
        }
        DynamicAnimation.MassState b3 = springForce.b(d2, d3, j3);
        this.f2853b = b3.f2861a;
        this.f2852a = b3.f2862b;
        float max = Math.max(this.f2853b, this.f2859h);
        this.f2853b = max;
        float min = Math.min(max, this.f2858g);
        this.f2853b = min;
        if (e(min, this.f2852a)) {
            this.f2853b = this.mSpring.getFinalPosition();
            this.f2852a = 0.0f;
            return true;
        }
        return false;
    }

    boolean e(float f2, float f3) {
        return this.mSpring.isAtEquilibrium(f2, f3);
    }

    public SpringForce getSpring() {
        return this.mSpring;
    }

    public SpringAnimation setSpring(SpringForce springForce) {
        this.mSpring = springForce;
        return this;
    }

    public void skipToEnd() {
        if (!canSkipToEnd()) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (this.f2857f) {
            this.mEndRequested = true;
        }
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void start() {
        sanityCheck();
        this.mSpring.a(a());
        super.start();
    }
}
