package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafeBrowsingData;
/* loaded from: classes2.dex */
final class zzy extends zzd {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzz f6601a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(zzz zzzVar) {
        this.f6601a = zzzVar;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzj(Status status, SafeBrowsingData safeBrowsingData) {
        this.f6601a.setResult((zzz) new zzac(status, safeBrowsingData));
    }
}
