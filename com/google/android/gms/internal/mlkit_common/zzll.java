package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class zzll {
    public static final /* synthetic */ int zza = 0;
    @Nullable
    private static zzam zzb;
    private static final zzao zzc = zzao.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    private final String zzd;
    private final String zze;
    private final zzlk zzf;
    private final SharedPrefManager zzg;
    private final Task zzh;
    private final Task zzi;
    private final String zzj;
    private final int zzk;
    private final Map zzl = new HashMap();
    private final Map zzm = new HashMap();

    public zzll(Context context, final SharedPrefManager sharedPrefManager, zzlk zzlkVar, final String str) {
        this.zzd = context.getPackageName();
        this.zze = CommonUtils.getAppVersion(context);
        this.zzg = sharedPrefManager;
        this.zzf = zzlkVar;
        this.zzj = str;
        this.zzh = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzlj
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String str2 = str;
                int i2 = zzll.zza;
                return LibraryVersion.getInstance().getVersion(str2);
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzi = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzli
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzao zzaoVar = zzc;
        this.zzk = zzaoVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzaoVar.get(str)) : -1;
    }

    @NonNull
    private static synchronized zzam zzg() {
        synchronized (zzll.class) {
            zzam zzamVar = zzb;
            if (zzamVar != null) {
                return zzamVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzaj zzajVar = new zzaj();
            for (int i2 = 0; i2 < locales.size(); i2++) {
                zzajVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i2)));
            }
            zzam zzc2 = zzajVar.zzc();
            zzb = zzc2;
            return zzc2;
        }
    }

    private final zzjz zzh(String str, String str2) {
        zzjz zzjzVar = new zzjz();
        zzjzVar.zzb(this.zzd);
        zzjzVar.zzc(this.zze);
        zzjzVar.zzh(zzg());
        zzjzVar.zzg(Boolean.TRUE);
        zzjzVar.zzl(str);
        zzjzVar.zzj(str2);
        zzjzVar.zzi(this.zzi.isSuccessful() ? (String) this.zzi.getResult() : this.zzg.getMlSdkInstanceId());
        zzjzVar.zzd(10);
        zzjzVar.zzk(Integer.valueOf(this.zzk));
        return zzjzVar;
    }

    @WorkerThread
    private final String zzi() {
        return this.zzh.isSuccessful() ? (String) this.zzh.getResult() : LibraryVersion.getInstance().getVersion(this.zzj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void a(zzlc zzlcVar, zzie zzieVar, String str) {
        zzlcVar.zza(zzieVar);
        zzlcVar.zzc(zzh(zzlcVar.zzd(), str));
        this.zzf.zza(zzlcVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(zzlc zzlcVar, zzln zzlnVar, RemoteModel remoteModel) {
        zzlcVar.zza(zzie.MODEL_DOWNLOAD);
        zzlcVar.zzc(zzh(zzlnVar.zze(), zzi()));
        zzlcVar.zzb(zzlx.zza(remoteModel, this.zzg, zzlnVar));
        this.zzf.zza(zzlcVar);
    }

    public final void zzc(final zzlc zzlcVar, final zzie zzieVar) {
        final String zzi = zzi();
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzlg
            @Override // java.lang.Runnable
            public final void run() {
                zzll.this.a(zzlcVar, zzieVar, zzi);
            }
        });
    }

    public final void zzd(zzlc zzlcVar, RemoteModel remoteModel, boolean z, int i2) {
        zzlm zzh = zzln.zzh();
        zzh.zzf(false);
        zzh.zzd(remoteModel.getModelType());
        zzh.zza(zzij.FAILED);
        zzh.zzb(zzid.DOWNLOAD_FAILED);
        zzh.zzc(i2);
        zzf(zzlcVar, remoteModel, zzh.zzh());
    }

    public final void zze(zzlc zzlcVar, RemoteModel remoteModel, zzid zzidVar, boolean z, ModelType modelType, zzij zzijVar) {
        zzlm zzh = zzln.zzh();
        zzh.zzf(z);
        zzh.zzd(modelType);
        zzh.zzb(zzidVar);
        zzh.zza(zzijVar);
        zzf(zzlcVar, remoteModel, zzh.zzh());
    }

    public final void zzf(final zzlc zzlcVar, final RemoteModel remoteModel, final zzln zzlnVar) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzlh
            @Override // java.lang.Runnable
            public final void run() {
                zzll.this.b(zzlcVar, zzlnVar, remoteModel);
            }
        });
    }
}
