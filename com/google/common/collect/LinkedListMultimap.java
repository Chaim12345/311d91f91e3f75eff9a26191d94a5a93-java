package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public class LinkedListMultimap<K, V> extends AbstractMultimap<K, V> implements ListMultimap<K, V>, Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    @NullableDecl
    private transient Node<K, V> head;
    private transient Map<K, KeyList<K, V>> keyToKeyList;
    private transient int modCount;
    private transient int size;
    @NullableDecl
    private transient Node<K, V> tail;

    /* loaded from: classes2.dex */
    private class DistinctKeyIterator implements Iterator<K> {

        /* renamed from: a  reason: collision with root package name */
        final Set f8691a;

        /* renamed from: b  reason: collision with root package name */
        Node f8692b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Node f8693c;

        /* renamed from: d  reason: collision with root package name */
        int f8694d;

        private DistinctKeyIterator() {
            this.f8691a = Sets.newHashSetWithExpectedSize(LinkedListMultimap.this.keySet().size());
            this.f8692b = LinkedListMultimap.this.head;
            this.f8694d = LinkedListMultimap.this.modCount;
        }

        private void checkForConcurrentModification() {
            if (LinkedListMultimap.this.modCount != this.f8694d) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkForConcurrentModification();
            return this.f8692b != null;
        }

        @Override // java.util.Iterator
        public K next() {
            Node node;
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.f8692b);
            Node node2 = this.f8692b;
            this.f8693c = node2;
            this.f8691a.add(node2.f8699a);
            do {
                node = this.f8692b.f8701c;
                this.f8692b = node;
                if (node == null) {
                    break;
                }
            } while (!this.f8691a.add(node.f8699a));
            return (K) this.f8693c.f8699a;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.e(this.f8693c != null);
            LinkedListMultimap.this.removeAllNodes(this.f8693c.f8699a);
            this.f8693c = null;
            this.f8694d = LinkedListMultimap.this.modCount;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class KeyList<K, V> {

        /* renamed from: a  reason: collision with root package name */
        Node f8696a;

        /* renamed from: b  reason: collision with root package name */
        Node f8697b;

        /* renamed from: c  reason: collision with root package name */
        int f8698c;

        KeyList(Node node) {
            this.f8696a = node;
            this.f8697b = node;
            node.f8704f = null;
            node.f8703e = null;
            this.f8698c = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Node<K, V> extends AbstractMapEntry<K, V> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8699a;
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        Object f8700b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Node f8701c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        Node f8702d;
        @NullableDecl

        /* renamed from: e  reason: collision with root package name */
        Node f8703e;
        @NullableDecl

        /* renamed from: f  reason: collision with root package name */
        Node f8704f;

        Node(@NullableDecl Object obj, @NullableDecl Object obj2) {
            this.f8699a = obj;
            this.f8700b = obj2;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return (K) this.f8699a;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return (V) this.f8700b;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(@NullableDecl V v) {
            V v2 = (V) this.f8700b;
            this.f8700b = v;
            return v2;
        }
    }

    /* loaded from: classes2.dex */
    private class NodeIterator implements ListIterator<Map.Entry<K, V>> {

        /* renamed from: a  reason: collision with root package name */
        int f8705a;
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        Node f8706b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Node f8707c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        Node f8708d;

        /* renamed from: e  reason: collision with root package name */
        int f8709e;

        NodeIterator(int i2) {
            this.f8709e = LinkedListMultimap.this.modCount;
            int size = LinkedListMultimap.this.size();
            Preconditions.checkPositionIndex(i2, size);
            if (i2 < size / 2) {
                this.f8706b = LinkedListMultimap.this.head;
                while (true) {
                    int i3 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    next();
                    i2 = i3;
                }
            } else {
                this.f8708d = LinkedListMultimap.this.tail;
                this.f8705a = size;
                while (true) {
                    int i4 = i2 + 1;
                    if (i2 >= size) {
                        break;
                    }
                    previous();
                    i2 = i4;
                }
            }
            this.f8707c = null;
        }

        private void checkForConcurrentModification() {
            if (LinkedListMultimap.this.modCount != this.f8709e) {
                throw new ConcurrentModificationException();
            }
        }

        void a(Object obj) {
            Preconditions.checkState(this.f8707c != null);
            this.f8707c.f8700b = obj;
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void add(Object obj) {
            add((Map.Entry) ((Map.Entry) obj));
        }

        public void add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            checkForConcurrentModification();
            return this.f8706b != null;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            checkForConcurrentModification();
            return this.f8708d != null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        @CanIgnoreReturnValue
        public Node<K, V> next() {
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.f8706b);
            Node<K, V> node = this.f8706b;
            this.f8707c = node;
            this.f8708d = node;
            this.f8706b = node.f8701c;
            this.f8705a++;
            return node;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.f8705a;
        }

        @Override // java.util.ListIterator
        @CanIgnoreReturnValue
        public Node<K, V> previous() {
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.f8708d);
            Node<K, V> node = this.f8708d;
            this.f8707c = node;
            this.f8706b = node;
            this.f8708d = node.f8702d;
            this.f8705a--;
            return node;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.f8705a - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.e(this.f8707c != null);
            Node node = this.f8707c;
            if (node != this.f8706b) {
                this.f8708d = node.f8702d;
                this.f8705a--;
            } else {
                this.f8706b = node.f8701c;
            }
            LinkedListMultimap.this.removeNode(node);
            this.f8707c = null;
            this.f8709e = LinkedListMultimap.this.modCount;
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(Object obj) {
            set((Map.Entry) ((Map.Entry) obj));
        }

        public void set(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ValueForKeyIterator implements ListIterator<V> {
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8711a;

        /* renamed from: b  reason: collision with root package name */
        int f8712b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Node f8713c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        Node f8714d;
        @NullableDecl

        /* renamed from: e  reason: collision with root package name */
        Node f8715e;

        ValueForKeyIterator(@NullableDecl Object obj) {
            this.f8711a = obj;
            KeyList keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(obj);
            this.f8713c = keyList == null ? null : keyList.f8696a;
        }

        public ValueForKeyIterator(@NullableDecl Object obj, int i2) {
            KeyList keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(obj);
            int i3 = keyList == null ? 0 : keyList.f8698c;
            Preconditions.checkPositionIndex(i2, i3);
            if (i2 < i3 / 2) {
                this.f8713c = keyList == null ? null : keyList.f8696a;
                while (true) {
                    int i4 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    next();
                    i2 = i4;
                }
            } else {
                this.f8715e = keyList == null ? null : keyList.f8697b;
                this.f8712b = i3;
                while (true) {
                    int i5 = i2 + 1;
                    if (i2 >= i3) {
                        break;
                    }
                    previous();
                    i2 = i5;
                }
            }
            this.f8711a = obj;
            this.f8714d = null;
        }

        @Override // java.util.ListIterator
        public void add(V v) {
            this.f8715e = LinkedListMultimap.this.addNode(this.f8711a, v, this.f8713c);
            this.f8712b++;
            this.f8714d = null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.f8713c != null;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.f8715e != null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        @CanIgnoreReturnValue
        public V next() {
            LinkedListMultimap.checkElement(this.f8713c);
            Node node = this.f8713c;
            this.f8714d = node;
            this.f8715e = node;
            this.f8713c = node.f8703e;
            this.f8712b++;
            return (V) node.f8700b;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.f8712b;
        }

        @Override // java.util.ListIterator
        @CanIgnoreReturnValue
        public V previous() {
            LinkedListMultimap.checkElement(this.f8715e);
            Node node = this.f8715e;
            this.f8714d = node;
            this.f8713c = node;
            this.f8715e = node.f8704f;
            this.f8712b--;
            return (V) node.f8700b;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.f8712b - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            CollectPreconditions.e(this.f8714d != null);
            Node node = this.f8714d;
            if (node != this.f8713c) {
                this.f8715e = node.f8704f;
                this.f8712b--;
            } else {
                this.f8713c = node.f8703e;
            }
            LinkedListMultimap.this.removeNode(node);
            this.f8714d = null;
        }

        @Override // java.util.ListIterator
        public void set(V v) {
            Preconditions.checkState(this.f8714d != null);
            this.f8714d.f8700b = v;
        }
    }

    LinkedListMultimap() {
        this(12);
    }

    private LinkedListMultimap(int i2) {
        this.keyToKeyList = Platform.c(i2);
    }

    private LinkedListMultimap(Multimap<? extends K, ? extends V> multimap) {
        this(multimap.keySet().size());
        putAll(multimap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CanIgnoreReturnValue
    public Node<K, V> addNode(@NullableDecl K k2, @NullableDecl V v, @NullableDecl Node<K, V> node) {
        Map<K, KeyList<K, V>> map;
        KeyList<K, V> keyList;
        Node<K, V> node2 = new Node<>(k2, v);
        if (this.head != null) {
            if (node == null) {
                Node<K, V> node3 = this.tail;
                node3.f8701c = node2;
                node2.f8702d = node3;
                this.tail = node2;
                KeyList<K, V> keyList2 = this.keyToKeyList.get(k2);
                if (keyList2 == null) {
                    map = this.keyToKeyList;
                    keyList = new KeyList<>(node2);
                } else {
                    keyList2.f8698c++;
                    Node node4 = keyList2.f8697b;
                    node4.f8703e = node2;
                    node2.f8704f = node4;
                    keyList2.f8697b = node2;
                }
            } else {
                this.keyToKeyList.get(k2).f8698c++;
                node2.f8702d = node.f8702d;
                node2.f8704f = node.f8704f;
                node2.f8701c = node;
                node2.f8703e = node;
                Node node5 = node.f8704f;
                if (node5 == null) {
                    this.keyToKeyList.get(k2).f8696a = node2;
                } else {
                    node5.f8703e = node2;
                }
                Node node6 = node.f8702d;
                if (node6 == null) {
                    this.head = node2;
                } else {
                    node6.f8701c = node2;
                }
                node.f8702d = node2;
                node.f8704f = node2;
            }
            this.size++;
            return node2;
        }
        this.tail = node2;
        this.head = node2;
        map = this.keyToKeyList;
        keyList = new KeyList<>(node2);
        map.put(k2, keyList);
        this.modCount++;
        this.size++;
        return node2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkElement(@NullableDecl Object obj) {
        if (obj == null) {
            throw new NoSuchElementException();
        }
    }

    public static <K, V> LinkedListMultimap<K, V> create() {
        return new LinkedListMultimap<>();
    }

    public static <K, V> LinkedListMultimap<K, V> create(int i2) {
        return new LinkedListMultimap<>(i2);
    }

    public static <K, V> LinkedListMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new LinkedListMultimap<>(multimap);
    }

    private List<V> getCopy(@NullableDecl Object obj) {
        return Collections.unmodifiableList(Lists.newArrayList(new ValueForKeyIterator(obj)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.keyToKeyList = CompactLinkedHashMap.create();
        int readInt = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAllNodes(@NullableDecl Object obj) {
        Iterators.c(new ValueForKeyIterator(obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeNode(Node<K, V> node) {
        Node<K, V> node2 = node.f8702d;
        Node<K, V> node3 = node.f8701c;
        if (node2 != null) {
            node2.f8701c = node3;
        } else {
            this.head = node3;
        }
        Node node4 = node.f8701c;
        if (node4 != null) {
            node4.f8702d = node2;
        } else {
            this.tail = node2;
        }
        if (node.f8704f == null && node.f8703e == null) {
            this.keyToKeyList.remove(node.f8699a).f8698c = 0;
            this.modCount++;
        } else {
            KeyList<K, V> keyList = this.keyToKeyList.get(node.f8699a);
            keyList.f8698c--;
            Node node5 = node.f8704f;
            Node node6 = node.f8703e;
            if (node5 == null) {
                keyList.f8696a = node6;
            } else {
                node5.f8703e = node6;
            }
            Node node7 = node.f8703e;
            if (node7 == null) {
                keyList.f8697b = node5;
            } else {
                node7.f8704f = node5;
            }
        }
        this.size--;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @Override // com.google.common.collect.AbstractMultimap
    Map a() {
        return new Multimaps.AsMap(this);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.AbstractMultimap
    Set c() {
        return new Sets.ImprovedAbstractSet<K>() { // from class: com.google.common.collect.LinkedListMultimap.1KeySetImpl
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return LinkedListMultimap.this.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<K> iterator() {
                return new DistinctKeyIterator();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                return !LinkedListMultimap.this.removeAll(obj).isEmpty();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return LinkedListMultimap.this.keyToKeyList.size();
            }
        };
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        this.head = null;
        this.tail = null;
        this.keyToKeyList.clear();
        this.size = 0;
        this.modCount++;
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return this.keyToKeyList.containsKey(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean containsValue(@NullableDecl Object obj) {
        return values().contains(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Multiset d() {
        return new Multimaps.Keys(this);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public List<Map.Entry<K, V>> entries() {
        return (List) super.entries();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Iterator f() {
        throw new AssertionError("should never be called");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Collection get(@NullableDecl Object obj) {
        return get((LinkedListMultimap<K, V>) obj);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> get(@NullableDecl final K k2) {
        return new AbstractSequentialList<V>() { // from class: com.google.common.collect.LinkedListMultimap.1
            @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
            public ListIterator<V> listIterator(int i2) {
                return new ValueForKeyIterator(k2, i2);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                KeyList keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(k2);
                if (keyList == null) {
                    return 0;
                }
                return keyList.f8698c;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean put(@NullableDecl K k2, @NullableDecl V v) {
        addNode(k2, v, null);
        return true;
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean putAll(Multimap multimap) {
        return super.putAll(multimap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean putAll(@NullableDecl Object obj, Iterable iterable) {
        return super.putAll(obj, iterable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: q */
    public List b() {
        return new AbstractSequentialList<Map.Entry<K, V>>() { // from class: com.google.common.collect.LinkedListMultimap.1EntriesImpl
            @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
            public ListIterator<Map.Entry<K, V>> listIterator(int i2) {
                return new NodeIterator(i2);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return LinkedListMultimap.this.size;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: r */
    public List e() {
        return new AbstractSequentialList<V>() { // from class: com.google.common.collect.LinkedListMultimap.1ValuesImpl
            @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
            public ListIterator<V> listIterator(int i2) {
                final NodeIterator nodeIterator = new NodeIterator(i2);
                return new TransformedListIterator<Map.Entry<K, V>, V>(this, nodeIterator) { // from class: com.google.common.collect.LinkedListMultimap.1ValuesImpl.1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    @Override // com.google.common.collect.TransformedIterator
                    /* renamed from: b */
                    public Object a(Map.Entry entry) {
                        return entry.getValue();
                    }

                    @Override // com.google.common.collect.TransformedListIterator, java.util.ListIterator
                    public void set(V v) {
                        nodeIterator.a(v);
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return LinkedListMultimap.this.size;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public List<V> removeAll(@NullableDecl Object obj) {
        List<V> copy = getCopy(obj);
        removeAllNodes(obj);
        return copy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Collection replaceValues(@NullableDecl Object obj, Iterable iterable) {
        return replaceValues((LinkedListMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public List<V> replaceValues(@NullableDecl K k2, Iterable<? extends V> iterable) {
        List<V> copy = getCopy(k2);
        ValueForKeyIterator valueForKeyIterator = new ValueForKeyIterator(k2);
        Iterator<? extends V> it = iterable.iterator();
        while (valueForKeyIterator.hasNext() && it.hasNext()) {
            valueForKeyIterator.next();
            valueForKeyIterator.set(it.next());
        }
        while (valueForKeyIterator.hasNext()) {
            valueForKeyIterator.next();
            valueForKeyIterator.remove();
        }
        while (it.hasNext()) {
            valueForKeyIterator.add(it.next());
        }
        return copy;
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public List<V> values() {
        return (List) super.values();
    }
}
