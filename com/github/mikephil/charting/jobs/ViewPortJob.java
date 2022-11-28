package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public abstract class ViewPortJob extends ObjectPool.Poolable implements Runnable {

    /* renamed from: b  reason: collision with root package name */
    protected float[] f5366b = new float[2];

    /* renamed from: c  reason: collision with root package name */
    protected ViewPortHandler f5367c;

    /* renamed from: d  reason: collision with root package name */
    protected float f5368d;

    /* renamed from: e  reason: collision with root package name */
    protected float f5369e;

    /* renamed from: f  reason: collision with root package name */
    protected Transformer f5370f;

    /* renamed from: g  reason: collision with root package name */
    protected View f5371g;

    public ViewPortJob(ViewPortHandler viewPortHandler, float f2, float f3, Transformer transformer, View view) {
        this.f5368d = 0.0f;
        this.f5369e = 0.0f;
        this.f5367c = viewPortHandler;
        this.f5368d = f2;
        this.f5369e = f3;
        this.f5370f = transformer;
        this.f5371g = view;
    }

    public float getXValue() {
        return this.f5368d;
    }

    public float getYValue() {
        return this.f5369e;
    }
}
