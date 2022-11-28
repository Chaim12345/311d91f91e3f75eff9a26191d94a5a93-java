package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.value.LottieValueCallback;
/* loaded from: classes.dex */
public class SolidLayer extends BaseLayer {
    @Nullable
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final Layer layerModel;
    private final Paint paint;
    private final Path path;
    private final float[] points;
    private final RectF rect;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SolidLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.rect = new RectF();
        LPaint lPaint = new LPaint();
        this.paint = lPaint;
        this.points = new float[8];
        this.path = new Path();
        this.layerModel = layer;
        lPaint.setAlpha(0);
        lPaint.setStyle(Paint.Style.FILL);
        lPaint.setColor(layer.k());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t2, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t2, lottieValueCallback);
        if (t2 == LottieProperty.COLOR_FILTER) {
            this.colorFilterAnimation = lottieValueCallback == null ? null : new ValueCallbackKeyframeAnimation(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void drawLayer(Canvas canvas, Matrix matrix, int i2) {
        int alpha = Color.alpha(this.layerModel.k());
        if (alpha == 0) {
            return;
        }
        int intValue = (int) ((i2 / 255.0f) * (((alpha / 255.0f) * (this.f4436d.getOpacity() == null ? 100 : this.f4436d.getOpacity().getValue().intValue())) / 100.0f) * 255.0f);
        this.paint.setAlpha(intValue);
        BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.colorFilterAnimation;
        if (baseKeyframeAnimation != null) {
            this.paint.setColorFilter(baseKeyframeAnimation.getValue());
        }
        if (intValue > 0) {
            float[] fArr = this.points;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = this.layerModel.m();
            float[] fArr2 = this.points;
            fArr2[3] = 0.0f;
            fArr2[4] = this.layerModel.m();
            this.points[5] = this.layerModel.l();
            float[] fArr3 = this.points;
            fArr3[6] = 0.0f;
            fArr3[7] = this.layerModel.l();
            matrix.mapPoints(this.points);
            this.path.reset();
            Path path = this.path;
            float[] fArr4 = this.points;
            path.moveTo(fArr4[0], fArr4[1]);
            Path path2 = this.path;
            float[] fArr5 = this.points;
            path2.lineTo(fArr5[2], fArr5[3]);
            Path path3 = this.path;
            float[] fArr6 = this.points;
            path3.lineTo(fArr6[4], fArr6[5]);
            Path path4 = this.path;
            float[] fArr7 = this.points;
            path4.lineTo(fArr7[6], fArr7[7]);
            Path path5 = this.path;
            float[] fArr8 = this.points;
            path5.lineTo(fArr8[0], fArr8[1]);
            this.path.close();
            canvas.drawPath(this.path, this.paint);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        this.rect.set(0.0f, 0.0f, this.layerModel.m(), this.layerModel.l());
        this.f4433a.mapRect(this.rect);
        rectF.set(this.rect);
    }
}
