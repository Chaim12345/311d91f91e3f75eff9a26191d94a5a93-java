package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzz extends zze<SafetyNetApi.SafeBrowsingResult> {

    /* renamed from: d  reason: collision with root package name */
    protected final zzg f6602d;

    public zzz(GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.f6602d = new zzy(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return new zzac(status, null);
    }
}
