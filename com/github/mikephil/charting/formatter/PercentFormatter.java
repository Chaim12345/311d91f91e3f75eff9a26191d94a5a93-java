package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class PercentFormatter implements IValueFormatter, IAxisValueFormatter {

    /* renamed from: a  reason: collision with root package name */
    protected DecimalFormat f5349a;

    public PercentFormatter() {
        this.f5349a = new DecimalFormat("###,###,##0.0");
    }

    public PercentFormatter(DecimalFormat decimalFormat) {
        this.f5349a = decimalFormat;
    }

    public int getDecimalDigits() {
        return 1;
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f2, AxisBase axisBase) {
        return this.f5349a.format(f2) + " %";
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f2, Entry entry, int i2, ViewPortHandler viewPortHandler) {
        return this.f5349a.format(f2) + " %";
    }
}
