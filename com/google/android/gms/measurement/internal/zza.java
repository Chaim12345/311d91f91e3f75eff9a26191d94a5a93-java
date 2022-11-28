package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zza implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6678a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f6679b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzd f6680c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(zzd zzdVar, String str, long j2) {
        this.f6680c = zzdVar;
        this.f6678a = str;
        this.f6679b = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzd.a(this.f6680c, this.f6678a, this.f6679b);
    }
}
