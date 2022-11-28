package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
/* loaded from: classes2.dex */
public class RotationLayout extends FrameLayout {
    private int mRotation;

    public RotationLayout(Context context) {
        super(context);
    }

    public RotationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RotationLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        float height;
        int i2;
        int i3 = this.mRotation;
        if (i3 == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        if (i3 == 1) {
            canvas.translate(getWidth(), 0.0f);
            canvas.rotate(90.0f, getWidth() / 2, 0.0f);
            height = getHeight() / 2;
            i2 = getWidth();
        } else if (i3 == 2) {
            canvas.rotate(180.0f, getWidth() / 2, getHeight() / 2);
            super.dispatchDraw(canvas);
        } else {
            canvas.translate(0.0f, getHeight());
            canvas.rotate(270.0f, getWidth() / 2, 0.0f);
            height = getHeight() / 2;
            i2 = -getWidth();
        }
        canvas.translate(height, i2 / 2);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = this.mRotation;
        if (i4 != 1 && i4 != 3) {
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, i3);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    public void setViewRotation(int i2) {
        this.mRotation = ((i2 + 360) % 360) / 90;
    }
}
