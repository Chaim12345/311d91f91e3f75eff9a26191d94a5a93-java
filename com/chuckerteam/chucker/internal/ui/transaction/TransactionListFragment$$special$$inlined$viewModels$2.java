package com.chuckerteam.chucker.internal.ui.transaction;

import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u0002\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/ViewModel;", "VM", "Landroidx/lifecycle/ViewModelStore;", "invoke", "()Landroidx/lifecycle/ViewModelStore;", "androidx/fragment/app/FragmentViewModelLazyKt$viewModels$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionListFragment$$special$$inlined$viewModels$2 extends Lambda implements Function0<ViewModelStore> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function0 f4971a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionListFragment$$special$$inlined$viewModels$2(Function0 function0) {
        super(0);
        this.f4971a = function0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final ViewModelStore invoke() {
        ViewModelStore viewModelStore = ((ViewModelStoreOwner) this.f4971a.invoke()).getViewModelStore();
        Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
        return viewModelStore;
    }
}
