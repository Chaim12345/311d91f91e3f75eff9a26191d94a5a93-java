package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class AnimatedZoomJob extends AnimatedViewPortJob {
    private static ObjectPool<AnimatedZoomJob> pool = ObjectPool.create(8, new AnimatedZoomJob(null, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0));

    /* renamed from: l  reason: collision with root package name */
    protected float f5359l;

    /* renamed from: m  reason: collision with root package name */
    protected float f5360m;

    /* renamed from: n  reason: collision with root package name */
    protected float f5361n;

    /* renamed from: o  reason: collision with root package name */
    protected float f5362o;

    /* renamed from: p  reason: collision with root package name */
    protected YAxis f5363p;

    /* renamed from: q  reason: collision with root package name */
    protected float f5364q;

    /* renamed from: r  reason: collision with root package name */
    protected Matrix f5365r;

    @SuppressLint({"NewApi"})
    public AnimatedZoomJob(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j2) {
        super(viewPortHandler, f3, f4, transformer, view, f5, f6, j2);
        this.f5365r = new Matrix();
        this.f5361n = f7;
        this.f5362o = f8;
        this.f5359l = f9;
        this.f5360m = f10;
        this.f5355h.addListener(this);
        this.f5363p = yAxis;
        this.f5364q = f2;
    }

    public static AnimatedZoomJob getInstance(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j2) {
        AnimatedZoomJob animatedZoomJob = pool.get();
        animatedZoomJob.f5367c = viewPortHandler;
        animatedZoomJob.f5368d = f3;
        animatedZoomJob.f5369e = f4;
        animatedZoomJob.f5370f = transformer;
        animatedZoomJob.f5371g = view;
        animatedZoomJob.f5357j = f5;
        animatedZoomJob.f5358k = f6;
        animatedZoomJob.f5363p = yAxis;
        animatedZoomJob.f5364q = f2;
        animatedZoomJob.b();
        animatedZoomJob.f5355h.setDuration(j2);
        return animatedZoomJob;
    }

    @Override // com.github.mikephil.charting.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable a() {
        return new AnimatedZoomJob(null, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L);
    }

    @Override // com.github.mikephil.charting.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // com.github.mikephil.charting.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        ((BarLineChartBase) this.f5371g).calculateOffsets();
        this.f5371g.postInvalidate();
    }

    @Override // com.github.mikephil.charting.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // com.github.mikephil.charting.jobs.AnimatedViewPortJob, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }

    @Override // com.github.mikephil.charting.jobs.AnimatedViewPortJob, android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f2 = this.f5357j;
        float f3 = this.f5356i;
        float f4 = f2 + ((this.f5368d - f2) * f3);
        float f5 = this.f5358k;
        float f6 = f5 + ((this.f5369e - f5) * f3);
        Matrix matrix = this.f5365r;
        this.f5367c.setZoom(f4, f6, matrix);
        this.f5367c.refresh(matrix, this.f5371g, false);
        float scaleY = this.f5363p.mAxisRange / this.f5367c.getScaleY();
        float scaleX = this.f5364q / this.f5367c.getScaleX();
        float[] fArr = this.f5366b;
        float f7 = this.f5359l;
        float f8 = this.f5356i;
        fArr[0] = f7 + (((this.f5361n - (scaleX / 2.0f)) - f7) * f8);
        float f9 = this.f5360m;
        fArr[1] = f9 + (((this.f5362o + (scaleY / 2.0f)) - f9) * f8);
        this.f5370f.pointValuesToPixel(fArr);
        this.f5367c.translate(this.f5366b, matrix);
        this.f5367c.refresh(matrix, this.f5371g, true);
    }

    @Override // com.github.mikephil.charting.jobs.AnimatedViewPortJob
    public void recycleSelf() {
    }
}
