package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public abstract class AxisRenderer extends Renderer {

    /* renamed from: b  reason: collision with root package name */
    protected AxisBase f5381b;

    /* renamed from: c  reason: collision with root package name */
    protected Transformer f5382c;

    /* renamed from: d  reason: collision with root package name */
    protected Paint f5383d;

    /* renamed from: e  reason: collision with root package name */
    protected Paint f5384e;

    /* renamed from: f  reason: collision with root package name */
    protected Paint f5385f;

    /* renamed from: g  reason: collision with root package name */
    protected Paint f5386g;

    public AxisRenderer(ViewPortHandler viewPortHandler, Transformer transformer, AxisBase axisBase) {
        super(viewPortHandler);
        this.f5382c = transformer;
        this.f5381b = axisBase;
        if (this.f5436a != null) {
            this.f5384e = new Paint(1);
            Paint paint = new Paint();
            this.f5383d = paint;
            paint.setColor(-7829368);
            this.f5383d.setStrokeWidth(1.0f);
            this.f5383d.setStyle(Paint.Style.STROKE);
            this.f5383d.setAlpha(90);
            Paint paint2 = new Paint();
            this.f5385f = paint2;
            paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.f5385f.setStrokeWidth(1.0f);
            this.f5385f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint(1);
            this.f5386g = paint3;
            paint3.setStyle(Paint.Style.STROKE);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v5, types: [int] */
    public void a(float f2, float f3) {
        AxisBase axisBase;
        int i2;
        float f4 = f2;
        int labelCount = this.f5381b.getLabelCount();
        double abs = Math.abs(f3 - f4);
        if (labelCount == 0 || abs <= 0.0d || Double.isInfinite(abs)) {
            AxisBase axisBase2 = this.f5381b;
            axisBase2.mEntries = new float[0];
            axisBase2.mCenteredEntries = new float[0];
            axisBase2.mEntryCount = 0;
            return;
        }
        double roundToNextSignificant = Utils.roundToNextSignificant(abs / labelCount);
        if (this.f5381b.isGranularityEnabled() && roundToNextSignificant < this.f5381b.getGranularity()) {
            roundToNextSignificant = this.f5381b.getGranularity();
        }
        double roundToNextSignificant2 = Utils.roundToNextSignificant(Math.pow(10.0d, (int) Math.log10(roundToNextSignificant)));
        if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
            roundToNextSignificant = Math.floor(roundToNextSignificant2 * 10.0d);
        }
        int isCenterAxisLabelsEnabled = this.f5381b.isCenterAxisLabelsEnabled();
        if (this.f5381b.isForceLabelsEnabled()) {
            roundToNextSignificant = ((float) abs) / (labelCount - 1);
            AxisBase axisBase3 = this.f5381b;
            axisBase3.mEntryCount = labelCount;
            if (axisBase3.mEntries.length < labelCount) {
                axisBase3.mEntries = new float[labelCount];
            }
            for (int i3 = 0; i3 < labelCount; i3++) {
                this.f5381b.mEntries[i3] = f4;
                f4 = (float) (f4 + roundToNextSignificant);
            }
        } else {
            int i4 = (roundToNextSignificant > 0.0d ? 1 : (roundToNextSignificant == 0.0d ? 0 : -1));
            double ceil = i4 == 0 ? 0.0d : Math.ceil(f4 / roundToNextSignificant) * roundToNextSignificant;
            if (this.f5381b.isCenterAxisLabelsEnabled()) {
                ceil -= roundToNextSignificant;
            }
            double nextUp = i4 == 0 ? 0.0d : Utils.nextUp(Math.floor(f3 / roundToNextSignificant) * roundToNextSignificant);
            if (i4 != 0) {
                double d2 = ceil;
                isCenterAxisLabelsEnabled = isCenterAxisLabelsEnabled;
                while (d2 <= nextUp) {
                    d2 += roundToNextSignificant;
                    isCenterAxisLabelsEnabled++;
                }
            }
            AxisBase axisBase4 = this.f5381b;
            axisBase4.mEntryCount = isCenterAxisLabelsEnabled;
            if (axisBase4.mEntries.length < isCenterAxisLabelsEnabled) {
                axisBase4.mEntries = new float[isCenterAxisLabelsEnabled];
            }
            for (int i5 = 0; i5 < isCenterAxisLabelsEnabled; i5++) {
                if (ceil == 0.0d) {
                    ceil = 0.0d;
                }
                this.f5381b.mEntries[i5] = (float) ceil;
                ceil += roundToNextSignificant;
            }
            labelCount = isCenterAxisLabelsEnabled;
        }
        if (roundToNextSignificant < 1.0d) {
            axisBase = this.f5381b;
            i2 = (int) Math.ceil(-Math.log10(roundToNextSignificant));
        } else {
            axisBase = this.f5381b;
            i2 = 0;
        }
        axisBase.mDecimals = i2;
        if (this.f5381b.isCenterAxisLabelsEnabled()) {
            AxisBase axisBase5 = this.f5381b;
            if (axisBase5.mCenteredEntries.length < labelCount) {
                axisBase5.mCenteredEntries = new float[labelCount];
            }
            float f5 = ((float) roundToNextSignificant) / 2.0f;
            for (int i6 = 0; i6 < labelCount; i6++) {
                AxisBase axisBase6 = this.f5381b;
                axisBase6.mCenteredEntries[i6] = axisBase6.mEntries[i6] + f5;
            }
        }
    }

    public void computeAxis(float f2, float f3, boolean z) {
        float f4;
        double d2;
        ViewPortHandler viewPortHandler = this.f5436a;
        if (viewPortHandler != null && viewPortHandler.contentWidth() > 10.0f && !this.f5436a.isFullyZoomedOutY()) {
            MPPointD valuesByTouchPoint = this.f5382c.getValuesByTouchPoint(this.f5436a.contentLeft(), this.f5436a.contentTop());
            MPPointD valuesByTouchPoint2 = this.f5382c.getValuesByTouchPoint(this.f5436a.contentLeft(), this.f5436a.contentBottom());
            if (z) {
                f4 = (float) valuesByTouchPoint.y;
                d2 = valuesByTouchPoint2.y;
            } else {
                f4 = (float) valuesByTouchPoint2.y;
                d2 = valuesByTouchPoint.y;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f2 = f4;
            f3 = (float) d2;
        }
        a(f2, f3);
    }

    public Paint getPaintAxisLabels() {
        return this.f5384e;
    }

    public Paint getPaintAxisLine() {
        return this.f5385f;
    }

    public Paint getPaintGrid() {
        return this.f5383d;
    }

    public Transformer getTransformer() {
        return this.f5382c;
    }

    public abstract void renderAxisLabels(Canvas canvas);

    public abstract void renderAxisLine(Canvas canvas);

    public abstract void renderGridLines(Canvas canvas);

    public abstract void renderLimitLines(Canvas canvas);
}
