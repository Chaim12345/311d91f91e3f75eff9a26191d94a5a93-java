package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzox extends zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzox(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.barcode.aidls.IBarcodeScanner");
    }

    public final List zzd(IObjectWrapper iObjectWrapper, zzpg zzpgVar) {
        Parcel a2 = a();
        zzc.zzb(a2, iObjectWrapper);
        zzc.zza(a2, zzpgVar);
        Parcel b2 = b(3, a2);
        ArrayList createTypedArrayList = b2.createTypedArrayList(zzon.CREATOR);
        b2.recycle();
        return createTypedArrayList;
    }

    public final void zze() {
        c(1, a());
    }

    public final void zzf() {
        c(2, a());
    }
}
