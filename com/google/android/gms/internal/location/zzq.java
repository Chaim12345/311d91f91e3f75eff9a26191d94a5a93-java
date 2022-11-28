package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
final class zzq extends zzan {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f5906a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ CountDownLatch f5907b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(zzz zzzVar, AtomicReference atomicReference, CountDownLatch countDownLatch) {
        this.f5906a = atomicReference;
        this.f5907b = countDownLatch;
    }

    @Override // com.google.android.gms.internal.location.zzao
    public final void zzb(Status status, Location location) {
        if (status.isSuccess()) {
            this.f5906a.set(location);
        }
        this.f5907b.countDown();
    }
}
