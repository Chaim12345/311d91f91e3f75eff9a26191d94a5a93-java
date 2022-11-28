package com.chuckerteam.chucker.internal.ui;

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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.ui.MainViewModel$clearThrowables$1", f = "MainViewModel.kt", i = {0}, l = {54}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes.dex */
final class MainViewModel$clearThrowables$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f4940a;

    /* renamed from: b  reason: collision with root package name */
    int f4941b;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainViewModel$clearThrowables$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        MainViewModel$clearThrowables$1 mainViewModel$clearThrowables$1 = new MainViewModel$clearThrowables$1(completion);
        mainViewModel$clearThrowables$1.p$ = (CoroutineScope) obj;
        return mainViewModel$clearThrowables$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$clearThrowables$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4941b;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RecordedThrowableRepository throwable = RepositoryProvider.INSTANCE.throwable();
            this.f4940a = coroutineScope;
            this.f4941b = 1;
            if (throwable.deleteAllThrowables(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.f4940a;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
