package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes.dex */
public abstract class SQLiteDatabaseCompat {
    public static final int ENABLE_FOREIGN_KEY_CONSTRAINTS = 2;
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 1;
    private static final SQLiteDatabaseCompat sInstance;

    @TargetApi(11)
    /* loaded from: classes.dex */
    private static class HoneycombImpl extends SQLiteDatabaseCompat {
        private HoneycombImpl() {
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public void enableFeatures(@SQLiteOpenOptions int i2, SQLiteDatabase sQLiteDatabase) {
            if ((i2 & 1) != 0) {
                sQLiteDatabase.enableWriteAheadLogging();
            }
            if ((i2 & 2) != 0) {
                sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
            }
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public int provideOpenFlags(@SQLiteOpenOptions int i2) {
            return 0;
        }
    }

    @TargetApi(16)
    /* loaded from: classes.dex */
    private static class JellyBeanAndBeyondImpl extends SQLiteDatabaseCompat {
        private JellyBeanAndBeyondImpl() {
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public void enableFeatures(@SQLiteOpenOptions int i2, SQLiteDatabase sQLiteDatabase) {
            if ((i2 & 2) != 0) {
                sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
            }
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public int provideOpenFlags(@SQLiteOpenOptions int i2) {
            if ((i2 & 1) != 0) {
                return PKIFailureInfo.duplicateCertReq;
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class NoopImpl extends SQLiteDatabaseCompat {
        private NoopImpl() {
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public void enableFeatures(@SQLiteOpenOptions int i2, SQLiteDatabase sQLiteDatabase) {
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public int provideOpenFlags(@SQLiteOpenOptions int i2) {
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public @interface SQLiteOpenOptions {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        sInstance = i2 >= 16 ? new JellyBeanAndBeyondImpl() : i2 >= 11 ? new HoneycombImpl() : new NoopImpl();
    }

    public static SQLiteDatabaseCompat getInstance() {
        return sInstance;
    }

    public abstract void enableFeatures(@SQLiteOpenOptions int i2, SQLiteDatabase sQLiteDatabase);

    public abstract int provideOpenFlags(@SQLiteOpenOptions int i2);
}
