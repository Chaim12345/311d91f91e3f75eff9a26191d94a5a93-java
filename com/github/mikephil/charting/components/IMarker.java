package com.github.mikephil.charting.components;

import android.graphics.Canvas;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
/* loaded from: classes.dex */
public interface IMarker {
    void draw(Canvas canvas, float f2, float f3);

    MPPointF getOffset();

    MPPointF getOffsetForDrawingAtPoint(float f2, float f3);

    void refreshContent(Entry entry, Highlight highlight);
}
