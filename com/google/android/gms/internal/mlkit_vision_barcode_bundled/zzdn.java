package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Collections;
import java.util.Map;
/* loaded from: classes2.dex */
public final class zzdn {

    /* renamed from: a  reason: collision with root package name */
    static final zzdn f6414a = new zzdn(true);
    private static volatile boolean zzb = false;
    private static volatile zzdn zzc;
    private final Map zzd = Collections.emptyMap();

    zzdn(boolean z) {
    }

    public static zzdn zza() {
        zzdn zzdnVar = zzc;
        if (zzdnVar == null) {
            synchronized (zzdn.class) {
                zzdnVar = zzc;
                if (zzdnVar == null) {
                    zzdnVar = f6414a;
                    zzc = zzdnVar;
                }
            }
        }
        return zzdnVar;
    }

    public final zzea zzb(zzfl zzflVar, int i2) {
        return (zzea) this.zzd.get(new zzdm(zzflVar, i2));
    }
}
