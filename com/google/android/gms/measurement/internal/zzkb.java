package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
/* loaded from: classes2.dex */
final class zzkb implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzkd f6993a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkb(zzkd zzkdVar) {
        this.f6993a = zzkdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzke zzkeVar = this.f6993a.f6995a;
        Context zzau = zzkeVar.f6809a.zzau();
        this.f6993a.f6995a.f6809a.zzaw();
        zzke.s(zzkeVar, new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
