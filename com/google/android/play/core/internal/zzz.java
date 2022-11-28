package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes2.dex */
public final class zzz extends zzk {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    public final void zzc(Bundle bundle) {
        Parcel a2 = a();
        zzm.zzb(a2, bundle);
        b(4, a2);
    }

    public final void zzd(Bundle bundle) {
        Parcel a2 = a();
        zzm.zzb(a2, bundle);
        b(3, a2);
    }

    public final void zze(Bundle bundle, Bundle bundle2) {
        Parcel a2 = a();
        zzm.zzb(a2, bundle);
        zzm.zzb(a2, bundle2);
        b(2, a2);
    }
}
