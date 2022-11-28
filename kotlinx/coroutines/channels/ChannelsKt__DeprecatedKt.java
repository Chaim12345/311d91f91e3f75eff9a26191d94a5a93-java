package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final /* synthetic */ class ChannelsKt__DeprecatedKt {
    /* JADX WARN: Removed duplicated region for block: B:41:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0035  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object any(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$any$1 channelsKt__DeprecatedKt$any$1;
        Object coroutine_suspended;
        int i2;
        try {
            if (continuation instanceof ChannelsKt__DeprecatedKt$any$1) {
                channelsKt__DeprecatedKt$any$1 = (ChannelsKt__DeprecatedKt$any$1) continuation;
                int i3 = channelsKt__DeprecatedKt$any$1.f11353c;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    channelsKt__DeprecatedKt$any$1.f11353c = i3 - Integer.MIN_VALUE;
                    Object obj = channelsKt__DeprecatedKt$any$1.f11352b;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = channelsKt__DeprecatedKt$any$1.f11353c;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$any$1.f11351a = receiveChannel;
                        channelsKt__DeprecatedKt$any$1.f11353c = 1;
                        obj = it.hasNext(channelsKt__DeprecatedKt$any$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        receiveChannel = (ReceiveChannel) channelsKt__DeprecatedKt$any$1.f11351a;
                        ResultKt.throwOnFailure(obj);
                    }
                    ChannelsKt.cancelConsumed(receiveChannel, null);
                    return obj;
                }
            }
            if (i2 != 0) {
            }
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return obj;
        } finally {
        }
        channelsKt__DeprecatedKt$any$1 = new ChannelsKt__DeprecatedKt$any$1(continuation);
        Object obj2 = channelsKt__DeprecatedKt$any$1.f11352b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$any$1.f11353c;
    }

    @PublishedApi
    @NotNull
    public static final Function1<Throwable, Unit> consumes(@NotNull ReceiveChannel<?> receiveChannel) {
        return new ChannelsKt__DeprecatedKt$consumes$1(receiveChannel);
    }

    @PublishedApi
    @NotNull
    public static final Function1<Throwable, Unit> consumesAll(@NotNull ReceiveChannel<?>... receiveChannelArr) {
        return new ChannelsKt__DeprecatedKt$consumesAll$1(receiveChannelArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0069 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:60:0x0031, B:74:0x0061, B:76:0x0069, B:77:0x0073), top: B:90:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0073 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:60:0x0031, B:74:0x0061, B:76:0x0069, B:77:0x0073), top: B:90:0x0031 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x005d -> B:73:0x0060). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object count(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$count$1 channelsKt__DeprecatedKt$count$1;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        Throwable th;
        Ref.IntRef intRef;
        ReceiveChannel receiveChannel3;
        ChannelIterator it;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$count$1) {
            channelsKt__DeprecatedKt$count$1 = (ChannelsKt__DeprecatedKt$count$1) continuation;
            int i3 = channelsKt__DeprecatedKt$count$1.f11360e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$count$1.f11360e = i3 - Integer.MIN_VALUE;
                Object obj = channelsKt__DeprecatedKt$count$1.f11359d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$count$1.f11360e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        intRef = new Ref.IntRef();
                        receiveChannel3 = receiveChannel;
                        it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$count$1.f11356a = intRef;
                        channelsKt__DeprecatedKt$count$1.f11357b = receiveChannel3;
                        channelsKt__DeprecatedKt$count$1.f11358c = it;
                        channelsKt__DeprecatedKt$count$1.f11360e = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$count$1);
                        if (hasNext != coroutine_suspended) {
                        }
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    it = (ChannelIterator) channelsKt__DeprecatedKt$count$1.f11358c;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$count$1.f11357b;
                    intRef = (Ref.IntRef) channelsKt__DeprecatedKt$count$1.f11356a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        if (!((Boolean) obj).booleanValue()) {
                            it.next();
                            intRef.element++;
                            receiveChannel3 = receiveChannel2;
                            try {
                                channelsKt__DeprecatedKt$count$1.f11356a = intRef;
                                channelsKt__DeprecatedKt$count$1.f11357b = receiveChannel3;
                                channelsKt__DeprecatedKt$count$1.f11358c = it;
                                channelsKt__DeprecatedKt$count$1.f11360e = 1;
                                hasNext = it.hasNext(channelsKt__DeprecatedKt$count$1);
                                if (hasNext != coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                receiveChannel2 = receiveChannel3;
                                obj = hasNext;
                                if (!((Boolean) obj).booleanValue()) {
                                    Unit unit = Unit.INSTANCE;
                                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                                    return Boxing.boxInt(intRef.element);
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                receiveChannel2 = receiveChannel3;
                                try {
                                    throw th;
                                } catch (Throwable th4) {
                                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                                    throw th4;
                                }
                            }
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        throw th;
                    }
                }
            }
        }
        channelsKt__DeprecatedKt$count$1 = new ChannelsKt__DeprecatedKt$count$1(continuation);
        Object obj2 = channelsKt__DeprecatedKt$count$1.f11359d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$count$1.f11360e;
        if (i2 != 0) {
        }
    }

    @PublishedApi
    @NotNull
    public static final <E, K> ReceiveChannel<E> distinctBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$distinctBy$1(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel drop(ReceiveChannel receiveChannel, int i2, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$drop$1(i2, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i2, CoroutineContext coroutineContext, int i3, Object obj) {
        ReceiveChannel drop;
        if ((i3 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        drop = drop(receiveChannel, i2, coroutineContext);
        return drop;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel dropWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$dropWhile$1(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        ReceiveChannel dropWhile;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        dropWhile = dropWhile(receiveChannel, coroutineContext, function2);
        return dropWhile;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x006c A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #2 {all -> 0x0039, blocks: (B:62:0x0035, B:77:0x0064, B:79:0x006c, B:85:0x007b, B:86:0x0092), top: B:98:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x007b A[Catch: all -> 0x0039, TRY_ENTER, TryCatch #2 {all -> 0x0039, blocks: (B:62:0x0035, B:77:0x0064, B:79:0x006c, B:85:0x007b, B:86:0x0092), top: B:98:0x0035 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:75:0x005f -> B:76:0x0063). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object elementAt(ReceiveChannel receiveChannel, int i2, Continuation continuation) {
        ChannelsKt__DeprecatedKt$elementAt$1 channelsKt__DeprecatedKt$elementAt$1;
        Object coroutine_suspended;
        int i3;
        ReceiveChannel receiveChannel2;
        Throwable th;
        int i4;
        ChannelIterator it;
        Object hasNext;
        try {
            if (continuation instanceof ChannelsKt__DeprecatedKt$elementAt$1) {
                channelsKt__DeprecatedKt$elementAt$1 = (ChannelsKt__DeprecatedKt$elementAt$1) continuation;
                int i5 = channelsKt__DeprecatedKt$elementAt$1.f11384f;
                if ((i5 & Integer.MIN_VALUE) != 0) {
                    channelsKt__DeprecatedKt$elementAt$1.f11384f = i5 - Integer.MIN_VALUE;
                    Object obj = channelsKt__DeprecatedKt$elementAt$1.f11383e;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i3 = channelsKt__DeprecatedKt$elementAt$1.f11384f;
                    if (i3 != 0) {
                        ResultKt.throwOnFailure(obj);
                        if (i2 < 0) {
                            throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + i2 + '.');
                        }
                        i4 = 0;
                        it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$elementAt$1.f11381c = receiveChannel;
                        channelsKt__DeprecatedKt$elementAt$1.f11382d = it;
                        channelsKt__DeprecatedKt$elementAt$1.f11379a = i2;
                        channelsKt__DeprecatedKt$elementAt$1.f11380b = i4;
                        channelsKt__DeprecatedKt$elementAt$1.f11384f = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$elementAt$1);
                        if (hasNext != coroutine_suspended) {
                        }
                    } else if (i3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        int i6 = channelsKt__DeprecatedKt$elementAt$1.f11380b;
                        i2 = channelsKt__DeprecatedKt$elementAt$1.f11379a;
                        it = (ChannelIterator) channelsKt__DeprecatedKt$elementAt$1.f11382d;
                        receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$elementAt$1.f11381c;
                        try {
                            ResultKt.throwOnFailure(obj);
                            if (!((Boolean) obj).booleanValue()) {
                                Object next = it.next();
                                int i7 = i6 + 1;
                                if (i2 == i6) {
                                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                                    return next;
                                }
                                receiveChannel = receiveChannel2;
                                i4 = i7;
                                channelsKt__DeprecatedKt$elementAt$1.f11381c = receiveChannel;
                                channelsKt__DeprecatedKt$elementAt$1.f11382d = it;
                                channelsKt__DeprecatedKt$elementAt$1.f11379a = i2;
                                channelsKt__DeprecatedKt$elementAt$1.f11380b = i4;
                                channelsKt__DeprecatedKt$elementAt$1.f11384f = 1;
                                hasNext = it.hasNext(channelsKt__DeprecatedKt$elementAt$1);
                                if (hasNext != coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                receiveChannel2 = receiveChannel;
                                i6 = i4;
                                obj = hasNext;
                                if (!((Boolean) obj).booleanValue()) {
                                    throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + i2 + '.');
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                ChannelsKt.cancelConsumed(receiveChannel2, th);
                                throw th3;
                            }
                        }
                    }
                }
            }
            if (i3 != 0) {
            }
        } catch (Throwable th4) {
            receiveChannel2 = receiveChannel;
            th = th4;
        }
        channelsKt__DeprecatedKt$elementAt$1 = new ChannelsKt__DeprecatedKt$elementAt$1(continuation);
        Object obj2 = channelsKt__DeprecatedKt$elementAt$1.f11383e;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i3 = channelsKt__DeprecatedKt$elementAt$1.f11384f;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x006b A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #2 {all -> 0x007d, blocks: (B:76:0x0063, B:78:0x006b, B:73:0x0052, B:72:0x004e), top: B:96:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0079  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x0060 -> B:76:0x0063). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object elementAtOrNull(ReceiveChannel receiveChannel, int i2, Continuation continuation) {
        ChannelsKt__DeprecatedKt$elementAtOrNull$1 channelsKt__DeprecatedKt$elementAtOrNull$1;
        Object coroutine_suspended;
        int i3;
        int i4;
        ChannelIterator it;
        Throwable th;
        ReceiveChannel receiveChannel2;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$elementAtOrNull$1) {
            channelsKt__DeprecatedKt$elementAtOrNull$1 = (ChannelsKt__DeprecatedKt$elementAtOrNull$1) continuation;
            int i5 = channelsKt__DeprecatedKt$elementAtOrNull$1.f11390f;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$elementAtOrNull$1.f11390f = i5 - Integer.MIN_VALUE;
                Object obj = channelsKt__DeprecatedKt$elementAtOrNull$1.f11389e;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i3 = channelsKt__DeprecatedKt$elementAtOrNull$1.f11390f;
                if (i3 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (i2 < 0) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return null;
                    }
                    i4 = 0;
                    try {
                        it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$elementAtOrNull$1.f11387c = receiveChannel;
                        channelsKt__DeprecatedKt$elementAtOrNull$1.f11388d = it;
                        channelsKt__DeprecatedKt$elementAtOrNull$1.f11385a = i2;
                        channelsKt__DeprecatedKt$elementAtOrNull$1.f11386b = i4;
                        channelsKt__DeprecatedKt$elementAtOrNull$1.f11390f = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$elementAtOrNull$1);
                        if (hasNext == coroutine_suspended) {
                        }
                        if (((Boolean) hasNext).booleanValue()) {
                        }
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i3 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    int i6 = channelsKt__DeprecatedKt$elementAtOrNull$1.f11386b;
                    i2 = channelsKt__DeprecatedKt$elementAtOrNull$1.f11385a;
                    it = (ChannelIterator) channelsKt__DeprecatedKt$elementAtOrNull$1.f11388d;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$elementAtOrNull$1.f11387c;
                    try {
                        ResultKt.throwOnFailure(obj);
                        i4 = i6;
                        receiveChannel = receiveChannel2;
                        hasNext = obj;
                        if (((Boolean) hasNext).booleanValue()) {
                            Object next = it.next();
                            int i7 = i4 + 1;
                            if (i2 == i4) {
                                ChannelsKt.cancelConsumed(receiveChannel, null);
                                return next;
                            }
                            i4 = i7;
                            channelsKt__DeprecatedKt$elementAtOrNull$1.f11387c = receiveChannel;
                            channelsKt__DeprecatedKt$elementAtOrNull$1.f11388d = it;
                            channelsKt__DeprecatedKt$elementAtOrNull$1.f11385a = i2;
                            channelsKt__DeprecatedKt$elementAtOrNull$1.f11386b = i4;
                            channelsKt__DeprecatedKt$elementAtOrNull$1.f11390f = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$elementAtOrNull$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (((Boolean) hasNext).booleanValue()) {
                                ChannelsKt.cancelConsumed(receiveChannel, null);
                                return null;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
            }
        }
        channelsKt__DeprecatedKt$elementAtOrNull$1 = new ChannelsKt__DeprecatedKt$elementAtOrNull$1(continuation);
        Object obj2 = channelsKt__DeprecatedKt$elementAtOrNull$1.f11389e;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i3 = channelsKt__DeprecatedKt$elementAtOrNull$1.f11390f;
        if (i3 != 0) {
        }
    }

    @PublishedApi
    @NotNull
    public static final <E> ReceiveChannel<E> filter(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$filter$1(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterIndexed(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$filterIndexed$1(receiveChannel, function3, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i2, Object obj) {
        ReceiveChannel filterIndexed;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        filterIndexed = filterIndexed(receiveChannel, coroutineContext, function3);
        return filterIndexed;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterNot(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filter(receiveChannel, coroutineContext, new ChannelsKt__DeprecatedKt$filterNot$1(function2, null));
    }

    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        ReceiveChannel filterNot;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        filterNot = filterNot(receiveChannel, coroutineContext, function2);
        return filterNot;
    }

    @PublishedApi
    @NotNull
    public static final <E> ReceiveChannel<E> filterNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        ReceiveChannel<E> filter$default;
        filter$default = filter$default(receiveChannel, null, new ChannelsKt__DeprecatedKt$filterNotNull$1(null), 1, null);
        return filter$default;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0065 A[Catch: all -> 0x0035, TryCatch #0 {all -> 0x0035, blocks: (B:58:0x0031, B:71:0x005c, B:73:0x0065, B:75:0x006b, B:67:0x004a, B:77:0x0070), top: B:86:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0070 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #0 {all -> 0x0035, blocks: (B:58:0x0031, B:71:0x005c, B:73:0x0065, B:75:0x006b, B:67:0x004a, B:77:0x0070), top: B:86:0x0031 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0059 -> B:71:0x005c). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object filterNotNullTo(ReceiveChannel receiveChannel, Collection collection, Continuation continuation) {
        ChannelsKt__DeprecatedKt$filterNotNullTo$1 channelsKt__DeprecatedKt$filterNotNullTo$1;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Collection collection2;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$filterNotNullTo$1) {
            channelsKt__DeprecatedKt$filterNotNullTo$1 = (ChannelsKt__DeprecatedKt$filterNotNullTo$1) continuation;
            int i3 = channelsKt__DeprecatedKt$filterNotNullTo$1.f11411e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$filterNotNullTo$1.f11411e = i3 - Integer.MIN_VALUE;
                Object obj = channelsKt__DeprecatedKt$filterNotNullTo$1.f11410d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$filterNotNullTo$1.f11411e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        receiveChannel2 = receiveChannel;
                        it = receiveChannel.iterator();
                        collection2 = collection;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11407a = collection2;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11408b = receiveChannel2;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11409c = it;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11411e = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$1);
                        if (hasNext != coroutine_suspended) {
                        }
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    it = (ChannelIterator) channelsKt__DeprecatedKt$filterNotNullTo$1.f11409c;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$filterNotNullTo$1.f11408b;
                    Collection collection3 = (Collection) channelsKt__DeprecatedKt$filterNotNullTo$1.f11407a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        if (!((Boolean) obj).booleanValue()) {
                            Unit unit = Unit.INSTANCE;
                            ChannelsKt.cancelConsumed(receiveChannel2, null);
                            return collection3;
                        }
                        Object next = it.next();
                        if (next != null) {
                            collection3.add(next);
                        }
                        collection2 = collection3;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11407a = collection2;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11408b = receiveChannel2;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11409c = it;
                        channelsKt__DeprecatedKt$filterNotNullTo$1.f11411e = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$1);
                        if (hasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        collection3 = collection2;
                        obj = hasNext;
                        if (!((Boolean) obj).booleanValue()) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
            }
        }
        channelsKt__DeprecatedKt$filterNotNullTo$1 = new ChannelsKt__DeprecatedKt$filterNotNullTo$1(continuation);
        Object obj2 = channelsKt__DeprecatedKt$filterNotNullTo$1.f11410d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$filterNotNullTo$1.f11411e;
        if (i2 != 0) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x007e A[Catch: all -> 0x009d, TryCatch #2 {all -> 0x009d, blocks: (B:76:0x0062, B:80:0x0075, B:82:0x007e, B:84:0x0084, B:88:0x0097, B:75:0x005e), top: B:100:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0097 A[Catch: all -> 0x009d, TRY_LEAVE, TryCatch #2 {all -> 0x009d, blocks: (B:76:0x0062, B:80:0x0075, B:82:0x007e, B:84:0x0084, B:88:0x0097, B:75:0x005e), top: B:100:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0024 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v0, types: [kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v8, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x0093 -> B:76:0x0062). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object filterNotNullTo(ReceiveChannel receiveChannel, SendChannel sendChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$filterNotNullTo$3 channelsKt__DeprecatedKt$filterNotNullTo$3;
        Object coroutine_suspended;
        int i2;
        ChannelsKt__DeprecatedKt$filterNotNullTo$3 channelsKt__DeprecatedKt$filterNotNullTo$32;
        ChannelIterator channelIterator;
        SendChannel sendChannel2;
        ChannelIterator channelIterator2;
        SendChannel sendChannel3;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$filterNotNullTo$3) {
            channelsKt__DeprecatedKt$filterNotNullTo$3 = (ChannelsKt__DeprecatedKt$filterNotNullTo$3) continuation;
            int i3 = channelsKt__DeprecatedKt$filterNotNullTo$3.f11416e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$filterNotNullTo$3.f11416e = i3 - Integer.MIN_VALUE;
                Object obj = channelsKt__DeprecatedKt$filterNotNullTo$3.f11415d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$filterNotNullTo$3.f11416e;
                if (i2 == 0) {
                    try {
                        if (i2 == 1) {
                            ChannelIterator channelIterator3 = (ChannelIterator) channelsKt__DeprecatedKt$filterNotNullTo$3.f11414c;
                            ReceiveChannel receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.f11413b;
                            SendChannel sendChannel4 = (SendChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.f11412a;
                            ResultKt.throwOnFailure(obj);
                            channelsKt__DeprecatedKt$filterNotNullTo$32 = channelsKt__DeprecatedKt$filterNotNullTo$3;
                            channelIterator = channelIterator3;
                            receiveChannel = receiveChannel2;
                            sendChannel2 = sendChannel4;
                            Object obj2 = coroutine_suspended;
                            ChannelsKt__DeprecatedKt$filterNotNullTo$3 channelsKt__DeprecatedKt$filterNotNullTo$33 = channelsKt__DeprecatedKt$filterNotNullTo$32;
                            if (!((Boolean) obj).booleanValue()) {
                                Unit unit = Unit.INSTANCE;
                                ChannelsKt.cancelConsumed(receiveChannel, null);
                                return sendChannel2;
                            }
                            Object next = channelIterator.next();
                            if (next != null) {
                                channelsKt__DeprecatedKt$filterNotNullTo$33.f11412a = sendChannel2;
                                channelsKt__DeprecatedKt$filterNotNullTo$33.f11413b = receiveChannel;
                                channelsKt__DeprecatedKt$filterNotNullTo$33.f11414c = channelIterator;
                                channelsKt__DeprecatedKt$filterNotNullTo$33.f11416e = 2;
                                if (sendChannel2.send(next, channelsKt__DeprecatedKt$filterNotNullTo$33) == obj2) {
                                    return obj2;
                                }
                            }
                            channelIterator2 = channelIterator;
                            channelsKt__DeprecatedKt$filterNotNullTo$3 = channelsKt__DeprecatedKt$filterNotNullTo$33;
                            coroutine_suspended = obj2;
                            sendChannel3 = sendChannel2;
                        } else if (i2 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ChannelIterator channelIterator4 = (ChannelIterator) channelsKt__DeprecatedKt$filterNotNullTo$3.f11414c;
                            ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.f11413b;
                            SendChannel sendChannel5 = (SendChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.f11412a;
                            ResultKt.throwOnFailure(obj);
                            channelIterator2 = channelIterator4;
                            receiveChannel = receiveChannel3;
                            sendChannel3 = sendChannel5;
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            ChannelsKt.cancelConsumed(sendChannel, th);
                            throw th2;
                        }
                    }
                } else {
                    ResultKt.throwOnFailure(obj);
                    try {
                        channelIterator2 = receiveChannel.iterator();
                        sendChannel3 = sendChannel;
                    } catch (Throwable th3) {
                        sendChannel = receiveChannel;
                        th = th3;
                        throw th;
                    }
                }
                channelsKt__DeprecatedKt$filterNotNullTo$3.f11412a = sendChannel3;
                channelsKt__DeprecatedKt$filterNotNullTo$3.f11413b = receiveChannel;
                channelsKt__DeprecatedKt$filterNotNullTo$3.f11414c = channelIterator2;
                channelsKt__DeprecatedKt$filterNotNullTo$3.f11416e = 1;
                hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$3);
                if (hasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                channelsKt__DeprecatedKt$filterNotNullTo$32 = channelsKt__DeprecatedKt$filterNotNullTo$3;
                channelIterator = channelIterator2;
                obj = hasNext;
                sendChannel2 = sendChannel3;
                Object obj22 = coroutine_suspended;
                ChannelsKt__DeprecatedKt$filterNotNullTo$3 channelsKt__DeprecatedKt$filterNotNullTo$332 = channelsKt__DeprecatedKt$filterNotNullTo$32;
                if (!((Boolean) obj).booleanValue()) {
                }
            }
        }
        channelsKt__DeprecatedKt$filterNotNullTo$3 = new ChannelsKt__DeprecatedKt$filterNotNullTo$3(continuation);
        Object obj3 = channelsKt__DeprecatedKt$filterNotNullTo$3.f11415d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$filterNotNullTo$3.f11416e;
        if (i2 == 0) {
        }
        channelsKt__DeprecatedKt$filterNotNullTo$3.f11412a = sendChannel3;
        channelsKt__DeprecatedKt$filterNotNullTo$3.f11413b = receiveChannel;
        channelsKt__DeprecatedKt$filterNotNullTo$3.f11414c = channelIterator2;
        channelsKt__DeprecatedKt$filterNotNullTo$3.f11416e = 1;
        hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$3);
        if (hasNext != coroutine_suspended) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x005c A[Catch: all -> 0x0031, TRY_LEAVE, TryCatch #1 {all -> 0x0031, blocks: (B:54:0x002d, B:66:0x0054, B:68:0x005c, B:71:0x0064, B:72:0x006b), top: B:81:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0064 A[Catch: all -> 0x0031, TRY_ENTER, TryCatch #1 {all -> 0x0031, blocks: (B:54:0x002d, B:66:0x0054, B:68:0x005c, B:71:0x0064, B:72:0x006b), top: B:81:0x002d }] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object first(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$first$1 channelsKt__DeprecatedKt$first$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        if (continuation instanceof ChannelsKt__DeprecatedKt$first$1) {
            channelsKt__DeprecatedKt$first$1 = (ChannelsKt__DeprecatedKt$first$1) continuation;
            int i3 = channelsKt__DeprecatedKt$first$1.f11420d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$first$1.f11420d = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$first$1.f11419c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$first$1.f11420d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$first$1.f11417a = receiveChannel;
                        channelsKt__DeprecatedKt$first$1.f11418b = it;
                        channelsKt__DeprecatedKt$first$1.f11420d = 1;
                        Object hasNext = it.hasNext(channelsKt__DeprecatedKt$first$1);
                        if (hasNext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext;
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$first$1.f11418b;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$first$1.f11417a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                Object next = channelIterator.next();
                ChannelsKt.cancelConsumed(receiveChannel2, null);
                return next;
            }
        }
        channelsKt__DeprecatedKt$first$1 = new ChannelsKt__DeprecatedKt$first$1(continuation);
        obj = channelsKt__DeprecatedKt$first$1.f11419c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$first$1.f11420d;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0060 A[Catch: all -> 0x0031, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0031, blocks: (B:55:0x002d, B:66:0x0053, B:71:0x0060), top: B:80:0x002d }] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object firstOrNull(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$firstOrNull$1 channelsKt__DeprecatedKt$firstOrNull$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        if (continuation instanceof ChannelsKt__DeprecatedKt$firstOrNull$1) {
            channelsKt__DeprecatedKt$firstOrNull$1 = (ChannelsKt__DeprecatedKt$firstOrNull$1) continuation;
            int i3 = channelsKt__DeprecatedKt$firstOrNull$1.f11424d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$firstOrNull$1.f11424d = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$firstOrNull$1.f11423c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$firstOrNull$1.f11424d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$firstOrNull$1.f11421a = receiveChannel;
                        channelsKt__DeprecatedKt$firstOrNull$1.f11422b = it;
                        channelsKt__DeprecatedKt$firstOrNull$1.f11424d = 1;
                        Object hasNext = it.hasNext(channelsKt__DeprecatedKt$firstOrNull$1);
                        if (hasNext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext;
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$firstOrNull$1.f11422b;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$firstOrNull$1.f11421a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return null;
                }
                Object next = channelIterator.next();
                ChannelsKt.cancelConsumed(receiveChannel2, null);
                return next;
            }
        }
        channelsKt__DeprecatedKt$firstOrNull$1 = new ChannelsKt__DeprecatedKt$firstOrNull$1(continuation);
        obj = channelsKt__DeprecatedKt$firstOrNull$1.f11423c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$firstOrNull$1.f11424d;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel flatMap(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$flatMap$1(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        ReceiveChannel flatMap;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        flatMap = flatMap(receiveChannel, coroutineContext, function2);
        return flatMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0070 A[Catch: all -> 0x0037, TryCatch #1 {all -> 0x0037, blocks: (B:59:0x0033, B:72:0x0067, B:74:0x0070, B:76:0x007a, B:79:0x0084, B:68:0x0053, B:80:0x008b), top: B:91:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x008b A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #1 {all -> 0x0037, blocks: (B:59:0x0033, B:72:0x0067, B:74:0x0070, B:76:0x007a, B:79:0x0084, B:68:0x0053, B:80:0x008b), top: B:91:0x0033 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0064 -> B:72:0x0067). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object indexOf(ReceiveChannel receiveChannel, Object obj, Continuation continuation) {
        ChannelsKt__DeprecatedKt$indexOf$1 channelsKt__DeprecatedKt$indexOf$1;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Ref.IntRef intRef;
        Object obj2;
        Object hasNext;
        try {
            if (continuation instanceof ChannelsKt__DeprecatedKt$indexOf$1) {
                channelsKt__DeprecatedKt$indexOf$1 = (ChannelsKt__DeprecatedKt$indexOf$1) continuation;
                int i3 = channelsKt__DeprecatedKt$indexOf$1.f11434f;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    channelsKt__DeprecatedKt$indexOf$1.f11434f = i3 - Integer.MIN_VALUE;
                    Object obj3 = channelsKt__DeprecatedKt$indexOf$1.f11433e;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = channelsKt__DeprecatedKt$indexOf$1.f11434f;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj3);
                        Ref.IntRef intRef2 = new Ref.IntRef();
                        try {
                            receiveChannel2 = receiveChannel;
                            it = receiveChannel.iterator();
                            intRef = intRef2;
                            obj2 = obj;
                            channelsKt__DeprecatedKt$indexOf$1.f11429a = obj2;
                            channelsKt__DeprecatedKt$indexOf$1.f11430b = intRef;
                            channelsKt__DeprecatedKt$indexOf$1.f11431c = receiveChannel2;
                            channelsKt__DeprecatedKt$indexOf$1.f11432d = it;
                            channelsKt__DeprecatedKt$indexOf$1.f11434f = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$indexOf$1);
                            if (hasNext != coroutine_suspended) {
                            }
                        } catch (Throwable th2) {
                            receiveChannel2 = receiveChannel;
                            th = th2;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        it = (ChannelIterator) channelsKt__DeprecatedKt$indexOf$1.f11432d;
                        receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$indexOf$1.f11431c;
                        intRef = (Ref.IntRef) channelsKt__DeprecatedKt$indexOf$1.f11430b;
                        Object obj4 = channelsKt__DeprecatedKt$indexOf$1.f11429a;
                        try {
                            ResultKt.throwOnFailure(obj3);
                            if (!((Boolean) obj3).booleanValue()) {
                                Unit unit = Unit.INSTANCE;
                                ChannelsKt.cancelConsumed(receiveChannel2, null);
                                return Boxing.boxInt(-1);
                            } else if (Intrinsics.areEqual(obj4, it.next())) {
                                Integer boxInt = Boxing.boxInt(intRef.element);
                                ChannelsKt.cancelConsumed(receiveChannel2, null);
                                return boxInt;
                            } else {
                                intRef.element++;
                                obj2 = obj4;
                                channelsKt__DeprecatedKt$indexOf$1.f11429a = obj2;
                                channelsKt__DeprecatedKt$indexOf$1.f11430b = intRef;
                                channelsKt__DeprecatedKt$indexOf$1.f11431c = receiveChannel2;
                                channelsKt__DeprecatedKt$indexOf$1.f11432d = it;
                                channelsKt__DeprecatedKt$indexOf$1.f11434f = 1;
                                hasNext = it.hasNext(channelsKt__DeprecatedKt$indexOf$1);
                                if (hasNext != coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                obj4 = obj2;
                                obj3 = hasNext;
                                if (!((Boolean) obj3).booleanValue()) {
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    throw th;
                }
            }
            throw th;
        } catch (Throwable th4) {
            ChannelsKt.cancelConsumed(receiveChannel2, th);
            throw th4;
        }
        channelsKt__DeprecatedKt$indexOf$1 = new ChannelsKt__DeprecatedKt$indexOf$1(continuation);
        Object obj32 = channelsKt__DeprecatedKt$indexOf$1.f11433e;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$indexOf$1.f11434f;
        if (i2 != 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x009d A[Catch: all -> 0x004e, TRY_ENTER, TryCatch #3 {all -> 0x004e, blocks: (B:79:0x004a, B:88:0x0068, B:90:0x0070, B:103:0x009d, B:104:0x00a4), top: B:117:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0070 A[Catch: all -> 0x004e, TRY_LEAVE, TryCatch #3 {all -> 0x004e, blocks: (B:79:0x004a, B:88:0x0068, B:90:0x0070, B:103:0x009d, B:104:0x00a4), top: B:117:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0085 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0093 A[Catch: all -> 0x0036, TRY_LEAVE, TryCatch #1 {all -> 0x0036, blocks: (B:72:0x0032, B:97:0x008b, B:99:0x0093), top: B:113:0x0032 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:95:0x0086 -> B:96:0x008a). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object last(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$last$1 channelsKt__DeprecatedKt$last$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Object next;
        ChannelIterator channelIterator2;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$last$1) {
            channelsKt__DeprecatedKt$last$1 = (ChannelsKt__DeprecatedKt$last$1) continuation;
            int i3 = channelsKt__DeprecatedKt$last$1.f11439e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$last$1.f11439e = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$last$1.f11438d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$last$1.f11439e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$last$1.f11435a = receiveChannel;
                        channelsKt__DeprecatedKt$last$1.f11436b = it;
                        channelsKt__DeprecatedKt$last$1.f11439e = 1;
                        Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$last$1);
                        if (hasNext2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext2;
                    } catch (Throwable th) {
                        receiveChannel2 = receiveChannel;
                        th = th;
                        throw th;
                    }
                } else if (i2 == 1) {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$last$1.f11436b;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$last$1.f11435a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Object obj2 = channelsKt__DeprecatedKt$last$1.f11437c;
                    channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$last$1.f11436b;
                    ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$last$1.f11435a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        if (!((Boolean) obj).booleanValue()) {
                            next = channelIterator2.next();
                            receiveChannel = receiveChannel3;
                            channelsKt__DeprecatedKt$last$1.f11435a = receiveChannel;
                            channelsKt__DeprecatedKt$last$1.f11436b = channelIterator2;
                            channelsKt__DeprecatedKt$last$1.f11437c = next;
                            channelsKt__DeprecatedKt$last$1.f11439e = 2;
                            hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$last$1);
                            if (hasNext != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            receiveChannel3 = receiveChannel;
                            obj2 = next;
                            obj = hasNext;
                            if (!((Boolean) obj).booleanValue()) {
                                ChannelsKt.cancelConsumed(receiveChannel3, null);
                                return obj2;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        receiveChannel2 = receiveChannel3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                next = channelIterator.next();
                ReceiveChannel receiveChannel4 = receiveChannel2;
                channelIterator2 = channelIterator;
                receiveChannel = receiveChannel4;
                channelsKt__DeprecatedKt$last$1.f11435a = receiveChannel;
                channelsKt__DeprecatedKt$last$1.f11436b = channelIterator2;
                channelsKt__DeprecatedKt$last$1.f11437c = next;
                channelsKt__DeprecatedKt$last$1.f11439e = 2;
                hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$last$1);
                if (hasNext != coroutine_suspended) {
                }
            }
        }
        channelsKt__DeprecatedKt$last$1 = new ChannelsKt__DeprecatedKt$last$1(continuation);
        obj = channelsKt__DeprecatedKt$last$1.f11438d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$last$1.f11439e;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0071 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x007e A[Catch: all -> 0x003b, TryCatch #1 {all -> 0x003b, blocks: (B:58:0x0037, B:71:0x0075, B:73:0x007e, B:75:0x0088, B:76:0x008c, B:67:0x005f, B:77:0x0093), top: B:88:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0093 A[Catch: all -> 0x003b, TRY_LEAVE, TryCatch #1 {all -> 0x003b, blocks: (B:58:0x0037, B:71:0x0075, B:73:0x007e, B:75:0x0088, B:76:0x008c, B:67:0x005f, B:77:0x0093), top: B:88:0x0037 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0072 -> B:71:0x0075). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object lastIndexOf(ReceiveChannel receiveChannel, Object obj, Continuation continuation) {
        ChannelsKt__DeprecatedKt$lastIndexOf$1 channelsKt__DeprecatedKt$lastIndexOf$1;
        Object coroutine_suspended;
        int i2;
        Ref.IntRef intRef;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Ref.IntRef intRef2;
        Object obj2;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$lastIndexOf$1) {
            channelsKt__DeprecatedKt$lastIndexOf$1 = (ChannelsKt__DeprecatedKt$lastIndexOf$1) continuation;
            int i3 = channelsKt__DeprecatedKt$lastIndexOf$1.f11446g;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$lastIndexOf$1.f11446g = i3 - Integer.MIN_VALUE;
                Object obj3 = channelsKt__DeprecatedKt$lastIndexOf$1.f11445f;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$lastIndexOf$1.f11446g;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj3);
                    Ref.IntRef intRef3 = new Ref.IntRef();
                    intRef3.element = -1;
                    intRef = new Ref.IntRef();
                    try {
                        receiveChannel2 = receiveChannel;
                        it = receiveChannel.iterator();
                        intRef2 = intRef3;
                        obj2 = obj;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11440a = obj2;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11441b = intRef2;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11442c = intRef;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11443d = receiveChannel2;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11444e = it;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11446g = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$lastIndexOf$1);
                        if (hasNext != coroutine_suspended) {
                        }
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    it = (ChannelIterator) channelsKt__DeprecatedKt$lastIndexOf$1.f11444e;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$lastIndexOf$1.f11443d;
                    intRef = (Ref.IntRef) channelsKt__DeprecatedKt$lastIndexOf$1.f11442c;
                    intRef2 = (Ref.IntRef) channelsKt__DeprecatedKt$lastIndexOf$1.f11441b;
                    Object obj4 = channelsKt__DeprecatedKt$lastIndexOf$1.f11440a;
                    try {
                        ResultKt.throwOnFailure(obj3);
                        if (!((Boolean) obj3).booleanValue()) {
                            Unit unit = Unit.INSTANCE;
                            ChannelsKt.cancelConsumed(receiveChannel2, null);
                            return Boxing.boxInt(intRef2.element);
                        }
                        if (Intrinsics.areEqual(obj4, it.next())) {
                            intRef2.element = intRef.element;
                        }
                        intRef.element++;
                        obj2 = obj4;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11440a = obj2;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11441b = intRef2;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11442c = intRef;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11443d = receiveChannel2;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11444e = it;
                        channelsKt__DeprecatedKt$lastIndexOf$1.f11446g = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$lastIndexOf$1);
                        if (hasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj4 = obj2;
                        obj3 = hasNext;
                        if (!((Boolean) obj3).booleanValue()) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
            }
        }
        channelsKt__DeprecatedKt$lastIndexOf$1 = new ChannelsKt__DeprecatedKt$lastIndexOf$1(continuation);
        Object obj32 = channelsKt__DeprecatedKt$lastIndexOf$1.f11445f;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$lastIndexOf$1.f11446g;
        if (i2 != 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0097 A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #2 {all -> 0x0037, blocks: (B:72:0x0033, B:98:0x008f, B:100:0x0097), top: B:114:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0075 A[Catch: all -> 0x004f, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x004f, blocks: (B:79:0x004b, B:88:0x0069, B:92:0x0075), top: B:116:0x004b }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x008b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:97:0x008b -> B:98:0x008f). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object lastOrNull(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$lastOrNull$1 channelsKt__DeprecatedKt$lastOrNull$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Object next;
        ChannelIterator channelIterator2;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$lastOrNull$1) {
            channelsKt__DeprecatedKt$lastOrNull$1 = (ChannelsKt__DeprecatedKt$lastOrNull$1) continuation;
            int i3 = channelsKt__DeprecatedKt$lastOrNull$1.f11451e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$lastOrNull$1.f11451e = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$lastOrNull$1.f11450d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$lastOrNull$1.f11451e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$lastOrNull$1.f11447a = receiveChannel;
                        channelsKt__DeprecatedKt$lastOrNull$1.f11448b = it;
                        channelsKt__DeprecatedKt$lastOrNull$1.f11451e = 1;
                        Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$lastOrNull$1);
                        if (hasNext2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext2;
                    } catch (Throwable th) {
                        receiveChannel2 = receiveChannel;
                        th = th;
                        throw th;
                    }
                } else if (i2 == 1) {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$lastOrNull$1.f11448b;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$lastOrNull$1.f11447a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Object obj2 = channelsKt__DeprecatedKt$lastOrNull$1.f11449c;
                    channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$lastOrNull$1.f11448b;
                    ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$lastOrNull$1.f11447a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        if (!((Boolean) obj).booleanValue()) {
                            next = channelIterator2.next();
                            receiveChannel = receiveChannel3;
                            channelsKt__DeprecatedKt$lastOrNull$1.f11447a = receiveChannel;
                            channelsKt__DeprecatedKt$lastOrNull$1.f11448b = channelIterator2;
                            channelsKt__DeprecatedKt$lastOrNull$1.f11449c = next;
                            channelsKt__DeprecatedKt$lastOrNull$1.f11451e = 2;
                            hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$lastOrNull$1);
                            if (hasNext != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            receiveChannel3 = receiveChannel;
                            obj2 = next;
                            obj = hasNext;
                            if (!((Boolean) obj).booleanValue()) {
                                ChannelsKt.cancelConsumed(receiveChannel3, null);
                                return obj2;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        receiveChannel2 = receiveChannel3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return null;
                }
                next = channelIterator.next();
                ReceiveChannel receiveChannel4 = receiveChannel2;
                channelIterator2 = channelIterator;
                receiveChannel = receiveChannel4;
                channelsKt__DeprecatedKt$lastOrNull$1.f11447a = receiveChannel;
                channelsKt__DeprecatedKt$lastOrNull$1.f11448b = channelIterator2;
                channelsKt__DeprecatedKt$lastOrNull$1.f11449c = next;
                channelsKt__DeprecatedKt$lastOrNull$1.f11451e = 2;
                hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$lastOrNull$1);
                if (hasNext != coroutine_suspended) {
                }
            }
        }
        channelsKt__DeprecatedKt$lastOrNull$1 = new ChannelsKt__DeprecatedKt$lastOrNull$1(continuation);
        obj = channelsKt__DeprecatedKt$lastOrNull$1.f11450d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$lastOrNull$1.f11451e;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    @PublishedApi
    @NotNull
    public static final <E, R> ReceiveChannel<R> map(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$map$1(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    @PublishedApi
    @NotNull
    public static final <E, R> ReceiveChannel<R> mapIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$mapIndexed$1(receiveChannel, function3, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapIndexedNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i2, Object obj) {
        ReceiveChannel mapIndexedNotNull;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        mapIndexedNotNull = mapIndexedNotNull(receiveChannel, coroutineContext, function3);
        return mapIndexedNotNull;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        ReceiveChannel mapNotNull;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        mapNotNull = mapNotNull(receiveChannel, coroutineContext, function2);
        return mapNotNull;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00ac A[Catch: all -> 0x00bf, TRY_LEAVE, TryCatch #3 {all -> 0x00bf, blocks: (B:103:0x00a4, B:105:0x00ac, B:99:0x008f, B:89:0x0063), top: B:124:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0087 A[Catch: all -> 0x005d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x005d, blocks: (B:84:0x0059, B:93:0x007b, B:97:0x0087), top: B:122:0x0059 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:102:0x00a0 -> B:78:0x003d). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object maxWith(ReceiveChannel receiveChannel, Comparator comparator, Continuation continuation) {
        ChannelsKt__DeprecatedKt$maxWith$1 channelsKt__DeprecatedKt$maxWith$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Comparator comparator2;
        Object next;
        Comparator comparator3;
        ChannelIterator channelIterator2;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$maxWith$1) {
            channelsKt__DeprecatedKt$maxWith$1 = (ChannelsKt__DeprecatedKt$maxWith$1) continuation;
            int i3 = channelsKt__DeprecatedKt$maxWith$1.f11470f;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$maxWith$1.f11470f = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$maxWith$1.f11469e;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$maxWith$1.f11470f;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$maxWith$1.f11465a = comparator;
                        channelsKt__DeprecatedKt$maxWith$1.f11466b = receiveChannel;
                        channelsKt__DeprecatedKt$maxWith$1.f11467c = it;
                        channelsKt__DeprecatedKt$maxWith$1.f11470f = 1;
                        Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$maxWith$1);
                        if (hasNext2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext2;
                        comparator2 = comparator;
                    } catch (Throwable th) {
                        receiveChannel2 = receiveChannel;
                        th = th;
                        throw th;
                    }
                } else if (i2 == 1) {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$maxWith$1.f11467c;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$maxWith$1.f11466b;
                    comparator2 = (Comparator) channelsKt__DeprecatedKt$maxWith$1.f11465a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Object obj2 = channelsKt__DeprecatedKt$maxWith$1.f11468d;
                    channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$maxWith$1.f11467c;
                    ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$maxWith$1.f11466b;
                    comparator3 = (Comparator) channelsKt__DeprecatedKt$maxWith$1.f11465a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        ChannelsKt__DeprecatedKt$maxWith$1 channelsKt__DeprecatedKt$maxWith$12 = channelsKt__DeprecatedKt$maxWith$1;
                        Object obj3 = obj2;
                        receiveChannel = receiveChannel3;
                        Object obj4 = coroutine_suspended;
                        ChannelsKt__DeprecatedKt$maxWith$1 channelsKt__DeprecatedKt$maxWith$13 = channelsKt__DeprecatedKt$maxWith$12;
                        if (!((Boolean) obj).booleanValue()) {
                            next = channelIterator2.next();
                            if (comparator3.compare(obj3, next) >= 0) {
                                next = obj3;
                            }
                            channelsKt__DeprecatedKt$maxWith$1 = channelsKt__DeprecatedKt$maxWith$13;
                            coroutine_suspended = obj4;
                            channelsKt__DeprecatedKt$maxWith$1.f11465a = comparator3;
                            channelsKt__DeprecatedKt$maxWith$1.f11466b = receiveChannel;
                            channelsKt__DeprecatedKt$maxWith$1.f11467c = channelIterator2;
                            channelsKt__DeprecatedKt$maxWith$1.f11468d = next;
                            channelsKt__DeprecatedKt$maxWith$1.f11470f = 2;
                            hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$maxWith$1);
                            if (hasNext != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            channelsKt__DeprecatedKt$maxWith$12 = channelsKt__DeprecatedKt$maxWith$1;
                            obj3 = next;
                            obj = hasNext;
                            Object obj42 = coroutine_suspended;
                            ChannelsKt__DeprecatedKt$maxWith$1 channelsKt__DeprecatedKt$maxWith$132 = channelsKt__DeprecatedKt$maxWith$12;
                            if (!((Boolean) obj).booleanValue()) {
                                ChannelsKt.cancelConsumed(receiveChannel, null);
                                return obj3;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        receiveChannel2 = receiveChannel3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return null;
                }
                next = channelIterator.next();
                comparator3 = comparator2;
                ReceiveChannel receiveChannel4 = receiveChannel2;
                channelIterator2 = channelIterator;
                receiveChannel = receiveChannel4;
                channelsKt__DeprecatedKt$maxWith$1.f11465a = comparator3;
                channelsKt__DeprecatedKt$maxWith$1.f11466b = receiveChannel;
                channelsKt__DeprecatedKt$maxWith$1.f11467c = channelIterator2;
                channelsKt__DeprecatedKt$maxWith$1.f11468d = next;
                channelsKt__DeprecatedKt$maxWith$1.f11470f = 2;
                hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$maxWith$1);
                if (hasNext != coroutine_suspended) {
                }
            }
        }
        channelsKt__DeprecatedKt$maxWith$1 = new ChannelsKt__DeprecatedKt$maxWith$1(continuation);
        obj = channelsKt__DeprecatedKt$maxWith$1.f11469e;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$maxWith$1.f11470f;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00ac A[Catch: all -> 0x00bf, TRY_LEAVE, TryCatch #3 {all -> 0x00bf, blocks: (B:103:0x00a4, B:105:0x00ac, B:99:0x008f, B:89:0x0063), top: B:124:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0087 A[Catch: all -> 0x005d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x005d, blocks: (B:84:0x0059, B:93:0x007b, B:97:0x0087), top: B:122:0x0059 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:102:0x00a0 -> B:78:0x003d). Please submit an issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object minWith(ReceiveChannel receiveChannel, Comparator comparator, Continuation continuation) {
        ChannelsKt__DeprecatedKt$minWith$1 channelsKt__DeprecatedKt$minWith$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Comparator comparator2;
        Object next;
        Comparator comparator3;
        ChannelIterator channelIterator2;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$minWith$1) {
            channelsKt__DeprecatedKt$minWith$1 = (ChannelsKt__DeprecatedKt$minWith$1) continuation;
            int i3 = channelsKt__DeprecatedKt$minWith$1.f11476f;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$minWith$1.f11476f = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$minWith$1.f11475e;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$minWith$1.f11476f;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$minWith$1.f11471a = comparator;
                        channelsKt__DeprecatedKt$minWith$1.f11472b = receiveChannel;
                        channelsKt__DeprecatedKt$minWith$1.f11473c = it;
                        channelsKt__DeprecatedKt$minWith$1.f11476f = 1;
                        Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$minWith$1);
                        if (hasNext2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext2;
                        comparator2 = comparator;
                    } catch (Throwable th) {
                        receiveChannel2 = receiveChannel;
                        th = th;
                        throw th;
                    }
                } else if (i2 == 1) {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$minWith$1.f11473c;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$minWith$1.f11472b;
                    comparator2 = (Comparator) channelsKt__DeprecatedKt$minWith$1.f11471a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Object obj2 = channelsKt__DeprecatedKt$minWith$1.f11474d;
                    channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$minWith$1.f11473c;
                    ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$minWith$1.f11472b;
                    comparator3 = (Comparator) channelsKt__DeprecatedKt$minWith$1.f11471a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        ChannelsKt__DeprecatedKt$minWith$1 channelsKt__DeprecatedKt$minWith$12 = channelsKt__DeprecatedKt$minWith$1;
                        Object obj3 = obj2;
                        receiveChannel = receiveChannel3;
                        Object obj4 = coroutine_suspended;
                        ChannelsKt__DeprecatedKt$minWith$1 channelsKt__DeprecatedKt$minWith$13 = channelsKt__DeprecatedKt$minWith$12;
                        if (!((Boolean) obj).booleanValue()) {
                            next = channelIterator2.next();
                            if (comparator3.compare(obj3, next) <= 0) {
                                next = obj3;
                            }
                            channelsKt__DeprecatedKt$minWith$1 = channelsKt__DeprecatedKt$minWith$13;
                            coroutine_suspended = obj4;
                            channelsKt__DeprecatedKt$minWith$1.f11471a = comparator3;
                            channelsKt__DeprecatedKt$minWith$1.f11472b = receiveChannel;
                            channelsKt__DeprecatedKt$minWith$1.f11473c = channelIterator2;
                            channelsKt__DeprecatedKt$minWith$1.f11474d = next;
                            channelsKt__DeprecatedKt$minWith$1.f11476f = 2;
                            hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$minWith$1);
                            if (hasNext != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            channelsKt__DeprecatedKt$minWith$12 = channelsKt__DeprecatedKt$minWith$1;
                            obj3 = next;
                            obj = hasNext;
                            Object obj42 = coroutine_suspended;
                            ChannelsKt__DeprecatedKt$minWith$1 channelsKt__DeprecatedKt$minWith$132 = channelsKt__DeprecatedKt$minWith$12;
                            if (!((Boolean) obj).booleanValue()) {
                                ChannelsKt.cancelConsumed(receiveChannel, null);
                                return obj3;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        receiveChannel2 = receiveChannel3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return null;
                }
                next = channelIterator.next();
                comparator3 = comparator2;
                ReceiveChannel receiveChannel4 = receiveChannel2;
                channelIterator2 = channelIterator;
                receiveChannel = receiveChannel4;
                channelsKt__DeprecatedKt$minWith$1.f11471a = comparator3;
                channelsKt__DeprecatedKt$minWith$1.f11472b = receiveChannel;
                channelsKt__DeprecatedKt$minWith$1.f11473c = channelIterator2;
                channelsKt__DeprecatedKt$minWith$1.f11474d = next;
                channelsKt__DeprecatedKt$minWith$1.f11476f = 2;
                hasNext = channelIterator2.hasNext(channelsKt__DeprecatedKt$minWith$1);
                if (hasNext != coroutine_suspended) {
                }
            }
        }
        channelsKt__DeprecatedKt$minWith$1 = new ChannelsKt__DeprecatedKt$minWith$1(continuation);
        obj = channelsKt__DeprecatedKt$minWith$1.f11475e;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$minWith$1.f11476f;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0051  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object none(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$none$1 channelsKt__DeprecatedKt$none$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        try {
            if (continuation instanceof ChannelsKt__DeprecatedKt$none$1) {
                channelsKt__DeprecatedKt$none$1 = (ChannelsKt__DeprecatedKt$none$1) continuation;
                int i3 = channelsKt__DeprecatedKt$none$1.f11479c;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    channelsKt__DeprecatedKt$none$1.f11479c = i3 - Integer.MIN_VALUE;
                    obj = channelsKt__DeprecatedKt$none$1.f11478b;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = channelsKt__DeprecatedKt$none$1.f11479c;
                    boolean z = true;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$none$1.f11477a = receiveChannel;
                        channelsKt__DeprecatedKt$none$1.f11479c = 1;
                        obj = it.hasNext(channelsKt__DeprecatedKt$none$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        receiveChannel = (ReceiveChannel) channelsKt__DeprecatedKt$none$1.f11477a;
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        z = false;
                    }
                    Boolean boxBoolean = Boxing.boxBoolean(z);
                    ChannelsKt.cancelConsumed(receiveChannel, null);
                    return boxBoolean;
                }
            }
            if (i2 != 0) {
            }
            if (!((Boolean) obj).booleanValue()) {
            }
            Boolean boxBoolean2 = Boxing.boxBoolean(z);
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return boxBoolean2;
        } finally {
        }
        channelsKt__DeprecatedKt$none$1 = new ChannelsKt__DeprecatedKt$none$1(continuation);
        obj = channelsKt__DeprecatedKt$none$1.f11478b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$none$1.f11479c;
        boolean z2 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x006c A[Catch: all -> 0x004a, TRY_LEAVE, TryCatch #2 {all -> 0x004a, blocks: (B:77:0x0046, B:86:0x0064, B:88:0x006c, B:99:0x0096, B:100:0x009d), top: B:111:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x008e A[Catch: all -> 0x0032, TRY_ENTER, TryCatch #1 {all -> 0x0032, blocks: (B:70:0x002e, B:93:0x0082, B:97:0x008e, B:98:0x0095), top: B:109:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0096 A[Catch: all -> 0x004a, TRY_ENTER, TryCatch #2 {all -> 0x004a, blocks: (B:77:0x0046, B:86:0x0064, B:88:0x006c, B:99:0x0096, B:100:0x009d), top: B:111:0x0046 }] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object single(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$single$1 channelsKt__DeprecatedKt$single$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel3;
        Object obj2;
        if (continuation instanceof ChannelsKt__DeprecatedKt$single$1) {
            channelsKt__DeprecatedKt$single$1 = (ChannelsKt__DeprecatedKt$single$1) continuation;
            int i3 = channelsKt__DeprecatedKt$single$1.f11486d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$single$1.f11486d = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$single$1.f11485c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$single$1.f11486d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$single$1.f11483a = receiveChannel;
                        channelsKt__DeprecatedKt$single$1.f11484b = it;
                        channelsKt__DeprecatedKt$single$1.f11486d = 1;
                        Object hasNext = it.hasNext(channelsKt__DeprecatedKt$single$1);
                        if (hasNext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext;
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        obj2 = channelsKt__DeprecatedKt$single$1.f11484b;
                        receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$single$1.f11483a;
                        try {
                            ResultKt.throwOnFailure(obj);
                            if (!((Boolean) obj).booleanValue()) {
                                throw new IllegalArgumentException("ReceiveChannel has more than one element.");
                            }
                            ChannelsKt.cancelConsumed(receiveChannel3, null);
                            return obj2;
                        } catch (Throwable th3) {
                            th = th3;
                            receiveChannel2 = receiveChannel3;
                            try {
                                throw th;
                            } catch (Throwable th4) {
                                ChannelsKt.cancelConsumed(receiveChannel2, th);
                                throw th4;
                            }
                        }
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$single$1.f11484b;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$single$1.f11483a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th5) {
                        th = th5;
                        throw th;
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                Object next = channelIterator.next();
                channelsKt__DeprecatedKt$single$1.f11483a = receiveChannel2;
                channelsKt__DeprecatedKt$single$1.f11484b = next;
                channelsKt__DeprecatedKt$single$1.f11486d = 2;
                Object hasNext2 = channelIterator.hasNext(channelsKt__DeprecatedKt$single$1);
                if (hasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel3 = receiveChannel2;
                obj = hasNext2;
                obj2 = next;
                if (!((Boolean) obj).booleanValue()) {
                }
            }
        }
        channelsKt__DeprecatedKt$single$1 = new ChannelsKt__DeprecatedKt$single$1(continuation);
        obj = channelsKt__DeprecatedKt$single$1.f11485c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$single$1.f11486d;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0070 A[Catch: all -> 0x004a, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x004a, blocks: (B:76:0x0046, B:85:0x0064, B:89:0x0070), top: B:110:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0090 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0091 A[RETURN] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object singleOrNull(ReceiveChannel receiveChannel, Continuation continuation) {
        ChannelsKt__DeprecatedKt$singleOrNull$1 channelsKt__DeprecatedKt$singleOrNull$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel3;
        Object obj2;
        boolean booleanValue;
        if (continuation instanceof ChannelsKt__DeprecatedKt$singleOrNull$1) {
            channelsKt__DeprecatedKt$singleOrNull$1 = (ChannelsKt__DeprecatedKt$singleOrNull$1) continuation;
            int i3 = channelsKt__DeprecatedKt$singleOrNull$1.f11490d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$singleOrNull$1.f11490d = i3 - Integer.MIN_VALUE;
                obj = channelsKt__DeprecatedKt$singleOrNull$1.f11489c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$singleOrNull$1.f11490d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        ChannelIterator it = receiveChannel.iterator();
                        channelsKt__DeprecatedKt$singleOrNull$1.f11487a = receiveChannel;
                        channelsKt__DeprecatedKt$singleOrNull$1.f11488b = it;
                        channelsKt__DeprecatedKt$singleOrNull$1.f11490d = 1;
                        Object hasNext = it.hasNext(channelsKt__DeprecatedKt$singleOrNull$1);
                        if (hasNext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        obj = hasNext;
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        obj2 = channelsKt__DeprecatedKt$singleOrNull$1.f11488b;
                        receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$singleOrNull$1.f11487a;
                        try {
                            ResultKt.throwOnFailure(obj);
                            booleanValue = ((Boolean) obj).booleanValue();
                            ChannelsKt.cancelConsumed(receiveChannel3, null);
                            if (booleanValue) {
                                return obj2;
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                            receiveChannel2 = receiveChannel3;
                            try {
                                throw th;
                            } catch (Throwable th4) {
                                ChannelsKt.cancelConsumed(receiveChannel2, th);
                                throw th4;
                            }
                        }
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$singleOrNull$1.f11488b;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$singleOrNull$1.f11487a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th5) {
                        th = th5;
                        throw th;
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return null;
                }
                Object next = channelIterator.next();
                channelsKt__DeprecatedKt$singleOrNull$1.f11487a = receiveChannel2;
                channelsKt__DeprecatedKt$singleOrNull$1.f11488b = next;
                channelsKt__DeprecatedKt$singleOrNull$1.f11490d = 2;
                Object hasNext2 = channelIterator.hasNext(channelsKt__DeprecatedKt$singleOrNull$1);
                if (hasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel3 = receiveChannel2;
                obj = hasNext2;
                obj2 = next;
                booleanValue = ((Boolean) obj).booleanValue();
                ChannelsKt.cancelConsumed(receiveChannel3, null);
                if (booleanValue) {
                }
            }
        }
        channelsKt__DeprecatedKt$singleOrNull$1 = new ChannelsKt__DeprecatedKt$singleOrNull$1(continuation);
        obj = channelsKt__DeprecatedKt$singleOrNull$1.f11489c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$singleOrNull$1.f11490d;
        if (i2 != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel take(ReceiveChannel receiveChannel, int i2, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$take$1(i2, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i2, CoroutineContext coroutineContext, int i3, Object obj) {
        ReceiveChannel take;
        if ((i3 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        take = take(receiveChannel, i2, coroutineContext);
        return take;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel takeWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$takeWhile$1(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        ReceiveChannel takeWhile;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        takeWhile = takeWhile(receiveChannel, coroutineContext, function2);
        return takeWhile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0079 A[Catch: all -> 0x0053, TryCatch #1 {all -> 0x0053, blocks: (B:62:0x0034, B:78:0x0071, B:80:0x0079, B:83:0x008c, B:67:0x004f), top: B:94:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x008c A[Catch: all -> 0x0053, TRY_LEAVE, TryCatch #1 {all -> 0x0053, blocks: (B:62:0x0034, B:78:0x0071, B:80:0x0079, B:83:0x008c, B:67:0x004f), top: B:94:0x0022 }] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v14, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r8v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v5, types: [kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0089 -> B:63:0x0037). Please submit an issue!!! */
    @PublishedApi
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E, C extends SendChannel<? super E>> Object toChannel(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Continuation<? super C> continuation) {
        ChannelsKt__DeprecatedKt$toChannel$1 channelsKt__DeprecatedKt$toChannel$1;
        Object coroutine_suspended;
        int i2;
        ChannelIterator<? extends E> it;
        ChannelIterator<? extends E> channelIterator;
        SendChannel sendChannel;
        C c3;
        Object hasNext;
        try {
            if (continuation instanceof ChannelsKt__DeprecatedKt$toChannel$1) {
                channelsKt__DeprecatedKt$toChannel$1 = (ChannelsKt__DeprecatedKt$toChannel$1) continuation;
                int i3 = channelsKt__DeprecatedKt$toChannel$1.f11505e;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    channelsKt__DeprecatedKt$toChannel$1.f11505e = i3 - Integer.MIN_VALUE;
                    Object obj = channelsKt__DeprecatedKt$toChannel$1.f11504d;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = channelsKt__DeprecatedKt$toChannel$1.f11505e;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        try {
                            it = receiveChannel.iterator();
                            c3 = c2;
                            channelsKt__DeprecatedKt$toChannel$1.f11501a = c3;
                            channelsKt__DeprecatedKt$toChannel$1.f11502b = receiveChannel;
                            channelsKt__DeprecatedKt$toChannel$1.f11503c = it;
                            channelsKt__DeprecatedKt$toChannel$1.f11505e = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$toChannel$1);
                            if (hasNext == coroutine_suspended) {
                            }
                        } catch (Throwable th) {
                            c2 = receiveChannel;
                            th = th;
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                ChannelsKt.cancelConsumed(c2, th);
                                throw th2;
                            }
                        }
                    } else if (i2 == 1) {
                        channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$toChannel$1.f11503c;
                        C c4 = (C) ((ReceiveChannel) channelsKt__DeprecatedKt$toChannel$1.f11502b);
                        sendChannel = (SendChannel) channelsKt__DeprecatedKt$toChannel$1.f11501a;
                        ResultKt.throwOnFailure(obj);
                        c2 = c4;
                        if (((Boolean) obj).booleanValue()) {
                        }
                    } else if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$toChannel$1.f11503c;
                        ?? r8 = (C) ((ReceiveChannel) channelsKt__DeprecatedKt$toChannel$1.f11502b);
                        sendChannel = (SendChannel) channelsKt__DeprecatedKt$toChannel$1.f11501a;
                        ResultKt.throwOnFailure(obj);
                        ReceiveChannel<? extends E> receiveChannel2 = r8;
                        it = channelIterator;
                        receiveChannel = receiveChannel2;
                        c3 = (C) sendChannel;
                        channelsKt__DeprecatedKt$toChannel$1.f11501a = c3;
                        channelsKt__DeprecatedKt$toChannel$1.f11502b = receiveChannel;
                        channelsKt__DeprecatedKt$toChannel$1.f11503c = it;
                        channelsKt__DeprecatedKt$toChannel$1.f11505e = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$toChannel$1);
                        if (hasNext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        channelIterator = it;
                        obj = hasNext;
                        sendChannel = c3;
                        c2 = (C) receiveChannel;
                        if (((Boolean) obj).booleanValue()) {
                            Unit unit = Unit.INSTANCE;
                            ChannelsKt.cancelConsumed((ReceiveChannel) c2, null);
                            return sendChannel;
                        }
                        E next = channelIterator.next();
                        channelsKt__DeprecatedKt$toChannel$1.f11501a = sendChannel;
                        channelsKt__DeprecatedKt$toChannel$1.f11502b = (Object) c2;
                        channelsKt__DeprecatedKt$toChannel$1.f11503c = channelIterator;
                        channelsKt__DeprecatedKt$toChannel$1.f11505e = 2;
                        receiveChannel2 = c2;
                        if (sendChannel.send(next, channelsKt__DeprecatedKt$toChannel$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        it = channelIterator;
                        receiveChannel = receiveChannel2;
                        c3 = (C) sendChannel;
                        channelsKt__DeprecatedKt$toChannel$1.f11501a = c3;
                        channelsKt__DeprecatedKt$toChannel$1.f11502b = receiveChannel;
                        channelsKt__DeprecatedKt$toChannel$1.f11503c = it;
                        channelsKt__DeprecatedKt$toChannel$1.f11505e = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$toChannel$1);
                        if (hasNext == coroutine_suspended) {
                        }
                    }
                }
            }
            if (i2 != 0) {
            }
        } catch (Throwable th3) {
            th = th3;
        }
        channelsKt__DeprecatedKt$toChannel$1 = new ChannelsKt__DeprecatedKt$toChannel$1(continuation);
        Object obj2 = channelsKt__DeprecatedKt$toChannel$1.f11504d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$toChannel$1.f11505e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0065 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:55:0x0031, B:68:0x005c, B:70:0x0065, B:64:0x004a, B:71:0x006e), top: B:82:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x006e A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:55:0x0031, B:68:0x005c, B:70:0x0065, B:64:0x004a, B:71:0x006e), top: B:82:0x0031 }] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.util.Collection] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0059 -> B:68:0x005c). Please submit an issue!!! */
    @PublishedApi
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E, C extends Collection<? super E>> Object toCollection(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Continuation<? super C> continuation) {
        ChannelsKt__DeprecatedKt$toCollection$1 channelsKt__DeprecatedKt$toCollection$1;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel<? extends E> receiveChannel2;
        Throwable th;
        ChannelIterator<? extends E> it;
        C c3;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$toCollection$1) {
            channelsKt__DeprecatedKt$toCollection$1 = (ChannelsKt__DeprecatedKt$toCollection$1) continuation;
            int i3 = channelsKt__DeprecatedKt$toCollection$1.f11510e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$toCollection$1.f11510e = i3 - Integer.MIN_VALUE;
                Object obj = channelsKt__DeprecatedKt$toCollection$1.f11509d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$toCollection$1.f11510e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        receiveChannel2 = receiveChannel;
                        it = receiveChannel.iterator();
                        c3 = c2;
                        channelsKt__DeprecatedKt$toCollection$1.f11506a = c3;
                        channelsKt__DeprecatedKt$toCollection$1.f11507b = receiveChannel2;
                        channelsKt__DeprecatedKt$toCollection$1.f11508c = it;
                        channelsKt__DeprecatedKt$toCollection$1.f11510e = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$toCollection$1);
                        if (hasNext != coroutine_suspended) {
                        }
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ChannelIterator<? extends E> channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$toCollection$1.f11508c;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$toCollection$1.f11507b;
                    ?? r2 = (Collection) channelsKt__DeprecatedKt$toCollection$1.f11506a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        C c4 = r2;
                        ChannelIterator<? extends E> channelIterator2 = channelIterator;
                        if (!((Boolean) obj).booleanValue()) {
                            c4.add(channelIterator2.next());
                            c3 = c4;
                            it = channelIterator2;
                            channelsKt__DeprecatedKt$toCollection$1.f11506a = c3;
                            channelsKt__DeprecatedKt$toCollection$1.f11507b = receiveChannel2;
                            channelsKt__DeprecatedKt$toCollection$1.f11508c = it;
                            channelsKt__DeprecatedKt$toCollection$1.f11510e = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$toCollection$1);
                            if (hasNext != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            c4 = c3;
                            obj = hasNext;
                            channelIterator2 = it;
                            if (!((Boolean) obj).booleanValue()) {
                                Unit unit = Unit.INSTANCE;
                                ChannelsKt.cancelConsumed(receiveChannel2, null);
                                return c4;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
            }
        }
        channelsKt__DeprecatedKt$toCollection$1 = new ChannelsKt__DeprecatedKt$toCollection$1(continuation);
        Object obj2 = channelsKt__DeprecatedKt$toCollection$1.f11509d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$toCollection$1.f11510e;
        if (i2 != 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0065 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:55:0x0031, B:68:0x005c, B:70:0x0065, B:64:0x004a, B:71:0x0078), top: B:82:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0078 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:55:0x0031, B:68:0x005c, B:70:0x0065, B:64:0x004a, B:71:0x0078), top: B:82:0x0031 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0059 -> B:68:0x005c). Please submit an issue!!! */
    @PublishedApi
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <K, V, M extends Map<? super K, ? super V>> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull M m2, @NotNull Continuation<? super M> continuation) {
        ChannelsKt__DeprecatedKt$toMap$2 channelsKt__DeprecatedKt$toMap$2;
        Object coroutine_suspended;
        int i2;
        ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel2;
        Throwable th;
        ChannelIterator<? extends Pair<? extends K, ? extends V>> it;
        M m3;
        Object hasNext;
        if (continuation instanceof ChannelsKt__DeprecatedKt$toMap$2) {
            channelsKt__DeprecatedKt$toMap$2 = (ChannelsKt__DeprecatedKt$toMap$2) continuation;
            int i3 = channelsKt__DeprecatedKt$toMap$2.f11515e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$toMap$2.f11515e = i3 - Integer.MIN_VALUE;
                Object obj = channelsKt__DeprecatedKt$toMap$2.f11514d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = channelsKt__DeprecatedKt$toMap$2.f11515e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        receiveChannel2 = receiveChannel;
                        it = receiveChannel.iterator();
                        m3 = m2;
                        channelsKt__DeprecatedKt$toMap$2.f11511a = m3;
                        channelsKt__DeprecatedKt$toMap$2.f11512b = receiveChannel2;
                        channelsKt__DeprecatedKt$toMap$2.f11513c = it;
                        channelsKt__DeprecatedKt$toMap$2.f11515e = 1;
                        hasNext = it.hasNext(channelsKt__DeprecatedKt$toMap$2);
                        if (hasNext != coroutine_suspended) {
                        }
                    } catch (Throwable th2) {
                        receiveChannel2 = receiveChannel;
                        th = th2;
                        throw th;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ChannelIterator<? extends Pair<? extends K, ? extends V>> channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$toMap$2.f11513c;
                    receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$toMap$2.f11512b;
                    Map map = (Map) channelsKt__DeprecatedKt$toMap$2.f11511a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        Map map2 = map;
                        ChannelIterator<? extends Pair<? extends K, ? extends V>> channelIterator2 = channelIterator;
                        if (!((Boolean) obj).booleanValue()) {
                            Pair<? extends K, ? extends V> next = channelIterator2.next();
                            map2.put(next.getFirst(), next.getSecond());
                            m3 = map2;
                            it = channelIterator2;
                            channelsKt__DeprecatedKt$toMap$2.f11511a = m3;
                            channelsKt__DeprecatedKt$toMap$2.f11512b = receiveChannel2;
                            channelsKt__DeprecatedKt$toMap$2.f11513c = it;
                            channelsKt__DeprecatedKt$toMap$2.f11515e = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$toMap$2);
                            if (hasNext != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            map2 = m3;
                            obj = hasNext;
                            channelIterator2 = it;
                            if (!((Boolean) obj).booleanValue()) {
                                Unit unit = Unit.INSTANCE;
                                ChannelsKt.cancelConsumed(receiveChannel2, null);
                                return map2;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
            }
        }
        channelsKt__DeprecatedKt$toMap$2 = new ChannelsKt__DeprecatedKt$toMap$2(continuation);
        Object obj2 = channelsKt__DeprecatedKt$toMap$2.f11514d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = channelsKt__DeprecatedKt$toMap$2.f11515e;
        if (i2 != 0) {
        }
    }

    @PublishedApi
    @Nullable
    public static final <E> Object toMutableSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel withIndex(ReceiveChannel receiveChannel, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$withIndex$1(receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i2, Object obj) {
        ReceiveChannel withIndex;
        if ((i2 & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        withIndex = withIndex(receiveChannel, coroutineContext);
        return withIndex;
    }

    @PublishedApi
    @NotNull
    public static final <E, R, V> ReceiveChannel<V> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super R, ? extends V> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new ChannelsKt__DeprecatedKt$zip$2(receiveChannel2, receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }
}
