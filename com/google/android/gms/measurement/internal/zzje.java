package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import androidx.car.app.CarContext;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes2.dex */
public final class zzje extends zzf {
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    protected zziw f6918b;
    private volatile zziw zzb;
    private volatile zziw zzc;
    private final Map zzd;
    @GuardedBy("activityLock")
    private Activity zze;
    @GuardedBy("activityLock")
    private volatile boolean zzf;
    private volatile zziw zzg;
    private zziw zzh;
    @GuardedBy("activityLock")
    private boolean zzi;
    private final Object zzj;
    @GuardedBy("this")
    private zziw zzk;
    @GuardedBy("this")
    private String zzl;

    public zzje(zzgk zzgkVar) {
        super(zzgkVar);
        this.zzj = new Object();
        this.zzd = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void h(zzje zzjeVar, Bundle bundle, zziw zziwVar, zziw zziwVar2, long j2) {
        bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
        bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        zzjeVar.zzB(zziwVar, zziwVar2, j2, true, zzjeVar.f6809a.zzv().Q(null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, null, false));
    }

    @MainThread
    private final void zzA(Activity activity, zziw zziwVar, boolean z) {
        zziw zziwVar2;
        zziw zziwVar3 = this.zzb == null ? this.zzc : this.zzb;
        if (zziwVar.zzb == null) {
            zziwVar2 = new zziw(zziwVar.zza, activity != null ? e(activity.getClass(), "Activity") : null, zziwVar.zzc, zziwVar.zze, zziwVar.zzf);
        } else {
            zziwVar2 = zziwVar;
        }
        this.zzc = this.zzb;
        this.zzb = zziwVar2;
        this.f6809a.zzaz().zzp(new zziz(this, zziwVar2, zziwVar3, this.f6809a.zzav().elapsedRealtime(), z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r8v5, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r8v6, types: [android.os.Bundle, long] */
    @WorkerThread
    public final void zzB(zziw zziwVar, zziw zziwVar2, long j2, boolean z, Bundle bundle) {
        long j3;
        zzg();
        boolean z2 = false;
        boolean z3 = (zziwVar2 != null && zziwVar2.zzc == zziwVar.zzc && zzix.zza(zziwVar2.zzb, zziwVar.zzb) && zzix.zza(zziwVar2.zza, zziwVar.zza)) ? false : true;
        if (z && this.f6918b != null) {
            z2 = true;
        }
        if (z3) {
            zzlt.zzK(zziwVar, bundle != null ? new Bundle(bundle) : new Bundle(), true);
            if (zziwVar2 != null) {
                String str = zziwVar2.zza;
                if (str != null) {
                    "_pn".putString("_pn", str);
                }
                String str2 = zziwVar2.zzb;
                if (str2 != null) {
                    "_pc".putString("_pc", str2);
                }
                ?? r8 = zziwVar2.zzc;
                r8.putLong("_pi", r8);
            }
            ?? r82 = 0;
            if (z2) {
                zzks zzksVar = this.f6809a.zzu().f7015c;
                long j4 = j2 - zzksVar.f7011b;
                zzksVar.f7011b = j2;
                if (j4 > 0) {
                    this.f6809a.zzv().j(null, j4);
                }
            }
            if (!this.f6809a.zzf().zzu()) {
                r82.putLong("_mst", 1L);
            }
            String str3 = true != zziwVar.zze ? DebugKt.DEBUG_PROPERTY_VALUE_AUTO : CarContext.APP_SERVICE;
            long currentTimeMillis = this.f6809a.zzav().currentTimeMillis();
            if (zziwVar.zze) {
                currentTimeMillis = zziwVar.zzf;
                if (currentTimeMillis != 0) {
                    j3 = currentTimeMillis;
                    this.f6809a.zzq().g(str3, "_vs", j3, null);
                }
            }
            j3 = currentTimeMillis;
            this.f6809a.zzq().g(str3, "_vs", j3, null);
        }
        if (z2) {
            zzC(this.f6918b, true, j2);
        }
        this.f6918b = zziwVar;
        if (zziwVar.zze) {
            this.zzh = zziwVar;
        }
        this.f6809a.zzt().h(zziwVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzC(zziw zziwVar, boolean z, long j2) {
        this.f6809a.zzd().zzf(this.f6809a.zzav().elapsedRealtime());
        if (!this.f6809a.zzu().f7015c.zzd(zziwVar != null && zziwVar.f6896a, z, j2) || zziwVar == null) {
            return;
        }
        zziwVar.f6896a = false;
    }

    @MainThread
    private final zziw zzz(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zziw zziwVar = (zziw) this.zzd.get(activity);
        if (zziwVar == null) {
            zziw zziwVar2 = new zziw(null, e(activity.getClass(), "Activity"), this.f6809a.zzv().zzq());
            this.zzd.put(activity, zziwVar2);
            zziwVar = zziwVar2;
        }
        return this.zzg != null ? this.zzg : zziwVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean c() {
        return false;
    }

    @VisibleForTesting
    final String e(Class cls, String str) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return "Activity";
        }
        String[] split = canonicalName.split("\\.");
        int length = split.length;
        String str2 = length > 0 ? split[length - 1] : "";
        int length2 = str2.length();
        this.f6809a.zzf();
        if (length2 > 100) {
            this.f6809a.zzf();
            return str2.substring(0, 100);
        }
        return str2;
    }

    public final zziw zzi() {
        return this.zzb;
    }

    @WorkerThread
    public final zziw zzj(boolean z) {
        zza();
        zzg();
        if (z) {
            zziw zziwVar = this.f6918b;
            return zziwVar != null ? zziwVar : this.zzh;
        }
        return this.f6918b;
    }

    @MainThread
    public final void zzr(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (!this.f6809a.zzf().zzu() || bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.zzd.put(activity, new zziw(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle2.getString("referrer_name"), bundle2.getLong("id")));
    }

    @MainThread
    public final void zzs(Activity activity) {
        synchronized (this.zzj) {
            if (activity == this.zze) {
                this.zze = null;
            }
        }
        if (this.f6809a.zzf().zzu()) {
            this.zzd.remove(activity);
        }
    }

    @MainThread
    public final void zzt(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        long elapsedRealtime = this.f6809a.zzav().elapsedRealtime();
        if (!this.f6809a.zzf().zzu()) {
            this.zzb = null;
            this.f6809a.zzaz().zzp(new zzjb(this, elapsedRealtime));
            return;
        }
        zziw zzz = zzz(activity);
        this.zzc = this.zzb;
        this.zzb = null;
        this.f6809a.zzaz().zzp(new zzjc(this, zzz, elapsedRealtime));
    }

    @MainThread
    public final void zzu(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = true;
            if (activity != this.zze) {
                synchronized (this.zzj) {
                    this.zze = activity;
                    this.zzf = false;
                }
                if (this.f6809a.zzf().zzu()) {
                    this.zzg = null;
                    this.f6809a.zzaz().zzp(new zzjd(this));
                }
            }
        }
        if (!this.f6809a.zzf().zzu()) {
            this.zzb = this.zzg;
            this.f6809a.zzaz().zzp(new zzja(this));
            return;
        }
        zzA(activity, zzz(activity), false);
        zzd zzd = this.f6809a.zzd();
        zzd.f6809a.zzaz().zzp(new zzc(zzd, zzd.f6809a.zzav().elapsedRealtime()));
    }

    @MainThread
    public final void zzv(Activity activity, Bundle bundle) {
        zziw zziwVar;
        if (!this.f6809a.zzf().zzu() || bundle == null || (zziwVar = (zziw) this.zzd.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", zziwVar.zzc);
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zziwVar.zza);
        bundle2.putString("referrer_name", zziwVar.zzb);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
        if (r5.length() <= 100) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b4, code lost:
        if (r6.length() <= 100) goto L36;
     */
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzw(@NonNull Activity activity, @Size(max = 36, min = 1) String str, @Size(max = 36, min = 1) String str2) {
        if (!this.f6809a.zzf().zzu()) {
            this.f6809a.zzay().zzl().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        zziw zziwVar = this.zzb;
        if (zziwVar == null) {
            this.f6809a.zzay().zzl().zza("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzd.get(activity) == null) {
            this.f6809a.zzay().zzl().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = e(activity.getClass(), "Activity");
            }
            boolean zza = zzix.zza(zziwVar.zzb, str2);
            boolean zza2 = zzix.zza(zziwVar.zza, str);
            if (zza && zza2) {
                this.f6809a.zzay().zzl().zza("setCurrentScreen cannot be called with the same class and name");
                return;
            }
            if (str != null) {
                if (str.length() > 0) {
                    this.f6809a.zzf();
                }
                this.f6809a.zzay().zzl().zzb("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
                return;
            }
            if (str2 != null) {
                if (str2.length() > 0) {
                    this.f6809a.zzf();
                }
                this.f6809a.zzay().zzl().zzb("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
                return;
            }
            this.f6809a.zzay().zzj().zzc("Setting current screen to name, class", str == null ? "null" : str, str2);
            zziw zziwVar2 = new zziw(str, str2, this.f6809a.zzv().zzq());
            this.zzd.put(activity, zziwVar2);
            zzA(activity, zziwVar2, true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r2 > 100) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0063, code lost:
        if (r4 > 100) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzx(Bundle bundle, long j2) {
        String str;
        synchronized (this.zzj) {
            if (!this.zzi) {
                this.f6809a.zzay().zzl().zza("Cannot log screen view event when the app is in the background.");
                return;
            }
            String string = bundle.getString(FirebaseAnalytics.Param.SCREEN_NAME);
            if (string != null) {
                if (string.length() > 0) {
                    int length = string.length();
                    this.f6809a.zzf();
                }
                this.f6809a.zzay().zzl().zzb("Invalid screen name length for screen view. Length", Integer.valueOf(string.length()));
                return;
            }
            String string2 = bundle.getString(FirebaseAnalytics.Param.SCREEN_CLASS);
            if (string2 != null) {
                if (string2.length() > 0) {
                    int length2 = string2.length();
                    this.f6809a.zzf();
                }
                this.f6809a.zzay().zzl().zzb("Invalid screen class length for screen view. Length", Integer.valueOf(string2.length()));
                return;
            }
            if (string2 == null) {
                Activity activity = this.zze;
                str = activity != null ? e(activity.getClass(), "Activity") : "Activity";
            } else {
                str = string2;
            }
            zziw zziwVar = this.zzb;
            if (this.zzf && zziwVar != null) {
                this.zzf = false;
                boolean zza = zzix.zza(zziwVar.zzb, str);
                boolean zza2 = zzix.zza(zziwVar.zza, string);
                if (zza && zza2) {
                    this.f6809a.zzay().zzl().zza("Ignoring call to log screen view event with duplicate parameters.");
                    return;
                }
            }
            this.f6809a.zzay().zzj().zzc("Logging screen view with name, class", string == null ? "null" : string, str == null ? "null" : str);
            zziw zziwVar2 = this.zzb == null ? this.zzc : this.zzb;
            zziw zziwVar3 = new zziw(string, str, this.f6809a.zzv().zzq(), true, j2);
            this.zzb = zziwVar3;
            this.zzc = zziwVar2;
            this.zzg = zziwVar3;
            this.f6809a.zzaz().zzp(new zziy(this, bundle, zziwVar3, zziwVar2, this.f6809a.zzav().elapsedRealtime()));
        }
    }

    @WorkerThread
    public final void zzy(String str, zziw zziwVar) {
        zzg();
        synchronized (this) {
            String str2 = this.zzl;
            if (str2 == null || str2.equals(str) || zziwVar != null) {
                this.zzl = str;
                this.zzk = zziwVar;
            }
        }
    }
}
