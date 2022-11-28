package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhz implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bundle f6845a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6846b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhz(zzip zzipVar, Bundle bundle) {
        this.f6846b = zzipVar;
        this.f6845a = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzip zzipVar = this.f6846b;
        Bundle bundle = this.f6845a;
        zzipVar.zzg();
        zzipVar.zza();
        Preconditions.checkNotNull(bundle);
        String checkNotEmpty = Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        if (!zzipVar.f6809a.zzJ()) {
            zzipVar.f6809a.zzay().zzj().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        try {
            zzipVar.f6809a.zzt().f(new zzac(bundle.getString("app_id"), "", new zzlo(checkNotEmpty, 0L, null, ""), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzipVar.f6809a.zzv().R(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), "", bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true)));
        } catch (IllegalArgumentException unused) {
        }
    }
}
