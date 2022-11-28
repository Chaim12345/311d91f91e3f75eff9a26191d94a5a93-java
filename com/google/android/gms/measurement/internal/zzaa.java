package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzoi;
import com.google.android.gms.internal.measurement.zzol;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaa extends zzkz {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zzll zzllVar) {
        super(zzllVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i2, int i3) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i2));
        if (zzuVar == null) {
            return false;
        }
        return zzu.b(zzuVar).get(i3);
    }

    @Override // com.google.android.gms.measurement.internal.zzkz
    protected final boolean c() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(16:(6:19|20|21|22|23|(20:(7:25|26|27|28|(1:30)(3:503|(1:505)(1:507)|506)|31|(1:34)(1:33))|35|36|37|38|39|40|(2:42|43)(3:463|(6:464|465|466|467|468|(1:471)(1:470))|472)|44|(1:46)(6:274|(11:276|277|278|279|280|(2:(3:282|(1:284)|285)|287)(1:449)|288|289|(3:384|(6:387|(1:446)(2:391|(4:397|398|(7:400|(4:403|(2:405|406)(1:408)|407|401)|409|410|(4:413|(3:415|416|417)(1:419)|418|411)|420|421)(6:425|(4:428|(2:430|431)(1:433)|432|426)|434|435|(4:438|(2:440|441)(1:443)|442|436)|444)|422)(4:393|394|395|396))|423|424|396|385)|448)|291|292)(1:462)|293|(10:296|(3:300|(4:303|(5:305|306|(1:308)(1:312)|309|310)(1:313)|311|301)|314)|315|(3:319|(4:322|(3:327|328|329)|330|320)|333)|334|(3:336|(6:339|(2:341|(3:343|344|345))(1:348)|346|347|345|337)|349)|350|(3:359|(8:362|(1:364)|365|(1:367)|368|(3:370|371|372)(1:374)|373|360)|375)|376|294)|382|383)|47|(3:175|(4:178|(10:180|181|(1:183)(1:271)|184|(9:186|187|188|189|190|191|193|194|(4:196|(11:197|198|199|200|201|202|203|(3:205|206|207)(1:248)|208|209|(1:212)(1:211))|213|214)(4:254|255|247|214))(1:270)|215|(4:218|(3:236|237|238)(4:220|221|(2:222|(2:224|(1:226)(2:227|228))(1:235))|(3:230|231|232)(1:234))|233|216)|239|240|241)(1:272)|242|176)|273)|49|50|(3:77|(6:80|(6:82|83|84|85|86|(3:(9:88|89|90|91|92|(1:94)(1:151)|95|96|(1:99)(1:98))|100|101)(4:158|159|150|101))(1:173)|102|(2:103|(2:105|(3:141|142|143)(6:107|(2:108|(4:110|(3:112|(1:114)(1:137)|115)(1:138)|116|(1:1)(2:120|(1:122)(2:123|124)))(2:139|140))|(2:129|128)|126|127|128))(0))|144|78)|174)|52|53|(9:56|57|58|59|60|61|(2:63|64)(1:66)|65|54)|74|75)(2:511|512))|39|40|(0)(0)|44|(0)(0)|47|(0)|49|50|(0)|52|53|(1:54)|74|75) */
    /* JADX WARN: Can't wrap try/catch for region: R(26:1|(2:2|(2:4|(2:6|7))(2:526|527))|8|(3:10|11|12)|16|(6:19|20|21|22|23|(20:(7:25|26|27|28|(1:30)(3:503|(1:505)(1:507)|506)|31|(1:34)(1:33))|35|36|37|38|39|40|(2:42|43)(3:463|(6:464|465|466|467|468|(1:471)(1:470))|472)|44|(1:46)(6:274|(11:276|277|278|279|280|(2:(3:282|(1:284)|285)|287)(1:449)|288|289|(3:384|(6:387|(1:446)(2:391|(4:397|398|(7:400|(4:403|(2:405|406)(1:408)|407|401)|409|410|(4:413|(3:415|416|417)(1:419)|418|411)|420|421)(6:425|(4:428|(2:430|431)(1:433)|432|426)|434|435|(4:438|(2:440|441)(1:443)|442|436)|444)|422)(4:393|394|395|396))|423|424|396|385)|448)|291|292)(1:462)|293|(10:296|(3:300|(4:303|(5:305|306|(1:308)(1:312)|309|310)(1:313)|311|301)|314)|315|(3:319|(4:322|(3:327|328|329)|330|320)|333)|334|(3:336|(6:339|(2:341|(3:343|344|345))(1:348)|346|347|345|337)|349)|350|(3:359|(8:362|(1:364)|365|(1:367)|368|(3:370|371|372)(1:374)|373|360)|375)|376|294)|382|383)|47|(3:175|(4:178|(10:180|181|(1:183)(1:271)|184|(9:186|187|188|189|190|191|193|194|(4:196|(11:197|198|199|200|201|202|203|(3:205|206|207)(1:248)|208|209|(1:212)(1:211))|213|214)(4:254|255|247|214))(1:270)|215|(4:218|(3:236|237|238)(4:220|221|(2:222|(2:224|(1:226)(2:227|228))(1:235))|(3:230|231|232)(1:234))|233|216)|239|240|241)(1:272)|242|176)|273)|49|50|(3:77|(6:80|(6:82|83|84|85|86|(3:(9:88|89|90|91|92|(1:94)(1:151)|95|96|(1:99)(1:98))|100|101)(4:158|159|150|101))(1:173)|102|(2:103|(2:105|(3:141|142|143)(6:107|(2:108|(4:110|(3:112|(1:114)(1:137)|115)(1:138)|116|(1:1)(2:120|(1:122)(2:123|124)))(2:139|140))|(2:129|128)|126|127|128))(0))|144|78)|174)|52|53|(9:56|57|58|59|60|61|(2:63|64)(1:66)|65|54)|74|75)(2:511|512))|525|36|37|38|39|40|(0)(0)|44|(0)(0)|47|(0)|49|50|(0)|52|53|(1:54)|74|75|(5:(0)|(0)|(0)|(0)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02ef, code lost:
        if (r5 == null) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x07b6, code lost:
        if (r5 != null) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x0959, code lost:
        if (r13 == null) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x0a59, code lost:
        r0 = r64.f6809a.zzay().zzk();
        r6 = com.google.android.gms.measurement.internal.zzfa.g(r64.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x0a6d, code lost:
        if (r7.zzj() == false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x0a6f, code lost:
        r7 = java.lang.Integer.valueOf(r7.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x0a78, code lost:
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x0a79, code lost:
        r0.zzc("Invalid property filter ID. appId, id", r6, java.lang.String.valueOf(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0171, code lost:
        if (r5 == null) goto L525;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0220, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0221, code lost:
        r18 = "audience_id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0226, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0227, code lost:
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x022a, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x022b, code lost:
        r18 = "audience_id";
        r19 = com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE;
        r4 = null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x086c  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0ab6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01b0 A[Catch: SQLiteException -> 0x0220, all -> 0x0b46, TRY_LEAVE, TryCatch #7 {SQLiteException -> 0x0220, blocks: (B:58:0x01aa, B:60:0x01b0, B:62:0x01be, B:63:0x01c3, B:64:0x01cd, B:65:0x01dd, B:67:0x01ec), top: B:432:0x01aa }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01be A[Catch: SQLiteException -> 0x0220, all -> 0x0b46, TRY_ENTER, TryCatch #7 {SQLiteException -> 0x0220, blocks: (B:58:0x01aa, B:60:0x01b0, B:62:0x01be, B:63:0x01c3, B:64:0x01cd, B:65:0x01dd, B:67:0x01ec), top: B:432:0x01aa }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x025c  */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v59, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v60 */
    /* JADX WARN: Type inference failed for: r5v61, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.database.Cursor] */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List d(String str, List list, List list2, Long l2, Long l3) {
        int i2;
        int i3;
        boolean z;
        Cursor cursor;
        ArrayMap arrayMap;
        String str2;
        String str3;
        Cursor cursor2;
        ArrayMap arrayMap2;
        String str4;
        ArrayMap arrayMap3;
        String str5;
        String str6;
        String str7;
        String str8;
        List<com.google.android.gms.internal.measurement.zzej> list3;
        String str9;
        Cursor cursor3;
        Map map;
        Iterator it;
        String str10;
        zzas zzasVar;
        zzw zzwVar;
        Iterator it2;
        zzas zzasVar2;
        String str11;
        Cursor cursor4;
        List list4;
        Iterator it3;
        String str12;
        String str13;
        Map map2;
        Cursor cursor5;
        Cursor cursor6;
        List list5;
        ArrayMap arrayMap4;
        Cursor cursor7;
        List list6;
        String str14 = "current_results";
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zza = str;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = l2;
        this.zze = l3;
        Iterator it4 = list.iterator();
        while (true) {
            i2 = 0;
            i3 = 1;
            if (it4.hasNext()) {
                if ("_s".equals(((com.google.android.gms.internal.measurement.zzfs) it4.next()).zzh())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        zzoi.zzc();
        boolean zzs = this.f6809a.zzf().zzs(this.zza, zzen.zzX);
        zzoi.zzc();
        boolean zzs2 = this.f6809a.zzf().zzs(this.zza, zzen.zzW);
        if (z) {
            zzam zzi = this.f7018b.zzi();
            String str15 = this.zza;
            zzi.a();
            zzi.zzg();
            Preconditions.checkNotEmpty(str15);
            ContentValues contentValues = new ContentValues();
            ?? r5 = "current_session_count";
            contentValues.put("current_session_count", (Integer) 0);
            try {
                r5 = new String[]{str15};
                zzi.p().update("events", contentValues, "app_id = ?", r5);
                cursor = r5;
            } catch (SQLiteException e2) {
                zzi.f6809a.zzay().zzd().zzc("Error resetting session-scoped event counts. appId", zzfa.g(str15), e2);
                cursor = r5;
            }
        }
        Map emptyMap = Collections.emptyMap();
        String str16 = "Failed to merge filter. appId";
        String str17 = "Database error querying filters. appId";
        String str18 = Constants.ScionAnalytics.MessageType.DATA_MESSAGE;
        String str19 = "audience_id";
        try {
            if (zzs2 && zzs) {
                zzam zzi2 = this.f7018b.zzi();
                String str20 = this.zza;
                Preconditions.checkNotEmpty(str20);
                ArrayMap arrayMap5 = new ArrayMap();
                try {
                    try {
                        cursor7 = zzi2.p().query("event_filters", new String[]{"audience_id", Constants.ScionAnalytics.MessageType.DATA_MESSAGE}, "app_id=?", new String[]{str20}, null, null, null);
                        try {
                        } catch (SQLiteException e3) {
                            e = e3;
                            zzi2.f6809a.zzay().zzd().zzc("Database error querying filters. appId", zzfa.g(str20), e);
                            emptyMap = Collections.emptyMap();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (SQLiteException e4) {
                    e = e4;
                    cursor7 = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    if (cursor != null) {
                    }
                    throw th;
                }
                if (cursor7.moveToFirst()) {
                    while (true) {
                        try {
                            com.google.android.gms.internal.measurement.zzej zzejVar = (com.google.android.gms.internal.measurement.zzej) ((com.google.android.gms.internal.measurement.zzei) zzln.m(com.google.android.gms.internal.measurement.zzej.zzc(), cursor7.getBlob(i3))).zzaE();
                            if (zzejVar.zzo()) {
                                Integer valueOf = Integer.valueOf(cursor7.getInt(i2));
                                List list7 = (List) arrayMap5.get(valueOf);
                                if (list7 == null) {
                                    list6 = new ArrayList();
                                    arrayMap5.put(valueOf, list6);
                                } else {
                                    list6 = list7;
                                }
                                list6.add(zzejVar);
                            }
                        } catch (IOException e5) {
                            zzi2.f6809a.zzay().zzd().zzc("Failed to merge filter. appId", zzfa.g(str20), e5);
                        }
                        if (!cursor7.moveToNext()) {
                            break;
                        }
                        i2 = 0;
                        i3 = 1;
                    }
                    cursor7.close();
                    arrayMap = arrayMap5;
                    zzam zzi3 = this.f7018b.zzi();
                    String str21 = this.zza;
                    zzi3.a();
                    zzi3.zzg();
                    Preconditions.checkNotEmpty(str21);
                    cursor2 = zzi3.p().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str21}, null, null, null);
                    if (cursor2.moveToFirst()) {
                        Map emptyMap2 = Collections.emptyMap();
                        cursor2.close();
                        arrayMap2 = emptyMap2;
                        str2 = "audience_id";
                        str3 = Constants.ScionAnalytics.MessageType.DATA_MESSAGE;
                    } else {
                        ArrayMap arrayMap6 = new ArrayMap();
                        while (true) {
                            int i4 = cursor2.getInt(0);
                            try {
                                arrayMap6.put(Integer.valueOf(i4), (com.google.android.gms.internal.measurement.zzgh) ((com.google.android.gms.internal.measurement.zzgg) zzln.m(com.google.android.gms.internal.measurement.zzgh.zzf(), cursor2.getBlob(1))).zzaE());
                                arrayMap4 = arrayMap6;
                                str2 = str19;
                                str3 = str18;
                            } catch (IOException e6) {
                                arrayMap4 = arrayMap6;
                                str2 = str19;
                                try {
                                    str3 = str18;
                                    try {
                                        zzi3.f6809a.zzay().zzd().zzd("Failed to merge filter results. appId, audienceId, error", zzfa.g(str21), Integer.valueOf(i4), e6);
                                    } catch (SQLiteException e7) {
                                        e = e7;
                                        zzi3.f6809a.zzay().zzd().zzc("Database error querying filter results. appId", zzfa.g(str21), e);
                                        Map emptyMap3 = Collections.emptyMap();
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        arrayMap2 = emptyMap3;
                                        if (arrayMap2.isEmpty()) {
                                        }
                                        if (!list.isEmpty()) {
                                        }
                                        String str22 = str14;
                                        if (!list2.isEmpty()) {
                                        }
                                        String str23 = str6;
                                        ArrayList arrayList = new ArrayList();
                                        Set<Integer> keySet = this.zzc.keySet();
                                        keySet.removeAll(this.zzb);
                                        while (r2.hasNext()) {
                                        }
                                        return arrayList;
                                    }
                                } catch (SQLiteException e8) {
                                    e = e8;
                                    str3 = str18;
                                    zzi3.f6809a.zzay().zzd().zzc("Database error querying filter results. appId", zzfa.g(str21), e);
                                    Map emptyMap32 = Collections.emptyMap();
                                    if (cursor2 != null) {
                                    }
                                    arrayMap2 = emptyMap32;
                                    if (arrayMap2.isEmpty()) {
                                    }
                                    if (!list.isEmpty()) {
                                    }
                                    String str222 = str14;
                                    if (!list2.isEmpty()) {
                                    }
                                    String str232 = str6;
                                    ArrayList arrayList2 = new ArrayList();
                                    Set<Integer> keySet2 = this.zzc.keySet();
                                    keySet2.removeAll(this.zzb);
                                    while (r2.hasNext()) {
                                    }
                                    return arrayList2;
                                }
                            }
                            if (!cursor2.moveToNext()) {
                                break;
                            }
                            arrayMap6 = arrayMap4;
                            str19 = str2;
                            str18 = str3;
                        }
                        cursor2.close();
                        arrayMap2 = arrayMap4;
                    }
                    if (arrayMap2.isEmpty()) {
                        HashSet<Integer> hashSet = new HashSet(arrayMap2.keySet());
                        if (z) {
                            String str24 = this.zza;
                            zzam zzi4 = this.f7018b.zzi();
                            String str25 = this.zza;
                            zzi4.a();
                            zzi4.zzg();
                            Preconditions.checkNotEmpty(str25);
                            Map arrayMap7 = new ArrayMap();
                            ?? p2 = zzi4.p();
                            try {
                                try {
                                    cursor3 = p2.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str25, str25});
                                    try {
                                        if (cursor3.moveToFirst()) {
                                            do {
                                                Integer valueOf2 = Integer.valueOf(cursor3.getInt(0));
                                                List list8 = (List) arrayMap7.get(valueOf2);
                                                if (list8 == null) {
                                                    list8 = new ArrayList();
                                                    arrayMap7.put(valueOf2, list8);
                                                }
                                                list8.add(Integer.valueOf(cursor3.getInt(1)));
                                            } while (cursor3.moveToNext());
                                        } else {
                                            arrayMap7 = Collections.emptyMap();
                                        }
                                    } catch (SQLiteException e9) {
                                        e = e9;
                                        zzi4.f6809a.zzay().zzd().zzc("Database error querying scoped filters. appId", zzfa.g(str25), e);
                                        arrayMap7 = Collections.emptyMap();
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    if (p2 != 0) {
                                        p2.close();
                                    }
                                    throw th;
                                }
                            } catch (SQLiteException e10) {
                                e = e10;
                                cursor3 = null;
                            } catch (Throwable th4) {
                                th = th4;
                                p2 = 0;
                                if (p2 != 0) {
                                }
                                throw th;
                            }
                            cursor3.close();
                            Preconditions.checkNotEmpty(str24);
                            Preconditions.checkNotNull(arrayMap2);
                            ArrayMap arrayMap8 = new ArrayMap();
                            if (!arrayMap2.isEmpty()) {
                                Iterator it5 = arrayMap2.keySet().iterator();
                                while (it5.hasNext()) {
                                    int intValue = ((Integer) it5.next()).intValue();
                                    Integer valueOf3 = Integer.valueOf(intValue);
                                    com.google.android.gms.internal.measurement.zzgh zzghVar = (com.google.android.gms.internal.measurement.zzgh) arrayMap2.get(valueOf3);
                                    List list9 = (List) arrayMap7.get(valueOf3);
                                    if (list9 == null || list9.isEmpty()) {
                                        map = arrayMap7;
                                        it = it5;
                                        str10 = str17;
                                        arrayMap8.put(valueOf3, zzghVar);
                                    } else {
                                        map = arrayMap7;
                                        List q2 = this.f7018b.zzu().q(zzghVar.zzk(), list9);
                                        if (q2.isEmpty()) {
                                            arrayMap7 = map;
                                        } else {
                                            com.google.android.gms.internal.measurement.zzgg zzggVar = (com.google.android.gms.internal.measurement.zzgg) zzghVar.zzbB();
                                            zzggVar.zzf();
                                            zzggVar.zzb(q2);
                                            it = it5;
                                            List q3 = this.f7018b.zzu().q(zzghVar.zzn(), list9);
                                            zzggVar.zzh();
                                            zzggVar.zzd(q3);
                                            zzol.zzc();
                                            str10 = str17;
                                            if (this.f6809a.zzf().zzs(null, zzen.zzaO)) {
                                                ArrayList arrayList3 = new ArrayList();
                                                Iterator it6 = zzghVar.zzj().iterator();
                                                while (it6.hasNext()) {
                                                    com.google.android.gms.internal.measurement.zzfq zzfqVar = (com.google.android.gms.internal.measurement.zzfq) it6.next();
                                                    Iterator it7 = it6;
                                                    if (!list9.contains(Integer.valueOf(zzfqVar.zza()))) {
                                                        arrayList3.add(zzfqVar);
                                                    }
                                                    it6 = it7;
                                                }
                                                zzggVar.zze();
                                                zzggVar.zza(arrayList3);
                                                ArrayList arrayList4 = new ArrayList();
                                                for (com.google.android.gms.internal.measurement.zzgj zzgjVar : zzghVar.zzm()) {
                                                    if (!list9.contains(Integer.valueOf(zzgjVar.zzb()))) {
                                                        arrayList4.add(zzgjVar);
                                                    }
                                                }
                                                zzggVar.zzg();
                                                zzggVar.zzc(arrayList4);
                                            } else {
                                                for (int i5 = 0; i5 < zzghVar.zza(); i5++) {
                                                    if (list9.contains(Integer.valueOf(zzghVar.zze(i5).zza()))) {
                                                        zzggVar.zzi(i5);
                                                    }
                                                }
                                                for (int i6 = 0; i6 < zzghVar.zzc(); i6++) {
                                                    if (list9.contains(Integer.valueOf(zzghVar.zzi(i6).zzb()))) {
                                                        zzggVar.zzj(i6);
                                                    }
                                                }
                                            }
                                            arrayMap8.put(Integer.valueOf(intValue), (com.google.android.gms.internal.measurement.zzgh) zzggVar.zzaE());
                                        }
                                    }
                                    arrayMap7 = map;
                                    it5 = it;
                                    str17 = str10;
                                }
                            }
                            str4 = str17;
                            arrayMap3 = arrayMap8;
                        } else {
                            str4 = "Database error querying filters. appId";
                            arrayMap3 = arrayMap2;
                        }
                        for (Integer num : hashSet) {
                            int intValue2 = num.intValue();
                            com.google.android.gms.internal.measurement.zzgh zzghVar2 = (com.google.android.gms.internal.measurement.zzgh) arrayMap3.get(Integer.valueOf(intValue2));
                            BitSet bitSet = new BitSet();
                            BitSet bitSet2 = new BitSet();
                            ArrayMap arrayMap9 = new ArrayMap();
                            if (zzghVar2 != null && zzghVar2.zza() != 0) {
                                for (com.google.android.gms.internal.measurement.zzfq zzfqVar2 : zzghVar2.zzj()) {
                                    if (zzfqVar2.zzh()) {
                                        arrayMap9.put(Integer.valueOf(zzfqVar2.zza()), zzfqVar2.zzg() ? Long.valueOf(zzfqVar2.zzb()) : null);
                                    }
                                }
                            }
                            ArrayMap arrayMap10 = new ArrayMap();
                            if (zzghVar2 != null && zzghVar2.zzc() != 0) {
                                for (com.google.android.gms.internal.measurement.zzgj zzgjVar2 : zzghVar2.zzm()) {
                                    if (zzgjVar2.zzi() && zzgjVar2.zza() > 0) {
                                        arrayMap10.put(Integer.valueOf(zzgjVar2.zzb()), Long.valueOf(zzgjVar2.zzc(zzgjVar2.zza() - 1)));
                                        arrayMap3 = arrayMap3;
                                    }
                                }
                            }
                            Map map3 = arrayMap3;
                            if (zzghVar2 != null) {
                                int i7 = 0;
                                while (i7 < zzghVar2.zzd() * 64) {
                                    if (zzln.v(zzghVar2.zzn(), i7)) {
                                        str9 = str16;
                                        this.f6809a.zzay().zzj().zzc("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue2), Integer.valueOf(i7));
                                        bitSet2.set(i7);
                                        if (zzln.v(zzghVar2.zzk(), i7)) {
                                            bitSet.set(i7);
                                            i7++;
                                            str16 = str9;
                                        }
                                    } else {
                                        str9 = str16;
                                    }
                                    arrayMap9.remove(Integer.valueOf(i7));
                                    i7++;
                                    str16 = str9;
                                }
                            }
                            String str26 = str16;
                            Integer valueOf4 = Integer.valueOf(intValue2);
                            com.google.android.gms.internal.measurement.zzgh zzghVar3 = (com.google.android.gms.internal.measurement.zzgh) arrayMap2.get(valueOf4);
                            if (zzs2 && zzs && (list3 = (List) arrayMap.get(valueOf4)) != null && this.zze != null && this.zzd != null) {
                                for (com.google.android.gms.internal.measurement.zzej zzejVar2 : list3) {
                                    int zzb = zzejVar2.zzb();
                                    long longValue = this.zze.longValue() / 1000;
                                    if (zzejVar2.zzm()) {
                                        longValue = this.zzd.longValue() / 1000;
                                    }
                                    Integer valueOf5 = Integer.valueOf(zzb);
                                    if (arrayMap9.containsKey(valueOf5)) {
                                        arrayMap9.put(valueOf5, Long.valueOf(longValue));
                                    }
                                    if (arrayMap10.containsKey(valueOf5)) {
                                        arrayMap10.put(valueOf5, Long.valueOf(longValue));
                                    }
                                }
                            }
                            this.zzc.put(Integer.valueOf(intValue2), new zzu(this, this.zza, zzghVar3, bitSet, bitSet2, arrayMap9, arrayMap10, null));
                            str16 = str26;
                            arrayMap = arrayMap;
                            arrayMap3 = map3;
                            arrayMap2 = arrayMap2;
                        }
                        str5 = str16;
                        str6 = str2;
                        str7 = str3;
                        str8 = str4;
                    } else {
                        str8 = "Database error querying filters. appId";
                        str5 = "Failed to merge filter. appId";
                        str6 = str2;
                        str7 = str3;
                    }
                    if (!list.isEmpty()) {
                        zzw zzwVar2 = new zzw(this, null);
                        ArrayMap arrayMap11 = new ArrayMap();
                        Iterator it8 = list.iterator();
                        while (it8.hasNext()) {
                            com.google.android.gms.internal.measurement.zzfs zzfsVar = (com.google.android.gms.internal.measurement.zzfs) it8.next();
                            com.google.android.gms.internal.measurement.zzfs a2 = zzwVar2.a(this.zza, zzfsVar);
                            if (a2 != null) {
                                zzam zzi5 = this.f7018b.zzi();
                                String str27 = this.zza;
                                String zzh = a2.zzh();
                                zzas zzn = zzi5.zzn(str27, zzfsVar.zzh());
                                if (zzn == null) {
                                    zzi5.f6809a.zzay().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzfa.g(str27), zzi5.f6809a.zzj().d(zzh));
                                    zzasVar = new zzas(str27, zzfsVar.zzh(), 1L, 1L, 1L, zzfsVar.zzd(), 0L, null, null, null, null);
                                } else {
                                    zzasVar = new zzas(zzn.f6698a, zzn.f6699b, zzn.f6700c + 1, zzn.f6701d + 1, zzn.f6702e + 1, zzn.f6703f, zzn.f6704g, zzn.f6705h, zzn.f6706i, zzn.f6707j, zzn.f6708k);
                                }
                                this.f7018b.zzi().zzE(zzasVar);
                                long j2 = zzasVar.f6700c;
                                String zzh2 = a2.zzh();
                                Map map4 = (Map) arrayMap11.get(zzh2);
                                if (map4 == null) {
                                    zzam zzi6 = this.f7018b.zzi();
                                    String str28 = this.zza;
                                    zzi6.a();
                                    zzi6.zzg();
                                    Preconditions.checkNotEmpty(str28);
                                    Preconditions.checkNotEmpty(zzh2);
                                    zzwVar = zzwVar2;
                                    ArrayMap arrayMap12 = new ArrayMap();
                                    it2 = it8;
                                    str11 = str14;
                                    String str29 = str6;
                                    String str30 = str7;
                                    try {
                                        try {
                                            str7 = str30;
                                        } catch (SQLiteException e11) {
                                            e = e11;
                                            str7 = str30;
                                        }
                                        try {
                                            cursor4 = zzi6.p().query("event_filters", new String[]{str29, str30}, "app_id=? AND event_name=?", new String[]{str28, zzh2}, null, null, null);
                                            try {
                                                try {
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    if (cursor4 != null) {
                                                        cursor4.close();
                                                    }
                                                    throw th;
                                                }
                                            } catch (SQLiteException e12) {
                                                e = e12;
                                                zzasVar2 = zzasVar;
                                                str6 = str29;
                                            }
                                        } catch (SQLiteException e13) {
                                            e = e13;
                                            zzasVar2 = zzasVar;
                                            str6 = str29;
                                            cursor4 = null;
                                            zzi6.f6809a.zzay().zzd().zzc(str8, zzfa.g(str28), e);
                                            map4 = Collections.emptyMap();
                                        }
                                        if (cursor4.moveToFirst()) {
                                            str6 = str29;
                                            while (true) {
                                                try {
                                                    try {
                                                        com.google.android.gms.internal.measurement.zzej zzejVar3 = (com.google.android.gms.internal.measurement.zzej) ((com.google.android.gms.internal.measurement.zzei) zzln.m(com.google.android.gms.internal.measurement.zzej.zzc(), cursor4.getBlob(1))).zzaE();
                                                        Integer valueOf6 = Integer.valueOf(cursor4.getInt(0));
                                                        List list10 = (List) arrayMap12.get(valueOf6);
                                                        if (list10 == null) {
                                                            zzasVar2 = zzasVar;
                                                            try {
                                                                list4 = new ArrayList();
                                                                arrayMap12.put(valueOf6, list4);
                                                            } catch (SQLiteException e14) {
                                                                e = e14;
                                                                zzi6.f6809a.zzay().zzd().zzc(str8, zzfa.g(str28), e);
                                                                map4 = Collections.emptyMap();
                                                            }
                                                        } else {
                                                            zzasVar2 = zzasVar;
                                                            list4 = list10;
                                                        }
                                                        list4.add(zzejVar3);
                                                    } catch (IOException e15) {
                                                        zzasVar2 = zzasVar;
                                                        zzi6.f6809a.zzay().zzd().zzc(str5, zzfa.g(str28), e15);
                                                    }
                                                    if (!cursor4.moveToNext()) {
                                                        break;
                                                    }
                                                    zzasVar = zzasVar2;
                                                } catch (SQLiteException e16) {
                                                    e = e16;
                                                    zzasVar2 = zzasVar;
                                                }
                                            }
                                            cursor4.close();
                                            map4 = arrayMap12;
                                            arrayMap11.put(zzh2, map4);
                                        } else {
                                            zzasVar2 = zzasVar;
                                            str6 = str29;
                                            map4 = Collections.emptyMap();
                                            cursor4.close();
                                            arrayMap11.put(zzh2, map4);
                                        }
                                    } catch (Throwable th6) {
                                        th = th6;
                                        cursor4 = null;
                                    }
                                } else {
                                    zzwVar = zzwVar2;
                                    it2 = it8;
                                    zzasVar2 = zzasVar;
                                    str11 = str14;
                                }
                                for (Integer num2 : map4.keySet()) {
                                    int intValue3 = num2.intValue();
                                    Set set = this.zzb;
                                    Integer valueOf7 = Integer.valueOf(intValue3);
                                    if (set.contains(valueOf7)) {
                                        this.f6809a.zzay().zzj().zzb("Skipping failed audience ID", valueOf7);
                                    } else {
                                        Iterator it9 = ((List) map4.get(valueOf7)).iterator();
                                        boolean z2 = true;
                                        while (true) {
                                            if (!it9.hasNext()) {
                                                break;
                                            }
                                            com.google.android.gms.internal.measurement.zzej zzejVar4 = (com.google.android.gms.internal.measurement.zzej) it9.next();
                                            zzx zzxVar = new zzx(this, this.zza, intValue3, zzejVar4);
                                            z2 = zzxVar.j(this.zzd, this.zze, a2, j2, zzasVar2, zzf(intValue3, zzejVar4.zzb()));
                                            if (!z2) {
                                                this.zzb.add(Integer.valueOf(intValue3));
                                                break;
                                            }
                                            zzd(Integer.valueOf(intValue3)).c(zzxVar);
                                        }
                                        if (!z2) {
                                            this.zzb.add(Integer.valueOf(intValue3));
                                        }
                                    }
                                }
                                zzwVar2 = zzwVar;
                                it8 = it2;
                                str14 = str11;
                            }
                        }
                    }
                    String str2222 = str14;
                    if (!list2.isEmpty()) {
                        ArrayMap arrayMap13 = new ArrayMap();
                        Iterator it10 = list2.iterator();
                        while (it10.hasNext()) {
                            com.google.android.gms.internal.measurement.zzgl zzglVar = (com.google.android.gms.internal.measurement.zzgl) it10.next();
                            String zzf = zzglVar.zzf();
                            Map map5 = (Map) arrayMap13.get(zzf);
                            if (map5 == null) {
                                zzam zzi7 = this.f7018b.zzi();
                                String str31 = this.zza;
                                zzi7.a();
                                zzi7.zzg();
                                Preconditions.checkNotEmpty(str31);
                                Preconditions.checkNotEmpty(zzf);
                                ArrayMap arrayMap14 = new ArrayMap();
                                str12 = str6;
                                str13 = str7;
                                try {
                                    cursor6 = zzi7.p().query("property_filters", new String[]{str12, str13}, "app_id=? AND property_name=?", new String[]{str31, zzf}, null, null, null);
                                    try {
                                        try {
                                        } catch (Throwable th7) {
                                            th = th7;
                                            cursor5 = cursor6;
                                            if (cursor5 != null) {
                                                cursor5.close();
                                            }
                                            throw th;
                                        }
                                    } catch (SQLiteException e17) {
                                        e = e17;
                                        it3 = it10;
                                    }
                                } catch (SQLiteException e18) {
                                    e = e18;
                                    it3 = it10;
                                    cursor6 = null;
                                } catch (Throwable th8) {
                                    th = th8;
                                    cursor5 = null;
                                }
                                if (cursor6.moveToFirst()) {
                                    while (true) {
                                        try {
                                            com.google.android.gms.internal.measurement.zzes zzesVar = (com.google.android.gms.internal.measurement.zzes) ((com.google.android.gms.internal.measurement.zzer) zzln.m(com.google.android.gms.internal.measurement.zzes.zzc(), cursor6.getBlob(1))).zzaE();
                                            Integer valueOf8 = Integer.valueOf(cursor6.getInt(0));
                                            List list11 = (List) arrayMap14.get(valueOf8);
                                            if (list11 == null) {
                                                list5 = new ArrayList();
                                                arrayMap14.put(valueOf8, list5);
                                            } else {
                                                list5 = list11;
                                            }
                                            list5.add(zzesVar);
                                            it3 = it10;
                                        } catch (IOException e19) {
                                            it3 = it10;
                                            try {
                                                zzi7.f6809a.zzay().zzd().zzc("Failed to merge filter", zzfa.g(str31), e19);
                                            } catch (SQLiteException e20) {
                                                e = e20;
                                                zzi7.f6809a.zzay().zzd().zzc(str8, zzfa.g(str31), e);
                                                map5 = Collections.emptyMap();
                                            }
                                        }
                                        if (!cursor6.moveToNext()) {
                                            break;
                                        }
                                        it10 = it3;
                                    }
                                    cursor6.close();
                                    map5 = arrayMap14;
                                    arrayMap13.put(zzf, map5);
                                } else {
                                    it3 = it10;
                                    map5 = Collections.emptyMap();
                                    cursor6.close();
                                    arrayMap13.put(zzf, map5);
                                }
                            } else {
                                it3 = it10;
                                str12 = str6;
                                str13 = str7;
                            }
                            Iterator it11 = map5.keySet().iterator();
                            while (true) {
                                if (it11.hasNext()) {
                                    int intValue4 = ((Integer) it11.next()).intValue();
                                    Set set2 = this.zzb;
                                    Integer valueOf9 = Integer.valueOf(intValue4);
                                    if (set2.contains(valueOf9)) {
                                        this.f6809a.zzay().zzj().zzb("Skipping failed audience ID", valueOf9);
                                        break;
                                    }
                                    Iterator it12 = ((List) map5.get(valueOf9)).iterator();
                                    boolean z3 = true;
                                    while (true) {
                                        if (!it12.hasNext()) {
                                            map2 = map5;
                                            break;
                                        }
                                        com.google.android.gms.internal.measurement.zzes zzesVar2 = (com.google.android.gms.internal.measurement.zzes) it12.next();
                                        if (Log.isLoggable(this.f6809a.zzay().zzq(), 2)) {
                                            map2 = map5;
                                            this.f6809a.zzay().zzj().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue4), zzesVar2.zzj() ? Integer.valueOf(zzesVar2.zza()) : null, this.f6809a.zzj().f(zzesVar2.zze()));
                                            this.f6809a.zzay().zzj().zzb("Filter definition", this.f7018b.zzu().p(zzesVar2));
                                        } else {
                                            map2 = map5;
                                        }
                                        if (!zzesVar2.zzj() || zzesVar2.zza() > 256) {
                                            break;
                                        }
                                        zzz zzzVar = new zzz(this, this.zza, intValue4, zzesVar2);
                                        z3 = zzzVar.j(this.zzd, this.zze, zzglVar, zzf(intValue4, zzesVar2.zza()));
                                        if (!z3) {
                                            this.zzb.add(Integer.valueOf(intValue4));
                                            break;
                                        }
                                        zzd(Integer.valueOf(intValue4)).c(zzzVar);
                                        map5 = map2;
                                    }
                                    if (z3) {
                                        map5 = map2;
                                    }
                                    this.zzb.add(Integer.valueOf(intValue4));
                                    map5 = map2;
                                }
                            }
                            it10 = it3;
                            str7 = str13;
                            str6 = str12;
                        }
                    }
                    String str2322 = str6;
                    ArrayList arrayList22 = new ArrayList();
                    Set<Integer> keySet22 = this.zzc.keySet();
                    keySet22.removeAll(this.zzb);
                    for (Integer num3 : keySet22) {
                        int intValue5 = num3.intValue();
                        Map map6 = this.zzc;
                        Integer valueOf10 = Integer.valueOf(intValue5);
                        zzu zzuVar = (zzu) map6.get(valueOf10);
                        Preconditions.checkNotNull(zzuVar);
                        com.google.android.gms.internal.measurement.zzfo a3 = zzuVar.a(intValue5);
                        arrayList22.add(a3);
                        zzam zzi8 = this.f7018b.zzi();
                        String str32 = this.zza;
                        com.google.android.gms.internal.measurement.zzgh zzd = a3.zzd();
                        zzi8.a();
                        zzi8.zzg();
                        Preconditions.checkNotEmpty(str32);
                        Preconditions.checkNotNull(zzd);
                        byte[] zzby = zzd.zzby();
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("app_id", str32);
                        contentValues2.put(str2322, valueOf10);
                        String str33 = str2222;
                        contentValues2.put(str33, zzby);
                        try {
                            try {
                                if (zzi8.p().insertWithOnConflict("audience_filter_values", null, contentValues2, 5) == -1) {
                                    zzi8.f6809a.zzay().zzd().zzb("Failed to insert filter results (got -1). appId", zzfa.g(str32));
                                }
                            } catch (SQLiteException e21) {
                                e = e21;
                                zzi8.f6809a.zzay().zzd().zzc("Error storing filter results. appId", zzfa.g(str32), e);
                                str2222 = str33;
                            }
                        } catch (SQLiteException e22) {
                            e = e22;
                        }
                        str2222 = str33;
                    }
                    return arrayList22;
                }
                emptyMap = Collections.emptyMap();
                cursor7.close();
            }
            if (cursor2.moveToFirst()) {
            }
            if (arrayMap2.isEmpty()) {
            }
            if (!list.isEmpty()) {
            }
            String str22222 = str14;
            if (!list2.isEmpty()) {
            }
            String str23222 = str6;
            ArrayList arrayList222 = new ArrayList();
            Set<Integer> keySet222 = this.zzc.keySet();
            keySet222.removeAll(this.zzb);
            while (r2.hasNext()) {
            }
            return arrayList222;
        } catch (Throwable th9) {
            th = th9;
            Cursor cursor8 = cursor2;
            if (cursor8 != null) {
                cursor8.close();
            }
            throw th;
        }
        arrayMap = emptyMap;
        zzam zzi32 = this.f7018b.zzi();
        String str212 = this.zza;
        zzi32.a();
        zzi32.zzg();
        Preconditions.checkNotEmpty(str212);
        cursor2 = zzi32.p().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str212}, null, null, null);
    }
}
