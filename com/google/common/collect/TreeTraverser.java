package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
@Beta
@GwtCompatible
@Deprecated
/* loaded from: classes2.dex */
public abstract class TreeTraverser<T> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class BreadthFirstIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        private final Queue<T> queue;

        BreadthFirstIterator(Object obj) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.queue = arrayDeque;
            arrayDeque.add(obj);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator, com.google.common.collect.PeekingIterator
        public T next() {
            T remove = this.queue.remove();
            Iterables.addAll(this.queue, TreeTraverser.this.children(remove));
            return remove;
        }

        @Override // com.google.common.collect.PeekingIterator
        public T peek() {
            return this.queue.element();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class PostOrderIterator extends AbstractIterator<T> {
        private final ArrayDeque<PostOrderNode<T>> stack;

        PostOrderIterator(Object obj) {
            ArrayDeque<PostOrderNode<T>> arrayDeque = new ArrayDeque<>();
            this.stack = arrayDeque;
            arrayDeque.addLast(expand(obj));
        }

        private PostOrderNode<T> expand(T t2) {
            return new PostOrderNode<>(t2, TreeTraverser.this.children(t2).iterator());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractIterator
        protected Object computeNext() {
            while (!this.stack.isEmpty()) {
                PostOrderNode<T> last = this.stack.getLast();
                if (!last.f9116b.hasNext()) {
                    this.stack.removeLast();
                    return last.f9115a;
                }
                this.stack.addLast(expand(last.f9116b.next()));
            }
            return a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class PostOrderNode<T> {

        /* renamed from: a  reason: collision with root package name */
        final Object f9115a;

        /* renamed from: b  reason: collision with root package name */
        final Iterator f9116b;

        PostOrderNode(Object obj, Iterator it) {
            this.f9115a = Preconditions.checkNotNull(obj);
            this.f9116b = (Iterator) Preconditions.checkNotNull(it);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class PreOrderIterator extends UnmodifiableIterator<T> {
        private final Deque<Iterator<T>> stack;

        PreOrderIterator(Object obj) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.stack = arrayDeque;
            arrayDeque.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(obj)));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            Iterator<T> last = this.stack.getLast();
            T t2 = (T) Preconditions.checkNotNull(last.next());
            if (!last.hasNext()) {
                this.stack.removeLast();
            }
            Iterator<T> it = TreeTraverser.this.children(t2).iterator();
            if (it.hasNext()) {
                this.stack.addLast(it);
            }
            return t2;
        }
    }

    @Deprecated
    public static <T> TreeTraverser<T> using(final Function<T, ? extends Iterable<T>> function) {
        Preconditions.checkNotNull(function);
        return new TreeTraverser<T>() { // from class: com.google.common.collect.TreeTraverser.1
            @Override // com.google.common.collect.TreeTraverser
            public Iterable<T> children(T t2) {
                return (Iterable) Function.this.apply(t2);
            }
        };
    }

    UnmodifiableIterator a(Object obj) {
        return new PostOrderIterator(obj);
    }

    UnmodifiableIterator b(Object obj) {
        return new PreOrderIterator(obj);
    }

    @Deprecated
    public final FluentIterable<T> breadthFirstTraversal(final T t2) {
        Preconditions.checkNotNull(t2);
        return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.4
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return new BreadthFirstIterator(t2);
            }
        };
    }

    public abstract Iterable<T> children(T t2);

    @Deprecated
    public final FluentIterable<T> postOrderTraversal(final T t2) {
        Preconditions.checkNotNull(t2);
        return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.3
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.a(t2);
            }
        };
    }

    @Deprecated
    public final FluentIterable<T> preOrderTraversal(final T t2) {
        Preconditions.checkNotNull(t2);
        return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.2
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.b(t2);
            }
        };
    }
}
