package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.4")
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
/* loaded from: classes3.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    private static final int defaultMinCapacity = 10;
    private static final int maxArraySize = 2147483639;
    @NotNull
    private Object[] elementData;
    private int head;
    private int size;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Object[] emptyElementData = new Object[0];

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int newCapacity$kotlin_stdlib(int i2, int i3) {
            int i4 = i2 + (i2 >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            if (i4 - ArrayDeque.maxArraySize > 0) {
                if (i3 > ArrayDeque.maxArraySize) {
                    return Integer.MAX_VALUE;
                }
                return ArrayDeque.maxArraySize;
            }
            return i4;
        }
    }

    public ArrayDeque() {
        this.elementData = emptyElementData;
    }

    public ArrayDeque(int i2) {
        Object[] objArr;
        if (i2 == 0) {
            objArr = emptyElementData;
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + i2);
        } else {
            objArr = new Object[i2];
        }
        this.elementData = objArr;
    }

    public ArrayDeque(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] array = elements.toArray(new Object[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        this.elementData = array;
        this.size = array.length;
        if (array.length == 0) {
            this.elementData = emptyElementData;
        }
    }

    private final void copyCollectionElements(int i2, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.elementData.length;
        while (i2 < length && it.hasNext()) {
            this.elementData[i2] = it.next();
            i2++;
        }
        int i3 = this.head;
        for (int i4 = 0; i4 < i3 && it.hasNext(); i4++) {
            this.elementData[i4] = it.next();
        }
        this.size = size() + collection.size();
    }

    private final void copyElements(int i2) {
        Object[] objArr = new Object[i2];
        Object[] objArr2 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr, 0, this.head, objArr2.length);
        Object[] objArr3 = this.elementData;
        int length = objArr3.length;
        int i3 = this.head;
        ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr, length - i3, 0, i3);
        this.head = 0;
        this.elementData = objArr;
    }

    private final int decremented(int i2) {
        int lastIndex;
        if (i2 == 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(this.elementData);
            return lastIndex;
        }
        return i2 - 1;
    }

    private final void ensureCapacity(int i2) {
        int coerceAtLeast;
        if (i2 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.elementData;
        if (i2 <= objArr.length) {
            return;
        }
        if (objArr != emptyElementData) {
            copyElements(Companion.newCapacity$kotlin_stdlib(objArr.length, i2));
            return;
        }
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i2, 10);
        this.elementData = new Object[coerceAtLeast];
    }

    private final boolean filterInPlace(Function1<? super E, Boolean> function1) {
        int positiveMod;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if ((this.elementData.length == 0 ? 1 : null) == null) {
                int positiveMod2 = positiveMod(this.head + size());
                int i2 = this.head;
                if (i2 < positiveMod2) {
                    positiveMod = i2;
                    while (i2 < positiveMod2) {
                        Object obj = this.elementData[i2];
                        if (function1.invoke(obj).booleanValue()) {
                            this.elementData[positiveMod] = obj;
                            positiveMod++;
                        } else {
                            z = true;
                        }
                        i2++;
                    }
                    ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, positiveMod, positiveMod2);
                } else {
                    int length = this.elementData.length;
                    boolean z2 = false;
                    int i3 = i2;
                    while (i2 < length) {
                        Object[] objArr = this.elementData;
                        Object obj2 = objArr[i2];
                        objArr[i2] = null;
                        if (function1.invoke(obj2).booleanValue()) {
                            this.elementData[i3] = obj2;
                            i3++;
                        } else {
                            z2 = true;
                        }
                        i2++;
                    }
                    positiveMod = positiveMod(i3);
                    for (int i4 = 0; i4 < positiveMod2; i4++) {
                        Object[] objArr2 = this.elementData;
                        Object obj3 = objArr2[i4];
                        objArr2[i4] = null;
                        if (function1.invoke(obj3).booleanValue()) {
                            this.elementData[positiveMod] = obj3;
                            positiveMod = incremented(positiveMod);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(positiveMod - this.head);
                }
            }
        }
        return z;
    }

    private final int incremented(int i2) {
        int lastIndex;
        lastIndex = ArraysKt___ArraysKt.getLastIndex(this.elementData);
        if (i2 == lastIndex) {
            return 0;
        }
        return i2 + 1;
    }

    @InlineOnly
    private final E internalGet(int i2) {
        return (E) this.elementData[i2];
    }

    @InlineOnly
    private final int internalIndex(int i2) {
        return positiveMod(this.head + i2);
    }

    private final int negativeMod(int i2) {
        return i2 < 0 ? i2 + this.elementData.length : i2;
    }

    private final int positiveMod(int i2) {
        Object[] objArr = this.elementData;
        return i2 >= objArr.length ? i2 - objArr.length : i2;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int i2, E e2) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i2, size());
        if (i2 == size()) {
            addLast(e2);
        } else if (i2 == 0) {
            addFirst(e2);
        } else {
            ensureCapacity(size() + 1);
            int positiveMod = positiveMod(this.head + i2);
            if (i2 < ((size() + 1) >> 1)) {
                int decremented = decremented(positiveMod);
                int decremented2 = decremented(this.head);
                int i3 = this.head;
                if (decremented >= i3) {
                    Object[] objArr = this.elementData;
                    objArr[decremented2] = objArr[i3];
                    ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i3, i3 + 1, decremented + 1);
                } else {
                    Object[] objArr2 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i3 - 1, i3, objArr2.length);
                    Object[] objArr3 = this.elementData;
                    objArr3[objArr3.length - 1] = objArr3[0];
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, 0, 1, decremented + 1);
                }
                this.elementData[decremented] = e2;
                this.head = decremented2;
            } else {
                int positiveMod2 = positiveMod(this.head + size());
                Object[] objArr4 = this.elementData;
                if (positiveMod < positiveMod2) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, positiveMod + 1, positiveMod, positiveMod2);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, 1, 0, positiveMod2);
                    Object[] objArr5 = this.elementData;
                    objArr5[0] = objArr5[objArr5.length - 1];
                    ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, positiveMod + 1, positiveMod, objArr5.length - 1);
                }
                this.elementData[positiveMod] = e2;
            }
            this.size = size() + 1;
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e2) {
        addLast(e2);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i2, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i2, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (i2 == size()) {
            return addAll(elements);
        }
        ensureCapacity(size() + elements.size());
        int positiveMod = positiveMod(this.head + size());
        int positiveMod2 = positiveMod(this.head + i2);
        int size = elements.size();
        if (i2 < ((size() + 1) >> 1)) {
            int i3 = this.head;
            int i4 = i3 - size;
            if (positiveMod2 < i3) {
                Object[] objArr = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i4, i3, objArr.length);
                Object[] objArr2 = this.elementData;
                if (size >= positiveMod2) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, objArr2.length - size, 0, positiveMod2);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, objArr2.length - size, 0, size);
                    Object[] objArr3 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, 0, size, positiveMod2);
                }
            } else if (i4 >= 0) {
                Object[] objArr4 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, i4, i3, positiveMod2);
            } else {
                Object[] objArr5 = this.elementData;
                i4 += objArr5.length;
                int i5 = positiveMod2 - i3;
                int length = objArr5.length - i4;
                if (length >= i5) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, i4, i3, positiveMod2);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, i4, i3, i3 + length);
                    Object[] objArr6 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, 0, this.head + length, positiveMod2);
                }
            }
            this.head = i4;
            copyCollectionElements(negativeMod(positiveMod2 - size), elements);
        } else {
            int i6 = positiveMod2 + size;
            if (positiveMod2 < positiveMod) {
                int i7 = size + positiveMod;
                Object[] objArr7 = this.elementData;
                if (i7 > objArr7.length) {
                    if (i6 >= objArr7.length) {
                        i6 -= objArr7.length;
                    } else {
                        int length2 = positiveMod - (i7 - objArr7.length);
                        ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, 0, length2, positiveMod);
                        Object[] objArr8 = this.elementData;
                        ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, i6, positiveMod2, length2);
                    }
                }
                ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, i6, positiveMod2, positiveMod);
            } else {
                Object[] objArr9 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr9, objArr9, size, 0, positiveMod);
                Object[] objArr10 = this.elementData;
                if (i6 >= objArr10.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr10, objArr10, i6 - objArr10.length, positiveMod2, objArr10.length);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr10, objArr10, 0, objArr10.length - size, objArr10.length);
                    Object[] objArr11 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr11, objArr11, i6, positiveMod2, objArr11.length - size);
                }
            }
            copyCollectionElements(positiveMod2, elements);
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(size() + elements.size());
        copyCollectionElements(positiveMod(this.head + size()), elements);
        return true;
    }

    public final void addFirst(E e2) {
        ensureCapacity(size() + 1);
        int decremented = decremented(this.head);
        this.head = decremented;
        this.elementData[decremented] = e2;
        this.size = size() + 1;
    }

    public final void addLast(E e2) {
        ensureCapacity(size() + 1);
        this.elementData[positiveMod(this.head + size())] = e2;
        this.size = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int positiveMod = positiveMod(this.head + size());
        int i2 = this.head;
        if (i2 < positiveMod) {
            ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, i2, positiveMod);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            ArraysKt___ArraysJvmKt.fill(objArr, (Object) null, this.head, objArr.length);
            ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, 0, positiveMod);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[this.head];
    }

    @Nullable
    public final E firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[this.head];
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i2) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i2, size());
        return (E) this.elementData[positiveMod(this.head + i2)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int positiveMod = positiveMod(this.head + size());
        int i2 = this.head;
        if (i2 < positiveMod) {
            while (i2 < positiveMod) {
                if (!Intrinsics.areEqual(obj, this.elementData[i2])) {
                    i2++;
                }
            }
            return -1;
        } else if (i2 < positiveMod) {
            return -1;
        } else {
            int length = this.elementData.length;
            while (true) {
                if (i2 >= length) {
                    for (int i3 = 0; i3 < positiveMod; i3++) {
                        if (Intrinsics.areEqual(obj, this.elementData[i3])) {
                            i2 = i3 + this.elementData.length;
                        }
                    }
                    return -1;
                } else if (Intrinsics.areEqual(obj, this.elementData[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        return i2 - this.head;
    }

    public final void internalStructure$kotlin_stdlib(@NotNull Function2<? super Integer, ? super Object[], Unit> structure) {
        int i2;
        Intrinsics.checkNotNullParameter(structure, "structure");
        structure.invoke(Integer.valueOf((isEmpty() || (i2 = this.head) < positiveMod(this.head + size())) ? this.head : i2 - this.elementData.length), toArray());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final E last() {
        int lastIndex;
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.elementData;
        int i2 = this.head;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
        return (E) objArr[positiveMod(i2 + lastIndex)];
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int lastIndex;
        int positiveMod = positiveMod(this.head + size());
        int i2 = this.head;
        if (i2 < positiveMod) {
            lastIndex = positiveMod - 1;
            if (i2 <= lastIndex) {
                while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                    if (lastIndex != i2) {
                        lastIndex--;
                    }
                }
                return lastIndex - this.head;
            }
            return -1;
        }
        if (i2 > positiveMod) {
            int i3 = positiveMod - 1;
            while (true) {
                if (-1 >= i3) {
                    lastIndex = ArraysKt___ArraysKt.getLastIndex(this.elementData);
                    int i4 = this.head;
                    if (i4 <= lastIndex) {
                        while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                            if (lastIndex != i4) {
                                lastIndex--;
                            }
                        }
                    }
                } else if (Intrinsics.areEqual(obj, this.elementData[i3])) {
                    lastIndex = i3 + this.elementData.length;
                    break;
                } else {
                    i3--;
                }
            }
        }
        return -1;
    }

    @Nullable
    public final E lastOrNull() {
        int lastIndex;
        if (isEmpty()) {
            return null;
        }
        Object[] objArr = this.elementData;
        int i2 = this.head;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
        return (E) objArr[positiveMod(i2 + lastIndex)];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(@NotNull Collection<? extends Object> elements) {
        int positiveMod;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if ((this.elementData.length == 0 ? 1 : null) == null) {
                int positiveMod2 = positiveMod(this.head + size());
                int i2 = this.head;
                if (i2 < positiveMod2) {
                    positiveMod = i2;
                    while (i2 < positiveMod2) {
                        Object obj = this.elementData[i2];
                        if (!elements.contains(obj)) {
                            this.elementData[positiveMod] = obj;
                            positiveMod++;
                        } else {
                            z = true;
                        }
                        i2++;
                    }
                    ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, positiveMod, positiveMod2);
                } else {
                    int length = this.elementData.length;
                    boolean z2 = false;
                    int i3 = i2;
                    while (i2 < length) {
                        Object[] objArr = this.elementData;
                        Object obj2 = objArr[i2];
                        objArr[i2] = null;
                        if (!elements.contains(obj2)) {
                            this.elementData[i3] = obj2;
                            i3++;
                        } else {
                            z2 = true;
                        }
                        i2++;
                    }
                    positiveMod = positiveMod(i3);
                    for (int i4 = 0; i4 < positiveMod2; i4++) {
                        Object[] objArr2 = this.elementData;
                        Object obj3 = objArr2[i4];
                        objArr2[i4] = null;
                        if (!elements.contains(obj3)) {
                            this.elementData[positiveMod] = obj3;
                            positiveMod = incremented(positiveMod);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(positiveMod - this.head);
                }
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int i2) {
        int lastIndex;
        int lastIndex2;
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i2, size());
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
        if (i2 == lastIndex) {
            return removeLast();
        }
        if (i2 == 0) {
            return removeFirst();
        }
        int positiveMod = positiveMod(this.head + i2);
        E e2 = (E) this.elementData[positiveMod];
        if (i2 < (size() >> 1)) {
            int i3 = this.head;
            if (positiveMod >= i3) {
                Object[] objArr = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i3 + 1, i3, positiveMod);
            } else {
                Object[] objArr2 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, 1, 0, positiveMod);
                Object[] objArr3 = this.elementData;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i4 = this.head;
                ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, i4 + 1, i4, objArr3.length - 1);
            }
            Object[] objArr4 = this.elementData;
            int i5 = this.head;
            objArr4[i5] = null;
            this.head = incremented(i5);
        } else {
            int i6 = this.head;
            lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(this);
            int positiveMod2 = positiveMod(i6 + lastIndex2);
            Object[] objArr5 = this.elementData;
            if (positiveMod <= positiveMod2) {
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, positiveMod, positiveMod + 1, positiveMod2 + 1);
            } else {
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, positiveMod, positiveMod + 1, objArr5.length);
                Object[] objArr6 = this.elementData;
                objArr6[objArr6.length - 1] = objArr6[0];
                ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, 0, 1, positiveMod2 + 1);
            }
            this.elementData[positiveMod2] = null;
        }
        this.size = size() - 1;
        return e2;
    }

    public final E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.elementData;
        int i2 = this.head;
        E e2 = (E) objArr[i2];
        objArr[i2] = null;
        this.head = incremented(i2);
        this.size = size() - 1;
        return e2;
    }

    @Nullable
    public final E removeFirstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    public final E removeLast() {
        int lastIndex;
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int i2 = this.head;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
        int positiveMod = positiveMod(i2 + lastIndex);
        Object[] objArr = this.elementData;
        E e2 = (E) objArr[positiveMod];
        objArr[positiveMod] = null;
        this.size = size() - 1;
        return e2;
    }

    @Nullable
    public final E removeLastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeLast();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(@NotNull Collection<? extends Object> elements) {
        int positiveMod;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if ((this.elementData.length == 0 ? 1 : null) == null) {
                int positiveMod2 = positiveMod(this.head + size());
                int i2 = this.head;
                if (i2 < positiveMod2) {
                    positiveMod = i2;
                    while (i2 < positiveMod2) {
                        Object obj = this.elementData[i2];
                        if (elements.contains(obj)) {
                            this.elementData[positiveMod] = obj;
                            positiveMod++;
                        } else {
                            z = true;
                        }
                        i2++;
                    }
                    ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, positiveMod, positiveMod2);
                } else {
                    int length = this.elementData.length;
                    boolean z2 = false;
                    int i3 = i2;
                    while (i2 < length) {
                        Object[] objArr = this.elementData;
                        Object obj2 = objArr[i2];
                        objArr[i2] = null;
                        if (elements.contains(obj2)) {
                            this.elementData[i3] = obj2;
                            i3++;
                        } else {
                            z2 = true;
                        }
                        i2++;
                    }
                    positiveMod = positiveMod(i3);
                    for (int i4 = 0; i4 < positiveMod2; i4++) {
                        Object[] objArr2 = this.elementData;
                        Object obj3 = objArr2[i4];
                        objArr2[i4] = null;
                        if (elements.contains(obj3)) {
                            this.elementData[positiveMod] = obj3;
                            positiveMod = incremented(positiveMod);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(positiveMod - this.head);
                }
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int i2, E e2) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i2, size());
        int positiveMod = positiveMod(this.head + i2);
        Object[] objArr = this.elementData;
        E e3 = (E) objArr[positiveMod];
        objArr[positiveMod] = e2;
        return e3;
    }

    @NotNull
    public final Object[] testToArray$kotlin_stdlib() {
        return toArray();
    }

    @NotNull
    public final <T> T[] testToArray$kotlin_stdlib(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) toArray(array);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length < size()) {
            array = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(array, size());
        }
        int positiveMod = positiveMod(this.head + size());
        int i2 = this.head;
        if (i2 < positiveMod) {
            ArraysKt___ArraysJvmKt.copyInto$default(this.elementData, array, 0, i2, positiveMod, 2, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            ArraysKt___ArraysJvmKt.copyInto(objArr, array, 0, this.head, objArr.length);
            Object[] objArr2 = this.elementData;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, array, objArr2.length - this.head, 0, positiveMod);
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }
}
