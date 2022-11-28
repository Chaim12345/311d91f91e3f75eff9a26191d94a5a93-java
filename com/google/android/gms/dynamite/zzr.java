package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes.dex */
public final class zzr extends com.google.android.gms.internal.common.zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final IObjectWrapper zze(IObjectWrapper iObjectWrapper, String str, int i2, IObjectWrapper iObjectWrapper2) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        d2.writeInt(i2);
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper2);
        Parcel a2 = a(2, d2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzf(IObjectWrapper iObjectWrapper, String str, int i2, IObjectWrapper iObjectWrapper2) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        d2.writeInt(i2);
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper2);
        Parcel a2 = a(3, d2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }
}
