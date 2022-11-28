package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes.dex */
public final class zzq extends com.google.android.gms.internal.common.zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final int zze() {
        Parcel a2 = a(6, d());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    public final int zzf(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        com.google.android.gms.internal.common.zzc.zzb(d2, z);
        Parcel a2 = a(3, d2);
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    public final int zzg(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        com.google.android.gms.internal.common.zzc.zzb(d2, z);
        Parcel a2 = a(5, d2);
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    public final IObjectWrapper zzh(IObjectWrapper iObjectWrapper, String str, int i2) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        d2.writeInt(i2);
        Parcel a2 = a(2, d2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzi(IObjectWrapper iObjectWrapper, String str, int i2, IObjectWrapper iObjectWrapper2) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        d2.writeInt(i2);
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper2);
        Parcel a2 = a(8, d2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzj(IObjectWrapper iObjectWrapper, String str, int i2) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        d2.writeInt(i2);
        Parcel a2 = a(4, d2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzk(IObjectWrapper iObjectWrapper, String str, boolean z, long j2) {
        Parcel d2 = d();
        com.google.android.gms.internal.common.zzc.zze(d2, iObjectWrapper);
        d2.writeString(str);
        com.google.android.gms.internal.common.zzc.zzb(d2, z);
        d2.writeLong(j2);
        Parcel a2 = a(7, d2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }
}
