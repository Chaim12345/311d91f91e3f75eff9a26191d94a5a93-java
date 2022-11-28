package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes.dex */
final class zzea extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Activity f6039e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzed f6040f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzea(zzed zzedVar, Activity activity) {
        super(zzedVar.f6046a, true);
        this.f6040f = zzedVar;
        this.f6039e = activity;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6040f.f6046a.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivityStopped(ObjectWrapper.wrap(this.f6039e), this.f6027b);
    }
}
