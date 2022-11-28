package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Map;
/* loaded from: classes.dex */
public final class zzjo {

    /* renamed from: a  reason: collision with root package name */
    static final zzjo f6096a = new zzjo(true);
    private static volatile boolean zzb = false;
    private static volatile zzjo zzc;
    private static volatile zzjo zzd;
    private final Map zze = Collections.emptyMap();

    zzjo(boolean z) {
    }

    public static zzjo zza() {
        zzjo zzjoVar = zzc;
        if (zzjoVar == null) {
            synchronized (zzjo.class) {
                zzjoVar = zzc;
                if (zzjoVar == null) {
                    zzjoVar = f6096a;
                    zzc = zzjoVar;
                }
            }
        }
        return zzjoVar;
    }

    public static zzjo zzb() {
        zzjo zzjoVar = zzd;
        if (zzjoVar != null) {
            return zzjoVar;
        }
        synchronized (zzjo.class) {
            zzjo zzjoVar2 = zzd;
            if (zzjoVar2 != null) {
                return zzjoVar2;
            }
            zzjo b2 = zzjw.b(zzjo.class);
            zzd = b2;
            return b2;
        }
    }

    public final zzka zzc(zzlj zzljVar, int i2) {
        return (zzka) this.zze.get(new zzjn(zzljVar, i2));
    }
}
