package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
public final class zzbc {
    public static int zza(int i2) {
        boolean z;
        if (i2 != 0 && i2 != 1) {
            if (i2 != 2) {
                z = false;
                Preconditions.checkArgument(z, "granularity %d must be a Granularity.GRANULARITY_* constants", Integer.valueOf(i2));
                return i2;
            }
            i2 = 2;
        }
        z = true;
        Preconditions.checkArgument(z, "granularity %d must be a Granularity.GRANULARITY_* constants", Integer.valueOf(i2));
        return i2;
    }

    public static String zzb(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return "GRANULARITY_FINE";
                }
                throw new IllegalArgumentException();
            }
            return "GRANULARITY_COARSE";
        }
        return "GRANULARITY_PERMISSION_LEVEL";
    }
}
