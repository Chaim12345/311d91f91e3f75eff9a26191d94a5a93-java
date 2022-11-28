package com.chuckerteam.chucker.internal.data.repository;

import androidx.lifecycle.LiveData;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.HttpTransactionTuple;
import com.chuckerteam.chucker.internal.data.room.ChuckerDatabase;
import com.chuckerteam.chucker.internal.data.room.HttpTransactionDao;
import com.chuckerteam.chucker.internal.support.LiveDataUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b!\u0010\"J$\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00052\u0006\u0010\n\u001a\u00020\tH\u0016J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\u0016J\u0013\u0010\u000f\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u001b\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0010R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010 \u001a\u00020\u001d8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/repository/HttpTransactionDatabaseRepository;", "Lcom/chuckerteam/chucker/internal/data/repository/HttpTransactionRepository;", "", "code", ClientCookie.PATH_ATTR, "Landroidx/lifecycle/LiveData;", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransactionTuple;", "getFilteredTransactionTuples", "", "transactionId", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "getTransaction", "getSortedTransactionTuples", "", "deleteAllTransactions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transaction", "insertTransaction", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "updateTransaction", "threshold", "deleteOldTransactions", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTransactions", "Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;", "database", "Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;", "Lcom/chuckerteam/chucker/internal/data/room/HttpTransactionDao;", "getTransactionDao", "()Lcom/chuckerteam/chucker/internal/data/room/HttpTransactionDao;", "transactionDao", "<init>", "(Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class HttpTransactionDatabaseRepository implements HttpTransactionRepository {
    private final ChuckerDatabase database;

    public HttpTransactionDatabaseRepository(@NotNull ChuckerDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        this.database = database;
    }

    private final HttpTransactionDao getTransactionDao() {
        return this.database.transactionDao();
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    @Nullable
    public Object deleteAllTransactions(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object deleteAll = getTransactionDao().deleteAll(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return deleteAll == coroutine_suspended ? deleteAll : Unit.INSTANCE;
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    @Nullable
    public Object deleteOldTransactions(long j2, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object deleteBefore = getTransactionDao().deleteBefore(j2, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return deleteBefore == coroutine_suspended ? deleteBefore : Unit.INSTANCE;
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    @Nullable
    public Object getAllTransactions(@NotNull Continuation<? super List<HttpTransaction>> continuation) {
        return getTransactionDao().getAll(continuation);
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    @NotNull
    public LiveData<List<HttpTransactionTuple>> getFilteredTransactionTuples(@NotNull String code, @NotNull String path) {
        String str;
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(path, "path");
        if (path.length() > 0) {
            str = '%' + path + '%';
        } else {
            str = "%";
        }
        return getTransactionDao().getFilteredTuples(code + '%', str);
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    @NotNull
    public LiveData<List<HttpTransactionTuple>> getSortedTransactionTuples() {
        return getTransactionDao().getSortedTuples();
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    @NotNull
    public LiveData<HttpTransaction> getTransaction(long j2) {
        return LiveDataUtilsKt.distinctUntilChanged$default(getTransactionDao().getById(j2), null, HttpTransactionDatabaseRepository$getTransaction$1.INSTANCE, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object insertTransaction(@NotNull HttpTransaction httpTransaction, @NotNull Continuation<? super Unit> continuation) {
        HttpTransactionDatabaseRepository$insertTransaction$1 httpTransactionDatabaseRepository$insertTransaction$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof HttpTransactionDatabaseRepository$insertTransaction$1) {
            httpTransactionDatabaseRepository$insertTransaction$1 = (HttpTransactionDatabaseRepository$insertTransaction$1) continuation;
            int i3 = httpTransactionDatabaseRepository$insertTransaction$1.f4858b;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                httpTransactionDatabaseRepository$insertTransaction$1.f4858b = i3 - Integer.MIN_VALUE;
                Object obj = httpTransactionDatabaseRepository$insertTransaction$1.f4857a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = httpTransactionDatabaseRepository$insertTransaction$1.f4858b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    HttpTransactionDao transactionDao = getTransactionDao();
                    httpTransactionDatabaseRepository$insertTransaction$1.f4860d = this;
                    httpTransactionDatabaseRepository$insertTransaction$1.f4861e = httpTransaction;
                    httpTransactionDatabaseRepository$insertTransaction$1.f4858b = 1;
                    obj = transactionDao.insert(httpTransaction, httpTransactionDatabaseRepository$insertTransaction$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    httpTransaction = (HttpTransaction) httpTransactionDatabaseRepository$insertTransaction$1.f4861e;
                    HttpTransactionDatabaseRepository httpTransactionDatabaseRepository = (HttpTransactionDatabaseRepository) httpTransactionDatabaseRepository$insertTransaction$1.f4860d;
                    ResultKt.throwOnFailure(obj);
                }
                Long l2 = (Long) obj;
                httpTransaction.setId(l2 == null ? l2.longValue() : 0L);
                return Unit.INSTANCE;
            }
        }
        httpTransactionDatabaseRepository$insertTransaction$1 = new HttpTransactionDatabaseRepository$insertTransaction$1(this, continuation);
        Object obj2 = httpTransactionDatabaseRepository$insertTransaction$1.f4857a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = httpTransactionDatabaseRepository$insertTransaction$1.f4858b;
        if (i2 != 0) {
        }
        Long l22 = (Long) obj2;
        httpTransaction.setId(l22 == null ? l22.longValue() : 0L);
        return Unit.INSTANCE;
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository
    public int updateTransaction(@NotNull HttpTransaction transaction) {
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        return getTransactionDao().update(transaction);
    }
}
