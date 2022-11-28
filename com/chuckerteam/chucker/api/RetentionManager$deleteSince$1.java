package com.chuckerteam.chucker.api;

import com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository;
import com.chuckerteam.chucker.internal.data.repository.RecordedThrowableRepository;
import com.chuckerteam.chucker.internal.data.repository.RepositoryProvider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.api.RetentionManager$deleteSince$1", f = "RetentionManager.kt", i = {0, 1}, l = {68, 69}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
public final class RetentionManager$deleteSince$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f4854a;

    /* renamed from: b  reason: collision with root package name */
    int f4855b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ long f4856c;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RetentionManager$deleteSince$1(long j2, Continuation continuation) {
        super(2, continuation);
        this.f4856c = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        RetentionManager$deleteSince$1 retentionManager$deleteSince$1 = new RetentionManager$deleteSince$1(this.f4856c, completion);
        retentionManager$deleteSince$1.p$ = (CoroutineScope) obj;
        return retentionManager$deleteSince$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RetentionManager$deleteSince$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        CoroutineScope coroutineScope;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4855b;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.p$;
            HttpTransactionRepository transaction = RepositoryProvider.INSTANCE.transaction();
            long j2 = this.f4856c;
            this.f4854a = coroutineScope;
            this.f4855b = 1;
            if (transaction.deleteOldTransactions(j2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            if (i2 == 2) {
                CoroutineScope coroutineScope2 = (CoroutineScope) this.f4854a;
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            coroutineScope = (CoroutineScope) this.f4854a;
            ResultKt.throwOnFailure(obj);
        }
        RecordedThrowableRepository throwable = RepositoryProvider.INSTANCE.throwable();
        long j3 = this.f4856c;
        this.f4854a = coroutineScope;
        this.f4855b = 2;
        if (throwable.deleteOldThrowables(j3, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
