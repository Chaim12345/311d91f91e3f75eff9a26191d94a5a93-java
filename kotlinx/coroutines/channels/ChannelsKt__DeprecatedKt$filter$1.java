package kotlinx.coroutines.channels;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1", f = "Deprecated.kt", i = {0, 1, 1, 2}, l = {CipherSuite.TLS_SM4_GCM_SM3, CipherSuite.TLS_SM4_CCM_SM3, CipherSuite.TLS_SM4_CCM_SM3}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$filter$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11391a;

    /* renamed from: b  reason: collision with root package name */
    Object f11392b;

    /* renamed from: c  reason: collision with root package name */
    int f11393c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11394d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Function2 f11395e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$filter$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f11394d = receiveChannel;
        this.f11395e = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$filter$1 channelsKt__DeprecatedKt$filter$1 = new ChannelsKt__DeprecatedKt$filter$1(this.f11394d, this.f11395e, continuation);
        channelsKt__DeprecatedKt$filter$1.L$0 = obj;
        return channelsKt__DeprecatedKt$filter$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$filter$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a9  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00a4 -> B:14:0x0057). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ChannelIterator it;
        ProducerScope producerScope;
        ChannelIterator channelIterator;
        Object obj2;
        ChannelsKt__DeprecatedKt$filter$1 channelsKt__DeprecatedKt$filter$1;
        ChannelsKt__DeprecatedKt$filter$1 channelsKt__DeprecatedKt$filter$12;
        ProducerScope producerScope2;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11393c;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            it = this.f11394d.iterator();
            producerScope = (ProducerScope) this.L$0;
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator = (ChannelIterator) this.f11391a;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$filter$1 = this;
            if (((Boolean) obj).booleanValue()) {
            }
        } else if (i2 == 2) {
            Object obj3 = this.f11392b;
            channelIterator = (ChannelIterator) this.f11391a;
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope3 = (ProducerScope) this.L$0;
            Object obj4 = obj3;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$filter$1 = this;
            if (((Boolean) obj).booleanValue()) {
                channelsKt__DeprecatedKt$filter$1.L$0 = producerScope3;
                channelsKt__DeprecatedKt$filter$1.f11391a = channelIterator;
                channelsKt__DeprecatedKt$filter$1.f11392b = null;
                channelsKt__DeprecatedKt$filter$1.f11393c = 3;
                if (producerScope3.send(obj4, channelsKt__DeprecatedKt$filter$1) == obj2) {
                    return obj2;
                }
            }
            channelsKt__DeprecatedKt$filter$12 = channelsKt__DeprecatedKt$filter$1;
            coroutine_suspended = obj2;
            it = channelIterator;
            producerScope = producerScope3;
            channelsKt__DeprecatedKt$filter$12.L$0 = producerScope;
            channelsKt__DeprecatedKt$filter$12.f11391a = it;
            channelsKt__DeprecatedKt$filter$12.f11392b = null;
            channelsKt__DeprecatedKt$filter$12.f11393c = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$filter$12);
            if (hasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj5 = coroutine_suspended;
            channelsKt__DeprecatedKt$filter$1 = channelsKt__DeprecatedKt$filter$12;
            obj = hasNext;
            producerScope2 = producerScope;
            channelIterator = it;
            obj2 = obj5;
            if (((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            Object next = channelIterator.next();
            Function2 function2 = channelsKt__DeprecatedKt$filter$1.f11395e;
            channelsKt__DeprecatedKt$filter$1.L$0 = producerScope2;
            channelsKt__DeprecatedKt$filter$1.f11391a = channelIterator;
            channelsKt__DeprecatedKt$filter$1.f11392b = next;
            channelsKt__DeprecatedKt$filter$1.f11393c = 2;
            Object invoke = function2.invoke(next, channelsKt__DeprecatedKt$filter$1);
            if (invoke == obj2) {
                return obj2;
            }
            ProducerScope producerScope4 = producerScope2;
            obj4 = next;
            obj = invoke;
            producerScope3 = producerScope4;
            if (((Boolean) obj).booleanValue()) {
            }
            channelsKt__DeprecatedKt$filter$12 = channelsKt__DeprecatedKt$filter$1;
            coroutine_suspended = obj2;
            it = channelIterator;
            producerScope = producerScope3;
            channelsKt__DeprecatedKt$filter$12.L$0 = producerScope;
            channelsKt__DeprecatedKt$filter$12.f11391a = it;
            channelsKt__DeprecatedKt$filter$12.f11392b = null;
            channelsKt__DeprecatedKt$filter$12.f11393c = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$filter$12);
            if (hasNext == coroutine_suspended) {
            }
        } else if (i2 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            it = (ChannelIterator) this.f11391a;
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        channelsKt__DeprecatedKt$filter$12 = this;
        channelsKt__DeprecatedKt$filter$12.L$0 = producerScope;
        channelsKt__DeprecatedKt$filter$12.f11391a = it;
        channelsKt__DeprecatedKt$filter$12.f11392b = null;
        channelsKt__DeprecatedKt$filter$12.f11393c = 1;
        hasNext = it.hasNext(channelsKt__DeprecatedKt$filter$12);
        if (hasNext == coroutine_suspended) {
        }
    }
}
