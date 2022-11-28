package com.chuckerteam.chucker.internal.ui.transaction;

import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$Factory;", "invoke", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class TransactionPayloadFragment$viewModel$2 extends Lambda implements Function0<ViewModelProvider.Factory> {
    public static final TransactionPayloadFragment$viewModel$2 INSTANCE = new TransactionPayloadFragment$viewModel$2();

    TransactionPayloadFragment$viewModel$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final ViewModelProvider.Factory invoke() {
        return new TransactionViewModelFactory(0L, 1, null);
    }
}
