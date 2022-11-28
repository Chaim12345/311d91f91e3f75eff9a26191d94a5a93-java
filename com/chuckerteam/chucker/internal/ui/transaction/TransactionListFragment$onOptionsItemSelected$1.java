package com.chuckerteam.chucker.internal.ui.transaction;

import com.chuckerteam.chucker.internal.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class TransactionListFragment$onOptionsItemSelected$1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TransactionListFragment f4977a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionListFragment$onOptionsItemSelected$1(TransactionListFragment transactionListFragment) {
        super(0);
        this.f4977a = transactionListFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        MainViewModel viewModel;
        viewModel = this.f4977a.getViewModel();
        viewModel.clearTransactions();
    }
}
