package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class DefaultAxisValueFormatter implements IAxisValueFormatter {

    /* renamed from: a  reason: collision with root package name */
    protected DecimalFormat f5345a;

    /* renamed from: b  reason: collision with root package name */
    protected int f5346b;

    public DefaultAxisValueFormatter(int i2) {
        this.f5346b = 0;
        this.f5346b = i2;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.f5345a = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    public int getDecimalDigits() {
        return this.f5346b;
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f2, AxisBase axisBase) {
        return this.f5345a.format(f2);
    }
}
