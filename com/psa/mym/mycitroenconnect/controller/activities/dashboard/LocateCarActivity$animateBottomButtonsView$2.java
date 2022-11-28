package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.psa.mym.mycitroenconnect.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class LocateCarActivity$animateBottomButtonsView$2 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ LocateCarActivity f10376a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocateCarActivity$animateBottomButtonsView$2(LocateCarActivity locateCarActivity) {
        super(0);
        this.f10376a = locateCarActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((ConstraintLayout) this.f10376a._$_findCachedViewById(R.id.animationView)).setVisibility(8);
    }
}
