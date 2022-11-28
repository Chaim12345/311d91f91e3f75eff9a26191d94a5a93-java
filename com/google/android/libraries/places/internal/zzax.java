package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzax {
    static zzax zza;
    private final zzay zzb;

    private zzax(zzay zzayVar) {
        this.zzb = zzayVar;
    }

    public static zzax zza() {
        zzax zzaxVar = new zzax(new zzaw());
        zza = zzaxVar;
        return zzaxVar;
    }
}
