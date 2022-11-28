package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AggregateFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {
    private List<Present<V>> values;

    /* loaded from: classes2.dex */
    static final class ListFuture<V> extends CollectionFuture<V, List<V>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ListFuture(ImmutableCollection immutableCollection, boolean z) {
            super(immutableCollection, z);
            C();
        }

        @Override // com.google.common.util.concurrent.CollectionFuture
        public List<V> combine(List<Present<V>> list) {
            ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
            Iterator<Present<V>> it = list.iterator();
            while (it.hasNext()) {
                Present<V> next = it.next();
                newArrayListWithCapacity.add(next != null ? next.f9487a : null);
            }
            return Collections.unmodifiableList(newArrayListWithCapacity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Present<V> {

        /* renamed from: a  reason: collision with root package name */
        Object f9487a;

        Present(Object obj) {
            this.f9487a = obj;
        }
    }

    CollectionFuture(ImmutableCollection immutableCollection, boolean z) {
        super(immutableCollection, z, true);
        List<Present<V>> of = immutableCollection.isEmpty() ? ImmutableList.of() : Lists.newArrayListWithCapacity(immutableCollection.size());
        for (int i2 = 0; i2 < immutableCollection.size(); i2++) {
            of.add(null);
        }
        this.values = of;
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    final void A(int i2, @NullableDecl Object obj) {
        List<Present<V>> list = this.values;
        if (list != null) {
            list.set(i2, new Present<>(obj));
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    final void B() {
        List<Present<V>> list = this.values;
        if (list != null) {
            set(combine(list));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.util.concurrent.AggregateFuture
    public void D(AggregateFuture.ReleaseResourcesReason releaseResourcesReason) {
        super.D(releaseResourcesReason);
        this.values = null;
    }

    abstract Object combine(List list);
}
