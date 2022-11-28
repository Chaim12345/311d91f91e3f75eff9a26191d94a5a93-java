package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class DefaultValueFormatter implements IValueFormatter {

    /* renamed from: a  reason: collision with root package name */
    protected DecimalFormat f5347a;

    /* renamed from: b  reason: collision with root package name */
    protected int f5348b;

    public DefaultValueFormatter(int i2) {
        setup(i2);
    }

    public int getDecimalDigits() {
        return this.f5348b;
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f2, Entry entry, int i2, ViewPortHandler viewPortHandler) {
        return this.f5347a.format(f2);
    }

    public void setup(int i2) {
        this.f5348b = i2;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.f5347a = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }
}
