package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes2.dex */
public final class zzag extends zzhd {
    private Boolean zza;
    private zzaf zzb;
    private Boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzag(zzgk zzgkVar) {
        super(zzgkVar);
        this.zzb = zzae.zza;
    }

    public static final long zzA() {
        return ((Long) zzen.zzC.zza(null)).longValue();
    }

    private final String zzB(String str, String str2) {
        zzey zzd;
        String str3;
        try {
            String str4 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, "");
            Preconditions.checkNotNull(str4);
            return str4;
        } catch (ClassNotFoundException e2) {
            e = e2;
            zzd = this.f6809a.zzay().zzd();
            str3 = "Could not find SystemProperties class";
            zzd.zzb(str3, e);
            return "";
        } catch (IllegalAccessException e3) {
            e = e3;
            zzd = this.f6809a.zzay().zzd();
            str3 = "Could not access SystemProperties.get()";
            zzd.zzb(str3, e);
            return "";
        } catch (NoSuchMethodException e4) {
            e = e4;
            zzd = this.f6809a.zzay().zzd();
            str3 = "Could not find SystemProperties.get() method";
            zzd.zzb(str3, e);
            return "";
        } catch (InvocationTargetException e5) {
            e = e5;
            zzd = this.f6809a.zzay().zzd();
            str3 = "SystemProperties.get() threw an exception";
            zzd.zzb(str3, e);
            return "";
        }
    }

    public static final long zzz() {
        return ((Long) zzen.zzc.zza(null)).longValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(@Size(min = 1) String str) {
        return zzf(str, zzen.zzG, 500, 2000);
    }

    @VisibleForTesting
    final Bundle b() {
        try {
            if (this.f6809a.zzau().getPackageManager() == null) {
                this.f6809a.zzay().zzd().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(this.f6809a.zzau()).getApplicationInfo(this.f6809a.zzau().getPackageName(), 128);
            if (applicationInfo == null) {
                this.f6809a.zzay().zzd().zza("Failed to load metadata: ApplicationInfo is null");
                return null;
            }
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException e2) {
            this.f6809a.zzay().zzd().zzb("Failed to load metadata: Package name not found", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public final Boolean c(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle b2 = b();
        if (b2 == null) {
            this.f6809a.zzay().zzd().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (b2.containsKey(str)) {
            return Boolean.valueOf(b2.getBoolean(str));
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String d() {
        this.f6809a.zzaw();
        return "FA";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List e(@Size(min = 1) String str) {
        Integer valueOf;
        Preconditions.checkNotEmpty("analytics.safelisted_events");
        Bundle b2 = b();
        if (b2 == null) {
            this.f6809a.zzay().zzd().zza("Failed to load metadata: Metadata bundle is null");
        } else if (b2.containsKey("analytics.safelisted_events")) {
            valueOf = Integer.valueOf(b2.getInt("analytics.safelisted_events"));
            if (valueOf != null) {
                try {
                    String[] stringArray = this.f6809a.zzau().getResources().getStringArray(valueOf.intValue());
                    if (stringArray == null) {
                        return null;
                    }
                    return Arrays.asList(stringArray);
                } catch (Resources.NotFoundException e2) {
                    this.f6809a.zzay().zzd().zzb("Failed to load string array from metadata: resource not found", e2);
                }
            }
            return null;
        }
        valueOf = null;
        if (valueOf != null) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f(zzaf zzafVar) {
        this.zzb = zzafVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean g() {
        if (this.zza == null) {
            Boolean c2 = c("app_measurement_lite");
            this.zza = c2;
            if (c2 == null) {
                this.zza = Boolean.FALSE;
            }
        }
        return this.zza.booleanValue() || !this.f6809a.zzN();
    }

    @WorkerThread
    public final double zza(String str, zzem zzemVar) {
        if (str != null) {
            String zza = this.zzb.zza(str, zzemVar.zzb());
            if (!TextUtils.isEmpty(zza)) {
                try {
                    return ((Double) zzemVar.zza(Double.valueOf(Double.parseDouble(zza)))).doubleValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Double) zzemVar.zza(null)).doubleValue();
    }

    public final int zzc() {
        zzlt zzv = this.f6809a.zzv();
        Boolean p2 = zzv.f6809a.zzt().p();
        if (zzv.zzm() < 201500) {
            return (p2 == null || p2.booleanValue()) ? 25 : 100;
        }
        return 100;
    }

    public final int zzd(@Size(min = 1) String str) {
        return zzf(str, zzen.zzH, 25, 100);
    }

    @WorkerThread
    public final int zze(String str, zzem zzemVar) {
        if (str != null) {
            String zza = this.zzb.zza(str, zzemVar.zzb());
            if (!TextUtils.isEmpty(zza)) {
                try {
                    return ((Integer) zzemVar.zza(Integer.valueOf(Integer.parseInt(zza)))).intValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Integer) zzemVar.zza(null)).intValue();
    }

    @WorkerThread
    public final int zzf(String str, zzem zzemVar, int i2, int i3) {
        return Math.max(Math.min(zze(str, zzemVar), i3), i2);
    }

    public final long zzh() {
        this.f6809a.zzaw();
        return 64000L;
    }

    @WorkerThread
    public final long zzi(String str, zzem zzemVar) {
        if (str != null) {
            String zza = this.zzb.zza(str, zzemVar.zzb());
            if (!TextUtils.isEmpty(zza)) {
                try {
                    return ((Long) zzemVar.zza(Long.valueOf(Long.parseLong(zza)))).longValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Long) zzemVar.zza(null)).longValue();
    }

    public final String zzl() {
        return zzB("debug.firebase.analytics.app", "");
    }

    public final String zzm() {
        return zzB("debug.deferred.deeplink", "");
    }

    @WorkerThread
    public final String zzo(String str, zzem zzemVar) {
        return (String) zzemVar.zza(str == null ? null : this.zzb.zza(str, zzemVar.zzb()));
    }

    public final boolean zzr() {
        Boolean c2 = c("google_analytics_adid_collection_enabled");
        return c2 == null || c2.booleanValue();
    }

    @WorkerThread
    public final boolean zzs(String str, zzem zzemVar) {
        Object zza;
        if (str != null) {
            String zza2 = this.zzb.zza(str, zzemVar.zzb());
            if (!TextUtils.isEmpty(zza2)) {
                zza = zzemVar.zza(Boolean.valueOf("1".equals(zza2)));
                return ((Boolean) zza).booleanValue();
            }
        }
        zza = zzemVar.zza(null);
        return ((Boolean) zza).booleanValue();
    }

    public final boolean zzt(String str) {
        return "1".equals(this.zzb.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzu() {
        Boolean c2 = c("google_analytics_automatic_screen_reporting_enabled");
        return c2 == null || c2.booleanValue();
    }

    public final boolean zzv() {
        this.f6809a.zzaw();
        Boolean c2 = c("firebase_analytics_collection_deactivated");
        return c2 != null && c2.booleanValue();
    }

    public final boolean zzw(String str) {
        return "1".equals(this.zzb.zza(str, "measurement.event_sampling_enabled"));
    }

    @EnsuresNonNull({"this.isMainProcess"})
    public final boolean zzy() {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    ApplicationInfo applicationInfo = this.f6809a.zzau().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = false;
                        if (str != null && str.equals(myProcessName)) {
                            z = true;
                        }
                        this.zzc = Boolean.valueOf(z);
                    }
                    if (this.zzc == null) {
                        this.zzc = Boolean.TRUE;
                        this.f6809a.zzay().zzd().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzc.booleanValue();
    }
}
