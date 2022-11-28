package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zznz;
import com.google.android.gms.internal.measurement.zzps;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Marker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzam extends zzkz {
    private static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;"};
    private static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzal zzj;
    private final zzkv zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzam(zzll zzllVar) {
        super(zzllVar);
        this.zzk = new zzkv(this.f6809a.zzav());
        this.f6809a.zzf();
        this.zzj = new zzal(this, this.f6809a.zzau(), "google_app_measurement.db");
    }

    @WorkerThread
    static final void n(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else if (!(obj instanceof Double)) {
            throw new IllegalArgumentException("Invalid value type");
        } else {
            contentValues.put("value", (Double) obj);
        }
    }

    @WorkerThread
    private final long zzZ(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = p().rawQuery(str, strArr);
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    rawQuery.close();
                    return j2;
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e2) {
                this.f6809a.zzay().zzd().zzc("Database error", str, e2);
                throw e2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final long zzaa(String str, String[] strArr, long j2) {
        Cursor cursor = null;
        try {
            try {
                cursor = p().rawQuery(str, strArr);
                if (!cursor.moveToFirst()) {
                    cursor.close();
                    return j2;
                }
                long j3 = cursor.getLong(0);
                cursor.close();
                return j3;
            } catch (SQLiteException e2) {
                this.f6809a.zzay().zzd().zzc("Database error", str, e2);
                throw e2;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkz
    protected final boolean c() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0334, code lost:
        if (r3.zzk() == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0336, code lost:
        r0 = java.lang.Boolean.valueOf(r3.zzi());
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x033f, code lost:
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0340, code lost:
        r11.put("session_scoped", r0);
        r11.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0354, code lost:
        if (p().insertWithOnConflict("property_filters", null, r11, 5) != (-1)) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0356, code lost:
        r23.f6809a.zzay().zzd().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzfa.g(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x036a, code lost:
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x036e, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x036f, code lost:
        r3 = r23.f6809a.zzay().zzd();
        r4 = "Error storing property filter. appId";
        r7 = com.google.android.gms.measurement.internal.zzfa.g(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0381, code lost:
        a();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        r0 = p();
        r3 = r17;
        r0.delete("property_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r0.delete("event_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r17 = r3;
        r4 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x03b8, code lost:
        r4 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0187, code lost:
        r0.zzc(r7, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x018b, code lost:
        r11 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0197, code lost:
        if (r11.hasNext() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01a3, code lost:
        if (((com.google.android.gms.internal.measurement.zzes) r11.next()).zzj() != false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01a5, code lost:
        r0 = r23.f6809a.zzay().zzk();
        r7 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
        r8 = com.google.android.gms.measurement.internal.zzfa.g(r24);
        r10 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01ba, code lost:
        r11 = r0.zzg().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01d0, code lost:
        if (r11.hasNext() == false) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01d2, code lost:
        r12 = (com.google.android.gms.internal.measurement.zzej) r11.next();
        a();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01ec, code lost:
        if (r12.zzg().isEmpty() == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01ee, code lost:
        r0 = r23.f6809a.zzay().zzk();
        r8 = com.google.android.gms.measurement.internal.zzfa.g(r24);
        r11 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0206, code lost:
        if (r12.zzp() == false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0208, code lost:
        r20 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0213, code lost:
        r20 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0215, code lost:
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r8, r11, java.lang.String.valueOf(r20));
        r21 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0220, code lost:
        r3 = r12.zzby();
        r21 = r4;
        r4 = new android.content.ContentValues();
        r4.put("app_id", r24);
        r4.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0239, code lost:
        if (r12.zzp() == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x023b, code lost:
        r8 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0244, code lost:
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0245, code lost:
        r4.put("filter_id", r8);
        r4.put("event_name", r12.zzg());
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0255, code lost:
        if (r12.zzq() == false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0257, code lost:
        r8 = java.lang.Boolean.valueOf(r12.zzn());
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0260, code lost:
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0261, code lost:
        r4.put("session_scoped", r8);
        r4.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0275, code lost:
        if (p().insertWithOnConflict("event_filters", null, r4, 5) != (-1)) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0277, code lost:
        r23.f6809a.zzay().zzd().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzfa.g(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x028a, code lost:
        r4 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0290, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0291, code lost:
        r3 = r23.f6809a.zzay().zzd();
        r4 = "Error storing event filter. appId";
        r7 = com.google.android.gms.measurement.internal.zzfa.g(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x02a1, code lost:
        r3.zzc(r4, r7, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x02a6, code lost:
        r21 = r4;
        r0 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x02b4, code lost:
        if (r0.hasNext() == false) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x02b6, code lost:
        r3 = (com.google.android.gms.internal.measurement.zzes) r0.next();
        a();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02d0, code lost:
        if (r3.zze().isEmpty() == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02d2, code lost:
        r0 = r23.f6809a.zzay().zzk();
        r7 = com.google.android.gms.measurement.internal.zzfa.g(r24);
        r8 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x02ea, code lost:
        if (r3.zzj() == false) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x02ec, code lost:
        r3 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x02f5, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x02f6, code lost:
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r7, r8, java.lang.String.valueOf(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x02ff, code lost:
        r4 = r3.zzby();
        r11 = new android.content.ContentValues();
        r11.put("app_id", r24);
        r11.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0316, code lost:
        if (r3.zzj() == false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0318, code lost:
        r12 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0321, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0322, code lost:
        r11.put("filter_id", r12);
        r22 = r0;
        r11.put("property_name", r3.zze());
     */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void d(String str, List list) {
        String str2;
        com.google.android.gms.internal.measurement.zzeg zzegVar;
        boolean z;
        String str3 = "app_id=? and audience_id=?";
        Preconditions.checkNotNull(list);
        int i2 = 0;
        while (i2 < list.size()) {
            com.google.android.gms.internal.measurement.zzeg zzegVar2 = (com.google.android.gms.internal.measurement.zzeg) ((com.google.android.gms.internal.measurement.zzeh) list.get(i2)).zzbB();
            if (zzegVar2.zza() != 0) {
                zzegVar = zzegVar2;
                int i3 = 0;
                while (i3 < zzegVar.zza()) {
                    com.google.android.gms.internal.measurement.zzei zzeiVar = (com.google.android.gms.internal.measurement.zzei) zzegVar.zze(i3).zzbB();
                    com.google.android.gms.internal.measurement.zzei zzeiVar2 = (com.google.android.gms.internal.measurement.zzei) zzeiVar.zzau();
                    String zzb2 = zzhh.zzb(zzeiVar.zze());
                    if (zzb2 != null) {
                        zzeiVar2.zzb(zzb2);
                        z = true;
                    } else {
                        z = false;
                    }
                    int i4 = 0;
                    while (i4 < zzeiVar.zza()) {
                        com.google.android.gms.internal.measurement.zzel zzd2 = zzeiVar.zzd(i4);
                        com.google.android.gms.internal.measurement.zzei zzeiVar3 = zzeiVar;
                        String str4 = str3;
                        String zzb3 = zziv.zzb(zzd2.zze(), zzhi.zza, zzhi.zzb);
                        if (zzb3 != null) {
                            com.google.android.gms.internal.measurement.zzek zzekVar = (com.google.android.gms.internal.measurement.zzek) zzd2.zzbB();
                            zzekVar.zza(zzb3);
                            zzeiVar2.zzc(i4, (com.google.android.gms.internal.measurement.zzel) zzekVar.zzaE());
                            z = true;
                        }
                        i4++;
                        zzeiVar = zzeiVar3;
                        str3 = str4;
                    }
                    String str5 = str3;
                    if (z) {
                        zzegVar.zzc(i3, zzeiVar2);
                        list.set(i2, (com.google.android.gms.internal.measurement.zzeh) zzegVar2.zzaE());
                        zzegVar = zzegVar2;
                    }
                    i3++;
                    str3 = str5;
                }
                str2 = str3;
            } else {
                str2 = str3;
                zzegVar = zzegVar2;
            }
            if (zzegVar.zzb() != 0) {
                for (int i5 = 0; i5 < zzegVar.zzb(); i5++) {
                    com.google.android.gms.internal.measurement.zzes zzf = zzegVar.zzf(i5);
                    String zzb4 = zziv.zzb(zzf.zze(), zzhj.zza, zzhj.zzb);
                    if (zzb4 != null) {
                        com.google.android.gms.internal.measurement.zzer zzerVar = (com.google.android.gms.internal.measurement.zzer) zzf.zzbB();
                        zzerVar.zza(zzb4);
                        zzegVar.zzd(i5, zzerVar);
                        list.set(i2, (com.google.android.gms.internal.measurement.zzeh) zzegVar2.zzaE());
                        zzegVar = zzegVar2;
                    }
                }
            }
            i2++;
            str3 = str2;
        }
        String str6 = str3;
        a();
        zzg();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase p2 = p();
        p2.beginTransaction();
        try {
            a();
            zzg();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase p3 = p();
            p3.delete("property_filters", "app_id=?", new String[]{str});
            p3.delete("event_filters", "app_id=?", new String[]{str});
            Iterator it = list.iterator();
            while (it.hasNext()) {
                com.google.android.gms.internal.measurement.zzeh zzehVar = (com.google.android.gms.internal.measurement.zzeh) it.next();
                a();
                zzg();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzehVar);
                if (zzehVar.zzk()) {
                    int zza2 = zzehVar.zza();
                    Iterator it2 = zzehVar.zzg().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (!((com.google.android.gms.internal.measurement.zzej) it2.next()).zzp()) {
                                zzey zzk = this.f6809a.zzay().zzk();
                                String str7 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                                Object g2 = zzfa.g(str);
                                Integer valueOf = Integer.valueOf(zza2);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    try {
                        this.f6809a.zzay().zzk().zzb("Audience with no ID. appId", zzfa.g(str));
                    } catch (Throwable th) {
                        th = th;
                        p2.endTransaction();
                        throw th;
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                com.google.android.gms.internal.measurement.zzeh zzehVar2 = (com.google.android.gms.internal.measurement.zzeh) it3.next();
                arrayList.add(zzehVar2.zzk() ? Integer.valueOf(zzehVar2.zza()) : null);
            }
            Preconditions.checkNotEmpty(str);
            a();
            zzg();
            SQLiteDatabase p4 = p();
            try {
                long zzZ = zzZ("select count(1) from audience_filter_values where app_id=?", new String[]{str});
                int max = Math.max(0, Math.min(2000, this.f6809a.zzf().zze(str, zzen.zzE)));
                if (zzZ > max) {
                    ArrayList arrayList2 = new ArrayList();
                    int i6 = 0;
                    while (true) {
                        if (i6 >= arrayList.size()) {
                            String join = TextUtils.join(",", arrayList2);
                            StringBuilder sb = new StringBuilder();
                            sb.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
                            sb.append("(" + join + ")");
                            sb.append(" order by rowid desc limit -1 offset ?)");
                            p4.delete("audience_filter_values", sb.toString(), new String[]{str, Integer.toString(max)});
                            break;
                        }
                        Integer num = (Integer) arrayList.get(i6);
                        if (num == null) {
                            break;
                        }
                        arrayList2.add(Integer.toString(num.intValue()));
                        i6++;
                    }
                }
            } catch (SQLiteException e2) {
                this.f6809a.zzay().zzd().zzc("Database error querying filters. appId", zzfa.g(str), e2);
            }
            p2.setTransactionSuccessful();
            p2.endTransaction();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @VisibleForTesting
    protected final boolean e() {
        Context zzau = this.f6809a.zzau();
        this.f6809a.zzf();
        return zzau.getDatabasePath("google_app_measurement.db").exists();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    @WorkerThread
    public final long o(String str, String str2) {
        long j2;
        SQLiteException e2;
        ContentValues contentValues;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        a();
        SQLiteDatabase p2 = p();
        p2.beginTransaction();
        try {
            try {
                j2 = zzaa("select first_open_count from app2 where app_id=?", new String[]{str}, -1L);
                if (j2 == -1) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("first_open_count", (Integer) 0);
                    contentValues2.put("previous_install_count", (Integer) 0);
                    if (p2.insertWithOnConflict("app2", null, contentValues2, 5) == -1) {
                        this.f6809a.zzay().zzd().zzc("Failed to insert column (got -1). appId", zzfa.g(str), "first_open_count");
                        return -1L;
                    }
                    j2 = 0;
                }
            } catch (SQLiteException e3) {
                j2 = 0;
                e2 = e3;
            }
            try {
                contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Long.valueOf(1 + j2));
            } catch (SQLiteException e4) {
                e2 = e4;
                this.f6809a.zzay().zzd().zzd("Error inserting column. appId", zzfa.g(str), "first_open_count", e2);
                return j2;
            }
            if (p2.update("app2", contentValues, "app_id = ?", new String[]{str}) == 0) {
                this.f6809a.zzay().zzd().zzc("Failed to update column (got 0). appId", zzfa.g(str), "first_open_count");
                return -1L;
            }
            p2.setTransactionSuccessful();
            return j2;
        } finally {
            p2.endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final SQLiteDatabase p() {
        zzg();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzk().zzb("Error opening database", e2);
            throw e2;
        }
    }

    @VisibleForTesting
    @WorkerThread
    final Object r(Cursor cursor, int i2) {
        int type = cursor.getType(i2);
        if (type == 0) {
            this.f6809a.zzay().zzd().zza("Loaded invalid null value from database");
            return null;
        } else if (type != 1) {
            if (type != 2) {
                if (type != 3) {
                    if (type != 4) {
                        this.f6809a.zzay().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                        return null;
                    }
                    this.f6809a.zzay().zzd().zza("Loaded invalid blob type value, ignoring it");
                    return null;
                }
                return cursor.getString(i2);
            }
            return Double.valueOf(cursor.getDouble(i2));
        } else {
            return Long.valueOf(cursor.getLong(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void s(List list) {
        zzg();
        a();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (e()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzZ("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", null) > 0) {
                this.f6809a.zzay().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                p().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e2) {
                this.f6809a.zzay().zzd().zzb("Error incrementing retry count. error", e2);
            }
        }
    }

    @WorkerThread
    public final void zzA(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        a();
        try {
            p().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzd("Error deleting user property. appId", zzfa.g(str), this.f6809a.zzj().f(str2), e2);
        }
    }

    @WorkerThread
    public final void zzC() {
        a();
        p().setTransactionSuccessful();
    }

    @WorkerThread
    public final void zzD(zzh zzhVar) {
        Preconditions.checkNotNull(zzhVar);
        zzg();
        a();
        String zzt = zzhVar.zzt();
        Preconditions.checkNotNull(zzt);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzt);
        contentValues.put("app_instance_id", zzhVar.zzu());
        contentValues.put("gmp_app_id", zzhVar.zzy());
        contentValues.put("resettable_device_id_hash", zzhVar.zzA());
        contentValues.put("last_bundle_index", Long.valueOf(zzhVar.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzhVar.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzhVar.zzn()));
        contentValues.put("app_version", zzhVar.zzw());
        contentValues.put("app_store", zzhVar.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzhVar.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzhVar.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzhVar.zzaj()));
        contentValues.put("day", Long.valueOf(zzhVar.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzhVar.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzhVar.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzhVar.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzhVar.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzhVar.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzhVar.zzb()));
        contentValues.put("firebase_instance_id", zzhVar.zzx());
        contentValues.put("daily_error_events_count", Long.valueOf(zzhVar.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzhVar.zzh()));
        contentValues.put("health_monitor_sample", zzhVar.zzz());
        contentValues.put("android_id", Long.valueOf(zzhVar.zza()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzhVar.zzai()));
        contentValues.put("admob_app_id", zzhVar.zzr());
        contentValues.put("dynamite_version", Long.valueOf(zzhVar.zzk()));
        contentValues.put("session_stitching_token", zzhVar.zzB());
        List zzC = zzhVar.zzC();
        if (zzC != null) {
            if (zzC.isEmpty()) {
                this.f6809a.zzay().zzk().zzb("Safelisted events should not be an empty list. appId", zzt);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzC));
            }
        }
        zznz.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzay) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        try {
            SQLiteDatabase p2 = p();
            if (p2.update("apps", contentValues, "app_id = ?", new String[]{zzt}) == 0 && p2.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                this.f6809a.zzay().zzd().zzb("Failed to insert/update app (got -1). appId", zzfa.g(zzt));
            }
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzc("Error storing app. appId", zzfa.g(zzt), e2);
        }
    }

    @WorkerThread
    public final void zzE(zzas zzasVar) {
        Preconditions.checkNotNull(zzasVar);
        zzg();
        a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzasVar.f6698a);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzasVar.f6699b);
        contentValues.put("lifetime_count", Long.valueOf(zzasVar.f6700c));
        contentValues.put("current_bundle_count", Long.valueOf(zzasVar.f6701d));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzasVar.f6703f));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzasVar.f6704g));
        contentValues.put("last_bundled_day", zzasVar.f6705h);
        contentValues.put("last_sampled_complex_event_id", zzasVar.f6706i);
        contentValues.put("last_sampling_rate", zzasVar.f6707j);
        contentValues.put("current_session_count", Long.valueOf(zzasVar.f6702e));
        Boolean bool = zzasVar.f6708k;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (p().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                this.f6809a.zzay().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzfa.g(zzasVar.f6698a));
            }
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzc("Error storing event aggregates. appId", zzfa.g(zzasVar.f6698a), e2);
        }
    }

    public final boolean zzF() {
        return zzZ("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzG() {
        return zzZ("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final boolean zzH() {
        return zzZ("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final boolean zzJ(String str, Long l2, long j2, com.google.android.gms.internal.measurement.zzfs zzfsVar) {
        zzg();
        a();
        Preconditions.checkNotNull(zzfsVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l2);
        byte[] zzby = zzfsVar.zzby();
        this.f6809a.zzay().zzj().zzc("Saving complex main event, appId, data size", this.f6809a.zzj().d(str), Integer.valueOf(zzby.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l2);
        contentValues.put("children_to_process", Long.valueOf(j2));
        contentValues.put("main_event", zzby);
        try {
            if (p().insertWithOnConflict("main_event_params", null, contentValues, 5) == -1) {
                this.f6809a.zzay().zzd().zzb("Failed to insert complex main event (got -1). appId", zzfa.g(str));
                return false;
            }
            return true;
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzc("Error storing complex main event. appId", zzfa.g(str), e2);
            return false;
        }
    }

    @WorkerThread
    public final boolean zzK(zzac zzacVar) {
        Preconditions.checkNotNull(zzacVar);
        zzg();
        a();
        String str = zzacVar.zza;
        Preconditions.checkNotNull(str);
        if (zzp(str, zzacVar.zzc.zzb) == null) {
            long zzZ = zzZ("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.f6809a.zzf();
            if (zzZ >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzacVar.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzacVar.zzc.zzb);
        n(contentValues, "value", Preconditions.checkNotNull(zzacVar.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzacVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzacVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzacVar.zzh));
        contentValues.put("timed_out_event", this.f6809a.zzv().G(zzacVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzacVar.zzd));
        contentValues.put("triggered_event", this.f6809a.zzv().G(zzacVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzacVar.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzacVar.zzj));
        contentValues.put("expired_event", this.f6809a.zzv().G(zzacVar.zzk));
        try {
            if (p().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                this.f6809a.zzay().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzfa.g(str));
            }
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzc("Error storing conditional user property", zzfa.g(str), e2);
        }
        return true;
    }

    @WorkerThread
    public final boolean zzL(zzlq zzlqVar) {
        Preconditions.checkNotNull(zzlqVar);
        zzg();
        a();
        if (zzp(zzlqVar.f7042a, zzlqVar.f7044c) == null) {
            if (zzlt.C(zzlqVar.f7044c)) {
                if (zzZ("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzlqVar.f7042a}) >= this.f6809a.zzf().zzf(zzlqVar.f7042a, zzen.zzF, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzlqVar.f7044c)) {
                long zzZ = zzZ("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzlqVar.f7042a, zzlqVar.f7043b});
                this.f6809a.zzf();
                if (zzZ >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzlqVar.f7042a);
        contentValues.put("origin", zzlqVar.f7043b);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzlqVar.f7044c);
        contentValues.put("set_timestamp", Long.valueOf(zzlqVar.f7045d));
        n(contentValues, "value", zzlqVar.f7046e);
        try {
            if (p().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                this.f6809a.zzay().zzd().zzb("Failed to insert/update user property (got -1). appId", zzfa.g(zzlqVar.f7042a));
            }
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzc("Error storing user property. appId", zzfa.g(zzlqVar.f7042a), e2);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0227: MOVE  (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:98:0x0227 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v9 */
    public final void zzU(String str, long j2, long j3, zzli zzliVar) {
        ?? r4;
        Cursor cursor;
        Cursor rawQuery;
        String string;
        int i2;
        String str2;
        String[] strArr;
        Preconditions.checkNotNull(zzliVar);
        zzg();
        a();
        Cursor cursor2 = null;
        r3 = null;
        r3 = null;
        String str3 = null;
        try {
            try {
                SQLiteDatabase p2 = p();
                r4 = TextUtils.isEmpty(null);
                try {
                    if (r4 != 0) {
                        int i3 = (j3 > (-1L) ? 1 : (j3 == (-1L) ? 0 : -1));
                        rawQuery = p2.rawQuery("select app_id, metadata_fingerprint from raw_events where " + (i3 != 0 ? "rowid <= ? and " : "") + "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;", i3 != 0 ? new String[]{String.valueOf(j3), String.valueOf(j2)} : new String[]{String.valueOf(j2)});
                        if (!rawQuery.moveToFirst()) {
                            rawQuery.close();
                            return;
                        }
                        str3 = rawQuery.getString(0);
                        string = rawQuery.getString(1);
                        rawQuery.close();
                    } else {
                        int i4 = (j3 > (-1L) ? 1 : (j3 == (-1L) ? 0 : -1));
                        rawQuery = p2.rawQuery("select metadata_fingerprint from raw_events where app_id = ?" + (i4 != 0 ? " and rowid <= ?" : "") + " order by rowid limit 1;", i4 != 0 ? new String[]{null, String.valueOf(j3)} : new String[]{null});
                        if (!rawQuery.moveToFirst()) {
                            rawQuery.close();
                            return;
                        } else {
                            string = rawQuery.getString(0);
                            rawQuery.close();
                        }
                    }
                    Cursor cursor3 = rawQuery;
                    String str4 = string;
                    try {
                        Cursor query = p2.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str3, str4}, null, null, "rowid", ExifInterface.GPS_MEASUREMENT_2D);
                        try {
                            if (!query.moveToFirst()) {
                                this.f6809a.zzay().zzd().zzb("Raw event metadata record is missing. appId", zzfa.g(str3));
                                query.close();
                                return;
                            }
                            try {
                                try {
                                    com.google.android.gms.internal.measurement.zzgc zzgcVar = (com.google.android.gms.internal.measurement.zzgc) ((com.google.android.gms.internal.measurement.zzgb) zzln.m(com.google.android.gms.internal.measurement.zzgc.zzu(), query.getBlob(0))).zzaE();
                                    if (query.moveToNext()) {
                                        this.f6809a.zzay().zzk().zzb("Get multiple raw event metadata records, expected one. appId", zzfa.g(str3));
                                    }
                                    query.close();
                                    Preconditions.checkNotNull(zzgcVar);
                                    zzliVar.f7033a = zzgcVar;
                                    if (j3 != -1) {
                                        i2 = 1;
                                        str2 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                                        strArr = new String[]{str3, str4, String.valueOf(j3)};
                                    } else {
                                        i2 = 1;
                                        str2 = "app_id = ? and metadata_fingerprint = ?";
                                        strArr = new String[]{str3, str4};
                                    }
                                    Cursor query2 = p2.query("raw_events", new String[]{"rowid", AppMeasurementSdk.ConditionalUserProperty.NAME, "timestamp", Constants.ScionAnalytics.MessageType.DATA_MESSAGE}, str2, strArr, null, null, "rowid", null);
                                    if (!query2.moveToFirst()) {
                                        this.f6809a.zzay().zzk().zzb("Raw event data disappeared while in transaction. appId", zzfa.g(str3));
                                        query2.close();
                                        return;
                                    }
                                    do {
                                        long j4 = query2.getLong(0);
                                        try {
                                            com.google.android.gms.internal.measurement.zzfr zzfrVar = (com.google.android.gms.internal.measurement.zzfr) zzln.m(com.google.android.gms.internal.measurement.zzfs.zze(), query2.getBlob(3));
                                            zzfrVar.zzi(query2.getString(i2));
                                            zzfrVar.zzm(query2.getLong(2));
                                            if (!zzliVar.zza(j4, (com.google.android.gms.internal.measurement.zzfs) zzfrVar.zzaE())) {
                                                query2.close();
                                                return;
                                            }
                                        } catch (IOException e2) {
                                            this.f6809a.zzay().zzd().zzc("Data loss. Failed to merge raw event. appId", zzfa.g(str3), e2);
                                        }
                                    } while (query2.moveToNext());
                                    query2.close();
                                } catch (IOException e3) {
                                    this.f6809a.zzay().zzd().zzc("Data loss. Failed to merge raw event metadata. appId", zzfa.g(str3), e3);
                                    query.close();
                                }
                            } catch (SQLiteException e4) {
                                e = e4;
                                r4 = str4;
                                this.f6809a.zzay().zzd().zzc("Data loss. Error selecting raw event. appId", zzfa.g(str3), e);
                                if (r4 != 0) {
                                    r4.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor2 = str4;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteException e5) {
                            e = e5;
                            str4 = query;
                        } catch (Throwable th2) {
                            th = th2;
                            str4 = query;
                        }
                    } catch (SQLiteException e6) {
                        e = e6;
                        r4 = cursor3;
                    } catch (Throwable th3) {
                        th = th3;
                        cursor2 = cursor3;
                    }
                } catch (SQLiteException e7) {
                    e = e7;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor2 = cursor;
            }
        } catch (SQLiteException e8) {
            e = e8;
            r4 = 0;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    @WorkerThread
    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        a();
        try {
            return p().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzd("Error deleting conditional property", zzfa.g(str), this.f6809a.zzj().f(str2), e2);
            return 0;
        }
    }

    @WorkerThread
    public final long zzd() {
        return zzaa("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    @WorkerThread
    public final long zze() {
        return zzaa("select max(timestamp) from raw_events", null, 0L);
    }

    public final long zzf(String str) {
        Preconditions.checkNotEmpty(str);
        return zzaa("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00d6: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:44:0x00d6 */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bundle zzi(String str) {
        Cursor cursor;
        Cursor cursor2;
        zzg();
        a();
        Cursor cursor3 = null;
        try {
            try {
                cursor = p().rawQuery("select parameters from default_event_params where app_id=?", new String[]{str});
                try {
                    if (!cursor.moveToFirst()) {
                        this.f6809a.zzay().zzj().zza("Default event parameters not found");
                        cursor.close();
                        return null;
                    }
                    try {
                        com.google.android.gms.internal.measurement.zzfs zzfsVar = (com.google.android.gms.internal.measurement.zzfs) ((com.google.android.gms.internal.measurement.zzfr) zzln.m(com.google.android.gms.internal.measurement.zzfs.zze(), cursor.getBlob(0))).zzaE();
                        this.f7018b.zzu();
                        List<com.google.android.gms.internal.measurement.zzfw> zzi2 = zzfsVar.zzi();
                        Bundle bundle = new Bundle();
                        for (com.google.android.gms.internal.measurement.zzfw zzfwVar : zzi2) {
                            String zzg2 = zzfwVar.zzg();
                            if (zzfwVar.zzu()) {
                                bundle.putDouble(zzg2, zzfwVar.zza());
                            } else if (zzfwVar.zzv()) {
                                bundle.putFloat(zzg2, zzfwVar.zzb());
                            } else if (zzfwVar.zzy()) {
                                bundle.putString(zzg2, zzfwVar.zzh());
                            } else if (zzfwVar.zzw()) {
                                bundle.putLong(zzg2, zzfwVar.zzd());
                            }
                        }
                        cursor.close();
                        return bundle;
                    } catch (IOException e2) {
                        this.f6809a.zzay().zzd().zzc("Failed to retrieve default event parameters. appId", zzfa.g(str), e2);
                        cursor.close();
                        return null;
                    }
                } catch (SQLiteException e3) {
                    e = e3;
                    this.f6809a.zzay().zzd().zzb("Error selecting default event parameters", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
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
            e = e4;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x011d A[Catch: SQLiteException -> 0x01ea, all -> 0x0209, TryCatch #0 {SQLiteException -> 0x01ea, blocks: (B:4:0x0061, B:8:0x006b, B:10:0x00ce, B:15:0x00d8, B:19:0x0122, B:21:0x0159, B:25:0x0167, B:24:0x0163, B:26:0x016a, B:28:0x0172, B:32:0x017a, B:36:0x0193, B:38:0x019e, B:39:0x01b0, B:41:0x01c1, B:42:0x01ca, B:44:0x01d3, B:35:0x018f, B:18:0x011d), top: B:62:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0159 A[Catch: SQLiteException -> 0x01ea, all -> 0x0209, TryCatch #0 {SQLiteException -> 0x01ea, blocks: (B:4:0x0061, B:8:0x006b, B:10:0x00ce, B:15:0x00d8, B:19:0x0122, B:21:0x0159, B:25:0x0167, B:24:0x0163, B:26:0x016a, B:28:0x0172, B:32:0x017a, B:36:0x0193, B:38:0x019e, B:39:0x01b0, B:41:0x01c1, B:42:0x01ca, B:44:0x01d3, B:35:0x018f, B:18:0x011d), top: B:62:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x018f A[Catch: SQLiteException -> 0x01ea, all -> 0x0209, TryCatch #0 {SQLiteException -> 0x01ea, blocks: (B:4:0x0061, B:8:0x006b, B:10:0x00ce, B:15:0x00d8, B:19:0x0122, B:21:0x0159, B:25:0x0167, B:24:0x0163, B:26:0x016a, B:28:0x0172, B:32:0x017a, B:36:0x0193, B:38:0x019e, B:39:0x01b0, B:41:0x01c1, B:42:0x01ca, B:44:0x01d3, B:35:0x018f, B:18:0x011d), top: B:62:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x019e A[Catch: SQLiteException -> 0x01ea, all -> 0x0209, TryCatch #0 {SQLiteException -> 0x01ea, blocks: (B:4:0x0061, B:8:0x006b, B:10:0x00ce, B:15:0x00d8, B:19:0x0122, B:21:0x0159, B:25:0x0167, B:24:0x0163, B:26:0x016a, B:28:0x0172, B:32:0x017a, B:36:0x0193, B:38:0x019e, B:39:0x01b0, B:41:0x01c1, B:42:0x01ca, B:44:0x01d3, B:35:0x018f, B:18:0x011d), top: B:62:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01c1 A[Catch: SQLiteException -> 0x01ea, all -> 0x0209, TryCatch #0 {SQLiteException -> 0x01ea, blocks: (B:4:0x0061, B:8:0x006b, B:10:0x00ce, B:15:0x00d8, B:19:0x0122, B:21:0x0159, B:25:0x0167, B:24:0x0163, B:26:0x016a, B:28:0x0172, B:32:0x017a, B:36:0x0193, B:38:0x019e, B:39:0x01b0, B:41:0x01c1, B:42:0x01ca, B:44:0x01d3, B:35:0x018f, B:18:0x011d), top: B:62:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01d3 A[Catch: SQLiteException -> 0x01ea, all -> 0x0209, TRY_LEAVE, TryCatch #0 {SQLiteException -> 0x01ea, blocks: (B:4:0x0061, B:8:0x006b, B:10:0x00ce, B:15:0x00d8, B:19:0x0122, B:21:0x0159, B:25:0x0167, B:24:0x0163, B:26:0x016a, B:28:0x0172, B:32:0x017a, B:36:0x0193, B:38:0x019e, B:39:0x01b0, B:41:0x01c1, B:42:0x01ca, B:44:0x01d3, B:35:0x018f, B:18:0x011d), top: B:62:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x020d  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzh zzj(String str) {
        Cursor cursor;
        boolean z;
        Preconditions.checkNotEmpty(str);
        zzg();
        a();
        Cursor cursor2 = null;
        try {
            boolean z2 = true;
            cursor = p().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id", "session_stitching_token"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                try {
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        return null;
                    }
                    zzh zzhVar = new zzh(this.f7018b.E(), str);
                    zzhVar.zzI(cursor.getString(0));
                    zzhVar.zzX(cursor.getString(1));
                    zzhVar.zzaf(cursor.getString(2));
                    zzhVar.zzab(cursor.getLong(3));
                    zzhVar.zzac(cursor.getLong(4));
                    zzhVar.zzaa(cursor.getLong(5));
                    zzhVar.zzK(cursor.getString(6));
                    zzhVar.zzJ(cursor.getString(7));
                    zzhVar.zzY(cursor.getLong(8));
                    zzhVar.zzT(cursor.getLong(9));
                    if (!cursor.isNull(10) && cursor.getInt(10) == 0) {
                        z = false;
                        zzhVar.zzad(z);
                        zzhVar.zzS(cursor.getLong(11));
                        zzhVar.zzQ(cursor.getLong(12));
                        zzhVar.zzP(cursor.getLong(13));
                        zzhVar.zzN(cursor.getLong(14));
                        zzhVar.zzM(cursor.getLong(15));
                        zzhVar.zzV(cursor.getLong(16));
                        zzhVar.zzL(!cursor.isNull(17) ? -2147483648L : cursor.getInt(17));
                        zzhVar.zzW(cursor.getString(18));
                        zzhVar.zzO(cursor.getLong(19));
                        zzhVar.zzR(cursor.getLong(20));
                        zzhVar.zzZ(cursor.getString(21));
                        long j2 = 0;
                        if (!this.f6809a.zzf().zzs(null, zzen.zzah)) {
                            zzhVar.zzH(cursor.isNull(22) ? 0L : cursor.getLong(22));
                        }
                        if (!cursor.isNull(23) && cursor.getInt(23) == 0) {
                            z2 = false;
                        }
                        zzhVar.zzG(z2);
                        zzhVar.zzF(cursor.getString(24));
                        if (cursor.isNull(25)) {
                            j2 = cursor.getLong(25);
                        }
                        zzhVar.zzU(j2);
                        if (!cursor.isNull(26)) {
                            zzhVar.zzag(Arrays.asList(cursor.getString(26).split(",", -1)));
                        }
                        zzps.zzc();
                        if (this.f6809a.zzf().zzs(null, zzen.zzaH)) {
                            zzhVar.zzah(cursor.getString(28));
                        }
                        zzhVar.zzD();
                        if (cursor.moveToNext()) {
                            this.f6809a.zzay().zzd().zzb("Got multiple records for app, expected one. appId", zzfa.g(str));
                        }
                        cursor.close();
                        return zzhVar;
                    }
                    z = true;
                    zzhVar.zzad(z);
                    zzhVar.zzS(cursor.getLong(11));
                    zzhVar.zzQ(cursor.getLong(12));
                    zzhVar.zzP(cursor.getLong(13));
                    zzhVar.zzN(cursor.getLong(14));
                    zzhVar.zzM(cursor.getLong(15));
                    zzhVar.zzV(cursor.getLong(16));
                    zzhVar.zzL(!cursor.isNull(17) ? -2147483648L : cursor.getInt(17));
                    zzhVar.zzW(cursor.getString(18));
                    zzhVar.zzO(cursor.getLong(19));
                    zzhVar.zzR(cursor.getLong(20));
                    zzhVar.zzZ(cursor.getString(21));
                    long j22 = 0;
                    if (!this.f6809a.zzf().zzs(null, zzen.zzah)) {
                    }
                    if (!cursor.isNull(23)) {
                        z2 = false;
                    }
                    zzhVar.zzG(z2);
                    zzhVar.zzF(cursor.getString(24));
                    if (cursor.isNull(25)) {
                    }
                    zzhVar.zzU(j22);
                    if (!cursor.isNull(26)) {
                    }
                    zzps.zzc();
                    if (this.f6809a.zzf().zzs(null, zzen.zzaH)) {
                    }
                    zzhVar.zzD();
                    if (cursor.moveToNext()) {
                    }
                    cursor.close();
                    return zzhVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    this.f6809a.zzay().zzd().zzc("Error querying app. appId", zzfa.g(str), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0123: MOVE  (r9 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:31:0x0123 */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0126  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzac zzk(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        a();
        Cursor cursor3 = null;
        try {
            try {
                cursor = p().query("conditional_properties", new String[]{"origin", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        return null;
                    }
                    String string = cursor.getString(0);
                    if (string == null) {
                        string = "";
                    }
                    String str3 = string;
                    Object r2 = r(cursor, 1);
                    boolean z = cursor.getInt(2) != 0;
                    String string2 = cursor.getString(3);
                    long j2 = cursor.getLong(4);
                    zzln zzu = this.f7018b.zzu();
                    byte[] blob = cursor.getBlob(5);
                    Parcelable.Creator<zzaw> creator = zzaw.CREATOR;
                    zzac zzacVar = new zzac(str, str3, new zzlo(str2, cursor.getLong(8), r2, str3), cursor.getLong(6), z, string2, (zzaw) zzu.j(blob, creator), j2, (zzaw) this.f7018b.zzu().j(cursor.getBlob(7), creator), cursor.getLong(9), (zzaw) this.f7018b.zzu().j(cursor.getBlob(10), creator));
                    if (cursor.moveToNext()) {
                        this.f6809a.zzay().zzd().zzc("Got multiple records for conditional property, expected one", zzfa.g(str), this.f6809a.zzj().f(str2));
                    }
                    cursor.close();
                    return zzacVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    this.f6809a.zzay().zzd().zzd("Error querying conditional property", zzfa.g(str), this.f6809a.zzj().f(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor3 = cursor2;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
    }

    @WorkerThread
    public final zzak zzl(long j2, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zzm(j2, str, 1L, false, false, z3, false, z5);
    }

    @WorkerThread
    public final zzak zzm(long j2, String str, long j3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzg();
        a();
        String[] strArr = {str};
        zzak zzakVar = new zzak();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase p2 = p();
                Cursor query = p2.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!query.moveToFirst()) {
                    this.f6809a.zzay().zzk().zzb("Not updating daily counts, app is not known. appId", zzfa.g(str));
                    query.close();
                    return zzakVar;
                }
                if (query.getLong(0) == j2) {
                    zzakVar.f6685b = query.getLong(1);
                    zzakVar.f6684a = query.getLong(2);
                    zzakVar.f6686c = query.getLong(3);
                    zzakVar.f6687d = query.getLong(4);
                    zzakVar.f6688e = query.getLong(5);
                }
                if (z) {
                    zzakVar.f6685b += j3;
                }
                if (z2) {
                    zzakVar.f6684a += j3;
                }
                if (z3) {
                    zzakVar.f6686c += j3;
                }
                if (z4) {
                    zzakVar.f6687d += j3;
                }
                if (z5) {
                    zzakVar.f6688e += j3;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j2));
                contentValues.put("daily_public_events_count", Long.valueOf(zzakVar.f6684a));
                contentValues.put("daily_events_count", Long.valueOf(zzakVar.f6685b));
                contentValues.put("daily_conversions_count", Long.valueOf(zzakVar.f6686c));
                contentValues.put("daily_error_events_count", Long.valueOf(zzakVar.f6687d));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzakVar.f6688e));
                p2.update("apps", contentValues, "app_id=?", strArr);
                query.close();
                return zzakVar;
            } catch (SQLiteException e2) {
                this.f6809a.zzay().zzd().zzc("Error updating daily counts. appId", zzfa.g(str), e2);
                if (0 != 0) {
                    cursor.close();
                }
                return zzakVar;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0150  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzas zzn(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Boolean bool;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        a();
        Cursor cursor3 = null;
        try {
            Cursor query = p().query("events", (String[]) new ArrayList(Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count")).toArray(new String[0]), "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!query.moveToFirst()) {
                    query.close();
                    return null;
                }
                long j2 = query.getLong(0);
                long j3 = query.getLong(1);
                long j4 = query.getLong(2);
                long j5 = query.isNull(3) ? 0L : query.getLong(3);
                Long valueOf = query.isNull(4) ? null : Long.valueOf(query.getLong(4));
                Long valueOf2 = query.isNull(5) ? null : Long.valueOf(query.getLong(5));
                Long valueOf3 = query.isNull(6) ? null : Long.valueOf(query.getLong(6));
                if (query.isNull(7)) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(query.getLong(7) == 1);
                }
                cursor2 = query;
                try {
                    zzas zzasVar = new zzas(str, str2, j2, j3, query.isNull(8) ? 0L : query.getLong(8), j4, j5, valueOf, valueOf2, valueOf3, bool);
                    if (cursor2.moveToNext()) {
                        this.f6809a.zzay().zzd().zzb("Got multiple records for event aggregates, expected one. appId", zzfa.g(str));
                    }
                    cursor2.close();
                    return zzasVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    cursor = cursor2;
                    try {
                        this.f6809a.zzay().zzd().zzd("Error querying events. appId", zzfa.g(str), this.f6809a.zzj().d(str2), e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        cursor3 = cursor;
                        if (cursor3 != null) {
                            cursor3.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor3 = cursor2;
                    if (cursor3 != null) {
                    }
                    throw th;
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor2 = query;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00a3: MOVE  (r10 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:28:0x00a3 */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a6  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzlq zzp(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        a();
        Cursor cursor3 = null;
        try {
            try {
                cursor = p().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        return null;
                    }
                    long j2 = cursor.getLong(0);
                    Object r2 = r(cursor, 1);
                    if (r2 == null) {
                        cursor.close();
                        return null;
                    }
                    zzlq zzlqVar = new zzlq(str, cursor.getString(2), str2, j2, r2);
                    if (cursor.moveToNext()) {
                        this.f6809a.zzay().zzd().zzb("Got multiple records for user property, expected one. appId", zzfa.g(str));
                    }
                    cursor.close();
                    return zzlqVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    this.f6809a.zzay().zzd().zzd("Error querying user property. appId", zzfa.g(str), this.f6809a.zzj().f(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor3 = cursor2;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0040  */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v3 */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String zzr() {
        SQLiteException e2;
        Cursor cursor;
        SQLiteDatabase p2 = p();
        ?? r1 = 0;
        try {
            try {
                cursor = p2.rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
                try {
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        return null;
                    }
                    String string = cursor.getString(0);
                    cursor.close();
                    return string;
                } catch (SQLiteException e3) {
                    e2 = e3;
                    this.f6809a.zzay().zzd().zzb("Database error getting next bundle app id", e2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                r1 = p2;
                th = th;
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e2 = e4;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (r1 != 0) {
            }
            throw th;
        }
    }

    @WorkerThread
    public final List zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        a();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat(Marker.ANY_MARKER));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0058, code lost:
        r2 = r27.f6809a.zzay().zzd();
        r27.f6809a.zzf();
        r2.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzt(String str, String[] strArr) {
        zzg();
        a();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase p2 = p();
                String[] strArr2 = {"app_id", "origin", AppMeasurementSdk.ConditionalUserProperty.NAME, "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"};
                this.f6809a.zzf();
                cursor = p2.query("conditional_properties", strArr2, str, strArr, null, null, "rowid", "1001");
                if (!cursor.moveToFirst()) {
                    cursor.close();
                    return arrayList;
                }
                while (true) {
                    int size = arrayList.size();
                    this.f6809a.zzf();
                    if (size < 1000) {
                        String string = cursor.getString(0);
                        String string2 = cursor.getString(1);
                        String string3 = cursor.getString(2);
                        Object r2 = r(cursor, 3);
                        boolean z = cursor.getInt(4) != 0;
                        String string4 = cursor.getString(5);
                        long j2 = cursor.getLong(6);
                        zzln zzu = this.f7018b.zzu();
                        byte[] blob = cursor.getBlob(7);
                        Parcelable.Creator<zzaw> creator = zzaw.CREATOR;
                        arrayList.add(new zzac(string, string2, new zzlo(string3, cursor.getLong(10), r2, string2), cursor.getLong(8), z, string4, (zzaw) zzu.j(blob, creator), j2, (zzaw) this.f7018b.zzu().j(cursor.getBlob(9), creator), cursor.getLong(11), (zzaw) this.f7018b.zzu().j(cursor.getBlob(12), creator)));
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                cursor.close();
                return arrayList;
            } catch (SQLiteException e2) {
                this.f6809a.zzay().zzd().zzb("Error querying conditional user property value", e2);
                List emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List zzu(String str) {
        Preconditions.checkNotEmpty(str);
        zzg();
        a();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                this.f6809a.zzf();
                cursor = p().query("user_attributes", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME, "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                if (!cursor.moveToFirst()) {
                    cursor.close();
                    return arrayList;
                }
                do {
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    if (string2 == null) {
                        string2 = "";
                    }
                    String str2 = string2;
                    long j2 = cursor.getLong(2);
                    Object r2 = r(cursor, 3);
                    if (r2 == null) {
                        this.f6809a.zzay().zzd().zzb("Read invalid user property value, ignoring it. appId", zzfa.g(str));
                    } else {
                        arrayList.add(new zzlq(str, str2, string, j2, r2));
                    }
                } while (cursor.moveToNext());
                cursor.close();
                return arrayList;
            } catch (SQLiteException e2) {
                this.f6809a.zzay().zzd().zzc("Error querying user properties. appId", zzfa.g(str), e2);
                List emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a7, code lost:
        r0 = r17.f6809a.zzay().zzd();
        r17.f6809a.zzf();
        r0.zzb("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0124 A[DONT_GENERATE] */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzv(String str, String str2, String str3) {
        String str4;
        Preconditions.checkNotEmpty(str);
        zzg();
        a();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                ArrayList arrayList2 = new ArrayList(3);
                try {
                    arrayList2.add(str);
                    StringBuilder sb = new StringBuilder("app_id=?");
                    if (TextUtils.isEmpty(str2)) {
                        str4 = str2;
                    } else {
                        str4 = str2;
                        try {
                            arrayList2.add(str4);
                            sb.append(" and origin=?");
                        } catch (SQLiteException e2) {
                            e = e2;
                            this.f6809a.zzay().zzd().zzd("(2)Error querying user properties", zzfa.g(str), str4, e);
                            return Collections.emptyList();
                        }
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        arrayList2.add(str3 + Marker.ANY_MARKER);
                        sb.append(" and name glob ?");
                    }
                    String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                    SQLiteDatabase p2 = p();
                    String[] strArr2 = {AppMeasurementSdk.ConditionalUserProperty.NAME, "set_timestamp", "value", "origin"};
                    String sb2 = sb.toString();
                    this.f6809a.zzf();
                    cursor = p2.query("user_attributes", strArr2, sb2, strArr, null, null, "rowid", "1001");
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        return arrayList;
                    }
                    while (true) {
                        int size = arrayList.size();
                        this.f6809a.zzf();
                        if (size < 1000) {
                            String string = cursor.getString(0);
                            long j2 = cursor.getLong(1);
                            Object r2 = r(cursor, 2);
                            str4 = cursor.getString(3);
                            if (r2 == null) {
                                this.f6809a.zzay().zzd().zzd("(2)Read invalid user property value, ignoring it", zzfa.g(str), str4, str3);
                            } else {
                                arrayList.add(new zzlq(str, str4, string, j2, r2));
                            }
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    cursor.close();
                    return arrayList;
                } catch (SQLiteException e3) {
                    e = e3;
                    str4 = str2;
                    this.f6809a.zzay().zzd().zzd("(2)Error querying user properties", zzfa.g(str), str4, e);
                    return Collections.emptyList();
                }
            } finally {
                if (0 != 0) {
                    cursor.close();
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
        }
    }

    @WorkerThread
    public final void zzw() {
        a();
        p().beginTransaction();
    }

    @WorkerThread
    public final void zzx() {
        a();
        p().endTransaction();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzz() {
        zzg();
        a();
        if (e()) {
            long zza2 = this.f7018b.zzs().zza.zza();
            long elapsedRealtime = this.f6809a.zzav().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            this.f6809a.zzf();
            if (abs > ((Long) zzen.zzx.zza(null)).longValue()) {
                this.f7018b.zzs().zza.zzb(elapsedRealtime);
                zzg();
                a();
                if (e()) {
                    SQLiteDatabase p2 = p();
                    this.f6809a.zzf();
                    int delete = p2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(this.f6809a.zzav().currentTimeMillis()), String.valueOf(zzag.zzA())});
                    if (delete > 0) {
                        this.f6809a.zzay().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }
}
