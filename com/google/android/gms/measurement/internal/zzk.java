package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzk implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f6986a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6987b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6988c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ boolean f6989d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ AppMeasurementDynamiteService f6990e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2, boolean z) {
        this.f6990e = appMeasurementDynamiteService;
        this.f6986a = zzcfVar;
        this.f6987b = str;
        this.f6988c = str2;
        this.f6989d = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6990e.f6677a.zzt().z(this.f6986a, this.f6987b, this.f6988c, this.f6989d);
    }
}
