package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GeoFenceActivity$special$$inlined$viewModels$default$1 extends Lambda implements Function0<ViewModelProvider.Factory> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ComponentActivity f10373a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeoFenceActivity$special$$inlined$viewModels$default$1(ComponentActivity componentActivity) {
        super(0);
        this.f10373a = componentActivity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final ViewModelProvider.Factory invoke() {
        return this.f10373a.getDefaultViewModelProviderFactory();
    }
}
