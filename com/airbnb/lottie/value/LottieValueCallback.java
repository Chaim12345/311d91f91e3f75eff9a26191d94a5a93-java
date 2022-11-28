package com.airbnb.lottie.value;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
/* loaded from: classes.dex */
public class LottieValueCallback<T> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    protected Object f4481a;
    @Nullable
    private BaseKeyframeAnimation<?, ?> animation;
    private final LottieFrameInfo<T> frameInfo;

    public LottieValueCallback() {
        this.frameInfo = new LottieFrameInfo<>();
        this.f4481a = null;
    }

    public LottieValueCallback(@Nullable T t2) {
        this.frameInfo = new LottieFrameInfo<>();
        this.f4481a = null;
        this.f4481a = t2;
    }

    @Nullable
    public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
        return (T) this.f4481a;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final T getValueInternal(float f2, float f3, T t2, T t3, float f4, float f5, float f6) {
        return getValue(this.frameInfo.set(f2, f3, t2, t3, f4, f5, f6));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void setAnimation(@Nullable BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.animation = baseKeyframeAnimation;
    }

    public final void setValue(@Nullable T t2) {
        this.f4481a = t2;
        BaseKeyframeAnimation<?, ?> baseKeyframeAnimation = this.animation;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.notifyListeners();
        }
    }
}
