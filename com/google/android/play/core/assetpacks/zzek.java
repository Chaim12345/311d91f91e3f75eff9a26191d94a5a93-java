package com.google.android.play.core.assetpacks;

import java.io.File;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzek {
    private final zzbh zza;
    private final com.google.android.play.core.internal.zzco zzb;
    private final zzde zzc;
    private final com.google.android.play.core.internal.zzco zzd;
    private final zzco zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzek(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar, zzde zzdeVar, com.google.android.play.core.internal.zzco zzcoVar2, zzco zzcoVar3) {
        this.zza = zzbhVar;
        this.zzb = zzcoVar;
        this.zzc = zzdeVar;
        this.zzd = zzcoVar2;
        this.zze = zzcoVar3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void a(zzei zzeiVar) {
        this.zza.b(zzeiVar.f7833b, zzeiVar.f7849d, zzeiVar.f7850e);
    }

    public final void zza(final zzei zzeiVar) {
        File o2 = this.zza.o(zzeiVar.f7833b, zzeiVar.f7848c, zzeiVar.f7850e);
        if (!o2.exists()) {
            throw new zzck(String.format("Cannot find pack files to promote for pack %s at %s", zzeiVar.f7833b, o2.getAbsolutePath()), zzeiVar.f7832a);
        }
        File o3 = this.zza.o(zzeiVar.f7833b, zzeiVar.f7849d, zzeiVar.f7850e);
        o3.mkdirs();
        if (!o2.renameTo(o3)) {
            throw new zzck(String.format("Cannot promote pack %s from %s to %s", zzeiVar.f7833b, o2.getAbsolutePath(), o3.getAbsolutePath()), zzeiVar.f7832a);
        }
        ((Executor) this.zzd.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzej
            @Override // java.lang.Runnable
            public final void run() {
                zzek.this.a(zzeiVar);
            }
        });
        this.zzc.k(zzeiVar.f7833b, zzeiVar.f7849d, zzeiVar.f7850e);
        this.zze.c(zzeiVar.f7833b);
        ((zzy) this.zzb.zza()).zzh(zzeiVar.f7832a, zzeiVar.f7833b);
    }
}
