package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class zzcg extends zzbm implements zzci {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final int zzd() {
        Parcel b2 = b(2, a());
        int readInt = b2.readInt();
        b2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final void zze(String str, String str2, Bundle bundle, long j2) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        zzbo.zze(a2, bundle);
        a2.writeLong(j2);
        c(1, a2);
    }
}
