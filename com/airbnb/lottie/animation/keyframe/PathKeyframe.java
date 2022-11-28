package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
/* loaded from: classes.dex */
public class PathKeyframe extends Keyframe<PointF> {
    @Nullable
    private Path path;
    private final Keyframe<PointF> pointKeyFrame;

    public PathKeyframe(LottieComposition lottieComposition, Keyframe<PointF> keyframe) {
        super(lottieComposition, keyframe.startValue, keyframe.endValue, keyframe.interpolator, keyframe.xInterpolator, keyframe.yInterpolator, keyframe.startFrame, keyframe.endFrame);
        this.pointKeyFrame = keyframe;
        createPath();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Path a() {
        return this.path;
    }

    public void createPath() {
        T t2;
        T t3;
        T t4 = this.endValue;
        boolean z = (t4 == 0 || (t3 = this.startValue) == 0 || !((PointF) t3).equals(((PointF) t4).x, ((PointF) t4).y)) ? false : true;
        T t5 = this.startValue;
        if (t5 == 0 || (t2 = this.endValue) == 0 || z) {
            return;
        }
        Keyframe<PointF> keyframe = this.pointKeyFrame;
        this.path = Utils.createPath((PointF) t5, (PointF) t2, keyframe.pathCp1, keyframe.pathCp2);
    }
}
