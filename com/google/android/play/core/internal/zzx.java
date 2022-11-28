package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes2.dex */
public abstract class zzx extends zzl implements zzy {
    public zzx() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionService");
    }

    @Override // com.google.android.play.core.internal.zzl
    protected final boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) {
        zzz zzzVar = null;
        if (i2 == 2) {
            Bundle bundle = (Bundle) zzm.zza(parcel, Bundle.CREATOR);
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
                zzzVar = queryLocalInterface instanceof zzz ? (zzz) queryLocalInterface : new zzz(readStrongBinder);
            }
            zzc(bundle, zzzVar);
            return true;
        } else if (i2 != 3) {
            return false;
        } else {
            Bundle bundle2 = (Bundle) zzm.zza(parcel, Bundle.CREATOR);
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            if (readStrongBinder2 != null) {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
                zzzVar = queryLocalInterface2 instanceof zzz ? (zzz) queryLocalInterface2 : new zzz(readStrongBinder2);
            }
            zzb(bundle2, zzzVar);
            return true;
        }
    }
}
