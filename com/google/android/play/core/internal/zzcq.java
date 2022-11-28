package com.google.android.play.core.internal;

import java.util.Objects;
/* loaded from: classes2.dex */
public final class zzcq implements zzcs, zzco {
    private static final Object zza = new Object();
    private volatile zzcs zzb;
    private volatile Object zzc = zza;

    private zzcq(zzcs zzcsVar) {
        this.zzb = zzcsVar;
    }

    public static zzco zzb(zzcs zzcsVar) {
        if (zzcsVar instanceof zzco) {
            return (zzco) zzcsVar;
        }
        Objects.requireNonNull(zzcsVar);
        return new zzcq(zzcsVar);
    }

    public static zzcs zzc(zzcs zzcsVar) {
        Objects.requireNonNull(zzcsVar);
        return zzcsVar instanceof zzcq ? zzcsVar : new zzcq(zzcsVar);
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final Object zza() {
        Object obj = this.zzc;
        Object obj2 = zza;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.zzc;
                if (obj == obj2) {
                    obj = this.zzb.zza();
                    Object obj3 = this.zzc;
                    if (obj3 != obj2 && obj3 != obj) {
                        String valueOf = String.valueOf(obj3);
                        String valueOf2 = String.valueOf(obj);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 118 + valueOf2.length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(valueOf);
                        sb.append(" & ");
                        sb.append(valueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.zzc = obj;
                    this.zzb = null;
                }
            }
        }
        return obj;
    }
}
