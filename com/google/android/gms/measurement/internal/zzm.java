package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzm implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f7047a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f7048b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f7049c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f7050d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2) {
        this.f7050d = appMeasurementDynamiteService;
        this.f7047a = zzcfVar;
        this.f7048b = str;
        this.f7049c = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f7050d.f6677a.zzt().w(this.f7047a, this.f7048b, this.f7049c);
    }
}
