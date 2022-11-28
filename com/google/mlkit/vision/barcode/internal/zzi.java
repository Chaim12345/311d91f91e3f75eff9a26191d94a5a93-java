package com.google.mlkit.vision.barcode.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzbz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzdv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzdy;
import com.google.android.gms.internal.mlkit_vision_barcode.zzjs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzjv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzjw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzka;
import com.google.android.gms.internal.mlkit_vision_barcode.zzki;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkx;
import com.google.android.gms.internal.mlkit_vision_barcode.zznk;
import com.google.android.gms.internal.mlkit_vision_barcode.zznm;
import com.google.android.gms.internal.mlkit_vision_barcode.zzno;
import com.google.android.gms.internal.mlkit_vision_barcode.zznp;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.BitmapInStreamingChecker;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzi extends MLTask {
    private final BarcodeScannerOptions zzc;
    private final zzj zzd;
    private final zznm zze;
    private final zzno zzf;
    private final BitmapInStreamingChecker zzg = new BitmapInStreamingChecker();
    private boolean zzh;
    private static final ImageUtils zzb = ImageUtils.getInstance();
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    static boolean f10346b = true;

    public zzi(MlKitContext mlKitContext, BarcodeScannerOptions barcodeScannerOptions, zzj zzjVar, zznm zznmVar) {
        Preconditions.checkNotNull(mlKitContext, "MlKitContext can not be null");
        Preconditions.checkNotNull(barcodeScannerOptions, "BarcodeScannerOptions can not be null");
        this.zzc = barcodeScannerOptions;
        this.zzd = zzjVar;
        this.zze = zznmVar;
        this.zzf = zzno.zza(mlKitContext.getApplicationContext());
    }

    @WorkerThread
    private final void zzf(final zzkj zzkjVar, long j2, @NonNull final InputImage inputImage, @Nullable List list) {
        final zzbz zzbzVar = new zzbz();
        final zzbz zzbzVar2 = new zzbz();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Barcode barcode = (Barcode) it.next();
                zzbzVar.zzd(zzb.zza(barcode.getFormat()));
                zzbzVar2.zzd(zzb.zzb(barcode.getValueType()));
            }
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j2;
        this.zze.zzb(new zznk() { // from class: com.google.mlkit.vision.barcode.internal.zzh
            @Override // com.google.android.gms.internal.mlkit_vision_barcode.zznk
            public final zznp zza() {
                return zzi.this.c(elapsedRealtime, zzkjVar, zzbzVar, zzbzVar2, inputImage);
            }
        }, zzkk.ON_DEVICE_BARCODE_DETECT);
        zzdw zzdwVar = new zzdw();
        zzdwVar.zze(zzkjVar);
        zzdwVar.zzf(Boolean.valueOf(f10346b));
        zzdwVar.zzg(zzb.zzc(this.zzc));
        zzdwVar.zzc(zzbzVar.zzf());
        zzdwVar.zzd(zzbzVar2.zzf());
        final zzdy zzh = zzdwVar.zzh();
        final zzg zzgVar = new zzg(this);
        final zznm zznmVar = this.zze;
        final zzkk zzkkVar = zzkk.AGGREGATED_ON_DEVICE_BARCODE_DETECTION;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzkkVar, zzh, elapsedRealtime, zzgVar, null) { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzng
            public final /* synthetic */ zzkk zzb;
            public final /* synthetic */ Object zzc;
            public final /* synthetic */ long zzd;
            public final /* synthetic */ com.google.mlkit.vision.barcode.internal.zzg zze;

            @Override // java.lang.Runnable
            public final void run() {
                zznm.this.c(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
        long currentTimeMillis = System.currentTimeMillis();
        this.zzf.zzc(true != this.zzh ? 24301 : 24302, zzkjVar.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zznp c(long j2, zzkj zzkjVar, zzbz zzbzVar, zzbz zzbzVar2, InputImage inputImage) {
        zzkx zzkxVar = new zzkx();
        zzka zzkaVar = new zzka();
        zzkaVar.zzc(Long.valueOf(j2));
        zzkaVar.zzd(zzkjVar);
        zzkaVar.zze(Boolean.valueOf(f10346b));
        Boolean bool = Boolean.TRUE;
        zzkaVar.zza(bool);
        zzkaVar.zzb(bool);
        zzkxVar.zzh(zzkaVar.zzf());
        zzkxVar.zzi(zzb.zzc(this.zzc));
        zzkxVar.zze(zzbzVar.zzf());
        zzkxVar.zzf(zzbzVar2.zzf());
        int format = inputImage.getFormat();
        int mobileVisionImageSize = zzb.getMobileVisionImageSize(inputImage);
        zzjv zzjvVar = new zzjv();
        zzjvVar.zza(format != -1 ? format != 35 ? format != 842094169 ? format != 16 ? format != 17 ? zzjw.UNKNOWN_FORMAT : zzjw.NV21 : zzjw.NV16 : zzjw.YV12 : zzjw.YUV_420_888 : zzjw.BITMAP);
        zzjvVar.zzb(Integer.valueOf(mobileVisionImageSize));
        zzkxVar.zzg(zzjvVar.zzd());
        zzkl zzklVar = new zzkl();
        zzklVar.zze(this.zzh ? zzki.TYPE_THICK : zzki.TYPE_THIN);
        zzklVar.zzg(zzkxVar.zzj());
        return zznp.zzd(zzklVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zznp d(zzdy zzdyVar, int i2, zzjs zzjsVar) {
        zzkl zzklVar = new zzkl();
        zzklVar.zze(this.zzh ? zzki.TYPE_THICK : zzki.TYPE_THIN);
        zzdv zzdvVar = new zzdv();
        zzdvVar.zza(Integer.valueOf(i2));
        zzdvVar.zzc(zzdyVar);
        zzdvVar.zzb(zzjsVar);
        zzklVar.zzd(zzdvVar.zze());
        return zznp.zzd(zzklVar);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    @WorkerThread
    public final synchronized void load() {
        this.zzh = this.zzd.zzc();
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    @WorkerThread
    public final synchronized void release() {
        this.zzd.zzb();
        f10346b = true;
    }

    @Override // com.google.mlkit.common.sdkinternal.MLTask
    @WorkerThread
    /* renamed from: zzc */
    public final synchronized List run(@NonNull InputImage inputImage) {
        List zza;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.zzg.check(inputImage);
        try {
            zza = this.zzd.zza(inputImage);
            zzf(zzkj.NO_ERROR, elapsedRealtime, inputImage, zza);
            f10346b = false;
        } catch (MlKitException e2) {
            zzf(e2.getErrorCode() == 14 ? zzkj.MODEL_NOT_DOWNLOADED : zzkj.UNKNOWN_ERROR, elapsedRealtime, inputImage, null);
            throw e2;
        }
        return zza;
    }
}
