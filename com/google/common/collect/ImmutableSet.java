package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    @RetainedWith
    @NullableDecl
    @LazyInit
    private transient ImmutableList<E> asList;

    /* loaded from: classes2.dex */
    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        @VisibleForTesting
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        Object[] f8617d;
        private int hashCode;

        public Builder() {
            super(4);
        }

        Builder(int i2) {
            super(i2);
            this.f8617d = new Object[ImmutableSet.g(i2)];
        }

        private void addDeduping(E e2) {
            int length = this.f8617d.length - 1;
            int hashCode = e2.hashCode();
            int c2 = Hashing.c(hashCode);
            while (true) {
                int i2 = c2 & length;
                Object[] objArr = this.f8617d;
                Object obj = objArr[i2];
                if (obj == null) {
                    objArr[i2] = e2;
                    this.hashCode += hashCode;
                    super.add((Builder<E>) e2);
                    return;
                } else if (obj.equals(e2)) {
                    return;
                } else {
                    c2 = i2 + 1;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableCollection.ArrayBasedBuilder add(Object obj) {
            return add((Builder<E>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object obj) {
            return add((Builder<E>) obj);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E e2) {
            Preconditions.checkNotNull(e2);
            if (this.f8617d != null && ImmutableSet.g(this.f8556b) <= this.f8617d.length) {
                addDeduping(e2);
                return this;
            }
            this.f8617d = null;
            super.add((Builder<E>) e2);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            if (this.f8617d != null) {
                for (E e2 : eArr) {
                    add((Builder<E>) e2);
                }
            } else {
                super.add((Object[]) eArr);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            Preconditions.checkNotNull(iterable);
            if (this.f8617d != null) {
                for (E e2 : iterable) {
                    add((Builder<E>) e2);
                }
            } else {
                super.addAll((Iterable) iterable);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            Preconditions.checkNotNull(it);
            while (it.hasNext()) {
                add((Builder<E>) it.next());
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSet<E> build() {
            ImmutableSet<E> construct;
            int i2 = this.f8556b;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (this.f8617d == null || ImmutableSet.g(i2) != this.f8617d.length) {
                        construct = ImmutableSet.construct(this.f8556b, this.f8555a);
                        this.f8556b = construct.size();
                    } else {
                        Object[] copyOf = ImmutableSet.shouldTrim(this.f8556b, this.f8555a.length) ? Arrays.copyOf(this.f8555a, this.f8556b) : this.f8555a;
                        int i3 = this.hashCode;
                        Object[] objArr = this.f8617d;
                        construct = new RegularImmutableSet<>(copyOf, i3, objArr, objArr.length - 1, this.f8556b);
                    }
                    this.f8557c = true;
                    this.f8617d = null;
                    return construct;
                }
                return ImmutableSet.of(this.f8555a[0]);
            }
            return ImmutableSet.of();
        }
    }

    /* loaded from: classes2.dex */
    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    @Beta
    public static <E> Builder<E> builderWithExpectedSize(int i2) {
        CollectPreconditions.b(i2, "expectedSize");
        return new Builder<>(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int i2, Object... objArr) {
        if (i2 != 0) {
            if (i2 != 1) {
                int g2 = g(i2);
                Object[] objArr2 = new Object[g2];
                int i3 = g2 - 1;
                int i4 = 0;
                int i5 = 0;
                for (int i6 = 0; i6 < i2; i6++) {
                    Object a2 = ObjectArrays.a(objArr[i6], i6);
                    int hashCode = a2.hashCode();
                    int c2 = Hashing.c(hashCode);
                    while (true) {
                        int i7 = c2 & i3;
                        Object obj = objArr2[i7];
                        if (obj == null) {
                            objArr[i5] = a2;
                            objArr2[i7] = a2;
                            i4 += hashCode;
                            i5++;
                            break;
                        } else if (obj.equals(a2)) {
                            break;
                        } else {
                            c2++;
                        }
                    }
                }
                Arrays.fill(objArr, i5, i2, (Object) null);
                if (i5 == 1) {
                    return new SingletonImmutableSet(objArr[0], i4);
                }
                if (g(i5) < g2 / 2) {
                    return construct(i5, objArr);
                }
                if (shouldTrim(i5, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, i5);
                }
                return new RegularImmutableSet(objArr, i4, objArr2, i3, i5);
            }
            return of(objArr[0]);
        }
        return of();
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        return iterable instanceof Collection ? copyOf((Collection) iterable) : copyOf(iterable.iterator());
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (it.hasNext()) {
            E next = it.next();
            return !it.hasNext() ? of((Object) next) : new Builder().add((Builder) next).addAll((Iterator) it).build();
        }
        return of();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        return length != 0 ? length != 1 ? construct(eArr.length, (Object[]) eArr.clone()) : of((Object) eArr[0]) : of();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public static int g(int i2) {
        int max = Math.max(i2, 2);
        if (max >= CUTOFF) {
            Preconditions.checkArgument(max < 1073741824, "collection too large");
            return 1073741824;
        }
        int highestOneBit = Integer.highestOneBit(max - 1) << 1;
        while (highestOneBit * 0.7d < max) {
            highestOneBit <<= 1;
        }
        return highestOneBit;
    }

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.f8937c;
    }

    public static <E> ImmutableSet<E> of(E e2) {
        return new SingletonImmutableSet(e2);
    }

    public static <E> ImmutableSet<E> of(E e2, E e3) {
        return construct(2, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e2, E e3, E e4) {
        return construct(3, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e2, E e3, E e4, E e5) {
        return construct(4, e2, e3, e4, e5);
    }

    public static <E> ImmutableSet<E> of(E e2, E e3, E e4, E e5, E e6) {
        return construct(5, e2, e3, e4, e5, e6);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e2, E e3, E e4, E e5, E e6, E e7, E... eArr) {
        Preconditions.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        int length = eArr.length + 6;
        Object[] objArr = new Object[length];
        objArr[0] = e2;
        objArr[1] = e3;
        objArr[2] = e4;
        objArr[3] = e5;
        objArr[4] = e6;
        objArr[5] = e7;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(length, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldTrim(int i2, int i3) {
        return i2 < (i3 >> 1) + (i3 >> 2);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList == null) {
            ImmutableList<E> h2 = h();
            this.asList = h2;
            return h2;
        }
        return immutableList;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && i() && ((ImmutableSet) obj).i() && hashCode() != obj.hashCode()) {
            return false;
        }
        return Sets.a(this, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableList h() {
        return ImmutableList.e(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.b(this);
    }

    boolean i() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public abstract UnmodifiableIterator<E> iterator();
}
