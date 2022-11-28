package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class StandardMutableNetwork<N, E> extends StandardNetwork<N, E> implements MutableNetwork<N, E> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardMutableNetwork(NetworkBuilder networkBuilder) {
        super(networkBuilder);
    }

    @CanIgnoreReturnValue
    private NetworkConnections<N, E> addNodeInternal(N n2) {
        NetworkConnections<N, E> newConnections = newConnections();
        Preconditions.checkState(this.f9189a.put(n2, newConnections) == null);
        return newConnections;
    }

    private NetworkConnections<N, E> newConnections() {
        return isDirected() ? allowsParallelEdges() ? DirectedMultiNetworkConnections.c() : DirectedNetworkConnections.b() : allowsParallelEdges() ? UndirectedMultiNetworkConnections.b() : UndirectedNetworkConnections.a();
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(EndpointPair<N> endpointPair, E e2) {
        b(endpointPair);
        return addEdge(endpointPair.nodeU(), endpointPair.nodeV(), e2);
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(N n2, N n3, E e2) {
        Preconditions.checkNotNull(n2, "nodeU");
        Preconditions.checkNotNull(n3, "nodeV");
        Preconditions.checkNotNull(e2, "edge");
        boolean z = false;
        if (e(e2)) {
            EndpointPair<N> incidentNodes = incidentNodes(e2);
            EndpointPair b2 = EndpointPair.b(this, n2, n3);
            Preconditions.checkArgument(incidentNodes.equals(b2), "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.", e2, incidentNodes, b2);
            return false;
        }
        NetworkConnections<N, E> networkConnections = (NetworkConnections) this.f9189a.get(n2);
        if (!allowsParallelEdges()) {
            if (networkConnections == null || !networkConnections.successors().contains(n3)) {
                z = true;
            }
            Preconditions.checkArgument(z, "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.", n2, n3);
        }
        boolean equals = n2.equals(n3);
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!equals, "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", n2);
        }
        if (networkConnections == null) {
            networkConnections = addNodeInternal(n2);
        }
        networkConnections.addOutEdge(e2, n3);
        NetworkConnections<N, E> networkConnections2 = (NetworkConnections) this.f9189a.get(n3);
        if (networkConnections2 == null) {
            networkConnections2 = addNodeInternal(n3);
        }
        networkConnections2.addInEdge(e2, n2, equals);
        this.f9190b.put(e2, n2);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        if (f(n2)) {
            return false;
        }
        addNodeInternal(n2);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeEdge(E e2) {
        Preconditions.checkNotNull(e2, "edge");
        Object obj = this.f9190b.get(e2);
        boolean z = false;
        if (obj == null) {
            return false;
        }
        NetworkConnections networkConnections = (NetworkConnections) this.f9189a.get(obj);
        Object adjacentNode = networkConnections.adjacentNode(e2);
        NetworkConnections networkConnections2 = (NetworkConnections) this.f9189a.get(adjacentNode);
        networkConnections.removeOutEdge(e2);
        if (allowsSelfLoops() && obj.equals(adjacentNode)) {
            z = true;
        }
        networkConnections2.removeInEdge(e2, z);
        this.f9190b.remove(e2);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        NetworkConnections networkConnections = (NetworkConnections) this.f9189a.get(n2);
        if (networkConnections == null) {
            return false;
        }
        UnmodifiableIterator<E> it = ImmutableList.copyOf((Collection) networkConnections.incidentEdges()).iterator();
        while (it.hasNext()) {
            removeEdge(it.next());
        }
        this.f9189a.remove(n2);
        return true;
    }
}
