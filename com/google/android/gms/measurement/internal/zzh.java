package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzh {
    private long zzA;
    private long zzB;
    @Nullable
    private String zzC;
    private boolean zzD;
    private long zzE;
    private long zzF;
    private final zzgk zza;
    private final String zzb;
    @Nullable
    private String zzc;
    @Nullable
    private String zzd;
    @Nullable
    private String zze;
    @Nullable
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    @Nullable
    private String zzj;
    private long zzk;
    @Nullable
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private long zzp;
    private boolean zzq;
    @Nullable
    private String zzr;
    @Nullable
    private Boolean zzs;
    private long zzt;
    @Nullable
    private List zzu;
    @Nullable
    private String zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public zzh(zzgk zzgkVar, String str) {
        Preconditions.checkNotNull(zzgkVar);
        Preconditions.checkNotEmpty(str);
        this.zza = zzgkVar;
        this.zzb = str;
        zzgkVar.zzaz().zzg();
    }

    @Nullable
    @WorkerThread
    public final String zzA() {
        this.zza.zzaz().zzg();
        return this.zze;
    }

    @Nullable
    @WorkerThread
    public final String zzB() {
        this.zza.zzaz().zzg();
        return this.zzv;
    }

    @Nullable
    @WorkerThread
    public final List zzC() {
        this.zza.zzaz().zzg();
        return this.zzu;
    }

    @WorkerThread
    public final void zzD() {
        this.zza.zzaz().zzg();
        this.zzD = false;
    }

    @WorkerThread
    public final void zzE() {
        this.zza.zzaz().zzg();
        long j2 = this.zzg + 1;
        if (j2 > 2147483647L) {
            this.zza.zzay().zzk().zzb("Bundle index overflow. appId", zzfa.g(this.zzb));
            j2 = 0;
        }
        this.zzD = true;
        this.zzg = j2;
    }

    @WorkerThread
    public final void zzF(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (true == TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzD |= true ^ zzg.zza(this.zzr, str);
        this.zzr = str;
    }

    @WorkerThread
    public final void zzG(boolean z) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzq != z;
        this.zzq = z;
    }

    @WorkerThread
    public final void zzH(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzp != j2;
        this.zzp = j2;
    }

    @WorkerThread
    public final void zzI(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zzc, str);
        this.zzc = str;
    }

    @WorkerThread
    public final void zzJ(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zzl, str);
        this.zzl = str;
    }

    @WorkerThread
    public final void zzK(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zzj, str);
        this.zzj = str;
    }

    @WorkerThread
    public final void zzL(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzk != j2;
        this.zzk = j2;
    }

    @WorkerThread
    public final void zzM(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzE != j2;
        this.zzE = j2;
    }

    @WorkerThread
    public final void zzN(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzz != j2;
        this.zzz = j2;
    }

    @WorkerThread
    public final void zzO(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzA != j2;
        this.zzA = j2;
    }

    @WorkerThread
    public final void zzP(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzy != j2;
        this.zzy = j2;
    }

    @WorkerThread
    public final void zzQ(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzx != j2;
        this.zzx = j2;
    }

    @WorkerThread
    public final void zzR(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzB != j2;
        this.zzB = j2;
    }

    @WorkerThread
    public final void zzS(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzw != j2;
        this.zzw = j2;
    }

    @WorkerThread
    public final void zzT(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzn != j2;
        this.zzn = j2;
    }

    @WorkerThread
    public final void zzU(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzt != j2;
        this.zzt = j2;
    }

    @WorkerThread
    public final void zzV(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzF != j2;
        this.zzF = j2;
    }

    @WorkerThread
    public final void zzW(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zzf, str);
        this.zzf = str;
    }

    @WorkerThread
    public final void zzX(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (true == TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzD |= true ^ zzg.zza(this.zzd, str);
        this.zzd = str;
    }

    @WorkerThread
    public final void zzY(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzm != j2;
        this.zzm = j2;
    }

    @WorkerThread
    public final void zzZ(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zzC, str);
        this.zzC = str;
    }

    @WorkerThread
    public final long zza() {
        this.zza.zzaz().zzg();
        return this.zzp;
    }

    @WorkerThread
    public final void zzaa(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzi != j2;
        this.zzi = j2;
    }

    @WorkerThread
    public final void zzab(long j2) {
        Preconditions.checkArgument(j2 >= 0);
        this.zza.zzaz().zzg();
        this.zzD |= this.zzg != j2;
        this.zzg = j2;
    }

    @WorkerThread
    public final void zzac(long j2) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzh != j2;
        this.zzh = j2;
    }

    @WorkerThread
    public final void zzad(boolean z) {
        this.zza.zzaz().zzg();
        this.zzD |= this.zzo != z;
        this.zzo = z;
    }

    @WorkerThread
    public final void zzae(@Nullable Boolean bool) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zzs, bool);
        this.zzs = bool;
    }

    @WorkerThread
    public final void zzaf(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zze, str);
        this.zze = str;
    }

    @WorkerThread
    public final void zzag(@Nullable List list) {
        this.zza.zzaz().zzg();
        if (zzg.zza(this.zzu, list)) {
            return;
        }
        this.zzD = true;
        this.zzu = list != null ? new ArrayList(list) : null;
    }

    @WorkerThread
    public final void zzah(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzD |= !zzg.zza(this.zzv, str);
        this.zzv = str;
    }

    @WorkerThread
    public final boolean zzai() {
        this.zza.zzaz().zzg();
        return this.zzq;
    }

    @WorkerThread
    public final boolean zzaj() {
        this.zza.zzaz().zzg();
        return this.zzo;
    }

    @WorkerThread
    public final boolean zzak() {
        this.zza.zzaz().zzg();
        return this.zzD;
    }

    @WorkerThread
    public final long zzb() {
        this.zza.zzaz().zzg();
        return this.zzk;
    }

    @WorkerThread
    public final long zzc() {
        this.zza.zzaz().zzg();
        return this.zzE;
    }

    @WorkerThread
    public final long zzd() {
        this.zza.zzaz().zzg();
        return this.zzz;
    }

    @WorkerThread
    public final long zze() {
        this.zza.zzaz().zzg();
        return this.zzA;
    }

    @WorkerThread
    public final long zzf() {
        this.zza.zzaz().zzg();
        return this.zzy;
    }

    @WorkerThread
    public final long zzg() {
        this.zza.zzaz().zzg();
        return this.zzx;
    }

    @WorkerThread
    public final long zzh() {
        this.zza.zzaz().zzg();
        return this.zzB;
    }

    @WorkerThread
    public final long zzi() {
        this.zza.zzaz().zzg();
        return this.zzw;
    }

    @WorkerThread
    public final long zzj() {
        this.zza.zzaz().zzg();
        return this.zzn;
    }

    @WorkerThread
    public final long zzk() {
        this.zza.zzaz().zzg();
        return this.zzt;
    }

    @WorkerThread
    public final long zzl() {
        this.zza.zzaz().zzg();
        return this.zzF;
    }

    @WorkerThread
    public final long zzm() {
        this.zza.zzaz().zzg();
        return this.zzm;
    }

    @WorkerThread
    public final long zzn() {
        this.zza.zzaz().zzg();
        return this.zzi;
    }

    @WorkerThread
    public final long zzo() {
        this.zza.zzaz().zzg();
        return this.zzg;
    }

    @WorkerThread
    public final long zzp() {
        this.zza.zzaz().zzg();
        return this.zzh;
    }

    @Nullable
    @WorkerThread
    public final Boolean zzq() {
        this.zza.zzaz().zzg();
        return this.zzs;
    }

    @Nullable
    @WorkerThread
    public final String zzr() {
        this.zza.zzaz().zzg();
        return this.zzr;
    }

    @Nullable
    @WorkerThread
    public final String zzs() {
        this.zza.zzaz().zzg();
        String str = this.zzC;
        zzZ(null);
        return str;
    }

    @WorkerThread
    public final String zzt() {
        this.zza.zzaz().zzg();
        return this.zzb;
    }

    @Nullable
    @WorkerThread
    public final String zzu() {
        this.zza.zzaz().zzg();
        return this.zzc;
    }

    @Nullable
    @WorkerThread
    public final String zzv() {
        this.zza.zzaz().zzg();
        return this.zzl;
    }

    @Nullable
    @WorkerThread
    public final String zzw() {
        this.zza.zzaz().zzg();
        return this.zzj;
    }

    @Nullable
    @WorkerThread
    public final String zzx() {
        this.zza.zzaz().zzg();
        return this.zzf;
    }

    @Nullable
    @WorkerThread
    public final String zzy() {
        this.zza.zzaz().zzg();
        return this.zzd;
    }

    @Nullable
    @WorkerThread
    public final String zzz() {
        this.zza.zzaz().zzg();
        return this.zzC;
    }
}
