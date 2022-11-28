package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzt extends zze<SafetyNetApi.VerifyAppsUserResult> {

    /* renamed from: d  reason: collision with root package name */
    protected final zzg f6596d;

    public zzt(GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.f6596d = new zzs(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return new zzad(status, false);
    }
}
