package com.google.android.libraries.places.internal;

import java.lang.reflect.InvocationTargetException;
/* loaded from: classes2.dex */
public final class zzjo {
    private static final zzjq zza;

    static {
        String[] strArr;
        strArr = zzjq.zzd;
        zza = zzb(strArr);
    }

    public static /* synthetic */ zzjq zza() {
        return zza;
    }

    private static zzjq zzb(String[] strArr) {
        zzjq zzjqVar;
        try {
            zzjqVar = zzjr.zza();
        } catch (NoClassDefFoundError unused) {
            zzjqVar = null;
        }
        if (zzjqVar != null) {
            return zzjqVar;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            try {
                return (zzjq) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th) {
                th = th;
                if (th instanceof InvocationTargetException) {
                    th = th.getCause();
                }
                sb.append('\n');
                sb.append(str);
                sb.append(": ");
                sb.append(th);
            }
        }
        throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
    }
}
