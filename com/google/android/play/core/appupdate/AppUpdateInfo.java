package com.google.android.play.core.appupdate;

import android.app.PendingIntent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
/* loaded from: classes2.dex */
public class AppUpdateInfo {
    @NonNull
    private final String zza;
    private final int zzb;
    @UpdateAvailability
    private final int zzc;
    @InstallStatus
    private final int zzd;
    @Nullable
    private final Integer zze;
    private final int zzf;
    private final long zzg;
    private final long zzh;
    private final long zzi;
    private final long zzj;
    @Nullable
    private final PendingIntent zzk;
    @Nullable
    private final PendingIntent zzl;
    @Nullable
    private final PendingIntent zzm;
    @Nullable
    private final PendingIntent zzn;
    private boolean zzo = false;

    private AppUpdateInfo(@NonNull String str, int i2, @UpdateAvailability int i3, @InstallStatus int i4, @Nullable Integer num, int i5, long j2, long j3, long j4, long j5, @Nullable PendingIntent pendingIntent, @Nullable PendingIntent pendingIntent2, @Nullable PendingIntent pendingIntent3, @Nullable PendingIntent pendingIntent4) {
        this.zza = str;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zze = num;
        this.zzf = i5;
        this.zzg = j2;
        this.zzh = j3;
        this.zzi = j4;
        this.zzj = j5;
        this.zzk = pendingIntent;
        this.zzl = pendingIntent2;
        this.zzm = pendingIntent3;
        this.zzn = pendingIntent4;
    }

    public static AppUpdateInfo zzb(@NonNull String str, int i2, @UpdateAvailability int i3, @InstallStatus int i4, @Nullable Integer num, int i5, long j2, long j3, long j4, long j5, @Nullable PendingIntent pendingIntent, @Nullable PendingIntent pendingIntent2, @Nullable PendingIntent pendingIntent3, @Nullable PendingIntent pendingIntent4) {
        return new AppUpdateInfo(str, i2, i3, i4, num, i5, j2, j3, j4, j5, pendingIntent, pendingIntent2, pendingIntent3, pendingIntent4);
    }

    private final boolean zze(AppUpdateOptions appUpdateOptions) {
        return appUpdateOptions.allowAssetPackDeletion() && this.zzi <= this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final PendingIntent a(AppUpdateOptions appUpdateOptions) {
        if (appUpdateOptions.appUpdateType() == 0) {
            PendingIntent pendingIntent = this.zzl;
            if (pendingIntent != null) {
                return pendingIntent;
            }
            if (zze(appUpdateOptions)) {
                return this.zzn;
            }
            return null;
        }
        if (appUpdateOptions.appUpdateType() == 1) {
            PendingIntent pendingIntent2 = this.zzk;
            if (pendingIntent2 != null) {
                return pendingIntent2;
            }
            if (zze(appUpdateOptions)) {
                return this.zzm;
            }
        }
        return null;
    }

    public int availableVersionCode() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        this.zzo = true;
    }

    public long bytesDownloaded() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean c() {
        return this.zzo;
    }

    @Nullable
    public Integer clientVersionStalenessDays() {
        return this.zze;
    }

    @InstallStatus
    public int installStatus() {
        return this.zzd;
    }

    public boolean isUpdateTypeAllowed(@AppUpdateType int i2) {
        return a(AppUpdateOptions.defaultOptions(i2)) != null;
    }

    public boolean isUpdateTypeAllowed(@NonNull AppUpdateOptions appUpdateOptions) {
        return a(appUpdateOptions) != null;
    }

    @NonNull
    public String packageName() {
        return this.zza;
    }

    public long totalBytesToDownload() {
        return this.zzh;
    }

    @UpdateAvailability
    public int updateAvailability() {
        return this.zzc;
    }

    public int updatePriority() {
        return this.zzf;
    }
}
