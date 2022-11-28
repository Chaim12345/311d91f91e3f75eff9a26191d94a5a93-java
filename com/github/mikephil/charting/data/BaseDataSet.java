package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public abstract class BaseDataSet<T extends Entry> implements IDataSet<T> {

    /* renamed from: a  reason: collision with root package name */
    protected List f5315a;

    /* renamed from: b  reason: collision with root package name */
    protected GradientColor f5316b;

    /* renamed from: c  reason: collision with root package name */
    protected List f5317c;

    /* renamed from: d  reason: collision with root package name */
    protected List f5318d;

    /* renamed from: e  reason: collision with root package name */
    protected YAxis.AxisDependency f5319e;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f5320f;

    /* renamed from: g  reason: collision with root package name */
    protected transient IValueFormatter f5321g;

    /* renamed from: h  reason: collision with root package name */
    protected Typeface f5322h;

    /* renamed from: i  reason: collision with root package name */
    protected boolean f5323i;

    /* renamed from: j  reason: collision with root package name */
    protected boolean f5324j;

    /* renamed from: k  reason: collision with root package name */
    protected MPPointF f5325k;

    /* renamed from: l  reason: collision with root package name */
    protected float f5326l;

    /* renamed from: m  reason: collision with root package name */
    protected boolean f5327m;
    private Legend.LegendForm mForm;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private String mLabel;

    public BaseDataSet() {
        this.f5315a = null;
        this.f5316b = null;
        this.f5317c = null;
        this.f5318d = null;
        this.mLabel = "DataSet";
        this.f5319e = YAxis.AxisDependency.LEFT;
        this.f5320f = true;
        this.mForm = Legend.LegendForm.DEFAULT;
        this.mFormSize = Float.NaN;
        this.mFormLineWidth = Float.NaN;
        this.mFormLineDashEffect = null;
        this.f5323i = true;
        this.f5324j = true;
        this.f5325k = new MPPointF();
        this.f5326l = 17.0f;
        this.f5327m = true;
        this.f5315a = new ArrayList();
        this.f5318d = new ArrayList();
        this.f5315a.add(Integer.valueOf(Color.rgb((int) CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, 234, 255)));
        this.f5318d.add(Integer.valueOf((int) ViewCompat.MEASURED_STATE_MASK));
    }

    public BaseDataSet(String str) {
        this();
        this.mLabel = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(BaseDataSet baseDataSet) {
        baseDataSet.f5319e = this.f5319e;
        baseDataSet.f5315a = this.f5315a;
        baseDataSet.f5324j = this.f5324j;
        baseDataSet.f5323i = this.f5323i;
        baseDataSet.mForm = this.mForm;
        baseDataSet.mFormLineDashEffect = this.mFormLineDashEffect;
        baseDataSet.mFormLineWidth = this.mFormLineWidth;
        baseDataSet.mFormSize = this.mFormSize;
        baseDataSet.f5316b = this.f5316b;
        baseDataSet.f5317c = this.f5317c;
        baseDataSet.f5320f = this.f5320f;
        baseDataSet.f5325k = this.f5325k;
        baseDataSet.f5318d = this.f5318d;
        baseDataSet.f5321g = this.f5321g;
        baseDataSet.f5318d = this.f5318d;
        baseDataSet.f5326l = this.f5326l;
        baseDataSet.f5327m = this.f5327m;
    }

    public void addColor(int i2) {
        if (this.f5315a == null) {
            this.f5315a = new ArrayList();
        }
        this.f5315a.add(Integer.valueOf(i2));
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean contains(T t2) {
        for (int i2 = 0; i2 < getEntryCount(); i2++) {
            if (getEntryForIndex(i2).equals(t2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public YAxis.AxisDependency getAxisDependency() {
        return this.f5319e;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getColor() {
        return ((Integer) this.f5315a.get(0)).intValue();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getColor(int i2) {
        List list = this.f5315a;
        return ((Integer) list.get(i2 % list.size())).intValue();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<Integer> getColors() {
        return this.f5315a;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public Legend.LegendForm getForm() {
        return this.mForm;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getFormSize() {
        return this.mFormSize;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public GradientColor getGradientColor() {
        return this.f5316b;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public GradientColor getGradientColor(int i2) {
        List list = this.f5317c;
        return (GradientColor) list.get(i2 % list.size());
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<GradientColor> getGradientColors() {
        return this.f5317c;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public MPPointF getIconsOffset() {
        return this.f5325k;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getIndexInEntries(int i2) {
        for (int i3 = 0; i3 < getEntryCount(); i3++) {
            if (i2 == getEntryForIndex(i3).getX()) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public String getLabel() {
        return this.mLabel;
    }

    public List<Integer> getValueColors() {
        return this.f5318d;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public IValueFormatter getValueFormatter() {
        return needsFormatter() ? Utils.getDefaultValueFormatter() : this.f5321g;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getValueTextColor() {
        return ((Integer) this.f5318d.get(0)).intValue();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getValueTextColor(int i2) {
        List list = this.f5318d;
        return ((Integer) list.get(i2 % list.size())).intValue();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getValueTextSize() {
        return this.f5326l;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public Typeface getValueTypeface() {
        return this.f5322h;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean isDrawIconsEnabled() {
        return this.f5324j;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean isDrawValuesEnabled() {
        return this.f5323i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean isHighlightEnabled() {
        return this.f5320f;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean isVisible() {
        return this.f5327m;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean needsFormatter() {
        return this.f5321g == null;
    }

    public void notifyDataSetChanged() {
        calcMinMax();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean removeEntry(int i2) {
        return removeEntry((BaseDataSet<T>) getEntryForIndex(i2));
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean removeEntryByXValue(float f2) {
        return removeEntry((BaseDataSet<T>) getEntryForXValue(f2, Float.NaN));
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean removeFirst() {
        if (getEntryCount() > 0) {
            return removeEntry((BaseDataSet<T>) getEntryForIndex(0));
        }
        return false;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean removeLast() {
        if (getEntryCount() > 0) {
            return removeEntry((BaseDataSet<T>) getEntryForIndex(getEntryCount() - 1));
        }
        return false;
    }

    public void resetColors() {
        if (this.f5315a == null) {
            this.f5315a = new ArrayList();
        }
        this.f5315a.clear();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setAxisDependency(YAxis.AxisDependency axisDependency) {
        this.f5319e = axisDependency;
    }

    public void setColor(int i2) {
        resetColors();
        this.f5315a.add(Integer.valueOf(i2));
    }

    public void setColor(int i2, int i3) {
        setColor(Color.argb(i3, Color.red(i2), Color.green(i2), Color.blue(i2)));
    }

    public void setColors(List<Integer> list) {
        this.f5315a = list;
    }

    public void setColors(int... iArr) {
        this.f5315a = ColorTemplate.createColors(iArr);
    }

    public void setColors(int[] iArr, int i2) {
        resetColors();
        for (int i3 : iArr) {
            addColor(Color.argb(i2, Color.red(i3), Color.green(i3), Color.blue(i3)));
        }
    }

    public void setColors(int[] iArr, Context context) {
        if (this.f5315a == null) {
            this.f5315a = new ArrayList();
        }
        this.f5315a.clear();
        for (int i2 : iArr) {
            this.f5315a.add(Integer.valueOf(context.getResources().getColor(i2)));
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setDrawIcons(boolean z) {
        this.f5324j = z;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setDrawValues(boolean z) {
        this.f5323i = z;
    }

    public void setForm(Legend.LegendForm legendForm) {
        this.mForm = legendForm;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public void setFormLineWidth(float f2) {
        this.mFormLineWidth = f2;
    }

    public void setFormSize(float f2) {
        this.mFormSize = f2;
    }

    public void setGradientColor(int i2, int i3) {
        this.f5316b = new GradientColor(i2, i3);
    }

    public void setGradientColors(List<GradientColor> list) {
        this.f5317c = list;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setHighlightEnabled(boolean z) {
        this.f5320f = z;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setIconsOffset(MPPointF mPPointF) {
        MPPointF mPPointF2 = this.f5325k;
        mPPointF2.x = mPPointF.x;
        mPPointF2.y = mPPointF.y;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setLabel(String str) {
        this.mLabel = str;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setValueFormatter(IValueFormatter iValueFormatter) {
        if (iValueFormatter == null) {
            return;
        }
        this.f5321g = iValueFormatter;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setValueTextColor(int i2) {
        this.f5318d.clear();
        this.f5318d.add(Integer.valueOf(i2));
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setValueTextColors(List<Integer> list) {
        this.f5318d = list;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setValueTextSize(float f2) {
        this.f5326l = Utils.convertDpToPixel(f2);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setValueTypeface(Typeface typeface) {
        this.f5322h = typeface;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void setVisible(boolean z) {
        this.f5327m = z;
    }
}
