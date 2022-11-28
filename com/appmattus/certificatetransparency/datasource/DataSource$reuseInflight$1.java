package com.appmattus.certificatetransparency.datasource;

import com.appmattus.certificatetransparency.datasource.DataSource;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class DataSource$reuseInflight$1 implements DataSource<Value> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DataSource f4588a;
    @Nullable
    private Deferred<? extends Value> job;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataSource$reuseInflight$1(DataSource dataSource) {
        this.f4588a = dataSource;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<Value> compose(@NotNull DataSource<Value> dataSource) {
        return DataSource.DefaultImpls.compose(this, dataSource);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0057 A[PHI: r7 
      PHI: (r7v7 java.lang.Object) = (r7v6 java.lang.Object), (r7v1 java.lang.Object) binds: [B:20:0x0054, B:12:0x0028] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object get(@NotNull Continuation<? super Value> continuation) {
        DataSource$reuseInflight$1$get$1 dataSource$reuseInflight$1$get$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof DataSource$reuseInflight$1$get$1) {
            dataSource$reuseInflight$1$get$1 = (DataSource$reuseInflight$1$get$1) continuation;
            int i3 = dataSource$reuseInflight$1$get$1.f4591c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                dataSource$reuseInflight$1$get$1.f4591c = i3 - Integer.MIN_VALUE;
                Object obj = dataSource$reuseInflight$1$get$1.f4589a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = dataSource$reuseInflight$1$get$1.f4591c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    DataSource$reuseInflight$1$get$2 dataSource$reuseInflight$1$get$2 = new DataSource$reuseInflight$1$get$2(this, this.f4588a, null);
                    dataSource$reuseInflight$1$get$1.f4591c = 1;
                    obj = CoroutineScopeKt.coroutineScope(dataSource$reuseInflight$1$get$2, dataSource$reuseInflight$1$get$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                dataSource$reuseInflight$1$get$1.f4591c = 2;
                obj = ((Deferred) obj).await(dataSource$reuseInflight$1$get$1);
                return obj != coroutine_suspended ? coroutine_suspended : obj;
            }
        }
        dataSource$reuseInflight$1$get$1 = new DataSource$reuseInflight$1$get$1(this, continuation);
        Object obj2 = dataSource$reuseInflight$1$get$1.f4589a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = dataSource$reuseInflight$1$get$1.f4591c;
        if (i2 != 0) {
        }
        dataSource$reuseInflight$1$get$1.f4591c = 2;
        obj2 = ((Deferred) obj2).await(dataSource$reuseInflight$1$get$1);
        if (obj2 != coroutine_suspended) {
        }
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    public Object isValid(@Nullable Value value, @NotNull Continuation<? super Boolean> continuation) {
        return this.f4588a.isValid(value, continuation);
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
        Object obj = this.f4588a.set(value, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return obj == coroutine_suspended ? obj : Unit.INSTANCE;
    }
}
