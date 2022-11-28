package com.chuckerteam.chucker.internal.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowableTuple;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\ba\u0018\u00002\u00020\u0001J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H'J\u001d\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\u00020\u000bH§@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00022\u0006\u0010\u000e\u001a\u00020\bH'J\u001b\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/room/RecordedThrowableDao;", "", "Landroidx/lifecycle/LiveData;", "", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", "getTuples", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "throwable", "", "insert", "(Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "id", "getById", "threshold", "deleteBefore", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface RecordedThrowableDao {
    @Query("DELETE FROM throwables")
    @Nullable
    Object deleteAll(@NotNull Continuation<? super Unit> continuation);

    @Query("DELETE FROM throwables WHERE date <= :threshold")
    @Nullable
    Object deleteBefore(long j2, @NotNull Continuation<? super Unit> continuation);

    @Query("SELECT * FROM throwables WHERE id = :id")
    @NotNull
    LiveData<RecordedThrowable> getById(long j2);

    @Query("SELECT id,tag,date,clazz,message FROM throwables ORDER BY date DESC")
    @NotNull
    LiveData<List<RecordedThrowableTuple>> getTuples();

    @Insert
    @Nullable
    Object insert(@NotNull RecordedThrowable recordedThrowable, @NotNull Continuation<? super Long> continuation);
}
