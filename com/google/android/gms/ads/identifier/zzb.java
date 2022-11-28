package com.google.android.gms.ads.identifier;

import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
@VisibleForTesting
/* loaded from: classes.dex */
final class zzb extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final CountDownLatch f5595a = new CountDownLatch(1);

    /* renamed from: b  reason: collision with root package name */
    boolean f5596b = false;
    private final WeakReference<AdvertisingIdClient> zzc;
    private final long zzd;

    public zzb(AdvertisingIdClient advertisingIdClient, long j2) {
        this.zzc = new WeakReference<>(advertisingIdClient);
        this.zzd = j2;
        start();
    }

    private final void zza() {
        AdvertisingIdClient advertisingIdClient = this.zzc.get();
        if (advertisingIdClient != null) {
            advertisingIdClient.zza();
            this.f5596b = true;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            if (this.f5595a.await(this.zzd, TimeUnit.MILLISECONDS)) {
                return;
            }
            zza();
        } catch (InterruptedException unused) {
            zza();
        }
    }
}
