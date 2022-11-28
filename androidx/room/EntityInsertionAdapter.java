package androidx.room;

import androidx.annotation.RestrictTo;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class EntityInsertionAdapter<T> extends SharedSQLiteStatement {
    public EntityInsertionAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    protected abstract void bind(SupportSQLiteStatement supportSQLiteStatement, Object obj);

    public final void insert(Iterable<? extends T> iterable) {
        SupportSQLiteStatement acquire = acquire();
        try {
            for (T t2 : iterable) {
                bind(acquire, t2);
                acquire.executeInsert();
            }
        } finally {
            release(acquire);
        }
    }

    public final void insert(T t2) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, t2);
            acquire.executeInsert();
        } finally {
            release(acquire);
        }
    }

    public final void insert(T[] tArr) {
        SupportSQLiteStatement acquire = acquire();
        try {
            for (T t2 : tArr) {
                bind(acquire, t2);
                acquire.executeInsert();
            }
        } finally {
            release(acquire);
        }
    }

    public final long insertAndReturnId(T t2) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, t2);
            return acquire.executeInsert();
        } finally {
            release(acquire);
        }
    }

    public final long[] insertAndReturnIdsArray(Collection<? extends T> collection) {
        SupportSQLiteStatement acquire = acquire();
        try {
            long[] jArr = new long[collection.size()];
            int i2 = 0;
            for (T t2 : collection) {
                bind(acquire, t2);
                jArr[i2] = acquire.executeInsert();
                i2++;
            }
            return jArr;
        } finally {
            release(acquire);
        }
    }

    public final long[] insertAndReturnIdsArray(T[] tArr) {
        SupportSQLiteStatement acquire = acquire();
        try {
            long[] jArr = new long[tArr.length];
            int i2 = 0;
            for (T t2 : tArr) {
                bind(acquire, t2);
                jArr[i2] = acquire.executeInsert();
                i2++;
            }
            return jArr;
        } finally {
            release(acquire);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Collection<? extends T> collection) {
        SupportSQLiteStatement acquire = acquire();
        try {
            Long[] lArr = new Long[collection.size()];
            int i2 = 0;
            for (T t2 : collection) {
                bind(acquire, t2);
                lArr[i2] = Long.valueOf(acquire.executeInsert());
                i2++;
            }
            return lArr;
        } finally {
            release(acquire);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(T[] tArr) {
        SupportSQLiteStatement acquire = acquire();
        try {
            Long[] lArr = new Long[tArr.length];
            int i2 = 0;
            for (T t2 : tArr) {
                bind(acquire, t2);
                lArr[i2] = Long.valueOf(acquire.executeInsert());
                i2++;
            }
            return lArr;
        } finally {
            release(acquire);
        }
    }

    public final List<Long> insertAndReturnIdsList(Collection<? extends T> collection) {
        SupportSQLiteStatement acquire = acquire();
        try {
            ArrayList arrayList = new ArrayList(collection.size());
            int i2 = 0;
            for (T t2 : collection) {
                bind(acquire, t2);
                arrayList.add(i2, Long.valueOf(acquire.executeInsert()));
                i2++;
            }
            return arrayList;
        } finally {
            release(acquire);
        }
    }

    public final List<Long> insertAndReturnIdsList(T[] tArr) {
        SupportSQLiteStatement acquire = acquire();
        try {
            ArrayList arrayList = new ArrayList(tArr.length);
            int i2 = 0;
            for (T t2 : tArr) {
                bind(acquire, t2);
                arrayList.add(i2, Long.valueOf(acquire.executeInsert()));
                i2++;
            }
            return arrayList;
        } finally {
            release(acquire);
        }
    }
}
