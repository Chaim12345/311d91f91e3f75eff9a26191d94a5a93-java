package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.Image;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaq;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzar;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzas;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzau;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzav;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzax;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzay;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaz;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbk;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbu;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.libraries.barhopper.BarhopperV3;
import com.google.android.libraries.barhopper.RecognitionOptions;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.barhopper.deeplearning.zze;
import com.google.barhopper.deeplearning.zzf;
import com.google.barhopper.deeplearning.zzh;
import com.google.barhopper.deeplearning.zzi;
import com.google.barhopper.deeplearning.zzk;
import com.google.barhopper.deeplearning.zzm;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import com.google.photos.vision.barhopper.zzac;
import com.google.photos.vision.barhopper.zzad;
import com.google.photos.vision.barhopper.zzae;
import com.google.photos.vision.barhopper.zzag;
import com.google.photos.vision.barhopper.zzaj;
import com.google.photos.vision.barhopper.zzb;
import com.google.photos.vision.barhopper.zzc;
import com.google.photos.vision.barhopper.zzn;
import com.google.photos.vision.barhopper.zzp;
import com.google.photos.vision.barhopper.zzr;
import com.google.photos.vision.barhopper.zzt;
import com.google.photos.vision.barhopper.zzy;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
final class zza extends zzbk {
    private static final int[] zza = {5, 7, 7, 7, 5, 5};
    private static final double[][] zzb = {new double[]{0.075d, 1.0d}, new double[]{0.1d, 1.0d}, new double[]{0.125d, 1.0d}, new double[]{0.2d, 2.0d}, new double[]{0.2d, 0.5d}, new double[]{0.15d, 1.0d}, new double[]{0.2d, 1.0d}, new double[]{0.25d, 1.0d}, new double[]{0.35d, 2.0d}, new double[]{0.35d, 0.5d}, new double[]{0.35d, 3.0d}, new double[]{0.35d, 0.3333d}, new double[]{0.3d, 1.0d}, new double[]{0.4d, 1.0d}, new double[]{0.5d, 1.0d}, new double[]{0.5d, 2.0d}, new double[]{0.5d, 0.5d}, new double[]{0.5d, 3.0d}, new double[]{0.5d, 0.3333d}, new double[]{0.6d, 1.0d}, new double[]{0.8d, 1.0d}, new double[]{1.0d, 1.0d}, new double[]{0.65d, 2.0d}, new double[]{0.65d, 0.5d}, new double[]{0.65d, 3.0d}, new double[]{0.65d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.8d, 2.0d}, new double[]{0.8d, 0.5d}, new double[]{0.8d, 3.0d}, new double[]{0.8d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.95d, 2.0d}, new double[]{0.95d, 0.5d}, new double[]{0.95d, 3.0d}, new double[]{0.95d, 0.3333d}};
    private final Context zzc;
    private final RecognitionOptions zzd;
    @Nullable
    private BarhopperV3 zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(Context context, zzbc zzbcVar) {
        RecognitionOptions recognitionOptions = new RecognitionOptions();
        this.zzd = recognitionOptions;
        this.zzc = context;
        recognitionOptions.setBarcodeFormats(zzbcVar.zza());
    }

    @Nullable
    private static zzap zze(@Nullable zzn zznVar, @Nullable String str, String str2) {
        if (zznVar == null || str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str);
        return new zzap(zznVar.zzf(), zznVar.zzd(), zznVar.zza(), zznVar.zzb(), zznVar.zzc(), zznVar.zze(), zznVar.zzj(), matcher.find() ? matcher.group(1) : null);
    }

