package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f6907a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzaw f6908b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6909c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f6910d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar, zzaw zzawVar, String str) {
        this.f6910d = appMeasurementDynamiteService;
        this.f6907a = zzcfVar;
        this.f6908b = zzawVar;
        this.f6909c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6910d.f6677a.zzt().zzB(this.f6907a, this.f6908b, this.f6909c);
    }
}
