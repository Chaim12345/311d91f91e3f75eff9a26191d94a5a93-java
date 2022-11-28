package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzfp extends zzhe {
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    static final Pair f6737b = new Pair("", 0L);
    public zzfn zzb;
    public final zzfl zzc;
    public final zzfl zzd;
    public final zzfo zze;
    public final zzfl zzf;
    public final zzfj zzg;
    public final zzfo zzh;
    public final zzfj zzi;
    public final zzfl zzj;
    public boolean zzk;
    public final zzfj zzl;
    public final zzfj zzm;
    public final zzfl zzn;
    public final zzfo zzo;
    public final zzfo zzp;
    public final zzfl zzq;
    public final zzfk zzr;
    private SharedPreferences zzt;
    private String zzu;
    private boolean zzv;
    private long zzw;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfp(zzgk zzgkVar) {
        super(zzgkVar);
        this.zzf = new zzfl(this, "session_timeout", 1800000L);
        this.zzg = new zzfj(this, "start_new_session", true);
        this.zzj = new zzfl(this, "last_pause_time", 0L);
        this.zzh = new zzfo(this, "non_personalized_ads", null);
        this.zzi = new zzfj(this, "allow_remote_dynamite", false);
        this.zzc = new zzfl(this, "first_open_time", 0L);
        this.zzd = new zzfl(this, "app_install_time", 0L);
        this.zze = new zzfo(this, "app_instance_id", null);
        this.zzl = new zzfj(this, "app_backgrounded", false);
        this.zzm = new zzfj(this, "deep_link_retrieval_complete", false);
        this.zzn = new zzfl(this, "deep_link_retrieval_attempts", 0L);
        this.zzo = new zzfo(this, "firebase_feature_rollouts", null);
        this.zzp = new zzfo(this, "deferred_attribution_cache", null);
        this.zzq = new zzfl(this, "deferred_attribution_cache_timestamp", 0L);
        this.zzr = new zzfk(this, "default_event_parameters", null);
    }

    @Override // com.google.android.gms.measurement.internal.zzhe
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    @WorkerThread
    protected final void a() {
        SharedPreferences sharedPreferences = this.f6809a.zzau().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzt = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzk = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzt.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.f6809a.zzf();
        this.zzb = new zzfn(this, "health_monitor", Math.max(0L, ((Long) zzen.zzb.zza(null)).longValue()), null);
    }

    @Override // com.google.android.gms.measurement.internal.zzhe
    protected final boolean b() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    @WorkerThread
    public final SharedPreferences e() {
        zzg();
        c();
        Preconditions.checkNotNull(this.zzt);
        return this.zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final Pair f(String str) {
        zzg();
        long elapsedRealtime = this.f6809a.zzav().elapsedRealtime();
        String str2 = this.zzu;
        if (str2 == null || elapsedRealtime >= this.zzw) {
            this.zzw = elapsedRealtime + this.f6809a.zzf().zzi(str, zzen.zza);
            AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
            try {
                AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f6809a.zzau());
                this.zzu = "";
                String id = advertisingIdInfo.getId();
                if (id != null) {
                    this.zzu = id;
                }
                this.zzv = advertisingIdInfo.isLimitAdTrackingEnabled();
            } catch (Exception e2) {
                this.f6809a.zzay().zzc().zzb("Unable to get advertising id", e2);
                this.zzu = "";
            }
            AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
            return new Pair(this.zzu, Boolean.valueOf(this.zzv));
        }
        return new Pair(str2, Boolean.valueOf(this.zzv));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzai g() {
        zzg();
        return zzai.zzb(e().getString("consent_settings", "G1"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final Boolean h() {
        zzg();
        if (e().contains("measurement_enabled")) {
            return Boolean.valueOf(e().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void i(Boolean bool) {
        zzg();
        SharedPreferences.Editor edit = e().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void j(boolean z) {
        zzg();
        this.f6809a.zzay().zzj().zzb("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = e().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean k() {
        SharedPreferences sharedPreferences = this.zzt;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean l(long j2) {
        return j2 - this.zzf.zza() > this.zzj.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean m(int i2) {
        return zzai.zzj(i2, e().getInt("consent_source", 100));
    }
}
