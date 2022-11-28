package com.chuckerteam.chucker.internal.support;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.chuckerteam.chucker.internal.data.repository.RepositoryProvider;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00042\u00020\u0001:\u0002\u0005\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService;", "Landroid/app/IntentService;", "<init>", "()V", "Companion", "ClearAction", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ClearDatabaseService extends IntentService {
    @NotNull
    public static final String CLEAN_DATABASE_SERVICE_NAME = "Chucker-ClearDatabaseService";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_ITEM_TO_CLEAR = "EXTRA_ITEM_TO_CLEAR";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction;", "Ljava/io/Serializable;", "<init>", "()V", "Error", "Transaction", "Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction$Transaction;", "Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction$Error;", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static abstract class ClearAction implements Serializable {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction$Error;", "Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes.dex */
        public static final class Error extends ClearAction {
            public static final Error INSTANCE = new Error();

            private Error() {
                super(null);
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction$Transaction;", "Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$ClearAction;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes.dex */
        public static final class Transaction extends ClearAction {
            public static final Transaction INSTANCE = new Transaction();

            private Transaction() {
                super(null);
            }
        }

        private ClearAction() {
        }

        public /* synthetic */ ClearAction(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ClearDatabaseService$Companion;", "", "", "CLEAN_DATABASE_SERVICE_NAME", "Ljava/lang/String;", ClearDatabaseService.EXTRA_ITEM_TO_CLEAR, "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ClearDatabaseService() {
        super(CLEAN_DATABASE_SERVICE_NAME);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(@Nullable Intent intent) {
        Serializable serializableExtra = intent != null ? intent.getSerializableExtra(EXTRA_ITEM_TO_CLEAR) : null;
        if (serializableExtra instanceof ClearAction.Transaction) {
            RepositoryProvider repositoryProvider = RepositoryProvider.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            repositoryProvider.initialize(applicationContext);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ClearDatabaseService$onHandleIntent$1(null), 3, null);
            NotificationHelper.Companion.clearBuffer();
            new NotificationHelper(this).dismissTransactionsNotification();
        } else if (serializableExtra instanceof ClearAction.Error) {
            RepositoryProvider repositoryProvider2 = RepositoryProvider.INSTANCE;
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
            repositoryProvider2.initialize(applicationContext2);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ClearDatabaseService$onHandleIntent$2(null), 3, null);
            new NotificationHelper(this).dismissErrorsNotification();
        }
    }
}
