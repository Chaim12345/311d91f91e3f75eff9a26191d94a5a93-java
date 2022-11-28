package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;
import android.database.Cursor;
import com.facebook.stetho.inspector.protocol.module.Database;
import java.util.List;
/* loaded from: classes.dex */
public abstract class BaseDatabaseDriver<DESC> {
    protected Context mContext;

    /* loaded from: classes.dex */
    public interface ExecuteResultHandler<RESULT> {
        RESULT handleInsert(long j2);

        RESULT handleRawQuery();

        RESULT handleSelect(Cursor cursor);

        RESULT handleUpdateDelete(int i2);
    }

    public BaseDatabaseDriver(Context context) {
        this.mContext = context;
    }

    public abstract Database.ExecuteSQLResponse executeSQL(DESC desc, String str, ExecuteResultHandler<Database.ExecuteSQLResponse> executeResultHandler);

    public Context getContext() {
        return this.mContext;
    }

    public abstract List<DESC> getDatabaseNames();

    public abstract List<String> getTableNames(DESC desc);
}
