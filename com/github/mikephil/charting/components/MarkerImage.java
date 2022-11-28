package com.github.mikephil.charting.components;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class MarkerImage implements IMarker {
    private Context mContext;
    private Drawable mDrawable;
    private WeakReference<Chart> mWeakChart;
    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private FSize mSize = new FSize();
    private Rect mDrawableBoundsCache = new Rect();

    public MarkerImage(Context context, int i2) {
        this.mContext = context;
        int i3 = Build.VERSION.SDK_INT;
        Resources resources = context.getResources();
        this.mDrawable = i3 >= 21 ? resources.getDrawable(i2, null) : resources.getDrawable(i2);
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public void draw(Canvas canvas, float f2, float f3) {
        if (this.mDrawable == null) {
            return;
        }
        MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(f2, f3);
        FSize fSize = this.mSize;
        float f4 = fSize.width;
        float f5 = fSize.height;
        if (f4 == 0.0f) {
            f4 = this.mDrawable.getIntrinsicWidth();
        }
        if (f5 == 0.0f) {
            f5 = this.mDrawable.getIntrinsicHeight();
        }
        this.mDrawable.copyBounds(this.mDrawableBoundsCache);
        Drawable drawable = this.mDrawable;
        Rect rect = this.mDrawableBoundsCache;
        int i2 = rect.left;
        int i3 = rect.top;
        drawable.setBounds(i2, i3, ((int) f4) + i2, ((int) f5) + i3);
        int save = canvas.save();
        canvas.translate(f2 + offsetForDrawingAtPoint.x, f3 + offsetForDrawingAtPoint.y);
        this.mDrawable.draw(canvas);
        canvas.restoreToCount(save);
        this.mDrawable.setBounds(this.mDrawableBoundsCache);
    }

    public Chart getChartView() {
        WeakReference<Chart> weakReference = this.mWeakChart;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public MPPointF getOffset() {
        return this.mOffset;
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public MPPointF getOffsetForDrawingAtPoint(float f2, float f3) {
        Drawable drawable;
        Drawable drawable2;
        MPPointF offset = getOffset();
        MPPointF mPPointF = this.mOffset2;
        mPPointF.x = offset.x;
        mPPointF.y = offset.y;
        Chart chartView = getChartView();
        FSize fSize = this.mSize;
        float f4 = fSize.width;
        float f5 = fSize.height;
        if (f4 == 0.0f && (drawable2 = this.mDrawable) != null) {
            f4 = drawable2.getIntrinsicWidth();
        }
        if (f5 == 0.0f && (drawable = this.mDrawable) != null) {
            f5 = drawable.getIntrinsicHeight();
        }
        MPPointF mPPointF2 = this.mOffset2;
        float f6 = mPPointF2.x;
        if (f2 + f6 < 0.0f) {
            mPPointF2.x = -f2;
        } else if (chartView != null && f2 + f4 + f6 > chartView.getWidth()) {
            this.mOffset2.x = (chartView.getWidth() - f2) - f4;
        }
        MPPointF mPPointF3 = this.mOffset2;
        float f7 = mPPointF3.y;
        if (f3 + f7 < 0.0f) {
            mPPointF3.y = -f3;
        } else if (chartView != null && f3 + f5 + f7 > chartView.getHeight()) {
            this.mOffset2.y = (chartView.getHeight() - f3) - f5;
        }
        return this.mOffset2;
    }

    public FSize getSize() {
        return this.mSize;
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public void refreshContent(Entry entry, Highlight highlight) {
    }

    public void setChartView(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    public void setOffset(float f2, float f3) {
        MPPointF mPPointF = this.mOffset;
        mPPointF.x = f2;
        mPPointF.y = f3;
    }

    public void setOffset(MPPointF mPPointF) {
        this.mOffset = mPPointF;
        if (mPPointF == null) {
            this.mOffset = new MPPointF();
        }
    }

    public void setSize(FSize fSize) {
        this.mSize = fSize;
        if (fSize == null) {
            this.mSize = new FSize();
        }
    }
}
