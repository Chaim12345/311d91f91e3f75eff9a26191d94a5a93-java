package com.chuckerteam.chucker.internal.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.HttpTransactionTuple;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\ba\u0018\u00002\u00020\u0001J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H'J$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H'J\u001d\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\nH'J\u0013\u0010\u0012\u001a\u00020\u0011H§@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00022\u0006\u0010\u0014\u001a\u00020\fH'J\u001b\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H§@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/room/HttpTransactionDao;", "", "Landroidx/lifecycle/LiveData;", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransactionTuple;", "getSortedTuples", "", "codeQuery", "pathQuery", "getFilteredTuples", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "", "insert", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "update", "", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "id", "getById", "threshold", "deleteBefore", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface HttpTransactionDao {
    @Query("DELETE FROM transactions")
    @Nullable
    Object deleteAll(@NotNull Continuation<? super Unit> continuation);

    @Query("DELETE FROM transactions WHERE requestDate <= :threshold")
    @Nullable
    Object deleteBefore(long j2, @NotNull Continuation<? super Unit> continuation);

    @Query("SELECT * FROM transactions")
    @Nullable
    Object getAll(@NotNull Continuation<? super List<HttpTransaction>> continuation);

    @Query("SELECT * FROM transactions WHERE id = :id")
    @NotNull
    LiveData<HttpTransaction> getById(long j2);

    @Query("SELECT id, requestDate, tookMs, protocol, method, host, path, scheme, responseCode, requestPayloadSize, responsePayloadSize, error FROM transactions WHERE responseCode LIKE :codeQuery AND path LIKE :pathQuery ORDER BY requestDate DESC")
    @NotNull
    LiveData<List<HttpTransactionTuple>> getFilteredTuples(@NotNull String str, @NotNull String str2);

    @Query("SELECT id, requestDate, tookMs, protocol, method, host, path, scheme, responseCode, requestPayloadSize, responsePayloadSize, error FROM transactions ORDER BY requestDate DESC")
    @NotNull
    LiveData<List<HttpTransactionTuple>> getSortedTuples();

    @Insert
    @Nullable
    Object insert(@NotNull HttpTransaction httpTransaction, @NotNull Continuation<? super Long> continuation);

    @Update(onConflict = 1)
    int update(@NotNull HttpTransaction httpTransaction);
}
