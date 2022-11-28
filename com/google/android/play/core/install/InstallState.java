package com.google.android.play.core.install;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.play.core.install.model.InstallErrorCode;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.internal.zzag;
/* loaded from: classes2.dex */
public abstract class InstallState {
    public static InstallState zza(@InstallStatus int i2, long j2, long j3, @InstallErrorCode int i3, @NonNull String str) {
        return new zza(i2, j2, j3, i3, str);
    }

    public static InstallState zzb(@NonNull Intent intent, @NonNull zzag zzagVar) {
        zzagVar.zza("List of extras in received intent needed by fromUpdateIntent:", new Object[0]);
        zzagVar.zza("Key: %s; value: %s", "install.status", Integer.valueOf(intent.getIntExtra("install.status", 0)));
        zzagVar.zza("Key: %s; value: %s", "error.code", Integer.valueOf(intent.getIntExtra("error.code", 0)));
        return new zza(intent.getIntExtra("install.status", 0), intent.getLongExtra("bytes.downloaded", 0L), intent.getLongExtra("total.bytes.to.download", 0L), intent.getIntExtra("error.code", 0), intent.getStringExtra("package.name"));
    }

    public abstract long bytesDownloaded();

    @InstallErrorCode
    public abstract int installErrorCode();

    @InstallStatus
    public abstract int installStatus();

    public abstract String packageName();

    public abstract long totalBytesToDownload();
}
