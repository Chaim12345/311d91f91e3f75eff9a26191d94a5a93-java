package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import java.util.Map;
@GwtCompatible
/* loaded from: classes2.dex */
class SingletonImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {

    /* renamed from: a  reason: collision with root package name */
    final Object f8983a;

    /* renamed from: b  reason: collision with root package name */
    final Object f8984b;

    /* renamed from: c  reason: collision with root package name */
    final Object f8985c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingletonImmutableTable(Table.Cell cell) {
        this(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingletonImmutableTable(Object obj, Object obj2, Object obj3) {
        this.f8983a = Preconditions.checkNotNull(obj);
        this.f8984b = Preconditions.checkNotNull(obj2);
        this.f8985c = Preconditions.checkNotNull(obj3);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, V> column(C c2) {
        Preconditions.checkNotNull(c2);
        return containsColumn(c2) ? ImmutableMap.of(this.f8983a, this.f8985c) : ImmutableMap.of();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Map column(Object obj) {
        return column((SingletonImmutableTable<R, C, V>) obj);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.of(this.f8984b, ImmutableMap.of(this.f8983a, this.f8985c));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    /* renamed from: g */
    public ImmutableSet b() {
        return ImmutableSet.of(ImmutableTable.f(this.f8983a, this.f8984b, this.f8985c));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    /* renamed from: h */
    public ImmutableCollection c() {
        return ImmutableSet.of(this.f8985c);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.of(this.f8983a, ImmutableMap.of(this.f8984b, this.f8985c));
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return 1;
    }
}
