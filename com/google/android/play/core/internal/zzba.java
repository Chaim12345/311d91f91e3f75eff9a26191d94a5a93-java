package com.google.android.play.core.internal;

import android.os.Build;
/* loaded from: classes2.dex */
public final class zzba {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static zzaz zza() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            switch (i2) {
                case 21:
                    return new zzbf();
                case 22:
                    return new zzbg();
                case 23:
                    return new zzbk();
                case 24:
                    return new zzbl();
                case 25:
                    return new zzbm();
                case 26:
                    return new zzbp();
                case 27:
                    if (Build.VERSION.PREVIEW_SDK_INT == 0) {
                        return new zzbq();
                    }
                    break;
            }
            return new zzbs();
        }
        throw new AssertionError("Unsupported Android Version");
    }
}
