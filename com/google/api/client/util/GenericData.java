package com.google.api.client.util;

import com.google.api.client.util.DataMap;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public class GenericData extends AbstractMap<String, Object> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    Map f8090a;

    /* renamed from: b  reason: collision with root package name */
    final ClassInfo f8091b;

    /* loaded from: classes2.dex */
    final class EntryIterator implements Iterator<Map.Entry<String, Object>> {
        private final Iterator<Map.Entry<String, Object>> fieldIterator;
        private boolean startedUnknown;
        private final Iterator<Map.Entry<String, Object>> unknownIterator;

        EntryIterator(GenericData genericData, DataMap.EntrySet entrySet) {
            this.fieldIterator = entrySet.iterator();
            this.unknownIterator = genericData.f8090a.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.fieldIterator.hasNext() || this.unknownIterator.hasNext();
        }

        @Override // java.util.Iterator
        public Map.Entry<String, Object> next() {
            Iterator<Map.Entry<String, Object>> it;
            if (!this.startedUnknown) {
                if (this.fieldIterator.hasNext()) {
                    it = this.fieldIterator;
                    return it.next();
                }
                this.startedUnknown = true;
            }
            it = this.unknownIterator;
            return it.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.startedUnknown) {
                this.unknownIterator.remove();
            }
            this.fieldIterator.remove();
        }
    }

    /* loaded from: classes2.dex */
    final class EntrySet extends AbstractSet<Map.Entry<String, Object>> {
        private final DataMap.EntrySet dataEntrySet;

        EntrySet() {
            this.dataEntrySet = new DataMap(GenericData.this, GenericData.this.f8091b.getIgnoreCase()).entrySet();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            GenericData.this.f8090a.clear();
            this.dataEntrySet.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<String, Object>> iterator() {
            return new EntryIterator(GenericData.this, this.dataEntrySet);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return GenericData.this.f8090a.size() + this.dataEntrySet.size();
        }
    }

    /* loaded from: classes2.dex */
    public enum Flags {
        IGNORE_CASE
    }

    public GenericData() {
        this(EnumSet.noneOf(Flags.class));
    }

    public GenericData(EnumSet<Flags> enumSet) {
        this.f8090a = ArrayMap.create();
        this.f8091b = ClassInfo.of(getClass(), enumSet.contains(Flags.IGNORE_CASE));
    }

    @Override // java.util.AbstractMap
    public GenericData clone() {
        try {
            GenericData genericData = (GenericData) super.clone();
            Data.deepCopy(this, genericData);
            genericData.f8090a = (Map) Data.clone(this.f8090a);
            return genericData;
        } catch (CloneNotSupportedException e2) {
            throw new IllegalStateException(e2);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return new EntrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof GenericData)) {
            return false;
        }
        GenericData genericData = (GenericData) obj;
        return super.equals(genericData) && java.util.Objects.equals(this.f8091b, genericData.f8091b);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            FieldInfo fieldInfo = this.f8091b.getFieldInfo(str);
            if (fieldInfo != null) {
                return fieldInfo.getValue(this);
            }
            if (this.f8091b.getIgnoreCase()) {
                str = str.toLowerCase(Locale.US);
            }
            return this.f8090a.get(str);
        }
        return null;
    }

    public final ClassInfo getClassInfo() {
        return this.f8091b;
    }

    public final Map<String, Object> getUnknownKeys() {
        return this.f8090a;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return java.util.Objects.hash(Integer.valueOf(super.hashCode()), this.f8091b);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(String str, Object obj) {
        FieldInfo fieldInfo = this.f8091b.getFieldInfo(str);
        if (fieldInfo != null) {
            Object value = fieldInfo.getValue(this);
            fieldInfo.setValue(this, obj);
            return value;
        }
        if (this.f8091b.getIgnoreCase()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.f8090a.put(str, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends String, ?> map) {
        for (Map.Entry<? extends String, ?> entry : map.entrySet()) {
            set(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (this.f8091b.getFieldInfo(str) == null) {
                if (this.f8091b.getIgnoreCase()) {
                    str = str.toLowerCase(Locale.US);
                }
                return this.f8090a.remove(str);
            }
            throw new UnsupportedOperationException();
        }
        return null;
    }

    public GenericData set(String str, Object obj) {
        FieldInfo fieldInfo = this.f8091b.getFieldInfo(str);
        if (fieldInfo != null) {
            fieldInfo.setValue(this, obj);
        } else {
            if (this.f8091b.getIgnoreCase()) {
                str = str.toLowerCase(Locale.US);
            }
            this.f8090a.put(str, obj);
        }
        return this;
    }

    public final void setUnknownKeys(Map<String, Object> map) {
        this.f8090a = map;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return "GenericData{classInfo=" + this.f8091b.f8077a + ", " + super.toString() + "}";
    }
}
