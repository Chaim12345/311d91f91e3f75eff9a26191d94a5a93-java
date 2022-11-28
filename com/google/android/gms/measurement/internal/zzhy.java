package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhy implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bundle f6843a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6844b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhy(zzip zzipVar, Bundle bundle) {
        this.f6844b = zzipVar;
        this.f6843a = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzip zzipVar = this.f6844b;
        Bundle bundle = this.f6843a;
        zzipVar.zzg();
        zzipVar.zza();
        Preconditions.checkNotNull(bundle);
        String string = bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        String string2 = bundle.getString("origin");
        Preconditions.checkNotEmpty(string);
        Preconditions.checkNotEmpty(string2);
        Preconditions.checkNotNull(bundle.get("value"));
        if (!zzipVar.f6809a.zzJ()) {
            zzipVar.f6809a.zzay().zzj().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzlo zzloVar = new zzlo(string, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), string2);
        try {
            zzaw R = zzipVar.f6809a.zzv().R(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), string2, 0L, true, true);
            zzipVar.f6809a.zzt().f(new zzac(bundle.getString("app_id"), string2, zzloVar, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zzipVar.f6809a.zzv().R(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), string2, 0L, true, true), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), R, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzipVar.f6809a.zzv().R(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), string2, 0L, true, true)));
        } catch (IllegalArgumentException unused) {
        }
    }
}
