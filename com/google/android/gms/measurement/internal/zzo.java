package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzo implements zzhk {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f7053a;
    public final com.google.android.gms.internal.measurement.zzci zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzci zzciVar) {
        this.f7053a = appMeasurementDynamiteService;
        this.zza = zzciVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzhk
    public final void interceptEvent(String str, String str2, Bundle bundle, long j2) {
        try {
            this.zza.zze(str, str2, bundle, j2);
        } catch (RemoteException e2) {
            zzgk zzgkVar = this.f7053a.f6677a;
            if (zzgkVar != null) {
                zzgkVar.zzay().zzk().zzb("Event interceptor threw exception", e2);
            }
        }
    }
}
