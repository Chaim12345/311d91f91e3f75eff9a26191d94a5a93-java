package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.datasource.DataSource;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public class InMemoryDataSource<Value> implements DataSource<Value> {
    @Nullable
    private Value cachedValue;

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object b(InMemoryDataSource inMemoryDataSource, Object obj, Continuation continuation) {
        inMemoryDataSource.cachedValue = obj;
        return Unit.INSTANCE;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<Value> compose(@NotNull DataSource<Value> dataSource) {
        return DataSource.DefaultImpls.compose(this, dataSource);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    public Object get(@NotNull Continuation<? super Value> continuation) {
        return this.cachedValue;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    public Object isValid(@Nullable Value value, @NotNull Continuation<? super Boolean> continuation) {
        return DataSource.DefaultImpls.isValid(this, value, continuation);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public <MappedValue> DataSource<MappedValue> oneWayTransform(@NotNull Function1<? super Value, ? extends MappedValue> function1) {
        return DataSource.DefaultImpls.oneWayTransform(this, function1);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<Value> plus(@NotNull DataSource<Value> dataSource) {
        return DataSource.DefaultImpls.plus(this, dataSource);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<Value> reuseInflight() {
        return DataSource.DefaultImpls.reuseInflight(this);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    public Object set(@NotNull Value value, @NotNull Continuation<? super Unit> continuation) {
        return b(this, value, continuation);
    }
}
