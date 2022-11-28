package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class GeoFenceSetTimeFragment$special$$inlined$activityViewModels$default$2 extends Lambda implements Function0<ViewModelProvider.Factory> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Fragment f10542a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeoFenceSetTimeFragment$special$$inlined$activityViewModels$default$2(Fragment fragment) {
        super(0);
        this.f10542a = fragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final ViewModelProvider.Factory invoke() {
        FragmentActivity requireActivity = this.f10542a.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        return requireActivity.getDefaultViewModelProviderFactory();
    }
}
