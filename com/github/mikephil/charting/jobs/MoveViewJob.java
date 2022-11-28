package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class MoveViewJob extends ViewPortJob {
    private static ObjectPool<MoveViewJob> pool;

    static {
        ObjectPool<MoveViewJob> create = ObjectPool.create(2, new MoveViewJob(null, 0.0f, 0.0f, null, null));
        pool = create;
        create.setReplenishPercentage(0.5f);
    }

    public MoveViewJob(ViewPortHandler viewPortHandler, float f2, float f3, Transformer transformer, View view) {
        super(viewPortHandler, f2, f3, transformer, view);
    }

    public static MoveViewJob getInstance(ViewPortHandler viewPortHandler, float f2, float f3, Transformer transformer, View view) {
        MoveViewJob moveViewJob = pool.get();
        moveViewJob.f5367c = viewPortHandler;
        moveViewJob.f5368d = f2;
        moveViewJob.f5369e = f3;
        moveViewJob.f5370f = transformer;
        moveViewJob.f5371g = view;
        return moveViewJob;
    }

    public static void recycleInstance(MoveViewJob moveViewJob) {
        pool.recycle((ObjectPool<MoveViewJob>) moveViewJob);
    }

    @Override // com.github.mikephil.charting.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable a() {
        return new MoveViewJob(this.f5367c, this.f5368d, this.f5369e, this.f5370f, this.f5371g);
    }

    @Override // java.lang.Runnable
    public void run() {
        float[] fArr = this.f5366b;
        fArr[0] = this.f5368d;
        fArr[1] = this.f5369e;
        this.f5370f.pointValuesToPixel(fArr);
        this.f5367c.centerViewPort(this.f5366b, this.f5371g);
        recycleInstance(this);
    }
}
