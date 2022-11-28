package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class EndpointPairIterator<N> extends AbstractIterator<EndpointPair<N>> {

    /* renamed from: a  reason: collision with root package name */
    protected Object f9167a;

    /* renamed from: b  reason: collision with root package name */
    protected Iterator f9168b;
    private final BaseGraph<N> graph;
    private final Iterator<N> nodeIterator;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Directed<N> extends EndpointPairIterator<N> {
        private Directed(BaseGraph<N> baseGraph) {
            super(baseGraph);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: d */
        public EndpointPair computeNext() {
            while (!this.f9168b.hasNext()) {
                if (!b()) {
                    return (EndpointPair) a();
                }
            }
            return EndpointPair.ordered(this.f9167a, this.f9168b.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Undirected<N> extends EndpointPairIterator<N> {
        private Set<N> visitedNodes;

        private Undirected(BaseGraph<N> baseGraph) {
            super(baseGraph);
            this.visitedNodes = Sets.newHashSetWithExpectedSize(baseGraph.nodes().size());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractIterator
        /* renamed from: d */
        public EndpointPair computeNext() {
            while (true) {
                if (this.f9168b.hasNext()) {
                    Object next = this.f9168b.next();
                    if (!this.visitedNodes.contains(next)) {
                        return EndpointPair.unordered(this.f9167a, next);
                    }
                } else {
                    this.visitedNodes.add(this.f9167a);
                    if (!b()) {
                        this.visitedNodes = null;
                        return (EndpointPair) a();
                    }
                }
            }
        }
    }

    private EndpointPairIterator(BaseGraph<N> baseGraph) {
        this.f9167a = null;
        this.f9168b = ImmutableSet.of().iterator();
        this.graph = baseGraph;
        this.nodeIterator = baseGraph.nodes().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EndpointPairIterator c(BaseGraph baseGraph) {
        return baseGraph.isDirected() ? new Directed(baseGraph) : new Undirected(baseGraph);
    }

    protected final boolean b() {
        Preconditions.checkState(!this.f9168b.hasNext());
        if (this.nodeIterator.hasNext()) {
            N next = this.nodeIterator.next();
            this.f9167a = next;
            this.f9168b = this.graph.successors((BaseGraph<N>) next).iterator();
            return true;
        }
        return false;
    }
}
