package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
/* loaded from: classes2.dex */
final class zzw extends zzd {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzx f6599a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zzx zzxVar) {
        this.f6599a = zzxVar;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzh(Status status, com.google.android.gms.safetynet.zzf zzfVar) {
        this.f6599a.setResult((zzx) new zzab(status, zzfVar));
    }
}
