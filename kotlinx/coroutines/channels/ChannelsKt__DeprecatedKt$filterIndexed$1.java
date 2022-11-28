package kotlinx.coroutines.channels;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.bouncycastle.math.Primes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {Primes.SMALL_FACTOR_LIMIT, 212, 212}, m = "invokeSuspend", n = {"$this$produce", FirebaseAnalytics.Param.INDEX, "$this$produce", "e", FirebaseAnalytics.Param.INDEX, "$this$produce", FirebaseAnalytics.Param.INDEX}, s = {"L$0", "I$0", "L$0", "L$2", "I$0", "L$0", "I$0"})
/* loaded from: classes3.dex */
final class ChannelsKt__DeprecatedKt$filterIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11396a;

    /* renamed from: b  reason: collision with root package name */
    Object f11397b;

    /* renamed from: c  reason: collision with root package name */
    int f11398c;

    /* renamed from: d  reason: collision with root package name */
    int f11399d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11400e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function3 f11401f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$filterIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.f11400e = receiveChannel;
        this.f11401f = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$filterIndexed$1 channelsKt__DeprecatedKt$filterIndexed$1 = new ChannelsKt__DeprecatedKt$filterIndexed$1(this.f11400e, this.f11401f, continuation);
        channelsKt__DeprecatedKt$filterIndexed$1.L$0 = obj;
        return channelsKt__DeprecatedKt$filterIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$filterIndexed$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c1  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00bc -> B:15:0x0062). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        int i2;
        ChannelIterator it;
        ProducerScope producerScope;
        ProducerScope producerScope2;
        Object obj2;
        ChannelsKt__DeprecatedKt$filterIndexed$1 channelsKt__DeprecatedKt$filterIndexed$1;
        ChannelsKt__DeprecatedKt$filterIndexed$1 channelsKt__DeprecatedKt$filterIndexed$12;
        ChannelIterator channelIterator;
        int i3;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.f11399d;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            i2 = 0;
            it = this.f11400e.iterator();
            producerScope = (ProducerScope) this.L$0;
        } else if (i4 == 1) {
            int i5 = this.f11398c;
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator = (ChannelIterator) this.f11396a;
            i3 = i5;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$filterIndexed$1 = this;
            if (((Boolean) obj).booleanValue()) {
            }
        } else if (i4 == 2) {
            int i6 = this.f11398c;
            Object obj3 = this.f11397b;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            int i7 = i6;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$filterIndexed$1 = this;
            ChannelIterator channelIterator2 = (ChannelIterator) this.f11396a;
            Object obj4 = obj3;
            it = channelIterator2;
            if (((Boolean) obj).booleanValue()) {
                channelsKt__DeprecatedKt$filterIndexed$1.L$0 = producerScope2;
                channelsKt__DeprecatedKt$filterIndexed$1.f11396a = it;
                channelsKt__DeprecatedKt$filterIndexed$1.f11397b = null;
                channelsKt__DeprecatedKt$filterIndexed$1.f11398c = i7;
                channelsKt__DeprecatedKt$filterIndexed$1.f11399d = 3;
                if (producerScope2.send(obj4, channelsKt__DeprecatedKt$filterIndexed$1) == obj2) {
                    return obj2;
                }
            }
            channelsKt__DeprecatedKt$filterIndexed$12 = channelsKt__DeprecatedKt$filterIndexed$1;
            coroutine_suspended = obj2;
            producerScope = producerScope2;
            i2 = i7;
            channelsKt__DeprecatedKt$filterIndexed$12.L$0 = producerScope;
            channelsKt__DeprecatedKt$filterIndexed$12.f11396a = it;
            channelsKt__DeprecatedKt$filterIndexed$12.f11397b = null;
            channelsKt__DeprecatedKt$filterIndexed$12.f11398c = i2;
            channelsKt__DeprecatedKt$filterIndexed$12.f11399d = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$filterIndexed$12);
            if (hasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj5 = coroutine_suspended;
            channelsKt__DeprecatedKt$filterIndexed$1 = channelsKt__DeprecatedKt$filterIndexed$12;
            obj = hasNext;
            producerScope2 = producerScope;
            channelIterator = it;
            i3 = i2;
            obj2 = obj5;
            if (((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            Object next = channelIterator.next();
            Function3 function3 = channelsKt__DeprecatedKt$filterIndexed$1.f11401f;
            i7 = i3 + 1;
            Integer boxInt = Boxing.boxInt(i3);
            channelsKt__DeprecatedKt$filterIndexed$1.L$0 = producerScope2;
            channelsKt__DeprecatedKt$filterIndexed$1.f11396a = channelIterator;
            channelsKt__DeprecatedKt$filterIndexed$1.f11397b = next;
            channelsKt__DeprecatedKt$filterIndexed$1.f11398c = i7;
            channelsKt__DeprecatedKt$filterIndexed$1.f11399d = 2;
            Object invoke = function3.invoke(boxInt, next, channelsKt__DeprecatedKt$filterIndexed$1);
            if (invoke == obj2) {
                return obj2;
            }
            channelIterator2 = channelIterator;
            obj4 = next;
            obj = invoke;
            it = channelIterator2;
            if (((Boolean) obj).booleanValue()) {
            }
            channelsKt__DeprecatedKt$filterIndexed$12 = channelsKt__DeprecatedKt$filterIndexed$1;
            coroutine_suspended = obj2;
            producerScope = producerScope2;
            i2 = i7;
            channelsKt__DeprecatedKt$filterIndexed$12.L$0 = producerScope;
            channelsKt__DeprecatedKt$filterIndexed$12.f11396a = it;
            channelsKt__DeprecatedKt$filterIndexed$12.f11397b = null;
            channelsKt__DeprecatedKt$filterIndexed$12.f11398c = i2;
            channelsKt__DeprecatedKt$filterIndexed$12.f11399d = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$filterIndexed$12);
            if (hasNext == coroutine_suspended) {
            }
        } else if (i4 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            i2 = this.f11398c;
            it = (ChannelIterator) this.f11396a;
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        channelsKt__DeprecatedKt$filterIndexed$12 = this;
        channelsKt__DeprecatedKt$filterIndexed$12.L$0 = producerScope;
        channelsKt__DeprecatedKt$filterIndexed$12.f11396a = it;
        channelsKt__DeprecatedKt$filterIndexed$12.f11397b = null;
        channelsKt__DeprecatedKt$filterIndexed$12.f11398c = i2;
        channelsKt__DeprecatedKt$filterIndexed$12.f11399d = 1;
        hasNext = it.hasNext(channelsKt__DeprecatedKt$filterIndexed$12);
        if (hasNext == coroutine_suspended) {
        }
    }
}
