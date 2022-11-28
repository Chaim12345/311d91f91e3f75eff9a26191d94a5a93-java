package com.google.android.material.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
@Deprecated
/* loaded from: classes2.dex */
public class ShadowDrawableWrapper extends DrawableWrapper {

    /* renamed from: i  reason: collision with root package name */
    static final double f7445i = Math.cos(Math.toRadians(45.0d));
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final Paint f7446a;
    private boolean addPaddingForCorners;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    final Paint f7447b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    final RectF f7448c;

    /* renamed from: d  reason: collision with root package name */
    float f7449d;
    private boolean dirty;

    /* renamed from: e  reason: collision with root package name */
    Path f7450e;

    /* renamed from: f  reason: collision with root package name */
    float f7451f;

    /* renamed from: g  reason: collision with root package name */
    float f7452g;

    /* renamed from: h  reason: collision with root package name */
    float f7453h;
    private boolean printedShadowClipWarning;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    private final int shadowStartColor;

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f2, float f3, float f4) {
        super(drawable);
        this.dirty = true;
        this.addPaddingForCorners = true;
        this.printedShadowClipWarning = false;
        this.shadowStartColor = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.shadowMiddleColor = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.shadowEndColor = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        Paint paint = new Paint(5);
        this.f7446a = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f7449d = Math.round(f2);
        this.f7448c = new RectF();
        Paint paint2 = new Paint(paint);
        this.f7447b = paint2;
        paint2.setAntiAlias(false);
        setShadowSize(f3, f4);
    }

    private void buildComponents(@NonNull Rect rect) {
        float f2 = this.f7451f;
        float f3 = 1.5f * f2;
        this.f7448c.set(rect.left + f2, rect.top + f3, rect.right - f2, rect.bottom - f3);
        Drawable wrappedDrawable = getWrappedDrawable();
        RectF rectF = this.f7448c;
        wrappedDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        buildShadowCorners();
    }

    private void buildShadowCorners() {
        float f2 = this.f7449d;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.f7452g;
        rectF2.inset(-f3, -f3);
        Path path = this.f7450e;
        if (path == null) {
            this.f7450e = new Path();
        } else {
            path.reset();
        }
        this.f7450e.setFillType(Path.FillType.EVEN_ODD);
        this.f7450e.moveTo(-this.f7449d, 0.0f);
        this.f7450e.rLineTo(-this.f7452g, 0.0f);
        this.f7450e.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f7450e.arcTo(rectF, 270.0f, -90.0f, false);
        this.f7450e.close();
        float f4 = -rectF2.top;
        if (f4 > 0.0f) {
            float f5 = this.f7449d / f4;
            this.f7446a.setShader(new RadialGradient(0.0f, 0.0f, f4, new int[]{0, this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, f5, ((1.0f - f5) / 2.0f) + f5, 1.0f}, Shader.TileMode.CLAMP));
        }
        this.f7447b.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f7447b.setAntiAlias(false);
    }

    public static float calculateHorizontalPadding(float f2, float f3, boolean z) {
        return z ? (float) (f2 + ((1.0d - f7445i) * f3)) : f2;
    }

    public static float calculateVerticalPadding(float f2, float f3, boolean z) {
        float f4 = f2 * 1.5f;
        return z ? (float) (f4 + ((1.0d - f7445i) * f3)) : f4;
    }

    private void drawShadow(@NonNull Canvas canvas) {
        int i2;
        float f2;
        int i3;
        float f3;
        float f4;
        float f5;
        int save = canvas.save();
        canvas.rotate(this.rotation, this.f7448c.centerX(), this.f7448c.centerY());
        float f6 = this.f7449d;
        float f7 = (-f6) - this.f7452g;
        float f8 = f6 * 2.0f;
        boolean z = this.f7448c.width() - f8 > 0.0f;
        boolean z2 = this.f7448c.height() - f8 > 0.0f;
        float f9 = this.f7453h;
        float f10 = f6 / ((f9 - (0.5f * f9)) + f6);
        float f11 = f6 / ((f9 - (0.25f * f9)) + f6);
        float f12 = f6 / ((f9 - (f9 * 1.0f)) + f6);
        int save2 = canvas.save();
        RectF rectF = this.f7448c;
        canvas.translate(rectF.left + f6, rectF.top + f6);
        canvas.scale(f10, f11);
        canvas.drawPath(this.f7450e, this.f7446a);
        if (z) {
            canvas.scale(1.0f / f10, 1.0f);
            i2 = save2;
            f2 = f12;
            i3 = save;
            f3 = f11;
            canvas.drawRect(0.0f, f7, this.f7448c.width() - f8, -this.f7449d, this.f7447b);
        } else {
            i2 = save2;
            f2 = f12;
            i3 = save;
            f3 = f11;
        }
        canvas.restoreToCount(i2);
        int save3 = canvas.save();
        RectF rectF2 = this.f7448c;
        canvas.translate(rectF2.right - f6, rectF2.bottom - f6);
        float f13 = f2;
        canvas.scale(f10, f13);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f7450e, this.f7446a);
        if (z) {
            canvas.scale(1.0f / f10, 1.0f);
            f4 = f3;
            f5 = f13;
            canvas.drawRect(0.0f, f7, this.f7448c.width() - f8, (-this.f7449d) + this.f7452g, this.f7447b);
        } else {
            f4 = f3;
            f5 = f13;
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        RectF rectF3 = this.f7448c;
        canvas.translate(rectF3.left + f6, rectF3.bottom - f6);
        canvas.scale(f10, f5);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f7450e, this.f7446a);
        if (z2) {
            canvas.scale(1.0f / f5, 1.0f);
            canvas.drawRect(0.0f, f7, this.f7448c.height() - f8, -this.f7449d, this.f7447b);
        }
        canvas.restoreToCount(save4);
        int save5 = canvas.save();
        RectF rectF4 = this.f7448c;
        canvas.translate(rectF4.right - f6, rectF4.top + f6);
        float f14 = f4;
        canvas.scale(f10, f14);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f7450e, this.f7446a);
        if (z2) {
            canvas.scale(1.0f / f14, 1.0f);
            canvas.drawRect(0.0f, f7, this.f7448c.height() - f8, -this.f7449d, this.f7447b);
        }
        canvas.restoreToCount(save5);
        canvas.restoreToCount(i3);
    }

    private static int toEven(float f2) {
        int round = Math.round(f2);
        return round % 2 == 1 ? round - 1 : round;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (this.dirty) {
            buildComponents(getBounds());
            this.dirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    public float getCornerRadius() {
        return this.f7449d;
    }

    public float getMaxShadowSize() {
        return this.f7451f;
    }

    public float getMinHeight() {
        float f2 = this.f7451f;
        return (Math.max(f2, this.f7449d + ((f2 * 1.5f) / 2.0f)) * 2.0f) + (this.f7451f * 1.5f * 2.0f);
    }

    public float getMinWidth() {
        float f2 = this.f7451f;
        return (Math.max(f2, this.f7449d + (f2 / 2.0f)) * 2.0f) + (this.f7451f * 2.0f);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean getPadding(@NonNull Rect rect) {
        int ceil = (int) Math.ceil(calculateVerticalPadding(this.f7451f, this.f7449d, this.addPaddingForCorners));
        int ceil2 = (int) Math.ceil(calculateHorizontalPadding(this.f7451f, this.f7449d, this.addPaddingForCorners));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    public float getShadowSize() {
        return this.f7453h;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.dirty = true;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.addPaddingForCorners = z;
        invalidateSelf();
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        super.setAlpha(i2);
        this.f7446a.setAlpha(i2);
        this.f7447b.setAlpha(i2);
    }

    public void setCornerRadius(float f2) {
        float round = Math.round(f2);
        if (this.f7449d == round) {
            return;
        }
        this.f7449d = round;
        this.dirty = true;
        invalidateSelf();
    }

    public void setMaxShadowSize(float f2) {
        setShadowSize(this.f7453h, f2);
    }

    public final void setRotation(float f2) {
        if (this.rotation != f2) {
            this.rotation = f2;
            invalidateSelf();
        }
    }

    public void setShadowSize(float f2) {
        setShadowSize(f2, this.f7451f);
    }

    public void setShadowSize(float f2, float f3) {
        if (f2 < 0.0f || f3 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float even = toEven(f2);
        float even2 = toEven(f3);
        if (even > even2) {
            if (!this.printedShadowClipWarning) {
                this.printedShadowClipWarning = true;
            }
            even = even2;
        }
        if (this.f7453h == even && this.f7451f == even2) {
            return;
        }
        this.f7453h = even;
        this.f7451f = even2;
        this.f7452g = Math.round(even * 1.5f);
        this.dirty = true;
        invalidateSelf();
    }
}
