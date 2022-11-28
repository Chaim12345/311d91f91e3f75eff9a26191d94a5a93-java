package com.appmattus.certificatetransparency.datasource;

import com.appmattus.certificatetransparency.datasource.DataSource;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class DataSource$oneWayTransform$1 implements DataSource<MappedValue> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DataSource f4582a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function1 f4583b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataSource$oneWayTransform$1(DataSource dataSource, Function1 function1) {
        this.f4582a = dataSource;
        this.f4583b = function1;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<MappedValue> compose(@NotNull DataSource<MappedValue> dataSource) {
        return DataSource.DefaultImpls.compose(this, dataSource);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004f A[ORIG_RETURN, RETURN] */
    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object get(@NotNull Continuation<? super MappedValue> continuation) {
        DataSource$oneWayTransform$1$get$1 dataSource$oneWayTransform$1$get$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        DataSource$oneWayTransform$1 dataSource$oneWayTransform$1;
        if (continuation instanceof DataSource$oneWayTransform$1$get$1) {
            dataSource$oneWayTransform$1$get$1 = (DataSource$oneWayTransform$1$get$1) continuation;
            int i3 = dataSource$oneWayTransform$1$get$1.f4587d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                dataSource$oneWayTransform$1$get$1.f4587d = i3 - Integer.MIN_VALUE;
                obj = dataSource$oneWayTransform$1$get$1.f4585b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = dataSource$oneWayTransform$1$get$1.f4587d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    DataSource dataSource = this.f4582a;
                    dataSource$oneWayTransform$1$get$1.f4584a = this;
                    dataSource$oneWayTransform$1$get$1.f4587d = 1;
                    obj = dataSource.get(dataSource$oneWayTransform$1$get$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    dataSource$oneWayTransform$1 = this;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    dataSource$oneWayTransform$1 = (DataSource$oneWayTransform$1) dataSource$oneWayTransform$1$get$1.f4584a;
                    ResultKt.throwOnFailure(obj);
                }
                if (obj == null) {
                    return dataSource$oneWayTransform$1.f4583b.invoke(obj);
                }
                return null;
            }
        }
        dataSource$oneWayTransform$1$get$1 = new DataSource$oneWayTransform$1$get$1(this, continuation);
        obj = dataSource$oneWayTransform$1$get$1.f4585b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = dataSource$oneWayTransform$1$get$1.f4587d;
        if (i2 != 0) {
        }
        if (obj == null) {
        }
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    public Object isValid(@Nullable MappedValue mappedvalue, @NotNull Continuation<? super Boolean> continuation) {
        return DataSource.DefaultImpls.isValid(this, mappedvalue, continuation);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public <MappedValue> DataSource<MappedValue> oneWayTransform(@NotNull Function1<? super MappedValue, ? extends MappedValue> function1) {
        return DataSource.DefaultImpls.oneWayTransform(this, function1);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<MappedValue> plus(@NotNull DataSource<MappedValue> dataSource) {
        return DataSource.DefaultImpls.plus(this, dataSource);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<MappedValue> reuseInflight() {
        return DataSource.DefaultImpls.reuseInflight(this);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    public Object set(@NotNull MappedValue mappedvalue, @NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
