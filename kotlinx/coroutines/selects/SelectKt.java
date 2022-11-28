package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SelectKt {
    @NotNull
    private static final Object NOT_SELECTED = new Symbol("NOT_SELECTED");
    @NotNull
    private static final Object ALREADY_SELECTED = new Symbol("ALREADY_SELECTED");
    @NotNull
    private static final Object UNDECIDED = new Symbol("UNDECIDED");
    @NotNull
    private static final Object RESUMED = new Symbol("RESUMED");
    @NotNull
    private static final SeqNumber selectOpSequenceNumber = new SeqNumber();

    public static final /* synthetic */ Object access$getRESUMED$p() {
        return RESUMED;
    }

    public static final /* synthetic */ SeqNumber access$getSelectOpSequenceNumber$p() {
        return selectOpSequenceNumber;
    }

    public static final /* synthetic */ Object access$getUNDECIDED$p() {
        return UNDECIDED;
    }

    @NotNull
    public static final Object getALREADY_SELECTED() {
        return ALREADY_SELECTED;
    }

    public static /* synthetic */ void getALREADY_SELECTED$annotations() {
    }

    @NotNull
    public static final Object getNOT_SELECTED() {
        return NOT_SELECTED;
    }

    public static /* synthetic */ void getNOT_SELECTED$annotations() {
    }

    private static /* synthetic */ void getRESUMED$annotations() {
    }

    private static /* synthetic */ void getSelectOpSequenceNumber$annotations() {
    }

    private static /* synthetic */ void getUNDECIDED$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ExperimentalCoroutinesApi
    /* renamed from: onTimeout-8Mi8wO0 */
    public static final <R> void m1683onTimeout8Mi8wO0(@NotNull SelectBuilder<? super R> selectBuilder, long j2, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        selectBuilder.onTimeout(DelayKt.m1615toDelayMillisLRDsOJo(j2), function1);
    }

    @Nullable
    public static final <R> Object select(@NotNull Function1<? super SelectBuilder<? super R>, Unit> function1, @NotNull Continuation<? super R> continuation) {
        Object coroutine_suspended;
        SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(continuation);
        try {
            function1.invoke(selectBuilderImpl);
        } catch (Throwable th) {
            selectBuilderImpl.handleBuilderException(th);
        }
        Object result = selectBuilderImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    private static final <R> Object select$$forInline(Function1<? super SelectBuilder<? super R>, Unit> function1, Continuation<? super R> continuation) {
        Object coroutine_suspended;
        InlineMarker.mark(0);
        SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(continuation);
        try {
            function1.invoke(selectBuilderImpl);
        } catch (Throwable th) {
            selectBuilderImpl.handleBuilderException(th);
        }
        Object result = selectBuilderImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }
}
