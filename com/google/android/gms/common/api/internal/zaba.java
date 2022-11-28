package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
final class zaba implements GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StatusPendingResult f5652a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaba(zabe zabeVar, StatusPendingResult statusPendingResult) {
        this.f5652a = statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.f5652a.setResult(new Status(8));
    }
}
