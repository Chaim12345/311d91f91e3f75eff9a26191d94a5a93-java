package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Set;
/* loaded from: classes2.dex */
public final class zzjn {
    private static final zzjj zza = new zzjl();
    private static final zzji zzb = new zzjm();

    public static zzjg zza(Set set) {
        zzjg zzjgVar = new zzjg(zza, null);
        zzjgVar.zza(zzb);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzjgVar.zzg((zzix) it.next());
        }
        return zzjgVar;
    }
}
