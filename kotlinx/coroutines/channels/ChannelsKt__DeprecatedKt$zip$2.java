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
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {487, 469, 471}, m = "invokeSuspend", n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11520a;

    /* renamed from: b  reason: collision with root package name */
    Object f11521b;

    /* renamed from: c  reason: collision with root package name */
    Object f11522c;

    /* renamed from: d  reason: collision with root package name */
    Object f11523d;

    /* renamed from: e  reason: collision with root package name */
    Object f11524e;

    /* renamed from: f  reason: collision with root package name */
    int f11525f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11526g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11527h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ Function2 f11528i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$zip$2(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f11526g = receiveChannel;
        this.f11527h = receiveChannel2;
        this.f11528i = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$zip$2 channelsKt__DeprecatedKt$zip$2 = new ChannelsKt__DeprecatedKt$zip$2(this.f11526g, this.f11527h, this.f11528i, continuation);
        channelsKt__DeprecatedKt$zip$2.L$0 = obj;
        return channelsKt__DeprecatedKt$zip$2;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super V> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$zip$2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b9 A[Catch: all -> 0x0056, TRY_LEAVE, TryCatch #2 {all -> 0x0056, blocks: (B:26:0x00b1, B:28:0x00b9, B:40:0x0109, B:13:0x004a), top: B:52:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e1 A[Catch: all -> 0x0106, TRY_LEAVE, TryCatch #1 {all -> 0x0106, blocks: (B:32:0x00d9, B:34:0x00e1), top: B:50:0x00d9 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0109 A[Catch: all -> 0x0056, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0056, blocks: (B:26:0x00b1, B:28:0x00b9, B:40:0x0109, B:13:0x004a), top: B:52:0x004a }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00fe -> B:22:0x0093). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ReceiveChannel receiveChannel;
        Function2 function2;
        ProducerScope producerScope;
        ChannelsKt__DeprecatedKt$zip$2 channelsKt__DeprecatedKt$zip$2;
        ChannelIterator channelIterator;
        ChannelIterator it;
        ReceiveChannel receiveChannel2;
        ProducerScope producerScope2;
        ChannelIterator channelIterator2;
        Function2 function22;
        ReceiveChannel receiveChannel3;
        Object obj2;
        ChannelIterator channelIterator3;
        Object obj3;
        ChannelsKt__DeprecatedKt$zip$2 channelsKt__DeprecatedKt$zip$22;
        ProducerScope producerScope3;
        ChannelIterator channelIterator4;
        Function2 function23;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11525f;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                ChannelIterator it2 = this.f11526g.iterator();
                receiveChannel = this.f11527h;
                function2 = this.f11528i;
                producerScope = producerScope4;
                channelsKt__DeprecatedKt$zip$2 = this;
                channelIterator = it2;
                it = receiveChannel.iterator();
            } else if (i2 == 1) {
                ChannelIterator channelIterator5 = (ChannelIterator) this.f11523d;
                ReceiveChannel receiveChannel4 = (ReceiveChannel) this.f11522c;
                Function2 function24 = (Function2) this.f11521b;
                ChannelIterator channelIterator6 = (ChannelIterator) this.f11520a;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope3 = producerScope5;
                channelIterator4 = channelIterator6;
                function23 = function24;
                receiveChannel2 = receiveChannel4;
                channelIterator3 = channelIterator5;
                obj3 = coroutine_suspended;
                channelsKt__DeprecatedKt$zip$22 = this;
                if (((Boolean) obj).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return unit;
                }
                Object next = channelIterator3.next();
                channelsKt__DeprecatedKt$zip$22.L$0 = producerScope3;
                channelsKt__DeprecatedKt$zip$22.f11520a = channelIterator4;
                channelsKt__DeprecatedKt$zip$22.f11521b = function23;
                channelsKt__DeprecatedKt$zip$22.f11522c = receiveChannel2;
                channelsKt__DeprecatedKt$zip$22.f11523d = channelIterator3;
                channelsKt__DeprecatedKt$zip$22.f11524e = next;
                channelsKt__DeprecatedKt$zip$22.f11525f = 2;
                Object hasNext = channelIterator4.hasNext(channelsKt__DeprecatedKt$zip$22);
                if (hasNext == obj3) {
                    return obj3;
                }
                ReceiveChannel receiveChannel5 = receiveChannel2;
                obj2 = next;
                obj = hasNext;
                producerScope2 = producerScope3;
                channelIterator2 = channelIterator4;
                function22 = function23;
                receiveChannel3 = receiveChannel5;
                if (((Boolean) obj).booleanValue()) {
                }
                channelsKt__DeprecatedKt$zip$2 = channelsKt__DeprecatedKt$zip$22;
                coroutine_suspended = obj3;
                it = channelIterator3;
                receiveChannel = receiveChannel3;
                function2 = function22;
                channelIterator = channelIterator2;
                producerScope = producerScope2;
            } else if (i2 == 2) {
                Object obj4 = this.f11524e;
                channelIterator3 = (ChannelIterator) this.f11523d;
                receiveChannel2 = (ReceiveChannel) this.f11522c;
                Function2 function25 = (Function2) this.f11521b;
                ChannelIterator channelIterator7 = (ChannelIterator) this.f11520a;
                ProducerScope producerScope6 = (ProducerScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    producerScope2 = producerScope6;
                    channelIterator2 = channelIterator7;
                    function22 = function25;
                    receiveChannel3 = receiveChannel2;
                    obj2 = obj4;
                    obj3 = coroutine_suspended;
                    channelsKt__DeprecatedKt$zip$22 = this;
                    try {
                        if (((Boolean) obj).booleanValue()) {
                            Object invoke = function22.invoke(obj2, channelIterator2.next());
                            channelsKt__DeprecatedKt$zip$22.L$0 = producerScope2;
                            channelsKt__DeprecatedKt$zip$22.f11520a = channelIterator2;
                            channelsKt__DeprecatedKt$zip$22.f11521b = function22;
                            channelsKt__DeprecatedKt$zip$22.f11522c = receiveChannel3;
                            channelsKt__DeprecatedKt$zip$22.f11523d = channelIterator3;
                            channelsKt__DeprecatedKt$zip$22.f11524e = null;
                            channelsKt__DeprecatedKt$zip$22.f11525f = 3;
                            if (producerScope2.send(invoke, channelsKt__DeprecatedKt$zip$22) == obj3) {
                                return obj3;
                            }
                        }
                        channelsKt__DeprecatedKt$zip$2 = channelsKt__DeprecatedKt$zip$22;
                        coroutine_suspended = obj3;
                        it = channelIterator3;
                        receiveChannel = receiveChannel3;
                        function2 = function22;
                        channelIterator = channelIterator2;
                        producerScope = producerScope2;
                    } catch (Throwable th) {
                        th = th;
                        receiveChannel = receiveChannel3;
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            ChannelsKt.cancelConsumed(receiveChannel, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    receiveChannel = receiveChannel2;
                    throw th;
                }
            } else if (i2 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                it = (ChannelIterator) this.f11523d;
                receiveChannel = (ReceiveChannel) this.f11522c;
                function2 = (Function2) this.f11521b;
                channelIterator = (ChannelIterator) this.f11520a;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                channelsKt__DeprecatedKt$zip$2 = this;
            }
            channelsKt__DeprecatedKt$zip$2.L$0 = producerScope;
            channelsKt__DeprecatedKt$zip$2.f11520a = channelIterator;
            channelsKt__DeprecatedKt$zip$2.f11521b = function2;
            channelsKt__DeprecatedKt$zip$2.f11522c = receiveChannel;
            channelsKt__DeprecatedKt$zip$2.f11523d = it;
            channelsKt__DeprecatedKt$zip$2.f11524e = null;
            channelsKt__DeprecatedKt$zip$2.f11525f = 1;
            Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$zip$2);
            if (hasNext2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj5 = coroutine_suspended;
            channelsKt__DeprecatedKt$zip$22 = channelsKt__DeprecatedKt$zip$2;
            obj = hasNext2;
            producerScope3 = producerScope;
            channelIterator4 = channelIterator;
            function23 = function2;
            receiveChannel2 = receiveChannel;
            channelIterator3 = it;
            obj3 = obj5;
            if (((Boolean) obj).booleanValue()) {
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
