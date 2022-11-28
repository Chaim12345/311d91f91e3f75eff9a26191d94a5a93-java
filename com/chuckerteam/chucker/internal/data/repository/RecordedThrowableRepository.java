package com.chuckerteam.chucker.internal.data.repository;

import androidx.lifecycle.LiveData;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowableTuple;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u001b\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H¦@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rH&J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0007H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/repository/RecordedThrowableRepository;", "", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "throwable", "", "saveThrowable", "(Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "threshold", "deleteOldThrowables", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllThrowables", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LiveData;", "", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", "getSortedThrowablesTuples", "id", "getRecordedThrowable", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface RecordedThrowableRepository {
    @Nullable
    Object deleteAllThrowables(@NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object deleteOldThrowables(long j2, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    LiveData<RecordedThrowable> getRecordedThrowable(long j2);

    @NotNull
    LiveData<List<RecordedThrowableTuple>> getSortedThrowablesTuples();

    @Nullable
    Object saveThrowable(@NotNull RecordedThrowable recordedThrowable, @NotNull Continuation<? super Unit> continuation);
}
