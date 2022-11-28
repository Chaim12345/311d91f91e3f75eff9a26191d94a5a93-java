package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class StandardMutableValueGraph<N, V> extends StandardValueGraph<N, V> implements MutableValueGraph<N, V> {
    private final ElementOrder<N> incidentEdgeOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardMutableValueGraph(AbstractGraphBuilder abstractGraphBuilder) {
        super(abstractGraphBuilder);
        this.incidentEdgeOrder = abstractGraphBuilder.f9136d.a();
    }

    @CanIgnoreReturnValue
    private GraphConnections<N, V> addNodeInternal(N n2) {
        GraphConnections<N, V> newConnections = newConnections();
        Preconditions.checkState(this.f9191a.put(n2, newConnections) == null);
        return newConnections;
    }

    private GraphConnections<N, V> newConnections() {
        return isDirected() ? DirectedGraphConnections.g(this.incidentEdgeOrder) : UndirectedGraphConnections.a(this.incidentEdgeOrder);
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean addNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        if (e(n2)) {
            return false;
        }
        addNodeInternal(n2);
        return true;
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return this.incidentEdgeOrder;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(EndpointPair<N> endpointPair, V v) {
        c(endpointPair);
        return putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), v);
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(N n2, N n3, V v) {
        Preconditions.checkNotNull(n2, "nodeU");
        Preconditions.checkNotNull(n3, "nodeV");
        Preconditions.checkNotNull(v, "value");
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!n2.equals(n3), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", n2);
        }
        GraphConnections<N, V> graphConnections = (GraphConnections) this.f9191a.get(n2);
        if (graphConnections == null) {
            graphConnections = addNodeInternal(n2);
        }
        V addSuccessor = graphConnections.addSuccessor(n3, v);
        GraphConnections<N, V> graphConnections2 = (GraphConnections) this.f9191a.get(n3);
        if (graphConnections2 == null) {
            graphConnections2 = addNodeInternal(n3);
        }
        graphConnections2.addPredecessor(n2, v);
        if (addSuccessor == null) {
            long j2 = this.f9192b + 1;
            this.f9192b = j2;
            Graphs.d(j2);
        }
        return addSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(EndpointPair<N> endpointPair) {
        c(endpointPair);
        return removeEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(N n2, N n3) {
        Preconditions.checkNotNull(n2, "nodeU");
        Preconditions.checkNotNull(n3, "nodeV");
        GraphConnections graphConnections = (GraphConnections) this.f9191a.get(n2);
        GraphConnections graphConnections2 = (GraphConnections) this.f9191a.get(n3);
        if (graphConnections == null || graphConnections2 == null) {
            return null;
        }
        V v = (V) graphConnections.removeSuccessor(n3);
        if (v != null) {
            graphConnections2.removePredecessor(n2);
            long j2 = this.f9192b - 1;
            this.f9192b = j2;
            Graphs.b(j2);
        }
        return v;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean removeNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        GraphConnections graphConnections = (GraphConnections) this.f9191a.get(n2);
        if (graphConnections == null) {
            return false;
        }
        if (allowsSelfLoops() && graphConnections.removeSuccessor(n2) != null) {
            graphConnections.removePredecessor(n2);
            this.f9192b--;
        }
        for (N n3 : graphConnections.successors()) {
            ((GraphConnections) this.f9191a.getWithoutCaching(n3)).removePredecessor(n2);
            this.f9192b--;
        }
        if (isDirected()) {
            for (N n4 : graphConnections.predecessors()) {
                Preconditions.checkState(((GraphConnections) this.f9191a.getWithoutCaching(n4)).removeSuccessor(n2) != null);
                this.f9192b--;
            }
        }
        this.f9191a.remove(n2);
        Graphs.b(this.f9192b);
        return true;
    }
}
