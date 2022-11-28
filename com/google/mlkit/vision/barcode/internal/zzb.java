package com.google.mlkit.vision.barcode.internal;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.mlkit_vision_barcode.zzbz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzki;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzla;
import com.google.android.gms.internal.mlkit_vision_barcode.zzmv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzmw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzmy;
import com.google.android.gms.internal.mlkit_vision_barcode.zznk;
import com.google.android.gms.internal.mlkit_vision_barcode.zznm;
import com.google.android.gms.internal.mlkit_vision_barcode.zznp;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public final class zzb {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final AtomicReference f10345a;
    private static final SparseArray zzb;
    private static final SparseArray zzc;
    @SuppressLint({"UseSparseArrays"})
    private static final Map zzd;

    static {
        SparseArray sparseArray = new SparseArray();
        zzb = sparseArray;
        SparseArray sparseArray2 = new SparseArray();
        zzc = sparseArray2;
        f10345a = new AtomicReference();
        sparseArray.put(-1, zzkv.FORMAT_UNKNOWN);
        sparseArray.put(1, zzkv.FORMAT_CODE_128);
        sparseArray.put(2, zzkv.FORMAT_CODE_39);
        sparseArray.put(4, zzkv.FORMAT_CODE_93);
        sparseArray.put(8, zzkv.FORMAT_CODABAR);
        sparseArray.put(16, zzkv.FORMAT_DATA_MATRIX);
        sparseArray.put(32, zzkv.FORMAT_EAN_13);
        sparseArray.put(64, zzkv.FORMAT_EAN_8);
        sparseArray.put(128, zzkv.FORMAT_ITF);
        sparseArray.put(256, zzkv.FORMAT_QR_CODE);
        sparseArray.put(512, zzkv.FORMAT_UPC_A);
        sparseArray.put(1024, zzkv.FORMAT_UPC_E);
        sparseArray.put(2048, zzkv.FORMAT_PDF417);
        sparseArray.put(4096, zzkv.FORMAT_AZTEC);
        sparseArray2.put(0, zzkw.TYPE_UNKNOWN);
        sparseArray2.put(1, zzkw.TYPE_CONTACT_INFO);
        sparseArray2.put(2, zzkw.TYPE_EMAIL);
        sparseArray2.put(3, zzkw.TYPE_ISBN);
        sparseArray2.put(4, zzkw.TYPE_PHONE);
        sparseArray2.put(5, zzkw.TYPE_PRODUCT);
        sparseArray2.put(6, zzkw.TYPE_SMS);
        sparseArray2.put(7, zzkw.TYPE_TEXT);
        sparseArray2.put(8, zzkw.TYPE_URL);
        sparseArray2.put(9, zzkw.TYPE_WIFI);
        sparseArray2.put(10, zzkw.TYPE_GEO);
        sparseArray2.put(11, zzkw.TYPE_CALENDAR_EVENT);
        sparseArray2.put(12, zzkw.TYPE_DRIVER_LICENSE);
        HashMap hashMap = new HashMap();
        zzd = hashMap;
        hashMap.put(1, zzmv.CODE_128);
        hashMap.put(2, zzmv.CODE_39);
        hashMap.put(4, zzmv.CODE_93);
        hashMap.put(8, zzmv.CODABAR);
        hashMap.put(16, zzmv.DATA_MATRIX);
        hashMap.put(32, zzmv.EAN_13);
        hashMap.put(64, zzmv.EAN_8);
        hashMap.put(128, zzmv.ITF);
        hashMap.put(256, zzmv.QR_CODE);
        hashMap.put(512, zzmv.UPC_A);
        hashMap.put(1024, zzmv.UPC_E);
        hashMap.put(2048, zzmv.PDF417);
        hashMap.put(4096, zzmv.AZTEC);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(zznm zznmVar, final zzkj zzkjVar) {
        zznmVar.zzb(new zznk() { // from class: com.google.mlkit.vision.barcode.internal.zza
            @Override // com.google.android.gms.internal.mlkit_vision_barcode.zznk
            public final zznp zza() {
                zzkj zzkjVar2 = zzkj.this;
                zzkl zzklVar = new zzkl();
                zzklVar.zze(zzb.b() ? zzki.TYPE_THICK : zzki.TYPE_THIN);
                zzla zzlaVar = new zzla();
                zzlaVar.zzb(zzkjVar2);
                zzklVar.zzh(zzlaVar.zzc());
                return zznp.zzd(zzklVar);
            }
        }, zzkk.ON_DEVICE_BARCODE_LOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b() {
        AtomicReference atomicReference = f10345a;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean a2 = zzl.a(MlKitContext.getInstance().getApplicationContext());
        atomicReference.set(Boolean.valueOf(a2));
        return a2;
    }

    public static zzkv zza(@Barcode.BarcodeFormat int i2) {
        zzkv zzkvVar = (zzkv) zzb.get(i2);
        return zzkvVar == null ? zzkv.FORMAT_UNKNOWN : zzkvVar;
    }

    public static zzkw zzb(@Barcode.BarcodeValueType int i2) {
        zzkw zzkwVar = (zzkw) zzc.get(i2);
        return zzkwVar == null ? zzkw.TYPE_UNKNOWN : zzkwVar;
    }

    public static zzmy zzc(BarcodeScannerOptions barcodeScannerOptions) {
        int zza = barcodeScannerOptions.zza();
        zzbz zzbzVar = new zzbz();
        if (zza == 0) {
            zzbzVar.zze(zzd.values());
        } else {
            for (Map.Entry entry : zzd.entrySet()) {
                if ((((Integer) entry.getKey()).intValue() & zza) != 0) {
                    zzbzVar.zzd((zzmv) entry.getValue());
                }
            }
        }
        zzmw zzmwVar = new zzmw();
        zzmwVar.zzb(zzbzVar.zzf());
        return zzmwVar.zzc();
    }

    public static String zzd() {
        return true != b() ? "play-services-mlkit-barcode-scanning" : "barcode-scanning";
    }
}
