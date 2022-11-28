package com.airbnb.lottie.parser.moshi;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() { // from class: com.airbnb.lottie.parser.moshi.LinkedHashTreeMap.1
        @Override // java.util.Comparator
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    Comparator f4458a;

    /* renamed from: b  reason: collision with root package name */
    Node[] f4459b;

    /* renamed from: c  reason: collision with root package name */
    final Node f4460c;

    /* renamed from: d  reason: collision with root package name */
    int f4461d;

    /* renamed from: e  reason: collision with root package name */
    int f4462e;
    private LinkedHashTreeMap<K, V>.EntrySet entrySet;

    /* renamed from: f  reason: collision with root package name */
    int f4463f;
    private LinkedHashTreeMap<K, V>.KeySet keySet;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class AvlBuilder<K, V> {
        private int leavesSkipped;
        private int leavesToSkip;
        private int size;
        private Node<K, V> stack;

        AvlBuilder() {
        }

        void a(Node node) {
            node.f4472c = null;
            node.f4470a = null;
            node.f4471b = null;
            node.f4478i = 1;
            int i2 = this.leavesToSkip;
            if (i2 > 0) {
                int i3 = this.size;
                if ((i3 & 1) == 0) {
                    this.size = i3 + 1;
                    this.leavesToSkip = i2 - 1;
                    this.leavesSkipped++;
                }
            }
            node.f4470a = this.stack;
            this.stack = node;
            int i4 = this.size + 1;
            this.size = i4;
            int i5 = this.leavesToSkip;
            if (i5 > 0 && (i4 & 1) == 0) {
                this.size = i4 + 1;
                this.leavesToSkip = i5 - 1;
                this.leavesSkipped++;
            }
            int i6 = 4;
            while (true) {
                int i7 = i6 - 1;
                if ((this.size & i7) != i7) {
                    return;
                }
                int i8 = this.leavesSkipped;
                if (i8 == 0) {
                    Node<K, V> node2 = this.stack;
                    Node<K, V> node3 = node2.f4470a;
                    Node node4 = node3.f4470a;
                    node3.f4470a = node4.f4470a;
                    this.stack = node3;
                    node3.f4471b = node4;
                    node3.f4472c = node2;
                    node3.f4478i = node2.f4478i + 1;
                    node4.f4470a = node3;
                    node2.f4470a = node3;
                } else {
                    if (i8 == 1) {
                        Node<K, V> node5 = this.stack;
                        Node<K, V> node6 = node5.f4470a;
                        this.stack = node6;
                        node6.f4472c = node5;
                        node6.f4478i = node5.f4478i + 1;
                        node5.f4470a = node6;
                    } else if (i8 != 2) {
                    }
                    this.leavesSkipped = 0;
                }
                i6 *= 2;
            }
        }

        void b(int i2) {
            this.leavesToSkip = ((Integer.highestOneBit(i2) * 2) - 1) - i2;
            this.size = 0;
            this.leavesSkipped = 0;
            this.stack = null;
        }

        Node c() {
            Node<K, V> node = this.stack;
            if (node.f4470a == null) {
                return node;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AvlIterator<K, V> {
        private Node<K, V> stackTop;

        AvlIterator() {
        }

        void a(Node node) {
            Node node2 = null;
            while (node != null) {
                node.f4470a = node2;
                node2 = node;
                node = node.f4471b;
            }
            this.stackTop = node2;
        }

        public Node<K, V> next() {
            Node<K, V> node = this.stackTop;
            if (node == null) {
                return null;
            }
            Node<K, V> node2 = node.f4470a;
            node.f4470a = null;
            Node<K, V> node3 = node.f4472c;
            while (true) {
                Node<K, V> node4 = node2;
                node2 = node3;
                if (node2 == null) {
                    this.stackTop = node4;
                    return node;
                }
                node2.f4470a = node4;
                node3 = node2.f4471b;
            }
        }
    }

    /* loaded from: classes.dex */
    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.c((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>(this) { // from class: com.airbnb.lottie.parser.moshi.LinkedHashTreeMap.EntrySet.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    return a();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Node c2;
            if ((obj instanceof Map.Entry) && (c2 = LinkedHashTreeMap.this.c((Map.Entry) obj)) != null) {
                LinkedHashTreeMap.this.e(c2, true);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.f4461d;
        }
    }

    /* loaded from: classes.dex */
    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<K>(this) { // from class: com.airbnb.lottie.parser.moshi.LinkedHashTreeMap.KeySet.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return (K) a().f4475f;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.f(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.f4461d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class LinkedTreeMapIterator<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        Node f4466a;

        /* renamed from: b  reason: collision with root package name */
        Node f4467b = null;

        /* renamed from: c  reason: collision with root package name */
        int f4468c;

        LinkedTreeMapIterator() {
            this.f4466a = LinkedHashTreeMap.this.f4460c.f4473d;
            this.f4468c = LinkedHashTreeMap.this.f4462e;
        }

        final Node a() {
            Node node = this.f4466a;
            LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
            if (node != linkedHashTreeMap.f4460c) {
                if (linkedHashTreeMap.f4462e == this.f4468c) {
                    this.f4466a = node.f4473d;
                    this.f4467b = node;
                    return node;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f4466a != LinkedHashTreeMap.this.f4460c;
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node node = this.f4467b;
            if (node == null) {
                throw new IllegalStateException();
            }
            LinkedHashTreeMap.this.e(node, true);
            this.f4467b = null;
            this.f4468c = LinkedHashTreeMap.this.f4462e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Node<K, V> implements Map.Entry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        Node f4470a;

        /* renamed from: b  reason: collision with root package name */
        Node f4471b;

        /* renamed from: c  reason: collision with root package name */
        Node f4472c;

        /* renamed from: d  reason: collision with root package name */
        Node f4473d;

        /* renamed from: e  reason: collision with root package name */
        Node f4474e;

        /* renamed from: f  reason: collision with root package name */
        final Object f4475f;

        /* renamed from: g  reason: collision with root package name */
        final int f4476g;

        /* renamed from: h  reason: collision with root package name */
        Object f4477h;

        /* renamed from: i  reason: collision with root package name */
        int f4478i = 1;

        Node(Node node, Object obj, int i2, Node node2, Node node3) {
            this.f4470a = node;
            this.f4475f = obj;
            this.f4476g = i2;
            this.f4473d = node2;
            this.f4474e = node3;
            node3.f4473d = this;
            node2.f4474e = this;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object obj2 = this.f4475f;
                if (obj2 == null) {
                    if (entry.getKey() != null) {
                        return false;
                    }
                } else if (!obj2.equals(entry.getKey())) {
                    return false;
                }
                Object obj3 = this.f4477h;
                Object value = entry.getValue();
                if (obj3 == null) {
                    if (value != null) {
                        return false;
                    }
                } else if (!obj3.equals(value)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public Node<K, V> first() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.f4471b; node2 != null; node2 = node2.f4471b) {
                node = node2;
            }
            return node;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) this.f4475f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return (V) this.f4477h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.f4475f;
            int hashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.f4477h;
            return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
        }

        public Node<K, V> last() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.f4472c; node2 != null; node2 = node2.f4472c) {
                node = node2;
            }
            return node;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) this.f4477h;
            this.f4477h = v;
            return v2;
        }

        public String toString() {
            return this.f4475f + "=" + this.f4477h;
        }
    }

    static Node[] a(Node[] nodeArr) {
        int length = nodeArr.length;
        Node[] nodeArr2 = new Node[length * 2];
        AvlIterator avlIterator = new AvlIterator();
        AvlBuilder avlBuilder = new AvlBuilder();
        AvlBuilder avlBuilder2 = new AvlBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            Node node = nodeArr[i2];
            if (node != null) {
                avlIterator.a(node);
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    Node<K, V> next = avlIterator.next();
                    if (next == null) {
                        break;
                    } else if ((next.f4476g & length) == 0) {
                        i3++;
                    } else {
                        i4++;
                    }
                }
                avlBuilder.b(i3);
                avlBuilder2.b(i4);
                avlIterator.a(node);
                while (true) {
                    Node<K, V> next2 = avlIterator.next();
                    if (next2 == null) {
                        break;
                    } else if ((next2.f4476g & length) == 0) {
                        avlBuilder.a(next2);
                    } else {
                        avlBuilder2.a(next2);
                    }
                }
                nodeArr2[i2] = i3 > 0 ? avlBuilder.c() : null;
                nodeArr2[i2 + length] = i4 > 0 ? avlBuilder2.c() : null;
            }
        }
        return nodeArr2;
    }

    private void doubleCapacity() {
        Node[] a2 = a(this.f4459b);
        this.f4459b = a2;
        this.f4463f = (a2.length / 2) + (a2.length / 4);
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void rebalance(Node<K, V> node, boolean z) {
        while (node != null) {
            Node<K, V> node2 = node.f4471b;
            Node<K, V> node3 = node.f4472c;
            int i2 = node2 != null ? node2.f4478i : 0;
            int i3 = node3 != null ? node3.f4478i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                Node node4 = node3.f4471b;
                Node node5 = node3.f4472c;
                int i5 = (node4 != null ? node4.f4478i : 0) - (node5 != null ? node5.f4478i : 0);
                if (i5 != -1 && (i5 != 0 || z)) {
                    rotateRight(node3);
                }
                rotateLeft(node);
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                Node node6 = node2.f4471b;
                Node node7 = node2.f4472c;
                int i6 = (node6 != null ? node6.f4478i : 0) - (node7 != null ? node7.f4478i : 0);
                if (i6 != 1 && (i6 != 0 || z)) {
                    rotateLeft(node2);
                }
                rotateRight(node);
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                node.f4478i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                node.f4478i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            node = node.f4470a;
        }
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> node2) {
        Node node3 = node.f4470a;
        node.f4470a = null;
        if (node2 != null) {
            node2.f4470a = node3;
        }
        if (node3 == null) {
            int i2 = node.f4476g;
            Node[] nodeArr = this.f4459b;
            nodeArr[i2 & (nodeArr.length - 1)] = node2;
        } else if (node3.f4471b == node) {
            node3.f4471b = node2;
        } else {
            node3.f4472c = node2;
        }
    }

    private void rotateLeft(Node<K, V> node) {
        Node node2 = node.f4471b;
        Node<K, V> node3 = node.f4472c;
        Node node4 = node3.f4471b;
        Node node5 = node3.f4472c;
        node.f4472c = node4;
        if (node4 != null) {
            node4.f4470a = node;
        }
        replaceInParent(node, node3);
        node3.f4471b = node;
        node.f4470a = node3;
        int max = Math.max(node2 != null ? node2.f4478i : 0, node4 != null ? node4.f4478i : 0) + 1;
        node.f4478i = max;
        node3.f4478i = Math.max(max, node5 != null ? node5.f4478i : 0) + 1;
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> node2 = node.f4471b;
        Node node3 = node.f4472c;
        Node node4 = node2.f4471b;
        Node node5 = node2.f4472c;
        node.f4471b = node5;
        if (node5 != null) {
            node5.f4470a = node;
        }
        replaceInParent(node, node2);
        node2.f4472c = node;
        node.f4470a = node2;
        int max = Math.max(node3 != null ? node3.f4478i : 0, node5 != null ? node5.f4478i : 0) + 1;
        node.f4478i = max;
        node2.f4478i = Math.max(max, node4 != null ? node4.f4478i : 0) + 1;
    }

    private static int secondaryHash(int i2) {
        int i3 = i2 ^ ((i2 >>> 20) ^ (i2 >>> 12));
        return (i3 >>> 4) ^ ((i3 >>> 7) ^ i3);
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Object] */
    Node b(Object obj, boolean z) {
        int i2;
        Node<K, V> node;
        Comparator<Comparable> comparator = this.f4458a;
        Node<K, V>[] nodeArr = this.f4459b;
        int secondaryHash = secondaryHash(obj.hashCode());
        int length = (nodeArr.length - 1) & secondaryHash;
        Node<K, V> node2 = nodeArr[length];
        if (node2 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) obj : null;
            while (true) {
                ?? r7 = node2.f4475f;
                i2 = comparable != null ? comparable.compareTo(r7) : comparator.compare(obj, r7);
                if (i2 == 0) {
                    return node2;
                }
                Node<K, V> node3 = i2 < 0 ? node2.f4471b : node2.f4472c;
                if (node3 == null) {
                    break;
                }
                node2 = node3;
            }
        } else {
            i2 = 0;
        }
        Node<K, V> node4 = node2;
        int i3 = i2;
        if (z) {
            Node node5 = this.f4460c;
            if (node4 != null) {
                node = new Node<>(node4, obj, secondaryHash, node5, node5.f4474e);
                if (i3 < 0) {
                    node4.f4471b = node;
                } else {
                    node4.f4472c = node;
                }
                rebalance(node4, true);
            } else if (comparator == NATURAL_ORDER && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName() + " is not Comparable");
            } else {
                node = new Node<>(node4, obj, secondaryHash, node5, node5.f4474e);
                nodeArr[length] = node;
            }
            int i4 = this.f4461d;
            this.f4461d = i4 + 1;
            if (i4 > this.f4463f) {
                doubleCapacity();
            }
            this.f4462e++;
            return node;
        }
        return null;
    }

    Node c(Map.Entry entry) {
        Node d2 = d(entry.getKey());
        if (d2 != null && equal(d2.f4477h, entry.getValue())) {
            return d2;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.f4459b, (Object) null);
        this.f4461d = 0;
        this.f4462e++;
        Node node = this.f4460c;
        Node node2 = node.f4473d;
        while (node2 != node) {
            Node node3 = node2.f4473d;
            node2.f4474e = null;
            node2.f4473d = null;
            node2 = node3;
        }
        node.f4474e = node;
        node.f4473d = node;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return d(obj) != null;
    }

    Node d(Object obj) {
        if (obj != null) {
            try {
                return b(obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    void e(Node node, boolean z) {
        int i2;
        if (z) {
            Node node2 = node.f4474e;
            node2.f4473d = node.f4473d;
            node.f4473d.f4474e = node2;
            node.f4474e = null;
            node.f4473d = null;
        }
        Node<K, V> node3 = node.f4471b;
        Node<K, V> node4 = node.f4472c;
        Node<K, V> node5 = node.f4470a;
        int i3 = 0;
        if (node3 == null || node4 == null) {
            if (node3 != null) {
                replaceInParent(node, node3);
                node.f4471b = null;
            } else if (node4 != null) {
                replaceInParent(node, node4);
                node.f4472c = null;
            } else {
                replaceInParent(node, null);
            }
            rebalance(node5, false);
            this.f4461d--;
            this.f4462e++;
            return;
        }
        Node<K, V> last = node3.f4478i > node4.f4478i ? node3.last() : node4.first();
        e(last, false);
        Node node6 = node.f4471b;
        if (node6 != null) {
            i2 = node6.f4478i;
            last.f4471b = node6;
            node6.f4470a = last;
            node.f4471b = null;
        } else {
            i2 = 0;
        }
        Node node7 = node.f4472c;
        if (node7 != null) {
            i3 = node7.f4478i;
            last.f4472c = node7;
            node7.f4470a = last;
            node.f4472c = null;
        }
        last.f4478i = Math.max(i2, i3) + 1;
        replaceInParent(node, last);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.EntrySet entrySet = this.entrySet;
        if (entrySet != null) {
            return entrySet;
        }
        LinkedHashTreeMap<K, V>.EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    Node f(Object obj) {
        Node d2 = d(obj);
        if (d2 != null) {
            e(d2, true);
        }
        return d2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Node d2 = d(obj);
        if (d2 != null) {
            return (V) d2.f4477h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.KeySet keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        LinkedHashTreeMap<K, V>.KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v) {
        Objects.requireNonNull(k2, "key == null");
        Node b2 = b(k2, true);
        V v2 = (V) b2.f4477h;
        b2.f4477h = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        Node f2 = f(obj);
        if (f2 != null) {
            return (V) f2.f4477h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f4461d;
    }
}
