package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public final class zzs {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("SplitInstallInfoProvider");
    private final Context zzb;
    private final String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(Context context) {
        this.zzb = context;
        this.zzc = context.getPackageName();
    }

    public zzs(Context context, String str) {
        this.zzb = context;
        this.zzc = str;
    }

    public static String zzb(String str) {
        return str.startsWith("config.") ? "" : str.split("\\.config\\.", 2)[0];
    }

    public static boolean zze(String str) {
        return str.startsWith("config.");
    }

    public static boolean zzf(String str) {
        return str.startsWith("config.") || str.contains(".config.");
    }

    @Nullable
    private final Bundle zzg() {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = this.zzb.getPackageManager().getApplicationInfo(this.zzc, 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                zza.zza("App has no applicationInfo or metaData", new Object[0]);
                return null;
            }
            return bundle;
        } catch (PackageManager.NameNotFoundException unused) {
            zza.zze("App is not found in PackageManager", new Object[0]);
            return null;
        }
    }

    private final Set zzh() {
        HashSet hashSet = new HashSet();
        Bundle zzg = zzg();
        if (zzg != null) {
            String string = zzg.getString("com.android.dynamic.apk.fused.modules");
            if (string == null || string.isEmpty()) {
                zza.zza("App has no fused modules.", new Object[0]);
            } else {
                Collections.addAll(hashSet, string.split(",", -1));
                hashSet.remove("");
                hashSet.remove("base");
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = null;
            try {
                PackageInfo packageInfo = this.zzb.getPackageManager().getPackageInfo(this.zzc, 0);
                if (packageInfo != null) {
                    strArr = packageInfo.splitNames;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                zza.zze("App is not found in PackageManager", new Object[0]);
            }
            if (strArr != null) {
                zza.zza("Adding splits from package manager: %s", Arrays.toString(strArr));
                Collections.addAll(hashSet, strArr);
            } else {
                zza.zza("No splits are found or app cannot be found in package manager.", new Object[0]);
            }
            zzq a2 = zzr.a();
            if (a2 != null) {
                hashSet.addAll(a2.zza());
            }
        }
        return hashSet;
    }

    @Nullable
    public final zzk zza() {
        Bundle zzg = zzg();
        if (zzg == null) {
            zza.zze("No metadata found in Context.", new Object[0]);
            return null;
        }
        int i2 = zzg.getInt("com.android.vending.splits");
        if (i2 == 0) {
            zza.zze("No metadata found in AndroidManifest.", new Object[0]);
            return null;
        }
        try {
            zzk a2 = zzbg.a(this.zzb.getResources().getXml(i2), new zzi());
            if (a2 == null) {
                zza.zze("Can't parse languages metadata.", new Object[0]);
            }
            return a2;
        } catch (Resources.NotFoundException unused) {
            zza.zze("Resource with languages metadata doesn't exist.", new Object[0]);
            return null;
        }
    }

    public final Set zzc() {
        HashSet hashSet = new HashSet();
        for (String str : zzh()) {
            if (!zzf(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    @Nullable
    public final Set zzd() {
        zzk zza2 = zza();
        if (zza2 == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Set zzh = zzh();
        zzh.add("");
        Set zzc = zzc();
        zzc.add("");
        for (Map.Entry entry : zza2.zza(zzc).entrySet()) {
            if (zzh.containsAll((Collection) entry.getValue())) {
                hashSet.add((String) entry.getKey());
            }
        }
        return hashSet;
    }
}
