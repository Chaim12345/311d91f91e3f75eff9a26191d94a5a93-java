package com.chuckerteam.chucker.internal.ui.transaction;

import android.content.Intent;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.support.SharableKt;
import com.chuckerteam.chucker.internal.support.TransactionListDetailsSharable;
import com.chuckerteam.chucker.internal.ui.MainViewModel;
import java.util.List;
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
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.ui.transaction.TransactionListFragment$exportTransactions$1", f = "TransactionListFragment.kt", i = {0, 1, 1, 1}, l = {127, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "transactions", "sharableTransactions"}, s = {"L$0", "L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
public final class TransactionListFragment$exportTransactions$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f4972a;

    /* renamed from: b  reason: collision with root package name */
    Object f4973b;

    /* renamed from: c  reason: collision with root package name */
    Object f4974c;

    /* renamed from: d  reason: collision with root package name */
    int f4975d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ TransactionListFragment f4976e;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionListFragment$exportTransactions$1(TransactionListFragment transactionListFragment, Continuation continuation) {
        super(2, continuation);
        this.f4976e = transactionListFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        TransactionListFragment$exportTransactions$1 transactionListFragment$exportTransactions$1 = new TransactionListFragment$exportTransactions$1(this.f4976e, completion);
        transactionListFragment$exportTransactions$1.p$ = (CoroutineScope) obj;
        return transactionListFragment$exportTransactions$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TransactionListFragment$exportTransactions$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ae  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        CoroutineScope coroutineScope;
        MainViewModel viewModel;
        Intent intent;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4975d;
        boolean z = true;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.p$;
            viewModel = this.f4976e.getViewModel();
            this.f4972a = coroutineScope;
            this.f4975d = 1;
            obj = viewModel.getAllTransactions(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            if (i2 == 2) {
                TransactionListDetailsSharable transactionListDetailsSharable = (TransactionListDetailsSharable) this.f4974c;
                List list = (List) this.f4973b;
                CoroutineScope coroutineScope2 = (CoroutineScope) this.f4972a;
                ResultKt.throwOnFailure(obj);
                intent = (Intent) obj;
                if (intent != null) {
                    this.f4976e.startActivity(intent);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            coroutineScope = (CoroutineScope) this.f4972a;
            ResultKt.throwOnFailure(obj);
        }
        List list2 = (List) obj;
        if (list2 != null && !list2.isEmpty()) {
            z = false;
        }
        if (z) {
            Toast.makeText(this.f4976e.requireContext(), R.string.chucker_export_empty_text, 0).show();
            return Unit.INSTANCE;
        }
        TransactionListDetailsSharable transactionListDetailsSharable2 = new TransactionListDetailsSharable(list2, false);
        FragmentActivity requireActivity = this.f4976e.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = this.f4976e.getString(R.string.chucker_share_all_transactions_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.chuck…e_all_transactions_title)");
        String string2 = this.f4976e.getString(R.string.chucker_share_all_transactions_subject);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.chuck…all_transactions_subject)");
        this.f4972a = coroutineScope;
        this.f4973b = list2;
        this.f4974c = transactionListDetailsSharable2;
        this.f4975d = 2;
        obj = SharableKt.shareAsFile(transactionListDetailsSharable2, requireActivity, "transactions.txt", string, string2, "transactions", this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        intent = (Intent) obj;
        if (intent != null) {
        }
        return Unit.INSTANCE;
    }
}
