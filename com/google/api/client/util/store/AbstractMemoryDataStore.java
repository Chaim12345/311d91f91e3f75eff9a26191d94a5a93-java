package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes2.dex */
public class AbstractMemoryDataStore<V extends Serializable> extends AbstractDataStore<V> {

    /* renamed from: a  reason: collision with root package name */
    protected HashMap f8100a;
    private final Lock lock;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMemoryDataStore(DataStoreFactory dataStoreFactory, String str) {
        super(dataStoreFactory, str);
        this.lock = new ReentrantLock();
        this.f8100a = Maps.newHashMap();
    }

    @Override // com.google.api.client.util.store.DataStore
    public final DataStore<V> clear() {
        this.lock.lock();
        try {
            this.f8100a.clear();
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public boolean containsKey(String str) {
        if (str == null) {
            return false;
        }
        this.lock.lock();
        try {
            return this.f8100a.containsKey(str);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public boolean containsValue(V v) {
        if (v == null) {
            return false;
        }
        this.lock.lock();
        try {
            byte[] serialize = IOUtils.serialize(v);
            for (byte[] bArr : this.f8100a.values()) {
                if (Arrays.equals(serialize, bArr)) {
                    return true;
                }
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public DataStore<V> delete(String str) {
        if (str == null) {
            return this;
        }
        this.lock.lock();
        try {
            this.f8100a.remove(str);
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public final V get(String str) {
        if (str == null) {
            return null;
        }
        this.lock.lock();
        try {
            return (V) IOUtils.deserialize((byte[]) this.f8100a.get(str));
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public boolean isEmpty() {
        this.lock.lock();
        try {
            return this.f8100a.isEmpty();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public final Set<String> keySet() {
        this.lock.lock();
        try {
            return Collections.unmodifiableSet(this.f8100a.keySet());
        } finally {
            this.lock.unlock();
        }
    }

    public void save() {
    }

    @Override // com.google.api.client.util.store.DataStore
    public final DataStore<V> set(String str, V v) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(v);
        this.lock.lock();
        try {
            this.f8100a.put(str, IOUtils.serialize(v));
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public int size() {
        this.lock.lock();
        try {
            return this.f8100a.size();
        } finally {
            this.lock.unlock();
        }
    }

    public String toString() {
        return DataStoreUtils.toString(this);
    }

    @Override // com.google.api.client.util.store.DataStore
    public final Collection<V> values() {
        this.lock.lock();
        try {
            ArrayList newArrayList = Lists.newArrayList();
            for (byte[] bArr : this.f8100a.values()) {
                newArrayList.add(IOUtils.deserialize(bArr));
            }
            return Collections.unmodifiableList(newArrayList);
        } finally {
            this.lock.unlock();
        }
    }
}
