package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public final class Range<C extends Comparable> extends RangeGwtSerializationDependencies implements Predicate<C> {
    private static final Range<Comparable> ALL = new Range<>(Cut.c(), Cut.a());
    private static final long serialVersionUID = 0;

    /* renamed from: a  reason: collision with root package name */
    final Cut f8914a;

    /* renamed from: b  reason: collision with root package name */
    final Cut f8915b;

    /* renamed from: com.google.common.collect.Range$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8916a;

        static {
            int[] iArr = new int[BoundType.values().length];
            f8916a = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8916a[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes2.dex */
    static class LowerBoundFn implements Function<Range, Cut> {

        /* renamed from: a  reason: collision with root package name */
        static final LowerBoundFn f8917a = new LowerBoundFn();

        LowerBoundFn() {
        }

        @Override // com.google.common.base.Function
        public Cut apply(Range range) {
            return range.f8914a;
        }
    }

    /* loaded from: classes2.dex */
    private static class RangeLexOrdering extends Ordering<Range<?>> implements Serializable {

        /* renamed from: a  reason: collision with root package name */
        static final Ordering f8918a = new RangeLexOrdering();
        private static final long serialVersionUID = 0;

        private RangeLexOrdering() {
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Range<?> range, Range<?> range2) {
            return ComparisonChain.start().compare(range.f8914a, range2.f8914a).compare(range.f8915b, range2.f8915b).result();
        }
    }

    /* loaded from: classes2.dex */
    static class UpperBoundFn implements Function<Range, Cut> {

        /* renamed from: a  reason: collision with root package name */
        static final UpperBoundFn f8919a = new UpperBoundFn();

        UpperBoundFn() {
        }

        @Override // com.google.common.base.Function
        public Cut apply(Range range) {
            return range.f8915b;
        }
    }

    private Range(Cut<C> cut, Cut<C> cut2) {
        this.f8914a = (Cut) Preconditions.checkNotNull(cut);
        this.f8915b = (Cut) Preconditions.checkNotNull(cut2);
        if (cut.compareTo((Cut) cut2) > 0 || cut == Cut.a() || cut2 == Cut.c()) {
            throw new IllegalArgumentException("Invalid range: " + toString(cut, cut2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return (Range<C>) ALL;
    }

    public static <C extends Comparable<?>> Range<C> atLeast(C c2) {
        return b(Cut.d(c2), Cut.a());
    }

    public static <C extends Comparable<?>> Range<C> atMost(C c2) {
        return b(Cut.c(), Cut.b(c2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Range b(Cut cut, Cut cut2) {
        return new Range(cut, cut2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Function c() {
        return LowerBoundFn.f8917a;
    }

    private static <T> SortedSet<T> cast(Iterable<T> iterable) {
        return (SortedSet) iterable;
    }

    public static <C extends Comparable<?>> Range<C> closed(C c2, C c3) {
        return b(Cut.d(c2), Cut.b(c3));
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C c2, C c3) {
        return b(Cut.d(c2), Cut.d(c3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Ordering d() {
        return RangeLexOrdering.f8918a;
    }

    public static <C extends Comparable<?>> Range<C> downTo(C c2, BoundType boundType) {
        int i2 = AnonymousClass1.f8916a[boundType.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return atLeast(c2);
            }
            throw new AssertionError();
        }
        return greaterThan(c2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Function e() {
        return UpperBoundFn.f8919a;
    }

    public static <C extends Comparable<?>> Range<C> encloseAll(Iterable<C> iterable) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof SortedSet) {
            SortedSet cast = cast(iterable);
            Comparator comparator = cast.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return closed((Comparable) cast.first(), (Comparable) cast.last());
            }
        }
        Iterator<C> it = iterable.iterator();
        Comparable comparable = (Comparable) Preconditions.checkNotNull(it.next());
        Comparable comparable2 = comparable;
        while (it.hasNext()) {
            Comparable comparable3 = (Comparable) Preconditions.checkNotNull(it.next());
            comparable = (Comparable) Ordering.natural().min(comparable, comparable3);
            comparable2 = (Comparable) Ordering.natural().max(comparable2, comparable3);
        }
        return closed(comparable, comparable2);
    }

    public static <C extends Comparable<?>> Range<C> greaterThan(C c2) {
        return b(Cut.b(c2), Cut.a());
    }

    public static <C extends Comparable<?>> Range<C> lessThan(C c2) {
        return b(Cut.c(), Cut.d(c2));
    }

    public static <C extends Comparable<?>> Range<C> open(C c2, C c3) {
        return b(Cut.b(c2), Cut.d(c3));
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C c2, C c3) {
        return b(Cut.b(c2), Cut.b(c3));
    }

    public static <C extends Comparable<?>> Range<C> range(C c2, BoundType boundType, C c3, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        BoundType boundType3 = BoundType.OPEN;
        return b(boundType == boundType3 ? Cut.b(c2) : Cut.d(c2), boundType2 == boundType3 ? Cut.d(c3) : Cut.b(c3));
    }

    public static <C extends Comparable<?>> Range<C> singleton(C c2) {
        return closed(c2, c2);
    }

    private static String toString(Cut<?> cut, Cut<?> cut2) {
        StringBuilder sb = new StringBuilder(16);
        cut.f(sb);
        sb.append("..");
        cut2.g(sb);
        return sb.toString();
    }

    public static <C extends Comparable<?>> Range<C> upTo(C c2, BoundType boundType) {
        int i2 = AnonymousClass1.f8916a[boundType.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return atMost(c2);
            }
            throw new AssertionError();
        }
        return lessThan(c2);
    }

    @Deprecated
    public boolean apply(C c2) {
        return contains(c2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.base.Predicate
    @Deprecated
    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return apply((Range<C>) ((Comparable) obj));
    }

    public Range<C> canonical(DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(discreteDomain);
        Cut e2 = this.f8914a.e(discreteDomain);
        Cut e3 = this.f8915b.e(discreteDomain);
        return (e2 == this.f8914a && e3 == this.f8915b) ? this : b(e2, e3);
    }

    public boolean contains(C c2) {
        Preconditions.checkNotNull(c2);
        return this.f8914a.j(c2) && !this.f8915b.j(c2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsAll(Iterable<? extends C> iterable) {
        if (Iterables.isEmpty(iterable)) {
            return true;
        }
        if (iterable instanceof SortedSet) {
            SortedSet cast = cast(iterable);
            Comparator comparator = cast.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return contains((Comparable) cast.first()) && contains((Comparable) cast.last());
            }
        }
        for (C c2 : iterable) {
            if (!contains(c2)) {
                return false;
            }
        }
        return true;
    }

    public boolean encloses(Range<C> range) {
        return this.f8914a.compareTo((Cut) range.f8914a) <= 0 && this.f8915b.compareTo((Cut) range.f8915b) >= 0;
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof Range) {
            Range range = (Range) obj;
            return this.f8914a.equals(range.f8914a) && this.f8915b.equals(range.f8915b);
        }
        return false;
    }

    public Range<C> gap(Range<C> range) {
        boolean z = this.f8914a.compareTo((Cut) range.f8914a) < 0;
        Range<C> range2 = z ? this : range;
        if (!z) {
            range = this;
        }
        return b(range2.f8915b, range.f8914a);
    }

    public boolean hasLowerBound() {
        return this.f8914a != Cut.c();
    }

    public boolean hasUpperBound() {
        return this.f8915b != Cut.a();
    }

    public int hashCode() {
        return (this.f8914a.hashCode() * 31) + this.f8915b.hashCode();
    }

    public Range<C> intersection(Range<C> range) {
        int compareTo = this.f8914a.compareTo((Cut) range.f8914a);
        int compareTo2 = this.f8915b.compareTo((Cut) range.f8915b);
        if (compareTo < 0 || compareTo2 > 0) {
            if (compareTo > 0 || compareTo2 < 0) {
                return b(compareTo >= 0 ? this.f8914a : range.f8914a, compareTo2 <= 0 ? this.f8915b : range.f8915b);
            }
            return range;
        }
        return this;
    }

    public boolean isConnected(Range<C> range) {
        return this.f8914a.compareTo((Cut) range.f8915b) <= 0 && range.f8914a.compareTo((Cut) this.f8915b) <= 0;
    }

    public boolean isEmpty() {
        return this.f8914a.equals(this.f8915b);
    }

    public BoundType lowerBoundType() {
        return this.f8914a.l();
    }

    public C lowerEndpoint() {
        return (C) this.f8914a.h();
    }

    public Range<C> span(Range<C> range) {
        int compareTo = this.f8914a.compareTo((Cut) range.f8914a);
        int compareTo2 = this.f8915b.compareTo((Cut) range.f8915b);
        if (compareTo > 0 || compareTo2 < 0) {
            if (compareTo < 0 || compareTo2 > 0) {
                return b(compareTo <= 0 ? this.f8914a : range.f8914a, compareTo2 >= 0 ? this.f8915b : range.f8915b);
            }
            return range;
        }
        return this;
    }

    public String toString() {
        return toString(this.f8914a, this.f8915b);
    }

    public BoundType upperBoundType() {
        return this.f8915b.m();
    }

    public C upperEndpoint() {
        return (C) this.f8915b.h();
    }
}
