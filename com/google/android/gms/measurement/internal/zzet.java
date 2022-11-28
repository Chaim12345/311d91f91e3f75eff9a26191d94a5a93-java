package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzet extends zzf {
    private final zzes zza;
    private boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzet(zzgk zzgkVar) {
        super(zzgkVar);
        Context zzau = this.f6809a.zzau();
        this.f6809a.zzf();
        this.zza = new zzes(this, zzau, "google_app_measurement_local.db");
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0129  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r2v13 */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zzq(int i2, byte[] bArr) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        zzg();
        ?? r2 = 0;
        if (this.zzb) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i2));
        contentValues.put("entry", bArr);
        this.f6809a.zzf();
        int i3 = 0;
        int i4 = 5;
        for (int i5 = 5; i3 < i5; i5 = 5) {
            Cursor cursor2 = null;
            cursor2 = null;
            cursor2 = null;
            r8 = null;
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                sQLiteDatabase = d();
                try {
                    if (sQLiteDatabase == null) {
                        this.zzb = true;
                        return r2;
                    }
                    sQLiteDatabase.beginTransaction();
                    cursor = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                    long j2 = 0;
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                j2 = cursor.getLong(r2);
                            }
                        } catch (SQLiteDatabaseLockedException unused) {
                            cursor2 = cursor;
                            try {
                                SystemClock.sleep(i4);
                                i4 += 20;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                i3++;
                                r2 = 0;
                            } catch (Throwable th) {
                                th = th;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteFullException e2) {
                            e = e2;
                            sQLiteDatabase2 = sQLiteDatabase;
                            this.f6809a.zzay().zzd().zzb("Error writing entry; local database full", e);
                            this.zzb = true;
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase2 == null) {
                                i3++;
                                r2 = 0;
                            }
                            sQLiteDatabase2.close();
                            i3++;
                            r2 = 0;
                        } catch (SQLiteException e3) {
                            e = e3;
                            sQLiteDatabase2 = sQLiteDatabase;
                            if (sQLiteDatabase2 != null) {
                                try {
                                    if (sQLiteDatabase2.inTransaction()) {
                                        sQLiteDatabase2.endTransaction();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    sQLiteDatabase = sQLiteDatabase2;
                                    cursor2 = cursor;
                                    if (cursor2 != null) {
                                    }
                                    if (sQLiteDatabase != null) {
                                    }
                                    throw th;
                                }
                            }
                            this.f6809a.zzay().zzd().zzb("Error writing entry to local database", e);
                            this.zzb = true;
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase2 == null) {
                                i3++;
                                r2 = 0;
                            }
                            sQLiteDatabase2.close();
                            i3++;
                            r2 = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    }
                    if (j2 >= 100000) {
                        this.f6809a.zzay().zzd().zza("Data loss, local db full");
                        long j3 = (100000 - j2) + 1;
                        String[] strArr = new String[1];
                        strArr[r2] = Long.toString(j3);
                        long delete = sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", strArr);
                        if (delete != j3) {
                            this.f6809a.zzay().zzd().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j3), Long.valueOf(delete), Long.valueOf(j3 - delete));
                        }
                    }
                    sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    if (cursor != 0) {
                        cursor.close();
                    }
                    sQLiteDatabase.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused2) {
                } catch (SQLiteFullException e4) {
                    e = e4;
                    cursor = null;
                } catch (SQLiteException e5) {
                    e = e5;
                    cursor = null;
                }
            } catch (SQLiteDatabaseLockedException unused3) {
                sQLiteDatabase = null;
            } catch (SQLiteFullException e6) {
                e = e6;
                cursor = null;
            } catch (SQLiteException e7) {
                e = e7;
                cursor = null;
            } catch (Throwable th4) {
                th = th4;
                sQLiteDatabase = null;
                if (cursor2 != null) {
                }
                if (sQLiteDatabase != null) {
                }
                throw th;
            }
        }
        this.f6809a.zzay().zzj().zza("Failed to write entry to local database");
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean c() {
        return false;
    }

    @VisibleForTesting
    @WorkerThread
    final SQLiteDatabase d() {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase == null) {
            this.zzb = true;
            return null;
        }
        return writableDatabase;
    }

    @VisibleForTesting
    final boolean e() {
        Context zzau = this.f6809a.zzau();
        this.f6809a.zzf();
        return zzau.getDatabasePath("google_app_measurement_local.db").exists();
    }

    /* JADX WARN: Removed duplicated region for block: B:144:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x01f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x01cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x023e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x023e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x023e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzi(int i2) {
        int i3;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor2;
        long j2;
        String str;
        String[] strArr;
        Parcel obtain;
        SafeParcelable safeParcelable;
        zzey zzd;
        String str2;
        zzg();
        Cursor cursor3 = null;
        if (this.zzb) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (e()) {
            int i4 = 5;
            for (i3 = 0; i3 < 5; i3 = i3 + 1) {
                try {
                    SQLiteDatabase d2 = d();
                    if (d2 == null) {
                        this.zzb = true;
                        return null;
                    }
                    try {
                        d2.beginTransaction();
                        try {
                            try {
                                cursor2 = d2.query("messages", new String[]{"rowid"}, "type=?", new String[]{ExifInterface.GPS_MEASUREMENT_3D}, null, null, "rowid desc", "1");
                                try {
                                    long j3 = -1;
                                    if (cursor2.moveToFirst()) {
                                        j2 = cursor2.getLong(0);
                                        try {
                                            cursor2.close();
                                        } catch (SQLiteDatabaseLockedException unused) {
                                            sQLiteDatabase2 = d2;
                                            cursor = null;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            SystemClock.sleep(i4);
                                            i4 += 20;
                                            if (cursor != null) {
                                            }
                                            if (sQLiteDatabase == null) {
                                            }
                                            sQLiteDatabase.close();
                                        } catch (SQLiteFullException e2) {
                                            e = e2;
                                            sQLiteDatabase2 = d2;
                                            cursor = null;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            this.f6809a.zzay().zzd().zzb("Error reading entries from local database", e);
                                            this.zzb = true;
                                            if (cursor != null) {
                                            }
                                            if (sQLiteDatabase == null) {
                                            }
                                            sQLiteDatabase.close();
                                        } catch (SQLiteException e3) {
                                            e = e3;
                                            sQLiteDatabase2 = d2;
                                            cursor = null;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            if (sQLiteDatabase != null) {
                                            }
                                            this.f6809a.zzay().zzd().zzb("Error reading entries from local database", e);
                                            this.zzb = true;
                                            if (cursor != null) {
                                            }
                                            if (sQLiteDatabase == null) {
                                            }
                                            sQLiteDatabase.close();
                                        } catch (Throwable th) {
                                            th = th;
                                            sQLiteDatabase2 = d2;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            if (cursor3 != null) {
                                            }
                                            if (sQLiteDatabase != null) {
                                            }
                                            throw th;
                                        }
                                    } else {
                                        cursor2.close();
                                        j2 = -1;
                                    }
                                    if (j2 != -1) {
                                        str = "rowid<?";
                                        strArr = new String[]{String.valueOf(j2)};
                                    } else {
                                        str = null;
                                        strArr = null;
                                    }
                                    cursor = d2.query("messages", new String[]{"rowid", "type", "entry"}, str, strArr, null, null, "rowid asc", Integer.toString(100));
                                    while (cursor.moveToNext()) {
                                        try {
                                            j3 = cursor.getLong(0);
                                            int i5 = cursor.getInt(1);
                                            byte[] blob = cursor.getBlob(2);
                                            if (i5 == 0) {
                                                obtain = Parcel.obtain();
                                                try {
                                                    try {
                                                        obtain.unmarshall(blob, 0, blob.length);
                                                        obtain.setDataPosition(0);
                                                        safeParcelable = (zzaw) zzaw.CREATOR.createFromParcel(obtain);
                                                    } finally {
                                                    }
                                                } catch (SafeParcelReader.ParseException unused2) {
                                                    this.f6809a.zzay().zzd().zza("Failed to load event from local database");
                                                    obtain.recycle();
                                                }
                                                if (safeParcelable != null) {
                                                }
                                            } else if (i5 == 1) {
                                                obtain = Parcel.obtain();
                                                try {
                                                    try {
                                                        obtain.unmarshall(blob, 0, blob.length);
                                                        obtain.setDataPosition(0);
                                                        safeParcelable = (zzlo) zzlo.CREATOR.createFromParcel(obtain);
                                                    } finally {
                                                    }
                                                } catch (SafeParcelReader.ParseException unused3) {
                                                    this.f6809a.zzay().zzd().zza("Failed to load user property from local database");
                                                    obtain.recycle();
                                                    safeParcelable = null;
                                                }
                                                if (safeParcelable != null) {
                                                }
                                            } else if (i5 == 2) {
                                                obtain = Parcel.obtain();
                                                try {
                                                    try {
                                                        obtain.unmarshall(blob, 0, blob.length);
                                                        obtain.setDataPosition(0);
                                                        safeParcelable = (zzac) zzac.CREATOR.createFromParcel(obtain);
                                                    } finally {
                                                    }
                                                } catch (SafeParcelReader.ParseException unused4) {
                                                    this.f6809a.zzay().zzd().zza("Failed to load conditional user property from local database");
                                                    obtain.recycle();
                                                    safeParcelable = null;
                                                }
                                                if (safeParcelable != null) {
                                                }
                                            } else {
                                                if (i5 == 3) {
                                                    zzd = this.f6809a.zzay().zzk();
                                                    str2 = "Skipping app launch break";
                                                } else {
                                                    zzd = this.f6809a.zzay().zzd();
                                                    str2 = "Unknown record type in local database";
                                                }
                                                zzd.zza(str2);
                                            }
                                            arrayList.add(safeParcelable);
                                        } catch (SQLiteDatabaseLockedException unused5) {
                                            sQLiteDatabase2 = d2;
                                        } catch (SQLiteFullException e4) {
                                            e = e4;
                                            sQLiteDatabase2 = d2;
                                        } catch (SQLiteException e5) {
                                            e = e5;
                                            sQLiteDatabase2 = d2;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            sQLiteDatabase2 = d2;
                                        }
                                    }
                                    sQLiteDatabase2 = d2;
                                    try {
                                        if (sQLiteDatabase2.delete("messages", "rowid <= ?", new String[]{Long.toString(j3)}) < arrayList.size()) {
                                            this.f6809a.zzay().zzd().zza("Fewer entries removed from local database than expected");
                                        }
                                        sQLiteDatabase2.setTransactionSuccessful();
                                        sQLiteDatabase2.endTransaction();
                                        cursor.close();
                                        sQLiteDatabase2.close();
                                        return arrayList;
                                    } catch (SQLiteDatabaseLockedException unused6) {
                                        sQLiteDatabase = sQLiteDatabase2;
                                        SystemClock.sleep(i4);
                                        i4 += 20;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        i3 = sQLiteDatabase == null ? i3 + 1 : 0;
                                        sQLiteDatabase.close();
                                    } catch (SQLiteFullException e6) {
                                        e = e6;
                                        sQLiteDatabase = sQLiteDatabase2;
                                        this.f6809a.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (sQLiteDatabase == null) {
                                        }
                                        sQLiteDatabase.close();
                                    } catch (SQLiteException e7) {
                                        e = e7;
                                        sQLiteDatabase = sQLiteDatabase2;
                                        if (sQLiteDatabase != null) {
                                            try {
                                                if (sQLiteDatabase.inTransaction()) {
                                                    sQLiteDatabase.endTransaction();
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                cursor3 = cursor;
                                                if (cursor3 != null) {
                                                    cursor3.close();
                                                }
                                                if (sQLiteDatabase != null) {
                                                    sQLiteDatabase.close();
                                                }
                                                throw th;
                                            }
                                        }
                                        this.f6809a.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (sQLiteDatabase == null) {
                                        }
                                        sQLiteDatabase.close();
                                    } catch (Throwable th4) {
                                        th = th4;
                                        cursor3 = cursor;
                                        sQLiteDatabase = sQLiteDatabase2;
                                        if (cursor3 != null) {
                                        }
                                        if (sQLiteDatabase != null) {
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    sQLiteDatabase2 = d2;
                                    if (cursor2 != null) {
                                        try {
                                            cursor2.close();
                                        } catch (SQLiteDatabaseLockedException unused7) {
                                            cursor = null;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            SystemClock.sleep(i4);
                                            i4 += 20;
                                            if (cursor != null) {
                                            }
                                            if (sQLiteDatabase == null) {
                                            }
                                            sQLiteDatabase.close();
                                        } catch (SQLiteFullException e8) {
                                            e = e8;
                                            cursor = null;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            this.f6809a.zzay().zzd().zzb("Error reading entries from local database", e);
                                            this.zzb = true;
                                            if (cursor != null) {
                                            }
                                            if (sQLiteDatabase == null) {
                                            }
                                            sQLiteDatabase.close();
                                        } catch (SQLiteException e9) {
                                            e = e9;
                                            cursor = null;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            if (sQLiteDatabase != null) {
                                            }
                                            this.f6809a.zzay().zzd().zzb("Error reading entries from local database", e);
                                            this.zzb = true;
                                            if (cursor != null) {
                                            }
                                            if (sQLiteDatabase == null) {
                                            }
                                            sQLiteDatabase.close();
                                        } catch (Throwable th6) {
                                            th = th6;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            if (cursor3 != null) {
                                            }
                                            if (sQLiteDatabase != null) {
                                            }
                                            throw th;
                                        }
                                    }
                                    throw th;
                                    break;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                sQLiteDatabase2 = d2;
                                cursor2 = null;
                                if (cursor2 != null) {
                                }
                                throw th;
                                break;
                                break;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            sQLiteDatabase2 = d2;
                        }
                    } catch (SQLiteDatabaseLockedException unused8) {
                        sQLiteDatabase2 = d2;
                    } catch (SQLiteFullException e10) {
                        e = e10;
                        sQLiteDatabase2 = d2;
                    } catch (SQLiteException e11) {
                        e = e11;
                        sQLiteDatabase2 = d2;
                    } catch (Throwable th9) {
                        th = th9;
                        sQLiteDatabase2 = d2;
                    }
                } catch (SQLiteDatabaseLockedException unused9) {
                    cursor = null;
                    sQLiteDatabase = null;
                } catch (SQLiteFullException e12) {
                    e = e12;
                    cursor = null;
                    sQLiteDatabase = null;
                } catch (SQLiteException e13) {
                    e = e13;
                    cursor = null;
                    sQLiteDatabase = null;
                } catch (Throwable th10) {
                    th = th10;
                    sQLiteDatabase = null;
                }
            }
            this.f6809a.zzay().zzk().zza("Failed to read events from database in reasonable time");
            return null;
        }
        return arrayList;
    }

    @WorkerThread
    public final void zzj() {
        int delete;
        zzg();
        try {
            SQLiteDatabase d2 = d();
            if (d2 == null || (delete = d2.delete("messages", null, null)) <= 0) {
                return;
            }
            this.f6809a.zzay().zzj().zzb("Reset local analytics data. records", Integer.valueOf(delete));
        } catch (SQLiteException e2) {
            this.f6809a.zzay().zzd().zzb("Error resetting local analytics data. error", e2);
        }
    }

    @WorkerThread
    public final boolean zzk() {
        return zzq(3, new byte[0]);
    }

    @WorkerThread
    public final boolean zzm() {
        int i2;
        zzg();
        if (!this.zzb && e()) {
            int i3 = 5;
            for (i2 = 0; i2 < 5; i2 = i2 + 1) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    SQLiteDatabase d2 = d();
                    if (d2 == null) {
                        this.zzb = true;
                        return false;
                    }
                    d2.beginTransaction();
                    d2.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    d2.setTransactionSuccessful();
                    d2.endTransaction();
                    d2.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused) {
                    SystemClock.sleep(i3);
                    i3 += 20;
                    i2 = 0 == 0 ? i2 + 1 : 0;
                    sQLiteDatabase.close();
                } catch (SQLiteFullException e2) {
                    this.f6809a.zzay().zzd().zzb("Error deleting app launch break from local database", e2);
                    this.zzb = true;
                    if (0 == 0) {
                    }
                    sQLiteDatabase.close();
                } catch (SQLiteException e3) {
                    if (0 != 0) {
                        try {
                            if (sQLiteDatabase.inTransaction()) {
                                sQLiteDatabase.endTransaction();
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                sQLiteDatabase.close();
                            }
                            throw th;
                        }
                    }
                    this.f6809a.zzay().zzd().zzb("Error deleting app launch break from local database", e3);
                    this.zzb = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            }
            this.f6809a.zzay().zzk().zza("Error deleting app launch break from local database in reasonable time");
        }
        return false;
    }

    public final boolean zzn(zzac zzacVar) {
        byte[] G = this.f6809a.zzv().G(zzacVar);
        if (G.length > 131072) {
            this.f6809a.zzay().zzh().zza("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return zzq(2, G);
    }

    public final boolean zzo(zzaw zzawVar) {
        Parcel obtain = Parcel.obtain();
        zzax.a(zzawVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            this.f6809a.zzay().zzh().zza("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return zzq(0, marshall);
    }

    public final boolean zzp(zzlo zzloVar) {
        Parcel obtain = Parcel.obtain();
        zzlp.a(zzloVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            this.f6809a.zzay().zzh().zza("User property too long for local database. Sending directly to service");
            return false;
        }
        return zzq(1, marshall);
    }
}
