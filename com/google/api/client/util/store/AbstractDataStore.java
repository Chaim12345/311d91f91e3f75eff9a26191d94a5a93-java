package com.google.api.client.util.store;

import com.google.api.client.util.Preconditions;
import java.io.Serializable;
/* loaded from: classes2.dex */
public abstract class AbstractDataStore<V extends Serializable> implements DataStore<V> {
    private final DataStoreFactory dataStoreFactory;
    private final String id;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDataStore(DataStoreFactory dataStoreFactory, String str) {
        this.dataStoreFactory = (DataStoreFactory) Preconditions.checkNotNull(dataStoreFactory);
        this.id = (String) Preconditions.checkNotNull(str);
    }

    @Override // com.google.api.client.util.store.DataStore
    public boolean containsKey(String str) {
        return get(str) != null;
    }

    @Override // com.google.api.client.util.store.DataStore
    public boolean containsValue(V v) {
        return values().contains(v);
    }

    @Override // com.google.api.client.util.store.DataStore
    public DataStoreFactory getDataStoreFactory() {
        return this.dataStoreFactory;
    }

    @Override // com.google.api.client.util.store.DataStore
    public final String getId() {
        return this.id;
    }

    @Override // com.google.api.client.util.store.DataStore
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.api.client.util.store.DataStore
    public int size() {
        return keySet().size();
    }
}
