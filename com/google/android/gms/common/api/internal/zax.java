package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zax implements zabz {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zaaa f5713a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zax(zaaa zaaaVar, zaw zawVar) {
        this.f5713a = zaaaVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zaa(@NonNull ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        lock = this.f5713a.zam;
        lock.lock();
        try {
            this.f5713a.zaj = connectionResult;
            zaaa.j(this.f5713a);
        } finally {
            lock2 = this.f5713a.zam;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zab(@Nullable Bundle bundle) {
        Lock lock;
        Lock lock2;
        lock = this.f5713a.zam;
        lock.lock();
        try {
            zaaa.i(this.f5713a, bundle);
            this.f5713a.zaj = ConnectionResult.RESULT_SUCCESS;
            zaaa.j(this.f5713a);
        } finally {
            lock2 = this.f5713a.zam;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zac(int i2, boolean z) {
        Lock lock;
        Lock lock2;
        boolean z2;
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        zabi zabiVar;
        lock = this.f5713a.zam;
        lock.lock();
        try {
            zaaa zaaaVar = this.f5713a;
            z2 = zaaaVar.zal;
            if (!z2) {
                connectionResult = zaaaVar.zak;
                if (connectionResult != null) {
                    connectionResult2 = zaaaVar.zak;
                    if (connectionResult2.isSuccess()) {
                        this.f5713a.zal = true;
                        zabiVar = this.f5713a.zae;
                        zabiVar.onConnectionSuspended(i2);
                    }
                }
            }
            this.f5713a.zal = false;
            zaaa.h(this.f5713a, i2, z);
        } finally {
            lock2 = this.f5713a.zam;
            lock2.unlock();
        }
    }
}
