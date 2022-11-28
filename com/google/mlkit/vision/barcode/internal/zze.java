package com.google.mlkit.vision.barcode.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode.zznx;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
/* loaded from: classes2.dex */
public final class zze {
    private final zzf zza;
    private final ExecutorSelector zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(zzf zzfVar, ExecutorSelector executorSelector) {
        this.zza = zzfVar;
        this.zzb = executorSelector;
    }

    public final BarcodeScannerImpl zza() {
        BarcodeScannerOptions barcodeScannerOptions;
        barcodeScannerOptions = BarcodeScannerImpl.zzb;
        return zzb(barcodeScannerOptions);
    }

    public final BarcodeScannerImpl zzb(@NonNull BarcodeScannerOptions barcodeScannerOptions) {
        return new BarcodeScannerImpl(barcodeScannerOptions, (zzi) this.zza.get(barcodeScannerOptions), this.zzb.getExecutorToUse(barcodeScannerOptions.zzb()), zznx.zzb(zzb.zzd()));
    }
}
