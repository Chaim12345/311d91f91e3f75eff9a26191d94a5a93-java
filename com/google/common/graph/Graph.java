package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use GraphBuilder to create a real instance")
@Beta
/* loaded from: classes2.dex */
public interface Graph<N> extends BaseGraph<N> {
    @Override // 
    Set<N> adjacentNodes(N n2);

    @Override // 
    boolean allowsSelfLoops();

    @Override // com.google.common.graph.BaseGraph
    int degree(N n2);

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> edges();

    boolean equals(@NullableDecl Object obj);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(N n2, N n3);

    int hashCode();

    @Override // com.google.common.graph.BaseGraph
    int inDegree(N n2);

    @Override // com.google.common.graph.BaseGraph
    ElementOrder<N> incidentEdgeOrder();

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> incidentEdges(N n2);

    @Override // 
    boolean isDirected();

    @Override // 
    ElementOrder<N> nodeOrder();

    @Override // 
    Set<N> nodes();

    @Override // com.google.common.graph.BaseGraph
    int outDegree(N n2);

    @Override // 
    Set<N> predecessors(N n2);

    @Override // 
    Set<N> successors(N n2);
}
