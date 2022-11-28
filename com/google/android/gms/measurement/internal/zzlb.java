package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzlb implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzlm f7021a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzll f7022b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlb(zzll zzllVar, zzlm zzlmVar) {
        this.f7022b = zzllVar;
        this.f7021a = zzlmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll.H(this.f7022b, this.f7021a);
        this.f7022b.q();
    }
}
