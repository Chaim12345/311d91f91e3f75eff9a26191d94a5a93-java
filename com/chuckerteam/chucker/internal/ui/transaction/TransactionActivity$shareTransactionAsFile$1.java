package com.chuckerteam.chucker.internal.ui.transaction;

import android.content.Intent;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.support.Sharable;
import com.chuckerteam.chucker.internal.support.SharableKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.ui.transaction.TransactionActivity$shareTransactionAsFile$1", f = "TransactionActivity.kt", i = {0}, l = {122}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes.dex */
public final class TransactionActivity$shareTransactionAsFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f4960a;

    /* renamed from: b  reason: collision with root package name */
    int f4961b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ TransactionActivity f4962c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Sharable f4963d;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionActivity$shareTransactionAsFile$1(TransactionActivity transactionActivity, Sharable sharable, Continuation continuation) {
        super(2, continuation);
        this.f4962c = transactionActivity;
        this.f4963d = sharable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        TransactionActivity$shareTransactionAsFile$1 transactionActivity$shareTransactionAsFile$1 = new TransactionActivity$shareTransactionAsFile$1(this.f4962c, this.f4963d, completion);
        transactionActivity$shareTransactionAsFile$1.p$ = (CoroutineScope) obj;
        return transactionActivity$shareTransactionAsFile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TransactionActivity$shareTransactionAsFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4961b;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            Sharable sharable = this.f4963d;
            TransactionActivity transactionActivity = this.f4962c;
            String string = transactionActivity.getString(R.string.chucker_share_transaction_title);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.chuck…_share_transaction_title)");
            String string2 = this.f4962c.getString(R.string.chucker_share_transaction_subject);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.chuck…hare_transaction_subject)");
            this.f4960a = coroutineScope;
            this.f4961b = 1;
            obj = SharableKt.shareAsFile(sharable, transactionActivity, "transaction.txt", string, string2, "transaction", this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.f4960a;
            ResultKt.throwOnFailure(obj);
        }
        Intent intent = (Intent) obj;
        if (intent != null) {
            this.f4962c.startActivity(intent);
        }
        return Unit.INSTANCE;
    }
}
