package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.CopyOnWriteArrayList;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Lists {

    /* renamed from: com.google.common.collect.Lists$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    final class AnonymousClass1 extends RandomAccessListWrapper<Object> {
        private static final long serialVersionUID = 0;

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<Object> listIterator(int i2) {
            return this.f8717a.listIterator(i2);
        }
    }

    /* renamed from: com.google.common.collect.Lists$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    final class AnonymousClass2 extends AbstractListWrapper<Object> {
        private static final long serialVersionUID = 0;

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<Object> listIterator(int i2) {
            return this.f8717a.listIterator(i2);
        }
    }

    /* loaded from: classes2.dex */
    private static class AbstractListWrapper<E> extends AbstractList<E> {

        /* renamed from: a  reason: collision with root package name */
        final List f8717a;

        @Override // java.util.AbstractList, java.util.List
        public void add(int i2, E e2) {
            this.f8717a.add(i2, e2);
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i2, Collection<? extends E> collection) {
            return this.f8717a.addAll(i2, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return this.f8717a.contains(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i2) {
            return (E) this.f8717a.get(i2);
        }

        @Override // java.util.AbstractList, java.util.List
        public E remove(int i2) {
            return (E) this.f8717a.remove(i2);
        }

        @Override // java.util.AbstractList, java.util.List
        public E set(int i2, E e2) {
            return (E) this.f8717a.set(i2, e2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.f8717a.size();
        }
    }

    /* loaded from: classes2.dex */
    private static final class CharSequenceAsList extends AbstractList<Character> {
        private final CharSequence sequence;

        CharSequenceAsList(CharSequence charSequence) {
            this.sequence = charSequence;
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return Character.valueOf(this.sequence.charAt(i2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.sequence.length();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8718a;

        /* renamed from: b  reason: collision with root package name */
        final Object[] f8719b;

        OnePlusArrayList(@NullableDecl Object obj, Object[] objArr) {
            this.f8718a = obj;
            this.f8719b = (Object[]) Preconditions.checkNotNull(objArr);
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return i2 == 0 ? (E) this.f8718a : (E) this.f8719b[i2 - 1];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.f8719b.length, 1);
        }
    }

    /* loaded from: classes2.dex */
    private static class Partition<T> extends AbstractList<List<T>> {

        /* renamed from: a  reason: collision with root package name */
        final List f8720a;

        /* renamed from: b  reason: collision with root package name */
        final int f8721b;

        Partition(List list, int i2) {
            this.f8720a = list;
            this.f8721b = i2;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            int i3 = this.f8721b;
            int i4 = i2 * i3;
            return this.f8720a.subList(i4, Math.min(i3 + i4, this.f8720a.size()));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.f8720a.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.divide(this.f8720a.size(), this.f8721b, RoundingMode.CEILING);
        }
    }

    /* loaded from: classes2.dex */
    private static class RandomAccessListWrapper<E> extends AbstractListWrapper<E> implements RandomAccess {
    }

    /* loaded from: classes2.dex */
    private static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
        RandomAccessPartition(List list, int i2) {
            super(list, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RandomAccessReverseList<T> extends ReverseList<T> implements RandomAccess {
        RandomAccessReverseList(List list) {
            super(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ReverseList<T> extends AbstractList<T> {
        private final List<T> forwardList;

        ReverseList(List list) {
            this.forwardList = (List) Preconditions.checkNotNull(list);
        }

        private int reverseIndex(int i2) {
            int size = size();
            Preconditions.checkElementIndex(i2, size);
            return (size - 1) - i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int reversePosition(int i2) {
            int size = size();
            Preconditions.checkPositionIndex(i2, size);
            return size - i2;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i2, @NullableDecl T t2) {
            this.forwardList.add(reversePosition(i2), t2);
        }

        List b() {
            return this.forwardList;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.forwardList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int i2) {
            return this.forwardList.get(reverseIndex(i2));
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int i2) {
            final ListIterator<T> listIterator = this.forwardList.listIterator(reversePosition(i2));
            return new ListIterator<T>() { // from class: com.google.common.collect.Lists.ReverseList.1

                /* renamed from: a  reason: collision with root package name */
                boolean f8722a;

                @Override // java.util.ListIterator
                public void add(T t2) {
                    listIterator.add(t2);
                    listIterator.previous();
                    this.f8722a = false;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public boolean hasNext() {
                    return listIterator.hasPrevious();
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return listIterator.hasNext();
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public T next() {
                    if (hasNext()) {
                        this.f8722a = true;
                        return (T) listIterator.previous();
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return ReverseList.this.reversePosition(listIterator.nextIndex());
                }

                @Override // java.util.ListIterator
                public T previous() {
                    if (hasPrevious()) {
                        this.f8722a = true;
                        return (T) listIterator.next();
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return nextIndex() - 1;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public void remove() {
                    CollectPreconditions.e(this.f8722a);
                    listIterator.remove();
                    this.f8722a = false;
                }

                @Override // java.util.ListIterator
                public void set(T t2) {
                    Preconditions.checkState(this.f8722a);
                    listIterator.set(t2);
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int i2) {
            return this.forwardList.remove(reverseIndex(i2));
        }

        @Override // java.util.AbstractList
        protected void removeRange(int i2, int i3) {
            subList(i2, i3).clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T set(int i2, @NullableDecl T t2) {
            return this.forwardList.set(reverseIndex(i2), t2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> subList(int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, size());
            return Lists.reverse(this.forwardList.subList(reversePosition(i3), reversePosition(i2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class StringAsImmutableList extends ImmutableList<Character> {
        private final String string;

        StringAsImmutableList(String str) {
            this.string = str;
        }

        @Override // java.util.List
        public Character get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return Character.valueOf(this.string.charAt(i2));
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(@NullableDecl Object obj) {
            if (obj instanceof Character) {
                return this.string.indexOf(((Character) obj).charValue());
            }
            return -1;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(@NullableDecl Object obj) {
            if (obj instanceof Character) {
                return this.string.lastIndexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.string.length();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<Character> subList(int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, size());
            return Lists.charactersOf(this.string.substring(i2, i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TransformingRandomAccessList<F, T> extends AbstractList<T> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final List f8725a;

        /* renamed from: b  reason: collision with root package name */
        final Function f8726b;

        TransformingRandomAccessList(List list, Function function) {
            this.f8725a = (List) Preconditions.checkNotNull(list);
            this.f8726b = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.f8725a.clear();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractList, java.util.List
        public T get(int i2) {
            return (T) this.f8726b.apply(this.f8725a.get(i2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.f8725a.isEmpty();
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int i2) {
            return new TransformedListIterator<F, T>(this.f8725a.listIterator(i2)) { // from class: com.google.common.collect.Lists.TransformingRandomAccessList.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.TransformedIterator
                public Object a(Object obj) {
                    return TransformingRandomAccessList.this.f8726b.apply(obj);
                }
            };
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractList, java.util.List
        public T remove(int i2) {
            return (T) this.f8726b.apply(this.f8725a.remove(i2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.f8725a.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final List f8728a;

        /* renamed from: b  reason: collision with root package name */
        final Function f8729b;

        TransformingSequentialList(List list, Function function) {
            this.f8728a = (List) Preconditions.checkNotNull(list);
            this.f8729b = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.f8728a.clear();
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int i2) {
            return new TransformedListIterator<F, T>(this.f8728a.listIterator(i2)) { // from class: com.google.common.collect.Lists.TransformingSequentialList.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.TransformedIterator
                public Object a(Object obj) {
                    return TransformingSequentialList.this.f8729b.apply(obj);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.f8728a.size();
        }
    }

    /* loaded from: classes2.dex */
    private static class TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8731a;
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        final Object f8732b;

        /* renamed from: c  reason: collision with root package name */
        final Object[] f8733c;

        TwoPlusArrayList(@NullableDecl Object obj, @NullableDecl Object obj2, Object[] objArr) {
            this.f8731a = obj;
            this.f8732b = obj2;
            this.f8733c = (Object[]) Preconditions.checkNotNull(objArr);
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i2) {
            if (i2 != 0) {
                if (i2 != 1) {
                    Preconditions.checkElementIndex(i2, size());
                    return (E) this.f8733c[i2 - 2];
                }
                return (E) this.f8732b;
            }
            return (E) this.f8731a;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.f8733c.length, 2);
        }
    }

    private Lists() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List a(Iterable iterable) {
        return (List) iterable;
    }

    public static <E> List<E> asList(@NullableDecl E e2, @NullableDecl E e3, E[] eArr) {
        return new TwoPlusArrayList(e2, e3, eArr);
    }

    public static <E> List<E> asList(@NullableDecl E e2, E[] eArr) {
        return new OnePlusArrayList(e2, eArr);
    }

    @VisibleForTesting
    static int b(int i2) {
        CollectPreconditions.b(i2, "arraySize");
        return Ints.saturatedCast(i2 + 5 + (i2 / 10));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(List list, @NullableDecl Object obj) {
        if (obj == Preconditions.checkNotNull(list)) {
            return true;
        }
        if (obj instanceof List) {
            List list2 = (List) obj;
            int size = list.size();
            if (size != list2.size()) {
                return false;
            }
            if ((list instanceof RandomAccess) && (list2 instanceof RandomAccess)) {
                for (int i2 = 0; i2 < size; i2++) {
                    if (!Objects.equal(list.get(i2), list2.get(i2))) {
                        return false;
                    }
                }
                return true;
            }
            return Iterators.elementsEqual(list.iterator(), list2.iterator());
        }
        return false;
    }

    public static <B> List<List<B>> cartesianProduct(List<? extends List<? extends B>> list) {
        return CartesianList.c(list);
    }

    @SafeVarargs
    public static <B> List<List<B>> cartesianProduct(List<? extends B>... listArr) {
        return cartesianProduct(Arrays.asList(listArr));
    }

    public static ImmutableList<Character> charactersOf(String str) {
        return new StringAsImmutableList((String) Preconditions.checkNotNull(str));
    }

    @Beta
    public static List<Character> charactersOf(CharSequence charSequence) {
        return new CharSequenceAsList((CharSequence) Preconditions.checkNotNull(charSequence));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(List list, @NullableDecl Object obj) {
        if (list instanceof RandomAccess) {
            return indexOfRandomAccess(list, obj);
        }
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (Objects.equal(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(List list, @NullableDecl Object obj) {
        if (list instanceof RandomAccess) {
            return lastIndexOfRandomAccess(list, obj);
        }
        ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (Objects.equal(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    private static int indexOfRandomAccess(List<?> list, @NullableDecl Object obj) {
        int size = list.size();
        int i2 = 0;
        if (obj == null) {
            while (i2 < size) {
                if (list.get(i2) == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        while (i2 < size) {
            if (obj.equals(list.get(i2))) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    private static int lastIndexOfRandomAccess(List<?> list, @NullableDecl Object obj) {
        if (obj == null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (list.get(size) == null) {
                    return size;
                }
            }
            return -1;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            if (obj.equals(list.get(size2))) {
                return size2;
            }
        }
        return -1;
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(iterable);
        return iterable instanceof Collection ? new ArrayList<>(Collections2.b(iterable)) : newArrayList(iterable.iterator());
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> newArrayList = newArrayList();
        Iterators.addAll(newArrayList, it);
        return newArrayList;
    }

    @SafeVarargs
    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        Preconditions.checkNotNull(eArr);
        ArrayList<E> arrayList = new ArrayList<>(b(eArr.length));
        Collections.addAll(arrayList, eArr);
        return arrayList;
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithCapacity(int i2) {
        CollectPreconditions.b(i2, "initialArraySize");
        return new ArrayList<>(i2);
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithExpectedSize(int i2) {
        return new ArrayList<>(b(i2));
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> iterable) {
        return new CopyOnWriteArrayList<>(iterable instanceof Collection ? Collections2.b(iterable) : newArrayList(iterable));
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> iterable) {
        LinkedList<E> newLinkedList = newLinkedList();
        Iterables.addAll(newLinkedList, iterable);
        return newLinkedList;
    }

    public static <T> List<List<T>> partition(List<T> list, int i2) {
        Preconditions.checkNotNull(list);
        Preconditions.checkArgument(i2 > 0);
        return list instanceof RandomAccess ? new RandomAccessPartition(list, i2) : new Partition(list, i2);
    }

    public static <T> List<T> reverse(List<T> list) {
        return list instanceof ImmutableList ? ((ImmutableList) list).reverse() : list instanceof ReverseList ? ((ReverseList) list).b() : list instanceof RandomAccess ? new RandomAccessReverseList(list) : new ReverseList(list);
    }

    public static <F, T> List<T> transform(List<F> list, Function<? super F, ? extends T> function) {
        return list instanceof RandomAccess ? new TransformingRandomAccessList(list, function) : new TransformingSequentialList(list, function);
    }
}
