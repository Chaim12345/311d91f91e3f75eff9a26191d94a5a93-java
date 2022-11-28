package com.google.android.libraries.places.internal;

import android.content.Context;
/* loaded from: classes2.dex */
public final class zzez {
    private final Context zza;

    public zzez(Context context) {
        zzha.zzc(context, "Context must not be null.");
        this.zza = context;
    }

    public final zzhv zza() {
        String packageName = this.zza.getPackageName();
        String zza = zzeo.zza(this.zza.getPackageManager(), packageName);
        zzhu zzhuVar = new zzhu();
        if (packageName != null) {
            zzhuVar.zza("X-Android-Package", packageName);
        }
        if (zza != null) {
            zzhuVar.zza("X-Android-Cert", zza);
        }
        return zzhuVar.zzb();
    }
}
