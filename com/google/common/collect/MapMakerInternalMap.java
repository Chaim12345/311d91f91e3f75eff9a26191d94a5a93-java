package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap.InternalEntry;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes2.dex */
public class MapMakerInternalMap<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {

    /* renamed from: j  reason: collision with root package name */
    static final WeakValueReference f8740j = new WeakValueReference<Object, Object, DummyInternalEntry>() { // from class: com.google.common.collect.MapMakerInternalMap.1
        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public void clear() {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public WeakValueReference<Object, Object, DummyInternalEntry> copyFor(ReferenceQueue<Object> referenceQueue, DummyInternalEntry dummyInternalEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public Object get() {
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public DummyInternalEntry getEntry() {
            return null;
        }
    };
    private static final long serialVersionUID = 5;

    /* renamed from: a  reason: collision with root package name */
    final transient int f8741a;

    /* renamed from: b  reason: collision with root package name */
    final transient int f8742b;

    /* renamed from: c  reason: collision with root package name */
    final transient Segment[] f8743c;

    /* renamed from: d  reason: collision with root package name */
    final int f8744d;

    /* renamed from: e  reason: collision with root package name */
    final Equivalence f8745e;

    /* renamed from: f  reason: collision with root package name */
    final transient InternalEntryHelper f8746f;
    @NullableDecl

    /* renamed from: g  reason: collision with root package name */
    transient Set f8747g;
    @NullableDecl

    /* renamed from: h  reason: collision with root package name */
    transient Collection f8748h;
    @NullableDecl

    /* renamed from: i  reason: collision with root package name */
    transient Set f8749i;

    /* loaded from: classes2.dex */
    static abstract class AbstractSerializationProxy<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;

        /* renamed from: a  reason: collision with root package name */
        final Strength f8750a;

        /* renamed from: b  reason: collision with root package name */
        final Strength f8751b;

        /* renamed from: c  reason: collision with root package name */
        final Equivalence f8752c;

        /* renamed from: d  reason: collision with root package name */
        final int f8753d;

        /* renamed from: e  reason: collision with root package name */
        transient ConcurrentMap f8754e;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingConcurrentMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        /* renamed from: c */
        public ConcurrentMap delegate() {
            return this.f8754e;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void d(ObjectInputStream objectInputStream) {
            while (true) {
                Object readObject = objectInputStream.readObject();
                if (readObject == null) {
                    return;
                }
                this.f8754e.put(readObject, objectInputStream.readObject());
            }
        }

        MapMaker e(ObjectInputStream objectInputStream) {
            return new MapMaker().initialCapacity(objectInputStream.readInt()).g(this.f8750a).h(this.f8751b).f(this.f8752c).concurrencyLevel(this.f8753d);
        }

        void f(ObjectOutputStream objectOutputStream) {
            objectOutputStream.writeInt(this.f8754e.size());
            for (Map.Entry<K, V> entry : this.f8754e.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class AbstractStrongKeyEntry<K, V, E extends InternalEntry<K, V, E>> implements InternalEntry<K, V, E> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8755a;

        /* renamed from: b  reason: collision with root package name */
        final int f8756b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        final InternalEntry f8757c;

        AbstractStrongKeyEntry(Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
            this.f8755a = obj;
            this.f8756b = i2;
            this.f8757c = internalEntry;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public int getHash() {
            return this.f8756b;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public K getKey() {
            return (K) this.f8755a;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public E getNext() {
            return (E) this.f8757c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class AbstractWeakKeyEntry<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<K> implements InternalEntry<K, V, E> {

        /* renamed from: a  reason: collision with root package name */
        final int f8758a;
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        final InternalEntry f8759b;

        AbstractWeakKeyEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
            super(obj, referenceQueue);
            this.f8758a = i2;
            this.f8759b = internalEntry;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public int getHash() {
            return this.f8758a;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public K getKey() {
            return get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public E getNext() {
            return (E) this.f8759b;
        }
    }

    /* loaded from: classes2.dex */
    static final class CleanupMapTask implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final WeakReference f8760a;

        public CleanupMapTask(MapMakerInternalMap<?, ?, ?, ?> mapMakerInternalMap) {
            this.f8760a = new WeakReference(mapMakerInternalMap);
        }

        @Override // java.lang.Runnable
        public void run() {
            MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) this.f8760a.get();
            if (mapMakerInternalMap == null) {
                throw new CancellationException();
            }
            for (Segment segment : mapMakerInternalMap.f8743c) {
                segment.B();
            }
        }
    }

    /* loaded from: classes2.dex */
    static final class DummyInternalEntry implements InternalEntry<Object, Object, DummyInternalEntry> {
        private DummyInternalEntry() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public int getHash() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public Object getKey() {
            throw new AssertionError();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public DummyInternalEntry getNext() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public Object getValue() {
            throw new AssertionError();
        }
    }

    /* loaded from: classes2.dex */
    final class EntryIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<Map.Entry<K, V>> {
        EntryIterator(MapMakerInternalMap mapMakerInternalMap) {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public Map.Entry<K, V> next() {
            return c();
        }
    }

    /* loaded from: classes2.dex */
    final class EntrySet extends SafeToArraySet<Map.Entry<K, V>> {
        EntrySet() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = MapMakerInternalMap.this.get(key)) != null && MapMakerInternalMap.this.n().equivalent(entry.getValue(), obj2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator(MapMakerInternalMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && MapMakerInternalMap.this.remove(key, entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public abstract class HashIterator<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        int f8762a;

        /* renamed from: b  reason: collision with root package name */
        int f8763b = -1;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Segment f8764c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        AtomicReferenceArray f8765d;
        @NullableDecl

        /* renamed from: e  reason: collision with root package name */
        InternalEntry f8766e;
        @NullableDecl

        /* renamed from: f  reason: collision with root package name */
        WriteThroughEntry f8767f;
        @NullableDecl

        /* renamed from: g  reason: collision with root package name */
        WriteThroughEntry f8768g;

        HashIterator() {
            this.f8762a = MapMakerInternalMap.this.f8743c.length - 1;
            a();
        }

        final void a() {
            this.f8767f = null;
            if (d() || e()) {
                return;
            }
            while (true) {
                int i2 = this.f8762a;
                if (i2 < 0) {
                    return;
                }
                Segment[] segmentArr = MapMakerInternalMap.this.f8743c;
                this.f8762a = i2 - 1;
                Segment segment = segmentArr[i2];
                this.f8764c = segment;
                if (segment.f8772b != 0) {
                    AtomicReferenceArray atomicReferenceArray = this.f8764c.f8775e;
                    this.f8765d = atomicReferenceArray;
                    this.f8763b = atomicReferenceArray.length() - 1;
                    if (e()) {
                        return;
                    }
                }
            }
        }

        boolean b(InternalEntry internalEntry) {
            boolean z;
            try {
                Object key = internalEntry.getKey();
                Object f2 = MapMakerInternalMap.this.f(internalEntry);
                if (f2 != null) {
                    this.f8767f = new WriteThroughEntry(key, f2);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.f8764c.r();
            }
        }

        WriteThroughEntry c() {
            WriteThroughEntry writeThroughEntry = this.f8767f;
            if (writeThroughEntry != null) {
                this.f8768g = writeThroughEntry;
                a();
                return this.f8768g;
            }
            throw new NoSuchElementException();
        }

        boolean d() {
            InternalEntry internalEntry = this.f8766e;
            if (internalEntry == null) {
                return false;
            }
            while (true) {
                this.f8766e = internalEntry.getNext();
                InternalEntry internalEntry2 = this.f8766e;
                if (internalEntry2 == null) {
                    return false;
                }
                if (b(internalEntry2)) {
                    return true;
                }
                internalEntry = this.f8766e;
            }
        }

        boolean e() {
            while (true) {
                int i2 = this.f8763b;
                if (i2 < 0) {
                    return false;
                }
                AtomicReferenceArray atomicReferenceArray = this.f8765d;
                this.f8763b = i2 - 1;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i2);
                this.f8766e = internalEntry;
                if (internalEntry != null && (b(internalEntry) || d())) {
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f8767f != null;
        }

        @Override // java.util.Iterator
        public abstract T next();

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.e(this.f8768g != null);
            MapMakerInternalMap.this.remove(this.f8768g.getKey());
            this.f8768g = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface InternalEntry<K, V, E extends InternalEntry<K, V, E>> {
        int getHash();

        K getKey();

        E getNext();

        V getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface InternalEntryHelper<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> {
        E copy(S s2, E e2, @NullableDecl E e3);

        Strength keyStrength();

        E newEntry(S s2, K k2, int i2, @NullableDecl E e2);

        S newSegment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i2, int i3);

        void setValue(S s2, E e2, V v);

        Strength valueStrength();
    }

    /* loaded from: classes2.dex */
    final class KeyIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<K> {
        KeyIterator(MapMakerInternalMap mapMakerInternalMap) {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public K next() {
            return (K) c().getKey();
        }
    }

    /* loaded from: classes2.dex */
    final class KeySet extends SafeToArraySet<K> {
        KeySet() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator(MapMakerInternalMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return MapMakerInternalMap.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    /* loaded from: classes2.dex */
    private static abstract class SafeToArraySet<E> extends AbstractSet<E> {
        private SafeToArraySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) MapMakerInternalMap.toArrayList(this).toArray(tArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class Segment<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        @Weak

        /* renamed from: a  reason: collision with root package name */
        final MapMakerInternalMap f8771a;

        /* renamed from: b  reason: collision with root package name */
        volatile int f8772b;

        /* renamed from: c  reason: collision with root package name */
        int f8773c;

        /* renamed from: d  reason: collision with root package name */
        int f8774d;
        @NullableDecl

        /* renamed from: e  reason: collision with root package name */
        volatile AtomicReferenceArray f8775e;

        /* renamed from: f  reason: collision with root package name */
        final int f8776f;

        /* renamed from: g  reason: collision with root package name */
        final AtomicInteger f8777g = new AtomicInteger();

        Segment(MapMakerInternalMap mapMakerInternalMap, int i2, int i3) {
            this.f8771a = mapMakerInternalMap;
            this.f8776f = i3;
            m(q(i2));
        }

        static boolean n(InternalEntry internalEntry) {
            return internalEntry.getValue() == null;
        }

        boolean A(Object obj, int i2, Object obj2, Object obj3) {
            lock();
            try {
                s();
                AtomicReferenceArray atomicReferenceArray = this.f8775e;
                int length = (atomicReferenceArray.length() - 1) & i2;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry internalEntry2 = internalEntry; internalEntry2 != null; internalEntry2 = internalEntry2.getNext()) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() == i2 && key != null && this.f8771a.f8745e.equivalent(obj, key)) {
                        Object value = internalEntry2.getValue();
                        if (value != null) {
                            if (this.f8771a.n().equivalent(obj2, value)) {
                                this.f8773c++;
                                E(internalEntry2, obj3);
                                return true;
                            }
                            return false;
                        }
                        if (n(internalEntry2)) {
                            this.f8773c++;
                            atomicReferenceArray.set(length, y(internalEntry, internalEntry2));
                            this.f8772b--;
                        }
                        return false;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        void B() {
            C();
        }

        void C() {
            if (tryLock()) {
                try {
                    p();
                    this.f8777g.set(0);
                } finally {
                    unlock();
                }
            }
        }

        abstract Segment D();

        /* JADX WARN: Multi-variable type inference failed */
        void E(InternalEntry internalEntry, Object obj) {
            this.f8771a.f8746f.setValue(D(), internalEntry, obj);
        }

        void F() {
            if (tryLock()) {
                try {
                    p();
                } finally {
                    unlock();
                }
            }
        }

        void a() {
            if (this.f8772b != 0) {
                lock();
                try {
                    AtomicReferenceArray atomicReferenceArray = this.f8775e;
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    o();
                    this.f8777g.set(0);
                    this.f8773c++;
                    this.f8772b = 0;
                } finally {
                    unlock();
                }
            }
        }

        void b(ReferenceQueue referenceQueue) {
            do {
            } while (referenceQueue.poll() != null);
        }

        boolean c(Object obj, int i2) {
            try {
                boolean z = false;
                if (this.f8772b != 0) {
                    InternalEntry k2 = k(obj, i2);
                    if (k2 != null) {
                        if (k2.getValue() != null) {
                            z = true;
                        }
                    }
                    return z;
                }
                return false;
            } finally {
                r();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        InternalEntry d(InternalEntry internalEntry, InternalEntry internalEntry2) {
            return this.f8771a.f8746f.copy(D(), internalEntry, internalEntry2);
        }

        @GuardedBy("this")
        void e(ReferenceQueue referenceQueue) {
            int i2 = 0;
            do {
                Reference poll = referenceQueue.poll();
                if (poll == null) {
                    return;
                }
                this.f8771a.i((InternalEntry) poll);
                i2++;
            } while (i2 != 16);
        }

        @GuardedBy("this")
        void f(ReferenceQueue referenceQueue) {
            int i2 = 0;
            do {
                Reference poll = referenceQueue.poll();
                if (poll == null) {
                    return;
                }
                this.f8771a.j((WeakValueReference) poll);
                i2++;
            } while (i2 != 16);
        }

        @GuardedBy("this")
        void g() {
            AtomicReferenceArray atomicReferenceArray = this.f8775e;
            int length = atomicReferenceArray.length();
            if (length >= 1073741824) {
                return;
            }
            int i2 = this.f8772b;
            AtomicReferenceArray q2 = q(length << 1);
            this.f8774d = (q2.length() * 3) / 4;
            int length2 = q2.length() - 1;
            for (int i3 = 0; i3 < length; i3++) {
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i3);
                if (internalEntry != null) {
                    InternalEntry next = internalEntry.getNext();
                    int hash = internalEntry.getHash() & length2;
                    if (next == null) {
                        q2.set(hash, internalEntry);
                    } else {
                        InternalEntry internalEntry2 = internalEntry;
                        while (next != null) {
                            int hash2 = next.getHash() & length2;
                            if (hash2 != hash) {
                                internalEntry2 = next;
                                hash = hash2;
                            }
                            next = next.getNext();
                        }
                        q2.set(hash, internalEntry2);
                        while (internalEntry != internalEntry2) {
                            int hash3 = internalEntry.getHash() & length2;
                            InternalEntry d2 = d(internalEntry, (InternalEntry) q2.get(hash3));
                            if (d2 != null) {
                                q2.set(hash3, d2);
                            } else {
                                i2--;
                            }
                            internalEntry = internalEntry.getNext();
                        }
                    }
                }
            }
            this.f8775e = q2;
            this.f8772b = i2;
        }

        Object h(Object obj, int i2) {
            try {
                InternalEntry k2 = k(obj, i2);
                if (k2 == null) {
                    return null;
                }
                Object value = k2.getValue();
                if (value == null) {
                    F();
                }
                return value;
            } finally {
                r();
            }
        }

        InternalEntry i(Object obj, int i2) {
            if (this.f8772b != 0) {
                for (InternalEntry j2 = j(i2); j2 != null; j2 = j2.getNext()) {
                    if (j2.getHash() == i2) {
                        Object key = j2.getKey();
                        if (key == null) {
                            F();
                        } else if (this.f8771a.f8745e.equivalent(obj, key)) {
                            return j2;
                        }
                    }
                }
                return null;
            }
            return null;
        }

        InternalEntry j(int i2) {
            AtomicReferenceArray atomicReferenceArray = this.f8775e;
            return (InternalEntry) atomicReferenceArray.get(i2 & (atomicReferenceArray.length() - 1));
        }

        InternalEntry k(Object obj, int i2) {
            return i(obj, i2);
        }

        @NullableDecl
        Object l(InternalEntry internalEntry) {
            if (internalEntry.getKey() == null) {
                F();
                return null;
            }
            Object value = internalEntry.getValue();
            if (value == null) {
                F();
                return null;
            }
            return value;
        }

        void m(AtomicReferenceArray atomicReferenceArray) {
            int length = (atomicReferenceArray.length() * 3) / 4;
            this.f8774d = length;
            if (length == this.f8776f) {
                this.f8774d = length + 1;
            }
            this.f8775e = atomicReferenceArray;
        }

        void o() {
        }

        @GuardedBy("this")
        void p() {
        }

        AtomicReferenceArray q(int i2) {
            return new AtomicReferenceArray(i2);
        }

        void r() {
            if ((this.f8777g.incrementAndGet() & 63) == 0) {
                B();
            }
        }

        @GuardedBy("this")
        void s() {
            C();
        }

        /* JADX WARN: Multi-variable type inference failed */
        Object t(Object obj, int i2, Object obj2, boolean z) {
            lock();
            try {
                s();
                int i3 = this.f8772b + 1;
                if (i3 > this.f8774d) {
                    g();
                    i3 = this.f8772b + 1;
                }
                AtomicReferenceArray atomicReferenceArray = this.f8775e;
                int length = (atomicReferenceArray.length() - 1) & i2;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry internalEntry2 = internalEntry; internalEntry2 != null; internalEntry2 = internalEntry2.getNext()) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() == i2 && key != null && this.f8771a.f8745e.equivalent(obj, key)) {
                        Object value = internalEntry2.getValue();
                        if (value == null) {
                            this.f8773c++;
                            E(internalEntry2, obj2);
                            this.f8772b = this.f8772b;
                            return null;
                        } else if (z) {
                            return value;
                        } else {
                            this.f8773c++;
                            E(internalEntry2, obj2);
                            return value;
                        }
                    }
                }
                this.f8773c++;
                InternalEntry newEntry = this.f8771a.f8746f.newEntry(D(), obj, i2, internalEntry);
                E(newEntry, obj2);
                atomicReferenceArray.set(length, newEntry);
                this.f8772b = i3;
                return null;
            } finally {
                unlock();
            }
        }

        @CanIgnoreReturnValue
        boolean u(InternalEntry internalEntry, int i2) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.f8775e;
                int length = i2 & (atomicReferenceArray.length() - 1);
                InternalEntry internalEntry2 = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry internalEntry3 = internalEntry2; internalEntry3 != null; internalEntry3 = internalEntry3.getNext()) {
                    if (internalEntry3 == internalEntry) {
                        this.f8773c++;
                        atomicReferenceArray.set(length, y(internalEntry2, internalEntry3));
                        this.f8772b--;
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        @CanIgnoreReturnValue
        boolean v(Object obj, int i2, WeakValueReference weakValueReference) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.f8775e;
                int length = (atomicReferenceArray.length() - 1) & i2;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry internalEntry2 = internalEntry; internalEntry2 != null; internalEntry2 = internalEntry2.getNext()) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() == i2 && key != null && this.f8771a.f8745e.equivalent(obj, key)) {
                        if (((WeakValueEntry) internalEntry2).getValueReference() == weakValueReference) {
                            this.f8773c++;
                            atomicReferenceArray.set(length, y(internalEntry, internalEntry2));
                            this.f8772b--;
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        @CanIgnoreReturnValue
        Object w(Object obj, int i2) {
            lock();
            try {
                s();
                AtomicReferenceArray atomicReferenceArray = this.f8775e;
                int length = (atomicReferenceArray.length() - 1) & i2;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry internalEntry2 = internalEntry; internalEntry2 != null; internalEntry2 = internalEntry2.getNext()) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() == i2 && key != null && this.f8771a.f8745e.equivalent(obj, key)) {
                        Object value = internalEntry2.getValue();
                        if (value == null && !n(internalEntry2)) {
                            return null;
                        }
                        this.f8773c++;
                        atomicReferenceArray.set(length, y(internalEntry, internalEntry2));
                        this.f8772b--;
                        return value;
                    }
                }
                return null;
            } finally {
                unlock();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
            if (r8.f8771a.n().equivalent(r11, r4.getValue()) == false) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
            r5 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
            if (n(r4) == false) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
            r8.f8773c++;
            r0.set(r1, y(r3, r4));
            r8.f8772b--;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
            return r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
            return false;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        boolean x(Object obj, int i2, Object obj2) {
            lock();
            try {
                s();
                AtomicReferenceArray atomicReferenceArray = this.f8775e;
                int length = (atomicReferenceArray.length() - 1) & i2;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (true) {
                    boolean z = false;
                    if (internalEntry2 == null) {
                        return false;
                    }
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() == i2 && key != null && this.f8771a.f8745e.equivalent(obj, key)) {
                        break;
                    }
                    internalEntry2 = internalEntry2.getNext();
                }
            } finally {
                unlock();
            }
        }

        @GuardedBy("this")
        InternalEntry y(InternalEntry internalEntry, InternalEntry internalEntry2) {
            int i2 = this.f8772b;
            InternalEntry next = internalEntry2.getNext();
            while (internalEntry != internalEntry2) {
                InternalEntry d2 = d(internalEntry, next);
                if (d2 != null) {
                    next = d2;
                } else {
                    i2--;
                }
                internalEntry = internalEntry.getNext();
            }
            this.f8772b = i2;
            return next;
        }

        Object z(Object obj, int i2, Object obj2) {
            lock();
            try {
                s();
                AtomicReferenceArray atomicReferenceArray = this.f8775e;
                int length = (atomicReferenceArray.length() - 1) & i2;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry internalEntry2 = internalEntry; internalEntry2 != null; internalEntry2 = internalEntry2.getNext()) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() == i2 && key != null && this.f8771a.f8745e.equivalent(obj, key)) {
                        Object value = internalEntry2.getValue();
                        if (value != null) {
                            this.f8773c++;
                            E(internalEntry2, obj2);
                            return value;
                        }
                        if (n(internalEntry2)) {
                            this.f8773c++;
                            atomicReferenceArray.set(length, y(internalEntry, internalEntry2));
                            this.f8772b--;
                        }
                        return null;
                    }
                }
                return null;
            } finally {
                unlock();
            }
        }
    }

    /* loaded from: classes2.dex */
    private static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.f8754e = e(objectInputStream).makeMap();
            d(objectInputStream);
        }

        private Object readResolve() {
            return this.f8754e;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            f(objectOutputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum Strength {
        STRONG { // from class: com.google.common.collect.MapMakerInternalMap.Strength.1
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            Equivalence<Object> a() {
                return Equivalence.equals();
            }
        },
        WEAK { // from class: com.google.common.collect.MapMakerInternalMap.Strength.2
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            Equivalence<Object> a() {
                return Equivalence.identity();
            }
        };

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Equivalence a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class StrongKeyDummyValueEntry<K> extends AbstractStrongKeyEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> {

        /* loaded from: classes2.dex */
        static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            Helper() {
            }

            static Helper a() {
                return INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry copy(Segment segment, InternalEntry internalEntry, @NullableDecl InternalEntry internalEntry2) {
                return copy((StrongKeyDummyValueSegment) ((StrongKeyDummyValueSegment) segment), (StrongKeyDummyValueEntry) ((StrongKeyDummyValueEntry) internalEntry), (StrongKeyDummyValueEntry) ((StrongKeyDummyValueEntry) internalEntry2));
            }

            public StrongKeyDummyValueEntry<K> copy(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, @NullableDecl StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry2) {
                return strongKeyDummyValueEntry.a(strongKeyDummyValueEntry2);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.STRONG;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
                return newEntry((StrongKeyDummyValueSegment<StrongKeyDummyValueSegment<K>>) segment, (StrongKeyDummyValueSegment<K>) obj, i2, (StrongKeyDummyValueEntry<StrongKeyDummyValueSegment<K>>) internalEntry);
            }

            public StrongKeyDummyValueEntry<K> newEntry(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, K k2, int i2, @NullableDecl StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
                return new StrongKeyDummyValueEntry<>(k2, i2, strongKeyDummyValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i2, int i3) {
                return new StrongKeyDummyValueSegment<>(mapMakerInternalMap, i2, i3);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, MapMaker.Dummy dummy) {
                setValue((StrongKeyDummyValueSegment) ((StrongKeyDummyValueSegment) segment), (StrongKeyDummyValueEntry) ((StrongKeyDummyValueEntry) internalEntry), dummy);
            }

            public void setValue(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }
        }

        StrongKeyDummyValueEntry(Object obj, int i2, @NullableDecl StrongKeyDummyValueEntry strongKeyDummyValueEntry) {
            super(obj, i2, strongKeyDummyValueEntry);
        }

        StrongKeyDummyValueEntry a(StrongKeyDummyValueEntry strongKeyDummyValueEntry) {
            return new StrongKeyDummyValueEntry(this.f8755a, this.f8756b, strongKeyDummyValueEntry);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class StrongKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
        StrongKeyDummyValueSegment(MapMakerInternalMap mapMakerInternalMap, int i2, int i3) {
            super(mapMakerInternalMap, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        /* renamed from: G */
        public StrongKeyDummyValueSegment D() {
            return this;
        }

        public StrongKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (StrongKeyDummyValueEntry) internalEntry;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class StrongKeyStrongValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, StrongKeyStrongValueEntry<K, V>> {
        @NullableDecl
        private volatile V value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static Helper a() {
                return INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry copy(Segment segment, InternalEntry internalEntry, @NullableDecl InternalEntry internalEntry2) {
                return copy((StrongKeyStrongValueSegment) ((StrongKeyStrongValueSegment) segment), (StrongKeyStrongValueEntry) ((StrongKeyStrongValueEntry) internalEntry), (StrongKeyStrongValueEntry) ((StrongKeyStrongValueEntry) internalEntry2));
            }

            public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, @NullableDecl StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry2) {
                return strongKeyStrongValueEntry.a(strongKeyStrongValueEntry2);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.STRONG;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
                return newEntry((StrongKeyStrongValueSegment<StrongKeyStrongValueSegment<K, V>, V>) segment, (StrongKeyStrongValueSegment<K, V>) obj, i2, (StrongKeyStrongValueEntry<StrongKeyStrongValueSegment<K, V>, V>) internalEntry);
            }

            public StrongKeyStrongValueEntry<K, V> newEntry(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, K k2, int i2, @NullableDecl StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
                return new StrongKeyStrongValueEntry<>(k2, i2, strongKeyStrongValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i2, int i3) {
                return new StrongKeyStrongValueSegment<>(mapMakerInternalMap, i2, i3);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((StrongKeyStrongValueSegment<K, StrongKeyStrongValueEntry<K, V>>) segment, (StrongKeyStrongValueEntry<K, StrongKeyStrongValueEntry<K, V>>) internalEntry, (StrongKeyStrongValueEntry<K, V>) obj);
            }

            public void setValue(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, V v) {
                strongKeyStrongValueEntry.b(v);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }
        }

        StrongKeyStrongValueEntry(Object obj, int i2, @NullableDecl StrongKeyStrongValueEntry strongKeyStrongValueEntry) {
            super(obj, i2, strongKeyStrongValueEntry);
            this.value = null;
        }

        StrongKeyStrongValueEntry a(StrongKeyStrongValueEntry strongKeyStrongValueEntry) {
            StrongKeyStrongValueEntry strongKeyStrongValueEntry2 = new StrongKeyStrongValueEntry(this.f8755a, this.f8756b, strongKeyStrongValueEntry);
            strongKeyStrongValueEntry2.value = this.value;
            return strongKeyStrongValueEntry2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void b(Object obj) {
            this.value = obj;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        @NullableDecl
        public V getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
        StrongKeyStrongValueSegment(MapMakerInternalMap mapMakerInternalMap, int i2, int i3) {
            super(mapMakerInternalMap, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        /* renamed from: G */
        public StrongKeyStrongValueSegment D() {
            return this;
        }

        public StrongKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyStrongValueEntry) internalEntry;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class StrongKeyWeakValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, StrongKeyWeakValueEntry<K, V>> {
        private volatile WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> valueReference;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static Helper a() {
                return INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry copy(Segment segment, InternalEntry internalEntry, @NullableDecl InternalEntry internalEntry2) {
                return copy((StrongKeyWeakValueSegment) ((StrongKeyWeakValueSegment) segment), (StrongKeyWeakValueEntry) ((StrongKeyWeakValueEntry) internalEntry), (StrongKeyWeakValueEntry) ((StrongKeyWeakValueEntry) internalEntry2));
            }

            public StrongKeyWeakValueEntry<K, V> copy(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, @NullableDecl StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2) {
                if (Segment.n(strongKeyWeakValueEntry)) {
                    return null;
                }
                return strongKeyWeakValueEntry.c(((StrongKeyWeakValueSegment) strongKeyWeakValueSegment).queueForValues, strongKeyWeakValueEntry2);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.STRONG;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
                return newEntry((StrongKeyWeakValueSegment<StrongKeyWeakValueSegment<K, V>, V>) segment, (StrongKeyWeakValueSegment<K, V>) obj, i2, (StrongKeyWeakValueEntry<StrongKeyWeakValueSegment<K, V>, V>) internalEntry);
            }

            public StrongKeyWeakValueEntry<K, V> newEntry(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, K k2, int i2, @NullableDecl StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
                return new StrongKeyWeakValueEntry<>(k2, i2, strongKeyWeakValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i2, int i3) {
                return new StrongKeyWeakValueSegment<>(mapMakerInternalMap, i2, i3);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((StrongKeyWeakValueSegment<K, StrongKeyWeakValueEntry<K, V>>) segment, (StrongKeyWeakValueEntry<K, StrongKeyWeakValueEntry<K, V>>) internalEntry, (StrongKeyWeakValueEntry<K, V>) obj);
            }

            public void setValue(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, V v) {
                strongKeyWeakValueEntry.d(v, ((StrongKeyWeakValueSegment) strongKeyWeakValueSegment).queueForValues);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.WEAK;
            }
        }

        StrongKeyWeakValueEntry(Object obj, int i2, @NullableDecl StrongKeyWeakValueEntry strongKeyWeakValueEntry) {
            super(obj, i2, strongKeyWeakValueEntry);
            this.valueReference = MapMakerInternalMap.m();
        }

        StrongKeyWeakValueEntry c(ReferenceQueue referenceQueue, StrongKeyWeakValueEntry strongKeyWeakValueEntry) {
            StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2 = new StrongKeyWeakValueEntry<>(this.f8755a, this.f8756b, strongKeyWeakValueEntry);
            strongKeyWeakValueEntry2.valueReference = this.valueReference.copyFor(referenceQueue, strongKeyWeakValueEntry2);
            return strongKeyWeakValueEntry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public void clearValue() {
            this.valueReference.clear();
        }

        void d(Object obj, ReferenceQueue referenceQueue) {
            WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> weakValueReference = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(referenceQueue, obj, this);
            weakValueReference.clear();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public V getValue() {
            return this.valueReference.get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
        private final ReferenceQueue<V> queueForValues;

        StrongKeyWeakValueSegment(MapMakerInternalMap mapMakerInternalMap, int i2, int i3) {
            super(mapMakerInternalMap, i2, i3);
            this.queueForValues = new ReferenceQueue<>();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        /* renamed from: H */
        public StrongKeyWeakValueSegment D() {
            return this;
        }

        public StrongKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyWeakValueEntry) internalEntry;
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            return new WeakValueReferenceImpl(this.queueForValues, v, castForTesting((InternalEntry) internalEntry));
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void o() {
            b(this.queueForValues);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void p() {
            f(this.queueForValues);
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            StrongKeyWeakValueEntry<K, V> castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference weakValueReference2 = ((StrongKeyWeakValueEntry) castForTesting).valueReference;
            ((StrongKeyWeakValueEntry) castForTesting).valueReference = weakValueReference;
            weakValueReference2.clear();
        }
    }

    /* loaded from: classes2.dex */
    interface StrongValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
    }

    /* loaded from: classes2.dex */
    final class ValueIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<V> {
        ValueIterator(MapMakerInternalMap mapMakerInternalMap) {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public V next() {
            return (V) c().getValue();
        }
    }

    /* loaded from: classes2.dex */
    final class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator(MapMakerInternalMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return MapMakerInternalMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) MapMakerInternalMap.toArrayList(this).toArray(tArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WeakKeyDummyValueEntry<K> extends AbstractWeakKeyEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> {

        /* loaded from: classes2.dex */
        static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            Helper() {
            }

            static Helper a() {
                return INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry copy(Segment segment, InternalEntry internalEntry, @NullableDecl InternalEntry internalEntry2) {
                return copy((WeakKeyDummyValueSegment) ((WeakKeyDummyValueSegment) segment), (WeakKeyDummyValueEntry) ((WeakKeyDummyValueEntry) internalEntry), (WeakKeyDummyValueEntry) ((WeakKeyDummyValueEntry) internalEntry2));
            }

            public WeakKeyDummyValueEntry<K> copy(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, @NullableDecl WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry2) {
                if (weakKeyDummyValueEntry.getKey() == null) {
                    return null;
                }
                return weakKeyDummyValueEntry.a(((WeakKeyDummyValueSegment) weakKeyDummyValueSegment).queueForKeys, weakKeyDummyValueEntry2);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.WEAK;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
                return newEntry((WeakKeyDummyValueSegment<WeakKeyDummyValueSegment<K>>) segment, (WeakKeyDummyValueSegment<K>) obj, i2, (WeakKeyDummyValueEntry<WeakKeyDummyValueSegment<K>>) internalEntry);
            }

            public WeakKeyDummyValueEntry<K> newEntry(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, K k2, int i2, @NullableDecl WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
                return new WeakKeyDummyValueEntry<>(((WeakKeyDummyValueSegment) weakKeyDummyValueSegment).queueForKeys, k2, i2, weakKeyDummyValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i2, int i3) {
                return new WeakKeyDummyValueSegment<>(mapMakerInternalMap, i2, i3);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, MapMaker.Dummy dummy) {
                setValue((WeakKeyDummyValueSegment) ((WeakKeyDummyValueSegment) segment), (WeakKeyDummyValueEntry) ((WeakKeyDummyValueEntry) internalEntry), dummy);
            }

            public void setValue(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }
        }

        WeakKeyDummyValueEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl WeakKeyDummyValueEntry weakKeyDummyValueEntry) {
            super(referenceQueue, obj, i2, weakKeyDummyValueEntry);
        }

        WeakKeyDummyValueEntry a(ReferenceQueue referenceQueue, WeakKeyDummyValueEntry weakKeyDummyValueEntry) {
            return new WeakKeyDummyValueEntry(referenceQueue, getKey(), this.f8758a, weakKeyDummyValueEntry);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WeakKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
        private final ReferenceQueue<K> queueForKeys;

        WeakKeyDummyValueSegment(MapMakerInternalMap mapMakerInternalMap, int i2, int i3) {
            super(mapMakerInternalMap, i2, i3);
            this.queueForKeys = new ReferenceQueue<>();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        /* renamed from: H */
        public WeakKeyDummyValueSegment D() {
            return this;
        }

        public WeakKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (WeakKeyDummyValueEntry) internalEntry;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void o() {
            b(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void p() {
            e(this.queueForKeys);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WeakKeyStrongValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, WeakKeyStrongValueEntry<K, V>> {
        @NullableDecl
        private volatile V value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static Helper a() {
                return INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry copy(Segment segment, InternalEntry internalEntry, @NullableDecl InternalEntry internalEntry2) {
                return copy((WeakKeyStrongValueSegment) ((WeakKeyStrongValueSegment) segment), (WeakKeyStrongValueEntry) ((WeakKeyStrongValueEntry) internalEntry), (WeakKeyStrongValueEntry) ((WeakKeyStrongValueEntry) internalEntry2));
            }

            public WeakKeyStrongValueEntry<K, V> copy(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, @NullableDecl WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry2) {
                if (weakKeyStrongValueEntry.getKey() == null) {
                    return null;
                }
                return weakKeyStrongValueEntry.a(((WeakKeyStrongValueSegment) weakKeyStrongValueSegment).queueForKeys, weakKeyStrongValueEntry2);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.WEAK;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
                return newEntry((WeakKeyStrongValueSegment<WeakKeyStrongValueSegment<K, V>, V>) segment, (WeakKeyStrongValueSegment<K, V>) obj, i2, (WeakKeyStrongValueEntry<WeakKeyStrongValueSegment<K, V>, V>) internalEntry);
            }

            public WeakKeyStrongValueEntry<K, V> newEntry(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, K k2, int i2, @NullableDecl WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
                return new WeakKeyStrongValueEntry<>(((WeakKeyStrongValueSegment) weakKeyStrongValueSegment).queueForKeys, k2, i2, weakKeyStrongValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i2, int i3) {
                return new WeakKeyStrongValueSegment<>(mapMakerInternalMap, i2, i3);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((WeakKeyStrongValueSegment<K, WeakKeyStrongValueEntry<K, V>>) segment, (WeakKeyStrongValueEntry<K, WeakKeyStrongValueEntry<K, V>>) internalEntry, (WeakKeyStrongValueEntry<K, V>) obj);
            }

            public void setValue(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, V v) {
                weakKeyStrongValueEntry.b(v);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }
        }

        WeakKeyStrongValueEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl WeakKeyStrongValueEntry weakKeyStrongValueEntry) {
            super(referenceQueue, obj, i2, weakKeyStrongValueEntry);
            this.value = null;
        }

        WeakKeyStrongValueEntry a(ReferenceQueue referenceQueue, WeakKeyStrongValueEntry weakKeyStrongValueEntry) {
            WeakKeyStrongValueEntry weakKeyStrongValueEntry2 = new WeakKeyStrongValueEntry(referenceQueue, getKey(), this.f8758a, weakKeyStrongValueEntry);
            weakKeyStrongValueEntry2.b(this.value);
            return weakKeyStrongValueEntry2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void b(Object obj) {
            this.value = obj;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        @NullableDecl
        public V getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
        private final ReferenceQueue<K> queueForKeys;

        WeakKeyStrongValueSegment(MapMakerInternalMap mapMakerInternalMap, int i2, int i3) {
            super(mapMakerInternalMap, i2, i3);
            this.queueForKeys = new ReferenceQueue<>();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        /* renamed from: H */
        public WeakKeyStrongValueSegment D() {
            return this;
        }

        public WeakKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyStrongValueEntry) internalEntry;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void o() {
            b(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void p() {
            e(this.queueForKeys);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WeakKeyWeakValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, WeakKeyWeakValueEntry<K, V>> {
        private volatile WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> valueReference;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static Helper a() {
                return INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry copy(Segment segment, InternalEntry internalEntry, @NullableDecl InternalEntry internalEntry2) {
                return copy((WeakKeyWeakValueSegment) ((WeakKeyWeakValueSegment) segment), (WeakKeyWeakValueEntry) ((WeakKeyWeakValueEntry) internalEntry), (WeakKeyWeakValueEntry) ((WeakKeyWeakValueEntry) internalEntry2));
            }

            public WeakKeyWeakValueEntry<K, V> copy(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, @NullableDecl WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2) {
                if (weakKeyWeakValueEntry.getKey() == null || Segment.n(weakKeyWeakValueEntry)) {
                    return null;
                }
                return weakKeyWeakValueEntry.c(((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForKeys, ((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForValues, weakKeyWeakValueEntry2);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.WEAK;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i2, @NullableDecl InternalEntry internalEntry) {
                return newEntry((WeakKeyWeakValueSegment<WeakKeyWeakValueSegment<K, V>, V>) segment, (WeakKeyWeakValueSegment<K, V>) obj, i2, (WeakKeyWeakValueEntry<WeakKeyWeakValueSegment<K, V>, V>) internalEntry);
            }

            public WeakKeyWeakValueEntry<K, V> newEntry(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, K k2, int i2, @NullableDecl WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
                return new WeakKeyWeakValueEntry<>(((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForKeys, k2, i2, weakKeyWeakValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i2, int i3) {
                return new WeakKeyWeakValueSegment<>(mapMakerInternalMap, i2, i3);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((WeakKeyWeakValueSegment<K, WeakKeyWeakValueEntry<K, V>>) segment, (WeakKeyWeakValueEntry<K, WeakKeyWeakValueEntry<K, V>>) internalEntry, (WeakKeyWeakValueEntry<K, V>) obj);
            }

            public void setValue(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, V v) {
                weakKeyWeakValueEntry.d(v, ((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForValues);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.WEAK;
            }
        }

        WeakKeyWeakValueEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl WeakKeyWeakValueEntry weakKeyWeakValueEntry) {
            super(referenceQueue, obj, i2, weakKeyWeakValueEntry);
            this.valueReference = MapMakerInternalMap.m();
        }

        WeakKeyWeakValueEntry c(ReferenceQueue referenceQueue, ReferenceQueue referenceQueue2, WeakKeyWeakValueEntry weakKeyWeakValueEntry) {
            WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2 = new WeakKeyWeakValueEntry<>(referenceQueue, getKey(), this.f8758a, weakKeyWeakValueEntry);
            weakKeyWeakValueEntry2.valueReference = this.valueReference.copyFor(referenceQueue2, weakKeyWeakValueEntry2);
            return weakKeyWeakValueEntry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public void clearValue() {
            this.valueReference.clear();
        }

        void d(Object obj, ReferenceQueue referenceQueue) {
            WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> weakValueReference = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(referenceQueue, obj, this);
            weakValueReference.clear();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public V getValue() {
            return this.valueReference.get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
        private final ReferenceQueue<K> queueForKeys;
        private final ReferenceQueue<V> queueForValues;

        WeakKeyWeakValueSegment(MapMakerInternalMap mapMakerInternalMap, int i2, int i3) {
            super(mapMakerInternalMap, i2, i3);
            this.queueForKeys = new ReferenceQueue<>();
            this.queueForValues = new ReferenceQueue<>();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        /* renamed from: I */
        public WeakKeyWeakValueSegment D() {
            return this;
        }

        public WeakKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyWeakValueEntry) internalEntry;
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            return new WeakValueReferenceImpl(this.queueForValues, v, castForTesting((InternalEntry) internalEntry));
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void o() {
            b(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        void p() {
            e(this.queueForKeys);
            f(this.queueForValues);
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            WeakKeyWeakValueEntry<K, V> castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference weakValueReference2 = ((WeakKeyWeakValueEntry) castForTesting).valueReference;
            ((WeakKeyWeakValueEntry) castForTesting).valueReference = weakValueReference;
            weakValueReference2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface WeakValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
        void clearValue();

        WeakValueReference<K, V, E> getValueReference();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface WeakValueReference<K, V, E extends InternalEntry<K, V, E>> {
        void clear();

        WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e2);

        @NullableDecl
        V get();

        E getEntry();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WeakValueReferenceImpl<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<V> implements WeakValueReference<K, V, E> {
        @Weak

        /* renamed from: a  reason: collision with root package name */
        final InternalEntry f8779a;

        WeakValueReferenceImpl(ReferenceQueue referenceQueue, Object obj, InternalEntry internalEntry) {
            super(obj, referenceQueue);
            this.f8779a = internalEntry;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e2) {
            return new WeakValueReferenceImpl(referenceQueue, get(), e2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public E getEntry() {
            return (E) this.f8779a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class WriteThroughEntry extends AbstractMapEntry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8780a;

        /* renamed from: b  reason: collision with root package name */
        Object f8781b;

        WriteThroughEntry(Object obj, Object obj2) {
            this.f8780a = obj;
            this.f8781b = obj2;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return this.f8780a.equals(entry.getKey()) && this.f8781b.equals(entry.getValue());
            }
            return false;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return (K) this.f8780a;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return (V) this.f8781b;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public int hashCode() {
            return this.f8780a.hashCode() ^ this.f8781b.hashCode();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) MapMakerInternalMap.this.put(this.f8780a, v);
            this.f8781b = v;
            return v2;
        }
    }

    private MapMakerInternalMap(MapMaker mapMaker, InternalEntryHelper<K, V, E, S> internalEntryHelper) {
        this.f8744d = Math.min(mapMaker.a(), 65536);
        this.f8745e = mapMaker.c();
        this.f8746f = internalEntryHelper;
        int min = Math.min(mapMaker.b(), 1073741824);
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        int i5 = 1;
        while (i5 < this.f8744d) {
            i4++;
            i5 <<= 1;
        }
        this.f8742b = 32 - i4;
        this.f8741a = i5 - 1;
        this.f8743c = h(i5);
        int i6 = min / i5;
        while (i3 < (i5 * i6 < min ? i6 + 1 : i6)) {
            i3 <<= 1;
        }
        while (true) {
            Segment[] segmentArr = this.f8743c;
            if (i2 >= segmentArr.length) {
                return;
            }
            segmentArr[i2] = c(i3, -1);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MapMakerInternalMap b(MapMaker mapMaker) {
        Strength d2 = mapMaker.d();
        Strength strength = Strength.STRONG;
        if (d2 == strength && mapMaker.e() == strength) {
            return new MapMakerInternalMap(mapMaker, StrongKeyStrongValueEntry.Helper.a());
        }
        if (mapMaker.d() == strength && mapMaker.e() == Strength.WEAK) {
            return new MapMakerInternalMap(mapMaker, StrongKeyWeakValueEntry.Helper.a());
        }
        Strength d3 = mapMaker.d();
        Strength strength2 = Strength.WEAK;
        if (d3 == strength2 && mapMaker.e() == strength) {
            return new MapMakerInternalMap(mapMaker, WeakKeyStrongValueEntry.Helper.a());
        }
        if (mapMaker.d() == strength2 && mapMaker.e() == strength2) {
            return new MapMakerInternalMap(mapMaker, WeakKeyWeakValueEntry.Helper.a());
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MapMakerInternalMap d(MapMaker mapMaker) {
        Strength d2 = mapMaker.d();
        Strength strength = Strength.STRONG;
        if (d2 == strength && mapMaker.e() == strength) {
            return new MapMakerInternalMap(mapMaker, StrongKeyDummyValueEntry.Helper.a());
        }
        Strength d3 = mapMaker.d();
        Strength strength2 = Strength.WEAK;
        if (d3 == strength2 && mapMaker.e() == strength) {
            return new MapMakerInternalMap(mapMaker, WeakKeyDummyValueEntry.Helper.a());
        }
        if (mapMaker.e() == strength2) {
            throw new IllegalArgumentException("Map cannot have both weak and dummy values");
        }
        throw new AssertionError();
    }

    static int k(int i2) {
        int i3 = i2 + ((i2 << 15) ^ (-12931));
        int i4 = i3 ^ (i3 >>> 10);
        int i5 = i4 + (i4 << 3);
        int i6 = i5 ^ (i5 >>> 6);
        int i7 = i6 + (i6 << 2) + (i6 << 14);
        return i7 ^ (i7 >>> 16);
    }

    static WeakValueReference m() {
        return f8740j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    Segment c(int i2, int i3) {
        return this.f8746f.newSegment(this, i2, i3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment segment : this.f8743c) {
            segment.a();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        if (obj == null) {
            return false;
        }
        int g2 = g(obj);
        return l(g2).c(obj, g2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.util.concurrent.atomic.AtomicReferenceArray] */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        Segment[] segmentArr = this.f8743c;
        long j2 = -1;
        int i2 = 0;
        while (i2 < 3) {
            long j3 = 0;
            int length = segmentArr.length;
            for (int i3 = z; i3 < length; i3++) {
                Segment segment = segmentArr[i3];
                int i4 = segment.f8772b;
                ?? r12 = segment.f8775e;
                for (int i5 = z; i5 < r12.length(); i5++) {
                    for (InternalEntry internalEntry = (InternalEntry) r12.get(i5); internalEntry != null; internalEntry = internalEntry.getNext()) {
                        Object l2 = segment.l(internalEntry);
                        if (l2 != null && n().equivalent(obj, l2)) {
                            return true;
                        }
                    }
                }
                j3 += segment.f8773c;
                z = false;
            }
            if (j3 == j2) {
                return false;
            }
            i2++;
            j2 = j3;
            z = false;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalEntry e(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int g2 = g(obj);
        return l(g2).i(obj, g2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.f8749i;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.f8749i = entrySet;
        return entrySet;
    }

    Object f(InternalEntry internalEntry) {
        if (internalEntry.getKey() == null) {
            return null;
        }
        return internalEntry.getValue();
    }

    int g(Object obj) {
        return k(this.f8745e.hash(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int g2 = g(obj);
        return (V) l(g2).h(obj, g2);
    }

    final Segment[] h(int i2) {
        return new Segment[i2];
    }

    void i(InternalEntry internalEntry) {
        int hash = internalEntry.getHash();
        l(hash).u(internalEntry, hash);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment[] segmentArr = this.f8743c;
        long j2 = 0;
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].f8772b != 0) {
                return false;
            }
            j2 += segmentArr[i2].f8773c;
        }
        if (j2 != 0) {
            for (int i3 = 0; i3 < segmentArr.length; i3++) {
                if (segmentArr[i3].f8772b != 0) {
                    return false;
                }
                j2 -= segmentArr[i3].f8773c;
            }
            return j2 == 0;
        }
        return true;
    }

    void j(WeakValueReference weakValueReference) {
        InternalEntry entry = weakValueReference.getEntry();
        int hash = entry.getHash();
        l(hash).v(entry.getKey(), hash, weakValueReference);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.f8747g;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.f8747g = keySet;
        return keySet;
    }

    Segment l(int i2) {
        return this.f8743c[(i2 >>> this.f8742b) & this.f8741a];
    }

    @VisibleForTesting
    Equivalence n() {
        return this.f8746f.valueStrength().a();
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V put(K k2, V v) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v);
        int g2 = g(k2);
        return (V) l(g2).t(k2, g2, v, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public V putIfAbsent(K k2, V v) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v);
        int g2 = g(k2);
        return (V) l(g2).t(k2, g2, v, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V remove(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int g2 = g(obj);
        return (V) l(g2).w(obj, g2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int g2 = g(obj);
        return l(g2).x(obj, g2, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public V replace(K k2, V v) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v);
        int g2 = g(k2);
        return (V) l(g2).z(k2, g2, v);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public boolean replace(K k2, @NullableDecl V v, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        if (v == null) {
            return false;
        }
        int g2 = g(k2);
        return l(g2).A(k2, g2, v, v2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long j2 = 0;
        for (Segment segment : this.f8743c) {
            j2 += segment.f8772b;
        }
        return Ints.saturatedCast(j2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.f8748h;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.f8748h = values;
        return values;
    }
}
