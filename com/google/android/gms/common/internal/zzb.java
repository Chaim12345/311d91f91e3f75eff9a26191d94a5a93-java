package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzb extends com.google.android.gms.internal.common.zzi {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f5785a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzb(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.f5785a = baseGmsClient;
    }

    private static final void zza(Message message) {
        zzc zzcVar = (zzc) message.obj;
        zzcVar.b();
        zzcVar.zzg();
    }

    private static final boolean zzb(Message message) {
        int i2 = message.what;
        return i2 == 2 || i2 == 1 || i2 == 7;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks;
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks2;
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        boolean z;
        if (this.f5785a.f5747d.get() != message.arg1) {
            if (zzb(message)) {
                zza(message);
                return;
            }
            return;
        }
        int i2 = message.what;
        if ((i2 == 1 || i2 == 7 || ((i2 == 4 && !this.f5785a.b()) || message.what == 5)) && !this.f5785a.isConnecting()) {
            zza(message);
            return;
        }
        int i3 = message.what;
        if (i3 == 4) {
            this.f5785a.zzB = new ConnectionResult(message.arg2);
            if (BaseGmsClient.C(this.f5785a)) {
                BaseGmsClient baseGmsClient = this.f5785a;
                z = baseGmsClient.zzC;
                if (!z) {
                    baseGmsClient.zzp(3, null);
                    return;
                }
            }
            BaseGmsClient baseGmsClient2 = this.f5785a;
            connectionResult2 = baseGmsClient2.zzB;
            ConnectionResult connectionResult3 = connectionResult2 != null ? baseGmsClient2.zzB : new ConnectionResult(8);
            this.f5785a.f5746c.onReportServiceBinding(connectionResult3);
            this.f5785a.k(connectionResult3);
        } else if (i3 == 5) {
            BaseGmsClient baseGmsClient3 = this.f5785a;
            connectionResult = baseGmsClient3.zzB;
            ConnectionResult connectionResult4 = connectionResult != null ? baseGmsClient3.zzB : new ConnectionResult(8);
            this.f5785a.f5746c.onReportServiceBinding(connectionResult4);
            this.f5785a.k(connectionResult4);
        } else if (i3 == 3) {
            Object obj = message.obj;
            ConnectionResult connectionResult5 = new ConnectionResult(message.arg2, obj instanceof PendingIntent ? (PendingIntent) obj : null);
            this.f5785a.f5746c.onReportServiceBinding(connectionResult5);
            this.f5785a.k(connectionResult5);
        } else if (i3 == 6) {
            this.f5785a.zzp(5, null);
            BaseGmsClient baseGmsClient4 = this.f5785a;
            baseConnectionCallbacks = baseGmsClient4.zzw;
            if (baseConnectionCallbacks != null) {
                baseConnectionCallbacks2 = baseGmsClient4.zzw;
                baseConnectionCallbacks2.onConnectionSuspended(message.arg2);
            }
            this.f5785a.l(message.arg2);
            BaseGmsClient.B(this.f5785a, 5, 1, null);
        } else if (i3 == 2 && !this.f5785a.isConnected()) {
            zza(message);
        } else if (zzb(message)) {
            ((zzc) message.obj).zze();
        } else {
            int i4 = message.what;
            StringBuilder sb = new StringBuilder(45);
            sb.append("Don't know how to handle message: ");
            sb.append(i4);
            Log.wtf("GmsClient", sb.toString(), new Exception());
        }
    }
}
