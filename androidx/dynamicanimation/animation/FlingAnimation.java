package androidx.dynamicanimation.animation;

import androidx.annotation.FloatRange;
import androidx.dynamicanimation.animation.DynamicAnimation;
/* loaded from: classes.dex */
public final class FlingAnimation extends DynamicAnimation<FlingAnimation> {
    private final DragForce mFlingForce;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class DragForce implements Force {
        private static final float DEFAULT_FRICTION = -4.2f;
        private static final float VELOCITY_THRESHOLD_MULTIPLIER = 62.5f;
        private float mFriction = DEFAULT_FRICTION;
        private final DynamicAnimation.MassState mMassState = new DynamicAnimation.MassState();
        private float mVelocityThreshold;

        DragForce() {
        }

        float a() {
            return this.mFriction / DEFAULT_FRICTION;
        }

        void b(float f2) {
            this.mFriction = f2 * DEFAULT_FRICTION;
        }

        void c(float f2) {
            this.mVelocityThreshold = f2 * VELOCITY_THRESHOLD_MULTIPLIER;
        }

        DynamicAnimation.MassState d(float f2, float f3, long j2) {
            float f4 = (float) j2;
            this.mMassState.f2862b = (float) (f3 * Math.exp((f4 / 1000.0f) * this.mFriction));
            DynamicAnimation.MassState massState = this.mMassState;
            float f5 = this.mFriction;
            massState.f2861a = (float) ((f2 - (f3 / f5)) + ((f3 / f5) * Math.exp((f5 * f4) / 1000.0f)));
            DynamicAnimation.MassState massState2 = this.mMassState;
            if (isAtEquilibrium(massState2.f2861a, massState2.f2862b)) {
                this.mMassState.f2862b = 0.0f;
            }
            return this.mMassState;
        }

        @Override // androidx.dynamicanimation.animation.Force
        public float getAcceleration(float f2, float f3) {
            return f3 * this.mFriction;
        }

        @Override // androidx.dynamicanimation.animation.Force
        public boolean isAtEquilibrium(float f2, float f3) {
            return Math.abs(f3) < this.mVelocityThreshold;
        }
    }

    public FlingAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        DragForce dragForce = new DragForce();
        this.mFlingForce = dragForce;
        dragForce.c(a());
    }

    public <K> FlingAnimation(K k2, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k2, floatPropertyCompat);
        DragForce dragForce = new DragForce();
        this.mFlingForce = dragForce;
        dragForce.c(a());
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    void c(float f2) {
        this.mFlingForce.c(f2);
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    boolean d(long j2) {
        DynamicAnimation.MassState d2 = this.mFlingForce.d(this.f2853b, this.f2852a, j2);
        float f2 = d2.f2861a;
        this.f2853b = f2;
        float f3 = d2.f2862b;
        this.f2852a = f3;
        float f4 = this.f2859h;
        if (f2 < f4) {
            this.f2853b = f4;
            return true;
        }
        float f5 = this.f2858g;
        if (f2 <= f5) {
            return e(f2, f3);
        }
        this.f2853b = f5;
        return true;
    }

    boolean e(float f2, float f3) {
        return f2 >= this.f2858g || f2 <= this.f2859h || this.mFlingForce.isAtEquilibrium(f2, f3);
    }

    public float getFriction() {
        return this.mFlingForce.a();
    }

    public FlingAnimation setFriction(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        if (f2 > 0.0f) {
            this.mFlingForce.b(f2);
            return this;
        }
        throw new IllegalArgumentException("Friction must be positive");
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMaxValue(float f2) {
        super.setMaxValue(f2);
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMinValue(float f2) {
        super.setMinValue(f2);
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setStartVelocity(float f2) {
        super.setStartVelocity(f2);
        return this;
    }
}
