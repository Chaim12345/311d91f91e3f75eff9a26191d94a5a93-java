package androidx.room;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.ThreadContextElementKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a;\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u001c\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"R", "Landroidx/room/RoomDatabase;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "block", "withTransaction", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-ktx_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class RoomDatabaseKt {
    @Nullable
    static final /* synthetic */ Object a(@NotNull final Executor executor, @NotNull final Job job, @NotNull Continuation continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        cancellableContinuationImpl.invokeOnCancellation(new RoomDatabaseKt$acquireTransactionThread$$inlined$suspendCancellableCoroutine$lambda$1(executor, job));
        try {
            executor.execute(new Runnable() { // from class: androidx.room.RoomDatabaseKt$acquireTransactionThread$$inlined$suspendCancellableCoroutine$lambda$2

                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/room/RoomDatabaseKt$acquireTransactionThread$2$2$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
                /* renamed from: androidx.room.RoomDatabaseKt$acquireTransactionThread$$inlined$suspendCancellableCoroutine$lambda$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                    /* renamed from: a  reason: collision with root package name */
                    Object f3917a;

                    /* renamed from: b  reason: collision with root package name */
                    int f3918b;
                    private CoroutineScope p$;

                    AnonymousClass1(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                        anonymousClass1.p$ = (CoroutineScope) obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended;
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i2 = this.f3918b;
                        if (i2 == 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.p$;
                            CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                            CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(ContinuationInterceptor.Key);
                            if (element == null) {
                                Intrinsics.throwNpe();
                            }
                            Result.Companion companion = Result.Companion;
                            cancellableContinuation.resumeWith(Result.m187constructorimpl(element));
                            Job job = job;
                            this.f3917a = coroutineScope;
                            this.f3918b = 1;
                            if (job.join(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i2 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            CoroutineScope coroutineScope2 = (CoroutineScope) this.f3917a;
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // java.lang.Runnable
                public final void run() {
                    BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(null), 1, null);
                }
            });
        } catch (RejectedExecutionException e2) {
            cancellableContinuationImpl.cancel(new IllegalStateException("Unable to acquire a thread to perform the database transaction.", e2));
        }
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object b(@NotNull RoomDatabase roomDatabase, @NotNull Continuation continuation) {
        RoomDatabaseKt$createTransactionContext$1 roomDatabaseKt$createTransactionContext$1;
        Object coroutine_suspended;
        int i2;
        CompletableJob Job$default;
        RoomDatabase roomDatabase2;
        CompletableJob completableJob;
        if (continuation instanceof RoomDatabaseKt$createTransactionContext$1) {
            roomDatabaseKt$createTransactionContext$1 = (RoomDatabaseKt$createTransactionContext$1) continuation;
            int i3 = roomDatabaseKt$createTransactionContext$1.f3921b;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                roomDatabaseKt$createTransactionContext$1.f3921b = i3 - Integer.MIN_VALUE;
                Object obj = roomDatabaseKt$createTransactionContext$1.f3920a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = roomDatabaseKt$createTransactionContext$1.f3921b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
                    Job job = (Job) roomDatabaseKt$createTransactionContext$1.getContext().get(Job.Key);
                    if (job != null) {
                        job.invokeOnCompletion(new RoomDatabaseKt$createTransactionContext$2(Job$default));
                    }
                    Executor transactionExecutor = roomDatabase.getTransactionExecutor();
                    Intrinsics.checkExpressionValueIsNotNull(transactionExecutor, "transactionExecutor");
                    roomDatabaseKt$createTransactionContext$1.f3922c = roomDatabase;
                    roomDatabaseKt$createTransactionContext$1.f3923d = Job$default;
                    roomDatabaseKt$createTransactionContext$1.f3921b = 1;
                    Object a2 = a(transactionExecutor, Job$default, roomDatabaseKt$createTransactionContext$1);
                    if (a2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    roomDatabase2 = roomDatabase;
                    completableJob = Job$default;
                    obj = a2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    completableJob = (CompletableJob) roomDatabaseKt$createTransactionContext$1.f3923d;
                    roomDatabase2 = (RoomDatabase) roomDatabaseKt$createTransactionContext$1.f3922c;
                    ResultKt.throwOnFailure(obj);
                }
                ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) obj;
                TransactionElement transactionElement = new TransactionElement(completableJob, continuationInterceptor);
                ThreadLocal suspendingTransactionId = roomDatabase2.e();
                Intrinsics.checkExpressionValueIsNotNull(suspendingTransactionId, "suspendingTransactionId");
                return continuationInterceptor.plus(transactionElement).plus(ThreadContextElementKt.asContextElement(suspendingTransactionId, Boxing.boxInt(System.identityHashCode(completableJob))));
            }
        }
        roomDatabaseKt$createTransactionContext$1 = new RoomDatabaseKt$createTransactionContext$1(continuation);
        Object obj2 = roomDatabaseKt$createTransactionContext$1.f3920a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = roomDatabaseKt$createTransactionContext$1.f3921b;
        if (i2 != 0) {
        }
        ContinuationInterceptor continuationInterceptor2 = (ContinuationInterceptor) obj2;
        TransactionElement transactionElement2 = new TransactionElement(completableJob, continuationInterceptor2);
        ThreadLocal suspendingTransactionId2 = roomDatabase2.e();
        Intrinsics.checkExpressionValueIsNotNull(suspendingTransactionId2, "suspendingTransactionId");
        return continuationInterceptor2.plus(transactionElement2).plus(ThreadContextElementKt.asContextElement(suspendingTransactionId2, Boxing.boxInt(System.identityHashCode(completableJob))));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0088 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0089 A[PHI: r7 
      PHI: (r7v11 java.lang.Object) = (r7v8 java.lang.Object), (r7v1 java.lang.Object) binds: [B:26:0x0086, B:12:0x0028] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <R> Object withTransaction(@NotNull RoomDatabase roomDatabase, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1, @NotNull Continuation<? super R> continuation) {
        RoomDatabaseKt$withTransaction$1 roomDatabaseKt$withTransaction$1;
        Object obj;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof RoomDatabaseKt$withTransaction$1) {
            roomDatabaseKt$withTransaction$1 = (RoomDatabaseKt$withTransaction$1) continuation;
            int i3 = roomDatabaseKt$withTransaction$1.f3926b;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                roomDatabaseKt$withTransaction$1.f3926b = i3 - Integer.MIN_VALUE;
                obj = roomDatabaseKt$withTransaction$1.f3925a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = roomDatabaseKt$withTransaction$1.f3926b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    TransactionElement transactionElement = (TransactionElement) roomDatabaseKt$withTransaction$1.getContext().get(TransactionElement.Key);
                    if (transactionElement == null || (r7 = transactionElement.getTransactionDispatcher$room_ktx_release()) == null) {
                        roomDatabaseKt$withTransaction$1.f3927c = roomDatabase;
                        roomDatabaseKt$withTransaction$1.f3928d = function1;
                        roomDatabaseKt$withTransaction$1.f3926b = 1;
                        obj = b(roomDatabase, roomDatabaseKt$withTransaction$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    RoomDatabaseKt$withTransaction$2 roomDatabaseKt$withTransaction$2 = new RoomDatabaseKt$withTransaction$2(roomDatabase, function1, null);
                    roomDatabaseKt$withTransaction$1.f3927c = roomDatabase;
                    roomDatabaseKt$withTransaction$1.f3928d = function1;
                    roomDatabaseKt$withTransaction$1.f3929e = r7;
                    roomDatabaseKt$withTransaction$1.f3926b = 2;
                    obj = BuildersKt.withContext(r7, roomDatabaseKt$withTransaction$2, roomDatabaseKt$withTransaction$1);
                    return obj == coroutine_suspended ? coroutine_suspended : obj;
                } else if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    CoroutineContext coroutineContext = (CoroutineContext) roomDatabaseKt$withTransaction$1.f3929e;
                    Function1 function12 = (Function1) roomDatabaseKt$withTransaction$1.f3928d;
                    RoomDatabase roomDatabase2 = (RoomDatabase) roomDatabaseKt$withTransaction$1.f3927c;
                    ResultKt.throwOnFailure(obj);
                } else {
                    function1 = (Function1) roomDatabaseKt$withTransaction$1.f3928d;
                    roomDatabase = (RoomDatabase) roomDatabaseKt$withTransaction$1.f3927c;
                    ResultKt.throwOnFailure(obj);
                }
                CoroutineContext transactionDispatcher$room_ktx_release = (CoroutineContext) obj;
                RoomDatabaseKt$withTransaction$2 roomDatabaseKt$withTransaction$22 = new RoomDatabaseKt$withTransaction$2(roomDatabase, function1, null);
                roomDatabaseKt$withTransaction$1.f3927c = roomDatabase;
                roomDatabaseKt$withTransaction$1.f3928d = function1;
                roomDatabaseKt$withTransaction$1.f3929e = transactionDispatcher$room_ktx_release;
                roomDatabaseKt$withTransaction$1.f3926b = 2;
                obj = BuildersKt.withContext(transactionDispatcher$room_ktx_release, roomDatabaseKt$withTransaction$22, roomDatabaseKt$withTransaction$1);
                if (obj == coroutine_suspended) {
                }
            }
        }
        roomDatabaseKt$withTransaction$1 = new RoomDatabaseKt$withTransaction$1(continuation);
        obj = roomDatabaseKt$withTransaction$1.f3925a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = roomDatabaseKt$withTransaction$1.f3926b;
        if (i2 != 0) {
        }
        CoroutineContext transactionDispatcher$room_ktx_release2 = (CoroutineContext) obj;
        RoomDatabaseKt$withTransaction$2 roomDatabaseKt$withTransaction$222 = new RoomDatabaseKt$withTransaction$2(roomDatabase, function1, null);
        roomDatabaseKt$withTransaction$1.f3927c = roomDatabase;
        roomDatabaseKt$withTransaction$1.f3928d = function1;
        roomDatabaseKt$withTransaction$1.f3929e = transactionDispatcher$room_ktx_release2;
        roomDatabaseKt$withTransaction$1.f3926b = 2;
        obj = BuildersKt.withContext(transactionDispatcher$room_ktx_release2, roomDatabaseKt$withTransaction$222, roomDatabaseKt$withTransaction$1);
        if (obj == coroutine_suspended) {
        }
    }
}
