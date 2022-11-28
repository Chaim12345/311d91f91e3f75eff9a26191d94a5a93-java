package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class StackedValueFormatter implements IValueFormatter {
    private String mAppendix;
    private boolean mDrawWholeStack;
    private DecimalFormat mFormat;

    public StackedValueFormatter(boolean z, String str, int i2) {
        this.mDrawWholeStack = z;
        this.mAppendix = str;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f2, Entry entry, int i2, ViewPortHandler viewPortHandler) {
        BarEntry barEntry;
        float[] yVals;
        if (this.mDrawWholeStack || !(entry instanceof BarEntry) || (yVals = (barEntry = (BarEntry) entry).getYVals()) == null) {
            return this.mFormat.format(f2) + this.mAppendix;
        } else if (yVals[yVals.length - 1] == f2) {
            return this.mFormat.format(barEntry.getY()) + this.mAppendix;
        } else {
            return "";
        }
    }
}
