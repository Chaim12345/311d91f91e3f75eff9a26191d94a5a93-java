package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import androidx.annotation.Nullable;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzo implements ServiceConnection, zzs {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzr f5794a;
    private final Map<ServiceConnection, ServiceConnection> zzb = new HashMap();
    private int zzc = 2;
    private boolean zzd;
    @Nullable
    private IBinder zze;
    private final zzn zzf;
    private ComponentName zzg;

    public zzo(zzr zzrVar, zzn zznVar) {
        this.f5794a = zzrVar;
        this.zzf = zznVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.f5794a.zzb;
        synchronized (hashMap) {
            handler = this.f5794a.zzd;
            handler.removeMessages(1, this.zzf);
            this.zze = iBinder;
            this.zzg = componentName;
            for (ServiceConnection serviceConnection : this.zzb.values()) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
            this.zzc = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.f5794a.zzb;
        synchronized (hashMap) {
            handler = this.f5794a.zzd;
            handler.removeMessages(1, this.zzf);
            this.zze = null;
            this.zzg = componentName;
            for (ServiceConnection serviceConnection : this.zzb.values()) {
                serviceConnection.onServiceDisconnected(componentName);
            }
            this.zzc = 2;
        }
    }

    public final int zza() {
        return this.zzc;
    }

    public final ComponentName zzb() {
        return this.zzg;
    }

    @Nullable
    public final IBinder zzc() {
        return this.zze;
    }

    public final void zzd(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        this.zzb.put(serviceConnection, serviceConnection2);
    }

    public final void zze(String str, @Nullable Executor executor) {
        ConnectionTracker connectionTracker;
        Context context;
        Context context2;
        ConnectionTracker connectionTracker2;
        Context context3;
        Handler handler;
        Handler handler2;
        long j2;
        this.zzc = 3;
        zzr zzrVar = this.f5794a;
        connectionTracker = zzrVar.zzf;
        context = zzrVar.zzc;
        zzn zznVar = this.zzf;
        context2 = zzrVar.zzc;
        boolean zza = connectionTracker.zza(context, str, zznVar.zzc(context2), this, this.zzf.zza(), executor);
        this.zzd = zza;
        if (zza) {
            handler = this.f5794a.zzd;
            Message obtainMessage = handler.obtainMessage(1, this.zzf);
            handler2 = this.f5794a.zzd;
            j2 = this.f5794a.zzh;
            handler2.sendMessageDelayed(obtainMessage, j2);
            return;
        }
        this.zzc = 2;
        try {
            zzr zzrVar2 = this.f5794a;
            connectionTracker2 = zzrVar2.zzf;
            context3 = zzrVar2.zzc;
            connectionTracker2.unbindService(context3, this);
        } catch (IllegalArgumentException unused) {
        }
    }

    public final void zzf(ServiceConnection serviceConnection, String str) {
        this.zzb.remove(serviceConnection);
    }

    public final void zzg(String str) {
        Handler handler;
        ConnectionTracker connectionTracker;
        Context context;
        handler = this.f5794a.zzd;
        handler.removeMessages(1, this.zzf);
        zzr zzrVar = this.f5794a;
        connectionTracker = zzrVar.zzf;
        context = zzrVar.zzc;
        connectionTracker.unbindService(context, this);
        this.zzd = false;
        this.zzc = 2;
    }

    public final boolean zzh(ServiceConnection serviceConnection) {
        return this.zzb.containsKey(serviceConnection);
    }

    public final boolean zzi() {
        return this.zzb.isEmpty();
    }

    public final boolean zzj() {
        return this.zzd;
    }
}
