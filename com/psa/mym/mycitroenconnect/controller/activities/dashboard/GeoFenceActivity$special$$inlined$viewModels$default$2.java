package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelStore;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GeoFenceActivity$special$$inlined$viewModels$default$2 extends Lambda implements Function0<ViewModelStore> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ComponentActivity f10374a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeoFenceActivity$special$$inlined$viewModels$default$2(ComponentActivity componentActivity) {
        super(0);
        this.f10374a = componentActivity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final ViewModelStore invoke() {
        ViewModelStore viewModelStore = this.f10374a.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
        return viewModelStore;
    }
}
