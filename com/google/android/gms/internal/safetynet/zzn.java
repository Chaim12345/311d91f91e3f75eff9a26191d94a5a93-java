package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes2.dex */
final class zzn extends zzv {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzae zzaeVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzh) ((zzaf) anyClient).getService()).zzg(this.f6598d);
    }
}
