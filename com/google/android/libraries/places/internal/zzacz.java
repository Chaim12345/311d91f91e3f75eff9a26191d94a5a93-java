package com.google.android.libraries.places.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public final class zzacz {
    static final zzacz zza = new zzacz(true);
    private static volatile boolean zzb = false;
    private static volatile zzacz zzc;
    private final Map zzd;

    zzacz() {
        this.zzd = new HashMap();
    }

    zzacz(boolean z) {
        this.zzd = Collections.emptyMap();
    }

    public static zzacz zza() {
        zzacz zzaczVar = zzc;
        if (zzaczVar == null) {
            synchronized (zzacz.class) {
                zzaczVar = zzc;
                if (zzaczVar == null) {
                    zzaczVar = zza;
                    zzc = zzaczVar;
                }
            }
        }
        return zzaczVar;
    }
}
