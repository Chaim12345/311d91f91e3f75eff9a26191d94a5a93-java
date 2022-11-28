package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzav extends zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzao f5868a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzbe zzbeVar, zzao zzaoVar) {
        this.f5868a = zzaoVar;
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzb(zzaa zzaaVar) {
        if (zzaaVar.getStatus().isSuccess()) {
            return;
        }
        this.f5868a.zzb(zzaaVar.getStatus(), null);
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzc() {
        this.f5868a.zzb(Status.RESULT_SUCCESS, null);
    }
}
