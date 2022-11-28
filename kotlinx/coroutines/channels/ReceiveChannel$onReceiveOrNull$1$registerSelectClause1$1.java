package kotlinx.coroutines.channels;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "kotlinx.coroutines.channels.ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1", f = "Channel.kt", i = {}, l = {375}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1 extends SuspendLambda implements Function2<ChannelResult<? extends E>, Continuation<? super R>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f11536a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11537b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function2 f11538c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f11538c = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1 receiveChannel$onReceiveOrNull$1$registerSelectClause1$1 = new ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(this.f11538c, continuation);
        receiveChannel$onReceiveOrNull$1$registerSelectClause1$1.f11537b = obj;
        return receiveChannel$onReceiveOrNull$1$registerSelectClause1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m1650invokeWpGqRn0(((ChannelResult) obj).m1646unboximpl(), (Continuation) obj2);
    }

    @Nullable
    /* renamed from: invoke-WpGqRn0  reason: not valid java name */
    public final Object m1650invokeWpGqRn0(@NotNull Object obj, @Nullable Continuation<? super R> continuation) {
        return ((ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1) create(ChannelResult.m1634boximpl(obj), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11536a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Object m1646unboximpl = ((ChannelResult) this.f11537b).m1646unboximpl();
            Throwable m1638exceptionOrNullimpl = ChannelResult.m1638exceptionOrNullimpl(m1646unboximpl);
            if (m1638exceptionOrNullimpl != null) {
                throw m1638exceptionOrNullimpl;
            }
            Function2 function2 = this.f11538c;
            Object m1639getOrNullimpl = ChannelResult.m1639getOrNullimpl(m1646unboximpl);
            this.f11536a = 1;
            obj = function2.invoke(m1639getOrNullimpl, this);
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
