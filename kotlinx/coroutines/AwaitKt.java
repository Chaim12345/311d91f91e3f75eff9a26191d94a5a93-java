package kotlinx.coroutines;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AwaitKt {
    @Nullable
    public static final <T> Object awaitAll(@NotNull Collection<? extends Deferred<? extends T>> collection, @NotNull Continuation<? super List<? extends T>> continuation) {
        List emptyList;
        if (collection.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        Object[] array = collection.toArray(new Deferred[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return new AwaitAll((Deferred[]) array).await(continuation);
    }

    @Nullable
    public static final <T> Object awaitAll(@NotNull Deferred<? extends T>[] deferredArr, @NotNull Continuation<? super List<? extends T>> continuation) {
        List emptyList;
        if (deferredArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new AwaitAll(deferredArr).await(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object joinAll(@NotNull Collection<? extends Job> collection, @NotNull Continuation<? super Unit> continuation) {
        AwaitKt$joinAll$3 awaitKt$joinAll$3;
        Object coroutine_suspended;
        int i2;
        Iterator it;
        if (continuation instanceof AwaitKt$joinAll$3) {
            awaitKt$joinAll$3 = (AwaitKt$joinAll$3) continuation;
            int i3 = awaitKt$joinAll$3.f11277c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                awaitKt$joinAll$3.f11277c = i3 - Integer.MIN_VALUE;
                Object obj = awaitKt$joinAll$3.f11276b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = awaitKt$joinAll$3.f11277c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    it = collection.iterator();
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    it = (Iterator) awaitKt$joinAll$3.f11275a;
                    ResultKt.throwOnFailure(obj);
                }
                while (it.hasNext()) {
                    awaitKt$joinAll$3.f11275a = it;
                    awaitKt$joinAll$3.f11277c = 1;
                    if (((Job) it.next()).join(awaitKt$joinAll$3) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        awaitKt$joinAll$3 = new AwaitKt$joinAll$3(continuation);
        Object obj2 = awaitKt$joinAll$3.f11276b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = awaitKt$joinAll$3.f11277c;
        if (i2 != 0) {
        }
        while (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0053 -> B:19:0x0056). Please submit an issue!!! */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object joinAll(@NotNull Job[] jobArr, @NotNull Continuation<? super Unit> continuation) {
        AwaitKt$joinAll$1 awaitKt$joinAll$1;
        Object coroutine_suspended;
        int i2;
        Job[] jobArr2;
        int length;
        int i3;
        if (continuation instanceof AwaitKt$joinAll$1) {
            awaitKt$joinAll$1 = (AwaitKt$joinAll$1) continuation;
            int i4 = awaitKt$joinAll$1.f11274e;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                awaitKt$joinAll$1.f11274e = i4 - Integer.MIN_VALUE;
                Object obj = awaitKt$joinAll$1.f11273d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = awaitKt$joinAll$1.f11274e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    jobArr2 = jobArr;
                    length = jobArr.length;
                    i3 = 0;
                    if (i3 < length) {
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    length = awaitKt$joinAll$1.f11272c;
                    i3 = awaitKt$joinAll$1.f11271b;
                    ResultKt.throwOnFailure(obj);
                    jobArr2 = (Job[]) awaitKt$joinAll$1.f11270a;
                    i3++;
                    if (i3 < length) {
                        Job job = jobArr2[i3];
                        awaitKt$joinAll$1.f11270a = jobArr2;
                        awaitKt$joinAll$1.f11271b = i3;
                        awaitKt$joinAll$1.f11272c = length;
                        awaitKt$joinAll$1.f11274e = 1;
                        if (job.join(awaitKt$joinAll$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i3++;
                        if (i3 < length) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        awaitKt$joinAll$1 = new AwaitKt$joinAll$1(continuation);
        Object obj2 = awaitKt$joinAll$1.f11273d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = awaitKt$joinAll$1.f11274e;
        if (i2 != 0) {
        }
    }
}
