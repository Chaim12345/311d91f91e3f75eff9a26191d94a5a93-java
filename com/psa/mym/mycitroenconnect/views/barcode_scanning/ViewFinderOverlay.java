package com.psa.mym.mycitroenconnect.views.barcode_scanning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ViewFinderOverlay extends View {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    private final float boxCornerRadius;
    @NotNull
    private final Paint boxPaint;
    @Nullable
    private RectF boxRect;
    @NotNull
    private final Paint eraserPaint;
    @NotNull
    private final Paint scrimPaint;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewFinderOverlay(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(context.getResources().getDimensionPixelOffset(R.dimen.margin4));
        this.boxPaint = paint;
        Paint paint2 = new Paint();
        paint2.setColor(ContextCompat.getColor(context, R.color.barcode_reticle_background));
        this.scrimPaint = paint2;
        Paint paint3 = new Paint();
        paint3.setStrokeWidth(paint.getStrokeWidth());
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.eraserPaint = paint3;
        this.boxCornerRadius = context.getResources().getDimensionPixelOffset(R.dimen.margin8);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // android.view.View
    @SuppressLint({"CanvasSize"})
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.draw(canvas);
        RectF rectF = this.boxRect;
        if (rectF != null) {
            canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.scrimPaint);
            this.eraserPaint.setStyle(Paint.Style.FILL);
            float f2 = this.boxCornerRadius;
            canvas.drawRoundRect(rectF, f2, f2, this.eraserPaint);
            this.eraserPaint.setStyle(Paint.Style.STROKE);
            float f3 = this.boxCornerRadius;
            canvas.drawRoundRect(rectF, f3, f3, this.eraserPaint);
            float f4 = this.boxCornerRadius;
            canvas.drawRoundRect(rectF, f4, f4, this.boxPaint);
        }
    }

    public final void setViewFinder() {
        float width = getWidth();
        float height = getHeight();
        float f2 = 100;
        float f3 = (80 * width) / f2;
        float f4 = (36 * height) / f2;
        float f5 = 2;
        float f6 = width / f5;
        float f7 = height / f5;
        float f8 = f3 / f5;
        float f9 = f4 / f5;
        this.boxRect = new RectF(f6 - f8, f7 - f9, f6 + f8, f7 + f9);
        invalidate();
    }
}
