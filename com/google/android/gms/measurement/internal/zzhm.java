package com.google.android.gms.measurement.internal;

import android.util.Log;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhm implements zzeu {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzgk f6810a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhm(zzhn zzhnVar, zzgk zzgkVar) {
        this.f6810a = zzgkVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzeu
    public final boolean zza() {
        return this.f6810a.zzL() && Log.isLoggable(this.f6810a.zzay().zzq(), 3);
    }
}
