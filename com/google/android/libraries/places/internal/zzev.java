package com.google.android.libraries.places.internal;

import android.content.Context;
import android.os.Build;
import android.os.DropBoxManager;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzev {
    @Nullable
    @GuardedBy("CrashReporter.class")
    private static DropBoxManager zza;
    @GuardedBy("CrashReporter.class")
    private static final LinkedHashMap zzb = new zzeu(16, 0.75f, true);
    @Nullable
    @GuardedBy("CrashReporter.class")
    private static String zzc;

    public static synchronized void zza(Context context, boolean z) {
        synchronized (zzev.class) {
            if (zza == null) {
                zza = (DropBoxManager) context.getApplicationContext().getSystemService("dropbox");
                zzc = "com.google.android.libraries.places";
            }
        }
    }

    public static synchronized void zzb(Throwable th) {
        long j2;
        synchronized (zzev.class) {
            long id = Thread.currentThread().getId();
            int hashCode = th.hashCode();
            Integer num = (Integer) zzb.get(Long.valueOf(id));
            if (num == null || num.intValue() != hashCode) {
                DropBoxManager dropBoxManager = zza;
                if (dropBoxManager == null || !dropBoxManager.isTagEnabled("system_app_crash")) {
                    return;
                }
                DropBoxManager dropBoxManager2 = zza;
                StringBuilder sb = new StringBuilder();
                Object[] objArr = new Object[3];
                objArr[0] = zzc;
                List zzc2 = zzhe.zzb('.').zzc("2.6.0");
                if (zzc2.size() == 3) {
                    long j3 = 0;
                    for (int i2 = 0; i2 < zzc2.size(); i2++) {
                        try {
                            j3 = (j3 * 100) + Integer.parseInt((String) zzc2.get(i2));
                        } catch (NumberFormatException unused) {
                        }
                    }
                    j2 = j3;
                    objArr[1] = Long.valueOf(j2);
                    objArr[2] = "2.6.0";
                    sb.append(String.format("Package: %s v%d (%s)\n", objArr));
                    sb.append(String.format("Build: %s\n", Build.FINGERPRINT));
                    sb.append("\n");
                    sb.append(Log.getStackTraceString(th));
                    dropBoxManager2.addText("system_app_crash", sb.toString());
                    zzb.put(Long.valueOf(id), Integer.valueOf(hashCode));
                }
                j2 = -1;
                objArr[1] = Long.valueOf(j2);
                objArr[2] = "2.6.0";
                sb.append(String.format("Package: %s v%d (%s)\n", objArr));
                sb.append(String.format("Build: %s\n", Build.FINGERPRINT));
                sb.append("\n");
                sb.append(Log.getStackTraceString(th));
                dropBoxManager2.addText("system_app_crash", sb.toString());
                zzb.put(Long.valueOf(id), Integer.valueOf(hashCode));
            }
        }
    }
}
