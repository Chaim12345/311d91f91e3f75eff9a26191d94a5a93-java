package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzn implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f7051a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f7052b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.f7052b = appMeasurementDynamiteService;
        this.f7051a = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f7052b.f6677a.zzv().zzP(this.f7051a, this.f7052b.f6677a.zzI());
    }
}
