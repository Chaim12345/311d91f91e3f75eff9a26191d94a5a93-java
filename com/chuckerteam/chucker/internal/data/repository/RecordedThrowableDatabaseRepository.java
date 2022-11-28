package com.chuckerteam.chucker.internal.data.repository;

import androidx.lifecycle.LiveData;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowableTuple;
import com.chuckerteam.chucker.internal.data.room.ChuckerDatabase;
import com.chuckerteam.chucker.internal.support.LiveDataUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0013\u0010\b\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0004H\u0016J\u001b\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/repository/RecordedThrowableDatabaseRepository;", "Lcom/chuckerteam/chucker/internal/data/repository/RecordedThrowableRepository;", "", "id", "Landroidx/lifecycle/LiveData;", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "getRecordedThrowable", "", "deleteAllThrowables", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", "getSortedThrowablesTuples", "throwable", "saveThrowable", "(Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "threshold", "deleteOldThrowables", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;", "database", "Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;", "<init>", "(Lcom/chuckerteam/chucker/internal/data/room/ChuckerDatabase;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class RecordedThrowableDatabaseRepository implements RecordedThrowableRepository {
    private final ChuckerDatabase database;

    public RecordedThrowableDatabaseRepository(@NotNull ChuckerDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        this.database = database;
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.RecordedThrowableRepository
    @Nullable
    public Object deleteAllThrowables(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object deleteAll = this.database.throwableDao().deleteAll(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return deleteAll == coroutine_suspended ? deleteAll : Unit.INSTANCE;
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.RecordedThrowableRepository
    @Nullable
    public Object deleteOldThrowables(long j2, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object deleteBefore = this.database.throwableDao().deleteBefore(j2, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return deleteBefore == coroutine_suspended ? deleteBefore : Unit.INSTANCE;
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.RecordedThrowableRepository
    @NotNull
    public LiveData<RecordedThrowable> getRecordedThrowable(long j2) {
        return LiveDataUtilsKt.distinctUntilChanged$default(this.database.throwableDao().getById(j2), null, null, 3, null);
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.RecordedThrowableRepository
    @NotNull
    public LiveData<List<RecordedThrowableTuple>> getSortedThrowablesTuples() {
        return this.database.throwableDao().getTuples();
    }

    @Override // com.chuckerteam.chucker.internal.data.repository.RecordedThrowableRepository
    @Nullable
    public Object saveThrowable(@NotNull RecordedThrowable recordedThrowable, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object insert = this.database.throwableDao().insert(recordedThrowable, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return insert == coroutine_suspended ? insert : Unit.INSTANCE;
    }
}
