package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes.dex */
public final class zze implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f5787a;
    private final int zzb;

    public zze(BaseGmsClient baseGmsClient, int i2) {
        this.f5787a = baseGmsClient;
        this.zzb = i2;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Object obj;
        BaseGmsClient baseGmsClient = this.f5787a;
        if (iBinder == null) {
            BaseGmsClient.y(baseGmsClient, 16);
            return;
        }
        obj = baseGmsClient.zzq;
        synchronized (obj) {
            BaseGmsClient baseGmsClient2 = this.f5787a;
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            baseGmsClient2.zzr = (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) ? new zzac(iBinder) : (IGmsServiceBroker) queryLocalInterface;
        }
        this.f5787a.z(0, null, this.zzb);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Object obj;
        obj = this.f5787a.zzq;
        synchronized (obj) {
            this.f5787a.zzr = null;
        }
        Handler handler = this.f5787a.f5745b;
        handler.sendMessage(handler.obtainMessage(6, this.zzb, 1));
    }
}
