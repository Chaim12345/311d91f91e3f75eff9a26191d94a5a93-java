package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzx extends zze<SafetyNetApi.RecaptchaTokenResult> {

    /* renamed from: d  reason: collision with root package name */
    protected final zzg f6600d;

    public zzx(GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.f6600d = new zzw(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return new zzab(status, null);
    }
}
