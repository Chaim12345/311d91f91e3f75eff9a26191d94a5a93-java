package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
public final class zzkl {
    private final Context zza;

    public zzkl(Context context) {
        Preconditions.checkNotNull(context);
        this.zza = context;
    }

    private final zzfa zzk() {
        return zzgk.zzp(this.zza, null, null).zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void a(int i2, zzfa zzfaVar, Intent intent) {
        if (((zzkk) this.zza).zzc(i2)) {
            zzfaVar.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i2));
            zzk().zzj().zza("Completed wakeful intent.");
            ((zzkk) this.zza).zza(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(zzfa zzfaVar, JobParameters jobParameters) {
        zzfaVar.zzj().zza("AppMeasurementJobService processed last upload request.");
        ((zzkk) this.zza).zzb(jobParameters, false);
    }

    @MainThread
    public final int zza(final Intent intent, int i2, final int i3) {
        zzgk zzp = zzgk.zzp(this.zza, null, null);
        final zzfa zzay = zzp.zzay();
        if (intent == null) {
            zzay.zzk().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzp.zzaw();
        zzay.zzj().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i3), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzki
                @Override // java.lang.Runnable
                public final void run() {
                    zzkl.this.a(i3, zzay, intent);
                }
            });
        }
        return 2;
    }

    @MainThread
    public final IBinder zzb(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzhc(zzll.zzt(this.zza), null);
        }
        zzk().zzk().zzb("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final void zze() {
        zzgk zzp = zzgk.zzp(this.zza, null, null);
        zzfa zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void zzf() {
        zzgk zzp = zzgk.zzp(this.zza, null, null);
        zzfa zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final void zzg(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onRebind called with null intent");
            return;
        }
        zzk().zzj().zzb("onRebind called. action", intent.getAction());
    }

    public final void zzh(Runnable runnable) {
        zzll zzt = zzll.zzt(this.zza);
        zzt.zzaz().zzp(new zzkj(this, zzt, runnable));
    }

    @TargetApi(24)
    @MainThread
    public final boolean zzi(final JobParameters jobParameters) {
        zzgk zzp = zzgk.zzp(this.zza, null, null);
        final zzfa zzay = zzp.zzay();
        String string = jobParameters.getExtras().getString("action");
        zzp.zzaw();
        zzay.zzj().zzb("Local AppMeasurementJobService called. action", string);
        if ("com.google.android.gms.measurement.UPLOAD".equals(string)) {
            zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzkh
                @Override // java.lang.Runnable
                public final void run() {
                    zzkl.this.b(zzay, jobParameters);
                }
            });
            return true;
        }
        return true;
    }

    @MainThread
    public final boolean zzj(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onUnbind called with null intent");
            return true;
        }
        zzk().zzj().zzb("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
