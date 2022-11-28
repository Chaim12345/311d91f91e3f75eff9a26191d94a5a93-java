package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes.dex */
final class zzs extends zzw {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ LocationRequest f5910d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ LocationListener f5911e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Looper f5912f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzz zzzVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        super(googleApiClient);
        this.f5910d = locationRequest;
        this.f5911e = locationListener;
        this.f5912f = looper;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzbe) anyClient).zzC(zzbf.zzc(null, this.f5910d), ListenerHolders.createListenerHolder(this.f5911e, zzbn.zza(this.f5912f), LocationListener.class.getSimpleName()), new zzx(this));
    }
}
