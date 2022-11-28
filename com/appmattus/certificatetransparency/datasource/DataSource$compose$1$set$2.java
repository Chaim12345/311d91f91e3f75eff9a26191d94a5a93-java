package com.appmattus.certificatetransparency.datasource;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$compose$1$set$2", f = "DataSource.kt", i = {}, l = {70}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DataSource$compose$1$set$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Unit>>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    int f4572a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DataSource f4573b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Object f4574c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ DataSource f4575d;

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$compose$1$set$2$1", f = "DataSource.kt", i = {}, l = {70}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.appmattus.certificatetransparency.datasource.DataSource$compose$1$set$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f4576a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ DataSource f4577b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Object f4578c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DataSource dataSource, Object obj, Continuation continuation) {
            super(2, continuation);
            this.f4577b = dataSource;
            this.f4578c = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.f4577b, this.f4578c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f4576a;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                DataSource dataSource = this.f4577b;
                Object obj2 = this.f4578c;
                this.f4576a = 1;
                if (dataSource.set(obj2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$compose$1$set$2$2", f = "DataSource.kt", i = {}, l = {70}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.appmattus.certificatetransparency.datasource.DataSource$compose$1$set$2$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f4579a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ DataSource f4580b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Object f4581c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DataSource dataSource, Object obj, Continuation continuation) {
            super(2, continuation);
            this.f4580b = dataSource;
            this.f4581c = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass2(this.f4580b, this.f4581c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.f4579a;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                DataSource dataSource = this.f4580b;
                Object obj2 = this.f4581c;
                this.f4579a = 1;
                if (dataSource.set(obj2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSource$compose$1$set$2(DataSource dataSource, Object obj, DataSource dataSource2, Continuation continuation) {
        super(2, continuation);
        this.f4573b = dataSource;
        this.f4574c = obj;
        this.f4575d = dataSource2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        DataSource$compose$1$set$2 dataSource$compose$1$set$2 = new DataSource$compose$1$set$2(this.f4573b, this.f4574c, this.f4575d, continuation);
        dataSource$compose$1$set$2.L$0 = obj;
        return dataSource$compose$1$set$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Unit>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Unit>>) continuation);
    }

    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<Unit>> continuation) {
        return ((DataSource$compose$1$set$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4572a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Deferred[] deferredArr = {BuildersKt.async$default(coroutineScope, null, null, new AnonymousClass1(this.f4573b, this.f4574c, null), 3, null), BuildersKt.async$default(coroutineScope, null, null, new AnonymousClass2(this.f4575d, this.f4574c, null), 3, null)};
            this.f4572a = 1;
            obj = AwaitKt.awaitAll(deferredArr, this);
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
