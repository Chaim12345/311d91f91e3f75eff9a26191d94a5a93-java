package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {

    /* renamed from: a  reason: collision with root package name */
    protected final Map f9130a;

    /* renamed from: b  reason: collision with root package name */
    protected final Map f9131b;
    private int selfLoopCount;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDirectedNetworkConnections(Map map, Map map2, int i2) {
        this.f9130a = (Map) Preconditions.checkNotNull(map);
        this.f9131b = (Map) Preconditions.checkNotNull(map2);
        this.selfLoopCount = Graphs.a(i2);
        Preconditions.checkState(i2 <= map.size() && i2 <= map2.size());
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E e2, N n2, boolean z) {
        Preconditions.checkNotNull(e2);
        Preconditions.checkNotNull(n2);
        if (z) {
            int i2 = this.selfLoopCount + 1;
            this.selfLoopCount = i2;
            Graphs.c(i2);
        }
        Preconditions.checkState(this.f9130a.put(e2, n2) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E e2, N n2) {
        Preconditions.checkNotNull(e2);
        Preconditions.checkNotNull(n2);
        Preconditions.checkState(this.f9131b.put(e2, n2) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E e2) {
        return (N) Preconditions.checkNotNull(this.f9131b.get(e2));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.f9130a.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return new AbstractSet<E>() { // from class: com.google.common.graph.AbstractDirectedNetworkConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return AbstractDirectedNetworkConnections.this.f9130a.containsKey(obj) || AbstractDirectedNetworkConnections.this.f9131b.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                return Iterators.unmodifiableIterator((AbstractDirectedNetworkConnections.this.selfLoopCount == 0 ? Iterables.concat(AbstractDirectedNetworkConnections.this.f9130a.keySet(), AbstractDirectedNetworkConnections.this.f9131b.keySet()) : Sets.union(AbstractDirectedNetworkConnections.this.f9130a.keySet(), AbstractDirectedNetworkConnections.this.f9131b.keySet())).iterator());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.f9130a.size(), AbstractDirectedNetworkConnections.this.f9131b.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.f9131b.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E e2, boolean z) {
        if (z) {
            int i2 = this.selfLoopCount - 1;
            this.selfLoopCount = i2;
            Graphs.a(i2);
        }
        return (N) Preconditions.checkNotNull(this.f9130a.remove(e2));
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e2) {
        return (N) Preconditions.checkNotNull(this.f9131b.remove(e2));
    }
}
