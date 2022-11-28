package com.chuckerteam.chucker.internal.data.repository;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.chuckerteam.chucker.internal.data.room.ChuckerDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006J\b\u0010\n\u001a\u00020\bH\u0007R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/repository/RepositoryProvider;", "", "Lcom/chuckerteam/chucker/internal/data/repository/HttpTransactionRepository;", "transaction", "Lcom/chuckerteam/chucker/internal/data/repository/RecordedThrowableRepository;", "throwable", "Landroid/content/Context;", "applicationContext", "", "initialize", "close", "transactionRepository", "Lcom/chuckerteam/chucker/internal/data/repository/HttpTransactionRepository;", "throwableRepository", "Lcom/chuckerteam/chucker/internal/data/repository/RecordedThrowableRepository;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class RepositoryProvider {
    public static final RepositoryProvider INSTANCE = new RepositoryProvider();
    private static RecordedThrowableRepository throwableRepository;
    private static HttpTransactionRepository transactionRepository;

    private RepositoryProvider() {
    }

    @VisibleForTesting
    public final void close() {
        transactionRepository = null;
        throwableRepository = null;
    }

    public final void initialize(@NotNull Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        if (transactionRepository == null || throwableRepository == null) {
            ChuckerDatabase create = ChuckerDatabase.Companion.create(applicationContext);
            transactionRepository = new HttpTransactionDatabaseRepository(create);
            throwableRepository = new RecordedThrowableDatabaseRepository(create);
        }
    }

    @NotNull
    public final RecordedThrowableRepository throwable() {
        RecordedThrowableRepository recordedThrowableRepository = throwableRepository;
        if (recordedThrowableRepository != null) {
            return recordedThrowableRepository;
        }
        throw new IllegalStateException("You can't access the throwable repository if you don't initialize it!".toString());
    }

    @NotNull
    public final HttpTransactionRepository transaction() {
        HttpTransactionRepository httpTransactionRepository = transactionRepository;
        if (httpTransactionRepository != null) {
            return httpTransactionRepository;
        }
        throw new IllegalStateException("You can't access the transaction repository if you don't initialize it!".toString());
    }
}
