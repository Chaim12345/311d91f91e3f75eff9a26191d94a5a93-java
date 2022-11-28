package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import kotlin.UShort;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final byte ABSENT = -1;
    private static final int BYTE_MASK = 255;
    private static final int BYTE_MAX_SIZE = 128;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_MAX_SIZE = 32768;

    /* renamed from: c  reason: collision with root package name */
    static final ImmutableMap f8931c = new RegularImmutableMap(null, new Object[0], 0);
    private static final long serialVersionUID = 0;
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    final transient Object[] f8932b;
    private final transient Object hashTable;
    private final transient int size;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int keyOffset;
        private final transient ImmutableMap<K, V> map;
        private final transient int size;

        /* JADX INFO: Access modifiers changed from: package-private */
        public EntrySet(ImmutableMap immutableMap, Object[] objArr, int i2, int i3) {
            this.map = immutableMap;
            this.alternatingKeysAndValues = objArr;
            this.keyOffset = i2;
            this.size = i3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public int a(Object[] objArr, int i2) {
            return asList().a(objArr, i2);
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                return value != null && value.equals(this.map.get(key));
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList h() {
            return new ImmutableList<Map.Entry<K, V>>() { // from class: com.google.common.collect.RegularImmutableMap.EntrySet.1
                @Override // java.util.List
                public Map.Entry<K, V> get(int i2) {
                    Preconditions.checkElementIndex(i2, EntrySet.this.size);
                    int i3 = i2 * 2;
                    return new AbstractMap.SimpleImmutableEntry(EntrySet.this.alternatingKeysAndValues[EntrySet.this.keyOffset + i3], EntrySet.this.alternatingKeysAndValues[i3 + (EntrySet.this.keyOffset ^ 1)]);
                }

                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return EntrySet.this.size;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }
    }

    /* loaded from: classes2.dex */
    static final class KeySet<K> extends ImmutableSet<K> {
        private final transient ImmutableList<K> list;
        private final transient ImmutableMap<K, ?> map;

        /* JADX INFO: Access modifiers changed from: package-private */
        public KeySet(ImmutableMap immutableMap, ImmutableList immutableList) {
            this.map = immutableMap;
            this.list = immutableList;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public int a(Object[] objArr, int i2) {
            return asList().a(objArr, i2);
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public ImmutableList<K> asList() {
            return this.list;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return this.map.get(obj) != null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<K> iterator() {
            return asList().iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }
    }

    /* loaded from: classes2.dex */
    static final class KeysOrValuesAsList extends ImmutableList<Object> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        /* JADX INFO: Access modifiers changed from: package-private */
        public KeysOrValuesAsList(Object[] objArr, int i2, int i3) {
            this.alternatingKeysAndValues = objArr;
            this.offset = i2;
            this.size = i3;
        }

        @Override // java.util.List
        public Object get(int i2) {
            Preconditions.checkElementIndex(i2, this.size);
            return this.alternatingKeysAndValues[(i2 * 2) + this.offset];
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }
    }

    private RegularImmutableMap(Object obj, Object[] objArr, int i2) {
        this.hashTable = obj;
        this.f8932b = objArr;
        this.size = i2;
    }

    private static IllegalArgumentException duplicateKeyException(Object obj, Object obj2, Object[] objArr, int i2) {
        return new IllegalArgumentException("Multiple entries with same key: " + obj + "=" + obj2 + " and " + objArr[i2] + "=" + objArr[i2 ^ 1]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RegularImmutableMap j(int i2, Object[] objArr) {
        if (i2 == 0) {
            return (RegularImmutableMap) f8931c;
        }
        if (i2 == 1) {
            CollectPreconditions.a(objArr[0], objArr[1]);
            return new RegularImmutableMap(null, objArr, 1);
        }
        Preconditions.checkPositionIndex(i2, objArr.length >> 1);
        return new RegularImmutableMap(k(objArr, i2, ImmutableSet.g(i2), 0), objArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
        r11[r5] = (byte) r1;
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
        r11[r5] = (short) r1;
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b0, code lost:
        r11[r6] = r1;
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Object k(Object[] objArr, int i2, int i3, int i4) {
        if (i2 == 1) {
            CollectPreconditions.a(objArr[i4], objArr[i4 ^ 1]);
            return null;
        }
        int i5 = i3 - 1;
        int i6 = 0;
        if (i3 <= 128) {
            byte[] bArr = new byte[i3];
            Arrays.fill(bArr, (byte) -1);
            while (i6 < i2) {
                int i7 = (i6 * 2) + i4;
                Object obj = objArr[i7];
                Object obj2 = objArr[i7 ^ 1];
                CollectPreconditions.a(obj, obj2);
                int c2 = Hashing.c(obj.hashCode());
                while (true) {
                    int i8 = c2 & i5;
                    int i9 = bArr[i8] & 255;
                    if (i9 == 255) {
                        break;
                    } else if (objArr[i9].equals(obj)) {
                        throw duplicateKeyException(obj, obj2, objArr, i9);
                    } else {
                        c2 = i8 + 1;
                    }
                }
            }
            return bArr;
        } else if (i3 <= 32768) {
            short[] sArr = new short[i3];
            Arrays.fill(sArr, (short) -1);
            while (i6 < i2) {
                int i10 = (i6 * 2) + i4;
                Object obj3 = objArr[i10];
                Object obj4 = objArr[i10 ^ 1];
                CollectPreconditions.a(obj3, obj4);
                int c3 = Hashing.c(obj3.hashCode());
                while (true) {
                    int i11 = c3 & i5;
                    int i12 = sArr[i11] & UShort.MAX_VALUE;
                    if (i12 == 65535) {
                        break;
                    } else if (objArr[i12].equals(obj3)) {
                        throw duplicateKeyException(obj3, obj4, objArr, i12);
                    } else {
                        c3 = i11 + 1;
                    }
                }
            }
            return sArr;
        } else {
            int[] iArr = new int[i3];
            Arrays.fill(iArr, -1);
            while (i6 < i2) {
                int i13 = (i6 * 2) + i4;
                Object obj5 = objArr[i13];
                Object obj6 = objArr[i13 ^ 1];
                CollectPreconditions.a(obj5, obj6);
                int c4 = Hashing.c(obj5.hashCode());
                while (true) {
                    int i14 = c4 & i5;
                    int i15 = iArr[i14];
                    if (i15 == -1) {
                        break;
                    } else if (objArr[i15].equals(obj5)) {
                        throw duplicateKeyException(obj5, obj6, objArr, i15);
                    } else {
                        c4 = i14 + 1;
                    }
                }
            }
            return iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object l(@NullableDecl Object obj, @NullableDecl Object[] objArr, int i2, int i3, @NullableDecl Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (i2 == 1) {
            if (objArr[i3].equals(obj2)) {
                return objArr[i3 ^ 1];
            }
            return null;
        } else if (obj == null) {
            return null;
        } else {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int length = bArr.length - 1;
                int c2 = Hashing.c(obj2.hashCode());
                while (true) {
                    int i4 = c2 & length;
                    int i5 = bArr[i4] & 255;
                    if (i5 == 255) {
                        return null;
                    }
                    if (objArr[i5].equals(obj2)) {
                        return objArr[i5 ^ 1];
                    }
                    c2 = i4 + 1;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                int length2 = sArr.length - 1;
                int c3 = Hashing.c(obj2.hashCode());
                while (true) {
                    int i6 = c3 & length2;
                    int i7 = sArr[i6] & UShort.MAX_VALUE;
                    if (i7 == 65535) {
                        return null;
                    }
                    if (objArr[i7].equals(obj2)) {
                        return objArr[i7 ^ 1];
                    }
                    c3 = i6 + 1;
                }
            } else {
                int[] iArr = (int[]) obj;
                int length3 = iArr.length - 1;
                int c4 = Hashing.c(obj2.hashCode());
                while (true) {
                    int i8 = c4 & length3;
                    int i9 = iArr[i8];
                    if (i9 == -1) {
                        return null;
                    }
                    if (objArr[i9].equals(obj2)) {
                        return objArr[i9 ^ 1];
                    }
                    c4 = i8 + 1;
                }
            }
        }
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet c() {
        return new EntrySet(this, this.f8932b, 0, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet d() {
        return new KeySet(this, new KeysOrValuesAsList(this.f8932b, 0, this.size));
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableCollection e() {
        return new KeysOrValuesAsList(this.f8932b, 1, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    @NullableDecl
    public V get(@NullableDecl Object obj) {
        return (V) l(this.hashTable, this.f8932b, this.size, 0, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean h() {
        return false;
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }
}
