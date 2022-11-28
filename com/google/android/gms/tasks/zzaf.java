package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutionException;
import javax.annotation.concurrent.GuardedBy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaf implements zzae {
    private final Object zza = new Object();
    private final int zzb;
    private final zzw<Void> zzc;
    @GuardedBy("mLock")
    private int zzd;
    @GuardedBy("mLock")
    private int zze;
    @GuardedBy("mLock")
    private int zzf;
    @GuardedBy("mLock")
    private Exception zzg;
    @GuardedBy("mLock")
    private boolean zzh;

    public zzaf(int i2, zzw<Void> zzwVar) {
        this.zzb = i2;
        this.zzc = zzwVar;
    }

    @GuardedBy("mLock")
    private final void zza() {
        if (this.zzd + this.zze + this.zzf == this.zzb) {
            if (this.zzg == null) {
                if (this.zzh) {
                    this.zzc.zzc();
                    return;
                } else {
                    this.zzc.zzb(null);
                    return;
                }
            }
            zzw<Void> zzwVar = this.zzc;
            int i2 = this.zze;
            int i3 = this.zzb;
            StringBuilder sb = new StringBuilder(54);
            sb.append(i2);
            sb.append(" out of ");
            sb.append(i3);
            sb.append(" underlying tasks failed");
            zzwVar.zza(new ExecutionException(sb.toString(), this.zzg));
        }
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        synchronized (this.zza) {
            this.zzf++;
            this.zzh = true;
            zza();
        }
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(@NonNull Exception exc) {
        synchronized (this.zza) {
            this.zze++;
            this.zzg = exc;
            zza();
        }
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        synchronized (this.zza) {
            this.zzd++;
            zza();
        }
    }
}
