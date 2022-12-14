package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class DoubleArrayList extends AbstractProtobufList<Double> implements Internal.DoubleList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final DoubleArrayList EMPTY_LIST;
    private double[] array;
    private int size;

    static {
        DoubleArrayList doubleArrayList = new DoubleArrayList(new double[0], 0);
        EMPTY_LIST = doubleArrayList;
        doubleArrayList.makeImmutable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DoubleArrayList() {
        this(new double[10], 0);
    }

    private DoubleArrayList(double[] dArr, int i2) {
        this.array = dArr;
        this.size = i2;
    }

    private void addDouble(int i2, double d2) {
        int i3;
        a();
        if (i2 < 0 || i2 > (i3 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i2));
        }
        double[] dArr = this.array;
        if (i3 < dArr.length) {
            System.arraycopy(dArr, i2, dArr, i2 + 1, i3 - i2);
        } else {
            double[] dArr2 = new double[((i3 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            System.arraycopy(this.array, i2, dArr2, i2 + 1, this.size - i2);
            this.array = dArr2;
        }
        this.array[i2] = d2;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    public static DoubleArrayList emptyList() {
        return EMPTY_LIST;
    }

    private void ensureIndexInRange(int i2) {
        if (i2 < 0 || i2 >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i2));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i2) {
        return "Index:" + i2 + ", Size:" + this.size;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i2, Double d2) {
        addDouble(i2, d2.doubleValue());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Double d2) {
        addDouble(d2.doubleValue());
        return true;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Double> collection) {
        a();
        Internal.a(collection);
        if (collection instanceof DoubleArrayList) {
            DoubleArrayList doubleArrayList = (DoubleArrayList) collection;
            int i2 = doubleArrayList.size;
            if (i2 == 0) {
                return false;
            }
            int i3 = this.size;
            if (Integer.MAX_VALUE - i3 >= i2) {
                int i4 = i3 + i2;
                double[] dArr = this.array;
                if (i4 > dArr.length) {
                    this.array = Arrays.copyOf(dArr, i4);
                }
                System.arraycopy(doubleArrayList.array, 0, this.array, this.size, doubleArrayList.size);
                this.size = i4;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.DoubleList
    public void addDouble(double d2) {
        a();
        int i2 = this.size;
        double[] dArr = this.array;
        if (i2 == dArr.length) {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            this.array = dArr2;
        }
        double[] dArr3 = this.array;
        int i3 = this.size;
        this.size = i3 + 1;
        dArr3[i3] = d2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DoubleArrayList) {
            DoubleArrayList doubleArrayList = (DoubleArrayList) obj;
            if (this.size != doubleArrayList.size) {
                return false;
            }
            double[] dArr = doubleArrayList.array;
            for (int i2 = 0; i2 < this.size; i2++) {
                if (Double.doubleToLongBits(this.array[i2]) != Double.doubleToLongBits(dArr[i2])) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public Double get(int i2) {
        return Double.valueOf(getDouble(i2));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.DoubleList
    public double getDouble(int i2) {
        ensureIndexInRange(i2);
        return this.array[i2];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.size; i3++) {
            i2 = (i2 * 31) + Internal.hashLong(Double.doubleToLongBits(this.array[i3]));
        }
        return i2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList, com.google.crypto.tink.shaded.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Double> mutableCopyWithCapacity2(int i2) {
        if (i2 >= this.size) {
            return new DoubleArrayList(Arrays.copyOf(this.array, i2), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Double remove(int i2) {
        int i3;
        a();
        ensureIndexInRange(i2);
        double[] dArr = this.array;
        double d2 = dArr[i2];
        if (i2 < this.size - 1) {
            System.arraycopy(dArr, i2 + 1, dArr, i2, (i3 - i2) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        a();
        for (int i2 = 0; i2 < this.size; i2++) {
            if (obj.equals(Double.valueOf(this.array[i2]))) {
                double[] dArr = this.array;
                System.arraycopy(dArr, i2 + 1, dArr, i2, (this.size - i2) - 1);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    protected void removeRange(int i2, int i3) {
        a();
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.array;
        System.arraycopy(dArr, i3, dArr, i2, this.size - i3);
        this.size -= i3 - i2;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Double set(int i2, Double d2) {
        return Double.valueOf(setDouble(i2, d2.doubleValue()));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.DoubleList
    public double setDouble(int i2, double d2) {
        a();
        ensureIndexInRange(i2);
        double[] dArr = this.array;
        double d3 = dArr[i2];
        dArr[i2] = d2;
        return d3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }
}
