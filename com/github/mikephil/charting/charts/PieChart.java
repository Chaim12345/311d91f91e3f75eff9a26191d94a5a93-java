package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.PieHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;
/* loaded from: classes.dex */
public class PieChart extends PieRadarChartBase<PieData> {
    private float[] mAbsoluteAngles;
    private CharSequence mCenterText;
    private MPPointF mCenterTextOffset;
    private float mCenterTextRadiusPercent;
    private RectF mCircleBox;
    private float[] mDrawAngles;
    private boolean mDrawCenterText;
    private boolean mDrawEntryLabels;
    private boolean mDrawHole;
    private boolean mDrawRoundedSlices;
    private boolean mDrawSlicesUnderHole;
    private float mHoleRadiusPercent;
    private boolean mUsePercentValues;
    protected float y;
    protected float z;

    public PieChart(Context context) {
        super(context);
        this.mCircleBox = new RectF();
        this.mDrawEntryLabels = true;
        this.mDrawAngles = new float[1];
        this.mAbsoluteAngles = new float[1];
        this.mDrawHole = true;
        this.mDrawSlicesUnderHole = false;
        this.mUsePercentValues = false;
        this.mDrawRoundedSlices = false;
        this.mCenterText = "";
        this.mCenterTextOffset = MPPointF.getInstance(0.0f, 0.0f);
        this.mHoleRadiusPercent = 50.0f;
        this.y = 55.0f;
        this.mDrawCenterText = true;
        this.mCenterTextRadiusPercent = 100.0f;
        this.z = 360.0f;
    }

