package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzad extends zzae {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.location.zzbx f5865d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzad(zzaf zzafVar, GoogleApiClient googleApiClient, com.google.android.gms.location.zzbx zzbxVar) {
        super(googleApiClient);
        this.f5865d = zzbxVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void b(Api.AnyClient anyClient) {
        com.google.android.gms.location.zzbx zzbxVar = this.f5865d;
        Preconditions.checkNotNull(zzbxVar, "removeGeofencingRequest can't be null.");
        Preconditions.checkNotNull(this, "ResultHolder not provided.");
        ((zzam) ((zzbe) anyClient).getService()).zzm(zzbxVar, new zzba(this));
    }
}
