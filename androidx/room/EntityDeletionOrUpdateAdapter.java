package androidx.room;

import androidx.annotation.RestrictTo;
import androidx.sqlite.db.SupportSQLiteStatement;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class EntityDeletionOrUpdateAdapter<T> extends SharedSQLiteStatement {
    public EntityDeletionOrUpdateAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    protected abstract void bind(SupportSQLiteStatement supportSQLiteStatement, Object obj);

    public final int handle(T t2) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, t2);
            return acquire.executeUpdateDelete();
        } finally {
            release(acquire);
        }
    }

    public final int handleMultiple(Iterable<? extends T> iterable) {
        SupportSQLiteStatement acquire = acquire();
        int i2 = 0;
        try {
            for (T t2 : iterable) {
                bind(acquire, t2);
                i2 += acquire.executeUpdateDelete();
            }
            return i2;
        } finally {
            release(acquire);
        }
    }

    public final int handleMultiple(T[] tArr) {
        SupportSQLiteStatement acquire = acquire();
        try {
            int i2 = 0;
            for (T t2 : tArr) {
                bind(acquire, t2);
                i2 += acquire.executeUpdateDelete();
            }
            return i2;
        } finally {
            release(acquire);
        }
    }
}
