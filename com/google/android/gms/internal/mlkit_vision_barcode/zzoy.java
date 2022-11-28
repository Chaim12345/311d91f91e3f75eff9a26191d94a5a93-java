package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes2.dex */
public final class zzoy extends zza implements zzpa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzoy(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.barcode.aidls.IBarcodeScannerCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzpa
    public final zzox zzd(IObjectWrapper iObjectWrapper, zzop zzopVar) {
        zzox zzoxVar;
        Parcel a2 = a();
        zzc.zzb(a2, iObjectWrapper);
        zzc.zza(a2, zzopVar);
        Parcel b2 = b(1, a2);
        IBinder readStrongBinder = b2.readStrongBinder();
        if (readStrongBinder == null) {
            zzoxVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.mlkit.vision.barcode.aidls.IBarcodeScanner");
            zzoxVar = queryLocalInterface instanceof zzox ? (zzox) queryLocalInterface : new zzox(readStrongBinder);
        }
        b2.recycle();
        return zzoxVar;
    }
}
