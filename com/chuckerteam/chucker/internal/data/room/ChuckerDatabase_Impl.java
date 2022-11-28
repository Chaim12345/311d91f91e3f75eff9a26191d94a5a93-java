package com.chuckerteam.chucker.internal.data.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes.dex */
public final class ChuckerDatabase_Impl extends ChuckerDatabase {
    private volatile HttpTransactionDao _httpTransactionDao;
    private volatile RecordedThrowableDao _recordedThrowableDao;

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker a() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "throwables", "transactions");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper b(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(4) { // from class: com.chuckerteam.chucker.internal.data.room.ChuckerDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void a(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) ChuckerDatabase_Impl.this).f3911c != null) {
                    int size = ((RoomDatabase) ChuckerDatabase_Impl.this).f3911c.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) ChuckerDatabase_Impl.this).f3911c.get(i2)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult b(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(6);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                hashMap.put("tag", new TableInfo.Column("tag", "TEXT", false, 0, null, 1));
                hashMap.put("date", new TableInfo.Column("date", "INTEGER", false, 0, null, 1));
                hashMap.put("clazz", new TableInfo.Column("clazz", "TEXT", false, 0, null, 1));
                hashMap.put(AppConstants.ARG_MESSAGE, new TableInfo.Column(AppConstants.ARG_MESSAGE, "TEXT", false, 0, null, 1));
                hashMap.put(FirebaseAnalytics.Param.CONTENT, new TableInfo.Column(FirebaseAnalytics.Param.CONTENT, "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("throwables", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "throwables");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "throwables(com.chuckerteam.chucker.internal.data.entity.RecordedThrowable).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(26);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put("requestDate", new TableInfo.Column("requestDate", "INTEGER", false, 0, null, 1));
                hashMap2.put("responseDate", new TableInfo.Column("responseDate", "INTEGER", false, 0, null, 1));
                hashMap2.put("tookMs", new TableInfo.Column("tookMs", "INTEGER", false, 0, null, 1));
                hashMap2.put("protocol", new TableInfo.Column("protocol", "TEXT", false, 0, null, 1));
                hashMap2.put(FirebaseAnalytics.Param.METHOD, new TableInfo.Column(FirebaseAnalytics.Param.METHOD, "TEXT", false, 0, null, 1));
                hashMap2.put(ImagesContract.URL, new TableInfo.Column(ImagesContract.URL, "TEXT", false, 0, null, 1));
                hashMap2.put("host", new TableInfo.Column("host", "TEXT", false, 0, null, 1));
                hashMap2.put(ClientCookie.PATH_ATTR, new TableInfo.Column(ClientCookie.PATH_ATTR, "TEXT", false, 0, null, 1));
                hashMap2.put("scheme", new TableInfo.Column("scheme", "TEXT", false, 0, null, 1));
                hashMap2.put("responseTlsVersion", new TableInfo.Column("responseTlsVersion", "TEXT", false, 0, null, 1));
                hashMap2.put("responseCipherSuite", new TableInfo.Column("responseCipherSuite", "TEXT", false, 0, null, 1));
                hashMap2.put("requestPayloadSize", new TableInfo.Column("requestPayloadSize", "INTEGER", false, 0, null, 1));
                hashMap2.put("requestContentType", new TableInfo.Column("requestContentType", "TEXT", false, 0, null, 1));
                hashMap2.put("requestHeaders", new TableInfo.Column("requestHeaders", "TEXT", false, 0, null, 1));
                hashMap2.put("requestBody", new TableInfo.Column("requestBody", "TEXT", false, 0, null, 1));
                hashMap2.put("isRequestBodyPlainText", new TableInfo.Column("isRequestBodyPlainText", "INTEGER", true, 0, null, 1));
                hashMap2.put("responseCode", new TableInfo.Column("responseCode", "INTEGER", false, 0, null, 1));
                hashMap2.put("responseMessage", new TableInfo.Column("responseMessage", "TEXT", false, 0, null, 1));
                hashMap2.put(Constants.IPC_BUNDLE_KEY_SEND_ERROR, new TableInfo.Column(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "TEXT", false, 0, null, 1));
                hashMap2.put("responsePayloadSize", new TableInfo.Column("responsePayloadSize", "INTEGER", false, 0, null, 1));
                hashMap2.put("responseContentType", new TableInfo.Column("responseContentType", "TEXT", false, 0, null, 1));
                hashMap2.put("responseHeaders", new TableInfo.Column("responseHeaders", "TEXT", false, 0, null, 1));
                hashMap2.put("responseBody", new TableInfo.Column("responseBody", "TEXT", false, 0, null, 1));
                hashMap2.put("isResponseBodyPlainText", new TableInfo.Column("isResponseBodyPlainText", "INTEGER", true, 0, null, 1));
                hashMap2.put("responseImageData", new TableInfo.Column("responseImageData", "BLOB", false, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("transactions", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "transactions");
                if (tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(true, null);
                }
                return new RoomOpenHelper.ValidationResult(false, "transactions(com.chuckerteam.chucker.internal.data.entity.HttpTransaction).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `throwables` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `tag` TEXT, `date` INTEGER, `clazz` TEXT, `message` TEXT, `content` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `requestDate` INTEGER, `responseDate` INTEGER, `tookMs` INTEGER, `protocol` TEXT, `method` TEXT, `url` TEXT, `host` TEXT, `path` TEXT, `scheme` TEXT, `responseTlsVersion` TEXT, `responseCipherSuite` TEXT, `requestPayloadSize` INTEGER, `requestContentType` TEXT, `requestHeaders` TEXT, `requestBody` TEXT, `isRequestBodyPlainText` INTEGER NOT NULL, `responseCode` INTEGER, `responseMessage` TEXT, `error` TEXT, `responsePayloadSize` INTEGER, `responseContentType` TEXT, `responseHeaders` TEXT, `responseBody` TEXT, `isResponseBodyPlainText` INTEGER NOT NULL, `responseImageData` BLOB)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3ad896fa3ec863e554b9890fab536763')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `throwables`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `transactions`");
                if (((RoomDatabase) ChuckerDatabase_Impl.this).f3911c != null) {
                    int size = ((RoomDatabase) ChuckerDatabase_Impl.this).f3911c.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) ChuckerDatabase_Impl.this).f3911c.get(i2)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) ChuckerDatabase_Impl.this).f3909a = supportSQLiteDatabase;
                ChuckerDatabase_Impl.this.f(supportSQLiteDatabase);
                if (((RoomDatabase) ChuckerDatabase_Impl.this).f3911c != null) {
                    int size = ((RoomDatabase) ChuckerDatabase_Impl.this).f3911c.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) ChuckerDatabase_Impl.this).f3911c.get(i2)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }
        }, "3ad896fa3ec863e554b9890fab536763", "ff9d4b6aab15b17c7fd7e9a0ef9f18c7")).build());
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `throwables`");
            writableDatabase.execSQL("DELETE FROM `transactions`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.chuckerteam.chucker.internal.data.room.ChuckerDatabase
    public RecordedThrowableDao throwableDao() {
        RecordedThrowableDao recordedThrowableDao;
        if (this._recordedThrowableDao != null) {
            return this._recordedThrowableDao;
        }
        synchronized (this) {
            if (this._recordedThrowableDao == null) {
                this._recordedThrowableDao = new RecordedThrowableDao_Impl(this);
            }
            recordedThrowableDao = this._recordedThrowableDao;
        }
        return recordedThrowableDao;
    }

    @Override // com.chuckerteam.chucker.internal.data.room.ChuckerDatabase
    public HttpTransactionDao transactionDao() {
        HttpTransactionDao httpTransactionDao;
        if (this._httpTransactionDao != null) {
            return this._httpTransactionDao;
        }
        synchronized (this) {
            if (this._httpTransactionDao == null) {
                this._httpTransactionDao = new HttpTransactionDao_Impl(this);
            }
            httpTransactionDao = this._httpTransactionDao;
        }
        return httpTransactionDao;
    }
}
