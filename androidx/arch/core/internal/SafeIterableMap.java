package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: a  reason: collision with root package name */
    Entry f610a;
    private Entry<K, V> mEnd;
    private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators = new WeakHashMap<>();
    private int mSize = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AscendingIterator<K, V> extends ListIterator<K, V> {
        AscendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry a(Entry entry) {
            return entry.f614d;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry b(Entry entry) {
            return entry.f613c;
        }
    }

    /* loaded from: classes.dex */
    private static class DescendingIterator<K, V> extends ListIterator<K, V> {
        DescendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry a(Entry entry) {
            return entry.f613c;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry b(Entry entry) {
            return entry.f614d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Entry<K, V> implements Map.Entry<K, V> {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        final Object f611a;
        @NonNull

        /* renamed from: b  reason: collision with root package name */
        final Object f612b;

        /* renamed from: c  reason: collision with root package name */
        Entry f613c;

        /* renamed from: d  reason: collision with root package name */
        Entry f614d;

        Entry(@NonNull Object obj, @NonNull Object obj2) {
            this.f611a = obj;
            this.f612b = obj2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Entry) {
                Entry entry = (Entry) obj;
                return this.f611a.equals(entry.f611a) && this.f612b.equals(entry.f612b);
            }
            return false;
        }

        @Override // java.util.Map.Entry
        @NonNull
        public K getKey() {
            return (K) this.f611a;
        }

        @Override // java.util.Map.Entry
        @NonNull
        public V getValue() {
            return (V) this.f612b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f611a.hashCode() ^ this.f612b.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f611a + "=" + this.f612b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class IteratorWithAdditions implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
        private boolean mBeforeStart = true;
        private Entry<K, V> mCurrent;

        IteratorWithAdditions() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.mBeforeStart) {
                return SafeIterableMap.this.f610a != null;
            }
            Entry<K, V> entry = this.mCurrent;
            return (entry == null || entry.f613c == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            Entry<K, V> entry;
            if (this.mBeforeStart) {
                this.mBeforeStart = false;
                entry = SafeIterableMap.this.f610a;
            } else {
                Entry<K, V> entry2 = this.mCurrent;
                entry = entry2 != null ? entry2.f613c : null;
            }
            this.mCurrent = entry;
            return this.mCurrent;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public void supportRemove(@NonNull Entry<K, V> entry) {
            Entry<K, V> entry2 = this.mCurrent;
            if (entry == entry2) {
                Entry<K, V> entry3 = entry2.f614d;
                this.mCurrent = entry3;
                this.mBeforeStart = entry3 == null;
            }
        }
    }

    /* loaded from: classes.dex */
    private static abstract class ListIterator<K, V> implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {

        /* renamed from: a  reason: collision with root package name */
        Entry f616a;

        /* renamed from: b  reason: collision with root package name */
        Entry f617b;

        ListIterator(Entry entry, Entry entry2) {
            this.f616a = entry2;
            this.f617b = entry;
        }

        private Entry<K, V> nextNode() {
            Entry entry = this.f617b;
            Entry entry2 = this.f616a;
            if (entry == entry2 || entry2 == null) {
                return null;
            }
            return b(entry);
        }

        abstract Entry a(Entry entry);

        abstract Entry b(Entry entry);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f617b != null;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            Entry entry = this.f617b;
            this.f617b = nextNode();
            return entry;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public void supportRemove(@NonNull Entry<K, V> entry) {
            if (this.f616a == entry && entry == this.f617b) {
                this.f617b = null;
                this.f616a = null;
            }
            Entry<K, V> entry2 = this.f616a;
            if (entry2 == entry) {
                this.f616a = a(entry2);
            }
            if (this.f617b == entry) {
                this.f617b = nextNode();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface SupportRemove<K, V> {
        void supportRemove(@NonNull Entry<K, V> entry);
    }

    protected Entry a(Object obj) {
        Entry entry = this.f610a;
        while (entry != null && !entry.f611a.equals(obj)) {
            entry = entry.f613c;
        }
        return entry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Entry b(@NonNull Object obj, @NonNull Object obj2) {
        Entry<K, V> entry = new Entry<>(obj, obj2);
        this.mSize++;
        Entry<K, V> entry2 = this.mEnd;
        if (entry2 == null) {
            this.f610a = entry;
        } else {
            entry2.f613c = entry;
            entry.f614d = entry2;
        }
        this.mEnd = entry;
        return entry;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        DescendingIterator descendingIterator = new DescendingIterator(this.mEnd, this.f610a);
        this.mIterators.put(descendingIterator, Boolean.FALSE);
        return descendingIterator;
    }

    public Map.Entry<K, V> eldest() {
        return this.f610a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SafeIterableMap) {
            SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
            if (size() != safeIterableMap.size()) {
                return false;
            }
            Iterator<Map.Entry<K, V>> it = iterator();
            Iterator<Map.Entry<K, V>> it2 = safeIterableMap.iterator();
            while (it.hasNext() && it2.hasNext()) {
                Map.Entry<K, V> next = it.next();
                Map.Entry<K, V> next2 = it2.next();
                if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                    return false;
                }
            }
            return (it.hasNext() || it2.hasNext()) ? false : true;
        }
        return false;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().hashCode();
        }
        return i2;
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        AscendingIterator ascendingIterator = new AscendingIterator(this.f610a, this.mEnd);
        this.mIterators.put(ascendingIterator, Boolean.FALSE);
        return ascendingIterator;
    }

    public SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions() {
        SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
        this.mIterators.put(iteratorWithAdditions, Boolean.FALSE);
        return iteratorWithAdditions;
    }

    public Map.Entry<K, V> newest() {
        return this.mEnd;
    }

    public V putIfAbsent(@NonNull K k2, @NonNull V v) {
        Entry a2 = a(k2);
        if (a2 != null) {
            return (V) a2.f612b;
        }
        b(k2, v);
        return null;
    }

    public V remove(@NonNull K k2) {
        Entry<K, V> a2 = a(k2);
        if (a2 == null) {
            return null;
        }
        this.mSize--;
        if (!this.mIterators.isEmpty()) {
            for (SupportRemove<K, V> supportRemove : this.mIterators.keySet()) {
                supportRemove.supportRemove(a2);
            }
        }
        Entry<K, V> entry = a2.f614d;
        Entry entry2 = a2.f613c;
        if (entry != null) {
            entry.f613c = entry2;
        } else {
            this.f610a = entry2;
        }
        Entry entry3 = a2.f613c;
        if (entry3 != null) {
            entry3.f614d = entry;
        } else {
            this.mEnd = entry;
        }
        a2.f613c = null;
        a2.f614d = null;
        return (V) a2.f612b;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
