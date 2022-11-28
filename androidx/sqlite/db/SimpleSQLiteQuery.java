package androidx.sqlite.db;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    @Nullable
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String str) {
        this(str, null);
    }

    public SimpleSQLiteQuery(String str, @Nullable Object[] objArr) {
        this.mQuery = str;
        this.mBindArgs = objArr;
    }

    private static void bind(SupportSQLiteProgram supportSQLiteProgram, int i2, Object obj) {
        long j2;
        int byteValue;
        double doubleValue;
        if (obj == null) {
            supportSQLiteProgram.bindNull(i2);
        } else if (obj instanceof byte[]) {
            supportSQLiteProgram.bindBlob(i2, (byte[]) obj);
        } else {
            if (obj instanceof Float) {
                doubleValue = ((Float) obj).floatValue();
            } else if (!(obj instanceof Double)) {
                if (obj instanceof Long) {
                    j2 = ((Long) obj).longValue();
                } else {
                    if (obj instanceof Integer) {
                        byteValue = ((Integer) obj).intValue();
                    } else if (obj instanceof Short) {
                        byteValue = ((Short) obj).shortValue();
                    } else if (obj instanceof Byte) {
                        byteValue = ((Byte) obj).byteValue();
                    } else if (obj instanceof String) {
                        supportSQLiteProgram.bindString(i2, (String) obj);
                        return;
                    } else if (!(obj instanceof Boolean)) {
                        throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i2 + " Supported types: null, byte[], float, double, long, int, short, byte, string");
                    } else {
                        j2 = ((Boolean) obj).booleanValue() ? 1L : 0L;
                    }
                    j2 = byteValue;
                }
                supportSQLiteProgram.bindLong(i2, j2);
                return;
            } else {
                doubleValue = ((Double) obj).doubleValue();
            }
            supportSQLiteProgram.bindDouble(i2, doubleValue);
        }
    }

    public static void bind(SupportSQLiteProgram supportSQLiteProgram, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i2 = 0;
        while (i2 < length) {
            Object obj = objArr[i2];
            i2++;
            bind(supportSQLiteProgram, i2, obj);
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        bind(supportSQLiteProgram, this.mBindArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        Object[] objArr = this.mBindArgs;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }
}
