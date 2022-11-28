package com.facebook.stetho.inspector.database;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;
/* loaded from: classes.dex */
public interface DatabaseConnectionProvider {
    SQLiteDatabase openDatabase(File file);
}
