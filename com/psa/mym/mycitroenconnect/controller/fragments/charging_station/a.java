package com.psa.mym.mycitroenconnect.controller.fragments.charging_station;

import android.location.Location;
import androidx.lifecycle.Observer;
/* loaded from: classes3.dex */
public final /* synthetic */ class a implements Observer {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f10520a = new a();

    private /* synthetic */ a() {
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        ChargingStationFragment.m137initializeLocation$lambda6((Location) obj);
    }
}
