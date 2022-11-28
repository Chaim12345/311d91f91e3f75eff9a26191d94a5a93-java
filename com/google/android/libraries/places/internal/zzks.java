package com.google.android.libraries.places.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes2.dex */
public final class zzks {
    private static final String[] zza;
    private static final zzkw zzb;

    static {
        zzkw zzkxVar;
        String[] strArr = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
        zza = strArr;
        int i2 = 0;
        while (true) {
            if (i2 >= 2) {
                zzkxVar = new zzkx();
                break;
            }
            try {
                zzkxVar = (zzkw) Class.forName(strArr[i2]).asSubclass(zzkw.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused) {
                zzkxVar = null;
            }
            if (zzkxVar != null) {
                break;
            }
            i2++;
        }
        zzb = zzkxVar;
    }

    @NullableDecl
    public static StackTraceElement zza(Class cls, int i2) {
        zzkt.zza(cls, TypedValues.Attributes.S_TARGET);
        return zzb.zza(cls, 2);
    }
}
