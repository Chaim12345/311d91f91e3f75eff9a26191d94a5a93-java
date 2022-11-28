package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdz {
    private final zzbh zza;
    private final com.google.android.play.core.internal.zzco zzb;
    private final zzde zzc;
    private final com.google.android.play.core.internal.zzco zzd;
    private final zzco zze;
    private final com.google.android.play.core.common.zza zzf;
    private final zzeb zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdz(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar, zzde zzdeVar, com.google.android.play.core.internal.zzco zzcoVar2, zzco zzcoVar3, com.google.android.play.core.common.zza zzaVar, zzeb zzebVar) {
        this.zza = zzbhVar;
        this.zzb = zzcoVar;
        this.zzc = zzdeVar;
        this.zzd = zzcoVar2;
        this.zze = zzcoVar3;
        this.zzf = zzaVar;
        this.zzg = zzebVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void a(zzdw zzdwVar) {
        this.zza.b(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d);
    }

    public final void zza(final zzdw zzdwVar) {
        File q2 = this.zza.q(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d);
        File s2 = this.zza.s(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d);
        if (!q2.exists() || !s2.exists()) {
            throw new zzck(String.format("Cannot find pack files to move for pack %s.", zzdwVar.f7833b), zzdwVar.f7832a);
        }
        File o2 = this.zza.o(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d);
        o2.mkdirs();
        if (!q2.renameTo(o2)) {
            throw new zzck("Cannot move merged pack files to final location.", zzdwVar.f7832a);
        }
        new File(this.zza.o(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d), "merge.tmp").delete();
        File p2 = this.zza.p(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d);
        p2.mkdirs();
        if (!s2.renameTo(p2)) {
            throw new zzck("Cannot move metadata files to final location.", zzdwVar.f7832a);
        }
        if (this.zzf.zza("assetOnlyUpdates")) {
            try {
                this.zzg.b(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d, zzdwVar.f7839e);
                ((Executor) this.zzd.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdy
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzdz.this.a(zzdwVar);
                    }
                });
            } catch (IOException e2) {
                throw new zzck(String.format("Could not write asset pack version tag for pack %s: %s", zzdwVar.f7833b, e2.getMessage()), zzdwVar.f7832a);
            }
        } else {
            final zzbh zzbhVar = this.zza;
            zzbhVar.getClass();
            ((Executor) this.zzd.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdx
                @Override // java.lang.Runnable
                public final void run() {
                    zzbh.this.D();
                }
            });
        }
        this.zzc.k(zzdwVar.f7833b, zzdwVar.f7837c, zzdwVar.f7838d);
        this.zze.c(zzdwVar.f7833b);
        ((zzy) this.zzb.zza()).zzh(zzdwVar.f7832a, zzdwVar.f7833b);
    }
}
