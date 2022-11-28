package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
/* loaded from: classes.dex */
public class BarBuffer extends AbstractBuffer<IBarDataSet> {

    /* renamed from: d  reason: collision with root package name */
    protected boolean f5255d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f5256e;

    /* renamed from: f  reason: collision with root package name */
    protected float f5257f;

    public BarBuffer(int i2, int i3, boolean z) {
        super(i2);
        this.f5255d = false;
        this.f5256e = false;
        this.f5257f = 1.0f;
        this.f5255d = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(float f2, float f3, float f4, float f5) {
        float[] fArr = this.buffer;
        int i2 = this.f5252a;
        int i3 = i2 + 1;
        this.f5252a = i3;
        fArr[i2] = f2;
        int i4 = i3 + 1;
        this.f5252a = i4;
        fArr[i3] = f3;
        int i5 = i4 + 1;
        this.f5252a = i5;
        fArr[i4] = f4;
        this.f5252a = i5 + 1;
        fArr[i5] = f5;
    }

    @Override // com.github.mikephil.charting.buffer.AbstractBuffer
    public void feed(IBarDataSet iBarDataSet) {
        float f2;
        float abs;
        float abs2;
        float f3;
        float entryCount = iBarDataSet.getEntryCount() * this.f5253b;
        float f4 = this.f5257f / 2.0f;
        for (int i2 = 0; i2 < entryCount; i2++) {
            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i2);
            if (barEntry != null) {
                float x = barEntry.getX();
                float y = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                if (!this.f5255d || yVals == null) {
                    float f5 = x - f4;
                    float f6 = x + f4;
                    if (this.f5256e) {
                        f2 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                    } else {
                        float f7 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                        float f8 = y;
                        y = f7;
                        f2 = f8;
                    }
                    if (y > 0.0f) {
                        y *= this.f5254c;
                    } else {
                        f2 *= this.f5254c;
                    }
                    a(f5, y, f6, f2);
                } else {
                    float f9 = -barEntry.getNegativeSum();
                    float f10 = 0.0f;
                    int i3 = 0;
                    while (i3 < yVals.length) {
                        float f11 = yVals[i3];
                        int i4 = (f11 > 0.0f ? 1 : (f11 == 0.0f ? 0 : -1));
                        if (i4 == 0 && (f10 == 0.0f || f9 == 0.0f)) {
                            abs = f11;
                            abs2 = f9;
                            f9 = abs;
                        } else if (i4 >= 0) {
                            abs = f11 + f10;
                            abs2 = f9;
                            f9 = f10;
                            f10 = abs;
                        } else {
                            abs = Math.abs(f11) + f9;
                            abs2 = Math.abs(f11) + f9;
                        }
                        float f12 = x - f4;
                        float f13 = x + f4;
                        if (this.f5256e) {
                            f3 = f9 >= abs ? f9 : abs;
                            if (f9 > abs) {
                                f9 = abs;
                            }
                        } else {
                            float f14 = f9 >= abs ? f9 : abs;
                            if (f9 > abs) {
                                f9 = abs;
                            }
                            float f15 = f9;
                            f9 = f14;
                            f3 = f15;
                        }
                        float f16 = this.f5254c;
                        a(f12, f9 * f16, f13, f3 * f16);
                        i3++;
                        f9 = abs2;
                    }
                }
            }
        }
        reset();
    }

    public void setBarWidth(float f2) {
        this.f5257f = f2;
    }

    public void setDataSet(int i2) {
    }

    public void setInverted(boolean z) {
        this.f5256e = z;
    }
}
