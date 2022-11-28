package androidx.room;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "androidx.room.RoomDatabaseKt$withTransaction$2", f = "RoomDatabase.kt", i = {0, 0}, l = {58}, m = "invokeSuspend", n = {"$this$withContext", "transactionElement"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class RoomDatabaseKt$withTransaction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f3930a;

    /* renamed from: b  reason: collision with root package name */
    Object f3931b;

    /* renamed from: c  reason: collision with root package name */
    int f3932c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ RoomDatabase f3933d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Function1 f3934e;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoomDatabaseKt$withTransaction$2(RoomDatabase roomDatabase, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.f3933d = roomDatabase;
        this.f3934e = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RoomDatabaseKt$withTransaction$2 roomDatabaseKt$withTransaction$2 = new RoomDatabaseKt$withTransaction$2(this.f3933d, this.f3934e, completion);
        roomDatabaseKt$withTransaction$2.p$ = (CoroutineScope) obj;
        return roomDatabaseKt$withTransaction$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Object obj) {
        return ((RoomDatabaseKt$withTransaction$2) create(coroutineScope, (Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        TransactionElement transactionElement;
        TransactionElement transactionElement2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f3932c;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(TransactionElement.Key);
                if (element == null) {
                    Intrinsics.throwNpe();
                }
                transactionElement = (TransactionElement) element;
                transactionElement.acquire();
                try {
                    this.f3933d.beginTransaction();
                    try {
                        Function1 function1 = this.f3934e;
                        this.f3930a = coroutineScope;
                        this.f3931b = transactionElement;
                        this.f3932c = 1;
                        obj = function1.invoke(this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        transactionElement2 = transactionElement;
                    } catch (Throwable th) {
                        th = th;
                        this.f3933d.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    transactionElement.release();
                    throw th;
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                transactionElement2 = (TransactionElement) this.f3931b;
                CoroutineScope coroutineScope2 = (CoroutineScope) this.f3930a;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th3) {
                    th = th3;
                    this.f3933d.endTransaction();
                    throw th;
                }
            }
            this.f3933d.setTransactionSuccessful();
            this.f3933d.endTransaction();
            transactionElement2.release();
            return obj;
        } catch (Throwable th4) {
            th = th4;
            transactionElement = coroutine_suspended;
        }
    }
}
