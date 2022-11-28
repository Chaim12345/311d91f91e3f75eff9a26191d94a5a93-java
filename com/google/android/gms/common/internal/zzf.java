package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
/* loaded from: classes.dex */
public final class zzf extends zza {

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f5788c;
    @Nullable
    public final IBinder zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    public zzf(BaseGmsClient baseGmsClient, @Nullable int i2, @Nullable IBinder iBinder, Bundle bundle) {
        super(baseGmsClient, i2, bundle);
        this.f5788c = baseGmsClient;
        this.zze = iBinder;
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final void c(ConnectionResult connectionResult) {
        if (this.f5788c.zzx != null) {
            this.f5788c.zzx.onConnectionFailed(connectionResult);
        }
        this.f5788c.k(connectionResult);
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final boolean d() {
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks;
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks2;
        try {
            IBinder iBinder = this.zze;
            Preconditions.checkNotNull(iBinder);
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if (!this.f5788c.g().equals(interfaceDescriptor)) {
                String g2 = this.f5788c.g();
                StringBuilder sb = new StringBuilder(String.valueOf(g2).length() + 34 + String.valueOf(interfaceDescriptor).length());
                sb.append("service descriptor mismatch: ");
                sb.append(g2);
                sb.append(" vs. ");
                sb.append(interfaceDescriptor);
                return false;
            }
            IInterface createServiceInterface = this.f5788c.createServiceInterface(this.zze);
            if (createServiceInterface != null) {
                if (BaseGmsClient.B(this.f5788c, 2, 4, createServiceInterface) || BaseGmsClient.B(this.f5788c, 3, 4, createServiceInterface)) {
                    this.f5788c.zzB = null;
                    Bundle connectionHint = this.f5788c.getConnectionHint();
                    BaseGmsClient baseGmsClient = this.f5788c;
                    baseConnectionCallbacks = baseGmsClient.zzw;
                    if (baseConnectionCallbacks != null) {
                        baseConnectionCallbacks2 = baseGmsClient.zzw;
                        baseConnectionCallbacks2.onConnected(connectionHint);
                    }
                    return true;
                }
                return false;
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }
}
