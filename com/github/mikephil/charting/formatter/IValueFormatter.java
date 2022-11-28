package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public interface IValueFormatter {
    String getFormattedValue(float f2, Entry entry, int i2, ViewPortHandler viewPortHandler);
}
