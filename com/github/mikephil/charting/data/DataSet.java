package com.github.mikephil.charting.data;

import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class DataSet<T extends Entry> extends BaseDataSet<T> {

    /* renamed from: n  reason: collision with root package name */
    protected List f5338n;

    /* renamed from: o  reason: collision with root package name */
    protected float f5339o;

    /* renamed from: p  reason: collision with root package name */
    protected float f5340p;

    /* renamed from: q  reason: collision with root package name */
    protected float f5341q;

    /* renamed from: r  reason: collision with root package name */
    protected float f5342r;

    /* loaded from: classes.dex */
    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }

    public DataSet(List<T> list, String str) {
        super(str);
        this.f5338n = null;
        this.f5339o = -3.4028235E38f;
        this.f5340p = Float.MAX_VALUE;
        this.f5341q = -3.4028235E38f;
        this.f5342r = Float.MAX_VALUE;
        this.f5338n = list;
        if (list == null) {
            this.f5338n = new ArrayList();
        }
        calcMinMax();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean addEntry(T t2) {
        if (t2 == null) {
            return false;
        }
        List<T> values = getValues();
        if (values == null) {
            values = new ArrayList<>();
        }
        b(t2);
        return values.add(t2);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void addEntryOrdered(T t2) {
        if (t2 == null) {
            return;
        }
        if (this.f5338n == null) {
            this.f5338n = new ArrayList();
        }
        b(t2);
        if (this.f5338n.size() > 0) {
            List list = this.f5338n;
            if (((Entry) list.get(list.size() - 1)).getX() > t2.getX()) {
                this.f5338n.add(getEntryIndex(t2.getX(), t2.getY(), Rounding.UP), t2);
                return;
            }
        }
        this.f5338n.add(t2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Entry entry) {
        if (entry == null) {
            return;
        }
        c(entry);
        d(entry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Entry entry) {
        if (entry.getX() < this.f5342r) {
            this.f5342r = entry.getX();
        }
        if (entry.getX() > this.f5341q) {
            this.f5341q = entry.getX();
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMax() {
        List list = this.f5338n;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.f5339o = -3.4028235E38f;
        this.f5340p = Float.MAX_VALUE;
        this.f5341q = -3.4028235E38f;
        this.f5342r = Float.MAX_VALUE;
        for (Entry entry : this.f5338n) {
            b(entry);
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMaxY(float f2, float f3) {
        List list = this.f5338n;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.f5339o = -3.4028235E38f;
        this.f5340p = Float.MAX_VALUE;
        int entryIndex = getEntryIndex(f3, Float.NaN, Rounding.UP);
        for (int entryIndex2 = getEntryIndex(f2, Float.NaN, Rounding.DOWN); entryIndex2 <= entryIndex; entryIndex2++) {
            d((Entry) this.f5338n.get(entryIndex2));
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void clear() {
        this.f5338n.clear();
        notifyDataSetChanged();
    }

    public abstract DataSet<T> copy();

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Entry entry) {
        if (entry.getY() < this.f5340p) {
            this.f5340p = entry.getY();
        }
        if (entry.getY() > this.f5339o) {
            this.f5339o = entry.getY();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(DataSet dataSet) {
        super.a(dataSet);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<T> getEntriesForXValue(float f2) {
        ArrayList arrayList = new ArrayList();
        int size = this.f5338n.size() - 1;
        int i2 = 0;
        while (true) {
            if (i2 > size) {
                break;
            }
            int i3 = (size + i2) / 2;
            Entry entry = (Entry) this.f5338n.get(i3);
            if (f2 == entry.getX()) {
                while (i3 > 0 && ((Entry) this.f5338n.get(i3 - 1)).getX() == f2) {
                    i3--;
                }
                int size2 = this.f5338n.size();
                while (i3 < size2) {
                    Entry entry2 = (Entry) this.f5338n.get(i3);
                    if (entry2.getX() != f2) {
                        break;
                    }
                    arrayList.add(entry2);
                    i3++;
                }
            } else if (f2 > entry.getX()) {
                i2 = i3 + 1;
            } else {
                size = i3 - 1;
            }
        }
        return arrayList;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryCount() {
        return this.f5338n.size();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForIndex(int i2) {
        return (T) this.f5338n.get(i2);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForXValue(float f2, float f3) {
        return getEntryForXValue(f2, f3, Rounding.CLOSEST);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForXValue(float f2, float f3, Rounding rounding) {
        int entryIndex = getEntryIndex(f2, f3, rounding);
        if (entryIndex > -1) {
            return (T) this.f5338n.get(entryIndex);
        }
        return null;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryIndex(float f2, float f3, Rounding rounding) {
        int i2;
        Entry entry;
        List list = this.f5338n;
        if (list == null || list.isEmpty()) {
            return -1;
        }
        int i3 = 0;
        int size = this.f5338n.size() - 1;
        while (i3 < size) {
            int i4 = (i3 + size) / 2;
            float x = ((Entry) this.f5338n.get(i4)).getX() - f2;
            int i5 = i4 + 1;
            float abs = Math.abs(x);
            float abs2 = Math.abs(((Entry) this.f5338n.get(i5)).getX() - f2);
            if (abs2 >= abs) {
                if (abs >= abs2) {
                    double d2 = x;
                    if (d2 < 0.0d) {
                        if (d2 < 0.0d) {
                        }
                    }
                }
                size = i4;
            }
            i3 = i5;
        }
        if (size != -1) {
            float x2 = ((Entry) this.f5338n.get(size)).getX();
            if (rounding == Rounding.UP) {
                if (x2 < f2 && size < this.f5338n.size() - 1) {
                    size++;
                }
            } else if (rounding == Rounding.DOWN && x2 > f2 && size > 0) {
                size--;
            }
            if (Float.isNaN(f3)) {
                return size;
            }
            while (size > 0 && ((Entry) this.f5338n.get(size - 1)).getX() == x2) {
                size--;
            }
            float y = ((Entry) this.f5338n.get(size)).getY();
            loop2: while (true) {
                i2 = size;
                do {
                    size++;
                    if (size >= this.f5338n.size()) {
                        break loop2;
                    }
                    entry = (Entry) this.f5338n.get(size);
                    if (entry.getX() != x2) {
                        break loop2;
                    }
                } while (Math.abs(entry.getY() - f3) >= Math.abs(y - f3));
                y = f3;
            }
            return i2;
        }
        return size;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryIndex(Entry entry) {
        return this.f5338n.indexOf(entry);
    }

    public List<T> getValues() {
        return this.f5338n;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getXMax() {
        return this.f5341q;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getXMin() {
        return this.f5342r;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getYMax() {
        return this.f5339o;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getYMin() {
        return this.f5340p;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean removeEntry(T t2) {
        List list;
        if (t2 == null || (list = this.f5338n) == null) {
            return false;
        }
        boolean remove = list.remove(t2);
        if (remove) {
            calcMinMax();
        }
        return remove;
    }

    public void setValues(List<T> list) {
        this.f5338n = list;
        notifyDataSetChanged();
    }

    public String toSimpleString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        sb.append("DataSet, label: ");
        sb.append(getLabel() == null ? "" : getLabel());
        sb.append(", entries: ");
        sb.append(this.f5338n.size());
        sb.append("\n");
        stringBuffer.append(sb.toString());
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(toSimpleString());
        for (int i2 = 0; i2 < this.f5338n.size(); i2++) {
            stringBuffer.append(((Entry) this.f5338n.get(i2)).toString() + " ");
        }
        return stringBuffer.toString();
    }
}
