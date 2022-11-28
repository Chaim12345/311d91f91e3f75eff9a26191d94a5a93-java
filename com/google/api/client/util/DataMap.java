package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class DataMap extends AbstractMap<String, Object> {

    /* renamed from: a  reason: collision with root package name */
    final Object f8078a;

    /* renamed from: b  reason: collision with root package name */
    final ClassInfo f8079b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class Entry implements Map.Entry<String, Object> {
        private final FieldInfo fieldInfo;
        private Object fieldValue;

        Entry(FieldInfo fieldInfo, Object obj) {
            this.fieldInfo = fieldInfo;
            this.fieldValue = Preconditions.checkNotNull(obj);
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return getKey().equals(entry.getKey()) && getValue().equals(entry.getValue());
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public String getKey() {
            String name = this.fieldInfo.getName();
            return DataMap.this.f8079b.getIgnoreCase() ? name.toLowerCase(Locale.US) : name;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.fieldValue;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return getKey().hashCode() ^ getValue().hashCode();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            Object obj2 = this.fieldValue;
            this.fieldValue = Preconditions.checkNotNull(obj);
            this.fieldInfo.setValue(DataMap.this.f8078a, obj);
            return obj2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class EntryIterator implements Iterator<Map.Entry<String, Object>> {
        private FieldInfo currentFieldInfo;
        private boolean isComputed;
        private boolean isRemoved;
        private FieldInfo nextFieldInfo;
        private Object nextFieldValue;
        private int nextKeyIndex = -1;

        EntryIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.isComputed) {
                this.isComputed = true;
                Object obj = null;
                while (true) {
                    this.nextFieldValue = obj;
                    if (this.nextFieldValue != null) {
                        break;
                    }
                    int i2 = this.nextKeyIndex + 1;
                    this.nextKeyIndex = i2;
                    if (i2 >= DataMap.this.f8079b.f8077a.size()) {
                        break;
                    }
                    ClassInfo classInfo = DataMap.this.f8079b;
                    FieldInfo fieldInfo = classInfo.getFieldInfo((String) classInfo.f8077a.get(this.nextKeyIndex));
                    this.nextFieldInfo = fieldInfo;
                    obj = fieldInfo.getValue(DataMap.this.f8078a);
                }
            }
            return this.nextFieldValue != null;
        }

        @Override // java.util.Iterator
        public Map.Entry<String, Object> next() {
            if (hasNext()) {
                FieldInfo fieldInfo = this.nextFieldInfo;
                this.currentFieldInfo = fieldInfo;
                Object obj = this.nextFieldValue;
                this.isComputed = false;
                this.isRemoved = false;
                this.nextFieldInfo = null;
                this.nextFieldValue = null;
                return new Entry(fieldInfo, obj);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState((this.currentFieldInfo == null || this.isRemoved) ? false : true);
            this.isRemoved = true;
            this.currentFieldInfo.setValue(DataMap.this.f8078a, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class EntrySet extends AbstractSet<Map.Entry<String, Object>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            for (String str : DataMap.this.f8079b.f8077a) {
                DataMap.this.f8079b.getFieldInfo(str).setValue(DataMap.this.f8078a, null);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            for (String str : DataMap.this.f8079b.f8077a) {
                if (DataMap.this.f8079b.getFieldInfo(str).getValue(DataMap.this.f8078a) != null) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public EntryIterator iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            int i2 = 0;
            for (String str : DataMap.this.f8079b.f8077a) {
                if (DataMap.this.f8079b.getFieldInfo(str).getValue(DataMap.this.f8078a) != null) {
                    i2++;
                }
            }
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataMap(Object obj, boolean z) {
        this.f8078a = obj;
        this.f8079b = ClassInfo.of(obj.getClass(), z);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public EntrySet entrySet() {
        return new EntrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        FieldInfo fieldInfo;
        if ((obj instanceof String) && (fieldInfo = this.f8079b.getFieldInfo((String) obj)) != null) {
            return fieldInfo.getValue(this.f8078a);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(String str, Object obj) {
        FieldInfo fieldInfo = this.f8079b.getFieldInfo(str);
        Preconditions.checkNotNull(fieldInfo, "no field of key " + str);
        Object value = fieldInfo.getValue(this.f8078a);
        fieldInfo.setValue(this.f8078a, Preconditions.checkNotNull(obj));
        return value;
    }
}
