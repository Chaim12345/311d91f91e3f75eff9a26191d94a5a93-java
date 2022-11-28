package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzm implements ServiceConnection {

    /* renamed from: c  reason: collision with root package name */
    zzn f5608c;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzs f5611f;
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    int f5606a = 0;

    /* renamed from: b  reason: collision with root package name */
    final Messenger f5607b = new Messenger(new com.google.android.gms.internal.cloudmessaging.zzf(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.gms.cloudmessaging.zzf
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            zzm zzmVar = zzm.this;
            int i2 = message.arg1;
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                StringBuilder sb = new StringBuilder(41);
                sb.append("Received response to request: ");
                sb.append(i2);
            }
            synchronized (zzmVar) {
                zzp zzpVar = (zzp) zzmVar.f5610e.get(i2);
                if (zzpVar == null) {
                    StringBuilder sb2 = new StringBuilder(50);
                    sb2.append("Received response for unknown request: ");
                    sb2.append(i2);
                    return true;
                }
                zzmVar.f5610e.remove(i2);
                zzmVar.f();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    zzpVar.c(new zzq(4, "Not supported by GmsCore", null));
                    return true;
                }
                zzpVar.a(data);
                return true;
            }
        }
    }));
    @GuardedBy("this")

    /* renamed from: d  reason: collision with root package name */
    final Queue f5609d = new ArrayDeque();
    @GuardedBy("this")

    /* renamed from: e  reason: collision with root package name */
    final SparseArray f5610e = new SparseArray();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzm(zzs zzsVar, zzl zzlVar) {
        this.f5611f = zzsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(int i2, @Nullable String str) {
        b(i2, str, null);
    }

    final synchronized void b(int i2, @Nullable String str, @Nullable Throwable th) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                "Disconnected: ".concat(valueOf);
            }
        }
        int i3 = this.f5606a;
        if (i3 == 0) {
            throw new IllegalStateException();
        }
        if (i3 != 1 && i3 != 2) {
            if (i3 != 3) {
                return;
            }
            this.f5606a = 4;
            return;
        }
        Log.isLoggable("MessengerIpcClient", 2);
        this.f5606a = 4;
        ConnectionTracker.getInstance().unbindService(zzs.a(this.f5611f), this);
        zzq zzqVar = new zzq(i2, str, th);
        for (zzp zzpVar : this.f5609d) {
            zzpVar.c(zzqVar);
        }
        this.f5609d.clear();
        for (int i4 = 0; i4 < this.f5610e.size(); i4++) {
            ((zzp) this.f5610e.valueAt(i4)).c(zzqVar);
        }
        this.f5610e.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c() {
        zzs.b(this.f5611f).execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzh
            @Override // java.lang.Runnable
            public final void run() {
                final zzp zzpVar;
                final zzm zzmVar = zzm.this;
                while (true) {
                    synchronized (zzmVar) {
                        if (zzmVar.f5606a != 2) {
                            return;
                        }
                        if (zzmVar.f5609d.isEmpty()) {
                            zzmVar.f();
                            return;
                        }
                        zzpVar = (zzp) zzmVar.f5609d.poll();
                        zzmVar.f5610e.put(zzpVar.f5612a, zzpVar);
                        zzs.b(zzmVar.f5611f).schedule(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzk
                            @Override // java.lang.Runnable
                            public final void run() {
                                zzm.this.e(zzpVar.f5612a);
                            }
                        }, 30L, TimeUnit.SECONDS);
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String valueOf = String.valueOf(zzpVar);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 8);
                        sb.append("Sending ");
                        sb.append(valueOf);
                    }
                    Context a2 = zzs.a(zzmVar.f5611f);
                    Messenger messenger = zzmVar.f5607b;
                    Message obtain = Message.obtain();
                    obtain.what = zzpVar.f5614c;
                    obtain.arg1 = zzpVar.f5612a;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", zzpVar.b());
                    bundle.putString("pkg", a2.getPackageName());
                    bundle.putBundle(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, zzpVar.f5615d);
                    obtain.setData(bundle);
                    try {
                        zzmVar.f5608c.a(obtain);
                    } catch (RemoteException e2) {
                        zzmVar.a(2, e2.getMessage());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void d() {
        if (this.f5606a == 1) {
            a(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void e(int i2) {
        zzp zzpVar = (zzp) this.f5610e.get(i2);
        if (zzpVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i2);
            this.f5610e.remove(i2);
            zzpVar.c(new zzq(3, "Timed out waiting for response", null));
            f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void f() {
        if (this.f5606a == 2 && this.f5609d.isEmpty() && this.f5610e.size() == 0) {
            Log.isLoggable("MessengerIpcClient", 2);
            this.f5606a = 3;
            ConnectionTracker.getInstance().unbindService(zzs.a(this.f5611f), this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean g(zzp zzpVar) {
        int i2 = this.f5606a;
        if (i2 != 0) {
            if (i2 == 1) {
                this.f5609d.add(zzpVar);
                return true;
            } else if (i2 != 2) {
                return false;
            } else {
                this.f5609d.add(zzpVar);
                c();
                return true;
            }
        }
        this.f5609d.add(zzpVar);
        Preconditions.checkState(this.f5606a == 0);
        Log.isLoggable("MessengerIpcClient", 2);
        this.f5606a = 1;
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        try {
            if (ConnectionTracker.getInstance().bindService(zzs.a(this.f5611f), intent, this, 1)) {
                zzs.b(this.f5611f).schedule(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzi
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzm.this.d();
                    }
                }, 30L, TimeUnit.SECONDS);
            } else {
                a(0, "Unable to bind to service");
            }
        } catch (SecurityException e2) {
            b(0, "Unable to bind to service", e2);
        }
        return true;
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        Log.isLoggable("MessengerIpcClient", 2);
        zzs.b(this.f5611f).execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzj
            @Override // java.lang.Runnable
            public final void run() {
                zzm zzmVar = zzm.this;
                IBinder iBinder2 = iBinder;
                synchronized (zzmVar) {
                    try {
                        if (iBinder2 == null) {
                            zzmVar.a(0, "Null service connection");
                            return;
                        }
                        try {
                            zzmVar.f5608c = new zzn(iBinder2);
                            zzmVar.f5606a = 2;
                            zzmVar.c();
                        } catch (RemoteException e2) {
                            zzmVar.a(0, e2.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Log.isLoggable("MessengerIpcClient", 2);
        zzs.b(this.f5611f).execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzg
            @Override // java.lang.Runnable
            public final void run() {
                zzm.this.a(2, "Service disconnected");
            }
        });
    }
}
