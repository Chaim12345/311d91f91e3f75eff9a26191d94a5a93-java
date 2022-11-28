package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes2.dex */
final class zzo extends zzx {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f6592e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzo(zzae zzaeVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.f6592e = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzh) ((zzaf) anyClient).getService()).zzj(this.f6600d, this.f6592e);
    }
}
