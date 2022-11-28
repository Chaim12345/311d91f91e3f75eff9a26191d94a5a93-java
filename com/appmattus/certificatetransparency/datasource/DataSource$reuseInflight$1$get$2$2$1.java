package com.appmattus.certificatetransparency.datasource;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$reuseInflight$1$get$2$2$1", f = "DataSource.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DataSource$reuseInflight$1$get$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f4597a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Deferred f4598b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ DataSource$reuseInflight$1 f4599c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSource$reuseInflight$1$get$2$2$1(Deferred deferred, DataSource$reuseInflight$1 dataSource$reuseInflight$1, Continuation continuation) {
        super(2, continuation);
        this.f4598b = deferred;
        this.f4599c = dataSource$reuseInflight$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new DataSource$reuseInflight$1$get$2$2$1(this.f4598b, this.f4599c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((DataSource$reuseInflight$1$get$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4597a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Deferred deferred = this.f4598b;
            this.f4597a = 1;
            if (deferred.join(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        this.f4599c.job = null;
        return Unit.INSTANCE;
    }
}
