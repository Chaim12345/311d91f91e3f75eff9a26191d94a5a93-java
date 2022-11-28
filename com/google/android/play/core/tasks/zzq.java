package com.google.android.play.core.tasks;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import java.util.concurrent.ExecutionException;
/* loaded from: classes2.dex */
final class zzq implements zzp {
    private final Object zza = new Object();
    private final int zzb;
    private final zzm zzc;
    @GuardedBy("lock")
    private int zzd;
    @GuardedBy("lock")
    private int zze;
    @GuardedBy("lock")
    private Exception zzf;

    public zzq(int i2, zzm zzmVar) {
        this.zzb = i2;
        this.zzc = zzmVar;
    }

    @GuardedBy("lock")
    private final void zza() {
        if (this.zzd + this.zze == this.zzb) {
            if (this.zzf == null) {
                this.zzc.zzb(null);
                return;
            }
            zzm zzmVar = this.zzc;
            int i2 = this.zze;
            int i3 = this.zzb;
            StringBuilder sb = new StringBuilder(54);
            sb.append(i2);
            sb.append(" out of ");
            sb.append(i3);
            sb.append(" underlying tasks failed");
            zzmVar.zza(new ExecutionException(sb.toString(), this.zzf));
        }
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(@NonNull Exception exc) {
        synchronized (this.zza) {
            this.zze++;
            this.zzf = exc;
            zza();
        }
    }

    @Override // com.google.android.play.core.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        synchronized (this.zza) {
            this.zzd++;
            zza();
        }
    }
}
