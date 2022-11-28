package com.chuckerteam.chucker.internal.data.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.HttpTransactionTuple;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes.dex */
public final class HttpTransactionDao_Impl implements HttpTransactionDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<HttpTransaction> __insertionAdapterOfHttpTransaction;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBefore;
    private final EntityDeletionOrUpdateAdapter<HttpTransaction> __updateAdapterOfHttpTransaction;

    public HttpTransactionDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfHttpTransaction = new EntityInsertionAdapter<HttpTransaction>(this, roomDatabase) { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, HttpTransaction httpTransaction) {
                supportSQLiteStatement.bindLong(1, httpTransaction.getId());
                if (httpTransaction.getRequestDate() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, httpTransaction.getRequestDate().longValue());
                }
                if (httpTransaction.getResponseDate() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindLong(3, httpTransaction.getResponseDate().longValue());
                }
                if (httpTransaction.getTookMs() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, httpTransaction.getTookMs().longValue());
                }
                if (httpTransaction.getProtocol() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, httpTransaction.getProtocol());
                }
                if (httpTransaction.getMethod() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, httpTransaction.getMethod());
                }
                if (httpTransaction.getUrl() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, httpTransaction.getUrl());
                }
                if (httpTransaction.getHost() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, httpTransaction.getHost());
                }
                if (httpTransaction.getPath() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, httpTransaction.getPath());
                }
                if (httpTransaction.getScheme() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, httpTransaction.getScheme());
                }
                if (httpTransaction.getResponseTlsVersion() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, httpTransaction.getResponseTlsVersion());
                }
                if (httpTransaction.getResponseCipherSuite() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, httpTransaction.getResponseCipherSuite());
                }
                if (httpTransaction.getRequestPayloadSize() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindLong(13, httpTransaction.getRequestPayloadSize().longValue());
                }
                if (httpTransaction.getRequestContentType() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, httpTransaction.getRequestContentType());
                }
                if (httpTransaction.getRequestHeaders() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, httpTransaction.getRequestHeaders());
                }
                if (httpTransaction.getRequestBody() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, httpTransaction.getRequestBody());
                }
                supportSQLiteStatement.bindLong(17, httpTransaction.isRequestBodyPlainText() ? 1L : 0L);
                if (httpTransaction.getResponseCode() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindLong(18, httpTransaction.getResponseCode().intValue());
                }
                if (httpTransaction.getResponseMessage() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, httpTransaction.getResponseMessage());
                }
                if (httpTransaction.getError() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, httpTransaction.getError());
                }
                if (httpTransaction.getResponsePayloadSize() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindLong(21, httpTransaction.getResponsePayloadSize().longValue());
                }
                if (httpTransaction.getResponseContentType() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, httpTransaction.getResponseContentType());
                }
                if (httpTransaction.getResponseHeaders() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, httpTransaction.getResponseHeaders());
                }
                if (httpTransaction.getResponseBody() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, httpTransaction.getResponseBody());
                }
                supportSQLiteStatement.bindLong(25, httpTransaction.isResponseBodyPlainText() ? 1L : 0L);
                if (httpTransaction.getResponseImageData() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindBlob(26, httpTransaction.getResponseImageData());
                }
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `transactions` (`id`,`requestDate`,`responseDate`,`tookMs`,`protocol`,`method`,`url`,`host`,`path`,`scheme`,`responseTlsVersion`,`responseCipherSuite`,`requestPayloadSize`,`requestContentType`,`requestHeaders`,`requestBody`,`isRequestBodyPlainText`,`responseCode`,`responseMessage`,`error`,`responsePayloadSize`,`responseContentType`,`responseHeaders`,`responseBody`,`isResponseBodyPlainText`,`responseImageData`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
        };
        this.__updateAdapterOfHttpTransaction = new EntityDeletionOrUpdateAdapter<HttpTransaction>(this, roomDatabase) { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, HttpTransaction httpTransaction) {
                supportSQLiteStatement.bindLong(1, httpTransaction.getId());
                if (httpTransaction.getRequestDate() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, httpTransaction.getRequestDate().longValue());
                }
                if (httpTransaction.getResponseDate() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindLong(3, httpTransaction.getResponseDate().longValue());
                }
                if (httpTransaction.getTookMs() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, httpTransaction.getTookMs().longValue());
                }
                if (httpTransaction.getProtocol() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, httpTransaction.getProtocol());
                }
                if (httpTransaction.getMethod() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, httpTransaction.getMethod());
                }
                if (httpTransaction.getUrl() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, httpTransaction.getUrl());
                }
                if (httpTransaction.getHost() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, httpTransaction.getHost());
                }
                if (httpTransaction.getPath() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, httpTransaction.getPath());
                }
                if (httpTransaction.getScheme() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, httpTransaction.getScheme());
                }
                if (httpTransaction.getResponseTlsVersion() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, httpTransaction.getResponseTlsVersion());
                }
                if (httpTransaction.getResponseCipherSuite() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, httpTransaction.getResponseCipherSuite());
                }
                if (httpTransaction.getRequestPayloadSize() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindLong(13, httpTransaction.getRequestPayloadSize().longValue());
                }
                if (httpTransaction.getRequestContentType() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, httpTransaction.getRequestContentType());
                }
                if (httpTransaction.getRequestHeaders() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, httpTransaction.getRequestHeaders());
                }
                if (httpTransaction.getRequestBody() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, httpTransaction.getRequestBody());
                }
                supportSQLiteStatement.bindLong(17, httpTransaction.isRequestBodyPlainText() ? 1L : 0L);
                if (httpTransaction.getResponseCode() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindLong(18, httpTransaction.getResponseCode().intValue());
                }
                if (httpTransaction.getResponseMessage() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, httpTransaction.getResponseMessage());
                }
                if (httpTransaction.getError() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, httpTransaction.getError());
                }
                if (httpTransaction.getResponsePayloadSize() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindLong(21, httpTransaction.getResponsePayloadSize().longValue());
                }
                if (httpTransaction.getResponseContentType() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, httpTransaction.getResponseContentType());
                }
                if (httpTransaction.getResponseHeaders() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, httpTransaction.getResponseHeaders());
                }
                if (httpTransaction.getResponseBody() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, httpTransaction.getResponseBody());
                }
                supportSQLiteStatement.bindLong(25, httpTransaction.isResponseBodyPlainText() ? 1L : 0L);
                if (httpTransaction.getResponseImageData() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindBlob(26, httpTransaction.getResponseImageData());
                }
                supportSQLiteStatement.bindLong(27, httpTransaction.getId());
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR REPLACE `transactions` SET `id` = ?,`requestDate` = ?,`responseDate` = ?,`tookMs` = ?,`protocol` = ?,`method` = ?,`url` = ?,`host` = ?,`path` = ?,`scheme` = ?,`responseTlsVersion` = ?,`responseCipherSuite` = ?,`requestPayloadSize` = ?,`requestContentType` = ?,`requestHeaders` = ?,`requestBody` = ?,`isRequestBodyPlainText` = ?,`responseCode` = ?,`responseMessage` = ?,`error` = ?,`responsePayloadSize` = ?,`responseContentType` = ?,`responseHeaders` = ?,`responseBody` = ?,`isResponseBodyPlainText` = ?,`responseImageData` = ? WHERE `id` = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM transactions";
            }
        };
        this.__preparedStmtOfDeleteBefore = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM transactions WHERE requestDate <= ?";
            }
        };
    }

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public Object deleteAll(Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.6
            @Override // java.util.concurrent.Callable
            public Unit call() {
                SupportSQLiteStatement acquire = HttpTransactionDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                HttpTransactionDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    HttpTransactionDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    HttpTransactionDao_Impl.this.__db.endTransaction();
                    HttpTransactionDao_Impl.this.__preparedStmtOfDeleteAll.release(acquire);
                }
            }
        }, continuation);
    }

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public Object deleteBefore(final long j2, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.7
            @Override // java.util.concurrent.Callable
            public Unit call() {
                SupportSQLiteStatement acquire = HttpTransactionDao_Impl.this.__preparedStmtOfDeleteBefore.acquire();
                acquire.bindLong(1, j2);
                HttpTransactionDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    HttpTransactionDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    HttpTransactionDao_Impl.this.__db.endTransaction();
                    HttpTransactionDao_Impl.this.__preparedStmtOfDeleteBefore.release(acquire);
                }
            }
        }, continuation);
    }

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public Object getAll(Continuation<? super List<HttpTransaction>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM transactions", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<HttpTransaction>>() { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.11
            @Override // java.util.concurrent.Callable
            public List<HttpTransaction> call() {
                AnonymousClass11 anonymousClass11;
                Long valueOf;
                int i2;
                int i3;
                boolean z;
                Integer valueOf2;
                int i4;
                Long valueOf3;
                int i5;
                int i6;
                boolean z2;
                Cursor query = DBUtil.query(HttpTransactionDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requestDate");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "responseDate");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "tookMs");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "protocol");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.METHOD);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, ImagesContract.URL);
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "host");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, ClientCookie.PATH_ATTR);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "scheme");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "responseTlsVersion");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "responseCipherSuite");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "requestPayloadSize");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "requestContentType");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "requestHeaders");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "requestBody");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "isRequestBodyPlainText");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "responseCode");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "responseMessage");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "responsePayloadSize");
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "responseContentType");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "responseHeaders");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "responseBody");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "isResponseBodyPlainText");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "responseImageData");
                        int i7 = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(query.getCount());
                        while (query.moveToNext()) {
                            long j2 = query.getLong(columnIndexOrThrow);
                            Long valueOf4 = query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2));
                            Long valueOf5 = query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3));
                            Long valueOf6 = query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4));
                            String string = query.getString(columnIndexOrThrow5);
                            String string2 = query.getString(columnIndexOrThrow6);
                            String string3 = query.getString(columnIndexOrThrow7);
                            String string4 = query.getString(columnIndexOrThrow8);
                            String string5 = query.getString(columnIndexOrThrow9);
                            String string6 = query.getString(columnIndexOrThrow10);
                            String string7 = query.getString(columnIndexOrThrow11);
                            String string8 = query.getString(columnIndexOrThrow12);
                            if (query.isNull(columnIndexOrThrow13)) {
                                i2 = i7;
                                valueOf = null;
                            } else {
                                valueOf = Long.valueOf(query.getLong(columnIndexOrThrow13));
                                i2 = i7;
                            }
                            String string9 = query.getString(i2);
                            int i8 = columnIndexOrThrow;
                            int i9 = columnIndexOrThrow15;
                            String string10 = query.getString(i9);
                            columnIndexOrThrow15 = i9;
                            int i10 = columnIndexOrThrow16;
                            String string11 = query.getString(i10);
                            columnIndexOrThrow16 = i10;
                            int i11 = columnIndexOrThrow17;
                            if (query.getInt(i11) != 0) {
                                columnIndexOrThrow17 = i11;
                                i3 = columnIndexOrThrow18;
                                z = true;
                            } else {
                                columnIndexOrThrow17 = i11;
                                i3 = columnIndexOrThrow18;
                                z = false;
                            }
                            if (query.isNull(i3)) {
                                columnIndexOrThrow18 = i3;
                                i4 = columnIndexOrThrow19;
                                valueOf2 = null;
                            } else {
                                valueOf2 = Integer.valueOf(query.getInt(i3));
                                columnIndexOrThrow18 = i3;
                                i4 = columnIndexOrThrow19;
                            }
                            String string12 = query.getString(i4);
                            columnIndexOrThrow19 = i4;
                            int i12 = columnIndexOrThrow20;
                            String string13 = query.getString(i12);
                            columnIndexOrThrow20 = i12;
                            int i13 = columnIndexOrThrow21;
                            if (query.isNull(i13)) {
                                columnIndexOrThrow21 = i13;
                                i5 = columnIndexOrThrow22;
                                valueOf3 = null;
                            } else {
                                valueOf3 = Long.valueOf(query.getLong(i13));
                                columnIndexOrThrow21 = i13;
                                i5 = columnIndexOrThrow22;
                            }
                            String string14 = query.getString(i5);
                            columnIndexOrThrow22 = i5;
                            int i14 = columnIndexOrThrow23;
                            String string15 = query.getString(i14);
                            columnIndexOrThrow23 = i14;
                            int i15 = columnIndexOrThrow24;
                            String string16 = query.getString(i15);
                            columnIndexOrThrow24 = i15;
                            int i16 = columnIndexOrThrow25;
                            if (query.getInt(i16) != 0) {
                                columnIndexOrThrow25 = i16;
                                i6 = columnIndexOrThrow26;
                                z2 = true;
                            } else {
                                columnIndexOrThrow25 = i16;
                                i6 = columnIndexOrThrow26;
                                z2 = false;
                            }
                            columnIndexOrThrow26 = i6;
                            arrayList.add(new HttpTransaction(j2, valueOf4, valueOf5, valueOf6, string, string2, string3, string4, string5, string6, string7, string8, valueOf, string9, string10, string11, z, valueOf2, string12, string13, valueOf3, string14, string15, string16, z2, query.getBlob(i6)));
                            columnIndexOrThrow = i8;
                            i7 = i2;
                        }
                        query.close();
                        acquire.release();
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        anonymousClass11 = this;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    anonymousClass11 = this;
                }
            }
        }, continuation);
    }

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public LiveData<HttpTransaction> getById(long j2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM transactions WHERE id = ?", 1);
        acquire.bindLong(1, j2);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"transactions"}, false, new Callable<HttpTransaction>() { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public HttpTransaction call() {
                HttpTransaction httpTransaction;
                int i2;
                boolean z;
                Integer valueOf;
                int i3;
                Long valueOf2;
                int i4;
                Cursor query = DBUtil.query(HttpTransactionDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requestDate");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "responseDate");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "tookMs");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "protocol");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.METHOD);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, ImagesContract.URL);
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "host");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, ClientCookie.PATH_ATTR);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "scheme");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "responseTlsVersion");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "responseCipherSuite");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "requestPayloadSize");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "requestContentType");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "requestHeaders");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "requestBody");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "isRequestBodyPlainText");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "responseCode");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "responseMessage");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "responsePayloadSize");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "responseContentType");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "responseHeaders");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "responseBody");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "isResponseBodyPlainText");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "responseImageData");
                    if (query.moveToFirst()) {
                        long j3 = query.getLong(columnIndexOrThrow);
                        Long valueOf3 = query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2));
                        Long valueOf4 = query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3));
                        Long valueOf5 = query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4));
                        String string = query.getString(columnIndexOrThrow5);
                        String string2 = query.getString(columnIndexOrThrow6);
                        String string3 = query.getString(columnIndexOrThrow7);
                        String string4 = query.getString(columnIndexOrThrow8);
                        String string5 = query.getString(columnIndexOrThrow9);
                        String string6 = query.getString(columnIndexOrThrow10);
                        String string7 = query.getString(columnIndexOrThrow11);
                        String string8 = query.getString(columnIndexOrThrow12);
                        Long valueOf6 = query.isNull(columnIndexOrThrow13) ? null : Long.valueOf(query.getLong(columnIndexOrThrow13));
                        String string9 = query.getString(columnIndexOrThrow14);
                        String string10 = query.getString(columnIndexOrThrow15);
                        String string11 = query.getString(columnIndexOrThrow16);
                        if (query.getInt(columnIndexOrThrow17) != 0) {
                            z = true;
                            i2 = columnIndexOrThrow18;
                        } else {
                            i2 = columnIndexOrThrow18;
                            z = false;
                        }
                        if (query.isNull(i2)) {
                            i3 = columnIndexOrThrow19;
                            valueOf = null;
                        } else {
                            valueOf = Integer.valueOf(query.getInt(i2));
                            i3 = columnIndexOrThrow19;
                        }
                        String string12 = query.getString(i3);
                        String string13 = query.getString(columnIndexOrThrow20);
                        if (query.isNull(columnIndexOrThrow21)) {
                            i4 = columnIndexOrThrow22;
                            valueOf2 = null;
                        } else {
                            valueOf2 = Long.valueOf(query.getLong(columnIndexOrThrow21));
                            i4 = columnIndexOrThrow22;
                        }
                        httpTransaction = new HttpTransaction(j3, valueOf3, valueOf4, valueOf5, string, string2, string3, string4, string5, string6, string7, string8, valueOf6, string9, string10, string11, z, valueOf, string12, string13, valueOf2, query.getString(i4), query.getString(columnIndexOrThrow23), query.getString(columnIndexOrThrow24), query.getInt(columnIndexOrThrow25) != 0, query.getBlob(columnIndexOrThrow26));
                    } else {
                        httpTransaction = null;
                    }
                    return httpTransaction;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public LiveData<List<HttpTransactionTuple>> getFilteredTuples(String str, String str2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, requestDate, tookMs, protocol, method, host, path, scheme, responseCode, requestPayloadSize, responsePayloadSize, error FROM transactions WHERE responseCode LIKE ? AND path LIKE ? ORDER BY requestDate DESC", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"transactions"}, false, new Callable<List<HttpTransactionTuple>>() { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.9
            @Override // java.util.concurrent.Callable
            public List<HttpTransactionTuple> call() {
                Cursor query = DBUtil.query(HttpTransactionDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requestDate");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "tookMs");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "protocol");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.METHOD);
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "host");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, ClientCookie.PATH_ATTR);
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "scheme");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "responseCode");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "requestPayloadSize");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "responsePayloadSize");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new HttpTransactionTuple(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2)), query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3)), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8), query.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow9)), query.isNull(columnIndexOrThrow10) ? null : Long.valueOf(query.getLong(columnIndexOrThrow10)), query.isNull(columnIndexOrThrow11) ? null : Long.valueOf(query.getLong(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12)));
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

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public LiveData<List<HttpTransactionTuple>> getSortedTuples() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, requestDate, tookMs, protocol, method, host, path, scheme, responseCode, requestPayloadSize, responsePayloadSize, error FROM transactions ORDER BY requestDate DESC", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"transactions"}, false, new Callable<List<HttpTransactionTuple>>() { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.8
            @Override // java.util.concurrent.Callable
            public List<HttpTransactionTuple> call() {
                Cursor query = DBUtil.query(HttpTransactionDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requestDate");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "tookMs");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "protocol");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.METHOD);
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "host");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, ClientCookie.PATH_ATTR);
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "scheme");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "responseCode");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "requestPayloadSize");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "responsePayloadSize");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new HttpTransactionTuple(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2)), query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3)), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8), query.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow9)), query.isNull(columnIndexOrThrow10) ? null : Long.valueOf(query.getLong(columnIndexOrThrow10)), query.isNull(columnIndexOrThrow11) ? null : Long.valueOf(query.getLong(columnIndexOrThrow11)), query.getString(columnIndexOrThrow12)));
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

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public Object insert(final HttpTransaction httpTransaction, Continuation<? super Long> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Long>() { // from class: com.chuckerteam.chucker.internal.data.room.HttpTransactionDao_Impl.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() {
                HttpTransactionDao_Impl.this.__db.beginTransaction();
                try {
                    long insertAndReturnId = HttpTransactionDao_Impl.this.__insertionAdapterOfHttpTransaction.insertAndReturnId(httpTransaction);
                    HttpTransactionDao_Impl.this.__db.setTransactionSuccessful();
                    return Long.valueOf(insertAndReturnId);
                } finally {
                    HttpTransactionDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.chuckerteam.chucker.internal.data.room.HttpTransactionDao
    public int update(HttpTransaction httpTransaction) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__updateAdapterOfHttpTransaction.handle(httpTransaction) + 0;
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }
}
