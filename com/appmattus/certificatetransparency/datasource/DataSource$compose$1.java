package com.appmattus.certificatetransparency.datasource;

import com.appmattus.certificatetransparency.datasource.DataSource;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class DataSource$compose$1 implements DataSource<Value> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DataSource f4565a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DataSource f4566b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataSource$compose$1(DataSource dataSource, DataSource dataSource2) {
        this.f4565a = dataSource;
        this.f4566b = dataSource2;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<Value> compose(@NotNull DataSource<Value> dataSource) {
        return DataSource.DefaultImpls.compose(this, dataSource);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object get(@NotNull Continuation<? super Value> continuation) {
        DataSource$compose$1$get$1 dataSource$compose$1$get$1;
        Object coroutine_suspended;
        int i2;
        DataSource$compose$1 dataSource$compose$1;
        Object isValid;
        Object obj;
        if (continuation instanceof DataSource$compose$1$get$1) {
            dataSource$compose$1$get$1 = (DataSource$compose$1$get$1) continuation;
            int i3 = dataSource$compose$1$get$1.f4571e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                dataSource$compose$1$get$1.f4571e = i3 - Integer.MIN_VALUE;
                Object obj2 = dataSource$compose$1$get$1.f4569c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = dataSource$compose$1$get$1.f4571e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj2);
                    DataSource dataSource = this.f4565a;
                    dataSource$compose$1$get$1.f4567a = this;
                    dataSource$compose$1$get$1.f4571e = 1;
                    obj2 = dataSource.get(dataSource$compose$1$get$1);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    dataSource$compose$1 = this;
                } else if (i2 == 1) {
                    dataSource$compose$1 = (DataSource$compose$1) dataSource$compose$1$get$1.f4567a;
                    ResultKt.throwOnFailure(obj2);
                } else if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            Object obj3 = dataSource$compose$1$get$1.f4567a;
                            ResultKt.throwOnFailure(obj2);
                            return obj3;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    dataSource$compose$1 = (DataSource$compose$1) dataSource$compose$1$get$1.f4567a;
                    ResultKt.throwOnFailure(obj2);
                    if (obj2 == null) {
                        DataSource dataSource2 = dataSource$compose$1.f4565a;
                        dataSource$compose$1$get$1.f4567a = obj2;
                        dataSource$compose$1$get$1.f4571e = 4;
                        return dataSource2.set(obj2, dataSource$compose$1$get$1) == coroutine_suspended ? coroutine_suspended : obj2;
                    }
                    return null;
                } else {
                    Object obj4 = dataSource$compose$1$get$1.f4568b;
                    DataSource$compose$1 dataSource$compose$12 = (DataSource$compose$1) dataSource$compose$1$get$1.f4567a;
                    ResultKt.throwOnFailure(obj2);
                    obj = obj4;
                    dataSource$compose$1 = dataSource$compose$12;
                    if (!((Boolean) obj2).booleanValue()) {
                        return obj;
                    }
                    DataSource dataSource3 = dataSource$compose$1.f4566b;
                    dataSource$compose$1$get$1.f4567a = dataSource$compose$1;
                    dataSource$compose$1$get$1.f4568b = null;
                    dataSource$compose$1$get$1.f4571e = 3;
                    obj2 = dataSource3.get(dataSource$compose$1$get$1);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (obj2 == null) {
                    }
                }
                dataSource$compose$1$get$1.f4567a = dataSource$compose$1;
                dataSource$compose$1$get$1.f4568b = obj2;
                dataSource$compose$1$get$1.f4571e = 2;
                isValid = dataSource$compose$1.isValid(obj2, dataSource$compose$1$get$1);
                if (isValid != coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = obj2;
                obj2 = isValid;
                if (!((Boolean) obj2).booleanValue()) {
                }
            }
        }
        dataSource$compose$1$get$1 = new DataSource$compose$1$get$1(this, continuation);
        Object obj22 = dataSource$compose$1$get$1.f4569c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = dataSource$compose$1$get$1.f4571e;
        if (i2 != 0) {
        }
        dataSource$compose$1$get$1.f4567a = dataSource$compose$1;
        dataSource$compose$1$get$1.f4568b = obj22;
        dataSource$compose$1$get$1.f4571e = 2;
        isValid = dataSource$compose$1.isValid(obj22, dataSource$compose$1$get$1);
        if (isValid != coroutine_suspended) {
        }
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    public Object isValid(@Nullable Value value, @NotNull Continuation<? super Boolean> continuation) {
        return this.f4565a.isValid(value, continuation);
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
        Object coroutine_suspended;
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new DataSource$compose$1$set$2(this.f4565a, value, this.f4566b, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return coroutineScope == coroutine_suspended ? coroutineScope : Unit.INSTANCE;
    }
}
