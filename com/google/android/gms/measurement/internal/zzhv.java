package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhv implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6833a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6834b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Object f6835c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ long f6836d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzip f6837e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhv(zzip zzipVar, String str, String str2, Object obj, long j2) {
        this.f6837e = zzipVar;
        this.f6833a = str;
        this.f6834b = str2;
        this.f6835c = obj;
        this.f6836d = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6837e.n(this.f6833a, this.f6834b, this.f6835c, this.f6836d);
    }
}
