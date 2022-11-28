package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes2.dex */
public class FilteredKeyMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    final Multimap f8524a;

    /* renamed from: b  reason: collision with root package name */
    final Predicate f8525b;

    /* loaded from: classes2.dex */
    static class AddRejectingList<K, V> extends ForwardingList<V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8526a;

        AddRejectingList(Object obj) {
            this.f8526a = obj;
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        public void add(int i2, V v) {
            Preconditions.checkPositionIndex(i2, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.f8526a);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            add(0, v);
            return true;
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        @CanIgnoreReturnValue
        public boolean addAll(int i2, Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            Preconditions.checkPositionIndex(i2, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.f8526a);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            addAll(0, collection);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingList, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: h */
        public List delegate() {
            return Collections.emptyList();
        }
    }

    /* loaded from: classes2.dex */
    static class AddRejectingSet<K, V> extends ForwardingSet<V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8527a;

        AddRejectingSet(Object obj) {
            this.f8527a = obj;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.f8527a);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.f8527a);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: h */
        public Set delegate() {
            return Collections.emptySet();
        }
    }

    /* loaded from: classes2.dex */
    class Entries extends ForwardingCollection<Map.Entry<K, V>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Entries() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: a */
        public Collection delegate() {
            return Collections2.filter(FilteredKeyMultimap.this.f8524a.entries(), FilteredKeyMultimap.this.entryPredicate());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (FilteredKeyMultimap.this.f8524a.containsKey(entry.getKey()) && FilteredKeyMultimap.this.f8525b.apply(entry.getKey())) {
                    return FilteredKeyMultimap.this.f8524a.remove(entry.getKey(), entry.getValue());
                }
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteredKeyMultimap(Multimap multimap, Predicate predicate) {
        this.f8524a = (Multimap) Preconditions.checkNotNull(multimap);
        this.f8525b = (Predicate) Preconditions.checkNotNull(predicate);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Map a() {
        return Maps.filterKeys(this.f8524a.asMap(), this.f8525b);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Collection b() {
        return new Entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    Set c() {
        return Sets.filter(this.f8524a.keySet(), this.f8525b);
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        keySet().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        if (this.f8524a.containsKey(obj)) {
            return this.f8525b.apply(obj);
        }
        return false;
    }

    @Override // com.google.common.collect.AbstractMultimap
    Multiset d() {
        return Multisets.filter(this.f8524a.keys(), this.f8525b);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Collection e() {
        return new FilteredMultimapValues(this);
    }

    @Override // com.google.common.collect.FilteredMultimap
    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return Maps.r(this.f8525b);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Iterator f() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> get(K k2) {
        return this.f8525b.apply(k2) ? this.f8524a.get(k2) : this.f8524a instanceof SetMultimap ? new AddRejectingSet(k2) : new AddRejectingList(k2);
    }

    Collection h() {
        return this.f8524a instanceof SetMultimap ? ImmutableSet.of() : ImmutableList.of();
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> removeAll(Object obj) {
        return containsKey(obj) ? this.f8524a.removeAll(obj) : h();
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        int i2 = 0;
        for (Collection<V> collection : asMap().values()) {
            i2 += collection.size();
        }
        return i2;
    }

    public Multimap<K, V> unfiltered() {
        return this.f8524a;
    }
}
