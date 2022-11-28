package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public abstract class LineRadarRenderer extends LineScatterCandleRadarRenderer {
    public LineRadarRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
    }

    private boolean clipPathSupported() {
        return Utils.getSDKInt() >= 18;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(Canvas canvas, Path path, int i2, int i3) {
        int i4 = (i2 & 16777215) | (i3 << 24);
        if (clipPathSupported()) {
            int save = canvas.save();
            canvas.clipPath(path);
            canvas.drawColor(i4);
            canvas.restoreToCount(save);
            return;
        }
        Paint.Style style = this.f5401c.getStyle();
        int color = this.f5401c.getColor();
        this.f5401c.setStyle(Paint.Style.FILL);
        this.f5401c.setColor(i4);
        canvas.drawPath(path, this.f5401c);
        this.f5401c.setColor(color);
        this.f5401c.setStyle(style);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(Canvas canvas, Path path, Drawable drawable) {
        if (!clipPathSupported()) {
            throw new RuntimeException("Fill-drawables not (yet) supported below API level 18, this code was run on API level " + Utils.getSDKInt() + ".");
        }
        int save = canvas.save();
        canvas.clipPath(path);
        drawable.setBounds((int) this.f5436a.contentLeft(), (int) this.f5436a.contentTop(), (int) this.f5436a.contentRight(), (int) this.f5436a.contentBottom());
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }
}
