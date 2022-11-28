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
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {254, 255}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "remaining"}, s = {"L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes3.dex */
final class ChannelsKt__DeprecatedKt$take$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11491a;

    /* renamed from: b  reason: collision with root package name */
    int f11492b;

    /* renamed from: c  reason: collision with root package name */
    int f11493c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ int f11494d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11495e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$take$1(int i2, ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.f11494d = i2;
        this.f11495e = receiveChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$take$1 channelsKt__DeprecatedKt$take$1 = new ChannelsKt__DeprecatedKt$take$1(this.f11494d, this.f11495e, continuation);
        channelsKt__DeprecatedKt$take$1.L$0 = obj;
        return channelsKt__DeprecatedKt$take$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$take$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0080 -> B:29:0x0082). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ProducerScope producerScope;
        int i2;
        ChannelIterator it;
        ChannelsKt__DeprecatedKt$take$1 channelsKt__DeprecatedKt$take$1;
        ChannelsKt__DeprecatedKt$take$1 channelsKt__DeprecatedKt$take$12;
        ProducerScope producerScope2;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.f11493c;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = (ProducerScope) this.L$0;
            i2 = this.f11494d;
            if (i2 == 0) {
                return Unit.INSTANCE;
            }
            if (!(i2 >= 0)) {
                throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
            }
            it = this.f11495e.iterator();
            channelsKt__DeprecatedKt$take$1 = this;
            channelsKt__DeprecatedKt$take$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$take$1.f11491a = it;
            channelsKt__DeprecatedKt$take$1.f11492b = i2;
            channelsKt__DeprecatedKt$take$1.f11493c = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$take$1);
            if (hasNext == coroutine_suspended) {
            }
        } else if (i3 == 1) {
            i2 = this.f11492b;
            it = (ChannelIterator) this.f11491a;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__DeprecatedKt$take$12 = this;
            if (((Boolean) obj).booleanValue()) {
            }
        } else if (i3 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            i2 = this.f11492b;
            it = (ChannelIterator) this.f11491a;
            ResultKt.throwOnFailure(obj);
            producerScope = (ProducerScope) this.L$0;
            channelsKt__DeprecatedKt$take$1 = this;
            i2--;
            if (i2 == 0) {
                return Unit.INSTANCE;
            }
            channelsKt__DeprecatedKt$take$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$take$1.f11491a = it;
            channelsKt__DeprecatedKt$take$1.f11492b = i2;
            channelsKt__DeprecatedKt$take$1.f11493c = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$take$1);
            if (hasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            ChannelsKt__DeprecatedKt$take$1 channelsKt__DeprecatedKt$take$13 = channelsKt__DeprecatedKt$take$1;
            producerScope2 = producerScope;
            obj = hasNext;
            channelsKt__DeprecatedKt$take$12 = channelsKt__DeprecatedKt$take$13;
            if (((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            Object next = it.next();
            channelsKt__DeprecatedKt$take$12.L$0 = producerScope2;
            channelsKt__DeprecatedKt$take$12.f11491a = it;
            channelsKt__DeprecatedKt$take$12.f11492b = i2;
            channelsKt__DeprecatedKt$take$12.f11493c = 2;
            if (producerScope2.send(next, channelsKt__DeprecatedKt$take$12) == coroutine_suspended) {
                return coroutine_suspended;
            }
            producerScope = producerScope2;
            channelsKt__DeprecatedKt$take$1 = channelsKt__DeprecatedKt$take$12;
            i2--;
            if (i2 == 0) {
            }
            channelsKt__DeprecatedKt$take$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$take$1.f11491a = it;
            channelsKt__DeprecatedKt$take$1.f11492b = i2;
            channelsKt__DeprecatedKt$take$1.f11493c = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$take$1);
            if (hasNext == coroutine_suspended) {
            }
        }
    }
}
