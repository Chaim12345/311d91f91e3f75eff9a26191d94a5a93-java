package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhx implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f6841a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6842b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhx(zzip zzipVar, long j2) {
        this.f6842b = zzipVar;
        this.f6841a = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6842b.i(this.f6841a, true);
        this.f6842b.f6809a.zzt().zzu(new AtomicReference());
    }
}
