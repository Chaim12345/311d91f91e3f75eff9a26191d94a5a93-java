package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzof;
import com.google.android.gms.internal.measurement.zzps;
import com.google.android.gms.internal.measurement.zzpy;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes2.dex */
public final class zzer extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzer(zzgk zzgkVar, long j2) {
        super(zzgkVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(1:3)(6:64|65|(1:67)(2:82|(1:84))|68|69|(20:71|(1:73)(1:80)|75|76|5|(1:63)(1:9)|10|11|13|(1:15)|16|17|(1:19)|20|(3:22|(1:24)(1:26)|25)|(3:28|(1:30)(1:33)|31)|34|(3:36|(1:38)(3:45|(3:48|(1:50)|46)|51)|(2:40|41)(2:43|44))|52|(0)(0)))|4|5|(1:7)|63|10|11|13|(0)|16|17|(0)|20|(0)|(0)|34|(0)|52|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0192, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0193, code lost:
        r11.f6809a.zzay().zzd().zzc("Fetching Google App Id failed with exception. appId", com.google.android.gms.measurement.internal.zzfa.g(r0), r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014b A[Catch: IllegalStateException -> 0x0192, TryCatch #0 {IllegalStateException -> 0x0192, blocks: (B:46:0x012a, B:49:0x0143, B:51:0x014b, B:55:0x0169, B:54:0x0165, B:57:0x0173, B:59:0x0189, B:61:0x018e, B:60:0x018c), top: B:83:0x012a }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0173 A[Catch: IllegalStateException -> 0x0192, TryCatch #0 {IllegalStateException -> 0x0192, blocks: (B:46:0x012a, B:49:0x0143, B:51:0x014b, B:55:0x0169, B:54:0x0165, B:57:0x0173, B:59:0x0189, B:61:0x018e, B:60:0x018c), top: B:83:0x012a }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0203  */
    @Override // com.google.android.gms.measurement.internal.zzf
    @EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void a() {
        String str;
        String str2;
        PackageInfo packageInfo;
        Object[] objArr;
        int zza;
        zzey zzj;
        String str3;
        List<String> e2;
        String zzc;
        String packageName = this.f6809a.zzau().getPackageName();
        PackageManager packageManager = this.f6809a.zzau().getPackageManager();
        String str4 = "Unknown";
        int i2 = Integer.MIN_VALUE;
        String str5 = EnvironmentCompat.MEDIA_UNKNOWN;
        if (packageManager == null) {
            this.f6809a.zzay().zzd().zzb("PackageManager is null, app identity information might be inaccurate. appId", zzfa.g(packageName));
        } else {
            try {
                str5 = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException unused) {
                this.f6809a.zzay().zzd().zzb("Error retrieving app installer package name. appId", zzfa.g(packageName));
            }
            if (str5 == null) {
                str5 = "manual_install";
            } else if ("com.android.vending".equals(str5)) {
                str5 = "";
            }
            try {
                packageInfo = packageManager.getPackageInfo(this.f6809a.zzau().getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                str = "Unknown";
            }
            if (packageInfo != null) {
                CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                str2 = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : "Unknown";
                try {
                    str4 = packageInfo.versionName;
                    i2 = packageInfo.versionCode;
                } catch (PackageManager.NameNotFoundException unused3) {
                    str = str4;
                    str4 = str2;
                    this.f6809a.zzay().zzd().zzc("Error retrieving package info. appId, appName", zzfa.g(packageName), str4);
                    str2 = str4;
                    str4 = str;
                    this.zza = packageName;
                    this.zzd = str5;
                    this.zzb = str4;
                    this.zzc = i2;
                    this.zze = str2;
                    this.zzf = 0L;
                    if (TextUtils.isEmpty(this.f6809a.zzw())) {
                    }
                    zza = this.f6809a.zza();
                    zzfa zzay = this.f6809a.zzay();
                    switch (zza) {
                    }
                    zzj.zza(str3);
                    this.zzk = "";
                    this.zzl = "";
                    this.f6809a.zzaw();
                    if (objArr != null) {
                    }
                    zzc = zziv.zzc(this.f6809a.zzau(), "google_app_id", this.f6809a.zzz());
                    this.zzk = true != TextUtils.isEmpty(zzc) ? zzc : "";
                    if (!TextUtils.isEmpty(zzc)) {
                    }
                    if (zza == 0) {
                    }
                    this.zzh = null;
                    this.f6809a.zzaw();
                    e2 = this.f6809a.zzf().e("analytics.safelisted_events");
                    if (e2 != null) {
                    }
                    this.zzh = e2;
                    if (packageManager == null) {
                    }
                }
                this.zza = packageName;
                this.zzd = str5;
                this.zzb = str4;
                this.zzc = i2;
                this.zze = str2;
                this.zzf = 0L;
                objArr = (TextUtils.isEmpty(this.f6809a.zzw()) && "am".equals(this.f6809a.zzx())) ? 1 : null;
                zza = this.f6809a.zza();
                zzfa zzay2 = this.f6809a.zzay();
                switch (zza) {
                    case 0:
                        zzj = zzay2.zzj();
                        str3 = "App measurement collection enabled";
                        break;
                    case 1:
                        zzj = zzay2.zzi();
                        str3 = "App measurement deactivated via the manifest";
                        break;
                    case 2:
                        zzj = zzay2.zzj();
                        str3 = "App measurement deactivated via the init parameters";
                        break;
                    case 3:
                        zzj = zzay2.zzi();
                        str3 = "App measurement disabled by setAnalyticsCollectionEnabled(false)";
                        break;
                    case 4:
                        zzj = zzay2.zzi();
                        str3 = "App measurement disabled via the manifest";
                        break;
                    case 5:
                        zzj = zzay2.zzj();
                        str3 = "App measurement disabled via the init parameters";
                        break;
                    case 6:
                        zzj = zzay2.zzl();
                        str3 = "App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics";
                        break;
                    case 7:
                        zzj = zzay2.zzi();
                        str3 = "App measurement disabled via the global data collection setting";
                        break;
                    default:
                        zzj = zzay2.zzi();
                        str3 = "App measurement disabled due to denied storage consent";
                        break;
                }
                zzj.zza(str3);
                this.zzk = "";
                this.zzl = "";
                this.f6809a.zzaw();
                if (objArr != null) {
                    this.zzl = this.f6809a.zzw();
                }
                zzc = zziv.zzc(this.f6809a.zzau(), "google_app_id", this.f6809a.zzz());
                this.zzk = true != TextUtils.isEmpty(zzc) ? zzc : "";
                if (!TextUtils.isEmpty(zzc)) {
                    Context zzau = this.f6809a.zzau();
                    String zzz = this.f6809a.zzz();
                    Preconditions.checkNotNull(zzau);
                    Resources resources = zzau.getResources();
                    if (TextUtils.isEmpty(zzz)) {
                        zzz = zzgc.zza(zzau);
                    }
                    this.zzl = zzgc.zzb("admob_app_id", resources, zzz);
                }
                if (zza == 0) {
                    this.f6809a.zzay().zzj().zzc("App measurement enabled for app package, google app id", this.zza, TextUtils.isEmpty(this.zzk) ? this.zzl : this.zzk);
                }
                this.zzh = null;
                this.f6809a.zzaw();
                e2 = this.f6809a.zzf().e("analytics.safelisted_events");
                if (e2 != null) {
                    if (e2.isEmpty()) {
                        this.f6809a.zzay().zzl().zza("Safelisted event list is empty. Ignoring");
                    } else {
                        for (String str6 : e2) {
                            if (!this.f6809a.zzv().v("safelisted event", str6)) {
                            }
                        }
                    }
                    if (packageManager == null) {
                        this.zzj = InstantApps.isInstantApp(this.f6809a.zzau()) ? 1 : 0;
                        return;
                    } else {
                        this.zzj = 0;
                        return;
                    }
                }
                this.zzh = e2;
                if (packageManager == null) {
                }
            }
        }
        str2 = "Unknown";
        this.zza = packageName;
        this.zzd = str5;
        this.zzb = str4;
        this.zzc = i2;
        this.zze = str2;
        this.zzf = 0L;
        if (TextUtils.isEmpty(this.f6809a.zzw())) {
        }
        zza = this.f6809a.zza();
        zzfa zzay22 = this.f6809a.zzay();
        switch (zza) {
        }
        zzj.zza(str3);
        this.zzk = "";
        this.zzl = "";
        this.f6809a.zzaw();
        if (objArr != null) {
        }
        zzc = zziv.zzc(this.f6809a.zzau(), "google_app_id", this.f6809a.zzz());
        this.zzk = true != TextUtils.isEmpty(zzc) ? zzc : "";
        if (!TextUtils.isEmpty(zzc)) {
        }
        if (zza == 0) {
        }
        this.zzh = null;
        this.f6809a.zzaw();
        e2 = this.f6809a.zzf().e("analytics.safelisted_events");
        if (e2 != null) {
        }
        this.zzh = e2;
        if (packageManager == null) {
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean c() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final int d() {
        zza();
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final int e() {
        zza();
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0269  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzq f(String str) {
        String str2;
        Class<?> loadClass;
        zzey zzm;
        String str3;
        Object invoke;
        long zza;
        String str4;
        long min;
        Boolean c2;
        String str5;
        long j2;
        String str6;
        String str7;
        zzg();
        String zzl = zzl();
        String zzm2 = zzm();
        zza();
        String str8 = this.zzb;
        zza();
        long j3 = this.zzc;
        zza();
        Preconditions.checkNotNull(this.zzd);
        String str9 = this.zzd;
        this.f6809a.zzf().zzh();
        zza();
        zzg();
        long j4 = this.zzf;
        if (j4 == 0) {
            zzlt zzv = this.f6809a.zzv();
            Context zzau = this.f6809a.zzau();
            String packageName = this.f6809a.zzau().getPackageName();
            zzv.zzg();
            Preconditions.checkNotNull(zzau);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = zzau.getPackageManager();
            MessageDigest h2 = zzlt.h();
            long j5 = -1;
            if (h2 == null) {
                zzv.f6809a.zzay().zzd().zza("Could not get MD5 instance");
            } else {
                if (packageManager != null) {
                    try {
                        if (zzv.A(zzau, packageName)) {
                            j5 = 0;
                            zzv = zzv;
                        } else {
                            Signature[] signatureArr = Wrappers.packageManager(zzau).getPackageInfo(zzv.f6809a.zzau().getPackageName(), 64).signatures;
                            if (signatureArr == null || signatureArr.length <= 0) {
                                zzv.f6809a.zzay().zzk().zza("Could not get signatures");
                                zzv = zzv;
                            } else {
                                long N = zzlt.N(h2.digest(signatureArr[0].toByteArray()));
                                j5 = N;
                                zzv = N;
                            }
                        }
                    } catch (PackageManager.NameNotFoundException e2) {
                        zzv.f6809a.zzay().zzd().zzb("Package name not found", e2);
                    }
                }
                j4 = 0;
                this.zzf = j4;
            }
            j4 = j5;
            this.zzf = j4;
        }
        long j6 = j4;
        boolean zzJ = this.f6809a.zzJ();
        boolean z = !this.f6809a.zzm().zzk;
        zzg();
        if (this.f6809a.zzJ()) {
            zzpy.zzc();
            if (this.f6809a.zzf().zzs(null, zzen.zzab)) {
                this.f6809a.zzay().zzj().zza("Disabled IID for tests.");
            } else {
                try {
                    loadClass = this.f6809a.zzau().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                } catch (ClassNotFoundException unused) {
                }
                if (loadClass != null) {
                    try {
                        invoke = loadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, this.f6809a.zzau());
                    } catch (Exception unused2) {
                        zzm = this.f6809a.zzay().zzm();
                        str3 = "Failed to obtain Firebase Analytics instance";
                    }
                    if (invoke != null) {
                        try {
                            str2 = (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                        } catch (Exception unused3) {
                            zzm = this.f6809a.zzay().zzl();
                            str3 = "Failed to retrieve Firebase Instance Id";
                            zzm.zza(str3);
                            str2 = null;
                            zzgk zzgkVar = this.f6809a;
                            zza = zzgkVar.zzm().zzc.zza();
                            if (zza != 0) {
                            }
                            zza();
                            int i2 = this.zzj;
                            boolean zzr = this.f6809a.zzf().zzr();
                            zzfp zzm3 = this.f6809a.zzm();
                            zzm3.zzg();
                            boolean z2 = zzm3.e().getBoolean("deferred_analytics_collection", false);
                            zza();
                            String str10 = this.zzl;
                            if (this.f6809a.zzf().c("google_analytics_default_allow_ad_personalization_signals") != null) {
                            }
                            long j7 = this.zzg;
                            List list = this.zzh;
                            String zzh = this.f6809a.zzm().g().zzh();
                            if (this.zzi != null) {
                            }
                            String str11 = this.zzi;
                            zzps.zzc();
                            if (this.f6809a.zzf().zzs(null, zzen.zzaI)) {
                            }
                            return new zzq(str4, zzm2, str8, j3, str9, 64000L, j6, str, zzJ, z, str2, 0L, min, i2, zzr, z2, str5, r26, j2, list, (String) null, zzh, str6, str7);
                        }
                        zzgk zzgkVar2 = this.f6809a;
                        zza = zzgkVar2.zzm().zzc.zza();
                        if (zza != 0) {
                            str4 = zzl;
                            min = zzgkVar2.f6760c;
                        } else {
                            str4 = zzl;
                            min = Math.min(zzgkVar2.f6760c, zza);
                        }
                        zza();
                        int i22 = this.zzj;
                        boolean zzr2 = this.f6809a.zzf().zzr();
                        zzfp zzm32 = this.f6809a.zzm();
                        zzm32.zzg();
                        boolean z22 = zzm32.e().getBoolean("deferred_analytics_collection", false);
                        zza();
                        String str102 = this.zzl;
                        Boolean valueOf = this.f6809a.zzf().c("google_analytics_default_allow_ad_personalization_signals") != null ? null : Boolean.valueOf(!c2.booleanValue());
                        long j72 = this.zzg;
                        List list2 = this.zzh;
                        String zzh2 = this.f6809a.zzm().g().zzh();
                        if (this.zzi != null) {
                            str5 = str102;
                            j2 = j72;
                            this.zzi = this.f6809a.zzf().zzs(null, zzen.zzaN) ? this.f6809a.zzv().g() : "";
                        } else {
                            str5 = str102;
                            j2 = j72;
                        }
                        String str112 = this.zzi;
                        zzps.zzc();
                        if (this.f6809a.zzf().zzs(null, zzen.zzaI)) {
                            str6 = str112;
                            str7 = null;
                        } else {
                            zzg();
                            if (this.zzn == 0) {
                                str6 = str112;
                            } else {
                                str6 = str112;
                                long currentTimeMillis = this.f6809a.zzav().currentTimeMillis() - this.zzn;
                                if (this.zzm != null && currentTimeMillis > 86400000 && this.zzo == null) {
                                    i();
                                }
                            }
                            if (this.zzm == null) {
                                i();
                            }
                            str7 = this.zzm;
                        }
                        return new zzq(str4, zzm2, str8, j3, str9, 64000L, j6, str, zzJ, z, str2, 0L, min, i22, zzr2, z22, str5, valueOf, j2, list2, (String) null, zzh2, str6, str7);
                    }
                    str2 = null;
                    zzgk zzgkVar22 = this.f6809a;
                    zza = zzgkVar22.zzm().zzc.zza();
                    if (zza != 0) {
                    }
                    zza();
                    int i222 = this.zzj;
                    boolean zzr22 = this.f6809a.zzf().zzr();
                    zzfp zzm322 = this.f6809a.zzm();
                    zzm322.zzg();
                    boolean z222 = zzm322.e().getBoolean("deferred_analytics_collection", false);
                    zza();
                    String str1022 = this.zzl;
                    if (this.f6809a.zzf().c("google_analytics_default_allow_ad_personalization_signals") != null) {
                    }
                    long j722 = this.zzg;
                    List list22 = this.zzh;
                    String zzh22 = this.f6809a.zzm().g().zzh();
                    if (this.zzi != null) {
                    }
                    String str1122 = this.zzi;
                    zzps.zzc();
                    if (this.f6809a.zzf().zzs(null, zzen.zzaI)) {
                    }
                    return new zzq(str4, zzm2, str8, j3, str9, 64000L, j6, str, zzJ, z, str2, 0L, min, i222, zzr22, z222, str5, valueOf, j2, list22, (String) null, zzh22, str6, str7);
                }
            }
        }
        str2 = null;
        zzgk zzgkVar222 = this.f6809a;
        zza = zzgkVar222.zzm().zzc.zza();
        if (zza != 0) {
        }
        zza();
        int i2222 = this.zzj;
        boolean zzr222 = this.f6809a.zzf().zzr();
        zzfp zzm3222 = this.f6809a.zzm();
        zzm3222.zzg();
        boolean z2222 = zzm3222.e().getBoolean("deferred_analytics_collection", false);
        zza();
        String str10222 = this.zzl;
        if (this.f6809a.zzf().c("google_analytics_default_allow_ad_personalization_signals") != null) {
        }
        long j7222 = this.zzg;
        List list222 = this.zzh;
        String zzh222 = this.f6809a.zzm().g().zzh();
        if (this.zzi != null) {
        }
        String str11222 = this.zzi;
        zzps.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzaI)) {
        }
        return new zzq(str4, zzm2, str8, j3, str9, 64000L, j6, str, zzJ, z, str2, 0L, min, i2222, zzr222, z2222, str5, valueOf, j2, list222, (String) null, zzh222, str6, str7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final String g() {
        zza();
        return this.zzl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final List h() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void i() {
        String format;
        zzg();
        if (this.f6809a.zzm().g().zzi(zzah.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            this.f6809a.zzv().i().nextBytes(bArr);
            format = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        } else {
            this.f6809a.zzay().zzc().zza("Analytics Storage consent is not granted");
            format = null;
        }
        zzey zzc = this.f6809a.zzay().zzc();
        Object[] objArr = new Object[1];
        objArr[0] = format == null ? "null" : "not null";
        zzc.zza(String.format("Resetting session stitching token to %s", objArr));
        this.zzm = format;
        this.zzn = this.f6809a.zzav().currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean j(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final String zzm() {
        zzof.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzal)) {
            zzg();
        }
        zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }
}
