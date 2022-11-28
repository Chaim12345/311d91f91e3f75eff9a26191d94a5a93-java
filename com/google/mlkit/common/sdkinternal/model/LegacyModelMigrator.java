package com.google.mlkit.common.sdkinternal.model;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.File;
import java.util.concurrent.Executor;
@KeepForSdk
/* loaded from: classes2.dex */
public abstract class LegacyModelMigrator {
    private final TaskCompletionSource zza;
    private final Context zzb;
    private final Executor zzc;

    @KeepForSdk
    protected static void a(@NonNull File file) {
        File[] listFiles = file.listFiles();
        if ((listFiles == null || listFiles.length == 0) && !file.delete()) {
            Log.e("MlKitLegacyMigration", "Error deleting model directory ".concat(String.valueOf(file)));
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public static void migrateFile(@NonNull File file, @NonNull File file2) {
        if (file.exists()) {
            if (!file2.exists() && !file.renameTo(file2)) {
                String valueOf = String.valueOf(file);
                String valueOf2 = String.valueOf(file2);
                StringBuilder sb = new StringBuilder(valueOf.length() + 28 + valueOf2.length());
                sb.append("Error moving model file ");
                sb.append(valueOf);
                sb.append(" to ");
                sb.append(valueOf2);
                Log.e("MlKitLegacyMigration", sb.toString());
            }
            if (!file.exists() || file.delete()) {
                return;
            }
            Log.e("MlKitLegacyMigration", "Error deleting model file ".concat(String.valueOf(file)));
        }
    }

    @NonNull
    @VisibleForTesting
    @KeepForSdk
    protected abstract String b();

    @KeepForSdk
    protected abstract void c(@NonNull File file);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void d() {
        File legacyRootDir = getLegacyRootDir();
        File[] listFiles = legacyRootDir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                c(file);
            }
            a(legacyRootDir);
        }
        this.zza.setResult(null);
    }

    @NonNull
    @VisibleForTesting
    @KeepForSdk
    public File getLegacyRootDir() {
        String b2 = b();
        return Build.VERSION.SDK_INT >= 21 ? new File(this.zzb.getNoBackupFilesDir(), b2) : this.zzb.getApplicationContext().getDir(b2, 0);
    }

    @NonNull
    @KeepForSdk
    public Task<Void> getMigrationTask() {
        return this.zza.getTask();
    }

    @KeepForSdk
    public void start() {
        this.zzc.execute(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.model.zza
            @Override // java.lang.Runnable
            public final void run() {
                LegacyModelMigrator.this.d();
            }
        });
    }
}
