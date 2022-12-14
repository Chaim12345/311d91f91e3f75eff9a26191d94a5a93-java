package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes.dex */
public class Description extends ComponentBase {
    private MPPointF mPosition;
    private String text = "Description Label";
    private Paint.Align mTextAlign = Paint.Align.RIGHT;

    public Description() {
        this.f5310e = Utils.convertDpToPixel(8.0f);
    }

    public MPPointF getPosition() {
        return this.mPosition;
    }

    public String getText() {
        return this.text;
    }

    public Paint.Align getTextAlign() {
        return this.mTextAlign;
    }

    public void setPosition(float f2, float f3) {
        MPPointF mPPointF = this.mPosition;
        if (mPPointF == null) {
            this.mPosition = MPPointF.getInstance(f2, f3);
            return;
        }
        mPPointF.x = f2;
        mPPointF.y = f3;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setTextAlign(Paint.Align align) {
        this.mTextAlign = align;
    }
}
