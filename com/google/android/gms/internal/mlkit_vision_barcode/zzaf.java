package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes2.dex */
public final class zzaf extends zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final void zzd() {
        c(3, a());
    }

    public final zzq[] zze(IObjectWrapper iObjectWrapper, zzaj zzajVar) {
        Parcel a2 = a();
        zzc.zzb(a2, iObjectWrapper);
        zzc.zza(a2, zzajVar);
        Parcel b2 = b(1, a2);
        zzq[] zzqVarArr = (zzq[]) b2.createTypedArray(zzq.CREATOR);
        b2.recycle();
        return zzqVarArr;
    }

    public final zzq[] zzf(IObjectWrapper iObjectWrapper, zzaj zzajVar) {
        Parcel a2 = a();
        zzc.zzb(a2, iObjectWrapper);
        zzc.zza(a2, zzajVar);
        Parcel b2 = b(2, a2);
        zzq[] zzqVarArr = (zzq[]) b2.createTypedArray(zzq.CREATOR);
        b2.recycle();
        return zzqVarArr;
    }
}
