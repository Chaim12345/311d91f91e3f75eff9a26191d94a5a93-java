package com.chuckerteam.chucker.internal.data.repository;

import androidx.lifecycle.LiveData;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.HttpTransactionTuple;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.apache.http.cookie.ClientCookie;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\u001b\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H&J\u001b\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH¦@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH&J$\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H&J\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\tH&J\u0019\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H¦@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/repository/HttpTransactionRepository;", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "", "insertTransaction", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "updateTransaction", "", "threshold", "deleteOldTransactions", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllTransactions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LiveData;", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransactionTuple;", "getSortedTransactionTuples", "", "code", ClientCookie.PATH_ATTR, "getFilteredTransactionTuples", "transactionId", "getTransaction", "getAllTransactions", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface HttpTransactionRepository {
    @Nullable
    Object deleteAllTransactions(@NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object deleteOldTransactions(long j2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object getAllTransactions(@NotNull Continuation<? super List<HttpTransaction>> continuation);

    @NotNull
    LiveData<List<HttpTransactionTuple>> getFilteredTransactionTuples(@NotNull String str, @NotNull String str2);

    @NotNull
    LiveData<List<HttpTransactionTuple>> getSortedTransactionTuples();

    @NotNull
    LiveData<HttpTransaction> getTransaction(long j2);

    @Nullable
    Object insertTransaction(@NotNull HttpTransaction httpTransaction, @NotNull Continuation<? super Unit> continuation);

    int updateTransaction(@NotNull HttpTransaction httpTransaction);
}
