package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
/* loaded from: classes2.dex */
final class zzp implements zzhl {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f7054a;
    public final com.google.android.gms.internal.measurement.zzci zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzci zzciVar) {
        this.f7054a = appMeasurementDynamiteService;
        this.zza = zzciVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzhl
    public final void onEvent(String str, String str2, Bundle bundle, long j2) {
        try {
            this.zza.zze(str, str2, bundle, j2);
        } catch (RemoteException e2) {
            zzgk zzgkVar = this.f7054a.f6677a;
            if (zzgkVar != null) {
                zzgkVar.zzay().zzk().zzb("Event listener threw exception", e2);
            }
        }
    }
}
