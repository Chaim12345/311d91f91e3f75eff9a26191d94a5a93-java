package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes.dex */
final class zzd extends zzf {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ long f5890d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ PendingIntent f5891e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzd(zzg zzgVar, GoogleApiClient googleApiClient, long j2, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.f5890d = j2;
        this.f5891e = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        zzbe zzbeVar = (zzbe) anyClient;
        long j2 = this.f5890d;
        PendingIntent pendingIntent = this.f5891e;
        Preconditions.checkNotNull(pendingIntent);
        Preconditions.checkArgument(j2 >= 0, "detectionIntervalMillis must be >= 0");
        ((zzam) zzbeVar.getService()).zzr(j2, true, pendingIntent);
        setResult((zzd) Status.RESULT_SUCCESS);
    }
}
