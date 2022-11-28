package com.psa.mym.mycitroenconnect.controller.fragments.my_trips;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class TripSummaryFragment$animateRefreshButton$1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TripSummaryFragment f10572a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TripSummaryFragment$animateRefreshButton$1(TripSummaryFragment tripSummaryFragment) {
        super(0);
        this.f10572a = tripSummaryFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.f10572a.getTripList();
    }
}
