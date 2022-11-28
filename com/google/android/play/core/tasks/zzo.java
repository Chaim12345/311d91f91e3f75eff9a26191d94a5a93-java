package com.google.android.play.core.tasks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
final class zzo implements zzp {
    private final CountDownLatch zza = new CountDownLatch(1);

    private zzo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzo(zzn zznVar) {
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.zza.countDown();
    }

    @Override // com.google.android.play.core.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        this.zza.countDown();
    }

    public final void zza() {
        this.zza.await();
    }

    public final boolean zzb(long j2, TimeUnit timeUnit) {
        return this.zza.await(j2, timeUnit);
    }
}
