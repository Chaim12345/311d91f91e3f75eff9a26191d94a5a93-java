package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
/* loaded from: classes2.dex */
final class zzs extends zzd {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzt f6595a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(zzt zztVar) {
        this.f6595a = zztVar;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzb(Status status, boolean z) {
        this.f6595a.setResult((zzt) new zzad(status, z));
    }
}
