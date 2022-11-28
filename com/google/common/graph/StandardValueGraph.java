package com.google.common.graph;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes2.dex */
class StandardValueGraph<N, V> extends AbstractValueGraph<N, V> {

    /* renamed from: a  reason: collision with root package name */
    protected final MapIteratorCache f9191a;
    private final boolean allowsSelfLoops;

    /* renamed from: b  reason: collision with root package name */
    protected long f9192b;
    private final boolean isDirected;
    private final ElementOrder<N> nodeOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardValueGraph(AbstractGraphBuilder abstractGraphBuilder) {
        this(abstractGraphBuilder, abstractGraphBuilder.f9135c.b(((Integer) abstractGraphBuilder.f9137e.or((Optional) 10)).intValue()), 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardValueGraph(AbstractGraphBuilder abstractGraphBuilder, Map map, long j2) {
        this.isDirected = abstractGraphBuilder.f9133a;
        this.allowsSelfLoops = abstractGraphBuilder.f9134b;
        this.nodeOrder = abstractGraphBuilder.f9135c.a();
        this.f9191a = map instanceof TreeMap ? new MapRetrievalCache(map) : new MapIteratorCache(map);
        this.f9192b = Graphs.b(j2);
    }

    @Override // com.google.common.graph.AbstractBaseGraph
    protected long a() {
        return this.f9192b;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n2) {
        return d(n2).adjacentNodes();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    protected final GraphConnections d(Object obj) {
        GraphConnections graphConnections = (GraphConnections) this.f9191a.get(obj);
        if (graphConnections != null) {
            return graphConnections;
        }
        Preconditions.checkNotNull(obj);
        throw new IllegalArgumentException("Node " + obj + " is not an element of this graph.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean e(@NullableDecl Object obj) {
        return this.f9191a.containsKey(obj);
    }

    @NullableDecl
    public V edgeValueOrDefault(EndpointPair<N> endpointPair, @NullableDecl V v) {
        c(endpointPair);
        return (V) f(endpointPair.nodeU(), endpointPair.nodeV(), v);
    }

    @NullableDecl
    public V edgeValueOrDefault(N n2, N n3, @NullableDecl V v) {
        return (V) f(Preconditions.checkNotNull(n2), Preconditions.checkNotNull(n3), v);
    }

    protected final Object f(Object obj, Object obj2, Object obj3) {
        GraphConnections graphConnections = (GraphConnections) this.f9191a.get(obj);
        Object value = graphConnections == null ? null : graphConnections.value(obj2);
        return value == null ? obj3 : value;
    }

    protected final boolean g(Object obj, Object obj2) {
        GraphConnections graphConnections = (GraphConnections) this.f9191a.get(obj);
        return graphConnections != null && graphConnections.successors().contains(obj2);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        return b(endpointPair) && g(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n2, N n3) {
        return g(Preconditions.checkNotNull(n2), Preconditions.checkNotNull(n3));
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n2) {
        final GraphConnections d2 = d(n2);
        return new IncidentEdgeSet<N>(this, this, n2) { // from class: com.google.common.graph.StandardValueGraph.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<EndpointPair<N>> iterator() {
                return d2.incidentEdgeIterator(this.f9177a);
            }
        };
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> nodes() {
        return this.f9191a.unmodifiableKeySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((StandardValueGraph<N, V>) obj);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    public Set<N> predecessors(N n2) {
        return d(n2).predecessors();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((StandardValueGraph<N, V>) obj);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    public Set<N> successors(N n2) {
        return d(n2).successors();
    }
}
