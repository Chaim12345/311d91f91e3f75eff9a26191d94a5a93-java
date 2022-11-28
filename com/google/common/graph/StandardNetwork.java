package com.google.common.graph;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class StandardNetwork<N, E> extends AbstractNetwork<N, E> {

    /* renamed from: a  reason: collision with root package name */
    protected final MapIteratorCache f9189a;
    private final boolean allowsParallelEdges;
    private final boolean allowsSelfLoops;

    /* renamed from: b  reason: collision with root package name */
    protected final MapIteratorCache f9190b;
    private final ElementOrder<E> edgeOrder;
    private final boolean isDirected;
    private final ElementOrder<N> nodeOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardNetwork(NetworkBuilder networkBuilder) {
        this(networkBuilder, networkBuilder.f9135c.b(((Integer) networkBuilder.f9137e.or((Optional) 10)).intValue()), networkBuilder.f9187g.b(((Integer) networkBuilder.f9188h.or((Optional) 20)).intValue()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardNetwork(NetworkBuilder networkBuilder, Map map, Map map2) {
        this.isDirected = networkBuilder.f9133a;
        this.allowsParallelEdges = networkBuilder.f9186f;
        this.allowsSelfLoops = networkBuilder.f9134b;
        this.nodeOrder = networkBuilder.f9135c.a();
        this.edgeOrder = networkBuilder.f9187g.a();
        this.f9189a = map instanceof TreeMap ? new MapRetrievalCache(map) : new MapIteratorCache(map);
        this.f9190b = new MapIteratorCache(map2);
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n2) {
        return c(n2).adjacentNodes();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    protected final NetworkConnections c(Object obj) {
        NetworkConnections networkConnections = (NetworkConnections) this.f9189a.get(obj);
        if (networkConnections != null) {
            return networkConnections;
        }
        Preconditions.checkNotNull(obj);
        throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", obj));
    }

    protected final Object d(Object obj) {
        Object obj2 = this.f9190b.get(obj);
        if (obj2 != null) {
            return obj2;
        }
        Preconditions.checkNotNull(obj);
        throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", obj));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean e(@NullableDecl Object obj) {
        return this.f9190b.containsKey(obj);
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return this.edgeOrder;
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        return this.f9190b.unmodifiableKeySet();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(N n2, N n3) {
        NetworkConnections c2 = c(n2);
        if (this.allowsSelfLoops || n2 != n3) {
            Preconditions.checkArgument(f(n3), "Node %s is not an element of this graph.", n3);
            return c2.edgesConnecting(n3);
        }
        return ImmutableSet.of();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean f(@NullableDecl Object obj) {
        return this.f9189a.containsKey(obj);
    }

    @Override // com.google.common.graph.Network
    public Set<E> inEdges(N n2) {
        return c(n2).inEdges();
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n2) {
        return c(n2).incidentEdges();
    }

    @Override // com.google.common.graph.Network
    public EndpointPair<N> incidentNodes(E e2) {
        Object d2 = d(e2);
        return EndpointPair.b(this, d2, ((NetworkConnections) this.f9189a.get(d2)).adjacentNode(e2));
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        return this.f9189a.unmodifiableKeySet();
    }

    @Override // com.google.common.graph.Network
    public Set<E> outEdges(N n2) {
        return c(n2).outEdges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((StandardNetwork<N, E>) obj);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    public Set<N> predecessors(N n2) {
        return c(n2).predecessors();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((StandardNetwork<N, E>) obj);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    public Set<N> successors(N n2) {
        return c(n2).successors();
    }
}
