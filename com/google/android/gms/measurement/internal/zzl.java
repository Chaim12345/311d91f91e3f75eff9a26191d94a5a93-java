package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzl implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzo f7019a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f7020b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(AppMeasurementDynamiteService appMeasurementDynamiteService, zzo zzoVar) {
        this.f7020b = appMeasurementDynamiteService;
        this.f7019a = zzoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f7020b.f6677a.zzq().zzV(this.f7019a);
    }
}
