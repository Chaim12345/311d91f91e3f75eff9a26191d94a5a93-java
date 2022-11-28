package com.google.android.gms.internal.safetynet;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;
/* loaded from: classes2.dex */
final class zzp implements SafetyNetApi.AttestationResult {
    private final Status zza;
    @Nullable
    private final com.google.android.gms.safetynet.zza zzb;

    public zzp(Status status, @Nullable com.google.android.gms.safetynet.zza zzaVar) {
        this.zza = status;
        this.zzb = zzaVar;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.AttestationResult
    @Nullable
    public final String getJwsResult() {
        com.google.android.gms.safetynet.zza zzaVar = this.zzb;
        if (zzaVar == null) {
            return null;
        }
        return zzaVar.zza();
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
