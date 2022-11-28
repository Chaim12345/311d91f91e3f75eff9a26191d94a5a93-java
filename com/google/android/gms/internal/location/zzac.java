package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingRequest;
/* loaded from: classes.dex */
final class zzac extends zzae {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ GeofencingRequest f5863d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ PendingIntent f5864e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzac(zzaf zzafVar, GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.f5863d = geofencingRequest;
        this.f5864e = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzbe) anyClient).zzq(this.f5863d, this.f5864e, this);
    }
}
