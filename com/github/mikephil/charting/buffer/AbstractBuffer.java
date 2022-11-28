package com.github.mikephil.charting.buffer;
/* loaded from: classes.dex */
public abstract class AbstractBuffer<T> {

    /* renamed from: a  reason: collision with root package name */
    protected int f5252a;
    public final float[] buffer;

    /* renamed from: b  reason: collision with root package name */
    protected float f5253b = 1.0f;

    /* renamed from: c  reason: collision with root package name */
    protected float f5254c = 1.0f;

    public AbstractBuffer(int i2) {
        this.f5252a = 0;
        this.f5252a = 0;
        this.buffer = new float[i2];
    }

    public abstract void feed(T t2);

    public void limitFrom(int i2) {
    }

    public void limitTo(int i2) {
    }

    public void reset() {
        this.f5252a = 0;
    }

    public void setPhases(float f2, float f3) {
        this.f5253b = f2;
        this.f5254c = f3;
    }

    public int size() {
        return this.buffer.length;
    }
}
