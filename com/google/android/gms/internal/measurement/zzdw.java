package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes.dex */
final class zzdw extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Bundle f6030e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Activity f6031f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzed f6032g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdw(zzed zzedVar, Bundle bundle, Activity activity) {
        super(zzedVar.f6046a, true);
        this.f6032g = zzedVar;
        this.f6030e = bundle;
        this.f6031f = activity;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        Bundle bundle;
        zzcc zzccVar;
        if (this.f6030e != null) {
            bundle = new Bundle();
            if (this.f6030e.containsKey("com.google.app_measurement.screen_service")) {
                Object obj = this.f6030e.get("com.google.app_measurement.screen_service");
                if (obj instanceof Bundle) {
                    bundle.putBundle("com.google.app_measurement.screen_service", (Bundle) obj);
                }
            }
        } else {
            bundle = null;
        }
        zzccVar = this.f6032g.f6046a.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivityCreated(ObjectWrapper.wrap(this.f6031f), bundle, this.f6027b);
    }
}
