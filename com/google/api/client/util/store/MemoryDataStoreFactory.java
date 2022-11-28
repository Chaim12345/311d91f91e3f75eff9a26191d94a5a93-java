package com.google.api.client.util.store;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class MemoryDataStoreFactory extends AbstractDataStoreFactory {

    /* loaded from: classes2.dex */
    static class InstanceHolder {

        /* renamed from: a  reason: collision with root package name */
        static final MemoryDataStoreFactory f8101a = new MemoryDataStoreFactory();
    }

    /* loaded from: classes2.dex */
    static class MemoryDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        MemoryDataStore(MemoryDataStoreFactory memoryDataStoreFactory, String str) {
            super(memoryDataStoreFactory, str);
        }

        @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
        public MemoryDataStoreFactory getDataStoreFactory() {
            return (MemoryDataStoreFactory) super.getDataStoreFactory();
        }
    }

    public static MemoryDataStoreFactory getDefaultInstance() {
        return InstanceHolder.f8101a;
    }

    @Override // com.google.api.client.util.store.AbstractDataStoreFactory
    protected DataStore a(String str) {
        return new MemoryDataStore(this, str);
    }
}
