package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;
/* loaded from: classes.dex */
public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation<PointF, PointF> {
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    protected LottieValueCallback f4426d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    protected LottieValueCallback f4427e;
    private final PointF point;
    private final PointF pointWithCallbackValues;
    private final BaseKeyframeAnimation<Float, Float> xAnimation;
    private final BaseKeyframeAnimation<Float, Float> yAnimation;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation, BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2) {
        super(Collections.emptyList());
        this.point = new PointF();
        this.pointWithCallbackValues = new PointF();
        this.xAnimation = baseKeyframeAnimation;
        this.yAnimation = baseKeyframeAnimation2;
        setProgress(getProgress());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue() {
        return getValue((Keyframe) null, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe keyframe, float f2) {
        Float f3;
        Keyframe a2;
        Keyframe a3;
        Float f4 = null;
        if (this.f4426d == null || (a3 = this.xAnimation.a()) == null) {
            f3 = null;
        } else {
            float c2 = this.xAnimation.c();
            Float f5 = a3.endFrame;
            LottieValueCallback lottieValueCallback = this.f4426d;
            float f6 = a3.startFrame;
            f3 = (Float) lottieValueCallback.getValueInternal(f6, f5 == null ? f6 : f5.floatValue(), a3.startValue, a3.endValue, f2, f2, c2);
        }
        if (this.f4427e != null && (a2 = this.yAnimation.a()) != null) {
            float c3 = this.yAnimation.c();
            Float f7 = a2.endFrame;
            LottieValueCallback lottieValueCallback2 = this.f4427e;
            float f8 = a2.startFrame;
            f4 = (Float) lottieValueCallback2.getValueInternal(f8, f7 == null ? f8 : f7.floatValue(), a2.startValue, a2.endValue, f2, f2, c3);
        }
        if (f3 == null) {
            this.pointWithCallbackValues.set(this.point.x, 0.0f);
        } else {
            this.pointWithCallbackValues.set(f3.floatValue(), 0.0f);
        }
        PointF pointF = this.pointWithCallbackValues;
        pointF.set(pointF.x, f4 == null ? this.point.y : f4.floatValue());
        return this.pointWithCallbackValues;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void setProgress(float f2) {
        this.xAnimation.setProgress(f2);
        this.yAnimation.setProgress(f2);
        this.point.set(this.xAnimation.getValue().floatValue(), this.yAnimation.getValue().floatValue());
        for (int i2 = 0; i2 < this.f4423a.size(); i2++) {
            ((BaseKeyframeAnimation.AnimationListener) this.f4423a.get(i2)).onValueChanged();
        }
    }

    public void setXValueCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.f4426d;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation(null);
        }
        this.f4426d = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public void setYValueCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.f4427e;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation(null);
        }
        this.f4427e = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }
}
