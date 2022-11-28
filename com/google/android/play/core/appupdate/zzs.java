package com.google.android.play.core.appupdate;

import android.content.Context;
import java.io.File;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzs {
    private final Context zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(Context context) {
        this.zza = context;
    }

    private static long zzb(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            long j2 = 0;
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    j2 += zzb(file2);
                }
            }
            return j2;
        }
        return file.length();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long a() {
        return zzb(new File(this.zza.getFilesDir(), "assetpacks"));
    }
}
