package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final /* synthetic */ class FlowKt__ChannelsKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "'BroadcastChannel' is obsolete and all corresponding operators are deprecated in the favour of StateFlow and SharedFlow")
    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull final BroadcastChannel<T> broadcastChannel) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
                Object coroutine_suspended;
                Object emitAll = FlowKt.emitAll(flowCollector, BroadcastChannel.this.openSubscription(), continuation);
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                return emitAll == coroutine_suspended ? emitAll : Unit.INSTANCE;
            }
        };
    }

    @NotNull
    public static final <T> Flow<T> consumeAsFlow(@NotNull ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, true, null, 0, null, 28, null);
    }

    @Nullable
    public static final <T> Object emitAll(@NotNull FlowCollector<? super T> flowCollector, @NotNull ReceiveChannel<? extends T> receiveChannel, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object emitAllImpl$FlowKt__ChannelsKt = emitAllImpl$FlowKt__ChannelsKt(flowCollector, receiveChannel, true, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return emitAllImpl$FlowKt__ChannelsKt == coroutine_suspended ? emitAllImpl$FlowKt__ChannelsKt : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076 A[Catch: all -> 0x0055, TRY_LEAVE, TryCatch #0 {all -> 0x0055, blocks: (B:13:0x0032, B:28:0x0070, B:30:0x0076, B:36:0x0084, B:37:0x0085, B:18:0x004b), top: B:47:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0085 A[Catch: all -> 0x0055, TRY_LEAVE, TryCatch #0 {all -> 0x0055, blocks: (B:13:0x0032, B:28:0x0070, B:30:0x0076, B:36:0x0084, B:37:0x0085, B:18:0x004b), top: B:47:0x0022 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0095 -> B:14:0x0035). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object emitAllImpl$FlowKt__ChannelsKt(FlowCollector<? super T> flowCollector, ReceiveChannel<? extends T> receiveChannel, boolean z, Continuation<? super Unit> continuation) {
        FlowKt__ChannelsKt$emitAllImpl$1 flowKt__ChannelsKt$emitAllImpl$1;
        Object coroutine_suspended;
        int i2;
        Object obj;
        FlowCollector flowCollector2;
        boolean z2;
        try {
            if (continuation instanceof FlowKt__ChannelsKt$emitAllImpl$1) {
                flowKt__ChannelsKt$emitAllImpl$1 = (FlowKt__ChannelsKt$emitAllImpl$1) continuation;
                int i3 = flowKt__ChannelsKt$emitAllImpl$1.f11662e;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    flowKt__ChannelsKt$emitAllImpl$1.f11662e = i3 - Integer.MIN_VALUE;
                    Object obj2 = flowKt__ChannelsKt$emitAllImpl$1.f11661d;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = flowKt__ChannelsKt$emitAllImpl$1.f11662e;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowKt.ensureActive(flowCollector);
                        z2 = z;
                        flowKt__ChannelsKt$emitAllImpl$1.f11658a = flowCollector;
                        flowKt__ChannelsKt$emitAllImpl$1.f11659b = receiveChannel;
                        flowKt__ChannelsKt$emitAllImpl$1.f11660c = z2;
                        flowKt__ChannelsKt$emitAllImpl$1.f11662e = 1;
                        obj = receiveChannel.mo1627receiveCatchingJP2dKIU(flowKt__ChannelsKt$emitAllImpl$1);
                        if (obj == coroutine_suspended) {
                        }
                    } else if (i2 == 1) {
                        flowCollector = (FlowCollector<? super T>) flowKt__ChannelsKt$emitAllImpl$1.f11660c;
                        receiveChannel = (ReceiveChannel) flowKt__ChannelsKt$emitAllImpl$1.f11659b;
                        FlowCollector flowCollector3 = (FlowCollector) flowKt__ChannelsKt$emitAllImpl$1.f11658a;
                        ResultKt.throwOnFailure(obj2);
                        obj = ((ChannelResult) obj2).m1646unboximpl();
                        flowCollector2 = flowCollector3;
                        if (!ChannelResult.m1642isClosedimpl(obj)) {
                        }
                    } else if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        flowCollector = (FlowCollector<? super T>) flowKt__ChannelsKt$emitAllImpl$1.f11660c;
                        receiveChannel = (ReceiveChannel) flowKt__ChannelsKt$emitAllImpl$1.f11659b;
                        FlowCollector<? super T> flowCollector4 = (FlowCollector) flowKt__ChannelsKt$emitAllImpl$1.f11658a;
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector<? super T> flowCollector5 = flowCollector4;
                        FlowCollector<? super T> flowCollector6 = flowCollector5;
                        z2 = flowCollector;
                        flowCollector = flowCollector6;
                        try {
                            flowKt__ChannelsKt$emitAllImpl$1.f11658a = flowCollector;
                            flowKt__ChannelsKt$emitAllImpl$1.f11659b = receiveChannel;
                            flowKt__ChannelsKt$emitAllImpl$1.f11660c = z2;
                            flowKt__ChannelsKt$emitAllImpl$1.f11662e = 1;
                            obj = receiveChannel.mo1627receiveCatchingJP2dKIU(flowKt__ChannelsKt$emitAllImpl$1);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            FlowCollector<? super T> flowCollector7 = z2;
                            flowCollector2 = flowCollector;
                            flowCollector = flowCollector7;
                            if (!ChannelResult.m1642isClosedimpl(obj)) {
                                Throwable m1638exceptionOrNullimpl = ChannelResult.m1638exceptionOrNullimpl(obj);
                                if (m1638exceptionOrNullimpl != null) {
                                    throw m1638exceptionOrNullimpl;
                                }
                                if (flowCollector != null) {
                                    ChannelsKt.cancelConsumed(receiveChannel, null);
                                }
                                return Unit.INSTANCE;
                            }
                            Object m1640getOrThrowimpl = ChannelResult.m1640getOrThrowimpl(obj);
                            flowKt__ChannelsKt$emitAllImpl$1.f11658a = flowCollector2;
                            flowKt__ChannelsKt$emitAllImpl$1.f11659b = receiveChannel;
                            flowKt__ChannelsKt$emitAllImpl$1.f11660c = (boolean) flowCollector;
                            flowKt__ChannelsKt$emitAllImpl$1.f11662e = 2;
                            Object emit = flowCollector2.emit(m1640getOrThrowimpl, flowKt__ChannelsKt$emitAllImpl$1);
                            flowCollector5 = flowCollector2;
                            if (emit == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            FlowCollector<? super T> flowCollector62 = flowCollector5;
                            z2 = flowCollector;
                            flowCollector = flowCollector62;
                            flowKt__ChannelsKt$emitAllImpl$1.f11658a = flowCollector;
                            flowKt__ChannelsKt$emitAllImpl$1.f11659b = receiveChannel;
                            flowKt__ChannelsKt$emitAllImpl$1.f11660c = z2;
                            flowKt__ChannelsKt$emitAllImpl$1.f11662e = 1;
                            obj = receiveChannel.mo1627receiveCatchingJP2dKIU(flowKt__ChannelsKt$emitAllImpl$1);
                            if (obj == coroutine_suspended) {
                            }
                        } catch (Throwable th) {
                            FlowCollector<? super T> flowCollector8 = z2;
                            th = th;
                            flowCollector = flowCollector8;
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                if (flowCollector != null) {
                                    ChannelsKt.cancelConsumed(receiveChannel, th);
                                }
                                throw th2;
                            }
                        }
                    }
                }
            }
            if (i2 != 0) {
            }
        } catch (Throwable th3) {
            th = th3;
        }
        flowKt__ChannelsKt$emitAllImpl$1 = new FlowKt__ChannelsKt$emitAllImpl$1(continuation);
        Object obj22 = flowKt__ChannelsKt$emitAllImpl$1.f11661d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ChannelsKt$emitAllImpl$1.f11662e;
    }

    @FlowPreview
    @NotNull
    public static final <T> ReceiveChannel<T> produceIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope) {
        return ChannelFlowKt.asChannelFlow(flow).produceImpl(coroutineScope);
    }

    @NotNull
    public static final <T> Flow<T> receiveAsFlow(@NotNull ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, false, null, 0, null, 28, null);
    }
}
