package com.yalantis.ucrop.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.model.AspectRatio;
import java.util.Locale;
/* loaded from: classes3.dex */
public class AspectRatioTextView extends TextView {
    private float mAspectRatio;
    private String mAspectRatioTitle;
    private float mAspectRatioX;
    private float mAspectRatioY;
    private final Rect mCanvasClipBounds;
    private Paint mDotPaint;
    private int mDotSize;

    public AspectRatioTextView(Context context) {
        this(context, null);
    }

    public AspectRatioTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AspectRatioTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCanvasClipBounds = new Rect();
        init(context.obtainStyledAttributes(attributeSet, R.styleable.ucrop_AspectRatioTextView));
    }

    @TargetApi(21)
    public AspectRatioTextView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mCanvasClipBounds = new Rect();
        init(context.obtainStyledAttributes(attributeSet, R.styleable.ucrop_AspectRatioTextView));
    }

    private void applyActiveColor(@ColorInt int i2) {
        Paint paint = this.mDotPaint;
        if (paint != null) {
            paint.setColor(i2);
        }
        setTextColor(new ColorStateList(new int[][]{new int[]{16842913}, new int[]{0}}, new int[]{i2, ContextCompat.getColor(getContext(), R.color.ucrop_color_widget)}));
    }

    private void init(@NonNull TypedArray typedArray) {
        setGravity(1);
        this.mAspectRatioTitle = typedArray.getString(R.styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_title);
        this.mAspectRatioX = typedArray.getFloat(R.styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_x, 0.0f);
        float f2 = typedArray.getFloat(R.styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_y, 0.0f);
        this.mAspectRatioY = f2;
        float f3 = this.mAspectRatioX;
        if (f3 == 0.0f || f2 == 0.0f) {
            this.mAspectRatio = 0.0f;
        } else {
            this.mAspectRatio = f3 / f2;
        }
        this.mDotSize = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_size_dot_scale_text_view);
        Paint paint = new Paint(1);
        this.mDotPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        setTitle();
        applyActiveColor(getResources().getColor(R.color.ucrop_color_widget_active));
        typedArray.recycle();
    }

    private void setTitle() {
        setText(!TextUtils.isEmpty(this.mAspectRatioTitle) ? this.mAspectRatioTitle : String.format(Locale.US, "%d:%d", Integer.valueOf((int) this.mAspectRatioX), Integer.valueOf((int) this.mAspectRatioY)));
    }

    private void toggleAspectRatio() {
        if (this.mAspectRatio != 0.0f) {
            float f2 = this.mAspectRatioX;
            float f3 = this.mAspectRatioY;
            this.mAspectRatioX = f3;
            this.mAspectRatioY = f2;
            this.mAspectRatio = f3 / f2;
        }
    }

    public float getAspectRatio(boolean z) {
        if (z) {
            toggleAspectRatio();
            setTitle();
        }
        return this.mAspectRatio;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelected()) {
            canvas.getClipBounds(this.mCanvasClipBounds);
            Rect rect = this.mCanvasClipBounds;
            int i2 = rect.bottom;
            int i3 = this.mDotSize;
            canvas.drawCircle((rect.right - rect.left) / 2.0f, i2 - i3, i3 / 2, this.mDotPaint);
        }
    }

    public void setActiveColor(@ColorInt int i2) {
        applyActiveColor(i2);
        invalidate();
    }

    public void setAspectRatio(@NonNull AspectRatio aspectRatio) {
        this.mAspectRatioTitle = aspectRatio.getAspectRatioTitle();
        this.mAspectRatioX = aspectRatio.getAspectRatioX();
        float aspectRatioY = aspectRatio.getAspectRatioY();
        this.mAspectRatioY = aspectRatioY;
        float f2 = this.mAspectRatioX;
        if (f2 == 0.0f || aspectRatioY == 0.0f) {
            this.mAspectRatio = 0.0f;
        } else {
            this.mAspectRatio = f2 / aspectRatioY;
        }
        setTitle();
    }
}
