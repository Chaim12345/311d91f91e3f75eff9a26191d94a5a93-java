package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes2.dex */
interface GraphConnections<N, V> {
    void addPredecessor(N n2, V v);

    @CanIgnoreReturnValue
    V addSuccessor(N n2, V v);

    Set<N> adjacentNodes();

    Iterator<EndpointPair<N>> incidentEdgeIterator(N n2);

    Set<N> predecessors();

    void removePredecessor(N n2);

    @CanIgnoreReturnValue
    V removeSuccessor(N n2);

    Set<N> successors();

    @NullableDecl
    V value(N n2);
}
