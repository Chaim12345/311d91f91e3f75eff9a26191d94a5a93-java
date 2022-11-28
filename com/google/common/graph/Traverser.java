package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes2.dex */
public abstract class Traverser<N> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class GraphTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> graph;

        /* loaded from: classes2.dex */
        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();
            private final Set<N> visited = new HashSet();

            /* JADX WARN: Multi-variable type inference failed */
            BreadthFirstIterator(Iterable iterable) {
                for (Object obj : iterable) {
                    if (this.visited.add(obj)) {
                        this.queue.add(obj);
                    }
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                N remove = this.queue.remove();
                for (N n2 : GraphTraverser.this.graph.successors(remove)) {
                    if (this.visited.add(n2)) {
                        this.queue.add(n2);
                    }
                }
                return remove;
            }
        }

        /* loaded from: classes2.dex */
        private final class DepthFirstIterator extends AbstractIterator<N> {
            private final Order order;
            private final Deque<GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors> stack;
            private final Set<N> visited;

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: classes2.dex */
            public final class NodeAndSuccessors {
                @NullableDecl

                /* renamed from: a  reason: collision with root package name */
                final Object f9202a;

                /* renamed from: b  reason: collision with root package name */
                final Iterator f9203b;

                NodeAndSuccessors(@NullableDecl DepthFirstIterator depthFirstIterator, Object obj, Iterable iterable) {
                    this.f9202a = obj;
                    this.f9203b = iterable.iterator();
                }
            }

            DepthFirstIterator(Iterable iterable, Order order) {
                ArrayDeque arrayDeque = new ArrayDeque();
                this.stack = arrayDeque;
                this.visited = new HashSet();
                arrayDeque.push(new NodeAndSuccessors(this, null, iterable));
                this.order = order;
            }

            NodeAndSuccessors b(Object obj) {
                return new NodeAndSuccessors(this, obj, GraphTraverser.this.graph.successors(obj));
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.AbstractIterator
            protected Object computeNext() {
                Object obj;
                while (!this.stack.isEmpty()) {
                    GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors first = this.stack.getFirst();
                    boolean add = this.visited.add(first.f9202a);
                    boolean z = true;
                    boolean z2 = !first.f9203b.hasNext();
                    if ((!add || this.order != Order.PREORDER) && (!z2 || this.order != Order.POSTORDER)) {
                        z = false;
                    }
                    if (z2) {
                        this.stack.pop();
                    } else {
                        Object next = first.f9203b.next();
                        if (!this.visited.contains(next)) {
                            this.stack.push(b(next));
                        }
                    }
                    if (z && (obj = first.f9202a) != null) {
                        return obj;
                    }
                }
                return a();
            }
        }

        GraphTraverser(SuccessorsFunction successorsFunction) {
            super();
            this.graph = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
        }

        private void checkThatNodeIsInGraph(N n2) {
            this.graph.successors(n2);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            for (N n2 : iterable) {
                checkThatNodeIsInGraph(n2);
            }
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.GraphTraverser.1
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(iterable);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(N n2) {
            Preconditions.checkNotNull(n2);
            return breadthFirst((Iterable) ImmutableSet.of(n2));
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            for (N n2 : iterable) {
                checkThatNodeIsInGraph(n2);
            }
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.GraphTraverser.3
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(iterable, Order.POSTORDER);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(N n2) {
            Preconditions.checkNotNull(n2);
            return depthFirstPostOrder((Iterable) ImmutableSet.of(n2));
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            for (N n2 : iterable) {
                checkThatNodeIsInGraph(n2);
            }
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.GraphTraverser.2
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(iterable, Order.PREORDER);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(N n2) {
            Preconditions.checkNotNull(n2);
            return depthFirstPreOrder((Iterable) ImmutableSet.of(n2));
        }
    }

    /* loaded from: classes2.dex */
    private enum Order {
        PREORDER,
        POSTORDER
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class TreeTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> tree;

        /* loaded from: classes2.dex */
        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();

            /* JADX WARN: Multi-variable type inference failed */
            BreadthFirstIterator(Iterable iterable) {
                for (Object obj : iterable) {
                    this.queue.add(obj);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                N remove = this.queue.remove();
                Iterables.addAll(this.queue, TreeTraverser.this.tree.successors(remove));
                return remove;
            }
        }

        /* loaded from: classes2.dex */
        private final class DepthFirstPostOrderIterator extends AbstractIterator<N> {
            private final ArrayDeque<TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren> stack;

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: classes2.dex */
            public final class NodeAndChildren {
                @NullableDecl

                /* renamed from: a  reason: collision with root package name */
                final Object f9212a;

                /* renamed from: b  reason: collision with root package name */
                final Iterator f9213b;

                NodeAndChildren(@NullableDecl DepthFirstPostOrderIterator depthFirstPostOrderIterator, Object obj, Iterable iterable) {
                    this.f9212a = obj;
                    this.f9213b = iterable.iterator();
                }
            }

            DepthFirstPostOrderIterator(Iterable iterable) {
                ArrayDeque<TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren> arrayDeque = new ArrayDeque<>();
                this.stack = arrayDeque;
                arrayDeque.addLast(new NodeAndChildren(this, null, iterable));
            }

            NodeAndChildren b(Object obj) {
                return new NodeAndChildren(this, obj, TreeTraverser.this.tree.successors(obj));
            }

            @Override // com.google.common.collect.AbstractIterator
            protected Object computeNext() {
                while (!this.stack.isEmpty()) {
                    TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren last = this.stack.getLast();
                    if (last.f9213b.hasNext()) {
                        this.stack.addLast(b(last.f9213b.next()));
                    } else {
                        this.stack.removeLast();
                        Object obj = last.f9212a;
                        if (obj != null) {
                            return obj;
                        }
                    }
                }
                return a();
            }
        }

        /* loaded from: classes2.dex */
        private final class DepthFirstPreOrderIterator extends UnmodifiableIterator<N> {
            private final Deque<Iterator<? extends N>> stack;

            DepthFirstPreOrderIterator(Iterable iterable) {
                ArrayDeque arrayDeque = new ArrayDeque();
                this.stack = arrayDeque;
                arrayDeque.addLast(iterable.iterator());
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.stack.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                Iterator<? extends N> last = this.stack.getLast();
                N n2 = (N) Preconditions.checkNotNull(last.next());
                if (!last.hasNext()) {
                    this.stack.removeLast();
                }
                Iterator<? extends N> it = TreeTraverser.this.tree.successors(n2).iterator();
                if (it.hasNext()) {
                    this.stack.addLast(it);
                }
                return n2;
            }
        }

        TreeTraverser(SuccessorsFunction successorsFunction) {
            super();
            this.tree = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
        }

        private void checkThatNodeIsInTree(N n2) {
            this.tree.successors(n2);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            for (N n2 : iterable) {
                checkThatNodeIsInTree(n2);
            }
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.TreeTraverser.1
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(iterable);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(N n2) {
            Preconditions.checkNotNull(n2);
            return breadthFirst((Iterable) ImmutableSet.of(n2));
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            for (N n2 : iterable) {
                checkThatNodeIsInTree(n2);
            }
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.TreeTraverser.3
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPostOrderIterator(iterable);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(N n2) {
            Preconditions.checkNotNull(n2);
            return depthFirstPostOrder((Iterable) ImmutableSet.of(n2));
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            for (N n2 : iterable) {
                checkThatNodeIsInTree(n2);
            }
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.TreeTraverser.2
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPreOrderIterator(iterable);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(N n2) {
            Preconditions.checkNotNull(n2);
            return depthFirstPreOrder((Iterable) ImmutableSet.of(n2));
        }
    }

    private Traverser() {
    }

    public static <N> Traverser<N> forGraph(SuccessorsFunction<N> successorsFunction) {
        Preconditions.checkNotNull(successorsFunction);
        return new GraphTraverser(successorsFunction);
    }

    public static <N> Traverser<N> forTree(SuccessorsFunction<N> successorsFunction) {
        Preconditions.checkNotNull(successorsFunction);
        if (successorsFunction instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph) successorsFunction).isDirected(), "Undirected graphs can never be trees.");
        }
        if (successorsFunction instanceof Network) {
            Preconditions.checkArgument(((Network) successorsFunction).isDirected(), "Undirected networks can never be trees.");
        }
        return new TreeTraverser(successorsFunction);
    }

    public abstract Iterable<N> breadthFirst(Iterable<? extends N> iterable);

    public abstract Iterable<N> breadthFirst(N n2);

    public abstract Iterable<N> depthFirstPostOrder(Iterable<? extends N> iterable);

    public abstract Iterable<N> depthFirstPostOrder(N n2);

    public abstract Iterable<N> depthFirstPreOrder(Iterable<? extends N> iterable);

    public abstract Iterable<N> depthFirstPreOrder(N n2);
}
