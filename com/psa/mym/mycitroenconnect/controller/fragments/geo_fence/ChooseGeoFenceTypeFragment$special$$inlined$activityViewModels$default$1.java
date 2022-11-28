package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelStore;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ChooseGeoFenceTypeFragment$special$$inlined$activityViewModels$default$1 extends Lambda implements Function0<ViewModelStore> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Fragment f10527a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChooseGeoFenceTypeFragment$special$$inlined$activityViewModels$default$1(Fragment fragment) {
        super(0);
        this.f10527a = fragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final ViewModelStore invoke() {
        FragmentActivity requireActivity = this.f10527a.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ViewModelStore viewModelStore = requireActivity.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
        return viewModelStore;
    }
}
