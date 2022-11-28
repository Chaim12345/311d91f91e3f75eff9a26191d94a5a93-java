package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
/* loaded from: classes.dex */
final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zaaw f5646a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zaat(zaaw zaawVar, zaas zaasVar) {
        this.f5646a = zaawVar;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        ClientSettings clientSettings;
        com.google.android.gms.signin.zae zaeVar;
        clientSettings = this.f5646a.zar;
        ClientSettings clientSettings2 = (ClientSettings) Preconditions.checkNotNull(clientSettings);
        zaeVar = this.f5646a.zak;
        ((com.google.android.gms.signin.zae) Preconditions.checkNotNull(zaeVar)).zad(new zaar(this.f5646a));
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        boolean zaI;
        lock = this.f5646a.zab;
        lock.lock();
        try {
            zaI = this.f5646a.zaI(connectionResult);
            if (zaI) {
                this.f5646a.zaA();
                this.f5646a.zaF();
            } else {
                this.f5646a.zaD(connectionResult);
            }
        } finally {
            lock2 = this.f5646a.zab;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
    }
}
