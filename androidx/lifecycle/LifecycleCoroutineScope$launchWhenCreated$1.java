package androidx.lifecycle;

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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 1})
@DebugMetadata(c = "androidx.lifecycle.LifecycleCoroutineScope$launchWhenCreated$1", f = "Lifecycle.kt", i = {}, l = {74}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class LifecycleCoroutineScope$launchWhenCreated$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f3210a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ LifecycleCoroutineScope f3211b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function2 f3212c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LifecycleCoroutineScope$launchWhenCreated$1(LifecycleCoroutineScope lifecycleCoroutineScope, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f3211b = lifecycleCoroutineScope;
        this.f3212c = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        return new LifecycleCoroutineScope$launchWhenCreated$1(this.f3211b, this.f3212c, completion);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LifecycleCoroutineScope$launchWhenCreated$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f3210a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle lifecycle$lifecycle_runtime_ktx_release = this.f3211b.getLifecycle$lifecycle_runtime_ktx_release();
            Function2 function2 = this.f3212c;
            this.f3210a = 1;
            if (PausingDispatcherKt.whenCreated(lifecycle$lifecycle_runtime_ktx_release, function2, this) == coroutine_suspended) {
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
