package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.List;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry> extends DataSet<T> implements IBarLineScatterCandleBubbleDataSet<T> {

    /* renamed from: s  reason: collision with root package name */
    protected int f5314s;

    public BarLineScatterCandleBubbleDataSet(List<T> list, String str) {
        super(list, str);
        this.f5314s = Color.rgb(255, (int) CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, 115);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(BarLineScatterCandleBubbleDataSet barLineScatterCandleBubbleDataSet) {
        super.e(barLineScatterCandleBubbleDataSet);
        barLineScatterCandleBubbleDataSet.f5314s = this.f5314s;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet
    public int getHighLightColor() {
        return this.f5314s;
    }

    public void setHighLightColor(int i2) {
        this.f5314s = i2;
    }
}
