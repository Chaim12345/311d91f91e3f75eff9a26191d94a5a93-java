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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {344, 345, 345}, m = "invokeSuspend", n = {"$this$produce", FirebaseAnalytics.Param.INDEX, "$this$produce", FirebaseAnalytics.Param.INDEX, "$this$produce", FirebaseAnalytics.Param.INDEX}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$mapIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11459a;

    /* renamed from: b  reason: collision with root package name */
    Object f11460b;

    /* renamed from: c  reason: collision with root package name */
    int f11461c;

    /* renamed from: d  reason: collision with root package name */
    int f11462d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11463e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function3 f11464f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$mapIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.f11463e = receiveChannel;
        this.f11464f = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$mapIndexed$1 channelsKt__DeprecatedKt$mapIndexed$1 = new ChannelsKt__DeprecatedKt$mapIndexed$1(this.f11463e, this.f11464f, continuation);
        channelsKt__DeprecatedKt$mapIndexed$1.L$0 = obj;
        return channelsKt__DeprecatedKt$mapIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super R> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$mapIndexed$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ac  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a8 -> B:14:0x0059). Please submit an issue!!! */
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
        ChannelsKt__DeprecatedKt$mapIndexed$1 channelsKt__DeprecatedKt$mapIndexed$1;
        ChannelsKt__DeprecatedKt$mapIndexed$1 channelsKt__DeprecatedKt$mapIndexed$12;
        ProducerScope producerScope2;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.f11462d;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = (ProducerScope) this.L$0;
            i2 = 0;
            it = this.f11463e.iterator();
        } else if (i3 == 1) {
            i2 = this.f11461c;
            it = (ChannelIterator) this.f11459a;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__DeprecatedKt$mapIndexed$12 = this;
            if (((Boolean) obj).booleanValue()) {
            }
        } else if (i3 == 2) {
            i2 = this.f11461c;
            ProducerScope producerScope3 = (ProducerScope) this.f11460b;
            ChannelIterator channelIterator = (ChannelIterator) this.f11459a;
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            ChannelsKt__DeprecatedKt$mapIndexed$1 channelsKt__DeprecatedKt$mapIndexed$13 = this;
            channelsKt__DeprecatedKt$mapIndexed$13.L$0 = producerScope4;
            channelsKt__DeprecatedKt$mapIndexed$13.f11459a = channelIterator;
            channelsKt__DeprecatedKt$mapIndexed$13.f11460b = null;
            channelsKt__DeprecatedKt$mapIndexed$13.f11461c = i2;
            channelsKt__DeprecatedKt$mapIndexed$13.f11462d = 3;
            if (producerScope3.send(obj, channelsKt__DeprecatedKt$mapIndexed$13) != coroutine_suspended) {
                return coroutine_suspended;
            }
            it = channelIterator;
            producerScope = producerScope4;
            channelsKt__DeprecatedKt$mapIndexed$1 = channelsKt__DeprecatedKt$mapIndexed$13;
            channelsKt__DeprecatedKt$mapIndexed$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$mapIndexed$1.f11459a = it;
            channelsKt__DeprecatedKt$mapIndexed$1.f11461c = i2;
            channelsKt__DeprecatedKt$mapIndexed$1.f11462d = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$mapIndexed$1);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            ChannelsKt__DeprecatedKt$mapIndexed$1 channelsKt__DeprecatedKt$mapIndexed$14 = channelsKt__DeprecatedKt$mapIndexed$1;
            producerScope2 = producerScope;
            obj = hasNext;
            channelsKt__DeprecatedKt$mapIndexed$12 = channelsKt__DeprecatedKt$mapIndexed$14;
            if (((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            Object next = it.next();
            Function3 function3 = channelsKt__DeprecatedKt$mapIndexed$12.f11464f;
            int i4 = i2 + 1;
            Integer boxInt = Boxing.boxInt(i2);
            channelsKt__DeprecatedKt$mapIndexed$12.L$0 = producerScope2;
            channelsKt__DeprecatedKt$mapIndexed$12.f11459a = it;
            channelsKt__DeprecatedKt$mapIndexed$12.f11460b = producerScope2;
            channelsKt__DeprecatedKt$mapIndexed$12.f11461c = i4;
            channelsKt__DeprecatedKt$mapIndexed$12.f11462d = 2;
            obj = function3.invoke(boxInt, next, channelsKt__DeprecatedKt$mapIndexed$12);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            channelsKt__DeprecatedKt$mapIndexed$13 = channelsKt__DeprecatedKt$mapIndexed$12;
            i2 = i4;
            producerScope4 = producerScope2;
            channelIterator = it;
            producerScope3 = producerScope4;
            channelsKt__DeprecatedKt$mapIndexed$13.L$0 = producerScope4;
            channelsKt__DeprecatedKt$mapIndexed$13.f11459a = channelIterator;
            channelsKt__DeprecatedKt$mapIndexed$13.f11460b = null;
            channelsKt__DeprecatedKt$mapIndexed$13.f11461c = i2;
            channelsKt__DeprecatedKt$mapIndexed$13.f11462d = 3;
            if (producerScope3.send(obj, channelsKt__DeprecatedKt$mapIndexed$13) != coroutine_suspended) {
            }
        } else if (i3 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            i2 = this.f11461c;
            it = (ChannelIterator) this.f11459a;
            ResultKt.throwOnFailure(obj);
            producerScope = (ProducerScope) this.L$0;
        }
        channelsKt__DeprecatedKt$mapIndexed$1 = this;
        channelsKt__DeprecatedKt$mapIndexed$1.L$0 = producerScope;
        channelsKt__DeprecatedKt$mapIndexed$1.f11459a = it;
        channelsKt__DeprecatedKt$mapIndexed$1.f11461c = i2;
        channelsKt__DeprecatedKt$mapIndexed$1.f11462d = 1;
        hasNext = it.hasNext(channelsKt__DeprecatedKt$mapIndexed$1);
        if (hasNext != coroutine_suspended) {
        }
    }
}
