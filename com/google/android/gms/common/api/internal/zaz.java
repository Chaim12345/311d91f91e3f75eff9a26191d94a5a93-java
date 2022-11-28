package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zaz implements zabz {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zaaa f5714a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zaz(zaaa zaaaVar, zay zayVar) {
        this.f5714a = zaaaVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zaa(@NonNull ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        lock = this.f5714a.zam;
        lock.lock();
        try {
            this.f5714a.zak = connectionResult;
            zaaa.j(this.f5714a);
        } finally {
            lock2 = this.f5714a.zam;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zab(@Nullable Bundle bundle) {
        Lock lock;
        Lock lock2;
        lock = this.f5714a.zam;
        lock.lock();
        try {
            this.f5714a.zak = ConnectionResult.RESULT_SUCCESS;
            zaaa.j(this.f5714a);
        } finally {
            lock2 = this.f5714a.zam;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zac(int i2, boolean z) {
        Lock lock;
        Lock lock2;
        boolean z2;
        zabi zabiVar;
        lock = this.f5714a.zam;
        lock.lock();
        try {
            zaaa zaaaVar = this.f5714a;
            z2 = zaaaVar.zal;
            if (z2) {
                zaaaVar.zal = false;
                zaaa.h(this.f5714a, i2, z);
            } else {
                zaaaVar.zal = true;
                zabiVar = this.f5714a.zad;
                zabiVar.onConnectionSuspended(i2);
            }
        } finally {
            lock2 = this.f5714a.zam;
            lock2.unlock();
        }
    }
}
