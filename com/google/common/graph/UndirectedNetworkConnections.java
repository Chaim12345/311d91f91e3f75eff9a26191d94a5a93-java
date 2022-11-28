package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
final class UndirectedNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
    protected UndirectedNetworkConnections(Map map) {
        super(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UndirectedNetworkConnections a() {
        return new UndirectedNetworkConnections(HashBiMap.create(2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UndirectedNetworkConnections b(Map map) {
        return new UndirectedNetworkConnections(ImmutableBiMap.copyOf(map));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(((BiMap) this.f9145a).values());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(N n2) {
        return new EdgesConnecting(((BiMap) this.f9145a).inverse(), n2);
    }
}
