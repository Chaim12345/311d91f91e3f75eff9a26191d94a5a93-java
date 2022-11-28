package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
/* loaded from: classes.dex */
public final class zzg {
    public static final zzg zza;
    public static final zzg zzb;
    private final boolean zzc;
    private final boolean zzd = false;
    private final zzam zze;

    static {
        zze zzeVar = new zze(null);
        zzeVar.zza();
        zza = zzeVar.zzc();
        zze zzeVar2 = new zze(null);
        zzeVar2.zzb();
        zzb = zzeVar2.zzc();
    }

    public /* synthetic */ zzg(boolean z, boolean z2, zzam zzamVar, zzf zzfVar) {
        this.zzc = z;
        this.zze = zzamVar;
    }

    public static /* bridge */ /* synthetic */ boolean a(zzg zzgVar) {
        boolean z = zzgVar.zzd;
        return false;
    }

    public static /* bridge */ /* synthetic */ boolean b(zzg zzgVar) {
        return zzgVar.zzc;
    }

    public static /* bridge */ /* synthetic */ int c(zzg zzgVar, Context context, zzp zzpVar) {
        zzam zzamVar = zzgVar.zze;
        int size = zzamVar.size();
        int i2 = 0;
        while (i2 < size) {
            int a2 = ((zzq) zzamVar.get(i2)).a();
            int i3 = a2 - 1;
            if (a2 == 0) {
                throw null;
            }
            i2++;
            if (i3 == 0) {
                return 1;
            }
            if (i3 == 1) {
                return 2;
            }
        }
        return 3;
    }
}
