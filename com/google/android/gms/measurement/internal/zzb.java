package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzb implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6711a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f6712b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzd f6713c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(zzd zzdVar, String str, long j2) {
        this.f6713c = zzdVar;
        this.f6711a = str;
        this.f6712b = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzd.b(this.f6713c, this.f6711a, this.f6712b);
    }
}
