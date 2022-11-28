package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
/* loaded from: classes2.dex */
final class zzu extends zzd {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzv f6597a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(zzv zzvVar) {
        this.f6597a = zzvVar;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzg(Status status, com.google.android.gms.safetynet.zzd zzdVar) {
        this.f6597a.setResult((zzv) new zzaa(status, zzdVar));
    }
}
