package com.google.common.graph;

import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class ForwardingNetwork<N, E> extends AbstractNetwork<N, E> {
    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> adjacentEdges(E e2) {
        return c().adjacentEdges(e2);
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n2) {
        return c().adjacentNodes(n2);
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return c().allowsParallelEdges();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return c().allowsSelfLoops();
    }

    protected abstract Network c();

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int degree(N n2) {
        return c().degree(n2);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
        return (E) c().edgeConnectingOrNull(endpointPair);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public E edgeConnectingOrNull(N n2, N n3) {
        return (E) c().edgeConnectingOrNull(n2, n3);
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return c().edgeOrder();
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        return c().edges();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
        return c().edgesConnecting(endpointPair);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(N n2, N n3) {
        return c().edgesConnecting(n2, n3);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        return c().hasEdgeConnecting(endpointPair);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n2, N n3) {
        return c().hasEdgeConnecting(n2, n3);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int inDegree(N n2) {
        return c().inDegree(n2);
    }

    @Override // com.google.common.graph.Network
    public Set<E> inEdges(N n2) {
        return c().inEdges(n2);
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n2) {
        return c().incidentEdges(n2);
    }

    @Override // com.google.common.graph.Network
    public EndpointPair<N> incidentNodes(E e2) {
        return c().incidentNodes(e2);
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return c().isDirected();
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return c().nodeOrder();
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        return c().nodes();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int outDegree(N n2) {
        return c().outDegree(n2);
    }

    @Override // com.google.common.graph.Network
    public Set<E> outEdges(N n2) {
        return c().outEdges(n2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((ForwardingNetwork<N, E>) obj);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    public Set<N> predecessors(N n2) {
        return c().predecessors((Network) n2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((ForwardingNetwork<N, E>) obj);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    public Set<N> successors(N n2) {
        return c().successors((Network) n2);
    }
}
