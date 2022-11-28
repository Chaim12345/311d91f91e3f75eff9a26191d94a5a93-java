package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public final class Collections2 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class FilteredCollection<E> extends AbstractCollection<E> {

        /* renamed from: a  reason: collision with root package name */
        final Collection f8448a;

        /* renamed from: b  reason: collision with root package name */
        final Predicate f8449b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public FilteredCollection(Collection collection, Predicate predicate) {
            this.f8448a = collection;
            this.f8449b = predicate;
        }

        FilteredCollection a(Predicate predicate) {
            return new FilteredCollection(this.f8448a, Predicates.and(this.f8449b, predicate));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e2) {
            Preconditions.checkArgument(this.f8449b.apply(e2));
            return this.f8448a.add(e2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            for (E e2 : collection) {
                Preconditions.checkArgument(this.f8449b.apply(e2));
            }
            return this.f8448a.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Iterables.removeIf(this.f8448a, this.f8449b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (Collections2.e(this.f8448a, obj)) {
                return this.f8449b.apply(obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return Collections2.c(this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !Iterables.any(this.f8448a, this.f8449b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.f8448a.iterator(), this.f8449b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            return contains(obj) && this.f8448a.remove(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<E> it = this.f8448a.iterator();
            boolean z = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.f8449b.apply(next) && collection.contains(next)) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<E> it = this.f8448a.iterator();
            boolean z = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.f8449b.apply(next) && !collection.contains(next)) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i2 = 0;
            for (E e2 : this.f8448a) {
                if (this.f8449b.apply(e2)) {
                    i2++;
                }
            }
            return i2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {

        /* renamed from: a  reason: collision with root package name */
        final ImmutableList f8450a;

        /* renamed from: b  reason: collision with root package name */
        final Comparator f8451b;

        /* renamed from: c  reason: collision with root package name */
        final int f8452c;

        OrderedPermutationCollection(Iterable iterable, Comparator comparator) {
            ImmutableList sortedCopyOf = ImmutableList.sortedCopyOf(comparator, iterable);
            this.f8450a = sortedCopyOf;
            this.f8451b = comparator;
            this.f8452c = calculateSize(sortedCopyOf, comparator);
        }

        private static <E> int calculateSize(List<E> list, Comparator<? super E> comparator) {
            int i2 = 1;
            int i3 = 1;
            int i4 = 1;
            while (i2 < list.size()) {
                if (comparator.compare(list.get(i2 - 1), list.get(i2)) < 0) {
                    i3 = IntMath.saturatedMultiply(i3, IntMath.binomial(i2, i4));
                    i4 = 0;
                    if (i3 == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
                i2++;
                i4++;
            }
            return IntMath.saturatedMultiply(i3, IntMath.binomial(i2, i4));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof List) {
                return Collections2.isPermutation(this.f8450a, (List) obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.f8450a, this.f8451b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.f8452c;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return "orderedPermutationCollection(" + this.f8450a + ")";
        }
    }

    /* loaded from: classes2.dex */
    private static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        List f8453a;

        /* renamed from: b  reason: collision with root package name */
        final Comparator f8454b;

        OrderedPermutationIterator(List list, Comparator comparator) {
            this.f8453a = Lists.newArrayList(list);
            this.f8454b = comparator;
        }

        void b() {
            int d2 = d();
            if (d2 == -1) {
                this.f8453a = null;
                return;
            }
            Collections.swap(this.f8453a, d2, e(d2));
            Collections.reverse(this.f8453a.subList(d2 + 1, this.f8453a.size()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: c */
        public List computeNext() {
            List list = this.f8453a;
            if (list == null) {
                return (List) a();
            }
            ImmutableList copyOf = ImmutableList.copyOf((Collection) list);
            b();
            return copyOf;
        }

        int d() {
            for (int size = this.f8453a.size() - 2; size >= 0; size--) {
                if (this.f8454b.compare(this.f8453a.get(size), this.f8453a.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }

        int e(int i2) {
            Object obj = this.f8453a.get(i2);
            for (int size = this.f8453a.size() - 1; size > i2; size--) {
                if (this.f8454b.compare(obj, this.f8453a.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }
    }

    /* loaded from: classes2.dex */
    private static final class PermutationCollection<E> extends AbstractCollection<List<E>> {

        /* renamed from: a  reason: collision with root package name */
        final ImmutableList f8455a;

        PermutationCollection(ImmutableList immutableList) {
            this.f8455a = immutableList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof List) {
                return Collections2.isPermutation(this.f8455a, (List) obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.f8455a);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return IntMath.factorial(this.f8455a.size());
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return "permutations(" + this.f8455a + ")";
        }
    }

    /* loaded from: classes2.dex */
    private static class PermutationIterator<E> extends AbstractIterator<List<E>> {

        /* renamed from: a  reason: collision with root package name */
        final List f8456a;

        /* renamed from: b  reason: collision with root package name */
        final int[] f8457b;

        /* renamed from: c  reason: collision with root package name */
        final int[] f8458c;

        /* renamed from: d  reason: collision with root package name */
        int f8459d;

        PermutationIterator(List list) {
            this.f8456a = new ArrayList(list);
            int size = list.size();
            int[] iArr = new int[size];
            this.f8457b = iArr;
            int[] iArr2 = new int[size];
            this.f8458c = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 1);
            this.f8459d = Integer.MAX_VALUE;
        }

        void b() {
            int size = this.f8456a.size() - 1;
            this.f8459d = size;
            if (size == -1) {
                return;
            }
            int i2 = 0;
            while (true) {
                int[] iArr = this.f8457b;
                int i3 = this.f8459d;
                int i4 = iArr[i3] + this.f8458c[i3];
                if (i4 >= 0) {
                    if (i4 != i3 + 1) {
                        Collections.swap(this.f8456a, (i3 - iArr[i3]) + i2, (i3 - i4) + i2);
                        this.f8457b[this.f8459d] = i4;
                        return;
                    } else if (i3 == 0) {
                        return;
                    } else {
                        i2++;
                    }
                }
                d();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: c */
        public List computeNext() {
            if (this.f8459d <= 0) {
                return (List) a();
            }
            ImmutableList copyOf = ImmutableList.copyOf((Collection) this.f8456a);
            b();
            return copyOf;
        }

        void d() {
            int[] iArr = this.f8458c;
            int i2 = this.f8459d;
            iArr[i2] = -iArr[i2];
            this.f8459d = i2 - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TransformedCollection<F, T> extends AbstractCollection<T> {

        /* renamed from: a  reason: collision with root package name */
        final Collection f8460a;

        /* renamed from: b  reason: collision with root package name */
        final Function f8461b;

        TransformedCollection(Collection collection, Function function) {
            this.f8460a = (Collection) Preconditions.checkNotNull(collection);
            this.f8461b = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.f8460a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f8460a.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.f8460a.iterator(), this.f8461b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.f8460a.size();
        }
    }

    private Collections2() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Collection b(Iterable iterable) {
        return (Collection) iterable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(Collection collection, Collection collection2) {
        for (Object obj : collection2) {
            if (!collection.contains(obj)) {
                return false;
            }
        }
        return true;
    }

    private static <E> ObjectCountHashMap<E> counts(Collection<E> collection) {
        ObjectCountHashMap<E> objectCountHashMap = new ObjectCountHashMap<>();
        for (E e2 : collection) {
            objectCountHashMap.put(e2, objectCountHashMap.get(e2) + 1);
        }
        return objectCountHashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StringBuilder d(int i2) {
        CollectPreconditions.b(i2, "size");
        return new StringBuilder((int) Math.min(i2 * 8, 1073741824L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(Collection collection, @NullableDecl Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(Collection collection, @NullableDecl Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        return collection instanceof FilteredCollection ? ((FilteredCollection) collection).a(predicate) : new FilteredCollection((Collection) Preconditions.checkNotNull(collection), (Predicate) Preconditions.checkNotNull(predicate));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g(Collection collection) {
        StringBuilder d2 = d(collection.size());
        d2.append(AbstractJsonLexerKt.BEGIN_LIST);
        boolean z = true;
        for (Object obj : collection) {
            if (!z) {
                d2.append(", ");
            }
            z = false;
            if (obj == collection) {
                d2.append("(this Collection)");
            } else {
                d2.append(obj);
            }
        }
        d2.append(AbstractJsonLexerKt.END_LIST);
        return d2.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPermutation(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        ObjectCountHashMap counts = counts(list);
        ObjectCountHashMap counts2 = counts(list2);
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (counts.e(i2) != counts2.get(counts.d(i2))) {
                return false;
            }
        }
        return true;
    }

    @Beta
    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return orderedPermutations(iterable, Ordering.natural());
    }

    @Beta
    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(iterable, comparator);
    }

    @Beta
    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new PermutationCollection(ImmutableList.copyOf((Collection) collection));
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new TransformedCollection(collection, function);
    }
}
