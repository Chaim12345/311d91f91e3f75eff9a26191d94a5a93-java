package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class EnumMultiset<E extends Enum<E>> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    private transient int[] counts;
    private transient int distinctElements;
    private transient E[] enumConstants;
    private transient long size;
    private transient Class<E> type;

    /* loaded from: classes2.dex */
    abstract class Itr<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        int f8507a = 0;

        /* renamed from: b  reason: collision with root package name */
        int f8508b = -1;

        Itr() {
        }

        abstract Object a(int i2);

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.f8507a < EnumMultiset.this.enumConstants.length) {
                int[] iArr = EnumMultiset.this.counts;
                int i2 = this.f8507a;
                if (iArr[i2] > 0) {
                    return true;
                }
                this.f8507a = i2 + 1;
            }
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T t2 = (T) a(this.f8507a);
                int i2 = this.f8507a;
                this.f8508b = i2;
                this.f8507a = i2 + 1;
                return t2;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.e(this.f8508b >= 0);
            if (EnumMultiset.this.counts[this.f8508b] > 0) {
                EnumMultiset.g(EnumMultiset.this);
                EnumMultiset.this.size -= EnumMultiset.this.counts[this.f8508b];
                EnumMultiset.this.counts[this.f8508b] = 0;
            }
            this.f8508b = -1;
        }
    }

    private EnumMultiset(Class<E> cls) {
        this.type = cls;
        Preconditions.checkArgument(cls.isEnum());
        E[] enumConstants = cls.getEnumConstants();
        this.enumConstants = enumConstants;
        this.counts = new int[enumConstants.length];
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> cls) {
        return new EnumMultiset<>(cls);
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable) {
        Iterator<E> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "EnumMultiset constructor passed empty Iterable");
        EnumMultiset<E> enumMultiset = new EnumMultiset<>(it.next().getDeclaringClass());
        Iterables.addAll(enumMultiset, iterable);
        return enumMultiset;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable, Class<E> cls) {
        EnumMultiset<E> create = create(cls);
        Iterables.addAll(create, iterable);
        return create;
    }

    static /* synthetic */ int g(EnumMultiset enumMultiset) {
        int i2 = enumMultiset.distinctElements;
        enumMultiset.distinctElements = i2 - 1;
        return i2;
    }

    private boolean isActuallyE(@NullableDecl Object obj) {
        if (obj instanceof Enum) {
            Enum r5 = (Enum) obj;
            int ordinal = r5.ordinal();
            E[] eArr = this.enumConstants;
            return ordinal < eArr.length && eArr[ordinal] == r5;
        }
        return false;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        Class<E> cls = (Class) objectInputStream.readObject();
        this.type = cls;
        E[] enumConstants = cls.getEnumConstants();
        this.enumConstants = enumConstants;
        this.counts = new int[enumConstants.length];
        Serialization.f(this, objectInputStream);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.type);
        Serialization.k(this, objectOutputStream);
    }

    @CanIgnoreReturnValue
    public int add(E e2, int i2) {
        j(e2);
        CollectPreconditions.b(i2, "occurrences");
        if (i2 == 0) {
            return count(e2);
        }
        int ordinal = e2.ordinal();
        int i3 = this.counts[ordinal];
        long j2 = i2;
        long j3 = i3 + j2;
        Preconditions.checkArgument(j3 <= 2147483647L, "too many occurrences: %s", j3);
        this.counts[ordinal] = (int) j3;
        if (i3 == 0) {
            this.distinctElements++;
        }
        this.size += j2;
        return i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ int add(Object obj, int i2) {
        return add((EnumMultiset<E>) ((Enum) obj), i2);
    }

    @Override // com.google.common.collect.AbstractMultiset
    int b() {
        return this.distinctElements;
    }

    @Override // com.google.common.collect.AbstractMultiset
    Iterator c() {
        return new EnumMultiset<E>.Itr<E>() { // from class: com.google.common.collect.EnumMultiset.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.EnumMultiset.Itr
            /* renamed from: b */
            public Enum a(int i2) {
                return EnumMultiset.this.enumConstants[i2];
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        Arrays.fill(this.counts, 0);
        this.size = 0L;
        this.distinctElements = 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        if (isActuallyE(obj)) {
            return this.counts[((Enum) obj).ordinal()];
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Iterator d() {
        return new EnumMultiset<E>.Itr<Multiset.Entry<E>>() { // from class: com.google.common.collect.EnumMultiset.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.EnumMultiset.Itr
            /* renamed from: b */
            public Multiset.Entry a(final int i2) {
                return new Multisets.AbstractEntry<E>() { // from class: com.google.common.collect.EnumMultiset.2.1
                    @Override // com.google.common.collect.Multiset.Entry
                    public int getCount() {
                        return EnumMultiset.this.counts[i2];
                    }

                    @Override // com.google.common.collect.Multiset.Entry
                    public E getElement() {
                        return (E) EnumMultiset.this.enumConstants[i2];
                    }
                };
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return Multisets.f(this);
    }

    void j(@NullableDecl Object obj) {
        Preconditions.checkNotNull(obj);
        if (isActuallyE(obj)) {
            return;
        }
        throw new ClassCastException("Expected an " + this.type + " but got " + obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj, int i2) {
        if (isActuallyE(obj)) {
            Enum r0 = (Enum) obj;
            CollectPreconditions.b(i2, "occurrences");
            if (i2 == 0) {
                return count(obj);
            }
            int ordinal = r0.ordinal();
            int[] iArr = this.counts;
            int i3 = iArr[ordinal];
            if (i3 == 0) {
                return 0;
            }
            if (i3 <= i2) {
                iArr[ordinal] = 0;
                this.distinctElements--;
                this.size -= i3;
            } else {
                iArr[ordinal] = i3 - i2;
                this.size -= i2;
            }
            return i3;
        }
        return 0;
    }

    @CanIgnoreReturnValue
    public int setCount(E e2, int i2) {
        int i3;
        j(e2);
        CollectPreconditions.b(i2, "count");
        int ordinal = e2.ordinal();
        int[] iArr = this.counts;
        int i4 = iArr[ordinal];
        iArr[ordinal] = i2;
        this.size += i2 - i4;
        if (i4 != 0 || i2 <= 0) {
            if (i4 > 0 && i2 == 0) {
                i3 = this.distinctElements - 1;
            }
            return i4;
        }
        i3 = this.distinctElements + 1;
        this.distinctElements = i3;
        return i4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ int setCount(Object obj, int i2) {
        return setCount((EnumMultiset<E>) ((Enum) obj), i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(this.size);
    }
}
