package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class zznm {
    public static final /* synthetic */ int zza = 0;
    @Nullable
    private static zzcc zzb;
    private static final zzce zzc = zzce.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    private final String zzd;
    private final String zze;
    private final zznl zzf;
    private final SharedPrefManager zzg;
    private final Task zzh;
    private final Task zzi;
    private final String zzj;
    private final int zzk;
    private final Map zzl = new HashMap();
    private final Map zzm = new HashMap();

    public zznm(Context context, final SharedPrefManager sharedPrefManager, zznl zznlVar, final String str) {
        this.zzd = context.getPackageName();
        this.zze = CommonUtils.getAppVersion(context);
        this.zzg = sharedPrefManager;
        this.zzf = zznlVar;
        this.zzj = str;
        this.zzh = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zznj
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String str2 = str;
                int i2 = zznm.zza;
                return LibraryVersion.getInstance().getVersion(str2);
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzi = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzni
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzce zzceVar = zzc;
        this.zzk = zzceVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzceVar.get(str)) : -1;
    }

    @VisibleForTesting
    static long a(List list, double d2) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d2 / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    @NonNull
    private static synchronized zzcc zzg() {
        synchronized (zznm.class) {
            zzcc zzccVar = zzb;
            if (zzccVar != null) {
                return zzccVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzbz zzbzVar = new zzbz();
            for (int i2 = 0; i2 < locales.size(); i2++) {
                zzbzVar.zzd(CommonUtils.languageTagFromLocale(locales.get(i2)));
            }
            zzcc zzf = zzbzVar.zzf();
            zzb = zzf;
            return zzf;
        }
    }

    @WorkerThread
    private final String zzh() {
        return this.zzh.isSuccessful() ? (String) this.zzh.getResult() : LibraryVersion.getInstance().getVersion(this.zzj);
    }

    @WorkerThread
    private final boolean zzi(zzkk zzkkVar, long j2, long j3) {
        return this.zzl.get(zzkkVar) == null || j2 - ((Long) this.zzl.get(zzkkVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(zznp zznpVar, zzkk zzkkVar, String str) {
        zznpVar.zzf(zzkkVar);
        String zzb2 = zznpVar.zzb();
        zzmc zzmcVar = new zzmc();
        zzmcVar.zzb(this.zzd);
        zzmcVar.zzc(this.zze);
        zzmcVar.zzh(zzg());
        zzmcVar.zzg(Boolean.TRUE);
        zzmcVar.zzl(zzb2);
        zzmcVar.zzj(str);
        zzmcVar.zzi(this.zzi.isSuccessful() ? (String) this.zzi.getResult() : this.zzg.getMlSdkInstanceId());
        zzmcVar.zzd(10);
        zzmcVar.zzk(Integer.valueOf(this.zzk));
        zznpVar.zzg(zzmcVar);
        this.zzf.zza(zznpVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void c(zzkk zzkkVar, Object obj, long j2, com.google.mlkit.vision.barcode.internal.zzg zzgVar) {
        if (!this.zzm.containsKey(zzkkVar)) {
            this.zzm.put(zzkkVar, zzbh.zzr());
        }
        zzch zzchVar = (zzch) this.zzm.get(zzkkVar);
        zzchVar.zzo(obj, Long.valueOf(j2));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzi(zzkkVar, elapsedRealtime, 30L)) {
            this.zzl.put(zzkkVar, Long.valueOf(elapsedRealtime));
            for (Object obj2 : zzchVar.zzq()) {
                ArrayList<Long> arrayList = new ArrayList(zzchVar.zzc(obj2));
                Collections.sort(arrayList);
                zzjq zzjqVar = new zzjq();
                long j3 = 0;
                for (Long l2 : arrayList) {
                    j3 += l2.longValue();
                }
                zzjqVar.zza(Long.valueOf(j3 / arrayList.size()));
                zzjqVar.zzc(Long.valueOf(a(arrayList, 100.0d)));
                zzjqVar.zzf(Long.valueOf(a(arrayList, 75.0d)));
                zzjqVar.zzd(Long.valueOf(a(arrayList, 50.0d)));
                zzjqVar.zzb(Long.valueOf(a(arrayList, 25.0d)));
                zzjqVar.zze(Long.valueOf(a(arrayList, 0.0d)));
                zzf(zzgVar.zza(obj2, arrayList.size(), zzjqVar.zzg()), zzkkVar, zzh());
            }
            this.zzm.remove(zzkkVar);
        }
    }

    @WorkerThread
    public final void zzb(zznk zznkVar, zzkk zzkkVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzi(zzkkVar, elapsedRealtime, 30L)) {
            this.zzl.put(zzkkVar, Long.valueOf(elapsedRealtime));
            zzf(zznkVar.zza(), zzkkVar, zzh());
        }
    }

    public final void zze(zznp zznpVar, zzkk zzkkVar) {
        zzf(zznpVar, zzkkVar, zzh());
    }

    public final void zzf(final zznp zznpVar, final zzkk zzkkVar, final String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zznpVar, zzkkVar, str, null) { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zznh
            public final /* synthetic */ zzkk zzb;
            public final /* synthetic */ String zzc;
            public final /* synthetic */ zznp zzd;

            @Override // java.lang.Runnable
            public final void run() {
                zznm.this.b(this.zzd, this.zzb, this.zzc);
            }
        });
    }
}
