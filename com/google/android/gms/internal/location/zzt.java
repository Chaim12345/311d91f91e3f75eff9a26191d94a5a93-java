package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes.dex */
final class zzt extends zzw {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ LocationRequest f5913d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ LocationCallback f5914e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Looper f5915f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzt(zzz zzzVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        super(googleApiClient);
        this.f5913d = locationRequest;
        this.f5914e = locationCallback;
        this.f5915f = looper;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzbe) anyClient).zzB(zzbf.zzc(null, this.f5913d), ListenerHolders.createListenerHolder(this.f5914e, zzbn.zza(this.f5915f), LocationCallback.class.getSimpleName()), new zzx(this));
    }
}
