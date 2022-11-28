package com.chuckerteam.chucker.internal.data.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowableTuple;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* loaded from: classes.dex */
public final class RecordedThrowableDao_Impl implements RecordedThrowableDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<RecordedThrowable> __insertionAdapterOfRecordedThrowable;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBefore;

    public RecordedThrowableDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfRecordedThrowable = new EntityInsertionAdapter<RecordedThrowable>(this, roomDatabase) { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, RecordedThrowable recordedThrowable) {
                if (recordedThrowable.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, recordedThrowable.getId().longValue());
                }
                if (recordedThrowable.getTag() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, recordedThrowable.getTag());
                }
                if (recordedThrowable.getDate() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindLong(3, recordedThrowable.getDate().longValue());
                }
                if (recordedThrowable.getClazz() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, recordedThrowable.getClazz());
                }
                if (recordedThrowable.getMessage() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, recordedThrowable.getMessage());
                }
                if (recordedThrowable.getContent() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, recordedThrowable.getContent());
                }
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `throwables` (`id`,`tag`,`date`,`clazz`,`message`,`content`) VALUES (?,?,?,?,?,?)";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM throwables";
            }
        };
        this.__preparedStmtOfDeleteBefore = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM throwables WHERE date <= ?";
            }
        };
    }

    @Override // com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao
    public Object deleteAll(Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.5
            @Override // java.util.concurrent.Callable
            public Unit call() {
                SupportSQLiteStatement acquire = RecordedThrowableDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                RecordedThrowableDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    RecordedThrowableDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    RecordedThrowableDao_Impl.this.__db.endTransaction();
                    RecordedThrowableDao_Impl.this.__preparedStmtOfDeleteAll.release(acquire);
                }
            }
        }, continuation);
    }

    @Override // com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao
    public Object deleteBefore(final long j2, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.6
            @Override // java.util.concurrent.Callable
            public Unit call() {
                SupportSQLiteStatement acquire = RecordedThrowableDao_Impl.this.__preparedStmtOfDeleteBefore.acquire();
                acquire.bindLong(1, j2);
                RecordedThrowableDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    RecordedThrowableDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    RecordedThrowableDao_Impl.this.__db.endTransaction();
                    RecordedThrowableDao_Impl.this.__preparedStmtOfDeleteBefore.release(acquire);
                }
            }
        }, continuation);
    }

    @Override // com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao
    public LiveData<RecordedThrowable> getById(long j2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM throwables WHERE id = ?", 1);
        acquire.bindLong(1, j2);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"throwables"}, false, new Callable<RecordedThrowable>() { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public RecordedThrowable call() {
                RecordedThrowable recordedThrowable = null;
                Cursor query = DBUtil.query(RecordedThrowableDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "tag");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "date");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "clazz");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, AppConstants.ARG_MESSAGE);
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.CONTENT);
                    if (query.moveToFirst()) {
                        recordedThrowable = new RecordedThrowable(query.isNull(columnIndexOrThrow) ? null : Long.valueOf(query.getLong(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3)), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6));
                    }
                    return recordedThrowable;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao
    public LiveData<List<RecordedThrowableTuple>> getTuples() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id,tag,date,clazz,message FROM throwables ORDER BY date DESC", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"throwables"}, false, new Callable<List<RecordedThrowableTuple>>() { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.7
            @Override // java.util.concurrent.Callable
            public List<RecordedThrowableTuple> call() {
                Cursor query = DBUtil.query(RecordedThrowableDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "tag");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "date");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "clazz");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, AppConstants.ARG_MESSAGE);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new RecordedThrowableTuple(query.isNull(columnIndexOrThrow) ? null : Long.valueOf(query.getLong(columnIndexOrThrow)), query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3)), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao
    public Object insert(final RecordedThrowable recordedThrowable, Continuation<? super Long> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Long>() { // from class: com.chuckerteam.chucker.internal.data.room.RecordedThrowableDao_Impl.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() {
                RecordedThrowableDao_Impl.this.__db.beginTransaction();
                try {
                    long insertAndReturnId = RecordedThrowableDao_Impl.this.__insertionAdapterOfRecordedThrowable.insertAndReturnId(recordedThrowable);
                    RecordedThrowableDao_Impl.this.__db.setTransactionSuccessful();
                    return Long.valueOf(insertAndReturnId);
                } finally {
                    RecordedThrowableDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }
}
