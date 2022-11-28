package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes.dex */
final class zzr extends zzw {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ LocationRequest f5908d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ LocationListener f5909e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzr(zzz zzzVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
        super(googleApiClient);
        this.f5908d = locationRequest;
        this.f5909e = locationListener;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzbe) anyClient).zzC(zzbf.zzc(null, this.f5908d), ListenerHolders.createListenerHolder(this.f5909e, zzbn.zzb(), LocationListener.class.getSimpleName()), new zzx(this));
    }
}
