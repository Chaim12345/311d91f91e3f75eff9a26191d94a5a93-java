package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes2.dex */
public final class Graphs {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TransposedGraph<N> extends ForwardingGraph<N> {
        private final Graph<N> graph;

        TransposedGraph(Graph graph) {
            this.graph = graph;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.graph.ForwardingGraph
        /* renamed from: f */
        public Graph d() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return d().hasEdgeConnecting(Graphs.e(endpointPair));
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N n2, N n3) {
            return d().hasEdgeConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N n2) {
            return d().outDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public Set<EndpointPair<N>> incidentEdges(N n2) {
            return new IncidentEdgeSet<N>(this, n2) { // from class: com.google.common.graph.Graphs.TransposedGraph.1
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<EndpointPair<N>> iterator() {
                    return Iterators.transform(TransposedGraph.this.d().incidentEdges(this.f9177a).iterator(), new Function<EndpointPair<N>, EndpointPair<N>>() { // from class: com.google.common.graph.Graphs.TransposedGraph.1.1
                        public EndpointPair<N> apply(EndpointPair<N> endpointPair) {
                            return EndpointPair.a(TransposedGraph.this.d(), endpointPair.nodeV(), endpointPair.nodeU());
                        }

                        @Override // com.google.common.base.Function
                        public /* bridge */ /* synthetic */ Object apply(Object obj) {
                            return apply((EndpointPair) ((EndpointPair) obj));
                        }
                    });
                }
            };
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N n2) {
            return d().inDegree(n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedGraph<N>) obj);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
        public Set<N> predecessors(N n2) {
            return d().successors((Graph) n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedGraph<N>) obj);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public Set<N> successors(N n2) {
            return d().predecessors((Graph) n2);
        }
    }

    /* loaded from: classes2.dex */
    private static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        private final Network<N, E> network;

        TransposedNetwork(Network network) {
            this.network = network;
        }

