package com.chuckerteam.chucker.internal.ui.transaction;

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.support.Sharable;
import com.chuckerteam.chucker.internal.support.TransactionDetailsSharable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "Lcom/chuckerteam/chucker/internal/support/Sharable;", "invoke", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;)Lcom/chuckerteam/chucker/internal/support/Sharable;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class TransactionActivity$onOptionsItemSelected$1 extends Lambda implements Function1<HttpTransaction, Sharable> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TransactionActivity f4956a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionActivity$onOptionsItemSelected$1(TransactionActivity transactionActivity) {
        super(1);
        this.f4956a = transactionActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Sharable invoke(@NotNull HttpTransaction transaction) {
        TransactionViewModel viewModel;
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        viewModel = this.f4956a.getViewModel();
        Boolean value = viewModel.getEncodeUrl().getValue();
        Intrinsics.checkNotNull(value);
        Intrinsics.checkNotNullExpressionValue(value, "viewModel.encodeUrl.value!!");
        return new TransactionDetailsSharable(transaction, value.booleanValue());
    }
}
