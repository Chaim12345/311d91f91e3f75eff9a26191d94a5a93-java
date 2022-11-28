package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzar implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzas f7864a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzar(zzas zzasVar, zzaq zzaqVar) {
        this.f7864a = zzasVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzas.d(this.f7864a).zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.f7864a.zzc().post(new zzao(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzas.d(this.f7864a).zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.f7864a.zzc().post(new zzap(this));
    }
}
