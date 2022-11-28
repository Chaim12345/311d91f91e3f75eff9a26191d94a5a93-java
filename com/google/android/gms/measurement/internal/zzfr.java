package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.MainThread;
/* loaded from: classes2.dex */
public final class zzfr implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzfs f6741a;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfr(zzfs zzfsVar, String str) {
        this.f6741a = zzfsVar;
        this.zzb = str;
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.f6741a.f6742a.zzay().zzk().zza("Install Referrer connection returned with null binder");
            return;
        }
        try {
            com.google.android.gms.internal.measurement.zzbr zzb = com.google.android.gms.internal.measurement.zzbq.zzb(iBinder);
            if (zzb == null) {
                this.f6741a.f6742a.zzay().zzk().zza("Install Referrer Service implementation was not found");
                return;
            }
            this.f6741a.f6742a.zzay().zzj().zza("Install Referrer Service connected");
            this.f6741a.f6742a.zzaz().zzp(new zzfq(this, zzb, this));
        } catch (RuntimeException e2) {
            this.f6741a.f6742a.zzay().zzk().zzb("Exception occurred while calling Install Referrer API", e2);
        }
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f6741a.f6742a.zzay().zzj().zza("Install Referrer Service disconnected");
    }
}
