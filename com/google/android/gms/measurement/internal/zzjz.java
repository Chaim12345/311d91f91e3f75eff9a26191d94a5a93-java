package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
/* loaded from: classes2.dex */
final class zzjz implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ComponentName f6984a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzkd f6985b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjz(zzkd zzkdVar, ComponentName componentName) {
        this.f6985b = zzkdVar;
        this.f6984a = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzke.s(this.f6985b.f6995a, this.f6984a);
    }
}
