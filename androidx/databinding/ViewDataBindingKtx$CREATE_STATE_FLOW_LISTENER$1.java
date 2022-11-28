package androidx.databinding;

import androidx.databinding.ViewDataBindingKtx;
import java.lang.ref.ReferenceQueue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u000b\u001a\u001c\u0012\f\u0012\n \u0001*\u0004\u0018\u00010\b0\b \u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u00070\u00072\u000e\u0010\u0002\u001a\n \u0001*\u0004\u0018\u00010\u00000\u00002\u0006\u0010\u0004\u001a\u00020\u00032*\u0010\u0006\u001a&\u0012\f\u0012\n \u0001*\u0004\u0018\u00010\u00000\u0000 \u0001*\u0012\u0012\f\u0012\n \u0001*\u0004\u0018\u00010\u00000\u0000\u0018\u00010\u00050\u0005H\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"Landroidx/databinding/ViewDataBinding;", "kotlin.jvm.PlatformType", "viewDataBinding", "", "localFieldId", "Ljava/lang/ref/ReferenceQueue;", "referenceQueue", "Landroidx/databinding/WeakListener;", "", "create", "(Landroidx/databinding/ViewDataBinding;ILjava/lang/ref/ReferenceQueue;)Landroidx/databinding/WeakListener;", "<anonymous>"}, k = 3, mv = {1, 4, 2})
/* loaded from: classes.dex */
final class ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1 implements CreateWeakListener {
    public static final ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1 INSTANCE = new ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1();

    ViewDataBindingKtx$CREATE_STATE_FLOW_LISTENER$1() {
    }

    @Override // androidx.databinding.CreateWeakListener
    public final WeakListener<Object> create(ViewDataBinding viewDataBinding, int i2, ReferenceQueue<ViewDataBinding> referenceQueue) {
        Intrinsics.checkNotNullExpressionValue(referenceQueue, "referenceQueue");
        return new ViewDataBindingKtx.StateFlowListener(viewDataBinding, i2, referenceQueue).getListener();
    }
}
