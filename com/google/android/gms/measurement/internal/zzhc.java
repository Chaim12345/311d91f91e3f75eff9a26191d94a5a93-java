package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.BinderThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/* loaded from: classes2.dex */
public final class zzhc extends zzep {
    private final zzll zza;
    private Boolean zzb;
    private String zzc;

    public zzhc(zzll zzllVar, String str) {
        Preconditions.checkNotNull(zzllVar);
        this.zza = zzllVar;
        this.zzc = null;
    }

    private final void zzA(zzaw zzawVar, zzq zzqVar) {
        this.zza.a();
        this.zza.d(zzawVar, zzqVar);
    }

    @BinderThread
    private final void zzy(zzq zzqVar, boolean z) {
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzz(zzqVar.zza, false);
        this.zza.zzv().q(zzqVar.zzb, zzqVar.zzq);
    }

    @BinderThread
    private final void zzz(String str, boolean z) {
        boolean z2;
        if (TextUtils.isEmpty(str)) {
            this.zza.zzay().zzd().zza("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzb == null) {
                    if (!"com.google.android.gms".equals(this.zzc) && !UidVerifier.isGooglePlayServicesUid(this.zza.zzau(), Binder.getCallingUid()) && !GoogleSignatureVerifier.getInstance(this.zza.zzau()).isUidGoogleSigned(Binder.getCallingUid())) {
                        z2 = false;
                        this.zzb = Boolean.valueOf(z2);
                    }
                    z2 = true;
                    this.zzb = Boolean.valueOf(z2);
                }
                if (this.zzb.booleanValue()) {
                    return;
                }
            } catch (SecurityException e2) {
                this.zza.zzay().zzd().zzb("Measurement Service called with invalid calling package. appId", zzfa.g(str));
                throw e2;
            }
        }
        if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzau(), Binder.getCallingUid(), str)) {
            this.zzc = str;
        }
        if (str.equals(this.zzc)) {
            return;
        }
        throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public final zzaw b(zzaw zzawVar, zzq zzqVar) {
        zzau zzauVar;
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzawVar.zza) && (zzauVar = zzawVar.zzb) != null && zzauVar.zza() != 0) {
            String e2 = zzawVar.zzb.e("_cis");
            if ("referrer broadcast".equals(e2) || "referrer API".equals(e2)) {
                this.zza.zzay().zzi().zzb("Event has been filtered ", zzawVar.toString());
                return new zzaw("_cmpx", zzawVar.zzb, zzawVar.zzc, zzawVar.zzd);
            }
        }
        return zzawVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(zzaw zzawVar, zzq zzqVar) {
        zzey zzj;
        String str;
        String str2;
        if (!this.zza.zzo().zzo(zzqVar.zza)) {
            zzA(zzawVar, zzqVar);
            return;
        }
        this.zza.zzay().zzj().zzb("EES config found for", zzqVar.zza);
        zzgb zzo = this.zza.zzo();
        String str3 = zzqVar.zza;
        com.google.android.gms.internal.measurement.zzc zzcVar = TextUtils.isEmpty(str3) ? null : (com.google.android.gms.internal.measurement.zzc) zzo.f6750f.get(str3);
        if (zzcVar != null) {
            try {
                Map s2 = this.zza.zzu().s(zzawVar.zzb.zzc(), true);
                String zza = zzhh.zza(zzawVar.zza);
                if (zza == null) {
                    zza = zzawVar.zza;
                }
                if (zzcVar.zze(new com.google.android.gms.internal.measurement.zzaa(zza, zzawVar.zzd, s2))) {
                    if (zzcVar.zzg()) {
                        this.zza.zzay().zzj().zzb("EES edited event", zzawVar.zza);
                        zzawVar = this.zza.zzu().k(zzcVar.zza().zzb());
                    }
                    zzA(zzawVar, zzqVar);
                    if (zzcVar.zzf()) {
                        for (com.google.android.gms.internal.measurement.zzaa zzaaVar : zzcVar.zza().zzc()) {
                            this.zza.zzay().zzj().zzb("EES logging created event", zzaaVar.zzd());
                            zzA(this.zza.zzu().k(zzaaVar), zzqVar);
                        }
                        return;
                    }
                    return;
                }
            } catch (com.google.android.gms.internal.measurement.zzd unused) {
                this.zza.zzay().zzd().zzc("EES error. appId, eventName", zzqVar.zzb, zzawVar.zza);
            }
            zzj = this.zza.zzay().zzj();
            str = zzawVar.zza;
            str2 = "EES was not applied to event";
        } else {
            zzj = this.zza.zzay().zzj();
            str = zzqVar.zza;
            str2 = "EES not loaded for";
        }
        zzj.zzb(str2, str);
        zzA(zzawVar, zzqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void e(String str, Bundle bundle) {
        zzam zzi = this.zza.zzi();
        zzi.zzg();
        zzi.a();
        byte[] zzby = zzi.f7018b.zzu().l(new zzar(zzi.f6809a, "", str, "dep", 0L, 0L, bundle)).zzby();
        zzi.f6809a.zzay().zzj().zzc("Saving default event parameters, appId, data size", zzi.f6809a.zzj().d(str), Integer.valueOf(zzby.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", zzby);
        try {
            if (zzi.p().insertWithOnConflict("default_event_params", null, contentValues, 5) == -1) {
                zzi.f6809a.zzay().zzd().zzb("Failed to insert default event parameters (got -1). appId", zzfa.g(str));
            }
        } catch (SQLiteException e2) {
            zzi.f6809a.zzay().zzd().zzc("Error storing default event parameters. appId", zzfa.g(str), e2);
        }
    }

    @VisibleForTesting
    final void f(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzaz().zzs()) {
            runnable.run();
        } else {
            this.zza.zzaz().zzp(runnable);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final String zzd(zzq zzqVar) {
        zzy(zzqVar, false);
        return this.zza.G(zzqVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final List zze(zzq zzqVar, boolean z) {
        zzy(zzqVar, false);
        String str = zzqVar.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zzlq> list = (List) this.zza.zzaz().zzh(new zzgz(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzlq zzlqVar : list) {
                if (z || !zzlt.B(zzlqVar.f7044c)) {
                    arrayList.add(new zzlo(zzlqVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzay().zzd().zzc("Failed to get user properties. appId", zzfa.g(zzqVar.zza), e2);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final List zzf(String str, String str2, zzq zzqVar) {
        zzy(zzqVar, false);
        String str3 = zzqVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzaz().zzh(new zzgq(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzay().zzd().zzb("Failed to get conditional user properties", e2);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final List zzg(String str, String str2, String str3) {
        zzz(str, true);
        try {
            return (List) this.zza.zzaz().zzh(new zzgr(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzay().zzd().zzb("Failed to get conditional user properties as", e2);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final List zzh(String str, String str2, boolean z, zzq zzqVar) {
        zzy(zzqVar, false);
        String str3 = zzqVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zzlq> list = (List) this.zza.zzaz().zzh(new zzgo(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzlq zzlqVar : list) {
                if (z || !zzlt.B(zzlqVar.f7044c)) {
                    arrayList.add(new zzlo(zzlqVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzay().zzd().zzc("Failed to query user properties. appId", zzfa.g(zzqVar.zza), e2);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final List zzi(String str, String str2, String str3, boolean z) {
        zzz(str, true);
        try {
            List<zzlq> list = (List) this.zza.zzaz().zzh(new zzgp(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzlq zzlqVar : list) {
                if (z || !zzlt.B(zzlqVar.f7044c)) {
                    arrayList.add(new zzlo(zzlqVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzay().zzd().zzc("Failed to get user properties as. appId", zzfa.g(str), e2);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzj(zzq zzqVar) {
        zzy(zzqVar, false);
        f(new zzha(this, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzk(zzaw zzawVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzawVar);
        zzy(zzqVar, false);
        f(new zzgv(this, zzawVar, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzl(zzaw zzawVar, String str, String str2) {
        Preconditions.checkNotNull(zzawVar);
        Preconditions.checkNotEmpty(str);
        zzz(str, true);
        f(new zzgw(this, zzawVar, str));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzm(zzq zzqVar) {
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzz(zzqVar.zza, false);
        f(new zzgs(this, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzn(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotNull(zzacVar.zzc);
        zzy(zzqVar, false);
        zzac zzacVar2 = new zzac(zzacVar);
        zzacVar2.zza = zzqVar.zza;
        f(new zzgm(this, zzacVar2, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzo(zzac zzacVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zza);
        zzz(zzacVar.zza, true);
        f(new zzgn(this, new zzac(zzacVar)));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzp(zzq zzqVar) {
        Preconditions.checkNotEmpty(zzqVar.zza);
        Preconditions.checkNotNull(zzqVar.zzv);
        zzgu zzguVar = new zzgu(this, zzqVar);
        Preconditions.checkNotNull(zzguVar);
        if (this.zza.zzaz().zzs()) {
            zzguVar.run();
        } else {
            this.zza.zzaz().zzq(zzguVar);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzq(long j2, String str, String str2, String str3) {
        f(new zzhb(this, str2, str3, str, j2));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzr(final Bundle bundle, zzq zzqVar) {
        zzy(zzqVar, false);
        final String str = zzqVar.zza;
        Preconditions.checkNotNull(str);
        f(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzgl
            @Override // java.lang.Runnable
            public final void run() {
                zzhc.this.e(str, bundle);
            }
        });
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzs(zzq zzqVar) {
        zzy(zzqVar, false);
        f(new zzgt(this, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final void zzt(zzlo zzloVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzloVar);
        zzy(zzqVar, false);
        f(new zzgy(this, zzloVar, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    @BinderThread
    public final byte[] zzu(zzaw zzawVar, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzawVar);
        zzz(str, true);
        this.zza.zzay().zzc().zzb("Log and bundle. event", this.zza.zzj().d(zzawVar.zza));
        long nanoTime = this.zza.zzav().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzaz().zzi(new zzgx(this, zzawVar, str)).get();
            if (bArr == null) {
                this.zza.zzay().zzd().zzb("Log and bundle returned null. appId", zzfa.g(str));
                bArr = new byte[0];
            }
            this.zza.zzay().zzc().zzd("Log and bundle processed. event, size, time_ms", this.zza.zzj().d(zzawVar.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzav().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzay().zzd().zzd("Failed to log and bundle. appId, event, error", zzfa.g(str), this.zza.zzj().d(zzawVar.zza), e2);
            return null;
        }
    }
}
