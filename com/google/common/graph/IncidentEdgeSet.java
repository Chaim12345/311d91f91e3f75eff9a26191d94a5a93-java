package com.google.common.graph;

import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes2.dex */
abstract class IncidentEdgeSet<N> extends AbstractSet<EndpointPair<N>> {

    /* renamed from: a  reason: collision with root package name */
    protected final Object f9177a;

    /* renamed from: b  reason: collision with root package name */
    protected final BaseGraph f9178b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IncidentEdgeSet(BaseGraph baseGraph, Object obj) {
        this.f9178b = baseGraph;
        this.f9177a = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        if (obj instanceof EndpointPair) {
            EndpointPair endpointPair = (EndpointPair) obj;
            if (this.f9178b.isDirected()) {
                if (endpointPair.isOrdered()) {
                    Object source = endpointPair.source();
                    Object target = endpointPair.target();
                    return (this.f9177a.equals(source) && this.f9178b.successors((BaseGraph) this.f9177a).contains(target)) || (this.f9177a.equals(target) && this.f9178b.predecessors((BaseGraph) this.f9177a).contains(source));
                }
                return false;
            } else if (endpointPair.isOrdered()) {
                return false;
            } else {
                Set adjacentNodes = this.f9178b.adjacentNodes(this.f9177a);
                Object nodeU = endpointPair.nodeU();
                Object nodeV = endpointPair.nodeV();
                return (this.f9177a.equals(nodeV) && adjacentNodes.contains(nodeU)) || (this.f9177a.equals(nodeU) && adjacentNodes.contains(nodeV));
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.f9178b.isDirected() ? (this.f9178b.inDegree(this.f9177a) + this.f9178b.outDegree(this.f9177a)) - (this.f9178b.successors((BaseGraph) this.f9177a).contains(this.f9177a) ? 1 : 0) : this.f9178b.adjacentNodes(this.f9177a).size();
    }
}
