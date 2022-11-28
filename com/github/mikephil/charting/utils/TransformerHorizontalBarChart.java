package com.github.mikephil.charting.utils;
/* loaded from: classes.dex */
public class TransformerHorizontalBarChart extends Transformer {
    public TransformerHorizontalBarChart(ViewPortHandler viewPortHandler) {
        super(viewPortHandler);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void prepareMatrixOffset(boolean z) {
        this.f5463b.reset();
        if (!z) {
            this.f5463b.postTranslate(this.f5464c.offsetLeft(), this.f5464c.getChartHeight() - this.f5464c.offsetBottom());
            return;
        }
        this.f5463b.setTranslate(-(this.f5464c.getChartWidth() - this.f5464c.offsetRight()), this.f5464c.getChartHeight() - this.f5464c.offsetBottom());
        this.f5463b.postScale(-1.0f, 1.0f);
    }
}
