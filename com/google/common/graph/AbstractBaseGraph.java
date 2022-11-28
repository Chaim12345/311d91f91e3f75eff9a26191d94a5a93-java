package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AbstractBaseGraph<N> implements BaseGraph<N> {
    protected long a() {
        Iterator<N> it;
        long j2 = 0;
        while (nodes().iterator().hasNext()) {
            j2 += degree(it.next());
        }
        Preconditions.checkState((1 & j2) == 0);
        return j2 >>> 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean b(EndpointPair endpointPair) {
        return endpointPair.isOrdered() || !isDirected();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(EndpointPair endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        Preconditions.checkArgument(b(endpointPair), "Mismatch: unordered endpoints cannot be used with directed graphs");
    }

    @Override // com.google.common.graph.BaseGraph
    public int degree(N n2) {
        int i2;
        int size;
        if (isDirected()) {
            size = predecessors((AbstractBaseGraph<N>) n2).size();
            i2 = successors((AbstractBaseGraph<N>) n2).size();
        } else {
            Set<N> adjacentNodes = adjacentNodes(n2);
            i2 = (allowsSelfLoops() && adjacentNodes.contains(n2)) ? 1 : 0;
            size = adjacentNodes.size();
        }
        return IntMath.saturatedAdd(size, i2);
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> edges() {
        return new AbstractSet<EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                if (obj instanceof EndpointPair) {
                    EndpointPair endpointPair = (EndpointPair) obj;
                    return AbstractBaseGraph.this.b(endpointPair) && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) && AbstractBaseGraph.this.successors((AbstractBaseGraph) endpointPair.nodeU()).contains(endpointPair.nodeV());
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return EndpointPairIterator.c(AbstractBaseGraph.this);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return Ints.saturatedCast(AbstractBaseGraph.this.a());
            }
        };
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        if (b(endpointPair)) {
            N nodeU = endpointPair.nodeU();
            return nodes().contains(nodeU) && successors((AbstractBaseGraph<N>) nodeU).contains(endpointPair.nodeV());
        }
        return false;
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n2, N n3) {
        Preconditions.checkNotNull(n2);
        Preconditions.checkNotNull(n3);
        return nodes().contains(n2) && successors((AbstractBaseGraph<N>) n2).contains(n3);
    }

    @Override // com.google.common.graph.BaseGraph
    public int inDegree(N n2) {
        return isDirected() ? predecessors((AbstractBaseGraph<N>) n2).size() : degree(n2);
    }

    @Override // com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.unordered();
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n2) {
        Preconditions.checkNotNull(n2);
        Preconditions.checkArgument(nodes().contains(n2), "Node %s is not an element of this graph.", n2);
        return new IncidentEdgeSet<N>(this, this, n2) { // from class: com.google.common.graph.AbstractBaseGraph.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return Iterators.unmodifiableIterator(this.f9178b.isDirected() ? Iterators.concat(Iterators.transform(this.f9178b.predecessors((BaseGraph) this.f9177a).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.1
                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N n3) {
                        return EndpointPair.ordered(n3, AnonymousClass2.this.f9177a);
                    }

                    @Override // com.google.common.base.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply((AnonymousClass1) obj);
                    }
                }), Iterators.transform(Sets.difference(this.f9178b.successors((BaseGraph) this.f9177a), ImmutableSet.of(this.f9177a)).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.2
                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N n3) {
                        return EndpointPair.ordered(AnonymousClass2.this.f9177a, n3);
                    }

                    @Override // com.google.common.base.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply((C00382) obj);
                    }
                })) : Iterators.transform(this.f9178b.adjacentNodes(this.f9177a).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.3
                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N n3) {
                        return EndpointPair.unordered(AnonymousClass2.this.f9177a, n3);
                    }

                    @Override // com.google.common.base.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply((AnonymousClass3) obj);
                    }
                }));
            }
        };
    }

    @Override // com.google.common.graph.BaseGraph
    public int outDegree(N n2) {
        return isDirected() ? successors((AbstractBaseGraph<N>) n2).size() : degree(n2);
    }
}
