package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzps;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes2.dex */
final class zzgx implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzaw f6794a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6795b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzhc f6796c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgx(zzhc zzhcVar, zzaw zzawVar, String str) {
        this.f6796c = zzhcVar;
        this.f6794a = zzawVar;
        this.f6795b = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzll zzllVar;
        zzll zzllVar2;
        byte[] bArr;
        zzlq zzlqVar;
        zzh zzhVar;
        com.google.android.gms.internal.measurement.zzfz zzfzVar;
        String str;
        Bundle bundle;
        com.google.android.gms.internal.measurement.zzgb zzgbVar;
        String str2;
        zzas c2;
        long j2;
        zzllVar = this.f6796c.zza;
        zzllVar.a();
        zzllVar2 = this.f6796c.zza;
        zziu zzr = zzllVar2.zzr();
        zzaw zzawVar = this.f6794a;
        String str3 = this.f6795b;
        zzr.zzg();
        zzgk.h();
        Preconditions.checkNotNull(zzawVar);
        Preconditions.checkNotEmpty(str3);
        if (!zzr.f6809a.zzf().zzs(str3, zzen.zzT)) {
            zzr.f6809a.zzay().zzc().zzb("Generating ScionPayload disabled. packageName", str3);
            return new byte[0];
        } else if (!"_iap".equals(zzawVar.zza) && !"_iapx".equals(zzawVar.zza)) {
            zzr.f6809a.zzay().zzc().zzc("Generating a payload for this event is not available. package_name, event_name", str3, zzawVar.zza);
            return null;
        } else {
            com.google.android.gms.internal.measurement.zzfz zza = com.google.android.gms.internal.measurement.zzga.zza();
            zzr.f7018b.zzi().zzw();
            try {
                zzh zzj = zzr.f7018b.zzi().zzj(str3);
                if (zzj == null) {
                    zzr.f6809a.zzay().zzc().zzb("Log and bundle not available. package_name", str3);
                } else if (zzj.zzaj()) {
                    com.google.android.gms.internal.measurement.zzgb zzu = com.google.android.gms.internal.measurement.zzgc.zzu();
                    zzu.zzae(1);
                    zzu.zzaa("android");
                    if (!TextUtils.isEmpty(zzj.zzt())) {
                        zzu.zzE(zzj.zzt());
                    }
                    if (!TextUtils.isEmpty(zzj.zzv())) {
                        zzu.zzG((String) Preconditions.checkNotNull(zzj.zzv()));
                    }
                    if (!TextUtils.isEmpty(zzj.zzw())) {
                        zzu.zzH((String) Preconditions.checkNotNull(zzj.zzw()));
                    }
                    if (zzj.zzb() != -2147483648L) {
                        zzu.zzI((int) zzj.zzb());
                    }
                    zzu.zzW(zzj.zzm());
                    zzu.zzQ(zzj.zzk());
                    String zzy = zzj.zzy();
                    String zzr2 = zzj.zzr();
                    if (!TextUtils.isEmpty(zzy)) {
                        zzu.zzV(zzy);
                    } else if (!TextUtils.isEmpty(zzr2)) {
                        zzu.zzC(zzr2);
                    }
                    zzai C = zzr.f7018b.C(str3);
                    zzu.zzN(zzj.zzj());
                    if (zzr.f6809a.zzJ() && zzr.f6809a.zzf().zzt(zzu.zzaq()) && C.zzi(zzah.AD_STORAGE) && !TextUtils.isEmpty(null)) {
                        zzu.zzP(null);
                    }
                    zzu.zzM(C.zzh());
                    if (C.zzi(zzah.AD_STORAGE)) {
                        Pair e2 = zzr.f7018b.zzs().e(zzj.zzt(), C);
                        if (zzj.zzai() && !TextUtils.isEmpty((CharSequence) e2.first)) {
                            try {
                                zzu.zzaf(zziu.zza((String) e2.first, Long.toString(zzawVar.zzd)));
                                Object obj = e2.second;
                                if (obj != null) {
                                    zzu.zzY(((Boolean) obj).booleanValue());
                                }
                            } catch (SecurityException e3) {
                                zzr.f6809a.zzay().zzc().zzb("Resettable device id encryption failed", e3.getMessage());
                            }
                        }
                    }
                    zzr.f6809a.zzg().c();
                    zzu.zzO(Build.MODEL);
                    zzr.f6809a.zzg().c();
                    zzu.zzZ(Build.VERSION.RELEASE);
                    zzu.zzak((int) zzr.f6809a.zzg().zzb());
                    zzu.zzao(zzr.f6809a.zzg().zzc());
                    try {
                        if (C.zzi(zzah.ANALYTICS_STORAGE) && zzj.zzu() != null) {
                            zzu.zzF(zziu.zza((String) Preconditions.checkNotNull(zzj.zzu()), Long.toString(zzawVar.zzd)));
                        }
                        if (!TextUtils.isEmpty(zzj.zzx())) {
                            zzu.zzU((String) Preconditions.checkNotNull(zzj.zzx()));
                        }
                        String zzt = zzj.zzt();
                        List zzu2 = zzr.f7018b.zzi().zzu(zzt);
                        Iterator it = zzu2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                zzlqVar = null;
                                break;
                            }
                            zzlqVar = (zzlq) it.next();
                            if ("_lte".equals(zzlqVar.f7044c)) {
                                break;
                            }
                        }
                        if (zzlqVar == null || zzlqVar.f7046e == null) {
                            zzlq zzlqVar2 = new zzlq(zzt, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lte", zzr.f6809a.zzav().currentTimeMillis(), 0L);
                            zzu2.add(zzlqVar2);
                            zzr.f7018b.zzi().zzL(zzlqVar2);
                        }
                        zzln zzu3 = zzr.f7018b.zzu();
                        zzu3.f6809a.zzay().zzj().zza("Checking account type status for ad personalization signals");
                        if (zzu3.f6809a.zzg().g()) {
                            String zzt2 = zzj.zzt();
                            Preconditions.checkNotNull(zzt2);
                            if (zzj.zzai() && zzu3.f7018b.zzo().n(zzt2)) {
                                zzu3.f6809a.zzay().zzc().zza("Turning off ad personalization due to account type");
                                Iterator it2 = zzu2.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    } else if ("_npa".equals(((zzlq) it2.next()).f7044c)) {
                                        it2.remove();
                                        break;
                                    }
                                }
                                zzu2.add(new zzlq(zzt2, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_npa", zzu3.f6809a.zzav().currentTimeMillis(), 1L));
                            }
                        }
                        com.google.android.gms.internal.measurement.zzgl[] zzglVarArr = new com.google.android.gms.internal.measurement.zzgl[zzu2.size()];
                        for (int i2 = 0; i2 < zzu2.size(); i2++) {
                            com.google.android.gms.internal.measurement.zzgk zzd = com.google.android.gms.internal.measurement.zzgl.zzd();
                            zzd.zzf(((zzlq) zzu2.get(i2)).f7044c);
                            zzd.zzg(((zzlq) zzu2.get(i2)).f7045d);
                            zzr.f7018b.zzu().u(zzd, ((zzlq) zzu2.get(i2)).f7046e);
                            zzglVarArr[i2] = (com.google.android.gms.internal.measurement.zzgl) zzd.zzaE();
                        }
                        zzu.zzj(Arrays.asList(zzglVarArr));
                        zzfb zzb = zzfb.zzb(zzawVar);
                        zzr.f6809a.zzv().l(zzb.zzd, zzr.f7018b.zzi().zzi(str3));
                        zzr.f6809a.zzv().m(zzb, zzr.f6809a.zzf().zzd(str3));
                        Bundle bundle2 = zzb.zzd;
                        bundle2.putLong("_c", 1L);
                        zzr.f6809a.zzay().zzc().zza("Marking in-app purchase as real-time");
                        bundle2.putLong("_r", 1L);
                        bundle2.putString("_o", zzawVar.zzc);
                        if (zzr.f6809a.zzv().y(zzu.zzaq())) {
                            zzr.f6809a.zzv().o(bundle2, "_dbg", 1L);
                            zzr.f6809a.zzv().o(bundle2, "_r", 1L);
                        }
                        zzas zzn = zzr.f7018b.zzi().zzn(str3, zzawVar.zza);
                        if (zzn == null) {
                            zzgbVar = zzu;
                            zzhVar = zzj;
                            zzfzVar = zza;
                            str = str3;
                            bundle = bundle2;
                            str2 = null;
                            c2 = new zzas(str3, zzawVar.zza, 0L, 0L, 0L, zzawVar.zzd, 0L, null, null, null, null);
                            j2 = 0;
                        } else {
                            zzhVar = zzj;
                            zzfzVar = zza;
                            str = str3;
                            bundle = bundle2;
                            zzgbVar = zzu;
                            str2 = null;
                            long j3 = zzn.f6703f;
                            c2 = zzn.c(zzawVar.zzd);
                            j2 = j3;
                        }
                        zzr.f7018b.zzi().zzE(c2);
                        zzar zzarVar = new zzar(zzr.f6809a, zzawVar.zzc, str, zzawVar.zza, zzawVar.zzd, j2, bundle);
                        com.google.android.gms.internal.measurement.zzfr zze = com.google.android.gms.internal.measurement.zzfs.zze();
                        zze.zzm(zzarVar.f6695d);
                        zze.zzi(zzarVar.f6693b);
                        zze.zzl(zzarVar.f6696e);
                        zzat zzatVar = new zzat(zzarVar.f6697f);
                        while (zzatVar.hasNext()) {
                            String next = zzatVar.next();
                            com.google.android.gms.internal.measurement.zzfv zze2 = com.google.android.gms.internal.measurement.zzfw.zze();
                            zze2.zzj(next);
                            Object d2 = zzarVar.f6697f.d(next);
                            if (d2 != null) {
                                zzr.f7018b.zzu().t(zze2, d2);
                                zze.zze(zze2);
                            }
                        }
                        com.google.android.gms.internal.measurement.zzgb zzgbVar2 = zzgbVar;
                        zzgbVar2.zzk(zze);
                        com.google.android.gms.internal.measurement.zzgd zza2 = com.google.android.gms.internal.measurement.zzgf.zza();
                        com.google.android.gms.internal.measurement.zzft zza3 = com.google.android.gms.internal.measurement.zzfu.zza();
                        zza3.zza(c2.f6700c);
                        zza3.zzb(zzawVar.zza);
                        zza2.zza(zza3);
                        zzgbVar2.zzab(zza2);
                        zzgbVar2.zzf(zzr.f7018b.zzf().d(zzhVar.zzt(), Collections.emptyList(), zzgbVar2.zzat(), Long.valueOf(zze.zzc()), Long.valueOf(zze.zzc())));
                        if (zze.zzq()) {
                            zzgbVar2.zzaj(zze.zzc());
                            zzgbVar2.zzR(zze.zzc());
                        }
                        long zzn2 = zzhVar.zzn();
                        int i3 = (zzn2 > 0L ? 1 : (zzn2 == 0L ? 0 : -1));
                        if (i3 != 0) {
                            zzgbVar2.zzac(zzn2);
                        }
                        long zzp = zzhVar.zzp();
                        if (zzp != 0) {
                            zzgbVar2.zzad(zzp);
                        } else if (i3 != 0) {
                            zzgbVar2.zzad(zzn2);
                        }
                        String zzB = zzhVar.zzB();
                        zzps.zzc();
                        if (zzr.f6809a.zzf().zzs(str2, zzen.zzaH) && zzB != null) {
                            zzgbVar2.zzai(zzB);
                        }
                        zzhVar.zzE();
                        zzgbVar2.zzJ((int) zzhVar.zzo());
                        zzr.f6809a.zzf().zzh();
                        zzgbVar2.zzam(64000L);
                        zzgbVar2.zzal(zzr.f6809a.zzav().currentTimeMillis());
                        zzgbVar2.zzah(true);
                        com.google.android.gms.internal.measurement.zzfz zzfzVar2 = zzfzVar;
                        zzfzVar2.zza(zzgbVar2);
                        zzh zzhVar2 = zzhVar;
                        zzhVar2.zzac(zzgbVar2.zzd());
                        zzhVar2.zzaa(zzgbVar2.zzc());
                        zzr.f7018b.zzi().zzD(zzhVar2);
                        zzr.f7018b.zzi().zzC();
                        try {
                            return zzr.f7018b.zzu().y(((com.google.android.gms.internal.measurement.zzga) zzfzVar2.zzaE()).zzby());
                        } catch (IOException e4) {
                            zzr.f6809a.zzay().zzd().zzc("Data loss. Failed to bundle and serialize. appId", zzfa.g(str), e4);
                            return str2;
                        }
                    } catch (SecurityException e5) {
                        zzr.f6809a.zzay().zzc().zzb("app instance id encryption failed", e5.getMessage());
                        bArr = new byte[0];
                    }
                } else {
                    zzr.f6809a.zzay().zzc().zzb("Log and bundle disabled. package_name", str3);
                }
                bArr = new byte[0];
                return bArr;
            } finally {
                zzr.f7018b.zzi().zzx();
            }
        }
    }
}
