package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzem {
    private static final Object zza = new Object();
    private final String zzb;
    private final zzej zzc;
    private final Object zzd;
    private final Object zze;
    private final Object zzf = new Object();
    @GuardedBy("overrideLock")
    private volatile Object zzg = null;
    @GuardedBy("cachingLock")
    private volatile Object zzh = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzem(String str, Object obj, Object obj2, zzej zzejVar, zzel zzelVar) {
        this.zzb = str;
        this.zzd = obj;
        this.zze = obj2;
        this.zzc = zzejVar;
    }

    public final Object zza(Object obj) {
        synchronized (this.zzf) {
        }
        if (obj != null) {
            return obj;
        }
        if (zzek.f6716a == null) {
            return this.zzd;
        }
        synchronized (zza) {
            if (zzab.zza()) {
                return this.zzh == null ? this.zzd : this.zzh;
            }
            try {
                for (zzem zzemVar : zzen.b()) {
                    if (zzab.zza()) {
                        throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                    }
                    Object obj2 = null;
                    try {
                        zzej zzejVar = zzemVar.zzc;
                        if (zzejVar != null) {
                            obj2 = zzejVar.zza();
                        }
                    } catch (IllegalStateException unused) {
                    }
                    synchronized (zza) {
                        zzemVar.zzh = obj2;
                    }
                }
            } catch (SecurityException unused2) {
            }
            zzej zzejVar2 = this.zzc;
            if (zzejVar2 == null) {
                return this.zzd;
            }
            try {
                return zzejVar2.zza();
            } catch (IllegalStateException unused3) {
                return this.zzd;
            } catch (SecurityException unused4) {
                return this.zzd;
            }
        }
    }

    public final String zzb() {
        return this.zzb;
    }
}
