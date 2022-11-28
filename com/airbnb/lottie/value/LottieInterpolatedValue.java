package com.airbnb.lottie.value;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
/* loaded from: classes.dex */
abstract class LottieInterpolatedValue<T> extends LottieValueCallback<T> {
    private final T endValue;
    private final Interpolator interpolator;
    private final T startValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LottieInterpolatedValue(Object obj, Object obj2) {
        this(obj, obj2, new LinearInterpolator());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public LottieInterpolatedValue(Object obj, Object obj2, Interpolator interpolator) {
        this.startValue = obj;
        this.endValue = obj2;
        this.interpolator = interpolator;
    }

    abstract Object a(Object obj, Object obj2, float f2);

    @Override // com.airbnb.lottie.value.LottieValueCallback
    public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
        return (T) a(this.startValue, this.endValue, this.interpolator.getInterpolation(lottieFrameInfo.getOverallProgress()));
    }
}
