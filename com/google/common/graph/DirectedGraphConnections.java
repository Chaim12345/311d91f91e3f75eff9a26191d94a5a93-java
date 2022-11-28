package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.ElementOrder;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    @NullableDecl
    private final List<NodeConnection<N>> orderedNodeConnections;
    private int predecessorCount;
    private int successorCount;

    /* renamed from: com.google.common.graph.DirectedGraphConnections$8  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass8 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9162a;

        static {
            int[] iArr = new int[ElementOrder.Type.values().length];
            f9162a = iArr;
            try {
                iArr[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9162a[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class NodeConnection<N> {

        /* renamed from: a  reason: collision with root package name */
        final Object f9163a;

        /* loaded from: classes2.dex */
        static final class Pred<N> extends NodeConnection<N> {
            Pred(Object obj) {
                super(obj);
            }

            public boolean equals(Object obj) {
                if (obj instanceof Pred) {
                    return this.f9163a.equals(((Pred) obj).f9163a);
                }
                return false;
            }

            public int hashCode() {
                return Pred.class.hashCode() + this.f9163a.hashCode();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public static final class Succ<N> extends NodeConnection<N> {
            Succ(Object obj) {
                super(obj);
            }

            public boolean equals(Object obj) {
                if (obj instanceof Succ) {
                    return this.f9163a.equals(((Succ) obj).f9163a);
                }
                return false;
            }

            public int hashCode() {
                return Succ.class.hashCode() + this.f9163a.hashCode();
            }
        }

        NodeConnection(Object obj) {
            this.f9163a = Preconditions.checkNotNull(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    private DirectedGraphConnections(Map<N, Object> map, @NullableDecl List<NodeConnection<N>> list, int i2, int i3) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
        this.orderedNodeConnections = list;
        this.predecessorCount = Graphs.a(i2);
        this.successorCount = Graphs.a(i3);
        Preconditions.checkState(i2 <= map.size() && i3 <= map.size());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DirectedGraphConnections g(ElementOrder elementOrder) {
        ArrayList arrayList;
        int i2 = AnonymousClass8.f9162a[elementOrder.type().ordinal()];
        if (i2 == 1) {
            arrayList = null;
        } else if (i2 != 2) {
            throw new AssertionError(elementOrder.type());
        } else {
            arrayList = new ArrayList();
        }
        return new DirectedGraphConnections(new HashMap(4, 1.0f), arrayList, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static DirectedGraphConnections h(Object obj, Iterable iterable, Function function) {
        Preconditions.checkNotNull(obj);
        Preconditions.checkNotNull(function);
        HashMap hashMap = new HashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        Iterator it = iterable.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            EndpointPair endpointPair = (EndpointPair) it.next();
            if (endpointPair.nodeU().equals(obj) && endpointPair.nodeV().equals(obj)) {
                hashMap.put(obj, new PredAndSucc(function.apply(obj)));
                builder.add((ImmutableList.Builder) new NodeConnection.Pred(obj));
                builder.add((ImmutableList.Builder) new NodeConnection.Succ(obj));
                i2++;
            } else if (endpointPair.nodeV().equals(obj)) {
                Object nodeU = endpointPair.nodeU();
                Object put = hashMap.put(nodeU, PRED);
                if (put != null) {
                    hashMap.put(nodeU, new PredAndSucc(put));
                }
                builder.add((ImmutableList.Builder) new NodeConnection.Pred(nodeU));
                i2++;
            } else {
                Preconditions.checkArgument(endpointPair.nodeU().equals(obj));
                Object nodeV = endpointPair.nodeV();
                Object apply = function.apply(nodeV);
                Object put2 = hashMap.put(nodeV, apply);
                if (put2 != null) {
                    Preconditions.checkArgument(put2 == PRED);
                    hashMap.put(nodeV, new PredAndSucc(apply));
                }
                builder.add((ImmutableList.Builder) new NodeConnection.Succ(nodeV));
            }
            i3++;
        }
        return new DirectedGraphConnections(hashMap, builder.build(), i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPredecessor(@NullableDecl Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSuccessor(@NullableDecl Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addPredecessor(N n2, V v) {
        Map<N, Object> map = this.adjacentNodeValues;
        Object obj = PRED;
        Object put = map.put(n2, obj);
        boolean z = false;
        if (put != null) {
            if (put instanceof PredAndSucc) {
                this.adjacentNodeValues.put(n2, put);
            } else if (put != obj) {
                this.adjacentNodeValues.put(n2, new PredAndSucc(put));
            }
            if (z) {
                return;
            }
            int i2 = this.predecessorCount + 1;
            this.predecessorCount = i2;
            Graphs.c(i2);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.add(new NodeConnection.Pred(n2));
                return;
            }
            return;
        }
        z = true;
        if (z) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public V addSuccessor(N n2, V v) {
        V v2 = (V) this.adjacentNodeValues.put(n2, v);
        if (v2 != null) {
            if (v2 instanceof PredAndSucc) {
                this.adjacentNodeValues.put(n2, new PredAndSucc(v));
                v2 = (V) ((PredAndSucc) v2).successorValue;
            } else if (v2 == PRED) {
                this.adjacentNodeValues.put(n2, new PredAndSucc(v));
            }
            if (v2 == null) {
                int i2 = this.successorCount + 1;
                this.successorCount = i2;
                Graphs.c(i2);
                List<NodeConnection<N>> list = this.orderedNodeConnections;
                if (list != null) {
                    list.add(new NodeConnection.Succ(n2));
                }
            }
            return v2;
        }
        v2 = null;
        if (v2 == null) {
        }
        return v2;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return this.orderedNodeConnections == null ? Collections.unmodifiableSet(this.adjacentNodeValues.keySet()) : new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                final Iterator it = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                final HashSet hashSet = new HashSet();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                    @Override // com.google.common.collect.AbstractIterator
                    protected Object computeNext() {
                        while (it.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it.next();
                            if (hashSet.add(nodeConnection.f9163a)) {
                                return nodeConnection.f9163a;
                            }
                        }
                        return a();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N n2) {
        Preconditions.checkNotNull(n2);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        final Iterator concat = list == null ? Iterators.concat(Iterators.transform(predecessors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.4
            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(N n3) {
                return EndpointPair.ordered(n3, n2);
            }

            @Override // com.google.common.base.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((AnonymousClass4) obj);
            }
        }), Iterators.transform(successors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.5
            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(N n3) {
                return EndpointPair.ordered(n2, n3);
            }

            @Override // com.google.common.base.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((AnonymousClass5) obj);
            }
        })) : Iterators.transform(list.iterator(), new Function<NodeConnection<N>, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.6
            public EndpointPair<N> apply(NodeConnection<N> nodeConnection) {
                return nodeConnection instanceof NodeConnection.Succ ? EndpointPair.ordered(n2, nodeConnection.f9163a) : EndpointPair.ordered(nodeConnection.f9163a, n2);
            }

            @Override // com.google.common.base.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((NodeConnection) ((NodeConnection) obj));
            }
        });
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return new AbstractIterator<EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.7
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: b */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public EndpointPair computeNext() {
                while (concat.hasNext()) {
                    EndpointPair endpointPair = (EndpointPair) concat.next();
                    if (!endpointPair.nodeU().equals(endpointPair.nodeV()) || !atomicBoolean.getAndSet(true)) {
                        return endpointPair;
                    }
                    while (concat.hasNext()) {
                    }
                }
                return (EndpointPair) a();
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                        @Override // com.google.common.collect.AbstractIterator
                        protected Object computeNext() {
                            while (it.hasNext()) {
                                Map.Entry entry = (Map.Entry) it.next();
                                if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                    return entry.getKey();
                                }
                            }
                            return a();
                        }
                    };
                }
                final Iterator it2 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.2
                    @Override // com.google.common.collect.AbstractIterator
                    protected Object computeNext() {
                        while (it2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it2.next();
                            if (nodeConnection instanceof NodeConnection.Pred) {
                                return nodeConnection.f9163a;
                            }
                        }
                        return a();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void removePredecessor(N n2) {
        boolean z;
        Preconditions.checkNotNull(n2);
        Object obj = this.adjacentNodeValues.get(n2);
        if (obj == PRED) {
            this.adjacentNodeValues.remove(n2);
        } else if (!(obj instanceof PredAndSucc)) {
            z = false;
            if (z) {
                return;
            }
            int i2 = this.predecessorCount - 1;
            this.predecessorCount = i2;
            Graphs.a(i2);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Pred(n2));
                return;
            }
            return;
        } else {
            this.adjacentNodeValues.put(n2, ((PredAndSucc) obj).successorValue);
        }
        z = true;
        if (z) {
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(Object obj) {
        Object obj2;
        Preconditions.checkNotNull(obj);
        V v = (V) this.adjacentNodeValues.get(obj);
        if (v == null || v == (obj2 = PRED)) {
            v = null;
        } else if (v instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            v = (V) ((PredAndSucc) v).successorValue;
        } else {
            this.adjacentNodeValues.remove(obj);
        }
        if (v != null) {
            int i2 = this.successorCount - 1;
            this.successorCount = i2;
            Graphs.a(i2);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Succ(obj));
            }
        }
        return v;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.3
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.1
                        @Override // com.google.common.collect.AbstractIterator
                        protected Object computeNext() {
                            while (it.hasNext()) {
                                Map.Entry entry = (Map.Entry) it.next();
                                if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                    return entry.getKey();
                                }
                            }
                            return a();
                        }
                    };
                }
                final Iterator it2 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.2
                    @Override // com.google.common.collect.AbstractIterator
                    protected Object computeNext() {
                        while (it2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it2.next();
                            if (nodeConnection instanceof NodeConnection.Succ) {
                                return nodeConnection.f9163a;
                            }
                        }
                        return a();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public V value(N n2) {
        Preconditions.checkNotNull(n2);
        V v = (V) this.adjacentNodeValues.get(n2);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }
}
