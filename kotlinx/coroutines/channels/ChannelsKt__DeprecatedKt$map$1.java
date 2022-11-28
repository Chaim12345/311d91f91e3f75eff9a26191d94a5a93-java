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
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {487, 333, 333}, m = "invokeSuspend", n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$map$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11452a;

    /* renamed from: b  reason: collision with root package name */
    Object f11453b;

    /* renamed from: c  reason: collision with root package name */
    Object f11454c;

    /* renamed from: d  reason: collision with root package name */
    Object f11455d;

    /* renamed from: e  reason: collision with root package name */
    int f11456e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11457f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Function2 f11458g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$map$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f11457f = receiveChannel;
        this.f11458g = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$1 = new ChannelsKt__DeprecatedKt$map$1(this.f11457f, this.f11458g, continuation);
        channelsKt__DeprecatedKt$map$1.L$0 = obj;
        return channelsKt__DeprecatedKt$map$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super R> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$map$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0093 A[Catch: all -> 0x00cf, TRY_LEAVE, TryCatch #2 {all -> 0x00cf, blocks: (B:8:0x0022, B:22:0x0076, B:26:0x008b, B:28:0x0093, B:36:0x00c9, B:18:0x005e, B:21:0x006e), top: B:48:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c9 A[Catch: all -> 0x00cf, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00cf, blocks: (B:8:0x0022, B:22:0x0076, B:26:0x008b, B:28:0x0093, B:36:0x00c9, B:18:0x005e, B:21:0x006e), top: B:48:0x000a }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00c3 -> B:22:0x0076). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ReceiveChannel receiveChannel;
        ProducerScope producerScope;
        ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$1;
        Function2 function2;
        ChannelIterator it;
        ReceiveChannel receiveChannel2;
        ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$12;
        ProducerScope producerScope2;
        Function2 function22;
        ChannelIterator channelIterator;
        ProducerScope producerScope3;
        ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$13;
        ProducerScope producerScope4;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11456e;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                receiveChannel = this.f11457f;
                channelsKt__DeprecatedKt$map$1 = this;
                function2 = this.f11458g;
                it = receiveChannel.iterator();
            } else if (i2 == 1) {
                it = (ChannelIterator) this.f11454c;
                receiveChannel = (ReceiveChannel) this.f11453b;
                function2 = (Function2) this.f11452a;
                producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                channelsKt__DeprecatedKt$map$13 = this;
                if (((Boolean) obj).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel, null);
                    return unit;
                }
                Object next = it.next();
                channelsKt__DeprecatedKt$map$13.L$0 = producerScope4;
                channelsKt__DeprecatedKt$map$13.f11452a = function2;
                channelsKt__DeprecatedKt$map$13.f11453b = receiveChannel;
                channelsKt__DeprecatedKt$map$13.f11454c = it;
                channelsKt__DeprecatedKt$map$13.f11455d = producerScope4;
                channelsKt__DeprecatedKt$map$13.f11456e = 2;
                obj = function2.invoke(next, channelsKt__DeprecatedKt$map$13);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                channelsKt__DeprecatedKt$map$12 = channelsKt__DeprecatedKt$map$13;
                producerScope2 = producerScope4;
                function22 = function2;
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                producerScope3 = producerScope2;
                channelsKt__DeprecatedKt$map$12.L$0 = producerScope2;
                channelsKt__DeprecatedKt$map$12.f11452a = function22;
                channelsKt__DeprecatedKt$map$12.f11453b = receiveChannel2;
                channelsKt__DeprecatedKt$map$12.f11454c = channelIterator;
                channelsKt__DeprecatedKt$map$12.f11455d = null;
                channelsKt__DeprecatedKt$map$12.f11456e = 3;
                if (producerScope3.send(obj, channelsKt__DeprecatedKt$map$12) != coroutine_suspended) {
                }
            } else if (i2 == 2) {
                producerScope3 = (ProducerScope) this.f11455d;
                channelIterator = (ChannelIterator) this.f11454c;
                receiveChannel2 = (ReceiveChannel) this.f11453b;
                function22 = (Function2) this.f11452a;
                producerScope2 = (ProducerScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    channelsKt__DeprecatedKt$map$12 = this;
                    channelsKt__DeprecatedKt$map$12.L$0 = producerScope2;
                    channelsKt__DeprecatedKt$map$12.f11452a = function22;
                    channelsKt__DeprecatedKt$map$12.f11453b = receiveChannel2;
                    channelsKt__DeprecatedKt$map$12.f11454c = channelIterator;
                    channelsKt__DeprecatedKt$map$12.f11455d = null;
                    channelsKt__DeprecatedKt$map$12.f11456e = 3;
                    if (producerScope3.send(obj, channelsKt__DeprecatedKt$map$12) != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it = channelIterator;
                    receiveChannel = receiveChannel2;
                    function2 = function22;
                    producerScope = producerScope2;
                    channelsKt__DeprecatedKt$map$1 = channelsKt__DeprecatedKt$map$12;
                } catch (Throwable th) {
                    th = th;
                    receiveChannel = receiveChannel2;
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        ChannelsKt.cancelConsumed(receiveChannel, th);
                        throw th2;
                    }
                }
            } else if (i2 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                it = (ChannelIterator) this.f11454c;
                receiveChannel = (ReceiveChannel) this.f11453b;
                function2 = (Function2) this.f11452a;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope5;
                channelsKt__DeprecatedKt$map$1 = this;
            }
            channelsKt__DeprecatedKt$map$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$map$1.f11452a = function2;
            channelsKt__DeprecatedKt$map$1.f11453b = receiveChannel;
            channelsKt__DeprecatedKt$map$1.f11454c = it;
            channelsKt__DeprecatedKt$map$1.f11456e = 1;
            Object hasNext = it.hasNext(channelsKt__DeprecatedKt$map$1);
            if (hasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$14 = channelsKt__DeprecatedKt$map$1;
            producerScope4 = producerScope;
            obj = hasNext;
            channelsKt__DeprecatedKt$map$13 = channelsKt__DeprecatedKt$map$14;
            if (((Boolean) obj).booleanValue()) {
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
