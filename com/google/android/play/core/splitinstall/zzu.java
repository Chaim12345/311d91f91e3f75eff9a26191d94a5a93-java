package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.zzce;
/* loaded from: classes2.dex */
public final class zzu {
    private static zzp zza;

    public static synchronized zzp zza(Context context) {
        zzp zzpVar;
        synchronized (zzu.class) {
            if (zza == null) {
                zzc zzcVar = new zzc(null);
                zzcVar.zza(new zzac(zzce.zza(context)));
                zza = zzcVar.zzb();
            }
            zzpVar = zza;
        }
        return zzpVar;
    }
}