    public PieChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCircleBox = new RectF();
        this.mDrawEntryLabels = true;
        this.mDrawAngles = new float[1];
        this.mAbsoluteAngles = new float[1];
        this.mDrawHole = true;
        this.mDrawSlicesUnderHole = false;
        this.mUsePercentValues = false;
        this.mDrawRoundedSlices = false;
        this.mCenterText = "";
        this.mCenterTextOffset = MPPointF.getInstance(0.0f, 0.0f);
        this.mHoleRadiusPercent = 50.0f;
        this.y = 55.0f;
        this.mDrawCenterText = true;
        this.mCenterTextRadiusPercent = 100.0f;
        this.z = 360.0f;
    }

    public PieChart(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCircleBox = new RectF();
        this.mDrawEntryLabels = true;
        this.mDrawAngles = new float[1];
        this.mAbsoluteAngles = new float[1];
        this.mDrawHole = true;
        this.mDrawSlicesUnderHole = false;
        this.mUsePercentValues = false;
        this.mDrawRoundedSlices = false;
        this.mCenterText = "";
        this.mCenterTextOffset = MPPointF.getInstance(0.0f, 0.0f);
        this.mHoleRadiusPercent = 50.0f;
        this.y = 55.0f;
        this.mDrawCenterText = true;
        this.mCenterTextRadiusPercent = 100.0f;
        this.z = 360.0f;
    }

    private float calcAngle(float f2) {
        return calcAngle(f2, ((PieData) this.f5267b).getYValueSum());
    }

    private float calcAngle(float f2, float f3) {
        return (f2 / f3) * this.z;
    }

    private void calcAngles() {
        int entryCount = ((PieData) this.f5267b).getEntryCount();
        if (this.mDrawAngles.length != entryCount) {
            this.mDrawAngles = new float[entryCount];
        } else {
            for (int i2 = 0; i2 < entryCount; i2++) {
                this.mDrawAngles[i2] = 0.0f;
            }
        }
        if (this.mAbsoluteAngles.length != entryCount) {
            this.mAbsoluteAngles = new float[entryCount];
        } else {
            for (int i3 = 0; i3 < entryCount; i3++) {
                this.mAbsoluteAngles[i3] = 0.0f;
            }
        }
        float yValueSum = ((PieData) this.f5267b).getYValueSum();
        List<IPieDataSet> dataSets = ((PieData) this.f5267b).getDataSets();
        int i4 = 0;
        for (int i5 = 0; i5 < ((PieData) this.f5267b).getDataSetCount(); i5++) {
            IPieDataSet iPieDataSet = dataSets.get(i5);
            for (int i6 = 0; i6 < iPieDataSet.getEntryCount(); i6++) {
                this.mDrawAngles[i4] = calcAngle(Math.abs(iPieDataSet.getEntryForIndex(i6).getY()), yValueSum);
                float[] fArr = this.mAbsoluteAngles;
                if (i4 == 0) {
                    fArr[i4] = this.mDrawAngles[i4];
                } else {
                    fArr[i4] = fArr[i4 - 1] + this.mDrawAngles[i4];
                }
                i4++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart
    public float[] c(Highlight highlight) {
        int x;
        MPPointF centerCircleBox = getCenterCircleBox();
        float radius = getRadius();
        float f2 = (radius / 10.0f) * 3.6f;
        if (isDrawHoleEnabled()) {
            f2 = (radius - ((radius / 100.0f) * getHoleRadius())) / 2.0f;
        }
        float f3 = radius - f2;
        float rotationAngle = getRotationAngle();
        float f4 = this.mDrawAngles[(int) highlight.getX()] / 2.0f;
        double d2 = f3;
        float cos = (float) ((Math.cos(Math.toRadians(((this.mAbsoluteAngles[x] + rotationAngle) - f4) * this.f5282q.getPhaseY())) * d2) + centerCircleBox.x);
        MPPointF.recycleInstance(centerCircleBox);
        return new float[]{cos, (float) ((d2 * Math.sin(Math.toRadians(((rotationAngle + this.mAbsoluteAngles[x]) - f4) * this.f5282q.getPhaseY()))) + centerCircleBox.y)};
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.charts.Chart
    public void calculateOffsets() {
        super.calculateOffsets();
        if (this.f5267b == null) {
            return;
        }
        float diameter = getDiameter() / 2.0f;
        MPPointF centerOffsets = getCenterOffsets();
        float selectionShift = ((PieData) this.f5267b).getDataSet().getSelectionShift();
        RectF rectF = this.mCircleBox;
        float f2 = centerOffsets.x;
        float f3 = centerOffsets.y;
        rectF.set((f2 - diameter) + selectionShift, (f3 - diameter) + selectionShift, (f2 + diameter) - selectionShift, (f3 + diameter) - selectionShift);
        MPPointF.recycleInstance(centerOffsets);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.charts.Chart
    public void d() {
        super.d();
        this.f5279n = new PieChartRenderer(this, this.f5282q, this.f5281p);
        this.f5272g = null;
        this.f5280o = new PieHighlighter(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public void f() {
        calcAngles();
    }

    public float[] getAbsoluteAngles() {
        return this.mAbsoluteAngles;
    }

    public MPPointF getCenterCircleBox() {
        return MPPointF.getInstance(this.mCircleBox.centerX(), this.mCircleBox.centerY());
    }

    public CharSequence getCenterText() {
        return this.mCenterText;
    }

    public MPPointF getCenterTextOffset() {
        MPPointF mPPointF = this.mCenterTextOffset;
        return MPPointF.getInstance(mPPointF.x, mPPointF.y);
    }

    public float getCenterTextRadiusPercent() {
        return this.mCenterTextRadiusPercent;
    }

    public RectF getCircleBox() {
        return this.mCircleBox;
    }

    public int getDataSetIndexForIndex(int i2) {
        List<IPieDataSet> dataSets = ((PieData) this.f5267b).getDataSets();
        for (int i3 = 0; i3 < dataSets.size(); i3++) {
            if (dataSets.get(i3).getEntryForXValue(i2, Float.NaN) != null) {
                return i3;
            }
        }
        return -1;
    }

    public float[] getDrawAngles() {
        return this.mDrawAngles;
    }

    public float getHoleRadius() {
        return this.mHoleRadiusPercent;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public int getIndexForAngle(float f2) {
        float normalizedAngle = Utils.getNormalizedAngle(f2 - getRotationAngle());
        int i2 = 0;
        while (true) {
            float[] fArr = this.mAbsoluteAngles;
            if (i2 >= fArr.length) {
                return -1;
            }
            if (fArr[i2] > normalizedAngle) {
                return i2;
            }
            i2++;
        }
    }

    public float getMaxAngle() {
        return this.z;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public float getRadius() {
        RectF rectF = this.mCircleBox;
        if (rectF == null) {
            return 0.0f;
        }
        return Math.min(rectF.width() / 2.0f, this.mCircleBox.height() / 2.0f);
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    protected float getRequiredBaseOffset() {
        return 0.0f;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    protected float getRequiredLegendOffset() {
        return this.f5278m.getLabelPaint().getTextSize() * 2.0f;
    }

    public float getTransparentCircleRadius() {
        return this.y;
    }

    @Override // com.github.mikephil.charting.charts.Chart
    @Deprecated
    public XAxis getXAxis() {
        throw new RuntimeException("PieChart has no XAxis");
    }

    public boolean isDrawCenterTextEnabled() {
        return this.mDrawCenterText;
    }

    public boolean isDrawEntryLabelsEnabled() {
        return this.mDrawEntryLabels;
    }

    public boolean isDrawHoleEnabled() {
        return this.mDrawHole;
    }

    public boolean isDrawRoundedSlicesEnabled() {
        return this.mDrawRoundedSlices;
    }

    public boolean isDrawSlicesUnderHoleEnabled() {
        return this.mDrawSlicesUnderHole;
    }

    public boolean isUsePercentValuesEnabled() {
        return this.mUsePercentValues;
    }

    public boolean needsHighlight(int i2) {
        if (!valuesToHighlight()) {
            return false;
        }
        int i3 = 0;
        while (true) {
            Highlight[] highlightArr = this.f5283r;
            if (i3 >= highlightArr.length) {
                return false;
            }
            if (((int) highlightArr[i3].getX()) == i2) {
                return true;
            }
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        DataRenderer dataRenderer = this.f5279n;
        if (dataRenderer != null && (dataRenderer instanceof PieChartRenderer)) {
            ((PieChartRenderer) dataRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f5267b == null) {
            return;
        }
        this.f5279n.drawData(canvas);
        if (valuesToHighlight()) {
            this.f5279n.drawHighlighted(canvas, this.f5283r);
        }
        this.f5279n.drawExtras(canvas);
        this.f5279n.drawValues(canvas);
        this.f5278m.renderLegend(canvas);
        a(canvas);
        b(canvas);
    }

    public void setCenterText(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.mCenterText = charSequence;
    }

    public void setCenterTextColor(int i2) {
        ((PieChartRenderer) this.f5279n).getPaintCenterText().setColor(i2);
    }

    public void setCenterTextOffset(float f2, float f3) {
        this.mCenterTextOffset.x = Utils.convertDpToPixel(f2);
        this.mCenterTextOffset.y = Utils.convertDpToPixel(f3);
    }

    public void setCenterTextRadiusPercent(float f2) {
        this.mCenterTextRadiusPercent = f2;
    }

    public void setCenterTextSize(float f2) {
        ((PieChartRenderer) this.f5279n).getPaintCenterText().setTextSize(Utils.convertDpToPixel(f2));
    }

    public void setCenterTextSizePixels(float f2) {
        ((PieChartRenderer) this.f5279n).getPaintCenterText().setTextSize(f2);
    }

    public void setCenterTextTypeface(Typeface typeface) {
        ((PieChartRenderer) this.f5279n).getPaintCenterText().setTypeface(typeface);
    }

    public void setDrawCenterText(boolean z) {
        this.mDrawCenterText = z;
    }

    public void setDrawEntryLabels(boolean z) {
        this.mDrawEntryLabels = z;
    }

    public void setDrawHoleEnabled(boolean z) {
        this.mDrawHole = z;
    }

    @Deprecated
    public void setDrawSliceText(boolean z) {
        this.mDrawEntryLabels = z;
    }

    public void setDrawSlicesUnderHole(boolean z) {
        this.mDrawSlicesUnderHole = z;
    }

    public void setEntryLabelColor(int i2) {
        ((PieChartRenderer) this.f5279n).getPaintEntryLabels().setColor(i2);
    }

    public void setEntryLabelTextSize(float f2) {
        ((PieChartRenderer) this.f5279n).getPaintEntryLabels().setTextSize(Utils.convertDpToPixel(f2));
    }

    public void setEntryLabelTypeface(Typeface typeface) {
        ((PieChartRenderer) this.f5279n).getPaintEntryLabels().setTypeface(typeface);
    }

    public void setHoleColor(int i2) {
        ((PieChartRenderer) this.f5279n).getPaintHole().setColor(i2);
    }

    public void setHoleRadius(float f2) {
        this.mHoleRadiusPercent = f2;
    }

    public void setMaxAngle(float f2) {
        if (f2 > 360.0f) {
            f2 = 360.0f;
        }
        if (f2 < 90.0f) {
            f2 = 90.0f;
        }
        this.z = f2;
    }

    public void setTransparentCircleAlpha(int i2) {
        ((PieChartRenderer) this.f5279n).getPaintTransparentCircle().setAlpha(i2);
    }

    public void setTransparentCircleColor(int i2) {
        Paint paintTransparentCircle = ((PieChartRenderer) this.f5279n).getPaintTransparentCircle();
        int alpha = paintTransparentCircle.getAlpha();
        paintTransparentCircle.setColor(i2);
        paintTransparentCircle.setAlpha(alpha);
    }

    public void setTransparentCircleRadius(float f2) {
        this.y = f2;
    }

    public void setUsePercentValues(boolean z) {
        this.mUsePercentValues = z;
    }
}
