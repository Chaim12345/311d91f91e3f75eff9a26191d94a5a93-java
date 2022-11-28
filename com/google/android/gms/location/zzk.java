package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzk {
    private long zza = Long.MIN_VALUE;

    public final zzk zza(long j2) {
        Preconditions.checkArgument(j2 >= 0, "intervalMillis can't be negative.");
        this.zza = j2;
        return this;
    }

    public final zzl zzb() {
        Preconditions.checkState(this.zza != Long.MIN_VALUE, "Must set intervalMillis.");
        return new zzl(this.zza, true, null, null, null, false, null, 0L, null);
    }
}
