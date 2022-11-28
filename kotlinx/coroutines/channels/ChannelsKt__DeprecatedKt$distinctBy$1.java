package kotlinx.coroutines.channels;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.HashSet;
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
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2, 2}, l = {387, 388, 390}, m = "invokeSuspend", n = {"$this$produce", UserMetadata.KEYDATA_FILENAME, "$this$produce", UserMetadata.KEYDATA_FILENAME, "e", "$this$produce", UserMetadata.KEYDATA_FILENAME, "k"}, s = {"L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$distinctBy$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11363a;

    /* renamed from: b  reason: collision with root package name */
    Object f11364b;

    /* renamed from: c  reason: collision with root package name */
    Object f11365c;

    /* renamed from: d  reason: collision with root package name */
    int f11366d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ ReceiveChannel f11367e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function2 f11368f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__DeprecatedKt$distinctBy$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f11367e = receiveChannel;
        this.f11368f = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$distinctBy$1 channelsKt__DeprecatedKt$distinctBy$1 = new ChannelsKt__DeprecatedKt$distinctBy$1(this.f11367e, this.f11368f, continuation);
        channelsKt__DeprecatedKt$distinctBy$1.L$0 = obj;
        return channelsKt__DeprecatedKt$distinctBy$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$distinctBy$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00c5 -> B:29:0x00cb). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00d2 -> B:13:0x0071). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ProducerScope producerScope;
        ChannelsKt__DeprecatedKt$distinctBy$1 channelsKt__DeprecatedKt$distinctBy$1;
        HashSet hashSet;
        ChannelIterator channelIterator;
        ProducerScope producerScope2;
        HashSet hashSet2;
        Object obj2;
        ChannelIterator channelIterator2;
        Object obj3;
        ChannelsKt__DeprecatedKt$distinctBy$1 channelsKt__DeprecatedKt$distinctBy$12;
        ProducerScope producerScope3;
        HashSet hashSet3;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f11366d;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            HashSet hashSet4 = new HashSet();
            ChannelIterator it = this.f11367e.iterator();
            producerScope = (ProducerScope) this.L$0;
            channelsKt__DeprecatedKt$distinctBy$1 = this;
            hashSet = hashSet4;
            channelIterator = it;
            channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$distinctBy$1.f11363a = hashSet;
            channelsKt__DeprecatedKt$distinctBy$1.f11364b = channelIterator;
            channelsKt__DeprecatedKt$distinctBy$1.f11365c = null;
            channelsKt__DeprecatedKt$distinctBy$1.f11366d = 1;
            hasNext = channelIterator.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            producerScope3 = (ProducerScope) this.L$0;
            hashSet3 = (HashSet) this.f11363a;
            channelIterator2 = (ChannelIterator) this.f11364b;
            obj3 = coroutine_suspended;
            channelsKt__DeprecatedKt$distinctBy$12 = this;
            if (((Boolean) obj).booleanValue()) {
            }
        } else if (i2 == 2) {
            Object obj4 = this.f11365c;
            channelIterator2 = (ChannelIterator) this.f11364b;
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            hashSet2 = (HashSet) this.f11363a;
            obj2 = obj4;
            obj3 = coroutine_suspended;
            channelsKt__DeprecatedKt$distinctBy$12 = this;
            if (hashSet2.contains(obj)) {
            }
        } else if (i2 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            Object obj5 = this.f11365c;
            channelIterator2 = (ChannelIterator) this.f11364b;
            HashSet hashSet5 = (HashSet) this.f11363a;
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__DeprecatedKt$distinctBy$1 = this;
            hashSet5.add(obj5);
            channelIterator = channelIterator2;
            hashSet = hashSet5;
            producerScope = producerScope4;
            channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$distinctBy$1.f11363a = hashSet;
            channelsKt__DeprecatedKt$distinctBy$1.f11364b = channelIterator;
            channelsKt__DeprecatedKt$distinctBy$1.f11365c = null;
            channelsKt__DeprecatedKt$distinctBy$1.f11366d = 1;
            hasNext = channelIterator.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj6 = coroutine_suspended;
            channelsKt__DeprecatedKt$distinctBy$12 = channelsKt__DeprecatedKt$distinctBy$1;
            obj = hasNext;
            producerScope3 = producerScope;
            hashSet3 = hashSet;
            channelIterator2 = channelIterator;
            obj3 = obj6;
            if (((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            Object next = channelIterator2.next();
            Function2 function2 = channelsKt__DeprecatedKt$distinctBy$12.f11368f;
            channelsKt__DeprecatedKt$distinctBy$12.L$0 = producerScope3;
            channelsKt__DeprecatedKt$distinctBy$12.f11363a = hashSet3;
            channelsKt__DeprecatedKt$distinctBy$12.f11364b = channelIterator2;
            channelsKt__DeprecatedKt$distinctBy$12.f11365c = next;
            channelsKt__DeprecatedKt$distinctBy$12.f11366d = 2;
            Object invoke = function2.invoke(next, channelsKt__DeprecatedKt$distinctBy$12);
            if (invoke == obj3) {
                return obj3;
            }
            HashSet hashSet6 = hashSet3;
            obj2 = next;
            obj = invoke;
            producerScope2 = producerScope3;
            hashSet2 = hashSet6;
            if (hashSet2.contains(obj)) {
                channelsKt__DeprecatedKt$distinctBy$12.L$0 = producerScope2;
                channelsKt__DeprecatedKt$distinctBy$12.f11363a = hashSet2;
                channelsKt__DeprecatedKt$distinctBy$12.f11364b = channelIterator2;
                channelsKt__DeprecatedKt$distinctBy$12.f11365c = obj;
                channelsKt__DeprecatedKt$distinctBy$12.f11366d = 3;
                if (producerScope2.send(obj2, channelsKt__DeprecatedKt$distinctBy$12) == obj3) {
                    return obj3;
                }
                hashSet5 = hashSet2;
                producerScope4 = producerScope2;
                Object obj7 = obj3;
                obj5 = obj;
                channelsKt__DeprecatedKt$distinctBy$1 = channelsKt__DeprecatedKt$distinctBy$12;
                coroutine_suspended = obj7;
                hashSet5.add(obj5);
                channelIterator = channelIterator2;
                hashSet = hashSet5;
                producerScope = producerScope4;
                channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
                channelsKt__DeprecatedKt$distinctBy$1.f11363a = hashSet;
                channelsKt__DeprecatedKt$distinctBy$1.f11364b = channelIterator;
                channelsKt__DeprecatedKt$distinctBy$1.f11365c = null;
                channelsKt__DeprecatedKt$distinctBy$1.f11366d = 1;
                hasNext = channelIterator.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
                if (hasNext != coroutine_suspended) {
                }
            } else {
                channelsKt__DeprecatedKt$distinctBy$1 = channelsKt__DeprecatedKt$distinctBy$12;
                coroutine_suspended = obj3;
                channelIterator = channelIterator2;
                hashSet = hashSet2;
                producerScope = producerScope2;
                channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
                channelsKt__DeprecatedKt$distinctBy$1.f11363a = hashSet;
                channelsKt__DeprecatedKt$distinctBy$1.f11364b = channelIterator;
                channelsKt__DeprecatedKt$distinctBy$1.f11365c = null;
                channelsKt__DeprecatedKt$distinctBy$1.f11366d = 1;
                hasNext = channelIterator.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
                if (hasNext != coroutine_suspended) {
                }
            }
        }
    }
}
