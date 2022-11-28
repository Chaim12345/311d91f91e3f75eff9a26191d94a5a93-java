package com.google.gson.internal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes2.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() { // from class: com.google.gson.internal.LinkedTreeMap.1
        @Override // java.util.Comparator
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    Comparator f10184a;

    /* renamed from: b  reason: collision with root package name */
    Node f10185b;

    /* renamed from: c  reason: collision with root package name */
    int f10186c;

    /* renamed from: d  reason: collision with root package name */
    int f10187d;

    /* renamed from: e  reason: collision with root package name */
    final Node f10188e;
    private LinkedTreeMap<K, V>.EntrySet entrySet;
    private LinkedTreeMap<K, V>.KeySet keySet;

    /* loaded from: classes2.dex */
    class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedTreeMap.this.b((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>(this) { // from class: com.google.gson.internal.LinkedTreeMap.EntrySet.1
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    return a();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Node b2;
            if ((obj instanceof Map.Entry) && (b2 = LinkedTreeMap.this.b((Map.Entry) obj)) != null) {
                LinkedTreeMap.this.d(b2, true);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.f10186c;
        }
    }

    /* loaded from: classes2.dex */
    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<K>(this) { // from class: com.google.gson.internal.LinkedTreeMap.KeySet.1
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return (K) a().f10200f;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedTreeMap.this.e(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.f10186c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public abstract class LinkedTreeMapIterator<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        Node f10191a;

        /* renamed from: b  reason: collision with root package name */
        Node f10192b = null;

        /* renamed from: c  reason: collision with root package name */
        int f10193c;

        LinkedTreeMapIterator() {
            this.f10191a = LinkedTreeMap.this.f10188e.f10198d;
            this.f10193c = LinkedTreeMap.this.f10187d;
        }

        final Node a() {
            Node node = this.f10191a;
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            if (node != linkedTreeMap.f10188e) {
                if (linkedTreeMap.f10187d == this.f10193c) {
                    this.f10191a = node.f10198d;
                    this.f10192b = node;
                    return node;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f10191a != LinkedTreeMap.this.f10188e;
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node node = this.f10192b;
            if (node == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.d(node, true);
            this.f10192b = null;
            this.f10193c = LinkedTreeMap.this.f10187d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Node<K, V> implements Map.Entry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        Node f10195a;

        /* renamed from: b  reason: collision with root package name */
        Node f10196b;

        /* renamed from: c  reason: collision with root package name */
        Node f10197c;

        /* renamed from: d  reason: collision with root package name */
        Node f10198d;

        /* renamed from: e  reason: collision with root package name */
        Node f10199e;

        /* renamed from: f  reason: collision with root package name */
        final Object f10200f;

        /* renamed from: g  reason: collision with root package name */
        Object f10201g;

        /* renamed from: h  reason: collision with root package name */
        int f10202h;

        Node() {
            this.f10200f = null;
            this.f10199e = this;
            this.f10198d = this;
        }

        Node(Node node, Object obj, Node node2, Node node3) {
            this.f10195a = node;
            this.f10200f = obj;
            this.f10202h = 1;
            this.f10198d = node2;
            this.f10199e = node3;
            node3.f10198d = this;
            node2.f10199e = this;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object obj2 = this.f10200f;
                if (obj2 == null) {
                    if (entry.getKey() != null) {
                        return false;
                    }
                } else if (!obj2.equals(entry.getKey())) {
                    return false;
                }
                Object obj3 = this.f10201g;
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
            for (Node<K, V> node2 = this.f10196b; node2 != null; node2 = node2.f10196b) {
                node = node2;
            }
            return node;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) this.f10200f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return (V) this.f10201g;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.f10200f;
            int hashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.f10201g;
            return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
        }

        public Node<K, V> last() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.f10197c; node2 != null; node2 = node2.f10197c) {
                node = node2;
            }
            return node;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) this.f10201g;
            this.f10201g = v;
            return v2;
        }

        public String toString() {
            return this.f10200f + "=" + this.f10201g;
        }
    }

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.f10186c = 0;
        this.f10187d = 0;
        this.f10188e = new Node();
        this.f10184a = comparator == null ? NATURAL_ORDER : comparator;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization is unsupported");
    }

    private void rebalance(Node<K, V> node, boolean z) {
        while (node != null) {
            Node<K, V> node2 = node.f10196b;
            Node<K, V> node3 = node.f10197c;
            int i2 = node2 != null ? node2.f10202h : 0;
            int i3 = node3 != null ? node3.f10202h : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                Node node4 = node3.f10196b;
                Node node5 = node3.f10197c;
                int i5 = (node4 != null ? node4.f10202h : 0) - (node5 != null ? node5.f10202h : 0);
                if (i5 != -1 && (i5 != 0 || z)) {
                    rotateRight(node3);
                }
                rotateLeft(node);
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                Node node6 = node2.f10196b;
                Node node7 = node2.f10197c;
                int i6 = (node6 != null ? node6.f10202h : 0) - (node7 != null ? node7.f10202h : 0);
                if (i6 != 1 && (i6 != 0 || z)) {
                    rotateLeft(node2);
                }
                rotateRight(node);
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                node.f10202h = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                node.f10202h = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            node = node.f10195a;
        }
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> node2) {
        Node node3 = node.f10195a;
        node.f10195a = null;
        if (node2 != null) {
            node2.f10195a = node3;
        }
        if (node3 == null) {
            this.f10185b = node2;
        } else if (node3.f10196b == node) {
            node3.f10196b = node2;
        } else {
            node3.f10197c = node2;
        }
    }

    private void rotateLeft(Node<K, V> node) {
        Node node2 = node.f10196b;
        Node<K, V> node3 = node.f10197c;
        Node node4 = node3.f10196b;
        Node node5 = node3.f10197c;
        node.f10197c = node4;
        if (node4 != null) {
            node4.f10195a = node;
        }
        replaceInParent(node, node3);
        node3.f10196b = node;
        node.f10195a = node3;
        int max = Math.max(node2 != null ? node2.f10202h : 0, node4 != null ? node4.f10202h : 0) + 1;
        node.f10202h = max;
        node3.f10202h = Math.max(max, node5 != null ? node5.f10202h : 0) + 1;
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> node2 = node.f10196b;
        Node node3 = node.f10197c;
        Node node4 = node2.f10196b;
        Node node5 = node2.f10197c;
        node.f10196b = node5;
        if (node5 != null) {
            node5.f10195a = node;
        }
        replaceInParent(node, node2);
        node2.f10197c = node;
        node.f10195a = node2;
        int max = Math.max(node3 != null ? node3.f10202h : 0, node5 != null ? node5.f10202h : 0) + 1;
        node.f10202h = max;
        node2.f10202h = Math.max(max, node4 != null ? node4.f10202h : 0) + 1;
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    Node a(Object obj, boolean z) {
        int i2;
        Node node;
        Comparator<Comparable> comparator = this.f10184a;
        Node<K, V> node2 = this.f10185b;
        if (node2 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) obj : null;
            while (true) {
                ?? r4 = node2.f10200f;
                i2 = comparable != null ? comparable.compareTo(r4) : comparator.compare(obj, r4);
                if (i2 == 0) {
                    return node2;
                }
                Node<K, V> node3 = i2 < 0 ? node2.f10196b : node2.f10197c;
                if (node3 == null) {
                    break;
                }
                node2 = node3;
            }
        } else {
            i2 = 0;
        }
        if (z) {
            Node node4 = this.f10188e;
            if (node2 != null) {
                node = new Node(node2, obj, node4, node4.f10199e);
                if (i2 < 0) {
                    node2.f10196b = node;
                } else {
                    node2.f10197c = node;
                }
                rebalance(node2, true);
            } else if (comparator == NATURAL_ORDER && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName() + " is not Comparable");
            } else {
                node = new Node(node2, obj, node4, node4.f10199e);
                this.f10185b = node;
            }
            this.f10186c++;
            this.f10187d++;
            return node;
        }
        return null;
    }

    Node b(Map.Entry entry) {
        Node c2 = c(entry.getKey());
        if (c2 != null && equal(c2.f10201g, entry.getValue())) {
            return c2;
        }
        return null;
    }

    Node c(Object obj) {
        if (obj != null) {
            try {
                return a(obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f10185b = null;
        this.f10186c = 0;
        this.f10187d++;
        Node node = this.f10188e;
        node.f10199e = node;
        node.f10198d = node;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return c(obj) != null;
    }

    void d(Node node, boolean z) {
        int i2;
        if (z) {
            Node node2 = node.f10199e;
            node2.f10198d = node.f10198d;
            node.f10198d.f10199e = node2;
        }
        Node<K, V> node3 = node.f10196b;
        Node<K, V> node4 = node.f10197c;
        Node<K, V> node5 = node.f10195a;
        int i3 = 0;
        if (node3 == null || node4 == null) {
            if (node3 != null) {
                replaceInParent(node, node3);
                node.f10196b = null;
            } else if (node4 != null) {
                replaceInParent(node, node4);
                node.f10197c = null;
            } else {
                replaceInParent(node, null);
            }
            rebalance(node5, false);
            this.f10186c--;
            this.f10187d++;
            return;
        }
        Node<K, V> last = node3.f10202h > node4.f10202h ? node3.last() : node4.first();
        d(last, false);
        Node node6 = node.f10196b;
        if (node6 != null) {
            i2 = node6.f10202h;
            last.f10196b = node6;
            node6.f10195a = last;
            node.f10196b = null;
        } else {
            i2 = 0;
        }
        Node node7 = node.f10197c;
        if (node7 != null) {
            i3 = node7.f10202h;
            last.f10197c = node7;
            node7.f10195a = last;
            node.f10197c = null;
        }
        last.f10202h = Math.max(i2, i3) + 1;
        replaceInParent(node, last);
    }

    Node e(Object obj) {
        Node c2 = c(obj);
        if (c2 != null) {
            d(c2, true);
        }
        return c2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.EntrySet entrySet = this.entrySet;
        if (entrySet != null) {
            return entrySet;
        }
        LinkedTreeMap<K, V>.EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Node c2 = c(obj);
        if (c2 != null) {
            return (V) c2.f10201g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedTreeMap<K, V>.KeySet keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        LinkedTreeMap<K, V>.KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v) {
        Objects.requireNonNull(k2, "key == null");
        Node a2 = a(k2, true);
        V v2 = (V) a2.f10201g;
        a2.f10201g = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        Node e2 = e(obj);
        if (e2 != null) {
            return (V) e2.f10201g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f10186c;
    }
}
