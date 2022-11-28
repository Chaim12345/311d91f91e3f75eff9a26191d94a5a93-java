package com.google.android.play.core.appupdate.testing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.Nullable;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.appupdate.zzb;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallErrorCode;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class FakeAppUpdateManager implements AppUpdateManager {
    private final zzb zza;
    private final Context zzb;
    private final List zzc = new ArrayList();
    @InstallStatus
    private int zzd = 0;
    @InstallErrorCode
    private int zze = 0;
    private boolean zzf = false;
    private int zzg = 0;
    @Nullable
    private Integer zzh = null;
    private int zzi = 0;
    private long zzj = 0;
    private long zzk = 0;
    private boolean zzl = false;
    private boolean zzm = false;
    private boolean zzn = false;
    @Nullable
    @AppUpdateType
    private Integer zzo;

    public FakeAppUpdateManager(Context context) {
        this.zza = new zzb(context);
        this.zzb = context;
    }

    private static int zza() {
        return Build.VERSION.SDK_INT >= 23 ? 67108864 : 0;
    }

    @UpdateAvailability
    private final int zzb() {
        if (this.zzf) {
            int i2 = this.zzd;
            return (i2 == 0 || i2 == 4 || i2 == 5 || i2 == 6) ? 2 : 3;
        }
        return 1;
    }

    private final void zzc() {
        this.zza.zzi(InstallState.zza(this.zzd, this.zzj, this.zzk, this.zze, this.zzb.getPackageName()));
    }

    private final boolean zzd(AppUpdateInfo appUpdateInfo, AppUpdateOptions appUpdateOptions) {
        int i2;
        if (appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) || (AppUpdateOptions.defaultOptions(appUpdateOptions.appUpdateType()).equals(appUpdateOptions) && appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions.appUpdateType()))) {
            if (appUpdateOptions.appUpdateType() == 1) {
                this.zzm = true;
                i2 = 1;
            } else {
                this.zzl = true;
                i2 = 0;
            }
            this.zzo = i2;
            return true;
        }
        return false;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public Task<Void> completeUpdate() {
        int i2 = this.zze;
        if (i2 != 0) {
            return Tasks.zza(new InstallException(i2));
        }
        int i3 = this.zzd;
        if (i3 != 11) {
            return i3 == 3 ? Tasks.zza(new InstallException(-8)) : Tasks.zza(new InstallException(-7));
        }
        this.zzd = 3;
        this.zzn = true;
        Integer num = 0;
        if (num.equals(this.zzo)) {
            zzc();
        }
        return Tasks.zzb(null);
    }

    public void downloadCompletes() {
        int i2 = this.zzd;
        if (i2 == 2 || i2 == 1) {
            this.zzd = 11;
            this.zzj = 0L;
            this.zzk = 0L;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
                return;
            }
            Integer num2 = 1;
            if (num2.equals(this.zzo)) {
                completeUpdate();
            }
        }
    }

    public void downloadFails() {
        int i2 = this.zzd;
        if (i2 == 1 || i2 == 2) {
            this.zzd = 5;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzm = false;
            this.zzd = 0;
        }
    }

    public void downloadStarts() {
        if (this.zzd == 1) {
            this.zzd = 2;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
        }
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public Task<AppUpdateInfo> getAppUpdateInfo() {
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        PendingIntent pendingIntent3;
        PendingIntent pendingIntent4;
        PendingIntent pendingIntent5;
        PendingIntent pendingIntent6;
        int i2 = this.zze;
        if (i2 != 0) {
            return Tasks.zza(new InstallException(i2));
        }
        if (zzb() == 2) {
            if (this.zzc.contains(0)) {
                pendingIntent5 = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
                pendingIntent6 = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
            } else {
                pendingIntent5 = null;
                pendingIntent6 = null;
            }
            if (this.zzc.contains(1)) {
                PendingIntent broadcast = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
                pendingIntent2 = pendingIntent5;
                pendingIntent3 = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
                pendingIntent = broadcast;
            } else {
                pendingIntent2 = pendingIntent5;
                pendingIntent = null;
                pendingIntent3 = null;
            }
            pendingIntent4 = pendingIntent6;
        } else {
            pendingIntent = null;
            pendingIntent2 = null;
            pendingIntent3 = null;
            pendingIntent4 = null;
        }
        return Tasks.zzb(AppUpdateInfo.zzb(this.zzb.getPackageName(), this.zzg, zzb(), this.zzd, this.zzh, this.zzi, this.zzj, this.zzk, 0L, 0L, pendingIntent, pendingIntent2, pendingIntent3, pendingIntent4));
    }

    @Nullable
    @AppUpdateType
    public Integer getTypeForUpdateInProgress() {
        return this.zzo;
    }

    public void installCompletes() {
        if (this.zzd == 3) {
            this.zzd = 4;
            this.zzf = false;
            this.zzg = 0;
            this.zzh = null;
            this.zzi = 0;
            this.zzj = 0L;
            this.zzk = 0L;
            this.zzm = false;
            this.zzn = false;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzd = 0;
        }
    }

    public void installFails() {
        if (this.zzd == 3) {
            this.zzd = 5;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzn = false;
            this.zzm = false;
            this.zzd = 0;
        }
    }

    public boolean isConfirmationDialogVisible() {
        return this.zzl;
    }

    public boolean isImmediateFlowVisible() {
        return this.zzm;
    }

    public boolean isInstallSplashScreenVisible() {
        return this.zzn;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zza.zzf(installStateUpdatedListener);
    }

    public void setBytesDownloaded(long j2) {
        if (this.zzd != 2 || j2 > this.zzk) {
            return;
        }
        this.zzj = j2;
        Integer num = 0;
        if (num.equals(this.zzo)) {
            zzc();
        }
    }

    public void setClientVersionStalenessDays(@Nullable Integer num) {
        if (this.zzf) {
            this.zzh = num;
        }
    }

    public void setInstallErrorCode(@InstallErrorCode int i2) {
        this.zze = i2;
    }

    public void setTotalBytesToDownload(long j2) {
        if (this.zzd == 2) {
            this.zzk = j2;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
        }
    }

    public void setUpdateAvailable(int i2) {
        this.zzf = true;
        this.zzc.clear();
        this.zzc.add(0);
        this.zzc.add(1);
        this.zzg = i2;
    }

    public void setUpdateAvailable(int i2, @AppUpdateType int i3) {
        this.zzf = true;
        this.zzc.clear();
        this.zzc.add(Integer.valueOf(i3));
        this.zzg = i2;
    }

    public void setUpdateNotAvailable() {
        this.zzf = false;
        this.zzh = null;
    }

    public void setUpdatePriority(int i2) {
        if (this.zzf) {
            this.zzi = i2;
        }
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        return zzd(appUpdateInfo, appUpdateOptions) ? Tasks.zzb(-1) : Tasks.zza(new InstallException(-6));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, @AppUpdateType int i2, Activity activity, int i3) {
        return zzd(appUpdateInfo, AppUpdateOptions.newBuilder(i2).build());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, @AppUpdateType int i2, IntentSenderForResultStarter intentSenderForResultStarter, int i3) {
        return zzd(appUpdateInfo, AppUpdateOptions.newBuilder(i2).build());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int i2) {
        return zzd(appUpdateInfo, appUpdateOptions);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int i2) {
        return zzd(appUpdateInfo, appUpdateOptions);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zza.zzh(installStateUpdatedListener);
    }

    public void userAcceptsUpdate() {
        if (this.zzl || this.zzm) {
            this.zzl = false;
            this.zzd = 1;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
        }
    }

    public void userCancelsDownload() {
        int i2 = this.zzd;
        if (i2 == 1 || i2 == 2) {
            this.zzd = 6;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzm = false;
            this.zzd = 0;
        }
    }

    public void userRejectsUpdate() {
        if (this.zzl || this.zzm) {
            this.zzl = false;
            this.zzm = false;
            this.zzo = null;
            this.zzd = 0;
        }
    }
}
