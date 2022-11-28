package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class SimpleArrayMap<K, V> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    static Object[] f1608d;

    /* renamed from: e  reason: collision with root package name */
    static int f1609e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    static Object[] f1610f;

    /* renamed from: g  reason: collision with root package name */
    static int f1611g;

    /* renamed from: a  reason: collision with root package name */
    int[] f1612a;

    /* renamed from: b  reason: collision with root package name */
    Object[] f1613b;

    /* renamed from: c  reason: collision with root package name */
    int f1614c;

    public SimpleArrayMap() {
        this.f1612a = ContainerHelpers.f1585a;
        this.f1613b = ContainerHelpers.f1587c;
        this.f1614c = 0;
    }

    public SimpleArrayMap(int i2) {
        if (i2 == 0) {
            this.f1612a = ContainerHelpers.f1585a;
            this.f1613b = ContainerHelpers.f1587c;
        } else {
            allocArrays(i2);
        }
        this.f1614c = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        if (simpleArrayMap != 0) {
            putAll(simpleArrayMap);
        }
    }

    private void allocArrays(int i2) {
        if (i2 == 8) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr = f1610f;
                if (objArr != null) {
                    this.f1613b = objArr;
                    f1610f = (Object[]) objArr[0];
                    this.f1612a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f1611g--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr2 = f1608d;
                if (objArr2 != null) {
                    this.f1613b = objArr2;
                    f1608d = (Object[]) objArr2[0];
                    this.f1612a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f1609e--;
                    return;
                }
            }
        }
        this.f1612a = new int[i2];
        this.f1613b = new Object[i2 << 1];
    }

    private static int binarySearchHashes(int[] iArr, int i2, int i3) {
        try {
            return ContainerHelpers.a(iArr, i2, i3);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void freeArrays(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (SimpleArrayMap.class) {
                if (f1611g < 10) {
                    objArr[0] = f1610f;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f1610f = objArr;
                    f1611g++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (SimpleArrayMap.class) {
                if (f1609e < 10) {
                    objArr[0] = f1608d;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f1608d = objArr;
                    f1609e++;
                }
            }
        }
    }

    int a(Object obj, int i2) {
        int i3 = this.f1614c;
        if (i3 == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.f1612a, i3, i2);
        if (binarySearchHashes >= 0 && !obj.equals(this.f1613b[binarySearchHashes << 1])) {
            int i4 = binarySearchHashes + 1;
            while (i4 < i3 && this.f1612a[i4] == i2) {
                if (obj.equals(this.f1613b[i4 << 1])) {
                    return i4;
                }
                i4++;
            }
            for (int i5 = binarySearchHashes - 1; i5 >= 0 && this.f1612a[i5] == i2; i5--) {
                if (obj.equals(this.f1613b[i5 << 1])) {
                    return i5;
                }
            }
            return ~i4;
        }
        return binarySearchHashes;
    }

    int b() {
        int i2 = this.f1614c;
        if (i2 == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.f1612a, i2, 0);
        if (binarySearchHashes >= 0 && this.f1613b[binarySearchHashes << 1] != null) {
            int i3 = binarySearchHashes + 1;
            while (i3 < i2 && this.f1612a[i3] == 0) {
                if (this.f1613b[i3 << 1] == null) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = binarySearchHashes - 1; i4 >= 0 && this.f1612a[i4] == 0; i4--) {
                if (this.f1613b[i4 << 1] == null) {
                    return i4;
                }
            }
            return ~i3;
        }
        return binarySearchHashes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(Object obj) {
        int i2 = this.f1614c * 2;
        Object[] objArr = this.f1613b;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public void clear() {
        int i2 = this.f1614c;
        if (i2 > 0) {
            int[] iArr = this.f1612a;
            Object[] objArr = this.f1613b;
            this.f1612a = ContainerHelpers.f1585a;
            this.f1613b = ContainerHelpers.f1587c;
            this.f1614c = 0;
            freeArrays(iArr, objArr, i2);
        }
        if (this.f1614c > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(@Nullable Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return c(obj) >= 0;
    }

    public void ensureCapacity(int i2) {
        int i3 = this.f1614c;
        int[] iArr = this.f1612a;
        if (iArr.length < i2) {
            Object[] objArr = this.f1613b;
            allocArrays(i2);
            if (this.f1614c > 0) {
                System.arraycopy(iArr, 0, this.f1612a, 0, i3);
                System.arraycopy(objArr, 0, this.f1613b, 0, i3 << 1);
            }
            freeArrays(iArr, objArr, i3);
        }
        if (this.f1614c != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleArrayMap) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
            if (size() != simpleArrayMap.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f1614c; i2++) {
                try {
                    K keyAt = keyAt(i2);
                    V valueAt = valueAt(i2);
                    Object obj2 = simpleArrayMap.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(keyAt)) {
                            return false;
                        }
                    } else if (!valueAt.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i3 = 0; i3 < this.f1614c; i3++) {
                try {
                    K keyAt2 = keyAt(i3);
                    V valueAt2 = valueAt(i3);
                    Object obj3 = map.get(keyAt2);
                    if (valueAt2 == null) {
                        if (obj3 != null || !map.containsKey(keyAt2)) {
                            return false;
                        }
                    } else if (!valueAt2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    @Nullable
    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int indexOfKey = indexOfKey(obj);
        return indexOfKey >= 0 ? (V) this.f1613b[(indexOfKey << 1) + 1] : v;
    }

    public int hashCode() {
        int[] iArr = this.f1612a;
        Object[] objArr = this.f1613b;
        int i2 = this.f1614c;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            i5 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return i5;
    }

    public int indexOfKey(@Nullable Object obj) {
        return obj == null ? b() : a(obj, obj.hashCode());
    }

    public boolean isEmpty() {
        return this.f1614c <= 0;
    }

    public K keyAt(int i2) {
        return (K) this.f1613b[i2 << 1];
    }

    @Nullable
    public V put(K k2, V v) {
        int i2;
        int a2;
        int i3 = this.f1614c;
        if (k2 == null) {
            a2 = b();
            i2 = 0;
        } else {
            int hashCode = k2.hashCode();
            i2 = hashCode;
            a2 = a(k2, hashCode);
        }
        if (a2 >= 0) {
            int i4 = (a2 << 1) + 1;
            Object[] objArr = this.f1613b;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = ~a2;
        int[] iArr = this.f1612a;
        if (i3 >= iArr.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            Object[] objArr2 = this.f1613b;
            allocArrays(i6);
            if (i3 != this.f1614c) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f1612a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f1613b, 0, objArr2.length);
            }
            freeArrays(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.f1612a;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f1613b;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.f1614c - i5) << 1);
        }
        int i8 = this.f1614c;
        if (i3 == i8) {
            int[] iArr4 = this.f1612a;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.f1613b;
                int i9 = i5 << 1;
                objArr4[i9] = k2;
                objArr4[i9 + 1] = v;
                this.f1614c = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(@NonNull SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int i2 = simpleArrayMap.f1614c;
        ensureCapacity(this.f1614c + i2);
        if (this.f1614c != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                put(simpleArrayMap.keyAt(i3), simpleArrayMap.valueAt(i3));
            }
        } else if (i2 > 0) {
            System.arraycopy(simpleArrayMap.f1612a, 0, this.f1612a, 0, i2);
            System.arraycopy(simpleArrayMap.f1613b, 0, this.f1613b, 0, i2 << 1);
            this.f1614c = i2;
        }
    }

    @Nullable
    public V putIfAbsent(K k2, V v) {
        V v2 = get(k2);
        return v2 == null ? put(k2, v) : v2;
    }

    @Nullable
    public V remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public boolean remove(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            V valueAt = valueAt(indexOfKey);
            if (obj2 == valueAt || (obj2 != null && obj2.equals(valueAt))) {
                removeAt(indexOfKey);
                return true;
            }
            return false;
        }
        return false;
    }

    public V removeAt(int i2) {
        Object[] objArr = this.f1613b;
        int i3 = i2 << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.f1614c;
        int i5 = 0;
        if (i4 <= 1) {
            freeArrays(this.f1612a, objArr, i4);
            this.f1612a = ContainerHelpers.f1585a;
            this.f1613b = ContainerHelpers.f1587c;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.f1612a;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.f1613b;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.f1613b;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                allocArrays(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.f1614c) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.f1612a, 0, i2);
                    System.arraycopy(objArr, 0, this.f1613b, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.f1612a, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.f1613b, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 == this.f1614c) {
            this.f1614c = i5;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    @Nullable
    public V replace(K k2, V v) {
        int indexOfKey = indexOfKey(k2);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, v);
        }
        return null;
    }

    public boolean replace(K k2, V v, V v2) {
        int indexOfKey = indexOfKey(k2);
        if (indexOfKey >= 0) {
            V valueAt = valueAt(indexOfKey);
            if (valueAt == v || (v != null && v.equals(valueAt))) {
                setValueAt(indexOfKey, v2);
                return true;
            }
            return false;
        }
        return false;
    }

    public V setValueAt(int i2, V v) {
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.f1613b;
        V v2 = (V) objArr[i3];
        objArr[i3] = v;
        return v2;
    }

    public int size() {
        return this.f1614c;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f1614c * 28);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        for (int i2 = 0; i2 < this.f1614c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            K keyAt = keyAt(i2);
            if (keyAt != this) {
                sb.append(keyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V valueAt = valueAt(i2);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public V valueAt(int i2) {
        return (V) this.f1613b[(i2 << 1) + 1];
    }
}
