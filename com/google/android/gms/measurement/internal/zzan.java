package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
/* loaded from: classes2.dex */
public final class zzan {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0027, code lost:
        if (r0 == false) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e4  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a(zzfa zzfaVar, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) {
        Cursor cursor;
        String[] split;
        if (zzfaVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Cursor cursor2 = null;
        try {
            cursor = sQLiteDatabase.query("SQLITE_MASTER", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME}, "name=?", new String[]{str}, null, null, null);
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        try {
            try {
                boolean moveToFirst = cursor.moveToFirst();
                cursor.close();
            } catch (SQLiteException e3) {
                e = e3;
                zzfaVar.zzk().zzc("Error querying for table", str, e);
                if (cursor != null) {
                    cursor.close();
                }
                sQLiteDatabase.execSQL(str2);
                try {
                    HashSet hashSet = new HashSet();
                    Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
                    Collections.addAll(hashSet, rawQuery.getColumnNames());
                    rawQuery.close();
                    for (String str4 : str3.split(",")) {
                        if (!hashSet.remove(str4)) {
                            throw new SQLiteException("Table " + str + " is missing required column: " + str4);
                        }
                    }
                    if (strArr != null) {
                        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                            if (!hashSet.remove(strArr[i2])) {
                                sQLiteDatabase.execSQL(strArr[i2 + 1]);
                            }
                        }
                    }
                    if (hashSet.isEmpty()) {
                        return;
                    }
                    zzfaVar.zzk().zzc("Table has extra columns. table, columns", str, TextUtils.join(", ", hashSet));
                } catch (SQLiteException e4) {
                    zzfaVar.zzd().zzb("Failed to verify columns on table that was just created", str);
                    throw e4;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(zzfa zzfaVar, SQLiteDatabase sQLiteDatabase) {
        if (zzfaVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzfaVar.zzk().zza("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzfaVar.zzk().zza("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzfaVar.zzk().zza("Failed to turn on database read permission for owner");
        }
        if (file.setWritable(true, true)) {
            return;
        }
        zzfaVar.zzk().zza("Failed to turn on database write permission for owner");
    }
}
