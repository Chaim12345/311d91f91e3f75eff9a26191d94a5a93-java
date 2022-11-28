package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class StateFlowImpl<T> extends AbstractSharedFlow<StateFlowSlot> implements MutableStateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    @NotNull
    private volatile /* synthetic */ Object _state;
    private int sequence;

    public StateFlowImpl(@NotNull Object obj) {
        this._state = obj;
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    private final boolean updateState(Object obj, Object obj2) {
        int i2;
        AbstractSharedFlowSlot[] d2;
        d();
        synchronized (this) {
            Object obj3 = this._state;
            if (obj != null && !Intrinsics.areEqual(obj3, obj)) {
                return false;
            }
            if (Intrinsics.areEqual(obj3, obj2)) {
                return true;
            }
            this._state = obj2;
            int i3 = this.sequence;
            if ((i3 & 1) != 0) {
                this.sequence = i3 + 2;
                return true;
            }
            int i4 = i3 + 1;
            this.sequence = i4;
            AbstractSharedFlowSlot[] d3 = d();
            Unit unit = Unit.INSTANCE;
            while (true) {
                StateFlowSlot[] stateFlowSlotArr = (StateFlowSlot[]) d3;
                if (stateFlowSlotArr != null) {
                    for (StateFlowSlot stateFlowSlot : stateFlowSlotArr) {
                        if (stateFlowSlot != null) {
                            stateFlowSlot.makePending();
                        }
                    }
                }
                synchronized (this) {
                    i2 = this.sequence;
                    if (i2 == i4) {
                        this.sequence = i4 + 1;
                        return true;
                    }
                    d2 = d();
                    Unit unit2 = Unit.INSTANCE;
                }
                d3 = d2;
                i4 = i2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b3, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r11, r12) == false) goto L26;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00aa A[Catch: all -> 0x0073, TryCatch #0 {all -> 0x0073, blocks: (B:14:0x003e, B:34:0x00a6, B:36:0x00aa, B:38:0x00af, B:48:0x00d0, B:50:0x00d6, B:40:0x00b5, B:44:0x00bc, B:19:0x005c, B:22:0x006f, B:33:0x0097), top: B:57:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00af A[Catch: all -> 0x0073, TryCatch #0 {all -> 0x0073, blocks: (B:14:0x003e, B:34:0x00a6, B:36:0x00aa, B:38:0x00af, B:48:0x00d0, B:50:0x00d6, B:40:0x00b5, B:44:0x00bc, B:19:0x005c, B:22:0x006f, B:33:0x0097), top: B:57:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ce A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d6 A[Catch: all -> 0x0073, TRY_LEAVE, TryCatch #0 {all -> 0x0073, blocks: (B:14:0x003e, B:34:0x00a6, B:36:0x00aa, B:38:0x00af, B:48:0x00d0, B:50:0x00d6, B:40:0x00b5, B:44:0x00bc, B:19:0x005c, B:22:0x006f, B:33:0x0097), top: B:57:0x0024 }] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v2, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x00d4 -> B:34:0x00a6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00e6 -> B:34:0x00a6). Please submit an issue!!! */
    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<?> continuation) {
        StateFlowImpl$collect$1 stateFlowImpl$collect$1;
        Object coroutine_suspended;
        int i2;
        StateFlowImpl<T> stateFlowImpl;
        StateFlowSlot stateFlowSlot;
        FlowCollector flowCollector2;
        Job job;
        Object obj;
        FlowCollector<? super T> flowCollector3;
        StateFlowSlot stateFlowSlot2;
        boolean takePending;
        Object obj2;
        try {
            if (continuation instanceof StateFlowImpl$collect$1) {
                stateFlowImpl$collect$1 = (StateFlowImpl$collect$1) continuation;
                int i3 = stateFlowImpl$collect$1.f12228h;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    stateFlowImpl$collect$1.f12228h = i3 - Integer.MIN_VALUE;
                    Object obj3 = stateFlowImpl$collect$1.f12226f;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = stateFlowImpl$collect$1.f12228h;
                    StateFlowSlot stateFlowSlot3 = 1;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj3);
                        StateFlowSlot stateFlowSlot4 = (StateFlowSlot) a();
                        try {
                            if (flowCollector instanceof SubscribedFlowCollector) {
                                stateFlowImpl$collect$1.f12221a = this;
                                stateFlowImpl$collect$1.f12222b = flowCollector;
                                stateFlowImpl$collect$1.f12223c = stateFlowSlot4;
                                stateFlowImpl$collect$1.f12228h = 1;
                                if (((SubscribedFlowCollector) flowCollector).onSubscription(stateFlowImpl$collect$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            stateFlowImpl = this;
                            stateFlowSlot = stateFlowSlot4;
                        } catch (Throwable th) {
                            th = th;
                            stateFlowImpl = this;
                            stateFlowSlot3 = stateFlowSlot4;
                            stateFlowImpl.b(stateFlowSlot3);
                            throw th;
                        }
                    } else if (i2 == 1) {
                        stateFlowSlot = (StateFlowSlot) stateFlowImpl$collect$1.f12223c;
                        flowCollector = (FlowCollector) stateFlowImpl$collect$1.f12222b;
                        stateFlowImpl = (StateFlowImpl) stateFlowImpl$collect$1.f12221a;
                        ResultKt.throwOnFailure(obj3);
                    } else if (i2 == 2) {
                        obj = stateFlowImpl$collect$1.f12225e;
                        job = (Job) stateFlowImpl$collect$1.f12224d;
                        StateFlowSlot stateFlowSlot5 = (StateFlowSlot) stateFlowImpl$collect$1.f12223c;
                        FlowCollector<? super T> flowCollector4 = (FlowCollector) stateFlowImpl$collect$1.f12222b;
                        stateFlowImpl = (StateFlowImpl) stateFlowImpl$collect$1.f12221a;
                        ResultKt.throwOnFailure(obj3);
                        stateFlowSlot2 = stateFlowSlot5;
                        flowCollector3 = flowCollector4;
                        takePending = stateFlowSlot2.takePending();
                        stateFlowSlot3 = stateFlowSlot2;
                        flowCollector2 = flowCollector3;
                        if (!takePending) {
                        }
                        Object obj4 = stateFlowImpl._state;
                        if (job != null) {
                        }
                        if (obj != null) {
                        }
                        if (obj4 == NullSurrogateKt.NULL) {
                        }
                        stateFlowImpl$collect$1.f12221a = stateFlowImpl;
                        stateFlowImpl$collect$1.f12222b = flowCollector2;
                        stateFlowImpl$collect$1.f12223c = stateFlowSlot3;
                        stateFlowImpl$collect$1.f12224d = job;
                        stateFlowImpl$collect$1.f12225e = obj4;
                        stateFlowImpl$collect$1.f12228h = 2;
                        if (flowCollector2.emit(obj2, stateFlowImpl$collect$1) == coroutine_suspended) {
                        }
                    } else if (i2 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        obj = stateFlowImpl$collect$1.f12225e;
                        job = (Job) stateFlowImpl$collect$1.f12224d;
                        StateFlowSlot stateFlowSlot6 = (StateFlowSlot) stateFlowImpl$collect$1.f12223c;
                        FlowCollector flowCollector5 = (FlowCollector) stateFlowImpl$collect$1.f12222b;
                        stateFlowImpl = (StateFlowImpl) stateFlowImpl$collect$1.f12221a;
                        ResultKt.throwOnFailure(obj3);
                        stateFlowSlot3 = stateFlowSlot6;
                        flowCollector2 = flowCollector5;
                        Object obj42 = stateFlowImpl._state;
                        if (job != null) {
                            JobKt.ensureActive(job);
                        }
                        if (obj != null) {
                            stateFlowSlot2 = stateFlowSlot3;
                            flowCollector3 = flowCollector2;
                        }
                        obj2 = obj42 == NullSurrogateKt.NULL ? null : obj42;
                        stateFlowImpl$collect$1.f12221a = stateFlowImpl;
                        stateFlowImpl$collect$1.f12222b = flowCollector2;
                        stateFlowImpl$collect$1.f12223c = stateFlowSlot3;
                        stateFlowImpl$collect$1.f12224d = job;
                        stateFlowImpl$collect$1.f12225e = obj42;
                        stateFlowImpl$collect$1.f12228h = 2;
                        if (flowCollector2.emit(obj2, stateFlowImpl$collect$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = obj42;
                        stateFlowSlot2 = stateFlowSlot3;
                        flowCollector3 = flowCollector2;
                        takePending = stateFlowSlot2.takePending();
                        stateFlowSlot3 = stateFlowSlot2;
                        flowCollector2 = flowCollector3;
                        if (!takePending) {
                            stateFlowImpl$collect$1.f12221a = stateFlowImpl;
                            stateFlowImpl$collect$1.f12222b = flowCollector3;
                            stateFlowImpl$collect$1.f12223c = stateFlowSlot2;
                            stateFlowImpl$collect$1.f12224d = job;
                            stateFlowImpl$collect$1.f12225e = obj;
                            stateFlowImpl$collect$1.f12228h = 3;
                            Object awaitPending = stateFlowSlot2.awaitPending(stateFlowImpl$collect$1);
                            stateFlowSlot3 = stateFlowSlot2;
                            flowCollector2 = flowCollector3;
                            if (awaitPending == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        Object obj422 = stateFlowImpl._state;
                        if (job != null) {
                        }
                        if (obj != null) {
                        }
                        if (obj422 == NullSurrogateKt.NULL) {
                        }
                        stateFlowImpl$collect$1.f12221a = stateFlowImpl;
                        stateFlowImpl$collect$1.f12222b = flowCollector2;
                        stateFlowImpl$collect$1.f12223c = stateFlowSlot3;
                        stateFlowImpl$collect$1.f12224d = job;
                        stateFlowImpl$collect$1.f12225e = obj422;
                        stateFlowImpl$collect$1.f12228h = 2;
                        if (flowCollector2.emit(obj2, stateFlowImpl$collect$1) == coroutine_suspended) {
                        }
                    }
                    flowCollector2 = flowCollector;
                    job = (Job) stateFlowImpl$collect$1.getContext().get(Job.Key);
                    obj = null;
                    stateFlowSlot3 = stateFlowSlot;
                    Object obj4222 = stateFlowImpl._state;
                    if (job != null) {
                    }
                    if (obj != null) {
                    }
                    if (obj4222 == NullSurrogateKt.NULL) {
                    }
                    stateFlowImpl$collect$1.f12221a = stateFlowImpl;
                    stateFlowImpl$collect$1.f12222b = flowCollector2;
                    stateFlowImpl$collect$1.f12223c = stateFlowSlot3;
                    stateFlowImpl$collect$1.f12224d = job;
                    stateFlowImpl$collect$1.f12225e = obj4222;
                    stateFlowImpl$collect$1.f12228h = 2;
                    if (flowCollector2.emit(obj2, stateFlowImpl$collect$1) == coroutine_suspended) {
                    }
                }
            }
            if (i2 != 0) {
            }
            flowCollector2 = flowCollector;
            job = (Job) stateFlowImpl$collect$1.getContext().get(Job.Key);
            obj = null;
            stateFlowSlot3 = stateFlowSlot;
            Object obj42222 = stateFlowImpl._state;
            if (job != null) {
            }
            if (obj != null) {
            }
            if (obj42222 == NullSurrogateKt.NULL) {
            }
            stateFlowImpl$collect$1.f12221a = stateFlowImpl;
            stateFlowImpl$collect$1.f12222b = flowCollector2;
            stateFlowImpl$collect$1.f12223c = stateFlowSlot3;
            stateFlowImpl$collect$1.f12224d = job;
            stateFlowImpl$collect$1.f12225e = obj42222;
            stateFlowImpl$collect$1.f12228h = 2;
            if (flowCollector2.emit(obj2, stateFlowImpl$collect$1) == coroutine_suspended) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
        stateFlowImpl$collect$1 = new StateFlowImpl$collect$1(this, continuation);
        Object obj32 = stateFlowImpl$collect$1.f12226f;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = stateFlowImpl$collect$1.f12228h;
        StateFlowSlot stateFlowSlot32 = 1;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public boolean compareAndSet(T t2, T t3) {
        if (t2 == null) {
            t2 = (T) NullSurrogateKt.NULL;
        }
        if (t3 == null) {
            t3 = (T) NullSurrogateKt.NULL;
        }
        return updateState(t2, t3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    @NotNull
    /* renamed from: e */
    public StateFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow, kotlinx.coroutines.flow.FlowCollector
    @Nullable
    public Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        setValue(t2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    @NotNull
    /* renamed from: f */
    public StateFlowSlot[] createSlotArray(int i2) {
        return new StateFlowSlot[i2];
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    @NotNull
    public Flow<T> fuse(@NotNull CoroutineContext coroutineContext, int i2, @NotNull BufferOverflow bufferOverflow) {
        return StateFlowKt.fuseStateFlow(this, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    @NotNull
    public List<T> getReplayCache() {
        List<T> listOf;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(getValue());
        return listOf;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow, kotlinx.coroutines.flow.StateFlow
    public T getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        T t2 = (T) this._state;
        if (t2 == symbol) {
            return null;
        }
        return t2;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(T t2) {
        if (t2 == null) {
            t2 = (T) NullSurrogateKt.NULL;
        }
        updateState(null, t2);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T t2) {
        setValue(t2);
        return true;
    }
}
