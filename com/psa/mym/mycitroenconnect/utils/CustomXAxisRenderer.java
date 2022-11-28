package com.psa.mym.mycitroenconnect.utils;

import android.graphics.Canvas;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes3.dex */
public class CustomXAxisRenderer extends XAxisRenderer {
    public CustomXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(viewPortHandler, xAxis, transformer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    public void d(Canvas canvas, String str, float f2, float f3, MPPointF mPPointF, float f4) {
        String[] split = str.split("\n");
        if (split.length > 0) {
            if (split.length != 2) {
                Utils.drawXAxisValue(canvas, split[0], f2, f3, this.f5384e, mPPointF, f4);
                return;
            }
            Utils.drawXAxisValue(canvas, split[0], f2, f3, this.f5384e, mPPointF, f4);
            Utils.drawXAxisValue(canvas, split[1], f2 + this.f5384e.getTextSize(), f3 + this.f5384e.getTextSize(), this.f5384e, mPPointF, f4);
        }
    }
}
