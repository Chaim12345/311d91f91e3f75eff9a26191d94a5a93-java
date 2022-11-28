package kotlinx.coroutines.debug.internal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DebugCoroutineInfoImpl {
    @NotNull
    private final WeakReference<CoroutineContext> _context;
    @Nullable
    private WeakReference<CoroutineStackFrame> _lastObservedFrame;
    @NotNull
    private String _state = DebugCoroutineInfoImplKt.CREATED;
    @Nullable
    private final StackTraceFrame creationStackBottom;
    @JvmField
    @Nullable
    public Thread lastObservedThread;
    @JvmField
    public final long sequenceNumber;

    public DebugCoroutineInfoImpl(@Nullable CoroutineContext coroutineContext, @Nullable StackTraceFrame stackTraceFrame, long j2) {
        this.creationStackBottom = stackTraceFrame;
        this.sequenceNumber = j2;
        this._context = new WeakReference<>(coroutineContext);
    }

    private final List<StackTraceElement> creationStackTrace() {
        Sequence sequence;
        List<StackTraceElement> list;
        List<StackTraceElement> emptyList;
        StackTraceFrame stackTraceFrame = this.creationStackBottom;
        if (stackTraceFrame == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        sequence = SequencesKt__SequenceBuilderKt.sequence(new DebugCoroutineInfoImpl$creationStackTrace$1(this, stackTraceFrame, null));
        list = SequencesKt___SequencesKt.toList(sequence);
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x004a -> B:25:0x0061). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x005b -> B:24:0x005e). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object yieldFrames(SequenceScope<? super StackTraceElement> sequenceScope, CoroutineStackFrame coroutineStackFrame, Continuation<? super Unit> continuation) {
        DebugCoroutineInfoImpl$yieldFrames$1 debugCoroutineInfoImpl$yieldFrames$1;
        Object coroutine_suspended;
        int i2;
        DebugCoroutineInfoImpl debugCoroutineInfoImpl;
        if (continuation instanceof DebugCoroutineInfoImpl$yieldFrames$1) {
            debugCoroutineInfoImpl$yieldFrames$1 = (DebugCoroutineInfoImpl$yieldFrames$1) continuation;
            int i3 = debugCoroutineInfoImpl$yieldFrames$1.f11568f;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                debugCoroutineInfoImpl$yieldFrames$1.f11568f = i3 - Integer.MIN_VALUE;
                Object obj = debugCoroutineInfoImpl$yieldFrames$1.f11566d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = debugCoroutineInfoImpl$yieldFrames$1.f11568f;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    debugCoroutineInfoImpl = this;
                    if (coroutineStackFrame == null) {
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    CoroutineStackFrame coroutineStackFrame2 = (CoroutineStackFrame) debugCoroutineInfoImpl$yieldFrames$1.f11565c;
                    SequenceScope<? super StackTraceElement> sequenceScope2 = (SequenceScope) debugCoroutineInfoImpl$yieldFrames$1.f11564b;
                    debugCoroutineInfoImpl = (DebugCoroutineInfoImpl) debugCoroutineInfoImpl$yieldFrames$1.f11563a;
                    ResultKt.throwOnFailure(obj);
                    SequenceScope<? super StackTraceElement> sequenceScope3 = sequenceScope2;
                    coroutineStackFrame = coroutineStackFrame2;
                    sequenceScope = sequenceScope3;
                    coroutineStackFrame = coroutineStackFrame.getCallerFrame();
                    if (coroutineStackFrame == null) {
                        return Unit.INSTANCE;
                    }
                    if (coroutineStackFrame == null) {
                        StackTraceElement stackTraceElement = coroutineStackFrame.getStackTraceElement();
                        if (stackTraceElement != null) {
                            debugCoroutineInfoImpl$yieldFrames$1.f11563a = debugCoroutineInfoImpl;
                            debugCoroutineInfoImpl$yieldFrames$1.f11564b = sequenceScope;
                            debugCoroutineInfoImpl$yieldFrames$1.f11565c = coroutineStackFrame;
                            debugCoroutineInfoImpl$yieldFrames$1.f11568f = 1;
                            if (sequenceScope.yield(stackTraceElement, debugCoroutineInfoImpl$yieldFrames$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            CoroutineStackFrame coroutineStackFrame3 = coroutineStackFrame;
                            sequenceScope2 = sequenceScope;
                            coroutineStackFrame2 = coroutineStackFrame3;
                            SequenceScope<? super StackTraceElement> sequenceScope32 = sequenceScope2;
                            coroutineStackFrame = coroutineStackFrame2;
                            sequenceScope = sequenceScope32;
                        }
                        coroutineStackFrame = coroutineStackFrame.getCallerFrame();
                        if (coroutineStackFrame == null) {
                        }
                        if (coroutineStackFrame == null) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        debugCoroutineInfoImpl$yieldFrames$1 = new DebugCoroutineInfoImpl$yieldFrames$1(this, continuation);
        Object obj2 = debugCoroutineInfoImpl$yieldFrames$1.f11566d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = debugCoroutineInfoImpl$yieldFrames$1.f11568f;
        if (i2 != 0) {
        }
    }

    @Nullable
    public final CoroutineContext getContext() {
        return this._context.get();
    }

    @Nullable
    public final StackTraceFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    @NotNull
    public final List<StackTraceElement> getCreationStackTrace() {
        return creationStackTrace();
    }

    @Nullable
    public final CoroutineStackFrame getLastObservedFrame$kotlinx_coroutines_core() {
        WeakReference<CoroutineStackFrame> weakReference = this._lastObservedFrame;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @NotNull
    public final String getState() {
        return this._state;
    }

    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace() {
        List<StackTraceElement> emptyList;
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList();
        while (lastObservedFrame$kotlinx_coroutines_core != null) {
            StackTraceElement stackTraceElement = lastObservedFrame$kotlinx_coroutines_core.getStackTraceElement();
            if (stackTraceElement != null) {
                arrayList.add(stackTraceElement);
            }
            lastObservedFrame$kotlinx_coroutines_core = lastObservedFrame$kotlinx_coroutines_core.getCallerFrame();
        }
        return arrayList;
    }

    public final void setLastObservedFrame$kotlinx_coroutines_core(@Nullable CoroutineStackFrame coroutineStackFrame) {
        this._lastObservedFrame = coroutineStackFrame != null ? new WeakReference<>(coroutineStackFrame) : null;
    }

    @NotNull
    public String toString() {
        return "DebugCoroutineInfo(state=" + getState() + ",context=" + getContext() + ')';
    }

    public final void updateState$kotlinx_coroutines_core(@NotNull String str, @NotNull Continuation<?> continuation) {
        if (Intrinsics.areEqual(this._state, str) && Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.SUSPENDED) && getLastObservedFrame$kotlinx_coroutines_core() != null) {
            return;
        }
        this._state = str;
        setLastObservedFrame$kotlinx_coroutines_core(continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null);
        this.lastObservedThread = Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.RUNNING) ? Thread.currentThread() : null;
    }
}
