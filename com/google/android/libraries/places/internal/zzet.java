package com.google.android.libraries.places.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes2.dex */
public abstract class zzet {
    public static zzes zzd(Context context) {
        String packageName = context.getPackageName();
        int i2 = 0;
        try {
            i2 = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        zzep zzepVar = new zzep();
        zzepVar.zza(packageName);
        zzepVar.zzb(i2);
        zzepVar.zzd(1);
        return zzepVar;
    }

    public abstract int zza();

    public abstract String zzb();

    public abstract int zzc();
}
