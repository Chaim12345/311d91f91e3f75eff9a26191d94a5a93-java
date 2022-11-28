package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final Logger w = Logger.getLogger(LocalCache.class.getName());
    static final ValueReference x = new ValueReference<Object, Object>() { // from class: com.google.common.cache.LocalCache.1
        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, @NullableDecl Object obj, ReferenceEntry<Object, Object> referenceEntry) {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public Object get() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<Object, Object> getEntry() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 0;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(Object obj) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public Object waitForValue() {
            return null;
        }
    };
    static final Queue y = new AbstractQueue<Object>() { // from class: com.google.common.cache.LocalCache.2
        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return ImmutableSet.of().iterator();
        }

        @Override // java.util.Queue
        public boolean offer(Object obj) {
            return true;
        }

        @Override // java.util.Queue
        public Object peek() {
            return null;
        }

        @Override // java.util.Queue
        public Object poll() {
            return null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final int f8235a;

    /* renamed from: b  reason: collision with root package name */
    final int f8236b;

    /* renamed from: c  reason: collision with root package name */
    final Segment[] f8237c;

    /* renamed from: d  reason: collision with root package name */
    final int f8238d;

    /* renamed from: e  reason: collision with root package name */
    final Equivalence f8239e;

    /* renamed from: f  reason: collision with root package name */
    final Equivalence f8240f;

    /* renamed from: g  reason: collision with root package name */
    final Strength f8241g;

    /* renamed from: h  reason: collision with root package name */
    final Strength f8242h;

    /* renamed from: i  reason: collision with root package name */
    final long f8243i;

    /* renamed from: j  reason: collision with root package name */
    final Weigher f8244j;

    /* renamed from: k  reason: collision with root package name */
    final long f8245k;

    /* renamed from: l  reason: collision with root package name */
    final long f8246l;

    /* renamed from: m  reason: collision with root package name */
    final long f8247m;

    /* renamed from: n  reason: collision with root package name */
    final Queue f8248n;

    /* renamed from: o  reason: collision with root package name */
    final RemovalListener f8249o;

    /* renamed from: p  reason: collision with root package name */
    final Ticker f8250p;

    /* renamed from: q  reason: collision with root package name */
    final EntryFactory f8251q;

    /* renamed from: r  reason: collision with root package name */
    final AbstractCache.StatsCounter f8252r;
    @NullableDecl

    /* renamed from: s  reason: collision with root package name */
    final CacheLoader f8253s;
    @NullableDecl

    /* renamed from: t  reason: collision with root package name */
    Set f8254t;
    @NullableDecl
    Collection u;
    @NullableDecl
    Set v;

    /* loaded from: classes2.dex */
    abstract class AbstractCacheSet<T> extends AbstractSet<T> {
        @Weak

        /* renamed from: a  reason: collision with root package name */
        final ConcurrentMap f8255a;

        AbstractCacheSet(LocalCache localCache, ConcurrentMap concurrentMap) {
            this.f8255a = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.f8255a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.f8255a.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.f8255a.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    /* loaded from: classes2.dex */
    static abstract class AbstractReferenceEntry<K, V> implements ReferenceEntry<K, V> {
        AbstractReferenceEntry() {
        }

        @Override // com.google.common.cache.ReferenceEntry
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setAccessTime(long j2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setWriteTime(long j2) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class AccessQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {

        /* renamed from: a  reason: collision with root package name */
        final ReferenceEntry f8256a = new AbstractReferenceEntry<Object, Object>(this) { // from class: com.google.common.cache.LocalCache.AccessQueue.1

            /* renamed from: a  reason: collision with root package name */
            ReferenceEntry<Object, Object> f8257a = this;

            /* renamed from: b  reason: collision with root package name */
            ReferenceEntry<Object, Object> f8258b = this;

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public long getAccessTime() {
                return LongCompanionObject.MAX_VALUE;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public ReferenceEntry<Object, Object> getNextInAccessQueue() {
                return this.f8257a;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
                return this.f8258b;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public void setAccessTime(long j2) {
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public void setNextInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
                this.f8257a = referenceEntry;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
                this.f8258b = referenceEntry;
            }
        };

        AccessQueue() {
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ReferenceEntry<K, V> nextInAccessQueue = this.f8256a.getNextInAccessQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.f8256a;
                if (nextInAccessQueue == referenceEntry) {
                    referenceEntry.setNextInAccessQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.f8256a;
                    referenceEntry2.setPreviousInAccessQueue(referenceEntry2);
                    return;
                }
                ReferenceEntry<K, V> nextInAccessQueue2 = nextInAccessQueue.getNextInAccessQueue();
                LocalCache.v(nextInAccessQueue);
                nextInAccessQueue = nextInAccessQueue2;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInAccessQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f8256a.getNextInAccessQueue() == this.f8256a;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) { // from class: com.google.common.cache.LocalCache.AccessQueue.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractSequentialIterator
                /* renamed from: b */
                public ReferenceEntry a(ReferenceEntry referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
                    if (nextInAccessQueue == AccessQueue.this.f8256a) {
                        return null;
                    }
                    return nextInAccessQueue;
                }
            };
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.b(referenceEntry.getPreviousInAccessQueue(), referenceEntry.getNextInAccessQueue());
            LocalCache.b(this.f8256a.getPreviousInAccessQueue(), referenceEntry);
            LocalCache.b(referenceEntry, this.f8256a);
            return true;
        }

        @Override // java.util.Queue
        public /* bridge */ /* synthetic */ boolean offer(Object obj) {
            return offer((ReferenceEntry) ((ReferenceEntry) obj));
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInAccessQueue = this.f8256a.getNextInAccessQueue();
            if (nextInAccessQueue == this.f8256a) {
                return null;
            }
            return nextInAccessQueue;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInAccessQueue = this.f8256a.getNextInAccessQueue();
            if (nextInAccessQueue == this.f8256a) {
                return null;
            }
            remove(nextInAccessQueue);
            return nextInAccessQueue;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry<K, V> previousInAccessQueue = referenceEntry.getPreviousInAccessQueue();
            ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
            LocalCache.b(previousInAccessQueue, nextInAccessQueue);
            LocalCache.v(referenceEntry);
            return nextInAccessQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i2 = 0;
            for (ReferenceEntry<K, V> nextInAccessQueue = this.f8256a.getNextInAccessQueue(); nextInAccessQueue != this.f8256a; nextInAccessQueue = nextInAccessQueue.getNextInAccessQueue()) {
                i2++;
            }
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum EntryFactory {
        STRONG { // from class: com.google.common.cache.LocalCache.EntryFactory.1
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new StrongEntry(obj, i2, referenceEntry);
            }
        },
        STRONG_ACCESS { // from class: com.google.common.cache.LocalCache.EntryFactory.2
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, ReferenceEntry<Object, Object> referenceEntry2) {
                ReferenceEntry<Object, Object> b2 = super.b(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, b2);
                return b2;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new StrongAccessEntry(obj, i2, referenceEntry);
            }
        },
        STRONG_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.3
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, ReferenceEntry<Object, Object> referenceEntry2) {
                ReferenceEntry<Object, Object> b2 = super.b(segment, referenceEntry, referenceEntry2);
                c(referenceEntry, b2);
                return b2;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new StrongWriteEntry(obj, i2, referenceEntry);
            }
        },
        STRONG_ACCESS_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.4
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, ReferenceEntry<Object, Object> referenceEntry2) {
                ReferenceEntry<Object, Object> b2 = super.b(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, b2);
                c(referenceEntry, b2);
                return b2;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new StrongAccessWriteEntry(obj, i2, referenceEntry);
            }
        },
        WEAK { // from class: com.google.common.cache.LocalCache.EntryFactory.5
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new WeakEntry(segment.f8298h, obj, i2, referenceEntry);
            }
        },
        WEAK_ACCESS { // from class: com.google.common.cache.LocalCache.EntryFactory.6
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, ReferenceEntry<Object, Object> referenceEntry2) {
                ReferenceEntry<Object, Object> b2 = super.b(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, b2);
                return b2;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new WeakAccessEntry(segment.f8298h, obj, i2, referenceEntry);
            }
        },
        WEAK_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.7
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, ReferenceEntry<Object, Object> referenceEntry2) {
                ReferenceEntry<Object, Object> b2 = super.b(segment, referenceEntry, referenceEntry2);
                c(referenceEntry, b2);
                return b2;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new WeakWriteEntry(segment.f8298h, obj, i2, referenceEntry);
            }
        },
        WEAK_ACCESS_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.8
            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, ReferenceEntry<Object, Object> referenceEntry2) {
                ReferenceEntry<Object, Object> b2 = super.b(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, b2);
                c(referenceEntry, b2);
                return b2;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<Object, Object> e(Segment<Object, Object> segment, Object obj, int i2, @NullableDecl ReferenceEntry<Object, Object> referenceEntry) {
                return new WeakAccessWriteEntry(segment.f8298h, obj, i2, referenceEntry);
            }
        };
        

        /* renamed from: a  reason: collision with root package name */
        static final EntryFactory[] f8260a = {STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};

        /* JADX WARN: Multi-variable type inference failed */
        static EntryFactory d(Strength strength, boolean z, boolean z2) {
            return f8260a[(strength == Strength.WEAK ? 4 : 0) | (z ? 1 : 0) | (z2 ? 2 : 0)];
        }

        void a(ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2) {
            referenceEntry2.setAccessTime(referenceEntry.getAccessTime());
            LocalCache.b(referenceEntry.getPreviousInAccessQueue(), referenceEntry2);
            LocalCache.b(referenceEntry2, referenceEntry.getNextInAccessQueue());
            LocalCache.v(referenceEntry);
        }

        ReferenceEntry b(Segment segment, ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2) {
            return e(segment, referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry2);
        }

        void c(ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2) {
            referenceEntry2.setWriteTime(referenceEntry.getWriteTime());
            LocalCache.c(referenceEntry.getPreviousInWriteQueue(), referenceEntry2);
            LocalCache.c(referenceEntry2, referenceEntry.getNextInWriteQueue());
            LocalCache.w(referenceEntry);
        }

        abstract ReferenceEntry e(Segment segment, Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry);
    }

    /* loaded from: classes2.dex */
    final class EntryIterator extends LocalCache<K, V>.HashIterator<Map.Entry<K, V>> {
        EntryIterator(LocalCache localCache) {
            super();
        }

        @Override // com.google.common.cache.LocalCache.HashIterator, java.util.Iterator
        public Map.Entry<K, V> next() {
            return c();
        }
    }

    /* loaded from: classes2.dex */
    final class EntrySet extends LocalCache<K, V>.AbstractCacheSet<Map.Entry<K, V>> {
        EntrySet(ConcurrentMap concurrentMap) {
            super(LocalCache.this, concurrentMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = LocalCache.this.get(key)) != null && LocalCache.this.f8240f.equivalent(entry.getValue(), obj2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator(LocalCache.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && LocalCache.this.remove(key, entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public abstract class HashIterator<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        int f8262a;

        /* renamed from: b  reason: collision with root package name */
        int f8263b = -1;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Segment f8264c;
        @NullableDecl

        /* renamed from: d  reason: collision with root package name */
        AtomicReferenceArray f8265d;
        @NullableDecl

        /* renamed from: e  reason: collision with root package name */
        ReferenceEntry f8266e;
        @NullableDecl

        /* renamed from: f  reason: collision with root package name */
        WriteThroughEntry f8267f;
        @NullableDecl

        /* renamed from: g  reason: collision with root package name */
        WriteThroughEntry f8268g;

        HashIterator() {
            this.f8262a = LocalCache.this.f8237c.length - 1;
            a();
        }

        final void a() {
            this.f8267f = null;
            if (d() || e()) {
                return;
            }
            while (true) {
                int i2 = this.f8262a;
                if (i2 < 0) {
                    return;
                }
                Segment[] segmentArr = LocalCache.this.f8237c;
                this.f8262a = i2 - 1;
                Segment segment = segmentArr[i2];
                this.f8264c = segment;
                if (segment.f8292b != 0) {
                    AtomicReferenceArray atomicReferenceArray = this.f8264c.f8296f;
                    this.f8265d = atomicReferenceArray;
                    this.f8263b = atomicReferenceArray.length() - 1;
                    if (e()) {
                        return;
                    }
                }
            }
        }

        boolean b(ReferenceEntry referenceEntry) {
            boolean z;
            try {
                long read = LocalCache.this.f8250p.read();
                Object key = referenceEntry.getKey();
                Object m2 = LocalCache.this.m(referenceEntry, read);
                if (m2 != null) {
                    this.f8267f = new WriteThroughEntry(key, m2);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.f8264c.E();
            }
        }

        WriteThroughEntry c() {
            WriteThroughEntry writeThroughEntry = this.f8267f;
            if (writeThroughEntry != null) {
                this.f8268g = writeThroughEntry;
                a();
                return this.f8268g;
            }
            throw new NoSuchElementException();
        }

        boolean d() {
            ReferenceEntry referenceEntry = this.f8266e;
            if (referenceEntry == null) {
                return false;
            }
            while (true) {
                this.f8266e = referenceEntry.getNext();
                ReferenceEntry referenceEntry2 = this.f8266e;
                if (referenceEntry2 == null) {
                    return false;
                }
                if (b(referenceEntry2)) {
                    return true;
                }
                referenceEntry = this.f8266e;
            }
        }

        boolean e() {
            while (true) {
                int i2 = this.f8263b;
                if (i2 < 0) {
                    return false;
                }
                AtomicReferenceArray atomicReferenceArray = this.f8265d;
                this.f8263b = i2 - 1;
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(i2);
                this.f8266e = referenceEntry;
                if (referenceEntry != null && (b(referenceEntry) || d())) {
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f8267f != null;
        }

        @Override // java.util.Iterator
        public abstract T next();

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.f8268g != null);
            LocalCache.this.remove(this.f8268g.getKey());
            this.f8268g = null;
        }
    }

    /* loaded from: classes2.dex */
    final class KeyIterator extends LocalCache<K, V>.HashIterator<K> {
        KeyIterator(LocalCache localCache) {
            super();
        }

        @Override // com.google.common.cache.LocalCache.HashIterator, java.util.Iterator
        public K next() {
            return (K) c().getKey();
        }
    }

    /* loaded from: classes2.dex */
    final class KeySet extends LocalCache<K, V>.AbstractCacheSet<K> {
        KeySet(ConcurrentMap concurrentMap) {
            super(LocalCache.this, concurrentMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.f8255a.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator(LocalCache.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return this.f8255a.remove(obj) != null;
        }
    }

    /* loaded from: classes2.dex */
    static final class LoadingSerializationProxy<K, V> extends ManualSerializationProxy<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;
        @NullableDecl

        /* renamed from: n  reason: collision with root package name */
        transient LoadingCache f8271n;

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.f8271n = b().build(this.f8289l);
        }

        private Object readResolve() {
            return this.f8271n;
        }

        @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
        public final V apply(K k2) {
            return (V) this.f8271n.apply(k2);
        }

        @Override // com.google.common.cache.LoadingCache
        public V get(K k2) {
            return (V) this.f8271n.get(k2);
        }

        @Override // com.google.common.cache.LoadingCache
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
            return this.f8271n.getAll(iterable);
        }

        @Override // com.google.common.cache.LoadingCache
        public V getUnchecked(K k2) {
            return (V) this.f8271n.getUnchecked(k2);
        }

        @Override // com.google.common.cache.LoadingCache
        public void refresh(K k2) {
            this.f8271n.refresh(k2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class LoadingValueReference<K, V> implements ValueReference<K, V> {

        /* renamed from: a  reason: collision with root package name */
        volatile ValueReference f8272a;

        /* renamed from: b  reason: collision with root package name */
        final SettableFuture f8273b;

        /* renamed from: c  reason: collision with root package name */
        final Stopwatch f8274c;

        public LoadingValueReference() {
            this(LocalCache.H());
        }

        public LoadingValueReference(ValueReference<K, V> valueReference) {
            this.f8273b = SettableFuture.create();
            this.f8274c = Stopwatch.createUnstarted();
            this.f8272a = valueReference;
        }

        private ListenableFuture<V> fullyFailedFuture(Throwable th) {
            return Futures.immediateFailedFuture(th);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @NullableDecl V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public long elapsedNanos() {
            return this.f8274c.elapsed(TimeUnit.NANOSECONDS);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V get() {
            return (V) this.f8272a.get();
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> getOldValue() {
            return this.f8272a;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.f8272a.getWeight();
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return this.f8272a.isActive();
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ListenableFuture<V> loadFuture(K k2, CacheLoader<? super K, V> cacheLoader) {
            try {
                this.f8274c.start();
                Object obj = this.f8272a.get();
                if (obj == null) {
                    V load = cacheLoader.load(k2);
                    return set(load) ? this.f8273b : Futures.immediateFuture(load);
                }
                ListenableFuture reload = cacheLoader.reload(k2, obj);
                return reload == null ? Futures.immediateFuture(null) : Futures.transform(reload, new Function<V, V>() { // from class: com.google.common.cache.LocalCache.LoadingValueReference.1
                    @Override // com.google.common.base.Function
                    public V apply(V v) {
                        LoadingValueReference.this.set(v);
                        return v;
                    }
                }, MoreExecutors.directExecutor());
            } catch (Throwable th) {
                ListenableFuture<V> fullyFailedFuture = setException(th) ? this.f8273b : fullyFailedFuture(th);
                if (th instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return fullyFailedFuture;
            }
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(@NullableDecl V v) {
            if (v != null) {
                set(v);
            } else {
                this.f8272a = LocalCache.H();
            }
        }

        public boolean set(@NullableDecl V v) {
            return this.f8273b.set(v);
        }

        public boolean setException(Throwable th) {
            return this.f8273b.setException(th);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return (V) Uninterruptibles.getUninterruptibly(this.f8273b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LocalLoadingCache(CacheBuilder cacheBuilder, CacheLoader cacheLoader) {
            super();
        }

        @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
        public final V apply(K k2) {
            return getUnchecked(k2);
        }

        @Override // com.google.common.cache.LoadingCache
        public V get(K k2) {
            return (V) this.f8276a.n(k2);
        }

        @Override // com.google.common.cache.LoadingCache
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
            return this.f8276a.k(iterable);
        }

        @Override // com.google.common.cache.LoadingCache
        public V getUnchecked(K k2) {
            try {
                return get(k2);
            } catch (ExecutionException e2) {
                throw new UncheckedExecutionException(e2.getCause());
            }
        }

        @Override // com.google.common.cache.LoadingCache
        public void refresh(K k2) {
            this.f8276a.D(k2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
        private static final long serialVersionUID = 1;

        /* renamed from: a  reason: collision with root package name */
        final LocalCache f8276a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LocalManualCache(CacheBuilder cacheBuilder) {
            this(new LocalCache(cacheBuilder, null));
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.f8276a = localCache;
        }

        @Override // com.google.common.cache.Cache
        public ConcurrentMap<K, V> asMap() {
            return this.f8276a;
        }

        @Override // com.google.common.cache.Cache
        public void cleanUp() {
            this.f8276a.cleanUp();
        }

        @Override // com.google.common.cache.Cache
        public V get(K k2, final Callable<? extends V> callable) {
            Preconditions.checkNotNull(callable);
            return (V) this.f8276a.j(k2, new CacheLoader<Object, V>(this) { // from class: com.google.common.cache.LocalCache.LocalManualCache.1
                @Override // com.google.common.cache.CacheLoader
                public V load(Object obj) {
                    return (V) callable.call();
                }
            });
        }

        @Override // com.google.common.cache.Cache
        public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
            return this.f8276a.l(iterable);
        }

        @Override // com.google.common.cache.Cache
        @NullableDecl
        public V getIfPresent(Object obj) {
            return (V) this.f8276a.getIfPresent(obj);
        }

        @Override // com.google.common.cache.Cache
        public void invalidate(Object obj) {
            Preconditions.checkNotNull(obj);
            this.f8276a.remove(obj);
        }

        @Override // com.google.common.cache.Cache
        public void invalidateAll() {
            this.f8276a.clear();
        }

        @Override // com.google.common.cache.Cache
        public void invalidateAll(Iterable<?> iterable) {
            this.f8276a.p(iterable);
        }

        @Override // com.google.common.cache.Cache
        public void put(K k2, V v) {
            this.f8276a.put(k2, v);
        }

        @Override // com.google.common.cache.Cache
        public void putAll(Map<? extends K, ? extends V> map) {
            this.f8276a.putAll(map);
        }

        @Override // com.google.common.cache.Cache
        public long size() {
            return this.f8276a.s();
        }

        @Override // com.google.common.cache.Cache
        public CacheStats stats() {
            AbstractCache.SimpleStatsCounter simpleStatsCounter = new AbstractCache.SimpleStatsCounter();
            simpleStatsCounter.incrementBy(this.f8276a.f8252r);
            for (Segment segment : this.f8276a.f8237c) {
                simpleStatsCounter.incrementBy(segment.f8304n);
            }
            return simpleStatsCounter.snapshot();
        }
    }

    /* loaded from: classes2.dex */
    static class ManualSerializationProxy<K, V> extends ForwardingCache<K, V> implements Serializable {
        private static final long serialVersionUID = 1;

        /* renamed from: a  reason: collision with root package name */
        final Strength f8278a;

        /* renamed from: b  reason: collision with root package name */
        final Strength f8279b;

        /* renamed from: c  reason: collision with root package name */
        final Equivalence f8280c;

        /* renamed from: d  reason: collision with root package name */
        final Equivalence f8281d;

        /* renamed from: e  reason: collision with root package name */
        final long f8282e;

        /* renamed from: f  reason: collision with root package name */
        final long f8283f;

        /* renamed from: g  reason: collision with root package name */
        final long f8284g;

        /* renamed from: h  reason: collision with root package name */
        final Weigher f8285h;

        /* renamed from: i  reason: collision with root package name */
        final int f8286i;

        /* renamed from: j  reason: collision with root package name */
        final RemovalListener f8287j;
        @NullableDecl

        /* renamed from: k  reason: collision with root package name */
        final Ticker f8288k;

        /* renamed from: l  reason: collision with root package name */
        final CacheLoader f8289l;
        @NullableDecl

        /* renamed from: m  reason: collision with root package name */
        transient Cache f8290m;

        private ManualSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j2, long j3, long j4, Weigher<K, V> weigher, int i2, RemovalListener<? super K, ? super V> removalListener, Ticker ticker, CacheLoader<? super K, V> cacheLoader) {
            this.f8278a = strength;
            this.f8279b = strength2;
            this.f8280c = equivalence;
            this.f8281d = equivalence2;
            this.f8282e = j2;
            this.f8283f = j3;
            this.f8284g = j4;
            this.f8285h = weigher;
            this.f8286i = i2;
            this.f8287j = removalListener;
            this.f8288k = (ticker == Ticker.systemTicker() || ticker == CacheBuilder.f8199t) ? null : null;
            this.f8289l = cacheLoader;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.f8290m = b().build();
        }

        private Object readResolve() {
            return this.f8290m;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        /* renamed from: a */
        public Cache delegate() {
            return this.f8290m;
        }

        CacheBuilder b() {
            CacheBuilder<K1, V1> removalListener = CacheBuilder.newBuilder().q(this.f8278a).r(this.f8279b).o(this.f8280c).s(this.f8281d).concurrencyLevel(this.f8286i).removalListener(this.f8287j);
            removalListener.f8200a = false;
            long j2 = this.f8282e;
            if (j2 > 0) {
                removalListener.expireAfterWrite(j2, TimeUnit.NANOSECONDS);
            }
            long j3 = this.f8283f;
            if (j3 > 0) {
                removalListener.expireAfterAccess(j3, TimeUnit.NANOSECONDS);
            }
            Weigher weigher = this.f8285h;
            if (weigher != CacheBuilder.OneWeigher.INSTANCE) {
                removalListener.weigher(weigher);
                long j4 = this.f8284g;
                if (j4 != -1) {
                    removalListener.maximumWeight(j4);
                }
            } else {
                long j5 = this.f8284g;
                if (j5 != -1) {
                    removalListener.maximumSize(j5);
                }
            }
            Ticker ticker = this.f8288k;
            if (ticker != null) {
                removalListener.ticker(ticker);
            }
            return removalListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum NullEntry implements ReferenceEntry<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.ReferenceEntry
        public long getAccessTime() {
            return 0L;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public int getHash() {
            return 0;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public Object getKey() {
            return null;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<Object, Object> getNextInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<Object, Object> getNextInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public long getWriteTime() {
            return 0L;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setAccessTime(long j2) {
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setWriteTime(long j2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Segment<K, V> extends ReentrantLock {
        @Weak

        /* renamed from: a  reason: collision with root package name */
        final LocalCache f8291a;

        /* renamed from: b  reason: collision with root package name */
        volatile int f8292b;
        @GuardedBy("this")

        /* renamed from: c  reason: collision with root package name */
        long f8293c;

        /* renamed from: d  reason: collision with root package name */
        int f8294d;

        /* renamed from: e  reason: collision with root package name */
        int f8295e;
        @NullableDecl

        /* renamed from: f  reason: collision with root package name */
        volatile AtomicReferenceArray f8296f;

        /* renamed from: g  reason: collision with root package name */
        final long f8297g;
        @NullableDecl

        /* renamed from: h  reason: collision with root package name */
        final ReferenceQueue f8298h;
        @NullableDecl

        /* renamed from: i  reason: collision with root package name */
        final ReferenceQueue f8299i;

        /* renamed from: j  reason: collision with root package name */
        final Queue f8300j;

        /* renamed from: k  reason: collision with root package name */
        final AtomicInteger f8301k = new AtomicInteger();
        @GuardedBy("this")

        /* renamed from: l  reason: collision with root package name */
        final Queue f8302l;
        @GuardedBy("this")

        /* renamed from: m  reason: collision with root package name */
        final Queue f8303m;

        /* renamed from: n  reason: collision with root package name */
        final AbstractCache.StatsCounter f8304n;

        Segment(LocalCache localCache, int i2, long j2, AbstractCache.StatsCounter statsCounter) {
            this.f8291a = localCache;
            this.f8297g = j2;
            this.f8304n = (AbstractCache.StatsCounter) Preconditions.checkNotNull(statsCounter);
            x(D(i2));
            this.f8298h = localCache.K() ? new ReferenceQueue() : null;
            this.f8299i = localCache.L() ? new ReferenceQueue() : null;
            this.f8300j = localCache.J() ? new ConcurrentLinkedQueue() : LocalCache.f();
            this.f8302l = localCache.N() ? new WriteQueue() : LocalCache.f();
            this.f8303m = localCache.J() ? new AccessQueue() : LocalCache.f();
        }

        Object A(Object obj, int i2, LoadingValueReference loadingValueReference, CacheLoader cacheLoader) {
            return r(obj, i2, loadingValueReference, loadingValueReference.loadFuture(obj, cacheLoader));
        }

        Object B(Object obj, int i2, CacheLoader cacheLoader) {
            LoadingValueReference loadingValueReference;
            boolean z;
            ValueReference valueReference;
            Object A;
            int weight;
            RemovalCause removalCause;
            lock();
            try {
                long read = this.f8291a.f8250p.read();
                G(read);
                int i3 = this.f8292b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = i2 & (atomicReferenceArray.length() - 1);
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    loadingValueReference = null;
                    if (referenceEntry2 == null) {
                        z = true;
                        valueReference = null;
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null && this.f8291a.f8239e.equivalent(obj, key)) {
                        ValueReference valueReference2 = referenceEntry2.getValueReference();
                        if (valueReference2.isLoading()) {
                            z = false;
                        } else {
                            Object obj2 = valueReference2.get();
                            if (obj2 == null) {
                                weight = valueReference2.getWeight();
                                removalCause = RemovalCause.COLLECTED;
                            } else if (!this.f8291a.q(referenceEntry2, read)) {
                                K(referenceEntry2, read);
                                this.f8304n.recordHits(1);
                                return obj2;
                            } else {
                                weight = valueReference2.getWeight();
                                removalCause = RemovalCause.EXPIRED;
                            }
                            l(key, i2, obj2, weight, removalCause);
                            this.f8302l.remove(referenceEntry2);
                            this.f8303m.remove(referenceEntry2);
                            this.f8292b = i3;
                            z = true;
                        }
                        valueReference = valueReference2;
                    } else {
                        referenceEntry2 = referenceEntry2.getNext();
                    }
                }
                if (z) {
                    loadingValueReference = new LoadingValueReference();
                    if (referenceEntry2 == null) {
                        referenceEntry2 = C(obj, i2, referenceEntry);
                        referenceEntry2.setValueReference(loadingValueReference);
                        atomicReferenceArray.set(length, referenceEntry2);
                    } else {
                        referenceEntry2.setValueReference(loadingValueReference);
                    }
                }
                if (z) {
                    try {
                        synchronized (referenceEntry2) {
                            A = A(obj, i2, loadingValueReference, cacheLoader);
                        }
                        return A;
                    } finally {
                        this.f8304n.recordMisses(1);
                    }
                }
                return e0(referenceEntry2, obj, valueReference);
            } finally {
                unlock();
                F();
            }
        }

        @GuardedBy("this")
        ReferenceEntry C(Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            return this.f8291a.f8251q.e(this, Preconditions.checkNotNull(obj), i2, referenceEntry);
        }

        AtomicReferenceArray D(int i2) {
            return new AtomicReferenceArray(i2);
        }

        void E() {
            if ((this.f8301k.incrementAndGet() & 63) == 0) {
                a();
            }
        }

        void F() {
            Y();
        }

        @GuardedBy("this")
        void G(long j2) {
            X(j2);
        }

        @NullableDecl
        Object H(Object obj, int i2, Object obj2, boolean z) {
            int i3;
            lock();
            try {
                long read = this.f8291a.f8250p.read();
                G(read);
                if (this.f8292b + 1 > this.f8295e) {
                    n();
                }
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = i2 & (atomicReferenceArray.length() - 1);
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        this.f8294d++;
                        referenceEntry2 = C(obj, i2, referenceEntry);
                        a0(referenceEntry2, obj, obj2, read);
                        atomicReferenceArray.set(length, referenceEntry2);
                        this.f8292b++;
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null && this.f8291a.f8239e.equivalent(obj, key)) {
                        ValueReference<K, V> valueReference = referenceEntry2.getValueReference();
                        V v = valueReference.get();
                        if (v != null) {
                            if (z) {
                                K(referenceEntry2, read);
                            } else {
                                this.f8294d++;
                                l(obj, i2, v, valueReference.getWeight(), RemovalCause.REPLACED);
                                a0(referenceEntry2, obj, obj2, read);
                                m(referenceEntry2);
                            }
                            return v;
                        }
                        this.f8294d++;
                        if (valueReference.isActive()) {
                            l(obj, i2, v, valueReference.getWeight(), RemovalCause.COLLECTED);
                            a0(referenceEntry2, obj, obj2, read);
                            i3 = this.f8292b;
                        } else {
                            a0(referenceEntry2, obj, obj2, read);
                            i3 = this.f8292b + 1;
                        }
                        this.f8292b = i3;
                    } else {
                        referenceEntry2 = referenceEntry2.getNext();
                    }
                }
                m(referenceEntry2);
                return null;
            } finally {
                unlock();
                F();
            }
        }

        boolean I(ReferenceEntry referenceEntry, int i2) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry2 = (ReferenceEntry) atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> referenceEntry3 = referenceEntry2; referenceEntry3 != null; referenceEntry3 = referenceEntry3.getNext()) {
                    if (referenceEntry3 == referenceEntry) {
                        this.f8294d++;
                        atomicReferenceArray.set(length, U(referenceEntry2, referenceEntry3, referenceEntry3.getKey(), i2, referenceEntry3.getValueReference().get(), referenceEntry3.getValueReference(), RemovalCause.COLLECTED));
                        this.f8292b--;
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
                F();
            }
        }

        boolean J(Object obj, int i2, ValueReference valueReference) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                for (ReferenceEntry referenceEntry2 = referenceEntry; referenceEntry2 != null; referenceEntry2 = referenceEntry2.getNext()) {
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null && this.f8291a.f8239e.equivalent(obj, key)) {
                        if (referenceEntry2.getValueReference() != valueReference) {
                            unlock();
                            if (!isHeldByCurrentThread()) {
                                F();
                            }
                            return false;
                        }
                        this.f8294d++;
                        atomicReferenceArray.set(length, U(referenceEntry, referenceEntry2, key, i2, valueReference.get(), valueReference, RemovalCause.COLLECTED));
                        this.f8292b--;
                        return true;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    F();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    F();
                }
            }
        }

        @GuardedBy("this")
        void K(ReferenceEntry referenceEntry, long j2) {
            if (this.f8291a.A()) {
                referenceEntry.setAccessTime(j2);
            }
            this.f8303m.add(referenceEntry);
        }

        void L(ReferenceEntry referenceEntry, long j2) {
            if (this.f8291a.A()) {
                referenceEntry.setAccessTime(j2);
            }
            this.f8300j.add(referenceEntry);
        }

        @GuardedBy("this")
        void M(ReferenceEntry referenceEntry, int i2, long j2) {
            i();
            this.f8293c += i2;
            if (this.f8291a.A()) {
                referenceEntry.setAccessTime(j2);
            }
            if (this.f8291a.C()) {
                referenceEntry.setWriteTime(j2);
            }
            this.f8303m.add(referenceEntry);
            this.f8302l.add(referenceEntry);
        }

        @NullableDecl
        Object N(Object obj, int i2, CacheLoader cacheLoader, boolean z) {
            LoadingValueReference y = y(obj, i2, z);
            if (y == null) {
                return null;
            }
            ListenableFuture z2 = z(obj, i2, y, cacheLoader);
            if (z2.isDone()) {
                try {
                    return Uninterruptibles.getUninterruptibly(z2);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
            r9 = r5.getValueReference();
            r12 = r9.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0040, code lost:
            if (r12 == null) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0042, code lost:
            r2 = com.google.common.cache.RemovalCause.EXPLICIT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
            r10 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x004a, code lost:
            if (r9.isActive() == false) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
            r2 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
            r11.f8294d++;
            r0.set(r1, U(r4, r5, r6, r13, r12, r9, r10));
            r11.f8292b--;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
            return r12;
         */
        @NullableDecl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        Object O(Object obj, int i2) {
            lock();
            try {
                G(this.f8291a.f8250p.read());
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null && this.f8291a.f8239e.equivalent(obj, key)) {
                        break;
                    }
                    referenceEntry2 = referenceEntry2.getNext();
                }
                return null;
            } finally {
                unlock();
                F();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
            r10 = r6.getValueReference();
            r9 = r10.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:
            if (r12.f8291a.f8240f.equivalent(r15, r9) == false) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
            r13 = com.google.common.cache.RemovalCause.EXPLICIT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x004d, code lost:
            if (r9 != null) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
            if (r10.isActive() == false) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
            r13 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
            r12.f8294d++;
            r0.set(r1, U(r5, r6, r7, r14, r9, r10, r13));
            r12.f8292b--;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x006d, code lost:
            if (r13 != com.google.common.cache.RemovalCause.EXPLICIT) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0070, code lost:
            r2 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
            return r2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        boolean P(Object obj, int i2, Object obj2) {
            lock();
            try {
                G(this.f8291a.f8250p.read());
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                boolean z = true;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null && this.f8291a.f8239e.equivalent(obj, key)) {
                        break;
                    }
                    referenceEntry2 = referenceEntry2.getNext();
                }
                return false;
            } finally {
                unlock();
                F();
            }
        }

        @GuardedBy("this")
        void Q(ReferenceEntry referenceEntry) {
            l(referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry.getValueReference().get(), referenceEntry.getValueReference().getWeight(), RemovalCause.COLLECTED);
            this.f8302l.remove(referenceEntry);
            this.f8303m.remove(referenceEntry);
        }

        @VisibleForTesting
        @GuardedBy("this")
        boolean R(ReferenceEntry referenceEntry, int i2, RemovalCause removalCause) {
            AtomicReferenceArray atomicReferenceArray = this.f8296f;
            int length = (atomicReferenceArray.length() - 1) & i2;
            ReferenceEntry referenceEntry2 = (ReferenceEntry) atomicReferenceArray.get(length);
            for (ReferenceEntry<K, V> referenceEntry3 = referenceEntry2; referenceEntry3 != null; referenceEntry3 = referenceEntry3.getNext()) {
                if (referenceEntry3 == referenceEntry) {
                    this.f8294d++;
                    atomicReferenceArray.set(length, U(referenceEntry2, referenceEntry3, referenceEntry3.getKey(), i2, referenceEntry3.getValueReference().get(), referenceEntry3.getValueReference(), removalCause));
                    this.f8292b--;
                    return true;
                }
            }
            return false;
        }

        @NullableDecl
        @GuardedBy("this")
        ReferenceEntry S(ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2) {
            int i2 = this.f8292b;
            ReferenceEntry<K, V> next = referenceEntry2.getNext();
            while (referenceEntry != referenceEntry2) {
                ReferenceEntry<K, V> g2 = g(referenceEntry, next);
                if (g2 != null) {
                    next = g2;
                } else {
                    Q(referenceEntry);
                    i2--;
                }
                referenceEntry = referenceEntry.getNext();
            }
            this.f8292b = i2;
            return next;
        }

        boolean T(Object obj, int i2, LoadingValueReference loadingValueReference) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i2 || key == null || !this.f8291a.f8239e.equivalent(obj, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else if (referenceEntry2.getValueReference() == loadingValueReference) {
                        if (loadingValueReference.isActive()) {
                            referenceEntry2.setValueReference(loadingValueReference.getOldValue());
                        } else {
                            atomicReferenceArray.set(length, S(referenceEntry, referenceEntry2));
                        }
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
                F();
            }
        }

        @NullableDecl
        @GuardedBy("this")
        ReferenceEntry U(ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2, @NullableDecl Object obj, int i2, Object obj2, ValueReference valueReference, RemovalCause removalCause) {
            l(obj, i2, obj2, valueReference.getWeight(), removalCause);
            this.f8302l.remove(referenceEntry2);
            this.f8303m.remove(referenceEntry2);
            if (valueReference.isLoading()) {
                valueReference.notifyNewValue(null);
                return referenceEntry;
            }
            return S(referenceEntry, referenceEntry2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0072, code lost:
            return null;
         */
        @NullableDecl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        Object V(Object obj, int i2, Object obj2) {
            lock();
            try {
                long read = this.f8291a.f8250p.read();
                G(read);
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = i2 & (atomicReferenceArray.length() - 1);
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null) {
                        if (this.f8291a.f8239e.equivalent(obj, key)) {
                            ValueReference<K, V> valueReference = referenceEntry2.getValueReference();
                            V v = valueReference.get();
                            if (v != null) {
                                this.f8294d++;
                                l(obj, i2, v, valueReference.getWeight(), RemovalCause.REPLACED);
                                a0(referenceEntry2, obj, obj2, read);
                                m(referenceEntry2);
                                return v;
                            } else if (valueReference.isActive()) {
                                this.f8294d++;
                                atomicReferenceArray.set(length, U(referenceEntry, referenceEntry2, key, i2, v, valueReference, RemovalCause.COLLECTED));
                                this.f8292b--;
                            }
                        }
                    }
                    referenceEntry2 = referenceEntry2.getNext();
                }
            } finally {
                unlock();
                F();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x006f, code lost:
            return false;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        boolean W(Object obj, int i2, Object obj2, Object obj3) {
            lock();
            try {
                long read = this.f8291a.f8250p.read();
                G(read);
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = i2 & (atomicReferenceArray.length() - 1);
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null) {
                        if (this.f8291a.f8239e.equivalent(obj, key)) {
                            ValueReference<K, V> valueReference = referenceEntry2.getValueReference();
                            V v = valueReference.get();
                            if (v == null) {
                                if (valueReference.isActive()) {
                                    this.f8294d++;
                                    atomicReferenceArray.set(length, U(referenceEntry, referenceEntry2, key, i2, v, valueReference, RemovalCause.COLLECTED));
                                    this.f8292b--;
                                }
                            } else if (this.f8291a.f8240f.equivalent(obj2, v)) {
                                this.f8294d++;
                                l(obj, i2, v, valueReference.getWeight(), RemovalCause.REPLACED);
                                a0(referenceEntry2, obj, obj3, read);
                                m(referenceEntry2);
                                return true;
                            } else {
                                K(referenceEntry2, read);
                            }
                        }
                    }
                    referenceEntry2 = referenceEntry2.getNext();
                }
            } finally {
                unlock();
                F();
            }
        }

        void X(long j2) {
            if (tryLock()) {
                try {
                    j();
                    o(j2);
                    this.f8301k.set(0);
                } finally {
                    unlock();
                }
            }
        }

        void Y() {
            if (isHeldByCurrentThread()) {
                return;
            }
            this.f8291a.x();
        }

        Object Z(ReferenceEntry referenceEntry, Object obj, int i2, Object obj2, long j2, CacheLoader cacheLoader) {
            Object N;
            return (!this.f8291a.E() || j2 - referenceEntry.getWriteTime() <= this.f8291a.f8247m || referenceEntry.getValueReference().isLoading() || (N = N(obj, i2, cacheLoader, true)) == null) ? obj2 : N;
        }

        void a() {
            X(this.f8291a.f8250p.read());
            Y();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        void a0(ReferenceEntry referenceEntry, Object obj, Object obj2, long j2) {
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            int weigh = this.f8291a.f8244j.weigh(obj, obj2);
            Preconditions.checkState(weigh >= 0, "Weights must be non-negative");
            referenceEntry.setValueReference(this.f8291a.f8242h.b(this, referenceEntry, obj2, weigh));
            M(referenceEntry, weigh, j2);
            valueReference.notifyNewValue(obj2);
        }

        void b() {
            RemovalCause removalCause;
            if (this.f8292b != 0) {
                lock();
                try {
                    G(this.f8291a.f8250p.read());
                    AtomicReferenceArray atomicReferenceArray = this.f8296f;
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        for (ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(i2); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                            if (referenceEntry.getValueReference().isActive()) {
                                Object key = referenceEntry.getKey();
                                V v = referenceEntry.getValueReference().get();
                                if (key != null && v != null) {
                                    removalCause = RemovalCause.EXPLICIT;
                                    l(key, referenceEntry.getHash(), v, referenceEntry.getValueReference().getWeight(), removalCause);
                                }
                                removalCause = RemovalCause.COLLECTED;
                                l(key, referenceEntry.getHash(), v, referenceEntry.getValueReference().getWeight(), removalCause);
                            }
                        }
                    }
                    for (int i3 = 0; i3 < atomicReferenceArray.length(); i3++) {
                        atomicReferenceArray.set(i3, null);
                    }
                    d();
                    this.f8302l.clear();
                    this.f8303m.clear();
                    this.f8301k.set(0);
                    this.f8294d++;
                    this.f8292b = 0;
                } finally {
                    unlock();
                    F();
                }
            }
        }

        boolean b0(Object obj, int i2, LoadingValueReference loadingValueReference, Object obj2) {
            lock();
            try {
                long read = this.f8291a.f8250p.read();
                G(read);
                int i3 = this.f8292b + 1;
                if (i3 > this.f8295e) {
                    n();
                    i3 = this.f8292b + 1;
                }
                int i4 = i3;
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = i2 & (atomicReferenceArray.length() - 1);
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        this.f8294d++;
                        referenceEntry2 = C(obj, i2, referenceEntry);
                        a0(referenceEntry2, obj, obj2, read);
                        atomicReferenceArray.set(length, referenceEntry2);
                        this.f8292b = i4;
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null && this.f8291a.f8239e.equivalent(obj, key)) {
                        ValueReference<K, V> valueReference = referenceEntry2.getValueReference();
                        V v = valueReference.get();
                        if (loadingValueReference != valueReference && (v != null || valueReference == LocalCache.x)) {
                            l(obj, i2, obj2, 0, RemovalCause.REPLACED);
                            return false;
                        }
                        this.f8294d++;
                        if (loadingValueReference.isActive()) {
                            l(obj, i2, v, loadingValueReference.getWeight(), v == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED);
                            i4--;
                        }
                        a0(referenceEntry2, obj, obj2, read);
                        this.f8292b = i4;
                    } else {
                        referenceEntry2 = referenceEntry2.getNext();
                    }
                }
                m(referenceEntry2);
                return true;
            } finally {
                unlock();
                F();
            }
        }

        void c() {
            do {
            } while (this.f8298h.poll() != null);
        }

        void c0() {
            if (tryLock()) {
                try {
                    j();
                } finally {
                    unlock();
                }
            }
        }

        void d() {
            if (this.f8291a.K()) {
                c();
            }
            if (this.f8291a.L()) {
                e();
            }
        }

        void d0(long j2) {
            if (tryLock()) {
                try {
                    o(j2);
                } finally {
                    unlock();
                }
            }
        }

        void e() {
            do {
            } while (this.f8299i.poll() != null);
        }

        Object e0(ReferenceEntry referenceEntry, Object obj, ValueReference valueReference) {
            if (valueReference.isLoading()) {
                Preconditions.checkState(!Thread.holdsLock(referenceEntry), "Recursive load of: %s", obj);
                try {
                    Object waitForValue = valueReference.waitForValue();
                    if (waitForValue != null) {
                        L(referenceEntry, this.f8291a.f8250p.read());
                        return waitForValue;
                    }
                    throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + obj + ".");
                } finally {
                    this.f8304n.recordMisses(1);
                }
            }
            throw new AssertionError();
        }

        boolean f(Object obj, int i2) {
            try {
                if (this.f8292b != 0) {
                    ReferenceEntry u = u(obj, i2, this.f8291a.f8250p.read());
                    if (u == null) {
                        return false;
                    }
                    return u.getValueReference().get() != null;
                }
                return false;
            } finally {
                E();
            }
        }

        @GuardedBy("this")
        ReferenceEntry g(ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2) {
            if (referenceEntry.getKey() == null) {
                return null;
            }
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            V v = valueReference.get();
            if (v == null && valueReference.isActive()) {
                return null;
            }
            ReferenceEntry b2 = this.f8291a.f8251q.b(this, referenceEntry, referenceEntry2);
            b2.setValueReference(valueReference.copyFor(this.f8299i, v, b2));
            return b2;
        }

        @GuardedBy("this")
        void h() {
            int i2 = 0;
            do {
                Reference poll = this.f8298h.poll();
                if (poll == null) {
                    return;
                }
                this.f8291a.y((ReferenceEntry) poll);
                i2++;
            } while (i2 != 16);
        }

        @GuardedBy("this")
        void i() {
            while (true) {
                ReferenceEntry referenceEntry = (ReferenceEntry) this.f8300j.poll();
                if (referenceEntry == null) {
                    return;
                }
                if (this.f8303m.contains(referenceEntry)) {
                    this.f8303m.add(referenceEntry);
                }
            }
        }

        @GuardedBy("this")
        void j() {
            if (this.f8291a.K()) {
                h();
            }
            if (this.f8291a.L()) {
                k();
            }
        }

        @GuardedBy("this")
        void k() {
            int i2 = 0;
            do {
                Reference poll = this.f8299i.poll();
                if (poll == null) {
                    return;
                }
                this.f8291a.z((ValueReference) poll);
                i2++;
            } while (i2 != 16);
        }

        @GuardedBy("this")
        void l(@NullableDecl Object obj, int i2, @NullableDecl Object obj2, int i3, RemovalCause removalCause) {
            this.f8293c -= i3;
            if (removalCause.a()) {
                this.f8304n.recordEviction();
            }
            if (this.f8291a.f8248n != LocalCache.y) {
                this.f8291a.f8248n.offer(RemovalNotification.create(obj, obj2, removalCause));
            }
        }

        @GuardedBy("this")
        void m(ReferenceEntry referenceEntry) {
            if (this.f8291a.g()) {
                i();
                if (referenceEntry.getValueReference().getWeight() > this.f8297g && !R(referenceEntry, referenceEntry.getHash(), RemovalCause.SIZE)) {
                    throw new AssertionError();
                }
                while (this.f8293c > this.f8297g) {
                    ReferenceEntry w = w();
                    if (!R(w, w.getHash(), RemovalCause.SIZE)) {
                        throw new AssertionError();
                    }
                }
            }
        }

        @GuardedBy("this")
        void n() {
            AtomicReferenceArray atomicReferenceArray = this.f8296f;
            int length = atomicReferenceArray.length();
            if (length >= 1073741824) {
                return;
            }
            int i2 = this.f8292b;
            AtomicReferenceArray D = D(length << 1);
            this.f8295e = (D.length() * 3) / 4;
            int length2 = D.length() - 1;
            for (int i3 = 0; i3 < length; i3++) {
                ReferenceEntry<K, V> referenceEntry = (ReferenceEntry) atomicReferenceArray.get(i3);
                if (referenceEntry != null) {
                    ReferenceEntry<K, V> next = referenceEntry.getNext();
                    int hash = referenceEntry.getHash() & length2;
                    if (next == null) {
                        D.set(hash, referenceEntry);
                    } else {
                        ReferenceEntry<K, V> referenceEntry2 = referenceEntry;
                        while (next != null) {
                            int hash2 = next.getHash() & length2;
                            if (hash2 != hash) {
                                referenceEntry2 = next;
                                hash = hash2;
                            }
                            next = next.getNext();
                        }
                        D.set(hash, referenceEntry2);
                        while (referenceEntry != referenceEntry2) {
                            int hash3 = referenceEntry.getHash() & length2;
                            ReferenceEntry g2 = g(referenceEntry, (ReferenceEntry) D.get(hash3));
                            if (g2 != null) {
                                D.set(hash3, g2);
                            } else {
                                Q(referenceEntry);
                                i2--;
                            }
                            referenceEntry = referenceEntry.getNext();
                        }
                    }
                }
            }
            this.f8296f = D;
            this.f8292b = i2;
        }

        @GuardedBy("this")
        void o(long j2) {
            ReferenceEntry referenceEntry;
            ReferenceEntry referenceEntry2;
            i();
            do {
                referenceEntry = (ReferenceEntry) this.f8302l.peek();
                if (referenceEntry == null || !this.f8291a.q(referenceEntry, j2)) {
                    do {
                        referenceEntry2 = (ReferenceEntry) this.f8303m.peek();
                        if (referenceEntry2 == null || !this.f8291a.q(referenceEntry2, j2)) {
                            return;
                        }
                    } while (R(referenceEntry2, referenceEntry2.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (R(referenceEntry, referenceEntry.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        @NullableDecl
        Object p(Object obj, int i2) {
            try {
                if (this.f8292b != 0) {
                    long read = this.f8291a.f8250p.read();
                    ReferenceEntry u = u(obj, i2, read);
                    if (u == null) {
                        return null;
                    }
                    V v = u.getValueReference().get();
                    if (v != null) {
                        L(u, read);
                        return Z(u, u.getKey(), i2, v, read, this.f8291a.f8253s);
                    }
                    c0();
                }
                return null;
            } finally {
                E();
            }
        }

        Object q(Object obj, int i2, CacheLoader cacheLoader) {
            ReferenceEntry s2;
            Preconditions.checkNotNull(obj);
            Preconditions.checkNotNull(cacheLoader);
            try {
                try {
                    if (this.f8292b != 0 && (s2 = s(obj, i2)) != null) {
                        long read = this.f8291a.f8250p.read();
                        Object v = v(s2, read);
                        if (v != null) {
                            L(s2, read);
                            this.f8304n.recordHits(1);
                            return Z(s2, obj, i2, v, read, cacheLoader);
                        }
                        ValueReference<K, V> valueReference = s2.getValueReference();
                        if (valueReference.isLoading()) {
                            return e0(s2, obj, valueReference);
                        }
                    }
                    return B(obj, i2, cacheLoader);
                } catch (ExecutionException e2) {
                    Throwable cause = e2.getCause();
                    if (cause instanceof Error) {
                        throw new ExecutionError((Error) cause);
                    }
                    if (cause instanceof RuntimeException) {
                        throw new UncheckedExecutionException(cause);
                    }
                    throw e2;
                }
            } finally {
                E();
            }
        }

        Object r(Object obj, int i2, LoadingValueReference loadingValueReference, ListenableFuture listenableFuture) {
            Object obj2;
            try {
                obj2 = Uninterruptibles.getUninterruptibly(listenableFuture);
            } catch (Throwable th) {
                th = th;
                obj2 = null;
            }
            try {
                if (obj2 != null) {
                    this.f8304n.recordLoadSuccess(loadingValueReference.elapsedNanos());
                    b0(obj, i2, loadingValueReference, obj2);
                    return obj2;
                }
                throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + obj + ".");
            } catch (Throwable th2) {
                th = th2;
                if (obj2 == null) {
                    this.f8304n.recordLoadException(loadingValueReference.elapsedNanos());
                    T(obj, i2, loadingValueReference);
                }
                throw th;
            }
        }

        @NullableDecl
        ReferenceEntry s(Object obj, int i2) {
            for (ReferenceEntry t2 = t(i2); t2 != null; t2 = t2.getNext()) {
                if (t2.getHash() == i2) {
                    Object key = t2.getKey();
                    if (key == null) {
                        c0();
                    } else if (this.f8291a.f8239e.equivalent(obj, key)) {
                        return t2;
                    }
                }
            }
            return null;
        }

        ReferenceEntry t(int i2) {
            AtomicReferenceArray atomicReferenceArray = this.f8296f;
            return (ReferenceEntry) atomicReferenceArray.get(i2 & (atomicReferenceArray.length() - 1));
        }

        @NullableDecl
        ReferenceEntry u(Object obj, int i2, long j2) {
            ReferenceEntry s2 = s(obj, i2);
            if (s2 == null) {
                return null;
            }
            if (this.f8291a.q(s2, j2)) {
                d0(j2);
                return null;
            }
            return s2;
        }

        Object v(ReferenceEntry referenceEntry, long j2) {
            if (referenceEntry.getKey() == null) {
                c0();
                return null;
            }
            V v = referenceEntry.getValueReference().get();
            if (v == null) {
                c0();
                return null;
            } else if (this.f8291a.q(referenceEntry, j2)) {
                d0(j2);
                return null;
            } else {
                return v;
            }
        }

        @GuardedBy("this")
        ReferenceEntry w() {
            for (ReferenceEntry referenceEntry : this.f8303m) {
                if (referenceEntry.getValueReference().getWeight() > 0) {
                    return referenceEntry;
                }
            }
            throw new AssertionError();
        }

        void x(AtomicReferenceArray atomicReferenceArray) {
            this.f8295e = (atomicReferenceArray.length() * 3) / 4;
            if (!this.f8291a.e()) {
                int i2 = this.f8295e;
                if (i2 == this.f8297g) {
                    this.f8295e = i2 + 1;
                }
            }
            this.f8296f = atomicReferenceArray;
        }

        @NullableDecl
        LoadingValueReference y(Object obj, int i2, boolean z) {
            lock();
            try {
                long read = this.f8291a.f8250p.read();
                G(read);
                AtomicReferenceArray atomicReferenceArray = this.f8296f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                for (ReferenceEntry referenceEntry2 = referenceEntry; referenceEntry2 != null; referenceEntry2 = referenceEntry2.getNext()) {
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == i2 && key != null && this.f8291a.f8239e.equivalent(obj, key)) {
                        ValueReference<K, V> valueReference = referenceEntry2.getValueReference();
                        if (!valueReference.isLoading() && (!z || read - referenceEntry2.getWriteTime() >= this.f8291a.f8247m)) {
                            this.f8294d++;
                            LoadingValueReference loadingValueReference = new LoadingValueReference(valueReference);
                            referenceEntry2.setValueReference(loadingValueReference);
                            return loadingValueReference;
                        }
                        return null;
                    }
                }
                this.f8294d++;
                LoadingValueReference loadingValueReference2 = new LoadingValueReference();
                ReferenceEntry C = C(obj, i2, referenceEntry);
                C.setValueReference(loadingValueReference2);
                atomicReferenceArray.set(length, C);
                return loadingValueReference2;
            } finally {
                unlock();
                F();
            }
        }

        ListenableFuture z(final Object obj, final int i2, final LoadingValueReference loadingValueReference, CacheLoader cacheLoader) {
            final ListenableFuture<V> loadFuture = loadingValueReference.loadFuture(obj, cacheLoader);
            loadFuture.addListener(new Runnable() { // from class: com.google.common.cache.LocalCache.Segment.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Segment.this.r(obj, i2, loadingValueReference, loadFuture);
                    } catch (Throwable th) {
                        LocalCache.w.log(Level.WARNING, "Exception thrown during refresh", th);
                        loadingValueReference.setException(th);
                    }
                }
            }, MoreExecutors.directExecutor());
            return loadFuture;
        }
    }

    /* loaded from: classes2.dex */
    static class SoftValueReference<K, V> extends SoftReference<V> implements ValueReference<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final ReferenceEntry f8310a;

        SoftValueReference(ReferenceQueue referenceQueue, Object obj, ReferenceEntry referenceEntry) {
            super(obj, referenceQueue);
            this.f8310a = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new SoftValueReference(referenceQueue, v, referenceEntry);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.f8310a;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(V v) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum Strength {
        STRONG { // from class: com.google.common.cache.LocalCache.Strength.1
            @Override // com.google.common.cache.LocalCache.Strength
            Equivalence<Object> a() {
                return Equivalence.equals();
            }

            @Override // com.google.common.cache.LocalCache.Strength
            <K, V> ValueReference<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, Object obj, int i2) {
                return i2 == 1 ? new StrongValueReference(obj) : new WeightedStrongValueReference(obj, i2);
            }
        },
        SOFT { // from class: com.google.common.cache.LocalCache.Strength.2
            @Override // com.google.common.cache.LocalCache.Strength
            Equivalence<Object> a() {
                return Equivalence.identity();
            }

            @Override // com.google.common.cache.LocalCache.Strength
            <K, V> ValueReference<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, Object obj, int i2) {
                return i2 == 1 ? new SoftValueReference(segment.f8299i, obj, referenceEntry) : new WeightedSoftValueReference(segment.f8299i, obj, referenceEntry, i2);
            }
        },
        WEAK { // from class: com.google.common.cache.LocalCache.Strength.3
            @Override // com.google.common.cache.LocalCache.Strength
            Equivalence<Object> a() {
                return Equivalence.identity();
            }

            @Override // com.google.common.cache.LocalCache.Strength
            <K, V> ValueReference<Object, Object> b(Segment<Object, Object> segment, ReferenceEntry<Object, Object> referenceEntry, Object obj, int i2) {
                return i2 == 1 ? new WeakValueReference(segment.f8299i, obj, referenceEntry) : new WeightedWeakValueReference(segment.f8299i, obj, referenceEntry, i2);
            }
        };

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Equivalence a();

        abstract ValueReference b(Segment segment, ReferenceEntry referenceEntry, Object obj, int i2);
    }

    /* loaded from: classes2.dex */
    static final class StrongAccessEntry<K, V> extends StrongEntry<K, V> {

        /* renamed from: e  reason: collision with root package name */
        volatile long f8311e;

        /* renamed from: f  reason: collision with root package name */
        ReferenceEntry f8312f;

        /* renamed from: g  reason: collision with root package name */
        ReferenceEntry f8313g;

        StrongAccessEntry(Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            super(obj, i2, referenceEntry);
            this.f8311e = LongCompanionObject.MAX_VALUE;
            this.f8312f = LocalCache.u();
            this.f8313g = LocalCache.u();
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public long getAccessTime() {
            return this.f8311e;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.f8312f;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.f8313g;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setAccessTime(long j2) {
            this.f8311e = j2;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8312f = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8313g = referenceEntry;
        }
    }

    /* loaded from: classes2.dex */
    static final class StrongAccessWriteEntry<K, V> extends StrongEntry<K, V> {

        /* renamed from: e  reason: collision with root package name */
        volatile long f8314e;

        /* renamed from: f  reason: collision with root package name */
        ReferenceEntry f8315f;

        /* renamed from: g  reason: collision with root package name */
        ReferenceEntry f8316g;

        /* renamed from: h  reason: collision with root package name */
        volatile long f8317h;

        /* renamed from: i  reason: collision with root package name */
        ReferenceEntry f8318i;

        /* renamed from: j  reason: collision with root package name */
        ReferenceEntry f8319j;

        StrongAccessWriteEntry(Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            super(obj, i2, referenceEntry);
            this.f8314e = LongCompanionObject.MAX_VALUE;
            this.f8315f = LocalCache.u();
            this.f8316g = LocalCache.u();
            this.f8317h = LongCompanionObject.MAX_VALUE;
            this.f8318i = LocalCache.u();
            this.f8319j = LocalCache.u();
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public long getAccessTime() {
            return this.f8314e;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.f8315f;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.f8318i;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.f8316g;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.f8319j;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public long getWriteTime() {
            return this.f8317h;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setAccessTime(long j2) {
            this.f8314e = j2;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8315f = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8318i = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8316g = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8319j = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setWriteTime(long j2) {
            this.f8317h = j2;
        }
    }

    /* loaded from: classes2.dex */
    static class StrongEntry<K, V> extends AbstractReferenceEntry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8320a;

        /* renamed from: b  reason: collision with root package name */
        final int f8321b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        final ReferenceEntry f8322c;

        /* renamed from: d  reason: collision with root package name */
        volatile ValueReference f8323d = LocalCache.H();

        StrongEntry(Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            this.f8320a = obj;
            this.f8321b = i2;
            this.f8322c = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public int getHash() {
            return this.f8321b;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public K getKey() {
            return (K) this.f8320a;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.f8322c;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            return this.f8323d;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.f8323d = valueReference;
        }
    }

    /* loaded from: classes2.dex */
    static class StrongValueReference<K, V> implements ValueReference<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8324a;

        StrongValueReference(Object obj) {
            this.f8324a = obj;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V get() {
            return (V) this.f8324a;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(V v) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    /* loaded from: classes2.dex */
    static final class StrongWriteEntry<K, V> extends StrongEntry<K, V> {

        /* renamed from: e  reason: collision with root package name */
        volatile long f8325e;

        /* renamed from: f  reason: collision with root package name */
        ReferenceEntry f8326f;

        /* renamed from: g  reason: collision with root package name */
        ReferenceEntry f8327g;

        StrongWriteEntry(Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            super(obj, i2, referenceEntry);
            this.f8325e = LongCompanionObject.MAX_VALUE;
            this.f8326f = LocalCache.u();
            this.f8327g = LocalCache.u();
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.f8326f;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.f8327g;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public long getWriteTime() {
            return this.f8325e;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8326f = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8327g = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
        public void setWriteTime(long j2) {
            this.f8325e = j2;
        }
    }

    /* loaded from: classes2.dex */
    final class ValueIterator extends LocalCache<K, V>.HashIterator<V> {
        ValueIterator(LocalCache localCache) {
            super();
        }

        @Override // com.google.common.cache.LocalCache.HashIterator, java.util.Iterator
        public V next() {
            return (V) c().getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface ValueReference<K, V> {
        ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @NullableDecl V v, ReferenceEntry<K, V> referenceEntry);

        @NullableDecl
        V get();

        @NullableDecl
        ReferenceEntry<K, V> getEntry();

        int getWeight();

        boolean isActive();

        boolean isLoading();

        void notifyNewValue(@NullableDecl V v);

        V waitForValue();
    }

    /* loaded from: classes2.dex */
    final class Values extends AbstractCollection<V> {
        private final ConcurrentMap<?, ?> map;

        Values(ConcurrentMap concurrentMap) {
            this.map = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator(LocalCache.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    /* loaded from: classes2.dex */
    static final class WeakAccessEntry<K, V> extends WeakEntry<K, V> {

        /* renamed from: d  reason: collision with root package name */
        volatile long f8329d;

        /* renamed from: e  reason: collision with root package name */
        ReferenceEntry f8330e;

        /* renamed from: f  reason: collision with root package name */
        ReferenceEntry f8331f;

        WeakAccessEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            super(referenceQueue, obj, i2, referenceEntry);
            this.f8329d = LongCompanionObject.MAX_VALUE;
            this.f8330e = LocalCache.u();
            this.f8331f = LocalCache.u();
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public long getAccessTime() {
            return this.f8329d;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.f8330e;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.f8331f;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setAccessTime(long j2) {
            this.f8329d = j2;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8330e = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8331f = referenceEntry;
        }
    }

    /* loaded from: classes2.dex */
    static final class WeakAccessWriteEntry<K, V> extends WeakEntry<K, V> {

        /* renamed from: d  reason: collision with root package name */
        volatile long f8332d;

        /* renamed from: e  reason: collision with root package name */
        ReferenceEntry f8333e;

        /* renamed from: f  reason: collision with root package name */
        ReferenceEntry f8334f;

        /* renamed from: g  reason: collision with root package name */
        volatile long f8335g;

        /* renamed from: h  reason: collision with root package name */
        ReferenceEntry f8336h;

        /* renamed from: i  reason: collision with root package name */
        ReferenceEntry f8337i;

        WeakAccessWriteEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            super(referenceQueue, obj, i2, referenceEntry);
            this.f8332d = LongCompanionObject.MAX_VALUE;
            this.f8333e = LocalCache.u();
            this.f8334f = LocalCache.u();
            this.f8335g = LongCompanionObject.MAX_VALUE;
            this.f8336h = LocalCache.u();
            this.f8337i = LocalCache.u();
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public long getAccessTime() {
            return this.f8332d;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.f8333e;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.f8336h;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.f8334f;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.f8337i;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public long getWriteTime() {
            return this.f8335g;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setAccessTime(long j2) {
            this.f8332d = j2;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8333e = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8336h = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8334f = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8337i = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setWriteTime(long j2) {
            this.f8335g = j2;
        }
    }

    /* loaded from: classes2.dex */
    static class WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final int f8338a;
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        final ReferenceEntry f8339b;

        /* renamed from: c  reason: collision with root package name */
        volatile ValueReference f8340c;

        WeakEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            super(obj, referenceQueue);
            this.f8340c = LocalCache.H();
            this.f8338a = i2;
            this.f8339b = referenceEntry;
        }

        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public int getHash() {
            return this.f8338a;
        }

        @Override // com.google.common.cache.ReferenceEntry
        public K getKey() {
            return get();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.f8339b;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            return this.f8340c;
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j2) {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.f8340c = valueReference;
        }

        public void setWriteTime(long j2) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes2.dex */
    static class WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final ReferenceEntry f8341a;

        WeakValueReference(ReferenceQueue referenceQueue, Object obj, ReferenceEntry referenceEntry) {
            super(obj, referenceQueue);
            this.f8341a = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeakValueReference(referenceQueue, v, referenceEntry);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.f8341a;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(V v) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    /* loaded from: classes2.dex */
    static final class WeakWriteEntry<K, V> extends WeakEntry<K, V> {

        /* renamed from: d  reason: collision with root package name */
        volatile long f8342d;

        /* renamed from: e  reason: collision with root package name */
        ReferenceEntry f8343e;

        /* renamed from: f  reason: collision with root package name */
        ReferenceEntry f8344f;

        WeakWriteEntry(ReferenceQueue referenceQueue, Object obj, int i2, @NullableDecl ReferenceEntry referenceEntry) {
            super(referenceQueue, obj, i2, referenceEntry);
            this.f8342d = LongCompanionObject.MAX_VALUE;
            this.f8343e = LocalCache.u();
            this.f8344f = LocalCache.u();
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.f8343e;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.f8344f;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public long getWriteTime() {
            return this.f8342d;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8343e = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f8344f = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.ReferenceEntry
        public void setWriteTime(long j2) {
            this.f8342d = j2;
        }
    }

    /* loaded from: classes2.dex */
    static final class WeightedSoftValueReference<K, V> extends SoftValueReference<K, V> {

        /* renamed from: b  reason: collision with root package name */
        final int f8345b;

        WeightedSoftValueReference(ReferenceQueue referenceQueue, Object obj, ReferenceEntry referenceEntry, int i2) {
            super(referenceQueue, obj, referenceEntry);
            this.f8345b = i2;
        }

        @Override // com.google.common.cache.LocalCache.SoftValueReference, com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedSoftValueReference(referenceQueue, v, referenceEntry, this.f8345b);
        }

        @Override // com.google.common.cache.LocalCache.SoftValueReference, com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.f8345b;
        }
    }

    /* loaded from: classes2.dex */
    static final class WeightedStrongValueReference<K, V> extends StrongValueReference<K, V> {

        /* renamed from: b  reason: collision with root package name */
        final int f8346b;

        WeightedStrongValueReference(Object obj, int i2) {
            super(obj);
            this.f8346b = i2;
        }

        @Override // com.google.common.cache.LocalCache.StrongValueReference, com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.f8346b;
        }
    }

    /* loaded from: classes2.dex */
    static final class WeightedWeakValueReference<K, V> extends WeakValueReference<K, V> {

        /* renamed from: b  reason: collision with root package name */
        final int f8347b;

        WeightedWeakValueReference(ReferenceQueue referenceQueue, Object obj, ReferenceEntry referenceEntry, int i2) {
            super(referenceQueue, obj, referenceEntry);
            this.f8347b = i2;
        }

        @Override // com.google.common.cache.LocalCache.WeakValueReference, com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedWeakValueReference(referenceQueue, v, referenceEntry, this.f8347b);
        }

        @Override // com.google.common.cache.LocalCache.WeakValueReference, com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.f8347b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WriteQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {

        /* renamed from: a  reason: collision with root package name */
        final ReferenceEntry f8348a = new AbstractReferenceEntry<Object, Object>(this) { // from class: com.google.common.cache.LocalCache.WriteQueue.1

            /* renamed from: a  reason: collision with root package name */
            ReferenceEntry<Object, Object> f8349a = this;

            /* renamed from: b  reason: collision with root package name */
            ReferenceEntry<Object, Object> f8350b = this;

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public ReferenceEntry<Object, Object> getNextInWriteQueue() {
                return this.f8349a;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
                return this.f8350b;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public long getWriteTime() {
                return LongCompanionObject.MAX_VALUE;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public void setNextInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
                this.f8349a = referenceEntry;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
                this.f8350b = referenceEntry;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.ReferenceEntry
            public void setWriteTime(long j2) {
            }
        };

        WriteQueue() {
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ReferenceEntry<K, V> nextInWriteQueue = this.f8348a.getNextInWriteQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.f8348a;
                if (nextInWriteQueue == referenceEntry) {
                    referenceEntry.setNextInWriteQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.f8348a;
                    referenceEntry2.setPreviousInWriteQueue(referenceEntry2);
                    return;
                }
                ReferenceEntry<K, V> nextInWriteQueue2 = nextInWriteQueue.getNextInWriteQueue();
                LocalCache.w(nextInWriteQueue);
                nextInWriteQueue = nextInWriteQueue2;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInWriteQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f8348a.getNextInWriteQueue() == this.f8348a;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) { // from class: com.google.common.cache.LocalCache.WriteQueue.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractSequentialIterator
                /* renamed from: b */
                public ReferenceEntry a(ReferenceEntry referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
                    if (nextInWriteQueue == WriteQueue.this.f8348a) {
                        return null;
                    }
                    return nextInWriteQueue;
                }
            };
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.c(referenceEntry.getPreviousInWriteQueue(), referenceEntry.getNextInWriteQueue());
            LocalCache.c(this.f8348a.getPreviousInWriteQueue(), referenceEntry);
            LocalCache.c(referenceEntry, this.f8348a);
            return true;
        }

        @Override // java.util.Queue
        public /* bridge */ /* synthetic */ boolean offer(Object obj) {
            return offer((ReferenceEntry) ((ReferenceEntry) obj));
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInWriteQueue = this.f8348a.getNextInWriteQueue();
            if (nextInWriteQueue == this.f8348a) {
                return null;
            }
            return nextInWriteQueue;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInWriteQueue = this.f8348a.getNextInWriteQueue();
            if (nextInWriteQueue == this.f8348a) {
                return null;
            }
            remove(nextInWriteQueue);
            return nextInWriteQueue;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry<K, V> previousInWriteQueue = referenceEntry.getPreviousInWriteQueue();
            ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
            LocalCache.c(previousInWriteQueue, nextInWriteQueue);
            LocalCache.w(referenceEntry);
            return nextInWriteQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i2 = 0;
            for (ReferenceEntry<K, V> nextInWriteQueue = this.f8348a.getNextInWriteQueue(); nextInWriteQueue != this.f8348a; nextInWriteQueue = nextInWriteQueue.getNextInWriteQueue()) {
                i2++;
            }
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class WriteThroughEntry implements Map.Entry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f8352a;

        /* renamed from: b  reason: collision with root package name */
        Object f8353b;

        WriteThroughEntry(Object obj, Object obj2) {
            this.f8352a = obj;
            this.f8353b = obj2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return this.f8352a.equals(entry.getKey()) && this.f8353b.equals(entry.getValue());
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) this.f8352a;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return (V) this.f8353b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f8352a.hashCode() ^ this.f8353b.hashCode();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) LocalCache.this.put(this.f8352a, v);
            this.f8353b = v;
            return v2;
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    LocalCache(CacheBuilder cacheBuilder, @NullableDecl CacheLoader cacheLoader) {
        this.f8238d = Math.min(cacheBuilder.a(), 65536);
        Strength f2 = cacheBuilder.f();
        this.f8241g = f2;
        this.f8242h = cacheBuilder.m();
        this.f8239e = cacheBuilder.e();
        this.f8240f = cacheBuilder.l();
        long g2 = cacheBuilder.g();
        this.f8243i = g2;
        this.f8244j = cacheBuilder.n();
        this.f8245k = cacheBuilder.b();
        this.f8246l = cacheBuilder.c();
        this.f8247m = cacheBuilder.h();
        RemovalListener i2 = cacheBuilder.i();
        this.f8249o = i2;
        this.f8248n = i2 == CacheBuilder.NullListener.INSTANCE ? f() : new ConcurrentLinkedQueue();
        this.f8250p = cacheBuilder.k(B());
        this.f8251q = EntryFactory.d(f2, I(), M());
        this.f8252r = (AbstractCache.StatsCounter) cacheBuilder.j().get();
        this.f8253s = cacheLoader;
        int min = Math.min(cacheBuilder.d(), 1073741824);
        if (g() && !e()) {
            min = (int) Math.min(min, g2);
        }
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        int i6 = 1;
        while (i6 < this.f8238d && (!g() || i6 * 20 <= this.f8243i)) {
            i5++;
            i6 <<= 1;
        }
        this.f8236b = 32 - i5;
        this.f8235a = i6 - 1;
        this.f8237c = t(i6);
        int i7 = min / i6;
        while (i4 < (i7 * i6 < min ? i7 + 1 : i7)) {
            i4 <<= 1;
        }
        if (g()) {
            long j2 = this.f8243i;
            long j3 = i6;
            long j4 = (j2 / j3) + 1;
            long j5 = j2 % j3;
            while (true) {
                Segment[] segmentArr = this.f8237c;
                if (i3 >= segmentArr.length) {
                    return;
                }
                if (i3 == j5) {
                    j4--;
                }
                segmentArr[i3] = d(i4, j4, (AbstractCache.StatsCounter) cacheBuilder.j().get());
                i3++;
            }
        } else {
            while (true) {
                Segment[] segmentArr2 = this.f8237c;
                if (i3 >= segmentArr2.length) {
                    return;
                }
                segmentArr2[i3] = d(i4, -1L, (AbstractCache.StatsCounter) cacheBuilder.j().get());
                i3++;
            }
        }
    }

    static int F(int i2) {
        int i3 = i2 + ((i2 << 15) ^ (-12931));
        int i4 = i3 ^ (i3 >>> 10);
        int i5 = i4 + (i4 << 3);
        int i6 = i5 ^ (i5 >>> 6);
        int i7 = i6 + (i6 << 2) + (i6 << 14);
        return i7 ^ (i7 >>> 16);
    }

    static ValueReference H() {
        return x;
    }

    static void b(ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2) {
        referenceEntry.setNextInAccessQueue(referenceEntry2);
        referenceEntry2.setPreviousInAccessQueue(referenceEntry);
    }

    static void c(ReferenceEntry referenceEntry, ReferenceEntry referenceEntry2) {
        referenceEntry.setNextInWriteQueue(referenceEntry2);
        referenceEntry2.setPreviousInWriteQueue(referenceEntry);
    }

    static Queue f() {
        return y;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    static ReferenceEntry u() {
        return NullEntry.INSTANCE;
    }

    static void v(ReferenceEntry referenceEntry) {
        ReferenceEntry<K, V> u = u();
        referenceEntry.setNextInAccessQueue(u);
        referenceEntry.setPreviousInAccessQueue(u);
    }

    static void w(ReferenceEntry referenceEntry) {
        ReferenceEntry<K, V> u = u();
        referenceEntry.setNextInWriteQueue(u);
        referenceEntry.setPreviousInWriteQueue(u);
    }

    boolean A() {
        return h();
    }

    boolean B() {
        return C() || A();
    }

    boolean C() {
        return i() || E();
    }

    void D(Object obj) {
        int o2 = o(Preconditions.checkNotNull(obj));
        G(o2).N(obj, o2, this.f8253s, false);
    }

    boolean E() {
        return this.f8247m > 0;
    }

    Segment G(int i2) {
        return this.f8237c[(i2 >>> this.f8236b) & this.f8235a];
    }

    boolean I() {
        return J() || A();
    }

    boolean J() {
        return h() || g();
    }

    boolean K() {
        return this.f8241g != Strength.STRONG;
    }

    boolean L() {
        return this.f8242h != Strength.STRONG;
    }

    boolean M() {
        return N() || C();
    }

    boolean N() {
        return i();
    }

    public void cleanUp() {
        for (Segment segment : this.f8237c) {
            segment.a();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment segment : this.f8237c) {
            segment.b();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        if (obj == null) {
            return false;
        }
        int o2 = o(obj);
        return G(o2).f(obj, o2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.util.concurrent.atomic.AtomicReferenceArray] */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        long read = this.f8250p.read();
        Segment[] segmentArr = this.f8237c;
        long j2 = -1;
        int i2 = 0;
        while (i2 < 3) {
            long j3 = 0;
            int length = segmentArr.length;
            for (int i3 = z; i3 < length; i3++) {
                Segment segment = segmentArr[i3];
                int i4 = segment.f8292b;
                ?? r14 = segment.f8296f;
                for (int i5 = z; i5 < r14.length(); i5++) {
                    for (ReferenceEntry<K, V> referenceEntry = (ReferenceEntry) r14.get(i5); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                        Segment[] segmentArr2 = segmentArr;
                        Object v = segment.v(referenceEntry, read);
                        long j4 = read;
                        if (v != null && this.f8240f.equivalent(obj, v)) {
                            return true;
                        }
                        segmentArr = segmentArr2;
                        read = j4;
                    }
                }
                j3 += segment.f8294d;
                read = read;
                z = false;
            }
            long j5 = read;
            Segment[] segmentArr3 = segmentArr;
            if (j3 == j2) {
                return false;
            }
            i2++;
            j2 = j3;
            segmentArr = segmentArr3;
            read = j5;
            z = false;
        }
        return z;
    }

    Segment d(int i2, long j2, AbstractCache.StatsCounter statsCounter) {
        return new Segment(this, i2, j2, statsCounter);
    }

    boolean e() {
        return this.f8244j != CacheBuilder.OneWeigher.INSTANCE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @GwtIncompatible
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.v;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet(this);
        this.v = entrySet;
        return entrySet;
    }

    boolean g() {
        return this.f8243i >= 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public V get(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int o2 = o(obj);
        return (V) G(o2).p(obj, o2);
    }

    @NullableDecl
    public V getIfPresent(Object obj) {
        int o2 = o(Preconditions.checkNotNull(obj));
        V v = (V) G(o2).p(obj, o2);
        AbstractCache.StatsCounter statsCounter = this.f8252r;
        if (v == null) {
            statsCounter.recordMisses(1);
        } else {
            statsCounter.recordHits(1);
        }
        return v;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @NullableDecl
    public V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    boolean h() {
        return this.f8245k > 0;
    }

    boolean i() {
        return this.f8246l > 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment[] segmentArr = this.f8237c;
        long j2 = 0;
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].f8292b != 0) {
                return false;
            }
            j2 += segmentArr[i2].f8294d;
        }
        if (j2 != 0) {
            for (int i3 = 0; i3 < segmentArr.length; i3++) {
                if (segmentArr[i3].f8292b != 0) {
                    return false;
                }
                j2 -= segmentArr[i3].f8294d;
            }
            return j2 == 0;
        }
        return true;
    }

    Object j(Object obj, CacheLoader cacheLoader) {
        int o2 = o(Preconditions.checkNotNull(obj));
        return G(o2).q(obj, o2, cacheLoader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    ImmutableMap k(Iterable iterable) {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        LinkedHashSet newLinkedHashSet = Sets.newLinkedHashSet();
        int i2 = 0;
        int i3 = 0;
        for (Object obj : iterable) {
            V v = get(obj);
            if (!newLinkedHashMap.containsKey(obj)) {
                newLinkedHashMap.put(obj, v);
                if (v == null) {
                    i3++;
                    newLinkedHashSet.add(obj);
                } else {
                    i2++;
                }
            }
        }
        try {
            if (!newLinkedHashSet.isEmpty()) {
                try {
                    Map r2 = r(newLinkedHashSet, this.f8253s);
                    for (Object obj2 : newLinkedHashSet) {
                        Object obj3 = r2.get(obj2);
                        if (obj3 == null) {
                            throw new CacheLoader.InvalidCacheLoadException("loadAll failed to return a value for " + obj2);
                        }
                        newLinkedHashMap.put(obj2, obj3);
                    }
                } catch (CacheLoader.UnsupportedLoadingOperationException unused) {
                    for (Object obj4 : newLinkedHashSet) {
                        i3--;
                        newLinkedHashMap.put(obj4, j(obj4, this.f8253s));
                    }
                }
            }
            return ImmutableMap.copyOf((Map) newLinkedHashMap);
        } finally {
            this.f8252r.recordHits(i2);
            this.f8252r.recordMisses(i3);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.f8254t;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet(this);
        this.f8254t = keySet;
        return keySet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    ImmutableMap l(Iterable iterable) {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        int i2 = 0;
        int i3 = 0;
        for (Object obj : iterable) {
            V v = get(obj);
            if (v == null) {
                i3++;
            } else {
                newLinkedHashMap.put(obj, v);
                i2++;
            }
        }
        this.f8252r.recordHits(i2);
        this.f8252r.recordMisses(i3);
        return ImmutableMap.copyOf((Map) newLinkedHashMap);
    }

    @NullableDecl
    Object m(ReferenceEntry referenceEntry, long j2) {
        V v;
        if (referenceEntry.getKey() == null || (v = referenceEntry.getValueReference().get()) == null || q(referenceEntry, j2)) {
            return null;
        }
        return v;
    }

    Object n(Object obj) {
        return j(obj, this.f8253s);
    }

    int o(@NullableDecl Object obj) {
        return F(this.f8239e.hash(obj));
    }

    void p(Iterable iterable) {
        for (Object obj : iterable) {
            remove(obj);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v);
        int o2 = o(k2);
        return (V) G(o2).H(k2, o2, v, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k2, V v) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v);
        int o2 = o(k2);
        return (V) G(o2).H(k2, o2, v, true);
    }

    boolean q(ReferenceEntry referenceEntry, long j2) {
        Preconditions.checkNotNull(referenceEntry);
        if (!h() || j2 - referenceEntry.getAccessTime() < this.f8245k) {
            return i() && j2 - referenceEntry.getWriteTime() >= this.f8246l;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b9  */
    @NullableDecl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    Map r(Set set, CacheLoader cacheLoader) {
        Preconditions.checkNotNull(cacheLoader);
        Preconditions.checkNotNull(set);
        Stopwatch createStarted = Stopwatch.createStarted();
        boolean z = true;
        boolean z2 = false;
        try {
            try {
                try {
                    Map<K, V> loadAll = cacheLoader.loadAll(set);
                    if (loadAll == null) {
                        this.f8252r.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
                        throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null map from loadAll");
                    }
                    createStarted.stop();
                    for (Map.Entry<K, V> entry : loadAll.entrySet()) {
                        K key = entry.getKey();
                        V value = entry.getValue();
                        if (key == null || value == null) {
                            z2 = true;
                        } else {
                            put(key, value);
                        }
                    }
                    if (!z2) {
                        this.f8252r.recordLoadSuccess(createStarted.elapsed(TimeUnit.NANOSECONDS));
                        return loadAll;
                    }
                    this.f8252r.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
                    throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null keys or values from loadAll");
                } catch (CacheLoader.UnsupportedLoadingOperationException e2) {
                    try {
                        throw e2;
                    } catch (Throwable th) {
                        th = th;
                        if (!z) {
                            this.f8252r.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    throw new ExecutionException(e3);
                }
            } catch (Error e4) {
                throw new ExecutionError(e4);
            } catch (InterruptedException e5) {
                Thread.currentThread().interrupt();
                throw new ExecutionException(e5);
            } catch (RuntimeException e6) {
                throw new UncheckedExecutionException(e6);
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
            if (!z) {
            }
            throw th;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int o2 = o(obj);
        return (V) G(o2).O(obj, o2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int o2 = o(obj);
        return G(o2).P(obj, o2, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k2, V v) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v);
        int o2 = o(k2);
        return (V) G(o2).V(k2, o2, v);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k2, @NullableDecl V v, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        if (v == null) {
            return false;
        }
        int o2 = o(k2);
        return G(o2).W(k2, o2, v, v2);
    }

    long s() {
        Segment[] segmentArr;
        long j2 = 0;
        for (int i2 = 0; i2 < this.f8237c.length; i2++) {
            j2 += Math.max(0, segmentArr[i2].f8292b);
        }
        return j2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return Ints.saturatedCast(s());
    }

    final Segment[] t(int i2) {
        return new Segment[i2];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.u;
        if (collection != null) {
            return collection;
        }
        Values values = new Values(this);
        this.u = values;
        return values;
    }

    void x() {
        while (true) {
            RemovalNotification<K, V> removalNotification = (RemovalNotification) this.f8248n.poll();
            if (removalNotification == null) {
                return;
            }
            try {
                this.f8249o.onRemoval(removalNotification);
            } catch (Throwable th) {
                w.log(Level.WARNING, "Exception thrown by removal listener", th);
            }
        }
    }

    void y(ReferenceEntry referenceEntry) {
        int hash = referenceEntry.getHash();
        G(hash).I(referenceEntry, hash);
    }

    void z(ValueReference valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        G(hash).J(entry.getKey(), hash, valueReference);
    }
}
