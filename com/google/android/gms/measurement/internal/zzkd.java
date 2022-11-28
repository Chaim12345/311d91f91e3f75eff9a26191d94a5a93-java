package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzkd implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzke f6995a;
    private volatile boolean zzb;
    private volatile zzew zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzkd(zzke zzkeVar) {
        this.f6995a = zzkeVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    @MainThread
    public final void onConnected(Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                Preconditions.checkNotNull(this.zzc);
                this.f6995a.f6809a.zzaz().zzp(new zzka(this, (zzeq) this.zzc.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzfa zzl = this.f6995a.f6809a.zzl();
        if (zzl != null) {
            zzl.zzk().zzb("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.f6995a.f6809a.zzaz().zzp(new zzkc(this));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    @MainThread
    public final void onConnectionSuspended(int i2) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.f6995a.f6809a.zzay().zzc().zza("Service connection suspended");
        this.f6995a.f6809a.zzaz().zzp(new zzkb(this));
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzkd zzkdVar;
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.zzb = false;
                this.f6995a.f6809a.zzay().zzd().zza("Service connected with null binder");
                return;
            }
            zzeq zzeqVar = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zzeqVar = queryLocalInterface instanceof zzeq ? (zzeq) queryLocalInterface : new zzeo(iBinder);
                    this.f6995a.f6809a.zzay().zzj().zza("Bound to IMeasurementService interface");
                } else {
                    this.f6995a.f6809a.zzay().zzd().zzb("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException unused) {
                this.f6995a.f6809a.zzay().zzd().zza("Service connect failed to get IMeasurementService");
            }
            if (zzeqVar == null) {
                this.zzb = false;
                try {
                    ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
                    Context zzau = this.f6995a.f6809a.zzau();
                    zzkdVar = this.f6995a.zza;
                    connectionTracker.unbindService(zzau, zzkdVar);
                } catch (IllegalArgumentException unused2) {
                }
            } else {
                this.f6995a.f6809a.zzaz().zzp(new zzjy(this, zzeqVar));
            }
        }
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.f6995a.f6809a.zzay().zzc().zza("Service disconnected");
        this.f6995a.f6809a.zzaz().zzp(new zzjz(this, componentName));
    }

    @WorkerThread
    public final void zzb(Intent intent) {
        zzkd zzkdVar;
        this.f6995a.zzg();
        Context zzau = this.f6995a.f6809a.zzau();
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzb) {
                this.f6995a.f6809a.zzay().zzj().zza("Connection attempt already in progress");
                return;
            }
            this.f6995a.f6809a.zzay().zzj().zza("Using local app measurement service");
            this.zzb = true;
            zzkdVar = this.f6995a.zza;
            connectionTracker.bindService(zzau, intent, zzkdVar, 129);
        }
    }

    @WorkerThread
    public final void zzc() {
        this.f6995a.zzg();
        Context zzau = this.f6995a.f6809a.zzau();
        synchronized (this) {
            if (this.zzb) {
                this.f6995a.f6809a.zzay().zzj().zza("Connection attempt already in progress");
            } else if (this.zzc != null && (this.zzc.isConnecting() || this.zzc.isConnected())) {
                this.f6995a.f6809a.zzay().zzj().zza("Already awaiting connection attempt");
            } else {
                this.zzc = new zzew(zzau, Looper.getMainLooper(), this, this);
                this.f6995a.f6809a.zzay().zzj().zza("Connecting to remote service");
                this.zzb = true;
                Preconditions.checkNotNull(this.zzc);
                this.zzc.checkAvailabilityAndConnect();
            }
        }
    }

    @WorkerThread
    public final void zzd() {
        if (this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }
}
