package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
/* loaded from: classes2.dex */
public final class zzkx extends zzkz {
    private final AlarmManager zza;
    private zzap zzb;
    private Integer zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzkx(zzll zzllVar) {
        super(zzllVar);
        this.zza = (AlarmManager) this.f6809a.zzau().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private final int zzf() {
        if (this.zzc == null) {
            this.zzc = Integer.valueOf("measurement".concat(String.valueOf(this.f6809a.zzau().getPackageName())).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzh() {
        Context zzau = this.f6809a.zzau();
        return PendingIntent.getBroadcast(zzau, 0, new Intent().setClassName(zzau, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzbs.zza);
    }

    private final zzap zzi() {
        if (this.zzb == null) {
            this.zzb = new zzkw(this, this.f7018b.E());
        }
        return this.zzb;
    }

    @TargetApi(24)
    private final void zzj() {
        JobScheduler jobScheduler = (JobScheduler) this.f6809a.zzau().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzf());
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkz
    protected final boolean c() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        if (Build.VERSION.SDK_INT >= 24) {
            zzj();
            return false;
        }
        return false;
    }

    public final void zza() {
        a();
        this.f6809a.zzay().zzj().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        zzi().b();
        if (Build.VERSION.SDK_INT >= 24) {
            zzj();
        }
    }

    public final void zzd(long j2) {
        a();
        this.f6809a.zzaw();
        Context zzau = this.f6809a.zzau();
        if (!zzlt.D(zzau)) {
            this.f6809a.zzay().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzlt.E(zzau, false)) {
            this.f6809a.zzay().zzc().zza("Service not registered/enabled");
        }
        zza();
        this.f6809a.zzay().zzj().zzb("Scheduling upload, millis", Long.valueOf(j2));
        long elapsedRealtime = this.f6809a.zzav().elapsedRealtime() + j2;
        this.f6809a.zzf();
        if (j2 < Math.max(0L, ((Long) zzen.zzw.zza(null)).longValue()) && !zzi().zze()) {
            zzi().zzd(j2);
        }
        this.f6809a.zzaw();
        if (Build.VERSION.SDK_INT < 24) {
            AlarmManager alarmManager = this.zza;
            if (alarmManager != null) {
                this.f6809a.zzf();
                alarmManager.setInexactRepeating(2, elapsedRealtime, Math.max(((Long) zzen.zzr.zza(null)).longValue(), j2), zzh());
                return;
            }
            return;
        }
        Context zzau2 = this.f6809a.zzau();
        ComponentName componentName = new ComponentName(zzau2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int zzf = zzf();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzbt.zza(zzau2, new JobInfo.Builder(zzf, componentName).setMinimumLatency(j2).setOverrideDeadline(j2 + j2).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }
}
