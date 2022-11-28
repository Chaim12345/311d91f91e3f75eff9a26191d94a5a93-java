package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzw {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzaa f7056a;
    private com.google.android.gms.internal.measurement.zzfs zzb;
    private Long zzc;
    private long zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzw(zzaa zzaaVar, zzv zzvVar) {
        this.f7056a = zzaaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00fa, code lost:
        if (r14 == null) goto L64;
     */
    /* JADX WARN: Not initialized variable reg: 14, insn: 0x01df: MOVE  (r5 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY]), block:B:66:0x01df */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final com.google.android.gms.internal.measurement.zzfs a(String str, com.google.android.gms.internal.measurement.zzfs zzfsVar) {
        Cursor cursor;
        Cursor cursor2;
        Cursor cursor3;
        Pair pair;
        Object obj;
        String zzh = zzfsVar.zzh();
        List zzi = zzfsVar.zzi();
        this.f7056a.f7018b.zzu();
        Long l2 = (Long) zzln.f(zzfsVar, "_eid");
        if (l2 != null) {
            if (zzh.equals("_ep")) {
                Preconditions.checkNotNull(l2);
                this.f7056a.f7018b.zzu();
                String str2 = (String) zzln.f(zzfsVar, "_en");
                if (TextUtils.isEmpty(str2)) {
                    this.f7056a.f6809a.zzay().zzh().zzb("Extra parameter without an event name. eventId", l2);
                    return null;
                }
                if (this.zzb == null || this.zzc == null || l2.longValue() != this.zzc.longValue()) {
                    zzam zzi2 = this.f7056a.f7018b.zzi();
                    zzi2.zzg();
                    zzi2.a();
                    try {
                        try {
                            cursor2 = zzi2.p().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, l2.toString()});
                            try {
                                if (cursor2.moveToFirst()) {
                                    try {
                                        pair = Pair.create((com.google.android.gms.internal.measurement.zzfs) ((com.google.android.gms.internal.measurement.zzfr) zzln.m(com.google.android.gms.internal.measurement.zzfs.zze(), cursor2.getBlob(0))).zzaE(), Long.valueOf(cursor2.getLong(1)));
                                        cursor2.close();
                                    } catch (IOException e2) {
                                        zzi2.f6809a.zzay().zzd().zzd("Failed to merge main event. appId, eventId", zzfa.g(str), l2, e2);
                                        cursor2.close();
                                        pair = null;
                                        if (pair != null) {
                                        }
                                        this.f7056a.f6809a.zzay().zzh().zzc("Extra parameter without existing main event. eventName, eventId", str2, l2);
                                        return null;
                                    }
                                } else {
                                    zzi2.f6809a.zzay().zzj().zza("Main event not found");
                                    cursor2.close();
                                    pair = null;
                                }
                            } catch (SQLiteException e3) {
                                e = e3;
                                zzi2.f6809a.zzay().zzd().zzb("Error selecting main event", e);
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursor3;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    } catch (SQLiteException e4) {
                        e = e4;
                        cursor2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = null;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                    if (pair != null || (obj = pair.first) == null) {
                        this.f7056a.f6809a.zzay().zzh().zzc("Extra parameter without existing main event. eventName, eventId", str2, l2);
                        return null;
                    }
                    this.zzb = (com.google.android.gms.internal.measurement.zzfs) obj;
                    this.zzd = ((Long) pair.second).longValue();
                    this.f7056a.f7018b.zzu();
                    this.zzc = (Long) zzln.f(this.zzb, "_eid");
                }
                long j2 = this.zzd - 1;
                this.zzd = j2;
                if (j2 <= 0) {
                    zzam zzi3 = this.f7056a.f7018b.zzi();
                    zzi3.zzg();
                    zzi3.f6809a.zzay().zzj().zzb("Clearing complex main event info. appId", str);
                    try {
                        zzi3.p().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                    } catch (SQLiteException e5) {
                        zzi3.f6809a.zzay().zzd().zzb("Error clearing complex main event", e5);
                    }
                } else {
                    this.f7056a.f7018b.zzi().zzJ(str, l2, this.zzd, this.zzb);
                }
                ArrayList arrayList = new ArrayList();
                for (com.google.android.gms.internal.measurement.zzfw zzfwVar : this.zzb.zzi()) {
                    this.f7056a.f7018b.zzu();
                    if (zzln.e(zzfsVar, zzfwVar.zzg()) == null) {
                        arrayList.add(zzfwVar);
                    }
                }
                if (arrayList.isEmpty()) {
                    this.f7056a.f6809a.zzay().zzh().zzb("No unique parameters in main event. eventName", str2);
                } else {
                    arrayList.addAll(zzi);
                    zzi = arrayList;
                }
                zzh = str2;
            } else {
                this.zzc = l2;
                this.zzb = zzfsVar;
                this.f7056a.f7018b.zzu();
                Object f2 = zzln.f(zzfsVar, "_epc");
                long longValue = ((Long) (f2 != null ? f2 : 0L)).longValue();
                this.zzd = longValue;
                if (longValue <= 0) {
                    this.f7056a.f6809a.zzay().zzh().zzb("Complex event with zero extra param count. eventName", zzh);
                } else {
                    this.f7056a.f7018b.zzi().zzJ(str, (Long) Preconditions.checkNotNull(l2), this.zzd, zzfsVar);
                }
            }
        }
        com.google.android.gms.internal.measurement.zzfr zzfrVar = (com.google.android.gms.internal.measurement.zzfr) zzfsVar.zzbB();
        zzfrVar.zzi(zzh);
        zzfrVar.zzg();
        zzfrVar.zzd(zzi);
        return (com.google.android.gms.internal.measurement.zzfs) zzfrVar.zzaE();
    }
}
