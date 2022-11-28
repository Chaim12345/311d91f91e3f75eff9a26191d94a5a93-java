package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnTokenCanceledListener;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzau extends LocationCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzao f5866a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ OnTokenCanceledListener f5867b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzau(zzbe zzbeVar, zzao zzaoVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.f5866a = zzaoVar;
        this.f5867b = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationAvailability(LocationAvailability locationAvailability) {
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationResult(LocationResult locationResult) {
        try {
            this.f5866a.zzb(Status.RESULT_SUCCESS, locationResult.getLastLocation());
            this.f5867b.onCanceled();
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }
}
