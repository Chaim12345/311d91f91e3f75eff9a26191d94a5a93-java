package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public class ObjectCountHashMap<K> {
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;

    /* renamed from: a  reason: collision with root package name */
    transient Object[] f8903a;

    /* renamed from: b  reason: collision with root package name */
    transient int[] f8904b;

    /* renamed from: c  reason: collision with root package name */
    transient int f8905c;

    /* renamed from: d  reason: collision with root package name */
    transient int f8906d;
    @VisibleForTesting

    /* renamed from: e  reason: collision with root package name */
    transient long[] f8907e;
    private transient float loadFactor;
    private transient int[] table;
    private transient int threshold;

    /* loaded from: classes2.dex */
    class MapEntry extends Multisets.AbstractEntry<K> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8908a;

        /* renamed from: b  reason: collision with root package name */
        int f8909b;

        MapEntry(int i2) {
            this.f8908a = ObjectCountHashMap.this.f8903a[i2];
            this.f8909b = i2;
        }

        void a() {
            int i2 = this.f8909b;
            if (i2 == -1 || i2 >= ObjectCountHashMap.this.o() || !Objects.equal(this.f8908a, ObjectCountHashMap.this.f8903a[this.f8909b])) {
                this.f8909b = ObjectCountHashMap.this.f(this.f8908a);
            }
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            a();
            int i2 = this.f8909b;
            if (i2 == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.f8904b[i2];
        }

        @Override // com.google.common.collect.Multiset.Entry
        public K getElement() {
            return (K) this.f8908a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public int setCount(int i2) {
            a();
            int i3 = this.f8909b;
            if (i3 == -1) {
                ObjectCountHashMap.this.put(this.f8908a, i2);
                return 0;
            }
            int[] iArr = ObjectCountHashMap.this.f8904b;
            int i4 = iArr[i3];
            iArr[i3] = i2;
            return i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap() {
        g(3, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int i2) {
        this(i2, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int i2, float f2) {
        g(i2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public ObjectCountHashMap(ObjectCountHashMap objectCountHashMap) {
        g(objectCountHashMap.o(), 1.0f);
        int b2 = objectCountHashMap.b();
        while (b2 != -1) {
            put(objectCountHashMap.d(b2), objectCountHashMap.e(b2));
            b2 = objectCountHashMap.j(b2);
        }
    }

    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int i2) {
        return new ObjectCountHashMap<>(i2);
    }

    private static int getHash(long j2) {
        return (int) (j2 >>> 32);
    }

    private static int getNext(long j2) {
        return (int) j2;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    private static long[] newEntries(int i2) {
        long[] jArr = new long[i2];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private static int[] newTable(int i2) {
        int[] iArr = new int[i2];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private int remove(@NullableDecl Object obj, int i2) {
        int hashTableMask = hashTableMask() & i2;
        int i3 = this.table[hashTableMask];
        if (i3 == -1) {
            return 0;
        }
        int i4 = -1;
        while (true) {
            if (getHash(this.f8907e[i3]) == i2 && Objects.equal(obj, this.f8903a[i3])) {
                int i5 = this.f8904b[i3];
                if (i4 == -1) {
                    this.table[hashTableMask] = getNext(this.f8907e[i3]);
                } else {
                    long[] jArr = this.f8907e;
                    jArr[i4] = swapNext(jArr[i4], getNext(jArr[i3]));
                }
                i(i3);
                this.f8905c--;
                this.f8906d++;
                return i5;
            }
            int next = getNext(this.f8907e[i3]);
            if (next == -1) {
                return 0;
            }
            i4 = i3;
            i3 = next;
        }
    }

    private void resizeMeMaybe(int i2) {
        int length = this.f8907e.length;
        if (i2 > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                m(max);
            }
        }
    }

    private void resizeTable(int i2) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i3 = ((int) (i2 * this.loadFactor)) + 1;
        int[] newTable = newTable(i2);
        long[] jArr = this.f8907e;
        int length = newTable.length - 1;
        for (int i4 = 0; i4 < this.f8905c; i4++) {
            int hash = getHash(jArr[i4]);
            int i5 = hash & length;
            int i6 = newTable[i5];
            newTable[i5] = i4;
            jArr[i4] = (hash << 32) | (i6 & 4294967295L);
        }
        this.threshold = i3;
        this.table = newTable;
    }

    private static long swapNext(long j2, int i2) {
        return (j2 & HASH_MASK) | (i2 & 4294967295L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i2) {
        if (i2 > this.f8907e.length) {
            m(i2);
        }
        if (i2 >= this.threshold) {
            resizeTable(Math.max(2, Integer.highestOneBit(i2 - 1) << 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.f8905c == 0 ? -1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Multiset.Entry c(int i2) {
        Preconditions.checkElementIndex(i2, this.f8905c);
        return new MapEntry(i2);
    }

    public void clear() {
        this.f8906d++;
        Arrays.fill(this.f8903a, 0, this.f8905c, (Object) null);
        Arrays.fill(this.f8904b, 0, this.f8905c, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.f8907e, -1L);
        this.f8905c = 0;
    }

    public boolean containsKey(@NullableDecl Object obj) {
        return f(obj) != -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object d(int i2) {
        Preconditions.checkElementIndex(i2, this.f8905c);
        return this.f8903a[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e(int i2) {
        Preconditions.checkElementIndex(i2, this.f8905c);
        return this.f8904b[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(@NullableDecl Object obj) {
        int d2 = Hashing.d(obj);
        int i2 = this.table[hashTableMask() & d2];
        while (i2 != -1) {
            long j2 = this.f8907e[i2];
            if (getHash(j2) == d2 && Objects.equal(obj, this.f8903a[i2])) {
                return i2;
            }
            i2 = getNext(j2);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(int i2, float f2) {
        Preconditions.checkArgument(i2 >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f2 > 0.0f, "Illegal load factor");
        int a2 = Hashing.a(i2, f2);
        this.table = newTable(a2);
        this.loadFactor = f2;
        this.f8903a = new Object[i2];
        this.f8904b = new int[i2];
        this.f8907e = newEntries(i2);
        this.threshold = Math.max(1, (int) (a2 * f2));
    }

    public int get(@NullableDecl Object obj) {
        int f2 = f(obj);
        if (f2 == -1) {
            return 0;
        }
        return this.f8904b[f2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(int i2, @NullableDecl Object obj, int i3, int i4) {
        this.f8907e[i2] = (i4 << 32) | 4294967295L;
        this.f8903a[i2] = obj;
        this.f8904b[i2] = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(int i2) {
        int o2 = o() - 1;
        if (i2 >= o2) {
            this.f8903a[i2] = null;
            this.f8904b[i2] = 0;
            this.f8907e[i2] = -1;
            return;
        }
        Object[] objArr = this.f8903a;
        objArr[i2] = objArr[o2];
        int[] iArr = this.f8904b;
        iArr[i2] = iArr[o2];
        objArr[o2] = null;
        iArr[o2] = 0;
        long[] jArr = this.f8907e;
        long j2 = jArr[o2];
        jArr[i2] = j2;
        jArr[o2] = -1;
        int hash = getHash(j2) & hashTableMask();
        int[] iArr2 = this.table;
        int i3 = iArr2[hash];
        if (i3 == o2) {
            iArr2[hash] = i2;
            return;
        }
        while (true) {
            long j3 = this.f8907e[i3];
            int next = getNext(j3);
            if (next == o2) {
                this.f8907e[i3] = swapNext(j3, i2);
                return;
            }
            i3 = next;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.f8905c) {
            return i3;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k(int i2, int i3) {
        return i2 - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public int l(int i2) {
        return remove(this.f8903a[i2], getHash(this.f8907e[i2]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(int i2) {
        this.f8903a = Arrays.copyOf(this.f8903a, i2);
        this.f8904b = Arrays.copyOf(this.f8904b, i2);
        long[] jArr = this.f8907e;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i2);
        if (i2 > length) {
            Arrays.fill(copyOf, length, i2, -1L);
        }
        this.f8907e = copyOf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(int i2, int i3) {
        Preconditions.checkElementIndex(i2, this.f8905c);
        this.f8904b[i2] = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int o() {
        return this.f8905c;
    }

    @CanIgnoreReturnValue
    public int put(@NullableDecl K k2, int i2) {
        CollectPreconditions.d(i2, "count");
        long[] jArr = this.f8907e;
        Object[] objArr = this.f8903a;
        int[] iArr = this.f8904b;
        int d2 = Hashing.d(k2);
        int hashTableMask = hashTableMask() & d2;
        int i3 = this.f8905c;
        int[] iArr2 = this.table;
        int i4 = iArr2[hashTableMask];
        if (i4 == -1) {
            iArr2[hashTableMask] = i3;
        } else {
            while (true) {
                long j2 = jArr[i4];
                if (getHash(j2) == d2 && Objects.equal(k2, objArr[i4])) {
                    int i5 = iArr[i4];
                    iArr[i4] = i2;
                    return i5;
                }
                int next = getNext(j2);
                if (next == -1) {
                    jArr[i4] = swapNext(j2, i3);
                    break;
                }
                i4 = next;
            }
        }
        if (i3 != Integer.MAX_VALUE) {
            int i6 = i3 + 1;
            resizeMeMaybe(i6);
            h(i3, k2, i2, d2);
            this.f8905c = i6;
            if (i3 >= this.threshold) {
                resizeTable(this.table.length * 2);
            }
            this.f8906d++;
            return 0;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj) {
        return remove(obj, Hashing.d(obj));
    }
}
