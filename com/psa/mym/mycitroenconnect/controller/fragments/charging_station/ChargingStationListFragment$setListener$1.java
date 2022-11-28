package com.psa.mym.mycitroenconnect.controller.fragments.charging_station;

import androidx.appcompat.widget.AppCompatEditText;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ChargingStationListFragment$setListener$1 extends Lambda implements Function1<AppCompatEditText, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ChargingStationListFragment f10519a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargingStationListFragment$setListener$1(ChargingStationListFragment chargingStationListFragment) {
        super(1);
        this.f10519a = chargingStationListFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AppCompatEditText appCompatEditText) {
        invoke2(appCompatEditText);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull AppCompatEditText it) {
        MainActivity mainActivity;
        Intrinsics.checkNotNullParameter(it, "it");
        mainActivity = this.f10519a.parentActivity;
        if (mainActivity != null) {
            mainActivity.onBackPressed();
        }
    }
}
