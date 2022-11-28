package com.github.mikephil.charting.data;

import android.graphics.Typeface;
import android.util.Log;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public abstract class ChartData<T extends IDataSet<? extends Entry>> {

    /* renamed from: a  reason: collision with root package name */
    protected float f5329a;

    /* renamed from: b  reason: collision with root package name */
    protected float f5330b;

    /* renamed from: c  reason: collision with root package name */
    protected float f5331c;

    /* renamed from: d  reason: collision with root package name */
    protected float f5332d;

    /* renamed from: e  reason: collision with root package name */
    protected float f5333e;

    /* renamed from: f  reason: collision with root package name */
    protected float f5334f;

    /* renamed from: g  reason: collision with root package name */
    protected float f5335g;

    /* renamed from: h  reason: collision with root package name */
    protected float f5336h;

    /* renamed from: i  reason: collision with root package name */
    protected List f5337i;

    public ChartData() {
        this.f5329a = -3.4028235E38f;
        this.f5330b = Float.MAX_VALUE;
        this.f5331c = -3.4028235E38f;
        this.f5332d = Float.MAX_VALUE;
        this.f5333e = -3.4028235E38f;
        this.f5334f = Float.MAX_VALUE;
        this.f5335g = -3.4028235E38f;
        this.f5336h = Float.MAX_VALUE;
        this.f5337i = new ArrayList();
    }

    public ChartData(List<T> list) {
        this.f5329a = -3.4028235E38f;
        this.f5330b = Float.MAX_VALUE;
        this.f5331c = -3.4028235E38f;
        this.f5332d = Float.MAX_VALUE;
        this.f5333e = -3.4028235E38f;
        this.f5334f = Float.MAX_VALUE;
        this.f5335g = -3.4028235E38f;
        this.f5336h = Float.MAX_VALUE;
        this.f5337i = list;
        notifyDataChanged();
    }

    public ChartData(T... tArr) {
        this.f5329a = -3.4028235E38f;
        this.f5330b = Float.MAX_VALUE;
        this.f5331c = -3.4028235E38f;
        this.f5332d = Float.MAX_VALUE;
        this.f5333e = -3.4028235E38f;
        this.f5334f = Float.MAX_VALUE;
        this.f5335g = -3.4028235E38f;
        this.f5336h = Float.MAX_VALUE;
        this.f5337i = arrayToList(tArr);
        notifyDataChanged();
    }

    private List<T> arrayToList(T[] tArr) {
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            arrayList.add(t2);
        }
        return arrayList;
    }

    protected void a(Entry entry, YAxis.AxisDependency axisDependency) {
        if (this.f5329a < entry.getY()) {
            this.f5329a = entry.getY();
        }
        if (this.f5330b > entry.getY()) {
            this.f5330b = entry.getY();
        }
        if (this.f5331c < entry.getX()) {
            this.f5331c = entry.getX();
        }
        if (this.f5332d > entry.getX()) {
            this.f5332d = entry.getX();
        }
        if (axisDependency == YAxis.AxisDependency.LEFT) {
            if (this.f5333e < entry.getY()) {
                this.f5333e = entry.getY();
            }
            if (this.f5334f > entry.getY()) {
                this.f5334f = entry.getY();
                return;
            }
            return;
        }
        if (this.f5335g < entry.getY()) {
            this.f5335g = entry.getY();
        }
        if (this.f5336h > entry.getY()) {
            this.f5336h = entry.getY();
        }
    }

    public void addDataSet(T t2) {
        if (t2 == null) {
            return;
        }
        b(t2);
        this.f5337i.add(t2);
    }

    public void addEntry(Entry entry, int i2) {
        if (this.f5337i.size() <= i2 || i2 < 0) {
            Log.e("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
            return;
        }
        IDataSet iDataSet = (IDataSet) this.f5337i.get(i2);
        if (iDataSet.addEntry(entry)) {
            a(entry, iDataSet.getAxisDependency());
        }
    }

    protected void b(IDataSet iDataSet) {
        if (this.f5329a < iDataSet.getYMax()) {
            this.f5329a = iDataSet.getYMax();
        }
        if (this.f5330b > iDataSet.getYMin()) {
            this.f5330b = iDataSet.getYMin();
        }
        if (this.f5331c < iDataSet.getXMax()) {
            this.f5331c = iDataSet.getXMax();
        }
        if (this.f5332d > iDataSet.getXMin()) {
            this.f5332d = iDataSet.getXMin();
        }
        if (iDataSet.getAxisDependency() == YAxis.AxisDependency.LEFT) {
            if (this.f5333e < iDataSet.getYMax()) {
                this.f5333e = iDataSet.getYMax();
            }
            if (this.f5334f > iDataSet.getYMin()) {
                this.f5334f = iDataSet.getYMin();
                return;
            }
            return;
        }
        if (this.f5335g < iDataSet.getYMax()) {
            this.f5335g = iDataSet.getYMax();
        }
        if (this.f5336h > iDataSet.getYMin()) {
            this.f5336h = iDataSet.getYMin();
        }
    }

    protected int c(List list, String str, boolean z) {
        int i2 = 0;
        if (z) {
            while (i2 < list.size()) {
                if (str.equalsIgnoreCase(((IDataSet) list.get(i2)).getLabel())) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        while (i2 < list.size()) {
            if (str.equals(((IDataSet) list.get(i2)).getLabel())) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void calcMinMax() {
        List<IDataSet> list = this.f5337i;
        if (list == null) {
            return;
        }
        this.f5329a = -3.4028235E38f;
        this.f5330b = Float.MAX_VALUE;
        this.f5331c = -3.4028235E38f;
        this.f5332d = Float.MAX_VALUE;
        for (IDataSet iDataSet : list) {
            b(iDataSet);
        }
        this.f5333e = -3.4028235E38f;
        this.f5334f = Float.MAX_VALUE;
        this.f5335g = -3.4028235E38f;
        this.f5336h = Float.MAX_VALUE;
        IDataSet d2 = d(this.f5337i);
        if (d2 != null) {
            this.f5333e = d2.getYMax();
            this.f5334f = d2.getYMin();
            for (IDataSet iDataSet2 : this.f5337i) {
                if (iDataSet2.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                    if (iDataSet2.getYMin() < this.f5334f) {
                        this.f5334f = iDataSet2.getYMin();
                    }
                    if (iDataSet2.getYMax() > this.f5333e) {
                        this.f5333e = iDataSet2.getYMax();
                    }
                }
            }
        }
        T firstRight = getFirstRight(this.f5337i);
        if (firstRight != null) {
            this.f5335g = firstRight.getYMax();
            this.f5336h = firstRight.getYMin();
            for (IDataSet iDataSet3 : this.f5337i) {
                if (iDataSet3.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
                    if (iDataSet3.getYMin() < this.f5336h) {
                        this.f5336h = iDataSet3.getYMin();
                    }
                    if (iDataSet3.getYMax() > this.f5335g) {
                        this.f5335g = iDataSet3.getYMax();
                    }
                }
            }
        }
    }

    public void calcMinMaxY(float f2, float f3) {
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.calcMinMaxY(f2, f3);
        }
        calcMinMax();
    }

    public void clearValues() {
        List list = this.f5337i;
        if (list != null) {
            list.clear();
        }
        notifyDataChanged();
    }

    public boolean contains(T t2) {
        for (IDataSet iDataSet : this.f5337i) {
            if (iDataSet.equals(t2)) {
                return true;
            }
        }
        return false;
    }

    protected IDataSet d(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            IDataSet iDataSet = (IDataSet) it.next();
            if (iDataSet.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                return iDataSet;
            }
        }
        return null;
    }

    public int[] getColors() {
        if (this.f5337i == null) {
            return null;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f5337i.size(); i3++) {
            i2 += ((IDataSet) this.f5337i.get(i3)).getColors().size();
        }
        int[] iArr = new int[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < this.f5337i.size(); i5++) {
            for (Integer num : ((IDataSet) this.f5337i.get(i5)).getColors()) {
                iArr[i4] = num.intValue();
                i4++;
            }
        }
        return iArr;
    }

    public T getDataSetByIndex(int i2) {
        List list = this.f5337i;
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return (T) this.f5337i.get(i2);
    }

    public T getDataSetByLabel(String str, boolean z) {
        int c2 = c(this.f5337i, str, z);
        if (c2 < 0 || c2 >= this.f5337i.size()) {
            return null;
        }
        return (T) this.f5337i.get(c2);
    }

    public int getDataSetCount() {
        List list = this.f5337i;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public T getDataSetForEntry(Entry entry) {
        if (entry == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.f5337i.size(); i2++) {
            T t2 = (T) this.f5337i.get(i2);
            for (int i3 = 0; i3 < t2.getEntryCount(); i3++) {
                if (entry.equalTo(t2.getEntryForXValue(entry.getX(), entry.getY()))) {
                    return t2;
                }
            }
        }
        return null;
    }

    public String[] getDataSetLabels() {
        String[] strArr = new String[this.f5337i.size()];
        for (int i2 = 0; i2 < this.f5337i.size(); i2++) {
            strArr[i2] = ((IDataSet) this.f5337i.get(i2)).getLabel();
        }
        return strArr;
    }

    public List<T> getDataSets() {
        return this.f5337i;
    }

    public int getEntryCount() {
        int i2 = 0;
        for (IDataSet iDataSet : this.f5337i) {
            i2 += iDataSet.getEntryCount();
        }
        return i2;
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        if (highlight.getDataSetIndex() >= this.f5337i.size()) {
            return null;
        }
        return ((IDataSet) this.f5337i.get(highlight.getDataSetIndex())).getEntryForXValue(highlight.getX(), highlight.getY());
    }

    public T getFirstRight(List<T> list) {
        for (T t2 : list) {
            if (t2.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
                return t2;
            }
        }
        return null;
    }

    public int getIndexOfDataSet(T t2) {
        return this.f5337i.indexOf(t2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T getMaxEntryCountSet() {
        List list = this.f5337i;
        if (list == null || list.isEmpty()) {
            return null;
        }
        T t2 = (T) this.f5337i.get(0);
        for (IDataSet iDataSet : this.f5337i) {
            if (iDataSet.getEntryCount() > t2.getEntryCount()) {
                t2 = iDataSet;
            }
        }
        return t2;
    }

    public float getXMax() {
        return this.f5331c;
    }

    public float getXMin() {
        return this.f5332d;
    }

    public float getYMax() {
        return this.f5329a;
    }

    public float getYMax(YAxis.AxisDependency axisDependency) {
        if (axisDependency == YAxis.AxisDependency.LEFT) {
            float f2 = this.f5333e;
            return f2 == -3.4028235E38f ? this.f5335g : f2;
        }
        float f3 = this.f5335g;
        return f3 == -3.4028235E38f ? this.f5333e : f3;
    }

    public float getYMin() {
        return this.f5330b;
    }

    public float getYMin(YAxis.AxisDependency axisDependency) {
        if (axisDependency == YAxis.AxisDependency.LEFT) {
            float f2 = this.f5334f;
            return f2 == Float.MAX_VALUE ? this.f5336h : f2;
        }
        float f3 = this.f5336h;
        return f3 == Float.MAX_VALUE ? this.f5334f : f3;
    }

    public boolean isHighlightEnabled() {
        for (IDataSet iDataSet : this.f5337i) {
            if (!iDataSet.isHighlightEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void notifyDataChanged() {
        calcMinMax();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean removeDataSet(int i2) {
        if (i2 >= this.f5337i.size() || i2 < 0) {
            return false;
        }
        return removeDataSet((ChartData<T>) ((IDataSet) this.f5337i.get(i2)));
    }

    public boolean removeDataSet(T t2) {
        if (t2 == null) {
            return false;
        }
        boolean remove = this.f5337i.remove(t2);
        if (remove) {
            calcMinMax();
        }
        return remove;
    }

    public boolean removeEntry(float f2, int i2) {
        Entry entryForXValue;
        if (i2 < this.f5337i.size() && (entryForXValue = ((IDataSet) this.f5337i.get(i2)).getEntryForXValue(f2, Float.NaN)) != null) {
            return removeEntry(entryForXValue, i2);
        }
        return false;
    }

    public boolean removeEntry(Entry entry, int i2) {
        IDataSet iDataSet;
        if (entry == null || i2 >= this.f5337i.size() || (iDataSet = (IDataSet) this.f5337i.get(i2)) == null) {
            return false;
        }
        boolean removeEntry = iDataSet.removeEntry((IDataSet) entry);
        if (removeEntry) {
            calcMinMax();
        }
        return removeEntry;
    }

    public void setDrawValues(boolean z) {
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.setDrawValues(z);
        }
    }

    public void setHighlightEnabled(boolean z) {
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.setHighlightEnabled(z);
        }
    }

    public void setValueFormatter(IValueFormatter iValueFormatter) {
        if (iValueFormatter == null) {
            return;
        }
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.setValueFormatter(iValueFormatter);
        }
    }

    public void setValueTextColor(int i2) {
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.setValueTextColor(i2);
        }
    }

    public void setValueTextColors(List<Integer> list) {
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.setValueTextColors(list);
        }
    }

    public void setValueTextSize(float f2) {
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.setValueTextSize(f2);
        }
    }

    public void setValueTypeface(Typeface typeface) {
        for (IDataSet iDataSet : this.f5337i) {
            iDataSet.setValueTypeface(typeface);
        }
    }
}
