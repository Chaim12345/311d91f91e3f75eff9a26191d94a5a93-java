package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzoc;
import com.google.firebase.messaging.Constants;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.DebugKt;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class zzgk implements zzhf {
    private static volatile zzgk zzd;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    protected Boolean f6758a;
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    protected Boolean f6759b;
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    final long f6760c;
    private zzer zzA;
    private Boolean zzC;
    private long zzD;
    private volatile Boolean zzE;
    private volatile boolean zzF;
    private int zzG;
    private final Context zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private final zzab zzj;
    private final zzag zzk;
    private final zzfp zzl;
    private final zzfa zzm;
    private final zzgh zzn;
    private final zzku zzo;
    private final zzlt zzp;
    private final zzev zzq;
    private final Clock zzr;
    private final zzje zzs;
    private final zzip zzt;
    private final zzd zzu;
    private final zzit zzv;
    private final String zzw;
    private zzet zzx;
    private zzke zzy;
    private zzaq zzz;
    private boolean zzB = false;
    private final AtomicInteger zzH = new AtomicInteger(0);

    zzgk(zzhn zzhnVar) {
        zzey zzk;
        String str;
        Bundle bundle;
        boolean z = false;
        Preconditions.checkNotNull(zzhnVar);
        Context context = zzhnVar.f6811a;
        zzab zzabVar = new zzab(context);
        this.zzj = zzabVar;
        zzek.f6716a = zzabVar;
        this.zze = context;
        this.zzf = zzhnVar.f6812b;
        this.zzg = zzhnVar.f6813c;
        this.zzh = zzhnVar.f6814d;
        this.zzi = zzhnVar.f6818h;
        this.zzE = zzhnVar.f6815e;
        this.zzw = zzhnVar.f6820j;
        this.zzF = true;
        com.google.android.gms.internal.measurement.zzcl zzclVar = zzhnVar.f6817g;
        if (zzclVar != null && (bundle = zzclVar.zzg) != null) {
            Object obj = bundle.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.f6758a = (Boolean) obj;
            }
            Object obj2 = zzclVar.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.f6759b = (Boolean) obj2;
            }
        }
        com.google.android.gms.internal.measurement.zzhy.zze(context);
        Clock defaultClock = DefaultClock.getInstance();
        this.zzr = defaultClock;
        Long l2 = zzhnVar.f6819i;
        this.f6760c = l2 != null ? l2.longValue() : defaultClock.currentTimeMillis();
        this.zzk = new zzag(this);
        zzfp zzfpVar = new zzfp(this);
        zzfpVar.zzv();
        this.zzl = zzfpVar;
        zzfa zzfaVar = new zzfa(this);
        zzfaVar.zzv();
        this.zzm = zzfaVar;
        zzlt zzltVar = new zzlt(this);
        zzltVar.zzv();
        this.zzp = zzltVar;
        this.zzq = new zzev(new zzhm(zzhnVar, this));
        this.zzu = new zzd(this);
        zzje zzjeVar = new zzje(this);
        zzjeVar.zzb();
        this.zzs = zzjeVar;
        zzip zzipVar = new zzip(this);
        zzipVar.zzb();
        this.zzt = zzipVar;
        zzku zzkuVar = new zzku(this);
        zzkuVar.zzb();
        this.zzo = zzkuVar;
        zzit zzitVar = new zzit(this);
        zzitVar.zzv();
        this.zzv = zzitVar;
        zzgh zzghVar = new zzgh(this);
        zzghVar.zzv();
        this.zzn = zzghVar;
        com.google.android.gms.internal.measurement.zzcl zzclVar2 = zzhnVar.f6817g;
        z = (zzclVar2 == null || zzclVar2.zzb == 0) ? true : z;
        if (context.getApplicationContext() instanceof Application) {
            zzip zzq = zzq();
            if (zzq.f6809a.zze.getApplicationContext() instanceof Application) {
                Application application = (Application) zzq.f6809a.zze.getApplicationContext();
                if (zzq.f6892b == null) {
                    zzq.f6892b = new zzio(zzq, null);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzq.f6892b);
                    application.registerActivityLifecycleCallbacks(zzq.f6892b);
                    zzk = zzq.f6809a.zzay().zzj();
                    str = "Registered activity lifecycle callback";
                }
            }
            zzghVar.zzp(new zzgj(this, zzhnVar));
        }
        zzk = zzay().zzk();
        str = "Application context is not an Application";
        zzk.zza(str);
        zzghVar.zzp(new zzgj(this, zzhnVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void a(zzgk zzgkVar, zzhn zzhnVar) {
        zzgkVar.zzaz().zzg();
        zzgkVar.zzk.d();
        zzaq zzaqVar = new zzaq(zzgkVar);
        zzaqVar.zzv();
        zzgkVar.zzz = zzaqVar;
        zzer zzerVar = new zzer(zzgkVar, zzhnVar.f6816f);
        zzerVar.zzb();
        zzgkVar.zzA = zzerVar;
        zzet zzetVar = new zzet(zzgkVar);
        zzetVar.zzb();
        zzgkVar.zzx = zzetVar;
        zzke zzkeVar = new zzke(zzgkVar);
        zzkeVar.zzb();
        zzgkVar.zzy = zzkeVar;
        zzgkVar.zzp.zzw();
        zzgkVar.zzl.zzw();
        zzgkVar.zzA.zzc();
        zzey zzi = zzgkVar.zzay().zzi();
        zzgkVar.zzk.zzh();
        zzi.zzb("App measurement initialized, version", 64000L);
        zzgkVar.zzay().zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzl = zzerVar.zzl();
        if (TextUtils.isEmpty(zzgkVar.zzf)) {
            if (zzgkVar.zzv().y(zzl)) {
                zzgkVar.zzay().zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzgkVar.zzay().zzi().zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(String.valueOf(zzl)));
            }
        }
        zzgkVar.zzay().zzc().zza("Debug-level message logging enabled");
        if (zzgkVar.zzG != zzgkVar.zzH.get()) {
            zzgkVar.zzay().zzd().zzc("Not all components initialized", Integer.valueOf(zzgkVar.zzG), Integer.valueOf(zzgkVar.zzH.get()));
        }
        zzgkVar.zzB = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void h() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static final void zzP(zzhd zzhdVar) {
        if (zzhdVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzQ(zzf zzfVar) {
        if (zzfVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzfVar.b()) {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(zzfVar.getClass())));
        }
    }

    private static final void zzR(zzhe zzheVar) {
        if (zzheVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzheVar.d()) {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(zzheVar.getClass())));
        }
    }

    public static zzgk zzp(Context context, com.google.android.gms.internal.measurement.zzcl zzclVar, Long l2) {
        Bundle bundle;
        if (zzclVar != null && (zzclVar.zze == null || zzclVar.zzf == null)) {
            zzclVar = new com.google.android.gms.internal.measurement.zzcl(zzclVar.zza, zzclVar.zzb, zzclVar.zzc, zzclVar.zzd, null, null, zzclVar.zzg, null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzd == null) {
            synchronized (zzgk.class) {
                if (zzd == null) {
                    zzd = new zzgk(new zzhn(context, zzclVar, l2));
                }
            }
        } else if (zzclVar != null && (bundle = zzclVar.zzg) != null && bundle.containsKey("dataCollectionDefaultEnabled")) {
            Preconditions.checkNotNull(zzd);
            zzd.zzE = Boolean.valueOf(zzclVar.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzd);
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        this.zzH.incrementAndGet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void c(String str, int i2, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> queryIntentActivities;
        if (i2 != 200 && i2 != 204) {
            if (i2 == 304) {
                i2 = 304;
            }
            zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th);
        }
        if (th == null) {
            zzm().zzm.zza(true);
            if (bArr == null || bArr.length == 0) {
                zzay().zzc().zza("Deferred Deep Link response empty.");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                String optString = jSONObject.optString("deeplink", "");
                String optString2 = jSONObject.optString("gclid", "");
                double optDouble = jSONObject.optDouble("timestamp", 0.0d);
                if (TextUtils.isEmpty(optString)) {
                    zzay().zzc().zza("Deferred Deep Link is empty.");
                    return;
                }
                zzlt zzv = zzv();
                zzgk zzgkVar = zzv.f6809a;
                if (!TextUtils.isEmpty(optString) && (queryIntentActivities = zzv.f6809a.zze.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0)) != null && !queryIntentActivities.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("gclid", optString2);
                    bundle.putString("_cis", "ddp");
                    this.zzt.f(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
                    zzlt zzv2 = zzv();
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    try {
                        SharedPreferences.Editor edit = zzv2.f6809a.zze.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                        edit.putString("deeplink", optString);
                        edit.putLong("timestamp", Double.doubleToRawLongBits(optDouble));
                        if (edit.commit()) {
                            zzv2.f6809a.zze.sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
                            return;
                        }
                        return;
                    } catch (RuntimeException e2) {
                        zzv2.f6809a.zzay().zzd().zzb("Failed to persist Deferred Deep Link. exception", e2);
                        return;
                    }
                }
                zzay().zzk().zzc("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                return;
            } catch (JSONException e3) {
                zzay().zzd().zzb("Failed to parse the Deferred Deep Link response. exception", e3);
                return;
            }
        }
        zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d() {
        this.zzG++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void e(boolean z) {
        this.zzE = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void f(com.google.android.gms.internal.measurement.zzcl zzclVar) {
        zzai zzaiVar;
        zzaz().zzg();
        zzai g2 = zzm().g();
        zzfp zzm = zzm();
        zzgk zzgkVar = zzm.f6809a;
        zzm.zzg();
        int i2 = 100;
        int i3 = zzm.e().getInt("consent_source", 100);
        zzag zzagVar = this.zzk;
        zzgk zzgkVar2 = zzagVar.f6809a;
        Boolean c2 = zzagVar.c("google_analytics_default_allow_ad_storage");
        zzag zzagVar2 = this.zzk;
        zzgk zzgkVar3 = zzagVar2.f6809a;
        Boolean c3 = zzagVar2.c("google_analytics_default_allow_analytics_storage");
        if (!(c2 == null && c3 == null) && zzm().m(-10)) {
            zzaiVar = new zzai(c2, c3);
            i2 = -10;
        } else {
            if (!TextUtils.isEmpty(zzh().zzm()) && (i3 == 0 || i3 == 30 || i3 == 10 || i3 == 30 || i3 == 30 || i3 == 40)) {
                zzq().zzT(zzai.zza, -10, this.f6760c);
            } else if (TextUtils.isEmpty(zzh().zzm()) && zzclVar != null && zzclVar.zzg != null && zzm().m(30)) {
                zzaiVar = zzai.zza(zzclVar.zzg);
                if (!zzaiVar.equals(zzai.zza)) {
                    i2 = 30;
                }
            }
            zzaiVar = null;
        }
        if (zzaiVar != null) {
            zzq().zzT(zzaiVar, i2, this.f6760c);
            g2 = zzaiVar;
        }
        zzq().m(g2);
        if (zzm().zzc.zza() == 0) {
            zzay().zzj().zzb("Persisting first open", Long.valueOf(this.f6760c));
            zzm().zzc.zzb(this.f6760c);
        }
        zzq().f6893c.c();
        if (g()) {
            if (!TextUtils.isEmpty(zzh().zzm()) || !TextUtils.isEmpty(zzh().g())) {
                zzlt zzv = zzv();
                String zzm2 = zzh().zzm();
                zzfp zzm3 = zzm();
                zzm3.zzg();
                String string = zzm3.e().getString("gmp_app_id", null);
                String g3 = zzh().g();
                zzfp zzm4 = zzm();
                zzm4.zzg();
                if (zzv.F(zzm2, string, g3, zzm4.e().getString("admob_app_id", null))) {
                    zzay().zzi().zza("Rechecking which service to use due to a GMP App Id change");
                    zzfp zzm5 = zzm();
                    zzm5.zzg();
                    Boolean h2 = zzm5.h();
                    SharedPreferences.Editor edit = zzm5.e().edit();
                    edit.clear();
                    edit.apply();
                    if (h2 != null) {
                        zzm5.i(h2);
                    }
                    zzi().zzj();
                    this.zzy.zzs();
                    this.zzy.v();
                    zzm().zzc.zzb(this.f6760c);
                    zzm().zze.zzb(null);
                }
                zzfp zzm6 = zzm();
                String zzm7 = zzh().zzm();
                zzm6.zzg();
                SharedPreferences.Editor edit2 = zzm6.e().edit();
                edit2.putString("gmp_app_id", zzm7);
                edit2.apply();
                zzfp zzm8 = zzm();
                String g4 = zzh().g();
                zzm8.zzg();
                SharedPreferences.Editor edit3 = zzm8.e().edit();
                edit3.putString("admob_app_id", g4);
                edit3.apply();
            }
            if (!zzm().g().zzi(zzah.ANALYTICS_STORAGE)) {
                zzm().zze.zzb(null);
            }
            zzq().l(zzm().zze.zza());
            zzoc.zzc();
            if (this.zzk.zzs(null, zzen.zzad)) {
                try {
                    zzv().f6809a.zze.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                } catch (ClassNotFoundException unused) {
                    if (!TextUtils.isEmpty(zzm().zzo.zza())) {
                        zzay().zzk().zza("Remote config removed with active feature rollouts");
                        zzm().zzo.zzb(null);
                    }
                }
            }
            if (!TextUtils.isEmpty(zzh().zzm()) || !TextUtils.isEmpty(zzh().g())) {
                boolean zzJ = zzJ();
                if (!zzm().k() && !this.zzk.zzv()) {
                    zzm().j(!zzJ);
                }
                if (zzJ) {
                    zzq().zzz();
                }
                zzu().f7014b.a();
                zzt().zzu(new AtomicReference());
                zzt().zzH(zzm().zzr.zza());
            }
        } else if (zzJ()) {
            if (!zzv().x("android.permission.INTERNET")) {
                zzay().zzd().zza("App is missing INTERNET permission");
            }
            if (!zzv().x("android.permission.ACCESS_NETWORK_STATE")) {
                zzay().zzd().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!Wrappers.packageManager(this.zze).isCallerInstantApp() && !this.zzk.g()) {
                if (!zzlt.D(this.zze)) {
                    zzay().zzd().zza("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzlt.E(this.zze, false)) {
                    zzay().zzd().zza("AppMeasurementService not registered/enabled");
                }
            }
            zzay().zzd().zza("Uploading is not possible. App measurement disabled");
        }
        zzm().zzi.zza(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final boolean g() {
        if (this.zzB) {
            zzaz().zzg();
            Boolean bool = this.zzC;
            if (bool == null || this.zzD == 0 || (!bool.booleanValue() && Math.abs(this.zzr.elapsedRealtime() - this.zzD) > 1000)) {
                this.zzD = this.zzr.elapsedRealtime();
                boolean z = true;
                Boolean valueOf = Boolean.valueOf(zzv().x("android.permission.INTERNET") && zzv().x("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zze).isCallerInstantApp() || this.zzk.g() || (zzlt.D(this.zze) && zzlt.E(this.zze, false))));
                this.zzC = valueOf;
                if (valueOf.booleanValue()) {
                    if (!zzv().q(zzh().zzm(), zzh().g()) && TextUtils.isEmpty(zzh().g())) {
                        z = false;
                    }
                    this.zzC = Boolean.valueOf(z);
                }
            }
            return this.zzC.booleanValue();
        }
        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SideEffectFree
    public final zzgh i() {
        return this.zzn;
    }

    @WorkerThread
    public final void zzE() {
        zzaz().zzg();
        zzR(zzr());
        String zzl = zzh().zzl();
        Pair f2 = zzm().f(zzl);
        if (!this.zzk.zzr() || ((Boolean) f2.second).booleanValue() || TextUtils.isEmpty((CharSequence) f2.first)) {
            zzay().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return;
        }
        zzit zzr = zzr();
        zzr.c();
        ConnectivityManager connectivityManager = (ConnectivityManager) zzr.f6809a.zze.getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException unused) {
            }
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            zzay().zzk().zza("Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        zzlt zzv = zzv();
        zzh().f6809a.zzk.zzh();
        URL zzE = zzv.zzE(64000L, zzl, (String) f2.first, zzm().zzn.zza() - 1);
        if (zzE != null) {
            zzit zzr2 = zzr();
            zzgi zzgiVar = new zzgi(this);
            zzr2.zzg();
            zzr2.c();
            Preconditions.checkNotNull(zzE);
            Preconditions.checkNotNull(zzgiVar);
            zzr2.f6809a.zzaz().zzo(new zzis(zzr2, zzl, zzE, null, null, zzgiVar, null));
        }
    }

    @WorkerThread
    public final void zzG(boolean z) {
        zzaz().zzg();
        this.zzF = z;
    }

    @WorkerThread
    public final boolean zzI() {
        return this.zzE != null && this.zzE.booleanValue();
    }

    @WorkerThread
    public final boolean zzJ() {
        return zza() == 0;
    }

    @WorkerThread
    public final boolean zzK() {
        zzaz().zzg();
        return this.zzF;
    }

    @Pure
    public final boolean zzL() {
        return TextUtils.isEmpty(this.zzf);
    }

    @Pure
    public final boolean zzN() {
        return this.zzi;
    }

    @WorkerThread
    public final int zza() {
        zzaz().zzg();
        if (this.zzk.zzv()) {
            return 1;
        }
        Boolean bool = this.f6759b;
        if (bool == null || !bool.booleanValue()) {
            zzaz().zzg();
            if (this.zzF) {
                Boolean h2 = zzm().h();
                if (h2 != null) {
                    return h2.booleanValue() ? 0 : 3;
                }
                zzag zzagVar = this.zzk;
                zzab zzabVar = zzagVar.f6809a.zzj;
                Boolean c2 = zzagVar.c("firebase_analytics_collection_enabled");
                if (c2 != null) {
                    return c2.booleanValue() ? 0 : 4;
                }
                Boolean bool2 = this.f6758a;
                return bool2 != null ? bool2.booleanValue() ? 0 : 5 : (this.zzE == null || this.zzE.booleanValue()) ? 0 : 7;
            }
            return 8;
        }
        return 2;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final Context zzau() {
        return this.zze;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final Clock zzav() {
        return this.zzr;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final zzab zzaw() {
        return this.zzj;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final zzfa zzay() {
        zzR(this.zzm);
        return this.zzm;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final zzgh zzaz() {
        zzR(this.zzn);
        return this.zzn;
    }

    @Pure
    public final zzd zzd() {
        zzd zzdVar = this.zzu;
        if (zzdVar != null) {
            return zzdVar;
        }
        throw new IllegalStateException("Component not created");
    }

    @Pure
    public final zzag zzf() {
        return this.zzk;
    }

    @Pure
    public final zzaq zzg() {
        zzR(this.zzz);
        return this.zzz;
    }

    @Pure
    public final zzer zzh() {
        zzQ(this.zzA);
        return this.zzA;
    }

    @Pure
    public final zzet zzi() {
        zzQ(this.zzx);
        return this.zzx;
    }

    @Pure
    public final zzev zzj() {
        return this.zzq;
    }

    public final zzfa zzl() {
        zzfa zzfaVar = this.zzm;
        if (zzfaVar == null || !zzfaVar.d()) {
            return null;
        }
        return zzfaVar;
    }

    @Pure
    public final zzfp zzm() {
        zzP(this.zzl);
        return this.zzl;
    }

    @Pure
    public final zzip zzq() {
        zzQ(this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzit zzr() {
        zzR(this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzje zzs() {
        zzQ(this.zzs);
        return this.zzs;
    }

    @Pure
    public final zzke zzt() {
        zzQ(this.zzy);
        return this.zzy;
    }

    @Pure
    public final zzku zzu() {
        zzQ(this.zzo);
        return this.zzo;
    }

    @Pure
    public final zzlt zzv() {
        zzP(this.zzp);
        return this.zzp;
    }

    @Pure
    public final String zzw() {
        return this.zzf;
    }

    @Pure
    public final String zzx() {
        return this.zzg;
    }

    @Pure
    public final String zzy() {
        return this.zzh;
    }

    @Pure
    public final String zzz() {
        return this.zzw;
    }
}
