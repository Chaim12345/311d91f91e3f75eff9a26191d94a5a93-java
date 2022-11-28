package kotlinx.coroutines.flow;

import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final /* synthetic */ class FlowKt__ReduceKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0069  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$first$1 flowKt__ReduceKt$first$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<T> flowCollector;
        T t2;
        if (continuation instanceof FlowKt__ReduceKt$first$1) {
            flowKt__ReduceKt$first$1 = (FlowKt__ReduceKt$first$1) continuation;
            int i3 = flowKt__ReduceKt$first$1.f11936d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$first$1.f11936d = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$first$1.f11935c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$first$1.f11936d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<T> flowCollector2 = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public Object emit(T t3, @NotNull Continuation<? super Unit> continuation2) {
                            Ref.ObjectRef.this.element = t3;
                            throw new AbortFlowException(this);
                        }
                    };
                    try {
                        flowKt__ReduceKt$first$1.f11933a = objectRef2;
                        flowKt__ReduceKt$first$1.f11934b = flowCollector2;
                        flowKt__ReduceKt$first$1.f11936d = 1;
                        if (flow.collect(flowCollector2, flowKt__ReduceKt$first$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowCollector2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t2 = objectRef.element;
                        if (t2 == NullSurrogateKt.NULL) {
                        }
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$first$$inlined$collectWhile$1) flowKt__ReduceKt$first$1.f11934b;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$first$1.f11933a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t2 = objectRef.element;
                        if (t2 == NullSurrogateKt.NULL) {
                        }
                    }
                }
                t2 = objectRef.element;
                if (t2 == NullSurrogateKt.NULL) {
                    return t2;
                }
                throw new NoSuchElementException("Expected at least one element");
            }
        }
        flowKt__ReduceKt$first$1 = new FlowKt__ReduceKt$first$1(continuation);
        Object obj2 = flowKt__ReduceKt$first$1.f11935c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$first$1.f11936d;
        if (i2 != 0) {
        }
        t2 = objectRef.element;
        if (t2 == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$first$3 flowKt__ReduceKt$first$3;
        Object coroutine_suspended;
        int i2;
        Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function22;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<? super Object> flowCollector;
        T t2;
        if (continuation instanceof FlowKt__ReduceKt$first$3) {
            flowKt__ReduceKt$first$3 = (FlowKt__ReduceKt$first$3) continuation;
            int i3 = flowKt__ReduceKt$first$3.f11941e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$first$3.f11941e = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$first$3.f11940d;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$first$3.f11941e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<? super Object> flowKt__ReduceKt$first$$inlined$collectWhile$2 = new FlowKt__ReduceKt$first$$inlined$collectWhile$2(function2, objectRef2);
                    try {
                        flowKt__ReduceKt$first$3.f11937a = function2;
                        flowKt__ReduceKt$first$3.f11938b = objectRef2;
                        flowKt__ReduceKt$first$3.f11939c = flowKt__ReduceKt$first$$inlined$collectWhile$2;
                        flowKt__ReduceKt$first$3.f11941e = 1;
                        if (flow.collect(flowKt__ReduceKt$first$$inlined$collectWhile$2, flowKt__ReduceKt$first$3) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        function22 = function2;
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        function22 = function2;
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowKt__ReduceKt$first$$inlined$collectWhile$2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t2 = objectRef.element;
                        if (t2 == NullSurrogateKt.NULL) {
                        }
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$first$$inlined$collectWhile$2) flowKt__ReduceKt$first$3.f11939c;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$first$3.f11938b;
                    function22 = (Function2) flowKt__ReduceKt$first$3.f11937a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t2 = objectRef.element;
                        if (t2 == NullSurrogateKt.NULL) {
                        }
                    }
                }
                t2 = objectRef.element;
                if (t2 == NullSurrogateKt.NULL) {
                    return t2;
                }
                throw new NoSuchElementException("Expected at least one element matching the predicate " + function22);
            }
        }
        flowKt__ReduceKt$first$3 = new FlowKt__ReduceKt$first$3(continuation);
        Object obj2 = flowKt__ReduceKt$first$3.f11940d;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$first$3.f11941e;
        if (i2 != 0) {
        }
        t2 = objectRef.element;
        if (t2 == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object firstOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$firstOrNull$1 flowKt__ReduceKt$firstOrNull$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<T> flowCollector;
        if (continuation instanceof FlowKt__ReduceKt$firstOrNull$1) {
            flowKt__ReduceKt$firstOrNull$1 = (FlowKt__ReduceKt$firstOrNull$1) continuation;
            int i3 = flowKt__ReduceKt$firstOrNull$1.f11945d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$firstOrNull$1.f11945d = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$firstOrNull$1.f11944c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$firstOrNull$1.f11945d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    FlowCollector<T> flowCollector2 = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public Object emit(T t2, @NotNull Continuation<? super Unit> continuation2) {
                            Ref.ObjectRef.this.element = t2;
                            throw new AbortFlowException(this);
                        }
                    };
                    try {
                        flowKt__ReduceKt$firstOrNull$1.f11942a = objectRef2;
                        flowKt__ReduceKt$firstOrNull$1.f11943b = flowCollector2;
                        flowKt__ReduceKt$firstOrNull$1.f11945d = 1;
                        if (flow.collect(flowCollector2, flowKt__ReduceKt$firstOrNull$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowCollector2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1) flowKt__ReduceKt$firstOrNull$1.f11943b;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$firstOrNull$1.f11942a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$firstOrNull$1 = new FlowKt__ReduceKt$firstOrNull$1(continuation);
        Object obj2 = flowKt__ReduceKt$firstOrNull$1.f11944c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$firstOrNull$1.f11945d;
        if (i2 != 0) {
        }
        return objectRef.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object firstOrNull(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$firstOrNull$3 flowKt__ReduceKt$firstOrNull$3;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<? super Object> flowCollector;
        if (continuation instanceof FlowKt__ReduceKt$firstOrNull$3) {
            flowKt__ReduceKt$firstOrNull$3 = (FlowKt__ReduceKt$firstOrNull$3) continuation;
            int i3 = flowKt__ReduceKt$firstOrNull$3.f11949d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$firstOrNull$3.f11949d = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$firstOrNull$3.f11948c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$firstOrNull$3.f11949d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    FlowCollector<? super Object> flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2 = new FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2(function2, objectRef2);
                    try {
                        flowKt__ReduceKt$firstOrNull$3.f11946a = objectRef2;
                        flowKt__ReduceKt$firstOrNull$3.f11947b = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2;
                        flowKt__ReduceKt$firstOrNull$3.f11949d = 1;
                        if (flow.collect(flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2, flowKt__ReduceKt$firstOrNull$3) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2) flowKt__ReduceKt$firstOrNull$3.f11947b;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$firstOrNull$3.f11946a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$firstOrNull$3 = new FlowKt__ReduceKt$firstOrNull$3(continuation);
        Object obj2 = flowKt__ReduceKt$firstOrNull$3.f11948c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$firstOrNull$3.f11949d;
        if (i2 != 0) {
        }
        return objectRef.element;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T, R> Object fold(@NotNull Flow<? extends T> flow, R r2, @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, @NotNull Continuation<? super R> continuation) {
        FlowKt__ReduceKt$fold$1 flowKt__ReduceKt$fold$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        if (continuation instanceof FlowKt__ReduceKt$fold$1) {
            flowKt__ReduceKt$fold$1 = (FlowKt__ReduceKt$fold$1) continuation;
            int i3 = flowKt__ReduceKt$fold$1.f11952c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$fold$1.f11952c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$fold$1.f11951b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$fold$1.f11952c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = r2;
                    FlowCollector<? super Object> flowKt__ReduceKt$fold$2 = new FlowKt__ReduceKt$fold$2<>(objectRef2, function3);
                    flowKt__ReduceKt$fold$1.f11950a = objectRef2;
                    flowKt__ReduceKt$fold$1.f11952c = 1;
                    if (flow.collect(flowKt__ReduceKt$fold$2, flowKt__ReduceKt$fold$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$fold$1.f11950a;
                    ResultKt.throwOnFailure(obj);
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$fold$1 = new FlowKt__ReduceKt$fold$1(continuation);
        Object obj2 = flowKt__ReduceKt$fold$1.f11951b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$fold$1.f11952c;
        if (i2 != 0) {
        }
        return objectRef.element;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T, R> Object fold$$forInline(Flow<? extends T> flow, R r2, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super R> continuation) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = r2;
        FlowKt__ReduceKt$fold$2 flowKt__ReduceKt$fold$2 = new FlowKt__ReduceKt$fold$2(objectRef, function3);
        InlineMarker.mark(0);
        flow.collect(flowKt__ReduceKt$fold$2, continuation);
        InlineMarker.mark(1);
        return objectRef.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object last(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$last$1 flowKt__ReduceKt$last$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        T t2;
        if (continuation instanceof FlowKt__ReduceKt$last$1) {
            flowKt__ReduceKt$last$1 = (FlowKt__ReduceKt$last$1) continuation;
            int i3 = flowKt__ReduceKt$last$1.f11961c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$last$1.f11961c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$last$1.f11960b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$last$1.f11961c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<? super Object> flowCollector = new FlowCollector() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$last$2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public final Object emit(T t3, @NotNull Continuation<? super Unit> continuation2) {
                            Ref.ObjectRef.this.element = t3;
                            return Unit.INSTANCE;
                        }
                    };
                    flowKt__ReduceKt$last$1.f11959a = objectRef2;
                    flowKt__ReduceKt$last$1.f11961c = 1;
                    if (flow.collect(flowCollector, flowKt__ReduceKt$last$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$last$1.f11959a;
                    ResultKt.throwOnFailure(obj);
                }
                t2 = objectRef.element;
                if (t2 == NullSurrogateKt.NULL) {
                    return t2;
                }
                throw new NoSuchElementException("Expected at least one element");
            }
        }
        flowKt__ReduceKt$last$1 = new FlowKt__ReduceKt$last$1(continuation);
        Object obj2 = flowKt__ReduceKt$last$1.f11960b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$last$1.f11961c;
        if (i2 != 0) {
        }
        t2 = objectRef.element;
        if (t2 == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object lastOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$lastOrNull$1 flowKt__ReduceKt$lastOrNull$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        if (continuation instanceof FlowKt__ReduceKt$lastOrNull$1) {
            flowKt__ReduceKt$lastOrNull$1 = (FlowKt__ReduceKt$lastOrNull$1) continuation;
            int i3 = flowKt__ReduceKt$lastOrNull$1.f11965c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$lastOrNull$1.f11965c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$lastOrNull$1.f11964b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$lastOrNull$1.f11965c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    FlowCollector<? super Object> flowCollector = new FlowCollector() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$lastOrNull$2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation2) {
                            Ref.ObjectRef.this.element = t2;
                            return Unit.INSTANCE;
                        }
                    };
                    flowKt__ReduceKt$lastOrNull$1.f11963a = objectRef2;
                    flowKt__ReduceKt$lastOrNull$1.f11965c = 1;
                    if (flow.collect(flowCollector, flowKt__ReduceKt$lastOrNull$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$lastOrNull$1.f11963a;
                    ResultKt.throwOnFailure(obj);
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$lastOrNull$1 = new FlowKt__ReduceKt$lastOrNull$1(continuation);
        Object obj2 = flowKt__ReduceKt$lastOrNull$1.f11964b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$lastOrNull$1.f11965c;
        if (i2 != 0) {
        }
        return objectRef.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.internal.Symbol, T] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <S, T extends S> Object reduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super S, ? super T, ? super Continuation<? super S>, ? extends Object> function3, @NotNull Continuation<? super S> continuation) {
        FlowKt__ReduceKt$reduce$1 flowKt__ReduceKt$reduce$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        T t2;
        if (continuation instanceof FlowKt__ReduceKt$reduce$1) {
            flowKt__ReduceKt$reduce$1 = (FlowKt__ReduceKt$reduce$1) continuation;
            int i3 = flowKt__ReduceKt$reduce$1.f11969c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$reduce$1.f11969c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$reduce$1.f11968b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$reduce$1.f11969c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = NullSurrogateKt.NULL;
                    FlowCollector<? super Object> flowKt__ReduceKt$reduce$2 = new FlowKt__ReduceKt$reduce$2<>(objectRef2, function3);
                    flowKt__ReduceKt$reduce$1.f11967a = objectRef2;
                    flowKt__ReduceKt$reduce$1.f11969c = 1;
                    if (flow.collect(flowKt__ReduceKt$reduce$2, flowKt__ReduceKt$reduce$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$reduce$1.f11967a;
                    ResultKt.throwOnFailure(obj);
                }
                t2 = objectRef.element;
                if (t2 == NullSurrogateKt.NULL) {
                    return t2;
                }
                throw new NoSuchElementException("Empty flow can't be reduced");
            }
        }
        flowKt__ReduceKt$reduce$1 = new FlowKt__ReduceKt$reduce$1(continuation);
        Object obj2 = flowKt__ReduceKt$reduce$1.f11968b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$reduce$1.f11969c;
        if (i2 != 0) {
        }
        t2 = objectRef.element;
        if (t2 == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object single(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$single$1 flowKt__ReduceKt$single$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        T t2;
        if (continuation instanceof FlowKt__ReduceKt$single$1) {
            flowKt__ReduceKt$single$1 = (FlowKt__ReduceKt$single$1) continuation;
            int i3 = flowKt__ReduceKt$single$1.f11978c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$single$1.f11978c = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$single$1.f11977b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$single$1.f11978c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<? super Object> flowCollector = new FlowCollector() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$single$2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public final Object emit(T t3, @NotNull Continuation<? super Unit> continuation2) {
                            Ref.ObjectRef objectRef3 = Ref.ObjectRef.this;
                            if (objectRef3.element == NullSurrogateKt.NULL) {
                                objectRef3.element = t3;
                                return Unit.INSTANCE;
                            }
                            throw new IllegalArgumentException("Flow has more than one element".toString());
                        }
                    };
                    flowKt__ReduceKt$single$1.f11976a = objectRef2;
                    flowKt__ReduceKt$single$1.f11978c = 1;
                    if (flow.collect(flowCollector, flowKt__ReduceKt$single$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$single$1.f11976a;
                    ResultKt.throwOnFailure(obj);
                }
                t2 = objectRef.element;
                if (t2 == NullSurrogateKt.NULL) {
                    return t2;
                }
                throw new NoSuchElementException("Flow is empty");
            }
        }
        flowKt__ReduceKt$single$1 = new FlowKt__ReduceKt$single$1(continuation);
        Object obj2 = flowKt__ReduceKt$single$1.f11977b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$single$1.f11978c;
        if (i2 != 0) {
        }
        t2 = objectRef.element;
        if (t2 == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0068 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object singleOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$singleOrNull$1 flowKt__ReduceKt$singleOrNull$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<T> flowCollector;
        T t2;
        if (continuation instanceof FlowKt__ReduceKt$singleOrNull$1) {
            flowKt__ReduceKt$singleOrNull$1 = (FlowKt__ReduceKt$singleOrNull$1) continuation;
            int i3 = flowKt__ReduceKt$singleOrNull$1.f11983d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$singleOrNull$1.f11983d = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$singleOrNull$1.f11982c;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$singleOrNull$1.f11983d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<T> flowCollector2 = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$$inlined$collectWhile$1
                        /* JADX WARN: Type inference failed for: r1v0, types: [kotlinx.coroutines.internal.Symbol, T] */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public Object emit(T t3, @NotNull Continuation<? super Unit> continuation2) {
                            boolean z;
                            Ref.ObjectRef objectRef3 = Ref.ObjectRef.this;
                            T t4 = objectRef3.element;
                            ?? r1 = NullSurrogateKt.NULL;
                            if (t4 == r1) {
                                objectRef3.element = t3;
                                z = true;
                            } else {
                                objectRef3.element = r1;
                                z = false;
                            }
                            if (z) {
                                return Unit.INSTANCE;
                            }
                            throw new AbortFlowException(this);
                        }
                    };
                    try {
                        flowKt__ReduceKt$singleOrNull$1.f11980a = objectRef2;
                        flowKt__ReduceKt$singleOrNull$1.f11981b = flowCollector2;
                        flowKt__ReduceKt$singleOrNull$1.f11983d = 1;
                        if (flow.collect(flowCollector2, flowKt__ReduceKt$singleOrNull$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowCollector2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t2 = objectRef.element;
                        if (t2 != NullSurrogateKt.NULL) {
                        }
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$singleOrNull$$inlined$collectWhile$1) flowKt__ReduceKt$singleOrNull$1.f11981b;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$singleOrNull$1.f11980a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t2 = objectRef.element;
                        if (t2 != NullSurrogateKt.NULL) {
                        }
                    }
                }
                t2 = objectRef.element;
                if (t2 != NullSurrogateKt.NULL) {
                    return null;
                }
                return t2;
            }
        }
        flowKt__ReduceKt$singleOrNull$1 = new FlowKt__ReduceKt$singleOrNull$1(continuation);
        Object obj2 = flowKt__ReduceKt$singleOrNull$1.f11982c;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$singleOrNull$1.f11983d;
        if (i2 != 0) {
        }
        t2 = objectRef.element;
        if (t2 != NullSurrogateKt.NULL) {
        }
    }
}
