package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class SquareShapeRenderer implements IShapeRenderer {
    @Override // com.github.mikephil.charting.renderer.scatter.IShapeRenderer
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f2, float f3, Paint paint) {
        float f4;
        Canvas canvas2;
        float f5;
        float f6;
        float f7;
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
        float f8 = scatterShapeSize / 2.0f;
        float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius());
        float f9 = (scatterShapeSize - (convertDpToPixel * 2.0f)) / 2.0f;
        float f10 = f9 / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        if (scatterShapeSize > 0.0d) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(f9);
            float f11 = f2 - convertDpToPixel;
            float f12 = f3 - convertDpToPixel;
            float f13 = f2 + convertDpToPixel;
            float f14 = f3 + convertDpToPixel;
            canvas.drawRect(f11 - f10, f12 - f10, f13 + f10, f14 + f10, paint);
            if (scatterShapeHoleColor == 1122867) {
                return;
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(scatterShapeHoleColor);
            canvas2 = canvas;
            f4 = f11;
            f5 = f12;
            f6 = f13;
            f7 = f14;
        } else {
            paint.setStyle(Paint.Style.FILL);
            f4 = f2 - f8;
            float f15 = f2 + f8;
            float f16 = f3 + f8;
            canvas2 = canvas;
            f5 = f3 - f8;
            f6 = f15;
            f7 = f16;
        }
        canvas2.drawRect(f4, f5, f6, f7, paint);
    }
}