        @Override // com.google.common.graph.ForwardingNetwork
        protected Network c() {
            return this.network;
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
            return (E) c().edgeConnectingOrNull(Graphs.e(endpointPair));
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(N n2, N n3) {
            return (E) c().edgeConnectingOrNull(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
            return c().edgesConnecting(Graphs.e(endpointPair));
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(N n2, N n3) {
            return c().edgesConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return c().hasEdgeConnecting(Graphs.e(endpointPair));
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(N n2, N n3) {
            return c().hasEdgeConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int inDegree(N n2) {
            return c().outDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> inEdges(N n2) {
            return c().outEdges(n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public EndpointPair<N> incidentNodes(E e2) {
            EndpointPair<N> incidentNodes = c().incidentNodes(e2);
            return EndpointPair.b(this.network, incidentNodes.nodeV(), incidentNodes.nodeU());
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int outDegree(N n2) {
            return c().inDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> outEdges(N n2) {
            return c().inEdges(n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedNetwork<N, E>) obj);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
        public Set<N> predecessors(N n2) {
            return c().successors((Network) n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedNetwork<N, E>) obj);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public Set<N> successors(N n2) {
            return c().predecessors((Network) n2);
        }
    }

    /* loaded from: classes2.dex */
    private static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        private final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph valueGraph) {
            this.graph = valueGraph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph
        protected ValueGraph d() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ValueGraph
        @NullableDecl
        public V edgeValueOrDefault(EndpointPair<N> endpointPair, @NullableDecl V v) {
            return (V) d().edgeValueOrDefault(Graphs.e(endpointPair), v);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ValueGraph
        @NullableDecl
        public V edgeValueOrDefault(N n2, N n3, @NullableDecl V v) {
            return (V) d().edgeValueOrDefault(n3, n2, v);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return d().hasEdgeConnecting(Graphs.e(endpointPair));
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N n2, N n3) {
            return d().hasEdgeConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N n2) {
            return d().outDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N n2) {
            return d().inDegree(n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedValueGraph<N, V>) obj);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
        public Set<N> predecessors(N n2) {
            return d().successors((ValueGraph) n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedValueGraph<N, V>) obj);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public Set<N> successors(N n2) {
            return d().predecessors((ValueGraph) n2);
        }
    }

    private Graphs() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public static int a(int i2) {
        Preconditions.checkArgument(i2 >= 0, "Not true that %s is non-negative.", i2);
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public static long b(long j2) {
        Preconditions.checkArgument(j2 >= 0, "Not true that %s is non-negative.", j2);
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public static int c(int i2) {
        Preconditions.checkArgument(i2 > 0, "Not true that %s is positive.", i2);
        return i2;
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object obj, @NullableDecl Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N> mutableGraph = (MutableGraph<N>) GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N n2 : graph.nodes()) {
            mutableGraph.addNode(n2);
        }
        for (EndpointPair<N> endpointPair : graph.edges()) {
            mutableGraph.putEdge(endpointPair.nodeU(), endpointPair.nodeV());
        }
        return mutableGraph;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N, E> mutableNetwork = (MutableNetwork<N, E>) NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N n2 : network.nodes()) {
            mutableNetwork.addNode(n2);
        }
        for (E e2 : network.edges()) {
            EndpointPair<N> incidentNodes = network.incidentNodes(e2);
            mutableNetwork.addEdge(incidentNodes.nodeU(), incidentNodes.nodeV(), e2);
        }
        return mutableNetwork;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph<N, V> mutableValueGraph = (MutableValueGraph<N, V>) ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        for (N n2 : valueGraph.nodes()) {
            mutableValueGraph.addNode(n2);
        }
        for (EndpointPair<N> endpointPair : valueGraph.edges()) {
            mutableValueGraph.putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), valueGraph.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null));
        }
        return mutableValueGraph;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public static long d(long j2) {
        Preconditions.checkArgument(j2 > 0, "Not true that %s is positive.", j2);
        return j2;
    }

    static EndpointPair e(EndpointPair endpointPair) {
        return endpointPair.isOrdered() ? EndpointPair.ordered(endpointPair.target(), endpointPair.source()) : endpointPair;
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (graph.isDirected() || size < graph.nodes().size()) {
            HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
            for (N n2 : graph.nodes()) {
                if (subgraphHasCycle(graph, newHashMapWithExpectedSize, n2, null)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        MutableGraph<N> mutableGraph = (MutableGraph<N>) (iterable instanceof Collection ? GraphBuilder.from(graph).expectedNodeCount(((Collection) iterable).size()) : GraphBuilder.from(graph)).build();
        for (N n2 : iterable) {
            mutableGraph.addNode(n2);
        }
        for (Object obj : mutableGraph.nodes()) {
            for (Object obj2 : graph.successors((Graph<N>) obj)) {
                if (mutableGraph.nodes().contains(obj2)) {
                    mutableGraph.putEdge(obj, obj2);
                }
            }
        }
        return mutableGraph;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        MutableNetwork<N, E> mutableNetwork = (MutableNetwork<N, E>) (iterable instanceof Collection ? NetworkBuilder.from(network).expectedNodeCount(((Collection) iterable).size()) : NetworkBuilder.from(network)).build();
        for (N n2 : iterable) {
            mutableNetwork.addNode(n2);
        }
        for (N n3 : mutableNetwork.nodes()) {
            for (E e2 : network.outEdges(n3)) {
                N adjacentNode = network.incidentNodes(e2).adjacentNode(n3);
                if (mutableNetwork.nodes().contains(adjacentNode)) {
                    mutableNetwork.addEdge(n3, adjacentNode, e2);
                }
            }
        }
        return mutableNetwork;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        MutableValueGraph<N, V> mutableValueGraph = (MutableValueGraph<N, V>) (iterable instanceof Collection ? ValueGraphBuilder.from(valueGraph).expectedNodeCount(((Collection) iterable).size()) : ValueGraphBuilder.from(valueGraph)).build();
        for (N n2 : iterable) {
            mutableValueGraph.addNode(n2);
        }
        for (Object obj : mutableValueGraph.nodes()) {
            for (Object obj2 : valueGraph.successors((ValueGraph<N, V>) obj)) {
                if (mutableValueGraph.nodes().contains(obj2)) {
                    mutableValueGraph.putEdgeValue(obj, obj2, valueGraph.edgeValueOrDefault(obj, obj2, null));
                }
            }
        }
        return mutableValueGraph;
    }

    public static <N> Set<N> reachableNodes(Graph<N> graph, N n2) {
        Preconditions.checkArgument(graph.nodes().contains(n2), "Node %s is not an element of this graph.", n2);
        return ImmutableSet.copyOf(Traverser.forGraph(graph).breadthFirst((Traverser) n2));
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> map, N n2, @NullableDecl N n3) {
        NodeVisitState nodeVisitState = map.get(n2);
        if (nodeVisitState == NodeVisitState.COMPLETE) {
            return false;
        }
        NodeVisitState nodeVisitState2 = NodeVisitState.PENDING;
        if (nodeVisitState == nodeVisitState2) {
            return true;
        }
        map.put(n2, nodeVisitState2);
        for (N n4 : graph.successors((Graph<N>) n2)) {
            if (canTraverseWithoutReusingEdge(graph, n4, n3) && subgraphHasCycle(graph, map, n4, n2)) {
                return true;
            }
        }
        map.put(n2, NodeVisitState.COMPLETE);
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.common.graph.MutableGraph, com.google.common.graph.Graph<N>] */
    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        ?? build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N n2 : graph.nodes()) {
                for (Object obj : reachableNodes(graph, n2)) {
                    build.putEdge(n2, obj);
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N n3 : graph.nodes()) {
                if (!hashSet.contains(n3)) {
                    Set reachableNodes = reachableNodes(graph, n3);
                    hashSet.addAll(reachableNodes);
                    int i2 = 1;
                    for (Object obj2 : reachableNodes) {
                        int i3 = i2 + 1;
                        for (Object obj3 : Iterables.limit(reachableNodes, i2)) {
                            build.putEdge(obj2, obj3);
                        }
                        i2 = i3;
                    }
                }
            }
        }
        return build;
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        return !graph.isDirected() ? graph : graph instanceof TransposedGraph ? ((TransposedGraph) graph).graph : new TransposedGraph(graph);
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        return !network.isDirected() ? network : network instanceof TransposedNetwork ? ((TransposedNetwork) network).network : new TransposedNetwork(network);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        return !valueGraph.isDirected() ? valueGraph : valueGraph instanceof TransposedValueGraph ? ((TransposedValueGraph) valueGraph).graph : new TransposedValueGraph(valueGraph);
    }
}
