package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public final class SingletonImmutableSet<E> extends ImmutableSet<E> {

    /* renamed from: a  reason: collision with root package name */
    final transient Object f8982a;
    @LazyInit
    private transient int cachedHashCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingletonImmutableSet(Object obj) {
        this.f8982a = Preconditions.checkNotNull(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingletonImmutableSet(Object obj, int i2) {
        this.f8982a = obj;
        this.cachedHashCode = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int a(Object[] objArr, int i2) {
        objArr[i2] = this.f8982a;
        return i2 + 1;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.f8982a.equals(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList h() {
        return ImmutableList.of(this.f8982a);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i2 = this.cachedHashCode;
        if (i2 == 0) {
            int hashCode = this.f8982a.hashCode();
            this.cachedHashCode = hashCode;
            return hashCode;
        }
        return i2;
    }

    @Override // com.google.common.collect.ImmutableSet
    boolean i() {
        return this.cachedHashCode != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.f8982a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return AbstractJsonLexerKt.BEGIN_LIST + this.f8982a.toString() + AbstractJsonLexerKt.END_LIST;
    }
}
