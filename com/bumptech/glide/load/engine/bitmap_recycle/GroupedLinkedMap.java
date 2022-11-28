package com.bumptech.glide.load.engine.bitmap_recycle;

import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
class GroupedLinkedMap<K extends Poolable, V> {
    private final LinkedEntry<K, V> head = new LinkedEntry<>();
    private final Map<K, LinkedEntry<K, V>> keyToEntry = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LinkedEntry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final Object f4749a;

        /* renamed from: b  reason: collision with root package name */
        LinkedEntry f4750b;

        /* renamed from: c  reason: collision with root package name */
        LinkedEntry f4751c;
        private List<V> values;

        LinkedEntry() {
            this(null);
        }

        LinkedEntry(Object obj) {
            this.f4751c = this;
            this.f4750b = this;
            this.f4749a = obj;
        }

        public void add(V v) {
            if (this.values == null) {
                this.values = new ArrayList();
            }
            this.values.add(v);
        }

        @Nullable
        public V removeLast() {
            int size = size();
            if (size > 0) {
                return this.values.remove(size - 1);
            }
            return null;
        }

        public int size() {
            List<V> list = this.values;
            if (list != null) {
                return list.size();
            }
            return 0;
        }
    }

    private void makeHead(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        LinkedEntry<K, V> linkedEntry2 = this.head;
        linkedEntry.f4751c = linkedEntry2;
        linkedEntry.f4750b = linkedEntry2.f4750b;
        updateEntry(linkedEntry);
    }

    private void makeTail(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        LinkedEntry<K, V> linkedEntry2 = this.head;
        linkedEntry.f4751c = linkedEntry2.f4751c;
        linkedEntry.f4750b = linkedEntry2;
        updateEntry(linkedEntry);
    }

    private static <K, V> void removeEntry(LinkedEntry<K, V> linkedEntry) {
        LinkedEntry linkedEntry2 = linkedEntry.f4751c;
        linkedEntry2.f4750b = linkedEntry.f4750b;
        linkedEntry.f4750b.f4751c = linkedEntry2;
    }

    private static <K, V> void updateEntry(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.f4750b.f4751c = linkedEntry;
        linkedEntry.f4751c.f4750b = linkedEntry;
    }

    @Nullable
    public V get(K k2) {
        LinkedEntry<K, V> linkedEntry = this.keyToEntry.get(k2);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(k2);
            this.keyToEntry.put(k2, linkedEntry);
        } else {
            k2.offer();
        }
        makeHead(linkedEntry);
        return linkedEntry.removeLast();
    }

    public void put(K k2, V v) {
        LinkedEntry<K, V> linkedEntry = this.keyToEntry.get(k2);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(k2);
            makeTail(linkedEntry);
            this.keyToEntry.put(k2, linkedEntry);
        } else {
            k2.offer();
        }
        linkedEntry.add(v);
    }

    @Nullable
    public V removeLast() {
        LinkedEntry<K, V> linkedEntry = this.head;
        while (true) {
            linkedEntry = linkedEntry.f4751c;
            if (linkedEntry.equals(this.head)) {
                return null;
            }
            V removeLast = linkedEntry.removeLast();
            if (removeLast != null) {
                return removeLast;
            }
            removeEntry(linkedEntry);
            this.keyToEntry.remove(linkedEntry.f4749a);
            ((Poolable) linkedEntry.f4749a).offer();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (LinkedEntry linkedEntry = this.head.f4750b; !linkedEntry.equals(this.head); linkedEntry = linkedEntry.f4750b) {
            z = true;
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            sb.append(linkedEntry.f4749a);
            sb.append(AbstractJsonLexerKt.COLON);
            sb.append(linkedEntry.size());
            sb.append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
