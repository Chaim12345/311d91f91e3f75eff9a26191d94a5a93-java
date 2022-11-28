package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzpj;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
public final class zzgb extends zzkz implements zzaf {
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    final Map f6747c;
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    final Map f6748d;
    @VisibleForTesting

    /* renamed from: e  reason: collision with root package name */
    final Map f6749e;
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    final LruCache f6750f;

    /* renamed from: g  reason: collision with root package name */
    final com.google.android.gms.internal.measurement.zzr f6751g;
    private final Map zzg;
    private final Map zzh;
    private final Map zzi;
    private final Map zzj;
    private final Map zzk;
    private final Map zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgb(zzll zzllVar) {
        super(zzllVar);
        this.zzg = new ArrayMap();
        this.f6747c = new ArrayMap();
        this.f6748d = new ArrayMap();
        this.f6749e = new ArrayMap();
        this.zzh = new ArrayMap();
        this.zzj = new ArrayMap();
        this.zzk = new ArrayMap();
        this.zzl = new ArrayMap();
        this.zzi = new ArrayMap();
        this.f6750f = new zzfy(this, 20);
        this.f6751g = new zzfz(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ com.google.android.gms.internal.measurement.zzc e(zzgb zzgbVar, String str) {
        zzgbVar.a();
        Preconditions.checkNotEmpty(str);
        if (zzgbVar.zzo(str)) {
            if (!zzgbVar.zzh.containsKey(str) || zzgbVar.zzh.get(str) == null) {
                zzgbVar.zzC(str);
            } else {
                zzgbVar.zzD(str, (com.google.android.gms.internal.measurement.zzfe) zzgbVar.zzh.get(str));
            }
            return (com.google.android.gms.internal.measurement.zzc) zzgbVar.f6750f.snapshot().get(str);
        }
        return null;
    }

    @WorkerThread
    private final com.google.android.gms.internal.measurement.zzfe zzA(String str, byte[] bArr) {
        if (bArr == null) {
            return com.google.android.gms.internal.measurement.zzfe.zzg();
        }
        try {
            com.google.android.gms.internal.measurement.zzfe zzfeVar = (com.google.android.gms.internal.measurement.zzfe) ((com.google.android.gms.internal.measurement.zzfd) zzln.m(com.google.android.gms.internal.measurement.zzfe.zze(), bArr)).zzaE();
            this.f6809a.zzay().zzj().zzc("Parsed config. version, gmp_app_id", zzfeVar.zzs() ? Long.valueOf(zzfeVar.zzc()) : null, zzfeVar.zzr() ? zzfeVar.zzh() : null);
            return zzfeVar;
        } catch (com.google.android.gms.internal.measurement.zzkm | RuntimeException e2) {
            this.f6809a.zzay().zzk().zzc("Unable to merge remote config. appId", zzfa.g(str), e2);
            return com.google.android.gms.internal.measurement.zzfe.zzg();
        }
    }

    private final void zzB(String str, com.google.android.gms.internal.measurement.zzfd zzfdVar) {
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zzfdVar != null) {
            zzpj.zzc();
            if (this.f6809a.zzf().zzs(null, zzen.zzaB)) {
                for (com.google.android.gms.internal.measurement.zzfa zzfaVar : zzfdVar.zzg()) {
                    hashSet.add(zzfaVar.zzb());
                }
            }
            for (int i2 = 0; i2 < zzfdVar.zza(); i2++) {
                com.google.android.gms.internal.measurement.zzfb zzfbVar = (com.google.android.gms.internal.measurement.zzfb) zzfdVar.zzb(i2).zzbB();
                if (zzfbVar.zzc().isEmpty()) {
                    this.f6809a.zzay().zzk().zza("EventConfig contained null event name");
                } else {
                    String zzc = zzfbVar.zzc();
                    String zzb = zzhh.zzb(zzfbVar.zzc());
                    if (!TextUtils.isEmpty(zzb)) {
                        zzfbVar.zzb(zzb);
                        zzfdVar.zzd(i2, zzfbVar);
                    }
                    if (zzfbVar.zzf() && zzfbVar.zzd()) {
                        arrayMap.put(zzc, Boolean.TRUE);
                    }
                    if (zzfbVar.zzg() && zzfbVar.zze()) {
                        arrayMap2.put(zzfbVar.zzc(), Boolean.TRUE);
                    }
                    if (zzfbVar.zzh()) {
                        if (zzfbVar.zza() < 2 || zzfbVar.zza() > 65535) {
                            this.f6809a.zzay().zzk().zzc("Invalid sampling rate. Event name, sample rate", zzfbVar.zzc(), Integer.valueOf(zzfbVar.zza()));
                        } else {
                            arrayMap3.put(zzfbVar.zzc(), Integer.valueOf(zzfbVar.zza()));
                        }
                    }
                }
            }
        }
        this.f6747c.put(str, hashSet);
        this.f6748d.put(str, arrayMap);
        this.f6749e.put(str, arrayMap2);
        this.zzi.put(str, arrayMap3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a4, code lost:
        if (r2 == null) goto L11;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0122: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:36:0x0122 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0125  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzC(String str) {
        SQLiteException e2;
        Cursor cursor;
        Cursor cursor2;
        zzaj zzajVar;
        Map map;
        a();
        zzg();
        Preconditions.checkNotEmpty(str);
        if (this.zzh.get(str) == null) {
            zzam zzi = this.f7018b.zzi();
            Preconditions.checkNotEmpty(str);
            zzi.zzg();
            zzi.a();
            Cursor cursor3 = null;
            String str2 = null;
            try {
                try {
                    cursor = zzi.p().query("apps", new String[]{"remote_config", "config_last_modified_time", "e_tag"}, "app_id=?", new String[]{str}, null, null, null);
                    try {
                    } catch (SQLiteException e3) {
                        e2 = e3;
                        zzi.f6809a.zzay().zzd().zzc("Error querying remote config. appId", zzfa.g(str), e2);
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor3 = cursor2;
                    if (cursor3 != null) {
                        cursor3.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e4) {
                e2 = e4;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                if (cursor3 != null) {
                }
                throw th;
            }
            if (cursor.moveToFirst()) {
                byte[] blob = cursor.getBlob(0);
                String string = cursor.getString(1);
                String string2 = zzi.f6809a.zzf().zzs(null, zzen.zzaM) ? cursor.getString(2) : null;
                if (cursor.moveToNext()) {
                    zzi.f6809a.zzay().zzd().zzb("Got multiple records for app config, expected one. appId", zzfa.g(str));
                }
                if (blob != null) {
                    zzajVar = new zzaj(blob, string, string2);
                    cursor.close();
                    if (zzajVar != null) {
                        this.zzg.put(str, null);
                        this.f6748d.put(str, null);
                        this.f6747c.put(str, null);
                        this.f6749e.put(str, null);
                        this.zzh.put(str, null);
                        this.zzj.put(str, null);
                        this.zzk.put(str, null);
                        this.zzl.put(str, null);
                        map = this.zzi;
                    } else {
                        com.google.android.gms.internal.measurement.zzfd zzfdVar = (com.google.android.gms.internal.measurement.zzfd) zzA(str, zzajVar.f6681a).zzbB();
                        zzB(str, zzfdVar);
                        this.zzg.put(str, zzE((com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE()));
                        this.zzh.put(str, (com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE());
                        zzD(str, (com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE());
                        this.zzj.put(str, zzfdVar.zze());
                        this.zzk.put(str, zzajVar.f6682b);
                        map = this.zzl;
                        str2 = zzajVar.f6683c;
                    }
                    map.put(str, str2);
                }
            }
            cursor.close();
            zzajVar = null;
            if (zzajVar != null) {
            }
            map.put(str, str2);
        }
    }

    @WorkerThread
    private final void zzD(final String str, com.google.android.gms.internal.measurement.zzfe zzfeVar) {
        if (zzfeVar.zza() == 0) {
            this.f6750f.remove(str);
            return;
        }
        this.f6809a.zzay().zzj().zzb("EES programs found", Integer.valueOf(zzfeVar.zza()));
        com.google.android.gms.internal.measurement.zzgs zzgsVar = (com.google.android.gms.internal.measurement.zzgs) zzfeVar.zzm().get(0);
        try {
            com.google.android.gms.internal.measurement.zzc zzcVar = new com.google.android.gms.internal.measurement.zzc();
            zzcVar.zzd("internal.remoteConfig", new Callable() { // from class: com.google.android.gms.measurement.internal.zzfv
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return new com.google.android.gms.internal.measurement.zzn("internal.remoteConfig", new zzga(zzgb.this, str));
                }
            });
            zzcVar.zzd("internal.appMetadata", new Callable() { // from class: com.google.android.gms.measurement.internal.zzfw
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    final zzgb zzgbVar = zzgb.this;
                    final String str2 = str;
                    return new com.google.android.gms.internal.measurement.zzu("internal.appMetadata", new Callable() { // from class: com.google.android.gms.measurement.internal.zzfu
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            zzgb zzgbVar2 = zzgb.this;
                            String str3 = str2;
                            zzh zzj = zzgbVar2.f7018b.zzi().zzj(str3);
                            HashMap hashMap = new HashMap();
                            hashMap.put("platform", "android");
                            hashMap.put("package_name", str3);
                            zzgbVar2.f6809a.zzf().zzh();
                            hashMap.put("gmp_version", 64000L);
                            if (zzj != null) {
                                String zzw = zzj.zzw();
                                if (zzw != null) {
                                    hashMap.put("app_version", zzw);
                                }
                                hashMap.put("app_version_int", Long.valueOf(zzj.zzb()));
                                hashMap.put("dynamite_version", Long.valueOf(zzj.zzk()));
                            }
                            return hashMap;
                        }
                    });
                }
            });
            zzcVar.zzd("internal.logger", new Callable() { // from class: com.google.android.gms.measurement.internal.zzfx
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return new com.google.android.gms.internal.measurement.zzt(zzgb.this.f6751g);
                }
            });
            zzcVar.zzc(zzgsVar);
            this.f6750f.put(str, zzcVar);
            this.f6809a.zzay().zzj().zzc("EES program loaded for appId, activities", str, Integer.valueOf(zzgsVar.zza().zza()));
            for (com.google.android.gms.internal.measurement.zzgq zzgqVar : zzgsVar.zza().zzd()) {
                this.f6809a.zzay().zzj().zzb("EES program activity", zzgqVar.zzb());
            }
        } catch (com.google.android.gms.internal.measurement.zzd unused) {
            this.f6809a.zzay().zzd().zzb("Failed to load EES program. appId", str);
        }
    }

    private static final Map zzE(com.google.android.gms.internal.measurement.zzfe zzfeVar) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzfeVar != null) {
            for (com.google.android.gms.internal.measurement.zzfi zzfiVar : zzfeVar.zzn()) {
                arrayMap.put(zzfiVar.zzb(), zzfiVar.zzc());
            }
        }
        return arrayMap;
    }

    @Override // com.google.android.gms.measurement.internal.zzkz
    protected final boolean c() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final int d(String str, String str2) {
        Integer num;
        zzg();
        zzC(str);
        Map map = (Map) this.zzi.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final com.google.android.gms.internal.measurement.zzfe f(String str) {
        a();
        zzg();
        Preconditions.checkNotEmpty(str);
        zzC(str);
        return (com.google.android.gms.internal.measurement.zzfe) this.zzh.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final String g(String str) {
        zzg();
        return (String) this.zzl.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final String h(String str) {
        zzg();
        return (String) this.zzk.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final String i(String str) {
        zzg();
        zzC(str);
        return (String) this.zzj.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final Set k(String str) {
        zzg();
        zzC(str);
        return (Set) this.f6747c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void l(String str) {
        zzg();
        this.zzk.put(str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void m(String str) {
        zzg();
        this.zzh.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean n(String str) {
        zzg();
        com.google.android.gms.internal.measurement.zzfe f2 = f(str);
        if (f2 == null) {
            return false;
        }
        return f2.zzq();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean o(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean p(String str, String str2) {
        Boolean bool;
        zzg();
        zzC(str);
        if ("ecommerce_purchase".equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = (Map) this.f6749e.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean q(String str, String str2) {
        Boolean bool;
        zzg();
        zzC(str);
        if (o(str) && zzlt.B(str2)) {
            return true;
        }
        if (r(str) && zzlt.C(str2)) {
            return true;
        }
        Map map = (Map) this.f6748d.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean r(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final boolean s(String str, byte[] bArr, String str2, String str3) {
        a();
        zzg();
        Preconditions.checkNotEmpty(str);
        com.google.android.gms.internal.measurement.zzfd zzfdVar = (com.google.android.gms.internal.measurement.zzfd) zzA(str, bArr).zzbB();
        if (zzfdVar == null) {
            return false;
        }
        zzB(str, zzfdVar);
        zzD(str, (com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE());
        this.zzh.put(str, (com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE());
        this.zzj.put(str, zzfdVar.zze());
        this.zzk.put(str, str2);
        this.zzl.put(str, str3);
        this.zzg.put(str, zzE((com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE()));
        this.f7018b.zzi().d(str, new ArrayList(zzfdVar.zzf()));
        try {
            zzfdVar.zzc();
            bArr = ((com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE()).zzby();
        } catch (RuntimeException e2) {
            this.f6809a.zzay().zzk().zzc("Unable to serialize reduced-size config. Storing full config instead. appId", zzfa.g(str), e2);
        }
        zzam zzi = this.f7018b.zzi();
        Preconditions.checkNotEmpty(str);
        zzi.zzg();
        zzi.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        if (zzi.f6809a.zzf().zzs(null, zzen.zzaM)) {
            contentValues.put("e_tag", str3);
        }
        try {
            if (zzi.p().update("apps", contentValues, "app_id = ?", new String[]{str}) == 0) {
                zzi.f6809a.zzay().zzd().zzb("Failed to update remote config (got 0). appId", zzfa.g(str));
            }
        } catch (SQLiteException e3) {
            zzi.f6809a.zzay().zzd().zzc("Error storing remote config. appId", zzfa.g(str), e3);
        }
        this.zzh.put(str, (com.google.android.gms.internal.measurement.zzfe) zzfdVar.zzaE());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean t(String str) {
        zzg();
        zzC(str);
        return this.f6747c.get(str) != null && ((Set) this.f6747c.get(str)).contains("app_instance_id");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean u(String str) {
        zzg();
        zzC(str);
        return this.f6747c.get(str) != null && (((Set) this.f6747c.get(str)).contains("device_model") || ((Set) this.f6747c.get(str)).contains("device_info"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean v(String str) {
        zzg();
        zzC(str);
        return this.f6747c.get(str) != null && ((Set) this.f6747c.get(str)).contains("google_signals");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean w(String str) {
        zzg();
        zzC(str);
        return this.f6747c.get(str) != null && (((Set) this.f6747c.get(str)).contains("os_version") || ((Set) this.f6747c.get(str)).contains("device_info"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean x(String str) {
        zzg();
        zzC(str);
        return this.f6747c.get(str) != null && ((Set) this.f6747c.get(str)).contains("user_id");
    }

    @Override // com.google.android.gms.measurement.internal.zzaf
    @WorkerThread
    public final String zza(String str, String str2) {
        zzg();
        zzC(str);
        Map map = (Map) this.zzg.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    public final boolean zzo(String str) {
        com.google.android.gms.internal.measurement.zzfe zzfeVar;
        return (TextUtils.isEmpty(str) || (zzfeVar = (com.google.android.gms.internal.measurement.zzfe) this.zzh.get(str)) == null || zzfeVar.zza() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzw(String str) {
        zzg();
        zzC(str);
        return this.f6747c.get(str) != null && ((Set) this.f6747c.get(str)).contains("enhanced_user_id");
    }
}
