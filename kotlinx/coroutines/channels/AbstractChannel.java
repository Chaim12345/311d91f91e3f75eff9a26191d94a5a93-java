package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Itr<E> implements ChannelIterator<E> {
        @JvmField
        @NotNull
        public final AbstractChannel<E> channel;
        @Nullable
        private Object result = AbstractChannelKt.POLL_FAILED;

        public Itr(@NotNull AbstractChannel<E> abstractChannel) {
            this.channel = abstractChannel;
        }

        private final boolean hasNextResult(Object obj) {
            if (obj instanceof Closed) {
                Closed closed = (Closed) obj;
                if (closed.closeCause == null) {
                    return false;
                }
                throw StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException());
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object hasNextSuspend(Continuation<? super Boolean> continuation) {
            Continuation intercepted;
            Object coroutine_suspended;
            Object createFailure;
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, orCreateCancellableContinuation);
            while (true) {
                if (this.channel.enqueueReceive(receiveHasNext)) {
                    this.channel.removeReceiveOnCancel(orCreateCancellableContinuation, receiveHasNext);
                    break;
                }
                Object y = this.channel.y();
                setResult(y);
                if (y instanceof Closed) {
                    Closed closed = (Closed) y;
                    if (closed.closeCause == null) {
                        Result.Companion companion = Result.Companion;
                        createFailure = Boxing.boxBoolean(false);
                    } else {
                        Result.Companion companion2 = Result.Companion;
                        createFailure = ResultKt.createFailure(closed.getReceiveException());
                    }
                    orCreateCancellableContinuation.resumeWith(Result.m187constructorimpl(createFailure));
                } else if (y != AbstractChannelKt.POLL_FAILED) {
                    Boolean boxBoolean = Boxing.boxBoolean(true);
                    Function1 function1 = this.channel.f11318a;
                    orCreateCancellableContinuation.resume(boxBoolean, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, y, orCreateCancellableContinuation.getContext()) : null);
                }
            }
            Object result = orCreateCancellableContinuation.getResult();
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }

        @Nullable
        public final Object getResult() {
            return this.result;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Nullable
        public Object hasNext(@NotNull Continuation<? super Boolean> continuation) {
            Object obj = this.result;
            Symbol symbol = AbstractChannelKt.POLL_FAILED;
            if (obj == symbol) {
                obj = this.channel.y();
                this.result = obj;
                if (obj == symbol) {
                    return hasNextSuspend(continuation);
                }
            }
            return Boxing.boxBoolean(hasNextResult(obj));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            E e2 = (E) this.result;
            if (e2 instanceof Closed) {
                throw StackTraceRecoveryKt.recoverStackTrace(((Closed) e2).getReceiveException());
            }
            Symbol symbol = AbstractChannelKt.POLL_FAILED;
            if (e2 != symbol) {
                this.result = symbol;
                return e2;
            }
            throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        @JvmName(name = "next")
        public /* synthetic */ Object next(Continuation continuation) {
            return ChannelIterator.DefaultImpls.next(this, continuation);
        }

        public final void setResult(@Nullable Object obj) {
            this.result = obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ReceiveElement<E> extends Receive<E> {
        @JvmField
        @NotNull
        public final CancellableContinuation<Object> cont;
        @JvmField
        public final int receiveMode;

        public ReceiveElement(@NotNull CancellableContinuation<Object> cancellableContinuation, int i2) {
            this.cont = cancellableContinuation;
            this.receiveMode = i2;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e2) {
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            CancellableContinuation<Object> cancellableContinuation;
            Object createFailure;
            if (this.receiveMode == 1) {
                cancellableContinuation = this.cont;
                createFailure = ChannelResult.m1634boximpl(ChannelResult.Companion.m1647closedJP2dKIU(closed.closeCause));
                Result.Companion companion = Result.Companion;
            } else {
                cancellableContinuation = this.cont;
                Result.Companion companion2 = Result.Companion;
                createFailure = ResultKt.createFailure(closed.getReceiveException());
            }
            cancellableContinuation.resumeWith(Result.m187constructorimpl(createFailure));
        }

        @Nullable
        public final Object resumeValue(E e2) {
            return this.receiveMode == 1 ? ChannelResult.m1634boximpl(ChannelResult.Companion.m1649successJP2dKIU(e2)) : e2;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveElement@" + DebugStringsKt.getHexAddress(this) + "[receiveMode=" + this.receiveMode + AbstractJsonLexerKt.END_LIST;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e2, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            Object tryResume = this.cont.tryResume(resumeValue(e2), prepareOp != null ? prepareOp.desc : null, resumeOnCancellationFun(e2));
            if (tryResume == null) {
                return null;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(tryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                    throw new AssertionError();
                }
            }
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ReceiveElementWithUndeliveredHandler<E> extends ReceiveElement<E> {
        @JvmField
        @NotNull
        public final Function1<E, Unit> onUndeliveredElement;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveElementWithUndeliveredHandler(@NotNull CancellableContinuation<Object> cancellableContinuation, int i2, @NotNull Function1<? super E, Unit> function1) {
            super(cancellableContinuation, i2);
            this.onUndeliveredElement = function1;
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e2) {
            return OnUndeliveredElementKt.bindCancellationFun(this.onUndeliveredElement, e2, this.cont.getContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ReceiveHasNext<E> extends Receive<E> {
        @JvmField
        @NotNull
        public final CancellableContinuation<Boolean> cont;
        @JvmField
        @NotNull
        public final Itr<E> iterator;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveHasNext(@NotNull Itr<E> itr, @NotNull CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.iterator = itr;
            this.cont = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e2) {
            this.iterator.setResult(e2);
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e2) {
            Function1 function1 = this.iterator.channel.f11318a;
            if (function1 != null) {
                return OnUndeliveredElementKt.bindCancellationFun(function1, e2, this.cont.getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Object tryResume$default = closed.closeCause == null ? CancellableContinuation.DefaultImpls.tryResume$default(this.cont, Boolean.FALSE, null, 2, null) : this.cont.tryResumeWithException(closed.getReceiveException());
            if (tryResume$default != null) {
                this.iterator.setResult(closed);
                this.cont.completeResume(tryResume$default);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveHasNext@" + DebugStringsKt.getHexAddress(this);
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e2, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            Object tryResume = this.cont.tryResume(Boolean.TRUE, prepareOp != null ? prepareOp.desc : null, resumeOnCancellationFun(e2));
            if (tryResume == null) {
                return null;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(tryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                    throw new AssertionError();
                }
            }
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ReceiveSelect<R, E> extends Receive<E> implements DisposableHandle {
        @JvmField
        @NotNull
        public final Function2<Object, Continuation<? super R>, Object> block;
        @JvmField
        @NotNull
        public final AbstractChannel<E> channel;
        @JvmField
        public final int receiveMode;
        @JvmField
        @NotNull
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveSelect(@NotNull AbstractChannel<E> abstractChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i2) {
            this.channel = abstractChannel;
            this.select = selectInstance;
            this.block = function2;
            this.receiveMode = i2;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e2) {
            CancellableKt.startCoroutineCancellable(this.block, this.receiveMode == 1 ? ChannelResult.m1634boximpl(ChannelResult.Companion.m1649successJP2dKIU(e2)) : e2, this.select.getCompletion(), resumeOnCancellationFun(e2));
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (remove()) {
                this.channel.w();
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e2) {
            Function1 function1 = this.channel.f11318a;
            if (function1 != null) {
                return OnUndeliveredElementKt.bindCancellationFun(function1, e2, this.select.getCompletion().getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            if (this.select.trySelect()) {
                int i2 = this.receiveMode;
                if (i2 == 0) {
                    this.select.resumeSelectWithException(closed.getReceiveException());
                } else if (i2 != 1) {
                } else {
                    CancellableKt.startCoroutineCancellable$default(this.block, ChannelResult.m1634boximpl(ChannelResult.Companion.m1647closedJP2dKIU(closed.closeCause)), this.select.getCompletion(), null, 4, null);
                }
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveSelect@" + DebugStringsKt.getHexAddress(this) + AbstractJsonLexerKt.BEGIN_LIST + this.select + ",receiveMode=" + this.receiveMode + AbstractJsonLexerKt.END_LIST;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e2, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.select.trySelectOther(prepareOp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class RemoveReceiveOnCancel extends BeforeResumeCancelHandler {
        @NotNull
        private final Receive<?> receive;

        public RemoveReceiveOnCancel(@NotNull Receive<?> receive) {
            this.receive = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            if (this.receive.remove()) {
                AbstractChannel.this.w();
            }
        }

        @NotNull
        public String toString() {
            return "RemoveReceiveOnCancel[" + this.receive + AbstractJsonLexerKt.END_LIST;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static final class TryPollDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<Send> {
        public TryPollDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        protected Object a(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof Send) {
                return null;
            }
            return AbstractChannelKt.POLL_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol tryResumeSend = ((Send) prepareOp.affected).tryResumeSend(prepareOp);
            if (tryResumeSend == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            Object obj = AtomicKt.RETRY_ATOMIC;
            if (tryResumeSend == obj) {
                return obj;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN) {
                    return null;
                }
                throw new AssertionError();
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void onRemoved(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            ((Send) lockFreeLinkedListNode).undeliveredElement();
        }
    }

    public AbstractChannel(@Nullable Function1<? super E, Unit> function1) {
        super(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean enqueueReceive(Receive<? super E> receive) {
        boolean p2 = p(receive);
        if (p2) {
            x();
        }
        return p2;
    }

    private final <R> boolean enqueueReceiveSelect(SelectInstance<? super R> selectInstance, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i2) {
        ReceiveSelect receiveSelect = new ReceiveSelect(this, selectInstance, function2, i2);
        boolean enqueueReceive = enqueueReceive(receiveSelect);
        if (enqueueReceive) {
            selectInstance.disposeOnSelect(receiveSelect);
        }
        return enqueueReceive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [kotlinx.coroutines.channels.AbstractChannel$ReceiveElement] */
    public final <R> Object receiveSuspend(int i2, Continuation<? super R> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        ReceiveElementWithUndeliveredHandler receiveElement = this.f11318a == null ? new ReceiveElement(orCreateCancellableContinuation, i2) : new ReceiveElementWithUndeliveredHandler(orCreateCancellableContinuation, i2, this.f11318a);
        while (true) {
            if (!enqueueReceive(receiveElement)) {
                Object y = y();
                if (!(y instanceof Closed)) {
                    if (y != AbstractChannelKt.POLL_FAILED) {
                        orCreateCancellableContinuation.resume(receiveElement.resumeValue(y), receiveElement.resumeOnCancellationFun(y));
                        break;
                    }
                } else {
                    receiveElement.resumeReceiveClosed((Closed) y);
                    break;
                }
            } else {
                removeReceiveOnCancel(orCreateCancellableContinuation, receiveElement);
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectReceiveMode(SelectInstance<? super R> selectInstance, int i2, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            if (!t()) {
                Object z = z(selectInstance);
                if (z == SelectKt.getALREADY_SELECTED()) {
                    return;
                }
                if (z != AbstractChannelKt.POLL_FAILED && z != AtomicKt.RETRY_ATOMIC) {
                    tryStartBlockUnintercepted(function2, selectInstance, i2, z);
                }
            } else if (enqueueReceiveSelect(selectInstance, function2, i2)) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeReceiveOnCancel(CancellableContinuation<?> cancellableContinuation, Receive<?> receive) {
        cancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(receive));
    }

    private final <R> void tryStartBlockUnintercepted(Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, SelectInstance<? super R> selectInstance, int i2, Object obj) {
        ChannelResult.Companion companion;
        Object m1649successJP2dKIU;
        boolean z = obj instanceof Closed;
        if (z) {
            if (i2 == 0) {
                throw StackTraceRecoveryKt.recoverStackTrace(((Closed) obj).getReceiveException());
            }
            if (i2 != 1 || !selectInstance.trySelect()) {
                return;
            }
            companion = ChannelResult.Companion;
        } else if (i2 != 1) {
            UndispatchedKt.startCoroutineUnintercepted(function2, obj, selectInstance.getCompletion());
            return;
        } else {
            companion = ChannelResult.Companion;
            if (!z) {
                m1649successJP2dKIU = companion.m1649successJP2dKIU(obj);
                UndispatchedKt.startCoroutineUnintercepted(function2, ChannelResult.m1634boximpl(m1649successJP2dKIU), selectInstance.getCompletion());
            }
        }
        m1649successJP2dKIU = companion.m1647closedJP2dKIU(((Closed) obj).closeCause);
        UndispatchedKt.startCoroutineUnintercepted(function2, ChannelResult.m1634boximpl(m1649successJP2dKIU), selectInstance.getCompletion());
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(@Nullable CancellationException cancellationException) {
        if (isClosedForReceive()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new CancellationException(DebugStringsKt.getClassSimpleName(this) + " was cancelled");
        }
        cancel(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: cancelInternal$kotlinx_coroutines_core */
    public final boolean cancel(@Nullable Throwable th) {
        boolean close = close(th);
        u(close);
        return close;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<E> getOnReceive() {
        return new SelectClause1<E>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceive$1
            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
                AbstractChannel.this.registerSelectReceiveMode(selectInstance, 0, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        return (SelectClause1<ChannelResult<? extends E>>) new SelectClause1<ChannelResult<? extends E>>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveCatching$1
            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super ChannelResult<? extends E>, ? super Continuation<? super R>, ? extends Object> function2) {
                AbstractChannel.this.registerSelectReceiveMode(selectInstance, 1, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<E> getOnReceiveOrNull() {
        return Channel.DefaultImpls.getOnReceiveOrNull(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return e() != null && s();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return t();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final ChannelIterator<E> iterator() {
        return new Itr(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @Nullable
    public ReceiveOrClosed m() {
        ReceiveOrClosed m2 = super.m();
        if (m2 != null && !(m2 instanceof Closed)) {
            w();
        }
        return m2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final TryPollDesc o() {
        return new TryPollDesc(g());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean p(@NotNull final Receive receive) {
        int tryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (!r()) {
            LockFreeLinkedListNode g2 = g();
            LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(receive) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
                @Override // kotlinx.coroutines.internal.AtomicOp
                @Nullable
                public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
                    if (this.s()) {
                        return null;
                    }
                    return LockFreeLinkedListKt.getCONDITION_FALSE();
                }
            };
            do {
                LockFreeLinkedListNode prevNode2 = g2.getPrevNode();
                if (!(!(prevNode2 instanceof Send))) {
                    return false;
                }
                tryCondAddNext = prevNode2.tryCondAddNext(receive, g2, condAddOp);
                if (tryCondAddNext != 1) {
                }
            } while (tryCondAddNext != 2);
            return false;
        }
        LockFreeLinkedListNode g3 = g();
        do {
            prevNode = g3.getPrevNode();
            if (!(!(prevNode instanceof Send))) {
                return false;
            }
        } while (!prevNode.addNext(receive, g3));
        return true;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    @Nullable
    public E poll() {
        return (E) Channel.DefaultImpls.poll(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean q() {
        return g().getNextNode() instanceof ReceiveOrClosed;
    }

    protected abstract boolean r();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public final Object receive(@NotNull Continuation<? super E> continuation) {
        Object y = y();
        return (y == AbstractChannelKt.POLL_FAILED || (y instanceof Closed)) ? receiveSuspend(0, continuation) : y;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    /* renamed from: receiveCatching-JP2dKIU  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object mo1627receiveCatchingJP2dKIU(@NotNull Continuation<? super ChannelResult<? extends E>> continuation) {
        AbstractChannel$receiveCatching$1 abstractChannel$receiveCatching$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof AbstractChannel$receiveCatching$1) {
            abstractChannel$receiveCatching$1 = (AbstractChannel$receiveCatching$1) continuation;
            int i3 = abstractChannel$receiveCatching$1.f11317c;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                abstractChannel$receiveCatching$1.f11317c = i3 - Integer.MIN_VALUE;
                Object obj = abstractChannel$receiveCatching$1.f11315a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = abstractChannel$receiveCatching$1.f11317c;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Object y = y();
                    if (y != AbstractChannelKt.POLL_FAILED) {
                        return y instanceof Closed ? ChannelResult.Companion.m1647closedJP2dKIU(((Closed) y).closeCause) : ChannelResult.Companion.m1649successJP2dKIU(y);
                    }
                    abstractChannel$receiveCatching$1.f11317c = 1;
                    obj = receiveSuspend(1, abstractChannel$receiveCatching$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return ((ChannelResult) obj).m1646unboximpl();
            }
        }
        abstractChannel$receiveCatching$1 = new AbstractChannel$receiveCatching$1(this, continuation);
        Object obj2 = abstractChannel$receiveCatching$1.f11315a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = abstractChannel$receiveCatching$1.f11317c;
        if (i2 != 0) {
        }
        return ((ChannelResult) obj2).m1646unboximpl();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    @LowPriorityInOverloadResolution
    @Nullable
    public Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        return Channel.DefaultImpls.receiveOrNull(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean s();

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean t() {
        return !(g().getNextNode() instanceof Send) && s();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    public final Object mo1628tryReceivePtdJZtk() {
        Object y = y();
        return y == AbstractChannelKt.POLL_FAILED ? ChannelResult.Companion.m1648failurePtdJZtk() : y instanceof Closed ? ChannelResult.Companion.m1647closedJP2dKIU(((Closed) y).closeCause) : ChannelResult.Companion.m1649successJP2dKIU(y);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void u(boolean z) {
        Closed f2 = f();
        if (f2 == null) {
            throw new IllegalStateException("Cannot happen".toString());
        }
        Object m1665constructorimpl$default = InlineList.m1665constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = f2.getPrevNode();
            if (prevNode instanceof LockFreeLinkedListHead) {
                v(m1665constructorimpl$default, f2);
                return;
            } else if (DebugKt.getASSERTIONS_ENABLED() && !(prevNode instanceof Send)) {
                throw new AssertionError();
            } else {
                if (prevNode.remove()) {
                    m1665constructorimpl$default = InlineList.m1670plusFjFbRPM(m1665constructorimpl$default, (Send) prevNode);
                } else {
                    prevNode.helpRemove();
                }
            }
        }
    }

    protected void v(@NotNull Object obj, @NotNull Closed closed) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            ((Send) obj).resumeSendClosed(closed);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            ((Send) arrayList.get(size)).resumeSendClosed(closed);
        }
    }

    protected void w() {
    }

    protected void x() {
    }

    @Nullable
    protected Object y() {
        while (true) {
            Send n2 = n();
            if (n2 == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            Symbol tryResumeSend = n2.tryResumeSend(null);
            if (tryResumeSend != null) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                        throw new AssertionError();
                    }
                }
                n2.completeResumeSend();
                return n2.getPollResult();
            }
            n2.undeliveredElement();
        }
    }

    @Nullable
    protected Object z(@NotNull SelectInstance selectInstance) {
        TryPollDesc o2 = o();
        Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(o2);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        o2.getResult().completeResumeSend();
        return o2.getResult().getPollResult();
    }
}
