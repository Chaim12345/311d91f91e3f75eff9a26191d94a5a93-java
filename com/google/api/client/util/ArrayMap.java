package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/* loaded from: classes2.dex */
public class ArrayMap<K, V> extends AbstractMap<K, V> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    int f8070a;
    private Object[] data;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class Entry implements Map.Entry<K, V> {
        private int index;

        Entry(int i2) {
            this.index = i2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return Objects.equal(getKey(), entry.getKey()) && Objects.equal(getValue(), entry.getValue());
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) ArrayMap.this.getKey(this.index);
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return (V) ArrayMap.this.getValue(this.index);
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object key = getKey();
            Object value = getValue();
            return (key != null ? key.hashCode() : 0) ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            return (V) ArrayMap.this.set(this.index, v);
        }
    }

    /* loaded from: classes2.dex */
    final class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private int nextIndex;
        private boolean removed;

        EntryIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < ArrayMap.this.f8070a;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            int i2 = this.nextIndex;
            ArrayMap arrayMap = ArrayMap.this;
            if (i2 != arrayMap.f8070a) {
                this.nextIndex = i2 + 1;
                this.removed = false;
                return new Entry(i2);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i2 = this.nextIndex - 1;
            if (this.removed || i2 < 0) {
                throw new IllegalArgumentException();
            }
            ArrayMap.this.remove(i2);
            this.nextIndex--;
            this.removed = true;
        }
    }

    /* loaded from: classes2.dex */
    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ArrayMap.this.f8070a;
        }
    }

    public static <K, V> ArrayMap<K, V> create() {
        return new ArrayMap<>();
    }

    public static <K, V> ArrayMap<K, V> create(int i2) {
        ArrayMap<K, V> create = create();
        create.ensureCapacity(i2);
        return create;
    }

    private int getDataIndexOfKey(Object obj) {
        int i2 = this.f8070a << 1;
        Object[] objArr = this.data;
        for (int i3 = 0; i3 < i2; i3 += 2) {
            Object obj2 = objArr[i3];
            if (obj == null) {
                if (obj2 == null) {
                    return i3;
                }
            } else if (obj.equals(obj2)) {
                return i3;
            }
        }
        return -2;
    }

    public static <K, V> ArrayMap<K, V> of(Object... objArr) {
        ArrayMap<K, V> create = create(1);
        int length = objArr.length;
        if (1 == length % 2) {
            throw new IllegalArgumentException("missing value for last key: " + objArr[length - 1]);
        }
        create.f8070a = objArr.length / 2;
        Object[] objArr2 = new Object[length];
        ((ArrayMap) create).data = objArr2;
        System.arraycopy(objArr, 0, objArr2, 0, length);
        return create;
    }

    private V removeFromDataIndexOfKey(int i2) {
        int i3 = this.f8070a << 1;
        if (i2 < 0 || i2 >= i3) {
            return null;
        }
        V valueAtDataIndex = valueAtDataIndex(i2 + 1);
        Object[] objArr = this.data;
        int i4 = (i3 - i2) - 2;
        if (i4 != 0) {
            System.arraycopy(objArr, i2 + 2, objArr, i2, i4);
        }
        this.f8070a--;
        setData(i3 - 2, null, null);
        return valueAtDataIndex;
    }

    private void setData(int i2, K k2, V v) {
        Object[] objArr = this.data;
        objArr[i2] = k2;
        objArr[i2 + 1] = v;
    }

    private void setDataCapacity(int i2) {
        if (i2 == 0) {
            this.data = null;
            return;
        }
        int i3 = this.f8070a;
        Object[] objArr = this.data;
        if (i3 == 0 || i2 != objArr.length) {
            Object[] objArr2 = new Object[i2];
            this.data = objArr2;
            if (i3 != 0) {
                System.arraycopy(objArr, 0, objArr2, 0, i3 << 1);
            }
        }
    }

    private V valueAtDataIndex(int i2) {
        if (i2 < 0) {
            return null;
        }
        return (V) this.data[i2];
    }

    public final void add(K k2, V v) {
        set(this.f8070a, k2, v);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f8070a = 0;
        this.data = null;
    }

    @Override // java.util.AbstractMap
    public ArrayMap<K, V> clone() {
        try {
            ArrayMap<K, V> arrayMap = (ArrayMap) super.clone();
            Object[] objArr = this.data;
            if (objArr != null) {
                int length = objArr.length;
                Object[] objArr2 = new Object[length];
                arrayMap.data = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
            }
            return arrayMap;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return -2 != getDataIndexOfKey(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        int i2 = this.f8070a << 1;
        Object[] objArr = this.data;
        for (int i3 = 1; i3 < i2; i3 += 2) {
            Object obj2 = objArr[i3];
            if (obj == null) {
                if (obj2 == null) {
                    return true;
                }
            } else if (obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    public final void ensureCapacity(int i2) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] objArr = this.data;
        int i3 = i2 << 1;
        int length = objArr == null ? 0 : objArr.length;
        if (i3 > length) {
            int i4 = ((length / 2) * 3) + 1;
            if (i4 % 2 != 0) {
                i4++;
            }
            if (i4 >= i3) {
                i3 = i4;
            }
            setDataCapacity(i3);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        return valueAtDataIndex(getDataIndexOfKey(obj) + 1);
    }

    public final int getIndexOfKey(K k2) {
        return getDataIndexOfKey(k2) >> 1;
    }

    public final K getKey(int i2) {
        if (i2 < 0 || i2 >= this.f8070a) {
            return null;
        }
        return (K) this.data[i2 << 1];
    }

    public final V getValue(int i2) {
        if (i2 < 0 || i2 >= this.f8070a) {
            return null;
        }
        return valueAtDataIndex((i2 << 1) + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V put(K k2, V v) {
        int indexOfKey = getIndexOfKey(k2);
        if (indexOfKey == -1) {
            indexOfKey = this.f8070a;
        }
        return set(indexOfKey, k2, v);
    }

    public final V remove(int i2) {
        return removeFromDataIndexOfKey(i2 << 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        return removeFromDataIndexOfKey(getDataIndexOfKey(obj));
    }

    public final V set(int i2, V v) {
        int i3 = this.f8070a;
        if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = (i2 << 1) + 1;
        V valueAtDataIndex = valueAtDataIndex(i4);
        this.data[i4] = v;
        return valueAtDataIndex;
    }

    public final V set(int i2, K k2, V v) {
        if (i2 >= 0) {
            int i3 = i2 + 1;
            ensureCapacity(i3);
            int i4 = i2 << 1;
            V valueAtDataIndex = valueAtDataIndex(i4 + 1);
            setData(i4, k2, v);
            if (i3 > this.f8070a) {
                this.f8070a = i3;
            }
            return valueAtDataIndex;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f8070a;
    }

    public final void trim() {
        setDataCapacity(this.f8070a << 1);
    }
}
