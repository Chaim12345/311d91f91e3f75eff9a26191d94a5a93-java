package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {
    private final Map<K, Provider<V>> contributingMap;

    /* loaded from: classes.dex */
    public static abstract class Builder<K, V, V2> {

        /* renamed from: a  reason: collision with root package name */
        final LinkedHashMap f5503a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(int i2) {
            this.f5503a = DaggerCollections.newLinkedHashMapWithExpectedSize(i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        public Builder put(Object obj, Provider provider) {
            this.f5503a.put(Preconditions.checkNotNull(obj, "key"), Preconditions.checkNotNull(provider, "provider"));
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder putAll(Provider provider) {
            if (provider instanceof DelegateFactory) {
                return putAll(((DelegateFactory) provider).a());
            }
            this.f5503a.putAll(((AbstractMapFactory) provider).contributingMap);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractMapFactory(Map map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map b() {
        return this.contributingMap;
    }
}
