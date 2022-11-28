package com.github.mikephil.charting.jobs;

import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class ZoomJob extends ViewPortJob {
    private static ObjectPool<ZoomJob> pool;

    /* renamed from: h  reason: collision with root package name */
    protected float f5372h;

    /* renamed from: i  reason: collision with root package name */
    protected float f5373i;

    /* renamed from: j  reason: collision with root package name */
    protected YAxis.AxisDependency f5374j;

    /* renamed from: k  reason: collision with root package name */
    protected Matrix f5375k;

    static {
        ObjectPool<ZoomJob> create = ObjectPool.create(1, new ZoomJob(null, 0.0f, 0.0f, 0.0f, 0.0f, null, null, null));
        pool = create;
        create.setReplenishPercentage(0.5f);
    }

    public ZoomJob(ViewPortHandler viewPortHandler, float f2, float f3, float f4, float f5, Transformer transformer, YAxis.AxisDependency axisDependency, View view) {
        super(viewPortHandler, f4, f5, transformer, view);
        this.f5375k = new Matrix();
        this.f5372h = f2;
        this.f5373i = f3;
        this.f5374j = axisDependency;
    }

    public static ZoomJob getInstance(ViewPortHandler viewPortHandler, float f2, float f3, float f4, float f5, Transformer transformer, YAxis.AxisDependency axisDependency, View view) {
        ZoomJob zoomJob = pool.get();
        zoomJob.f5368d = f4;
        zoomJob.f5369e = f5;
        zoomJob.f5372h = f2;
        zoomJob.f5373i = f3;
        zoomJob.f5367c = viewPortHandler;
        zoomJob.f5370f = transformer;
        zoomJob.f5374j = axisDependency;
        zoomJob.f5371g = view;
        return zoomJob;
    }

    public static void recycleInstance(ZoomJob zoomJob) {
        pool.recycle((ObjectPool<ZoomJob>) zoomJob);
    }

    @Override // com.github.mikephil.charting.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable a() {
        return new ZoomJob(null, 0.0f, 0.0f, 0.0f, 0.0f, null, null, null);
    }

    @Override // java.lang.Runnable
    public void run() {
        Matrix matrix = this.f5375k;
        this.f5367c.zoom(this.f5372h, this.f5373i, matrix);
        this.f5367c.refresh(matrix, this.f5371g, false);
        float scaleY = ((BarLineChartBase) this.f5371g).getAxis(this.f5374j).mAxisRange / this.f5367c.getScaleY();
        float scaleX = ((BarLineChartBase) this.f5371g).getXAxis().mAxisRange / this.f5367c.getScaleX();
        float[] fArr = this.f5366b;
        fArr[0] = this.f5368d - (scaleX / 2.0f);
        fArr[1] = this.f5369e + (scaleY / 2.0f);
        this.f5370f.pointValuesToPixel(fArr);
        this.f5367c.translate(this.f5366b, matrix);
        this.f5367c.refresh(matrix, this.f5371g, false);
        ((BarLineChartBase) this.f5371g).calculateOffsets();
        this.f5371g.postInvalidate();
        recycleInstance(this);
    }
}
