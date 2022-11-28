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
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1", f = "Deprecated.kt", i = {0, 1, 1, 2, 3, 4}, l = {CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, 188}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0", "L$0", "L$0"})
/* loaded from: classes3.dex */
final class ChannelsKt__DeprecatedKt$dropWhile$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11374a;

    /* renamed from: b  reason: collision with root package name */
    Object f11375b;

    /* renamed from: c  reason: collision with root package name */
    int f11376c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11377d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Function2 f11378e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$dropWhile$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f11377d = receiveChannel;
        this.f11378e = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$dropWhile$1 channelsKt__DeprecatedKt$dropWhile$1 = new ChannelsKt__DeprecatedKt$dropWhile$1(this.f11377d, this.f11378e, continuation);
        channelsKt__DeprecatedKt$dropWhile$1.L$0 = obj;
        return channelsKt__DeprecatedKt$dropWhile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$dropWhile$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0104  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00b1 -> B:27:0x00b5). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0102 -> B:35:0x00d9). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ChannelIterator it;
        ProducerScope producerScope;
        ChannelsKt__DeprecatedKt$dropWhile$1 channelsKt__DeprecatedKt$dropWhile$1;
        ProducerScope producerScope2;
        ChannelIterator channelIterator;
        Object obj2;
        ChannelsKt__DeprecatedKt$dropWhile$1 channelsKt__DeprecatedKt$dropWhile$12;
        ProducerScope producerScope3;
        Object hasNext;
        ChannelIterator it2;
        ChannelIterator channelIterator2;
        Object hasNext2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11376c;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            it = this.f11377d.iterator();
            producerScope = (ProducerScope) this.L$0;
            channelsKt__DeprecatedKt$dropWhile$1 = this;
            channelsKt__DeprecatedKt$dropWhile$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$dropWhile$1.f11374a = it;
            channelsKt__DeprecatedKt$dropWhile$1.f11375b = null;
            channelsKt__DeprecatedKt$dropWhile$1.f11376c = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$dropWhile$1);
            if (hasNext != coroutine_suspended) {
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator = (ChannelIterator) this.f11374a;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$dropWhile$12 = this;
            if (((Boolean) obj).booleanValue()) {
            }
            it2 = channelsKt__DeprecatedKt$dropWhile$12.f11377d.iterator();
            channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__DeprecatedKt$dropWhile$12.f11374a = it2;
            channelsKt__DeprecatedKt$dropWhile$12.f11376c = 4;
            hasNext2 = it2.hasNext(channelsKt__DeprecatedKt$dropWhile$12);
            if (hasNext2 != obj2) {
            }
        } else if (i2 == 2) {
            Object obj3 = this.f11375b;
            channelIterator = (ChannelIterator) this.f11374a;
            ResultKt.throwOnFailure(obj);
            producerScope3 = (ProducerScope) this.L$0;
            Object obj4 = obj3;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$dropWhile$12 = this;
            if (((Boolean) obj).booleanValue()) {
                channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope3;
                channelsKt__DeprecatedKt$dropWhile$12.f11374a = null;
                channelsKt__DeprecatedKt$dropWhile$12.f11375b = null;
                channelsKt__DeprecatedKt$dropWhile$12.f11376c = 3;
                if (producerScope3.send(obj4, channelsKt__DeprecatedKt$dropWhile$12) == obj2) {
                    return obj2;
                }
                producerScope2 = producerScope3;
                it2 = channelsKt__DeprecatedKt$dropWhile$12.f11377d.iterator();
                channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
                channelsKt__DeprecatedKt$dropWhile$12.f11374a = it2;
                channelsKt__DeprecatedKt$dropWhile$12.f11376c = 4;
                hasNext2 = it2.hasNext(channelsKt__DeprecatedKt$dropWhile$12);
                if (hasNext2 != obj2) {
                }
            } else {
                channelsKt__DeprecatedKt$dropWhile$1 = channelsKt__DeprecatedKt$dropWhile$12;
                coroutine_suspended = obj2;
                it = channelIterator;
                producerScope = producerScope3;
                channelsKt__DeprecatedKt$dropWhile$1.L$0 = producerScope;
                channelsKt__DeprecatedKt$dropWhile$1.f11374a = it;
                channelsKt__DeprecatedKt$dropWhile$1.f11375b = null;
                channelsKt__DeprecatedKt$dropWhile$1.f11376c = 1;
                hasNext = it.hasNext(channelsKt__DeprecatedKt$dropWhile$1);
                if (hasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj5 = coroutine_suspended;
                channelsKt__DeprecatedKt$dropWhile$12 = channelsKt__DeprecatedKt$dropWhile$1;
                obj = hasNext;
                producerScope2 = producerScope;
                channelIterator = it;
                obj2 = obj5;
                if (((Boolean) obj).booleanValue()) {
                    Object next = channelIterator.next();
                    Function2 function2 = channelsKt__DeprecatedKt$dropWhile$12.f11378e;
                    channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
                    channelsKt__DeprecatedKt$dropWhile$12.f11374a = channelIterator;
                    channelsKt__DeprecatedKt$dropWhile$12.f11375b = next;
                    channelsKt__DeprecatedKt$dropWhile$12.f11376c = 2;
                    Object invoke = function2.invoke(next, channelsKt__DeprecatedKt$dropWhile$12);
                    if (invoke == obj2) {
                        return obj2;
                    }
                    ProducerScope producerScope4 = producerScope2;
                    obj4 = next;
                    obj = invoke;
                    producerScope3 = producerScope4;
                    if (((Boolean) obj).booleanValue()) {
                    }
                }
                it2 = channelsKt__DeprecatedKt$dropWhile$12.f11377d.iterator();
                channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
                channelsKt__DeprecatedKt$dropWhile$12.f11374a = it2;
                channelsKt__DeprecatedKt$dropWhile$12.f11376c = 4;
                hasNext2 = it2.hasNext(channelsKt__DeprecatedKt$dropWhile$12);
                if (hasNext2 != obj2) {
                }
            }
        } else if (i2 == 3) {
            ResultKt.throwOnFailure(obj);
            producerScope3 = (ProducerScope) this.L$0;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$dropWhile$12 = this;
            producerScope2 = producerScope3;
            it2 = channelsKt__DeprecatedKt$dropWhile$12.f11377d.iterator();
            channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__DeprecatedKt$dropWhile$12.f11374a = it2;
            channelsKt__DeprecatedKt$dropWhile$12.f11376c = 4;
            hasNext2 = it2.hasNext(channelsKt__DeprecatedKt$dropWhile$12);
            if (hasNext2 != obj2) {
            }
        } else if (i2 == 4) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator2 = (ChannelIterator) this.f11374a;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$dropWhile$12 = this;
            if (((Boolean) obj).booleanValue()) {
            }
        } else if (i2 != 5) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
            it2 = (ChannelIterator) this.f11374a;
            producerScope2 = (ProducerScope) this.L$0;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$dropWhile$12 = this;
            channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__DeprecatedKt$dropWhile$12.f11374a = it2;
            channelsKt__DeprecatedKt$dropWhile$12.f11376c = 4;
            hasNext2 = it2.hasNext(channelsKt__DeprecatedKt$dropWhile$12);
            if (hasNext2 != obj2) {
                return obj2;
            }
            channelIterator2 = it2;
            obj = hasNext2;
            if (((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            Object next2 = channelIterator2.next();
            channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__DeprecatedKt$dropWhile$12.f11374a = channelIterator2;
            channelsKt__DeprecatedKt$dropWhile$12.f11376c = 5;
            if (producerScope2.send(next2, channelsKt__DeprecatedKt$dropWhile$12) == obj2) {
                return obj2;
            }
            it2 = channelIterator2;
            channelsKt__DeprecatedKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__DeprecatedKt$dropWhile$12.f11374a = it2;
            channelsKt__DeprecatedKt$dropWhile$12.f11376c = 4;
            hasNext2 = it2.hasNext(channelsKt__DeprecatedKt$dropWhile$12);
            if (hasNext2 != obj2) {
            }
        }
    }
}
