package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;
/* loaded from: classes.dex */
public class PointKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PointF point;

    public PointKeyframeAnimation(List<Keyframe<PointF>> list) {
        super(list);
        this.point = new PointF();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: f */
    public PointF e(Keyframe keyframe, float f2, float f3, float f4) {
        T t2;
        PointF pointF;
        T t3 = keyframe.startValue;
        if (t3 == 0 || (t2 = keyframe.endValue) == 0) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF2 = (PointF) t3;
        PointF pointF3 = (PointF) t2;
        LottieValueCallback lottieValueCallback = this.f4425c;
        if (lottieValueCallback == null || (pointF = (PointF) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), pointF2, pointF3, f2, d(), getProgress())) == null) {
            PointF pointF4 = this.point;
            float f5 = pointF2.x;
            float f6 = f5 + (f3 * (pointF3.x - f5));
            float f7 = pointF2.y;
            pointF4.set(f6, f7 + (f4 * (pointF3.y - f7)));
            return this.point;
        }
        return pointF;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe<PointF> keyframe, float f2) {
        return e(keyframe, f2, f2, f2);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f2) {
        return getValue((Keyframe<PointF>) keyframe, f2);
    }
}
