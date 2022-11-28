package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
/* loaded from: classes.dex */
public class HorizontalBarBuffer extends BarBuffer {
    public HorizontalBarBuffer(int i2, int i3, boolean z) {
        super(i2, i3, z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.mikephil.charting.buffer.BarBuffer, com.github.mikephil.charting.buffer.AbstractBuffer
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
                    a(f2, f6, y, f5);
                } else {
                    float f9 = -barEntry.getNegativeSum();
                    float f10 = 0.0f;
                    int i3 = 0;
                    while (i3 < yVals.length) {
                        float f11 = yVals[i3];
                        if (f11 >= 0.0f) {
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
                        a(f3 * f16, f13, f9 * f16, f12);
                        i3++;
                        f9 = abs2;
                    }
                }
            }
        }
        reset();
    }
}
