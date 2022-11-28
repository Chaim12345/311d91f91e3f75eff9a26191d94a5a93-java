package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
/* loaded from: classes2.dex */
final class zzq extends zzd {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzr f6593a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(zzr zzrVar) {
        this.f6593a = zzrVar;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzd(Status status, com.google.android.gms.safetynet.zza zzaVar) {
        this.f6593a.setResult((zzr) new zzp(status, zzaVar));
    }
}
