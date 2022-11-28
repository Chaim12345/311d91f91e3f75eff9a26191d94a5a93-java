package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class zzbp extends zzbm implements zzbr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbp(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    @Override // com.google.android.gms.internal.measurement.zzbr
    public final Bundle zzd(Bundle bundle) {
        Parcel a2 = a();
        zzbo.zze(a2, bundle);
        Parcel b2 = b(1, a2);
        Bundle bundle2 = (Bundle) zzbo.zza(b2, Bundle.CREATOR);
        b2.recycle();
        return bundle2;
    }
}
