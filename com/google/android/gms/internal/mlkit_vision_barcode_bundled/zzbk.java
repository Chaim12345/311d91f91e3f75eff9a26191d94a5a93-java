package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class zzbk extends zzb implements zzbl {
    public zzbk() {
        super("com.google.mlkit.vision.barcode.aidls.IBarcodeScanner");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzb
    protected final boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            zzc();
        } else if (i2 != 2) {
            if (i2 != 3) {
                return false;
            }
            List zzb = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzbu) zzc.zza(parcel, zzbu.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedList(zzb);
            return true;
        } else {
            zzd();
        }
        parcel2.writeNoException();
        return true;
    }
}
