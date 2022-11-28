package kotlinx.coroutines;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TimeoutKt {
    @NotNull
    public static final TimeoutCancellationException TimeoutCancellationException(long j2, @NotNull Job job) {
        return new TimeoutCancellationException("Timed out waiting for " + j2 + " ms", job);
    }

    private static final <U, T extends U> Object setupTimeout(TimeoutCoroutine<U, ? super T> timeoutCoroutine, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        JobKt.disposeOnCompletion(timeoutCoroutine, DelayKt.getDelay(timeoutCoroutine.uCont.getContext()).invokeOnTimeout(timeoutCoroutine.time, timeoutCoroutine, timeoutCoroutine.getContext()));
        return UndispatchedKt.startUndispatchedOrReturnIgnoreTimeout(timeoutCoroutine, timeoutCoroutine, function2);
    }

    @Nullable
    public static final <T> Object withTimeout(long j2, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        Object coroutine_suspended;
        if (j2 > 0) {
            Object obj = setupTimeout(new TimeoutCoroutine(j2, continuation), function2);
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (obj == coroutine_suspended) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return obj;
        }
        throw new TimeoutCancellationException("Timed out immediately");
    }

    @Nullable
    /* renamed from: withTimeout-KLykuaI  reason: not valid java name */
    public static final <T> Object m1623withTimeoutKLykuaI(long j2, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return withTimeout(DelayKt.m1615toDelayMillisLRDsOJo(j2), function2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0075  */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.TimeoutCoroutine, T] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object withTimeoutOrNull(long j2, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        TimeoutKt$withTimeoutOrNull$1 timeoutKt$withTimeoutOrNull$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        Object coroutine_suspended2;
        if (continuation instanceof TimeoutKt$withTimeoutOrNull$1) {
            timeoutKt$withTimeoutOrNull$1 = (TimeoutKt$withTimeoutOrNull$1) continuation;
            int i3 = timeoutKt$withTimeoutOrNull$1.f11299e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                timeoutKt$withTimeoutOrNull$1.f11299e = i3 - Integer.MIN_VALUE;
                Object obj = timeoutKt$withTimeoutOrNull$1.f11298d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = timeoutKt$withTimeoutOrNull$1.f11299e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (j2 <= 0) {
                        return null;
                    }
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    try {
                        timeoutKt$withTimeoutOrNull$1.f11296b = function2;
                        timeoutKt$withTimeoutOrNull$1.f11297c = objectRef2;
                        timeoutKt$withTimeoutOrNull$1.f11295a = j2;
                        timeoutKt$withTimeoutOrNull$1.f11299e = 1;
                        ?? r2 = (T) new TimeoutCoroutine(j2, timeoutKt$withTimeoutOrNull$1);
                        objectRef2.element = r2;
                        Object obj2 = setupTimeout(r2, function2);
                        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (obj2 == coroutine_suspended2) {
                            DebugProbesKt.probeCoroutineSuspended(timeoutKt$withTimeoutOrNull$1);
                        }
                        return obj2 == coroutine_suspended ? coroutine_suspended : obj2;
                    } catch (TimeoutCancellationException e2) {
                        e = e2;
                        objectRef = objectRef2;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) timeoutKt$withTimeoutOrNull$1.f11297c;
                    Function2 function22 = (Function2) timeoutKt$withTimeoutOrNull$1.f11296b;
                    try {
                        ResultKt.throwOnFailure(obj);
                        return obj;
                    } catch (TimeoutCancellationException e3) {
                        e = e3;
                    }
                }
                if (e.coroutine != objectRef.element) {
                    return null;
                }
                throw e;
            }
        }
        timeoutKt$withTimeoutOrNull$1 = new TimeoutKt$withTimeoutOrNull$1(continuation);
        Object obj3 = timeoutKt$withTimeoutOrNull$1.f11298d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = timeoutKt$withTimeoutOrNull$1.f11299e;
        if (i2 != 0) {
        }
        if (e.coroutine != objectRef.element) {
        }
    }

    @Nullable
    /* renamed from: withTimeoutOrNull-KLykuaI  reason: not valid java name */
    public static final <T> Object m1624withTimeoutOrNullKLykuaI(long j2, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return withTimeoutOrNull(DelayKt.m1615toDelayMillisLRDsOJo(j2), function2, continuation);
    }
}
