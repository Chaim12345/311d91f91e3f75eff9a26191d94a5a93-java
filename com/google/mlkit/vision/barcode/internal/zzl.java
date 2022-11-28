package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import android.media.Image;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcc;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkj;
import com.google.android.gms.internal.mlkit_vision_barcode.zznm;
import com.google.android.gms.internal.mlkit_vision_barcode.zzon;
import com.google.android.gms.internal.mlkit_vision_barcode.zzop;
import com.google.android.gms.internal.mlkit_vision_barcode.zzox;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpg;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzl implements zzj {
    private static final zzcc zza = zzcc.zzi("com.google.android.gms.vision.barcode", OptionalModuleUtils.TFLITE_DYNAMITE_MODULE_ID);
    private boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private final Context zze;
    private final BarcodeScannerOptions zzf;
    private final zznm zzg;
    @Nullable
    private zzox zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(Context context, BarcodeScannerOptions barcodeScannerOptions, zznm zznmVar) {
        this.zze = context;
        this.zzf = barcodeScannerOptions;
        this.zzg = zznmVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > 0;
    }

    @VisibleForTesting
    final zzox b(DynamiteModule.VersionPolicy versionPolicy, String str, String str2) {
        return zzoz.zza(DynamiteModule.load(this.zze, versionPolicy, str).instantiate(str2)).zzd(ObjectWrapper.wrap(this.zze), new zzop(this.zzf.zza()));
    }

    @Override // com.google.mlkit.vision.barcode.internal.zzj
    @WorkerThread
    public final List zza(InputImage inputImage) {
        if (this.zzh == null) {
            zzc();
        }
        zzox zzoxVar = (zzox) Preconditions.checkNotNull(this.zzh);
        if (!this.zzb) {
            try {
                zzoxVar.zze();
                this.zzb = true;
            } catch (RemoteException e2) {
                throw new MlKitException("Failed to init barcode scanner.", 13, e2);
            }
        }
        int width = inputImage.getWidth();
        if (inputImage.getFormat() == 35) {
            width = ((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getRowStride();
        }
        try {
            List<zzon> zzd = zzoxVar.zzd(ImageUtils.getInstance().getImageDataWrapper(inputImage), new zzpg(inputImage.getFormat(), width, inputImage.getHeight(), CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()), SystemClock.elapsedRealtime()));
            ArrayList arrayList = new ArrayList();
            for (zzon zzonVar : zzd) {
                arrayList.add(new Barcode(new zzk(zzonVar), inputImage.getCoordinatesMatrix()));
            }
            return arrayList;
        } catch (RemoteException e3) {
            throw new MlKitException("Failed to run barcode scanner.", 13, e3);
        }
    }

    @Override // com.google.mlkit.vision.barcode.internal.zzj
    @WorkerThread
    public final void zzb() {
        zzox zzoxVar = this.zzh;
        if (zzoxVar != null) {
            try {
                zzoxVar.zzf();
            } catch (RemoteException e2) {
                Log.e("DecoupledBarcodeScanner", "Failed to release barcode scanner.", e2);
            }
            this.zzh = null;
            this.zzb = false;
        }
    }

    @Override // com.google.mlkit.vision.barcode.internal.zzj
    @WorkerThread
    public final boolean zzc() {
        if (this.zzh != null) {
            return this.zzc;
        }
        if (a(this.zze)) {
            this.zzc = true;
            try {
                this.zzh = b(DynamiteModule.PREFER_LOCAL, ModuleDescriptor.MODULE_ID, "com.google.mlkit.vision.barcode.bundled.internal.ThickBarcodeScannerCreator");
            } catch (RemoteException e2) {
                throw new MlKitException("Failed to create thick barcode scanner.", 13, e2);
            } catch (DynamiteModule.LoadingException e3) {
                throw new MlKitException("Failed to load the bundled barcode module.", 13, e3);
            }
        } else {
            this.zzc = false;
            if (!OptionalModuleUtils.areAllRequiredModulesAvailable(this.zze, zza)) {
                if (!this.zzd) {
                    OptionalModuleUtils.requestDownload(this.zze, zzcc.zzi(OptionalModuleUtils.BARCODE, OptionalModuleUtils.TFLITE_DYNAMITE));
                    this.zzd = true;
                }
                zzb.a(this.zzg, zzkj.OPTIONAL_MODULE_NOT_AVAILABLE);
                throw new MlKitException("Waiting for the barcode module to be downloaded. Please wait.", 14);
            }
            try {
                this.zzh = b(DynamiteModule.PREFER_REMOTE, "com.google.android.gms.vision.barcode", "com.google.android.gms.vision.barcode.mlkit.BarcodeScannerCreator");
            } catch (RemoteException | DynamiteModule.LoadingException e4) {
                zzb.a(this.zzg, zzkj.OPTIONAL_MODULE_INIT_ERROR);
                throw new MlKitException("Failed to create thin barcode scanner.", 13, e4);
            }
        }
        zzb.a(this.zzg, zzkj.NO_ERROR);
        return this.zzc;
    }
}
