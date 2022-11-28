package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzii implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Boolean f6869a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6870b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzii(zzip zzipVar, Boolean bool) {
        this.f6870b = zzipVar;
        this.f6869a = bool;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6870b.zzad(this.f6869a, true);
    }
}
