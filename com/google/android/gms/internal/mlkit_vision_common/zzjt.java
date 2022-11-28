package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class zzjt {
    public static final /* synthetic */ int zza = 0;
    @Nullable
    private static zzp zzb;
    private static final zzr zzc = zzr.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    private final String zzd;
    private final String zze;
    private final zzjs zzf;
    private final SharedPrefManager zzg;
    private final Task zzh;
    private final Task zzi;
    private final String zzj;
    private final int zzk;
    private final Map zzl = new HashMap();
    private final Map zzm = new HashMap();

    public zzjt(Context context, final SharedPrefManager sharedPrefManager, zzjs zzjsVar, final String str) {
        this.zzd = context.getPackageName();
        this.zze = CommonUtils.getAppVersion(context);
        this.zzg = sharedPrefManager;
        this.zzf = zzjsVar;
        this.zzj = str;
        this.zzh = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzjr
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String str2 = str;
                int i2 = zzjt.zza;
                return LibraryVersion.getInstance().getVersion(str2);
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzi = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzjq
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzr zzrVar = zzc;
        this.zzk = zzrVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzrVar.get(str)) : -1;
    }

    @NonNull
    private static synchronized zzp zzc() {
        synchronized (zzjt.class) {
            zzp zzpVar = zzb;
            if (zzpVar != null) {
                return zzpVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzm zzmVar = new zzm();
            for (int i2 = 0; i2 < locales.size(); i2++) {
                zzmVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i2)));
            }
            zzp zzc2 = zzmVar.zzc();
            zzb = zzc2;
            return zzc2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void a(zzju zzjuVar, zzgz zzgzVar, String str) {
        zzjuVar.zzd(zzgzVar);
        String zza2 = zzjuVar.zza();
        zzil zzilVar = new zzil();
        zzilVar.zzb(this.zzd);
        zzilVar.zzc(this.zze);
        zzilVar.zzh(zzc());
        zzilVar.zzg(Boolean.TRUE);
        zzilVar.zzl(zza2);
        zzilVar.zzj(str);
        zzilVar.zzi(this.zzi.isSuccessful() ? (String) this.zzi.getResult() : this.zzg.getMlSdkInstanceId());
        zzilVar.zzd(10);
        zzilVar.zzk(Integer.valueOf(this.zzk));
        zzjuVar.zze(zzilVar);
        this.zzf.zza(zzjuVar);
    }

    @WorkerThread
    public final void zzb(zzkd zzkdVar, final zzgz zzgzVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzl.get(zzgzVar) != null && elapsedRealtime - ((Long) this.zzl.get(zzgzVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.zzl.put(zzgzVar, Long.valueOf(elapsedRealtime));
        int i2 = zzkdVar.zza;
        int i3 = zzkdVar.zzb;
        int i4 = zzkdVar.zzc;
        int i5 = zzkdVar.zzd;
        int i6 = zzkdVar.zze;
        long j2 = zzkdVar.zzf;
        int i7 = zzkdVar.zzg;
        zzgr zzgrVar = new zzgr();
        zzgrVar.zzd(i2 != -1 ? i2 != 35 ? i2 != 842094169 ? i2 != 16 ? i2 != 17 ? zzgn.UNKNOWN_FORMAT : zzgn.NV21 : zzgn.NV16 : zzgn.YV12 : zzgn.YUV_420_888 : zzgn.BITMAP);
        zzgrVar.zzf(i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? zzgs.ANDROID_MEDIA_IMAGE : zzgs.FILEPATH : zzgs.BYTEBUFFER : zzgs.BYTEARRAY : zzgs.BITMAP);
        zzgrVar.zzc(Integer.valueOf(i4));
        zzgrVar.zze(Integer.valueOf(i5));
        zzgrVar.zzg(Integer.valueOf(i6));
        zzgrVar.zzb(Long.valueOf(j2));
        zzgrVar.zzh(Integer.valueOf(i7));
        zzgu zzj = zzgrVar.zzj();
        zzha zzhaVar = new zzha();
        zzhaVar.zzd(zzj);
        final zzju zzc2 = zzju.zzc(zzhaVar);
        final String version = this.zzh.isSuccessful() ? (String) this.zzh.getResult() : LibraryVersion.getInstance().getVersion(this.zzj);
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzc2, zzgzVar, version, null) { // from class: com.google.android.gms.internal.mlkit_vision_common.zzjp
            public final /* synthetic */ zzgz zzb;
            public final /* synthetic */ String zzc;
            public final /* synthetic */ zzju zzd;

            @Override // java.lang.Runnable
            public final void run() {
                zzjt.this.a(this.zzd, this.zzb, this.zzc);
            }
        });
    }
}
