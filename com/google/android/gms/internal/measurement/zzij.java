package com.google.android.gms.internal.measurement;

import java.io.Serializable;
/* loaded from: classes.dex */
public final class zzij {
    public static zzif zza(zzif zzifVar) {
        return ((zzifVar instanceof zzih) || (zzifVar instanceof zzig)) ? zzifVar : zzifVar instanceof Serializable ? new zzig(zzifVar) : new zzih(zzifVar);
    }

    public static zzif zzb(Object obj) {
        return new zzii(obj);
    }
}
