package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzi implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f6847a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f6848b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.f6848b = appMeasurementDynamiteService;
        this.f6847a = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6848b.f6677a.zzt().zzt(this.f6847a);
    }
}
