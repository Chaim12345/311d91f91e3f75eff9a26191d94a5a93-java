package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes.dex */
final class zzba extends zzaj {
    private final BaseImplementation.ResultHolder zza;

    public zzba(BaseImplementation.ResultHolder resultHolder) {
        Preconditions.checkArgument(resultHolder != null, "listener can't be null.");
        this.zza = resultHolder;
    }

    private final void zze(int i2) {
        BaseImplementation.ResultHolder resultHolder = this.zza;
        if (i2 != 0 && (i2 < 1000 || i2 >= 1006)) {
            i2 = 13;
        }
        resultHolder.setResult(new Status(i2));
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzb(int i2, String[] strArr) {
        zze(i2);
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzc(int i2, PendingIntent pendingIntent) {
        zze(i2);
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzd(int i2, String[] strArr) {
        zze(i2);
    }
}
