package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.WorkerThread;
import androidx.car.app.CarContext;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzoc;
import com.google.android.gms.internal.measurement.zzof;
import com.google.android.gms.internal.measurement.zzoo;
import com.google.android.gms.internal.measurement.zzpp;
import com.google.android.gms.internal.measurement.zzps;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes2.dex */
public final class zzip extends zzf {
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    protected zzio f6892b;

    /* renamed from: c  reason: collision with root package name */
    final zzs f6893c;
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    protected boolean f6894d;
    private zzhk zzd;
    private final Set zze;
    private boolean zzf;
    private final AtomicReference zzg;
    private final Object zzh;
    @GuardedBy("consentLock")
    private zzai zzi;
    @GuardedBy("consentLock")
    private int zzj;
    private final AtomicLong zzk;
    private long zzl;
    private int zzm;
    private final zzls zzn;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzip(zzgk zzgkVar) {
        super(zzgkVar);
        this.zze = new CopyOnWriteArraySet();
        this.zzh = new Object();
        this.f6894d = true;
        this.zzn = new zzid(this);
        this.zzg = new AtomicReference();
        this.zzi = new zzai(null, null);
        this.zzj = 100;
        this.zzl = -1L;
        this.zzm = 100;
        this.zzk = new AtomicLong(0L);
        this.f6893c = new zzs(zzgkVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void o(zzip zzipVar, zzai zzaiVar, zzai zzaiVar2) {
        boolean z;
        zzah[] zzahVarArr = {zzah.ANALYTICS_STORAGE, zzah.AD_STORAGE};
        int i2 = 0;
        while (true) {
            if (i2 >= 2) {
                z = false;
                break;
            }
            zzah zzahVar = zzahVarArr[i2];
            if (!zzaiVar2.zzi(zzahVar) && zzaiVar.zzi(zzahVar)) {
                z = true;
                break;
            }
            i2++;
        }
        boolean zzl = zzaiVar.zzl(zzaiVar2, zzah.ANALYTICS_STORAGE, zzah.AD_STORAGE);
        if (z || zzl) {
            zzipVar.f6809a.zzh().i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void p(zzip zzipVar, zzai zzaiVar, int i2, long j2, boolean z, boolean z2) {
        zzipVar.zzg();
        zzipVar.zza();
        if (j2 <= zzipVar.zzl && zzai.zzj(zzipVar.zzm, i2)) {
            zzipVar.f6809a.zzay().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzaiVar);
            return;
        }
        zzfp zzm = zzipVar.f6809a.zzm();
        zzgk zzgkVar = zzm.f6809a;
        zzm.zzg();
        if (!zzm.m(i2)) {
            zzipVar.f6809a.zzay().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(i2));
            return;
        }
        SharedPreferences.Editor edit = zzm.e().edit();
        edit.putString("consent_settings", zzaiVar.zzh());
        edit.putInt("consent_source", i2);
        edit.apply();
        zzipVar.zzl = j2;
        zzipVar.zzm = i2;
        zzipVar.f6809a.zzt().g(z);
        if (z2) {
            zzipVar.f6809a.zzt().zzu(new AtomicReference());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    /* renamed from: zzac */
    public final void d(Bundle bundle, long j2) {
        if (TextUtils.isEmpty(this.f6809a.zzh().zzm())) {
            zzS(bundle, 0, j2);
        } else {
            this.f6809a.zzay().zzl().zza("Using developer consent only; google app id found");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzad(Boolean bool, boolean z) {
        zzg();
        zza();
        this.f6809a.zzay().zzc().zzb("Setting app measurement enabled (FE)", bool);
        this.f6809a.zzm().i(bool);
        if (z) {
            zzfp zzm = this.f6809a.zzm();
            zzgk zzgkVar = zzm.f6809a;
            zzm.zzg();
            SharedPreferences.Editor edit = zzm.e().edit();
            if (bool != null) {
                edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                edit.remove("measurement_enabled_from_api");
            }
            edit.apply();
        }
        if (this.f6809a.zzK() || !(bool == null || bool.booleanValue())) {
            zzae();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzae() {
        Long valueOf;
        zzg();
        String zza = this.f6809a.zzm().zzh.zza();
        if (zza != null) {
            if ("unset".equals(zza)) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(true != "true".equals(zza) ? 0L : 1L);
            }
            n(CarContext.APP_SERVICE, "_npa", valueOf, this.f6809a.zzav().currentTimeMillis());
        }
        if (!this.f6809a.zzJ() || !this.f6894d) {
            this.f6809a.zzay().zzc().zza("Updating Scion state (FE)");
            this.f6809a.zzt().i();
            return;
        }
        this.f6809a.zzay().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzz();
        zzoo.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzae)) {
            this.f6809a.zzu().f7014b.a();
        }
        this.f6809a.zzaz().zzp(new zzhs(this));
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean c() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void e(Bundle bundle) {
        if (bundle == null) {
            this.f6809a.zzm().zzr.zzb(new Bundle());
            return;
        }
        Bundle zza = this.f6809a.zzm().zzr.zza();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                if (this.f6809a.zzv().z(obj)) {
                    this.f6809a.zzv().n(this.zzn, null, 27, null, null, 0);
                }
                this.f6809a.zzay().zzl().zzc("Invalid default event parameter type. Name, value", str, obj);
            } else if (zzlt.B(str)) {
                this.f6809a.zzay().zzl().zzb("Invalid default event parameter name. Name", str);
            } else if (obj == null) {
                zza.remove(str);
            } else {
                zzlt zzv = this.f6809a.zzv();
                this.f6809a.zzf();
                if (zzv.u("param", str, 100, obj)) {
                    this.f6809a.zzv().o(zza, str, obj);
                }
            }
        }
        this.f6809a.zzv();
        int zzc = this.f6809a.zzf().zzc();
        if (zza.size() > zzc) {
            int i2 = 0;
            for (String str2 : new TreeSet(zza.keySet())) {
                i2++;
                if (i2 > zzc) {
                    zza.remove(str2);
                }
            }
            this.f6809a.zzv().n(this.zzn, null, 26, null, null, 0);
            this.f6809a.zzay().zzl().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        this.f6809a.zzm().zzr.zzb(zza);
        this.f6809a.zzt().zzH(zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void f(String str, String str2, Bundle bundle) {
        zzg();
        g(str, str2, this.f6809a.zzav().currentTimeMillis(), bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void g(String str, String str2, long j2, Bundle bundle) {
        zzg();
        h(str, str2, j2, bundle, true, this.zzd == null || zzlt.B(str2), true, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01a5, code lost:
        if (r27 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01c3, code lost:
        if (r27 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01c5, code lost:
        r3 = true;
     */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void h(String str, String str2, long j2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zziw zzj;
        boolean z4;
        boolean z5;
        String str4;
        long j3;
        String str5;
        String str6;
        Bundle[] bundleArr;
        Object[] objArr;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzg();
        zza();
        if (!this.f6809a.zzJ()) {
            this.f6809a.zzay().zzc().zza("Event not sent since app measurement is disabled");
            return;
        }
        List h2 = this.f6809a.zzh().h();
        if (h2 != null && !h2.contains(str2)) {
            this.f6809a.zzay().zzc().zzc("Dropping non-safelisted event. event name, origin", str2, str);
            return;
        }
        if (!this.zzf) {
            this.zzf = true;
            try {
                try {
                    (!this.f6809a.zzN() ? Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, this.f6809a.zzau().getClassLoader()) : Class.forName("com.google.android.gms.tagmanager.TagManagerService")).getDeclaredMethod("initialize", Context.class).invoke(null, this.f6809a.zzau());
                } catch (Exception e2) {
                    this.f6809a.zzay().zzk().zzb("Failed to invoke Tag Manager's initialize() method", e2);
                }
            } catch (ClassNotFoundException unused) {
                this.f6809a.zzay().zzi().zza("Tag Manager is not found and thus will not be used");
            }
        }
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(str2) && bundle.containsKey("gclid")) {
            this.f6809a.zzaw();
            n(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lgclid", bundle.getString("gclid"), this.f6809a.zzav().currentTimeMillis());
        }
        this.f6809a.zzaw();
        if (z && zzlt.zzal(str2)) {
            this.f6809a.zzv().l(bundle, this.f6809a.zzm().zzr.zza());
        }
        if (!z3) {
            this.f6809a.zzaw();
            if (!"_iap".equals(str2)) {
                zzlt zzv = this.f6809a.zzv();
                int i2 = 2;
                if (zzv.w(NotificationCompat.CATEGORY_EVENT, str2)) {
                    if (zzv.s(NotificationCompat.CATEGORY_EVENT, zzhh.zza, zzhh.zzb, str2)) {
                        zzv.f6809a.zzf();
                        if (zzv.r(NotificationCompat.CATEGORY_EVENT, 40, str2)) {
                            i2 = 0;
                        }
                    } else {
                        i2 = 13;
                    }
                }
                if (i2 != 0) {
                    this.f6809a.zzay().zze().zzb("Invalid public event name. Event will not be logged (FE)", this.f6809a.zzj().d(str2));
                    zzlt zzv2 = this.f6809a.zzv();
                    this.f6809a.zzf();
                    this.f6809a.zzv().n(this.zzn, null, i2, "_ev", zzv2.zzD(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
            }
        }
        zzpp.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzat)) {
            this.f6809a.zzaw();
            zzj = this.f6809a.zzs().zzj(false);
            if (zzj != null && !bundle.containsKey("_sc")) {
                zzj.f6896a = true;
            }
            if (z) {
            }
            z4 = false;
        } else {
            this.f6809a.zzaw();
            zzj = this.f6809a.zzs().zzj(false);
            if (zzj != null && !bundle.containsKey("_sc")) {
                zzj.f6896a = true;
            }
            if (z) {
            }
            z4 = false;
        }
        zzlt.zzK(zzj, bundle, z4);
        boolean equals = "am".equals(str);
        boolean B = zzlt.B(str2);
        if (!z || this.zzd == null || B) {
            z5 = equals;
        } else if (!equals) {
            this.f6809a.zzay().zzc().zzc("Passing event to registered event handler (FE)", this.f6809a.zzj().d(str2), this.f6809a.zzj().b(bundle));
            Preconditions.checkNotNull(this.zzd);
            this.zzd.interceptEvent(str, str2, bundle, j2);
            return;
        } else {
            z5 = true;
        }
        if (this.f6809a.g()) {
            int zzh = this.f6809a.zzv().zzh(str2);
            if (zzh != 0) {
                this.f6809a.zzay().zze().zzb("Invalid event name. Event will not be logged (FE)", this.f6809a.zzj().d(str2));
                zzlt zzv3 = this.f6809a.zzv();
                this.f6809a.zzf();
                this.f6809a.zzv().n(this.zzn, str3, zzh, "_ev", zzv3.zzD(str2, 40, true), str2 != null ? str2.length() : 0);
                return;
            }
            Bundle Q = this.f6809a.zzv().Q(str3, str2, bundle, CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"}), z3);
            Preconditions.checkNotNull(Q);
            this.f6809a.zzaw();
            if (this.f6809a.zzs().zzj(false) != null && "_ae".equals(str2)) {
                zzks zzksVar = this.f6809a.zzu().f7015c;
                long elapsedRealtime = zzksVar.f7012c.f6809a.zzav().elapsedRealtime();
                long j4 = elapsedRealtime - zzksVar.f7011b;
                zzksVar.f7011b = elapsedRealtime;
                if (j4 > 0) {
                    this.f6809a.zzv().j(Q, j4);
                }
            }
            zzoc.zzc();
            if (this.f6809a.zzf().zzs(null, zzen.zzad)) {
                if (!DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(str) && "_ssr".equals(str2)) {
                    zzlt zzv4 = this.f6809a.zzv();
                    String string = Q.getString("_ffr");
                    if (Strings.isEmptyOrWhitespace(string)) {
                        string = null;
                    } else if (string != null) {
                        string = string.trim();
                    }
                    if (zzlr.zza(string, zzv4.f6809a.zzm().zzo.zza())) {
                        zzv4.f6809a.zzay().zzc().zza("Not logging duplicate session_start_with_rollout event");
                        return;
                    }
                    zzv4.f6809a.zzm().zzo.zzb(string);
                } else if ("_ae".equals(str2)) {
                    String zza = this.f6809a.zzv().f6809a.zzm().zzo.zza();
                    if (!TextUtils.isEmpty(zza)) {
                        Q.putString("_ffr", zza);
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(Q);
            if (this.f6809a.zzm().zzj.zza() > 0 && this.f6809a.zzm().l(j2) && this.f6809a.zzm().zzl.zzb()) {
                this.f6809a.zzay().zzj().zza("Current session is expired, remove the session number, ID, and engagement time");
                str4 = "_ae";
                j3 = 0;
                n(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_sid", null, this.f6809a.zzav().currentTimeMillis());
                n(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_sno", null, this.f6809a.zzav().currentTimeMillis());
                n(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_se", null, this.f6809a.zzav().currentTimeMillis());
            } else {
                str4 = "_ae";
                j3 = 0;
            }
            if (Q.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, j3) == 1) {
                this.f6809a.zzay().zzj().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                this.f6809a.zzu().f7014b.b(j2, true);
            }
            ArrayList arrayList2 = new ArrayList(Q.keySet());
            Collections.sort(arrayList2);
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                String str7 = (String) arrayList2.get(i3);
                if (str7 != null) {
                    this.f6809a.zzv();
                    Object obj = Q.get(str7);
                    if (obj instanceof Bundle) {
                        bundleArr = new Bundle[]{(Bundle) obj};
                    } else {
                        if (obj instanceof Parcelable[]) {
                            Parcelable[] parcelableArr = (Parcelable[]) obj;
                            objArr = Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
                        } else if (obj instanceof ArrayList) {
                            ArrayList arrayList3 = (ArrayList) obj;
                            objArr = arrayList3.toArray(new Bundle[arrayList3.size()]);
                        } else {
                            bundleArr = null;
                        }
                        bundleArr = (Bundle[]) objArr;
                    }
                    if (bundleArr != null) {
                        Q.putParcelableArray(str7, bundleArr);
                    }
                }
            }
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                Bundle bundle2 = (Bundle) arrayList.get(i4);
                if (i4 != 0) {
                    str6 = "_ep";
                    str5 = str;
                } else {
                    str5 = str;
                    str6 = str2;
                }
                bundle2.putString("_o", str5);
                if (z2) {
                    bundle2 = this.f6809a.zzv().P(bundle2);
                }
                Bundle bundle3 = bundle2;
                this.f6809a.zzt().d(new zzaw(str6, new zzau(bundle3), str, j2), str3);
                if (!z5) {
                    for (zzhl zzhlVar : this.zze) {
                        zzhlVar.onEvent(str, str2, new Bundle(bundle3), j2);
                    }
                }
            }
            this.f6809a.zzaw();
            if (this.f6809a.zzs().zzj(false) == null || !str4.equals(str2)) {
                return;
            }
            this.f6809a.zzu().f7015c.zzd(true, true, this.f6809a.zzav().elapsedRealtime());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i(long j2, boolean z) {
        zzg();
        zza();
        this.f6809a.zzay().zzc().zza("Resetting analytics data (FE)");
        zzku zzu = this.f6809a.zzu();
        zzu.zzg();
        zzu.f7015c.a();
        zzps.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzaI)) {
            this.f6809a.zzh().i();
        }
        boolean zzJ = this.f6809a.zzJ();
        zzfp zzm = this.f6809a.zzm();
        zzm.zzc.zzb(j2);
        if (!TextUtils.isEmpty(zzm.f6809a.zzm().zzo.zza())) {
            zzm.zzo.zzb(null);
        }
        zzoo.zzc();
        zzag zzf = zzm.f6809a.zzf();
        zzem zzemVar = zzen.zzae;
        if (zzf.zzs(null, zzemVar)) {
            zzm.zzj.zzb(0L);
        }
        if (!zzm.f6809a.zzf().zzv()) {
            zzm.j(!zzJ);
        }
        zzm.zzp.zzb(null);
        zzm.zzq.zzb(0L);
        zzm.zzr.zzb(null);
        if (z) {
            this.f6809a.zzt().zzC();
        }
        zzoo.zzc();
        if (this.f6809a.zzf().zzs(null, zzemVar)) {
            this.f6809a.zzu().f7014b.a();
        }
        this.f6894d = !zzJ;
    }

    protected final void j(String str, String str2, long j2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2 = new Bundle(bundle);
        for (String str4 : bundle2.keySet()) {
            Object obj = bundle2.get(str4);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str4, new Bundle((Bundle) obj));
            } else {
                int i2 = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i2 < parcelableArr.length) {
                        Parcelable parcelable = parcelableArr[i2];
                        if (parcelable instanceof Bundle) {
                            parcelableArr[i2] = new Bundle((Bundle) parcelable);
                        }
                        i2++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i2 < list.size()) {
                        Object obj2 = list.get(i2);
                        if (obj2 instanceof Bundle) {
                            list.set(i2, new Bundle((Bundle) obj2));
                        }
                        i2++;
                    }
                }
            }
        }
        this.f6809a.zzaz().zzp(new zzhu(this, str, str2, j2, bundle2, z, z2, z3, str3));
    }

    final void k(String str, String str2, long j2, Object obj) {
        this.f6809a.zzaz().zzp(new zzhv(this, str, str2, obj, j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l(String str) {
        this.zzg.set(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void m(zzai zzaiVar) {
        zzg();
        boolean z = (zzaiVar.zzi(zzah.ANALYTICS_STORAGE) && zzaiVar.zzi(zzah.AD_STORAGE)) || this.f6809a.zzt().l();
        if (z != this.f6809a.zzK()) {
            this.f6809a.zzG(z);
            zzfp zzm = this.f6809a.zzm();
            zzgk zzgkVar = zzm.f6809a;
            zzm.zzg();
            Boolean valueOf = zzm.e().contains("measurement_enabled_from_api") ? Boolean.valueOf(zzm.e().getBoolean("measurement_enabled_from_api", true)) : null;
            if (!z || valueOf == null || valueOf.booleanValue()) {
                zzad(Boolean.valueOf(z), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007b  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void n(String str, String str2, Object obj, long j2) {
        String str3;
        Object obj2;
        zzfo zzfoVar;
        String str4;
        Long l2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zza();
        if (FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            if (obj instanceof String) {
                String str5 = (String) obj;
                if (!TextUtils.isEmpty(str5)) {
                    String lowerCase = str5.toLowerCase(Locale.ENGLISH);
                    str4 = "false";
                    Long valueOf = Long.valueOf(true != "false".equals(lowerCase) ? 0L : 1L);
                    zzfoVar = this.f6809a.zzm().zzh;
                    int i2 = (valueOf.longValue() > 1L ? 1 : (valueOf.longValue() == 1L ? 0 : -1));
                    l2 = valueOf;
                    if (i2 == 0) {
                        str4 = "true";
                        l2 = valueOf;
                    }
                    zzfoVar.zzb(str4);
                    obj2 = l2;
                    str3 = "_npa";
                    if (!this.f6809a.zzJ()) {
                        this.f6809a.zzay().zzj().zza("User property not set since app measurement is disabled");
                        return;
                    } else if (this.f6809a.g()) {
                        this.f6809a.zzt().k(new zzlo(str3, j2, obj2, str));
                        return;
                    } else {
                        return;
                    }
                }
            }
            if (obj == null) {
                zzfoVar = this.f6809a.zzm().zzh;
                str4 = "unset";
                l2 = obj;
                zzfoVar.zzb(str4);
                obj2 = l2;
                str3 = "_npa";
                if (!this.f6809a.zzJ()) {
                }
            }
        }
        str3 = str2;
        obj2 = obj;
        if (!this.f6809a.zzJ()) {
        }
    }

    public final void zzA(String str, String str2, Bundle bundle) {
        long currentTimeMillis = this.f6809a.zzav().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        this.f6809a.zzaz().zzp(new zzhz(this, bundle2));
    }

    public final void zzB() {
        if (!(this.f6809a.zzau().getApplicationContext() instanceof Application) || this.f6892b == null) {
            return;
        }
        ((Application) this.f6809a.zzau().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.f6892b);
    }

    public final void zzE(String str, String str2, Bundle bundle) {
        zzF(str, str2, bundle, true, true, this.f6809a.zzav().currentTimeMillis());
    }

    public final void zzF(String str, String str2, Bundle bundle, boolean z, boolean z2, long j2) {
        String str3 = str == null ? CarContext.APP_SERVICE : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (str2 == FirebaseAnalytics.Event.SCREEN_VIEW || (str2 != null && str2.equals(FirebaseAnalytics.Event.SCREEN_VIEW))) {
            this.f6809a.zzs().zzx(bundle2, j2);
            return;
        }
        boolean z3 = true;
        if (z2 && this.zzd != null && !zzlt.B(str2)) {
            z3 = false;
        }
        j(str3, str2, j2, bundle2, z2, z3, z, null);
    }

    public final void zzG(String str, String str2, Bundle bundle, String str3) {
        zzgk.h();
        j(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str2, this.f6809a.zzav().currentTimeMillis(), bundle, false, true, true, str3);
    }

    public final void zzK(zzhl zzhlVar) {
        zza();
        Preconditions.checkNotNull(zzhlVar);
        if (this.zze.add(zzhlVar)) {
            return;
        }
        this.f6809a.zzay().zzk().zza("OnEventListener already registered");
    }

    public final void zzL(long j2) {
        this.zzg.set(null);
        this.f6809a.zzaz().zzp(new zzhx(this, j2));
    }

    public final void zzQ(Bundle bundle) {
        zzR(bundle, this.f6809a.zzav().currentTimeMillis());
    }

    public final void zzR(Bundle bundle, long j2) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            this.f6809a.zzay().zzk().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        zzhg.zza(bundle2, "app_id", String.class, null);
        zzhg.zza(bundle2, "origin", String.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzhg.zza(bundle2, "value", Object.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzhg.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j2);
        String string = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle2.get("value");
        if (this.f6809a.zzv().M(string) != 0) {
            this.f6809a.zzay().zzd().zzb("Invalid conditional user property name", this.f6809a.zzj().f(string));
        } else if (this.f6809a.zzv().J(string, obj) != 0) {
            this.f6809a.zzay().zzd().zzc("Invalid conditional user property value", this.f6809a.zzj().f(string), obj);
        } else {
            Object f2 = this.f6809a.zzv().f(string, obj);
            if (f2 == null) {
                this.f6809a.zzay().zzd().zzc("Unable to normalize conditional user property value", this.f6809a.zzj().f(string), obj);
                return;
            }
            zzhg.zzb(bundle2, f2);
            long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (!TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME))) {
                this.f6809a.zzf();
                if (j3 > 15552000000L || j3 < 1) {
                    this.f6809a.zzay().zzd().zzc("Invalid conditional user property timeout", this.f6809a.zzj().f(string), Long.valueOf(j3));
                    return;
                }
            }
            long j4 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
            this.f6809a.zzf();
            if (j4 > 15552000000L || j4 < 1) {
                this.f6809a.zzay().zzd().zzc("Invalid conditional user property time to live", this.f6809a.zzj().f(string), Long.valueOf(j4));
            } else {
                this.f6809a.zzaz().zzp(new zzhy(this, bundle2));
            }
        }
    }

    public final void zzS(Bundle bundle, int i2, long j2) {
        zza();
        String zzg = zzai.zzg(bundle);
        if (zzg != null) {
            this.f6809a.zzay().zzl().zzb("Ignoring invalid consent setting", zzg);
            this.f6809a.zzay().zzl().zza("Valid consent values are 'granted', 'denied'");
        }
        zzT(zzai.zza(bundle), i2, j2);
    }

    public final void zzT(zzai zzaiVar, int i2, long j2) {
        zzai zzaiVar2;
        boolean z;
        boolean z2;
        boolean z3;
        zzai zzaiVar3 = zzaiVar;
        zza();
        if (i2 != -10 && zzaiVar.zze() == null && zzaiVar.zzf() == null) {
            this.f6809a.zzay().zzl().zza("Discarding empty consent settings");
            return;
        }
        synchronized (this.zzh) {
            zzaiVar2 = this.zzi;
            z = true;
            z2 = false;
            if (zzai.zzj(i2, this.zzj)) {
                boolean zzk = zzaiVar3.zzk(this.zzi);
                zzah zzahVar = zzah.ANALYTICS_STORAGE;
                if (zzaiVar3.zzi(zzahVar) && !this.zzi.zzi(zzahVar)) {
                    z2 = true;
                }
                zzaiVar3 = zzaiVar3.zzd(this.zzi);
                this.zzi = zzaiVar3;
                this.zzj = i2;
                z3 = z2;
                z2 = zzk;
            } else {
                z = false;
                z3 = false;
            }
        }
        if (!z) {
            this.f6809a.zzay().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzaiVar3);
            return;
        }
        long andIncrement = this.zzk.getAndIncrement();
        if (z2) {
            this.zzg.set(null);
            this.f6809a.zzaz().zzq(new zzij(this, zzaiVar3, j2, i2, andIncrement, z3, zzaiVar2));
            return;
        }
        zzik zzikVar = new zzik(this, zzaiVar3, i2, andIncrement, z3, zzaiVar2);
        if (i2 == 30 || i2 == -10) {
            this.f6809a.zzaz().zzq(zzikVar);
        } else {
            this.f6809a.zzaz().zzp(zzikVar);
        }
    }

    public final void zzU(final Bundle bundle, final long j2) {
        zzof.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzal)) {
            this.f6809a.zzaz().zzq(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzho
                @Override // java.lang.Runnable
                public final void run() {
                    zzip.this.d(bundle, j2);
                }
            });
        } else {
            d(bundle, j2);
        }
    }

    @WorkerThread
    public final void zzV(zzhk zzhkVar) {
        zzhk zzhkVar2;
        zzg();
        zza();
        if (zzhkVar != null && zzhkVar != (zzhkVar2 = this.zzd)) {
            Preconditions.checkState(zzhkVar2 == null, "EventInterceptor already set.");
        }
        this.zzd = zzhkVar;
    }

    public final void zzW(Boolean bool) {
        zza();
        this.f6809a.zzaz().zzp(new zzii(this, bool));
    }

    public final void zzY(String str, String str2, Object obj, boolean z) {
        zzZ(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ldl", obj, true, this.f6809a.zzav().currentTimeMillis());
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzZ(String str, String str2, Object obj, boolean z, long j2) {
        int i2;
        String str3 = str == null ? CarContext.APP_SERVICE : str;
        int i3 = 6;
        if (z) {
            i3 = this.f6809a.zzv().M(str2);
        } else {
            zzlt zzv = this.f6809a.zzv();
            if (zzv.w("user property", str2)) {
                if (zzv.s("user property", zzhj.zza, null, str2)) {
                    zzv.f6809a.zzf();
                    if (zzv.r("user property", 24, str2)) {
                        i2 = 0;
                        if (i2 == 0) {
                            zzlt zzv2 = this.f6809a.zzv();
                            this.f6809a.zzf();
                            this.f6809a.zzv().n(this.zzn, null, i2, "_ev", zzv2.zzD(str2, 24, true), str2 != null ? str2.length() : 0);
                            return;
                        } else if (obj == null) {
                            k(str3, str2, j2, null);
                            return;
                        } else {
                            int J = this.f6809a.zzv().J(str2, obj);
                            if (J == 0) {
                                Object f2 = this.f6809a.zzv().f(str2, obj);
                                if (f2 != null) {
                                    k(str3, str2, j2, f2);
                                    return;
                                }
                                return;
                            }
                            zzlt zzv3 = this.f6809a.zzv();
                            this.f6809a.zzf();
                            String zzD = zzv3.zzD(str2, 24, true);
                            if ((obj instanceof String) || (obj instanceof CharSequence)) {
                                r4 = obj.toString().length();
                            }
                            this.f6809a.zzv().n(this.zzn, null, J, "_ev", zzD, r4);
                            return;
                        }
                    }
                } else {
                    i3 = 15;
                }
            }
        }
        i2 = i3;
        if (i2 == 0) {
        }
    }

    public final void zzab(zzhl zzhlVar) {
        zza();
        Preconditions.checkNotNull(zzhlVar);
        if (this.zze.remove(zzhlVar)) {
            return;
        }
        this.f6809a.zzay().zzk().zza("OnEventListener had not been registered");
    }

    public final int zzh(String str) {
        Preconditions.checkNotEmpty(str);
        this.f6809a.zzf();
        return 25;
    }

    public final Boolean zzi() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) this.f6809a.zzaz().h(atomicReference, 15000L, "boolean test flag value", new zzib(this, atomicReference));
    }

    public final Double zzj() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) this.f6809a.zzaz().h(atomicReference, 15000L, "double test flag value", new zzih(this, atomicReference));
    }

    public final Integer zzl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) this.f6809a.zzaz().h(atomicReference, 15000L, "int test flag value", new zzig(this, atomicReference));
    }

    public final Long zzm() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) this.f6809a.zzaz().h(atomicReference, 15000L, "long test flag value", new zzif(this, atomicReference));
    }

    public final String zzo() {
        return (String) this.zzg.get();
    }

    public final String zzp() {
        zziw zzi = this.f6809a.zzs().zzi();
        if (zzi != null) {
            return zzi.zzb;
        }
        return null;
    }

    public final String zzq() {
        zziw zzi = this.f6809a.zzs().zzi();
        if (zzi != null) {
            return zzi.zza;
        }
        return null;
    }

    public final String zzr() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) this.f6809a.zzaz().h(atomicReference, 15000L, "String test flag value", new zzie(this, atomicReference));
    }

    public final ArrayList zzs(String str, String str2) {
        if (this.f6809a.zzaz().zzs()) {
            this.f6809a.zzay().zzd().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList(0);
        }
        this.f6809a.zzaw();
        if (zzab.zza()) {
            this.f6809a.zzay().zzd().zza("Cannot get conditional user properties from main thread");
            return new ArrayList(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.f6809a.zzaz().h(atomicReference, 5000L, "get conditional user properties", new zzia(this, atomicReference, null, str, str2));
        List list = (List) atomicReference.get();
        if (list == null) {
            this.f6809a.zzay().zzd().zzb("Timed out waiting for get conditional user properties", null);
            return new ArrayList();
        }
        return zzlt.zzH(list);
    }

    public final List zzt(boolean z) {
        zzey zzd;
        String str;
        zza();
        this.f6809a.zzay().zzj().zza("Getting user properties (FE)");
        if (this.f6809a.zzaz().zzs()) {
            zzd = this.f6809a.zzay().zzd();
            str = "Cannot get all user properties from analytics worker thread";
        } else {
            this.f6809a.zzaw();
            if (!zzab.zza()) {
                AtomicReference atomicReference = new AtomicReference();
                this.f6809a.zzaz().h(atomicReference, 5000L, "get user properties", new zzhw(this, atomicReference, z));
                List list = (List) atomicReference.get();
                if (list == null) {
                    this.f6809a.zzay().zzd().zzb("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
                    return Collections.emptyList();
                }
                return list;
            }
            zzd = this.f6809a.zzay().zzd();
            str = "Cannot get all user properties from main thread";
        }
        zzd.zza(str);
        return Collections.emptyList();
    }

    public final Map zzu(String str, String str2, boolean z) {
        zzey zzd;
        String str3;
        if (this.f6809a.zzaz().zzs()) {
            zzd = this.f6809a.zzay().zzd();
            str3 = "Cannot get user properties from analytics worker thread";
        } else {
            this.f6809a.zzaw();
            if (!zzab.zza()) {
                AtomicReference atomicReference = new AtomicReference();
                this.f6809a.zzaz().h(atomicReference, 5000L, "get user properties", new zzic(this, atomicReference, null, str, str2, z));
                List<zzlo> list = (List) atomicReference.get();
                if (list == null) {
                    this.f6809a.zzay().zzd().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
                    return Collections.emptyMap();
                }
                ArrayMap arrayMap = new ArrayMap(list.size());
                for (zzlo zzloVar : list) {
                    Object zza = zzloVar.zza();
                    if (zza != null) {
                        arrayMap.put(zzloVar.zzb, zza);
                    }
                }
                return arrayMap;
            }
            zzd = this.f6809a.zzay().zzd();
            str3 = "Cannot get user properties from main thread";
        }
        zzd.zza(str3);
        return Collections.emptyMap();
    }

    @WorkerThread
    public final void zzz() {
        zzg();
        zza();
        if (this.f6809a.g()) {
            if (this.f6809a.zzf().zzs(null, zzen.zzY)) {
                zzag zzf = this.f6809a.zzf();
                zzf.f6809a.zzaw();
                Boolean c2 = zzf.c("google_analytics_deferred_deep_link_enabled");
                if (c2 != null && c2.booleanValue()) {
                    this.f6809a.zzay().zzc().zza("Deferred Deep Link feature enabled.");
                    this.f6809a.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhr
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzip zzipVar = zzip.this;
                            zzipVar.zzg();
                            if (zzipVar.f6809a.zzm().zzm.zzb()) {
                                zzipVar.f6809a.zzay().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
                                return;
                            }
                            long zza = zzipVar.f6809a.zzm().zzn.zza();
                            zzipVar.f6809a.zzm().zzn.zzb(1 + zza);
                            zzipVar.f6809a.zzf();
                            if (zza < 5) {
                                zzipVar.f6809a.zzE();
                                return;
                            }
                            zzipVar.f6809a.zzay().zzk().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                            zzipVar.f6809a.zzm().zzm.zza(true);
                        }
                    });
                }
            }
            this.f6809a.zzt().u();
            this.f6894d = false;
            zzfp zzm = this.f6809a.zzm();
            zzm.zzg();
            String string = zzm.e().getString("previous_os_version", null);
            zzm.f6809a.zzg().c();
            String str = Build.VERSION.RELEASE;
            if (!TextUtils.isEmpty(str) && !str.equals(string)) {
                SharedPreferences.Editor edit = zzm.e().edit();
                edit.putString("previous_os_version", str);
                edit.apply();
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            this.f6809a.zzg().c();
            if (string.equals(str)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", string);
            f(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ou", bundle);
        }
    }
}
