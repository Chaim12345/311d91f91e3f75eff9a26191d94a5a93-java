package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes.dex */
final class zzu extends zzw {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ LocationRequest f5916d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ PendingIntent f5917e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzu(zzz zzzVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.f5916d = locationRequest;
        this.f5917e = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzbe) anyClient).zzD(zzbf.zzc(null, this.f5916d), this.f5917e, new zzx(this));
    }
}
