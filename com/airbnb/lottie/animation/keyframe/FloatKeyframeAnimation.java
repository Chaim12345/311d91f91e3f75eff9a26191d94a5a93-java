package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;
/* loaded from: classes.dex */
public class FloatKeyframeAnimation extends KeyframeAnimation<Float> {
    public FloatKeyframeAnimation(List<Keyframe<Float>> list) {
        super(list);
    }

    float f(Keyframe keyframe, float f2) {
        Float f3;
        if (keyframe.startValue == 0 || keyframe.endValue == 0) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.f4425c;
        return (lottieValueCallback == null || (f3 = (Float) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), keyframe.startValue, keyframe.endValue, f2, d(), getProgress())) == null) ? MiscUtils.lerp(keyframe.getStartValueFloat(), keyframe.getEndValueFloat(), f2) : f3.floatValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: g */
    public Float getValue(Keyframe keyframe, float f2) {
        return Float.valueOf(f(keyframe, f2));
    }

    public float getFloatValue() {
        return f(a(), c());
    }
}
