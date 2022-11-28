package com.appmattus.certificatetransparency.datasource;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$reuseInflight$1$get$2", f = "DataSource.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class DataSource$reuseInflight$1$get$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Deferred<? extends Value>>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    int f4592a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DataSource$reuseInflight$1 f4593b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ DataSource f4594c;

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$reuseInflight$1$get$2$1", f = "DataSource.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.appmattus.certificatetransparency.datasource.DataSource$reuseInflight$1$get$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Value>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f4595a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ DataSource f4596b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DataSource dataSource, Continuation continuation) {
            super(2, continuation);
            this.f4596b = dataSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.f4596b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Value> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f4595a;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                DataSource dataSource = this.f4596b;
                this.f4595a = 1;
                obj = dataSource.get(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSource$reuseInflight$1$get$2(DataSource$reuseInflight$1 dataSource$reuseInflight$1, DataSource dataSource, Continuation continuation) {
        super(2, continuation);
        this.f4593b = dataSource$reuseInflight$1;
        this.f4594c = dataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        DataSource$reuseInflight$1$get$2 dataSource$reuseInflight$1$get$2 = new DataSource$reuseInflight$1$get$2(this.f4593b, this.f4594c, continuation);
        dataSource$reuseInflight$1$get$2.L$0 = obj;
        return dataSource$reuseInflight$1$get$2;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Deferred<? extends Value>> continuation) {
        return ((DataSource$reuseInflight$1$get$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Deferred deferred;
        Deferred async$default;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.f4592a == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            deferred = this.f4593b.job;
            if (deferred == null) {
                async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass1(this.f4594c, null), 3, null);
                DataSource$reuseInflight$1 dataSource$reuseInflight$1 = this.f4593b;
                dataSource$reuseInflight$1.job = async$default;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DataSource$reuseInflight$1$get$2$2$1(async$default, dataSource$reuseInflight$1, null), 3, null);
                return async$default;
            }
            return deferred;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
