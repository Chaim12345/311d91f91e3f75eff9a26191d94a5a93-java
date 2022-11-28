package kotlinx.coroutines.channels;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class TickerChannelsKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0071 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007f A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x007d -> B:14:0x0034). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object fixedDelayTicker(long j2, long j3, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) {
        TickerChannelsKt$fixedDelayTicker$1 tickerChannelsKt$fixedDelayTicker$1;
        Object coroutine_suspended;
        int i2;
        SendChannel<? super Unit> sendChannel2;
        Unit unit;
        if (continuation instanceof TickerChannelsKt$fixedDelayTicker$1) {
            tickerChannelsKt$fixedDelayTicker$1 = (TickerChannelsKt$fixedDelayTicker$1) continuation;
            int i3 = tickerChannelsKt$fixedDelayTicker$1.f11544d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                tickerChannelsKt$fixedDelayTicker$1.f11544d = i3 - Integer.MIN_VALUE;
                Object obj = tickerChannelsKt$fixedDelayTicker$1.f11543c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = tickerChannelsKt$fixedDelayTicker$1.f11544d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    tickerChannelsKt$fixedDelayTicker$1.f11542b = sendChannel;
                    tickerChannelsKt$fixedDelayTicker$1.f11541a = j2;
                    tickerChannelsKt$fixedDelayTicker$1.f11544d = 1;
                    if (DelayKt.delay(j3, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 == 1) {
                    j2 = tickerChannelsKt$fixedDelayTicker$1.f11541a;
                    sendChannel = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.f11542b;
                    ResultKt.throwOnFailure(obj);
                } else if (i2 == 2) {
                    j2 = tickerChannelsKt$fixedDelayTicker$1.f11541a;
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.f11542b;
                    ResultKt.throwOnFailure(obj);
                    tickerChannelsKt$fixedDelayTicker$1.f11542b = sendChannel2;
                    tickerChannelsKt$fixedDelayTicker$1.f11541a = j2;
                    tickerChannelsKt$fixedDelayTicker$1.f11544d = 3;
                    if (DelayKt.delay(j2, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sendChannel = sendChannel2;
                } else if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    j2 = tickerChannelsKt$fixedDelayTicker$1.f11541a;
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.f11542b;
                    ResultKt.throwOnFailure(obj);
                    sendChannel = sendChannel2;
                }
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedDelayTicker$1.f11542b = sendChannel;
                tickerChannelsKt$fixedDelayTicker$1.f11541a = j2;
                tickerChannelsKt$fixedDelayTicker$1.f11544d = 2;
                if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sendChannel2 = sendChannel;
                tickerChannelsKt$fixedDelayTicker$1.f11542b = sendChannel2;
                tickerChannelsKt$fixedDelayTicker$1.f11541a = j2;
                tickerChannelsKt$fixedDelayTicker$1.f11544d = 3;
                if (DelayKt.delay(j2, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                }
                sendChannel = sendChannel2;
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedDelayTicker$1.f11542b = sendChannel;
                tickerChannelsKt$fixedDelayTicker$1.f11541a = j2;
                tickerChannelsKt$fixedDelayTicker$1.f11544d = 2;
                if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                }
            }
        }
        tickerChannelsKt$fixedDelayTicker$1 = new TickerChannelsKt$fixedDelayTicker$1(continuation);
        Object obj2 = tickerChannelsKt$fixedDelayTicker$1.f11543c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = tickerChannelsKt$fixedDelayTicker$1.f11544d;
        if (i2 != 0) {
        }
        unit = Unit.INSTANCE;
        tickerChannelsKt$fixedDelayTicker$1.f11542b = sendChannel;
        tickerChannelsKt$fixedDelayTicker$1.f11541a = j2;
        tickerChannelsKt$fixedDelayTicker$1.f11544d = 2;
        if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0114 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00fc -> B:31:0x00aa). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0112 -> B:15:0x003d). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object fixedPeriodTicker(long j2, long j3, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) {
        TickerChannelsKt$fixedPeriodTicker$1 tickerChannelsKt$fixedPeriodTicker$1;
        Object coroutine_suspended;
        int i2;
        SendChannel<? super Unit> sendChannel2;
        long j4;
        long j5;
        long delayToNanos;
        long j6;
        long j7;
        SendChannel<? super Unit> sendChannel3;
        char c2;
        SendChannel<? super Unit> sendChannel4;
        long j8;
        long coerceAtLeast;
        char c3;
        long delayNanosToMillis;
        char c4;
        Unit unit;
        if (continuation instanceof TickerChannelsKt$fixedPeriodTicker$1) {
            tickerChannelsKt$fixedPeriodTicker$1 = (TickerChannelsKt$fixedPeriodTicker$1) continuation;
            int i3 = tickerChannelsKt$fixedPeriodTicker$1.f11549e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                tickerChannelsKt$fixedPeriodTicker$1.f11549e = i3 - Integer.MIN_VALUE;
                Object obj = tickerChannelsKt$fixedPeriodTicker$1.f11548d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = tickerChannelsKt$fixedPeriodTicker$1.f11549e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
                    long nanoTime = (timeSource != null ? timeSource.nanoTime() : System.nanoTime()) + EventLoop_commonKt.delayToNanos(j3);
                    sendChannel2 = sendChannel;
                    tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel2;
                    j4 = j2;
                    tickerChannelsKt$fixedPeriodTicker$1.f11545a = j4;
                    tickerChannelsKt$fixedPeriodTicker$1.f11546b = nanoTime;
                    tickerChannelsKt$fixedPeriodTicker$1.f11549e = 1;
                    if (DelayKt.delay(j3, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    j5 = nanoTime;
                } else if (i2 == 1) {
                    j5 = tickerChannelsKt$fixedPeriodTicker$1.f11546b;
                    long j9 = tickerChannelsKt$fixedPeriodTicker$1.f11545a;
                    ResultKt.throwOnFailure(obj);
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.f11547c;
                    j4 = j9;
                } else if (i2 == 2) {
                    j7 = tickerChannelsKt$fixedPeriodTicker$1.f11546b;
                    j8 = tickerChannelsKt$fixedPeriodTicker$1.f11545a;
                    sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.f11547c;
                    ResultKt.throwOnFailure(obj);
                    AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
                    if (timeSource2 == null) {
                    }
                    coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(j8 - r11, 0L);
                    if (coerceAtLeast == 0) {
                    }
                    c3 = 3;
                    delayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(coerceAtLeast);
                    tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel3;
                    tickerChannelsKt$fixedPeriodTicker$1.f11545a = j8;
                    tickerChannelsKt$fixedPeriodTicker$1.f11546b = j7;
                    c4 = 4;
                    tickerChannelsKt$fixedPeriodTicker$1.f11549e = 4;
                    if (DelayKt.delay(delayNanosToMillis, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                    }
                    long j10 = j7;
                    j5 = j8;
                    delayToNanos = j10;
                    sendChannel4 = sendChannel3;
                    long j11 = j5 + delayToNanos;
                    unit = Unit.INSTANCE;
                    tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel4;
                    tickerChannelsKt$fixedPeriodTicker$1.f11545a = j11;
                    tickerChannelsKt$fixedPeriodTicker$1.f11546b = delayToNanos;
                    tickerChannelsKt$fixedPeriodTicker$1.f11549e = 2;
                    if (sendChannel4.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                    }
                } else if (i2 == 3) {
                    j7 = tickerChannelsKt$fixedPeriodTicker$1.f11546b;
                    j6 = tickerChannelsKt$fixedPeriodTicker$1.f11545a;
                    sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.f11547c;
                    ResultKt.throwOnFailure(obj);
                    c2 = 3;
                    long j12 = j7;
                    j5 = j6;
                    delayToNanos = j12;
                    sendChannel4 = sendChannel3;
                    long j112 = j5 + delayToNanos;
                    unit = Unit.INSTANCE;
                    tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel4;
                    tickerChannelsKt$fixedPeriodTicker$1.f11545a = j112;
                    tickerChannelsKt$fixedPeriodTicker$1.f11546b = delayToNanos;
                    tickerChannelsKt$fixedPeriodTicker$1.f11549e = 2;
                    if (sendChannel4.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                    }
                } else if (i2 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    j7 = tickerChannelsKt$fixedPeriodTicker$1.f11546b;
                    j8 = tickerChannelsKt$fixedPeriodTicker$1.f11545a;
                    sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.f11547c;
                    ResultKt.throwOnFailure(obj);
                    c4 = 4;
                    c3 = 3;
                    long j102 = j7;
                    j5 = j8;
                    delayToNanos = j102;
                    sendChannel4 = sendChannel3;
                    long j1122 = j5 + delayToNanos;
                    unit = Unit.INSTANCE;
                    tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel4;
                    tickerChannelsKt$fixedPeriodTicker$1.f11545a = j1122;
                    tickerChannelsKt$fixedPeriodTicker$1.f11546b = delayToNanos;
                    tickerChannelsKt$fixedPeriodTicker$1.f11549e = 2;
                    if (sendChannel4.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sendChannel3 = sendChannel4;
                    j7 = delayToNanos;
                    j8 = j1122;
                    AbstractTimeSource timeSource22 = AbstractTimeSourceKt.getTimeSource();
                    long nanoTime2 = timeSource22 == null ? timeSource22.nanoTime() : System.nanoTime();
                    coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(j8 - nanoTime2, 0L);
                    if (coerceAtLeast == 0 || j7 == 0) {
                        c3 = 3;
                        delayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(coerceAtLeast);
                        tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel3;
                        tickerChannelsKt$fixedPeriodTicker$1.f11545a = j8;
                        tickerChannelsKt$fixedPeriodTicker$1.f11546b = j7;
                        c4 = 4;
                        tickerChannelsKt$fixedPeriodTicker$1.f11549e = 4;
                        if (DelayKt.delay(delayNanosToMillis, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        long j1022 = j7;
                        j5 = j8;
                        delayToNanos = j1022;
                        sendChannel4 = sendChannel3;
                        long j11222 = j5 + delayToNanos;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel4;
                        tickerChannelsKt$fixedPeriodTicker$1.f11545a = j11222;
                        tickerChannelsKt$fixedPeriodTicker$1.f11546b = delayToNanos;
                        tickerChannelsKt$fixedPeriodTicker$1.f11549e = 2;
                        if (sendChannel4.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        }
                    } else {
                        long j13 = j7 - ((nanoTime2 - j8) % j7);
                        j6 = nanoTime2 + j13;
                        long delayNanosToMillis2 = EventLoop_commonKt.delayNanosToMillis(j13);
                        tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel3;
                        tickerChannelsKt$fixedPeriodTicker$1.f11545a = j6;
                        tickerChannelsKt$fixedPeriodTicker$1.f11546b = j7;
                        c2 = 3;
                        tickerChannelsKt$fixedPeriodTicker$1.f11549e = 3;
                        if (DelayKt.delay(delayNanosToMillis2, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        long j122 = j7;
                        j5 = j6;
                        delayToNanos = j122;
                        sendChannel4 = sendChannel3;
                        long j112222 = j5 + delayToNanos;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel4;
                        tickerChannelsKt$fixedPeriodTicker$1.f11545a = j112222;
                        tickerChannelsKt$fixedPeriodTicker$1.f11546b = delayToNanos;
                        tickerChannelsKt$fixedPeriodTicker$1.f11549e = 2;
                        if (sendChannel4.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        }
                    }
                }
                delayToNanos = EventLoop_commonKt.delayToNanos(j4);
                sendChannel4 = sendChannel2;
                long j1122222 = j5 + delayToNanos;
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel4;
                tickerChannelsKt$fixedPeriodTicker$1.f11545a = j1122222;
                tickerChannelsKt$fixedPeriodTicker$1.f11546b = delayToNanos;
                tickerChannelsKt$fixedPeriodTicker$1.f11549e = 2;
                if (sendChannel4.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                }
            }
        }
        tickerChannelsKt$fixedPeriodTicker$1 = new TickerChannelsKt$fixedPeriodTicker$1(continuation);
        Object obj2 = tickerChannelsKt$fixedPeriodTicker$1.f11548d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = tickerChannelsKt$fixedPeriodTicker$1.f11549e;
        if (i2 != 0) {
        }
        delayToNanos = EventLoop_commonKt.delayToNanos(j4);
        sendChannel4 = sendChannel2;
        long j11222222 = j5 + delayToNanos;
        unit = Unit.INSTANCE;
        tickerChannelsKt$fixedPeriodTicker$1.f11547c = sendChannel4;
        tickerChannelsKt$fixedPeriodTicker$1.f11545a = j11222222;
        tickerChannelsKt$fixedPeriodTicker$1.f11546b = delayToNanos;
        tickerChannelsKt$fixedPeriodTicker$1.f11549e = 2;
        if (sendChannel4.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
        }
    }

    @ObsoleteCoroutinesApi
    @NotNull
    public static final ReceiveChannel<Unit> ticker(long j2, long j3, @NotNull CoroutineContext coroutineContext, @NotNull TickerMode tickerMode) {
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j2 + " ms").toString());
        }
        if (j3 >= 0) {
            return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext), 0, new TickerChannelsKt$ticker$3(tickerMode, j2, j3, null));
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j3 + " ms").toString());
    }

    public static /* synthetic */ ReceiveChannel ticker$default(long j2, long j3, CoroutineContext coroutineContext, TickerMode tickerMode, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j3 = j2;
        }
        if ((i2 & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j2, j3, coroutineContext, tickerMode);
    }
}