    private final BarhopperProto$BarhopperResponse zzf(ByteBuffer byteBuffer, zzbu zzbuVar) {
        BarhopperV3 barhopperV3 = (BarhopperV3) Preconditions.checkNotNull(this.zze);
        if (((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).isDirect()) {
            return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), byteBuffer, this.zzd);
        }
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), byteBuffer.array(), this.zzd);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), bArr, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final List zzb(IObjectWrapper iObjectWrapper, zzbu zzbuVar) {
        BarhopperProto$BarhopperResponse recognize;
        zzat zzatVar;
        zzaw zzawVar;
        zzax zzaxVar;
        zzaz zzazVar;
        zzay zzayVar;
        zzau zzauVar;
        zzaq zzaqVar;
        int i2;
        zzar zzarVar;
        zzas zzasVar;
        int i3;
        Point[] pointArr;
        int i4;
        int i5;
        zzaw[] zzawVarArr;
        zzat[] zzatVarArr;
        zzao[] zzaoVarArr;
        ByteBuffer byteBuffer;
        int zzb2 = zzbuVar.zzb();
        int i6 = -1;
        int i7 = 0;
        if (zzb2 != -1) {
            if (zzb2 != 17) {
                if (zzb2 == 35) {
                    byteBuffer = ((Image) Preconditions.checkNotNull((Image) ObjectWrapper.unwrap(iObjectWrapper))).getPlanes()[0].getBuffer();
                    recognize = zzf(byteBuffer, zzbuVar);
                } else if (zzb2 != 842094169) {
                    int zzb3 = zzbuVar.zzb();
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unsupported image format: ");
                    sb.append(zzb3);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            byteBuffer = (ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper);
            recognize = zzf(byteBuffer, zzbuVar);
        } else {
            recognize = ((BarhopperV3) Preconditions.checkNotNull(this.zze)).recognize((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), this.zzd);
        }
        ArrayList arrayList = new ArrayList();
        Matrix uprightRotationMatrix = ImageUtils.getInstance().getUprightRotationMatrix(zzbuVar.zzd(), zzbuVar.zza(), zzbuVar.zzc());
        for (zzc zzcVar : recognize.zzc()) {
            if (zzcVar.zza() > 0 && uprightRotationMatrix != null) {
                float[] fArr = new float[8];
                List zzo = zzcVar.zzo();
                int zza2 = zzcVar.zza();
                for (int i8 = i7; i8 < zza2; i8++) {
                    int i9 = i8 + i8;
                    fArr[i9] = ((zzae) zzo.get(i8)).zza();
                    fArr[i9 + 1] = ((zzae) zzo.get(i8)).zzb();
                }
                uprightRotationMatrix.mapPoints(fArr);
                int zzc = zzbuVar.zzc();
                for (int i10 = i7; i10 < zza2; i10++) {
                    zzb zzbVar = (zzb) zzcVar.zzG();
                    int i11 = i10 + i10;
                    zzad zzc2 = zzae.zzc();
                    zzc2.zza((int) fArr[i11]);
                    zzc2.zzb((int) fArr[i11 + 1]);
                    zzbVar.zza((i10 + zzc) % zza2, (zzae) zzc2.zzl());
                    zzcVar = (zzc) zzbVar.zzl();
                }
            }
            if (zzcVar.zzt()) {
                zzy zzh = zzcVar.zzh();
                zzatVar = new zzat(zzh.zzf() + i6, zzh.zzc(), zzh.zze(), zzh.zzd());
            } else {
                zzatVar = null;
            }
            if (zzcVar.zzv()) {
                zzci zzb4 = zzcVar.zzb();
                zzawVar = new zzaw(zzb4.zzd() + i6, zzb4.zzc());
            } else {
                zzawVar = null;
            }
            if (zzcVar.zzw()) {
                zzag zzj = zzcVar.zzj();
                zzaxVar = new zzax(zzj.zzc(), zzj.zzd());
            } else {
                zzaxVar = null;
            }
            if (zzcVar.zzy()) {
                com.google.photos.vision.barhopper.zzao zzl = zzcVar.zzl();
                zzazVar = new zzaz(zzl.zzd(), zzl.zzc(), zzl.zze() + i6);
            } else {
                zzazVar = null;
            }
            if (zzcVar.zzx()) {
                zzaj zzk = zzcVar.zzk();
                zzayVar = new zzay(zzk.zzc(), zzk.zzd());
            } else {
                zzayVar = null;
            }
            if (zzcVar.zzu()) {
                zzac zzi = zzcVar.zzi();
                zzauVar = new zzau(zzi.zza(), zzi.zzb());
            } else {
                zzauVar = null;
            }
            if (zzcVar.zzq()) {
                zzp zzd = zzcVar.zzd();
                zzaqVar = new zzaq(zzd.zzj(), zzd.zze(), zzd.zzf(), zzd.zzh(), zzd.zzi(), zze(zzd.zzb(), zzcVar.zzm().zzn() ? zzcVar.zzm().zzv() : null, "DTSTART:([0-9TZ]*)"), zze(zzd.zza(), zzcVar.zzm().zzn() ? zzcVar.zzm().zzv() : null, "DTEND:([0-9TZ]*)"));
            } else {
                zzaqVar = null;
            }
            if (zzcVar.zzr()) {
                zzr zze = zzcVar.zze();
                zzcd zza3 = zze.zza();
                zzav zzavVar = zza3 != null ? new zzav(zza3.zzd(), zza3.zzi(), zza3.zzh(), zza3.zzc(), zza3.zzf(), zza3.zze(), zza3.zzj()) : null;
                String zzd2 = zze.zzd();
                String zze2 = zze.zze();
                List zzi2 = zze.zzi();
                if (zzi2.isEmpty()) {
                    zzawVarArr = null;
                } else {
                    zzaw[] zzawVarArr2 = new zzaw[zzi2.size()];
                    for (int i12 = 0; i12 < zzi2.size(); i12++) {
                        zzawVarArr2[i12] = new zzaw(((zzci) zzi2.get(i12)).zzd() + i6, ((zzci) zzi2.get(i12)).zzc());
                    }
                    zzawVarArr = zzawVarArr2;
                }
                List zzh2 = zze.zzh();
                if (zzh2.isEmpty()) {
                    zzatVarArr = null;
                } else {
                    zzat[] zzatVarArr2 = new zzat[zzh2.size()];
                    int i13 = 0;
                    while (i13 < zzh2.size()) {
                        zzatVarArr2[i13] = new zzat(((zzy) zzh2.get(i13)).zzf() + i6, ((zzy) zzh2.get(i13)).zzc(), ((zzy) zzh2.get(i13)).zze(), ((zzy) zzh2.get(i13)).zzd());
                        i13++;
                        i6 = -1;
                    }
                    zzatVarArr = zzatVarArr2;
                }
                String[] strArr = (String[]) zze.zzj().toArray(new String[0]);
                List zzf = zze.zzf();
                if (zzf.isEmpty()) {
                    i2 = 0;
                    zzaoVarArr = null;
                } else {
                    zzao[] zzaoVarArr2 = new zzao[zzf.size()];
                    for (int i14 = 0; i14 < zzf.size(); i14++) {
                        zzaoVarArr2[i14] = new zzao(((zzcb) zzf.get(i14)).zzc() - 1, (String[]) ((zzcb) zzf.get(i14)).zzb().toArray(new String[0]));
                    }
                    i2 = 0;
                    zzaoVarArr = zzaoVarArr2;
                }
                zzarVar = new zzar(zzavVar, zzd2, zze2, zzawVarArr, zzatVarArr, strArr, zzaoVarArr);
            } else {
                i2 = 0;
                zzarVar = null;
            }
            if (zzcVar.zzs()) {
                zzt zzf2 = zzcVar.zzf();
                zzasVar = new zzas(zzf2.zzi(), zzf2.zzk(), zzf2.zzq(), zzf2.zzo(), zzf2.zzl(), zzf2.zze(), zzf2.zzc(), zzf2.zzd(), zzf2.zzf(), zzf2.zzp(), zzf2.zzm(), zzf2.zzj(), zzf2.zzh(), zzf2.zzn());
            } else {
                zzasVar = null;
            }
            switch (zzcVar.zzz() - 1) {
                case 0:
                    i3 = i2;
                    break;
                case 1:
                    i3 = 1;
                    break;
                case 2:
                    i3 = 2;
                    break;
                case 3:
                    i3 = 4;
                    break;
                case 4:
                    i3 = 8;
                    break;
                case 5:
                    i3 = 16;
                    break;
                case 6:
                    i3 = 32;
                    break;
                case 7:
                    i3 = 64;
                    break;
                case 8:
                    i3 = 128;
                    break;
                case 9:
                    i3 = 256;
                    break;
                case 10:
                    i3 = 512;
                    break;
                case 11:
                    i3 = 1024;
                    break;
                case 12:
                    i3 = 2048;
                    break;
                case 13:
                    i3 = 4096;
                    break;
                default:
                    i3 = -1;
                    break;
            }
            String zzn = zzcVar.zzn();
            String zzv = zzcVar.zzm().zzn() ? zzcVar.zzm().zzv() : null;
            byte[] zzy = zzcVar.zzm().zzy();
            List zzo2 = zzcVar.zzo();
            if (zzo2.isEmpty()) {
                pointArr = null;
            } else {
                Point[] pointArr2 = new Point[zzo2.size()];
                for (int i15 = i2; i15 < zzo2.size(); i15++) {
                    pointArr2[i15] = new Point(((zzae) zzo2.get(i15)).zza(), ((zzae) zzo2.get(i15)).zzb());
                }
                pointArr = pointArr2;
            }
            switch (zzcVar.zzA() - 1) {
                case 1:
                    i4 = 1;
                    continue;
                    arrayList.add(new zzba(i3, zzn, zzv, zzy, pointArr, i4, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i6 = -1;
                    i7 = i2;
                case 2:
                    i4 = 2;
                    continue;
                    arrayList.add(new zzba(i3, zzn, zzv, zzy, pointArr, i4, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i6 = -1;
                    i7 = i2;
                case 3:
                    i5 = 3;
                    break;
                case 4:
                    i4 = 4;
                    continue;
                    arrayList.add(new zzba(i3, zzn, zzv, zzy, pointArr, i4, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i6 = -1;
                    i7 = i2;
                case 5:
                    i5 = 5;
                    break;
                case 6:
                    i5 = 6;
                    break;
                case 7:
                    i5 = 7;
                    break;
                case 8:
                    i4 = 8;
                    continue;
                    arrayList.add(new zzba(i3, zzn, zzv, zzy, pointArr, i4, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i6 = -1;
                    i7 = i2;
                case 9:
                    i5 = 9;
                    break;
                case 10:
                    i5 = 10;
                    break;
                case 11:
                    i5 = 11;
                    break;
                case 12:
                    i5 = 12;
                    break;
                default:
                    i4 = i2;
                    continue;
                    arrayList.add(new zzba(i3, zzn, zzv, zzy, pointArr, i4, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i6 = -1;
                    i7 = i2;
            }
            i4 = i5;
            arrayList.add(new zzba(i3, zzn, zzv, zzy, pointArr, i4, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
            i6 = -1;
            i7 = i2;
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final void zzc() {
        if (this.zze != null) {
            return;
        }
        this.zze = new BarhopperV3();
        zzh zza2 = zzi.zza();
        zze zza3 = zzf.zza();
        int i2 = 16;
        int i3 = 0;
        for (int i4 = 0; i4 < 6; i4++) {
            com.google.barhopper.deeplearning.zzb zza4 = com.google.barhopper.deeplearning.zzc.zza();
            zza4.zzc(i2);
            zza4.zzd(i2);
            for (int i5 = 0; i5 < zza[i4]; i5++) {
                double[] dArr = zzb[i3];
                float f2 = (float) (dArr[0] * 320.0d);
                float sqrt = (float) Math.sqrt(dArr[1]);
                zza4.zza(f2 / sqrt);
                zza4.zzb(f2 * sqrt);
                i3++;
            }
            i2 += i2;
            zza3.zza(zza4);
        }
        zza2.zza(zza3);
        try {
            InputStream open = this.zzc.getAssets().open("mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite");
            InputStream open2 = this.zzc.getAssets().open("mlkit_barcode_models/oned_auto_regressor_mobile.tflite");
            try {
                InputStream open3 = this.zzc.getAssets().open("mlkit_barcode_models/oned_feature_extractor_mobile.tflite");
                zzk zza5 = BarhopperV3Options.zza();
                zza2.zzb(zzdb.zzt(open));
                zza5.zza(zza2);
                zzm zza6 = com.google.barhopper.deeplearning.zzn.zza();
                zza6.zza(zzdb.zzt(open2));
                zza6.zzb(zzdb.zzt(open3));
                zza5.zzb(zza6);
                ((BarhopperV3) Preconditions.checkNotNull(this.zze)).create(zza5.zzl());
                if (open3 != null) {
                    open3.close();
                }
                if (open2 != null) {
                    open2.close();
                }
                if (open != null) {
                    open.close();
                }
            } catch (Throwable th) {
                if (open2 != null) {
                    try {
                        open2.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Failed to open Barcode models", e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final void zzd() {
        BarhopperV3 barhopperV3 = this.zze;
        if (barhopperV3 != null) {
            barhopperV3.close();
            this.zze = null;
        }
    }
}
