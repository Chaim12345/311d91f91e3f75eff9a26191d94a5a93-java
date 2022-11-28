package com.google.android.gms.measurement.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes2.dex */
public final class zzfg extends zzkz {
    public zzfg(zzll zzllVar) {
        super(zzllVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkz
    protected final boolean c() {
        return false;
    }

    public final boolean zza() {
        a();
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f6809a.zzau().getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException unused) {
            }
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
