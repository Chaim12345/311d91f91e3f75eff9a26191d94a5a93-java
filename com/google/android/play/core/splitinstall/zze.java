package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;
import java.io.File;
/* loaded from: classes2.dex */
public final class zze implements zzp {
    private final zze zza = this;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;
    private final zzcs zze;
    private final zzcs zzf;
    private final zzcs zzg;
    private final zzcs zzh;
    private final zzcs zzi;
    private final zzcs zzj;
    private final zzcs zzk;
    private final zzcs zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zze(zzac zzacVar, zzd zzdVar) {
        zzad zzadVar = new zzad(zzacVar);
        this.zzb = zzadVar;
        zzcs zzc = zzcq.zzc(new zzbd(zzadVar));
        this.zzc = zzc;
        zzcs zzc2 = zzcq.zzc(new zzag(zzacVar));
        this.zzd = zzc2;
        zzcs zzc3 = zzcq.zzc(new zzt(zzadVar));
        this.zze = zzc3;
        zzcs zzc4 = zzcq.zzc(new zzbf(zzadVar));
        this.zzf = zzc4;
        zzcs zzc5 = zzcq.zzc(new zzab(zzc, zzc2, zzc3, zzc4));
        this.zzg = zzc5;
        zzcs zzc6 = zzcq.zzc(new zzaf(zzadVar));
        this.zzh = zzc6;
        zzae zzaeVar = new zzae(zzc6);
        this.zzi = zzaeVar;
        zzcs zzc7 = zzcq.zzc(new com.google.android.play.core.splitinstall.testing.zzr(zzadVar, zzc6, zzc3, zzaeVar));
        this.zzj = zzc7;
        zzcs zzc8 = zzcq.zzc(new zzm(zzc5, zzc7, zzc6));
        this.zzk = zzc8;
        this.zzl = zzcq.zzc(new zzah(zzacVar, zzc8));
    }

    @Override // com.google.android.play.core.splitinstall.zzp
    public final SplitInstallManager zza() {
        return (SplitInstallManager) this.zzl.zza();
    }

    @Override // com.google.android.play.core.splitinstall.zzp
    public final File zzb() {
        return (File) this.zzh.zza();
    }
}
