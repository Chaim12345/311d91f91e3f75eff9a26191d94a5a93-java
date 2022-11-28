package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public abstract class DataRenderer extends Renderer {

    /* renamed from: b  reason: collision with root package name */
    protected ChartAnimator f5400b;

    /* renamed from: c  reason: collision with root package name */
    protected Paint f5401c;

    /* renamed from: d  reason: collision with root package name */
    protected Paint f5402d;

    /* renamed from: e  reason: collision with root package name */
    protected Paint f5403e;

    public DataRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(viewPortHandler);
        this.f5400b = chartAnimator;
        Paint paint = new Paint(1);
        this.f5401c = paint;
        paint.setStyle(Paint.Style.FILL);
        new Paint(4);
        Paint paint2 = new Paint(1);
        this.f5403e = paint2;
        paint2.setColor(Color.rgb(63, 63, 63));
        this.f5403e.setTextAlign(Paint.Align.CENTER);
        this.f5403e.setTextSize(Utils.convertDpToPixel(9.0f));
        Paint paint3 = new Paint(1);
        this.f5402d = paint3;
        paint3.setStyle(Paint.Style.STROKE);
        this.f5402d.setStrokeWidth(2.0f);
        this.f5402d.setColor(Color.rgb(255, (int) CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, 115));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(IDataSet iDataSet) {
        this.f5403e.setTypeface(iDataSet.getValueTypeface());
        this.f5403e.setTextSize(iDataSet.getValueTextSize());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < ((float) chartInterface.getMaxVisibleCount()) * this.f5436a.getScaleX();
    }

    public abstract void drawData(Canvas canvas);

    public abstract void drawExtras(Canvas canvas);

    public abstract void drawHighlighted(Canvas canvas, Highlight[] highlightArr);

    public void drawValue(Canvas canvas, IValueFormatter iValueFormatter, float f2, Entry entry, int i2, float f3, float f4, int i3) {
        this.f5403e.setColor(i3);
        canvas.drawText(iValueFormatter.getFormattedValue(f2, entry, i2, this.f5436a), f3, f4, this.f5403e);
    }

    public abstract void drawValues(Canvas canvas);

    public Paint getPaintHighlight() {
        return this.f5402d;
    }

    public Paint getPaintRender() {
        return this.f5401c;
    }

    public Paint getPaintValues() {
        return this.f5403e;
    }

    public abstract void initBuffers();
}
