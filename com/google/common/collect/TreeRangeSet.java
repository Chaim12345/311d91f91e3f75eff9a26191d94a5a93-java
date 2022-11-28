package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public class TreeRangeSet<C extends Comparable<?>> extends AbstractRangeSet<C> implements Serializable {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final NavigableMap f9085a;
    @NullableDecl
    private transient Set<Range<C>> asDescendingSetOfRanges;
    @NullableDecl
    private transient Set<Range<C>> asRanges;
    @NullableDecl
    private transient RangeSet<C> complement;

    /* loaded from: classes2.dex */
    final class AsRanges extends ForwardingCollection<Range<C>> implements Set<Range<C>> {

        /* renamed from: a  reason: collision with root package name */
        final Collection f9086a;

        AsRanges(TreeRangeSet treeRangeSet, Collection collection) {
            this.f9086a = collection;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: a */
        public Collection delegate() {
            return this.f9086a;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            return Sets.a(this, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.b(this);
        }
    }

    /* loaded from: classes2.dex */
    private final class Complement extends TreeRangeSet<C> {
        Complement() {
            super(new ComplementRangesByLowerBound(TreeRangeSet.this.f9085a));
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void add(Range<C> range) {
            TreeRangeSet.this.remove(range);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.RangeSet
        public RangeSet<C> complement() {
            return TreeRangeSet.this;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public boolean contains(C c2) {
            return !TreeRangeSet.this.contains(c2);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void remove(Range<C> range) {
            TreeRangeSet.this.add(range);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ComplementRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final Range<Cut<C>> complementLowerBoundWindow;
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByUpperBound;

        ComplementRangesByLowerBound(NavigableMap navigableMap) {
            this(navigableMap, Range.all());
        }

        private ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> navigableMap, Range<Cut<C>> range) {
            this.positiveRangesByLowerBound = navigableMap;
            this.positiveRangesByUpperBound = new RangesByUpperBound(navigableMap);
            this.complementLowerBoundWindow = range;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> range) {
            if (this.complementLowerBoundWindow.isConnected(range)) {
                return new ComplementRangesByLowerBound(this.positiveRangesByLowerBound, range.intersection(this.complementLowerBoundWindow));
            }
            return ImmutableSortedMap.of();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator a() {
            NavigableMap<Cut<C>, Range<C>> navigableMap;
            Cut cut;
            if (this.complementLowerBoundWindow.hasLowerBound()) {
                navigableMap = this.positiveRangesByUpperBound.tailMap(this.complementLowerBoundWindow.lowerEndpoint(), this.complementLowerBoundWindow.lowerBoundType() == BoundType.CLOSED);
            } else {
                navigableMap = this.positiveRangesByUpperBound;
            }
            PeekingIterator peekingIterator = Iterators.peekingIterator(navigableMap.values().iterator());
            if (this.complementLowerBoundWindow.contains(Cut.c()) && (!peekingIterator.hasNext() || ((Range) peekingIterator.peek()).f8914a != Cut.c())) {
                cut = Cut.c();
            } else if (!peekingIterator.hasNext()) {
                return Iterators.e();
            } else {
                cut = ((Range) peekingIterator.next()).f8915b;
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(cut, peekingIterator) { // from class: com.google.common.collect.TreeRangeSet.ComplementRangesByLowerBound.1

                /* renamed from: a  reason: collision with root package name */
                Cut f9088a;

                /* renamed from: b  reason: collision with root package name */
                final /* synthetic */ Cut f9089b;

                /* renamed from: c  reason: collision with root package name */
                final /* synthetic */ PeekingIterator f9090c;

                {
                    this.f9089b = cut;
                    this.f9090c = peekingIterator;
                    this.f9088a = cut;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry computeNext() {
                    Range b2;
                    Cut a2;
                    if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.f8915b.j(this.f9088a) || this.f9088a == Cut.a()) {
                        return (Map.Entry) a();
                    }
                    if (this.f9090c.hasNext()) {
                        Range range = (Range) this.f9090c.next();
                        b2 = Range.b(this.f9088a, range.f8914a);
                        a2 = range.f8915b;
                    } else {
                        b2 = Range.b(this.f9088a, Cut.a());
                        a2 = Cut.a();
                    }
                    this.f9088a = a2;
                    return Maps.immutableEntry(b2.f8914a, b2);
                }
            };
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        Iterator b() {
            NavigableMap<Cut<C>, Range<C>> navigableMap;
            Cut<C> c2;
            Cut<C> higherKey;
            PeekingIterator peekingIterator = Iterators.peekingIterator(this.positiveRangesByUpperBound.headMap(this.complementLowerBoundWindow.hasUpperBound() ? this.complementLowerBoundWindow.upperEndpoint() : Cut.a(), this.complementLowerBoundWindow.hasUpperBound() && this.complementLowerBoundWindow.upperBoundType() == BoundType.CLOSED).descendingMap().values().iterator());
            if (peekingIterator.hasNext()) {
                if (((Range) peekingIterator.peek()).f8915b == Cut.a()) {
                    higherKey = ((Range) peekingIterator.next()).f8914a;
                    return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>((Cut) MoreObjects.firstNonNull(higherKey, Cut.a()), peekingIterator) { // from class: com.google.common.collect.TreeRangeSet.ComplementRangesByLowerBound.2

                        /* renamed from: a  reason: collision with root package name */
                        Cut f9092a;

                        /* renamed from: b  reason: collision with root package name */
                        final /* synthetic */ Cut f9093b;

                        /* renamed from: c  reason: collision with root package name */
                        final /* synthetic */ PeekingIterator f9094c;

                        {
                            this.f9093b = r2;
                            this.f9094c = peekingIterator;
                            this.f9092a = r2;
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.google.common.collect.AbstractIterator
                        /* renamed from: b */
                        public Map.Entry computeNext() {
                            if (this.f9092a == Cut.c()) {
                                return (Map.Entry) a();
                            }
                            if (this.f9094c.hasNext()) {
                                Range range = (Range) this.f9094c.next();
                                Range b2 = Range.b(range.f8915b, this.f9092a);
                                this.f9092a = range.f8914a;
                                if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.f8914a.j(b2.f8914a)) {
                                    return Maps.immutableEntry(b2.f8914a, b2);
                                }
                            } else if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.f8914a.j(Cut.c())) {
                                Range b3 = Range.b(Cut.c(), this.f9092a);
                                this.f9092a = Cut.c();
                                return Maps.immutableEntry(Cut.c(), b3);
                            }
                            return (Map.Entry) a();
                        }
                    };
                }
                navigableMap = this.positiveRangesByLowerBound;
                c2 = ((Range) peekingIterator.peek()).f8915b;
            } else if (!this.complementLowerBoundWindow.contains(Cut.c()) || this.positiveRangesByLowerBound.containsKey(Cut.c())) {
                return Iterators.e();
            } else {
                navigableMap = this.positiveRangesByLowerBound;
                c2 = Cut.c();
            }
            higherKey = navigableMap.higherKey(c2);
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>((Cut) MoreObjects.firstNonNull(higherKey, Cut.a()), peekingIterator) { // from class: com.google.common.collect.TreeRangeSet.ComplementRangesByLowerBound.2

                /* renamed from: a  reason: collision with root package name */
                Cut f9092a;

                /* renamed from: b  reason: collision with root package name */
                final /* synthetic */ Cut f9093b;

                /* renamed from: c  reason: collision with root package name */
                final /* synthetic */ PeekingIterator f9094c;

                {
                    this.f9093b = r2;
                    this.f9094c = peekingIterator;
                    this.f9092a = r2;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry computeNext() {
                    if (this.f9092a == Cut.c()) {
                        return (Map.Entry) a();
                    }
                    if (this.f9094c.hasNext()) {
                        Range range = (Range) this.f9094c.next();
                        Range b2 = Range.b(range.f8915b, this.f9092a);
                        this.f9092a = range.f8914a;
                        if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.f8914a.j(b2.f8914a)) {
                            return Maps.immutableEntry(b2.f8914a, b2);
                        }
                    } else if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.f8914a.j(Cut.c())) {
                        Range b3 = Range.b(Cut.c(), this.f9092a);
                        this.f9092a = Cut.c();
                        return Maps.immutableEntry(Cut.c(), b3);
                    }
                    return (Map.Entry) a();
                }
            };
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        @NullableDecl
        public Range<C> get(Object obj) {
            if (obj instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) obj;
                    Map.Entry<Cut<C>, Range<C>> firstEntry = tailMap((Cut) cut, true).firstEntry();
                    if (firstEntry != null && firstEntry.getKey().equals(cut)) {
                        return firstEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return subMap(Range.upTo(cut, BoundType.a(z)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap headMap(Object obj, boolean z) {
            return headMap((Cut) ((Cut) obj), z);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.size(a());
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return subMap(Range.range(cut, BoundType.a(z), cut2, BoundType.a(z2)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
            return subMap((Cut) ((Cut) obj), z, (Cut) ((Cut) obj2), z2);
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return subMap(Range.downTo(cut, BoundType.a(z)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap tailMap(Object obj, boolean z) {
            return tailMap((Cut) ((Cut) obj), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static final class RangesByUpperBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        private final Range<Cut<C>> upperBoundWindow;

        RangesByUpperBound(NavigableMap navigableMap) {
            this.rangesByLowerBound = navigableMap;
            this.upperBoundWindow = Range.all();
        }

        private RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> navigableMap, Range<Cut<C>> range) {
            this.rangesByLowerBound = navigableMap;
            this.upperBoundWindow = range;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> range) {
            return range.isConnected(this.upperBoundWindow) ? new RangesByUpperBound(this.rangesByLowerBound, range.intersection(this.upperBoundWindow)) : ImmutableSortedMap.of();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator a() {
            Map.Entry<Cut<C>, Range<C>> lowerEntry;
            final Iterator<Range<C>> it = ((this.upperBoundWindow.hasLowerBound() && (lowerEntry = this.rangesByLowerBound.lowerEntry(this.upperBoundWindow.lowerEndpoint())) != null) ? this.upperBoundWindow.f8914a.j(lowerEntry.getValue().f8915b) ? this.rangesByLowerBound.tailMap(lowerEntry.getKey(), true) : this.rangesByLowerBound.tailMap(this.upperBoundWindow.lowerEndpoint(), true) : this.rangesByLowerBound).values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() { // from class: com.google.common.collect.TreeRangeSet.RangesByUpperBound.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry computeNext() {
                    if (it.hasNext()) {
                        Range range = (Range) it.next();
                        return RangesByUpperBound.this.upperBoundWindow.f8915b.j(range.f8915b) ? (Map.Entry) a() : Maps.immutableEntry(range.f8915b, range);
                    }
                    return (Map.Entry) a();
                }
            };
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        Iterator b() {
            final PeekingIterator peekingIterator = Iterators.peekingIterator((this.upperBoundWindow.hasUpperBound() ? this.rangesByLowerBound.headMap(this.upperBoundWindow.upperEndpoint(), false) : this.rangesByLowerBound).descendingMap().values().iterator());
            if (peekingIterator.hasNext() && this.upperBoundWindow.f8915b.j(((Range) peekingIterator.peek()).f8915b)) {
                peekingIterator.next();
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() { // from class: com.google.common.collect.TreeRangeSet.RangesByUpperBound.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry computeNext() {
                    if (peekingIterator.hasNext()) {
                        Range range = (Range) peekingIterator.next();
                        return RangesByUpperBound.this.upperBoundWindow.f8914a.j(range.f8915b) ? Maps.immutableEntry(range.f8915b, range) : (Map.Entry) a();
                    }
                    return (Map.Entry) a();
                }
            };
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public Range<C> get(@NullableDecl Object obj) {
            Map.Entry<Cut<C>, Range<C>> lowerEntry;
            if (obj instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) obj;
                    if (this.upperBoundWindow.contains(cut) && (lowerEntry = this.rangesByLowerBound.lowerEntry(cut)) != null && lowerEntry.getValue().f8915b.equals(cut)) {
                        return lowerEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return subMap(Range.upTo(cut, BoundType.a(z)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap headMap(Object obj, boolean z) {
            return headMap((Cut) ((Cut) obj), z);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.upperBoundWindow.equals(Range.all()) ? this.rangesByLowerBound.isEmpty() : !a().hasNext();
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.upperBoundWindow.equals(Range.all()) ? this.rangesByLowerBound.size() : Iterators.size(a());
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return subMap(Range.range(cut, BoundType.a(z), cut2, BoundType.a(z2)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
            return subMap((Cut) ((Cut) obj), z, (Cut) ((Cut) obj2), z2);
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return subMap(Range.downTo(cut, BoundType.a(z)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap tailMap(Object obj, boolean z) {
            return tailMap((Cut) ((Cut) obj), z);
        }
    }

    /* loaded from: classes2.dex */
    private final class SubRangeSet extends TreeRangeSet<C> {
        private final Range<C> restriction;

        SubRangeSet(Range range) {
            super(new SubRangeSetRangesByLowerBound(Range.all(), range, TreeRangeSet.this.f9085a));
            this.restriction = range;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void add(Range<C> range) {
            Preconditions.checkArgument(this.restriction.encloses(range), "Cannot add range %s to subRangeSet(%s)", range, this.restriction);
            super.add(range);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void clear() {
            TreeRangeSet.this.remove(this.restriction);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public boolean contains(C c2) {
            return this.restriction.contains(c2) && TreeRangeSet.this.contains(c2);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public boolean encloses(Range<C> range) {
            Range rangeEnclosing;
            return (this.restriction.isEmpty() || !this.restriction.encloses(range) || (rangeEnclosing = TreeRangeSet.this.rangeEnclosing(range)) == null || rangeEnclosing.intersection(this.restriction).isEmpty()) ? false : true;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        @NullableDecl
        public Range<C> rangeContaining(C c2) {
            Range<C> rangeContaining;
            if (this.restriction.contains(c2) && (rangeContaining = TreeRangeSet.this.rangeContaining(c2)) != null) {
                return rangeContaining.intersection(this.restriction);
            }
            return null;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
        public void remove(Range<C> range) {
            if (range.isConnected(this.restriction)) {
                TreeRangeSet.this.remove(range.intersection(this.restriction));
            }
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.RangeSet
        public RangeSet<C> subRangeSet(Range<C> range) {
            return range.encloses(this.restriction) ? this : range.isConnected(this.restriction) ? new SubRangeSet(this.restriction.intersection(range)) : ImmutableRangeSet.of();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class SubRangeSetRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final Range<Cut<C>> lowerBoundWindow;
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> rangesByUpperBound;
        private final Range<C> restriction;

        private SubRangeSetRangesByLowerBound(Range<Cut<C>> range, Range<C> range2, NavigableMap<Cut<C>, Range<C>> navigableMap) {
            this.lowerBoundWindow = (Range) Preconditions.checkNotNull(range);
            this.restriction = (Range) Preconditions.checkNotNull(range2);
            this.rangesByLowerBound = (NavigableMap) Preconditions.checkNotNull(navigableMap);
            this.rangesByUpperBound = new RangesByUpperBound(navigableMap);
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> range) {
            return !range.isConnected(this.lowerBoundWindow) ? ImmutableSortedMap.of() : new SubRangeSetRangesByLowerBound(this.lowerBoundWindow.intersection(range), this.restriction, this.rangesByLowerBound);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator a() {
            NavigableMap navigableMap;
            Comparable h2;
            if (!this.restriction.isEmpty() && !this.lowerBoundWindow.f8915b.j(this.restriction.f8914a)) {
                boolean z = false;
                if (this.lowerBoundWindow.f8914a.j(this.restriction.f8914a)) {
                    navigableMap = this.rangesByUpperBound;
                    h2 = this.restriction.f8914a;
                } else {
                    navigableMap = this.rangesByLowerBound;
                    h2 = this.lowerBoundWindow.f8914a.h();
                    if (this.lowerBoundWindow.lowerBoundType() == BoundType.CLOSED) {
                        z = true;
                    }
                }
                final Iterator it = navigableMap.tailMap(h2, z).values().iterator();
                final Cut cut = (Cut) Ordering.natural().min(this.lowerBoundWindow.f8915b, Cut.d(this.restriction.f8915b));
                return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() { // from class: com.google.common.collect.TreeRangeSet.SubRangeSetRangesByLowerBound.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: b */
                    public Map.Entry computeNext() {
                        if (it.hasNext()) {
                            Range range = (Range) it.next();
                            if (cut.j(range.f8914a)) {
                                return (Map.Entry) a();
                            }
                            Range intersection = range.intersection(SubRangeSetRangesByLowerBound.this.restriction);
                            return Maps.immutableEntry(intersection.f8914a, intersection);
                        }
                        return (Map.Entry) a();
                    }
                };
            }
            return Iterators.e();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractNavigableMap
        Iterator b() {
            if (this.restriction.isEmpty()) {
                return Iterators.e();
            }
            Cut cut = (Cut) Ordering.natural().min(this.lowerBoundWindow.f8915b, Cut.d(this.restriction.f8915b));
            final Iterator it = this.rangesByLowerBound.headMap(cut.h(), cut.m() == BoundType.CLOSED).descendingMap().values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() { // from class: com.google.common.collect.TreeRangeSet.SubRangeSetRangesByLowerBound.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                /* renamed from: b */
                public Map.Entry computeNext() {
                    if (it.hasNext()) {
                        Range range = (Range) it.next();
                        if (SubRangeSetRangesByLowerBound.this.restriction.f8914a.compareTo(range.f8915b) >= 0) {
                            return (Map.Entry) a();
                        }
                        Range intersection = range.intersection(SubRangeSetRangesByLowerBound.this.restriction);
                        return SubRangeSetRangesByLowerBound.this.lowerBoundWindow.contains(intersection.f8914a) ? Maps.immutableEntry(intersection.f8914a, intersection) : (Map.Entry) a();
                    }
                    return (Map.Entry) a();
                }
            };
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        @NullableDecl
        public Range<C> get(@NullableDecl Object obj) {
            if (obj instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) obj;
                    if (this.lowerBoundWindow.contains(cut) && cut.compareTo(this.restriction.f8914a) >= 0 && cut.compareTo(this.restriction.f8915b) < 0) {
                        if (cut.equals(this.restriction.f8914a)) {
                            Range range = (Range) Maps.E(this.rangesByLowerBound.floorEntry(cut));
                            if (range != null && range.f8915b.compareTo(this.restriction.f8914a) > 0) {
                                return range.intersection(this.restriction);
                            }
                        } else {
                            Range range2 = (Range) this.rangesByLowerBound.get(cut);
                            if (range2 != null) {
                                return range2.intersection(this.restriction);
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return subMap(Range.upTo(cut, BoundType.a(z)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap headMap(Object obj, boolean z) {
            return headMap((Cut) ((Cut) obj), z);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.size(a());
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return subMap(Range.range(cut, BoundType.a(z), cut2, BoundType.a(z2)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
            return subMap((Cut) ((Cut) obj), z, (Cut) ((Cut) obj2), z2);
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return subMap(Range.downTo(cut, BoundType.a(z)));
        }

        @Override // java.util.NavigableMap
        public /* bridge */ /* synthetic */ NavigableMap tailMap(Object obj, boolean z) {
            return tailMap((Cut) ((Cut) obj), z);
        }
    }

    private TreeRangeSet(NavigableMap<Cut<C>, Range<C>> navigableMap) {
        this.f9085a = navigableMap;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create() {
        return new TreeRangeSet<>(new TreeMap());
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(RangeSet<C> rangeSet) {
        TreeRangeSet<C> create = create();
        create.addAll(rangeSet);
        return create;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(Iterable<Range<C>> iterable) {
        TreeRangeSet<C> create = create();
        create.addAll(iterable);
        return create;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public Range<C> rangeEnclosing(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry floorEntry = this.f9085a.floorEntry(range.f8914a);
        if (floorEntry == null || !((Range) floorEntry.getValue()).encloses(range)) {
            return null;
        }
        return (Range) floorEntry.getValue();
    }

    private void replaceRangeWithSameLowerBound(Range<C> range) {
        if (range.isEmpty()) {
            this.f9085a.remove(range.f8914a);
        } else {
            this.f9085a.put(range.f8914a, range);
        }
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public void add(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (range.isEmpty()) {
            return;
        }
        Cut cut = range.f8914a;
        Cut cut2 = range.f8915b;
        Map.Entry lowerEntry = this.f9085a.lowerEntry(cut);
        if (lowerEntry != null) {
            Range range2 = (Range) lowerEntry.getValue();
            if (range2.f8915b.compareTo(cut) >= 0) {
                if (range2.f8915b.compareTo(cut2) >= 0) {
                    cut2 = range2.f8915b;
                }
                cut = range2.f8914a;
            }
        }
        Map.Entry floorEntry = this.f9085a.floorEntry(cut2);
        if (floorEntry != null) {
            Range range3 = (Range) floorEntry.getValue();
            if (range3.f8915b.compareTo(cut2) >= 0) {
                cut2 = range3.f8915b;
            }
        }
        this.f9085a.subMap(cut, cut2).clear();
        replaceRangeWithSameLowerBound(Range.b(cut, cut2));
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void addAll(RangeSet rangeSet) {
        super.addAll(rangeSet);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void addAll(Iterable iterable) {
        super.addAll(iterable);
    }

    @Override // com.google.common.collect.RangeSet
    public Set<Range<C>> asDescendingSetOfRanges() {
        Set<Range<C>> set = this.asDescendingSetOfRanges;
        if (set == null) {
            AsRanges asRanges = new AsRanges(this, this.f9085a.descendingMap().values());
            this.asDescendingSetOfRanges = asRanges;
            return asRanges;
        }
        return set;
    }

    @Override // com.google.common.collect.RangeSet
    public Set<Range<C>> asRanges() {
        Set<Range<C>> set = this.asRanges;
        if (set == null) {
            AsRanges asRanges = new AsRanges(this, this.f9085a.values());
            this.asRanges = asRanges;
            return asRanges;
        }
        return set;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.RangeSet
    public RangeSet<C> complement() {
        RangeSet<C> rangeSet = this.complement;
        if (rangeSet == null) {
            Complement complement = new Complement();
            this.complement = complement;
            return complement;
        }
        return rangeSet;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public boolean encloses(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry floorEntry = this.f9085a.floorEntry(range.f8914a);
        return floorEntry != null && ((Range) floorEntry.getValue()).encloses(range);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet rangeSet) {
        return super.enclosesAll(rangeSet);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(Iterable iterable) {
        return super.enclosesAll(iterable);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public boolean intersects(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry ceilingEntry = this.f9085a.ceilingEntry(range.f8914a);
        if (ceilingEntry == null || !((Range) ceilingEntry.getValue()).isConnected(range) || ((Range) ceilingEntry.getValue()).intersection(range).isEmpty()) {
            Map.Entry lowerEntry = this.f9085a.lowerEntry(range.f8914a);
            return (lowerEntry == null || !((Range) lowerEntry.getValue()).isConnected(range) || ((Range) lowerEntry.getValue()).intersection(range).isEmpty()) ? false : true;
        }
        return true;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    @NullableDecl
    public Range<C> rangeContaining(C c2) {
        Preconditions.checkNotNull(c2);
        Map.Entry floorEntry = this.f9085a.floorEntry(Cut.d(c2));
        if (floorEntry == null || !((Range) floorEntry.getValue()).contains(c2)) {
            return null;
        }
        return (Range) floorEntry.getValue();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public void remove(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (range.isEmpty()) {
            return;
        }
        Map.Entry lowerEntry = this.f9085a.lowerEntry(range.f8914a);
        if (lowerEntry != null) {
            Range range2 = (Range) lowerEntry.getValue();
            if (range2.f8915b.compareTo(range.f8914a) >= 0) {
                if (range.hasUpperBound() && range2.f8915b.compareTo(range.f8915b) >= 0) {
                    replaceRangeWithSameLowerBound(Range.b(range.f8915b, range2.f8915b));
                }
                replaceRangeWithSameLowerBound(Range.b(range2.f8914a, range.f8914a));
            }
        }
        Map.Entry floorEntry = this.f9085a.floorEntry(range.f8915b);
        if (floorEntry != null) {
            Range range3 = (Range) floorEntry.getValue();
            if (range.hasUpperBound() && range3.f8915b.compareTo(range.f8915b) >= 0) {
                replaceRangeWithSameLowerBound(Range.b(range.f8915b, range3.f8915b));
            }
        }
        this.f9085a.subMap(range.f8914a, range.f8915b).clear();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void removeAll(RangeSet rangeSet) {
        super.removeAll(rangeSet);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void removeAll(Iterable iterable) {
        super.removeAll(iterable);
    }

    @Override // com.google.common.collect.RangeSet
    public Range<C> span() {
        Map.Entry firstEntry = this.f9085a.firstEntry();
        Map.Entry lastEntry = this.f9085a.lastEntry();
        if (firstEntry != null) {
            return Range.b(((Range) firstEntry.getValue()).f8914a, ((Range) lastEntry.getValue()).f8915b);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.RangeSet
    public RangeSet<C> subRangeSet(Range<C> range) {
        return range.equals(Range.all()) ? this : new SubRangeSet(range);
    }
}
