package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class FloatArrayList extends AbstractProtobufList<Float> implements Internal.FloatList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final FloatArrayList EMPTY_LIST;
    private float[] array;
    private int size;

    static {
        FloatArrayList floatArrayList = new FloatArrayList(new float[0], 0);
        EMPTY_LIST = floatArrayList;
        floatArrayList.makeImmutable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatArrayList() {
        this(new float[10], 0);
    }

    private FloatArrayList(float[] fArr, int i2) {
        this.array = fArr;
        this.size = i2;
    }

    private void addFloat(int i2, float f2) {
        int i3;
        a();
        if (i2 < 0 || i2 > (i3 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i2));
        }
        float[] fArr = this.array;
        if (i3 < fArr.length) {
            System.arraycopy(fArr, i2, fArr, i2 + 1, i3 - i2);
        } else {
            float[] fArr2 = new float[((i3 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            System.arraycopy(this.array, i2, fArr2, i2 + 1, this.size - i2);
            this.array = fArr2;
        }
        this.array[i2] = f2;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    public static FloatArrayList emptyList() {
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
    public void add(int i2, Float f2) {
        addFloat(i2, f2.floatValue());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Float f2) {
        addFloat(f2.floatValue());
        return true;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Float> collection) {
        a();
        Internal.a(collection);
        if (collection instanceof FloatArrayList) {
            FloatArrayList floatArrayList = (FloatArrayList) collection;
            int i2 = floatArrayList.size;
            if (i2 == 0) {
                return false;
            }
            int i3 = this.size;
            if (Integer.MAX_VALUE - i3 >= i2) {
                int i4 = i3 + i2;
                float[] fArr = this.array;
                if (i4 > fArr.length) {
                    this.array = Arrays.copyOf(fArr, i4);
                }
                System.arraycopy(floatArrayList.array, 0, this.array, this.size, floatArrayList.size);
                this.size = i4;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.FloatList
    public void addFloat(float f2) {
        a();
        int i2 = this.size;
        float[] fArr = this.array;
        if (i2 == fArr.length) {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            this.array = fArr2;
        }
        float[] fArr3 = this.array;
        int i3 = this.size;
        this.size = i3 + 1;
        fArr3[i3] = f2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FloatArrayList) {
            FloatArrayList floatArrayList = (FloatArrayList) obj;
            if (this.size != floatArrayList.size) {
                return false;
            }
            float[] fArr = floatArrayList.array;
            for (int i2 = 0; i2 < this.size; i2++) {
                if (Float.floatToIntBits(this.array[i2]) != Float.floatToIntBits(fArr[i2])) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public Float get(int i2) {
        return Float.valueOf(getFloat(i2));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.FloatList
    public float getFloat(int i2) {
        ensureIndexInRange(i2);
        return this.array[i2];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.size; i3++) {
            i2 = (i2 * 31) + Float.floatToIntBits(this.array[i3]);
        }
        return i2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList, com.google.crypto.tink.shaded.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Float> mutableCopyWithCapacity2(int i2) {
        if (i2 >= this.size) {
            return new FloatArrayList(Arrays.copyOf(this.array, i2), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Float remove(int i2) {
        int i3;
        a();
        ensureIndexInRange(i2);
        float[] fArr = this.array;
        float f2 = fArr[i2];
        if (i2 < this.size - 1) {
            System.arraycopy(fArr, i2 + 1, fArr, i2, (i3 - i2) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        a();
        for (int i2 = 0; i2 < this.size; i2++) {
            if (obj.equals(Float.valueOf(this.array[i2]))) {
                float[] fArr = this.array;
                System.arraycopy(fArr, i2 + 1, fArr, i2, (this.size - i2) - 1);
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
        float[] fArr = this.array;
        System.arraycopy(fArr, i3, fArr, i2, this.size - i3);
        this.size -= i3 - i2;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Float set(int i2, Float f2) {
        return Float.valueOf(setFloat(i2, f2.floatValue()));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.FloatList
    public float setFloat(int i2, float f2) {
        a();
        ensureIndexInRange(i2);
        float[] fArr = this.array;
        float f3 = fArr[i2];
        fArr[i2] = f2;
        return f3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }
}
