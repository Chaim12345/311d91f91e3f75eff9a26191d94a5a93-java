package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.color.MaterialColors;
/* loaded from: classes2.dex */
final class CircularDrawingDelegate extends DrawingDelegate<CircularProgressIndicatorSpec> {
    private float adjustedRadius;
    private int arcDirectionFactor;
    private float displayedCornerRadius;
    private float displayedTrackThickness;

    public CircularDrawingDelegate(@NonNull CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
        this.arcDirectionFactor = 1;
    }

    private void drawRoundedEnd(Canvas canvas, Paint paint, float f2, float f3, float f4, boolean z, RectF rectF) {
        float f5 = z ? -1.0f : 1.0f;
        canvas.save();
        canvas.rotate(f4);
        float f6 = f2 / 2.0f;
        float f7 = f5 * f3;
        canvas.drawRect((this.adjustedRadius - f6) + f3, Math.min(0.0f, this.arcDirectionFactor * f7), (this.adjustedRadius + f6) - f3, Math.max(0.0f, f7 * this.arcDirectionFactor), paint);
        canvas.translate((this.adjustedRadius - f6) + f3, 0.0f);
        canvas.drawArc(rectF, 180.0f, (-f5) * 90.0f * this.arcDirectionFactor, true, paint);
        canvas.translate(f2 - (f3 * 2.0f), 0.0f);
        canvas.drawArc(rectF, 0.0f, f5 * 90.0f * this.arcDirectionFactor, true, paint);
        canvas.restore();
    }

    private int getSize() {
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f7428a;
        return ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorSize + (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorInset * 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void a(@NonNull Canvas canvas, @NonNull Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((CircularProgressIndicatorSpec) this.f7428a).trackColor, this.f7429b.getAlpha());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        paint.setStrokeWidth(this.displayedTrackThickness);
        float f2 = this.adjustedRadius;
        canvas.drawArc(new RectF(-f2, -f2, f2, f2), 0.0f, 360.0f, false, paint);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void adjustCanvas(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        float f3;
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f7428a;
        float f4 = (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorSize / 2.0f) + ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorInset;
        canvas.translate(f4, f4);
        canvas.rotate(-90.0f);
        float f5 = -f4;
        canvas.clipRect(f5, f5, f4, f4);
        BaseProgressIndicatorSpec baseProgressIndicatorSpec2 = this.f7428a;
        this.arcDirectionFactor = ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).indicatorDirection == 0 ? 1 : -1;
        this.displayedTrackThickness = ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).trackThickness * f2;
        this.displayedCornerRadius = ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).trackCornerRadius * f2;
        this.adjustedRadius = (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).indicatorSize - ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).trackThickness) / 2.0f;
        if ((this.f7429b.isShowing() && ((CircularProgressIndicatorSpec) this.f7428a).showAnimationBehavior == 2) || (this.f7429b.isHiding() && ((CircularProgressIndicatorSpec) this.f7428a).hideAnimationBehavior == 1)) {
            f3 = this.adjustedRadius + (((1.0f - f2) * ((CircularProgressIndicatorSpec) this.f7428a).trackThickness) / 2.0f);
        } else if ((!this.f7429b.isShowing() || ((CircularProgressIndicatorSpec) this.f7428a).showAnimationBehavior != 1) && (!this.f7429b.isHiding() || ((CircularProgressIndicatorSpec) this.f7428a).hideAnimationBehavior != 2)) {
            return;
        } else {
            f3 = this.adjustedRadius - (((1.0f - f2) * ((CircularProgressIndicatorSpec) this.f7428a).trackThickness) / 2.0f);
        }
        this.adjustedRadius = f3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @ColorInt int i2) {
        if (f2 == f3) {
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(i2);
        paint.setStrokeWidth(this.displayedTrackThickness);
        int i3 = this.arcDirectionFactor;
        float f4 = f2 * 360.0f * i3;
        float f5 = (f3 >= f2 ? f3 - f2 : (f3 + 1.0f) - f2) * 360.0f * i3;
        float f6 = this.adjustedRadius;
        canvas.drawArc(new RectF(-f6, -f6, f6, f6), f4, f5, false, paint);
        if (this.displayedCornerRadius <= 0.0f || Math.abs(f5) >= 360.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        float f7 = this.displayedCornerRadius;
        RectF rectF = new RectF(-f7, -f7, f7, f7);
        drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f4, true, rectF);
        drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f4 + f5, false, rectF);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredHeight() {
        return getSize();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredWidth() {
        return getSize();
    }
}
