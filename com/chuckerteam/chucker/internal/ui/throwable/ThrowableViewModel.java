package com.chuckerteam.chucker.internal.ui.throwable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.data.repository.RepositoryProvider;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bR\u001f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableViewModel;", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/LiveData;", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "throwable", "Landroidx/lifecycle/LiveData;", "getThrowable", "()Landroidx/lifecycle/LiveData;", "", "throwableId", "<init>", "(J)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ThrowableViewModel extends ViewModel {
    @NotNull
    private final LiveData<RecordedThrowable> throwable;

    public ThrowableViewModel(long j2) {
        this.throwable = RepositoryProvider.INSTANCE.throwable().getRecordedThrowable(j2);
    }

    @NotNull
    public final LiveData<RecordedThrowable> getThrowable() {
        return this.throwable;
    }
}
